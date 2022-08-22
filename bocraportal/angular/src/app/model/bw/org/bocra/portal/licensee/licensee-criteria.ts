import { Injectable } from '@angular/core';


@Injectable()
export class LicenseeCriteria {
    uin: string | any = null;

    licenseeName: string | any = null;

    licenceIds: number[] | any[] = [];

    
    constructor() {}
}
