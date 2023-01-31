// Generated by andromda-angular cartridge (view\view.component.imp.ts.vsl) CAN EDIT!
import { Component, Injector } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { DocumentVO } from '@app/model/bw/org/bocra/portal/document/document-vo';
import { LicenseeFormVO } from '@app/model/bw/org/bocra/portal/licensee/form/licensee-form-vo';
import { LicenseeShareholderVO } from '@app/model/bw/org/bocra/portal/licensee/shares/licensee-shareholder-vo';
import * as DocumentActions from '@app/store/document/document.actions';
import * as DocumentSelectors from '@app/store/document/document.selectors';
import * as FormActions from '@app/store/form/form.actions';
import * as LicenceActions from '@app/store/licence/licence.actions';
import * as LicenceSelectors from '@app/store/licence/licence.selectors';
import * as LicenseeFormActions from '@app/store/licensee/form/licensee-form.actions';
import * as LicenseeFormSelectors from '@app/store/licensee/form/licensee-form.selectors';
import * as LicenseeActions from '@app/store/licensee/licensee.actions';
import * as LicenseeSectorActions from '@app/store/licensee/sector/licensee-sector.actions';
import * as LicenseeSectorSelectors from '@app/store/licensee/sector/licensee-sector.selectors';
import * as LicenseeShareholderActions from '@app/store/licensee/shares/licensee-shareholder.actions';
import * as LicenseeShareholderSelectors from '@app/store/licensee/shares/licensee-shareholder.selectors';
import * as SectorActions from '@app/store/sector/sector.actions';
import * as ViewActions from '@app/store/view/view.actions';
import * as ViewSelectors from '@app/store/view/view.selectors';
import { saveAs } from 'file-saver';

import * as ShareholderSelectors from '@app/store/shareholder/shareholder.selectors';
import {
  EditLicenseeComponent,
  EditLicenseeDeleteForm,
  EditLicenseeDocumentsForm,
  EditLicenseeNewDocumentForm, EditLicenseeNewShareholderForm, EditLicenseeSaveForm, EditLicenseeVarsForm
} from '@app/view/licensee/edit-licensee.component';
import { DocumentMetadataTarget } from '@model/bw/org/bocra/portal/document/document-metadata-target';
import { select } from '@ngrx/store';
import { KeycloakService } from 'keycloak-angular';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-edit-licensee',
  templateUrl: './edit-licensee.component.html',
  styleUrls: ['./edit-licensee.component.scss'],
})
export class EditLicenseeComponentImpl extends EditLicenseeComponent {
  protected keycloakService: KeycloakService;
  unauthorisedUrls$: Observable<string[]>;
  deleteUnrestricted: boolean = true;
  sectorRemoved$: Observable<boolean>;
  formRemoved$: Observable<boolean>;
  documentDelete$: Observable<boolean>;
  licenseeDocument$: Observable<DocumentVO>;
  licenseeShareholder$: Observable<LicenseeShareholderVO>
  licenseeShareholderId: any;
  file$: Observable<Blob>;

  constructor(private injector: Injector) {
    super(injector);
    this.keycloakService = injector.get(KeycloakService);
    this.licenseeDocuments$ = this.store.pipe(select(DocumentSelectors.selectDocuments));
    this.licenseeLicences$ = this.store.pipe(select(LicenceSelectors.selectLicences));
    this.unauthorisedUrls$ = this.store.pipe(select(ViewSelectors.selectUnauthorisedUrls));
    this.sectorRemoved$ = this.store.pipe(select(LicenseeSectorSelectors.selectRemoved));
    this.formRemoved$ = this.store.pipe(select(LicenseeFormSelectors.selectRemoved));
    this.documentDelete$ = this.store.pipe(select(DocumentSelectors.selectRemoved));
    this.licenseeDocument$ = this.store.pipe(select(DocumentSelectors.selectDocument));
    this.licenseeShareholder$ = this.store.pipe(select(LicenseeShareholderSelectors.selectLicenseeShareholder))
    this.licenseeShareholders$ = this.store.pipe(select(LicenseeShareholderSelectors.selectLicenseeShareholders));
    this.file$ = this.store.pipe(select(DocumentSelectors.selectFile));
  }

  beforeOnInit(form: EditLicenseeVarsForm): EditLicenseeVarsForm {
    return form;
  }

  override afterOnInit() { }

