import { Injectable } from '@angular/core';

import {ShareholderType} from '@app/model/bw/org/bocra/portal/licensee/shares/shareholder-type';
import {LicenseeVO} from '@app/model/bw/org/bocra/portal/licensee/licensee-vo';

@Injectable()
export class ShareholderVO {
    id: number | any = null;

    createdBy: string | any = null;

    updatedBy: string | any = null;

    createdDate: Date | any = null;

    updatedDate: Date | any = null;

    shareholderId: string | any = null;

    type: ShareholderType | any = null;

    name: string | any = null;

    address: string | any = null;

    numberOfShares: number | any = null;

    percentageShares: number | any = null;

    licensees: LicenseeVO[] | any[] = [];

    
    constructor() {}
}
