import { Injectable } from '@angular/core';

import {LicenseeVO} from '@app/model/bw/org/bocra/portal/licensee/licensee-vo';
import {FormVO} from '@app/model/bw/org/bocra/portal/form/form-vo';

@Injectable()
export class LicenseeFormVO {
    id: number | any = null;


    form: FormVO = <FormVO>{
        id: null,
        createdBy: null,
        updatedBy: null,
        createdDate: null,
        updatedDate: null,
        code: null,
        formName: null,
        description: null,
        entryType: null,
        licenceTypes: new Array(),
        formSections: new Array(),
        formFields: new Array(),
        licensees: new Array(),
        sectors: new Array(),
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
    
    constructor() {}
}
