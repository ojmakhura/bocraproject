import { Injectable } from '@angular/core';

import {AccessPointVO} from '@app/model/bw/org/bocra/portal/access/access-point-vo';

@Injectable()
export class AuthorisationVO {
    id: number | any = null;

    createdBy: string | any = null;

    updatedBy: string | any = null;

    createdDate: Date | any = null;

    updatedDate: Date | any = null;


    accessPoint: AccessPointVO = <AccessPointVO>{
        id: null,
        createdBy: null,
        updatedBy: null,
        createdDate: null,
        updatedDate: null,
        accessPointType: null,
        name: null,
        url: null,
        icon: null,
    };
    roles: string[] | any[] = [];

    
    constructor() {}
}
