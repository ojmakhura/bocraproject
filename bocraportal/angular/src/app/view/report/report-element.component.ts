// Generated by andromda-angular cartridge (view\view.component.imp.ts.vsl) CAN EDIT!
import { formatNumber } from '@angular/common';
import {
  AfterViewInit,
  Component,
  ElementRef,
  EventEmitter,
  Inject,
  Injector,
  Input,
  LOCALE_ID,
  OnDestroy,
  OnInit,
  Output,
  QueryList,
  ViewChildren,
} from '@angular/core';
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
import * as math from 'mathjs';
import { i } from 'mathjs';
import { BaseChartDirective } from 'ng2-charts';
import { Observable, of } from 'rxjs';
import { ReportChart } from './report-chart.component';

export class ReportElement {
  groupBy: string = '';
  reportType: string = '';
  dataColumns: string = '';
  dataRows: string = '';
  selectAllLicensees: boolean = false;
  selectAllPeriods: boolean = false;
  selectAllForms: boolean = false;
  formSubmissions: FormSubmissionVO[] = [];
  selectedLicensees: any[] = [];
  selectedPeriods: string[] = [];
  selectedFields: string[] = [];
  charts: ReportChart[] = [];
}

@Component({
  selector: 'app-report-element',
  templateUrl: './report-element.component.html',
})
export class ReportElementComponent implements OnInit, AfterViewInit, OnDestroy {
  @Input() reportElementGroup: FormGroup | any;
  protected formBuilder: FormBuilder;
  @Input() formSubmissions: FormSubmissionVO[] | undefined;
  @Output() actionIndexEvent = new EventEmitter<number>();

  colors = {};
  selectedLicensees: any[] = [];
  selectedFields: any[] = [];
  selectedPeriods: any[] = [];
  additionalDataColumns: any[] = [];
  additionalReportCalculations: any[] = [];
  additionalDataRows: any[] = [];
  customDataColumns: any = {};
  customDataRows: any = {};

  constructor(private injector: Injector, @Inject(LOCALE_ID) public locale: string) {
    this.formBuilder = this.injector.get(FormBuilder);
    console.log(this.formSubmissions)
    console.log(this.reportElementGroup)
  }

  ngOnInit(): void {
    this.licensees.forEach((lic, index) => {
      this.licenseeSelectionsArray.insert(index, this.createLicenseeSelectionGroup(true, lic));
    });

    this.periods.forEach((per, index) => {
      this.periodSelectionsArray.insert(index, this.createPeriodSelectionGroup(true, per));
    });

    if (!this.fieldSelectionsArray) {
      this.reportElementGroup.addControl('fieldSelection', this.formBuilder.array([]));
    }

    this.fields.forEach((field, index) => {
      this.fieldSelectionsArray.insert(index, this.createFieldSelectionGroup(true, field));
    });

    this.reportTypeControl.patchValue('default');
    this.dataColumnsControl.setValue('fields');

    this.reportElementGroup.addControl('dataColumnsAnalytics', this.formBuilder.array([]));
    this.reportElementGroup.addControl('dataRowsAnalytics', this.formBuilder.array([]));
  }

  get licensees() {
    return [...new Set(this.formSubmissions?.map((sub) => sub?.licensee?.licenseeName))];
  }

  get periods() {
    return [...new Set(this.formSubmissions?.map((sub) => sub?.period?.periodName))];
  }

  get fields() {
    let formFields: string[] = [];

    if (this.formSubmissions && this.formSubmissions?.length > 0) {
      this.formSubmissions[0]?.form?.formSections?.forEach((sec) => {
        formFields = [
          ...formFields,
          ...sec?.formFields?.map((field: any) => {
            return { fieldId: field?.fieldId, fieldName: field?.fieldName };
          }),
        ];
      });
    }

    return formFields;
  }

