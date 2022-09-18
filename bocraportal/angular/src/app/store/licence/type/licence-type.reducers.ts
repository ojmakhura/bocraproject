// Generated by andromda-angular cartridge (app\usecase\reducer.store.ts.vsl) DO NOT EDIT
import { Action, createFeature, createReducer, on } from '@ngrx/store';
import * as LicenceTypeActions from './licence-type.actions';
import {licenceTypeKey, initialState} from './licence-type.state';

export const licenceTypeReducer = createReducer(
    initialState,
    on(LicenceTypeActions.findById, (state, action) => ({
        ...state,
        loading: action.loading,
        loaderMessage: action.loaderMessage,
    })),
    on(LicenceTypeActions.findByIdSuccess, (state, action) => ({
        ...state,
        licenceType: action.licenceType, 
        loading: false,
        loaderMessage: undefined,
        success: action.success,
        error: false,
        messages:  action.messages
    })),
    on(LicenceTypeActions.save, (state, action) => ({
        ...state,
        loading: action.loading,
        loaderMessage: action.loaderMessage,
    })),
    on(LicenceTypeActions.saveSuccess, (state, action) => ({
        ...state,
        licenceType: action.licenceType, 
        loading: false,
        loaderMessage: undefined,
        success: action.success,
        error: false,
        messages:  action.messages
    })),
    on(LicenceTypeActions.remove, (state, action) => ({
        ...state,
        loading: action.loading,
        loaderMessage: action.loaderMessage,
    })),
    on(LicenceTypeActions.removeSuccess, (state, action) => ({
        ...state,
        licenceType: null, 
        removed: action.removed,
        loading: false,
        loaderMessage: undefined,
        success: action.success,
        error: false,
        messages:  action.messages
    })),
    on(LicenceTypeActions.getAll, (state, action) => ({
        ...state,
        loading: action.loading,
        loaderMessage: action.loaderMessage,
    })),
    on(LicenceTypeActions.getAllSuccess, (state, action) => ({
        ...state,
        licenceTypes: action.licenceTypes, 
        loading: false,
        loaderMessage: undefined,
        success: action.success,
        error: false,
        messages:  action.messages
    })),
    on(LicenceTypeActions.search, (state, action) => ({
        ...state,
        loading: action.loading,
        loaderMessage: action.loaderMessage,
    })),
    on(LicenceTypeActions.searchSuccess, (state, action) => ({
        ...state,
        licenceTypes: action.licenceTypes, 
        loading: false,
        loaderMessage: undefined,
        success: action.success,
        error: false,
        messages:  action.messages
    })),
    on(LicenceTypeActions.getAllPaged, (state, action) => ({
        ...state,
        loading: action.loading,
        loaderMessage: action.loaderMessage,
    })),
    on(LicenceTypeActions.getAllPagedSuccess, (state, action) => ({
        ...state,
        licenceTypes: action.licenceTypes, 
        loading: false,
        loaderMessage: undefined,
        success: action.success,
        error: false,
        message:  action.messages
    })),
    on(LicenceTypeActions.licenceTypeReset, (state) => ({
        ...state,
        licenceType: null, 
        licenceTypes: [], 
        criteria: null, 
        loading: false,
        loaderMessage: undefined,
        id: null, 
        removed: false,
        error: false,
        success: false,
        messages: []
    })),
    on(LicenceTypeActions.licenceTypeFailure, (state, action) => ({
        ...state,
        messages: action.messages,
        error: true,
    }))
);

export const licenceTypeFeature = createFeature({
    name: licenceTypeKey,
    reducer: licenceTypeReducer
});
