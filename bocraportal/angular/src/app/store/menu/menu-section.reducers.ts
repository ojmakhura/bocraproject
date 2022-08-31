// Generated by andromda-angular cartridge (app\usecase\reducer.store.ts.vsl) DO NOT EDIT
import { Action, createFeature, createReducer, on } from '@ngrx/store';
import * as MenuSectionActions from './menu-section.actions';
import {menuSectionKey, initialState} from './menu-section.state';

export const menuSectionReducer = createReducer(
    initialState,
    on(MenuSectionActions.findByIdSuccess, (state, action) => ({
        ...state,
        menuSection: action.menuSection, 
        loading: false,
        success: action.success,
        error: false,
        messages: action.messages
    })),
    on(MenuSectionActions.saveSuccess, (state, action) => ({
        ...state,
        menuSection: action.menuSection, 
        loading: false,
        success: action.success,
        error: false,
        messages: action.messages
    })),
    on(MenuSectionActions.addMenuSection, (state, action) => ({
        ...state,
        menuSections: [...state.menuSections, action.menuSection], 
        loading: false,
        success: false,
        messages: []
    })),
    on(MenuSectionActions.removeSuccess, (state, action) => ({
        ...state,
        removed: action.removed, 
        loading: false,
        success: action.success,
        error: false,
        messages: action.messages
    })),
    on(MenuSectionActions.getAllSuccess, (state, action) => ({
        ...state,
        menuSections: action.menuSections, 
        loading: false,
        success: action.success,
        error: false,
        messages: action.messages
    })),
    on(MenuSectionActions.searchSuccess, (state, action) => ({
        ...state,
        menuSections: action.menuSections, 
        loading: false,
        success: action.success,
        error: false,
        messages: action.messages
    })),
    on(MenuSectionActions.getAllPagedSuccess, (state, action) => ({
        ...state,
        id: null, 
        criteria: null, 
        menuSection: null, 
        menuSections: action.menuSections, 
        loading: false,
        success: action.success,
        error: false,
        messages: action.messages
    })),
    on(MenuSectionActions.findByAuthorisationRolesSuccess, (state, action) => ({
        ...state,
        id: null, 
        criteria: null, 
        menuSection: null, 
        menuSections: action.menuSections, 
        loading: false,
        success: action.success,
        error: false,
        messages: action.messages
    })),
    on(MenuSectionActions.menuSectionReset, (state) => ({
      ...state,
        id: null, 
        criteria: null, 
        menuSection: null, 
        menuSections: [], 
        loading: false,
        removed: false,
        success: false,
        error: false,
        messges: []
    })),
    on(MenuSectionActions.menuSectionFailure, (state, action) => ({
        ...state,
        loading: false,
        success: false,
        error: true,
        messages: action.messages
    })),
    on(MenuSectionActions.menuSectionLoading, (state, action) => ({
        ...state,
        loading: action.loading,
        success: false
    }))
);

export const menuSectionFeature = createFeature({
    name: menuSectionKey,
    reducer: menuSectionReducer
});
