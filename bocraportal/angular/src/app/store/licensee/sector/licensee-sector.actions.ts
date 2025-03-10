// Generated by andromda-angular cartridge (app\usecase\action.store.ts.vsl) DO NOT EDIT
import { createAction, props } from '@ngrx/store';
import { LicenseeSectorVO } from '@model/bw/org/bocra/portal/licensee/sector/licensee-sector-vo';

export enum LicenseeSectorActionType {
  FIND_BY_ID = '[Licensee Sector] Find By Id',
  FIND_BY_ID_SUCCESS = '[Licensee Sector] Find By Id Success',
  FIND_BY_LICENSEE = '[Licensee Sector] Find By Licensee',
  FIND_BY_LICENSEE_SUCCESS = '[Licensee Sector] Find By Licensee Success',
  FIND_BY_SECTOR = '[Licensee Sector] Find By Sector',
  FIND_BY_SECTOR_SUCCESS = '[Licensee Sector] Find By Sector Success',
  CREATE = '[Licensee Sector] Save',
  CREATE_SUCCESS = '[Licensee Sector] Save Success',
  UPDATE_LICENSEE = '[Licensee Sector] Update Licensee',
  UPDATE_SECTOR = '[Licensee Sector] Update Sector',
  UPDATE_SUCCESS = '[Licensee Sector] Update Success',
  REMOVE = '[Licensee Sector] Remove',
  REMOVE_SUCCESS = '[Licensee Sector] Remove Success',
  GET_ALL = '[Licensee Sector] Get All',
  GET_ALL_SUCCESS = '[Licensee Sector] Get All Success',
  LICENSEE_SECTOR_RESET = '[Licensee Sector] Licensee Sector Reset',
  LICENSEE_SECTOR_FAILURE = '[Licensee Sector] Licensee Sector Action Failure',
  LICENSEE_SECTOR_LOADING = '[Licensee Sector] Licensee Sector Loading',
}

export const findById = createAction(
  LicenseeSectorActionType.FIND_BY_ID,
  props<{ id: number | any; loading: boolean; loaderMessage: string | undefined }>()
);

export const findByIdSuccess = createAction(
  LicenseeSectorActionType.FIND_BY_ID_SUCCESS,
  props<{ licenseeSector: LicenseeSectorVO | any; messages: any[]; success: boolean }>()
);

export const findByLicensee = createAction(
  LicenseeSectorActionType.FIND_BY_LICENSEE,
  props<{ licenseeId: number | any; loading: boolean; loaderMessage: string | undefined }>()
);

export const findByLicenseeSuccess = createAction(
  LicenseeSectorActionType.FIND_BY_LICENSEE_SUCCESS,
  props<{ licenseeSectors: LicenseeSectorVO[] | any[]; messages: any[]; success: boolean }>()
);

export const findBySector = createAction(
  LicenseeSectorActionType.FIND_BY_SECTOR,
  props<{ sectorId: number | any; loading: boolean; loaderMessage: string | undefined }>()
);

export const findBySectorSuccess = createAction(
  LicenseeSectorActionType.FIND_BY_SECTOR_SUCCESS,
  props<{ licenseeSectors: LicenseeSectorVO[] | any[]; messages: any[]; success: boolean }>()
);

export const create = createAction(
  LicenseeSectorActionType.CREATE,
  props<{ licenseeId: number; sectorId: number; loading: boolean; loaderMessage: string | undefined }>()
);

export const createSuccess = createAction(
  LicenseeSectorActionType.CREATE_SUCCESS,
  props<{ licenseeSector: LicenseeSectorVO | any; messages: any[]; success: boolean }>()
);

export const updateLicensee = createAction(
  LicenseeSectorActionType.UPDATE_LICENSEE,
  props<{ id: number; licenseeId: number; loading: boolean; loaderMessage: string | undefined }>()
);

export const updateSector = createAction(
  LicenseeSectorActionType.UPDATE_SECTOR,
  props<{ id: number; sectorId: number; loading: boolean; loaderMessage: string | undefined }>()
);

export const updateSuccess = createAction(
  LicenseeSectorActionType.UPDATE_SUCCESS,
  props<{ licenseeSector: LicenseeSectorVO | any; messages: any[]; success: boolean }>()
);

export const remove = createAction(
  LicenseeSectorActionType.REMOVE,
  props<{ id: number | any; loading: boolean; loaderMessage: string | undefined }>()
);

export const removeSuccess = createAction(
  LicenseeSectorActionType.REMOVE_SUCCESS,
  props<{ removed: boolean | any; messages: any[]; success: boolean }>()
);

export const getAll = createAction(
  LicenseeSectorActionType.GET_ALL,
  props<{ loading: boolean; loaderMessage: string | undefined }>()
);

export const getAllSuccess = createAction(
  LicenseeSectorActionType.GET_ALL_SUCCESS,
  props<{ licenseeSectors: LicenseeSectorVO[] | any[]; messages: any[]; success: boolean }>()
);

export const licenseeSectorReset = createAction(LicenseeSectorActionType.LICENSEE_SECTOR_RESET);

export const licenseeSectorLoading = createAction(
  LicenseeSectorActionType.LICENSEE_SECTOR_LOADING,
  props<{ loading: boolean; loaderMessage: string | undefined; success: boolean; messages: any[] }>()
);

export const licenseeSectorFailure = createAction(
  LicenseeSectorActionType.LICENSEE_SECTOR_FAILURE,
  props<{ messages: any[] }>()
);
