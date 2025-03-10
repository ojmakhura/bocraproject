// Generated by andromda-angular cartridge (service\service.impl.ts.vsl) CAN EDIT
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { FormFieldVO } from '@app/model/bw/org/bocra/portal/form/field/form-field-vo';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class FormFieldRestController {
  protected path = '/form/field';

  constructor(private http: HttpClient) {}

  public findById(id: number | any): Observable<FormFieldVO | any> {
    return this.http.get<FormFieldVO | any>(this.path + `/${id}`);
  }

  public getAll(): Observable<FormFieldVO[] | any[]> {
    return this.http.get<FormFieldVO[] | any[]>(this.path + `/all`);
  }

  public getAllPaged(pageNumber: number | any, pageSize: number | any): Observable<FormFieldVO[] | any[]> {
    return this.http.get<FormFieldVO[] | any[]>(this.path + `/page/${pageNumber}/size/${pageSize}`);
  }

  public remove(id: number | any): Observable<boolean | any> {
    return this.http.delete<boolean | any>(this.path + `/${id}`);
  }

  public save(formField: FormFieldVO | any): Observable<FormFieldVO | any> {
    return this.http.post<FormFieldVO | any>(this.path, formField);
  }
}
