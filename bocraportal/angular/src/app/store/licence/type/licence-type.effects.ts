// Generated by andromda-angular cartridge (app\usecase\effect.store.ts.vsl) DO NOT EDIT
import { Injectable } from '@angular/core';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import { catchError, map, mergeMap } from 'rxjs/operators';
import * as LicenceTypeActions from './licence-type.actions';
import { LicenceTypeRestController } from '@app/service/bw/org/bocra/portal/licence/type/licence-type-rest-controller';

@Injectable()
export class LicenceTypeEffects {

    constructor(private actions$: Actions, private licenceTypeRestController: LicenceTypeRestController) {}

    findById$ = createEffect(() => 
         this.actions$.pipe(
            ofType(LicenceTypeActions.findById),
            mergeMap(({ id }) => this.licenceTypeRestController.findById(id).pipe(
                map( licenceType => LicenceTypeActions.findByIdSuccess({
                    licenceType,
                    messages: [`Licence type ${licenceType.name} found.`],
                    success: true
                })),
                catchError(({error}) => [LicenceTypeActions.licenceTypeFailure({messages: [error]})])
            ))
        )
    );

    save$ = createEffect(() => 
         this.actions$.pipe(
            ofType(LicenceTypeActions.save),
            mergeMap(({ licenceType }) => this.licenceTypeRestController.save(licenceType).pipe(
                map( licenceType => LicenceTypeActions.saveSuccess({
                    licenceType,
                    messages: [`Licence type ${licenceType.name} saved.`],
                    success: true
                })),
                catchError(({error}) => [LicenceTypeActions.licenceTypeFailure({messages: [error]})])
            ))
        )
    );

    remove$ = createEffect(() => 
         this.actions$.pipe(
            ofType(LicenceTypeActions.remove),
            mergeMap(({ id }) => this.licenceTypeRestController.remove(id).pipe(
                map( removed => LicenceTypeActions.removeSuccess({
                    removed,
                    messages: [`Licence type ${id} removed.`],
                    success: true
                })),
                catchError(({error}) => [LicenceTypeActions.licenceTypeFailure({messages: [error]})])
            ))
        )
    );

    getAll$ = createEffect(() => 
         this.actions$.pipe(
            ofType(LicenceTypeActions.getAll),
            mergeMap(() => this.licenceTypeRestController.getAll().pipe(
                map( licenceTypes => LicenceTypeActions.getAllSuccess({
                    licenceTypes,
                    messages: [`${licenceTypes.length} licence types found.`],
                    success: true
                })),
                catchError(({error}) => [LicenceTypeActions.licenceTypeFailure({messages: [error]})])
            ))
        )
    );

    search$ = createEffect(() => 
         this.actions$.pipe(
            ofType(LicenceTypeActions.search),
            mergeMap(({ criteria }) => this.licenceTypeRestController.search(criteria).pipe(
                map( licenceTypes => LicenceTypeActions.searchSuccess({
                    licenceTypes,
                    messages: [`${licenceTypes.length} licence types found.`],
                    success: true
                })),
                catchError(({error}) => [LicenceTypeActions.licenceTypeFailure({messages: [error]})])
            ))
        )
    );

    getAllPaged$ = createEffect(() => 
         this.actions$.pipe(
            ofType(LicenceTypeActions.getAllPaged),
            mergeMap(({ pageNumber, pageSize }) => this.licenceTypeRestController.getAllPaged(pageNumber, pageSize).pipe(
                map( licenceTypes => LicenceTypeActions.getAllPagedSuccess({
                    licenceTypes,
                    messages: [`Page ${pageNumber} found with ${licenceTypes.length} licence types.`],
                    success: true
                })),
                catchError(({error}) => [LicenceTypeActions.licenceTypeFailure({messages: [error]})])
            ))
        )
    );

}
