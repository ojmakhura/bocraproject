import { Injectable } from '@angular/core';

import {SectorVO} from '@app/model/bw/org/bocra/portal/sector/sector-vo';
import {FormVO} from '@app/model/bw/org/bocra/portal/form/form-vo';

@Injectable()
export class SectorFormVO {
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

    sector: SectorVO = <SectorVO>{
        id: null,
        createdBy: null,
        updatedBy: null,
        createdDate: null,
        updatedDate: null,
        code: null,
        name: null,
        description: null,
        licensees: new Array(),
        forms: new Array(),
    };
    
    constructor() {}
}
