// Generated by andromda-angular cartridge (app\usecase\reducer.store.ts.vsl) DO NOT EDIT
import { Action, createFeature, createReducer, on } from '@ngrx/store';
import * as LicenseeFormActions from './licensee-form.actions';
import { licenseeFormKey, initialState } from './licensee-form.state';

export const licenseeFormReducer = createReducer(
  initialState,
  on(LicenseeFormActions.findById, (state, action) => ({
    ...state,
    loading: action.loading,
    loaderMessage: action.loaderMesage,
  })),
  on(LicenseeFormActions.findByIdSuccess, (state, action) => ({
    ...state,
    licenseeForm: action.licenseeForm,
    loading: false,
    loaderMessage: undefined,
    success: action.success,
    error: false,
    messages: action.messages,
  })),
  on(LicenseeFormActions.findByLicensee, (state, action) => ({
    ...state,
    loading: action.loading,
    loaderMessage: action.loaderMesage,
  })),
  on(LicenseeFormActions.findByLicenseeSuccess, (state, action) => ({
    ...state,
    licenseeForms: action.licenseeForms,
    loading: false,
    loaderMessage: undefined,
    success: action.success,
    error: false,
    messages: action.messages,
  })),
  on(LicenseeFormActions.findByForm, (state, action) => ({
    ...state,
    loading: action.loading,
    loaderMessage: action.loaderMesage,
  })),
  on(LicenseeFormActions.findByFormSuccess, (state, action) => ({
    ...state,
    licenseeForms: action.licenseeForms,
    loading: false,
    loaderMessage: undefined,
    success: action.success,
    error: false,
    messages: action.messages,
  })),
  on(LicenseeFormActions.create, (state, action) => ({
    ...state,
    loading: action.loading,
    loaderMessage: action.loaderMesage,
  })),
  on(LicenseeFormActions.createSuccess, (state, action) => ({
    ...state,
    licenseeForm: action.licenseeForm,
    loading: false,
    loaderMessage: undefined,
    success: action.success,
    error: false,
    messages: action.messages,
  })),
  on(LicenseeFormActions.updateLicensee, (state, action) => ({
    ...state,
    loading: action.loading,
    loaderMessage: action.loaderMesage,
  })),
  on(LicenseeFormActions.updateForm, (state, action) => ({
    ...state,
    loading: action.loading,
    loaderMessage: action.loaderMesage,
  })),
  on(LicenseeFormActions.updateSuccess, (state, action) => ({
    ...state,
    licenseeForm: action.licenseeForm,
    loading: false,
    loaderMessage: undefined,
    success: action.success,
    error: false,
    messages: action.messages,
  })),
  on(LicenseeFormActions.remove, (state, action) => ({
    ...state,
    loading: action.loading,
    loaderMessage: action.loaderMesage,
  })),
  on(LicenseeFormActions.removeSuccess, (state, action) => ({
    ...state,
    removed: action.removed,
    loading: false,
    loaderMessage: undefined,
    success: action.success,
    error: false,
    messages: action.messages,
  })),
  on(LicenseeFormActions.getAll, (state, action) => ({
    ...state,
    loading: action.loading,
    loaderMessage: action.loaderMesage,
  })),
  on(LicenseeFormActions.getAllSuccess, (state, action) => ({
    ...state,
    licenseeForms: action.licenseeForms,
    loading: false,
    loaderMessage: undefined,
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
    loaderMessage: undefined,
    error: false,
    messages: [],
  })),
  on(LicenseeFormActions.licenseeFormFailure, (state, action) => ({
    ...state,
    messages: action.messages,
    error: true,
    loading: false,
    loaderMessage: undefined,
    success: false,
  }))
);

export const licenseeFormFeature = createFeature({
  name: licenseeFormKey,
  reducer: licenseeFormReducer,
});
