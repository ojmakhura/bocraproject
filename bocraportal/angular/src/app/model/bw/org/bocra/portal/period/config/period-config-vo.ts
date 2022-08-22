import { Injectable } from '@angular/core';

import {RepeatPeriod} from '@app/model/bw/org/bocra/portal/period/config/repeat-period';

@Injectable()
export class PeriodConfigVO {
    id: number | any = null;

    periodConfigName: string | any = null;

    startDay: number | any = null;

    startMonth: number | any = null;

    createdBy: string | any = null;

    updatedBy: string | any = null;

    createdDate: Date | any = null;

    updatedDate: Date | any = null;

    repeat: number | any = null;

    repeatPeriod: RepeatPeriod | any = null;

    finalDay: number | any = null;

    
    constructor() {}
}
