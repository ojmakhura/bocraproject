// Generated by andromda-angular cartridge (app\usecase\state.store.ts.vsl) DO NOT EDIT
import { MenuSectionVO } from '@app/model/bw/org/bocra/portal/menu/menu-section-vo';

export const menuSectionKey = 'menuSection';

export interface MenuSectionState {
  id: number | any;
  criteria: String | any;
  menuSection: MenuSectionVO | any;
  menuSections: Array<MenuSectionVO> | Array<any>;
  loading: boolean;
  loaderMessage: string | undefined;
  removed: boolean;
  success: boolean;
  error: boolean;
  messages: string[];
}

export const initialState: MenuSectionState = {
  id: null,
  criteria: null,
  menuSection: null,
  menuSections: [],
  loading: false,
  removed: false,
  success: false,
  error: false,
  messages: [],
  loaderMessage: undefined,
};
