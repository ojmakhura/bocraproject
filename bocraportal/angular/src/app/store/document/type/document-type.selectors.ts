// Generated by andromda-angular cartridge (app\usecase\selector.store.ts.vsl) DO NOT EDIT
import { createFeatureSelector, createSelector } from '@ngrx/store';
import { documentTypeKey, DocumentTypeState } from './document-type.state';

export const selectDocumentTypeState = createFeatureSelector<DocumentTypeState>(documentTypeKey);

export const selectDocumentType = createSelector(
  selectDocumentTypeState,
  (state: DocumentTypeState) => state.documentType
);

export const selectDocumentTypes = createSelector(
  selectDocumentTypeState,
  (state: DocumentTypeState) => state.documentTypes
);

export const selectId = createSelector(selectDocumentTypeState, (state: DocumentTypeState) => state.id);

export const selectCriteria = createSelector(selectDocumentTypeState, (state: DocumentTypeState) => state.criteria);

export const selectRemoved = createSelector(selectDocumentTypeState, (state: DocumentTypeState) => state.removed);

export const selectMessages = createSelector(selectDocumentTypeState, (state: DocumentTypeState) => state.messages);

export const selectSuccess = createSelector(selectDocumentTypeState, (state: DocumentTypeState) => state.success);

export const selectLoading = createSelector(selectDocumentTypeState, (state: DocumentTypeState) => state.loading);

export const selectLoaderMessage = createSelector(
  selectDocumentTypeState,
  (state: DocumentTypeState) => state.loaderMessage
);

export const selectError = createSelector(selectDocumentTypeState, (state: DocumentTypeState) => state.error);
