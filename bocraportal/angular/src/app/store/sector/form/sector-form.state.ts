// Generated by andromda-angular cartridge (app\usecase\state.store.ts.vsl) DO NOT EDIT
import { SectorVO } from '@app/model/bw/org/bocra/portal/sector/sector-vo';
import { SectorFormVO } from '@model/bw/org/bocra/portal/sector/form/sector-form-vo';
import { FormVO } from '@model/bw/org/bocra/portal/form/form-vo';

export const sectorFormKey = 'sectorForm';

export interface SectorFormState {
  id: number | any;
  sectorForms: Array<SectorFormVO> | Array<any>;
  sectorForm: SectorFormVO | any;
  sector: SectorVO | any;
  form: FormVO | any;
  removed: boolean;
  success: boolean;
  loading: boolean;
  loaderMessage: string | undefined;
  error: boolean;
  messages: any[];
}

export const initialState: SectorFormState = {
  id: undefined,
  sectorForms: [],
  sectorForm: undefined,
  sector: undefined,
  form: undefined,
  removed: false,
  success: false,
  loading: false,
  error: false,
  messages: [],
  loaderMessage: undefined,
};
