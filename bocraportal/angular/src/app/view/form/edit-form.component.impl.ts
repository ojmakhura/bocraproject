// Generated by andromda-angular cartridge (view\view.component.imp.ts.vsl) CAN EDIT!
import { Component, Injector } from '@angular/core';
import { EditFormComponent } from '@app/view/form/edit-form.component';
import { EditFormSaveForm } from '@app/view/form/edit-form.component';
import { EditFormDeleteForm } from '@app/view/form/edit-form.component';
import { EditFormSearchForm } from '@app/view/form/edit-form.component';
import { EditFormVarsForm } from '@app/view/form/edit-form.component';
import * as LicenseTypeSelectors from '@app/store/type/license-type.selectors';
import * as FormActions from '@app/store/form/form.actions';
import * as FormSelectors from '@app/store/form/form.selectors';
import { select } from '@ngrx/store';
import { LicenseTypeVO } from '@app/model/bw/org/bocra/portal/type/license-type-vo';
import { FormFieldVO } from '@app/model/bw/org/bocra/portal/form/field/form-field-vo';
import { EditFormAddFieldForm } from '@app/view/form/edit-form.component';
import { AddNewFieldComponentImpl } from './add-new-field.component.impl';
import { KeycloakService } from 'keycloak-angular';
import { Observable } from 'rxjs';
import { LicenseTypeCriteria } from '@app/model/bw/org/bocra/portal/type/license-type-criteria';
import * as LicenseTypeActions from '@app/store/type/license-type.actions';
import { MatCheckboxChange } from '@angular/material/checkbox';

@Component({
  selector: 'app-edit-form',
  templateUrl: './edit-form.component.html',
  styleUrls: ['./edit-form.component.scss'],
})
export class EditFormComponentImpl extends EditFormComponent {

  protected keycloakService: KeycloakService;

  constructor(private injector: Injector) {
    super(injector);
    this.licenseTypes$ = this.store.pipe(select(LicenseTypeSelectors.selectLicenseTypes));
    this.keycloakService = injector.get(KeycloakService);
    this.formFields$ = this.store.pipe(select(FormSelectors.selectFormFields));
  }

  beforeOnInit() {
  }

  afterOnInit() {
    
  }

  doNgAfterViewInit() {
    
    if (this.useCaseScope.pageVariables['id']) {
      this.store.dispatch(FormActions.findById({ id: this.useCaseScope.pageVariables['id'] }));
    }

    this.form$.subscribe((form) => {
      this.setEditFormSaveForm({ form: form } as EditFormSaveForm);
    });

    this.formFields$.subscribe(data => {
      console.log(data);
    });
  }

  handleFormChanges(change: any) {
    console.log('chaning');
    
  }

  doNgOnDestroy() {}

  /**
   * This method may be overwritten
   */
  afterSetEditFormVarsForm(form: EditFormVarsForm): void {}

  /**
   * This method may be overwritten
   */
  afterSetEditFormSaveForm(form: EditFormSaveForm): void {}

  /**
   * This method may be overwritten
   */
  beforeEditFormSave(form: EditFormSaveForm): void {
    if(this.form.valid) {
      if(form.form.id) {
        form.form.updatedBy = this.keycloakService.getUsername();
        form.form.updatedDate = new Date();
      } else {

        form.form.createdBy = this.keycloakService.getUsername();
        form.form.createdDate = new Date();
      }
      this.store.dispatch(FormActions.save({form: form.form}));
    }
  }

  /**
   * This method may be overwritten
   */
  afterEditFormSave(form: EditFormSaveForm): void {}

  /**
   * This method may be overwritten
   */
  afterSetEditFormDeleteForm(form: EditFormDeleteForm): void {}

  /**
   * This method may be overwritten
   */
  beforeEditFormDelete(form: EditFormDeleteForm): void {}

  /**
   * This method may be overwritten
   */
  afterEditFormDelete(form: EditFormDeleteForm): void {}

  /**
   * This method may be overwritten
   */
  afterSetEditFormSearchForm(form: EditFormSearchForm): void {}

  /**
   * This method may be overwritten
   */
  beforeEditFormSearch(form: EditFormSearchForm): void {}

  /**
   * This method may be overwritten
   */
  afterEditFormSearch(form: EditFormSearchForm): void {}

  handleFormLicenseTypesAddDialog(): void {}

  handleFormFormFieldsAddDialog(): void {}

  handleFormFormFieldsSearch(): void {}

  handleFormLicenseTypesSearch(): void {
    let criteria: LicenseTypeCriteria = new LicenseTypeCriteria();
    criteria.typeSearch = this.formLicenseTypesSearchField.value;
    this.store.dispatch(LicenseTypeActions.search({searchCriteria: criteria}));
  }

  handleFormLicenseTypesSelected(event: MatCheckboxChange, element: LicenseTypeVO): void {}

  handleFormFormFieldsSelected(event: MatCheckboxChange, element: FormFieldVO): void {}

  afterSetEditFormAddFieldForm(form: EditFormAddFieldForm): void {}

  beforeEditFormAddField(form: EditFormAddFieldForm): void {}

  afterEditFormAddField(form: EditFormAddFieldForm): void {
    const dialogRef = this.dialog.open(AddNewFieldComponentImpl, {});
    dialogRef.afterClosed().subscribe((result) => {
      if (result?.dialogData) {
        let field: FormFieldVO = result.dialogData;
        if(field.id) {
          field.updatedBy = this.keycloakService.getUsername();
          field.updatedDate = new Date();
        } else {
          field.createdBy = this.keycloakService.getUsername();
          field.createdDate = new Date();
          field.form = this.form.value
        }
        this.addToFormFormFields(result.dialogData);
      } 
    });
  }
}
