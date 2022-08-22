import { Injectable } from '@angular/core';

import {LicenseeStatus} from '@app/model/bw/org/bocra/portal/licensee/licensee-status';
import {LicenseeSectorVO} from '@app/model/bw/org/bocra/portal/licensee/sector/licensee-sector-vo';
import {DocumentVO} from '@app/model/bw/org/bocra/portal/document/document-vo';
import {LicenseeFormVO} from '@app/model/bw/org/bocra/portal/licensee/form/licensee-form-vo';
import {UserVO} from '@app/model/bw/org/bocra/portal/user/user-vo';
import {LicenceVO} from '@app/model/bw/org/bocra/portal/licence/licence-vo';

@Injectable()
export class LicenseeVO {
    id: number | any = null;

    createdBy: string | any = null;

    updatedBy: string | any = null;

    createdDate: Date | any = null;

    updatedDate: Date | any = null;

    status: LicenseeStatus | any = null;

    uin: string | any = null;

    licenseeName: string | any = null;

    address: string | any = null;

    users: UserVO[] | any[] = [];

    forms: LicenseeFormVO[] | any[] = [];

    licences: LicenceVO[] | any[] = [];

    documents: DocumentVO[] | any[] = [];

    sectors: LicenseeSectorVO[] | any[] = [];

    
    constructor() {}
}
