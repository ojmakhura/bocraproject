// Generated by andromda-angular cartridge (app\usecase\action.store.ts.vsl) DO NOT EDIT
import { createAction, props } from '@ngrx/store';
import { FormActivationVO } from '@app/model/bw/org/bocra/portal/form/activation/form-activation-vo';
import { FormActivationCriteria } from '@app/model/bw/org/bocra/portal/form/activation/form-activation-criteria';
import { DataPage } from '@app/model/bw/org/bocra/portal/data-page';
import { FormSubmissionVO } from '@app/model/bw/org/bocra/portal/form/submission/form-submission-vo';

export enum FormActivationActionType {
  FIND_BY_ID = '[FormActivation] Find By Id',
  FIND_BY_ID_SUCCESS = '[FormActivation] Find By Id Success',
  SAVE = '[FormActivation] Save',
  SAVE_SUCCESS = '[FormActivation] Save Success',
  REMOVE = '[FormActivation] Remove',
  REMOVE_SUCCESS = '[FormActivation] Remove Success',
  GET_ALL = '[FormActivation] Get All',
  GET_ALL_SUCCESS = '[FormActivation] Get All Success',
  SEARCH = '[FormActivation] Search',
  SEARCH_SUCCESS = '[FormActivation] Search Success',
  PAGED_SEARCH = '[FormActivation] Paged Search',
  PAGED_SEARCH_SUCCESS = '[FormActivation] Paged Search Success',
  GET_ALL_PAGED = '[FormActivation] Get All Paged',
  GET_ALL_PAGED_SUCCESS = '[FormActivation] Get All Paged Success',
  RECREATE_ACTIVATION_SUBMISSIONS = '[FormActivation] Recreate Activation Submissions',
  RECREATE_ACTIVATION_SUBMISSIONS_SUCCESS = '[FormActivation] Recreate Activation Submissions Success',
  CREATE_MISSING_SUBMISSIONS = '[FormActivation] Create Missing Submissions',
  CREATE_MISSING_SUBMISSIONS_SUCCESS = '[FormActivation] Create Missing Submissions Success',
  ACTIVATE_FOR = '[FormActivation] Activate For',
  ACTIVATE_FOR_SUCCESS = '[FormActivation] Activate For Success',
  FORM_ACTIVATION_RESET = '[FormActivation] FormActivation Reset',
  FORM_ACTIVATION_FAILURE = '[FormActivation] FormActivation Action Failure',
  FORM_ACTIVATION_LOADING = '[FormActivation] FormActivation Loading',
}

export const recreateActivationSubmissions = createAction(
  FormActivationActionType.RECREATE_ACTIVATION_SUBMISSIONS,
  props<{ id: number | any; loading: boolean; loaderMessage: string | undefined }>()
);

export const recreateActivationSubmissionsSuccess = createAction(
  FormActivationActionType.RECREATE_ACTIVATION_SUBMISSIONS_SUCCESS,
  props<{ formSubmissions: FormSubmissionVO[] | any[]; messages: any[]; success: boolean }>()
);

export const createMissingSubmissions = createAction(
  FormActivationActionType.CREATE_MISSING_SUBMISSIONS,
  props<{ id: number | any; loading: boolean; loaderMessage: string | undefined }>()
);

export const createMissingSubmissionsSuccess = createAction(
  FormActivationActionType.CREATE_MISSING_SUBMISSIONS_SUCCESS,
  props<{ formSubmissions: FormSubmissionVO[] | any[]; messages: any[]; success: boolean }>()
);

export const findById = createAction(
  FormActivationActionType.FIND_BY_ID,
  props<{ id: number | any; loading: boolean; loaderMessage: string | undefined }>()
);

export const findByIdSuccess = createAction(
  FormActivationActionType.FIND_BY_ID_SUCCESS,
  props<{ formActivation: FormActivationVO | any; messages: any[]; success: boolean }>()
);

export const save = createAction(
  FormActivationActionType.SAVE,
  props<{ formActivation: FormActivationVO | any; loading: boolean; loaderMessage: string | undefined }>()
);

export const saveSuccess = createAction(
  FormActivationActionType.SAVE_SUCCESS,
  props<{ formActivation: FormActivationVO | any; messages: any[]; success: boolean }>()
);

export const remove = createAction(
  FormActivationActionType.REMOVE,
  props<{ id: number | any; loading: boolean; loaderMessage: string | undefined }>()
);

export const removeSuccess = createAction(
  FormActivationActionType.REMOVE_SUCCESS,
  props<{ removed: boolean | any; messages: any[]; success: boolean }>()
);

export const getAll = createAction(
  FormActivationActionType.GET_ALL,
  props<{ loading: boolean; loaderMessage: string | undefined }>()
);

export const getAllSuccess = createAction(
  FormActivationActionType.GET_ALL_SUCCESS,
  props<{ formActivations: FormActivationVO[] | any[]; messages: any[]; success: boolean }>()
);

export const search = createAction(
  FormActivationActionType.SEARCH,
  props<{ criteria: FormActivationCriteria | any; loading: boolean; loaderMessage: string | undefined }>()
);

export const searchSuccess = createAction(
  FormActivationActionType.SEARCH_SUCCESS,
  props<{ formActivations: FormActivationVO[] | any[]; messages: any[]; success: boolean }>()
);

export const activateFor = createAction(
  FormActivationActionType.ACTIVATE_FOR,
  props<{ activationDate: Date; periodConfigId: number, sendEmail: boolean; loading: boolean; loaderMessage: string | undefined }>()
);

export const activateForSuccess = createAction(
  FormActivationActionType.ACTIVATE_FOR_SUCCESS,
  props<{ formActivations: FormActivationVO[] | any[]; messages: any[]; success: boolean }>()
);

export const getAllPaged = createAction(
  FormActivationActionType.GET_ALL_PAGED,
  props<{ pageNumber: number | any; pageSize: number | any; loading: boolean; loaderMessage: string | undefined }>()
);

export const getAllPagedSuccess = createAction(
  FormActivationActionType.GET_ALL_PAGED_SUCCESS,
  props<{ formActivations: FormActivationVO[] | any[]; messages: any[]; success: boolean }>()
);

export const pagedSearch = createAction(
  FormActivationActionType.PAGED_SEARCH,
  props<{
    pageNumber: number | any;
    pageSize: number | any;
    criteria: FormActivationCriteria | any;
    loading: boolean;
    loaderMessage: string | undefined;
  }>()
);

export const pagedSearchSuccess = createAction(
  FormActivationActionType.PAGED_SEARCH_SUCCESS,
  props<{ formActivationsPage: DataPage | any; messages: any[]; success: boolean }>()
);

export const formActivationReset = createAction(FormActivationActionType.FORM_ACTIVATION_RESET);

export const formActivationLoading = createAction(
  FormActivationActionType.FORM_ACTIVATION_LOADING,
  props<{ loading: boolean; loaderMessage: string | undefined; success: boolean; messages: any[] }>()
);

export const formActivationFailure = createAction(
  FormActivationActionType.FORM_ACTIVATION_FAILURE,
  props<{ messages: any[] }>()
);
