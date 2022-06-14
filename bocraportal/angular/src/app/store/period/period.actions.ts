// Generated by andromda-angular cartridge (app\usecase\action.store.ts.vsl) DO NOT EDIT
import { createAction, props } from '@ngrx/store';
import { PeriodVO } from '@app/model/bw/org/bocra/portal/period/period-vo';
import { PeriodCriteria } from '@app/model/bw/org/bocra/portal/period/period-criteria';

export enum PeriodActionType {
    FIND_BY_ID = '[Period] Find By Id',
    FIND_BY_ID_SUCCESS = '[Period] Find By Id Success',
    SAVE = '[Period] Save',
    SAVE_SUCCESS = '[Period] Save Success',
    REMOVE = '[Period] Remove',
    REMOVE_SUCCESS = '[Period] Remove Success',
    GET_ALL = '[Period] Get All',
    GET_ALL_SUCCESS = '[Period] Get All Success',
    SEARCH = '[Period] Search',
    SEARCH_SUCCESS = '[Period] Search Success',
    GET_ALL_PAGED = '[Period] Get All Paged',
    GET_ALL_PAGED_SUCCESS = '[Period] Get All Paged Success',
    PERIOD_RESET = '[Period] Period Reset',
    PERIOD_FAILURE = '[Period] Period Action Failure',
    PERIOD_LOADING = '[Period] Period Loading'
}

export const findById = createAction(
    PeriodActionType.FIND_BY_ID,
    props<{ id: number | any , loading: boolean }>()
);

export const findByIdSuccess = createAction(
    PeriodActionType.FIND_BY_ID_SUCCESS,
    props<{ period: PeriodVO | any, success: boolean}>()
);

export const save = createAction(
    PeriodActionType.SAVE,
    props<{ period: PeriodVO | any , loading: boolean }>()
);

export const saveSuccess = createAction(
    PeriodActionType.SAVE_SUCCESS,
    props<{ period: PeriodVO | any, success: boolean}>()
);

export const remove = createAction(
    PeriodActionType.REMOVE,
    props<{ id: number | any , loading: boolean }>()
);

export const removeSuccess = createAction(
    PeriodActionType.REMOVE_SUCCESS,
    props<{ removed: boolean | any, success: boolean}>()
);

export const getAll = createAction(
    PeriodActionType.GET_ALL,
    props<{  loading: boolean }>()
);

export const getAllSuccess = createAction(
    PeriodActionType.GET_ALL_SUCCESS,
    props<{ periods: PeriodVO[] | any[], success: boolean}>()
);

export const search = createAction(
    PeriodActionType.SEARCH,
    props<{ criteria: PeriodCriteria | any , loading: boolean }>()
);

export const searchSuccess = createAction(
    PeriodActionType.SEARCH_SUCCESS,
    props<{ periods: PeriodVO[] | any[], success: boolean}>()
);

export const getAllPaged = createAction(
    PeriodActionType.GET_ALL_PAGED,
    props<{ pageNumber: number | any , pageSize: number | any , loading: boolean }>()
);

export const getAllPagedSuccess = createAction(
    PeriodActionType.GET_ALL_PAGED_SUCCESS,
    props<{ periods: PeriodVO[] | any[], success: boolean}>()
);


export const periodReset = createAction(PeriodActionType.PERIOD_RESET);

export const periodLoading = createAction(
    PeriodActionType.PERIOD_LOADING,
    props<{ loading: boolean, success: boolean, errors: any[] }>()
);

export const periodFailure = createAction(
    PeriodActionType.PERIOD_FAILURE,
    props<{ errors: any[], success: boolean, loading: boolean }>()
);
