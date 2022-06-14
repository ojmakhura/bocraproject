// Generated by andromda-angular cartridge (app\usecase\selector.store.ts.vsl) DO NOT EDIT
import { createFeatureSelector, createSelector } from '@ngrx/store';
import { useCaseKey, UseCaseScopeState } from './use-case.reducers';

export const selectUseCaseScopeState = createFeatureSelector<UseCaseScopeState>(useCaseKey);

export const selectPageVariables = createSelector(selectUseCaseScopeState, (state: UseCaseScopeState) => state.pageVariables);

export const selectUseCaseInDialog = createSelector(selectUseCaseScopeState, (state: UseCaseScopeState) => state.useCaseInDialog);

export const selectErrors = createSelector(selectUseCaseScopeState, (state: UseCaseScopeState) => state.errors);

export const selectUseCaseParameters = createSelector(selectUseCaseScopeState, (state: UseCaseScopeState) => state.useCaseParameters);

export const selectReturnValues = createSelector(selectUseCaseScopeState, (state: UseCaseScopeState) => state.useCaseReturnValues);
