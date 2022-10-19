// Generated by andromda-angular cartridge (view\view.component.imp.ts.vsl) CAN EDIT!
import { Component, Injector } from '@angular/core';
import { ReportComponent } from '@app/view/report/report.component';
import * as SubmissionActions from '@app/store/form/submission/form-submission.actions';
import * as SubmissionSelectors from '@app/store/form/submission/form-submission.selectors';
import * as ViewActions from '@app/store/view/view.actions';
import * as ViewSelectors from '@app/store/view/view.selectors';
import { Observable } from 'rxjs';
import { FormSubmissionVO } from '@app/model/bw/org/bocra/portal/form/submission/form-submission-vo';
import { select } from '@ngrx/store';
import { AbstractControl, FormArray, FormControl, FormGroup } from '@angular/forms';
import { ChartData } from 'chart.js';
import { FormVO } from '@app/model/bw/org/bocra/portal/form/form-vo';
import { FormSectionVO } from '@app/model/bw/org/bocra/portal/form/section/form-section-vo';
import { DataFieldSectionVO } from '@app/model/bw/org/bocra/portal/form/submission/data/data-field-section-vo';
import { DataFieldVO } from '@app/model/bw/org/bocra/portal/form/submission/data/data-field-vo';

export class ReportElement {
  chartType = '';
  datasets: ChartData[] = [];
}

export class FormReport {
  formName: string = '';
  formCode: string = '';
  submissions: FormSubmissionVO[] = [];
  licensees: string[] = [];
  reportElements: ReportElement[] = [];
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
  forms: FormVO[] = [];
  fullReport: FormReport[] = [];

  constructor(private injector: Injector) {
    super(injector);
    this.submissions$ = this.store.pipe(select(SubmissionSelectors.selectFormSubmissions));
  }

  doNgOnDestroy(): void {}

  override doNgAfterViewInit() {
    this.route.queryParams.subscribe((queryParams: any) => {
      let ids = queryParams?.submissions?.map((id: string) => +id);
      this.store.dispatch(
        SubmissionActions.findByIds({
          ids: ids,
          loaderMessage: `Loading ${ids?.length} submissions for report generation ....`,
          loading: true,
        })
      );
    });

    this.submissions$.subscribe((submissions) => {
      this.submissions = submissions;
      this.licensees = [...new Set(submissions.map((submission) => submission.licensee.licenseeName))];

      submissions
        .map((submission) => submission.form)
        .forEach((form) => {
          let fs: FormVO[] = this.forms.filter((f) => f.code === form.code);
          if (!fs || fs.length === 0) {
            this.forms.push(form);
          }
        });

      this.forms.forEach((form) => {
        let rep: FormReport = new FormReport();

        rep.submissions = submissions.filter((submission) => submission.form.formName === form.formName);
        rep.formName = form.formName;
        rep.formCode = form.code;
        rep.licensees = [...new Set(rep.submissions.map((submission) => submission.licensee.licenseeName))];
        this.fullReport.push(rep);
        this.formReports.push(this.createFormReportGroup(rep));
      });
    });
  }

  override afterOnInit(): void {}

  get formReports(): FormArray {
    return this.reportForm.get('formReports') as FormArray;
  }

  createSubmissionsControl(submission: FormSubmissionVO): FormGroup {
    return this.formBuilder.group({
      id: submission.id,
    });
  }

  createSubmissionsArrayControl(submissions: FormSubmissionVO[]) {
    let formArray: FormArray = this.formBuilder.array([]);
    submissions.forEach((sub) => {
      formArray.push(new FormControl(sub.id));
    });

    return formArray;
  }

  createFormReportGroup(formReport: FormReport): FormGroup {
    return this.formBuilder.group({
      formName: [{ value: formReport?.formName, disabled: false }],
      formCode: [{ value: formReport?.formCode, disabled: false }],
      submissions: this.formBuilder.array(formReport?.submissions?.map((sub) => sub.id)),
      reportElements: this.formBuilder.array([]),
    });
  }

  override newForm(): FormGroup {
    return this.formBuilder.group({
      formReports: this.formBuilder.array([]),
    });
  }

  getReportElementsControl(formIndex: number): FormArray {
    return this.formReports?.at(formIndex)?.get('reportElements') as FormArray;
  }

  getReportElements(formIndex: number) {
    return this.getReportElementsControl(formIndex)?.value;
  }

  getReportElementControl(formIndex: number, i: number) {
    return this.getReportElementsControl(formIndex)?.at(i);
  }

  getReportElement(formIndex: number, i: number) {
    return this.getReportElementsControl(formIndex)?.at(i)?.value;
  }

  removeReportElement(formIndex: number, i: number) {
    this.getReportElementsControl(formIndex).removeAt(i);
  }

