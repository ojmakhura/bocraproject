// Generated by andromda-angular cartridge (view\view.component.imp.ts.vsl) CAN EDIT!
import { ChangeDetectorRef, Component, Injector, ViewChild } from '@angular/core';
import {
  EditFormSubmissionAcceptForm,
  EditFormSubmissionComponent,
  EditFormSubmissionDeleteForm,
  EditFormSubmissionReturnForm,
  EditFormSubmissionSubmitForm,
} from '@app/view/form/submission/edit-form-submission.component';
import { EditFormSubmissionSaveForm } from '@app/view/form/submission/edit-form-submission.component';
import { EditFormSubmissionVarsForm } from '@app/view/form/submission/edit-form-submission.component';
import * as SubmissionActions from '@app/store/form/submission/form-submission.actions';
import * as SubmissionSelectors from '@app/store/form/submission/form-submission.selectors';
import * as FormSelectors from '@app/store/form/form.selectors';
import * as LicenseeSelectors from '@app/store/licensee/licensee.selectors';
import * as FormSubmissionActions from '@app/store/form/submission/form-submission.actions';
import { KeycloakService } from 'keycloak-angular';
import { Observable } from 'rxjs';
import { FormSubmissionVO } from '@app/model/bw/org/bocra/portal/form/submission/form-submission-vo';
import { select } from '@ngrx/store';
import { FormVO } from '@app/model/bw/org/bocra/portal/form/form-vo';
import { FormFieldVO } from '@app/model/bw/org/bocra/portal/form/field/form-field-vo';
import { DataFieldVO } from '@app/model/bw/org/bocra/portal/form/submission/data/data-field-vo';
import { FormArray, FormGroup, Validators } from '@angular/forms';
import { DataFieldSectionVO } from '@app/model/bw/org/bocra/portal/form/submission/data/data-field-section-vo';
import { FormEntryType } from '@app/model/bw/org/bocra/portal/form/form-entry-type';
import { FormSubmissionStatus } from '@app/model/bw/org/bocra/portal/form/submission/form-submission-status';
import { RowGroup } from '@app/model/submission/row-group';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { FieldValueType } from '@app/model/bw/org/bocra/portal/form/field/field-value-type';
import * as math from 'mathjs';
import * as ViewActions from '@app/store/view/view.actions';
import * as ViewSelectors from '@app/store/view/view.selectors';

@Component({
  selector: 'app-edit-form-submission',
  templateUrl: './edit-form-submission.component.html',
  styleUrls: ['./edit-form-submission.component.scss'],
})
export class EditFormSubmissionComponentImpl extends EditFormSubmissionComponent {
  protected keycloakService: KeycloakService;
  unauthorisedUrls$: Observable<string[]>;
  deleteUnrestricted: boolean = true;
  formSubmissions$: Observable<FormSubmissionVO[]>;
  forms$: Observable<FormVO[]>;
  fieldColumns: string[] = ['Row'];
  fieldColumnIds: string[] = ['row'];
  formFields: FormFieldVO[] = [];
  submissionName: string = 'defaultTemplate';
  templateHeaders: string[][] = [];
  rowGroups: RowGroup[] = [];
  submitUnrestricted: boolean = true;
  returnUnrestricted: boolean = true;
  acceptUnrestricted: boolean = true;
  addUnrestricted: boolean = true;

  dataFieldsDataSource = new MatTableDataSource<RowGroup>([]);
  @ViewChild(MatPaginator) dataFieldsPaginator: MatPaginator;
  @ViewChild(MatSort) dataFieldsSort: MatSort;

  submissionStatus: FormSubmissionStatus = FormSubmissionStatus.NEW;

  constructor(
    private changeDetectorRefs: ChangeDetectorRef,
    private injector: Injector
  ) {
    super(injector);
    this.keycloakService = injector.get(KeycloakService);
    this.formSubmissions$ = this.store.pipe(select(SubmissionSelectors.selectFormSubmissions));
    this.forms$ = this.store.pipe(select(FormSelectors.selectForms));
    this.formSubmissionLicensees$ = this.store.pipe(select(LicenseeSelectors.selectLicensees));
    this.unauthorisedUrls$ = this.store.pipe(select(ViewSelectors.selectUnauthorisedUrls));
  }

  override beforeOnInit(form: EditFormSubmissionVarsForm): EditFormSubmissionVarsForm {
    return form;
  }

