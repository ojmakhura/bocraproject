import { Injectable } from '@angular/core';

import {DataFieldVO} from '@app/model/bw/org/bocra/portal/form/submission/data/data-field-vo';

@Injectable()
export class DataFieldSectionVO {
    sectionId: string | any = null;

    sectionLabel: string | any = null;

    position: number | any = null;

    dataFields: DataFieldVO[] | any[] = [];

    
    constructor() {}
}
