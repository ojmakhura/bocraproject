// Generated by andromda-angular cartridge (app\usecase\state.store.ts.vsl) DO NOT EDIT
import { ComplaintVO } from '@app/model/bw/org/bocra/portal/complaint/complaint-vo';

export const complaintKey = "complaint";

export interface ComplaintState {
    complaint: ComplaintVO | any;
    criteria: String | any;
    complaints: Array<ComplaintVO> | Array<any>;
    id: number | any;
    removed: boolean;
    loading: boolean;
    success: boolean;
    error: boolean;
    messages: string[];
}

export const initialState: ComplaintState = {
    complaint: null,
    criteria: null,
    complaints: [],
    id: null,
    removed: false,
    loading: false,
    success: false,
    error: false,
    messages: []
};