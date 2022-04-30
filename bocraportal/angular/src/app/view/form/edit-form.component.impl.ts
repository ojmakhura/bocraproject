// Generated by andromda-angular cartridge (view\view.component.imp.ts.vsl) CAN EDIT!
import { Component, Injector } from '@angular/core';
import { EditFormComponent } from '@app/view/form/edit-form.component';
import { EditFormSaveForm } from '@app/view/form/edit-form.component';
import { EditFormDeleteForm } from '@app/view/form/edit-form.component';
import { EditFormSearchForm } from '@app/view/form/edit-form.component';
import { EditFormVarsForm } from '@app/view/form/edit-form.component';
import * as LicenseTypeSelectors from '@app/store/type/license-type.selectors';
import { select } from '@ngrx/store';
import { LicenseTypeVO } from '@app/model/bw/org/bocra/portal/type/license-type-vo';
import { FormFieldVO } from '@app/model/bw/org/bocra/portal/form/field/form-field-vo';
import { EditFormAddFieldForm } from '@app/view/form/edit-form.component';
import { AddNewFieldComponentImpl } from './add-new-field.component.impl';

@Component({
  selector: 'app-edit-form',
  templateUrl: './edit-form.component.html',
  styleUrls: ['./edit-form.component.scss'],
})
export class EditFormComponentImpl extends EditFormComponent {
  constructor(private injector: Injector) {
    super(injector);
    this.licenseTypes$ = this.store.pipe(select(LicenseTypeSelectors.selectLicenseTypes));
  }

  beforeOnInit() {}

  afterOnInit() {}

  doNgAfterViewInit() {}

  handleFormChanges(change: any) {}

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
  beforeEditFormSave(form: EditFormSaveForm): void {}

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

  handleFormLicenseTypesSearch(): void {}

  handleFormLicenseTypesSelected(element: LicenseTypeVO, index: number): void {}

  handleFormFormFieldsSelected(element: FormFieldVO, index: number): void {}

  afterSetEditFormAddFieldForm(form: EditFormAddFieldForm): void {}

  beforeEditFormAddField(form: EditFormAddFieldForm): void {}

  afterEditFormAddField(form: EditFormAddFieldForm): void {
    const dialogRef = this.dialog.open(AddNewFieldComponentImpl, {});
    dialogRef.afterClosed().subscribe((result) => {
      if(result?.dialogData) {
        this.addToFormFormFields(result.dialogData);
      }
    });
  }
}
