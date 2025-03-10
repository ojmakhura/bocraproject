// Generated by andromda-jsf cartridge (view\table\table.component.ts.vsl) DO NOT EDIT!
import { Component, Injector, Input, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { ActivatedRoute, Router } from '@angular/router';
import { ReportState } from '@app/store/report/report.state';
import { UseCaseScope } from '@app/utils/use-case-scope';
import { select, Store } from '@ngrx/store';
import { Observable } from 'rxjs';

import { ReportControllerImpl } from '@app/controller/report/report-controller.impl';
import { FormSubmissionVO } from '@app/model/bw/org/bocra/portal/form/submission/form-submission-vo';
import * as FormSubmissionSelectors from '@app/store/form/submission/form-submission.selectors';
import { SearchComponentImpl } from '@app/view/report/search.component.impl';
import { MatTableExporterDirective } from 'mat-table-exporter';

@Component({
  selector: 'search-form-submissions-base',
  template: '',
})
export abstract class SearchFormSubmissionsComponent implements OnInit, OnDestroy {
  formSubmissionsColumns = ['id', 'licensee.licenseeName', 'form.formName', 'period.periodName'];

  formSubmissions$: Observable<Array<FormSubmissionVO>>;

  formSubmissionsDataSource = new MatTableDataSource<FormSubmissionVO>([]);
  @ViewChild('formSubmissionsPaginator', { static: true }) formSubmissionsPaginator: MatPaginator;
  @ViewChild('formSubmissionsSort', { static: true }) formSubmissionsSort: MatSort;
  @ViewChild(MatTableExporterDirective) matTableExporter: MatTableExporterDirective;

  @Input() protected pageVariables: any;
  protected route: ActivatedRoute;
  protected router: Router;
  protected useCaseScope: UseCaseScope;
  protected store: Store<ReportState>;
  protected reportController: ReportControllerImpl;
  protected searchComponent: SearchComponentImpl;
  protected _injector: Injector;

  constructor(injector: Injector) {
    this.route = injector.get(ActivatedRoute);
    this.router = injector.get(Router);
    this.useCaseScope = injector.get(UseCaseScope);
    this.store = injector.get(Store);
    this.reportController = injector.get(ReportControllerImpl);
    this.searchComponent = injector.get(SearchComponentImpl);
    this._injector = injector;
    this.formSubmissions$ = this.store.pipe(select(FormSubmissionSelectors.selectFormSubmissions));
  }

  ngOnInit(): void {}

  ngOnDestroy() {}

  ngAfterViewInit() {
    this.formSubmissions$.subscribe((formSubmissions) => {
      this.formSubmissionsDataSource.data = formSubmissions;
    });
    this.formSubmissionsDataSource.paginator = this.formSubmissionsPaginator;
    this.formSubmissionsDataSource.sort = this.formSubmissionsSort;
  }

  getFormSubmissionsExportData(): any {
    return this.formSubmissionsDataSource?.data?.map((row) => {
      return {
        id: row?.id,
        licenseelicenseeName: row?.licensee?.licenseeName,
        formformName: row?.form?.formName,
        periodperiodName: row?.period?.periodName,
      };
    });
  }
}
