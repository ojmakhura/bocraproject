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
                    messages: [`Licensee ${licensee.licenseeName} found.`],
                    success: true
                })),
                catchError(({error}) => [LicenseeActions.licenseeFailure({messages: [error.error]})])
            ))
        )
    );

    save$ = createEffect(() => 
         this.actions$.pipe(
            ofType(LicenseeActions.save),
            mergeMap(({ licensee }) => this.licenseeRestController.save(licensee).pipe(
                map( licensee => LicenseeActions.saveSuccess({
                    licensee,
                    messages: [`Licensee ${licensee.licenseeName} saved.`],
                    success: true
                })),
                catchError(({error}) => [LicenseeActions.licenseeFailure({messages: [error?.error ? error.error : error]})])
            ))
        )
    );

    remove$ = createEffect(() => 
         this.actions$.pipe(
            ofType(LicenseeActions.remove),
            mergeMap(({ id }) => this.licenseeRestController.remove(id).pipe(
                map( removed => LicenseeActions.removeSuccess({
                    removed,
                    messages: [`Licensee ${id} removed.`],
                    success: true
                })),
                catchError(({error}) => [LicenseeActions.licenseeFailure({messages: [error.error]})])
            ))
        )
    );

    getAll$ = createEffect(() => 
         this.actions$.pipe(
            ofType(LicenseeActions.getAll),
            mergeMap(() => this.licenseeRestController.getAll().pipe(
                map( licensees => LicenseeActions.getAllSuccess({
                    licensees,
                    messages: [`${licensees.length} licensees found.`],
                    success: true
                })),
                catchError(({error}) => [LicenseeActions.licenseeFailure({messages: [error.error]})])
            ))
        )
    );

    search$ = createEffect(() => 
         this.actions$.pipe(
            ofType(LicenseeActions.search),
            mergeMap(({ criteria }) => this.licenseeRestController.search(criteria).pipe(
                map( licensees => LicenseeActions.searchSuccess({
                    licensees,
                    messages: [`${licensees.length} licensees found.`],
                    success: true
                })),
                catchError(({error}) => [LicenseeActions.licenseeFailure({messages: [error.error]})])
            ))
        )
    );

    getAllPaged$ = createEffect(() => 
         this.actions$.pipe(
            ofType(LicenseeActions.getAllPaged),
            mergeMap(({ pageNumber, pageSize }) => this.licenseeRestController.getAllPaged(pageNumber, pageSize).pipe(
                map( licensees => LicenseeActions.getAllPagedSuccess({
                    licensees,
                    messages: [`Page ${pageNumber} found with ${licensees.length} licensees.`],
                    success: true
                })),
                catchError(({error}) => [LicenseeActions.licenseeFailure({messages: [error.error]})])
            ))
        )
    );

    // getDocuments$ = createEffect(() => 
    //      this.actions$.pipe(
    //         ofType(LicenseeActions.getDocuments),
    //         mergeMap(({ licenseeId }) => this.documentRestController.getLicenceDocuments(licenseeId).pipe(
    //             map( documents => LicenseeActions.getDocumentsSuccess({documents})),
    //             catchError(({error}) => [LicenseeActions.licenseeFailure({messages: [error.error]})])
    //         ))
    //     )
    // );

}
