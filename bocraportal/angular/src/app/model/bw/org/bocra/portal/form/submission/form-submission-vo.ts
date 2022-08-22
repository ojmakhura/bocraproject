import { Injectable } from '@angular/core';

import {DataFieldSectionVO} from '@app/model/bw/org/bocra/portal/form/submission/data/data-field-section-vo';
import {PeriodVO} from '@app/model/bw/org/bocra/portal/period/period-vo';
import {LicenseeVO} from '@app/model/bw/org/bocra/portal/licensee/licensee-vo';
import {NoteVO} from '@app/model/bw/org/bocra/portal/form/submission/note/note-vo';
import {DataFieldVO} from '@app/model/bw/org/bocra/portal/form/submission/data/data-field-vo';
import {FormVO} from '@app/model/bw/org/bocra/portal/form/form-vo';

@Injectable()
export class FormSubmissionVO {
    id: number | any = null;

    createdBy: string | any = null;

    updatedBy: string | any = null;

    createdDate: Date | any = null;

    updatedDate: Date | any = null;

    submittedBy: string | any = null;

    submissionDate: Date | any = null;


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
    dataFields: DataFieldVO[] | any[] = [];

    submissionStatus: string | any = null;

    upload: File | any = null;

    notes: NoteVO[] | any[] = [];

    sections: DataFieldSectionVO[] | any[] = [];

    
    constructor() {}
}
