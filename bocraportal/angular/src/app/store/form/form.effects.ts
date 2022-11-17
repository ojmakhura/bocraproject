// Generated by andromda-angular cartridge (app\usecase\effect.store.ts.vsl) DO NOT EDIT
import { Injectable } from '@angular/core';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import { catchError, map, mergeMap } from 'rxjs/operators';
import * as FormActions from './form.actions';
import { FormFieldRestController } from '@app/service/bw/org/bocra/portal/form/field/form-field-rest-controller';
import { FormRestController } from '@app/service/bw/org/bocra/portal/form/form-rest-controller';
import { FormSectionRestController } from '@app/service/bw/org/bocra/portal/form/section/form-section-rest-controller';

@Injectable()
export class FormEffects {

    constructor(
        private actions$: Actions, 
        private formFieldRestController: FormFieldRestController, 
        private formRestController: FormRestController,
        private formSectionRestController: FormSectionRestController) {
        
    }

    findFormById$ = createEffect(() => 
         this.actions$.pipe(
            ofType(FormActions.findFormById),
            mergeMap(({ id }) => this.formRestController.findById(id).pipe(
                map( form => FormActions.findFormByIdSuccess({
                    form,
                    messages: [`Form ${form?.formName} found.`],
                    success: true
                })),
                catchError(({error}) => [FormActions.formFailure({messages: [error?.error ? error.error : error]})])
            ))
        )
    );

    saveForm$ = createEffect(() => 
         this.actions$.pipe(
            ofType(FormActions.saveForm),
            mergeMap(({ form }) => this.formRestController.save(form).pipe(
                map( form => FormActions.saveFormSuccess({
                    form,
                    messages: [`Form ${form?.formName} saved.`],
                    success: true
                })),
                catchError(({error}) => [FormActions.formFailure({messages: [error?.error ? error.error : error]})])
            ))
        )
    );

    removeForm$ = createEffect(() => 
         this.actions$.pipe(
            ofType(FormActions.removeForm),
            mergeMap(({ id }) => this.formRestController.remove(id).pipe(
                map( removed => FormActions.removeFormSuccess({
                    removed,
                    messages: [`Form ${id} successfully removed.`],
                    success: true
                })),
                catchError(({error}) => [FormActions.formFailure({messages: [error?.error ? error.error : error]})])
            ))
        )
    );

    getAllForms$ = createEffect(() => 
         this.actions$.pipe(
            ofType(FormActions.getAllForms),
            mergeMap(() => this.formRestController.getAll().pipe(
                map( forms => FormActions.getAllFormsSuccess({
                    forms,
                    messages: [`${forms.length} forms found.`],
                    success: true
                })),
                catchError(({error}) => [FormActions.formFailure({messages: [error?.error ? error.error : error]})])
            ))
        )
    );

    searchForms$ = createEffect(() => 
         this.actions$.pipe(
            ofType(FormActions.searchForms),
            mergeMap(({ criteria }) => this.formRestController.search(criteria).pipe(
                map( forms => FormActions.searchFormsSuccess({
                    forms,
                    messages: [`${forms.length} forms found.`],
                    success: true
                })),
                catchError(({error}) => [FormActions.formFailure({messages: [error?.error ? error.error : error]})])
            ))
        )
    );

    getAllFormsPaged$ = createEffect(() => 
         this.actions$.pipe(
            ofType(FormActions.getAllFormsPaged),
            mergeMap(({ pageNumber, pageSize }) => this.formRestController.getAllPaged(pageNumber, pageSize).pipe(
                map( forms => FormActions.getAllFormsPagedSuccess({
                    forms,
                    messages: [`Page ${pageNumber} found with ${forms.length} forms.`],
                    success: true
                })),
                catchError(({error}) => [FormActions.formFailure({messages: [error?.error ? error.error : error]})])
            ))
        )
    );

    findFieldById$ = createEffect(() => 
         this.actions$.pipe(
            ofType(FormActions.findFieldById),
            mergeMap(({ id }) => this.formFieldRestController.findById(id).pipe(
                map( formField => FormActions.findFieldByIdSuccess({
                    formField,
                    messages: [`Form field  ${formField?.fieldName} found.`],
                    success: true
                })),
                catchError(({error}) => [FormActions.formFailure({messages: [error?.error ? error.error : error]})])
            ))
        )
    );

    saveField$ = createEffect(() => 
         this.actions$.pipe(
            ofType(FormActions.saveField),
            mergeMap(({ formField }) => this.formFieldRestController.save(formField).pipe(
                map( formField => FormActions.saveFieldSuccess({
                    formField,
                    messages: [`Form field ${formField?.fieldName} saved.`],
                    success: true
                })),
                catchError(({error}) => [FormActions.formFailure({messages: [error?.error ? error.error : error]})])
            ))
        )
    );

    saveSection$ = createEffect(() => 
         this.actions$.pipe(
            ofType(FormActions.saveSection),
            mergeMap(({ formSection }) => this.formSectionRestController.save(formSection).pipe(
                map( formSection => FormActions.saveSectionSuccess({
                    formSection,
                    messages: [`Form section ${formSection?.sectionLabel} saved.`],
                    success: true
                })),
                catchError(({error}) => [FormActions.formFailure({messages: [error?.error ? error.error : error]})])
            ))
        )
    );

    removeSection$ = createEffect(() => 
         this.actions$.pipe(
            ofType(FormActions.removeSection),
            mergeMap(({ id }) => this.formSectionRestController.remove(id).pipe(
                map( removed => FormActions.removeSectionSuccess({
                    removed,
                    messages: [`Form section ${id} removed.`],
                    success: true
                })),
                catchError(({error}) => [FormActions.formFailure({messages: [error?.error ? error.error : error]})])
            ))
        )
    );

    removeField$ = createEffect(() => 
         this.actions$.pipe(
            ofType(FormActions.removeField),
            mergeMap(({ id }) => this.formFieldRestController.remove(id).pipe(
                map( removed => FormActions.removeFieldSuccess({
                    removed,
                    messages: [`Form field ${id} removed.`],
                    success: true
                })),
                catchError(({error}) => [FormActions.formFailure({messages: [error?.error ? error.error : error]})])
            ))
        )
    );

    getAllFields$ = createEffect(() => 
         this.actions$.pipe(
            ofType(FormActions.getAllFields),
            mergeMap(() => this.formFieldRestController.getAll().pipe(
                map( formFields => FormActions.getAllFieldsSuccess({
                    formFields,
                    messages: [`${formFields.length} form fields found.`],
                    success: true
                })),
                catchError(({error}) => [FormActions.formFailure({messages: [error?.error ? error.error : error]})])
            ))
        )
    );

    getAllFieldsPaged$ = createEffect(() => 
         this.actions$.pipe(
            ofType(FormActions.getAllFieldsPaged),
            mergeMap(({ pageNumber, pageSize }) => this.formFieldRestController.getAllPaged(pageNumber, pageSize).pipe(
                map( formFields => FormActions.getAllFieldsPagedSuccess({
                    formFields,
                    messages: [`Page ${pageNumber} found with ${formFields.length} form fields.`],
                    success: true
                })),
                catchError(({error}) => [FormActions.formFailure({messages: [error?.error ? error.error : error]})])
            ))
        )
    );

}
