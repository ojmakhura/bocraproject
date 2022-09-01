// Generated by andromda-angular cartridge (app\usecase\reducer.store.ts.vsl) DO NOT EDIT
import { Action, createFeature, createReducer, on } from '@ngrx/store';
import * as LicenseeFormActions from './licensee-form.actions';
import { licenseeFormKey, initialState } from './licensee-form.state';

export const licenseeFormReducer = createReducer(
  initialState,
  on(LicenseeFormActions.findByIdSuccess, (state, action) => ({
    ...state,
    licenseeForm: action.licenseeForm,
    loading: false,
    success: action.success,
    error: false,
    messages: action.messages,
  })),
  on(LicenseeFormActions.findByLicenseeSuccess, (state, action) => ({
    ...state,
    licenseeForms: action.licenseeForms,
    loading: false,
    success: action.success,
    error: false,
    messages: action.messages,
  })),
  on(LicenseeFormActions.findByFormSuccess, (state, action) => ({
    ...state,
    licenseeForms: action.licenseeForms,
    loading: false,
    success: action.success,
    error: false,
    messages: action.messages,
  })),
  on(LicenseeFormActions.createSuccess, (state, action) => ({
    ...state,
    licenseeForm: action.licenseeForm,
    loading: false,
    success: action.success,
    error: false,
    messages: action.messages,
  })),
  on(LicenseeFormActions.updateSuccess, (state, action) => ({
    ...state,
    licenseeForm: action.licenseeForm,
    loading: false,
    success: action.success,
    error: false,
    messages: action.messages,
  })),
  on(LicenseeFormActions.removeSuccess, (state, action) => ({
    ...state,
    removed: action.removed,
    loading: false,
    success: action.success,
    error: false,
    messages: action.messages,
  })),
  on(LicenseeFormActions.getAllSuccess, (state, action) => ({
    ...state,
    licenseeForms: action.licenseeForms,
    loading: false,
    success: action.success,
    error: false,
    messages: action.messages,
  })),
  on(LicenseeFormActions.licenseeFormReset, (state) => ({
    ...state,
    id: null,
    licenseeForms: [],
    licenseeForm: null,
    licensee: undefined,
    form: undefined,
    removed: false,
    success: false,
    loading: false,
    error: false,
    messages: [],
  })),
  on(LicenseeFormActions.licenseeFormFailure, (state, action) => ({
    ...state,
    messages: action.messages,
    error: true,
    loading: false,
    success: false,
  }))
);

export const licenseeFormFeature = createFeature({
  name: licenseeFormKey,
  reducer: licenseeFormReducer,
});
