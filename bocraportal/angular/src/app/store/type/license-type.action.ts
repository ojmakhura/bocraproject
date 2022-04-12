// Generated by andromda-angular cartridge (app\usecase\action.store.ts.vsl) DO NOT EDIT
import { createAction, props } from '@ngrx/store';
import { LicenseTypeCriteria } from '@app/model/bw/org/bocra/portal/type/license-type-criteria';
import { LicenseTypeVO } from '@app/model/bw/org/bocra/portal/type/license-type-vo';

export enum LicenseTypeActionType {
    FIND_BY_ID = '[LicenseType] Find By Id',
    FIND_BY_ID_SUCCESS = '[LicenseType] Find By Id Success',
    SAVE = '[LicenseType] Save',
    SAVE_SUCCESS = '[LicenseType] Save Success',
    REMOVE = '[LicenseType] Remove',
    REMOVE_SUCCESS = '[LicenseType] Remove Success',
    GET_ALL = '[LicenseType] Get All',
    GET_ALL_SUCCESS = '[LicenseType] Get All Success',
    SEARCH = '[LicenseType] Search',
    SEARCH_SUCCESS = '[LicenseType] Search Success',
    GET_ALL_PAGED = '[LicenseType] Get All Paged',
    GET_ALL_PAGED_SUCCESS = '[LicenseType] Get All Paged Success',
    LICENSETYPE_RESET = '[LicenseType] LicenseType Reset',
    LICENSETYPE_FAILURE = '[LicenseType] LicenseType Action Failure'
}

export const findById = createAction(
    LicenseTypeActionType.FIND_BY_ID,
    props<{ id: number | any  }>()
);

export const findByIdSuccess = createAction(
    LicenseTypeActionType.FIND_BY_ID_SUCCESS,
//    props<{ results: LicenseTypeVO | any }>()
);

export const save = createAction(
    LicenseTypeActionType.SAVE,
    props<{ licenseTypeVO: LicenseTypeVO | any  }>()
);

export const saveSuccess = createAction(
    LicenseTypeActionType.SAVE_SUCCESS,
//    props<{ results: LicenseTypeVO | any }>()
);

export const remove = createAction(
    LicenseTypeActionType.REMOVE,
    props<{ id: number | any  }>()
);

export const removeSuccess = createAction(
    LicenseTypeActionType.REMOVE_SUCCESS,
//    props<{ results: boolean | any }>()
);

export const getAll = createAction(
    LicenseTypeActionType.GET_ALL);

export const getAllSuccess = createAction(
    LicenseTypeActionType.GET_ALL_SUCCESS,
//    props<{ results: LicenseTypeVO[] | any }>()
);

export const search = createAction(
    LicenseTypeActionType.SEARCH,
    props<{ searchCriteria: LicenseTypeCriteria | any  }>()
);

export const searchSuccess = createAction(
    LicenseTypeActionType.SEARCH_SUCCESS,
//    props<{ results: LicenseTypeVO[] | any }>()
);

export const getAllPaged = createAction(
    LicenseTypeActionType.GET_ALL_PAGED,
    props<{ pageNumber: number | any , pageSize: number | any  }>()
);

export const getAllPagedSuccess = createAction(
    LicenseTypeActionType.GET_ALL_PAGED_SUCCESS,
//    props<{ results: LicenseTypeVO[] | any }>()
);


export const licenseTypeReset = createAction(LicenseTypeActionType.LICENSETYPE_RESET);

export const licenseTypeFailure = createAction(
    LicenseTypeActionType.LICENSETYPE_FAILURE,
    props<{ error: any }>()
);