  generateColors(reset: boolean) {
    if (reset) {
      this.colors = {};
    }

    if (this.formSubmissions) {
      if (this.dataRows === 'fields') {
        this.formSubmissions[0].sections?.forEach((section: DataFieldSectionVO) => {
          section?.dataFields?.forEach((field: DataFieldVO) => {
            this.colors[field.formField.fieldId] = this.getRandomColor();
          });
        });
      } else if (this.dataRows === 'licensees') {
        this.formSubmissions?.forEach((submission) => {
          if (!this.colors[submission?.licensee?.licenseeName]) {
            this.colors[submission?.licensee?.licenseeName] = this.getRandomColor();
          }
        });
      } else if (this.dataRows === 'periods') {
        this.formSubmissions?.forEach((submission) => {
          this.colors[submission?.period?.periodName] = this.getRandomColor();
        });
      }
    }

    if (this.dataRowsAnalytics?.length > 0) {
      this.dataRowsAnalytics?.forEach((analytic) => {
        this.colors[analytic?.name] = this.getRandomColor();
      });
    }

    this.dataRowsAnalytics?.forEach(row => {
      if(row?.name) {
        this.colors[row?.name] = this.getRandomColor();
      }
    })
  }

  newForm(reportElement: ReportElement): FormGroup {
    return this.formBuilder.group({
      groupBy: [reportElement?.groupBy],
      reportType: [reportElement?.reportType],
      selectAllLicensees: [reportElement?.selectAllLicensees],
      selectAllPeriods: [reportElement?.selectAllPeriods],
      selectAllForms: [reportElement?.selectAllForms],
      dataColumns: [reportElement?.dataColumns],
      dataRows: [reportElement?.dataRows],
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
    while (length--) hex += chars[(Math.random() * 16) | 0];
    return hex;
  }

  refreshColors() {
    Object.keys(this.colors).forEach((key) => {
      this.colors[key] = this.getRandomColor();
    });
  }

  ngAfterViewInit(): void {
    this.dataRowsControl.patchValue('licensees');
    this.generateColors(false);
    this.periodSelectionChange();
    this.fieldSelectionChange();
    this.licenseeSelectionChange();
  }

  ngOnDestroy(): void {}

  private calculate(values: number[], calculationType: string) {
    if (calculationType === 'sum') {
      return math.sum(values);
    } else if (calculationType === 'mean') {
      return math.mean(values);
    } else if (calculationType === 'mode') {
      return math.mode(values);
    } else if (calculationType === 'median') {
      return math.median(values);
    } else if (calculationType === 'variance') {
      return math.variance(values);
    } else if (calculationType === 'std') {
      return math.std(values);
    } else if (calculationType === 'min') {
      return math.min(values);
    } else if (calculationType === 'max') {
      return math.max(values);
    }
  }

  additionalDataColumnChange(index: number) {
    this.generateColors(false);
    this.periodSelectionChange();
    this.fieldSelectionChange();
    this.licenseeSelectionChange();

    this.additionalDataColumns = this.dataColumnsAnalytics;
    let fields: DataFieldVO[] = [];
    this.filteredFormSubmissions[0]?.sections?.forEach((section: DataFieldSectionVO) => {
      fields = [...fields, ...section?.dataFields];
    });

    let changedlabel: any = this.additionalDataColumns[index];

    if (!changedlabel?.type || !changedlabel?.name || !changedlabel?.sources) {
      return;
    }
    let sourceString: string = changedlabel?.sources;

    if (this.dataColumns === 'fields' && this.dataRows === 'licensees') {
      this.customDataColumns[index] = {
        name: changedlabel?.name,
      };

      this.filteredFormSubmissions?.forEach((submission) => {
        let fields: DataFieldVO[] = [];
        submission?.sections?.forEach((section: DataFieldSectionVO) => {
          fields = [...fields, ...section?.dataFields];
        });

        if (changedlabel?.type === 'custom') {
          let expression = sourceString;
          fields.forEach((field) => {
            if (expression?.includes(`[${field.formField.fieldId}]`)) {
              expression = expression?.replace(`[${field.formField.fieldId}]`, field.value);
            }
          });

          this.customDataColumns[index][submission?.id] = this.formatCalculation(math.evaluate(expression));
        } else {
          let sources: string[] = sourceString
            ?.split(',')
            ?.map((val) => val.trim())
            ?.filter((val) => val?.length > 0);

          let calcFields = fields?.filter((field) => sources?.find((source) => (source === 'all' || field?.formField?.fieldId === source)));
          let calValues = calcFields?.map((value) => +value?.value);
          if (calValues && calValues.length > 0) {
            let calc = this.formatCalculation(this.calculate(calValues, changedlabel?.type));
            this.customDataColumns[index][submission?.id] = calc;
          }
        }
      });
    } else if (this.dataColumns === 'licensees' && this.dataRows === 'fields') {
      this.selectedPeriods?.forEach((period) => {
        let tmp = this.filteredFormSubmissions?.filter((submission) => {
          return submission?.period?.periodName === period;
        });

        if (changedlabel?.type === 'custom') {
        } else {
          let sources: string[] = sourceString
            ?.split(',')
            ?.map((val) => val.trim())
            ?.filter((val) => val?.length > 0);
          let sourceData = {};
          sources?.forEach((source) => {
            sourceData[source] = [];

            tmp?.forEach((submission) => {
              let fields: DataFieldVO[] = [];
              submission?.sections?.forEach((section: DataFieldSectionVO) => {
                fields = [...fields, ...section?.dataFields];
              });

              let selectedField = fields?.find((field) => field.formField?.fieldId === source);
              if (source === 'all' || selectedField) {
                sourceData[source].push(+selectedField?.value);
              }
            });
          });

          Object.keys(sourceData)?.forEach((key) => {
            let selectedField = fields?.find((field) => field.formField?.fieldId === key);
            this.customDataColumns[`${period}: ${selectedField?.formField?.fieldName}`] = this.formatCalculation(this.calculate(
              sourceData[key],
              changedlabel?.type
            ));
          });
        }
      });
    } else if (this.dataColumns === 'periods' && this.dataRows === 'fields') {
      if (changedlabel?.type === 'custom') {
      } else {
        let sourceData = {};
        this.filteredFormSubmissions?.forEach((submission) => {
          let fields: DataFieldVO[] = [];
          submission?.sections?.forEach((section: DataFieldSectionVO) => {
            fields = [...fields, ...section?.dataFields];
          });

          fields?.forEach((field) => {
            if (!sourceData[`${submission?.licensee?.licenseeName}: ${field?.formField?.fieldName}`]) {
              sourceData[`${submission?.licensee?.licenseeName}: ${field?.formField?.fieldName}`] = [];
            }

            sourceData[`${submission?.licensee?.licenseeName}: ${field?.formField?.fieldName}`]?.push(+field.value);
          });
        });

        Object.keys(sourceData)?.forEach((key) => {
          this.customDataColumns[key] = this.formatCalculation(this.calculate(sourceData[key], changedlabel?.type));
        });
      }
    } else if (this.dataColumns === 'periods' && this.dataRows === 'licensees') {
      if (changedlabel?.type === 'custom') {
      } else {
        let sourceData = {};
        this.filteredFormSubmissions?.forEach((submission) => {
          let fields: DataFieldVO[] = [];
          submission?.sections?.forEach((section: DataFieldSectionVO) => {
            fields = [...fields, ...section?.dataFields];
          });

          fields?.forEach((field) => {
            if (!sourceData[`${submission?.licensee?.licenseeName}: ${field?.formField?.fieldName}`]) {
              sourceData[`${submission?.licensee?.licenseeName}: ${field?.formField?.fieldName}`] = [];
            }

            sourceData[`${submission?.licensee?.licenseeName}: ${field?.formField?.fieldName}`]?.push(+field.value);
          });
        });

        Object.keys(sourceData)?.forEach((key) => {
          this.customDataColumns[key] = this.formatCalculation(this.calculate(sourceData[key], changedlabel?.type));
        });
      }
    }
  }

  extractFieldNames(formSubmission: FormSubmissionVO) {
    let fields: DataFieldVO[] = [];
    formSubmission?.sections?.forEach((section: DataFieldSectionVO) => {
      fields = [...fields, ...section.dataFields];
    });

    return fields;
  }

  concatenate(first: string, second: string): string {
    return `${first}: ${second}`;
  }

  getSources(sources: string) {
    let sourceList: string[][] = []

    let sourceSplit: string[] = sources
                  ?.split('::')
                  ?.map((val: any) => val.trim())
                  ?.filter((val: any) => val?.length > 0);
  
    sourceList.push(
      sourceSplit[0]?.split(',')
                        ?.map((val: any) => val.trim())
                        ?.filter((val: any) => val?.length > 0)
    );


    if(sourceSplit.length == 2) {
      sourceList.push(
        sourceSplit[1]?.split(',')
                          ?.map((val: any) => val.trim())
                          ?.filter((val: any) => val?.length > 0)
      );
    }
  
    return sourceList;
  }

  additionalRowChange(index: number) {
    this.generateColors(false);
    this.periodSelectionChange();
    this.fieldSelectionChange();
    this.licenseeSelectionChange();

    this.additionalDataRows = this.dataRowsAnalytics;
    let changingRow = this.dataRowsAnalytics[index];

    let tmp = {};

    if (this.dataColumns === 'fields' && this.dataRows === 'licensees') {
      if (changingRow?.type === 'custom') {
        let sourceString = changingRow?.sources;
      } else {
        let licensees: string[] = [];
        let fields: string[] = [];

        if(!changingRow?.sources) {
          return;
        }

        let sourceSplit = this.getSources(changingRow?.sources);

        licensees = sourceSplit[0]

        if(sourceSplit.length == 2) {
          fields = sourceSplit[1]
        }
        licensees?.forEach((licensee) => {
          this.filteredFormSubmissions?.forEach((submission) => {
            let key = this.concatenate(changingRow?.name, submission?.period?.periodName);
            if (!tmp[key]) {
              tmp[key] = {};
            }

            let lc = tmp[key];
            submission?.sections?.forEach((section: DataFieldSectionVO) => {
              section?.dataFields?.forEach((field: DataFieldVO) => {
                if (!lc[field?.formField?.fieldName]) {
                  lc[field?.formField?.fieldName] = [];
                }
                if (licensee === 'all' || submission?.licensee?.licenseeName === licensee) {
                  if(fields.length === 0) {
                    lc[field?.formField?.fieldName]?.push(+field.value);
                  } else {
                    fields?.forEach(f => {
                      if(f === 'all' || f === field?.formField?.fieldId) {

                      lc[field?.formField?.fieldName]?.push(+field.value);
                      }
                    });
                  }
                }
              });
            });
          });
        });
      }

      Object.keys(tmp)?.forEach((key) => {
        this.customDataRows[`${key}`] = {};
        let t = tmp[key];
        this.customDataRows[key] = {};
        Object.keys(t)?.forEach((tk) => {
          if(t[tk].length > 0)
            this.customDataRows[`${key}`][tk] = this.formatCalculation(this.calculate(t[tk], changingRow?.type));
          
        });
      });

    } else if (this.dataColumns === 'periods' && this.dataRows === 'licensees') {
    } else if (this.dataColumns === 'licensees' && this.dataRows === 'fields') {
      if (changingRow?.type === 'custom') {
        let sourceString = changingRow?.sources;
      } else {
        let licensees: string[] = [];
        let fields: string[] = [];

        if(!changingRow?.sources) {
          return;
        }

        let sourceSplit: string[][] = this.getSources(changingRow?.sources)

        fields = sourceSplit[0];

        if(sourceSplit.length == 2) {
          licensees = sourceSplit[1];
        }
        licensees?.forEach((licensee) => {
          this.filteredFormSubmissions?.forEach((submission) => {
            let key = this.concatenate(submission?.period?.periodName, changingRow?.name);
            if (!tmp[key]) {
              tmp[key] = {};
            }

            let lc = tmp[key];

            if (!lc[submission?.licensee?.licenseeName]) {
              lc[submission?.licensee?.licenseeName] = [];
            }

            submission?.sections?.forEach((section: DataFieldSectionVO) => {
              section?.dataFields?.forEach((field: DataFieldVO) => {
                
                if (licensee === 'all' || submission?.licensee?.licenseeName === licensee) {
                  if(fields.length === 0) {
                    lc[submission?.licensee?.licenseeName]?.push(+field.value);
                  } else {
                    fields?.forEach(f => {
                      if(f === 'all' || f === field?.formField?.fieldId) {

                      lc[submission?.licensee?.licenseeName]?.push(+field.value);
                      }
                    });
                  }
                }
              });
            });
          });
        });
      }

      Object.keys(tmp)?.forEach((key) => {
        this.customDataRows[`${key}`] = {};
        let t = tmp[key];
        this.customDataRows[key] = {};
        Object.keys(t)?.forEach((tk) => {
          if(t[tk].length > 0)
            this.customDataRows[`${key}`][tk] = this.formatCalculation(this.calculate(t[tk], changingRow?.type));
          
        });
      });

    } else if (this.dataColumns === 'periods' && this.dataRows === 'fields') {
    }
  }

  private formatCalculation(val: number) {
    return +formatNumber(val, this.locale, '1.0-2').replace(',', '');
  }

  createChartsArrayControl(charts: ReportChart[]) {
    let chartControl: FormArray = this.formBuilder.array([]);
    charts.forEach((chart) => {
      chartControl.push(
        this.formBuilder.group({
          chartLabel: [chart?.chartLabel],
          chartType: [chart?.chartType],
          chartCaption: [chart?.chartCaption],
        })
      );
    });
    return chartControl;
  }

  createFieldSelectionArray(fields: any[]) {
    let selections: FormArray = this.formBuilder.array([]);
    this.fields?.forEach((field) => {
      selections?.push(this.createFieldSelectionGroup(fields?.find((f) => f === field) ? true : false, field));
    });
    return selections;
  }

  createFieldSelectionGroup(selected: boolean, field: any): FormGroup {
    return this.formBuilder.group({
      selected: [selected],
      fieldId: [field?.fieldId],
      fieldName: [field?.fieldName],
      alias: [field?.fieldName],
    });
  }

  createLicenseeSelectionArray(licensees: any[]): FormArray {
    let selections: FormArray = this.formBuilder.array([]);
    this.licensees?.forEach((licensee) => {
      selections?.push(
        this.createLicenseeSelectionGroup(licensees?.find((lic) => lic === licensee) ? true : false, licensee)
      );
    });
    return selections;
  }

  createLicenseeSelectionGroup(selected: boolean, licensee: string): FormGroup {
    return this.formBuilder.group({
      selected: [selected],
      licensee: [licensee],
    });
  }

  createPeriodSelectionArray(periods: any[]): FormArray {
    let selections: FormArray = this.formBuilder.array([]);
    this.periods?.forEach((period) => {
      selections?.push(this.createPeriodSelectionGroup(periods?.find((p) => p === period) ? true : false, period));
    });
    return selections;
  }

  createPeriodSelectionGroup(selected: boolean, period: string): FormGroup {
    return this.formBuilder.group({
      selected: [selected],
      period: [period],
    });
  }

  periodSelectionChange() {
    this.selectedPeriods = this.periodSelections?.filter((sel) => sel.selected);
    this.selectedLicensees = this.licenseeSelections?.filter((sel) => sel.selected);

    this.licenseeSelectionsArray?.controls?.forEach((lc) => {
      if (this.filteredFormSubmissions?.find((sub) => sub.licensee.licenseeName === lc.value.licensee)) {
        lc.get('selected')?.patchValue(true);
      } else {
        lc.get('selected')?.patchValue(false);
      }
    });
  }

  fieldSelectionChange() {
    this.selectedFields = this.fieldSelections?.filter((sel) => sel.selected);
  }

  licenseeSelectionChange() {
    this.selectedLicensees = this.licenseeSelections?.filter((sel) => sel.selected);
    this.selectedPeriods = this.periodSelections?.filter((sel) => sel.selected);

    this.periodSelectionsArray?.controls?.forEach((pr) => {
      if (this.filteredFormSubmissions?.find((sub: FormSubmissionVO) => sub.period.periodName === pr.value.period)) {
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
    return this.licenseeSelections?.filter((sel) => sel.selected)?.length === this.licensees.length;
  }

  addReportChart() {
    this.chartsControl?.push(
      this.formBuilder.group({
        chartLabel: [],
        chartType: [],
        chartCaption: [],
      })
    );
  }

  get dataColumnsAnalyticsControl(): FormArray {
    return this.reportElementGroup?.get('dataColumnsAnalytics') as FormArray;
  }

  addLabelsAnalytic(target: string) {
    if (target === 'report') {
      this.dataColumnsAnalyticsControl.push(
        this.formBuilder.group({
          type: [],
          sources: [],
          name: [],
        })
      );
    } else {
      this.dataRowsAnalyticsControl.push(
        this.formBuilder.group({
          type: [],
          sources: [],
          name: [],
        })
      );
    }

    this.additionalDataRows = this.dataRowsAnalytics;
    // this.additionalDataLabelChange();
  }

  removeLabelsAnalytic(target: string, index: number) {
    if (target === 'report') {
      this.dataColumnsAnalyticsControl.removeAt(index);
      this.additionalDataColumnChange(index);
    } else {
      this.dataRowsAnalyticsControl.removeAt(index);
      this.additionalDataRows = this.dataRowsAnalytics;
      // this.additionalDataLabelChange();
    }

    this.customDataColumns = {};
    this.additionalDataColumns?.forEach((value, index) => {
      this.additionalDataColumnChange(index);
    });
  }

  removeFromArray(arrayControl: FormArray, index: number) {
    arrayControl.removeAt(index);
  }

  get dataColumnsAnalytics(): any[] {
    return this.dataColumnsAnalyticsControl.value;
  }

  get dataRowsAnalyticsControl(): FormArray {
    return this.reportElementGroup?.get('dataRowsAnalytics') as FormArray;
  }

  get dataRowsAnalytics(): any[] {
    return this.dataRowsAnalyticsControl.value;
  }

  get reportElement(): ReportElement {
    return this.reportElementGroup.value;
  }

  get reportTypeControl() {
    return this.reportElementGroup?.get('reportType') as FormControl;
  }

  get reportType() {
    return this.reportTypeControl?.value;
  }

  get dataColumnsControl() {
    return this.reportElementGroup?.get('dataColumns') as FormControl;
  }

  get dataColumns() {
    return this.dataColumnsControl?.value;
  }

  get dataRowsControl() {
    return this.reportElementGroup?.get('dataRows') as FormControl;
  }

  get dataRows() {
    return this.dataRowsControl?.value;
  }

  get groupByControl() {
    return this.reportElementGroup?.get('groupBy') as FormControl;
  }

  get groupBy() {
    return this.groupByControl?.value;
  }

  get fieldSelectionsArray(): FormArray {
    return this.reportElementGroup?.get('fieldSelections') as FormArray;
  }

  get fieldSelections(): any[] {
    return this.fieldSelectionsArray?.value;
  }

  get licenseeSelectionsArray(): FormArray {
    return this.reportElementGroup?.get('licenseeSelections') as FormArray;
  }

  get licenseeSelections(): any[] {
    return this.licenseeSelectionsArray?.value;
  }

  get periodSelectionsArray(): FormArray {
    return this.reportElementGroup?.get('periodSelections') as FormArray;
  }

  get periodSelections(): any[] {
    return this.periodSelectionsArray?.value;
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

    if (table === 'periods') {
      arrayControls = this.periodSelectionsArray;
    } else if (table === 'licensees') {
      arrayControls = this.licenseeSelectionsArray;
    }

    if (event?.target?.checked) {
      arrayControls?.controls?.forEach((value) => {
        value.get('selected')?.patchValue(true);
      });
    } else {
      arrayControls?.controls?.forEach((value) => {
        value.get('selected')?.patchValue(false);
      });
    }
  }

  selectDataColumns(event: any) {
    // if(event?.target?.value === 'licensees') {
    //   this.labels = this.licenseeSelections.filter(lc => lc.selected).map(lc => lc.licensee);
    // } if(event?.target?.value === 'periods') {
    //   this.labels = this.periodSelections.filter(pr => pr.selected).map(pr => pr.period);
    // }
  }

  reportTypeChange() {
    // if(this.reportType) {
    //   this.dataColumnsControl.patchValue('fields');
    //   this.dataRowsControl.patchValue('licensees')
    // }
  }

  /**
   * Filter the form submissions based on the selected periods and
   * selected licensees
   */
  get filteredFormSubmissions(): FormSubmissionVO[] {
    let selectedPeriods = this.periodSelections?.filter((pr) => pr.selected);
    let selectedLicensees = this.licenseeSelections?.filter((lc) => lc.selected);

    let filtered = this.formSubmissions
      ?.filter((submission) => selectedPeriods?.find((pr) => pr.period === submission?.period?.periodName))
      ?.filter((submission) => selectedLicensees?.find((lc) => lc.licensee === submission?.licensee?.licenseeName));

    return filtered ? filtered : [];
  }

  extractDataColumns(source: string) {
    if (source === 'licensees') {
      return this.licenseeSelections?.filter((lc) => lc.selected).map((lc) => lc.licensee);
    } else if (source === 'periods') {
      return this.periodSelections?.filter((pr) => pr.selected).map((pr) => pr.period);
    } else if (source === 'fields') {
      return this.fieldSelections
        ?.filter((field) => field.selected)
        .map((field) => (field.alias ? field.alias : field.fieldName));
    }

    return [];
  }
}
