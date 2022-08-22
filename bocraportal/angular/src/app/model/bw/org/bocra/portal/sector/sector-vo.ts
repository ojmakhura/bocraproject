import { Injectable } from '@angular/core';

import {LicenseeSectorVO} from '@app/model/bw/org/bocra/portal/licensee/sector/licensee-sector-vo';
import {SectorFormVO} from '@app/model/bw/org/bocra/portal/sector/form/sector-form-vo';

@Injectable()
export class SectorVO {
    id: number | any = null;

    createdBy: string | any = null;

    updatedBy: string | any = null;

    createdDate: Date | any = null;

    updatedDate: Date | any = null;

    code: string | any = null;

    name: string | any = null;

    description: string | any = null;

    licensees: LicenseeSectorVO[] | any[] = [];

    forms: SectorFormVO[] | any[] = [];

    
    constructor() {}
}
