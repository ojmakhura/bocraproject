// Generated by andromda-angular cartridge (app\usecase\reducer.store.ts.vsl) DO NOT EDIT
import { Action, createFeature, createReducer, on } from '@ngrx/store';
import * as UserActions from './user.actions';
import {userKey, initialState} from './user.state';

export const userReducer = createReducer(
    initialState,
    on(UserActions.createUserSuccess, (state, action) => ({
        ...state,
        userId: null, 
        user: action.user, 
        users: [], 
        criteria: null, 
        loading: false,
        success: action.success,
        messages: action.messages
    })),
    on(UserActions.updateUserNameSuccess, (state, action) => ({
        ...state,
        userId: null, 
        user: null, 
        users: [], 
        criteria: null, 
        loading: false,
        success: action.success,
        messages: action.messages
    })),
    on(UserActions.loadUsersSuccess, (state, action) => ({
        ...state,
        userId: null, 
        user: null, 
        users: action.users, 
        criteria: null, 
        loading: false,
        success: action.success,
        messages: action.messages
    })),
    // on(UserActions.findByIdSuccess, (state, action) => ({
    //     ...state,
    //     userId: null, 
    //     user: null, 
    //     users: [], 
    //     criteria: null, 
    //     loading: false,
    //     success: action.success,
    //     messages: action.messages
    // })),
    // on(UserActions.saveSuccess, (state, action) => ({
    //     ...state,
    //     userId: null, 
    //     user: null, 
    //     users: [], 
    //     criteria: null, 
    //     loading: false,
    //     success: action.success,
    //     messages: action.messages
    // })),
    // on(UserActions.removeSuccess, (state, action) => ({
    //     ...state,
    //     userId: null, 
    //     user: null, 
    //     users: [], 
    //     criteria: null, 
    //     loading: false,
    //     success: action.success,
    //     messages: action.messages
    // })),
    // on(UserActions.getAllSuccess, (state, action) => ({
    //     ...state,
    //     userId: null, 
    //     user: null, 
    //     users: [], 
    //     criteria: null, 
    //     loading: false,
    //     success: action.success,
    //     messages: action.messages
    // })),
    on(UserActions.searchSuccess, (state, action) => ({
        ...state,
        userId: null, 
        user: null, 
        users: action.users, 
        criteria: null, 
        loading: false,
        success: action.success,
        messages: action.messages
    })),
    // on(UserActions.getAllPagedSuccess, (state, action) => ({
    //     ...state,
    //     userId: null, 
    //     user: null, 
    //     users: [], 
    //     criteria: null, 
    //     loading: false,
    //     success: action.success,
    //     messages: action.messages
    // })),
    on(UserActions.userReset, (state) => ({
      ...state,
        userId: null, 
        user: null, 
        users: [], 
        criteria: null, 
        loading: false,
        success: false,
        error: false,
        messages: []
    })),
    on(UserActions.userFailure, (state, action) => ({
        ...state,
        loading: false,
        success: false,
        error: true,
        messages: action.messages
    })),
    on(UserActions.userLoading, (state, action) => ({
        ...state,
        loading: action.loading,
        success: false
    }))
);

export const userFeature = createFeature({
    name: userKey,
    reducer: userReducer
});
