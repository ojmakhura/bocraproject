// Generated by andromda-angular cartridge (app\usecase\action.store.ts.vsl) DO NOT EDIT
import { createAction, props } from '@ngrx/store';
import { LicenseeShareholderVO } from '@model/bw/org/bocra/portal/licensee/shares/licensee-shareholder-vo';

export enum LicenseeShareholderActionType {
  FIND_BY_ID = '[Licensee Shareholder] Find By Id',
  FIND_BY_ID_SUCCESS = '[Licensee Shareholder] Find By Id Success',
  FIND_BY_LICENSEE = '[Licensee Shareholder] Find By Licensee',
  FIND_BY_LICENSEE_SUCCESS = '[Licensee Shareholder] Find By Licensee Success',
  FIND_BY_SHAREHOLDER = '[Licensee Shareholder] Find By Shareholder',
  FIND_BY_SHAREHOLDER_SUCCESS = '[Licensee Shareholder] Find By Shareholder Success',
  CREATE = '[Licensee Shareholder] Save',
  CREATE_SUCCESS = '[Licensee Shareholder] Save Success',
  UPDATE_LICENSEE = '[Licensee Shareholder] Update Licensee',
  UPDATE_SHAREHOLDER = '[Licensee Shareholder] Update Shareholder',
  UPDATE_NUMBER_OF_SHARES = '[Licensee Shareholder] Update Number of Shares',
  UPDATE_SUCCESS = '[Licensee Shareholder] Update Success',
  REMOVE = '[Licensee Shareholder] Remove',
  REMOVE_SUCCESS = '[Licensee Shareholder] Remove Success',
  GET_ALL = '[Licensee Shareholder] Get All',
  GET_ALL_SUCCESS = '[Licensee Shareholder] Get All Success',
  LICENSEE_SHAREHOLDER_RESET = '[Licensee Shareholder] Licensee Shareholder Reset',
  LICENSEE_SHAREHOLDER_FAILURE = '[Licensee Shareholder] Licensee Shareholder Action Failure',
  LICENSEE_SHAREHOLDER_LOADING = '[Licensee Shareholder] Licensee Shareholder Loading',
}

export const findById = createAction(
  LicenseeShareholderActionType.FIND_BY_ID,
  props<{ id: number | any; loading: boolean; loaderMessage: string | undefined }>()
);

export const findByIdSuccess = createAction(
  LicenseeShareholderActionType.FIND_BY_ID_SUCCESS,
  props<{ licenseeShareholder: LicenseeShareholderVO | any; messages: any[]; success: boolean }>()
);

export const findByLicensee = createAction(
  LicenseeShareholderActionType.FIND_BY_LICENSEE,
  props<{ licenseeId: number | any; loading: boolean; loaderMessage: string | undefined }>()
);

export const findByLicenseeSuccess = createAction(
  LicenseeShareholderActionType.FIND_BY_LICENSEE_SUCCESS,
  props<{ licenseeShareholders: LicenseeShareholderVO[] | any[]; messages: any[]; success: boolean }>()
);

export const findByShareholder = createAction(
  LicenseeShareholderActionType.FIND_BY_SHAREHOLDER,
  props<{ shareholderId: number | any; loading: boolean; loaderMessage: string | undefined }>()
);

export const findByShareholderSuccess = createAction(
  LicenseeShareholderActionType.FIND_BY_SHAREHOLDER_SUCCESS,
  props<{ licenseeShareholders: LicenseeShareholderVO[] | any[]; messages: any[]; success: boolean }>()
);

export const create = createAction(
  LicenseeShareholderActionType.CREATE,
  props<{
    licenseeId: number;
    shareholderId: number;
    numberOfShares: number;
    loading: boolean;
    loaderMessage: string | undefined;
  }>()
);

export const createSuccess = createAction(
  LicenseeShareholderActionType.CREATE_SUCCESS,
  props<{ licenseeShareholder: LicenseeShareholderVO | any; messages: any[]; success: boolean }>()
);

export const updateLicensee = createAction(
  LicenseeShareholderActionType.UPDATE_LICENSEE,
  props<{ id: number; licenseeId: number; loading: boolean; loaderMessage: string | undefined }>()
);

export const updateShareholder = createAction(
  LicenseeShareholderActionType.UPDATE_SHAREHOLDER,
  props<{ id: number; shareholderId: number; loading: boolean; loaderMessage: string | undefined }>()
);

export const updateNumberOfShares = createAction(
  LicenseeShareholderActionType.UPDATE_NUMBER_OF_SHARES,
  props<{ id: number; numberOfShares: number; loading: boolean; loaderMessage: string | undefined }>()
);

export const updateSuccess = createAction(
  LicenseeShareholderActionType.UPDATE_SUCCESS,
  props<{ licenseeShareholder: LicenseeShareholderVO | any; messages: any[]; success: boolean }>()
);

export const remove = createAction(
  LicenseeShareholderActionType.REMOVE,
  props<{ id: number | any; loading: boolean; loaderMessage: string | undefined }>()
);

export const removeSuccess = createAction(
  LicenseeShareholderActionType.REMOVE_SUCCESS,
  props<{ removed: boolean | any; messages: any[]; success: boolean }>()
);

export const getAll = createAction(
  LicenseeShareholderActionType.GET_ALL,
  props<{ loading: boolean; loaderMessage: string | undefined }>()
);

export const getAllSuccess = createAction(
  LicenseeShareholderActionType.GET_ALL_SUCCESS,
  props<{ licenseeShareholders: LicenseeShareholderVO[] | any[]; messages: any[]; success: boolean }>()
);

export const licenseeShareholderReset = createAction(LicenseeShareholderActionType.LICENSEE_SHAREHOLDER_RESET);

export const licenseeShareholderLoading = createAction(
  LicenseeShareholderActionType.LICENSEE_SHAREHOLDER_LOADING,
  props<{ loading: boolean; loaderMessage: string | undefined; success: boolean; messages: any[] }>()
);

export const licenseeShareholderFailure = createAction(
  LicenseeShareholderActionType.LICENSEE_SHAREHOLDER_FAILURE,
  props<{ messages: any[] }>()
);
