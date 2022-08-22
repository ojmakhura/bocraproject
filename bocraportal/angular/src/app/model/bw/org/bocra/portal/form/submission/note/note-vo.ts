import { Injectable } from '@angular/core';

import {FormSubmissionVO} from '@app/model/bw/org/bocra/portal/form/submission/form-submission-vo';

@Injectable()
export class NoteVO {
    id: number | any = null;

    createdBy: string | any = null;

    createdDate: Date | any = null;

    note: string | any = null;


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
    
    constructor() {}
}
