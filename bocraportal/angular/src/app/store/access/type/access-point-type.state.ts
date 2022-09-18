// Generated by andromda-angular cartridge (app\usecase\state.store.ts.vsl) DO NOT EDIT
import { AccessPointTypeVO } from '@app/model/bw/org/bocra/portal/access/type/access-point-type-vo';

export const accessPointTypeKey = "accessPointType";

export interface AccessPointTypeState {
    id: number | any,
    accessPointType: AccessPointTypeVO | any,
    accessPointTypes: AccessPointTypeVO[] | any[],
    criteria: String | any,
    removed: false,
    loading: boolean,
    loaderMessage: string | undefined;
    success: boolean,
    error: boolean,
    messages: string[]
}

export const initialState: AccessPointTypeState = {
    id: null,
    accessPointType: null,
    accessPointTypes: [],
    criteria: null,
    removed: false,
    loading: false,
    success: false,
    error: false,
    messages: [],
    loaderMessage: undefined
};