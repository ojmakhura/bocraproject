import { Injectable } from '@angular/core';

import {ShareholderType} from '@app/model/bw/org/bocra/portal/licensee/shares/shareholder-type';

@Injectable()
export class ShareholderCriteria {
    shareholderId: string | any = null;

    type: ShareholderType | any = null;

    name: string | any = null;

    
    constructor() {}
}
