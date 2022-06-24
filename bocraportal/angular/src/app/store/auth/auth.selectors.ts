// Generated by andromda-angular cartridge (app\usecase\selector.store.ts.vsl) DO NOT EDIT
import { createFeatureSelector, createSelector } from '@ngrx/store';
import { authKey, AuthState } from './auth.state';

export const selectAuthState = createFeatureSelector<AuthState>(authKey);

export const selectUsername = createSelector(selectAuthState, (state: AuthState) => state.username);

export const selectEmail = createSelector(selectAuthState, (state: AuthState) => state.email);

export const selectLastName = createSelector(selectAuthState, (state: AuthState) => state.lastName);

export const selectFirstName = createSelector(selectAuthState, (state: AuthState) => state.username);

export const selectId = createSelector(selectAuthState, (state: AuthState) => state.id);

export const selectRoles = createSelector(selectAuthState, (state: AuthState) => state.roles);

export const isLoggedIn = createSelector(selectAuthState, (state: AuthState) => state.loggedIn);

export const selectMessages = createSelector(selectAuthState, (state: AuthState) => state.messages);
