// Generated by andromda-angular cartridge (app\usecase\action.store.ts.vsl) DO NOT EDIT
import { createAction, props } from '@ngrx/store';
import { LicenseeVO } from '@app/model/bw/org/bocra/portal/licensee/licensee-vo';
import { LicenseeCriteria } from '@app/model/bw/org/bocra/portal/licensee/licensee-criteria';
import { LicenseeSectorVO } from '@model/bw/org/bocra/portal/licensee/sector/licensee-sector-vo';
import { DocumentVO } from '@app/model/bw/org/bocra/portal/document/document-vo';

export enum LicenseeActionType {
  FIND_BY_ID = '[Licensee] Find By Id',
  FIND_BY_ID_SUCCESS = '[Licensee] Find By Id Success',
  ADD_SECTOR = '[Licensee] Add Sector',
  ADD_SECTOR_SUCCESS = '[Licensee] Add Sector Success',
  REMOVE_SECTOR = '[Licensee] Remove Sector',
  REMOVE_SECTOR_SUCCESS = '[Licensee] Remove Sector Success',
  SAVE = '[Licensee] Save',
  SAVE_SUCCESS = '[Licensee] Save Success',
  REMOVE = '[Licensee] Remove',
  REMOVE_SUCCESS = '[Licensee] Remove Success',
  GET_ALL = '[Licensee] Get All',
  GET_ALL_SUCCESS = '[Licensee] Get All Success',
  SEARCH = '[Licensee] Search',
  SEARCH_SUCCESS = '[Licensee] Search Success',
  GET_ALL_PAGED = '[Licensee] Get All Paged',
  GET_ALL_PAGED_SUCCESS = '[Licensee] Get All Paged Success',
  LICENSEE_RESET = '[Licensee] Licensee Reset',
  LICENSEE_FAILURE = '[Licensee] Licensee Action Failure',
  LICENSEE_LOADING = '[Licensee] Licensee Loading',
  ADD_DOCUMENT = '[Licensee] Add Licensee Document',
  ADD_DOCUMENT_SUCCESS = '[Licensee] Add Licensee Document Succcess',
  REMOVE_DOCUMENT = '[Licensee] Remove Licensee Document',
  REMOVE_DOCUMENT_SUCCESS = '[Licensee] Remove Licensee Document Succcess',
}

export const findById = createAction(
  LicenseeActionType.FIND_BY_ID,
  props<{ id: number | any; loading: boolean; loaderMessage: string | undefined }>()
);

export const findByIdSuccess = createAction(
  LicenseeActionType.FIND_BY_ID_SUCCESS,
  props<{ licensee: LicenseeVO | any; messages: any[]; success: boolean }>()
);

export const save = createAction(
  LicenseeActionType.SAVE,
  props<{ licensee: LicenseeVO | any; loading: boolean; loaderMessage: string | undefined }>()
);

export const addSector = createAction(
  LicenseeActionType.ADD_SECTOR,
  props<{ licenseeId: number; sectorId: number; loading: boolean; loaderMessage: string | undefined }>()
);

export const addSectorSuccess = createAction(
  LicenseeActionType.ADD_SECTOR_SUCCESS,
  props<{ sector: LicenseeSectorVO | any; messages: any[]; success: boolean }>()
);

export const removeSector = createAction(
  LicenseeActionType.REMOVE_SECTOR,
  props<{ id: number; loading: boolean; loaderMessage: string | undefined }>()
);

export const removeSectorSuccess = createAction(
  LicenseeActionType.REMOVE_SECTOR_SUCCESS,
  props<{ messages: any[]; success: boolean }>()
);

export const addDocument = createAction(
  LicenseeActionType.ADD_DOCUMENT,
  props<{
    id: number;
    documentTypeId: number;
    file: File;
    fileName: string;
    loading: boolean;
    loaderMessage: string | undefined;
  }>()
);

export const addDocumentSuccess = createAction(
  LicenseeActionType.ADD_DOCUMENT_SUCCESS,
  props<{ document: DocumentVO | any; messages: any[]; success: boolean }>()
);

export const removeDocument = createAction(
  LicenseeActionType.REMOVE_DOCUMENT,
  props<{ id: number; loading: boolean; loaderMessage: string | undefined }>()
);

export const removeDocumentSuccess = createAction(
  LicenseeActionType.REMOVE_DOCUMENT_SUCCESS,
  props<{ messages: any[]; success: boolean }>()
);

export const saveSuccess = createAction(
  LicenseeActionType.SAVE_SUCCESS,
  props<{ licensee: LicenseeVO | any; messages: any[]; success: boolean }>()
);

export const remove = createAction(
  LicenseeActionType.REMOVE,
  props<{ id: number | any; loading: boolean; loaderMessage: string | undefined }>()
);

export const removeSuccess = createAction(
  LicenseeActionType.REMOVE_SUCCESS,
  props<{ removed: boolean | any; messages: any[]; success: boolean }>()
);

export const getAll = createAction(
  LicenseeActionType.GET_ALL,
  props<{ loading: boolean; loaderMessage: string | undefined }>()
);

export const getAllSuccess = createAction(
  LicenseeActionType.GET_ALL_SUCCESS,
  props<{ licensees: LicenseeVO[] | any[]; messages: any[]; success: boolean }>()
);

export const search = createAction(
  LicenseeActionType.SEARCH,
  props<{ criteria: LicenseeCriteria | any; loading: boolean; loaderMessage: string | undefined }>()
);

export const searchSuccess = createAction(
  LicenseeActionType.SEARCH_SUCCESS,
  props<{ licensees: LicenseeVO[] | any[]; messages: any[]; success: boolean }>()
);

export const getAllPaged = createAction(
  LicenseeActionType.GET_ALL_PAGED,
  props<{ pageNumber: number | any; pageSize: number | any; loading: boolean; loaderMessage: string | undefined }>()
);

export const getAllPagedSuccess = createAction(
  LicenseeActionType.GET_ALL_PAGED_SUCCESS,
  props<{ licensees: LicenseeVO[] | any[]; messages: any[]; success: boolean }>()
);

export const licenseeReset = createAction(LicenseeActionType.LICENSEE_RESET);

export const licenseeLoading = createAction(
  LicenseeActionType.LICENSEE_LOADING,
  props<{ loading: boolean; loaderMessage: string | undefined; success: boolean; messages: any[] }>()
);

export const licenseeFailure = createAction(LicenseeActionType.LICENSEE_FAILURE, props<{ messages: any[] }>());
