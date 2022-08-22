import { Injectable } from '@angular/core';

import {NotificationTarget} from '@app/model/bw/org/bocra/portal/notification/notification-target';
import {SectorVO} from '@app/model/bw/org/bocra/portal/sector/sector-vo';
import {LicenseeVO} from '@app/model/bw/org/bocra/portal/licensee/licensee-vo';
import {UserVO} from '@app/model/bw/org/bocra/portal/user/user-vo';

@Injectable()
export class NotificationVO {
    id: number | any = null;

    subject: string | any = null;

    target: NotificationTarget | any = null;

    users: UserVO[] | any[] = [];

    roles: string[] | any[] = [];

    read: Boolean | any = null;

    sectors: SectorVO[] | any[] = [];

    licensees: LicenseeVO[] | any[] = [];

    message: string | any = null;

    
    constructor() {}
}
