// Generated by andromda-angular cartridge (service\service.impl.ts.vsl) CAN EDIT
import { Injectable, Injector } from '@angular/core';
import { HttpClient} from '@angular/common/http';
import { Observable } from 'rxjs';
import { SMSTrafficRestController } from '@app/service/bw/org/bocra/portal/form/sms/smstraffic-rest-controller';
import { SimVO } from '@app/model/bw/org/bocra/portal/form/sim/sim-vo';
import { FormCriteria } from '@app/model/bw/org/bocra/portal/form/form-criteria';
import { SMSTrafficVO } from '@app/model/bw/org/bocra/portal/form/sms/smstraffic-vo';

@Injectable()
export class SMSTrafficRestControllerImpl extends SMSTrafficRestController {

    constructor(private injector: Injector) {
        super(injector);
    }

    public findById(id: number): Observable<SMSTrafficVO> {

        return this.http.get<SMSTrafficVO>(this.path + `/id/${id}`);

    }

    public getAll(): Observable<SMSTrafficVO[]> {

        return this.http.get<SMSTrafficVO[]>(this.path + `/all`);

    }

    public getAllPaged(pageNumber: number, pageSize: number): Observable<SMSTrafficVO[]> {

        return this.http.get<SMSTrafficVO[]>(this.path + `/all/pageNumber/${pageNumber}/pageSize/${pageSize}`);

    }

    public remove(id: number): Observable<boolean> {

        return this.http.delete<boolean>(this.path + `/id/${id}`);

    }

    public save(smsTrafficVO: SimVO): Observable<SMSTrafficVO> {

        return this.http.post<SMSTrafficVO>(this.path, smsTrafficVO);

    }

    public search(searchCriteria: FormCriteria): Observable<SMSTrafficVO[]> {

        return this.http.post<SMSTrafficVO[]>(this.path + `/search`, searchCriteria);

    }

}