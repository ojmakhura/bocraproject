// Generated by andromda-angular cartridge (app\usecase\selector.store.ts.vsl) DO NOT EDIT
import { createFeatureSelector, createSelector } from '@ngrx/store';
import { licenseeKey, LicenseeState } from './licensee.state';

export const selectLicenseeState = createFeatureSelector<LicenseeState>(licenseeKey);

export const selectCriteria = createSelector(selectLicenseeState, (state: LicenseeState) => state.criteria);

export const selectId = createSelector(selectLicenseeState, (state: LicenseeState) => state.id);

export const selectLicensee = createSelector(selectLicenseeState, (state: LicenseeState) => state.licensee);

export const selectShareholder = createSelector(selectLicenseeState, (state: LicenseeState) => state.shareholder);

export const selectLicensees = createSelector(selectLicenseeState, (state: LicenseeState) => state.licensees);

export const selectSector = createSelector(selectLicenseeState, (state: LicenseeState) => state.sector);

export const selectSectors = createSelector(selectLicenseeState, (state: LicenseeState) => state.sectors);

export const selectMessages = createSelector(selectLicenseeState, (state: LicenseeState) => state.messages);

export const selectSuccess = createSelector(selectLicenseeState, (state: LicenseeState) => state.success);

export const selectLoading = createSelector(selectLicenseeState, (state: LicenseeState) => state.loading);

export const selectLoaderMessage = createSelector(selectLicenseeState, (state: LicenseeState) => state.loaderMessage);

export const selectDocument = createSelector(selectLicenseeState, (state: LicenseeState) => state.document);

export const selectError = createSelector(selectLicenseeState, (state: LicenseeState) => state.error);