  addReportElement(formIndex: number) {
    let submissions: FormSubmissionVO[] = this.submissions.filter((sub) =>
      this.getFormReportSubmissions(formIndex).find((val) => val === sub.id)
    );
    let submission: FormSubmissionVO = submissions[0];
    let sectionDatasets: any = {};

    submission?.sections.forEach((section: DataFieldSectionVO) => {
      let sectionDataset = sectionDatasets[section.sectionId];
      if(!sectionDataset) {
        sectionDataset = []

        sectionDatasets[section.sectionId] = sectionDataset;
      }

      submissions.forEach(sub => {
        let sec: DataFieldSectionVO = sub.sections.find((sc: DataFieldSectionVO) => section.sectionLabel === sc.sectionLabel);
        sectionDataset.push({
          label: sub.licensee.licenseeName,
          data: sec.dataFields.map(field => +field.value)
        });
      });

    })

    this.getReportElementsControl(formIndex).push(this.createReportElementControl(submission?.sections, sectionDatasets));
  }

  createSectionGraphsControls(sections: DataFieldSectionVO[], sectionDatasets: any[]): FormArray {
    let arr: FormArray = this.formBuilder.array([]);
    Object.keys(sectionDatasets).forEach(prop => {
      const dt: any[] = sectionDatasets[prop]
      let section: DataFieldSectionVO | undefined = sections.find(v => v.sectionId === prop);
      if(section) {
        arr.push(
          this.formBuilder.group({
            section: [section?.sectionLabel],
            graphData: this.formBuilder.group({
              labels: this.formBuilder.array(section.dataFields.map((field: DataFieldVO) => field.formField.fieldName)),
              datasets: this.createSectionDatasetsControls(dt),
            }),
          })
        );
      }
    });
    return arr;
  }

  createSectionDatasetsControls(datasets: any[]) {
    let datasetsControls: FormArray = this.formBuilder.array([]);
    datasets.forEach(dset => {
      datasetsControls.push(
        this.formBuilder.group({
          label: [dset.label],
          data: this.formBuilder.array(dset.data)
        })
      )
    });

    return datasetsControls;
  }

  getSectionGraphsControls(i: number, j: number) {
    return this.getReportElementControl(i, j).get('sectionGraphs') as FormArray;
  }

  getSectionGraphs(i: number, j: number) {
    return this.getSectionGraphsControls(i, j)?.value;
  }

  getSectionGraphControl(i: number, j: number, k: number) {
    return this.getSectionGraphsControls(i, j).at(k) as FormGroup;
  }

  getSectionGraph(i: number, j: number, k: number) {
    return this.getSectionGraphControl(i, j, k)?.value;
  }

  getSectionGraphSection(i: number, j: number, k: number) {
    return this.getSectionGraph(i, j, k)?.section;
  }

  getSectionGraphGraphData(i: number, j: number, k: number) {
    return this.getSectionGraph(i, j, k)?.graphData;
  }

  createReportElementControl(sections: DataFieldSectionVO[], sectionDatasets: any[]): FormGroup {
    return this.formBuilder.group({
      chartType: ['bar'],
      sectionGraphs: this.createSectionGraphsControls(sections, sectionDatasets),
    });
  }

  getFormReportControl(i: number) {
    return this.formReports.at(i);
  }

  getFormReport(i: number): FormReport {
    return this.formReports.at(i)?.value;
  }

  getFormReportFormNameControl(i: number) {
    return this.formReports.at(i)?.get('formName');
  }

  getFormReportFormName(i: number): string {
    return this.formReports.at(i)?.value?.formName;
  }

  getFormReportFormCodeControl(i: number) {
    return this.formReports.at(i)?.get('formCode');
  }

  getFormReportFormCode(i: number): string {
    return this.formReports.at(i)?.value?.formCode;
  }

  getFormReportSubmissionsControl(i: number) {
    return this.formReports.at(i)?.get('submissions');
  }

  getFormReportSubmissions(i: number): number[] {
    return this.formReports.at(i)?.value?.submissions;
  }

  getChartType(i: number, j: number) {
    return this.getReportElement(i, j)?.chartType;
  }

  getGraphDataControl(i: number, j: number) {
    return this.getReportElementControl(i, j)?.get('graphData');
  }

  getGraphData(i: number, j: number) {
    return this.getReportElement(i, j)?.graphData;
  }

  selectedChartType(i: number, j: number) {
    return this.getReportElement(i, j).chartType ? this.getReportElement(i, j).chartType : 'bar';
  }

  selectSectionDataset(i: number, j: number, k: number) {
    return this.getReportElement(i, j).sectionGraphs[k].graphData;
  }

  selectSectionDatasetLabels(i: number, j: number, k: number) {
    return this.selectSectionDataset(i, j, k).labels;
  }

  selectSectionPieDataset(i: number, j: number, k: number, l: number) {
    return [{data: this.selectSectionDataset(i, j, k).datasets?.map((dt: any) => dt?.data[l])}];
  }

  selectSectionPieDatasetLabels(i: number, j: number, k: number, l: number) {
    return this.selectSectionDataset(i, j, k).datasets?.map((dt: any) => dt?.label);
  }
}
