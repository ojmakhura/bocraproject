// Generated by andromda-angular cartridge (app\usecase\state.store.ts.vsl) DO NOT EDIT
import { LicenceVO } from '@app/model/bw/org/bocra/portal/licence/licence-vo';
import { LicenseeCriteria } from '@app/model/bw/org/bocra/portal/licensee/licensee-criteria';
import { DocumentVO } from '@app/model/bw/org/bocra/portal/document/document-vo';

export const licenceKey = "licence";

export interface LicenceState {
    licence: LicenceVO | any,
    licences: Array<LicenceVO> | Array<any>,
    id: number | any,
    criteria: LicenseeCriteria | any,
    documents: DocumentVO[] | any[],
    removed: boolean,
    success: boolean,
    loading: boolean,
    error: any
}

export const initialState: LicenceState = {
    licence: null,
    licences: [],
    id: null,
    criteria: null,
    removed: false,
    documents: [],
    success: false,
    loading: false,
    error: null
};