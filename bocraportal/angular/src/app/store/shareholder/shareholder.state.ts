// Generated by andromda-angular cartridge (app\usecase\state.store.ts.vsl) DO NOT EDIT
import { DocumentVO } from '@app/model/bw/org/bocra/portal/document/document-vo';
import { LicenseeShareholderVO } from '@app/model/bw/org/bocra/portal/licensee/shares/licensee-shareholder-vo';
import { ShareholderCriteria } from '@app/model/bw/org/bocra/portal/shareholder/shareholder-criteria';
import { ShareholderVO } from '@app/model/bw/org/bocra/portal/shareholder/shareholder-vo';

export const shareholderKey = 'shareholder';

export interface ShareholderState {
  shareholders: Array<ShareholderVO> | Array<any>;
  id: number | any;
  shareholder: ShareholderVO | any;
  criteria: ShareholderCriteria | any;
  loaderMessage: string | undefined;
  licensee: LicenseeShareholderVO;
  document: DocumentVO;
  removed: boolean;
  loading: boolean;
  success: boolean;
  error: boolean;
  messages: string[];
}

export const initialState: ShareholderState = {
  shareholders: [],
  id: null,
  shareholder: null,
  criteria: null,
  loading: false,
  success: false,
  loaderMessage: undefined,
  error: false,
  messages: [],
  removed: false,
  licensee: new LicenseeShareholderVO(),
  document: new DocumentVO(),
};
