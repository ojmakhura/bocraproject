// Generated by andromda-angular cartridge (view\view.component.imp.ts.vsl) CAN EDIT!
import { Component, Injector } from '@angular/core';
import { FormSubmissionCriteria } from '@app/model/bw/org/bocra/portal/form/submission/form-submission-criteria';
import { FormSubmissionStatus } from '@app/model/bw/org/bocra/portal/form/submission/form-submission-status';
import { SubmissionSummary } from '@app/model/bw/org/bocra/portal/form/submission/submission-summary';
import * as DataProcessingActions from '@app/store/form/processing/data-processing.actions';
import * as DataProcessingSelectors from '@app/store/form/processing/data-processing.selectors';
import { DataCaptureProcessingAllSubmissionsForm, DataCaptureProcessingComponent, DataCaptureProcessingDraftsForm, DataCaptureProcessingMySubmissionsForm, DataCaptureProcessingNewSubmissionForm, DataCaptureProcessingOverdueSubmissionsForm, DataCaptureProcessingReturnedSubmissionsForm } from '@app/view/form/processing/data-capture-processing.component';
import { select } from '@ngrx/store';
import { KeycloakService } from 'keycloak-angular';
import { Observable } from 'rxjs';
import { FormModule } from '../form.module';

@Component({
  selector: 'app-data-capture-processing',
  templateUrl: './data-capture-processing.component.html',
  styleUrls: ['./data-capture-processing.component.scss']
})
export class DataCaptureProcessingComponentImpl extends DataCaptureProcessingComponent {

  submissionSummary$: Observable<SubmissionSummary>;

  summary!: SubmissionSummary;

  constructor(private injector: Injector,
    private keycloakService: KeycloakService) {
    super(injector);
  }

  override beforeOnInit(): void {
  }

  override doNgAfterViewInit(): void {
    this.store.dispatch(
      DataProcessingActions.dataCaptureSummary({ loading: true })
    );

    this.submissionSummary$ = this.store.pipe(select(DataProcessingSelectors.selectSubmissionSummary));

    this.submissionSummary$.subscribe(data => {
      this.summary = data;
    });

  }

  doNgOnDestroy(): void {
  }

  override beforeDataCaptureProcessingNewSubmission(form: DataCaptureProcessingNewSubmissionForm): void {
    this.store.dispatch(
      DataProcessingActions.loadDataSuccess({
        formSubmissions: [],
        messages: [],
        success: true
      })
    );

    let criteria: FormSubmissionCriteria = new FormSubmissionCriteria();
    criteria.submissionStatus = FormSubmissionStatus.NEW;

    this.store.dispatch(DataProcessingActions.loadData({
      criteria: criteria,
      loading: true
    }));
  }

  override beforeDataCaptureProcessingDrafts(form: DataCaptureProcessingDraftsForm): void {
    
    this.store.dispatch(
      DataProcessingActions.loadDataSuccess({
        formSubmissions: [],
        messages: [],
        success: true
      })
    );
    let criteria: FormSubmissionCriteria = new FormSubmissionCriteria();
    criteria.submissionStatus = FormSubmissionStatus.DRAFT;

    this.store.dispatch(DataProcessingActions.loadData({
      criteria: criteria,
      loading: true
    }));
  }

  override beforeDataCaptureProcessingMySubmissions(form: DataCaptureProcessingMySubmissionsForm): void {
    this.store.dispatch(
      DataProcessingActions.loadDataSuccess({
        formSubmissions: [],
        messages: [],
        success: true
      })
    );

    let criteria: FormSubmissionCriteria = new FormSubmissionCriteria();
    criteria.submissionStatus = FormSubmissionStatus.SUBMITTED
    criteria.submittedBy = this.keycloakService.getUsername();
  
    this.store.dispatch(DataProcessingActions.loadData({
      criteria: criteria,
      loading: true
    }));
  }

  override beforeDataCaptureProcessingAllSubmissions(form: DataCaptureProcessingAllSubmissionsForm): void {
    this.store.dispatch(
      DataProcessingActions.loadDataSuccess({
        formSubmissions: [],
        messages: [],
        success: true
      })
    );
    
    let criteria: FormSubmissionCriteria = new FormSubmissionCriteria();
    // criteria.submissionStatus = FormSubmissionStatus.SUBMITTED

    this.store.dispatch(DataProcessingActions.loadData({
      criteria: criteria,
      loading: true
    }));
  }

  override beforeDataCaptureProcessingOverdueSubmissions(form: DataCaptureProcessingOverdueSubmissionsForm): void {
    this.store.dispatch(
      DataProcessingActions.loadDataSuccess({
        formSubmissions: [],
        messages: [],
        success: true
      })
    );

    let criteria: FormSubmissionCriteria = new FormSubmissionCriteria();
    // criteria.submissionStatus = FormSubmissionStatus.

    this.store.dispatch(DataProcessingActions.loadData({
      criteria: criteria,
      loading: true
    }));
  }

  override beforeDataCaptureProcessingReturnedSubmissions(form: DataCaptureProcessingReturnedSubmissionsForm): void {
    this.store.dispatch(
      DataProcessingActions.loadDataSuccess({
        formSubmissions: [],
        messages: [],
        success: true
      })
    );
    
    let criteria: FormSubmissionCriteria = new FormSubmissionCriteria();
    criteria.submissionStatus = FormSubmissionStatus.RETURNED

    this.store.dispatch(DataProcessingActions.loadData({
      criteria: criteria,
      loading: true
    }));

  }
}