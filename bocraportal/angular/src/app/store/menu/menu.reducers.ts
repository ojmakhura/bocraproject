import { Action, createFeature, createReducer, on } from '@ngrx/store';
import * as MenuActions from './menu.actions';
import { menuKey, initialState } from './menu.state';

export const menuReducer = createReducer(
  initialState,

  on(MenuActions.addMenu, (state, action) => ({
    ...state,
    menus: state.menus.some((menu) => menu.routerLink === action.menu.routerLink)
      ? [...state.menus]
      : [...state.menus, action.menu],
    messages: [],
  })),
  on(MenuActions.getMenusSuccess, (state, action) => ({
    ...state,
    menus: action.menus,
    messages: [],
  })),
  on(MenuActions.menuReset, (state) => ({
    ...state,
    menus: [],
    messages: [],
  })),
  on(MenuActions.menuFailure, (state, action) => ({
    ...state,
    messages: action.messages,
  }))
);

export const menuFeature = createFeature({
  name: menuKey,
  reducer: menuReducer,
});