  override doNgAfterViewInit(): void {

    this.store.dispatch(
      ViewActions.loadViewAuthorisations({
        viewUrl: "/licensee/edit-licensee",
        roles: this.keycloakService.getUserRoles(),
        loading: true
      })
    );

    this.route.queryParams.subscribe((queryParams: any) => {
      if (queryParams?.id) {
        this.store.dispatch(
          LicenseeActions.findById({
            id: queryParams?.id,
            loading: true,
            loaderMessage: 'Loading licensee by id ...'
          })
        );
      }
    });

    this.licensee$.subscribe((licensee) => {
      this.setEditLicenseeFormValue({ licensee: licensee });
    });

    this.unauthorisedUrls$.subscribe(restrictedItems => {
      restrictedItems.forEach(item => {
        if (item === '/licensee/edit-licensee/{button:delete}') {
          this.deleteUnrestricted = false;
        }
      });
    });

    this.licenseeSector$.subscribe(ls => {
      if (ls?.id)
        this.addToLicenseeSectors(ls);
    });

    this.licenseeForm$.subscribe(lf => {
      if (lf?.id) {
        this.addToLicenseeForms(lf);
      }
    });

    this.licenseeDocument$.subscribe(document => {
      if (document?.id)
        this.addToLicenseeDocuments(document);
    });

    this.licenseeShareholder$.subscribe(ls => {
      if (ls?.id) {
        this.licenseeShareholderId = ls?.id;
        this.addToLicenseeShareholders(ls);
      }
    })
  }

  doNgOnDestroy() { }

  override deleteFromLicenseeForms(index: number) {
    if (confirm('Are you sure you want to delete the licensee form?')) {

      this.store.dispatch(
        LicenseeFormActions.remove({
          id: this.licenseeForms[index].id,
          loading: true,
          loaderMessage: 'Removing licensee ...'
        })
      );

      this.formRemoved$.subscribe(removed => {
        if (removed) {
          this.handleDeleteFromLicenseeForms(this.licenseeForms[index]);
          this.licenseeFormsControl.removeAt(index);
        }
      });
    }
  }

  override deleteFromLicenseeSectors(index: number) {
    if (confirm('Are you sure you want to delete the licensee sector?')) {
      this.store.dispatch(
        LicenseeSectorActions.remove({
          id: this.licenseeSectors[index].id,
          loading: true,
          loaderMessage: 'Removing sector from licensee ...'
        })
      );

      this.sectorRemoved$.subscribe(removed => {
        if (removed) {

          this.handleDeleteFromLicenseeSectors(this.licenseeSectors[index]);
          this.licenseeSectorsControl.removeAt(index);
        }
      });
    }
  }

  override deleteFromLicenseeDocuments(index: number) {
    if (confirm('Are you sure you want to delete the licensee document?')) {
      const doc: DocumentVO = this.licenseeDocuments[index];
      this.store.dispatch(
        DocumentActions.remove({
          id: doc.id,
          loading: true,
          loaderMessage: 'Remove document from licensee ...'
        })
      );
      this.documentDelete$.subscribe(removed => {
        if (removed)
          this.licenseeDocumentsControl.removeAt(index);
      });

    }
  }


  /**
   * This method may be overwritten
   */
  afterSetEditLicenseeSaveForm(form: EditLicenseeSaveForm): void { }

  /**
   * This method may be overwritten
   */
  override beforeEditLicenseeSave(form: EditLicenseeSaveForm): void {
    if (this.editLicenseeForm.valid) {
      if (form.licensee?.id) {
        form.licensee.updatedBy = this.keycloakService.getUsername();
        form.licensee.updatedDate = new Date();
      } else {
        form.licensee.createdBy = this.keycloakService.getUsername();
        form.licensee.createdDate = new Date();
      }

      this.store.dispatch(
        LicenseeActions.save({
          licensee: form.licensee,
          loading: true,
          loaderMessage: 'Saving licensees ...'
        })
      );
    }
    else {
      let messages: string[] = []
      if (!this.licenseeControl.valid) {
        messages.push("Licensee has errors, Please fill in the required form fields.")
      }
      if (!this.licenseeStatusControl.valid) {
        messages.push("Licensee status is missing!")
      }
      if (!this.licenseeUinControl.valid) {
        messages.push("Licensee Uin is missing!")
      }
      if (!this.licenseeLicenseeNameControl.valid) {
        messages.push("Licensee name is missing!")
      }
      this.store.dispatch(LicenseeActions.licenseeFailure({ messages: messages }));
    }
  }

  override beforeEditLicenseeDelete(form: EditLicenseeDeleteForm): void {
    if (form?.licensee?.id) {
      if (!(form?.licensee?.users.length > 0) && !(form?.licensee?.forms.length > 0) && !(form?.licensee?.documents.length > 0) && !(form?.licensee?.sectors.length > 0) && confirm('Are you sure you want to delete the form activation?')) {

        this.store.dispatch(
          LicenseeActions.remove({
            id: form?.licensee?.id,
            loading: false,
            loaderMessage: 'Removing licensee ...'
          })
        );
        this.editLicenseeFormReset();
      } else {
        this.store.dispatch(LicenseeActions.licenseeFailure({ messages: ['This Licensee can not be deleted it has attachments  '] }));
      }
    }

    else {
      this.store.dispatch(LicenseeActions.licenseeFailure({ messages: ['Please select something to delete'] }));
    }
  }

