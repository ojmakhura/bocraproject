import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { PeriodConfigVO } from '@app/model/bw/org/bocra/portal/period/config/period-config-vo';
import { PeriodConfigCriteria } from '@app/model/bw/org/bocra/portal/period/config/period-config-criteria';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class PeriodConfigRestController {
    protected path = '/period/config';


    constructor(private http: HttpClient) {
    }

    public findById(id: number | any ): Observable<PeriodConfigVO | any> {

        return this.http.get<PeriodConfigVO | any>(this.path + `/${id}`);

    }

    public getAll(): Observable<PeriodConfigVO[] | any[]> {

        return this.http.get<PeriodConfigVO[] | any[]>(this.path + `/all`);

    }

    public getAllPaged(pageNumber: number | any , pageSize: number | any ): Observable<PeriodConfigVO[] | any[]> {

        return this.http.get<PeriodConfigVO[] | any[]>(this.path + `/page/${pageNumber}/size/${pageSize}`);

    }

    public remove(id: number | any ): Observable<boolean | any> {

        return this.http.delete<boolean | any>(this.path + `/${id}`);

    }

    public save(periodConfig: PeriodConfigVO | any ): Observable<PeriodConfigVO | any> {

        return this.http.post<PeriodConfigVO | any>(this.path, periodConfig);

    }

    public search(criteria: PeriodConfigCriteria | any ): Observable<PeriodConfigVO[] | any[]> {

        return this.http.post<PeriodConfigVO[] | any[]>(this.path + `/search`, criteria);

    }

}