  private loadSubmission(id: number) {
    this.store.dispatch(
      FormSubmissionActions.findById({
        id: id,
        loading: true,
        loaderMessage: 'Loading form submissions by id ...'
      })
    );

  }

  override doNgAfterViewInit() {

    this.store.dispatch(
      ViewActions.loadViewAuthorisations({
        viewUrl: "/form/submission/edit-form-submission",
        roles: this.keycloakService.getUserRoles(),
        loading: true
      })
    );

    this.route.queryParams.subscribe((queryParams: any) => {
      if (queryParams?.id) {
        this.loadSubmission(queryParams.id);
      } else {
        if (this.useCaseScope.pageVariables['id']) {
          this.loadSubmission(this.useCaseScope.pageVariables['id']);
        }
      }
    });

    this.store.dispatch(
      ViewActions.loadViewAuthorisations({
        viewUrl: "/form/submission/edit-form-submission",
        roles: this.keycloakService.getUserRoles(),
        loading: true
      })
    );

    this.unauthorisedUrls$.subscribe(restrictedItems => {
      restrictedItems.forEach(item => {
        if(item === '/form/submission/edit-form-submission/{button:delete}') {
          this.deleteUnrestricted = false;
        }

        if(item === '/form/submission/edit-form-submission/{button:return}') {
          this.returnUnrestricted = false;
        }

        if(item === '/form/submission/edit-form-submission/{button:accept}') {
          this.acceptUnrestricted = false;
        }

        if (item === '/form/submission/edit-form-submission/{button:submit}') {
          this.submitUnrestricted = false;
        }
      });
    });

    this.formSubmission$.subscribe((submission) => {
      this.rowGroups = [];
      this.formFields = submission?.form?.formFields;
      if(this.formFields) {
        this.templateHeaders = [this.formFields.map(field => field.fieldId)];
        
        this.submissionName = `${submission.licensee.licenseeName}-${submission?.form?.formName} - ${submission?.period?.periodName}`;
      } else {
        this.templateHeaders = [];
      }
      this.submissionStatus = submission?.submissionStatus;

      submission?.sections?.forEach((section) => {
        section.dataFields.forEach((dataField: DataFieldVO) => {
          this.addToRowGroup(dataField);
        });
      });

      this.setEditFormSubmissionFormValue({ formSubmission: submission });
      submission?.form?.formFields?.forEach((field) => {
        this.fieldColumns.push(field.fieldName);
        this.fieldColumnIds.push(field.fieldId);
      });
    });

    this.dataFieldsDataSource.paginator = this.dataFieldsPaginator;
  }

  isCalculatedField(dataField: DataFieldVO): boolean {
    return dataField.formField.fieldValueType === FieldValueType.CALCULATED;
  }

  onRowChange(section: any, dataField: DataFieldVO) {
    let sec: DataFieldSectionVO = section.value;
    let calculationFields: FormGroup[] = [];

    // Find the caculation fields controls
    for (let i = 0; i < this.formSubmissionSectionsControl.length; i++) {
      let fieldsControls: FormArray = this.formSubmissionSectionsControl.controls[i].get('dataFields') as FormArray;
      for (let i = 0; i < fieldsControls.length; i++) {
        let fieldControl: FormGroup = <FormGroup>fieldsControls.controls[i];
        let field: DataFieldVO = fieldControl.value;
        let formField: FormFieldVO = field.formField;
        
        if (
          formField.fieldValueType === FieldValueType.CALCULATED &&
          formField.expression.includes(`[${dataField?.formField?.fieldId}]`)
        ) {
          calculationFields.push(fieldControl);
        }
      }
    }

    // Make the calculations
    calculationFields.forEach((fieldControl) => {
      let field: DataFieldVO = fieldControl.value;
      let expression: string = field.formField.expression;

      for (let i = 0; i < sec.dataFields.length; i++) {
        let field: DataFieldVO = sec.dataFields[i];
        if (expression.includes(`[${field.formField.fieldId}]`)) {
          expression = expression.replace(`[${field.formField.fieldId}]`, field.value);
        }
      }

      fieldControl?.get('value')?.setValue(math.evaluate(expression));
    });
  }

  doNgOnDestroy() { }

