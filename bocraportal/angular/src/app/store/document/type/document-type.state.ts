// Generated by andromda-angular cartridge (app\usecase\state.store.ts.vsl) DO NOT EDIT
import { DocumentTypeVO } from '@app/model/bw/org/bocra/portal/document/type/document-type-vo';

export const documentTypeKey = "documentType";

export interface DocumentTypeState {
    documentType: DocumentTypeVO | any,
    documentTypes: Array<DocumentTypeVO> | Array<any>,
    criteria: String | any,
    id: number | any,
    removed: boolean,
    loading: boolean,
    loaderMessage: string | undefined,
    success: boolean,
    error: boolean,
    messages: any[]
}

export const initialState: DocumentTypeState = {
    documentType: null,
    documentTypes: [],
    criteria: null,
    id: null,
    removed: false,
    loading: false,
    success: false,
    error: false,
    messages: [],
    loaderMessage: undefined
};