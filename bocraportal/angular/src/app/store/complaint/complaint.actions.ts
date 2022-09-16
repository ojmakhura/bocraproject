// Generated by andromda-angular cartridge (app\usecase\action.store.ts.vsl) DO NOT EDIT
import { createAction, props } from '@ngrx/store';
import { ComplaintVO } from '@app/model/bw/org/bocra/portal/complaint/complaint-vo';

export enum ComplaintActionType {
    FIND_BY_ID = '[Complaint] Find By Id',
    FIND_BY_ID_SUCCESS = '[Complaint] Find By Id Success',
    SAVE = '[Complaint] Save',
    SAVE_SUCCESS = '[Complaint] Save Success',
    REMOVE = '[Complaint] Remove',
    REMOVE_SUCCESS = '[Complaint] Remove Success',
    GET_ALL = '[Complaint] Get All',
    GET_ALL_SUCCESS = '[Complaint] Get All Success',
    SEARCH = '[Complaint] Search',
    SEARCH_SUCCESS = '[Complaint] Search Success',
    GET_ALL_PAGED = '[Complaint] Get All Paged',
    GET_ALL_PAGED_SUCCESS = '[Complaint] Get All Paged Success',
    COMPLAINT_RESET = '[Complaint] Complaint Reset',
    COMPLAINT_FAILURE = '[Complaint] Complaint Action Failure',
    COMPLAINT_LOADING = '[Complaint] Complaint Loading'
}

export const findById = createAction(
    ComplaintActionType.FIND_BY_ID,
    props<{ id: number | any , loading: boolean, loaderMesage: string | undefined }>()
);

export const findByIdSuccess = createAction(
    ComplaintActionType.FIND_BY_ID_SUCCESS,
    props<{ complaint: ComplaintVO | any, messages: any[], success: boolean}>()
);

export const save = createAction(
    ComplaintActionType.SAVE,
    props<{ complaint: ComplaintVO | any , loading: boolean, loaderMesage: string | undefined }>()
);

export const saveSuccess = createAction(
    ComplaintActionType.SAVE_SUCCESS,
    props<{ complaint: ComplaintVO | any, messages: any[], success: boolean}>()
);

export const remove = createAction(
    ComplaintActionType.REMOVE,
    props<{ id: number | any , loading: boolean, loaderMesage: string | undefined }>()
);

export const removeSuccess = createAction(
    ComplaintActionType.REMOVE_SUCCESS,
    props<{ removed: boolean | any, messages: any[], success: boolean}>()
);

export const getAll = createAction(
    ComplaintActionType.GET_ALL,
    props<{  loading: boolean, loaderMesage: string | undefined }>()
);

export const getAllSuccess = createAction(
    ComplaintActionType.GET_ALL_SUCCESS,
    props<{ complaints: ComplaintVO[] | any[], messages: any[], success: boolean}>()
);

export const search = createAction(
    ComplaintActionType.SEARCH,
    props<{ criteria: string | any , loading: boolean, loaderMesage: string | undefined }>()
);

export const searchSuccess = createAction(
    ComplaintActionType.SEARCH_SUCCESS,
    props<{ complaints: ComplaintVO[] | any[], messages: any[], success: boolean}>()
);

export const getAllPaged = createAction(
    ComplaintActionType.GET_ALL_PAGED,
    props<{ pageNumber: number | any , pageSize: number | any , loading: boolean, loaderMesage: string | undefined }>()
);

export const getAllPagedSuccess = createAction(
    ComplaintActionType.GET_ALL_PAGED_SUCCESS,
    props<{ complaints: ComplaintVO[] | any[], messages: any[], success: boolean}>()
);


export const complaintReset = createAction(ComplaintActionType.COMPLAINT_RESET);

export const complaintLoading = createAction(
    ComplaintActionType.COMPLAINT_LOADING,
    props<{ loading: boolean, loaderMesage: string | undefined, success: boolean, messages: any[] }>()
);

export const complaintFailure = createAction(
    ComplaintActionType.COMPLAINT_FAILURE,
    props<{ messages: any[] }>()
);
