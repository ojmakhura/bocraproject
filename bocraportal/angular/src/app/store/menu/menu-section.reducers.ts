// Generated by andromda-angular cartridge (app\usecase\reducer.store.ts.vsl) DO NOT EDIT
import { Action, createFeature, createReducer, on } from '@ngrx/store';
import * as MenuSectionActions from './menu-section.actions';
import {menuSectionKey, initialState} from './menu-section.state';

export const menuSectionReducer = createReducer(
    initialState,
    on(MenuSectionActions.findById, (state, action) => ({
        ...state,
        loading: action.loading,
        loaderMessage: action.loaderMesage,
    })),
    on(MenuSectionActions.findByIdSuccess, (state, action) => ({
        ...state,
        menuSection: action.menuSection, 
        loading: false,
        loaderMessage: undefined,
        success: action.success,
        error: false,
        messages: action.messages
    })),
    on(MenuSectionActions.save, (state, action) => ({
        ...state,
        loading: action.loading,
        loaderMessage: action.loaderMesage,
    })),
    on(MenuSectionActions.saveSuccess, (state, action) => ({
        ...state,
        menuSection: action.menuSection, 
        loading: false,
        loaderMessage: undefined,
        success: action.success,
        error: false,
        messages: action.messages
    })),
    on(MenuSectionActions.addMenuSection, (state, action) => ({
        ...state,
        menuSections: [...state.menuSections, action.menuSection], 
        loading: false,
        loaderMessage: undefined,
        success: false,
        messages: []
    })),
    on(MenuSectionActions.remove, (state, action) => ({
        ...state,
        loading: action.loading,
        loaderMessage: action.loaderMesage,
    })),
    on(MenuSectionActions.removeSuccess, (state, action) => ({
        ...state,
        removed: action.removed, 
        loading: false,
        loaderMessage: undefined,
        success: action.success,
        error: false,
        messages: action.messages
    })),
    on(MenuSectionActions.getAll, (state, action) => ({
        ...state,
        loading: action.loading,
        loaderMessage: action.loaderMesage,
    })),
    on(MenuSectionActions.getAllSuccess, (state, action) => ({
        ...state,
        menuSections: action.menuSections, 
        loading: false,
        loaderMessage: undefined,
        success: action.success,
        error: false,
        messages: action.messages
    })),
    on(MenuSectionActions.search, (state, action) => ({
        ...state,
        loading: action.loading,
        loaderMessage: action.loaderMesage,
    })),
    on(MenuSectionActions.searchSuccess, (state, action) => ({
        ...state,
        menuSections: action.menuSections, 
        loading: false,
        loaderMessage: undefined,
        success: action.success,
        error: false,
        messages: action.messages
    })),
    on(MenuSectionActions.getAllPaged, (state, action) => ({
        ...state,
        loading: action.loading,
        loaderMessage: action.loaderMesage,
    })),
    on(MenuSectionActions.getAllPagedSuccess, (state, action) => ({
        ...state,
        id: null, 
        criteria: null, 
        menuSection: null, 
        menuSections: action.menuSections, 
        loading: false,
        loaderMessage: undefined,
        success: action.success,
        error: false,
        messages: action.messages
    })),
    on(MenuSectionActions.findByAuthorisationRoles, (state, action) => ({
        ...state,
        loading: action.loading,
        loaderMessage: action.loaderMesage,
    })),
    on(MenuSectionActions.findByAuthorisationRolesSuccess, (state, action) => ({
        ...state,
        id: null, 
        criteria: null, 
        menuSection: null, 
        menuSections: action.menuSections, 
        loading: false,
        loaderMessage: undefined,
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
        loaderMessage: undefined,
        removed: false,
        success: false,
        error: false,
        messges: []
    })),
    on(MenuSectionActions.menuSectionFailure, (state, action) => ({
        ...state,
        loading: false,
        loaderMessage: undefined,
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
