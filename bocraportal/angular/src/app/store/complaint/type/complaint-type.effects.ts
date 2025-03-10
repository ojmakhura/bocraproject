// Generated by andromda-angular cartridge (app\usecase\effect.store.ts.vsl) DO NOT EDIT
import { Injectable } from '@angular/core';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import { catchError, map, mergeMap } from 'rxjs/operators';
import * as ComplaintTypeActions from './complaint-type.actions';
import { ComplaintTypeRestController } from '@app/service/bw/org/bocra/portal/complaint/type/complaint-type-rest-controller';

@Injectable()
export class ComplaintTypeEffects {
  constructor(private actions$: Actions, private complaintTypeRestController: ComplaintTypeRestController) {}

  findById$ = createEffect(() =>
    this.actions$.pipe(
      ofType(ComplaintTypeActions.findById),
      mergeMap(({ id }) =>
        this.complaintTypeRestController.findById(id).pipe(
          map((complaintType) =>
            ComplaintTypeActions.findByIdSuccess({
              complaintType,
              messages: [`Complaint type ${complaintType.typeName} found.`],
              success: true,
            })
          ),
          catchError(({ error }) => [
            ComplaintTypeActions.complaintTypeFailure({ messages: [error?.error ? error?.error : error] }),
          ])
        )
      )
    )
  );

  save$ = createEffect(() =>
    this.actions$.pipe(
      ofType(ComplaintTypeActions.save),
      mergeMap(({ complaintType }) =>
        this.complaintTypeRestController.save(complaintType).pipe(
          map((complaintType) =>
            ComplaintTypeActions.saveSuccess({
              complaintType,
              messages: [`Complaint type ${complaintType.typeName} saved.`],
              success: true,
            })
          ),
          catchError(({ error }) => [
            ComplaintTypeActions.complaintTypeFailure({ messages: [error?.error ? error?.error : error] }),
          ])
        )
      )
    )
  );

  remove$ = createEffect(() =>
    this.actions$.pipe(
      ofType(ComplaintTypeActions.remove),
      mergeMap(({ id }) =>
        this.complaintTypeRestController.remove(id).pipe(
          map((removed) =>
            ComplaintTypeActions.removeSuccess({
              removed,
              messages: [`Complaint type successfully removed.`],
              success: true,
            })
          ),
          catchError(({ error }) => [
            ComplaintTypeActions.complaintTypeFailure({ messages: [error?.error ? error?.error : error] }),
          ])
        )
      )
    )
  );

  getAll$ = createEffect(() =>
    this.actions$.pipe(
      ofType(ComplaintTypeActions.getAll),
      mergeMap(({}) =>
        this.complaintTypeRestController.getAll().pipe(
          map((complaintTypes) =>
            ComplaintTypeActions.getAllSuccess({
              complaintTypes,
              messages: [`${complaintTypes.length} complaint types found.`],
              success: true,
            })
          ),
          catchError(({ error }) => [
            ComplaintTypeActions.complaintTypeFailure({ messages: [error?.error ? error?.error : error] }),
          ])
        )
      )
    )
  );

  search$ = createEffect(() =>
    this.actions$.pipe(
      ofType(ComplaintTypeActions.search),
      mergeMap(({ criteria }) =>
        this.complaintTypeRestController.search(criteria).pipe(
          map((complaintTypes) =>
            ComplaintTypeActions.searchSuccess({
              complaintTypes,
              messages: [`${complaintTypes.length} complaint types found.`],
              success: true,
            })
          ),
          catchError(({ error }) => [
            ComplaintTypeActions.complaintTypeFailure({ messages: [error?.error ? error?.error : error] }),
          ])
        )
      )
    )
  );

  getAllPaged$ = createEffect(() =>
    this.actions$.pipe(
      ofType(ComplaintTypeActions.getAllPaged),
      mergeMap(({ pageNumber, pageSize }) =>
        this.complaintTypeRestController.getAllPaged(pageNumber, pageSize).pipe(
          map((complaintTypes) =>
            ComplaintTypeActions.getAllPagedSuccess({
              complaintTypes,
              messages: [`Page ${pageNumber} found with ${complaintTypes.length} complaint types.`],
              success: true,
            })
          ),
          catchError(({ error }) => [
            ComplaintTypeActions.complaintTypeFailure({ messages: [error?.error ? error?.error : error] }),
          ])
        )
      )
    )
  );
}
