// Generated by andromda-angular cartridge (app\usecase\effect.store.ts.vsl) DO NOT EDIT
import { Injectable } from '@angular/core';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import { catchError, map, mergeMap } from 'rxjs/operators';
import * as UserActions from './user.action';
import { UserRestControllerImpl } from '@app/service/bw/org/bocra/portal/user/user-rest-controller.impl';

@Injectable()
export class UserEffects {

    constructor(private actions$: Actions, private userRestController: UserRestControllerImpl) {}

    // createUser$ = createEffect(() => 
    //      this.actions$.pipe(
    //         ofType(UserActions.createUser),
    //         mergeMap(({ userVO }) => this.userRestController.createUser(userVO).pipe(
    //             map( results => UserActions.createUserSuccess({results})),
    //             catchError(({error}) => [UserActions.userFailure(error)])
    //         ))
    //     )
    // );

    // updateUserName$ = createEffect(() => 
    //      this.actions$.pipe(
    //         ofType(UserActions.updateUserName),
    //         mergeMap((username, userId) => this.userRestController.updateUserName(username, userId).pipe(
    //             map( updated => UserActions.updateUserNameSuccess({updated})),
    //             catchError(({error}) => [UserActions.userFailure(error)])
    //         ))
    //     )
    // );

    loadUsers$ = createEffect(() => 
         this.actions$.pipe(
            ofType(UserActions.loadUsers),
            mergeMap(() => this.userRestController.loadUsers().pipe(
                map( users => UserActions.loadUsersSuccess({users})),
                catchError(({error}) => [UserActions.userFailure(error)])
            ))
        )
    );

}
