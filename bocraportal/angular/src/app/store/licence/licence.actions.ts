// Generated by andromda-angular cartridge (app\usecase\action.store.ts.vsl) DO NOT EDIT
import { createAction, props } from '@ngrx/store';
import { LicenseeVO } from '@app/model/bw/org/bocra/portal/licensee/licensee-vo';
import { LicenseeCriteria } from '@app/model/bw/org/bocra/portal/licensee/licensee-criteria';
import { DocumentVO } from '@app/model/bw/org/bocra/portal/document/document-vo';
import { LicenceVO } from '@app/model/bw/org/bocra/portal/licence/licence-vo';

export enum LicenceActionType {
    FIND_BY_ID = '[Licence] Find By Id',
    FIND_BY_ID_SUCCESS = '[Licence] Find By Id Success',
    SAVE = '[Licence] Save',
    SAVE_SUCCESS = '[Licence] Save Success',
    REMOVE = '[Licence] Remove',
    REMOVE_SUCCESS = '[Licence] Remove Success',
    GET_ALL = '[Licence] Get All',
    GET_ALL_SUCCESS = '[Licence] Get All Success',
    SEARCH = '[Licence] Search',
    SEARCH_SUCCESS = '[Licence] Search Success',
    GET_ALL_PAGED = '[Licence] Get All Paged',
    GET_ALL_PAGED_SUCCESS = '[Licence] Get All Paged Success',
    GET_LICENCE_DOCUMENTS = '[Licence] Get Licence Documents',
    GET_LICENCE_DOCUMENTS_SUCCESS = '[Licence] Get Licence Documents Success',
    GET_LICENSEE_DOCUMENTS = '[Licence] Get Licensee Documents',
    GET_LICENSEE_DOCUMENTS_SUCCESS = '[Licence] Get Licensee Documents Success',
    LICENCE_RESET = '[Licence] Licence Reset',
    LICENCE_FAILURE = '[Licence] Licence Action Failure',
    LICENCE_LOADING = '[Licence] Licence Loading'
}

export const findById = createAction(
    LicenceActionType.FIND_BY_ID,
    props<{ id: number | any , loading: boolean }>()
);

export const findByIdSuccess = createAction(
    LicenceActionType.FIND_BY_ID_SUCCESS,
    props<{ licence: LicenseeVO | any, success: boolean}>()
);

export const save = createAction(
    LicenceActionType.SAVE,
    props<{ licence: LicenseeVO | any , loading: boolean }>()
);

export const saveSuccess = createAction(
    LicenceActionType.SAVE_SUCCESS,
    props<{ licence: LicenseeVO | any, success: boolean}>()
);

export const remove = createAction(
    LicenceActionType.REMOVE,
    props<{ id: number | any , loading: boolean }>()
);

export const removeSuccess = createAction(
    LicenceActionType.REMOVE_SUCCESS,
    props<{ removed: boolean | any, success: boolean}>()
);

export const getAll = createAction(
    LicenceActionType.GET_ALL,
    props<{  loading: boolean }>()
);

export const getAllSuccess = createAction(
    LicenceActionType.GET_ALL_SUCCESS,
    props<{ licences: LicenceVO[] | any[], success: boolean}>()
);

export const search = createAction(
    LicenceActionType.SEARCH,
    props<{ criteria: LicenseeCriteria | any , loading: boolean }>()
);

export const searchSuccess = createAction(
    LicenceActionType.SEARCH_SUCCESS,
    props<{ licences: LicenceVO[] | any[], success: boolean}>()
);

export const getAllPaged = createAction(
    LicenceActionType.GET_ALL_PAGED,
    props<{ pageNumber: number | any , pageSize: number | any , loading: boolean }>()
);

export const getAllPagedSuccess = createAction(
    LicenceActionType.GET_ALL_PAGED_SUCCESS,
    props<{ licences: LicenceVO[] | any[], success: boolean}>()
);

export const getLicenseeDocuments = createAction(
    LicenceActionType.GET_LICENSEE_DOCUMENTS,
    props<{ licenseeId: number | any , loading: boolean }>()
);

export const getLicenseeDocumentsSuccess = createAction(
    LicenceActionType.GET_LICENSEE_DOCUMENTS_SUCCESS,
    props<{ documents: DocumentVO[] | any[], success: boolean}>()
);

export const getLicenceDocuments = createAction(
    LicenceActionType.GET_LICENCE_DOCUMENTS,
    props<{ licenceId: number | any , loading: boolean }>()
);

export const getLicenceDocumentsSuccess = createAction(
    LicenceActionType.GET_LICENCE_DOCUMENTS_SUCCESS,
    props<{ documents: DocumentVO[] | any[], success: boolean}>()
);


export const licenceReset = createAction(LicenceActionType.LICENCE_RESET);

export const licenceLoading = createAction(
    LicenceActionType.LICENCE_LOADING,
    props<{ loading: boolean, success: boolean, error: any }>()
);

export const licenceFailure = createAction(
    LicenceActionType.LICENCE_FAILURE,
    props<{ error: any, success: boolean, loading: boolean }>()
);
