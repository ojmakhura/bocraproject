import { Injectable } from '@angular/core';

import {AccessPointTypeVO} from '@app/model/bw/org/bocra/portal/access/type/access-point-type-vo';

@Injectable()
export class AccessPointVO {
    id: number | any = null;

    createdBy: string | any = null;

    updatedBy: string | any = null;

    createdDate: Date | any = null;

    updatedDate: Date | any = null;


    accessPointType: AccessPointTypeVO = <AccessPointTypeVO>{
        id: null,
        code: null,
        name: null,
        description: null,
    };
    name: string | any = null;

    url: string | any = null;

    icon: string | any = null;

    
    constructor() {}
}
