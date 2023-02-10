// Generated by andromda-angular cartridge (app\usecase\reducer.store.ts.vsl) DO NOT EDIT
import { Action, createFeature, createReducer, on } from '@ngrx/store';
import * as PeriodActions from './period.actions';
import { periodKey, initialState } from './period.state';

export const periodReducer = createReducer(
  initialState,
  on(PeriodActions.findById, (state, action) => ({
    ...state,
    loading: action.loading,
    loaderMessage: action.loaderMessage,
  })),
  on(PeriodActions.findByIdSuccess, (state, action) => ({
    ...state,
    period: action.period,
    loading: false,
    success: action.success,
    error: false,
    messages: action.messages,
  })),
  on(PeriodActions.save, (state, action) => ({
    ...state,
    loading: action.loading,
    loaderMessage: action.loaderMessage,
  })),
  on(PeriodActions.saveSuccess, (state, action) => ({
    ...state,
    period: action.period,
    loading: false,
    success: action.success,
    error: false,
    messages: action.messages,
  })),
  on(PeriodActions.remove, (state, action) => ({
    ...state,
    loading: action.loading,
    loaderMessage: action.loaderMessage,
  })),
  on(PeriodActions.removeSuccess, (state, action) => ({
    ...state,
    removed: action.removed,
    loading: false,
    success: action.success,
    error: false,
    messages: action.messages,
  })),
  on(PeriodActions.getAll, (state, action) => ({
    ...state,
    loading: action.loading,
    loaderMessage: action.loaderMessage,
  })),
  on(PeriodActions.getAllSuccess, (state, action) => ({
    ...state,
    periods: action.periods,
    loading: false,
    success: action.success,
    error: false,
    messages: action.messages,
  })),
  on(PeriodActions.search, (state, action) => ({
    ...state,
    loading: action.loading,
    loaderMessage: action.loaderMessage,
  })),
  on(PeriodActions.searchSuccess, (state, action) => ({
    ...state,
    periods: action.periods,
    loading: false,
    success: action.success,
    error: false,
    messages: action.messages,
  })),
  on(PeriodActions.getAllPaged, (state, action) => ({
    ...state,
    loading: action.loading,
    loaderMessage: action.loaderMessage,
  })),
  on(PeriodActions.getAllPagedSuccess, (state, action) => ({
    ...state,
    periods: action.periods,
    loading: false,
    success: action.success,
    error: false,
    messages: action.messages,
  })),
  on(PeriodActions.periodReset, (state) => ({
    ...state,
    periods: [],
    criteria: null,
    period: null,
    id: null,
    removed: false,
    loading: false,
    loaderMessage: undefined,
    success: false,
    error: false,
    messages: [],
  })),
  on(PeriodActions.periodFailure, (state, action) => ({
    ...state,
    loading: false,
    loaderMessage: undefined,
    success: false,
    error: true,
    messages: action.messages,
  }))
);

export const periodFeature = createFeature({
  name: periodKey,
  reducer: periodReducer,
});