  /**
   * This method may be overwritten
   */
  override beforeEditFormSubmissionDelete(form: EditFormSubmissionDeleteForm): void {
    if (form?.formSubmission?.id && confirm('Are you sure you want to delete the form?')) {
      this.store.dispatch(
        FormSubmissionActions.remove({
          id: form.formSubmission.id,
          loading: true,
          loaderMessage: 'Removing form submissions ...'
        })
      );
      this.editFormSubmissionFormReset();
    } else {
      this.store.dispatch(FormSubmissionActions.formSubmissionFailure({ messages: ['Please select something to delete'] }));
    }
  }
  
  override beforeEditFormSubmissionSave(form: EditFormSubmissionSaveForm): void {
    if (this.formSubmissionControl.valid) {
      if(form.formSubmission.submissionStatus != FormSubmissionStatus.SUBMITTED && form.formSubmission.submissionStatus != FormSubmissionStatus.ACCEPTED) {
        form.formSubmission.submissionStatus = FormSubmissionStatus.DRAFT;
      }
      
      this.doFormSubmissionSave(form.formSubmission);
    } else {
      let messages: string[] = []
      if (!this.formSubmissionControl.valid) {
        messages.push("Form Submission has errors, Please fill in the required form fields")
      }
      if (!this.formSubmissionSubmissionStatusControl.valid) {
        messages.push("Form Submission Status is missing!")
      }
      this.store.dispatch(FormSubmissionActions.formSubmissionFailure({ messages: messages }));
    }
  }

  override beforeEditFormSubmissionSubmit(form: EditFormSubmissionSubmitForm): void {

    if(confirm('Are you sure you want to submit the form submission? You will not be able to edit the data afterwards.')) {

      let formSubmission: FormSubmissionVO = form.formSubmission;

      formSubmission.submittedBy = this.keycloakService.getUsername();
      formSubmission.submissionDate = new Date();
      formSubmission.submissionStatus = FormSubmissionStatus.SUBMITTED;
      this.doFormSubmissionSave(formSubmission);
    }

  }

  override beforeEditFormSubmissionAccept(form: EditFormSubmissionAcceptForm): void {
    if(confirm('Are you sure you want to accept the form submission?')) {

      let formSubmission: FormSubmissionVO = this.formSubmission;
      formSubmission.submissionStatus = FormSubmissionStatus.ACCEPTED;
      this.doFormSubmissionSave(formSubmission);
    }
  }

  override beforeEditFormSubmissionReturn(form: EditFormSubmissionReturnForm): void {
      
    if(confirm('Are you sure you want to return the form submission?')) {

      let formSubmission: FormSubmissionVO = form.formSubmission;
      formSubmission.submissionStatus = FormSubmissionStatus.RETURNED;
      this.doFormSubmissionSave(formSubmission);
    }
  }

