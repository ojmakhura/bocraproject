// Generated by andromda-angular cartridge (app\usecase\action.store.ts.vsl) DO NOT EDIT
import { createAction, props } from '@ngrx/store';
import { SectorVO } from '@app/model/bw/org/bocra/portal/sector/sector-vo';
import { LicenseeSectorVO } from '@model/bw/org/bocra/portal/licensee/licensee-sector-vo';

export enum SectorActionType {
    FIND_BY_ID = '[Sector] Find By Id',
    FIND_BY_ID_SUCCESS = '[Sector] Find By Id Success',
    SAVE = '[Sector] Save',
    SAVE_SUCCESS = '[Sector] Save Success',
    ADD_LICENSEE = '[Sector] Add Licensee',
    REMOVE = '[Sector] Remove',
    REMOVE_SUCCESS = '[Sector] Remove Success',
    GET_ALL = '[Sector] Get All',
    GET_ALL_SUCCESS = '[Sector] Get All Success',
    SEARCH = '[Sector] Search',
    SEARCH_SUCCESS = '[Sector] Search Success',
    GET_ALL_PAGED = '[Sector] Get All Paged',
    GET_ALL_PAGED_SUCCESS = '[Sector] Get All Paged Success',
    SECTOR_RESET = '[Sector] Sector Reset',
    SECTOR_FAILURE = '[Sector] Sector Action Failure',
    SECTOR_LOADING = '[Sector] Sector Loading'
}

export const findById = createAction(
    SectorActionType.FIND_BY_ID,
    props<{ id: number | any , loading: boolean }>()
);

export const findByIdSuccess = createAction(
    SectorActionType.FIND_BY_ID_SUCCESS,
    props<{ sector: SectorVO | any, success: boolean}>()
);

export const save = createAction(
    SectorActionType.SAVE,
    props<{ sector: SectorVO | any , loading: boolean }>()
);

export const saveSuccess = createAction(
    SectorActionType.SAVE_SUCCESS,
    props<{ sector: SectorVO | any, success: boolean}>()
);

export const addLicensee = createAction(
    SectorActionType.ADD_LICENSEE,
    props<{ licensee: LicenseeSectorVO | any }>()
);

export const remove = createAction(
    SectorActionType.REMOVE,
    props<{ id: number | any , loading: boolean }>()
);

export const removeSuccess = createAction(
    SectorActionType.REMOVE_SUCCESS,
    props<{ removed: boolean | any, success: boolean}>()
);

export const getAll = createAction(
    SectorActionType.GET_ALL,
    props<{  loading: boolean }>()
);

export const getAllSuccess = createAction(
    SectorActionType.GET_ALL_SUCCESS,
    props<{ sectors: SectorVO[] | any[], success: boolean}>()
);

export const search = createAction(
    SectorActionType.SEARCH,
    props<{ criteria: string | any , loading: boolean }>()
);

export const searchSuccess = createAction(
    SectorActionType.SEARCH_SUCCESS,
    props<{ sectors: SectorVO[] | any[], success: boolean}>()
);

export const getAllPaged = createAction(
    SectorActionType.GET_ALL_PAGED,
    props<{ pageNumber: number | any , pageSize: number | any , loading: boolean }>()
);

export const getAllPagedSuccess = createAction(
    SectorActionType.GET_ALL_PAGED_SUCCESS,
    props<{ sectors: SectorVO[] | any[], success: boolean}>()
);

export const sectorReset = createAction(SectorActionType.SECTOR_RESET);

export const sectorLoading = createAction(
    SectorActionType.SECTOR_LOADING,
    props<{ loading: boolean, success: boolean, errors: any[] }>()
);

export const sectorFailure = createAction(
    SectorActionType.SECTOR_FAILURE,
    props<{ errors: any[], success: boolean, loading: boolean }>()
);
