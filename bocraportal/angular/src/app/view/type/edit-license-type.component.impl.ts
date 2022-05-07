// Generated by andromda-angular cartridge (view\view.component.imp.ts.vsl) CAN EDIT!
import { Component, Injector } from '@angular/core';
import {
  EditLicenseTypeComponent,
  EditLicenseTypeDeleteForm,
  EditLicenseTypeVarsForm,
} from '@app/view/type/edit-license-type.component';
import { EditLicenseTypeSaveForm } from '@app/view/type/edit-license-type.component';
import { EditLicenseTypeSearchForm } from '@app/view/type/edit-license-type.component';
import { LicenseTypeVO } from '@app/model/bw/org/bocra/portal/type/license-type-vo';
import * as licenseTypeActions from '@app/store/type/license-type.actions';
import { Observable } from 'rxjs';
import { KeycloakService } from 'keycloak-angular';
import { select } from '@ngrx/store';
import * as LicenseSelectors from '@app/store/licensee/licensee.selectors';
import * as LicenseeActions from '@app/store/licensee/licensee.actions';
import * as FormActions from '@app/store/form/form.actions';
import { FormVO } from '@app/model/bw/org/bocra/portal/form/form-vo';
import { LicenseeVO } from '@app/model/bw/org/bocra/portal/licensee/licensee-vo';
import { MatCheckboxChange } from '@angular/material/checkbox';
import { LicenseeCriteria } from '@app/model/bw/org/bocra/portal/licensee/licensee-criteria';
import { FormCriteria } from '@app/model/bw/org/bocra/portal/form/form-criteria';

@Component({
  selector: 'app-edit-license-type',
  templateUrl: './edit-license-type.component.html',
  styleUrls: ['./edit-license-type.component.scss'],
})
export class EditLicenseTypeComponentImpl extends EditLicenseTypeComponent {
  protected keycloakService: KeycloakService;

  constructor(private injector: Injector) {
    super(injector);
    this.keycloakService = injector.get(KeycloakService);
    this.licensees$ = this.store.pipe(select(LicenseSelectors.selectLicensees));
  }

  beforeOnInit() {}

  afterOnInit() {
    if (this.useCaseScope.pageVariables['id']) {
      this.store.dispatch(licenseTypeActions.findById({ id: this.useCaseScope.pageVariables['id'] }));
    }

    this.licenseType$.subscribe((licenseType) => {
      this.setEditLicenseTypeSaveForm({ licenseType: licenseType } as EditLicenseTypeSaveForm);
    });
  }

  doNgAfterViewInit() {}

  handleFormChanges(change: any) {}

  doNgOnDestroy() {}

  /**
   * This method may be overwritten
   */
  afterSetEditLicenseTypeSaveForm(form: EditLicenseTypeSaveForm): void {}

  /**
   * This method may be overwritten
   */
  beforeEditLicenseTypeSave(form: EditLicenseTypeSaveForm): void {
    if (form.licenseType?.id) {
      form.licenseType.updatedBy = this.keycloakService.getUsername();
      form.licenseType.updatedDate = new Date();
    } else {
      form.licenseType.createdBy = this.keycloakService.getUsername();
      form.licenseType.createdDate = new Date();
    }

    this.store.dispatch(licenseTypeActions.save({ licenseType: form.licenseType }));
  }

  /**
   * This method may be overwritten
   */
  afterEditLicenseTypeSave(form: EditLicenseTypeSaveForm): void {}

  /**
   * This method may be overwritten
   */
  afterSetEditLicenseTypeSearchForm(form: EditLicenseTypeSearchForm): void {}

  /**
   * This method may be overwritten
   */
  beforeEditLicenseTypeSearch(form: EditLicenseTypeSearchForm): void {}

  /**
   * This method may be overwritten
   */
  afterEditLicenseTypeSearch(form: EditLicenseTypeSearchForm): void {}

  afterSetEditLicenseTypeDeleteForm(form: EditLicenseTypeDeleteForm): void {}

  beforeEditLicenseTypeDelete(form: EditLicenseTypeDeleteForm): void {
    this.store.dispatch(licenseTypeActions.remove({id: form?.licenseTypeId}));
  }

  afterEditLicenseTypeDelete(form: EditLicenseTypeDeleteForm): void {}

  afterSetEditLicenseTypeVarsForm(form: EditLicenseTypeVarsForm): void {}

  handleLicenseTypeLicenseesAddDialog(): void {}

  handleLicenseTypeFormsAddDialog(): void {}

  handleLicenseTypeLicenseesSearch(): void {
    let searchCriteria: LicenseeCriteria = new  LicenseeCriteria();
    searchCriteria.licenseeName = this.licenseTypeLicenseesSearchField.value;
    this.store.dispatch(LicenseeActions.searchLicensees({searchCriteria}));
  }

  handleLicenseTypeLicenseesSelected(event: MatCheckboxChange, element: LicenseeVO): void {}

  handleLicenseTypeFormsSearch(): void {

    let searchCriteria: FormCriteria = new  FormCriteria();
    searchCriteria.code = this.licenseTypeFormsSearchField.value;
    searchCriteria.formName = this.licenseTypeFormsSearchField.value;
    this.store.dispatch(LicenseeActions.searchLicensees({searchCriteria}));
  }

  handleLicenseTypeFormsSelected(event: MatCheckboxChange, element: FormVO): void {}
}
