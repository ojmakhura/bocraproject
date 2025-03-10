// Generated by andromda-angular cartridge (app\usecase\selector.store.ts.vsl) DO NOT EDIT
import { createFeatureSelector, createSelector } from '@ngrx/store';
import { licenseeShareholderKey, LicenseeShareholderState } from './licensee-shareholder.state';

export const selectLicenseeShareholderState = createFeatureSelector<LicenseeShareholderState>(licenseeShareholderKey);

export const selectId = createSelector(selectLicenseeShareholderState, (state: LicenseeShareholderState) => state.id);

export const selectLicenseeShareholder = createSelector(
  selectLicenseeShareholderState,
  (state: LicenseeShareholderState) => state.licenseeShareholder
);

export const selectLicenseeShareholders = createSelector(
  selectLicenseeShareholderState,
  (state: LicenseeShareholderState) => state.licenseeShareholders
);

export const selectLicensee = createSelector(
  selectLicenseeShareholderState,
  (state: LicenseeShareholderState) => state.licensee
);

export const selectRemoved = createSelector(
  selectLicenseeShareholderState,
  (state: LicenseeShareholderState) => state.removed
);

export const selectShareholder = createSelector(
  selectLicenseeShareholderState,
  (state: LicenseeShareholderState) => state.shareholder
);

export const selectMessages = createSelector(
  selectLicenseeShareholderState,
  (state: LicenseeShareholderState) => state.messages
);

export const selectSuccess = createSelector(
  selectLicenseeShareholderState,
  (state: LicenseeShareholderState) => state.success
);

export const selectLoading = createSelector(
  selectLicenseeShareholderState,
  (state: LicenseeShareholderState) => state.loading
);

export const selectLoaderMessage = createSelector(
  selectLicenseeShareholderState,
  (state: LicenseeShareholderState) => state.loaderMessage
);

export const selectError = createSelector(
  selectLicenseeShareholderState,
  (state: LicenseeShareholderState) => state.error
);
