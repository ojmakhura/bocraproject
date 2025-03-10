// Generated by andromda-angular cartridge (app\usecase\effect.store.ts.vsl) DO NOT EDIT
import { Injectable } from '@angular/core';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import { catchError, map, mergeMap } from 'rxjs/operators';
import * as FormActivationActions from './form-activation.actions';
import { FormActivationRestController } from '@app/service/bw/org/bocra/portal/form/activation/form-activation-rest-controller';

@Injectable()
export class FormActivationEffects {
  constructor(private actions$: Actions, private formActivationRestController: FormActivationRestController) {}

  recreateActivationSubmissions$ = createEffect(() =>
    this.actions$.pipe(
      ofType(FormActivationActions.recreateActivationSubmissions),
      mergeMap(({ id, includeInactive }) =>
        this.formActivationRestController.recreateActivationSubmission(id, includeInactive).pipe(
          map((formSubmissions) =>
            FormActivationActions.recreateActivationSubmissionsSuccess({
              formSubmissions,
              messages: [`Recreated ${formSubmissions.length} submissions.`],
              success: true,
            })
          ),
          catchError(({ error }) => [
            FormActivationActions.formActivationFailure({ messages: [error?.error ? error?.error : error] }),
          ])
        )
      )
    )
  );

  createMissingSubmissions$ = createEffect(() =>
    this.actions$.pipe(
      ofType(FormActivationActions.createMissingSubmissions),
      mergeMap(({ id, includeInactive }) =>
        this.formActivationRestController.createMissingSubmissions(id, includeInactive).pipe(
          map((formSubmissions) =>
            FormActivationActions.createMissingSubmissionsSuccess({
              formSubmissions,
              messages: [`Recreated ${formSubmissions.length} submissions.`],
              success: true,
            })
          ),
          catchError(({ error }) => [
            FormActivationActions.formActivationFailure({ messages: [error?.error ? error?.error : error] }),
          ])
        )
      )
    )
  );

  findById$ = createEffect(() =>
    this.actions$.pipe(
      ofType(FormActivationActions.findById),
      mergeMap(({ id }) =>
        this.formActivationRestController.findById(id).pipe(
          map((formActivation) =>
            FormActivationActions.findByIdSuccess({
              formActivation,
              messages: [`Form activation ${formActivation.activationName} found.`],
              success: true,
            })
          ),
          catchError(({ error }) => [
            FormActivationActions.formActivationFailure({ messages: [error?.error ? error?.error : error] }),
          ])
        )
      )
    )
  );

  save$ = createEffect(() =>
    this.actions$.pipe(
      ofType(FormActivationActions.save),
      mergeMap(({ formActivation, includeInactive }) =>
        this.formActivationRestController.save(formActivation, includeInactive).pipe(
          map((formActivation) =>
            FormActivationActions.saveSuccess({
              formActivation,
              messages: [`Form activation ${formActivation.activationName} saved.`],
              success: true,
            })
          ),
          catchError(({ error }) => [
            FormActivationActions.formActivationFailure({ messages: [error?.error ? error?.error : error] }),
          ])
        )
      )
    )
  );

  remove$ = createEffect(() =>
    this.actions$.pipe(
      ofType(FormActivationActions.remove),
      mergeMap(({ id }) =>
        this.formActivationRestController.remove(id).pipe(
          map((removed) =>
            FormActivationActions.removeSuccess({
              removed,
              messages: [`Form activation ${id} removed.`],
              success: true,
            })
          ),
          catchError(({ error }) => [
            FormActivationActions.formActivationFailure({ messages: [error?.error ? error?.error : error] }),
          ])
        )
      )
    )
  );

  getAll$ = createEffect(() =>
    this.actions$.pipe(
      ofType(FormActivationActions.getAll),
      mergeMap(({}) =>
        this.formActivationRestController.getAll().pipe(
          map((formActivations) =>
            FormActivationActions.getAllSuccess({
              formActivations,
              messages: [`${formActivations.length} form activations found.`],
              success: true,
            })
          ),
          catchError(({ error }) => [
            FormActivationActions.formActivationFailure({ messages: [error?.error ? error?.error : error] }),
          ])
        )
      )
    )
  );

  activateFor$ = createEffect(() =>
    this.actions$.pipe(
      ofType(FormActivationActions.activateFor),
      mergeMap(({ activationDate, periodConfigId, sendEmail, includeInactive }) =>
        this.formActivationRestController.activateDueFormsForDate(activationDate, periodConfigId, sendEmail, includeInactive).pipe(
          map((formActivations) =>
            FormActivationActions.activateForSuccess({
              formActivations,
              messages: [`${formActivations.length} form activations found.`],
              success: true,
            })
          ),
          catchError(({ error }) => [
            FormActivationActions.formActivationFailure({ messages: [error?.error ? error?.error : error] }),
          ])
        )
      )
    )
  );

  search$ = createEffect(() =>
    this.actions$.pipe(
      ofType(FormActivationActions.search),
      mergeMap(({ criteria }) =>
        this.formActivationRestController.search(criteria).pipe(
          map((formActivations) =>
            FormActivationActions.searchSuccess({
              formActivations,
              messages: [`${formActivations.length} form activations found.`],
              success: true,
            })
          ),
          catchError(({ error }) => [
            FormActivationActions.formActivationFailure({ messages: [error?.error ? error?.error : error] }),
          ])
        )
      )
    )
  );

  pagedSearch$ = createEffect(() =>
    this.actions$.pipe(
      ofType(FormActivationActions.pagedSearch),
      mergeMap(({pageNumber, pageSize, criteria}) =>
        this.formActivationRestController.pagedSearch(pageNumber, pageSize, criteria).pipe(
          map((formActivationsPage) => {
            
            return FormActivationActions.pagedSearchSuccess({ 
              formActivationsPage, 
                messages: [`Page ${pageNumber} found with ${formActivationsPage.elements.length} form activations.`], 
                success: true })
          }),
          catchError((error) => [FormActivationActions.formActivationFailure({ messages: [error.error] })])
        )
      )
    )
  );

  getAllPaged$ = createEffect(() =>
    this.actions$.pipe(
      ofType(FormActivationActions.getAllPaged),
      mergeMap(({ pageNumber, pageSize }) =>
        this.formActivationRestController.getAllPaged(pageNumber, pageSize).pipe(
          map((formActivations) =>
            FormActivationActions.getAllPagedSuccess({
              formActivations,
              messages: [`Page ${pageNumber} found with ${formActivations.length} form activations.`],
              success: true,
            })
          ),
          catchError(({ error }) => [
            FormActivationActions.formActivationFailure({ messages: [error?.error ? error?.error : error] }),
          ])
        )
      )
    )
  );
}
