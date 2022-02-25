// Generated by andromda-angular cartridge (service\service.impl.ts.vsl) CAN EDIT
import { Injectable, Injector } from '@angular/core';
import { HttpClient} from '@angular/common/http';
import { Observable } from 'rxjs';
import { UrlGuardRestController } from '@app/service/bw/org/bocra/portal/guard/url-guard-rest-controller';
import { UrlGuardVO } from '@app/model/bw/org/bocra/portal/guard/url-guard-vo';
import { UrlGuardCriteria } from '@app/model/bw/org/bocra/portal/guard/url-guard-criteria';

@Injectable()
export class UrlGuardRestControllerImpl extends UrlGuardRestController {

    constructor(private injector: Injector) {
        super(injector);
    }

    public findById(id: number): Observable<UrlGuardVO> {

        return this.http.get<UrlGuardVO>(this.path + `/id/${id}`);

    }

    public getAll(): Observable<UrlGuardVO[]> {

        return this.http.get<UrlGuardVO[]>(this.path + `all`);

    }

    public remove(id: number): Observable<boolean> {

        return this.http.delete<boolean>(this.path + `/id/${id}`);

    }

    public save(urlGuardVO: UrlGuardVO): Observable<UrlGuardVO> {

        return this.http.post<UrlGuardVO>(this.path, urlGuardVO);

    }

    public search(criteria: UrlGuardCriteria): Observable<UrlGuardVO[]> {

        return this.http.post<UrlGuardVO[]>(this.path + `search`, criteria);

    }

}