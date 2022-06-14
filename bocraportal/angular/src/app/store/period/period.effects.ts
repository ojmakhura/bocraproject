// Generated by andromda-angular cartridge (app\usecase\effect.store.ts.vsl) DO NOT EDIT
import { Injectable } from '@angular/core';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import { catchError, map, mergeMap } from 'rxjs/operators';
import * as PeriodActions from './period.actions';
import { PeriodRestControllerImpl } from '@app/service/bw/org/bocra/portal/period/period-rest-controller.impl';

@Injectable()
export class PeriodEffects {

    constructor(private actions$: Actions, private periodRestController: PeriodRestControllerImpl) {}

    findById$ = createEffect(() => 
         this.actions$.pipe(
            ofType(PeriodActions.findById),
            mergeMap(({ id }) => this.periodRestController.findById(id).pipe(
                map( period => PeriodActions.findByIdSuccess({
                    period,
                    success: true
                })),
                catchError(({errors}) => [PeriodActions.periodFailure(errors)])
            ))
        )
    );

    save$ = createEffect(() => 
         this.actions$.pipe(
            ofType(PeriodActions.save),
            mergeMap(({ period }) => this.periodRestController.save(period).pipe(
                map( period => PeriodActions.saveSuccess({
                    period,
                    success: true
                })),
                catchError(({errors}) => [PeriodActions.periodFailure(errors)])
            ))
        )
    );

    remove$ = createEffect(() => 
         this.actions$.pipe(
            ofType(PeriodActions.remove),
            mergeMap(({ id }) => this.periodRestController.remove(id).pipe(
                map( removed => PeriodActions.removeSuccess({
                    removed,
                    success: true
                })),
                catchError(({errors}) => [PeriodActions.periodFailure(errors)])
            ))
        )
    );

    getAll$ = createEffect(() => 
         this.actions$.pipe(
            ofType(PeriodActions.getAll),
            mergeMap(() => this.periodRestController.getAll().pipe(
                map( periods => PeriodActions.getAllSuccess({
                    periods,
                    success: true
                })),
                catchError(({errors}) => [PeriodActions.periodFailure(errors)])
            ))
        )
    );

    search$ = createEffect(() => 
         this.actions$.pipe(
            ofType(PeriodActions.search),
            mergeMap(({ criteria }) => this.periodRestController.search(criteria).pipe(
                map( periods => PeriodActions.searchSuccess({
                    periods,
                    success: true
                })),
                catchError(({errors}) => [PeriodActions.periodFailure(errors)])
            ))
        )
    );

    getAllPaged$ = createEffect(() => 
         this.actions$.pipe(
            ofType(PeriodActions.getAllPaged),
            mergeMap(({ pageNumber, pageSize }) => this.periodRestController.getAllPaged(pageNumber, pageSize).pipe(
                map( periods => PeriodActions.getAllPagedSuccess({
                    periods,
                    success: true
                })),
                catchError(({errors}) => [PeriodActions.periodFailure(errors)])
            ))
        )
    );

}
