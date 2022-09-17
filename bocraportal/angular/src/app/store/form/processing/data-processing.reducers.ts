// Generated by andromda-angular cartridge (app\usecase\reducer.store.ts.vsl) DO NOT EDIT
import { Action, createFeature, createReducer, on } from '@ngrx/store';
import * as DataProcessingActions from './data-processing.actions';
import {dataProcessingKey, initialState} from './data-processing.state';

export const dataProcessingReducer = createReducer(
    initialState,
    on(DataProcessingActions.dataCaptureSummary, (state, action) => ({
        ...state,
        loading: action.loading,
        loaderMessage: action.loaderMessage,
    })),
    on(DataProcessingActions.dataCaptureSummarySuccess, (state, action) => ({
        ...state,
        formSubmissions: [], 
        submissionSummary: action.submissionSummary,
        loading: false,
        loaderMessage: undefined,
        success: action.success,
        error: false,
        messages: action.messages
    })),
    on(DataProcessingActions.loadData, (state, action) => ({
        ...state,
        loading: action.loading,
        loaderMessage: action.loaderMessage,
    })),
    on(DataProcessingActions.loadDataSuccess, (state, action) => ({
        ...state,
        formSubmissions: action.formSubmissions, 
        submissionSummary: state.submissionSummary,
        loading: false,
        loaderMessage: undefined,
        success: action.success,
        error: false,
        messages: action.messages
    })),
    on(DataProcessingActions.dataProcessingReset, (state) => ({
      ...state,
      formSubmissions: [], 
        loading: false,
        loaderMessage: undefined,
        success: false,
        error: false,
        messges: []
    })),
    on(DataProcessingActions.dataProcessingFailure, (state, action) => ({
        ...state,
        loading: false,
        loaderMessage: undefined,
        success: false,
        error: true,
        messages: action.messages
    })),
    on(DataProcessingActions.dataProcessingLoading, (state, action) => ({
        ...state,
        loading: action.loading,
        success: false
    }))
);

export const dataProcessingFeature = createFeature({
    name: dataProcessingKey,
    reducer: dataProcessingReducer
});
