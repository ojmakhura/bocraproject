// Generated by andromda-angular cartridge (view\view.component.imp.ts.vsl) CAN EDIT!
import { Component, Injector } from '@angular/core';
import {
  EditShareholderAddDocumentForm,
  EditShareholderAddShareholderForm,
  EditShareholderComponent,
  EditShareholderDeleteForm,
  EditShareholderSaveForm,
  EditShareholderVarsForm,
} from '@app/view/shareholder/edit-shareholder.component';
import { ShareholderState } from '@app/store/shareholder/shareholder.state';
import * as ShareholderSelectors from '@app/store/shareholder/shareholder.selectors';
import * as ShareholderActions from '@app/store/shareholder/shareholder.actions';
import * as DocumentActions from '@app/store/document/document.actions';
import * as DocumentSelectors from '@app/store/document/document.selectors';
import * as LicenseeShareholderActions from '@app/store/licensee/shares/licensee-shareholder.actions';
import * as LicenseeShareholderSelectors from '@app/store/licensee/shares/licensee-shareholder.selectors';
import { MatRadioChange } from '@angular/material/radio';
import { MatCheckboxChange } from '@angular/material/checkbox';
import { KeycloakService } from 'keycloak-angular';
import { Observable, share } from 'rxjs';
import { select } from '@ngrx/store';
import * as ViewActions from '@app/store/view/view.actions';
import * as ViewSelectors from '@app/store/view/view.selectors';
import { DocumentVO } from '@app/model/bw/org/bocra/portal/document/document-vo';
import { DocumentMetadataTarget } from '@app/model/bw/org/bocra/portal/document/document-metadata-target';
import { FormGroup } from '@angular/forms';
import { LicenseeShareholderVO } from '@app/model/bw/org/bocra/portal/licensee/shares/licensee-shareholder-vo';
import { MatGridTileHeaderCssMatStyler } from '@angular/material/grid-list';
import { saveAs } from 'file-saver';

@Component({
  selector: 'app-edit-shareholder',
  templateUrl: './edit-shareholder.component.html',
  styleUrls: ['./edit-shareholder.component.scss'],
})
export class EditShareholderComponentImpl extends EditShareholderComponent {
  protected keycloakService: KeycloakService;
  unauthorisedUrls$: Observable<string[]>;
  deleteUnrestricted: boolean = true;
  shareholderDocument$: Observable<DocumentVO>;
  shareholderShare$: Observable<LicenseeShareholderVO>;
  licenseeShareholderId: any;
  file$: Observable<Blob>;

  constructor(private injector: Injector) {
    super(injector);
    this.keycloakService = injector.get(KeycloakService);
    this.unauthorisedUrls$ = this.store.pipe(select(ViewSelectors.selectUnauthorisedUrls));
    this.shareholderDocuments$ = this.store.pipe(select(DocumentSelectors.selectDocuments));
    this.shareholderDocument$ = this.store.pipe(select(DocumentSelectors.selectDocument));
    this.shareholderShare$ = this.store.pipe(select(LicenseeShareholderSelectors.selectLicenseeShareholder));
    this.shareholderShares$ = this.store.pipe(select(LicenseeShareholderSelectors.selectLicenseeShareholders));
    this.file$ = this.store.pipe(select(DocumentSelectors.selectFile));
  }

  override beforeOnInit(form: EditShareholderVarsForm): EditShareholderVarsForm {
    return form;
  }

  doNgOnDestroy(): void { }

  override doNgAfterViewInit(): void {
    this.store.dispatch(
      ViewActions.loadViewAuthorisations({
        viewUrl: '/shareholder/edit-shareholder',
        roles: this.keycloakService.getUserRoles(),
        loading: true,
      })
    );

    this.route.queryParams.subscribe((queryParams: any) => {
      if (queryParams?.id) {
        this.store.dispatch(
          ShareholderActions.findById({
            id: queryParams?.id,
            loading: false,
            loaderMessage: 'Find shareholder by id.',
          })
        );
      }
    });

    this.shareholder$.subscribe((shareholder) => {
      this.setEditShareholderFormValue({ shareholder: shareholder });
    });

    this.unauthorisedUrls$.subscribe((restrictedItems) => {
      restrictedItems.forEach((item) => {
        if (item === '/shareholder/edit-shareholder/{button:delete}') {
          this.deleteUnrestricted = false;
        }
      });
    });

    this.shareholderDocument$.subscribe((document) => {
      if (document?.id) {
        this.addToShareholderDocuments(document);
      }
    });

    this.shareholderShare$.subscribe((share) => {
      if (share) {
        let sr: LicenseeShareholderVO | undefined = this.shareholderShares.find((shar, i) => {
          if (shar.id == share.id) {
            this.shareholderSharesControl.at(i).patchValue(share);
            return true;
          } else {
            return false;
          }
        });

        if (!sr) {
          this.addToShareholderShares(share);
        }
      }
    });
  }

