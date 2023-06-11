// Generated by andromda-angular cartridge (app\usecase\effect.store.ts.vsl) DO NOT EDIT
import { Injectable } from '@angular/core';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import { catchError, map, mergeMap } from 'rxjs/operators';
import * as AccessPointActions from './access-point.actions';
import { AccessPointRestController } from '@app/service/bw/org/bocra/portal/access/access-point-rest-controller';

@Injectable()
export class AccessPointEffects {
  constructor(private actions$: Actions, private accessPointRestController: AccessPointRestController) {}

  findById$ = createEffect(() =>
    this.actions$.pipe(
      ofType(AccessPointActions.findById),
      mergeMap(({ id }) =>
        this.accessPointRestController.findById(id).pipe(
          map((accessPoint) =>
            AccessPointActions.findByIdSuccess({
              accessPoint,
              messages: [`Access point ${accessPoint.name} found.`],
              success: true,
            })
          ),
          catchError((error) => [
            AccessPointActions.accessPointFailure({ messages: [error?.error ? error.error : error] }),
          ])
        )
      )
    )
  );

  save$ = createEffect(() =>
    this.actions$.pipe(
      ofType(AccessPointActions.save),
      mergeMap(({ accessPoint }) =>
        this.accessPointRestController.save(accessPoint).pipe(
          map((accessPoint) =>
            AccessPointActions.saveSuccess({
              accessPoint,
              messages: [`Access point ${accessPoint.name} saved.`],
              success: true,
            })
          ),
          catchError((error) => [
            AccessPointActions.accessPointFailure({ messages: [error?.error ? error.error : error] }),
          ])
        )
      )
    )
  );

  remove$ = createEffect(() =>
    this.actions$.pipe(
      ofType(AccessPointActions.remove),
      mergeMap(({ id }) =>
        this.accessPointRestController.remove(id).pipe(
          map((removed) =>
            AccessPointActions.removeSuccess({
              removed,
              messages: [`Access point successfully removed.`],
              success: true,
            })
          ),
          catchError((error) => [
            AccessPointActions.accessPointFailure({ messages: [error?.error ? error.error : error] }),
          ])
        )
      )
    )
  );

  getAll$ = createEffect(() =>
    this.actions$.pipe(
      ofType(AccessPointActions.getAll),
      mergeMap(() =>
        this.accessPointRestController.getAll().pipe(
          map((accessPoints) =>
            AccessPointActions.getAllSuccess({
              accessPoints,
              messages: [`${accessPoints.length} access point types found.`],
              success: true,
            })
          ),
          catchError((error) => [
            AccessPointActions.accessPointFailure({ messages: [error?.error ? error.error : error] }),
          ])
        )
      )
    )
  );

  search$ = createEffect(() =>
    this.actions$.pipe(
      ofType(AccessPointActions.search),
      mergeMap(({ criteria }) =>
        this.accessPointRestController.search(criteria).pipe(
          map((accessPoints) =>
            AccessPointActions.searchSuccess({
              accessPoints,
              messages: [`${accessPoints.length} access point types found.`],
              success: true,
            })
          ),
          catchError((error) => [
            AccessPointActions.accessPointFailure({ messages: [error?.error ? error.error : error] }),
          ])
        )
      )
    )
  );

  getAllPaged$ = createEffect(() =>
    this.actions$.pipe(
      ofType(AccessPointActions.getAllPaged),
      mergeMap(({ pageNumber, pageSize }) =>
        this.accessPointRestController.getAllPaged(pageNumber, pageSize).pipe(
          map((accessPoints) =>
            AccessPointActions.getAllPagedSuccess({
              accessPoints,
              messages: [`Page ${pageNumber} found with ${pageSize} access points.`],
              success: true,
            })
          ),
          catchError((error) => [
            AccessPointActions.accessPointFailure({ messages: [error?.error ? error.error : error] }),
          ])
        )
      )
    )
  );

  pagedSearch$ = createEffect(() =>
    this.actions$.pipe(
      ofType(AccessPointActions.pagedSearch),
      mergeMap(({pageNumber, pageSize, criteria}) =>
        this.accessPointRestController.pagedSearch(pageNumber, pageSize, criteria).pipe(
          map((accessPointPage) => {
            console.log(accessPointPage)
            return AccessPointActions.pagedSearchSuccess({ accessPointPage, messages: [`Action successful.`], success: true })
          }),
          catchError((error) => [AccessPointActions.accessPointFailure({ messages: [error.error] })])
        )
      )
    )
  );
}
