// Generated by andromda-angular cartridge (service\service.impl.ts.vsl) CAN EDIT
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AccessPointVO } from '@app/model/bw/org/bocra/portal/access/access-point-vo';
import { AccessPointCriteria } from '@app/model/bw/org/bocra/portal/access/access-point-criteria';
import { HttpClient } from '@angular/common/http';
import { DataPage } from '@app/model/bw/org/bocra/portal/data-page';

@Injectable({
  providedIn: 'root',
})
export class AccessPointRestController {
  protected path = '/access';

  constructor(private http: HttpClient) {}

  public findById(id: number | any): Observable<AccessPointVO | any> {
    return this.http.get<AccessPointVO | any>(this.path + `/id/${id}`);
  }

  public getAll(): Observable<AccessPointVO[] | any[]> {
    return this.http.get<AccessPointVO[] | any[]>(this.path + `/all`);
  }

  public getAllPaged(pageNumber: number | any, pageSize: number | any): Observable<DataPage | any> {
    return this.http.get<AccessPointVO[] | any[]>(this.path + `/page/${pageNumber}/size/${pageSize}`);
  }

  public pagedSearch(
    pageNumber: number | any,
    pageSize: number | any,
    criteria: AccessPointCriteria | any
  ): Observable<DataPage | any> {

    return this.http.post<AccessPointVO[] | any[]>(this.path + `/search/page/${pageNumber}/size/${pageSize}`, criteria);
  }

  public remove(id: number | any): Observable<boolean | any> {
    return this.http.delete<boolean | any>(this.path + `/id/${id}`);
  }

  public save(accessPoint: AccessPointVO | any): Observable<AccessPointVO | any> {
    return this.http.post<AccessPointVO | any>(this.path, accessPoint);
  }

  public search(criteria: AccessPointCriteria | any): Observable<AccessPointVO[] | any[]> {
    return this.http.post<AccessPointVO[] | any[]>(this.path + `/search`, criteria);
  }
}
