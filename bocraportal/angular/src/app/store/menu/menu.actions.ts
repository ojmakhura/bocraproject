// Generated by andromda-angular cartridge (app\usecase\action.store.ts.vsl) DO NOT EDIT
import { Menu } from '@app/model/menu/menu';
import { createAction, props } from '@ngrx/store';

export enum MenuActionType {
  GET_MENUS = '[Menu] Get Menus',
  ADD_MENU = '[Menu] Add Menu',
  GET_MENUS_SUCCESS = '[Menu] Get Menus Success',
  MENU_RESET = '[Menu] Menu Reset',
  MENU_FAILURE = '[Menu] Menu Action Failure',
}

export const getMenus = createAction(MenuActionType.GET_MENUS);
export const addMenu = createAction(MenuActionType.ADD_MENU, props<{ menu: Menu }>());
export const getMenusSuccess = createAction(MenuActionType.GET_MENUS_SUCCESS, props<{ menus: Menu[] | any[] }>());

export const menuReset = createAction(MenuActionType.MENU_RESET);

export const menuFailure = createAction(MenuActionType.MENU_FAILURE, props<{ messages: any[] }>());
