import { Injectable } from '@angular/core';
import { LicenseTypeRestControllerImpl } from '@app/service/bw/org/bocra/portal/type/license-type-rest-controller.impl';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import { catchError, map, mergeMap } from 'rxjs';
import * as LicenseTypeActions from './license-type.action';

@Injectable()
export class LicenseTypeEffects {
  constructor(private actions$: Actions, private licenseTypeService: LicenseTypeRestControllerImpl) {}

  saveLicenseType$ = createEffect(() => 
    this.actions$.pipe(
      ofType(LicenseTypeActions.saveLicenseType),
      mergeMap(({licenseType}) => this.licenseTypeService.save(licenseType).pipe(
        map(licenseType => LicenseTypeActions.saveLicenseTypeSuccess({licenseType})),
        catchError(({error}) => [LicenseTypeActions.licenseTypeActionFailure(error)])
      ))
    )
  );

  findById$ = createEffect(() => 
    this.actions$.pipe(
      ofType(LicenseTypeActions.findById),
      mergeMap(({id}) => this.licenseTypeService.findById(id).pipe(
        map(licenseType => LicenseTypeActions.findByIdSuccess({licenseType})),
        catchError(({error}) => [LicenseTypeActions.licenseTypeActionFailure(error)])
      ))
    )
  );

  loadAll$ = createEffect(() => 
    this.actions$.pipe(
      ofType(LicenseTypeActions.loadAll),
      mergeMap(() => this.licenseTypeService.getAll().pipe(
        map(licenseTypes => LicenseTypeActions.loadAllSuccess({licenseTypes})),
        catchError(({error}) => [LicenseTypeActions.licenseTypeActionFailure(error)])
      ))
    )
  );

  searchLicenseTypes$ = createEffect(() => 
    this.actions$.pipe(
      ofType(LicenseTypeActions.searchLicenseTypes),
      mergeMap(({searchCriteria}) => this.licenseTypeService.search(searchCriteria).pipe(
        map(licenseTypes => LicenseTypeActions.searchLicenseTypesSuccess({licenseTypes})),
        catchError(({error}) => [LicenseTypeActions.licenseTypeActionFailure(error)])
      ))
    )
  );
}