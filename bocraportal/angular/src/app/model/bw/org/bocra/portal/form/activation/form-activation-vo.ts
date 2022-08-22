import { Injectable } from '@angular/core';

import {PeriodVO} from '@app/model/bw/org/bocra/portal/period/period-vo';
import {FormSubmissionVO} from '@app/model/bw/org/bocra/portal/form/submission/form-submission-vo';
import {FormVO} from '@app/model/bw/org/bocra/portal/form/form-vo';

@Injectable()
export class FormActivationVO {
    id: number | any = null;

    createdDate: Date | any = null;

    updatedBy: string | any = null;

    updatedDate: Date | any = null;

    createdBy: string | any = null;

    activationName: string | any = null;


    period: PeriodVO = <PeriodVO>{
        id: null,
        periodConfig: null,
        periodName: null,
        periodStart: null,
        periodEnd: null,
        createdBy: null,
        updatedBy: null,
        createdDate: null,
        updatedDate: null,
        next: null,
        previous: null,
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
    formSubmissions: FormSubmissionVO[] | any[] = [];

    
    constructor() {}
}
