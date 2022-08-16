// Generated by andromda-angular cartridge (app\usecase\effect.store.ts.vsl) DO NOT EDIT
import { Injectable } from '@angular/core';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import { catchError, map, mergeMap } from 'rxjs/operators';
import * as LicenceActions from './licence.actions';
import { DocumentRestController } from '@app/service/bw/org/bocra/portal/document/document-rest-controller';
import { LicenceRestController } from '@app/service/bw/org/bocra/portal/licence/licence-rest-controller';

@Injectable()
export class LicenceEffects {

    constructor(private actions$: Actions, private licenceRestController: LicenceRestController, private documentRestController: DocumentRestController) {}

    findById$ = createEffect(() => 
         this.actions$.pipe(
            ofType(LicenceActions.findById),
            mergeMap(({ id }) => this.licenceRestController.findById(id).pipe(
                map( licence => LicenceActions.findByIdSuccess({
                    licence,
                    messages: [`Licence ${licence.licenceNumber} found.`],
                    success: true
                })),
                catchError(({error}) => [LicenceActions.licenceFailure({messages: [error.error]})])
            ))
        )
    );

    save$ = createEffect(() => 
         this.actions$.pipe(
            ofType(LicenceActions.save),
            mergeMap(({ licence }) => this.licenceRestController.save(licence).pipe(
                map( licence => LicenceActions.saveSuccess({
                    licence,
                    messages: [`Licence ${licence.licenceNumber} saved.`],
                    success: true
                })),
                catchError(({error}) => [LicenceActions.licenceFailure({messages: [error.error]})])
            ))
        )
    );

    remove$ = createEffect(() => 
         this.actions$.pipe(
            ofType(LicenceActions.remove),
            mergeMap(({ id }) => this.licenceRestController.remove(id).pipe(
                map( removed => LicenceActions.removeSuccess({
                    removed,
                    messages: [`Licence ${id} removed.`],
                    success: true
                })),
                catchError(({error}) => [LicenceActions.licenceFailure({messages: [error.error]})])
            ))
        )
    );

    getAll$ = createEffect(() => 
         this.actions$.pipe(
            ofType(LicenceActions.getAll),
            mergeMap(() => this.licenceRestController.getAll().pipe(
                map( licences => LicenceActions.getAllSuccess({
                    licences,
                    messages: [`${licences.length} licences found.`],
                    success: true
                })),
                catchError(({error}) => [LicenceActions.licenceFailure({messages: [error.error]})])
            ))
        )
    );

    search$ = createEffect(() => 
         this.actions$.pipe(
            ofType(LicenceActions.search),
            mergeMap(({ criteria }) => this.licenceRestController.search(criteria).pipe(
                map( licences => LicenceActions.searchSuccess({
                    licences,
                    messages: [`${licences.length} licences found.`],
                    success: true
                })),
                catchError(({error}) => [LicenceActions.licenceFailure({messages: [error.error]})])
            ))
        )
    );

    getAllPaged$ = createEffect(() => 
         this.actions$.pipe(
            ofType(LicenceActions.getAllPaged),
            mergeMap(({ pageNumber, pageSize }) => this.licenceRestController.getAllPaged(pageNumber, pageSize).pipe(
                map( licences => LicenceActions.getAllPagedSuccess({
                    licences,
                    messages: [`Page ${pageNumber} found with ${licences.length} licences.`],
                    success: true
                })),
                catchError(({error}) => [LicenceActions.licenceFailure({messages: [error.error]})])
            ))
        )
    );

    // getLicenceDocuments$ = createEffect(() => 
    //      this.actions$.pipe(
    //         ofType(LicenceActions.getLicenceDocuments),
    //         mergeMap(({ licenceId }) => this.documentRestController.getLicenceDocuments(licenceId).pipe(
    //             map( documents => LicenceActions.getLicenceDocumentsSuccess({
    //                 documents,
    //                 success: true
    //             })),
    //             catchError(({error}) => [LicenceActions.licenceFailure({messages: [error.error]})])
    //         ))
    //     )
    // );

}
