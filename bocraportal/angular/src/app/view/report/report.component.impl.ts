// Generated by andromda-angular cartridge (view\view.component.imp.ts.vsl) CAN EDIT!
import { Component, Injector, ViewChild } from '@angular/core';
import { ReportComponent } from '@app/view/report/report.component';
import * as SubmissionActions from '@app/store/form/submission/form-submission.actions';
import * as SubmissionSelectors from '@app/store/form/submission/form-submission.selectors';
import * as ViewActions from '@app/store/view/view.actions';
import * as ViewSelectors from '@app/store/view/view.selectors';
import { Observable } from 'rxjs';
import { FormSubmissionVO } from '@app/model/bw/org/bocra/portal/form/submission/form-submission-vo';
import { select } from '@ngrx/store';
import { FormArray, FormGroup } from '@angular/forms';
import { ChartConfiguration, ChartData, ChartEvent, ChartType } from 'chart.js';
import { BaseChartDirective } from 'ng2-charts';

import DataLabelsPlugin from 'chartjs-plugin-datalabels';
import { DataFieldVO } from '@app/model/bw/org/bocra/portal/form/submission/data/data-field-vo';

export class DataObject {
  label: string = '';
  data: any [] = [];
}

@Component({
  selector: 'app-report',
  templateUrl: './report.component.html',
  styleUrls: ['./report.component.scss'],
})
export class ReportComponentImpl extends ReportComponent {

  submissions$: Observable<FormSubmissionVO[]>;
  submissions: FormSubmissionVO[] = [];
  licensees: string[] = [];
  dataset: DataObject[] = [];
  fields: Set<string> = new Set();
  dfields: string[] = []

  constructor(private injector: Injector) {
    super(injector);
    this.submissions$ = this.store.pipe(select(SubmissionSelectors.selectFormSubmissions));
  }

  doNgOnDestroy(): void {}

  override doNgAfterViewInit() {

    this.route.queryParams.subscribe((queryParams: any) => {
      let ids = queryParams.submissions.map((id: string) => +id);
      this.store.dispatch(
        SubmissionActions.findByIds({
          ids: ids,
          loaderMessage: `Loading ${ids.length} submissions for report generation ....`,
          loading: true,
        })
      );
    });

    this.submissions$.subscribe(submissions => {
      this.submissions = submissions;
      this.licensees = [...new Set(submissions.map(submission => submission.licensee.licenseeName))];
      
      submissions.forEach(submission => {
        let dob: DataObject = new DataObject();
        dob.label = submission?.licensee?.licenseeName;
        
        submission.sections.forEach(section => {
          section.dataFields.forEach((field: DataFieldVO) => {
            this.fields.add(field.formField.fieldName);
            dob.data.push(+field.value + 22);
          })
        })

        this.dataset.push(dob)

        // console.log(submission.sections.map(section => section.dataFields.map((field: DataFieldVO) => field.formField.fieldName)));
        // submission.sections.map(section => section.dataFields.map((field: DataFieldVO) => field.formField.fieldName)).forEach(fname => fields.add(fname))
      })

      console.log(this.dataset);
      this.fields.forEach(f => this.dfields.push(f))
      console.log(this.dfields);
      this.chart?.update();
    });
  }

  override afterOnInit(): void {
  }

  getReportElementControl(): FormGroup {
    return this.formBuilder.group({
      element: []
    });
  }

  addReportElement() {
    this.reportElements.push(
      this.getReportElementControl()
    );
  }

  override newForm(): FormGroup {
      return this.formBuilder.group({
        reportElements: this.formBuilder.array([this.getReportElementControl()])
      });
  }

  get reportElements(): FormArray {
    return this.reportForm.get('reportElements') as FormArray;
  }

  removeReportElement(i: number) {
    this.reportElements.removeAt(i);
  }


  @ViewChild(BaseChartDirective) chart: BaseChartDirective | undefined;

  public barChartOptions: ChartConfiguration['options'] = {
    responsive: true,
    // We use these empty structures as placeholders for dynamic theming.
    scales: {
      x: {},
      y: {
        min: 10
      }
    },
    plugins: {
      legend: {
        display: true,
      },
      datalabels: {
        anchor: 'end',
        align: 'end'
      }
    }
  };
  public barChartType: ChartType = 'bar';
  public barChartPlugins = [
    DataLabelsPlugin
  ];

  public barChartData: ChartData<'bar'> = {
    labels: this.dfields,
    datasets: this.dataset
  };

  // events
  public chartClicked({ event, active }: { event?: ChartEvent, active?: {}[] }): void {
    console.log(event, active);
  }

  public chartHovered({ event, active }: { event?: ChartEvent, active?: {}[] }): void {
    console.log(event, active);
  }

  public randomize(): void {
    // Only Change 3 values
    this.barChartData.datasets = this.dataset

    this.chart?.update();
  }
  
}
