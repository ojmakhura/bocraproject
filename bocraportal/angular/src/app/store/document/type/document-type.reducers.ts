// Generated by andromda-angular cartridge (app\usecase\reducer.store.ts.vsl) DO NOT EDIT
import { Action, createFeature, createReducer, on } from '@ngrx/store';
import * as DocumentTypeActions from './document-type.actions';
import {documentTypeKey, initialState} from './document-type.state';

export const documentTypeReducer = createReducer(
    initialState,
    on(DocumentTypeActions.findByIdSuccess, (state, action) => ({
        ...state,
        documentType: action.documentType, 
        documentTypes: [], 
        criteria: null, 
        id: null, 
        success: action.success,
        messages: action.messages
    })),
    on(DocumentTypeActions.saveSuccess, (state, action) => ({
        ...state,
        documentType: action.documentType, 
        documentTypes: [], 
        criteria: null, 
        id: null, 
        success: action.success,
        messages: action.messages
    })),
    on(DocumentTypeActions.removeSuccess, (state, action) => ({
        ...state,
        documentType: null, 
        documentTypes: [], 
        criteria: null, 
        id: null, 
        success: action.success,
        messages: action.messages
    })),
    on(DocumentTypeActions.getAllSuccess, (state, action) => ({
        ...state,
        documentType: null, 
        documentTypes: action.documentTypes, 
        criteria: null, 
        id: null, 
        success: action.success,
        messages: action.messages
    })),
    on(DocumentTypeActions.searchSuccess, (state, action) => ({
        ...state,
        documentType: null, 
        documentTypes: action.documentTypes, 
        criteria: null, 
        id: null, 
        success: action.success,
        messages: action.messages
    })),
    on(DocumentTypeActions.getAllPagedSuccess, (state, action) => ({
        ...state,
        documentType: null, 
        documentTypes: action.documentTypes, 
        criteria: null, 
        id: null, 
        success: action.success,
        messages: action.messages
    })),
    on(DocumentTypeActions.documentTypeReset, (state) => ({
      ...state,
        documentType: null, 
        documentTypes: [], 
        criteria: null, 
        id: null, 
        loading: false,
        success: false,
        error: false,
        messages: []
    })),
    on(DocumentTypeActions.documentTypeFailure, (state, action) => ({
        ...state,
        loading: false,
        success: false,
        error: true,
        messages: action.messages
    })),
    on(DocumentTypeActions.documentTypeLoading, (state, action) => ({
        ...state,
        loading: action.loading,
        success: false
    })),
    // on(DocumentTypeActions.documentTypeSuccess, (state, action) => ({
    //     ...state,
    //     loading: action.loading,
    //     success: action.success,
    //     messages: action.messages
    // }))
);

export const documentTypeFeature = createFeature({
    name: documentTypeKey,
    reducer: documentTypeReducer
});
