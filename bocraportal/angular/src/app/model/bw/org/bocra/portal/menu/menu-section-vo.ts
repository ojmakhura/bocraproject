import { Injectable } from '@angular/core';

import {AuthorisationVO} from '@app/model/bw/org/bocra/portal/auth/authorisation-vo';

@Injectable()
export class MenuSectionVO {
    id: number | any = null;

    createdBy: string | any = null;

    updatedBy: string | any = null;

    createdDate: Date | any = null;

    updatedDate: Date | any = null;

    position: number | any = null;

    displayId: string | any = null;

    displayName: string | any = null;

    authorisations: AuthorisationVO[] | any[] = [];

    
    constructor() {}
}
