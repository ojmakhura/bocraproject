// Generated by andromda-angular cartridge (app\usecase\selector.store.ts.vsl) DO NOT EDIT
import { createFeatureSelector, createSelector } from '@ngrx/store';
import { licenseTypeKey, LicenseTypeState } from './license-type.reducer';

export const selectLicenseTypeState = createFeatureSelector<LicenseTypeState> (
    licenseTypeKey
);

export const selectLicenseType = createSelector(
    selectLicenseTypeState,
      (state: LicenseTypeState) => state.licenseType
);

export const selectLicenseTypes = createSelector(
    selectLicenseTypeState,
      (state: LicenseTypeState) => state.licenseTypes
);

export const selectError = createSelector(
    selectLicenseTypeState,
      (state: LicenseTypeState) => state.error
);

export const selectId = createSelector(
    selectLicenseTypeState,
      (state: LicenseTypeState) => state.id
);

export const selectLoading = createSelector(
    selectLicenseTypeState,
      (state: LicenseTypeState) => state.loading
);

export const selectCriteria = createSelector(
    selectLicenseTypeState,
      (state: LicenseTypeState) => state.searchCriteria
);

