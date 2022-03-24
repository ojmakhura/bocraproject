// Generated by andromda-angular cartridge (app\usecase\reducer.store.ts.vsl) DO NOT EDIT
import { Action, createFeature, createReducer, on } from '@ngrx/store';

export const periodConfigKey = "periodConfig";

export interface PeriodConfigState {
    error: any
}

export const initialState: PeriodConfigState = {
    error: null
};

export const periodConfigReducer = createReducer(
    initialState,
);

export const periodConfigFeature = createFeature(
    {
        name: periodConfigKey,
        reducer: periodConfigReducer
    }
);
