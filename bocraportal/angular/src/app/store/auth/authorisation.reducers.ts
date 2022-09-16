// Generated by andromda-angular cartridge (app\usecase\reducer.store.ts.vsl) DO NOT EDIT
import { Action, createFeature, createReducer, on } from '@ngrx/store';
import * as AuthorisationActions from './authorisation.actions';
import {authorisationKey, initialState} from './authorisation.state';

export const authorisationReducer = createReducer(
    initialState,
    on(AuthorisationActions.findById, (state, action) => ({
        ...state,
        loading: action.loading,
        loaderMessage: action.loaderMesage,
    })),
    on(AuthorisationActions.findByIdSuccess, (state, action) => ({
        ...state,
        authorisation: action.authorisation, 
        success: action.success,
        loading: false,
        loaderMessage: undefined,
        error: false, 
        messages: action.messages
    })),
    on(AuthorisationActions.save, (state, action) => ({
        ...state,
        loading: action.loading,
        loaderMessage: action.loaderMesage,
    })),
    on(AuthorisationActions.saveSuccess, (state, action) => ({
        ...state,
        loading: false, 
        loaderMessage: undefined,
        authorisation: action.authorisation, 
        success: action.success,
        error: false,
        messages: action.messages
    })),
    on(AuthorisationActions.remove, (state, action) => ({
        ...state,
        loading: action.loading,
        loaderMessage: action.loaderMesage,
    })),
    on(AuthorisationActions.removeSuccess, (state, action) => ({
        ...state,
        removed: action.removed, 
        loading: false, 
        loaderMessage: undefined,
        success: action.success, 
        error: false,
        messages: action.messages
    })),
    on(AuthorisationActions.getAll, (state, action) => ({
        ...state,
        loading: action.loading,
        loaderMessage: action.loaderMesage,
    })),
    on(AuthorisationActions.getAllSuccess, (state, action) => ({
        ...state,
        authorisations: action.authorisations, 
        loading: false, 
        loaderMessage: undefined,
        success: action.success, 
        error: false,
        messages: action.messages
    })),
    on(AuthorisationActions.search, (state, action) => ({
        ...state,
        loading: action.loading,
        loaderMessage: action.loaderMesage,
    })),
    on(AuthorisationActions.searchSuccess, (state, action) => ({
        ...state,
        authorisations: action.authorisations, 
        loading: false, 
        loaderMessage: undefined,
        success: action.success, 
        error: false,
        messages: action.messages
    })),
    on(AuthorisationActions.getAllPaged, (state, action) => ({
        ...state,
        loading: action.loading,
        loaderMessage: action.loaderMesage,
    })),
    on(AuthorisationActions.getAllPagedSuccess, (state, action) => ({
        ...state,
        authorisations: action.authorisations, 
        loading: false, 
        loaderMessage: undefined,
        success: action.success, 
        error: false,
        messages: action.messages
    })),
    on(AuthorisationActions.authorisationReset, (state) => ({
      ...state,
        criteria: null, 
        authorisations: [], 
        authorisation: null, 
        id: null, 
        loading: false,
        loaderMessage: undefined,
        success: false,
        error: false,
        messages: []
    })),
    on(AuthorisationActions.authorisationFailure, (state, action) => ({
        ...state,
        loading: false,
        loaderMessage: undefined,
        success: false,
        error: true,
        messages: action.messages
    })),
    on(AuthorisationActions.authorisationLoading, (state, action) => ({
        ...state,
        loading: action.loading,
        success: false
    })),
    // on(AuthorisationActions.authorisationSuccess, (state, action) => ({
    //     ...state,
    //     loading: action.loading,
    //     success: action.success,
    //     messages: action.messages
    // }))
);

export const authorisationFeature = createFeature({
    name: authorisationKey,
    reducer: authorisationReducer
});
