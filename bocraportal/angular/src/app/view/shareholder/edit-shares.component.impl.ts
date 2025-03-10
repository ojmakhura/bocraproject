// Generated by andromda-angular cartridge (view\view.component.imp.ts.vsl) CAN EDIT!
import { Component, Injector, Inject } from '@angular/core';
import {
  EditLicenseeAddDocumentForm,
  EditSharesComponent,
  EditSharesVarsForm,
} from '@app/view/shareholder/edit-shares.component';
import * as LicenseeSelectors from '@app/store/licensee/licensee.selectors';
import * as LicenseeActions from '@app/store/licensee/licensee.actions';
import { ShareholderState } from '@app/store/shareholder/shareholder.state';
import * as ShareholderSelectors from '@app/store/shareholder/shareholder.selectors';
import * as ShareholderActions from '@app/store/shareholder/shareholder.actions';
import * as DocumentActions from '@app/store/document/document.actions';
import * as DocumentSelectors from '@app/store/document/document.selectors';
import { MatRadioChange } from '@angular/material/radio';
import { MatCheckboxChange } from '@angular/material/checkbox';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { select } from '@ngrx/store';
import { SelectItem } from '@app/utils/select-item';
import { DocumentVO } from '@app/model/bw/org/bocra/portal/document/document-vo';
import { FormGroup } from '@angular/forms';
import { saveAs } from 'file-saver';
import { Observable } from 'rxjs';
import { LicenseeShareholderVO } from '@app/model/bw/org/bocra/portal/licensee/shares/licensee-shareholder-vo';

@Component({
  selector: 'app-edit-shares',
  templateUrl: './edit-shares.component.html',
  styleUrls: ['./edit-shares.component.scss'],
})
export class EditSharesComponentImpl extends EditSharesComponent {
  file$: Observable<Blob>;

  constructor(@Inject(MAT_DIALOG_DATA) data: any, private injector: Injector) {
    super(data, injector);
    this.licenseeLicensees$ = this.store.pipe(select(LicenseeSelectors.selectLicensees));
    this.licenseeShareholders$ = this.store.pipe(select(ShareholderSelectors.selectShareholders));
    this.licenseeLicenseeBackingList = [];
    this.licenseeShareholderBackingList = [];
    this.file$ = this.store.pipe(select(DocumentSelectors.selectFile));
  }

  override beforeOnInit(form: EditSharesVarsForm): EditSharesVarsForm {
    this.store.dispatch(LicenseeActions.getAll({ loading: true, loaderMessage: 'Loading all licensees ...' }));
    this.licenseeLicensees$.subscribe((licensees) => {
      licensees.forEach((licensee) => {
        let item: SelectItem = new SelectItem();
        item.label = licensee.licenseeName;
        item.value = licensee.id;
        this.licenseeLicenseeBackingList.push(item);
      });
    });

    this.store.dispatch(ShareholderActions.getAll({ loading: true, loaderMessage: 'Loading all shareholders ...' }));
    this.licenseeShareholders$.subscribe((shareholders) => {
      shareholders.forEach((shareholder) => {
        let item: SelectItem = new SelectItem();
        item.label = shareholder.name;
        item.value = shareholder.id;
        this.licenseeShareholderBackingList.push(item);
      });
    });

    if (this.useCaseScope.pageVariables['licensee']) {
      form.licensee = this.useCaseScope.pageVariables['licensee'];
    } else {
      if (!form?.licensee) {
        form.licensee = new LicenseeShareholderVO();
      }
      form.licensee.form = this.dialogData?.form;
    }

    return form;
  }

  doNgOnDestroy(): void {}

  override afterEditLicenseeAddDocument(form: EditLicenseeAddDocumentForm, dialogData: any): void {
    if (dialogData) {
      this.addToLicenseeDocuments(dialogData.document);
    }
  }

  override createDocumentVOGroup(value: DocumentVO): FormGroup {
    return this.formBuilder.group({
      id: [value?.id],
      createdBy: [value?.createdBy],
      updatedBy: [value?.updatedBy],
      createdDate: [value?.createdDate],
      updatedDate: [value?.updatedDate],
      documentType: [value?.documentType],
      documentName: [value?.documentName],
      file: [value?.file],
      documentId: [value?.documentId],
      extension: [value?.extension],
      size: [value?.size],
      metadataTarget: [value?.metadataTarget],
      metadataTargetId: [value?.metadataTargetId],
    });
  }

  fileDownload(documentId: number, documentName: string) {
    this.store.dispatch(
      DocumentActions.downloadFile({
        documentId: documentId,
        loading: true,
        loaderMessage: 'Downloading document ...',
      })
    );

    this.file$.subscribe((file: any) => {
      if (file) {
        let blob: any = file as Blob;
        const url = window.URL.createObjectURL(blob);
        saveAs(blob, documentName);
      }
    });
  }

  override handleDeleteFromLicenseeDocuments(documents: DocumentVO): void {
    if (documents?.id && confirm('Are you sure you want to delete the licensee shareholder document?')) {
      this.store.dispatch(
        DocumentActions.remove({
          id: documents.id,
          loading: true,
          loaderMessage: 'Removing document from licensee shareholder ...',
        })
      );
    }
  }
}