  override afterEditLicenseeNewShareholder(form: EditLicenseeNewShareholderForm, dialogData: any): void {
    if (dialogData) {
      this.store.dispatch(
        LicenseeShareholderActions.create({
          licenseeId: this.licenseeId,
          shareholderId: dialogData.shareholder.shareholder.id,
          numberOfShares: dialogData.shareholder.numberOfShares,
          loading: true,
          loaderMessage: 'Creating licensee shareholder ...'
        })
      );

      this.licenseeShareholder$.subscribe(ls => {
        if (ls?.id) {
          this.licenseeShareholderId = ls?.id;
          for (var document of dialogData.shareholder.documents) {
            if (!document.id) {
              this.store.dispatch(
                DocumentActions.uploadFile({
                  metadataTarget: DocumentMetadataTarget.LICENSEE_SHAREHOLDER,
                  metadataTargetId: this.licenseeShareholderId,
                  documentTypeId: document.documentType.id,
                  file: document.file,
                  fileName: document.documentName,
                  loading: true,
                  loaderMessage: 'Add licensee document ...'
                })
              );
            }
          }
        }
      });
    }
  }

  override licenseeLicencesSearch(): void {
    let criteria: string = '';
    criteria = this.licenseeLicencesSearchField.value;
    this.store.dispatch(
      LicenceActions.search({
        criteria: { licenceNumber: criteria },
        loading: true,
        loaderMessage: 'Loading new form submissions ...'
      })
    );
  }

  override licenseeDocumentsSearch(): void {
    let criteria: string = '';
    criteria = this.licenseeDocumentsSearchField.value;
    this.store.dispatch(
      DocumentActions.search({
        criteria: criteria,
        loading: true,
        loaderMessage: 'Searching documents ...'
      })
    );
  }

  override licenseeFormsSearch(): void {
    let criteria: string = '';
    criteria = this.licenseeFormsSearchField.value;
    this.store.dispatch(
      FormActions.searchForms({
        criteria: { formName: criteria },
        loading: true,
        loaderMessage: 'Searching forms ...'
      })
    );
  }

  override beforeEditLicenseeDocuments(form: EditLicenseeDocumentsForm): void { }

  override licenseeSectorsSearch(): void {
    let criteria: string = '';
    criteria = this.licenseeSectorsSearchField.value;
    this.store.dispatch(
      SectorActions.search({
        criteria: criteria,
        loading: true,
        loaderMessage: 'Searching sectors ...'
      })
    );
  }

  override beforeEditLicenseeNewDocument(form: EditLicenseeNewDocumentForm): void {
  }

  override afterEditLicenseeNewDocument(form: EditLicenseeNewDocumentForm, dialogData: any): void {
    if (dialogData) {
      this.store.dispatch(
        DocumentActions.uploadFile({
          metadataTarget: DocumentMetadataTarget.LICENSEE,
          metadataTargetId: this.licenseeId,
          documentTypeId: dialogData.document.documentType.id,
          file: dialogData.document.file,
          fileName: dialogData.document.documentName,
          loading: true,
          loaderMessage: 'Add licensee document ...'
        })
      );
    }
  }

  override createDocumentVOGroup(value: DocumentVO): FormGroup {
    return this.formBuilder.group({
      id: [value?.id],
      createdBy: [value?.createdBy],
      updatedBy: [value?.updatedBy],
      createdDate: [value?.createdDate],
      updatedDate: [value?.updatedDate],
      documentName: [value?.documentName],
      file: [value?.file],
      documentId: [value?.documentId],
      documentType: {
        id: [value?.documentType?.id],
        code: [value?.documentType?.code],
        name: [value?.documentType?.name],
      },
    });
  }

  override createLicenseeFormVOGroup(value: LicenseeFormVO): FormGroup {
    return this.formBuilder.group({
      id: [value?.id],
      form: {
        id: value?.form?.id,
        code: value?.form?.code,
        formName: value?.form?.formName,
      },
    });
  }

  override createLicenseeShareholderVOGroup(value: LicenseeShareholderVO): FormGroup {
    return this.formBuilder.group({
      id: [value?.id],
      shareholderId: [value?.shareholder?.shareholderId],
      shareholderName: [value?.shareholder?.name],
      numberOfShares: [value?.numberOfShares],
    });
  }

  
  fileDownload(documentId: number, documentName: string) {

    this.store.dispatch(
      DocumentActions.downloadFile({
        documentId: documentId,
        loading: true,
        loaderMessage: 'Downloading document ...'
      })
    );

    this.file$.subscribe((file: any) => {
      if(file) {
        let blob:any = file as Blob;
        const url = window.URL.createObjectURL(blob);
        saveAs(blob, documentName);
      }
    });
  }
}
