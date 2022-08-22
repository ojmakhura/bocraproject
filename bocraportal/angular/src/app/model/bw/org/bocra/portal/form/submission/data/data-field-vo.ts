import { Injectable } from '@angular/core';

import {FormFieldVO} from '@app/model/bw/org/bocra/portal/form/field/form-field-vo';
import {FormSubmissionVO} from '@app/model/bw/org/bocra/portal/form/submission/form-submission-vo';

@Injectable()
export class DataFieldVO {
    id: number | any = null;


    formField: FormFieldVO = <FormFieldVO>{
        id: null,
        createdBy: null,
        updatedBy: null,
        createdDate: null,
        updatedDate: null,
        form: null,
        formSection: null,
        fieldType: null,
        fieldId: null,
        fieldName: null,
        description: null,
        fieldValueType: null,
        expression: null,
        defaultValue: null,
        required: null,
        min: null,
        max: null,
        units: new Array(),
        options: new Array(),
        minLength: null,
        maxLength: null,
    };
    value: string | any = null;


    formSubmission: FormSubmissionVO = <FormSubmissionVO>{
        id: null,
        createdBy: null,
        updatedBy: null,
        createdDate: null,
        updatedDate: null,
        submittedBy: null,
        submissionDate: null,
        form: null,
        period: null,
        licensee: null,
        dataFields: new Array(),
        submissionStatus: null,
        upload: null,
        notes: new Array(),
        sections: new Array(),
    };
    units: string | any = null;

    comments: string | any = null;

    sectionPosition: number | any = null;

    sectionId: string | any = null;

    sectionLabel: string | any = null;

    row: number | any = null;

    
    constructor() {}
}
