// Generated by andromda-angular cartridge (service\service.impl.ts.vsl) CAN EDIT
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ShareholderCriteria } from '@app/model/bw/org/bocra/portal/shareholder/shareholder-criteria';
import { ShareholderVO } from '@app/model/bw/org/bocra/portal/shareholder/shareholder-vo';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ShareholderRestController {
    
    protected path = '/shareholder';

    constructor(private http: HttpClient) {
    }

    public findById(id: number | any ): Observable<ShareholderVO | any> {
        return this.http.get<ShareholderVO | any>(`${this.path}/${id}`, {});
    }

    public getAll(): Observable<ShareholderVO[] | any[]> {
        return this.http.get<ShareholderVO[] | any[]>(`${this.path}/all`);
    }

    public getAllPaged(pageNumber: number | any , pageSize: number | any ): Observable<ShareholderVO[] | any[]> {
        return this.http.get<ShareholderVO[] | any[]>(`${this.path}/page/${pageNumber}/size/${pageSize}`, {});
    }

    public remove(id: number | any ): Observable<boolean | any> {
        return this.http.delete<boolean | any>(`${this.path}/${id}`, {});
    }

    public save(shareholder: ShareholderVO | any ): Observable<ShareholderVO | any> {
        return this.http.post<ShareholderVO | any>(`${this.path}`, shareholder);
    }

    public search(criteria: ShareholderCriteria | any ): Observable<ShareholderVO[] | any[]> {
        return this.http.post<ShareholderVO[] | any[]>(`${this.path}/search`, criteria);
    }

}
