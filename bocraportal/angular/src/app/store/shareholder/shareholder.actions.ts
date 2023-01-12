// Generated by andromda-angular cartridge (app\usecase\action.store.ts.vsl) DO NOT EDIT
import { createAction, props } from '@ngrx/store';
import { ShareholderCriteria } from '@app/model/bw/org/bocra/portal/shareholder/shareholder-criteria';
import { ShareholderVO } from '@app/model/bw/org/bocra/portal/shareholder/shareholder-vo';

export enum ShareholderActionType {
    FIND_BY_ID = '[Shareholder] Find By Id',
    FIND_BY_ID_SUCCESS = '[Shareholder] Find By Id Success',
    SAVE = '[Shareholder] Save',
    SAVE_SUCCESS = '[Shareholder] Save Success',
    REMOVE = '[Shareholder] Remove',
    REMOVE_SUCCESS = '[Shareholder] Remove Success',
    GET_ALL = '[Shareholder] Get All',
    GET_ALL_SUCCESS = '[Shareholder] Get All Success',
    SEARCH = '[Shareholder] Search',
    SEARCH_SUCCESS = '[Shareholder] Search Success',
    GET_ALL_PAGED = '[Shareholder] Get All Paged',
    GET_ALL_PAGED_SUCCESS = '[Shareholder] Get All Paged Success',
    SHAREHOLDER_RESET = '[Shareholder] Shareholder Reset',
    SHAREHOLDER_FAILURE = '[Shareholder] Shareholder Action Failure',
    SHAREHOLDER_LOADING = '[Shareholder] Shareholder Loading',
    SHAREHOLDER_LOADER_MESSAGE = '[Shareholder] Shareholder Loader Message'
}

export const findById = createAction(
    ShareholderActionType.FIND_BY_ID,
    props<{ id: number | any , loading: boolean, loaderMessage: string | undefined }>()
);

export const findByIdSuccess = createAction(
    ShareholderActionType.FIND_BY_ID_SUCCESS,
    props<{ shareholder: ShareholderVO | any, messages: any[], success: boolean}>()
);

export const save = createAction(
    ShareholderActionType.SAVE,
    props<{ shareholder: ShareholderVO | any , loading: boolean, loaderMessage: string | undefined }>()
);

export const saveSuccess = createAction(
    ShareholderActionType.SAVE_SUCCESS,
    props<{ shareholder: ShareholderVO | any, messages: any[], success: boolean}>()
);

export const remove = createAction(
    ShareholderActionType.REMOVE,
    props<{ id: number | any , loading: boolean, loaderMessage: string | undefined }>()
);

export const removeSuccess = createAction(
    ShareholderActionType.REMOVE_SUCCESS,
    props<{ removed: boolean | any, messages: any[], success: boolean}>()
);

export const getAll = createAction(
    ShareholderActionType.GET_ALL,
    props<{  loading: boolean, loaderMessage: string | undefined }>()
);

export const getAllSuccess = createAction(
    ShareholderActionType.GET_ALL_SUCCESS,
    props<{ shareholders: ShareholderVO[] | any[], messages: any[], success: boolean}>()
);

export const search = createAction(
    ShareholderActionType.SEARCH,
    props<{ criteria: ShareholderCriteria | any , loading: boolean, loaderMessage: string | undefined }>()
);

export const searchSuccess = createAction(
    ShareholderActionType.SEARCH_SUCCESS,
    props<{ shareholders: ShareholderVO[] | any[], messages: any[], success: boolean}>()
);

export const getAllPaged = createAction(
    ShareholderActionType.GET_ALL_PAGED,
    props<{ pageNumber: number | any , pageSize: number | any , loading: boolean, loaderMessage: string | undefined }>()
);

export const getAllPagedSuccess = createAction(
    ShareholderActionType.GET_ALL_PAGED_SUCCESS,
    props<{ shareholders: ShareholderVO[] | any[], messages: any[], success: boolean}>()
);


export const shareholderReset = createAction(ShareholderActionType.SHAREHOLDER_RESET);

export const shareholderLoading = createAction(
    ShareholderActionType.SHAREHOLDER_LOADING,
    props<{ loading: boolean, success: boolean, messages: any[] }>()
);

export const shareholderFailure = createAction(
    ShareholderActionType.SHAREHOLDER_FAILURE,
    props<{ messages: any[] }>()
);
