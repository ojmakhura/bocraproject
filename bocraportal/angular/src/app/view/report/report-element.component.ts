// Generated by andromda-angular cartridge (view\view.component.imp.ts.vsl) CAN EDIT!
import { AfterViewInit, Component, EventEmitter, Injector, Input, OnDestroy, OnInit, Output, QueryList, ViewChildren } from '@angular/core';
import { AbstractControl, FormArray, FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { FormVO } from '@app/model/bw/org/bocra/portal/form/form-vo';
import { DataFieldSectionVO } from '@app/model/bw/org/bocra/portal/form/submission/data/data-field-section-vo';
import { DataFieldVO } from '@app/model/bw/org/bocra/portal/form/submission/data/data-field-vo';
import { FormSubmissionVO } from '@app/model/bw/org/bocra/portal/form/submission/form-submission-vo';
import * as SubmissionActions from '@app/store/form/submission/form-submission.actions';
import * as SubmissionSelectors from '@app/store/form/submission/form-submission.selectors';
import { ReportComponent } from '@app/view/report/report.component';
import { select } from '@ngrx/store';
import { ChartData } from 'chart.js';
import { BaseChartDirective } from 'ng2-charts';
import { Observable, of } from 'rxjs';
import { ReportChart } from './report-chart.component';

export class ReportElement {
  groupBy: string = '';
  reportType: string = '';
  reportLabels: string = '';
  dataLabels: string = '';
  selectAllLicensees: boolean = false;
  selectAllPeriods: boolean = false;
  selectAllForms: boolean = false;
  formSubmissions: FormSubmissionVO[] = [];
  selectedLicensees: any[] = [];
  selectedPeriods: string[] = [];
  selectedFields: string[] = [];
  charts: ReportChart[] = []
}

@Component({
  selector: 'app-report-element',
  templateUrl: './report-element.component.html',
})
export class ReportElementComponent  implements OnInit, AfterViewInit, OnDestroy {

  @Input() reportElementGroup: FormGroup | any;
  protected formBuilder: FormBuilder;
  @Input() formSubmissions: FormSubmissionVO[] | undefined;
  @Output() actionIndexEvent = new EventEmitter<number>()

  colors = {};
  
  constructor(private injector: Injector) {
    this.formBuilder = this.injector.get(FormBuilder);
  }

  ngOnInit(): void {
    this.licensees.forEach((lic, index) => {
      this.licenseeSelectionsArray.insert(index, this.createLicenseeSelectionGroup(true, lic))
    });

    this.periods.forEach((per, index) => {
      this.periodSelectionsArray.insert(index, this.createPeriodSelectionGroup(true, per))
    });

    if(!this.fieldSelectionsArray) {
      this.reportElementGroup.addControl('fieldSelection', this.formBuilder.array([]));
    }

    this.fields.forEach((field, index) => {
      this.fieldSelectionsArray.insert(index, this.createFieldSelectionGroup(true, field))
    });

    this.reportTypeControl.patchValue('default')
    this.reportLabelsControl.setValue('fields');

  }

  get licensees() {
    return [...new Set(this.formSubmissions?.map(sub => sub?.licensee?.licenseeName))];
  }

  get periods() {
    return [...new Set(this.formSubmissions?.map(sub => sub?.period?.periodName))]
  }

  get fields() {

    let formFields: string[] = []

    if(this.formSubmissions && this.formSubmissions?.length > 0) {
      this.formSubmissions[0]?.form?.formSections?.forEach(sec => {
        formFields = [...formFields, ...sec?.formFields?.map((field: any) => {
          return {fieldId: field?.fieldId, fieldName: field?.fieldName}
        })];
      });
    }

    return formFields;
  }
  
  generateColors() {
    this.formSubmissions?.forEach(submission => {
      if(this.dataLabels === 'licensees') {
        if(!this.colors[submission?.licensee?.licenseeName]) {
          this.colors[submission?.licensee?.licenseeName] = this.getRandomColor();
        }
      }
    });
  }

  newForm(reportElement: ReportElement): FormGroup {
    
    return this.formBuilder.group({
      groupBy: [reportElement?.groupBy],
      reportType: [reportElement?.reportType],
      selectAllLicensees: [reportElement?.selectAllLicensees],
      selectAllPeriods: [reportElement?.selectAllPeriods],
      selectAllForms: [reportElement?.selectAllForms],
      reportLabels: [reportElement?.reportLabels],
      dataLabels: [reportElement?.dataLabels],
      charts: this.createChartsArrayControl([]),
      licenseeSelections: this.createLicenseeSelectionArray(reportElement?.selectedLicensees),
      periodSelections: this.createPeriodSelectionArray(reportElement?.selectedPeriods),
      fieldSelections: this.createFieldSelectionArray(reportElement?.selectedFields),
    });
  }

  getRandomColor() {
    var length = 6;
    var chars = '0123456789ABCDEF';
    var hex = '#';
    while(length--) hex += chars[(Math.random() * 16) | 0];
    return hex;
  }

  refreshColors() {
    Object.keys(this.colors).forEach(key => {
      this.colors[key] = this.getRandomColor()
    });
  }

  ngAfterViewInit(): void {
    
    this.dataLabelsControl.patchValue('licensees')
    this.generateColors();
  }

  ngOnDestroy(): void {
  }

  test(){
  }

  createChartsArrayControl(charts: ReportChart[]) {
    let chartControl: FormArray = this.formBuilder.array([]);
    charts.forEach(chart => {
      chartControl.push(this.formBuilder.group({
        chartLabel: [chart?.chartLabel],
        chartType: [chart?.chartType],
        chartCaption: [chart?.chartCaption],
      }))
    })
    return chartControl;
  }

  createFieldSelectionArray(fields: any[]) {

    let selections: FormArray = this.formBuilder.array([]);
    this.fields?.forEach(field => {
      selections?.push(this.createLicenseeSelectionGroup(fields?.find(f => f === field) ? true : false, field));
    });
    return selections;
  }

  createFieldSelectionGroup(selected: boolean, field: string): FormGroup {
    return this.formBuilder.group({
      selected: [selected],
      field: [field]
    });
  }

  createLicenseeSelectionArray(licensees: any[]): FormArray {

    let selections: FormArray = this.formBuilder.array([]);
    this.licensees?.forEach(licensee => {
      selections?.push(this.createLicenseeSelectionGroup(licensees?.find(lic => lic === licensee) ? true : false, licensee));
    });
    return selections;
  }

  createLicenseeSelectionGroup(selected: boolean, licensee: string): FormGroup {
    return this.formBuilder.group({
      selected: [selected],
      licensee: [licensee]
    });
  }

  createPeriodSelectionArray(periods: any[]): FormArray {

    let selections: FormArray = this.formBuilder.array([]);
    this.periods?.forEach(period => {
      selections?.push(
        this.createPeriodSelectionGroup(periods?.find(p => p === period) ? true : false, period)
      );
    });
    return selections;
  }

  createPeriodSelectionGroup(selected: boolean, period: string): FormGroup {
    return this.formBuilder.group({
      selected: [selected],
      period: [period]
    });
  }

  periodSelectionChange() {
    let selected = this.periodSelections?.filter(sel => sel.selected);

    this.licenseeSelectionsArray?.controls?.forEach(lc => {
      if(this.filteredFormSubmissions?.find(sub => sub.licensee.licenseeName === lc.value.licensee)) {
        lc.get('selected')?.patchValue(true);
      } else {
        lc.get('selected')?.patchValue(false);
      }
    });
  }

  licenseeSelectionChange() {
    let selected = this.licenseeSelections?.filter(sel => sel.selected);

    this.periodSelectionsArray?.controls?.forEach(pr => {
      if(this.filteredFormSubmissions?.find((sub: FormSubmissionVO) => sub.period.periodName === pr.value.period)) {
        pr.get('selected')?.patchValue(true);
      } else {
        pr.get('selected')?.patchValue(false);
      }
    });
  }

  selectedChartType() {
    // this.report = this.reportForm.value
    // this.formReports = this.report.formReports;

    // this.chart.forEach((c, index) => {
    //   c.type = this.getReportElement(i, j).chartType
    //   c.update();
    // });
    // return this.getReportElement(i, j).chartType ? this.getReportElement(i, j).chartType : 'bar';
  }

  get allLiceseesSelected() {
    return this.licenseeSelections?.filter(sel => sel.selected)?.length === this.licensees.length
  }

  addReportChart() {
    this.chartsControl?.push(this.formBuilder.group({
      chartLabel: [],
      chartType: [],
      chartCaption: [],
    }));
  }

  get reportElement(): ReportElement {
    return this.reportElementGroup.value;
  }

  get reportTypeControl() {
    return this.reportElementGroup?.get('reportType') as FormControl;
  }

  get reportType() {
    return this.reportTypeControl?.value
  }

  get reportLabelsControl() {
    return this.reportElementGroup?.get('reportLabels') as FormControl;
  }

  get reportLabels() {
    return this.reportLabelsControl?.value
  }

  get dataLabelsControl() {
    return this.reportElementGroup?.get('dataLabels') as FormControl;
  }

  get dataLabels() {
    return this.dataLabelsControl?.value
  }

  get groupByControl() {
    return this.reportElementGroup?.get('groupBy') as FormControl;
  }

  get groupBy() {
    return this.groupByControl?.value
  }

  get fieldSelectionsArray(): FormArray {
    return this.reportElementGroup?.get('fieldSelections') as FormArray;

  }

  get fieldSelections(): any[] {
    return this.fieldSelectionsArray?.value
  }

  get licenseeSelectionsArray(): FormArray {
    return this.reportElementGroup?.get('licenseeSelections') as FormArray;

  }

  get licenseeSelections(): any[] {
    return this.licenseeSelectionsArray?.value
  }

  get periodSelectionsArray(): FormArray {
    return this.reportElementGroup?.get('periodSelections') as FormArray;

  }

  get periodSelections(): any[] {
    return this.periodSelectionsArray?.value
  }

  get chartsControl(): FormArray {
    return this.reportElementGroup?.get('charts') as FormArray;
  }

  get charts(): ReportChart[] {
    return this.chartsControl.value;
  }

  removeReportChart(chartIndex: number) {
    this.chartsControl.removeAt(chartIndex);
  }

  selectAllChange(event: any, table: string) {

    let arrayControls: FormArray = this.licenseeSelectionsArray;

    if(table === 'periods') {
      arrayControls = this.periodSelectionsArray;
    } else if(table === 'licensees') {
      arrayControls = this.licenseeSelectionsArray;
    }

    if(event?.target?.checked) {
      arrayControls?.controls?.forEach(value => {
        value.get('selected')?.patchValue(true);
      });
    } else {

      arrayControls?.controls?.forEach(value => {
        value.get('selected')?.patchValue(false);
      });
    }
  }

  selectReportLabels(event: any) {
    // if(event?.target?.value === 'licensees') {
    //   this.labels = this.licenseeSelections.filter(lc => lc.selected).map(lc => lc.licensee);
    // } if(event?.target?.value === 'periods') {
    //   this.labels = this.periodSelections.filter(pr => pr.selected).map(pr => pr.period);
    // }
  }

  reportTypeChange() {
    // if(this.reportType) {
    //   this.reportLabelsControl.patchValue('fields');
    //   this.dataLabelsControl.patchValue('licensees')
    // } 
  }

  /**
   * Filter the form submissions based on the selected periods and
   * selected licensees
   */
  get filteredFormSubmissions(): FormSubmissionVO[] {
    let selectedPeriods = this.periodSelections?.filter(pr => pr.selected);
    let selectedLicensees = this.licenseeSelections?.filter(lc => lc.selected);

    let filtered = this.formSubmissions
      ?.filter(submission => selectedPeriods
        ?.find(pr => pr.period === submission?.period?.periodName))
      ?.filter(submission => selectedLicensees
        ?.find(lc => lc.licensee === submission?.licensee?.licenseeName));

    return filtered ? filtered : [];
  }

  get selectedLabels() {
    if(this.reportLabels === 'licensees') {
      return this.licenseeSelections?.filter(lc => lc.selected).map(lc => lc.licensee);
    } else if(this.reportLabels === 'periods') {
      return this.periodSelections?.filter(pr => pr.selected).map(pr => pr.period);
    } else if(this.reportLabels === 'fields') {
      return this.fieldSelections?.filter(field => field.selected).map(field => field.field);
    }

    return [];
  }

  get selectedDataLabels() {

    if(this.dataLabels === 'licensees') {
      return this.licenseeSelections?.filter(lc => lc.selected).map(lc => lc.licensee);
    } else if(this.reportLabels === 'periods') {
      return this.periodSelections?.filter(pr => pr.selected).map(pr => pr.period);
    } else if(this.reportLabels === 'fields') {
      return this.fieldSelections?.filter(field => field.selected).map(field => field.field);
    }

    return [];
  }
}
