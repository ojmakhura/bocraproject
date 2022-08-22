import { Injectable } from '@angular/core';

import {PeriodConfigVO} from '@app/model/bw/org/bocra/portal/period/config/period-config-vo';

@Injectable()
export class PeriodVO {
    id: number | any = null;


    periodConfig: PeriodConfigVO = <PeriodConfigVO>{
        id: null,
        periodConfigName: null,
        startDay: null,
        startMonth: null,
        createdBy: null,
        updatedBy: null,
        createdDate: null,
        updatedDate: null,
        repeat: null,
        repeatPeriod: null,
        finalDay: null,
    };
    periodName: string | any = null;

    periodStart: Date | any = null;

    periodEnd: Date | any = null;

    createdBy: string | any = null;

    updatedBy: string | any = null;

    createdDate: Date | any = null;

    updatedDate: Date | any = null;


    next: PeriodVO = <PeriodVO>{
        id: null,
        periodConfig: null,
        periodName: null,
        periodStart: null,
        periodEnd: null,
        createdBy: null,
        updatedBy: null,
        createdDate: null,
        updatedDate: null,
        next: null,
        previous: null,
    };

    previous: PeriodVO = <PeriodVO>{
        id: null,
        periodConfig: null,
        periodName: null,
        periodStart: null,
        periodEnd: null,
        createdBy: null,
        updatedBy: null,
        createdDate: null,
        updatedDate: null,
        next: null,
        previous: null,
    };
    
    constructor() {}
}
