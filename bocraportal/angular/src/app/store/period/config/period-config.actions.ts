// Generated by andromda-angular cartridge (app\usecase\action.store.ts.vsl) DO NOT EDIT
import { createAction, props } from '@ngrx/store';
import { PeriodConfigVO } from '@app/model/bw/org/bocra/portal/period/config/period-config-vo';
import { PeriodConfigCriteria } from '@app/model/bw/org/bocra/portal/period/config/period-config-criteria';

export enum PeriodConfigActionType {
    FIND_BY_ID = '[PeriodConfig] Find By Id',
    FIND_BY_ID_SUCCESS = '[PeriodConfig] Find By Id Success',
    SAVE = '[PeriodConfig] Save',
    SAVE_SUCCESS = '[PeriodConfig] Save Success',
    REMOVE = '[PeriodConfig] Remove',
    REMOVE_SUCCESS = '[PeriodConfig] Remove Success',
    GET_ALL = '[PeriodConfig] Get All',
    GET_ALL_SUCCESS = '[PeriodConfig] Get All Success',
    SEARCH = '[PeriodConfig] Search',
    SEARCH_SUCCESS = '[PeriodConfig] Search Success',
    GET_ALL_PAGED = '[PeriodConfig] Get All Paged',
    GET_ALL_PAGED_SUCCESS = '[PeriodConfig] Get All Paged Success',
    PERIOD_CONFIG_RESET = '[PeriodConfig] PeriodConfig Reset',
    PERIOD_CONFIG_FAILURE = '[PeriodConfig] PeriodConfig Action Failure'
}

export const findById = createAction(
    PeriodConfigActionType.FIND_BY_ID,
    props<{ id: number | any  }>()
);

export const findByIdSuccess = createAction(
    PeriodConfigActionType.FIND_BY_ID_SUCCESS,
    props<{ periodConfig: PeriodConfigVO | any }>()
);

export const save = createAction(
    PeriodConfigActionType.SAVE,
    props<{ periodConfig: PeriodConfigVO | any  }>()
);

export const saveSuccess = createAction(
    PeriodConfigActionType.SAVE_SUCCESS,
    props<{ periodConfig: PeriodConfigVO | any }>()
);

export const remove = createAction(
    PeriodConfigActionType.REMOVE,
    props<{ id: number | any  }>()
);

export const removeSuccess = createAction(
    PeriodConfigActionType.REMOVE_SUCCESS,
    props<{ removed: boolean | any }>()
);

export const getAll = createAction(
    PeriodConfigActionType.GET_ALL
);

export const getAllSuccess = createAction(
    PeriodConfigActionType.GET_ALL_SUCCESS,
    props<{ periodConfigs: PeriodConfigVO[] | any[] }>()
);

export const search = createAction(
    PeriodConfigActionType.SEARCH,
    props<{ criteria: PeriodConfigCriteria | any  }>()
);

export const searchSuccess = createAction(
    PeriodConfigActionType.SEARCH_SUCCESS,
    props<{ periodConfigs: PeriodConfigVO[] | any[] }>()
);

export const getAllPaged = createAction(
    PeriodConfigActionType.GET_ALL_PAGED,
    props<{ pageNumber: number | any , pageSize: number | any  }>()
);

export const getAllPagedSuccess = createAction(
    PeriodConfigActionType.GET_ALL_PAGED_SUCCESS,
    props<{ periodConfigs: PeriodConfigVO[] | any[] }>()
);

export const periodConfigReset = createAction(PeriodConfigActionType.PERIOD_CONFIG_RESET);

export const periodConfigFailure = createAction(
    PeriodConfigActionType.PERIOD_CONFIG_FAILURE,
    props<{ error: any }>()
);
