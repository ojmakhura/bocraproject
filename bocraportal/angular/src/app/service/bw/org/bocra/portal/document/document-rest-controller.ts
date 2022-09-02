// Generated by andromda-angular cartridge (service\service.ts.vsl) DO NOT EDIT






// Generated by andromda-angular cartridge (service\service.impl.ts.vsl) CAN EDIT
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { DocumentVO } from '@app/model/bw/org/bocra/portal/document/document-vo';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class DocumentRestController {
    protected path = '/document';


    constructor(private http: HttpClient) {
    }

    public downloadFile(id: number | any ): Observable<string[][] | any[]> {

        return this.http.get<string[][] | any[]>(this.path + `/download/${id}`);

    }

    public findById(id: number | any ): Observable<DocumentVO | any> {

        return this.http.get<DocumentVO | any>(this.path + `/${id}`);

    }

    public getAll(): Observable<DocumentVO[] | any[]> {

        return this.http.get<DocumentVO[] | any[]>(this.path + `/all`);

    }

    public getAllPaged(pageNumber: number | any , pageSize: number | any ): Observable<DocumentVO[] | any[]> {

        return this.http.get<DocumentVO[] | any[]>(this.path + `/page/${pageNumber}/size/${pageSize}`);

    }

    public remove(id: number | any ): Observable<boolean | any> {

        return this.http.delete<boolean | any>(this.path + `/${id}`);

    }

    public save(document: DocumentVO | any ): Observable<DocumentVO | any> {

        return this.http.post<DocumentVO | any>(this.path, document);

    }

    public search(criteria: string | any ): Observable<DocumentVO[] | any[]> {

        return this.http.get<DocumentVO[] | any[]>(this.path + `/search/${criteria}`);

    }

    public uploadLicenceDocument(licenceId: number | any , file: File | any ): Observable<DocumentVO | any> {

        return this.http.post<DocumentVO | any>(this.path + `/licence/upload`, {licenceId: licenceId, file: file});

    }

    public uploadLicenseeDocument(licenseeId: number | any , file: File | any ): Observable<DocumentVO | any> {

        return this.http.post<DocumentVO | any>(this.path + `/licensee/upload`, {licenseeId: licenseeId, file: file});

    }

}
