import { Injectable } from '@angular/core';

import {LicenceStatus} from '@app/model/bw/org/bocra/portal/licence/licence-status';
import {LicenseeVO} from '@app/model/bw/org/bocra/portal/licensee/licensee-vo';
import {LicenceTypeVO} from '@app/model/bw/org/bocra/portal/licence/type/licence-type-vo';
import {DocumentVO} from '@app/model/bw/org/bocra/portal/document/document-vo';

@Injectable()
export class LicenceVO {
    id: number | any = null;

    createdBy: string | any = null;

    updatedBy: string | any = null;

    createdDate: Date | any = null;

    updatedDate: Date | any = null;


    licenceType: LicenceTypeVO = <LicenceTypeVO>{
        id: null,
        code: null,
        name: null,
        description: null,
        forms: new Array(),
        createdBy: null,
        updatedBy: null,
        createdDate: null,
        updatedDate: null,
        licences: new Array(),
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
    status: LicenceStatus | any = null;

    licenceNumber: string | any = null;

    provisional: Boolean | any = null;

    startDate: Date | any = null;

    endDate: Date | any = null;

    documents: DocumentVO[] | any[] = [];

    
    constructor() {}
}
