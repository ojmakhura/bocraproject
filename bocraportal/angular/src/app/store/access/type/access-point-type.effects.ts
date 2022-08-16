// Generated by andromda-angular cartridge (app\usecase\effect.store.ts.vsl) DO NOT EDIT
import { Injectable } from '@angular/core';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import { catchError, map, mergeMap } from 'rxjs/operators';
import * as AccessPointTypeActions from './access-point-type.actions';
import { AccessPointTypeRestController } from '@app/service/bw/org/bocra/portal/access/type/access-point-type-rest-controller';

@Injectable()
export class AccessPointTypeEffects {
  constructor(private actions$: Actions, private accessPointTypeRestController: AccessPointTypeRestController) {}

  findById$ = createEffect(() =>
    this.actions$.pipe(
      ofType(AccessPointTypeActions.findById),
      mergeMap(({id}) =>
        this.accessPointTypeRestController.findById(id).pipe(
          map((accessPointType) =>
            AccessPointTypeActions.findByIdSuccess({ accessPointType, messages: [`Access point type ${accessPointType.name} found.`], success: true })
          ),
          catchError((error) => [AccessPointTypeActions.accessPointTypeFailure({ messages: [error.error] })])
        )
      )
    )
  );

  save$ = createEffect(() =>
    this.actions$.pipe(
      ofType(AccessPointTypeActions.save),
      mergeMap(({accessPointType}) =>
        this.accessPointTypeRestController.save(accessPointType).pipe(
          map((accessPointType) =>
            AccessPointTypeActions.saveSuccess({ accessPointType, messages: [`Access point type ${accessPointType.name} saved.`], success: true })
          ),
          catchError((error) => [AccessPointTypeActions.accessPointTypeFailure({ messages: [error.error] })])
        )
      )
    )
  );

  remove$ = createEffect(() =>
    this.actions$.pipe(
      ofType(AccessPointTypeActions.remove),
      mergeMap(({id}) =>
        this.accessPointTypeRestController.remove(id).pipe(
          map((removed) =>
            AccessPointTypeActions.removeSuccess({ removed, messages: [`Access point type successfully removed.`], success: true })
          ),
          catchError((error) => [AccessPointTypeActions.accessPointTypeFailure({ messages: [error.error] })])
        )
      )
    )
  );

  getAll$ = createEffect(() =>
    this.actions$.pipe(
      ofType(AccessPointTypeActions.getAll),
      mergeMap(() =>
        this.accessPointTypeRestController.getAll().pipe(
          map((accessPointTypes) =>
            AccessPointTypeActions.getAllSuccess({ accessPointTypes, messages: [`${accessPointTypes.length} access point types found.`], success: true })
          ),
          catchError((error) => [AccessPointTypeActions.accessPointTypeFailure({ messages: [error.error] })])
        )
      )
    )
  );

  search$ = createEffect(() =>
    this.actions$.pipe(
      ofType(AccessPointTypeActions.search),
      mergeMap(({criteria}) =>
        this.accessPointTypeRestController.search(criteria).pipe(
          map((accessPointTypes) =>
            AccessPointTypeActions.searchSuccess({ accessPointTypes, messages: [`${accessPointTypes.length} access point types found.`], success: true })
          ),
          catchError((error) => [AccessPointTypeActions.accessPointTypeFailure({ messages: [error.error] })])
        )
      )
    )
  );

  getAllPaged$ = createEffect(() =>
    this.actions$.pipe(
      ofType(AccessPointTypeActions.getAllPaged),
      mergeMap(({pageNumber, pageSize}) =>
        this.accessPointTypeRestController.getAllPaged(pageNumber, pageSize).pipe(
          map((accessPointTypes) =>
            AccessPointTypeActions.getAllPagedSuccess({ accessPointTypes, messages: [`Page ${pageNumber} found with ${pageSize} access point types.`], success: true })
          ),
          catchError((error) => [AccessPointTypeActions.accessPointTypeFailure({ messages: [error.error] })])
        )
      )
    )
  );
}
