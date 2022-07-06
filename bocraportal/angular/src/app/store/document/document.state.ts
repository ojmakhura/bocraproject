// Generated by andromda-angular cartridge (app\usecase\state.store.ts.vsl) DO NOT EDIT
import { DocumentVO } from '@app/model/bw/org/bocra/portal/document/document-vo';

export const documentKey = "document";

export interface DocumentState {
    document: DocumentVO | any,
    criteria: String | any,
    id: number | any,
    documents: Array<DocumentVO> | Array<any>,
    removed: boolean,
    success: boolean,
    loading: boolean,
    error: boolean,
    messages: any[]
}

export const initialState: DocumentState = {
    document: null,
    criteria: null,
    id: null,
    documents: [],
    removed: false,
    success: false,
    loading: false,
    error: false,
    messages: []
};