// Generated by andromda-angular cartridge (service\service.impl.ts.vsl) CAN EDIT
import { Injectable, Injector } from '@angular/core';
import { HttpClient} from '@angular/common/http';
import { Observable } from 'rxjs';
import { PeriodInstanceRestController } from '@app/service/bw/org/bocra/portal/period/instance/period-instance-rest-controller';
import { PeriodCriteria } from '@app/model/bw/org/bocra/portal/period/period-criteria';
import { PeriodVO } from '@app/model/bw/org/bocra/portal/period/period-vo';

@Injectable()
export class PeriodInstanceRestControllerImpl extends PeriodInstanceRestController {

    constructor(private injector: Injector) {
        super(injector);
    }

    public findById(id: number): Observable<PeriodVO> {

        return this.http.get<PeriodVO>(this.path + `/id/${id}`);

    }

    public getAll(): Observable<PeriodVO[]> {

        return this.http.get<PeriodVO[]>(this.path + `/all`);

    }

    public remove(id: number): Observable<boolean> {

        return this.http.delete<boolean>(this.path + `/id/${id}`);

    }

    public save(periodVO: PeriodVO): Observable<PeriodVO> {

        return this.http.post<PeriodVO>(this.path, periodVO);

    }

    public search(searchCriteria: PeriodCriteria): Observable<PeriodVO[]> {

        return this.http.post<PeriodVO[]>(this.path + `/search`, searchCriteria);

    }

}