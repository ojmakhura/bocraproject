// Generated by andromda-angular cartridge (view\view.component.imp.ts.vsl) CAN EDIT!
import { Component, Injector } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { DocumentVO } from '@app/model/bw/org/bocra/portal/document/document-vo';
import { LicenseeFormVO } from '@app/model/bw/org/bocra/portal/licensee/form/licensee-form-vo';
import * as DocumentActions from '@app/store/document/document.actions';
import * as DocumentSelectors from '@app/store/document/document.selectors';
import * as FormActions from '@app/store/form/form.actions';
import * as LicenceActions from '@app/store/licence/licence.actions';
import * as LicenceSelectors from '@app/store/licence/licence.selectors';
import * as LicenseeActions from '@app/store/licensee/licensee.actions';
import * as LicenseeSelectors from '@app/store/licensee/licensee.selectors';
import * as SectorActions from '@app/store/sector/sector.actions';
import * as ViewActions from '@app/store/view/view.actions';
import * as ViewSelectors from '@app/store/view/view.selectors';
import * as LicenseeSectorSelectors from '@app/store/licensee/sector/licensee-sector.selectors';
import * as LicenseeSectorActions from '@app/store/licensee/sector/licensee-sector.actions';
import * as LicenseeFormSelectors from '@app/store/licensee/form/licensee-form.selectors';
import * as LicenseeFormActions from '@app/store/licensee/form/licensee-form.actions';
import {
  EditLicenseeComponent,
  EditLicenseeDeleteForm,
  EditLicenseeDocumentsForm,
  EditLicenseeNewDocumentForm, EditLicenseeSaveForm, EditLicenseeVarsForm
} from '@app/view/licensee/edit-licensee.component';
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

  constructor(private injector: Injector) {
    super(injector);
    this.keycloakService = injector.get(KeycloakService);
    this.licenseeDocuments$ = this.store.pipe(select(DocumentSelectors.selectDocuments));
    this.licenseeLicences$ = this.store.pipe(select(LicenceSelectors.selectLicences));
    this.unauthorisedUrls$ = this.store.pipe(select(ViewSelectors.selectUnauthorisedUrls));
    this.sectorRemoved$ = this.store.pipe(select(LicenseeSectorSelectors.selectRemoved));
    this.formRemoved$ = this.store.pipe(select(LicenseeFormSelectors.selectRemoved));
    this.documentDelete$ = this.store.pipe(select(DocumentSelectors.selectRemoved));
    this.licenseeDocument$ = this.store.pipe(select(LicenseeSelectors.selectDocument));
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
      if(document?.id)
        this.addToLicenseeDocuments(document);
    });
  }

  doNgOnDestroy() { }

  override deleteFromLicenseeForms(index: number) {
    if (confirm('Are you sure you want to delete the licensee form?')) {

      this.store.dispatch(
        LicenseeFormActions.remove({
          id: this.licenseeForms[index].id,
          loading: true
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
          loading: true
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
    if(confirm('Are you sure you want to delete the licensee document?')) {
      const doc: DocumentVO = this.licenseeDocuments[index];
      this.store.dispatch(
        DocumentActions.remove({
          id: doc.id,
          loading: true
        })
      );
      this.documentDelete$.subscribe(removed => {
        if(removed)
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
    if (form?.licensee?.id && confirm("Are you sure you want to delete the licensee?")) {
      this.store.dispatch(
        LicenseeActions.remove({
          id: form?.licensee?.id,
          loading: false,
        })
      );
      this.editLicenseeFormReset();
    } else {
      this.store.dispatch(LicenseeActions.licenseeFailure({ messages: ['Please select something to delete'] }));
    }
  }

  override licenseeLicencesSearch(): void {
    let criteria: string = '';
    criteria = this.licenseeLicencesSearchField.value;
    this.store.dispatch(
      LicenceActions.search({
        criteria: { licenceNumber: criteria },
        loading: true,
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
      })
    );
  }

  override licenseeFormsSearch(): void {
    let criteria: string = '';
    criteria = this.licenseeFormsSearchField.value;
    this.store.dispatch(
      FormActions.searchForms({
        criteria: { code: criteria, formName: criteria },
        loading: true,
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
      })
    );
  }

  override beforeEditLicenseeNewDocument(form: EditLicenseeNewDocumentForm): void {
    console.log(form);
    console.log(this.useCaseScope.pageVariables);
    console.log(this.licensee);
  }

  override afterEditLicenseeNewDocument(form: EditLicenseeNewDocumentForm, dialogData: any): void {
    if (dialogData) {
      console.log(dialogData);
      this.store.dispatch(
        LicenseeActions.addDocument({
          id: this.licenseeId,
          documentTypeId: dialogData.document.documentType.id,
          file: dialogData.document.file,
          fileName: dialogData.document.documentName,
          loading: true,
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

  fileDownload(doc: DocumentVO) {
    console.log(doc);
    this.documentRestController.downloadFile(doc.id).subscribe((response: any) => {
      //console.log(response);
    });
  }
}
