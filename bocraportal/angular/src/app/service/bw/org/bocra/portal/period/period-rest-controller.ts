// Generated by andromda-angular cartridge (service\service.ts.vsl) DO NOT EDIT






// Generated by andromda-angular cartridge (service\service.impl.ts.vsl) CAN EDIT
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { PeriodVO } from '@app/model/bw/org/bocra/portal/period/period-vo';
import { PeriodCriteria } from '@app/model/bw/org/bocra/portal/period/period-criteria';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class PeriodRestController {
    protected path = 'period';


    constructor(private http: HttpClient) {
    }

    public findById(id: number | any ): Observable<PeriodVO | any> {

        return this.http.get<PeriodVO | any>(this.path + `/${id}`);

    }

    public getAll(): Observable<PeriodVO[] | any[]> {

        return this.http.get<PeriodVO[] | any[]>(this.path);

    }

    public getAllPaged(pageNumber: number | any , pageSize: number | any ): Observable<PeriodVO[] | any[]> {

        return this.http.get<PeriodVO[] | any[]>(this.path + `/page/${pageNumber}/size/${pageSize}`);

    }

    public remove(id: number | any ): Observable<boolean | any> {

        return this.http.delete<boolean | any>(this.path + `/${id}`);

    }

    public save(period: PeriodVO | any ): Observable<PeriodVO | any> {

        return this.http.post<PeriodVO | any>(this.path, period);

    }

    public search(criteria: PeriodCriteria | any ): Observable<PeriodVO[] | any[]> {

        return this.http.post<PeriodVO[] | any[]>(this.path + `/search`, criteria);

    }

}
