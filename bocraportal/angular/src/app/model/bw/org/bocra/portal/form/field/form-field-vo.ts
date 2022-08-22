import { Injectable } from '@angular/core';

import {FieldType} from '@app/model/bw/org/bocra/portal/form/field/field-type';
import {FormSectionVO} from '@app/model/bw/org/bocra/portal/form/section/form-section-vo';
import {FormVO} from '@app/model/bw/org/bocra/portal/form/form-vo';
import {FieldValueType} from '@app/model/bw/org/bocra/portal/form/field/field-value-type';

@Injectable()
export class FormFieldVO {
    id: number | any = null;

    createdBy: string | any = null;

    updatedBy: string | any = null;

    createdDate: Date | any = null;

    updatedDate: Date | any = null;


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

    formSection: FormSectionVO = <FormSectionVO>{
        id: null,
        createdBy: null,
        updatedBy: null,
        createdDate: null,
        updatedDate: null,
        position: null,
        sectionId: null,
        sectionLabel: null,
        form: null,
        formFields: new Array(),
    };
    fieldType: FieldType | any = null;

    fieldId: string | any = null;

    fieldName: string | any = null;

    description: string | any = null;

    fieldValueType: FieldValueType | any = null;

    expression: string | any = null;

    defaultValue: string | any = null;

    required: Boolean | any = null;

    min: number | any = null;

    max: number | any = null;

    units: string[] | any[] = [];

    options: string[] | any[] = [];

    minLength: number | any = null;

    maxLength: number | any = null;

    
    constructor() {}
}
