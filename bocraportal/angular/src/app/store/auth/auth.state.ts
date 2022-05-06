// Generated by andromda-angular cartridge (app\usecase\state.store.ts.vsl) DO NOT EDIT

import { Menu } from "@app/model/menu/menu";
import { LicenseeVO } from "@app/model/bw/org/bocra/portal/licensee/licensee-vo";

export const authKey = 'auth';

export interface AuthState {
  id: string | any;
  roles: string[] | any[];
  username: string | any;
  email: string | any;
  firstName: string | any;
  lastName: string | any;
  licensee: LicenseeVO | any;
  loggedIn: boolean;
  error: any;
}

export const initialState: AuthState = {
  id: null,
  roles: [],
  username: null,
  email: null,
  firstName: null,
  lastName: null,
  licensee: null,
  loggedIn: false,
  error: null,
};
