import { Injectable } from '@angular/core';

import {LicenseeVO} from '@app/model/bw/org/bocra/portal/licensee/licensee-vo';

@Injectable()
export class UserVO {
    userId: string | any = null;

    username: string | any = null;

    email: string | any = null;

    password: string | any = null;

    firstName: string | any = null;

    lastName: string | any = null;

    enabled: Boolean | any = null;


    licensee: LicenseeVO = <LicenseeVO>{
        id: null,
        createdBy: null,
        updatedBy: null,
        createdDate: null,
        updatedDate: null,
        status: null,
        uin: null,
        licenseeName: null,
        address: null,
        users: new Array(),
        forms: new Array(),
        licences: new Array(),
        documents: new Array(),
        sectors: new Array(),
    };
    roles: string[] | any[] = [];

    client: string | any = null;

    
    constructor() {}
}
