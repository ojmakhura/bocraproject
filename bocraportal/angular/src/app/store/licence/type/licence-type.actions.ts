// Generated by andromda-angular cartridge (app\usecase\action.store.ts.vsl) DO NOT EDIT
import { createAction, props } from '@ngrx/store';
import { LicenceTypeCriteria } from '@app/model/bw/org/bocra/portal/licence/type/licence-type-criteria';
import { LicenceTypeVO } from '@app/model/bw/org/bocra/portal/licence/type/licence-type-vo';

export enum LicenceTypeActionType {
    FIND_BY_ID = '[LicenceType] Find By Id',
    FIND_BY_ID_SUCCESS = '[LicenceType] Find By Id Success',
    SAVE = '[LicenceType] Save',
    SAVE_SUCCESS = '[LicenceType] Save Success',
    REMOVE = '[LicenceType] Remove',
    REMOVE_SUCCESS = '[LicenceType] Remove Success',
    GET_ALL = '[LicenceType] Get All',
    GET_ALL_SUCCESS = '[LicenceType] Get All Success',
    SEARCH = '[LicenceType] Search',
    SEARCH_SUCCESS = '[LicenceType] Search Success',
    GET_ALL_PAGED = '[LicenceType] Get All Paged',
    GET_ALL_PAGED_SUCCESS = '[LicenceType] Get All Paged Success',
    LICENCE_TYPE_RESET = '[LicenceType] LicenceType Reset',
    LICENCE_TYPE_FAILURE = '[LicenceType] LicenceType Action Failure',
    LICENCE_TYPE_LOADING = '[LicenceType] LicenceType Loading'
}

export const findById = createAction(
    LicenceTypeActionType.FIND_BY_ID,
    props<{ id: number | any, loading: boolean }>()
);

export const findByIdSuccess = createAction(
    LicenceTypeActionType.FIND_BY_ID_SUCCESS,
    props<{ licenceType: LicenceTypeVO | any, success: boolean}>()
);

export const save = createAction(
    LicenceTypeActionType.SAVE,
    props<{ licenceType: LicenceTypeVO | any , loading: boolean }>()
);

export const saveSuccess = createAction(
    LicenceTypeActionType.SAVE_SUCCESS,
    props<{ licenceType: LicenceTypeVO | any, success: boolean}>()
);

export const remove = createAction(
    LicenceTypeActionType.REMOVE,
    props<{ id: number | any , loading: boolean }>()
);

export const removeSuccess = createAction(
    LicenceTypeActionType.REMOVE_SUCCESS,
    props<{ removed: boolean | any, success: boolean}>()
);

export const getAll = createAction(
    LicenceTypeActionType.GET_ALL,
    props<{  loading: boolean }>()
);

export const getAllSuccess = createAction(
    LicenceTypeActionType.GET_ALL_SUCCESS,
    props<{ licenceTypes: LicenceTypeVO[] | any[], success: boolean}>()
);

export const search = createAction(
    LicenceTypeActionType.SEARCH,
    props<{ criteria: LicenceTypeCriteria | any , loading: boolean }>()
);

export const searchSuccess = createAction(
    LicenceTypeActionType.SEARCH_SUCCESS,
    props<{ licenceTypes: LicenceTypeVO[] | any[], success: boolean}>()
);

export const getAllPaged = createAction(
    LicenceTypeActionType.GET_ALL_PAGED,
    props<{ pageNumber: number | any , pageSize: number | any , loading: boolean }>()
);

export const getAllPagedSuccess = createAction(
    LicenceTypeActionType.GET_ALL_PAGED_SUCCESS,
    props<{ licenceTypes: LicenceTypeVO[] | any[], success: boolean}>()
);

export const licenceTypeReset = createAction(LicenceTypeActionType.LICENCE_TYPE_RESET);

export const licenceTypeLoading = createAction(
    LicenceTypeActionType.LICENCE_TYPE_LOADING,
    props<{ loading: boolean, success: boolean, errors: any[] }>()
);

export const licenceTypeFailure = createAction(
    LicenceTypeActionType.LICENCE_TYPE_FAILURE,
    props<{ errors: any[], success: boolean, loading: boolean }>()
);
