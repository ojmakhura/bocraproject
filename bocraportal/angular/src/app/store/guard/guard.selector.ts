// Generated by andromda-angular cartridge (app\usecase\selector.store.ts.vsl) DO NOT EDIT
import { createFeatureSelector, createSelector } from '@ngrx/store';
import { guardKey, GuardState } from './guard.reducer';

export const selectGuardState = createFeatureSelector<GuardState>(guardKey);

export const selectGuard = createSelector(
    selectGuardState,
      (state: GuardState) => state.guard
);

export const selectGuards = createSelector(
    selectGuardState,
      (state: GuardState) => state.guards
);

export const selectError = createSelector(
    selectGuardState,
      (state: GuardState) => state.error
);

export const selectId = createSelector(
    selectGuardState,
      (state: GuardState) => state.id
);

export const selectLoading = createSelector(
    selectGuardState,
      (state: GuardState) => state.loading
);

export const selectCriteria = createSelector(
    selectGuardState,
      (state: GuardState) => state.searchCriteria
);
