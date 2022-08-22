import { Injectable } from '@angular/core';


@Injectable()
export class AuthorisationCriteria {
    accessPointName: string | any = null;

    accessPointUrl: string | any = null;

    roles: string[] | any[] = [];

    
    constructor() {}
}
