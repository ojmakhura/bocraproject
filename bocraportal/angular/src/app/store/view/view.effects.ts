// Generated by andromda-angular cartridge (app\usecase\effect.store.ts.vsl) DO NOT EDIT
import { Injectable } from '@angular/core';
import { AuthorisationVO } from '@app/model/bw/org/bocra/portal/auth/authorisation-vo';
import { AuthorisationRestController } from '@app/service/bw/org/bocra/portal/auth/authorisation-rest-controller';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import { catchError, map, mergeMap } from 'rxjs/operators';
import * as ViewActions from './view.actions';

@Injectable()
export class ViewEffects {
  constructor(private actions$: Actions, private authorisationRestController: AuthorisationRestController) { }


  loadViewAuthorisations$ = createEffect(() =>
    this.actions$.pipe(
      ofType(ViewActions.loadViewAuthorisations),
      mergeMap(({ viewUrl, roles }) => {
        return this.authorisationRestController.findRestrictedViewItems(viewUrl, roles).pipe(
          map((urls) =>{
            return ViewActions.loadViewAuthorisationsSuccess({ unauthorisedUrls: urls, success: true })
          }),
          catchError((error) => [ViewActions.viewFailure({ messages: [error.error] })])
        )
        })
    )
  );
}
