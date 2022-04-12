// Generated by andromda-angular cartridge (app\usecase\action.store.ts.vsl) DO NOT EDIT
import { createAction, props } from '@ngrx/store';
import { UrlGuardCriteria } from '@app/model/bw/org/bocra/portal/guard/url-guard-criteria';
import { UrlGuardVO } from '@app/model/bw/org/bocra/portal/guard/url-guard-vo';

export enum GuardActionType {
    FIND_BY_ID = '[Guard] Find By Id',
    FIND_BY_ID_SUCCESS = '[Guard] Find By Id Success',
    SAVE = '[Guard] Save',
    SAVE_SUCCESS = '[Guard] Save Success',
    REMOVE = '[Guard] Remove',
    REMOVE_SUCCESS = '[Guard] Remove Success',
    GET_ALL = '[Guard] Get All',
    GET_ALL_SUCCESS = '[Guard] Get All Success',
    SEARCH = '[Guard] Search',
    SEARCH_SUCCESS = '[Guard] Search Success',
    GET_ALL_PAGED = '[Guard] Get All Paged',
    GET_ALL_PAGED_SUCCESS = '[Guard] Get All Paged Success',
    GUARD_RESET = '[Guard] Guard Reset',
    GUARD_FAILURE = '[Guard] Guard Action Failure'
}

export const findById = createAction(
    GuardActionType.FIND_BY_ID,
    props<{ id: number | any  }>()
);

export const findByIdSuccess = createAction(
    GuardActionType.FIND_BY_ID_SUCCESS,
//    props<{ results: UrlGuardVO | any }>()
);

export const save = createAction(
    GuardActionType.SAVE,
    props<{ urlGuardVO: UrlGuardVO | any  }>()
);

export const saveSuccess = createAction(
    GuardActionType.SAVE_SUCCESS,
//    props<{ results: UrlGuardVO | any }>()
);

export const remove = createAction(
    GuardActionType.REMOVE,
    props<{ id: number | any  }>()
);

export const removeSuccess = createAction(
    GuardActionType.REMOVE_SUCCESS,
//    props<{ results: boolean | any }>()
);

export const getAll = createAction(
    GuardActionType.GET_ALL);

export const getAllSuccess = createAction(
    GuardActionType.GET_ALL_SUCCESS,
//    props<{ results: UrlGuardVO[] | any }>()
);

export const search = createAction(
    GuardActionType.SEARCH,
    props<{ criteria: UrlGuardCriteria | any  }>()
);

export const searchSuccess = createAction(
    GuardActionType.SEARCH_SUCCESS,
//    props<{ results: UrlGuardVO[] | any }>()
);

export const getAllPaged = createAction(
    GuardActionType.GET_ALL_PAGED,
    props<{ pageNumber: number | any , pageSize: number | any  }>()
);

export const getAllPagedSuccess = createAction(
    GuardActionType.GET_ALL_PAGED_SUCCESS,
//    props<{ results: UrlGuardVO[] | any }>()
);


export const guardReset = createAction(GuardActionType.GUARD_RESET);

export const guardFailure = createAction(
    GuardActionType.GUARD_FAILURE,
    props<{ error: any }>()
);
