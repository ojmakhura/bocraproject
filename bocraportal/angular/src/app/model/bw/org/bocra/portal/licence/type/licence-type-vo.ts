import { Injectable } from '@angular/core';

import {LicenceVO} from '@app/model/bw/org/bocra/portal/licence/licence-vo';
import {FormVO} from '@app/model/bw/org/bocra/portal/form/form-vo';

@Injectable()
export class LicenceTypeVO {
    id: number | any = null;

    code: string | any = null;

    name: string | any = null;

    description: string | any = null;

    forms: FormVO[] | any[] = [];

    createdBy: string | any = null;

    updatedBy: string | any = null;

    createdDate: Date | any = null;

    updatedDate: Date | any = null;

    licences: LicenceVO[] | any[] = [];

    
    constructor() {}
}
