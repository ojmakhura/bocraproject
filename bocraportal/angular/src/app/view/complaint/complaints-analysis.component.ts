// Generated by andromda-angular cartridge (view\view.component.ts.vsl) DO NOT EDIT
import {
  Component,
  OnInit,
  Injector,
  ViewChild,
  Input,
  Output,
  EventEmitter,
  AfterViewInit,
  OnDestroy,
} from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormGroup, FormControl, FormArray, FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { formatDate } from '@angular/common';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatSelectChange } from '@angular/material/select';
import { MatRadioChange } from '@angular/material/radio';
import { MatCheckboxChange } from '@angular/material/checkbox';
import { MatTableDataSource } from '@angular/material/table';
import { UseCaseScope } from '@app/utils/use-case-scope';
import { SelectItem } from '@app/utils/select-item';
import { Store, select } from '@ngrx/store';
import { Observable } from 'rxjs';
import { MatDialog } from '@angular/material/dialog';
import { ComplaintState } from '@app/store/complaint/complaint.state';
import * as ComplaintSelectors from '@app/store/complaint/complaint.selectors';
import * as ComplaintActions from '@app/store/complaint/complaint.actions';

import { ComplaintControllerImpl } from '@app/controller/complaint/complaint-controller.impl';
import { ComplaintRestController } from '@app/service/bw/org/bocra/portal/complaint/complaint-rest-controller';
import { ComplaintVO } from '@app/model/bw/org/bocra/portal/complaint/complaint-vo';
import { ChartConfiguration, ChartData, ChartEvent, ChartType, Color } from 'chart.js';
import { BaseChartDirective } from 'ng2-charts';

import DataLabelsPlugin from 'chartjs-plugin-datalabels';

@Component({
  selector: 'app-complaints-analysis-base',
  template: '',
})
export abstract class ComplaintsAnalysisComponent implements OnInit, AfterViewInit, OnDestroy {
  @ViewChild(BaseChartDirective)
  chart!: BaseChartDirective;

  complaintsAnalysisForm: FormGroup | any;
  reportFilterForm = new FormGroup({
    report: new FormControl(''),
    chartType: new FormControl(''),
    chartLabel: new FormControl(''),
    chartCaption: new FormControl('')
  });
  hide: boolean = false;
  protected route: ActivatedRoute;
  protected router: Router;
  protected formBuilder: FormBuilder;
  protected _injector: Injector;
  protected useCaseScope: UseCaseScope;
  protected store: Store<ComplaintState>;
  dialog: MatDialog;
  complaintController: ComplaintControllerImpl;
  complaintRestController: ComplaintRestController;
  messages: Observable<any>;
  success: Observable<boolean>;
  loading: Observable<boolean>;
  loaderMessage: Observable<string>;
  error: Observable<boolean>;
  selected: any = null;

  reportLabel: string[] = [];
  reportData: number[] = [];

  reportLicenseesLabel: string[] = [];
  reportLicenseesData: number[] = [];

  reportTypeLabel: string[] = [];
  reportTypeData: number[] = [];

  reportStatusLabel: string[] = [];
  reportStatusData: number[] = [];

  reportSectorLabel: string[] = [];
  reportSectorData: number[] = [];

  constructor(injector: Injector) {
    this.route = injector.get(ActivatedRoute);
    this.router = injector.get(Router);
    this.formBuilder = injector.get(FormBuilder);
    this.useCaseScope = injector.get(UseCaseScope);
    this.store = injector.get(Store);
    this.dialog = injector.get(MatDialog);
    this.complaintController = injector.get(ComplaintControllerImpl);
    this.complaintRestController = injector.get(ComplaintRestController);
    this._injector = injector;
    this.loading = this.store.pipe(select(ComplaintSelectors.selectLoading));
    this.loaderMessage = this.store.pipe(select(ComplaintSelectors.selectLoaderMessage));
    this.success = this.store.pipe(select(ComplaintSelectors.selectSuccess));
    this.error = this.store.pipe(select(ComplaintSelectors.selectError));
    this.messages = this.store.pipe(select(ComplaintSelectors.selectMessages));
  }

  beforeOnInit(): void { }

  ngOnInit() {
    this.beforeOnInit();
    this.complaintsAnalysisForm = this.newForm();

    this.complaintsAnalysisForm.valueChanges.subscribe((change: any) => {
      this.handleFormChanges(change);
    });
    this.afterOnInit();
  }

