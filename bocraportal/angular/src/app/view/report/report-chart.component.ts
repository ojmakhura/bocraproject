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

  reportChartGroup: FormGroup | any;
  protected formBuilder: FormBuilder;
  @Input() chart: ReportChart;
  @Input() reportType: string;
  
  constructor(private injector: Injector) {
    this.formBuilder = this.injector.get(FormBuilder);
  }

  ngOnInit(): void {
    this.reportChartGroup = this.newForm(this.chart);
  }

  newForm(report: ReportChart): FormGroup {
    
    return this.formBuilder.group({
      chartLabel: [report?.chartLabel],
      chartType: [report?.chartType],
      chartCaption: [report?.chartCaption],
      labels: this.formBuilder.array(report?.labels),
      data: this.formBuilder.array(report?.data)
    });
  }

  get chartTypeControl() {
    return this.reportChartGroup.get('chartType') as FormControl;
  }

  get chartType() {
    return this.chartTypeControl.value;
  }

  ngAfterViewInit(): void {
  }

  ngOnDestroy(): void {
  }

  selectedChartType() {
    
  }

  clearReport() {
    
  }

}
