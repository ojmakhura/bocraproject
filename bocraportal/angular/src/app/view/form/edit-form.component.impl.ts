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
import { EditFieldComponentImpl } from './edit-field.component.impl';
import { KeycloakService } from 'keycloak-angular';
import { Observable } from 'rxjs';
import { LicenceTypeCriteria } from '@app/model/bw/org/bocra/portal/licence/type/licence-type-criteria';
import * as LicenceTypeActions from '@app/store/licence/type/licence-type.actions';
import { MatCheckboxChange } from '@angular/material/checkbox';
import { FormSectionVO } from '@app/model/bw/org/bocra/portal/form/section/form-section-vo';
import { EditSectionComponentImpl } from './edit-section.component.impl';
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

  override beforeOnInit(form: EditFormVarsForm): EditFormVarsForm {
    return form;
  }

  override doNgOnDestroy(): void {
  }

  override afterOnInit() { }

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
    criteria.typeSearch = this.formLicenceTypesSearchField;
    this.store.dispatch(
      LicenceTypeActions.search({
        criteria: criteria,
        loading: true,
      })
    );
  }

  override beforeEditFormAddField(form: EditFormAddFieldForm): void {

    this.useCaseScope.pageVariables['form'] = this.form;
    this.useCaseScope.queryParams['formId'] = this.form.id;
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
    this.router.navigate(['form/edit-field'], {queryParams: this.useCaseScope.queryParams})
  }
}
