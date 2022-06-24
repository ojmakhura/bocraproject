// Generated by andromda-angular cartridge (app\usecase\reducer.store.ts.vsl) DO NOT EDIT
import { Action, createFeature, createReducer, on } from '@ngrx/store';
import * as SectorActions from './sector.actions';
import {sectorKey, initialState} from './sector.state';

export const sectorReducer = createReducer(
    initialState,
    on(SectorActions.findByIdSuccess, (state, action) => ({
        ...state,
        sector: action.sector, 
        loading: false,
        success: action.success,
        messages: []
    })),
    on(SectorActions.saveSuccess, (state, action) => ({
        ...state,
        sector: action.sector, 
        loading: false,
        success: action.success,
        messages: []
    })),
    on(SectorActions.addLicenseeSuccess, (state, action) => ({
        ...state,
        licensee: action.licensee,
        licensees: [...state.licensees, action.licensee],
        loading: false,
        messages: []
    })),
    on(SectorActions.setLicensees, (state, action) => ({
        ...state,
        licensees: action.licensees,
        loading: false,
        messages: []
    })),
    on(SectorActions.removeSuccess, (state, action) => ({
        ...state,
        removed: action.removed, 
        loading: false,
        success: action.success,
        messages: []
    })),
    on(SectorActions.getAllSuccess, (state, action) => ({
        ...state,
        sectors: action.sectors, 
        loading: false,
        success: action.success,
        messages: []
    })),
    on(SectorActions.searchSuccess, (state, action) => ({
        ...state,
        sectors: action.sectors, 
        loading: false,
        success: action.success,
        messages: []
    })),
    on(SectorActions.getAllPagedSuccess, (state, action) => ({
        ...state,
        sectors: action.sectors, 
        loading: false,
        success: action.success,
        messages: []
    })),
    on(SectorActions.sectorReset, (state) => ({
      ...state,
        criteria: null, 
        id: null, 
        sectors: [], 
        sector: null, 
        loading: false,
        success: false,
        removed: false,
        error: false,
        messages: []
    })),
    on(SectorActions.sectorFailure, (state, action) => ({
        ...state,
        loading: false,
        success: false,
        error: true,
        messages: action.messages
    })),
    on(SectorActions.sectorLoading, (state, action) => ({
        ...state,
        loading: action.loading,
        success: false
    }))
);

export const sectorFeature = createFeature({
    name: sectorKey,
    reducer: sectorReducer
});
