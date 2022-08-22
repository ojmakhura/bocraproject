import { Injectable } from '@angular/core';


@Injectable()
export class LicenseeUserCriteria {
    userId: string | any = null;

    addedBefore: Date | any = null;

    addedAfter: Date | any = null;

    licenseeId: number | any = null;

    
    constructor() {}
}
