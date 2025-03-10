// Generated by andromda-angular cartridge (service\service.ts.vsl) DO NOT EDIT

// Generated by andromda-angular cartridge (service\service.impl.ts.vsl) CAN EDIT
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AccessPointCriteria } from '@app/model/bw/org/bocra/portal/access/access-point-criteria';
import { AccessPointTypeVO } from '@app/model/bw/org/bocra/portal/access/type/access-point-type-vo';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class AccessPointTypeRestController {
  protected path = '/access/type';

  constructor(private http: HttpClient) {}

  public findById(id: number | any): Observable<AccessPointTypeVO | any> {
    return this.http.get<AccessPointTypeVO | any>(this.path + `/id/${id}`);
  }

  public getAll(): Observable<AccessPointTypeVO[] | any[]> {
    return this.http.get<AccessPointTypeVO[] | any[]>(this.path + `/all`);
  }

  public getAllPaged(pageNumber: number | any, pageSize: number | any): Observable<AccessPointTypeVO[] | any[]> {
    return this.http.get<AccessPointTypeVO[] | any[]>(this.path + `/page/${pageNumber}/size/${pageSize}`);
  }

  public pagedSearch(
    pageNumber: number | any,
    pageSize: number | any,
    criteria: AccessPointCriteria | any
  ): Observable<AccessPointTypeVO[] | any[]> {
    if(criteria) {
      return this.http.post<AccessPointTypeVO[] | any[]>(this.path + `/search/page/${pageNumber}/size/${pageSize}`, {
        pageNumber: pageNumber,
        pageSize: pageSize,
        criteria: criteria,
      });
    } else {
      return this.getAllPaged(pageNumber, pageSize);
    }
  }

  public remove(id: number | any): Observable<boolean | any> {
    return this.http.delete<boolean | any>(this.path + `/id/${id}`);
  }

  public save(accessPointType: AccessPointTypeVO | any): Observable<AccessPointTypeVO | any> {
    return this.http.post<AccessPointTypeVO | any>(this.path, accessPointType);
  }

  public search(criteria: string | any): Observable<AccessPointTypeVO[] | any[]> {
    if(criteria) {
      return this.http.post<AccessPointTypeVO[] | any[]>(this.path + `/search`, criteria);
    } else {
      return this.getAll();
    }
  }
}
