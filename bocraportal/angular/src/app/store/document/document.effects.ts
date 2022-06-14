// Generated by andromda-angular cartridge (app\usecase\effect.store.ts.vsl) DO NOT EDIT
import { Injectable } from '@angular/core';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import { catchError, map, mergeMap } from 'rxjs/operators';
import * as DocumentActions from './document.actions';
import { DocumentRestControllerImpl } from '@app/service/bw/org/bocra/portal/document/document-rest-controller.impl';

@Injectable()
export class DocumentEffects {

    constructor(private actions$: Actions, private documentRestController: DocumentRestControllerImpl) {}

    findById$ = createEffect(() => 
         this.actions$.pipe(
            ofType(DocumentActions.findById),
            mergeMap(({ id }) => this.documentRestController.findById(id).pipe(
                map( document => DocumentActions.findByIdSuccess({
                    document,
                    success: true
                })),
                catchError(({errors}) => [DocumentActions.documentFailure(errors)])
            ))
        )
    );

    save$ = createEffect(() => 
         this.actions$.pipe(
            ofType(DocumentActions.save),
            mergeMap(({ document }) => this.documentRestController.save(document).pipe(
                map( document => DocumentActions.saveSuccess({
                    document,
                    success: true
                })),
                catchError(({errors}) => [DocumentActions.documentFailure(errors)])
            ))
        )
    );

    remove$ = createEffect(() => 
         this.actions$.pipe(
            ofType(DocumentActions.remove),
            mergeMap(({ id }) => this.documentRestController.remove(id).pipe(
                map( removed => DocumentActions.removeSuccess({
                    removed,
                    success: true
                })),
                catchError(({errors}) => [DocumentActions.documentFailure(errors)])
            ))
        )
    );

    getAll$ = createEffect(() => 
         this.actions$.pipe(
            ofType(DocumentActions.getAll),
            mergeMap(() => this.documentRestController.getAll().pipe(
                map( documents => DocumentActions.getAllSuccess({
                    documents,
                    success: true
                })),
                catchError(({errors}) => [DocumentActions.documentFailure(errors)])
            ))
        )
    );

    search$ = createEffect(() => 
         this.actions$.pipe(
            ofType(DocumentActions.search),
            mergeMap(({ criteria }) => this.documentRestController.search(criteria).pipe(
                map( documents => DocumentActions.searchSuccess({
                    documents,
                    success: true
                })),
                catchError(({errors}) => [DocumentActions.documentFailure(errors)])
            ))
        )
    );

    getAllPaged$ = createEffect(() => 
         this.actions$.pipe(
            ofType(DocumentActions.getAllPaged),
            mergeMap(({ pageNumber, pageSize }) => this.documentRestController.getAllPaged(pageNumber, pageSize).pipe(
                map( documents => DocumentActions.getAllPagedSuccess({
                    documents,
                    success: true
                })),
                catchError(({errors}) => [DocumentActions.documentFailure(errors)])
            ))
        )
    );

    // getLicenseeDocuments$ = createEffect(() => 
    //      this.actions$.pipe(
    //         ofType(DocumentActions.getLicenseeDocuments),
    //         mergeMap(({ licenseeId }) => this.documentRestController.getLicenseeDocuments(licenseeId).pipe(
    //             map( documents => DocumentActions.getLicenseeDocumentsSuccess({
    //                 documents,
    //                 success: false
    //             })),
    //             catchError(({errors}) => [DocumentActions.documentFailure(errors)])
    //         ))
    //     )
    // );

    // getLicenceDocuments$ = createEffect(() => 
    //      this.actions$.pipe(
    //         ofType(DocumentActions.getLicenceDocuments),
    //         mergeMap(({ licenceId }) => this.documentRestController.getLicenceDocuments(licenceId).pipe(
    //             map( documents => DocumentActions.getLicenceDocumentsSuccess({
    //                 documents,
    //                 success: false
    //             })),
    //             catchError(({errors}) => [DocumentActions.documentFailure(errors)])
    //         ))
    //     )
    // );

}
