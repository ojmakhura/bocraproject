// Generated by andromda-angular cartridge (view\view.component.imp.ts.vsl) CAN EDIT!
import { Component, Injector } from '@angular/core';
import {
  EditLicenceTypeComponent,
  EditLicenceTypeDeleteForm,
  EditLicenceTypeVarsForm,
} from '@app/view/licence/type/edit-licence-type.component';
import { EditLicenceTypeSaveForm } from '@app/view/licence/type/edit-licence-type.component';
import { EditLicenceTypeSearchForm } from '@app/view/licence/type/edit-licence-type.component';
import { LicenceTypeVO } from '@app/model/bw/org/bocra/portal/licence/type/licence-type-vo';
import * as licenceTypeActions from '@app/store/licence/type/licence-type.actions';
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
  selector: 'app-edit-licence-type',
  templateUrl: './edit-licence-type.component.html',
  styleUrls: ['./edit-licence-type.component.scss'],
})
export class EditLicenceTypeComponentImpl extends EditLicenceTypeComponent {
  protected keycloakService: KeycloakService;

  constructor(private injector: Injector) {
    super(injector);
    this.keycloakService = injector.get(KeycloakService);
    this.licensees$ = this.store.pipe(select(LicenseSelectors.selectLicensees));
  }

  beforeOnInit() {}

  afterOnInit() {
    if (this.useCaseScope.pageVariables['id']) {
      this.store.dispatch(licenceTypeActions.findById({ id: this.useCaseScope.pageVariables['id'] }));
    }

    this.licenceType$.subscribe((licenceType) => {
      this.setEditLicenceTypeSaveForm({ licenceType: licenceType } as EditLicenceTypeSaveForm);
    });
  }

  doNgAfterViewInit() {}

  handleFormChanges(change: any) {}

  doNgOnDestroy() {}

  /**
   * This method may be overwritten
   */
  afterSetEditLicenceTypeSaveForm(form: EditLicenceTypeSaveForm): void {}

  /**
   * This method may be overwritten
   */
  beforeEditLicenceTypeSave(form: EditLicenceTypeSaveForm): void {
    if (form.licenceType?.id) {
      form.licenceType.updatedBy = this.keycloakService.getUsername();
      form.licenceType.updatedDate = new Date();
    } else {
      form.licenceType.createdBy = this.keycloakService.getUsername();
      form.licenceType.createdDate = new Date();
    }

    this.store.dispatch(licenceTypeActions.save({ licenceType: form.licenceType }));
  }

  /**
   * This method may be overwritten
   */
  afterEditLicenceTypeSave(form: EditLicenceTypeSaveForm): void {}

  /**
   * This method may be overwritten
   */
  afterSetEditLicenceTypeSearchForm(form: EditLicenceTypeSearchForm): void {}

  /**
   * This method may be overwritten
   */
  beforeEditLicenceTypeSearch(form: EditLicenceTypeSearchForm): void {}

  /**
   * This method may be overwritten
   */
  afterEditLicenceTypeSearch(form: EditLicenceTypeSearchForm): void {}

  afterSetEditLicenceTypeDeleteForm(form: EditLicenceTypeDeleteForm): void {}

  beforeEditLicenceTypeDelete(form: EditLicenceTypeDeleteForm): void {
    this.store.dispatch(licenceTypeActions.remove({id: form?.licenceType?.id}));
  }

  afterEditLicenceTypeDelete(form: EditLicenceTypeDeleteForm): void {}

  afterSetEditLicenceTypeVarsForm(form: EditLicenceTypeVarsForm): void {}

  handleLicenceTypeLicenseesAddDialog(): void {}

  handleLicenceTypeFormsAddDialog(): void {}

  handleLicenceTypeLicenseesSearch(): void {
    let criteria: LicenseeCriteria = new  LicenseeCriteria();
    criteria.licenseeName = this.licenceTypeLicenseesSearchField.value;
    this.store.dispatch(LicenseeActions.search({criteria}));
  }

  handleLicenceTypeLicenseesSelected(event: MatCheckboxChange, element: LicenseeVO): void {}

  handleLicenceTypeFormsSearch(): void {

    let criteria: FormCriteria = new  FormCriteria();
    criteria.code = this.licenceTypeFormsSearchField.value;
    criteria.formName = this.licenceTypeFormsSearchField.value;
    this.store.dispatch(LicenseeActions.search({criteria}));
  }

  handleLicenceTypeFormsSelected(event: MatCheckboxChange, element: FormVO): void {}
}
