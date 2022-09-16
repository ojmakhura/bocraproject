// Generated by andromda-angular cartridge (app\usecase\reducer.store.ts.vsl) DO NOT EDIT
import { Action, createFeature, createReducer, on } from '@ngrx/store';
import * as PeriodConfigActions from './period-config.actions';
import {periodConfigKey, initialState} from './period-config.state';

export const periodConfigReducer = createReducer(
    initialState,
    on(PeriodConfigActions.findById, (state, action) => ({
        ...state,
        loading: action.loading,
        loaderMessage: action.loaderMesage,
    })),
    on(PeriodConfigActions.findByIdSuccess, (state, action) => ({
        ...state,
        periodConfig: action.periodConfig, 
        loading: false,
        loaderMessage: undefined,
        success: action.success,
        error: false,
        messages: action.messages
    })),
    on(PeriodConfigActions.save, (state, action) => ({
        ...state,
        loading: action.loading,
        loaderMessage: action.loaderMesage,
    })),
    on(PeriodConfigActions.saveSuccess, (state, action) => ({
        ...state,
        periodConfig: action.periodConfig, 
        loading: false,
        loaderMessage: undefined,
        success: action.success,
        error: false,
        messages: action.messages
    })),
    on(PeriodConfigActions.remove, (state, action) => ({
        ...state,
        loading: action.loading,
        loaderMessage: action.loaderMesage,
    })),
    on(PeriodConfigActions.removeSuccess, (state, action) => ({
        ...state,
        removed: action.removed,
        loading: false,
        loaderMessage: undefined,
        success: action.success,
        error: false,
        messages: action.messages
    })),
    on(PeriodConfigActions.getAll, (state, action) => ({
        ...state,
        loading: action.loading,
        loaderMessage: action.loaderMesage,
    })),
    on(PeriodConfigActions.getAllSuccess, (state, action) => ({
        ...state,
        periodConfigs: action.periodConfigs, 
        loading: false,
        loaderMessage: undefined,
        success: action.success,
        error: false,
        messages: action.messages
    })),
    on(PeriodConfigActions.search, (state, action) => ({
        ...state,
        loading: action.loading,
        loaderMessage: action.loaderMesage,
    })),
    on(PeriodConfigActions.searchSuccess, (state, action) => ({
        ...state,
        periodConfigs: action.periodConfigs, 
        loading: false,
        loaderMessage: undefined,
        success: action.success,
        error: false,
        messages: action.messages
    })),
    on(PeriodConfigActions.getAllPaged, (state, action) => ({
        ...state,
        loading: action.loading,
        loaderMessage: action.loaderMesage,
    })),
    on(PeriodConfigActions.getAllPagedSuccess, (state, action) => ({
        ...state,
        periodConfigs: action.periodConfigs,
        loading: false,
        loaderMessage: undefined,
        success: action.success,
        error: false,
        messages: action.messages
    })),
    on(PeriodConfigActions.periodConfigReset, (state) => ({
      ...state,
        periodConfig: null, 
        criteria: null, 
        id: null, 
        periodConfigs: [], 
        loading: false,
        loaderMessage: undefined,
        success: false,
        removed: false,
        error: false,
        messages: []
    })),
    on(PeriodConfigActions.periodConfigFailure, (state, action) => ({
        ...state,
        loading: false,
        loaderMessage: undefined,
        success: false,
        error: true,
        messages: action.messages
    }))
);

export const periodConfigFeature = createFeature({
    name: periodConfigKey,
    reducer: periodConfigReducer
});
