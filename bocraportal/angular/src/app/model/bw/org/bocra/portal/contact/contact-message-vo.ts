import { Injectable } from '@angular/core';


@Injectable()
export class ContactMessageVO {
    id: number | any = null;

    contactDate: Date | any = null;

    email: string | any = null;

    subject: string | any = null;

    message: string | any = null;

    
    constructor() {}
}
