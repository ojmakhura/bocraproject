// Generated by andromda-angular cartridge (view\view.component.imp.ts.vsl) CAN EDIT!
import { ChangeDetectorRef, Component, Injector, ViewChild } from '@angular/core';
import {
  EditFormSubmissionComponent,
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
import { map, Observable, of, tap } from 'rxjs';
import { FormSubmissionVO } from '@app/model/bw/org/bocra/portal/form/submission/form-submission-vo';
import { select } from '@ngrx/store';
import { FormVO } from '@app/model/bw/org/bocra/portal/form/form-vo';
import { FormFieldVO } from '@app/model/bw/org/bocra/portal/form/field/form-field-vo';
import { DataFieldVO } from '@app/model/bw/org/bocra/portal/form/submission/data/data-field-vo';
<<<<<<< HEAD
import { FormArray, FormGroup } from '@angular/forms';
=======
import { FormArray, FormControl, FormGroup } from '@angular/forms';
import { MatRadioChange } from '@angular/material/radio';
import { MatCheckboxChange } from '@angular/material/checkbox';
import { LicenseeVO } from '@app/model/bw/org/bocra/portal/licensee/licensee-vo';
>>>>>>> 225ceba (Multiple data submission)
import { DataFieldSectionVO } from '@app/model/bw/org/bocra/portal/form/submission/data/data-field-section-vo';
import { FormEntryType } from '@app/model/bw/org/bocra/portal/form/form-entry-type';
import { FormSubmissionStatus } from '@app/model/bw/org/bocra/portal/form/submission/form-submission-status';
import { RowGroup } from '@app/model/submission/row-group';
import { SubmissionRestControllerImpl } from '@app/service/bw/org/bocra/portal/form/submission/submission-rest-controller.impl';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { FieldValueType } from '@app/model/bw/org/bocra/portal/form/field/field-value-type';
import * as math from 'mathjs';

export class RowGroup {
  row: number | undefined = undefined;
  fields: DataFieldVO[] = [];
}

@Component({
  selector: 'app-edit-form-submission',
  templateUrl: './edit-form-submission.component.html',
  styleUrls: ['./edit-form-submission.component.scss'],
})
export class EditFormSubmissionComponentImpl extends EditFormSubmissionComponent {
  protected keycloakService: KeycloakService;
  formSubmissions$: Observable<FormSubmissionVO[]>;
  forms$: Observable<FormVO[]>;
<<<<<<< HEAD
  fieldColumns: string[] = ['Row'];
  fieldColumnIds: string[] = ['row'];
=======
  fieldColumns: string[] = []
  fieldColumnIds: string[] = []
>>>>>>> 225ceba (Multiple data submission)
  formFields: FormFieldVO[] = [];
  rowGroups: RowGroup[] = [];

  dataFieldsDataSource = new MatTableDataSource<RowGroup>([]);
  @ViewChild(MatPaginator) dataFieldsPaginator: MatPaginator;
  @ViewChild(MatSort) dataFieldsSort: MatSort;

  constructor(
    private changeDetectorRefs: ChangeDetectorRef,
    private injector: Injector
  ) {
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
<<<<<<< HEAD
      this.rowGroups = [];
      this.formFields = submission?.form?.formFields;

      submission?.sections?.forEach((section) => {
        section.dataFields.forEach((dataField: DataFieldVO) => {
          this.addToRowGroup(dataField);
        });
      });

      this.setEditFormSubmissionFormValue({ formSubmission: submission });
      submission?.form?.formFields?.forEach((field) => {
=======

      this.formFields = submission?.form?.formFields;

      this.setEditFormSubmissionFormValue({ formSubmission: submission });
      submission?.form?.formFields?.forEach(field => {
>>>>>>> 225ceba (Multiple data submission)
        this.fieldColumns.push(field.fieldName);
        this.fieldColumnIds.push(field.fieldId);
      });
    });

<<<<<<< HEAD
    this.dataFieldsDataSource.paginator = this.dataFieldsPaginator;
  }

  onRowChange(section: any, row: number) {
    let sec: DataFieldSectionVO = section.value;
    let changeField: DataFieldVO = section.value.dataFields[row];
    let calculationFields: FormGroup[] = [];

    // Find the caculation fields controls
    for(let i = 0; i < this.formSubmissionSectionsControl.length; i++) {
      let fieldsControls: FormArray = this.formSubmissionSectionsControl.controls[i].get('dataFields') as FormArray;
      for (let i = 0; i < fieldsControls.length; i++) {
        let fieldControl: FormGroup = <FormGroup>fieldsControls.controls[i];
        let field: DataFieldVO = fieldControl.value;
        let formField: FormFieldVO = field.formField;
        if (
          formField.fieldValueType === FieldValueType.CALCULATED &&
          formField.expression.includes(`[${changeField.formField.fieldId}]`)
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
        if(expression.includes(`[${field.formField.fieldId}]`)) {
          expression = expression.replace(`[${field.formField.fieldId}]`, field.value);
        }
      }

      fieldControl?.get('value')?.setValue(math.evaluate(expression));
    });
=======
>>>>>>> 225ceba (Multiple data submission)
  }

  doNgOnDestroy() { }

  /**
   * This method may be overwritten
   */
  override beforeEditFormSubmissionSave(form: EditFormSubmissionSaveForm): void {
    let formSubmission: FormSubmissionVO = form.formSubmission;
    formSubmission.submissionStatus = FormSubmissionStatus.DRAFT;
    console.log(formSubmission)
    console.log(this.formSubmissionDataFieldsControl.value)
    //this.doFormSubmissionSave(formSubmission);
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

  override createDataFieldSectionVOGroup(value: DataFieldSectionVO): FormGroup {
    return this.formBuilder.group({
      sectionId: [value?.sectionId],
      sectionLabel: [value?.sectionLabel],
      position: [value?.position],
      dataFields: this.createDataFieldVOArray(value.dataFields),
    });
  }

  override createDataFieldVOGroup(dataField: DataFieldVO): FormGroup {
    let disable = dataField.formField.fieldValueType === FieldValueType.CALCULATED;
    return this.formBuilder.group({
<<<<<<< HEAD
      id: [dataField?.id],
      row: [dataField?.row],
      formField: this.createFormFieldForm(dataField?.formField),
      value: [dataField?.value ? {value: dataField?.value, disabled: disable} : {value: this.getFieldDefaultValue(dataField), disabled: disable} ],
=======
      id: [value?.id],
      row: [value?.row],
      formField: this.createFormFieldForm(value?.formField),
      value: [value?.value ? value?.value : this.getFieldDefaultValue(value)],
>>>>>>> 225ceba (Multiple data submission)
    });
  }

  getFieldDefaultValue(field: DataFieldVO) {
    return field.formField.defaultValue;
  }

  override handleFormChanges(change: any): void {}

  getDataValue(data: DataFieldVO) {
    return `${data.formField.fieldName}`;
  }

  getSectorFieldsControls(i: number): FormArray {
    return this.formSubmissionSectionsControl.controls[i].get('dataFields') as FormArray;
  }

  getSectorFields(i: number): DataFieldVO[] {
    return this.formSubmissionSectionsControl.controls[i].get('dataFields')?.value;
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
<<<<<<< HEAD
    let filtered = this.formFields?.filter((field) => field.fieldId === fieldId);

    if (filtered?.length > 0) {
=======
    let filtered = this.formFields.filter(field => field.fieldId === fieldId);

    if (filtered.length > 0) {
>>>>>>> 225ceba (Multiple data submission)
      return filtered[0];
    }

    return null;
  }

  uploadData() {
<<<<<<< HEAD
    this.formSubmissionDataFields.forEach((field) => {
      if (!field.formSubmission) {
        field.formSubmission = new FormSubmissionVO();
        field.formSubmission.id = this.formSubmissionId;
      }

      this.submissionRestController.addDataField(field).subscribe((dataField) => {
        this.addToRowGroup(dataField);
      });
    });
=======

>>>>>>> 225ceba (Multiple data submission)
  }

  getFieldKeys(object: any): string[] {
    return Object.keys(object);
  }

  onFileSelected(event: any) {
    if (event) {
<<<<<<< HEAD
      const file: File = event.target.files[0];
      if (!file) {
        return;
      }
      file.text().then((content) => {
=======

      const file: File = event.target.files[0];
      file.text().then(content => {

>>>>>>> 225ceba (Multiple data submission)
        let rows: string[] = content.trim().split('\n');
        let headers: string[] = rows[0].trim().split(',');
        let dataRows: string[] = rows.splice(1);

        for (let i = 0; i < dataRows.length; i++) {
          const row = dataRows[i].trim();
          const rowData = row.split(',');

          if (rowData.length != headers.length) {
            continue;
          }
<<<<<<< HEAD

          for (let j = 0; j < rowData.length; j++) {
=======
          //let dt: FormGroup = this.formBuilder.group({});
          //dt.addControl('id', new FormControl());
          //dt.addControl('row', this.formBuilder.control(i + 1));

          for (let j = 0; j < rowData.length; j++) {
            //dt.addControl(headers[j], this.formBuilder.control(rowData[j]));
>>>>>>> 225ceba (Multiple data submission)
            let field: DataFieldVO = new DataFieldVO();
            field.row = i + 1;
            field.formField = this.getFormField(headers[j]);
            field.value = rowData[j];
            field.formSubmission = <FormSubmissionVO>{
<<<<<<< HEAD
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

  doEditDataRow(row: number, group: RowGroup) {}

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
=======
              id: this.formSubmissionId
            };
            
            let filteredGroups: RowGroup[] = this.rowGroups.filter(fd => fd.row === field.row);
            let group: RowGroup | undefined = undefined

            if (!filteredGroups || (filteredGroups && filteredGroups.length == 0)) {
              group = new RowGroup();
              group.row = field.row;
              this.rowGroups.push(group);
            } else {
              group = filteredGroups[0];
            }

            group.fields.push(field);
            this.formSubmissionDataFieldsControl.push(this.createDataFieldVOGroup(field));
          }

          //this.dataFieldsControl.push(dt);

        }
      });

    }
  }

  getRowDataFields(row: any): DataFieldVO[] {

    let keys = Object.keys(row).filter(key => key !== 'row'); // no need to get the 'row'
    let fields: DataFieldVO[] = [];
    keys.forEach(key => {
      let field: DataFieldVO = new DataFieldVO();
      field.row = row.row;
      field.id = row.id;
    });

    return [];
  }

  get groupedDataFields(): RowGroup[] {
    let groups: RowGroup[] = [];

    this.formSubmissionDataFieldsControl.value.forEach((dataField: any) => {
      let filteredGroups: RowGroup[] = groups.filter(field => field.row === dataField.row);
      let group: RowGroup | undefined = undefined

      if (!filteredGroups || (filteredGroups && filteredGroups.length == 0)) {
        group = new RowGroup();
        group.row = dataField.row;
        groups.push(group);
      } else {
        group = filteredGroups[0];
      }

      group.fields.push(dataField);

    });

    return this.rowGroups;
  }

  getColumnData(row: number, columnId: string) {
    console.log(row, columnId);
    return 'val'
>>>>>>> 225ceba (Multiple data submission)
  }
}