  override beforeEditShareholderSave(form: EditShareholderSaveForm): void {
    if (this.editShareholderForm.valid) {
      if (form?.shareholder?.id) {
        form.shareholder.updatedBy = this.keycloakService.getUsername();
        form.shareholder.updatedDate = new Date();
      } else {
        form.shareholder.createdBy = this.keycloakService.getUsername();
        form.shareholder.createdDate = new Date();
      }

      this.store.dispatch(
        ShareholderActions.save({
          shareholder: form.shareholder,
          loading: true,
          loaderMessage: 'Saving shareholder ...',
        })
      );
    } else {
      let messages: string[] = [];
      if (!this.shareholderControl.valid) {
        messages.push('Shareholder has errors, Please fill in the required form fields.');
      }
      if (!this.shareholderIdControl.valid) {
        messages.push('Shareholder Id is missing!');
      }
      if (!this.shareholderNameControl.valid) {
        messages.push('Shareholder name is missing!');
      }
      if (!this.shareholderTypeControl.valid) {
        messages.push('Shareholder type is missing!');
      }
      this.store.dispatch(ShareholderActions.shareholderFailure({ messages: messages }));
    }
  }

  override beforeEditShareholderDelete(form: EditShareholderDeleteForm): void {
    if (form?.shareholder?.id) {
      if (!(form.shareholder?.shares.length > 0) || !(form?.shareholder?.documents.length > 0)) {
        if (confirm('Are you sure you want to delete the shareholder')) {
          this.store.dispatch(
            ShareholderActions.remove({
              id: form?.shareholder?.id,
              loading: false,
              loaderMessage: 'Removing shareholder',
            })
          );
          this.editShareholderFormReset();
        }
      } else {
        this.store.dispatch(
          ShareholderActions.shareholderFailure({
            messages: ['This shareholder can not be deleted it has attachments'],
          })
        );
      }
    } else {
      this.store.dispatch(ShareholderActions.shareholderFailure({ messages: ['Please select something to delete'] }));
    }
  }

  override afterEditShareholderAddDocument(form: EditShareholderAddDocumentForm, dialogData: any): void {
    if (dialogData) {
      this.store.dispatch(
        DocumentActions.uploadFile({
          metadataTarget: DocumentMetadataTarget.SHAREHOLDER,
          metadataTargetId: this.shareholderId,
          documentTypeId: dialogData.document.documentType.id,
          file: dialogData.document.file,
          fileName: dialogData.document.documentName,
          loading: true,
          loaderMessage: 'Add shareholder document ...',
        })
      );
    }
  }

  override handleDeleteFromShareholderDocuments(documents: DocumentVO): void {
    console.log(documents);
    if (documents && confirm('Are you sure you want to delete the licensee document')) {
      this.store.dispatch(
        DocumentActions.remove({
          id: documents.id,
          loading: true,
          loaderMessage: 'Removing document from licensee ...',
        })
      );
    }
  }

  override afterEditShareholderAddShareholder(form: EditShareholderAddShareholderForm, dialogData: any): void {
    if (dialogData && !dialogData.licensee.id) {
      this.store.dispatch(
        LicenseeShareholderActions.create({
          licenseeId: dialogData.licensee.licensee.id,
          shareholderId: this.shareholderId,
          numberOfShares: dialogData.licensee.numberOfShares,
          loading: true,
          loaderMessage: 'Creating licensee shareholder ...',
        })
      );
    } else {
      if (dialogData.licensee.id) {
        this.store.dispatch(
          LicenseeShareholderActions.updateNumberOfShares({
            id: dialogData.licensee.id,
            numberOfShares: dialogData.licensee.numberOfShares,
            loading: false,
            loaderMessage: 'Updating licensee shareholder',
          })
        );
      }
    }
    this.shareholderShare$.subscribe((ls) => {
      if (ls?.id) {
        this.licenseeShareholderId = ls?.id;
        for (var document of dialogData.licensee.documents) {
          if (!document.id) {
            this.store.dispatch(
              DocumentActions.uploadFile({
                metadataTarget: DocumentMetadataTarget.LICENSEE_SHAREHOLDER,
                metadataTargetId: this.licenseeShareholderId,
                documentTypeId: document.documentType.id,
                file: document.file,
                fileName: document.documentName,
                loading: true,
                loaderMessage: 'Add licensee document ...',
              })
            );
          }
        }
      }
    });
  }

  override handleDeleteFromShareholderShares(shares: LicenseeShareholderVO): void {
    if (shares && confirm('Are you sure you want to delete the licensee shareholder?')) {
      this.store.dispatch(
        LicenseeShareholderActions.remove({
          id: shares.id,
          loading: true,
          loaderMessage: 'Removing shareholder from licensee ...',
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

  override createLicenseeShareholderVOGroup(value: LicenseeShareholderVO): FormGroup {
    return this.formBuilder.group({
      id: [value?.id],
      licensee: [value?.licensee],
      numberOfShares: [value?.numberOfShares],
      documents: [value?.documents],
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

  override doEditShareholderShares(shares: LicenseeShareholderVO) {
    this.useCaseScope.queryParams['licensee'] = shares;
    this.useCaseScope.pageVariables['licensee'] = shares;
    this.editShareholderAddShareholder();
  }
}
