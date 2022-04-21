// Generated by andromda-angular cartridge (app\usecase\state.store.ts.vsl) DO NOT EDIT
import { LicenseeVO } from '@app/model/bw/org/bocra/portal/licensee/licensee-vo';
import { LicenseeCriteria } from '@app/model/bw/org/bocra/portal/licensee/licensee-criteria';

export const licenseeKey = "licensee";

export interface LicenseeState {
    id: number | any,
    licensees: Array<LicenseeVO> | Array<any>,
    searchCriteria: LicenseeCriteria | any,
    licensee: LicenseeVO | any,
    removed: boolean,
    error: any
}

export const initialState: LicenseeState = {
    id: null,
    licensees: [],
    searchCriteria: null,
    licensee: null,
    removed: false,
    error: null
};