// Generated by andromda-angular cartridge (view\view.component.imp.ts.vsl) CAN EDIT!
import { Component, Injector } from '@angular/core';
import { EditFormSubmissionComponent } from '@app/view/form/submission/edit-form-submission.component';
import { EditFormSubmissionSaveForm } from '@app/view/form/submission/edit-form-submission.component';
import { EditFormSubmissionDeleteForm } from '@app/view/form/submission/edit-form-submission.component';
import { EditFormSubmissionSearchForm } from '@app/view/form/submission/edit-form-submission.component';
import { EditFormSubmissionVarsForm } from '@app/view/form/submission/edit-form-submission.component';
import * as SubmissionActions from '@app/store/form/submission/form-submission.actions';
import * as SubmissionSelectors from '@app/store/form/submission/form-submission.selectors';
import * as FormActions from '@app/store/form/form.actions';
import * as FormSelectors from '@app/store/form/form.selectors';
import * as LicenseeActions from '@app/store/licensee/licensee.actions';
import * as LicenseeSelectors from '@app/store/licensee/licensee.selectors';
import { KeycloakService } from 'keycloak-angular';
import { Observable } from 'rxjs';
import { FormSubmissionVO } from '@app/model/bw/org/bocra/portal/form/submission/form-submission-vo';
import { select } from '@ngrx/store';
import { FormVO } from '@app/model/bw/org/bocra/portal/form/form-vo';
import { SelectItem } from '@app/utils/select-item';
import { FormFieldVO } from '@app/model/bw/org/bocra/portal/form/field/form-field-vo';
import { SanitizeHtml } from '@app/pipe/sanitize-html.pipe';
import { FormDataVO } from '@app/model/bw/org/bocra/portal/form/submission/data/form-data-vo';
import { FormGroup } from '@angular/forms';
import { MatRadioChange } from '@angular/material/radio';
import { MatCheckboxChange } from '@angular/material/checkbox';
import { LicenseeVO } from '@app/model/bw/org/bocra/portal/licensee/licensee-vo';

// <div formArrayName="formDatas">
// 					<div *ngFor="let formData of formSubmissionFormDatas.controls; let i = index"  [formGroupName]='i'>
// 						<div class="mb-3" *ngIf="isButton(formData.value)">
// 							<input type="button" class="btn btn-primary" value="{{getDataValue(formData.value)}}">
// 						</div>
// 						<div class="mb-3" *ngIf="!isHidden(formData.value) && isSimpleType(formData.value)">
// 							<label  class="form-label">{{getDataValue(formData.value)}}</label>
// 							<input class="form-control" type="{{this.getType(formData.value)}}"
// 									formControlName="value" *ngIf="!isTextArea(formData.value)
// 												&& !isSelect(formData.value) && !isRange(formData.value)">
// 							<textarea class="form-control" formControlName="value" *ngIf="isTextArea(formData.value)"></textarea>
// 							<select class="form-select" aria-label="Default select example" formControlName="value" *ngIf="isSelect(formData.value)">
// 								<option selected></option>
// 								<option value="1">One</option>
// 								<option value="2">Two</option>
// 								<option value="3">Three</option>
// 							</select>
// 							<input type="range" class="form-range" min="{{formData.value.formField.min}}" max="{{formData.value.formField.max}}" *ngIf="isRange(formData.value)">
// 						</div>
// 					</div>
// 				</div>

@Component({
  selector: 'app-edit-form-submission',
  templateUrl: './edit-form-submission.component.html',
  styleUrls: ['./edit-form-submission.component.scss'],
})
export class EditFormSubmissionComponentImpl extends EditFormSubmissionComponent {
  protected keycloakService: KeycloakService;
  formSubmissions$: Observable<FormSubmissionVO[]>;
  forms$: Observable<FormVO[]>;
  _forms: FormVO[] = [];

  constructor(private injector: Injector) {
    super(injector);
    this.keycloakService = injector.get(KeycloakService);
    this.formSubmissions$ = this.store.pipe(select(SubmissionSelectors.selectFormSubmissions));
    this.forms$ = this.store.pipe(select(FormSelectors.selectForms));
    this.formSubmissionLicensees$ = this.store.pipe(select(LicenseeSelectors.selectLicensees));
  }

  override beforeOnInit(form: EditFormSubmissionVarsForm): EditFormSubmissionVarsForm {
    return form;
  }

