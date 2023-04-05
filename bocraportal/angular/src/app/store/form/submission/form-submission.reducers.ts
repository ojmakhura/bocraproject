// Generated by andromda-angular cartridge (app\usecase\reducer.store.ts.vsl) DO NOT EDIT
import { Action, createFeature, createReducer, on } from '@ngrx/store';
import * as FormSubmissionActions from './form-submission.actions';
import { formSubmissionKey, initialState } from './form-submission.state';

export const formSubmissionReducer = createReducer(
  initialState,
  on(FormSubmissionActions.findById, (state, action) => ({
    ...state,
    loading: action.loading,
    loaderMessage: action.loaderMessage,
  })),
  on(FormSubmissionActions.findByIdSuccess, (state, action) => ({
    ...state,
    formSubmission: action.formSubmission,
    loading: false,
    loaderMessage: undefined,
    success: action.success,
    error: false,
    messages: action.messages,
  })),
  on(FormSubmissionActions.findByIds, (state, action) => ({
    ...state,
    loading: action.loading,
    loaderMessage: action.loaderMessage,
  })),
  on(FormSubmissionActions.findByIdsSuccess, (state, action) => ({
    ...state,
    formSubmissions: action.formSubmissions,
    loading: false,
    loaderMessage: undefined,
    success: action.success,
    error: false,
    messages: action.messages,
  })),
  on(FormSubmissionActions.save, (state, action) => ({
    ...state,
    loading: action.loading,
    loaderMessage: action.loaderMessage,
  })),
  on(FormSubmissionActions.saveSuccess, (state, action) => ({
    ...state,
    formSubmission: action.formSubmission,
    loading: false,
    loaderMessage: undefined,
    success: action.success,
    error: false,
    messages: action.messages,
  })),
  on(FormSubmissionActions.uploadData, (state, action) => ({
    ...state,
    loading: action.loading,
    loaderMessage: action.loaderMessage,
  })),
  on(FormSubmissionActions.uploadDataSuccess, (state, action) => ({
    ...state,
    formSubmission: action.formSubmission,
    loading: false,
    loaderMessage: undefined,
    success: action.success,
    error: false,
    messages: action.messages,
  })),
  on(FormSubmissionActions.updateStatus, (state, action) => ({
    ...state,
    loading: action.loading,
    loaderMessage: action.loaderMessage,
  })),
  on(FormSubmissionActions.updateStatusSuccess, (state, action) => ({
    ...state,
    statusUpdated: action.statusUpdated,
    loading: false,
    loaderMessage: undefined,
    success: action.success,
    error: false,
    messages: action.messages,
  })),
  on(FormSubmissionActions.saveNote, (state, action) => ({
    ...state,
    loading: action.loading,
    loaderMessage: action.loaderMessage,
  })),
  on(FormSubmissionActions.saveNoteSuccess, (state, action) => ({
    ...state,
    note: action.note,
    notes: [...state.notes, action.note],
    loading: false,
    loaderMessage: undefined,
    success: action.success,
    error: false,
    messages: action.messages,
  })),
  on(FormSubmissionActions.remove, (state, action) => ({
    ...state,
    loading: action.loading,
    loaderMessage: action.loaderMessage,
  })),
  on(FormSubmissionActions.removeSuccess, (state, action) => ({
    ...state,
    removed: action.removed,
    loading: false,
    loaderMessage: undefined,
    success: action.success,
    error: false,
    messages: action.messages,
  })),
  on(FormSubmissionActions.getAll, (state, action) => ({
    ...state,
    loading: action.loading,
    loaderMessage: action.loaderMessage,
  })),
  on(FormSubmissionActions.getAllSuccess, (state, action) => ({
    ...state,
    formSubmissions: action.formSubmissions,
    loading: false,
    loaderMessage: undefined,
    success: action.success,
    error: false,
    messages: action.messages,
  })),
  on(FormSubmissionActions.search, (state, action) => ({
    ...state,
    loading: action.loading,
    loaderMessage: action.loaderMessage,
  })),
  on(FormSubmissionActions.searchSuccess, (state, action) => ({
    ...state,
    formSubmissions: action.formSubmissions,
    loading: false,
    loaderMessage: undefined,
    success: action.success,
    error: false,
    messages: action.messages,
  })),
  on(FormSubmissionActions.getAllPaged, (state, action) => ({
    ...state,
    loading: action.loading,
    loaderMessage: action.loaderMessage,
  })),
  on(FormSubmissionActions.getAllPagedSuccess, (state, action) => ({
    ...state,
    formSubmissions: action.formSubmissions,
    loading: false,
    loaderMessage: undefined,
    success: action.success,
    error: false,
    messages: action.messages,
  })),
  on(FormSubmissionActions.formSubmissionReset, (state) => ({
    ...state,
    formSubmission: null,
    criteria: null,
    formSubmissions: [],
    id: null,
    loading: false,
    loaderMessage: undefined,
    success: false,
    error: false,
    messages: [],
  })),
  on(FormSubmissionActions.formSubmissionFailure, (state, action) => ({
    ...state,
    loading: false,
    loaderMessage: undefined,
    success: false,
    error: true,
    messages: action.messages,
  })),
  on(FormSubmissionActions.formSubmissionLoading, (state, action) => ({
    ...state,
    loading: action.loading,
    success: false,
  }))
  // on(FormSubmissionActions.formSubmissionSuccess, (state, action) => ({
  //     ...state,
  //     loading: action.loading,
  //     success: action.success,
  //     messages: action.messages
  // }))
);

export const formSubmissionFeature = createFeature({
  name: formSubmissionKey,
  reducer: formSubmissionReducer,
});
