// Generated by andromda-angular cartridge (app\usecase\effect.store.ts.vsl) DO NOT EDIT
import { Injectable } from '@angular/core';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import { catchError, map, mergeMap } from 'rxjs/operators';
import * as FormSubmissionActions from './form-submission.actions';
import { SubmissionRestController } from '@app/service/bw/org/bocra/portal/form/submission/submission-rest-controller';
import { NoteRestController } from '@app/service/bw/org/bocra/portal/form/submission/note/note-rest-controller';

@Injectable()
export class FormSubmissionEffects {
  constructor(
    private actions$: Actions,
    private submissionRestController: SubmissionRestController,
    private noteController: NoteRestController
  ) {}

  findById$ = createEffect(() =>
    this.actions$.pipe(
      ofType(FormSubmissionActions.findById),
      mergeMap(({ id }) =>
        this.submissionRestController.findById(id).pipe(
          map((formSubmission) =>
            FormSubmissionActions.findByIdSuccess({
              formSubmission,
              messages: [`Submission ${formSubmission?.form?.formName} found.`],
              success: true,
            })
          ),
          catchError(({ error }) => [
            FormSubmissionActions.formSubmissionFailure({ messages: [error?.error ? error.error : error] }),
          ])
        )
      )
    )
  );

  findByIds$ = createEffect(() =>
    this.actions$.pipe(
      ofType(FormSubmissionActions.findByIds),
      mergeMap(({ ids }) =>
        this.submissionRestController.findByIds(ids).pipe(
          map((formSubmissions) =>
            FormSubmissionActions.findByIdsSuccess({
              formSubmissions,
              messages: [`${formSubmissions.length} submissions found.`],
              success: true,
            })
          ),
          catchError(({ error }) => [
            FormSubmissionActions.formSubmissionFailure({ messages: [error?.error ? error.error : error] }),
          ])
        )
      )
    )
  );

  save$ = createEffect(() =>
    this.actions$.pipe(
      ofType(FormSubmissionActions.save),
      mergeMap(({ formSubmission }) =>
        this.submissionRestController.save(formSubmission).pipe(
          map((formSubmission) =>
            FormSubmissionActions.saveSuccess({
              formSubmission,
              messages: [`Submission ${formSubmission?.form?.formName} saved.`],
              success: true,
            })
          ),
          catchError(({ error }) => [
            FormSubmissionActions.formSubmissionFailure({ messages: [error?.error ? error.error : error] }),
          ])
        )
      )
    )
  );

  saveNote$ = createEffect(() =>
    this.actions$.pipe(
      ofType(FormSubmissionActions.saveNote),
      mergeMap(({ note }) =>
        this.noteController.save(note).pipe(
          map((note) =>
            FormSubmissionActions.saveNoteSuccess({
              note,
              messages: [`Note for saved.`],
              success: true,
            })
          ),
          catchError(({ error }) => [
            FormSubmissionActions.formSubmissionFailure({ messages: [error?.error ? error.error : error] }),
          ])
        )
      )
    )
  );

  remove$ = createEffect(() =>
    this.actions$.pipe(
      ofType(FormSubmissionActions.remove),
      mergeMap(({ id }) =>
        this.submissionRestController.remove(id).pipe(
          map((removed) =>
            FormSubmissionActions.removeSuccess({
              removed,
              messages: [`Submission ${id} removed.`],
              success: true,
            })
          ),
          catchError(({ error }) => [
            FormSubmissionActions.formSubmissionFailure({ messages: [error?.error ? error.error : error] }),
          ])
        )
      )
    )
  );

  getAll$ = createEffect(() =>
    this.actions$.pipe(
      ofType(FormSubmissionActions.getAll),
      mergeMap(() =>
        this.submissionRestController.getAll().pipe(
          map((formSubmissions) =>
            FormSubmissionActions.getAllSuccess({
              formSubmissions,
              messages: [`${formSubmissions.length} submissions found.`],
              success: true,
            })
          ),
          catchError(({ error }) => [
            FormSubmissionActions.formSubmissionFailure({ messages: [error?.error ? error.error : error] }),
          ])
        )
      )
    )
  );

  search$ = createEffect(() =>
    this.actions$.pipe(
      ofType(FormSubmissionActions.search),
      mergeMap(({ criteria }) =>
        this.submissionRestController.search(criteria).pipe(
          map((formSubmissions) =>
            FormSubmissionActions.searchSuccess({
              formSubmissions,
              messages: [`${formSubmissions.length} submissions found.`],
              success: true,
            })
          ),
          catchError(({ error }) => [
            FormSubmissionActions.formSubmissionFailure({ messages: [error?.error ? error.error : error] }),
          ])
        )
      )
    )
  );

  getAllPaged$ = createEffect(() =>
    this.actions$.pipe(
      ofType(FormSubmissionActions.getAllPaged),
      mergeMap(({ pageNumber, pageSize }) =>
        this.submissionRestController.getAllPaged(pageNumber, pageSize).pipe(
          map((formSubmissions) =>
            FormSubmissionActions.getAllPagedSuccess({
              formSubmissions,
              messages: [`Page ${pageNumber} found with ${formSubmissions.length} submissions.`],
              success: true,
            })
          ),
          catchError(({ error }) => [
            FormSubmissionActions.formSubmissionFailure({ messages: [error?.error ? error.error : error] }),
          ])
        )
      )
    )
  );

  updateSubmissionStatus$ = createEffect(() =>
    this.actions$.pipe(
      ofType(FormSubmissionActions.updateStatus),
      mergeMap(({ id, submissionStatus, updateTime, username }) =>
        this.submissionRestController.updateSubmissionStatus(id, submissionStatus, updateTime, username).pipe(
          map((statusUpdated) =>
            FormSubmissionActions.updateStatusSuccess({
              statusUpdated,
              messages: [`Form submission stateud updated to ${submissionStatus}.`],
              success: true,
            })
          ),
          catchError(({ error }) => [
            FormSubmissionActions.formSubmissionFailure({ messages: [error?.error ? error.error : error] }),
          ])
        )
      )
    )
  );
}
