import { Injectable } from '@angular/core';

import {LicenseeStatus} from '@app/model/bw/org/bocra/portal/licensee/licensee-status';

@Injectable()
export class LicenseeSectorVO {
    id: number | any = null;

    code: string | any = null;

    name: string | any = null;

    uin: string | any = null;

    licenseeName: string | any = null;

    address: string | any = null;

    status: LicenseeStatus | any = null;

    licenseeSectorId: number | any = null;

    
    constructor() {}
}
