// Generated by andromda-angular cartridge (app\usecase\state.store.ts.vsl) DO NOT EDIT
import { LicenseeSectorVO } from '@app/model/bw/org/bocra/portal/licensee/sector/licensee-sector-vo';
import { SectorVO } from '@app/model/bw/org/bocra/portal/sector/sector-vo';

export const sectorKey = 'sector';

export interface SectorState {
  criteria: String | any;
  id: number | any;
  sectors: Array<SectorVO> | Array<any>;
  sector: SectorVO | any;
  licensee: LicenseeSectorVO | any;
  licensees: Array<LicenseeSectorVO>;
  loading: boolean;
  loaderMessage: string | undefined;
  success: boolean;
  removed: boolean;
  error: boolean;
  messages: any[];
}

export const initialState: SectorState = {
  criteria: null,
  id: null,
  sectors: [],
  sector: null,
  licensee: null,
  licensees: [],
  loading: false,
  success: false,
  removed: false,
  error: false,
  messages: [],
  loaderMessage: undefined,
};
