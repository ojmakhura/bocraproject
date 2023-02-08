// Generated by andromda-angular cartridge (app\usecase\effect.store.ts.vsl) DO NOT EDIT
import { Injectable } from '@angular/core';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import { catchError, map, mergeMap } from 'rxjs/operators';
import * as DocumentActions from './document.actions';
import { DocumentRestController } from '@app/service/bw/org/bocra/portal/document/document-rest-controller';

@Injectable()
export class DocumentEffects {
  constructor(private actions$: Actions, private documentRestController: DocumentRestController) {}

  findById$ = createEffect(() =>
    this.actions$.pipe(
      ofType(DocumentActions.findById),
      mergeMap(({ id }) =>
        this.documentRestController.findById(id).pipe(
          map((document) =>
            DocumentActions.findByIdSuccess({
              document,
              messages: [`Document ${document.documentName} found.`],
              success: true,
            })
          ),
          catchError(({ error }) => [
            DocumentActions.documentFailure({ messages: [error?.error ? error.error : error] }),
          ])
        )
      )
    )
  );

  findByDocumentId$ = createEffect(() =>
    this.actions$.pipe(
      ofType(DocumentActions.findByDocumentId),
      mergeMap(({ documentId }) =>
        this.documentRestController.findByDocumentId(documentId).pipe(
          map((document) =>
            DocumentActions.findByDocumentIdSuccess({
              document,
              messages: [`Document ${document.documentName} found.`],
              success: true,
            })
          ),
          catchError(({ error }) => [
            DocumentActions.documentFailure({ messages: [error?.error ? error.error : error] }),
          ])
        )
      )
    )
  );

  save$ = createEffect(() =>
    this.actions$.pipe(
      ofType(DocumentActions.save),
      mergeMap(({ document }) =>
        this.documentRestController.save(document).pipe(
          map((document) =>
            DocumentActions.saveSuccess({
              document,
              messages: [`Document ${document.documentName} saved.`],
              success: true,
            })
          ),
          catchError(({ error }) => [
            DocumentActions.documentFailure({ messages: [error?.error ? error.error : error] }),
          ])
        )
      )
    )
  );

  remove$ = createEffect(() =>
    this.actions$.pipe(
      ofType(DocumentActions.remove),
      mergeMap(({ id }) =>
        this.documentRestController.remove(id).pipe(
          map((removed) =>
            DocumentActions.removeSuccess({
              removed,
              messages: [`Document ${id} removed.`],
              success: true,
            })
          ),
          catchError(({ error }) => [
            DocumentActions.documentFailure({ messages: [error?.error ? error.error : error] }),
          ])
        )
      )
    )
  );

  getAll$ = createEffect(() =>
    this.actions$.pipe(
      ofType(DocumentActions.getAll),
      mergeMap(() =>
        this.documentRestController.getAll().pipe(
          map((documents) =>
            DocumentActions.getAllSuccess({
              documents,
              messages: [`${documents.length} document found.`],
              success: true,
            })
          ),
          catchError(({ error }) => [
            DocumentActions.documentFailure({ messages: [error?.error ? error.error : error] }),
          ])
        )
      )
    )
  );

  search$ = createEffect(() =>
    this.actions$.pipe(
      ofType(DocumentActions.search),
      mergeMap(({ criteria }) =>
        this.documentRestController.search(criteria).pipe(
          map((documents) =>
            DocumentActions.searchSuccess({
              documents,
              messages: [`${documents.length} document found.`],
              success: true,
            })
          ),
          catchError(({ error }) => [
            DocumentActions.documentFailure({ messages: [error?.error ? error.error : error] }),
          ])
        )
      )
    )
  );

  getAllPaged$ = createEffect(() =>
    this.actions$.pipe(
      ofType(DocumentActions.getAllPaged),
      mergeMap(({ pageNumber, pageSize }) =>
        this.documentRestController.getAllPaged(pageNumber, pageSize).pipe(
          map((documents) =>
            DocumentActions.getAllPagedSuccess({
              documents,
              messages: [`Page ${pageNumber} found with ${documents.length} documents.`],
              success: true,
            })
          ),
          catchError(({ error }) => [
            DocumentActions.documentFailure({ messages: [error?.error ? error.error : error] }),
          ])
        )
      )
    )
  );

  uploadFile$ = createEffect(() =>
    this.actions$.pipe(
      ofType(DocumentActions.uploadFile),
      mergeMap(({ documentTypeId, file, fileName, metadataTarget, metadataTargetId }) =>
        this.documentRestController.uploadFile(documentTypeId, file, fileName, metadataTarget, metadataTargetId).pipe(
          map((document) =>
            DocumentActions.uploadFileSuccess({
              document,
              messages: [`Added  with ID  licensees.`],
              success: true,
            })
          ),
          catchError(({ error }) => [DocumentActions.documentFailure({ messages: [error] })])
        )
      )
    )
  );

  downloadFile$ = createEffect(() =>
    this.actions$.pipe(
      ofType(DocumentActions.downloadFile),
      mergeMap(({ documentId }) =>
        this.documentRestController.downloadFile(documentId).pipe(
          map((file) =>
            DocumentActions.downloadFileSuccess({
              file,
              messages: [`Document downloaded.`],
              success: true,
            })
          ),
          catchError(({ error }) => [
            DocumentActions.documentFailure({ messages: [error?.error ? error.error : error] }),
          ])
        )
      )
    )
  );
}
