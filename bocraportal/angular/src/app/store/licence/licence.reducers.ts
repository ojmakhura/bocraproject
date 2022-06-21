// Generated by andromda-angular cartridge (app\usecase\reducer.store.ts.vsl) DO NOT EDIT
import { Action, createFeature, createReducer, on } from '@ngrx/store';
import * as LicenceActions from './licence.actions';
import {licenceKey, initialState} from './licence.state';

export const licenceReducer = createReducer(
    initialState,
    on(LicenceActions.findByIdSuccess, (state, action) => ({
        ...state,
        licence: action.licence, 
        loading: false,
        success: action.success,
        messages: action.messages
    })),
    on(LicenceActions.saveSuccess, (state, action) => ({
        ...state,
        licence: action.licence, 
        loading: false,
        success: action.success,
        messages: action.messages
    })),
    on(LicenceActions.removeSuccess, (state, action) => ({
        ...state,
        removed: true,
        loading: false,
        success: action.success,
        messages: action.messages
    })),
    on(LicenceActions.getAllSuccess, (state, action) => ({
        ...state,
        licences: action.licences, 
        loading: false,
        success: action.success,
        messages: action.messages
    })),
    on(LicenceActions.searchSuccess, (state, action) => ({
        ...state,
        licences: action.licences, 
        loading: false,
        success: action.success,
        messages: action.messages
    })),
    on(LicenceActions.getAllPagedSuccess, (state, action) => ({
        ...state,
        licences: action.licences,
        loading: false,
        success: action.success,
        messages: action.messages
    })),
    on(LicenceActions.getLicenceDocumentsSuccess, (state, action) => ({
        ...state,
        documents: action.documents, 
        loading: false,
        success: action.success,
        messages: action.messages
    })),
    on(LicenceActions.licenceReset, (state) => ({
      ...state,
        licence: null, 
        licences: [], 
        id: null, 
        criteria: null,
        documents: [],
        loading: false,
        success: false,
        error: false,
        messages: []
    })),
    on(LicenceActions.licenceFailure, (state, action) => ({
        ...state,
        messages: action.messages,
        error: true,
        loading: false,
        success: false
    }))
);

export const licenceFeature = createFeature({
    name: licenceKey,
    reducer: licenceReducer
});
