// Generated by andromda-jsf cartridge (view\table\table.component.impl.ts.vsl)
import { Component, Injector } from '@angular/core';
import { SubmissionRestController } from '@app/service/bw/org/bocra/portal/form/submission/submission-rest-controller';
import { SearchFormSubmissionsFormSubmissionsComponent } from '@app/view/form/submission/search-form-submissions-form-submissions.component';
import { FormSubmissionCriteria } from '@app/model/bw/org/bocra/portal/form/submission/form-submission-criteria';
import * as FormSubmissionActions from '@app/store/form/submission/form-submission.actions';
import * as SubmissionSelectors from '@app/store/form/submission/form-submission.selectors';
import { FormSubmissionVO } from '@app/model/bw/org/bocra/portal/form/submission/form-submission-vo';
import { Observable, map } from 'rxjs';
import { DataPage } from '@app/model/bw/org/bocra/portal/data-page';
import { select } from '@ngrx/store';
import { MatTableDataSource } from '@angular/material/table';

@Component({
  selector: 'search-form-submissions-form-submissions',
  templateUrl: './search-form-submissions-form-submissions.component.html',
  styleUrls: ['./search-form-submissions-form-submissions.component.scss'],
})
export class SearchFormSubmissionsFormSubmissionsComponentImpl extends SearchFormSubmissionsFormSubmissionsComponent {

  criteria: FormSubmissionCriteria = new FormSubmissionCriteria();
  formSubmissionsPage$: Observable<DataPage>;

  constructor(private injector: Injector, private submissionRestController: SubmissionRestController) {
    super(injector);
    this.formSubmissionsPage$ = this.store.pipe(select(SubmissionSelectors.selectFormSubmissionsPage));
  }

  getFormSubmissionsAnalyse() {
    let queryParams = {
      submissions: this.formSubmissionsDataSource.data.map((submission) => submission.id),
    };

    this.router.navigate([`/report/report`], { queryParams: queryParams });
  }

  override ngAfterViewInit() {
    this.formSubmissionsPaginator.page.subscribe((paginator) => {
      this.store.dispatch(
        FormSubmissionActions.pagedSearch({
          pageNumber: paginator.pageIndex + 1,
          pageSize: paginator.pageSize,
          criteria: this.criteria,
          loading: true,
          loaderMessage: 'Searching access points ...',
        })
      );
    });

    this.formSubmissionsPage$
      .pipe(
        map((dataPage) => {
          if (dataPage == null) return [];
          this.totalElements = dataPage['totalElements'];
          return dataPage['elements'] as FormSubmissionVO[];
        })
      )
      .subscribe((pageData) => {
        
        this.formSubmissionsDataSource = new MatTableDataSource(pageData);
      });
  }
}
