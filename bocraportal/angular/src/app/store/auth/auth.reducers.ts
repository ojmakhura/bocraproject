import { Action, createFeature, createReducer, on } from '@ngrx/store';
import * as AuthActions from './auth.actions';
import { authKey, initialState } from './auth.state';

export const authReducer = createReducer(
  initialState,

  on(AuthActions.setId, (state, action) => ({
    ...state,
    id: action.id,
    messages: [],
  })),
  on(AuthActions.setUsername, (state, action) => ({
    ...state,
    username: action.username,
    messages: [],
  })),
  on(AuthActions.setEmail, (state, action) => ({
    ...state,
    email: action.email,
    messages: [],
  })),
  on(AuthActions.setLastName, (state, action) => ({
    ...state,
    lastName: action.lastName,
    messages: [],
  })),
  on(AuthActions.setFirstName, (state, action) => ({
    ...state,
    firstName: action.firstName,
    messages: [],
  })),
  on(AuthActions.authReset, (state) => ({
    ...state,
    id: null,
    menus: [],
    roles: [],
    username: null,
    email: null,
    lastName: null,
    firstName: null,
    loggedIn: false,
    messages: [],
  })),
  on(AuthActions.authFailure, (state, action) => ({
    ...state,
    messages: action.messages,
  }))
);

export const authFeature = createFeature({
  name: authKey,
  reducer: authReducer,
});
