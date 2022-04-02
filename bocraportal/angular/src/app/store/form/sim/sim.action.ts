// Generated by andromda-angular cartridge (app\usecase\action.store.ts.vsl) DO NOT EDIT
import { createAction, props } from '@ngrx/store';
import { FormCriteria } from '@app/model/bw/org/bocra/portal/form/form-criteria';
import { SimVO } from '@app/model/bw/org/bocra/portal/form/sim/sim-vo';

export enum SimActionType {
    SAVE_SIM = '[Sim] Save Sim',
    SAVE_SIM_SUCCESS = '[Sim] Save Sim Success',
    FIND_BY_ID = '[Sim] Find By Id',
    FIND_BY_ID_SUCCESS = '[Sim] Find By Id Success',
    LOAD_ALL = '[Sim] Load All',
    LOAD_ALL_SUCCESS = '[Sim] Load All Success',
    SEARCH_SIMS = '[Sim] Search Sims',
    SEARCH_SIMS_SUCCESS = '[Sim] Search Sims Success',
    RESET = '[Sim] Reset',
    ACTION_FAILURE = '[Sim] Action Failure'
}

export const saveSim = createAction(
    SimActionType.SAVE_SIM,
    props<{ sim: SimVO }>()
);

export const saveSimSuccess = createAction(
    SimActionType.SAVE_SIM_SUCCESS,
    props<{ sim: any }>()
);

export const findById = createAction(
    SimActionType.FIND_BY_ID,
    props<{ id: number }>()
);

export const findByIdSuccess = createAction(
    SimActionType.FIND_BY_ID_SUCCESS,
    props<{ sim: SimVO }>()
);

export const loadAll = createAction(
    SimActionType.LOAD_ALL
);

export const loadAllSuccess = createAction(
    SimActionType.LOAD_ALL_SUCCESS,
    props<{ sims: SimVO[] }>()
);

export const searchSims = createAction(
    SimActionType.SEARCH_SIMS,
    props<{ searchCriteria: FormCriteria }>()
);

export const searchSimsSuccess = createAction(
    SimActionType.SEARCH_SIMS_SUCCESS,
    props<{ sims: SimVO[] }>()
);

export const reset = createAction(SimActionType.RESET);

export const simActionFailure = createAction(
    SimActionType.ACTION_FAILURE,
    props<{ error: any }>()
);