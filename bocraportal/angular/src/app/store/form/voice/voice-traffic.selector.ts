// Generated by andromda-angular cartridge (app\usecase\selector.store.ts.vsl) DO NOT EDIT
import { createFeatureSelector, createSelector } from '@ngrx/store';
import { voiceTrafficKey, VoiceTrafficState } from './voiceTraffic.reducer';

export const selectVoiceTrafficState = createFeatureSelector<VoiceTrafficState> (
    voiceTrafficKey
);
