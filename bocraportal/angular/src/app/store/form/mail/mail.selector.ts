// Generated by andromda-angular cartridge (app\usecase\selector.store.ts.vsl) DO NOT EDIT
import { createFeatureSelector, createSelector } from '@ngrx/store';
import { mailKey, MailState } from './mail.reducer';

export const selectMailState = createFeatureSelector<MailState> (
    mailKey
);
