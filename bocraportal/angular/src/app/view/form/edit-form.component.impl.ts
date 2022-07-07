// Generated by andromda-angular cartridge (view\view.component.imp.ts.vsl) CAN EDIT!
import { Component, Injector } from '@angular/core';
import {
  EditFormAddLicenseeForm,
  EditFormAddSectionForm,
  EditFormComponent,
  EditFormDeleteForm,
} from '@app/view/form/edit-form.component';
import { EditFormSaveForm } from '@app/view/form/edit-form.component';
import { EditFormVarsForm } from '@app/view/form/edit-form.component';
import * as LicenceTypeSelectors from '@app/store/licence/type/licence-type.selectors';
import * as FormActions from '@app/store/form/form.actions';
import * as FormSelectors from '@app/store/form/form.selectors';
import { select } from '@ngrx/store';
import { FormFieldVO } from '@app/model/bw/org/bocra/portal/form/field/form-field-vo';
import { EditFormAddFieldForm } from '@app/view/form/edit-form.component';
import { KeycloakService } from 'keycloak-angular';
import { Observable } from 'rxjs';
import { LicenceTypeCriteria } from '@app/model/bw/org/bocra/portal/licence/type/licence-type-criteria';
import * as LicenceTypeActions from '@app/store/licence/type/licence-type.actions';
import * as LicenseeFormSelectors from '@app/store/licensee/form/licensee-form.selectors';
import * as LicenseeFormActions from '@app/store/licensee/form/licensee-form.actions';
import * as LicenseeActions from '@app/store/licensee/licensee.actions';
import * as LicenseeSelectors from '@app/store/licensee/licensee.selectors';
import { FormSectionVO } from '@app/model/bw/org/bocra/portal/form/section/form-section-vo';
import { LicenseeCriteria } from '@app/model/bw/org/bocra/portal/licensee/licensee-criteria';
import { LicenseeFormVO } from '@app/model/bw/org/bocra/portal/licensee/form/licensee-form-vo';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'app-edit-form',
  templateUrl: './edit-form.component.html',
  styleUrls: ['./edit-form.component.scss'],
})
export class EditFormComponentImpl extends EditFormComponent {
  protected keycloakService: KeycloakService;
  private formSection$: Observable<FormSectionVO>;
  private formField$: Observable<FormFieldVO>;
  private licenseeForm$: Observable<LicenseeFormVO>;

  constructor(private injector: Injector) {
    super(injector);
    this.formLicenceTypes$ = this.store.pipe(select(LicenceTypeSelectors.selectLicenceTypes));
    this.keycloakService = injector.get(KeycloakService);
    this.formFormFields$ = this.store.pipe(select(FormSelectors.selectFormFields));
    this.formField$ = this.store.pipe(select(FormSelectors.selectFormField));
    this.formSection$ = this.store.pipe(select(FormSelectors.selectFormSection));
    this.formFormSections$ = this.store.pipe(select(FormSelectors.selectFormSections));
    this.licenseeForm$ = this.store.pipe(select(LicenseeFormSelectors.selectLicenseeForm));
  }

  override beforeOnInit(form: EditFormVarsForm): EditFormVarsForm {
    return form;
  }

  override doNgOnDestroy(): void {}

  override afterOnInit() {}

  override doNgAfterViewInit() {
    this.route.queryParams.subscribe((queryParams: any) => {
      if (queryParams?.id) {
        this.store.dispatch(
          FormActions.findFormById({
            id: queryParams.id,
            loading: true,
          })
        );
      }
    });

    this.form$.subscribe((form) => {
      if (form?.formSections) {
        this.store.dispatch(
          FormActions.setSections({
            formSections: form?.formSections,
          })
        );
      }
      this.setEditFormFormValue({ form: form });
    });

    this.formSection$.subscribe((section) => {
      if (section) {
        this.addToFormFormSections(section);
      }
    });

    this.formField$.subscribe((field) => {
      if (field) {
        this.addToFormFormFields(field);
      }
    });

    this.licenseeForm$.subscribe((licenseeForm) => {
      if (licenseeForm) {
        this.addToFormLicensees(licenseeForm);
      }
    });
  }

