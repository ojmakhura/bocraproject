import { Injectable } from '@angular/core';

import {DocumentTypeVO} from '@app/model/bw/org/bocra/portal/document/type/document-type-vo';
import {LicenseeVO} from '@app/model/bw/org/bocra/portal/licensee/licensee-vo';
import {LicenceVO} from '@app/model/bw/org/bocra/portal/licence/licence-vo';

@Injectable()
export class DocumentVO {
    id: number | any = null;

    createdBy: string | any = null;

    updatedBy: string | any = null;

    createdDate: Date | any = null;

    updatedDate: Date | any = null;


    licence: LicenceVO = <LicenceVO>{
        id: null,
        createdBy: null,
        updatedBy: null,
        createdDate: null,
        updatedDate: null,
        licenceType: null,
        licensee: null,
        status: null,
        licenceNumber: null,
        provisional: null,
        startDate: null,
        endDate: null,
        documents: new Array(),
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

    documentType: DocumentTypeVO = <DocumentTypeVO>{
        id: null,
        createdBy: null,
        updatedBy: null,
        createdDate: null,
        updatedDate: null,
        code: null,
        name: null,
        description: null,
    };
    documentName: string | any = null;

    file: File | any = null;

    documentId: string | any = null;

    
    constructor() {}
}
