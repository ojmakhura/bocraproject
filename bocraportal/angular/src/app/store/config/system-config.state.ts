// Generated by andromda-angular cartridge (app\usecase\state.store.ts.vsl) DO NOT EDIT
import { SystemConfigVO } from '@app/model/bw/org/bocra/portal/config/system-config-vo';

export const systemConfigKey = "systemConfig";

export interface SystemConfigState {
    id: number | any;
    systemConfig: SystemConfigVO | any;
    criteria: String | any;
    systemConfigs: Array<SystemConfigVO> | Array<any>;
    loaderMessage: string | undefined;
    loading: boolean;
    removed: boolean;
    success: boolean;
    error: boolean;
    messages: string[];
}

export const initialState: SystemConfigState = {
    id: null,
    systemConfig: null,
    criteria: null,
    systemConfigs: [],
    removed: false,
    loading: false,
    success: false,
    loaderMessage: undefined,
    error: false,
    messages: []
};