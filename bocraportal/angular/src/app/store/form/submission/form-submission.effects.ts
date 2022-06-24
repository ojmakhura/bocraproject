// Generated by andromda-angular cartridge (app\usecase\effect.store.ts.vsl) DO NOT EDIT
import { Injectable } from '@angular/core';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import { catchError, map, mergeMap } from 'rxjs/operators';
import * as FormSubmissionActions from './form-submission.actions';
import { SubmissionRestControllerImpl } from '@app/service/bw/org/bocra/portal/form/submission/submission-rest-controller.impl';

@Injectable()
export class FormSubmissionEffects {

    constructor(private actions$: Actions, private submissionRestController: SubmissionRestControllerImpl) {}

    findById$ = createEffect(() => 
         this.actions$.pipe(
            ofType(FormSubmissionActions.findById),
            mergeMap(({ id }) => this.submissionRestController.findById(id).pipe(
                map( formSubmission => FormSubmissionActions.findByIdSuccess({
                    formSubmission,
                    messages: [`Submission ${formSubmission?.form?.formName} found.`],
                    success: true
                })),
                catchError(({error}) => [FormSubmissionActions.formSubmissionFailure({messages: [error]})])
            ))
        )
    );

    save$ = createEffect(() => 
         this.actions$.pipe(
            ofType(FormSubmissionActions.save),
            mergeMap(({ formSubmission }) => this.submissionRestController.save(formSubmission).pipe(
                map( formSubmission => FormSubmissionActions.saveSuccess({
                    formSubmission,
                    messages: [`Submission ${formSubmission?.form?.formName} saved.`],
                    success: true
                })),
                catchError(({error}) => [FormSubmissionActions.formSubmissionFailure({messages: [error]})])
            ))
        )
    );

    remove$ = createEffect(() => 
         this.actions$.pipe(
            ofType(FormSubmissionActions.remove),
            mergeMap(({ id }) => this.submissionRestController.remove(id).pipe(
                map( removed => FormSubmissionActions.removeSuccess({
                    removed,
                    messages: [`Submission ${id} removed.`],
                    success: true
                })),
                catchError(({error}) => [FormSubmissionActions.formSubmissionFailure({messages: [error]})])
            ))
        )
    );

    getAll$ = createEffect(() => 
         this.actions$.pipe(
            ofType(FormSubmissionActions.getAll),
            mergeMap(() => this.submissionRestController.getAll().pipe(
                map( formSubmissions => FormSubmissionActions.getAllSuccess({
                    formSubmissions,
                    messages: [`${formSubmissions.length} submissions found.`],
                    success: true
                })),
                catchError(({error}) => [FormSubmissionActions.formSubmissionFailure({messages: [error]})])
            ))
        )
    );

    search$ = createEffect(() => 
         this.actions$.pipe(
            ofType(FormSubmissionActions.search),
            mergeMap(({ criteria }) => this.submissionRestController.search(criteria).pipe(
                map( formSubmissions => FormSubmissionActions.searchSuccess({
                    formSubmissions,
                    messages: [`${formSubmissions.length} submissions found.`],
                    success: true
                })),
                catchError(({error}) => [FormSubmissionActions.formSubmissionFailure({messages: [error]})])
            ))
        )
    );

    getAllPaged$ = createEffect(() => 
         this.actions$.pipe(
            ofType(FormSubmissionActions.getAllPaged),
            mergeMap(({ pageNumber, pageSize }) => this.submissionRestController.getAllPaged(pageNumber, pageSize).pipe(
                map( formSubmissions => FormSubmissionActions.getAllPagedSuccess({
                    formSubmissions,
                    messages: [`Page ${pageNumber} found with ${formSubmissions.length} documents.`],
                    success: true
                })),
                catchError(({error}) => [FormSubmissionActions.formSubmissionFailure({messages: [error]})])
            ))
        )
    );

}
