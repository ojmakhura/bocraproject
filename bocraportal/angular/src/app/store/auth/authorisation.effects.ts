// Generated by andromda-angular cartridge (app\usecase\effect.store.ts.vsl) DO NOT EDIT
import { Injectable } from '@angular/core';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import { catchError, map, mergeMap } from 'rxjs/operators';
import * as AuthorisationActions from './authorisation.actions';
import { AuthorisationRestControllerImpl } from '@app/service/bw/org/bocra/portal/auth/authorisation-rest-controller.impl';

@Injectable()
export class AuthorisationEffects {
  constructor(private actions$: Actions, private authorisationRestController: AuthorisationRestControllerImpl) {}

  findById$ = createEffect(() =>
    this.actions$.pipe(
      ofType(AuthorisationActions.findById),
      mergeMap(({ id }) =>
        this.authorisationRestController.findById(id).pipe(
          map((authorisation) =>
            AuthorisationActions.findByIdSuccess({
              authorisation,
              messages: [`Authorisation for ${authorisation.url} found.`],
              success: false,
            })
          ),
          catchError(({ error }) => [
            AuthorisationActions.authorisationFailure({ messages: [error?.error ? error.error : error] }),
          ])
        )
      )
    )
  );

  save$ = createEffect(() =>
    this.actions$.pipe(
      ofType(AuthorisationActions.save),
      mergeMap(({ authorisation }) =>
        this.authorisationRestController.save(authorisation).pipe(
          map((authorisation) =>
            AuthorisationActions.saveSuccess({
              authorisation,
              messages: [`Authorisation for ${authorisation.url} saved.`],
              success: true,
            })
          ),
          catchError(({ error }) => [
            AuthorisationActions.authorisationFailure({ messages: [error?.error ? error.error : error] }),
          ])
        )
      )
    )
  );

  remove$ = createEffect(() =>
    this.actions$.pipe(
      ofType(AuthorisationActions.remove),
      mergeMap(({ id }) =>
        this.authorisationRestController.remove(id).pipe(
          map((removed) =>
            AuthorisationActions.removeSuccess({
              removed,
              messages: [`Authorisation successfully removed.`],
              success: true,
            })
          ),
          catchError(({ error }) => [
            AuthorisationActions.authorisationFailure({ messages: [error?.error ? error.error : error] }),
          ])
        )
      )
    )
  );

  getAll$ = createEffect(() =>
    this.actions$.pipe(
      ofType(AuthorisationActions.getAll),
      mergeMap(() =>
        this.authorisationRestController.getAll().pipe(
          map((authorisations) =>
            AuthorisationActions.getAllSuccess({
              authorisations,
              messages: [`${authorisations.length} authorisation found.`],
              success: true,
            })
          ),
          catchError(({ error }) => [
            AuthorisationActions.authorisationFailure({ messages: [error?.error ? error.error : error] }),
          ])
        )
      )
    )
  );

  search$ = createEffect(() =>
    this.actions$.pipe(
      ofType(AuthorisationActions.search),
      mergeMap(({ criteria }) =>
        this.authorisationRestController.search(criteria).pipe(
          map((authorisations) =>
            AuthorisationActions.searchSuccess({
              authorisations,
              messages: [`${authorisations.length} authorisation found.`],
              success: true,
            })
          ),
          catchError(({ error }) => [
            AuthorisationActions.authorisationFailure({ messages: [error?.error ? error.error : error] }),
          ])
        )
      )
    )
  );

  getAllPaged$ = createEffect(() =>
    this.actions$.pipe(
      ofType(AuthorisationActions.getAllPaged),
      mergeMap(({ pageNumber, pageSize }) =>
        this.authorisationRestController.getAllPaged(pageNumber, pageSize).pipe(
          map((authorisations) =>
            AuthorisationActions.getAllPagedSuccess({
              authorisations,
              messages: [`Page ${pageNumber} found with ${pageSize} authorisations.`],
              success: true,
            })
          ),
          catchError(({ error }) => [
            AuthorisationActions.authorisationFailure({ messages: [error?.error ? error.error : error] }),
          ])
        )
      )
    )
  );
}
