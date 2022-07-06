// Generated by andromda-angular cartridge (app\usecase\reducer.store.ts.vsl) DO NOT EDIT
import { Action, createFeature, createReducer, on } from '@ngrx/store';
import * as LicenceTypeActions from './licence-type.actions';
import {licenceTypeKey, initialState} from './licence-type.state';

export const licenceTypeReducer = createReducer(
    initialState,
    on(LicenceTypeActions.findByIdSuccess, (state, action) => ({
        ...state,
        licenceType: action.licenceType, 
        loading: false,
        success: action.success,
        messages:  action.messages
    })),
    on(LicenceTypeActions.saveSuccess, (state, action) => ({
        ...state,
        licenceType: action.licenceType, 
        loading: false,
        success: action.success,
        messages:  action.messages
    })),
    on(LicenceTypeActions.removeSuccess, (state, action) => ({
        ...state,
        licenceType: null, 
        removed: action.removed,
        loading: false,
        success: action.success,
        messages:  action.messages
    })),
    on(LicenceTypeActions.getAllSuccess, (state, action) => ({
        ...state,
        licenceTypes: action.licenceTypes, 
        loading: false,
        success: action.success,
        messages:  action.messages
    })),
    on(LicenceTypeActions.searchSuccess, (state, action) => ({
        ...state,
        licenceTypes: action.licenceTypes, 
        loading: false,
        success: action.success,
        messages:  action.messages
    })),
    on(LicenceTypeActions.getAllPagedSuccess, (state, action) => ({
        ...state,
        licenceTypes: action.licenceTypes, 
        loading: false,
        success: action.success,
        message:  action.messages
    })),
    on(LicenceTypeActions.licenceTypeReset, (state) => ({
        ...state,
        licenceType: null, 
        licenceTypes: [], 
        criteria: null, 
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
