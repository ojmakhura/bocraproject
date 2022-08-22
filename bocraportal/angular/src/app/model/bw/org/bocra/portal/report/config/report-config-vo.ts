import { Injectable } from '@angular/core';

import {LicenseeVO} from '@app/model/bw/org/bocra/portal/licensee/licensee-vo';
import {LicenceTypeVO} from '@app/model/bw/org/bocra/portal/licence/type/licence-type-vo';
import {FormVO} from '@app/model/bw/org/bocra/portal/form/form-vo';

@Injectable()
export class ReportConfigVO {
    id: number | any = null;

    createdBy: string | any = null;

    updatedBy: string | any = null;

    createdDate: Date | any = null;

    updatedDate: Date | any = null;

    code: string | any = null;

    name: string | any = null;

    description: string | any = null;

    forms: FormVO[] | any[] = [];

    licensees: LicenseeVO[] | any[] = [];

    licenceTypes: LicenceTypeVO[] | any[] = [];

    
    constructor() {}
}
