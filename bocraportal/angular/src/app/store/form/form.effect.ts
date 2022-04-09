// Generated by andromda-angular cartridge (app\usecase\effect.store.ts.vsl) DO NOT EDIT
import { Injectable } from '@angular/core';
import { FormRestControllerImpl } from '@app/service/bw/org/bocra/portal/form/form-rest-controller.impl';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import { catchError, map, mergeMap } from 'rxjs/operators';
import * as FormActions from './form.action';

@Injectable()
export class FormEffects {
    
  constructor(private actions$: Actions, private formService: FormRestControllerImpl) {}

  saveForm$ = createEffect(() => 
    this.actions$.pipe(
      ofType(FormActions.saveForm),
      mergeMap(({form}) => this.formService.save(form).pipe(
        map(form => FormActions.saveFormSuccess({form})),
        catchError(({error}) => [FormActions.formActionFailure(error)])
      ))
    )
  );

  findById$ = createEffect(() => 
    this.actions$.pipe(
      ofType(FormActions.findById),
      mergeMap(({id}) => this.formService.findById(id).pipe(
        map(form => FormActions.findByIdSuccess({form})),
        catchError(({error}) => [FormActions.formActionFailure(error)])
      ))
    )
  );

  loadAll$ = createEffect(() => 
    this.actions$.pipe(
      ofType(FormActions.loadAll),
      mergeMap(() => this.formService.getAll().pipe(
        map(forms => FormActions.loadAllSuccess({forms})),
        catchError(({error}) => [FormActions.formActionFailure(error)])
      ))
    )
  );

  searchForms$ = createEffect(() => 
    this.actions$.pipe(
      ofType(FormActions.searchForms),
      mergeMap(({searchCriteria}) => this.formService.search(searchCriteria).pipe(
        map(forms => FormActions.searchFormsSuccess({forms})),
        catchError(({error}) => [FormActions.formActionFailure(error)])
      ))
    )
  );
}
