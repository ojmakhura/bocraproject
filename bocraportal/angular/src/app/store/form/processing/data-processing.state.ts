// Generated by andromda-angular cartridge (app\usecase\state.store.ts.vsl) DO NOT EDIT
import { FormSubmissionVO } from '@app/model/bw/org/bocra/portal/form/submission/form-submission-vo';

export const dataProcessingKey = "dataProcessing";

export interface DataProcessingState {
    submissions: Array<FormSubmissionVO> | Array<any>;
    loading: boolean;
    success: boolean;
    error: boolean;
    messages: string[];
}

export const initialState: DataProcessingState = {
    submissions: [],
    loading: false,
    success: false,
    error: false,
    messages: []
};