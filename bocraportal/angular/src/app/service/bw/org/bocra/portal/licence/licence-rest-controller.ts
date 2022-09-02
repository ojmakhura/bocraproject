// Generated by andromda-angular cartridge (service\service.ts.vsl) DO NOT EDIT






// Generated by andromda-angular cartridge (service\service.impl.ts.vsl) CAN EDIT
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { LicenceCriteria } from '@app/model/bw/org/bocra/portal/licence/licence-criteria';
import { LicenceVO } from '@app/model/bw/org/bocra/portal/licence/licence-vo';
import { DocumentVO } from '@app/model/bw/org/bocra/portal/document/document-vo';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class LicenceRestController {
    protected path = '/license';


    constructor(private http: HttpClient) {
    }

    public addDocument(id: number | any , documentTypeId: number | any , file: File | any , fileName: string | any ): Observable<DocumentVO | any> {

        return this.http.post<DocumentVO | any>(this.path + `/${id}/document`, {id: id, documentTypeId: documentTypeId, file: file, fileName: fileName});

    }

    public findById(id: number | any ): Observable<LicenceVO | any> {

        return this.http.get<LicenceVO | any>(this.path + `/${id}`);

    }

    public getAll(): Observable<LicenceVO[] | any[]> {

        return this.http.get<LicenceVO[] | any[]>(this.path + `/all`);

    }

    public getAllPaged(pageNumber: number | any , pageSize: number | any ): Observable<LicenceVO[] | any[]> {

        return this.http.get<LicenceVO[] | any[]>(this.path + `/page/${pageNumber}/size/${pageSize}`);

    }

    public remove(id: number | any ): Observable<boolean | any> {

        return this.http.delete<boolean | any>(this.path + `/${id}`);

    }

    public save(licence: LicenceVO | any ): Observable<LicenceVO | any> {

        return this.http.post<LicenceVO | any>(this.path, licence);

    }

    public search(criteria: LicenceCriteria | any ): Observable<LicenceVO[] | any[]> {

        return this.http.post<LicenceVO[] | any[]>(this.path + `/search`, criteria);

    }

}
