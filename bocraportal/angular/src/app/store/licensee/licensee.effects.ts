// Generated by andromda-angular cartridge (app\usecase\effect.store.ts.vsl) DO NOT EDIT
import { Injectable } from '@angular/core';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import { catchError, map, mergeMap } from 'rxjs/operators';
import * as LicenseeActions from './licensee.actions';
import { LicenseeRestControllerImpl } from '@app/service/bw/org/bocra/portal/licensee/licensee-rest-controller.impl';
import { DocumentRestControllerImpl } from '@app/service/bw/org/bocra/portal/document/document-rest-controller.impl';

@Injectable()
export class LicenseeEffects {

    constructor(private actions$: Actions, private licenseeRestController: LicenseeRestControllerImpl, private documentRestController: DocumentRestControllerImpl) {}

    findById$ = createEffect(() => 
         this.actions$.pipe(
            ofType(LicenseeActions.findById),
            mergeMap(({ id }) => this.licenseeRestController.findById(id).pipe(
                map( licensee => LicenseeActions.findByIdSuccess({licensee})),
                catchError(({error}) => [LicenseeActions.licenseeFailure(error)])
            ))
        )
    );

    save$ = createEffect(() => 
         this.actions$.pipe(
            ofType(LicenseeActions.save),
            mergeMap(({ licensee }) => this.licenseeRestController.save(licensee).pipe(
                map( licensee => LicenseeActions.saveSuccess({licensee})),
                catchError(({error}) => [LicenseeActions.licenseeFailure(error)])
            ))
        )
    );

    remove$ = createEffect(() => 
         this.actions$.pipe(
            ofType(LicenseeActions.remove),
            mergeMap(({ id }) => this.licenseeRestController.remove(id).pipe(
                map( removed => LicenseeActions.removeSuccess({removed})),
                catchError(({error}) => [LicenseeActions.licenseeFailure(error)])
            ))
        )
    );

    getAll$ = createEffect(() => 
         this.actions$.pipe(
            ofType(LicenseeActions.getAll),
            mergeMap(() => this.licenseeRestController.getAll().pipe(
                map( licensees => LicenseeActions.getAllSuccess({licensees})),
                catchError(({error}) => [LicenseeActions.licenseeFailure(error)])
            ))
        )
    );

    search$ = createEffect(() => 
         this.actions$.pipe(
            ofType(LicenseeActions.search),
            mergeMap(({ criteria }) => this.licenseeRestController.search(criteria).pipe(
                map( licensees => LicenseeActions.searchSuccess({licensees})),
                catchError(({error}) => [LicenseeActions.licenseeFailure(error)])
            ))
        )
    );

    getAllPaged$ = createEffect(() => 
         this.actions$.pipe(
            ofType(LicenseeActions.getAllPaged),
            mergeMap(({ pageNumber, pageSize }) => this.licenseeRestController.getAllPaged(pageNumber, pageSize).pipe(
                map( licensees => LicenseeActions.getAllPagedSuccess({licensees})),
                catchError(({error}) => [LicenseeActions.licenseeFailure(error)])
            ))
        )
    );

    getDocuments$ = createEffect(() => 
         this.actions$.pipe(
            ofType(LicenseeActions.getDocuments),
            mergeMap(({ licenseeId }) => this.documentRestController.getLicenceDocuments(licenseeId).pipe(
                map( documents => LicenseeActions.getDocumentsSuccess({documents})),
                catchError(({error}) => [LicenseeActions.licenseeFailure(error)])
            ))
        )
    );

}
