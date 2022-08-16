// Generated by andromda-angular cartridge (app\usecase\effect.store.ts.vsl) DO NOT EDIT
import { Injectable } from '@angular/core';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import { catchError, map, mergeMap } from 'rxjs/operators';
import * as LicenseeFormActions from './licensee-form.actions';
import { LicenseeFormRestController } from '@app/service/bw/org/bocra/portal/licensee/form/licensee-form-rest-controller';

@Injectable()
export class LicenseeFormEffects {
  documentRestController: any;

  constructor(private actions$: Actions, private licenseeFormRestController: LicenseeFormRestController) {}

  findById$ = createEffect(() =>
    this.actions$.pipe(
      ofType(LicenseeFormActions.findById),
      mergeMap(({ id }) =>
        this.licenseeFormRestController.findById(id).pipe(
          map((licenseeForm) =>
            LicenseeFormActions.findByIdSuccess({
              licenseeForm,
              messages: [],
              success: false,
            })
          ),
          catchError(({ error }) => [
            LicenseeFormActions.licenseeFormFailure({ messages: [error?.error ? error.error : error] }),
          ])
        )
      )
    )
  );

  findByLicensee$ = createEffect(() =>
    this.actions$.pipe(
      ofType(LicenseeFormActions.findByLicensee),
      mergeMap(({ licenseeId }) =>
        this.licenseeFormRestController.findByLicensee(licenseeId).pipe(
          map((licenseeForms) =>
            LicenseeFormActions.findByFormSuccess({
              licenseeForms,
              messages: [`${licenseeForms.length} licensee forms found.`],
              success: false,
            })
          ),
          catchError(({ error }) => [
            LicenseeFormActions.licenseeFormFailure({ messages: [error?.error ? error.error : error] }),
          ])
        )
      )
    )
  );

  findByForm$ = createEffect(() =>
    this.actions$.pipe(
      ofType(LicenseeFormActions.findByForm),
      mergeMap(({ formId }) =>
        this.licenseeFormRestController.findByForm(formId).pipe(
          map((licenseeForms) =>
            LicenseeFormActions.findByFormSuccess({
              licenseeForms,
              messages: [`${licenseeForms.length} licensee forms found.`],
              success: false,
            })
          ),
          catchError(({ error }) => [
            LicenseeFormActions.licenseeFormFailure({ messages: [error?.error ? error.error : error] }),
          ])
        )
      )
    )
  );

  create$ = createEffect(() =>
    this.actions$.pipe(
      ofType(LicenseeFormActions.create),
      mergeMap(({ licenseeId, formId }) =>
        this.licenseeFormRestController.create(licenseeId, formId).pipe(
          map((licenseeForm) =>
            LicenseeFormActions.createSuccess({
              licenseeForm,
              messages: [`Licensee form created.`],
              success: true
            })
          ),
          catchError(({ error }) => [
            LicenseeFormActions.licenseeFormFailure({ messages: [error?.error ? error.error : error] }),
          ])
        )
      )
    )
  );

  updateLicensee$ = createEffect(() =>
    this.actions$.pipe(
      ofType(LicenseeFormActions.updateLicensee),
      mergeMap(({ id, licenseeId }) =>
        this.licenseeFormRestController.updateLicensee(id, licenseeId).pipe(
          map((licenseeForm) =>
            LicenseeFormActions.updateSuccess({
              licenseeForm,
              messages: [`Licensee form created.`],
              success: true,
            })
          ),
          catchError(({ error }) => [
            LicenseeFormActions.licenseeFormFailure({ messages: [error?.error ? error.error : error] }),
          ])
        )
      )
    )
  );

  updateForm$ = createEffect(() =>
    this.actions$.pipe(
      ofType(LicenseeFormActions.updateForm),
      mergeMap(({ id, formId }) =>
        this.licenseeFormRestController.updateForm(id, formId).pipe(
          map((licenseeForm) =>
            LicenseeFormActions.updateSuccess({
              licenseeForm,
              messages: [`Licensee form created.`],
              success: true,
            })
          ),
          catchError(({ error }) => [
            LicenseeFormActions.licenseeFormFailure({ messages: [error?.error ? error.error : error] }),
          ])
        )
      )
    )
  );

  remove$ = createEffect(() =>
    this.actions$.pipe(
      ofType(LicenseeFormActions.remove),
      mergeMap(({ id }) =>
        this.licenseeFormRestController.remove(id).pipe(
          map((removed) =>
            LicenseeFormActions.removeSuccess({
              removed,
              messages: [`Licensee form ${id} removed.`],
              success: true,
            })
          ),
          catchError(({ error }) => [
            LicenseeFormActions.licenseeFormFailure({ messages: [error?.error ? error.error : error] }),
          ])
        )
      )
    )
  );

  getAll$ = createEffect(() =>
    this.actions$.pipe(
      ofType(LicenseeFormActions.getAll),
      mergeMap(() =>
        this.licenseeFormRestController.getAll().pipe(
          map((licenseeForms) =>
            LicenseeFormActions.getAllSuccess({
              licenseeForms,
              messages: [`${licenseeForms.length} licensee forms found.`],
              success: true,
            })
          ),
          catchError(({ error }) => [
            LicenseeFormActions.licenseeFormFailure({ messages: [error?.error ? error.error : error] }),
          ])
        )
      )
    )
  );
}
