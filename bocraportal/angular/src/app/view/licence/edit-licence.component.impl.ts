// Generated by andromda-angular cartridge (view\view.component.imp.ts.vsl) CAN EDIT!
import { Component, Injector } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { DocumentVO } from '@app/model/bw/org/bocra/portal/document/document-vo';
import { LicenceTypeCriteria } from '@app/model/bw/org/bocra/portal/licence/type/licence-type-criteria';
import * as LicenceActions from '@app/store/licence/licence.actions';
import * as LicenceSelectors from '@app/store/licence/licence.selectors';
import * as LicenseeActions from '@app/store/licensee/licensee.actions';
import * as LicenseeSelectors from '@app/store/licensee/licensee.selectors';
import * as LicenceTypeActions from '@app/store/licence/type/licence-type.actions';
import * as LicenceTypeSelectors from '@app/store/licence/type/licence-type.selectors';
import * as DocumentActions from '@app/store/document/document.actions';
import * as DocumentSelectors from '@app/store/document/document.selectors';
import * as ViewActions from '@app/store/view/view.actions';
import * as ViewSelectors from '@app/store/view/view.selectors';
import { EditLicenceComponent, EditLicenceDeleteForm, EditLicenceNewDocumentForm, EditLicenceSaveForm, EditLicenceVarsForm } from '@app/view/licence/edit-licence.component';
import { select } from '@ngrx/store';
import { KeycloakService } from 'keycloak-angular';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-edit-licence',
  templateUrl: './edit-licence.component.html',
  styleUrls: ['./edit-licence.component.scss'],
})
export class EditLicenceComponentImpl extends EditLicenceComponent {
  protected keycloakService: KeycloakService;
  unauthorisedUrls$: Observable<string[]>;
  deleteUnrestricted: boolean = true;
  documentDelete$: Observable<boolean>;
  licenceDocument$: Observable<DocumentVO>;

  constructor(private injector: Injector) {
    super(injector);
    this.keycloakService = injector.get(KeycloakService);
    this.unauthorisedUrls$ = this.store.pipe(select(ViewSelectors.selectUnauthorisedUrls));
    this.licenceLicenceTypes$ = this.store.pipe(select(LicenceTypeSelectors.selectLicenceTypes));
    this.documentDelete$ = this.store.pipe(select(DocumentSelectors.selectRemoved));
    this.licenceLicensees$ = this.store.pipe(select(LicenseeSelectors.selectLicensees));
    this.licenceDocument$ = this.store.pipe(select(LicenceSelectors.selectDocument));
  }

  override beforeOnInit(form: EditLicenceVarsForm): EditLicenceVarsForm {
    return form;
  }

  override doNgAfterViewInit(): void {
    this.route.queryParams.subscribe((queryParams: any) => {
      if (queryParams?.id) {
        this.store.dispatch(
          LicenceActions.findById({
            id: queryParams?.id,
            loading: true,
            loaderMessage: 'Find licence by id ...'
          })
        );
      }
    });

    this.licence$.subscribe((licence) => {
      this.setEditLicenceFormValue({ licence: licence });
    });

    this.store.dispatch(
      ViewActions.loadViewAuthorisations({
        viewUrl: "/licence/edit-licence",
        roles: this.keycloakService.getUserRoles(),
        loading: true
      })
    );

    this.unauthorisedUrls$.subscribe(restrictedItems => {
      restrictedItems.forEach(item => {
        if (item === '/licence/edit-licence/{button:delete}') {
          this.deleteUnrestricted = false;
        }
      });
    });

    this.licenceDocument$.subscribe(document => {
      this.addToLicenceDocuments(document);
    });
  }

  fileDownload(documentId: number) {

  }

  override doNgOnDestroy() { }

  /**
   * This method may be overwritten
   */
  override beforeEditLicenceSave(form: EditLicenceSaveForm): void {
    if (form.licence?.id) {
      form.licence.updatedBy = this.keycloakService.getUsername();
      form.licence.updatedDate = new Date();
    } else {
      form.licence.createdBy = this.keycloakService.getUsername();
      form.licence.createdDate = new Date();
    }

    this.store.dispatch(
      LicenceActions.save({
        licence: form.licence,
        loading: true,
        loaderMessage: 'Saving licence ...'
      })
    );
  }

  override beforeEditLicenceDelete(form: EditLicenceDeleteForm): void {
    if(form?.licence?.id){
      if(!(form?.licence?.documents.length>0) && confirm('Are you sure you want to delete the form activation?')) {

        this.store.dispatch(
          LicenceActions.remove({
            id: form.licence.id,
            loading: true,
            loaderMessage: 'Removing form Licence ...'
          })
        );
        this.editLicenceFormReset();
      }else{
        this.store.dispatch(LicenceActions.licenceFailure({ messages: ['This Licence can not be deleted it has documents attached'] }));
      }
    }

    else {
      this.store.dispatch(LicenceActions.licenceFailure({ messages: ['Please select something to delete'] }));
    }
  }
  override afterEditLicenceNewDocument(form: EditLicenceNewDocumentForm, dialogData: any): void {

    if(dialogData) {
      this.store.dispatch(
        LicenceActions.addDocument({
          id: this.licenceId,
          documentTypeId: dialogData.document.documentType.id,
          file: dialogData.document.file,
          fileName: dialogData.document.documentName,
          loading: true,
          loaderMessage: 'Adding document to licence ...'
        })
      );
    }
  }

  override licenceLicenceTypeSearch(): void {
    let criteria: LicenceTypeCriteria = new LicenceTypeCriteria();
    this.store.dispatch(
      LicenceTypeActions.search({
        criteria: criteria,
        loading: true,
        loaderMessage: 'Searching licence types ...'
      })
    );
  }

  override licenceLicenseeSearch(): void {
    let criteria: string = '';
    criteria = this.licenceLicenseeSearchField.value;
    this.store.dispatch(
      LicenseeActions.search({
        criteria: {licenseeName: criteria },
        loading: true,
        loaderMessage: 'Searching licensees ...'
      })
    );
  }

  override deleteFromLicenceDocuments(index: number) {
    if(confirm('Are you sure you want to delete the licence document?')) {
      const doc: DocumentVO = this.licenceDocuments[index];
      this.store.dispatch(
        DocumentActions.remove({
          id: doc.id,
          loading: true,
          loaderMessage: 'Removing document ...'
        })
      );
      this.documentDelete$.subscribe(removed => {
        this.licenceDocumentsControl.removeAt(index);
      });
      
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
      }
    });
  }
}
