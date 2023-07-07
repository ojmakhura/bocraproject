// Generated by andromda-angular cartridge (service\service.impl.ts.vsl) CAN EDIT
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthorisationCriteria } from '@app/model/bw/org/bocra/portal/auth/authorisation-criteria';
import { AuthorisationVO } from '@app/model/bw/org/bocra/portal/auth/authorisation-vo';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class AuthorisationRestController {
  protected path = '/authorisation';

  constructor(private http: HttpClient) {}

  public assignMenuSection(
    authorisationId: number | any,
    menuSectionId: number | any
  ): Observable<AuthorisationVO | any> {
    return this.http.get<AuthorisationVO | any>(`${this.path}/${authorisationId}/${menuSectionId}`);
  }

  public findById(id: number | any): Observable<AuthorisationVO | any> {
    return this.http.get<AuthorisationVO | any>(`${this.path}/id/${id}`);
  }

  public findByRolesAndUrl(url: string | any, roles: Set<string> | any): Observable<AuthorisationVO[] | any[]> {
    return this.http.post<AuthorisationVO[] | any[]>(`${this.path}/find-by-roles-url?url=${url}&roles=${roles}`, {});
  }

  public findRestrictedViewItems(url: string | any, roles: Set<string> | any): Observable<string[]> {
    return this.http.post<string[] | any[]>(`${this.path}/restricted-view-items?url=${url}&roles=${roles}`, {});
  }

  public getAccessTypeCodeAuthorisations(
    roles: Set<string> | any,
    accessPointTypeCodes: Set<string> | any
  ): Observable<AuthorisationVO[] | any[]> {
    return this.http.get<AuthorisationVO[] | any[]>(
      `${this.path}/authorised?roles=${roles}&accessPointTypeCodes=${accessPointTypeCodes}`
    );
  }

  public getAll(): Observable<AuthorisationVO[] | any[]> {
    return this.http.get<AuthorisationVO[] | any[]>(`${this.path}/all`);
  }

  public getAllPaged(pageNumber: number | any, pageSize: number | any): Observable<AuthorisationVO[] | any[]> {
    return this.http.get<AuthorisationVO[] | any[]>(`${this.path}/page/${pageNumber}/size/${pageSize}`);
  }

  public remove(id: number | any): Observable<boolean | any> {
    return this.http.delete<boolean | any>(`${this.path}/id/${id}`);
  }

  public save(authorisation: AuthorisationVO | any): Observable<AuthorisationVO | any> {
    return this.http.post<AuthorisationVO | any>(`${this.path}`, authorisation);
  }

  public search(criteria: AuthorisationCriteria | any): Observable<AuthorisationVO[] | any[]> {
    return this.http.post<AuthorisationVO[] | any[]>(`${this.path}/search`, criteria ? criteria : {});
  }
}
