// Generated by andromda-angular cartridge (app\usecase\state.store.ts.vsl) DO NOT EDIT
import { FormSubmissionCriteria } from '@app/model/bw/org/bocra/portal/form/submission/form-submission-criteria';
import { FormSubmissionVO } from '@app/model/bw/org/bocra/portal/form/submission/form-submission-vo';
import { NoteVO } from '@model/bw/org/bocra/portal/form/submission/note/note-vo';

export const formSubmissionKey = "formSubmission";

export interface FormSubmissionState {
    criteria: FormSubmissionCriteria | any,
    id: number | any,
    formSubmission: FormSubmissionVO | any,
    formSubmissions: Array<FormSubmissionVO> | Array<any>,
    notes: Array<NoteVO> | Array<any>,
    note: NoteVO | any,
    removed: boolean,
    success: boolean,
    loading: boolean,
    error: boolean,
    messages: any[]
}

export const initialState: FormSubmissionState = {
    criteria: null,
    id: null,
    formSubmission: null,
    formSubmissions: [],
    notes:[],
    note: null,
    removed: false,
    success: false,
    loading: false,
    error: false,
    messages: []
};