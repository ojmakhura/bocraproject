// Generated by andromda-angular cartridge (app\usecase\reducer.store.ts.vsl) DO NOT EDIT
import { Action, createFeature, createReducer, on } from '@ngrx/store';
import * as LicenceActions from './licence.actions';
import {licenceKey, initialState} from './licence.state';

export const licenceReducer = createReducer(
    initialState,
    on(LicenceActions.findByIdSuccess, (state, action) => ({
        ...state,
        licence: action.licence, 
        error: null
    })),
    on(LicenceActions.saveSuccess, (state, action) => ({
        ...state,
        licence: action.licence, 
        error: null
    })),
    on(LicenceActions.removeSuccess, (state, action) => ({
        ...state,
        removed: true,
        error: null
    })),
    on(LicenceActions.getAllSuccess, (state, action) => ({
        ...state,
        licences: action.licences, 
        error: null
    })),
    on(LicenceActions.searchSuccess, (state, action) => ({
        ...state,
        licences: action.licences, 
        error: null
    })),
    on(LicenceActions.getAllPagedSuccess, (state, action) => ({
        ...state,
        licences: action.licences,
        error: null
    })),
    on(LicenceActions.getLicenceDocumentsSuccess, (state, action) => ({
        ...state,
        documents: action.documents, 
        error: null
    })),
    on(LicenceActions.licenceReset, (state) => ({
      ...state,
        licence: null, 
        licences: [], 
        id: null, 
        criteria: null,
        documents: [],
        error: null
    })),
    on(LicenceActions.licenceFailure, (state, action) => ({
        ...state,
        error: action.error
    }))
);

export const licenceFeature = createFeature({
    name: licenceKey,
    reducer: licenceReducer
});
