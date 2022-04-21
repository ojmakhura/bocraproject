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
                map( period => PeriodActions.findByIdSuccess({period})),
                catchError(({error}) => [PeriodActions.periodFailure(error)])
            ))
        )
    );

    save$ = createEffect(() => 
         this.actions$.pipe(
            ofType(PeriodActions.save),
            mergeMap(({ period }) => this.periodRestController.save(period).pipe(
                map( period => PeriodActions.saveSuccess({period})),
                catchError(({error}) => [PeriodActions.periodFailure(error)])
            ))
        )
    );

    remove$ = createEffect(() => 
         this.actions$.pipe(
            ofType(PeriodActions.remove),
            mergeMap(({ id }) => this.periodRestController.remove(id).pipe(
                map( removed => PeriodActions.removeSuccess({removed})),
                catchError(({error}) => [PeriodActions.periodFailure(error)])
            ))
        )
    );

    getAll$ = createEffect(() => 
         this.actions$.pipe(
            ofType(PeriodActions.getAll),
            mergeMap(() => this.periodRestController.getAll().pipe(
                map( periods => PeriodActions.getAllSuccess({periods})),
                catchError(({error}) => [PeriodActions.periodFailure(error)])
            ))
        )
    );

    search$ = createEffect(() => 
         this.actions$.pipe(
            ofType(PeriodActions.search),
            mergeMap(({ searchCriteria }) => this.periodRestController.search(searchCriteria).pipe(
                map( periods => PeriodActions.searchSuccess({periods})),
                catchError(({error}) => [PeriodActions.periodFailure(error)])
            ))
        )
    );

    getAllPaged$ = createEffect(() => 
         this.actions$.pipe(
            ofType(PeriodActions.getAllPaged),
            mergeMap(({ pageNumber, pageSize }) => this.periodRestController.getAllPaged(pageNumber, pageSize).pipe(
                map( periods => PeriodActions.getAllPagedSuccess({periods})),
                catchError(({error}) => [PeriodActions.periodFailure(error)])
            ))
        )
    );

}
