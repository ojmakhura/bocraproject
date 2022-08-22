import { Injectable } from '@angular/core';

import {LicenceTypeVO} from '@app/model/bw/org/bocra/portal/licence/type/licence-type-vo';
import {FormVO} from '@app/model/bw/org/bocra/portal/form/form-vo';

@Injectable()
export class LicenceTypeFormVO {
    id: number | any = null;


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
    
    constructor() {}
}
