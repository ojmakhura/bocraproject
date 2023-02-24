// Generated by andromda-angular cartridge (service\service.impl.ts.vsl) CAN EDIT
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { SystemConfigVO } from '@app/model/bw/org/bocra/portal/config/system-config-vo';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class SystemConfigRestController {
    
    protected path = '/configs';

    constructor(private http: HttpClient) {
    }

    public findById(id: number | any ): Observable<SystemConfigVO | any> {
        return this.http.get<SystemConfigVO | any>(`${this.path}/${id}`, {});
    }

    public getAll(): Observable<SystemConfigVO[] | any[]> {
        return this.http.get<SystemConfigVO[] | any[]>(`${this.path}/all`);
    }

    public getAllPaged(pageNumber: number | any , pageSize: number | any ): Observable<SystemConfigVO[] | any[]> {
        return this.http.get<SystemConfigVO[] | any[]>(`${this.path}/page/${pageNumber}/size/${pageSize}`, {});
    }

    public remove(id: number | any ): Observable<boolean | any> {
        return this.http.delete<boolean | any>(`${this.path}/${id}`, {});
    }

    public save(systemConfig: SystemConfigVO | any ): Observable<SystemConfigVO | any> {
        return this.http.post<SystemConfigVO | any>(`${this.path}`, systemConfig);
    }

    public search(criteria: string | any ): Observable<SystemConfigVO[] | any[]> {
        return this.http.get<SystemConfigVO[] | any[]>(`${this.path}/search?criteria=${criteria}`, {});
    }

}
