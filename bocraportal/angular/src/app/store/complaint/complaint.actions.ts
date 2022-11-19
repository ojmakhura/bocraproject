// Generated by andromda-angular cartridge (app\usecase\action.store.ts.vsl) DO NOT EDIT
import { createAction, props } from '@ngrx/store';
import { ComplaintReplyVO } from '@app/model/bw/org/bocra/portal/complaint/complaint-reply-vo';
import { ComplaintVO } from '@app/model/bw/org/bocra/portal/complaint/complaint-vo';

export enum ComplaintActionType {
    FIND_BY_ID = '[Complaint] Find By Id',
    FIND_BY_ID_SUCCESS = '[Complaint] Find By Id Success',
    FIND_BY_COMPLAINT_ID = '[Complaint] Find By Complaint Id',
    FIND_BY_COMPLAINT_ID_SUCCESS = '[Complaint] Find By Complaint Id Success',
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
    ADD_COMPLAINT_REPLY = '[Complaint] Add Complaint Reply',
    ADD_COMPLAINT_REPLY_SUCCESS = '[Complaint] Add Complaint Reply Success',
    REMOVE_COMPLAINT_REPLY = '[Complaint] Remove Complaint Reply',
    REMOVE_COMPLAINT_REPLY_SUCCESS = '[Complaint] Remove Complaint Reply Success',
    COMPLAINT_RESET = '[Complaint] Complaint Reset',
    COMPLAINT_FAILURE = '[Complaint] Complaint Action Failure',
    COMPLAINT_LOADING = '[Complaint] Complaint Loading',
    COMPLAINT_LOADER_MESSAGE = '[Complaint] Complaint Loader Message'
}

export const findById = createAction(
    ComplaintActionType.FIND_BY_ID,
    props<{ id: number | any , loading: boolean, loaderMessage: string | undefined }>()
);

export const findByIdSuccess = createAction(
    ComplaintActionType.FIND_BY_ID_SUCCESS,
    props<{ complaint: ComplaintVO | any, messages: any[], success: boolean}>()
);

export const findByComplaintId = createAction(
    ComplaintActionType.FIND_BY_COMPLAINT_ID,
    props<{ complaintId: string | any , loading: boolean, loaderMessage: string | undefined }>()
);

export const findByComplaintIdSuccess = createAction(
    ComplaintActionType.FIND_BY_COMPLAINT_ID_SUCCESS,
    props<{ complaint: ComplaintVO | any, messages: any[], success: boolean}>()
);

export const save = createAction(
    ComplaintActionType.SAVE,
    props<{ complaint: ComplaintVO | any , loading: boolean, loaderMessage: string | undefined }>()
);

export const saveSuccess = createAction(
    ComplaintActionType.SAVE_SUCCESS,
    props<{ complaint: ComplaintVO | any, messages: any[], success: boolean}>()
);

export const remove = createAction(
    ComplaintActionType.REMOVE,
    props<{ id: number | any , loading: boolean, loaderMessage: string | undefined }>()
);

export const removeSuccess = createAction(
    ComplaintActionType.REMOVE_SUCCESS,
    props<{ removed: boolean | any, messages: any[], success: boolean}>()
);

export const getAll = createAction(
    ComplaintActionType.GET_ALL,
    props<{  loading: boolean, loaderMessage: string | undefined }>()
);

export const getAllSuccess = createAction(
    ComplaintActionType.GET_ALL_SUCCESS,
    props<{ complaints: ComplaintVO[] | any[], messages: any[], success: boolean}>()
);

export const search = createAction(
    ComplaintActionType.SEARCH,
    props<{ criteria: string | any , loading: boolean, loaderMessage: string | undefined }>()
);

export const searchSuccess = createAction(
    ComplaintActionType.SEARCH_SUCCESS,
    props<{ complaints: ComplaintVO[] | any[], messages: any[], success: boolean}>()
);

export const getAllPaged = createAction(
    ComplaintActionType.GET_ALL_PAGED,
    props<{ pageNumber: number | any , pageSize: number | any , loading: boolean, loaderMessage: string | undefined }>()
);

export const getAllPagedSuccess = createAction(
    ComplaintActionType.GET_ALL_PAGED_SUCCESS,
    props<{ complaints: ComplaintVO[] | any[], messages: any[], success: boolean}>()
);

export const addComplaintReply = createAction(
    ComplaintActionType.ADD_COMPLAINT_REPLY,
    props<{ complaintId: number | any , reply: ComplaintReplyVO | any , loading: boolean, loaderMessage: string | undefined }>()
);

export const addComplaintReplySuccess = createAction(
    ComplaintActionType.ADD_COMPLAINT_REPLY_SUCCESS,
    props<{ complaintReply: ComplaintReplyVO | any, messages: any[], success: boolean}>()
);

export const removeComplaintReply = createAction(
    ComplaintActionType.REMOVE_COMPLAINT_REPLY,
    props<{ id: number | any , loading: boolean, loaderMessage: string | undefined }>()
);

export const removeComplaintReplySuccess = createAction(
    ComplaintActionType.REMOVE_COMPLAINT_REPLY_SUCCESS,
    props<{ removed: Boolean | any, messages: any[], success: boolean}>()
);


export const complaintReset = createAction(ComplaintActionType.COMPLAINT_RESET);

export const complaintLoading = createAction(
    ComplaintActionType.COMPLAINT_LOADING,
    props<{ loading: boolean, loaderMessage: string | undefined, success: boolean, messages: any[] }>()
);

export const complaintFailure = createAction(
    ComplaintActionType.COMPLAINT_FAILURE,
    props<{ messages: any[] }>()
);
