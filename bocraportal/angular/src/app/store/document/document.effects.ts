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
                    messages: [`Document ${document.documentName} found.`],
                    success: true
                })),
                catchError(({messages}) => [DocumentActions.documentFailure(messages)])
            ))
        )
    );

    save$ = createEffect(() => 
         this.actions$.pipe(
            ofType(DocumentActions.save),
            mergeMap(({ document }) => this.documentRestController.save(document).pipe(
                map( document => DocumentActions.saveSuccess({
                    document,
                    messages: [`Document ${document.documentName} saved.`],
                    success: true
                })),
                catchError(({messages}) => [DocumentActions.documentFailure(messages)])
            ))
        )
    );

    remove$ = createEffect(() => 
         this.actions$.pipe(
            ofType(DocumentActions.remove),
            mergeMap(({ id }) => this.documentRestController.remove(id).pipe(
                map( removed => DocumentActions.removeSuccess({
                    removed,
                    messages: [`Document ${id} removed.`],
                    success: true
                })),
                catchError(({messages}) => [DocumentActions.documentFailure(messages)])
            ))
        )
    );

    getAll$ = createEffect(() => 
         this.actions$.pipe(
            ofType(DocumentActions.getAll),
            mergeMap(() => this.documentRestController.getAll().pipe(
                map( documents => DocumentActions.getAllSuccess({
                    documents,
                    messages: [`${documents.length} document found.`],
                    success: true
                })),
                catchError(({messages}) => [DocumentActions.documentFailure(messages)])
            ))
        )
    );

    search$ = createEffect(() => 
         this.actions$.pipe(
            ofType(DocumentActions.search),
            mergeMap(({ criteria }) => this.documentRestController.search(criteria).pipe(
                map( documents => DocumentActions.searchSuccess({
                    documents,
                    messages: [`${documents.length} document found.`],
                    success: true
                })),
                catchError(({messages}) => [DocumentActions.documentFailure(messages)])
            ))
        )
    );

    getAllPaged$ = createEffect(() => 
         this.actions$.pipe(
            ofType(DocumentActions.getAllPaged),
            mergeMap(({ pageNumber, pageSize }) => this.documentRestController.getAllPaged(pageNumber, pageSize).pipe(
                map( documents => DocumentActions.getAllPagedSuccess({
                    documents,
                    messages: [`Page ${pageNumber} found with ${documents.length} documents.`],
                    success: true
                })),
                catchError(({messages}) => [DocumentActions.documentFailure(messages)])
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
    //             catchError(({messages}) => [DocumentActions.documentFailure(messages)])
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
    //             catchError(({messages}) => [DocumentActions.documentFailure(messages)])
    //         ))
    //     )
    // );

}
