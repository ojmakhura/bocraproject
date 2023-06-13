// Generated by andromda-angular cartridge (app\usecase\action.store.ts.vsl) DO NOT EDIT
import { createAction, props } from '@ngrx/store';
import { FormSubmissionCriteria } from '@app/model/bw/org/bocra/portal/form/submission/form-submission-criteria';
import { FormSubmissionVO } from '@app/model/bw/org/bocra/portal/form/submission/form-submission-vo';
import { NoteVO } from '@model/bw/org/bocra/portal/form/submission/note/note-vo';
import { FormSubmissionStatus } from '@app/model/bw/org/bocra/portal/form/submission/form-submission-status';
import { DataPage } from '@app/model/bw/org/bocra/portal/data-page';

export enum FormSubmissionActionType {
  FIND_BY_ID = '[FormSubmission] Find By Id',
  FIND_BY_ID_SUCCESS = '[FormSubmission] Find By Id Success',
  FIND_BY_IDS = '[FormSubmission] Find By Ids',
  FIND_BY_IDS_SUCCESS = '[FormSubmission] Find By Ids Success',
  SAVE = '[FormSubmission] Save',
  SAVE_SUCCESS = '[FormSubmission] Save Success',
  SAVE_NOTE = '[FormSubmission] Save Note',
  SAVE_NOTE_SUCCESS = '[FormSubmission] Save Note Success',
  UPDATE_STATUS = '[FormSubmission] Update Form Submission Status',
  UPDATE_STATUS_SUCCESS = '[FormSubmission] Update Form Submission Status Success',
  REMOVE = '[FormSubmission] Remove',
  REMOVE_SUCCESS = '[FormSubmission] Remove Success',
  GET_ALL = '[FormSubmission] Get All',
  GET_ALL_SUCCESS = '[FormSubmission] Get All Success',
  SEARCH = '[FormSubmission] Search',
  SEARCH_SUCCESS = '[FormSubmission] Search Success',
  PAGED_SEARCH = '[FormSubmission] Paged Search',
  PAGED_SEARCH_SUCCESS = '[FormSubmission] Paged Search Success',
  GET_ALL_PAGED = '[FormSubmission] Get All Paged',
  GET_ALL_PAGED_SUCCESS = '[FormSubmission] Get All Paged Success',
  SET_LOADING = '[FormSubmission] Set Loading',
  UPLOAD_DATA = '[FormSubmission] Upload Submission Data',
  UPLOAD_DATA_SUCCESS = '[FormSubmission] Upload Submission Data Success',
  FORM_SUBMISSION_RESET = '[FormSubmission] Form Submission Reset',
  FORM_SUBMISSION_FAILURE = '[FormSubmission] Form Submission Action Failure',
  FORM_SUBMISSION_LOADING = '[FormSubmission] Form Submission Loading',
}

export const setLoading = createAction(
  FormSubmissionActionType.SET_LOADING,
  props<{ loading: boolean }>()
);

export const findById = createAction(
  FormSubmissionActionType.FIND_BY_ID,
  props<{ id: number | any; loading: boolean; loaderMessage: string | undefined }>()
);

export const findByIdSuccess = createAction(
  FormSubmissionActionType.FIND_BY_ID_SUCCESS,
  props<{ formSubmission: FormSubmissionVO | any; messages: any[]; success: boolean }>()
);

export const findByIds = createAction(
  FormSubmissionActionType.FIND_BY_IDS,
  props<{ ids: number[] | any[]; loading: boolean; loaderMessage: string | undefined }>()
);

export const findByIdsSuccess = createAction(
  FormSubmissionActionType.FIND_BY_IDS_SUCCESS,
  props<{ formSubmissions: FormSubmissionVO[] | any[]; messages: any[]; success: boolean }>()
);

export const save = createAction(
  FormSubmissionActionType.SAVE,
  props<{ formSubmission: FormSubmissionVO | any; loading: boolean; loaderMessage: string | undefined }>()
);

export const saveSuccess = createAction(
  FormSubmissionActionType.SAVE_SUCCESS,
  props<{ formSubmission: FormSubmissionVO | any; messages: any[]; success: boolean }>()
);

