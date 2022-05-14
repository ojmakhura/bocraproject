// Generated by andromda-angular cartridge (service\service.impl.ts.vsl) CAN EDIT
import { Injectable, Injector } from '@angular/core';
import { HttpClient} from '@angular/common/http';
import { Observable } from 'rxjs';
import { DocumentRestController } from '@app/service/bw/org/bocra/portal/document/document-rest-controller';
import { DocumentVO } from '@app/model/bw/org/bocra/portal/document/document-vo';

@Injectable()
export class DocumentRestControllerImpl extends DocumentRestController {

    constructor(private injector: Injector) {
        super(injector);
    }

    public findById(id: number | any ): Observable<DocumentVO | any> {

        return this.http.get<DocumentVO | any>(this.path + `//id/${id}`);

    }

    public getAll(): Observable<DocumentVO[] | any> {

        return this.http.get<DocumentVO[] | any>(this.path + `/all`);

    }

    public getAllPaged(pageNumber: number | any , pageSize: number | any ): Observable<DocumentVO[] | any> {

        return this.http.get<DocumentVO[] | any>(this.path + `/all/pageNumber/${pageNumber}/pageSize/${pageSize}`);

    }

    public getLicenceDocuments(licenceId: number | any ): Observable<DocumentVO | any> {

        return this.http.get<DocumentVO | any>(this.path + `/`);

    }

    public getLicenseeDocuments(licenseeId: number | any ): Observable<DocumentVO | any> {

        return this.http.get<DocumentVO | any>(this.path + `//licenseeId/${licenseeId}`);

    }

    public remove(id: number | any ): Observable<boolean | any> {

        return this.http.delete<boolean | any>(this.path + `//id/${id}`);

    }

    public save(document: DocumentVO | any ): Observable<DocumentVO | any> {

        return this.http.post<DocumentVO | any>(this.path + `/`, document);

    }

    public search(criteria: string | any ): Observable<DocumentVO[] | any> {

        return this.http.get<DocumentVO[] | any>(this.path + `/search/criteria/${criteria}`);

    }

}