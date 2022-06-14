// Generated by andromda-angular cartridge (app\usecase\reducer.store.ts.vsl) DO NOT EDIT
import { Action, createFeature, createReducer, on } from '@ngrx/store';
import * as DocumentActions from './document.actions';
import {documentKey, initialState} from './document.state';

export const documentReducer = createReducer(
    initialState,
    on(DocumentActions.findByIdSuccess, (state, action) => ({
        ...state,
        document: action.document, 
        loading: false,
        success: action.success,
        errors: []
    })),
    on(DocumentActions.saveSuccess, (state, action) => ({
        ...state,
        document: action.document, 
        loading: false,
        success: action.success,
        errors: []
    })),
    on(DocumentActions.removeSuccess, (state, action) => ({
        ...state,
        removed: action.removed, 
        loading: false,
        success: action.success,
        errors: []
    })),
    on(DocumentActions.getAllSuccess, (state, action) => ({
        ...state,
        documents: action.documents, 
        loading: false,
        success: action.success,
        errors: []
    })),
    on(DocumentActions.searchSuccess, (state, action) => ({
        ...state,
        documents: action.documents, 
        loading: false,
        success: action.success,
        errors: []
    })),
    on(DocumentActions.getAllPagedSuccess, (state, action) => ({
        ...state,
        documents: action.documents, 
        loading: false,
        success: action.success,
        errors: []
    })),
    on(DocumentActions.getLicenseeDocumentsSuccess, (state, action) => ({
        ...state,
        documents: action.documents, 
        loading: false,
        success: action.success,
        errors: []
    })),
    on(DocumentActions.getLicenceDocumentsSuccess, (state, action) => ({
        ...state,
        documents: action.documents, 
        loading: false,
        success: action.success,
        errors: []
    })),
    on(DocumentActions.documentReset, (state) => ({
      ...state,
        documents: [], 
        criteria: null, 
        id: null, 
        document: null, 
        loading: false,
        success: false,
        errors: []
    })),
    on(DocumentActions.documentFailure, (state, action) => ({
        ...state,
        loading: false,
        success: false,
        errors: action.errors
    })),
    on(DocumentActions.documentLoading, (state, action) => ({
        ...state,
        loading: action.loading,
        success: false
    })),
    // on(DocumentActions.documentSuccess, (state, action) => ({
    //     ...state,
    //     loading: action.loading,
    //     success: action.success,
    //     errors: action.errors
    // }))
);

export const documentFeature = createFeature({
    name: documentKey,
    reducer: documentReducer
});
