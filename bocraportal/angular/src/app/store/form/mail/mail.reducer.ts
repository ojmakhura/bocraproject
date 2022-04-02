// Generated by andromda-angular cartridge (app\usecase\action.store.ts.vsl) DO NOT EDIT
import { createFeature, createReducer, on } from '@ngrx/store';
import { MailVO } from '@app/model/bw/org/bocra/portal/mail/mail-vo';
import { FormCriteria } from '@app/model/bw/org/bocra/portal/form/form-criteria';
import * as MailActions from './mail.action';

export const mailKey = "mail";

export interface MailState {
    mail: MailVO;
    mails: MailVO[];
    searchCriteria: FormCriteria
    loading: boolean;
    id: number;
    error: any
}

export const initialState: MailState = {
    mail: new MailVO,
    mails: [],
    searchCriteria: new FormCriteria,
    loading: false,
    id: 0,
    error: null
};

export const mailReducer = createReducer(
    initialState,
    on(MailActions.saveMail, (state, action) => ({
        ...state,
        mail: action.mail
    })),
    on(MailActions.saveMailSuccess, (state, action) => ({
        ...state,
        mail: action.mail,
        mails: [...state.mails, action.mail]
    })),
    on(MailActions.findById, (state, action) => ({
        ...state,
        id: action.id
    })),
    on(MailActions.findByIdSuccess, (state, action) => ({
        ...state,
        mail: action.mail,
        mails: [...state.mails, action.mail]
    })),
    on(MailActions.loadAll, (state, action) => ({
        ...state
    })),
    on(MailActions.loadAllSuccess, (state, action) => ({
        ...state,
        mail: new MailVO,
        mails: action.mails
    })),
    on(MailActions.searchMails, (state, action) => ({
        ...state,
        searchCriteria: action.searchCriteria
    })),
    on(MailActions.searchMailsSuccess, (state, action) => ({
        ...state,
        mail: new MailVO,
        searchCriteria: new FormCriteria,
        mails: action.mails
    })),
    on(MailActions.reset, (state) => ({
      ...state,
      mail: new MailVO,
      searchCriteria: new FormCriteria,
      mails: []
    })),
    on(MailActions.mailActionFailure, (state, action) => ({
        ...state,
        error: action.error
    }))
);

export const mailFeature = createFeature({
    name: mailKey,
    reducer: mailReducer
});
