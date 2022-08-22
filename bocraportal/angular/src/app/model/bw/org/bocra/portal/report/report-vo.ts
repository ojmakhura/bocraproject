import { Injectable } from '@angular/core';

import {ReportConfigVO} from '@app/model/bw/org/bocra/portal/report/config/report-config-vo';
import {LicenseeVO} from '@app/model/bw/org/bocra/portal/licensee/licensee-vo';

@Injectable()
export class ReportVO {
    id: number | any = null;

    createdBy: string | any = null;

    updatedBy: string | any = null;

    createdDate: Date | any = null;

    updatedDate: Date | any = null;


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

    reportConfig: ReportConfigVO = <ReportConfigVO>{
        id: null,
        createdBy: null,
        updatedBy: null,
        createdDate: null,
        updatedDate: null,
        code: null,
        name: null,
        description: null,
        forms: new Array(),
        licensees: new Array(),
        licenceTypes: new Array(),
    };
    
    constructor() {}
}
