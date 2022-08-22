import { Injectable } from '@angular/core';


@Injectable()
export class DocumentTypeVO {
    id: number | any = null;

    createdBy: string | any = null;

    updatedBy: string | any = null;

    createdDate: Date | any = null;

    updatedDate: Date | any = null;

    code: string | any = null;

    name: string | any = null;

    description: string | any = null;

    
    constructor() {}
}
