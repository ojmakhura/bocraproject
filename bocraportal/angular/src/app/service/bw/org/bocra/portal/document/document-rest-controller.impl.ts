// Generated by andromda-angular cartridge (service\service.impl.ts.vsl) CAN EDIT
import { Injectable, Injector } from '@angular/core';
import { Observable } from 'rxjs';
import { DocumentRestController } from '@app/service/bw/org/bocra/portal/document/document-rest-controller';
import { DocumentVO } from '@app/model/bw/org/bocra/portal/document/document-vo';

@Injectable()
export class DocumentRestControllerImpl extends DocumentRestController {

    constructor(private injector: Injector) {
        super(injector);
    }

    public override findById(id: number | any ): Observable<DocumentVO | any> {

        return this.http.get<DocumentVO | any>(this.path + `/${id}`);

    }

    public override getAll(): Observable<DocumentVO[] | any[]> {

        return this.http.get<DocumentVO[] | any[]>(this.path + `/all`);

    }

    public override getAllPaged(pageNumber: number | any , pageSize: number | any ): Observable<DocumentVO[] | any[]> {

        return this.http.get<DocumentVO[] | any[]>(this.path + `/page/${pageNumber}/size/${pageSize}`);

    }

    public override remove(id: number | any ): Observable<boolean | any> {

        return this.http.delete<boolean | any>(this.path + `/${id}`);

    }

    public override save(document: DocumentVO | any ): Observable<DocumentVO | any> {

        return this.http.post<DocumentVO | any>(this.path, document);

    }

    public override search(criteria: string | any ): Observable<DocumentVO[] | any[]> {

        return this.http.get<DocumentVO[] | any[]>(this.path + `/search/criteria/${criteria}`);

    }

}