  override doNgAfterViewInit() {
    this.store.dispatch(FormActions.getAllForms({loading: true}));
    this.forms$.subscribe((forms) => {
      forms.forEach((form) => {
        let item: SelectItem = new SelectItem();
        item.label = form.formName;
        item.value = form.id;
        this.formSubmissionFormBackingList.push(item);
        this._forms.push(form);
      });
    });

    this.formSubmissionFormControl.get('id')?.valueChanges.subscribe((id) => {
      let item: FormVO | undefined = this._forms.find((form) => '' + form.id === id);
      console.log(item);
      if (item) {
        this.formSubmissionFormControl.patchValue(item);
        item?.formFields.forEach((field: FormFieldVO) => {
          let data: FormDataVO = new FormDataVO();
          data.formField = field;
          data.value = '';
          this.formSubmissionFormDatasControl.push(this.createFormDataVOGroup(data));
        });
      }
    });
  }

  doNgOnDestroy() {}

<<<<<<< HEAD
  override afterEditFormSubmissionDelete(form: EditFormSubmissionDeleteForm): void {
    if(form?.formSubmission?.id) {
      if(confirm("Are you sure to delete the form submission configuration?")) {
        this.store.dispatch(formSubmissionActions.remove({id: form.formSubmission.id, loading: true}));
        this.editFormSubmissionFormReset();
      }
    }
  }
  

=======
>>>>>>> origin/ojm-dev
  /**
   * This method may be overwritten
   */
   override beforeEditFormSubmissionSave(form: EditFormSubmissionSaveForm): void {
    if (form.formSubmission?.id) {
      form.formSubmission.updatedBy = this.keycloakService.getUsername();
      form.formSubmission.updatedDate = new Date();
    } else {
      form.formSubmission.createdBy = this.keycloakService.getUsername();
      form.formSubmission.createdDate = new Date();
    }
    //this.store.dispatch(SubmissionActions.save({ formSubmission: form.formSubmission }));
  }

  getFormObject(form: string) {
    return JSON.parse(form);
  }

  isSimpleType(data: FormDataVO): boolean {
    const type = data.formField.fieldType;

    return (
      type === 'TEXT' ||
      type === 'TEXTAREA' ||
      type === 'PASSWORD' ||
      type === 'LINK' ||
      type === 'EMAIL' ||
      type === 'RANGE' ||
      type === 'NUMBER' ||
      type === 'PLAINTEXT' ||
      type === 'DATE' ||
      type === 'MONTH' ||
      type === 'SEARCH' ||
      type === 'SELECT'
    );
  }

  isRange(data: FormDataVO): boolean {
    return data.formField.fieldType === 'RANGE';
  }

  isSelect(data: FormDataVO): boolean {
    return data.formField.fieldType === 'SELECT';
  }

  isHidden(data: FormDataVO) {
    return data.formField.fieldType === 'HIDDEN';
  }

  getType(data: FormDataVO): string {
    return data.formField.fieldType.toLowerCase();
  }

  isTextArea(data: FormDataVO): boolean {
    return data.formField.fieldType === 'TEXTAREA';
  }

  isButton(data: FormDataVO): boolean {
    return data.formField.fieldType === 'BUTTON';
  }

  isInputHidden(type: string): boolean {
    return type === 'HIDDEN';
  }

  createFormFieldForm(formField: FormFieldVO): FormGroup {
    return this.formBuilder.group({
      id: [formField?.id ? formField.id : null],
      createdBy: [formField?.createdBy ? formField.createdBy : null],
      updatedBy: [formField?.updatedBy ? formField.updatedBy : null],
      createdDate: [formField?.createdDate ? formField.createdDate : null],
      updatedDate: [formField?.updatedDate ? formField.updatedDate : null],
      fieldType: [formField?.fieldType ? formField.fieldType : null],
      fieldName: [formField?.fieldName ? formField.fieldName : null],
      defaultValue: [formField?.defaultValue ? formField.defaultValue : null],
      required: [formField?.required ? formField.required : null],
      min: [formField?.min ? formField.min : null],
      max: [formField?.max ? formField.max : null],
      options: this.formBuilder.array(formField?.options ? formField.options : []),
      tableColumns: this.formBuilder.array(formField?.tableColumns ? formField.tableColumns : []),
    });
  }

  override createFormDataVOGroup(value: FormDataVO): FormGroup {
    return this.formBuilder.group({
      id: [value?.id],
      formField: this.createFormFieldForm(value?.formField),
      value: [value?.value],
    });
  }

  getDataValue(data: FormDataVO) {
    return `${data.formField.fieldName}`;
  }
}
