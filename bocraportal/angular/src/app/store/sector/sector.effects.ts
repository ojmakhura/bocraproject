// Generated by andromda-angular cartridge (app\usecase\effect.store.ts.vsl) DO NOT EDIT
import { Injectable } from '@angular/core';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import { catchError, map, mergeMap } from 'rxjs/operators';
import * as SectorActions from './sector.actions';
import { SectorRestController } from '@app/service/bw/org/bocra/portal/sector/sector-rest-controller';

@Injectable()
export class SectorEffects {

    constructor(private actions$: Actions, private sectorRestController: SectorRestController) {}

    findById$ = createEffect(() => 
         this.actions$.pipe(
            ofType(SectorActions.findById),
            mergeMap(({ id }) => this.sectorRestController.findById(id).pipe(
                map( sector => SectorActions.findByIdSuccess({
                    sector,
                    messages: [`Sector for ${sector?.name} found.`],
                    success: false
                })),
                catchError(({error}) => [SectorActions.sectorFailure({messages: [error?.error ? error.error : error]})])
            ))
        )
    );

    save$ = createEffect(() => 
         this.actions$.pipe(
            ofType(SectorActions.save),
            mergeMap(({ sector }) => this.sectorRestController.save(sector).pipe(
                map( sector => SectorActions.saveSuccess({
                    sector,
                    messages: [`Sector for ${sector?.name} saved.`],
                    success: true
                })),
                catchError(({error}) => [SectorActions.sectorFailure({messages: [error?.error ? error.error : error]})])
            ))
        )
    );

    addLicensee$ = createEffect(() => 
         this.actions$.pipe(
            ofType(SectorActions.addLicensee),
            mergeMap(({ sectorId, licenseeId }) => this.sectorRestController.addLicensee(sectorId, licenseeId).pipe(
                map( licensee => SectorActions.addLicenseeSuccess({
                    licensee,
                    messages: [`Licensee for ${licensee?.name} added to sector.`],
                    success: true
                })),
                catchError(({error}) => [SectorActions.sectorFailure({messages: [error?.error ? error.error : error]})])
            ))
        )
    );

    remove$ = createEffect(() => 
         this.actions$.pipe(
            ofType(SectorActions.remove),
            mergeMap(({ id }) => this.sectorRestController.remove(id).pipe(
                map( removed => SectorActions.removeSuccess({
                    removed,
                    messages: [`Sector successfully removed.`],
                    success: true
                })),
                catchError(({error}) => [SectorActions.sectorFailure({messages: [error?.error ? error.error : error]})])
            ))
        )
    );

    getAll$ = createEffect(() => 
         this.actions$.pipe(
            ofType(SectorActions.getAll),
            mergeMap(() => this.sectorRestController.getAll().pipe(
                map( sectors => SectorActions.getAllSuccess({
                    sectors,
                    messages: [`${sectors.length} sectors found.`],
                    success: true
                })),
                catchError(({error}) => [SectorActions.sectorFailure({messages: [error?.error ? error.error : error]})])
            ))
        )
    );

    search$ = createEffect(() => 
         this.actions$.pipe(
            ofType(SectorActions.search),
            mergeMap(({ criteria }) => this.sectorRestController.search(criteria).pipe(
                map( sectors => SectorActions.searchSuccess({
                    sectors,
                    messages: [`${sectors.length} sectors found.`],
                    success: true
                })),
                catchError(({error}) => [SectorActions.sectorFailure({messages: [error?.error ? error.error : error]})])
            ))
        )
    );

    getAllPaged$ = createEffect(() => 
         this.actions$.pipe(
            ofType(SectorActions.getAllPaged),
            mergeMap(({ pageNumber, pageSize }) => this.sectorRestController.getAllPaged(pageNumber, pageSize).pipe(
                map( sectors => SectorActions.getAllPagedSuccess({
                    sectors,
                    messages: [`Page ${pageNumber} found with ${pageSize} sectors.`],
                    success: true
                })),
                catchError(({error}) => [SectorActions.sectorFailure({messages: [error?.error ? error.error : error]})])
            ))
        )
    );

}
