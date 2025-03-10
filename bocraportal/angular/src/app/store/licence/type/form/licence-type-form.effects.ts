// Generated by andromda-angular cartridge (app\usecase\effect.store.ts.vsl) DO NOT EDIT
import { Injectable } from '@angular/core';
import { LicenceTypeFormRestController } from '@app/service/bw/org/bocra/portal/licence/type/form/licence-type-form-rest-controller';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import { catchError, map, mergeMap } from 'rxjs/operators';
import * as LicenceTypeFormActions from './licence-type-form.actions';

@Injectable()
export class LicenceTypeFormEffects {
  documentRestController: any;

  constructor(private actions$: Actions, private licenceTypeFormRestController: LicenceTypeFormRestController) {}

  findById$ = createEffect(() =>
    this.actions$.pipe(
      ofType(LicenceTypeFormActions.findById),
      mergeMap(({ id }) =>
        this.licenceTypeFormRestController.findById(id).pipe(
          map((licenceTypeForm) =>
            LicenceTypeFormActions.findByIdSuccess({
              licenceTypeForm,
              messages: [],
              success: false,
            })
          ),
          catchError(({ error }) => [
            LicenceTypeFormActions.licenceTypeFormFailure({ messages: [error?.error ? error.error : error] }),
          ])
        )
      )
    )
  );

  findByLicence$ = createEffect(() =>
    this.actions$.pipe(
      ofType(LicenceTypeFormActions.findByLicenceType),
      mergeMap(({ licenceTypeId }) =>
        this.licenceTypeFormRestController.findByLicenceType(licenceTypeId).pipe(
          map((licenceTypeForms) =>
            LicenceTypeFormActions.findByFormSuccess({
              licenceTypeForms,
              messages: [`${licenceTypeForms.length} licence type forms found.`],
              success: false,
            })
          ),
          catchError(({ error }) => [
            LicenceTypeFormActions.licenceTypeFormFailure({ messages: [error?.error ? error.error : error] }),
          ])
        )
      )
    )
  );

  findByForm$ = createEffect(() =>
    this.actions$.pipe(
      ofType(LicenceTypeFormActions.findByForm),
      mergeMap(({ formId }) =>
        this.licenceTypeFormRestController.findByForm(formId).pipe(
          map((licenceTypeForms) =>
            LicenceTypeFormActions.findByFormSuccess({
              licenceTypeForms,
              messages: [`${licenceTypeForms.length} licence type forms found.`],
              success: false,
            })
          ),
          catchError(({ error }) => [
            LicenceTypeFormActions.licenceTypeFormFailure({ messages: [error?.error ? error.error : error] }),
          ])
        )
      )
    )
  );

  create$ = createEffect(() =>
    this.actions$.pipe(
      ofType(LicenceTypeFormActions.create),
      mergeMap(({ licenceTypeId, formId }) =>
        this.licenceTypeFormRestController.create(licenceTypeId, formId).pipe(
          map((licenceTypeForm) =>
            LicenceTypeFormActions.createSuccess({
              licenceTypeForm,
              messages: [`Licence type form created.`],
              success: true,
            })
          ),
          catchError(({ error }) => [
            LicenceTypeFormActions.licenceTypeFormFailure({ messages: [error?.error ? error.error : error] }),
          ])
        )
      )
    )
  );

  updateLicence$ = createEffect(() =>
    this.actions$.pipe(
      ofType(LicenceTypeFormActions.updateLicenceType),
      mergeMap(({ id, licenceTypeId }) =>
        this.licenceTypeFormRestController.updateLicenceType(id, licenceTypeId).pipe(
          map((licenceTypeForm) =>
            LicenceTypeFormActions.updateSuccess({
              licenceTypeForm,
              messages: [`Licence type form created.`],
              success: true,
            })
          ),
          catchError(({ error }) => [
            LicenceTypeFormActions.licenceTypeFormFailure({ messages: [error?.error ? error.error : error] }),
          ])
        )
      )
    )
  );

  updateForm$ = createEffect(() =>
    this.actions$.pipe(
      ofType(LicenceTypeFormActions.updateForm),
      mergeMap(({ id, formId }) =>
        this.licenceTypeFormRestController.updateForm(id, formId).pipe(
          map((licenceTypeForm) =>
            LicenceTypeFormActions.updateSuccess({
              licenceTypeForm,
              messages: [`Licence type form created.`],
              success: true,
            })
          ),
          catchError(({ error }) => [
            LicenceTypeFormActions.licenceTypeFormFailure({ messages: [error?.error ? error.error : error] }),
          ])
        )
      )
    )
  );

  remove$ = createEffect(() =>
    this.actions$.pipe(
      ofType(LicenceTypeFormActions.remove),
      mergeMap(({ id }) =>
        this.licenceTypeFormRestController.remove(id).pipe(
          map((removed) =>
            LicenceTypeFormActions.removeSuccess({
              removed,
              messages: [`Licence type form ${id} removed.`],
              success: true,
            })
          ),
          catchError(({ error }) => [
            LicenceTypeFormActions.licenceTypeFormFailure({ messages: [error?.error ? error.error : error] }),
          ])
        )
      )
    )
  );

  getAll$ = createEffect(() =>
    this.actions$.pipe(
      ofType(LicenceTypeFormActions.getAll),
      mergeMap(() =>
        this.licenceTypeFormRestController.getAll().pipe(
          map((licenceTypeForms) =>
            LicenceTypeFormActions.getAllSuccess({
              licenceTypeForms,
              messages: [`${licenceTypeForms.length} licence type forms found.`],
              success: true,
            })
          ),
          catchError(({ error }) => [
            LicenceTypeFormActions.licenceTypeFormFailure({ messages: [error?.error ? error.error : error] }),
          ])
        )
      )
    )
  );
}
