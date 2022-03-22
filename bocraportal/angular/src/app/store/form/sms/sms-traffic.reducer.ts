// Generated by andromda-angular cartridge (app\usecase\reducer.store.ts.vsl) DO NOT EDIT
import { Action, createFeature, createReducer, on } from '@ngrx/store';

export const sMSTrafficKey = "sMSTraffic";

export interface SMSTrafficState {
    error: any
}

export const initialState: SMSTrafficState = {
    error: null
};

export const sMSTrafficReducer = createReducer(
    initialState,
);

export const sMSTrafficFeature = createFeature(
    {
        name: sMSTrafficKey,
        reducer: sMSTrafficReducer
    }
);
