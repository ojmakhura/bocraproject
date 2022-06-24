// Generated by andromda-angular cartridge (app\usecase\effect.store.ts.vsl) DO NOT EDIT
import { Injectable } from '@angular/core';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import { catchError, map, mergeMap } from 'rxjs/operators';
import * as SectorActions from './sector.actions';
import { SectorRestControllerImpl } from '@app/service/bw/org/bocra/portal/sector/sector-rest-controller.impl';
import { LicenseeSectorVO } from '@app/model/bw/org/bocra/portal/licensee/licensee-sector-vo';

@Injectable()
export class SectorEffects {

    constructor(private actions$: Actions, private sectorRestController: SectorRestControllerImpl) {}

    findById$ = createEffect(() => 
         this.actions$.pipe(
            ofType(SectorActions.findById),
            mergeMap(({ id }) => this.sectorRestController.findById(id).pipe(
                map( sector => SectorActions.findByIdSuccess({
                    sector,
                    success: true
                })),
                catchError(({error}) => [SectorActions.sectorFailure({messages: [error]})])
            ))
        )
    );

    save$ = createEffect(() => 
         this.actions$.pipe(
            ofType(SectorActions.save),
            mergeMap(({ sector }) => this.sectorRestController.save(sector).pipe(
                map( sector => SectorActions.saveSuccess({
                    sector,
                    success: true
                })),
                catchError(({error}) => [SectorActions.sectorFailure({messages: [error]})])
            ))
        )
    );

    addLicensee$ = createEffect(() => 
         this.actions$.pipe(
            ofType(SectorActions.addLicensee),
            mergeMap(({ sectorId, licenseeId }) => this.sectorRestController.addLicensee(sectorId, licenseeId).pipe(
                map( licensee => SectorActions.addLicenseeSuccess({
                    licensee,
                    success: true
                })),
                catchError(({error}) => [SectorActions.sectorFailure({messages: [error]})])
            ))
        )
    );

    remove$ = createEffect(() => 
         this.actions$.pipe(
            ofType(SectorActions.remove),
            mergeMap(({ id }) => this.sectorRestController.remove(id).pipe(
                map( removed => SectorActions.removeSuccess({
                    removed,
                    success: true
                })),
                catchError(({error}) => [SectorActions.sectorFailure({messages: [error]})])
            ))
        )
    );

    getAll$ = createEffect(() => 
         this.actions$.pipe(
            ofType(SectorActions.getAll),
            mergeMap(() => this.sectorRestController.getAll().pipe(
                map( sectors => SectorActions.getAllSuccess({
                    sectors,
                    success: true
                })),
                catchError(({error}) => [SectorActions.sectorFailure({messages: [error]})])
            ))
        )
    );

    search$ = createEffect(() => 
         this.actions$.pipe(
            ofType(SectorActions.search),
            mergeMap(({ criteria }) => this.sectorRestController.search(criteria).pipe(
                map( sectors => SectorActions.searchSuccess({
                    sectors,
                    success: true
                })),
                catchError(({error}) => [SectorActions.sectorFailure({messages: [error]})])
            ))
        )
    );

    getAllPaged$ = createEffect(() => 
         this.actions$.pipe(
            ofType(SectorActions.getAllPaged),
            mergeMap(({ pageNumber, pageSize }) => this.sectorRestController.getAllPaged(pageNumber, pageSize).pipe(
                map( sectors => SectorActions.getAllPagedSuccess({
                    sectors,
                    success: true
                })),
                catchError(({error}) => [SectorActions.sectorFailure({messages: [error]})])
            ))
        )
    );

}
