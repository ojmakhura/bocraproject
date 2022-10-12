// Generated by andromda-angular cartridge (app\usecase\state.store.ts.vsl) DO NOT EDIT
import { FormSubmissionVO } from '@app/model/bw/org/bocra/portal/form/submission/form-submission-vo';

export const licenceTypeKey = "licenceType";

export interface LicenceTypeState {
    formSubmissions: Array<FormSubmissionVO> | Array<any>;
    loaderMessage: string | undefined;
    loading: boolean;
    success: boolean;
    error: boolean;
    messages: string[];
}

export const initialState: LicenceTypeState = {
    formSubmissions: [],
    loading: false,
    success: false,
    loaderMessage: undefined,
    error: false,
    messages: []
};