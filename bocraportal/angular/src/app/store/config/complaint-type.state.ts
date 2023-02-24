// Generated by andromda-angular cartridge (app\usecase\state.store.ts.vsl) DO NOT EDIT
import { SystemConfigVO } from '@app/model/bw/org/bocra/portal/config/system-config-vo';

export const complaintTypeKey = "complaintType";

export interface ComplaintTypeState {
    systemConfig: SystemConfigVO | any;
    criteria: String | any;
    systemConfigs: Array<SystemConfigVO> | Array<any>;
    id: number | any;
    loaderMessage: string | undefined;
    loading: boolean;
    success: boolean;
    error: boolean;
    messages: string[];
}

export const initialState: ComplaintTypeState = {
    systemConfig: null,
    criteria: null,
    systemConfigs: [],
    id: null,
    loading: false,
    success: false,
    loaderMessage: undefined,
    error: false,
    messages: []
};