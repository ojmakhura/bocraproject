// Generated by andromda-angular cartridge (view\view.component.imp.ts.vsl) CAN EDIT!
import { AfterViewInit, Component, EventEmitter, Injector, Input, OnDestroy, OnInit, Output, QueryList, ViewChild, ViewChildren } from '@angular/core';
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
export class ReportChartComponent  implements OnInit, AfterViewInit, OnDestroy {

  @Input() reportChartGroup: FormGroup | any;
  protected formBuilder: FormBuilder;
  @Input() reportType: string;
  @Input() formSubmissions: FormSubmissionVO[] | undefined;
  @Input() labels: any[];
  @Input() selectedDataLabels: string[];
  @Input() dataLabels: string;
  @Input() colors: any;
  sections: any[] = []
  periods: any[] = []
  labelNames: string[] = [];

  @ViewChild(BaseChartDirective) chart: BaseChartDirective;

  datasets: ChartDataset[] =[];
  
  constructor(private injector: Injector) {
    this.formBuilder = this.injector.get(FormBuilder);
  }

  ngOnInit(): void {
    let sections = {};
    let periods: string[] = [];
    if(this.formSubmissions && this.formSubmissions.length > 0) {
      this.formSubmissions[0].sections.forEach((section: DataFieldSectionVO) => {
        sections[section.sectionId] = section.sectionLabel;
      });

      let lics: any = {}

      this.formSubmissions?.forEach(submission => {
        periods.push(submission?.period?.periodName);
      });

      this.periods = [...new Set(periods)]
    }

    Object.keys(sections).forEach(key => {
      this.sections.push({
        sectionId: key,
        sectionLabel: sections[key]
      });
    });

    this.reportChartGroup.addControl("period", this.formBuilder.control([]));
    this.reportChartGroup.addControl("section", this.formBuilder.control([]));
    this.chartTypeControl.patchValue('bar');
    this.periodControl.patchValue('all');

    this.labels?.forEach(label => {
      this.labelNames?.push(label.fieldName)
    });

  }

  newForm(chart: ReportChart): FormGroup {
    
    return this.formBuilder.group({
      chartLabel: [chart?.chartLabel],
      chartType: [chart?.chartType],
      chartCaption: [chart?.chartCaption],
      labels: this.formBuilder.array(chart?.labels),
      data: this.formBuilder.array(chart?.data)
    });
  }

  selectedPeriod() {
    this.datasets = this.barChartDataSets();
  }

  selectedFormSection() {

  }

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

  ngAfterViewInit(): void {
    this.chart.datasets = this.barChartDataSets();
  }

  ngOnDestroy(): void {
  }

  selectedChartType() {
    if(this.chartType === 'bar') {
      this.datasets = this.barChartDataSets();
    }
  }

  clearReport() {
    
  }

  refreshChart() {
    this.datasets = this.barChartDataSets();
  }

  get filteredSubmissions() {
    
    if(this.formSubmissions) {

      if(this.period !== 'all') {
        return this.formSubmissions?.filter(submission => submission?.period?.periodName === this.period);
      } else {
        return this.formSubmissions;
      }
    }

    return [];
  }

  get pieChartDatasets() {
    let datasets: any[] = [];

    if(this.dataLabels === 'licensees') {

      this.filteredSubmissions?.forEach(submission => {
        console.log(submission)
        let fields: DataFieldVO[] = [];
        let fieldValues: number[] = [];
        
        submission?.sections?.forEach((section: DataFieldSectionVO) => {
          fields = [...fields, ...section.dataFields]
        });

        this.labels?.forEach(label => {
          let field = fields?.find(f => f.formField.fieldId === label.fieldId);
          if(field) {
            fieldValues.push(+field.value);
          }
        });

        datasets.push({
          label: submission?.licensee?.licenseeName,
          data: fieldValues
        })
      });

    } else if(this.dataLabels === 'periods') {

    } else if(this.dataLabels === 'fields') {
      console.log('data');
    }

    return datasets;

    return []
  }

  barChartDataSets(): any[] {
    
    let datasets: any[] = [];


    if(this.dataLabels === 'licensees') {

      this.filteredSubmissions?.forEach(submission => {
        
        let fields: DataFieldVO[] = [];
        let fieldValues: number[] = [];
        
        submission?.sections?.forEach((section: DataFieldSectionVO) => {
          fields = [...fields, ...section.dataFields]
        });
        
        this.labels?.forEach(label => {
          let field = fields?.find(f => f.formField.fieldId === label.fieldId);
          if(field) {
            fieldValues.push(+field.value);
          }
        });

        datasets.push({
          label: submission?.licensee?.licenseeName,
          backgroundColor: this.colors[submission?.licensee?.licenseeName],
          data: fieldValues
        })
      });

    } else if(this.dataLabels === 'periods') {

    } else if(this.dataLabels === 'fields') {
      console.log('data');
    }

    return datasets;
  }

  
}
