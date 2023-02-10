// Generated by andromda-angular cartridge (app\usecase\effect.store.ts.vsl) DO NOT EDIT
import { Injectable } from '@angular/core';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import { catchError, map, mergeMap } from 'rxjs/operators';
import * as ComplaintActions from './complaint.actions';
import { ComplaintRestController } from '@app/service/bw/org/bocra/portal/complaint/complaint-rest-controller';

@Injectable()
export class ComplaintEffects {
  constructor(private actions$: Actions, private complaintRestController: ComplaintRestController) {}

  findById$ = createEffect(() =>
    this.actions$.pipe(
      ofType(ComplaintActions.findById),
      mergeMap(({ id }) =>
        this.complaintRestController.findById(id).pipe(
          map((complaint) =>
            ComplaintActions.findByIdSuccess({
              complaint,
              messages: [`Complaint ${complaint.complaintId} found.`],
              success: true,
            })
          ),
          catchError(({ error }) => [
            ComplaintActions.complaintFailure({ messages: [error?.error ? error?.error : error] }),
          ])
        )
      )
    )
  );

  findByComplaintId$ = createEffect(() =>
    this.actions$.pipe(
      ofType(ComplaintActions.findByComplaintId),
      mergeMap(({ complaintId }) =>
        this.complaintRestController.findByComplaintId(complaintId).pipe(
          map((complaint) =>
            ComplaintActions.findByComplaintIdSuccess({
              complaint,
              messages: [`Complaint ${complaint.complaintId} found.`],
              success: true,
            })
          ),
          catchError(({ error }) => [
            ComplaintActions.complaintFailure({ messages: [error?.error ? error?.error : error] }),
          ])
        )
      )
    )
  );

  save$ = createEffect(() =>
    this.actions$.pipe(
      ofType(ComplaintActions.save),
      mergeMap(({ complaint }) =>
        this.complaintRestController.save(complaint).pipe(
          map((complaint) =>
            ComplaintActions.saveSuccess({
              complaint,
              messages: [`Complaint ${complaint.complaintId} successful saved.`],
              success: true,
            })
          ),
          catchError(({ error }) => [
            ComplaintActions.complaintFailure({ messages: [error?.error ? error?.error : error] }),
          ])
        )
      )
    )
  );

  remove$ = createEffect(() =>
    this.actions$.pipe(
      ofType(ComplaintActions.remove),
      mergeMap(({ id }) =>
        this.complaintRestController.remove(id).pipe(
          map((removed) =>
            ComplaintActions.removeSuccess({ removed, messages: [`Complaint successful removed.`], success: true })
          ),
          catchError(({ error }) => [
            ComplaintActions.complaintFailure({ messages: [error?.error ? error?.error : error] }),
          ])
        )
      )
    )
  );

  getAll$ = createEffect(() =>
    this.actions$.pipe(
      ofType(ComplaintActions.getAll),
      mergeMap(({}) =>
        this.complaintRestController.getAll().pipe(
          map((complaints) =>
            ComplaintActions.getAllSuccess({
              complaints,
              messages: [`${complaints.length} complaints found.`],
              success: true,
            })
          ),
          catchError(({ error }) => [
            ComplaintActions.complaintFailure({ messages: [error?.error ? error?.error : error] }),
          ])
        )
      )
    )
  );

  search$ = createEffect(() =>
    this.actions$.pipe(
      ofType(ComplaintActions.search),
      mergeMap(({ criteria }) =>
        this.complaintRestController.search(criteria).pipe(
          map((complaints) =>
            ComplaintActions.searchSuccess({
              complaints,
              messages: [`${complaints.length} complaints found.`],
              success: true,
            })
          ),
          catchError(({ error }) => [
            ComplaintActions.complaintFailure({ messages: [error?.error ? error?.error : error] }),
          ])
        )
      )
    )
  );

  getAllPaged$ = createEffect(() =>
    this.actions$.pipe(
      ofType(ComplaintActions.getAllPaged),
      mergeMap(({ pageNumber, pageSize }) =>
        this.complaintRestController.getAllPaged(pageNumber, pageSize).pipe(
          map((complaints) =>
            ComplaintActions.getAllPagedSuccess({
              complaints,
              messages: [`Page ${pageNumber} found with ${pageSize} complaints.`],
              success: true,
            })
          ),
          catchError(({ error }) => [
            ComplaintActions.complaintFailure({ messages: [error?.error ? error?.error : error] }),
          ])
        )
      )
    )
  );

  addComplaintReply$ = createEffect(() =>
    this.actions$.pipe(
      ofType(ComplaintActions.addComplaintReply),
      mergeMap(({ complaintId, reply }) =>
        this.complaintRestController.addComplaintReply(complaintId, reply).pipe(
          map((complaintReply) =>
            ComplaintActions.addComplaintReplySuccess({
              complaintReply,
              messages: [`Complaint Reply added successfully.`],
              success: true,
            })
          ),
          catchError(({ error }) => [
            ComplaintActions.complaintFailure({ messages: [error?.error ? error?.error : error] }),
          ])
        )
      )
    )
  );

  removeComplaintReply$ = createEffect(() =>
    this.actions$.pipe(
      ofType(ComplaintActions.removeComplaintReply),
      mergeMap(({ id }) =>
        this.complaintRestController.removeComplaintReply(id).pipe(
          map((removed) =>
            ComplaintActions.removeComplaintReplySuccess({
              removed,
              messages: [`Complaint Reply removed successfully.`],
              success: true,
            })
          ),
          catchError(({ error }) => [
            ComplaintActions.complaintFailure({ messages: [error?.error ? error?.error : error] }),
          ])
        )
      )
    )
  );

  addDocument$ = createEffect(() =>
    this.actions$.pipe(
      ofType(ComplaintActions.addDocument),
      mergeMap(({ id, documentTypeId, file, fileName }) =>
        this.complaintRestController.addDocument(id, documentTypeId, file, fileName).pipe(
          map((document) =>
            ComplaintActions.addDocumentSuccess({
              document,
              messages: ['Added ${document.documentType.name} with ID ${$document.documentId}.'],
              success: true,
            })
          ),
          catchError(({ error }) => [
            ComplaintActions.complaintFailure({ messages: [error?.error ? error?.error : error] }),
          ])
        )
      )
    )
  );
}
