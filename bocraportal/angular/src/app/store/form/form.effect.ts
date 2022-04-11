// Generated by andromda-angular cartridge (app\usecase\effect.store.ts.vsl) DO NOT EDIT
import { Injectable } from '@angular/core';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import { catchError, map, mergeMap } from 'rxjs/operators';
import * as FormActions from './form.action';
import { FormRestControllerImpl } from '@app/service/bw/org/bocra/portal/form/form-rest-controller.impl';

@Injectable()
export class FormEffects {

    constructor(private actions$: Actions, private formRestController: FormRestControllerImpl) {}

    findById$ = createEffect(() => 
         this.actions$.pipe(
            ofType(FormActions.findById),
            mergeMap(({ id }) => this.formRestController.findById(id).pipe(
                map( formVO => FormActions.findByIdSuccess({ formVO })),
                catchError(({error}) => [FormActions.formFailure(error)])
            ))
        )
    );

    save$ = createEffect(() => 
         this.actions$.pipe(
            ofType(FormActions.save),
            mergeMap(({ formVO }) => this.formRestController.save(formVO).pipe(
                map( formVO => FormActions.saveSuccess({ formVO })),
                catchError(({error}) => [FormActions.formFailure(error)])
            ))
        )
    );

    remove$ = createEffect(() => 
         this.actions$.pipe(
            ofType(FormActions.remove),
            mergeMap(({ id }) => this.formRestController.remove(id).pipe(
                map( removed => FormActions.removeSuccess({removed})),
                catchError(({error}) => [FormActions.formFailure(error)])
            ))
        )
    );

    getAll$ = createEffect(() => 
         this.actions$.pipe(
            ofType(FormActions.getAll),
            mergeMap(() => this.formRestController.getAll().pipe(
                map( forms => FormActions.getAllSuccess({forms})),
                catchError(({error}) => [FormActions.formFailure(error)])
            ))
        )
    );

    search$ = createEffect(() => 
         this.actions$.pipe(
            ofType(FormActions.search),
            mergeMap(({ criteria }) => this.formRestController.search(criteria).pipe(
                map( forms => FormActions.searchSuccess({ forms })),
                catchError(({error}) => [FormActions.formFailure(error)])
            ))
        )
    );

    getAllPaged$ = createEffect(() => 
         this.actions$.pipe(
            ofType(FormActions.getAllPaged),
            mergeMap(({ pageNumber, pageSize}) => this.formRestController.getAllPaged(pageNumber, pageSize).pipe(
                map( forms => FormActions.getAllPagedSuccess({ forms })),
                catchError(({error}) => [FormActions.formFailure(error)])
            ))
        )
    );

}
