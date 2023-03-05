// Generated by andromda-angular cartridge (service\service.impl.ts.vsl) CAN EDIT
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { FormSubmissionVO } from '@app/model/bw/org/bocra/portal/form/submission/form-submission-vo';
import { DataFieldVO } from '@app/model/bw/org/bocra/portal/form/submission/data/data-field-vo';
import { SubmissionSummary } from '@app/model/bw/org/bocra/portal/form/submission/submission-summary';
import { FormSubmissionCriteria } from '@app/model/bw/org/bocra/portal/form/submission/form-submission-criteria';
import { HttpClient } from '@angular/common/http';
import { FormSubmissionStatus } from '@model/bw/org/bocra/portal/form/submission/form-submission-status';
import { KeycloakService } from 'keycloak-angular';

@Injectable({
  providedIn: 'root',
})
export class SubmissionRestController {
  protected path = '/form/submission';

  constructor(private http: HttpClient, private keycloakService: KeycloakService) {}

  public addDataField(dataField: DataFieldVO | any): Observable<DataFieldVO | any> {
    return this.http.post<DataFieldVO | any>(`${this.path}/field`, dataField);
  }

  public addDataFields(dataFields: DataFieldVO | any): Observable<DataFieldVO[] | any[]> {
    return this.http.post<DataFieldVO[] | any[]>(`${this.path}/fields`, dataFields);
  }

  public checkOverdueSubmissions(): Observable<number | any> {
    return this.http.get<number | any>(`${this.path}/overdue`);
  }

  public createNewSubmissions(
    licenseeIds: Set<number> | any,
    activationId: number | any
  ): Observable<FormSubmissionVO[] | any[]> {
    return this.http.post<FormSubmissionVO[] | any[]>(
      `${this.path}/new/multiple?licenseeIds=${licenseeIds}&activationId=${activationId}`,
      {}
    );
  }

  public deleteDataField(id: number | any): Observable<Boolean | any> {
    return this.http.delete<Boolean | any>(`${this.path}/field?id=${id}`, {});
  }

  public findById(id: number | any): Observable<FormSubmissionVO | any> {
    return this.http.get<FormSubmissionVO | any>(`${this.path}/${id}`, {});
  }

  public findByIds(ids: Set<number> | any): Observable<FormSubmissionVO[] | any[]> {
    return this.http.get<FormSubmissionVO[] | any[]>(`${this.path}/ids?ids=${ids}`, {});
  }

  public getAll(): Observable<FormSubmissionVO[] | any[]> {
    return this.http.get<FormSubmissionVO[] | any[]>(`${this.path}/all`);
  }

  public getAllPaged(pageNumber: number | any, pageSize: number | any): Observable<FormSubmissionVO[] | any[]> {
    return this.http.get<FormSubmissionVO[] | any[]>(`${this.path}/page/${pageNumber}/size/${pageSize}`, {});
  }

  public getSubmissionSummary(criteria: FormSubmissionCriteria): Observable<SubmissionSummary | any> {
    return this.http.post<SubmissionSummary | any>(`${this.path}/summary`, criteria);
  }

  public loadDueSubmissions(): Observable<FormSubmissionVO[] | any[]> {
    return this.http.get<FormSubmissionVO[] | any[]>(`${this.path}/due`);
  }

  public remove(id: number | any): Observable<boolean | any> {
    return this.http.delete<boolean | any>(`${this.path}/${id}`, {});
  }

  public save(formSubmissionVO: FormSubmissionVO | any): Observable<FormSubmissionVO | any> {
    return this.http.post<FormSubmissionVO | any>(`${this.path}`, formSubmissionVO);
  }

  public search(criteria: FormSubmissionCriteria | any): Observable<FormSubmissionVO[] | any[]> {
    return this.http.post<FormSubmissionVO[] | any[]>(`${this.path}/search`, criteria);
  }

  public updateSubmissionStatus(
    id: number | any,
    submissionStatus: FormSubmissionStatus | any,
    updateTime: Date,
    username: string | any
  ): Observable<Boolean | any> {
    return this.http.get<Boolean | any>(
      `${this.path}/update?id=${id}&submissionStatus=${submissionStatus}&updateTime=${
        updateTime ? updateTime.toISOString() : new Date().toISOString()
      }&username=${username}`,
      {}
    );
  }
}
