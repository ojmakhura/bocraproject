// Generated by andromda-angular cartridge (app\usecase\selector.store.ts.vsl) DO NOT EDIT
import { createFeatureSelector, createSelector } from '@ngrx/store';
import { viewKey, ViewState } from './view.state';

export const selectViewState = createFeatureSelector<ViewState>(viewKey);

export const selectViewName = createSelector(selectViewState, (state: ViewState) => state.viewName);

export const selectUnauthorisedUrls = createSelector(selectViewState, (state: ViewState) => state.unauthorisedUrls);

export const selectMessages = createSelector(selectViewState, (state: ViewState) => state.messages);

export const selectSuccess = createSelector(selectViewState, (state: ViewState) => state.success);

export const selectLoading = createSelector(selectViewState, (state: ViewState) => state.loading);

export const selectError = createSelector(selectViewState, (state: ViewState) => state.error);
