// Generated by andromda-angular cartridge (app\usecase\reducer.store.ts.vsl) DO NOT EDIT
import { Action, createFeature, createReducer, on } from '@ngrx/store';
import * as FormActions from './form.actions';
import { formKey, initialState } from './form.state';

export const formReducer = createReducer(
    initialState,
    on(FormActions.findFormByIdSuccess, (state, action) => ({
        ...state,
        form: action.form,
        error: null
    })),
    on(FormActions.saveFormSuccess, (state, action) => ({
        ...state,
        form: action.form,
        error: null
    })),
    on(FormActions.removeFormSuccess, (state, action) => ({
        ...state,
        removed: action.removed,
        error: null
    })),
    on(FormActions.getAllFormsSuccess, (state, action) => ({
        ...state,
        forms: action.forms,
        error: null
    })),
    on(FormActions.searchFormsSuccess, (state, action) => ({
        ...state,
        forms: action.forms,
        error: null
    })),
    on(FormActions.getAllFormsPagedSuccess, (state, action) => ({
        ...state,
        forms: action.forms,
        error: null
    })),
    on(FormActions.findFieldByIdSuccess, (state, action) => ({
        ...state,
        formField: action.formField,
        error: null
    })),
    on(FormActions.saveFieldSuccess, (state, action) => ({
        ...state,
        formField: action.formField,
        error: null
    })),
    on(FormActions.removeFieldSuccess, (state, action) => ({
        ...state,
        removed: action.removed,
        error: null
    })),
    on(FormActions.getAllFieldsSuccess, (state, action) => ({
        ...state,
        formFields: action.formFields,
        error: null
    })),
    on(FormActions.getAllFieldsPagedSuccess, (state, action) => ({
        ...state,
        formFields: action.formFields,
        error: null
    })),
    on(FormActions.formReset, (state) => ({
        ...state,
        criteria: null,
        fieldType: null,
        formFields: [],
        formField: null,
        form: null,
        required: null,
        min: null,
        id: null,
        fieldName: null,
        forms: [],
        max: null,
        defaultValue: null,
        removed: false,
        error: null,
    })),
    on(FormActions.formFailure, (state, action) => ({
        ...state,
        error: action.error
    }))
);

export const formFeature = createFeature({
    name: formKey,
    reducer: formReducer
});