  /**
   * This method may be overwritten
   */
  override beforeEditFormSave(form: EditFormSaveForm): void {
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
      this.store.dispatch(FormActions.formFailure({ messages: ['Form has an error!'] }));
    }
  }

  override formLicenceTypesSearch(): void {
    let criteria: LicenceTypeCriteria = new LicenceTypeCriteria();
    criteria.typeSearch = this.formLicenceTypesSearchField.value;
    this.store.dispatch(
      LicenceTypeActions.search({
        criteria: criteria,
        loading: true,
      })
    );
  }

  override formLicenseesSearch(): void {
    let criteria: LicenseeCriteria = new LicenseeCriteria();
    criteria.licenseeName = this.formLicenseesSearchField.value;
    criteria.uin = this.formLicenseesSearchField.value;
    this.store.dispatch(
      LicenseeActions.search({
        criteria: criteria,
        loading: true,
      })
    );
  }

  override beforeEditFormAddField(form: EditFormAddFieldForm): void {
    this.useCaseScope.pageVariables['form'] = this.form;
    this.useCaseScope.queryParams['formId'] = this.form.id;
  }

  override afterEditFormAddLicensee(form: EditFormAddLicenseeForm, dialogData: any): void {
    if (dialogData?.licenseeForm) {
      let licensee: LicenseeFormVO = dialogData.licenseeForm;
      this.store.dispatch(
        LicenseeFormActions.create({
          licenseeId: licensee.licensee.id,
          formId: licensee.form.id,
          loading: true,
        })
      );
    }
  }

  override afterEditFormAddSection(form: EditFormAddSectionForm, dialogData: any): void {
    if (dialogData?.formSection) {
      let section: FormSectionVO = dialogData.formSection;
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
          loading: true,
        })
      );
    }
  }

  override doEditFormFormFields(formField: any) {
    this.formController.resetUseCaseScope();
    this.useCaseScope.queryParams['id'] = formField.id;
    this.router.navigate(['form/edit-field'], { queryParams: this.useCaseScope.queryParams });
  }

  override doEditFormFormSections(formSections: FormSectionVO) {}

  override afterEditFormDelete(form: EditFormDeleteForm): void {
    this.editFormFormReset();

    if (form?.form?.id && confirm('Are you sure you want to delete the form?')) {
      this.store.dispatch(
        FormActions.removeForm({
          id: form.form.id,
          loading: true,
        })
      );
    }
  }

  override createLicenseeFormVOGroup(value: LicenseeFormVO): FormGroup {
    return this.formBuilder.group({
      id: [value?.id],
      licensee: {
        id: value?.licensee?.id,
        uin: value?.licensee?.uin,
        licenseeName: value?.licensee?.licenseeName,
        status: value?.licensee?.status,
      },
      form: {
        id: value?.form?.id,
        code: value?.form?.code,
        formName: value?.form?.formName,
      }
    });
  }

  override createFormFieldVOGroup(value: FormFieldVO): FormGroup {
    return this.formBuilder.group({
      id: [value?.id],
      createdBy: [value?.createdBy],
      updatedBy: [value?.updatedBy],
      createdDate: [value?.createdDate],
      updatedDate: [value?.updatedDate],
      fieldType: [value?.fieldType],
      fieldId: [value?.fieldId],
      fieldName: [value?.fieldName],
      description: [value?.description],
      fieldValueType: [value?.fieldValueType],
      expression: [value?.expression],
      defaultValue: [value?.defaultValue],
      required: [value?.required],
      min: [value?.min],
      max: [value?.max],
      minLength: [value?.minLength],
      maxLength: [value?.maxLength],
      formSection: {
        id: [value?.formSection?.id],
        position: [value?.formSection?.position],
        sectionId: [value?.formSection?.sectionId],
        sectionLabel: [value?.formSection?.sectionLabel],
      }
    });
  }
}
