// Generated by andromda-angular cartridge (app\usecase\state.store.ts.vsl) DO NOT EDIT
import { LicenceTypeFormVO } from '@model/bw/org/bocra/portal/licence/type/form/licence-type-form-vo';
import { FormVO } from '@model/bw/org/bocra/portal/form/form-vo';

export const licenceTypeFormKey = "licenceTypeForm";

export interface LicenceTypeFormState {
    id: number | any,
    licenceTypeForms: Array<LicenceTypeFormVO> | Array<any>,
    licenceTypeForm: LicenceTypeFormVO | any,
    removed: boolean,
    success: boolean,
    loading: boolean,
    loaderMessage: string | undefined,
    error: boolean,
    messages: any[]
}

export const initialState: LicenceTypeFormState = {
    id: undefined,
    licenceTypeForms: [],
    licenceTypeForm: undefined,
    removed: false,
    success: false,
    loading: false,
    error: false,
    messages: [],
    loaderMessage: undefined
};