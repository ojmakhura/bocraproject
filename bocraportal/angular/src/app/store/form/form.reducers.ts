// Generated by andromda-angular cartridge (app\usecase\reducer.store.ts.vsl) DO NOT EDIT
import { Action, createFeature, createReducer, on } from '@ngrx/store';
import * as FormActions from './form.actions';
import {formKey, initialState} from './form.state';

export const formReducer = createReducer(
    initialState,
    on(FormActions.findFormById, (state, action) => ({
        ...state,
        loading: action.loading,
        loaderMessage: action.loaderMessage,
    })),
    on(FormActions.findFormByIdSuccess, (state, action) => ({
        ...state,
        form: action.form,
        loading: false,
        loaderMessage: undefined,
        success: action.success,
        error: false,
        messages: action.messages
    })),
    on(FormActions.saveForm, (state, action) => ({
        ...state,
        loading: action.loading,
        loaderMessage: action.loaderMessage,
    })),
    on(FormActions.saveFormSuccess, (state, action) => ({
        ...state,
        form: action.form,
        loading: false,
        loaderMessage: undefined,
        success: action.success,
        error: false,
        messages: action.messages
    })),
    on(FormActions.removeForm, (state, action) => ({
        ...state,
        loading: action.loading,
        loaderMessage: action.loaderMessage,
    })),
    on(FormActions.removeFormSuccess, (state, action) => ({
        ...state,
        removed: action.removed,
        loading: false,
        loaderMessage: undefined,
        success: action.success,
        error: false,
        messages: action.messages
    })),
    on(FormActions.getAllForms, (state, action) => ({
        ...state,
        loading: action.loading,
        loaderMessage: action.loaderMessage,
    })),
    on(FormActions.getAllFormsSuccess, (state, action) => ({
        ...state,
        forms: action.forms,
        loading: false,
        loaderMessage: undefined,
        success: action.success,
        error: false,
        messages: action.messages
    })),
    on(FormActions.searchForms, (state, action) => ({
        ...state,
        loading: action.loading,
        loaderMessage: action.loaderMessage,
    })),
    on(FormActions.searchFormsSuccess, (state, action) => ({
        ...state,
        forms: action.forms,
        loading: false,
        loaderMessage: undefined,
        success: action.success,
        error: false,
        messages: action.messages
    })),
    on(FormActions.getAllFormsPaged, (state, action) => ({
        ...state,
        loading: action.loading,
        loaderMessage: action.loaderMessage,
    })),
    on(FormActions.getAllFormsPagedSuccess, (state, action) => ({
        ...state,
        forms: action.forms,
        loading: false,
        loaderMessage: undefined,
        success: action.success,
        error: false,
        messages: action.messages
    })),
    on(FormActions.findFieldById, (state, action) => ({
        ...state,
        loading: action.loading,
        loaderMessage: action.loaderMessage,
    })),
    on(FormActions.findFieldByIdSuccess, (state, action) => ({
        ...state,
        formField: action.formField,
        loading: false,
        loaderMessage: undefined,
        success: action.success,
        error: false,
        messages: action.messages
    })),
    on(FormActions.saveField, (state, action) => ({
        ...state,
        loading: action.loading,
        loaderMessage: action.loaderMessage,
    })),
    on(FormActions.saveFieldSuccess, (state, action) => ({
        ...state,
        formField: action.formField,
        loading: false,
        loaderMessage: undefined,
        success: action.success,
        error: false,
        messages: action.messages
    })),
    on(FormActions.saveSection, (state, action) => ({
        ...state,
        loading: action.loading,
        loaderMessage: action.loaderMessage,
    })),
    on(FormActions.saveSectionSuccess, (state, action) => ({
        ...state,
        formSections: [...state.formSections, action.formSection],
        formSection: action.formSection,
        loading: false,
        loaderMessage: undefined,
        success: action.success,
        error: false,
        messages: action.messages
    })),
    on(FormActions.setSections, (state, action) => ({
        ...state,
        formSections: action.formSections,
        loading: false,
        loaderMessage: undefined,
    })),
    on(FormActions.removeField, (state, action) => ({
        ...state,
        loading: action.loading,
        loaderMessage: action.loaderMessage,
    })),
    on(FormActions.removeFieldSuccess, (state, action) => ({
        ...state,
        removed: action.removed,
        loading: false,
        loaderMessage: undefined,
        success: action.success,
        error: false,
        messages: action.messages
    })),
    on(FormActions.getAllFields, (state, action) => ({
        ...state,
        loading: action.loading,
        loaderMessage: action.loaderMessage,
    })),
    on(FormActions.getAllFieldsSuccess, (state, action) => ({
        ...state,
        formFields: action.formFields,
        loading: false,
        loaderMessage: undefined,
        success: action.success,
        error: false,
        messages: action.messages
    })),
    on(FormActions.getAllFieldsPaged, (state, action) => ({
        ...state,
        loading: action.loading,
        loaderMessage: action.loaderMessage,
    })),
    on(FormActions.getAllFieldsPagedSuccess, (state, action) => ({
        ...state,
        formFields: action.formFields,
        loading: false,
        loaderMessage: undefined,
        success: action.success,
        error: false,
        messages: action.messages
    })),
    on(FormActions.formReset, (state) => ({
      ...state,
        criteria: null, 
        form: null, 
        id: null, 
        formField: null, 
        formFields: [], 
        formSection: null, 
        formSections: [], 
        forms: [], 
        loading: false,
        loaderMessage: undefined,
        success: false,
        error: false,
        messages: []
    })),
    on(FormActions.formFailure, (state, action) => ({
        ...state,
        loading: false,
        loaderMessage: undefined,
        success: false,
        error: true,
        messages: action.messages
    })),
    on(FormActions.formLoading, (state, action) => ({
        ...state,
        loading: action.loading,
        success: false
    })),
    on(FormActions.formSuccess, (state, action) => ({
        ...state,
        loading: action.loading,
        success: action.success,
        messages: action.messages
    }))
);

export const formFeature = createFeature({
    name: formKey,
    reducer: formReducer
});
