// Generated by andromda-angular cartridge (app\usecase\effect.store.ts.vsl) DO NOT EDIT
import { Injectable } from '@angular/core';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import { catchError, map, mergeMap } from 'rxjs/operators';
import { KeycloakService } from 'keycloak-angular';
import { AuthorisationRestControllerImpl } from '@app/service/bw/org/bocra/portal/auth/authorisation-rest-controller.impl';
import * as nav from '@app/shell/navigation';

@Injectable()
export class AuthEffects {
  constructor(private actions$: Actions, private keycloakService: KeycloakService, private authorisationRestController: AuthorisationRestControllerImpl) {}

  // isLoggedIn$ = createEffect(() =>
  //   this.actions$.pipe(
  //     ofType(AuthActions.isLoggedIn),
  //     mergeMap(() =>
  //       this.keycloakService.isLoggedIn().then(
  //         map((isLoggedIn) => {

  //           if(isLoggedIn) {
  //             AuthActions.getMenus();
  //             AuthActions.getRolesSuccess({roles: this.keycloakService.getUserRoles()});
  //           }
  //         }),
  //         catchError(({ messages }) => [AuthActions.authFailure(messages)])
  //       )
  //     )
  //   )
  // );

  // logout$ = createEffect(() =>
  //   this.actions$.pipe(
  //     ofType(AuthActions.logout),
  //     mergeMap(() =>
  //       this.keycloakService.logout().then(
  //         map(() => AuthActions.authReset()),
  //         catchError(({ messages }) => [AuthActions.authFailure(messages)])
  //       )
  //     )
  //   )
  // );
}