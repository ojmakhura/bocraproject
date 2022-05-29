// Generated by andromda-angular cartridge (view\view.component.imp.ts.vsl) CAN EDIT!
import { Component, Injector } from '@angular/core';
import {
  EditLicenceTypeComponent,
  EditLicenceTypeDeleteForm,
  EditLicenceTypeVarsForm,
} from '@app/view/licence/type/edit-licence-type.component';
import { EditLicenceTypeSaveForm } from '@app/view/licence/type/edit-licence-type.component';
import { EditLicenceTypeSearchForm } from '@app/view/licence/type/edit-licence-type.component';
import * as licenceTypeActions from '@app/store/licence/type/licence-type.actions';
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
import { LicenceVO } from '@app/model/bw/org/bocra/portal/licence/licence-vo';

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
  }

  beforeOnInit() {}

  afterOnInit() {
    if (this.useCaseScope.pageVariables['id']) {
      this.store.dispatch(licenceTypeActions.findById({
        id: this.useCaseScope.pageVariables['id'],
        loading: true
      }));
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

    this.store.dispatch(licenceTypeActions.save({
      licenceType: form.licenceType,
      loading: true
    }));
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
    this.store.dispatch(licenceTypeActions.remove({
      id: form?.licenceType?.id,
      loading: true
    }));
  }

  afterEditLicenceTypeDelete(form: EditLicenceTypeDeleteForm): void {}

  afterSetEditLicenceTypeVarsForm(form: EditLicenceTypeVarsForm): void {}


  handleLicenceTypeFormsAddDialog(): void {}

  handleLicenceTypeFormsSearch(): void {

    let criteria: FormCriteria = new  FormCriteria();
    criteria.code = this.licenceTypeFormsSearchField.value;
    criteria.formName = this.licenceTypeFormsSearchField.value;
    this.store.dispatch(LicenseeActions.search({
      criteria,
      loading: true
    }));
  }

  handleLicenceTypeFormsSelected(event: MatCheckboxChange, element: FormVO): void {}

  handleLicenceTypeLicencesAddDialog(): void {
  }
  
  handleLicenceTypeLicencesSearch(): void {
  }

  handleLicenceTypeLicencesSelected(event: MatCheckboxChange, data: LicenceVO): void {
  }
}
