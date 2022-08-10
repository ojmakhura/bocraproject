// Generated by andromda-angular cartridge (view\view.component.imp.ts.vsl) CAN EDIT!
import { Component, Injector } from '@angular/core';
import {
  EditLicenseeComponent,
  EditLicenseeDeleteForm,
  EditLicenseeDocumentsForm,
  EditLicenseeNewDocumentForm,
  EditLicenseeNewShareholderForm,
  EditLicenseeVarsForm,
} from '@app/view/licensee/edit-licensee.component';
import { EditLicenseeSaveForm } from '@app/view/licensee/edit-licensee.component';
import { EditLicenseeSearchForm } from '@app/view/licensee/edit-licensee.component';
import * as LicenseeSelectors from '@app/store/licensee/licensee.selectors';
import * as LicenseeActions from '@app/store/licensee/licensee.actions';
import { select } from '@ngrx/store';
import { KeycloakService } from 'keycloak-angular';
import * as SectorActions from '@app/store/sector/sector.actions';
import * as SectorSelectors from '@app/store/sector/sector.selectors';
import * as DocumentActions from '@app/store/document/document.actions';
import * as DocumentSelectors from '@app/store/document/document.selectors';
import * as LicenceActions from '@app/store/licence/licence.actions';
import * as LicenceSelectors from '@app/store/licence/licence.selectors';
import * as FormActions from '@app/store/form/form.actions';

@Component({
  selector: 'app-edit-licensee',
  templateUrl: './edit-licensee.component.html',
  styleUrls: ['./edit-licensee.component.scss'],
})
export class EditLicenseeComponentImpl extends EditLicenseeComponent {
  protected keycloakService: KeycloakService;

  constructor(private injector: Injector) {
    super(injector);
    this.keycloakService = injector.get(KeycloakService);
    this.licenseeDocuments$ = this.store.pipe(select(DocumentSelectors.selectDocuments));
    this.licenseeLicences$ = this.store.pipe(select(LicenceSelectors.selectLicences));
  }

  beforeOnInit(form: EditLicenseeVarsForm): EditLicenseeVarsForm {
    return form;
  }

  override afterOnInit() {}

  override doNgAfterViewInit(): void {
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
  }

  doNgOnDestroy() {}

  /**
   * This method may be overwritten
   */
  afterSetEditLicenseeSaveForm(form: EditLicenseeSaveForm): void {}

  /**
   * This method may be overwritten
   */
  override beforeEditLicenseeSave(form: EditLicenseeSaveForm): void {
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

  override beforeEditLicenseeDocuments(form: EditLicenseeDocumentsForm): void {
    
  }

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
}
