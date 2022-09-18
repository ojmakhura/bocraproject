// Generated by andromda-angular cartridge (app\usecase\state.store.ts.vsl) DO NOT EDIT
import { LicenseeVO } from '@app/model/bw/org/bocra/portal/licensee/licensee-vo';
import { LicenseeSectorVO } from '@model/bw/org/bocra/portal/licensee/sector/licensee-sector-vo';
import { SectorVO } from '@model/bw/org/bocra/portal/sector/sector-vo';

export const licenseeSectorKey = "licenseeSector";

export interface LicenseeSectorState {
    id: number | any,
    licenseeSectors: Array<LicenseeSectorVO> | Array<any>,
    licenseeSector: LicenseeSectorVO | any,
    licensee: LicenseeVO | any,
    sector: SectorVO | any,
    removed: boolean,
    success: boolean,
    loading: boolean,
    loaderMessage: string | undefined,
    error: boolean,
    messages: any[]
}

export const initialState: LicenseeSectorState = {
    id: undefined,
    licenseeSectors: [],
    licenseeSector: undefined,
    licensee: undefined,
    sector: undefined,
    removed: false,
    success: false,
    loading: false,
    error: false,
    messages: [],
    loaderMessage: undefined
};