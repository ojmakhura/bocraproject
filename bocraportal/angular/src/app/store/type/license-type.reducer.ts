// Generated by andromda-angular cartridge (app\usecase\reducer.store.ts.vsl) DO NOT EDIT
import { Action, createFeature, createReducer, on } from '@ngrx/store';
import { LicenseTypeVO } from '@app/model/bw/org/bocra/portal/type/license-type-vo';
import { LicenseTypeCriteria } from '@app/model/bw/org/bocra/portal/type/license-type-criteria';
import * as LicenseTypeActions from './license-type.action';

export const licenseTypeKey = "licenseType";

export interface LicenseTypeState {
    licenseType: LicenseTypeVO;
    licenseTypes: LicenseTypeVO[];
    searchCriteria: LicenseTypeCriteria
    loading: boolean;
    id: number;
    error: any
}

export const initialState: LicenseTypeState = {
    licenseType: new LicenseTypeVO,
    licenseTypes: [],
    searchCriteria: new LicenseTypeCriteria,
    loading: false,
    id: 0,
    error: null
};

export const licenseTypeReducer = createReducer(
    initialState,
    on(LicenseTypeActions.saveLicenseType, (state, action) => ({
        ...state,
        licenseType: action.licenseType
    })),
    on(LicenseTypeActions.saveLicenseTypeSuccess, (state, action) => ({
        ...state,
        licenseType: action.licenseType,
        licenseTypes: [...state.licenseTypes, action.licenseType]
    })),
    on(LicenseTypeActions.findById, (state, action) => ({
        ...state,
        id: action.id
    })),
    on(LicenseTypeActions.findByIdSuccess, (state, action) => ({
        ...state,
        licenseType: action.licenseType,
        licenseTypes: [...state.licenseTypes, action.licenseType]
    })),
    on(LicenseTypeActions.loadAll, (state, action) => ({
        ...state
    })),
    on(LicenseTypeActions.loadAllSuccess, (state, action) => ({
        ...state,
        licenseType: new LicenseTypeVO,
        licenseTypes: action.licenseTypes
    })),
    on(LicenseTypeActions.searchLicenseTypes, (state, action) => ({
        ...state,
        searchCriteria: action.searchCriteria
    })),
    on(LicenseTypeActions.searchLicenseTypesSuccess, (state, action) => ({
        ...state,
        licenseType: new LicenseTypeVO,
        searchCriteria: new LicenseTypeCriteria,
        licenseTypes: action.licenseTypes
    })),
    on(LicenseTypeActions.reset, (state) => ({
      ...state,
      licenseType: new LicenseTypeVO(),
      searchCriteria: new LicenseTypeCriteria(),
      licenseTypes: [],
    })),
    on(LicenseTypeActions.licenseTypeActionFailure, (state, action) => ({
        ...state,
        error: action.error
    }))
);

export const licenseTypeFeature = createFeature(
    {
        name: licenseTypeKey,
        reducer: licenseTypeReducer
    }
);