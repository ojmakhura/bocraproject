// Generated by andromda-angular cartridge (view\view.component.imp.ts.vsl) CAN EDIT!
import { Component, Injector } from '@angular/core';
import { EditFormAddSectionForm, EditFormComponent } from '@app/view/form/edit-form.component';
import { EditFormSaveForm } from '@app/view/form/edit-form.component';
import { EditFormDeleteForm } from '@app/view/form/edit-form.component';
import { EditFormSearchForm } from '@app/view/form/edit-form.component';
import { EditFormVarsForm } from '@app/view/form/edit-form.component';
import * as LicenceTypeSelectors from '@app/store/licence/type/licence-type.selectors';
import * as FormActions from '@app/store/form/form.actions';
import * as FormSelectors from '@app/store/form/form.selectors';
import { select } from '@ngrx/store';
import { LicenceTypeVO } from '@app/model/bw/org/bocra/portal/licence/type/licence-type-vo';
import { FormFieldVO } from '@app/model/bw/org/bocra/portal/form/field/form-field-vo';
import { EditFormAddFieldForm } from '@app/view/form/edit-form.component';
import { AddNewFieldComponentImpl } from './add-new-field.component.impl';
import { KeycloakService } from 'keycloak-angular';
import { Observable } from 'rxjs';
import { LicenceTypeCriteria } from '@app/model/bw/org/bocra/portal/licence/type/licence-type-criteria';
import * as LicenceTypeActions from '@app/store/licence/type/licence-type.actions';
import { MatCheckboxChange } from '@angular/material/checkbox';
import { FormSectionVO } from '@app/model/bw/org/bocra/portal/form/section/form-section-vo';
import { AddNewSectionComponentImpl } from './add-new-section.component.impl';
import { MatDialogConfig } from '@angular/material/dialog';

@Component({
  selector: 'app-edit-form',
  templateUrl: './edit-form.component.html',
  styleUrls: ['./edit-form.component.scss'],
})
export class EditFormComponentImpl extends EditFormComponent {
  protected keycloakService: KeycloakService;
  private formSection$: Observable<FormSectionVO>;
  private formField$: Observable<FormFieldVO>;

  constructor(private injector: Injector) {
    super(injector);
    this.formLicenceTypes$ = this.store.pipe(select(LicenceTypeSelectors.selectLicenceTypes));
    this.keycloakService = injector.get(KeycloakService);
    this.formFormFields$ = this.store.pipe(select(FormSelectors.selectFormFields));
    this.formField$ = this.store.pipe(select(FormSelectors.selectFormField));
    this.formSection$ = this.store.pipe(select(FormSelectors.selectFormSection));
    this.formFormSections$ = this.store.pipe(select(FormSelectors.selectFormSections));
  }

  beforeOnInit(form: EditFormVarsForm): EditFormVarsForm {
    return form;
  }

  afterOnInit() {}

  doNgAfterViewInit() {
    if (this.useCaseScope.pageVariables['id']) {
      this.store.dispatch(
        FormActions.findFormById({
          id: this.useCaseScope.pageVariables['id'],
          loading: true,
        })
      );
    }

    this.form$.subscribe((form) => {
      if (form?.formSections) {
        this.store.dispatch(
          FormActions.setSections({
            formSections: form?.formSections
          })
        );
      }
      this.setEditFormSaveForm({ form: form } as EditFormSaveForm);
    });

    this.formSection$.subscribe((section) => {
      this.addToFormFormSections(section);
    });

    this.formField$.subscribe((field) => {
      this.addToFormFormFields(field);
    });
  }

  handleFormChanges(change: any) {
  }

  doNgOnDestroy() {
  }

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
    if (this.formControl.valid) {
      if (form.form.id) {
        form.form.updatedBy = this.keycloakService.getUsername();
        form.form.updatedDate = new Date();
      } else {
        form.form.createdBy = this.keycloakService.getUsername();
        form.form.createdDate = new Date();
      }
      this.store.dispatch(
        FormActions.saveForm({
          form: form.form,
          loading: true,
        })
      );
    } else {
      this.store.dispatch(
        FormActions.formFailure({errors: ["Form has and error!"]})
      );
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

  handleFormLicenceTypesAddDialog(): void {}

  handleFormFormFieldsAddDialog(): void {}

  handleFormFormFieldsSearch(): void {}

  handleFormLicenceTypesSearch(): void {
    let criteria: LicenceTypeCriteria = new LicenceTypeCriteria();
    criteria.typeSearch = this.formLicenceTypesSearchField;
    this.store.dispatch(
      LicenceTypeActions.search({
        criteria: criteria,
        loading: true,
      })
    );
  }

  handleFormLicenceTypesSelected(event: MatCheckboxChange, element: LicenceTypeVO): void {}

  handleFormFormFieldsSelected(event: MatCheckboxChange, element: FormFieldVO): void {}

  afterSetEditFormAddFieldForm(form: EditFormAddFieldForm): void {}

  beforeEditFormAddField(form: EditFormAddFieldForm): void {}

  afterEditFormAddField(form: EditFormAddFieldForm): void {
    this.useCaseScope.pageVariables['form'] = this.form;

    // const dialogConfig = new MatDialogConfig();
    // dialogConfig.data = {
    //   formSections: this.form.formSections
    // };
    
    // const dialogRef = this.dialog.open(AddNewFieldComponentImpl, dialogConfig);
    // dialogRef.afterClosed().subscribe((result) => {
    //   if (result?.dialogData) {
    //     let field: FormFieldVO = result.dialogData.formField;
    //     if (field.id) {
    //       field.updatedBy = this.keycloakService.getUsername();
    //       field.updatedDate = new Date();
    //     } else {
    //       field.createdBy = this.keycloakService.getUsername();
    //       field.createdDate = new Date();
    //       field.form = this.form;
    //     }

    //     this.store.dispatch(
    //       FormActions.saveField({
    //         formField: field,
    //         loading: true
    //       })
    //     );
    //   }
    // });
  }

  handleFormLicenseesAddDialog() {}

  handleFormLicenseesSearch() {}

  handleFormLicenseesSelected() {}

  afterSetEditFormAddSectionForm(form: EditFormAddSectionForm): void {
  }

  beforeEditFormAddSection(): void {}

  afterEditFormAddSection(): void {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.data = {
      form: this.form
    };

    const dialogRef = this.dialog.open(AddNewSectionComponentImpl, dialogConfig);
    dialogRef.afterClosed().subscribe((result) => {
      if (result?.dialogData) {
        let section: FormSectionVO = result.dialogData.formSection;
        if (section.id) {
          section.updatedBy = this.keycloakService.getUsername();
          section.updatedDate = new Date();
        } else {
          section.createdBy = this.keycloakService.getUsername();
          section.createdDate = new Date();
          section.form = this.form;
        }

        this.store.dispatch(
          FormActions.saveSection({
            formSection: section, 
            loading: true
          })
        );
      }
    });
  }

  handleFormFormSectionsAddDialog(): void {
  }

  handleFormFormSectionsSearch(): void {
  }

  handleFormFormSectionsSelected(event: MatCheckboxChange, data: FormSectionVO): void {
  }
}
