// Generated by andromda-angular cartridge (app\usecase\effect.store.ts.vsl) DO NOT EDIT
import { Injectable } from '@angular/core';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import { catchError, map, mergeMap } from 'rxjs/operators';
import * as FormSubmissionActions from './form-submission.actions';

@Injectable()
export class FormSubmissionEffects {

    constructor(private actions$: Actions) {}

}
