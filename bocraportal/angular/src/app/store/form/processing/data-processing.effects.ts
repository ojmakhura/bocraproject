// Generated by andromda-angular cartridge (app\usecase\effect.store.ts.vsl) DO NOT EDIT
import { Injectable } from '@angular/core';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import { catchError, map, mergeMap } from 'rxjs/operators';
import * as DataProcessingActions from './data-processing.actions';
import { SubmissionRestController } from '@app/service/bw/org/bocra/portal/form/submission/submission-rest-controller';
import { FormSubmissionCriteria } from '@app/model/bw/org/bocra/portal/form/submission/form-submission-criteria';

@Injectable()
export class DataProcessingEffects {

    constructor(private actions$: Actions, private submissionRestController: SubmissionRestController) {}
    
    dataCaptureSummary$ = createEffect(() => 
         this.actions$.pipe(
            ofType(DataProcessingActions.dataCaptureSummary),
            mergeMap(({}) => this.submissionRestController.getSubmissionSummary(new FormSubmissionCriteria()).pipe(
                map( submissionSummary => DataProcessingActions.dataCaptureSummarySuccess({submissionSummary, messages: [`Submission summary loaded.`], success: true})),
                catchError(({error}) => [DataProcessingActions.dataProcessingFailure({messages: [error?.error ? error?.error : error]})])
            ))
        )
    );

    loadData$ = createEffect(() => 
         this.actions$.pipe(
            ofType(DataProcessingActions.loadData),
            mergeMap(({ criteria }) => this.submissionRestController.search(criteria).pipe(
                map( formSubmissions => DataProcessingActions.loadDataSuccess({
                    formSubmissions,
                    messages: [`${formSubmissions.length} submissions found.`],
                    success: true
                })),
                catchError(({error}) => [DataProcessingActions.dataProcessingFailure({messages: [error.error]})])
            ))
        )
    );
}
