// Generated by andromda-angular cartridge (app\usecase\effect.store.ts.vsl) DO NOT EDIT
import { Injectable } from '@angular/core';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import { catchError, map, mergeMap } from 'rxjs/operators';
import * as ComplaintActions from './complaint.actions';
import { ComplaintRestController } from '@app/service/bw/org/bocra/portal/complaint/complaint-rest-controller';

@Injectable()
export class ComplaintEffects {

    constructor(private actions$: Actions, private complaintRestController: ComplaintRestController) {}

    findById$ = createEffect(() => 
         this.actions$.pipe(
            ofType(ComplaintActions.findById),
            mergeMap(({id}) => this.complaintRestController.findById(id).pipe(
                map( complaint => ComplaintActions.findByIdSuccess({complaint, messages: [`Action successful.`], success: true})),
                catchError(({error}) => [ComplaintActions.complaintFailure({messages: [error?.error ? error?.error : error]})])
            ))
        )
    );

    save$ = createEffect(() => 
         this.actions$.pipe(
            ofType(ComplaintActions.save),
            mergeMap(({complaint}) => this.complaintRestController.save(complaint).pipe(
                map( complaint => ComplaintActions.saveSuccess({complaint, messages: [`Action successful.`], success: true})),
                catchError(({error}) => [ComplaintActions.complaintFailure({messages: [error?.error ? error?.error : error]})])
            ))
        )
    );

    remove$ = createEffect(() => 
         this.actions$.pipe(
            ofType(ComplaintActions.remove),
            mergeMap(({id}) => this.complaintRestController.remove(id).pipe(
                map( removed => ComplaintActions.removeSuccess({removed, messages: [`Action successful.`], success: true})),
                catchError(({error}) => [ComplaintActions.complaintFailure({messages: [error?.error ? error?.error : error]})])
            ))
        )
    );

    getAll$ = createEffect(() => 
         this.actions$.pipe(
            ofType(ComplaintActions.getAll),
            mergeMap(({}) => this.complaintRestController.getAll().pipe(
                map( complaints => ComplaintActions.getAllSuccess({complaints, messages: [`Action successful.`], success: true})),
                catchError(({error}) => [ComplaintActions.complaintFailure({messages: [error?.error ? error?.error : error]})])
            ))
        )
    );

    search$ = createEffect(() => 
         this.actions$.pipe(
            ofType(ComplaintActions.search),
            mergeMap(({criteria}) => this.complaintRestController.search(criteria).pipe(
                map( complaints => ComplaintActions.searchSuccess({complaints, messages: [`Action successful.`], success: true})),
                catchError(({error}) => [ComplaintActions.complaintFailure({messages: [error?.error ? error?.error : error]})])
            ))
        )
    );

    getAllPaged$ = createEffect(() => 
         this.actions$.pipe(
            ofType(ComplaintActions.getAllPaged),
            mergeMap(({pageNumber, pageSize}) => this.complaintRestController.getAllPaged(pageNumber, pageSize).pipe(
                map( complaints => ComplaintActions.getAllPagedSuccess({complaints, messages: [`Action successful.`], success: true})),
                catchError(({error}) => [ComplaintActions.complaintFailure({messages: [error?.error ? error?.error : error]})])
            ))
        )
    );

    addDocument$ = createEffect(() => this.actions$.pipe(
        ofType(ComplaintActions.addDocument),
        mergeMap(({id, documentTypeId, file, fileName}) => this.complaintRestController.addDocument(id, documentTypeId, file, fileName).pipe(
            map( document => ComplaintActions.addDocumentSuccess({
                document,
                messages: ['Added ${document.documentType.name} with ID ${$document.documentId}.'],
                success: true
            })),
            catchError(({error}) => [ComplaintActions.complaintFailure({messages: [error?.error ? error?.error : error]})])
        ))
    ));

}
