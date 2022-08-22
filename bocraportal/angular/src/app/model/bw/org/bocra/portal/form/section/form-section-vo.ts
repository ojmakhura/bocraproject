import { Injectable } from '@angular/core';

import {FormFieldVO} from '@app/model/bw/org/bocra/portal/form/field/form-field-vo';
import {FormVO} from '@app/model/bw/org/bocra/portal/form/form-vo';

@Injectable()
export class FormSectionVO {
    id: number | any = null;

    createdBy: string | any = null;

    updatedBy: string | any = null;

    createdDate: Date | any = null;

    updatedDate: Date | any = null;

    position: number | any = null;

    sectionId: string | any = null;

    sectionLabel: string | any = null;


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
    formFields: FormFieldVO[] | any[] = [];

    
    constructor() {}
}
