import { Injectable } from '@angular/core';

import {LicenceStatus} from '@app/model/bw/org/bocra/portal/licence/licence-status';

@Injectable()
export class LicenceCriteria {
    status: LicenceStatus | any = null;

    licenceNumber: string | any = null;

    provisional: Boolean | any = null;

    licensee: number | any = null;

    
    constructor() {}
}
