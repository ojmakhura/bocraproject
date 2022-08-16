// Generated by andromda-angular cartridge (service\service.ts.vsl) DO NOT EDIT






// Generated by andromda-angular cartridge (service\service.impl.ts.vsl) CAN EDIT
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { LicenseeFormVO } from '@app/model/bw/org/bocra/portal/licensee/form/licensee-form-vo';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class LicenseeFormRestController {
    protected path = '/licensee/form';


    constructor(private http: HttpClient) {
    }

    public create(licenseeId: number | any , formId: number | any ): Observable<LicenseeFormVO | any> {

        return this.http.post<LicenseeFormVO | any>(this.path, {licenseeId: licenseeId, formId: formId});

    }

    public findByForm(formId: number | any ): Observable<LicenseeFormVO[] | any[]> {

        return this.http.get<LicenseeFormVO[] | any[]>(this.path + `/{formId}/formId/${formId}`);

    }

    public findById(id: number | any ): Observable<LicenseeFormVO | any> {

        return this.http.get<LicenseeFormVO | any>(this.path + `/${id}`);

    }

    public findByLicensee(licenseeId: number | any ): Observable<LicenseeFormVO[] | any[]> {

        return this.http.get<LicenseeFormVO[] | any[]>(this.path + `/${licenseeId}`);

    }

    public getAll(): Observable<LicenseeFormVO[] | any[]> {

        return this.http.get<LicenseeFormVO[] | any[]>(this.path + `/all`);

    }

    public remove(id: number | any ): Observable<boolean | any> {

        return this.http.delete<boolean | any>(this.path + `/${id}`);

    }

    public updateForm(id: number | any , formId: number | any ): Observable<LicenseeFormVO | any> {

        return this.http.patch<LicenseeFormVO | any>(this.path + `/${id}/${formId}`, {id: id, formId: formId});

    }

    public updateLicensee(id: number | any , licenseeId: number | any ): Observable<LicenseeFormVO | any> {

        return this.http.patch<LicenseeFormVO | any>(this.path + `/${id}/${licenseeId}`, {id: id, licenseeId: licenseeId});

    }

}