  private doFormSubmissionSave(formSubmission: FormSubmissionVO) {
    if (formSubmission?.id) {
      formSubmission.updatedBy = this.keycloakService.getUsername();
      formSubmission.updatedDate = new Date();
    } else {
      formSubmission.createdBy = this.keycloakService.getUsername();
      formSubmission.createdDate = new Date();
    }

    this.store.dispatch(SubmissionActions.save({ formSubmission, loading: true, loaderMessage: 'Saving form submission ...' }));
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
      fieldValueType: [formField?.fieldValueType ? formField.fieldValueType : null],
      fieldName: [formField?.fieldName ? formField.fieldName : null],
      expression: [formField?.expression ? formField.expression : null],
      fieldId: [formField?.fieldId ? formField.fieldId : null],
      defaultValue: [formField?.defaultValue ? formField.defaultValue : null],
      required: [formField?.required ? formField.required : null],
      min: [formField?.min ? formField.min : null],
      max: [formField?.max ? formField.max : null],
      options: this.formBuilder.array(formField?.options ? formField.options : []),
    });
  }

  override createFormSubmissionForm(formSubmission: FormSubmissionVO): FormGroup {
    return this.formBuilder.group({
        id: [{value: formSubmission?.id, disabled: false}],
        createdBy: [{value: formSubmission?.createdBy, disabled: false}],
        updatedBy: [{value: formSubmission?.updatedBy, disabled: false}],
        createdDate: [{value: formSubmission?.createdDate, disabled: false}],
        updatedDate: [{value: formSubmission?.updatedDate, disabled: false}],
        submittedBy: [{value: formSubmission?.submittedBy, disabled: false}],
        submissionDate: [{value: formSubmission?.submissionDate, disabled: false}],
        form: this.createFormVOGroup(formSubmission?.form),
        period: this.createPeriodVOGroup(formSubmission?.period),
        licensee: this.createLicenseeVOGroup(formSubmission?.licensee),
        dataFields: this.createDataFieldVOArray(formSubmission?.dataFields),
        submissionStatus: [{value: formSubmission?.submissionStatus, disabled: false}, [Validators.required, ]],
        upload: [{value: formSubmission?.upload, disabled: false}],
        notes: this.createNoteVOArray(formSubmission?.notes),
        sections: this.createDataFieldSectionVOArray(formSubmission?.sections),
        expectedSubmissionDate: [{value: formSubmission?.expectedSubmissionDate, disabled: false}],
    });
  }

  override createDataFieldSectionVOGroup(value: DataFieldSectionVO): FormGroup {
    return this.formBuilder.group({
      sectionId: [value?.sectionId],
      sectionLabel: [value?.sectionLabel],
      position: [value?.position],
      dataFields: this.createDataFieldVOArray(value.dataFields),
    });
  }

  formDisabled(): boolean {
    return this.submissionStatus == FormSubmissionStatus.SUBMITTED || this.submissionStatus == FormSubmissionStatus.ACCEPTED;
  }

  override createDataFieldVOGroup(dataField: DataFieldVO): FormGroup {
    let value = dataField?.value ? dataField?.value : this.getFieldDefaultValue(dataField);

    return this.formBuilder.group({
      id: [dataField?.id],
      row: [dataField?.row],
      formField: this.createFormFieldForm(dataField?.formField),
      value: [{ value: value, disabled: false } ],
    });
  }

  getFieldDefaultValue(field: DataFieldVO) {
    return field.formField.defaultValue;
  }

  override handleFormChanges(change: any): void { }

  getDataFieldName(data: DataFieldVO) {
    return `${data.formField.fieldName}`;
  }

  getDataFieldValue(data: DataFieldVO) {
    return `${data.value}`;
  }

  getSectorFieldsControls(i: number): FormArray {
    return this.formSubmissionSectionsControl.controls[i].get('dataFields') as FormArray;
  }

  getSectorFields(i: number): DataFieldVO[] {
    let fields: DataFieldVO[] = this.formSubmissionSectionsControl.controls[i].get('dataFields')?.value;
    fields = fields.slice().sort((a: DataFieldVO, b: DataFieldVO) => a.formField.id - b.formField.id);
    return fields;
  }

  getSectionId(i: number): string {
    return this.formSubmissionSections[i].sectionId;
  }

  getSectionLabel(i: number): string {
    return this.formSubmissionSections[i].sectionLabel;
  }

  trackByFn(index: any, item: any) {
    return index;
  }

  isSingleEntry(): boolean {
    return this.formSubmission.form.entryType === FormEntryType.SINGLE;
  }

  getFormField(fieldId: string): FormFieldVO | any {
    let filtered = this.formFields?.filter((field) => field.fieldId === fieldId);

    if (filtered?.length > 0) {
      return filtered[0];
    }

    return null;
  }

  uploadData() {
    this.formSubmissionDataFields.forEach((field) => {
      if (!field.formSubmission) {
        field.formSubmission = new FormSubmissionVO();
        field.formSubmission.id = this.formSubmissionId;
      }

      this.submissionRestController.addDataField(field).subscribe((dataField) => {
        this.addToRowGroup(dataField);
      });
    });
  }

  getFieldKeys(object: any): string[] {
    return Object.keys(object);
  }

  onFileSelected(event: any) {
    if (event) {
      const file: File = event.target.files[0];
      if (!file) {
        return;
      }
      file.text().then((content) => {
        let rows: string[] = content.trim().split('\n');
        let headers: string[] = rows[0].trim().split(',');
        let dataRows: string[] = rows.splice(1);

        for (let i = 0; i < dataRows.length; i++) {
          const row = dataRows[i].trim();
          const rowData = row.split(',');

          if (rowData.length != headers.length) {
            continue;
          }

          for (let j = 0; j < rowData.length; j++) {
            let field: DataFieldVO = new DataFieldVO();
            field.row = i + 1;
            field.formField = this.getFormField(headers[j]);
            field.value = rowData[j];
            field.formSubmission = <FormSubmissionVO>{
              id: this.formSubmissionId,
            };
            this.addToRowGroup(field);
          }
        }
      });
    }
  }

  addToRowGroup(dataField: DataFieldVO) {
    let filteredGroups: RowGroup[] = this.rowGroups.filter((fd) => fd.row === dataField.row);
    let group: RowGroup | undefined = undefined;

    if (!filteredGroups || (filteredGroups && filteredGroups.length == 0)) {
      group = new RowGroup();
      group.row = dataField.row;
      this.rowGroups.push(group);
      this.dataFieldsDataSource.data.push(group);
      this.dataFieldsDataSource.paginator = this.dataFieldsPaginator;
    } else {
      group = filteredGroups[0];
    }
    this.changeDetectorRefs.detectChanges();
    group.fields.push(dataField);
    group.saved = dataField?.id !== null;
    this.formSubmissionDataFieldsControl.push(this.createDataFieldVOGroup(dataField));
  }

  deleteDataRow(row: number, group: RowGroup) {
    if (!group) {
      return;
    }

    if (confirm('Are you sure you want to delete this data row?')) {
      group.fields.forEach((field) => {
        if (field.id) {
          this.submissionRestController.deleteDataField(field.id).subscribe((removed) => {
            const index = this.formSubmissionDataFields.findIndex(
              (fd) => fd.row === field.row && fd.formField.id === field.formField.id
            );
            this.formSubmissionDataFieldsControl.removeAt(index);
          });
        } else {
          const index = this.formSubmissionDataFields.findIndex(
            (fd) => fd.row === field.row && fd.formField.id === field.formField.id
          );
          this.formSubmissionDataFieldsControl.removeAt(index);
        }
      });

      this.rowGroups.splice(row, 1); // remove from the row group array
      this.dataFieldsDataSource.data.splice(row, 1);
      this.dataFieldsDataSource.paginator = this.dataFieldsPaginator;
    }
  }

  doEditDataRow(row: number, group: RowGroup) { }

  getColumnValue(columnId: string, group: RowGroup) {
    if (columnId === 'row') return group.row;

    return this.getDataFieldByFieldId(columnId, group)?.value;
  }

  getColumnHeader(columnId: string, group: RowGroup) {
    return this.getDataFieldByFieldId(columnId, group)?.formField.fieldName;
  }

  getColumnName(columnId: string): string {
    if (columnId === 'status') {
      return 'Status';
    }

    if (columnId === 'row') {
      return 'Row';
    }

    if (columnId === 'actions') {
      return 'Actions';
    }

    let index: number = this.fieldColumnIds.findIndex((colId) => colId === columnId);

    if (index === -1) {
      return '';
    }

    return this.fieldColumns[index];
  }

  getDataFieldByFieldId(columnId: string, group: RowGroup) {
    let field: DataFieldVO[] = group.fields.filter((field) => field.formField.fieldId === columnId);

    if (!field || field.length == 0) {
      return null;
    }

    return field[0];
  }

  actionRowAdded(): string[] {
    return ['status'].concat(this.fieldColumnIds).concat(['actions']);
  }

  hasUnsavedDataFields() {
    for (let i = 0; i < this.rowGroups.length; i++) {
      if (!this.rowGroups[i].saved) {
        return true;
      }
    }

    return false;
  }

  clearData() {
    if (
      confirm(
        'Are you sure you want to clear the data? This will delete all the data associated with this submission from the database.'
      )
    ) {
      while (this.rowGroups.length > 0) {
        let group: RowGroup = this.rowGroups[0];
        group.fields.forEach((field) => {
          if (field.id) {
            this.submissionRestController.deleteDataField(field.id).subscribe((removed) => {
              const index = this.formSubmissionDataFields.findIndex(
                (fd) => fd.row === field.row && fd.formField.id === field.formField.id
              );
              this.formSubmissionDataFieldsControl.removeAt(index);
            });
          } else {
            const index = this.formSubmissionDataFields.findIndex(
              (fd) => fd.row === field.row && fd.formField.id === field.formField.id
            );
            this.formSubmissionDataFieldsControl.removeAt(index);
          }
        });

        this.rowGroups.splice(0, 1);
        this.dataFieldsDataSource.data.splice(0, 1);
        this.dataFieldsDataSource.paginator = this.dataFieldsPaginator;
      }
    }
  }

  getDataFieldId(dataField: DataFieldVO): string {

    return `${dataField.row}_${dataField.formField.fieldId}`;
  }

  scroll(el: HTMLElement) {
    el.scrollIntoView();
  }
  
}