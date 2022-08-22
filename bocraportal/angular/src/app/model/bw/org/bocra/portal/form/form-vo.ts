import { Injectable } from '@angular/core';

import {FormEntryType} from '@app/model/bw/org/bocra/portal/form/form-entry-type';
import {FormSectionVO} from '@app/model/bw/org/bocra/portal/form/section/form-section-vo';
import {FormFieldVO} from '@app/model/bw/org/bocra/portal/form/field/form-field-vo';
import {LicenceTypeVO} from '@app/model/bw/org/bocra/portal/licence/type/licence-type-vo';
import {SectorFormVO} from '@app/model/bw/org/bocra/portal/sector/form/sector-form-vo';
import {LicenseeFormVO} from '@app/model/bw/org/bocra/portal/licensee/form/licensee-form-vo';

@Injectable()
export class FormVO {
    id: number | any = null;

    createdBy: string | any = null;

    updatedBy: string | any = null;

    createdDate: Date | any = null;

    updatedDate: Date | any = null;

    code: string | any = null;

    formName: string | any = null;

    description: string | any = null;

    entryType: FormEntryType | any = null;

    licenceTypes: LicenceTypeVO[] | any[] = [];

    formSections: FormSectionVO[] | any[] = [];

    formFields: FormFieldVO[] | any[] = [];

    licensees: LicenseeFormVO[] | any[] = [];

    sectors: SectorFormVO[] | any[] = [];

    
    constructor() {}
}
