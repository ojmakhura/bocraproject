// Generated by andromda-angular cartridge (app\usecase\effect.store.ts.vsl) DO NOT EDIT
import { Injectable } from '@angular/core';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import { catchError, map, mergeMap } from 'rxjs/operators';
import * as LicenseeActions from './licensee.actions';
import { LicenseeRestControllerImpl } from '@app/service/bw/org/bocra/portal/licensee/licensee-rest-controller.impl';

@Injectable()
export class LicenseeEffects {
    documentRestController: any;

    constructor(private actions$: Actions, private licenseeRestController: LicenseeRestControllerImpl) {}

    findById$ = createEffect(() => 
         this.actions$.pipe(
            ofType(LicenseeActions.findById),
            mergeMap(({ id }) => this.licenseeRestController.findById(id).pipe(
                map( licensee => LicenseeActions.findByIdSuccess({
                    licensee,
                    success: true
                })),
                catchError(({errors}) => [LicenseeActions.licenseeFailure(errors)])
            ))
        )
    );

    save$ = createEffect(() => 
         this.actions$.pipe(
            ofType(LicenseeActions.save),
            mergeMap(({ licensee }) => this.licenseeRestController.save(licensee).pipe(
                map( licensee => LicenseeActions.saveSuccess({
                    licensee,
                    success: true
                })),
                catchError(({errors}) => [LicenseeActions.licenseeFailure(errors)])
            ))
        )
    );

    remove$ = createEffect(() => 
         this.actions$.pipe(
            ofType(LicenseeActions.remove),
            mergeMap(({ id }) => this.licenseeRestController.remove(id).pipe(
                map( removed => LicenseeActions.removeSuccess({
                    removed,
                    success: true
                })),
                catchError(({errors}) => [LicenseeActions.licenseeFailure(errors)])
            ))
        )
    );

    getAll$ = createEffect(() => 
         this.actions$.pipe(
            ofType(LicenseeActions.getAll),
            mergeMap(() => this.licenseeRestController.getAll().pipe(
                map( licensees => LicenseeActions.getAllSuccess({
                    licensees,
                    success: true
                })),
                catchError(({errors}) => [LicenseeActions.licenseeFailure(errors)])
            ))
        )
    );

    search$ = createEffect(() => 
         this.actions$.pipe(
            ofType(LicenseeActions.search),
            mergeMap(({ criteria }) => this.licenseeRestController.search(criteria).pipe(
                map( licensees => LicenseeActions.searchSuccess({
                    licensees,
                    success: true
                })),
                catchError(({errors}) => [LicenseeActions.licenseeFailure(errors)])
            ))
        )
    );

    getAllPaged$ = createEffect(() => 
         this.actions$.pipe(
            ofType(LicenseeActions.getAllPaged),
            mergeMap(({ pageNumber, pageSize }) => this.licenseeRestController.getAllPaged(pageNumber, pageSize).pipe(
                map( licensees => LicenseeActions.getAllPagedSuccess({
                    licensees,
                    success: true
                })),
                catchError(({errors}) => [LicenseeActions.licenseeFailure(errors)])
            ))
        )
    );

    // getDocuments$ = createEffect(() => 
    //      this.actions$.pipe(
    //         ofType(LicenseeActions.getDocuments),
    //         mergeMap(({ licenseeId }) => this.documentRestController.getLicenceDocuments(licenseeId).pipe(
    //             map( documents => LicenseeActions.getDocumentsSuccess({documents})),
    //             catchError(({errors}) => [LicenseeActions.licenseeFailure(errors)])
    //         ))
    //     )
    // );

}
