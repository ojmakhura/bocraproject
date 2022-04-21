// Generated by andromda-angular cartridge (app\usecase\state.store.ts.vsl) DO NOT EDIT
import { PeriodConfigCriteria } from '@app/model/bw/org/bocra/portal/period/config/period-config-criteria';
import { PeriodConfigVO } from '@app/model/bw/org/bocra/portal/period/config/period-config-vo';

export const periodConfigKey = "periodConfig";

export interface PeriodConfigState {
    periodConfigs: Array<PeriodConfigVO> | Array<any>,
    periodConfig: PeriodConfigVO | any,
    id: number | any,
    searchCriteria: PeriodConfigCriteria | any,
    removed: boolean,
    error: any
}

export const initialState: PeriodConfigState = {
    periodConfigs: [],
    periodConfig: null,
    id: null,
    searchCriteria: null,
    removed: false,
    error: null
};