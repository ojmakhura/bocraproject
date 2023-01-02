// Generated by andromda-angular cartridge (view\view.component.imp.ts.vsl) CAN EDIT!
import {
  AfterViewInit,
  Component, Injector,
  Input,
  OnDestroy,
  OnInit, ViewChild
} from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { DataFieldSectionVO } from '@app/model/bw/org/bocra/portal/form/submission/data/data-field-section-vo';
import { DataFieldVO } from '@app/model/bw/org/bocra/portal/form/submission/data/data-field-vo';
import { FormSubmissionVO } from '@app/model/bw/org/bocra/portal/form/submission/form-submission-vo';
import { ChartDataset } from 'chart.js';
import { i } from 'mathjs';
import { BaseChartDirective } from 'ng2-charts';

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
  @Input() additionalDataColumns: any[];
  @Input() additionalDataRows: any;
  @Input() customDataColumns: any;
  @Input() customDataRows: any;
  @Input() grid: any;

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
    this.datasets = this.basicDatasets();

    console.log(this.grid);
  }

  ngAfterViewInit(): void {
  }

  ngOnDestroy(): void {}

  basicDatasets() {
    let tset = {};

    Object.keys(this.grid)?.forEach(g => {

      let tmp = this.grid[g];
      
      let found = {selected: true}

      if(this.dataRows === 'licensees') {
        found = this.selectedLicensees.find(l => l.licensee === tmp.label);
      } else {
        found = this.selectedFields.find(f => f?.fieldId === tmp.elementId);
      }

      if(!found || !found.selected) {
        return;
      }

      Object.keys(tmp)?.forEach(h => {

        let key = `${tmp[h].period}: ${tmp.label}`;

        if(tmp[h].period) {
          
          if(this.dataColumns === 'licensees') {
            found = this.selectedLicensees.find(l => l.licensee === tmp[h].label);
          } else {
            found = this.selectedFields.find(f => f?.fieldId === tmp[h].elementId);
          }
          
          if(!found || !found.selected) {
            return;
          }

          if(tset[key]?.data === undefined) {
            tset[key] = {
              label: key,
              backgroundColor: this.colors[tmp.elementId],
              data: []
            };
          }

          tset[key].data.push(tmp[h]?.value)
        }
      });
    });

    let dset: any[] = [];

    this.selectedPeriods.forEach(period => {

      if(this.dataRows === 'licensees') {
        
        this.selectedLicensees.forEach(lic => {
          let key = `${period.period}: ${lic.licensee}`;

          if(tset[key]) {
            tset[key].backgroundColor = this.colors[lic.licensee];
            dset.push(tset[key]);
          }
          
        });
      } else {
        this.selectedFields.forEach(field => {
          let key = `${period.period}: ${field.fieldName}`;
          if(tset[key]) {
            dset.push(tset[`${period.period}: ${field.fieldName}`]);
          }
        });
      }

    });

    return dset;
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

    Object.keys(this.additionalDataColumns)?.forEach((key) =>
      this.labelNames.push(this.additionalDataColumns[key].name)
    );

    if (this.labelNames.length === 0 && this.additionalDataRows.length > 0) {
      let keys = Object.keys(this.customDataRows);
      if (keys && keys.length > 0) {
        this.labelNames = Object.keys(this.customDataRows[keys[0]]);
      }
    }
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
    this.datasets = this.basicDatasets();
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
      this.datasets = this.basicDatasets();
    }
  }

  clearReport() {}

  refreshChart() {
    this.setPeriods();
    this.setLabels();
    this.datasets = this.basicDatasets();
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

}
