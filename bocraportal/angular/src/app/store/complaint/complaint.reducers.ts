// Generated by andromda-angular cartridge (app\usecase\reducer.store.ts.vsl) DO NOT EDIT
import { Action, createFeature, createReducer, on } from '@ngrx/store';
import * as ComplaintActions from './complaint.actions';
import {complaintKey, initialState} from './complaint.state';

export const complaintReducer = createReducer(
    initialState,
    on(ComplaintActions.findByIdSuccess, (state, action) => ({
        ...state,
        complaint: action.complaint, 
        criteria: null, 
        complaints: [], 
        id: null, 
        loading: false,
        success: action.success,
        messages: action.messages
    })),
    on(ComplaintActions.saveSuccess, (state, action) => ({
        ...state,
        complaint: action.complaint, 
        criteria: null, 
        complaints: [], 
        id: null, 
        loading: false,
        success: action.success,
        messages: action.messages
    })),
    on(ComplaintActions.removeSuccess, (state, action) => ({
        ...state,
        complaint: null, 
        criteria: null, 
        complaints: [], 
        id: null, 
        loading: action.removed,
        success: action.success,
        messages: action.messages
    })),
    on(ComplaintActions.getAllSuccess, (state, action) => ({
        ...state,
        complaint: null, 
        criteria: null, 
        complaints: action.complaints, 
        id: null, 
        loading: false,
        success: action.success,
        messages: action.messages
    })),
    on(ComplaintActions.searchSuccess, (state, action) => ({
        ...state,
        complaint: null, 
        criteria: null, 
        complaints: action.complaints, 
        id: null, 
        loading: false,
        success: action.success,
        messages: action.messages
    })),
    on(ComplaintActions.getAllPagedSuccess, (state, action) => ({
        ...state,
        complaint: null, 
        criteria: null, 
        complaints: action.complaints, 
        id: null, 
        loading: false,
        success: action.success,
        messages: action.messages
    })),
    on(ComplaintActions.complaintReset, (state) => ({
      ...state,
        complaint: null, 
        criteria: null, 
        complaints: [], 
        id: null, 
        removed: false,
        loading: false,
        success: false,
        error: false,
        messges: []
    })),
    on(ComplaintActions.complaintFailure, (state, action) => ({
        ...state,
        loading: false,
        success: false,
        error: true,
        messages: action.messages
    })),
    on(ComplaintActions.complaintLoading, (state, action) => ({
        ...state,
        loading: action.loading,
        success: false
    }))
);

export const complaintFeature = createFeature({
    name: complaintKey,
    reducer: complaintReducer
});
