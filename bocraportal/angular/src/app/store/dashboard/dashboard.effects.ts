// Generated by andromda-angular cartridge (app\usecase\effect.store.ts.vsl) DO NOT EDIT
import { Injectable } from '@angular/core';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import { catchError, map, mergeMap } from 'rxjs/operators';
import * as DashboardActions from './dashboard.actions';

@Injectable()
export class DashboardEffects {
  constructor(private actions$: Actions) {}
}
