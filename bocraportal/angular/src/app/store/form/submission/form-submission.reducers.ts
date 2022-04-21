// Generated by andromda-angular cartridge (app\usecase\reducer.store.ts.vsl) DO NOT EDIT
import { Action, createFeature, createReducer, on } from '@ngrx/store';
import * as FormSubmissionActions from './form-submission.actions';
import {formSubmissionKey, initialState} from './form-submission.state';

export const formSubmissionReducer = createReducer(
    initialState,
    on(FormSubmissionActions.formSubmissionReset, (state) => ({
      ...state,
        formFieldVO: null, 
        searchCriteria: null, 
        formSubmissionVO: null, 
        submissions: [], 
        id: null, 
        error: null
    })),
    on(FormSubmissionActions.formSubmissionFailure, (state, action) => ({
        ...state,
        error: action.error
    }))
);

export const formSubmissionFeature = createFeature({
    name: formSubmissionKey,
    reducer: formSubmissionReducer
});
