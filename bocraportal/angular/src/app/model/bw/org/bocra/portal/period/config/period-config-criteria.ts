import { Injectable } from '@angular/core';


@Injectable()
export class PeriodConfigCriteria {
    periodConfigName: string | any = null;

    startDayFrom: number | any = null;

    startDayTo: number | any = null;

    startMonthFrom: number | any = null;

    startMonthTo: number | any = null;

    
    constructor() {}
}
