// Generated by andromda-angular cartridge (view\view.component.imp.ts.vsl) CAN EDIT!
import {
  AfterViewInit,
  Component,
  EventEmitter,
  Injector,
  Input,
  OnDestroy,
  OnInit,
  Output,
  QueryList,
  ViewChild,
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
import { ChartConfiguration, ChartData, ChartDataset } from 'chart.js';
import { e, i } from 'mathjs';
import { BaseChartDirective } from 'ng2-charts';
import { Observable, of } from 'rxjs';

export class ReportChart {
  chartLabel: string = '';
  chartType: string = '';
  labels: string[] = [];
  data: number[] = [];
  chartCaption: string = '';
}

@Component({
  selector: 'app-report-chart',
  templateUrl: './report-chart.component.html',
})
export class ReportChartComponent implements OnInit, AfterViewInit, OnDestroy {
  @Input() reportChartGroup: FormGroup | any;
  protected formBuilder: FormBuilder;

  @Input() selectedFields: any[];
  @Input() selectedLicensees: any[];
  @Input() selectedPeriods: any[];

  @Input() reportType: string;
  @Input() formSubmissions: FormSubmissionVO[] | undefined;
  @Input() dataColumns: string;
  @Input() dataRows: string;
  @Input() colors: any;
  @Input() chartIndex: number;
  @Input() additionalDataColumns: any[]
  @Input() additionalDataRows: any;
  @Input() customDataColumns: any;
  @Input() customDataRows: any;

  sections: any[] = [];
  periods: any[] = [];
  labelNames: string[] = [];
  chartData: any = [];

  @ViewChild(BaseChartDirective) chart: BaseChartDirective;

  datasets: ChartDataset[] = [];

  constructor(private injector: Injector) {
    this.formBuilder = this.injector.get(FormBuilder);
  }

  ngOnInit(): void {
    this.setSections();

    this.reportChartGroup.addControl('period', this.formBuilder.control([]));
    this.reportChartGroup.addControl('section', this.formBuilder.control([]));
    this.chartTypeControl.patchValue('bar');
    this.periodControl.patchValue('all');

    this.setLabels();
    this.createChartData();
    this.datasets = this.barChartDataSets();

    console.log(this.customDataRows)
  }

  ngAfterViewInit(): void {}

  ngOnDestroy(): void {}

  createChartData() {
    this.selectedPeriods?.forEach((period) => {
      this.chartData[period.period] = {};
      let pr = this.chartData[period.period];

      this.selectedLicensees?.forEach((lic) => {
        pr[lic.licensee] = {};
        let lc = pr[lic.licensee];

        this.selectedFields?.forEach((field) => {
          let fl = field?.alias;
          if (!fl) {
            fl = field?.fieldName;
          }

          lc[fl] = 0;
        });
      });
    });

    this.formSubmissions?.forEach((submission) => {
      let period = this.chartData[submission?.period?.periodName];
      let licensee = period[submission?.licensee?.licenseeName];

      this.selectedFields?.forEach((field) => {
        let f: DataFieldVO = this.findField(submission, field.fieldId);

        if (f) {
          let fl = field?.alias;

          if (!fl) {
            fl = field?.fieldName;
          }

          licensee[fl] = f.value;
        }
      });
    });
  }

  setLabels() {
    this.labelNames = [];

    if (this.dataColumns === 'fields') {
      this.labelNames = this.selectedFields.map((field) => (field?.alias ? field?.alias : field?.fieldName));
    } else if (this.dataColumns === 'licensees') {
      this.labelNames = [...new Set(this.filteredSubmissions.map((sub) => sub?.licensee?.licenseeName))];
    } else if (this.dataColumns === 'periods') {
      this.labelNames = [...new Set(this.filteredSubmissions.map((sub) => sub?.period.periodName))];
    }

    Object.keys(this.additionalDataColumns)?.forEach(key => this.labelNames.push(this.additionalDataColumns[key].name));
  }

  setSections() {
    let sections = {};
    if (this.formSubmissions && this.formSubmissions.length > 0) {
      this.formSubmissions[0].sections.forEach((section: DataFieldSectionVO) => {
        sections[section.sectionId] = section.sectionLabel;
      });

      this.setPeriods();
    }

    Object.keys(sections).forEach((key) => {
      this.sections.push({
        sectionId: key,
        sectionLabel: sections[key],
      });
    });
  }

  setPeriods() {
    let periods: string[] = [];
    this.formSubmissions?.forEach((submission) => {
      periods.push(submission?.period?.periodName);
    });

    this.periods = [...new Set(periods)];
  }

  newForm(chart: ReportChart): FormGroup {
    return this.formBuilder.group({
      chartLabel: [chart?.chartLabel],
      chartType: [chart?.chartType],
      chartCaption: [chart?.chartCaption],
      labels: this.formBuilder.array(chart?.labels),
      data: this.formBuilder.array(chart?.data),
    });
  }

  selectedPeriod() {
    this.setLabels();
    this.datasets = this.barChartDataSets();
  }

  selectedFormSection() {}

  get chartTypeControl() {
    return this.reportChartGroup.get('chartType') as FormControl;
  }

  get chartType() {
    return this.chartTypeControl.value;
  }

  get periodControl() {
    return this.reportChartGroup.get('period') as FormControl;
  }

  get period() {
    return this.periodControl.value;
  }

  get sectionControl() {
    return this.reportChartGroup.get('section') as FormControl;
  }

  get section() {
    return this.sectionControl.value;
  }

  selectedChartType() {
    if (this.chartType === 'bar') {
      this.datasets = this.barChartDataSets();
    }
  }

  clearReport() {}

  refreshChart() {
    this.setPeriods();
    this.setLabels();
    this.createChartData();
    this.datasets = this.barChartDataSets();
  }

  get filteredSubmissions() {
    if (this.formSubmissions) {
      if (this.period !== 'all') {
        return this.formSubmissions?.filter((submission) => submission?.period?.periodName === this.period);
      } else {
        return this.formSubmissions;
      }
    }

    return [];
  }

  private findField(sub: FormSubmissionVO, fieldId: string): DataFieldVO | any {
    let field: DataFieldVO | any = undefined;

    sub?.sections?.forEach((section: DataFieldSectionVO) => {
      if (!field) field = section?.dataFields?.find((field: DataFieldVO) => field.formField.fieldId === fieldId);
    });

    return field;
  }

  getLicenseeCombinedDataSet(): any {
    let extraction = {};
    console.log(this.chartData)
    if (this.dataColumns === 'fields') {
      this.filteredSubmissions?.forEach((submission) => {
        let licenseeData = this.chartData[submission.period.periodName][submission?.licensee?.licenseeName];

        let dataLabel = submission.licensee?.licenseeName;

        if (this.period === 'all') {
          dataLabel = `${dataLabel}: ${submission?.period?.periodName}`;
        }

        if (!extraction[dataLabel]) {
          extraction[dataLabel] = {
            backgroundColor: this.colors[submission.licensee?.licenseeName],
            data: [],
          };
        }

        Object.keys(licenseeData).forEach((key) => {
          extraction[dataLabel]?.data.push(+licenseeData[key]);
        });

        Object.keys(this.customDataColumns)?.forEach(key => {
          extraction[dataLabel]?.data.push(this.customDataColumns[key][submission.id]);
        });
      });

      console.log(this.chartData)

      Object.keys(this.customDataRows)?.forEach(key1 => {
        Object.keys(this.customDataRows[key1])?.forEach(key2 => {
          let t2 = this.customDataRows[key1][key2];
          let label = `${key2}: ${key1}`;

          if (!extraction[label]) {
            extraction[label] = {
              backgroundColor: this.colors[key2],
              data: [],
            };
          }
          console.log(key1, key2)
          let cd = this.chartData[key1];
          console.log(cd[Object.keys(cd)[0]])
          let cdkeys = Object.keys(cd[Object.keys(cd)[0]]);

          if(cdkeys && cdkeys.length > 0) {
            cdkeys?.forEach(k => {
              extraction[label]?.data.push(t2[k]);
            })
          } else {
            extraction[label].data = Object.values(t2);
          }
        })
      });

      // Object.keys(this.customDataColumns)?.forEach(key => this.labelNames.push(this.customDataColumns[key].name));

    } else if (this.dataColumns === 'periods') {
      
      if (this.period === 'all') {
        this.selectedPeriods?.forEach((period) => {
          this.getPeriodLicenseesCombinedDataset(extraction, period.period);
        });
      } else {
        this.getPeriodLicenseesCombinedDataset(extraction, this.period);
      }

      console.log(this.customDataColumns);

      Object.keys(this.customDataColumns)?.forEach(key => {
        extraction[key]?.data.push(this.customDataColumns[key]);
      }); 
      
    }

    console.log(extraction);

    return this.extractCombinedDatasets(extraction);
  }

  private getPeriodLicenseesCombinedDataset(extraction: any, periodName: string) {
    let periodData = this.chartData[periodName];
    // console.log(extraction)
    // console.log(periodData)
    console.log(this.additionalDataRows)
    
    Object.keys(periodData).forEach(licenseeName => {
      let licenseeData = periodData[licenseeName];
      this.selectedFields?.forEach(sf => {
        let dataLabel = `${licenseeName}: ${sf?.alias ? sf.alias : sf?.fieldName}`;

        if (!extraction[dataLabel]) {
          extraction[dataLabel] = {
            backgroundColor: this.colors[licenseeName],
            data: [],
          };
        }

        let value = licenseeData[sf?.alias ? sf.alias : sf?.fieldName];

        extraction[dataLabel]?.data.push(+value);
      });
    });
  }

  private findSelectedFieldColor(field: string) {
    let color = this.colors[field];
    if(!color) {
      let fid = this.selectedFields.find(fd => field === fd.alias)

      if(!color) {
        fid = this.selectedFields.find(fd => field === fd.fieldName)
      }

      if(fid) {
        color = this.colors[fid.fieldId]
      }
    }

    return color;
  }

  private getPeriodFieldsCombinedDataSet(period: string, extraction: any) {
    this.labelNames?.forEach((label) => {
      if(this.chartData[period] && this.chartData[period][label]) {
        Object.keys(this.chartData[period][label])?.forEach((field) => {
          let tmp = `${period}: ${field}`;
          if (!extraction[tmp]) {
            let color = this.findSelectedFieldColor(field);
            extraction[tmp] = {
              backgroundColor: color,
              data: [],
            };
          }
  
          extraction[tmp].data.push(+this.chartData[period][label][field]);
        });
      }
    });
  }

  getFieldsCombinedDataSet(): any {
    let extraction = {};

    if(this.dataColumns === 'periods') {
      if (this.period === 'all') {
        this.selectedPeriods?.forEach((sp) => {
          this.getFieldPeriodCombinedDatasets(extraction, sp.period);
          
        });
      } else {
        this.getFieldPeriodCombinedDatasets(extraction, this.period);
      }

    } else if(this.dataColumns === 'licensees') {
      if (this.period === 'all') {
        Object.keys(this.chartData)?.forEach((period) => {
          this.getPeriodFieldsCombinedDataSet(period, extraction);

          if(this.customDataColumns[period]) {
            Object.keys(this.customDataColumns[period])?.forEach(key => {
              extraction[period][`${period}: ${key}`] = this.customDataColumns[period][key];
            });
          }
        });
      } else {
        this.getPeriodFieldsCombinedDataSet(this.period, extraction);
      }

    }
    Object.keys(this.customDataColumns)?.forEach(key => {
      extraction[key]?.data.push(this.customDataColumns[key]);
    });

    return this.extractCombinedDatasets(extraction);
  }

  private extractCombinedDatasets(extraction: any): any[] {
    let datasets: any[] = [];
    Object.keys(extraction)?.forEach((key) => {
      datasets.push({
        label: key,
        backgroundColor: extraction[key].backgroundColor,
        data: extraction[key].data,
      });
    });

    return datasets;
  }

  getPeriodCombinedDatasets() {
    let extraction = {};
    

    if(this.dataColumns === 'licensees') {
      if (this.period === 'all') {
        this.selectedPeriods?.forEach((sp) => {
          
          this.getFieldPeriodCombinedDatasets(extraction, sp.period);
          
        });
      } else {
        this.getFieldPeriodCombinedDatasets(extraction, this.period);
      }
    } else if(this.dataColumns === 'fields') {

    }
    
    return this.extractCombinedDatasets(extraction);
  }

  getFieldPeriodCombinedDatasets(extraction: any, periodName: string) {
    
    let period = this.chartData[periodName];
    
    Object.keys(period).forEach(licenseeName => {
      let licensee = period[licenseeName];


      this.selectedFields?.forEach(sf => {
        let tmp = `${licenseeName}: ${sf?.alias ? sf.alias : sf.fieldName}`;
  
        if(!extraction[tmp]) {
          extraction[tmp] = {
            backgroundColor: this.colors[sf?.alias ? sf.alias : sf.fieldName],
            data: [],
          }
        }

        let value = licensee[sf.fieldId];
        if(!value) {
          value = licensee[sf.fieldName];
          if(!value) {
            value = licensee[sf.alias];
          }
        }
        extraction[tmp].data.push(value)
      })
    });
  }

  barChartDataSets(): any[] {
    let datasets: any[] = [];

    if (this.dataRows === 'licensees') {
      datasets = this.getLicenseeCombinedDataSet();
    } else if (this.dataRows === 'periods') {
      
      datasets = this.getPeriodCombinedDatasets();

    } else if (this.dataRows === 'fields') {
      datasets = this.getFieldsCombinedDataSet();
    }
    console.log(datasets);
    datasets.sort((a, b) => (a.label > b.label ? 1 : -1));

    return datasets;
  }
}