  handleFormChanges(change: any): void { }

  complaintsAnalysisFormReset() {
    this.store.dispatch(ComplaintActions.complaintReset());

    this.complaintsAnalysisForm.reset();
    this.complaintsAnalysisForm.markAsPristine();

    if (this.router.url.substring(0, this.router.url.indexOf('?'))) {
      this.router.navigate([this.router.url.substring(0, this.router.url.indexOf('?'))]);
    } else {
      this.router.navigate([this.router.url]);
    }
  }

  afterOnInit(): void { }

  doNgAfterViewInit(): void { }

  ngAfterViewInit() {
    this.doNgAfterViewInit();
    this.chart?.update();
    this.complaintController.resetUseCaseScope();
  }

  newForm(): FormGroup {
    return this.formBuilder.group({});
  }

  abstract doNgOnDestroy(): void;

  ngOnDestroy() {
    this.doNgOnDestroy();
    this.store.dispatch(ComplaintActions.complaintReset());
  }

  getItemControl(name: string): FormControl {
    return this.complaintsAnalysisForm.get(name) as FormControl;
  }

  getGroupControl(name: string): FormGroup {
    return this.complaintsAnalysisForm.get(name) as FormGroup;
  }

  getArrayControl(name: string): FormArray {
    return this.complaintsAnalysisForm.get(name) as FormArray;
  }

  setComplaintsAnalysisFormValue(form: any) { }

  public barChartOptions: ChartConfiguration['options'] = {
    responsive: true,
    // We use these empty structures as placeholders for dynamic theming.
    scales: {
      x: {},
      y: {
        min: 0,
      },
    },
    plugins: {
      legend: {
        display: true,
      },
      datalabels: {
        anchor: 'center',
        align: 'start',
      },
    },
  };
  public barChartType: ChartType = 'bar';
  public barChartPlugins = [DataLabelsPlugin];

  public barChartData: ChartData<'bar'> = {
    labels: [],
    datasets: [],
  };

  public barChartLicenseeData: ChartData<'bar'> = {
    labels: [],
    datasets: [],
  };

  public barChartTypeData: ChartData<'bar'> = {
    labels: [],
    datasets: [],
  };

  public barChartStatusData: ChartData<'bar'> = {
    labels: [],
    datasets: [],
  };

  public barChartSectorData: ChartData<'bar'> = {
    labels: [],
    datasets: [],
  };

  public pieChartOptions: ChartConfiguration['options'] = {
    responsive: true,
    plugins: {
      legend: {
        display: true,
        position: 'top',
      },
      datalabels: {
        formatter: (value, ctx) => {
          if (ctx.chart.data.labels) {
            return ctx.chart.data.labels[ctx.dataIndex];
          }
        },
      },
    },
  };
  public pieChartData: ChartData<'pie', number[], string | string[]> = {
    labels: [],
    datasets: [
      {
        data: [],
      },
    ],
  };
  public pieChartLicenseeData: ChartData<'pie', number[], string | string[]> = {
    labels: [],
    datasets: [
      {
        data: [],
      },
    ],
  };

  public pieChartTypeData: ChartData<'pie', number[], string | string[]> = {
    labels: [],
    datasets: [
      {
        data: [],
      },
    ],
  };

  public pieChartStatusData: ChartData<'pie', number[], string | string[]> = {
    labels: [],
    datasets: [
      {
        data: [],
      },
    ],
  };

  public pieChartSectorData: ChartData<'pie', number[], string | string[]> = {
    labels: [],
    datasets: [
      {
        data: [],
      },
    ],
  };

  public pieChartType: ChartType = 'pie';
  public pieChartPlugins = [DataLabelsPlugin];

  // events
  public chartClicked({ event, active }: { event?: ChartEvent; active?: {}[] }): void {
    console.log(event, active);
  }

  public chartHovered({ event, active }: { event?: ChartEvent; active?: {}[] }): void {
    console.log(event, active);
  }

  selectedReport(e: any) {
    this.reportFilterForm.patchValue({
      report: e.target.value,
    });
  }

  selectedChartType(e: any) {
    this.reportFilterForm.patchValue({
      chartType: e.target.value,
    });
  }
}
