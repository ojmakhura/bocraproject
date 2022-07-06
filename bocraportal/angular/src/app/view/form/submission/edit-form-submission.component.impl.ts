// Generated by andromda-angular cartridge (view\view.component.imp.ts.vsl) CAN EDIT!
import { Component, Injector } from '@angular/core';
import { EditFormSubmissionComponent, EditFormSubmissionSubmitForm } from '@app/view/form/submission/edit-form-submission.component';
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
import * as FormSubmissionSelectors from '@app/store/form/submission/form-submission.selectors';
import * as FormSubmissionActions from '@app/store/form/submission/form-submission.actions';
import { KeycloakService } from 'keycloak-angular';
import { Observable } from 'rxjs';
import { FormSubmissionVO } from '@app/model/bw/org/bocra/portal/form/submission/form-submission-vo';
import { select } from '@ngrx/store';
import { FormVO } from '@app/model/bw/org/bocra/portal/form/form-vo';
import { SelectItem } from '@app/utils/select-item';
import { FormFieldVO } from '@app/model/bw/org/bocra/portal/form/field/form-field-vo';
import { SanitizeHtml } from '@app/pipe/sanitize-html.pipe';
import { DataFieldVO } from '@app/model/bw/org/bocra/portal/form/submission/data/data-field-vo';
import { FormArray, FormGroup } from '@angular/forms';
import { MatRadioChange } from '@angular/material/radio';
import { MatCheckboxChange } from '@angular/material/checkbox';
import { LicenseeVO } from '@app/model/bw/org/bocra/portal/licensee/licensee-vo';
import { DataFieldSectionVO } from '@app/model/bw/org/bocra/portal/form/submission/data/data-field-section-vo';
import { FormSubmissionStatus } from '@app/model/bw/org/bocra/portal/form/submission/form-submission-status';

// <div formArrayName="dataFields">
// 					<div *ngFor="let dataField of formSubmissionDataFields.controls; let i = index"  [formGroupName]='i'>
// 						<div class="mb-3" *ngIf="isButton(dataField.value)">
// 							<input type="button" class="btn btn-primary" value="{{getDataValue(dataField.value)}}">
// 						</div>
// 						<div class="mb-3" *ngIf="!isHidden(dataField.value) && isSimpleType(dataField.value)">
// 							<label  class="form-label">{{getDataValue(dataField.value)}}</label>
// 							<input class="form-control" type="{{this.getType(dataField.value)}}"
// 									formControlName="value" *ngIf="!isTextArea(dataField.value)
// 												&& !isSelect(dataField.value) && !isRange(dataField.value)">
// 							<textarea class="form-control" formControlName="value" *ngIf="isTextArea(dataField.value)"></textarea>
// 							<select class="form-select" aria-label="Default select example" formControlName="value" *ngIf="isSelect(dataField.value)">
// 								<option selected></option>
// 								<option value="1">One</option>
// 								<option value="2">Two</option>
// 								<option value="3">Three</option>
// 							</select>
// 							<input type="range" class="form-range" min="{{dataField.value.formField.min}}" max="{{dataField.value.formField.max}}" *ngIf="isRange(dataField.value)">
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
    this.route.queryParams.subscribe((queryParams: any) => {
      if (queryParams?.id) {
        this.store.dispatch(
          FormSubmissionActions.findById({
            id: queryParams.id,
            loading: true,
          })
        );
      }
    });

    this.formSubmission$.subscribe((submission) => {
      this.setEditFormSubmissionFormValue({ formSubmission: submission });
    });

    // this.store.dispatch(FormActions.getAllForms({ loading: true }));
    // this.forms$.subscribe((forms) => {
    //   forms.forEach((form) => {
    //     let item: SelectItem = new SelectItem();
    //     item.label = form.formName;
    //     item.value = form.id;
    //     this.formSubmissionFormBackingList.push(item);
    //     this._forms.push(form);
    //   });
    // });

    // this.formSubmissionFormControl.get('id')?.valueChanges.subscribe((id) => {
    //   let item: FormVO | undefined = this._forms.find((form) => '' + form.id === id);
    //   console.log(item);
    //   if (item) {
    //     this.formSubmissionFormControl.patchValue(item);
    //     item?.formFields.forEach((field: FormFieldVO) => {
    //       let data: DataFieldVO = new DataFieldVO();
    //       data.formField = field;
    //       data.value = '';
    //       this.formSubmissionDataFieldsControl.push(this.createDataFieldVOGroup(data));
    //     });
    //   }
    // });
  }

  doNgOnDestroy() {}

  /**
   * This method may be overwritten
   */
  override beforeEditFormSubmissionSave(form: EditFormSubmissionSaveForm): void {
    let formSubmission: FormSubmissionVO = form.formSubmission;
    formSubmission.submissionStatus = FormSubmissionStatus.DRAFT;
    this.doFormSubmissionSave(formSubmission);
  }

  override beforeEditFormSubmissionSubmit(form: EditFormSubmissionSubmitForm): void {

    let formSubmission: FormSubmissionVO = form.formSubmission;
    formSubmission.submissionStatus = FormSubmissionStatus.SUBMITTED;
    this.doFormSubmissionSave(formSubmission);
  }

  private doFormSubmissionSave(formSubmission: FormSubmissionVO) {
    if (formSubmission?.id) {
      formSubmission.updatedBy = this.keycloakService.getUsername();
      formSubmission.updatedDate = new Date();
    } else {
      formSubmission.createdBy = this.keycloakService.getUsername();
      formSubmission.createdDate = new Date();
    }

    this.store.dispatch(SubmissionActions.save({ formSubmission, loading: true }));
  }

  getFormObject(form: string) {
    return JSON.parse(form);
  }

  isSimpleType(data: DataFieldVO): boolean {
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

  isRange(data: DataFieldVO): boolean {
    return data.formField.fieldType === 'RANGE';
  }

  isSelect(data: DataFieldVO): boolean {
    return data.formField.fieldType === 'SELECT';
  }

  isHidden(data: DataFieldVO) {
    return data.formField.fieldType === 'HIDDEN';
  }

  getType(data: DataFieldVO): string {
    return data.formField.fieldType.toLowerCase();
  }

  isTextArea(data: DataFieldVO): boolean {
    return data.formField.fieldType === 'TEXTAREA';
  }

  isButton(data: DataFieldVO): boolean {
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
    });
  }

  override createDataFieldSectionVOGroup(value: DataFieldSectionVO): FormGroup {
    return this.formBuilder.group({
      sectionName: [value?.sectionName],
      sectionLabel: [value?.sectionLabel],
      position: [value?.position],
      dataFields: this.createDataFieldVOArray(value.dataFields),
    });
  }

  override createDataFieldVOGroup(value: DataFieldVO): FormGroup {
    return this.formBuilder.group({
      id: [value?.id],
      formField: this.createFormFieldForm(value?.formField),
      value: [value?.value],
    });
  }

  override handleFormChanges(change: any): void {
      console.log(change)
  }

  getDataValue(data: DataFieldVO) {
    return `${data.formField.fieldName}`;
  }

  getSectorFieldsControls(i: number): FormArray {
    return this.formSubmissionSectionsControl.controls[i].get('dataFields') as FormArray;
  }

  getSectorFields(i: number): DataFieldVO[] {
    return this.formSubmissionSectionsControl.controls[i].get('dataFields')?.value;
  }

  getSectionName(i: number): string {
    return this.formSubmissionSections[i].sectionName;
  }

  getSectionLabel(i: number): string {
    return this.formSubmissionSections[i].sectionLabel;
  }

  trackByFn(index: any, item: any) {
    return index;
 }
}
