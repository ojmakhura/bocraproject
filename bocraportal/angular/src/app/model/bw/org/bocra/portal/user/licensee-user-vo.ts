import { Injectable } from '@angular/core';

import {LicenseeVO} from '@app/model/bw/org/bocra/portal/licensee/licensee-vo';
import {UserVO} from '@app/model/bw/org/bocra/portal/user/user-vo';

@Injectable()
export class LicenseeUserVO {

    user: UserVO = <UserVO>{
        userId: null,
        username: null,
        email: null,
        password: null,
        firstName: null,
        lastName: null,
        enabled: null,
        licensee: null,
        roles: new Array(),
        client: null,
    };

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
    id: number | any = null;

    dateAdded: Date | any = null;

    
    constructor() {}
}
