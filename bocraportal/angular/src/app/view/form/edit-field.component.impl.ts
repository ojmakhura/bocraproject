// Generated by andromda-angular cartridge (view\view.component.imp.ts.vsl) CAN EDIT!
import { Component, Injector } from '@angular/core';
import { EditFieldComponent } from '@app/view/form/edit-field.component';
import { EditFieldCancelForm } from '@app/view/form/edit-field.component';
import { EditFieldDoneForm } from '@app/view/form/edit-field.component';
import { EditFieldSaveForm } from '@app/view/form/edit-field.component';
import { EditFieldVarsForm } from '@app/view/form/edit-field.component';
import { FormState } from '@app/store/form/form.state';
import * as FormSelectors from '@app/store/form/form.selectors';
import * as FormActions from '@app/store/form/form.actions';
import { MatRadioChange } from '@angular/material/radio';
import { MatCheckboxChange } from '@angular/material/checkbox';
import { Observable } from 'rxjs';
import { FormVO } from '@app/model/bw/org/bocra/portal/form/form-vo';
import { KeycloakService } from 'keycloak-angular';
import { select } from '@ngrx/store';
import { SelectItem } from '@app/utils/select-item';
import { FormSectionVO } from '@app/model/bw/org/bocra/portal/form/section/form-section-vo';
import { FormFieldVO } from '@app/model/bw/org/bocra/portal/form/field/form-field-vo';

@Component({
  selector: 'app-edit-field',
  templateUrl: './edit-field.component.html',
  styleUrls: ['./edit-field.component.scss'],
})
export class EditFieldComponentImpl extends EditFieldComponent {
  form$: Observable<FormVO>;
  protected keycloakService: KeycloakService;

  constructor(private injector: Injector) {
    super(injector);
    this.form$ = this.store.pipe(select(FormSelectors.selectForm));
    this.keycloakService = injector.get(KeycloakService);
  }

  override beforeOnInit(form: EditFieldVarsForm): EditFieldVarsForm {
    if (this.useCaseScope?.pageVariables['form']) {
      const f: FormVO = this.useCaseScope?.pageVariables['form'];

      f?.formSections?.forEach((element: FormSectionVO) => {
        let item: SelectItem = new SelectItem();
        item.label = element.sectionLabel;
        item.value = element.id;

        this.formFieldFormSectionBackingList.push(item);
      });

      if (!form?.formField) {
        form.formField = new FormFieldVO();
      }

      form.formField.form = f;
    }

    return form;
  }

  override doNgOnDestroy(): void {}

  override doNgAfterViewInit() {
    this.route.queryParams.subscribe((queryParams: any) => {
      if (queryParams?.id) {
        this.store.dispatch(FormActions.findFieldById({ id: queryParams.id, loading: true }));
      } else {
        if (queryParams?.formId) {
          this.store.dispatch(FormActions.findFormById({ id: queryParams.formId, loading: true }));
        }
      }
    });

    this.formField$.subscribe((field) => {
      if (field) {
        if (field.form) {
          this.store.dispatch(FormActions.findFormById({ id: field.form.id, loading: true }));
        }

        this.setEditFieldFormValue({ formField: field });
      }
    });

    this.form$.subscribe((f) => {
      this.formFieldFormControl.patchValue(f);
      this.formFieldFormSectionBackingList = [];
      f?.formSections?.forEach((element: FormSectionVO) => {
        let item: SelectItem = new SelectItem();
        item.label = element?.sectionLabel ? element?.sectionLabel : element?.sectionId;
        item.value = element.id;

        this.formFieldFormSectionBackingList.push(item);
      });
    });
  }

  /**
   * This method may be overwritten
   */
  override beforeEditFieldCancel(form: EditFieldCancelForm): void {
    this.useCaseScope.pageVariables['form'] = this.formField.form;
    this.useCaseScope.queryParams['id'] = this.formField.form.id;
  }

  /**
   * This method may be overwritten
   */
  override beforeEditFieldDone(form: EditFieldDoneForm): void {
    this.useCaseScope.pageVariables['form'] = this.formFieldForm;
    this.useCaseScope.queryParams['id'] = this.formFieldForm.id;
  }

  /**
   * This method may be overwritten
   */
  override beforeEditFieldSave(form: EditFieldSaveForm): void {
    console.log(form);

    if (this.formFieldControl.valid) {
      if (this.formField.id) {
        this.formField.updatedBy = this.keycloakService.getUsername();
        this.formField.updatedDate = new Date();
      } else {
        this.formField.createdBy = this.keycloakService.getUsername();
        this.formField.createdDate = new Date();
      }

      this.store.dispatch(
        FormActions.saveField({
          formField: this.formField,
          loading: true,
        })
      );
    } else {
      this.store.dispatch(FormActions.formFailure({ messages: ['Form has and error!'] }));
    }
  }
}