export const updateStatus = createAction(
  FormSubmissionActionType.UPDATE_STATUS,
  props<{
    id: number | any;
    submissionStatus: FormSubmissionStatus | any;
    updateTime: Date;
    username: string | any;
    loading: boolean;
    loaderMessage: string | undefined;
  }>()
);

export const updateStatusSuccess = createAction(
  FormSubmissionActionType.UPDATE_STATUS_SUCCESS,
  props<{ statusUpdated: boolean | any; messages: any[]; success: boolean }>()
);

export const uploadData = createAction(
  FormSubmissionActionType.UPLOAD_DATA,
  props<{
    submissionId: number | any;
    file: File | any;
    sendEmail: boolean;
    loading: boolean;
    loaderMessage: string | undefined;
  }>()
);

export const uploadDataSuccess = createAction(
  FormSubmissionActionType.UPLOAD_DATA_SUCCESS,
  props<{ formSubmission: FormSubmissionVO | any; messages: any[]; success: boolean }>()
);

export const saveNote = createAction(
  FormSubmissionActionType.SAVE_NOTE,
  props<{ note: NoteVO | any; loading: boolean; loaderMessage: string | undefined }>()
);

export const saveNoteSuccess = createAction(
  FormSubmissionActionType.SAVE_NOTE_SUCCESS,
  props<{ note: NoteVO | any; messages: any[]; success: boolean }>()
);

export const remove = createAction(
  FormSubmissionActionType.REMOVE,
  props<{ id: number | any; loading: boolean; loaderMessage: string | undefined }>()
);

export const removeSuccess = createAction(
  FormSubmissionActionType.REMOVE_SUCCESS,
  props<{ removed: boolean | any; messages: any[]; success: boolean }>()
);

export const getAll = createAction(
  FormSubmissionActionType.GET_ALL,
  props<{ loading: boolean; loaderMessage: string | undefined }>()
);

export const getAllSuccess = createAction(
  FormSubmissionActionType.GET_ALL_SUCCESS,
  props<{ formSubmissions: FormSubmissionVO[] | any[]; messages: any[]; success: boolean }>()
);

export const search = createAction(
  FormSubmissionActionType.SEARCH,
  props<{ criteria: FormSubmissionCriteria | any; loading: boolean; loaderMessage: string | undefined }>()
);

export const searchSuccess = createAction(
  FormSubmissionActionType.SEARCH_SUCCESS,
  props<{ formSubmissions: FormSubmissionVO[] | any[]; messages: any[]; success: boolean }>()
);

export const pagedSearch = createAction(
  FormSubmissionActionType.PAGED_SEARCH,
  props<{
    pageNumber: number | any;
    pageSize: number | any;
    criteria: FormSubmissionCriteria | any;
    loading: boolean;
    loaderMessage: string | undefined;
  }>()
);

export const pagedSearchSuccess = createAction(
  FormSubmissionActionType.PAGED_SEARCH_SUCCESS,
  props<{ formSubmissionsPage: DataPage | any; messages: any[]; success: boolean }>()
);

export const getAllPaged = createAction(
  FormSubmissionActionType.GET_ALL_PAGED,
  props<{ pageNumber: number | any; pageSize: number | any; loading: boolean; loaderMessage: string | undefined }>()
);

export const getAllPagedSuccess = createAction(
  FormSubmissionActionType.GET_ALL_PAGED_SUCCESS,
  props<{ formSubmissions: FormSubmissionVO[] | any[]; messages: any[]; success: boolean }>()
);

export const formSubmissionReset = createAction(FormSubmissionActionType.FORM_SUBMISSION_RESET);

export const formSubmissionLoading = createAction(
  FormSubmissionActionType.FORM_SUBMISSION_LOADING,
  props<{ loading: boolean; loaderMessage: string | undefined; success: boolean; messages: any[] }>()
);

export const formSubmissionFailure = createAction(
  FormSubmissionActionType.FORM_SUBMISSION_FAILURE,
  props<{ messages: any[] }>()
);
