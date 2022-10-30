// Generated by andromda-jsf cartridge (view\table\table.component.ts.vsl) DO NOT EDIT!
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { 
    Component, 
    OnInit, 
    ViewChild, 
    Injector, 
    Input,
    OnDestroy
} from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { FormArray } from '@angular/forms';
import { ControllerBase } from '@app/controller/utils/controller.base';
import { ActivatedRoute, Router } from '@angular/router';
import { UseCaseScope } from '@app/utils/use-case-scope';
import { Store, select } from '@ngrx/store';
import { Observable } from 'rxjs';
import { ReportState } from '@app/store/report/report.state';
import * as ReportSelectors from '@app/store/report/report.selectors';

import { NoteVO } from '@app/model/bw/org/bocra/portal/form/submission/note/note-vo';
import { FormVO } from '@app/model/bw/org/bocra/portal/form/form-vo';
import { LicenseeVO } from '@app/model/bw/org/bocra/portal/licensee/licensee-vo';
import { DataFieldVO } from '@app/model/bw/org/bocra/portal/form/submission/data/data-field-vo';
import { FormSubmissionStatus } from '@app/model/bw/org/bocra/portal/form/submission/form-submission-status';
import { ReportControllerImpl } from '@app/controller/report/report-controller.impl';
import { DataFieldSectionVO } from '@app/model/bw/org/bocra/portal/form/submission/data/data-field-section-vo';
import { PeriodVO } from '@app/model/bw/org/bocra/portal/period/period-vo';
import { FormSubmissionVO } from '@app/model/bw/org/bocra/portal/form/submission/form-submission-vo';
import { ReportComponentImpl } from '@app/view/report/report.component.impl';

@Component({
  selector: 'report-form-submissions-base',
  template: ''
})
export abstract class ReportFormSubmissionsComponent implements OnInit, OnDestroy {

    formSubmissionsColumns = [
        'id',
        'licensee.licenseeName',
        'form.formName',
        'period.periodName',
    ];

    formSubmissions$: Observable<Array<FormSubmissionVO>>;

    formSubmissionsDataSource = new MatTableDataSource<FormSubmissionVO>([]);
    @ViewChild('formSubmissionsPaginator', {static: true}) formSubmissionsPaginator: MatPaginator;
    @ViewChild('formSubmissionsSort', {static: true}) formSubmissionsSort: MatSort;

    @Input() protected pageVariables: any;
    protected route: ActivatedRoute;
    protected router: Router;
    protected useCaseScope: UseCaseScope;
    protected store: Store<ReportState>;
    protected reportController: ReportControllerImpl;
    protected _injector: Injector;

    constructor(injector: Injector) {
        this.route = injector.get(ActivatedRoute);
        this.router = injector.get(Router);
        this.useCaseScope = injector.get(UseCaseScope);
        this.store = injector.get(Store);
        this.reportController = injector.get(ReportControllerImpl);
        this._injector = injector;
        this.formSubmissions$ = this.store.pipe(select(ReportSelectors.selectFormSubmissions));
    }

    ngOnInit(): void {
    }
	    
    ngOnDestroy() { 
    }
    
    ngAfterViewInit() {
        this.formSubmissions$
        .subscribe(
            formSubmissions => {
                this.formSubmissionsDataSource.data = formSubmissions;
            }
        );
        this.formSubmissionsDataSource.paginator = this.formSubmissionsPaginator;
        this.formSubmissionsDataSource.sort = this.formSubmissionsSort;
    }

    getFormSubmissionsExportData(): any {

        return this.formSubmissionsDataSource?.data?.map(row => {
            return {
                id: row?.id,
                licenseelicenseeName: row?.licensee?.licenseeName,
                formformName: row?.form?.formName,
                periodperiodName: row?.period?.periodName,
            }
        });
    }
}