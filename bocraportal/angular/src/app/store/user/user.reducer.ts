// Generated by andromda-angular cartridge (app\usecase\reducer.store.ts.vsl) DO NOT EDIT
import { Action, createFeature, createReducer, on } from '@ngrx/store';
import * as UserActions from './user.action';
import {userKey, initialState} from './user.state';

export const userReducer = createReducer(
    initialState,
    on(UserActions.createUserSuccess, (state, action) => ({
        ...state,
        searchCriteria: null, 
        id: null, 
        userId: null, 
        userVO: null, 
        users: [], 
        error: action.error
    })),
    on(UserActions.updateUserNameSuccess, (state, action) => ({
        ...state,
        searchCriteria: null, 
        id: null, 
        userId: null, 
        userVO: null, 
        users: [], 
        error: action.error
    })),
    on(UserActions.loadUsersSuccess, (state, action) => ({
        ...state,
        searchCriteria: null, 
        id: null, 
        userId: null, 
        userVO: null, 
        users: [], 
        error: action.error
    })),
    on(UserActions.userReset, (state) => ({
      ...state,
        searchCriteria: null, 
        id: null, 
        userId: null, 
        userVO: null, 
        users: [], 
        error: null
    })),
    on(UserActions.userFailure, (state, action) => ({
        ...state,
        error: action.error
    }))
);

export const userFeature = createFeature({
    name: userKey,
    reducer: userReducer
});
