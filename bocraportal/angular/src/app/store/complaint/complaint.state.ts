// Generated by andromda-angular cartridge (app\usecase\state.store.ts.vsl) DO NOT EDIT
import { ComplaintVO } from '@app/model/bw/org/bocra/portal/complaint/complaint-vo';
import { DocumentVO } from '@model/bw/org/bocra/portal/document/document-vo';
import { ComplaintReplyVO } from '@model/bw/org/bocra/portal/complaint/complaint-reply-vo';
import { ComplaintSeachCriteria } from '@model/bw/org/bocra/portal/complaint/complaint-seach-criteria';

export const complaintKey = 'complaint';

export interface ComplaintState {
  complaint: ComplaintVO | any;
  criteria: String | any;
  complaints: Array<ComplaintVO> | Array<any>;
  document: DocumentVO | any;
  documents: Array<DocumentVO> | Array<any>;
  complaintReply: ComplaintReplyVO;
  complaintReplies: Array<ComplaintReplyVO> | Array<any>;
  id: number | any;
  complaintId: string;
  removed: boolean;
  loading: boolean;
  loaderMessage: string | undefined;
  success: boolean;
  error: boolean;
  assigned: boolean;
  updated: boolean;
  messages: string[];
  loggedInSearch: ComplaintSeachCriteria | any;
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
  messages: [],
  loaderMessage: undefined,
  document: undefined,
  documents: [],
  complaintReply: undefined,
  complaintReplies: [],
  loggedInSearch: null,
  complaintId: '',
  assigned: false,
  updated: false
};
