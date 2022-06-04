// Generated by andromda-angular cartridge (service\service.impl.ts.vsl) CAN EDIT
import { Injectable, Injector } from '@angular/core';
import { HttpClient} from '@angular/common/http';
import { Observable } from 'rxjs';
import { ReportConfigRestController } from '@app/service/bw/org/bocra/portal/report/config/report-config-rest-controller';
import { ReportConfigVO } from '@app/model/bw/org/bocra/portal/report/config/report-config-vo';
import { ReportConfigCriteria } from '@app/model/bw/org/bocra/portal/report/config/report-config-criteria';

@Injectable()
export class ReportConfigRestControllerImpl extends ReportConfigRestController {

    constructor(private injector: Injector) {
        super(injector);
    }

    public findById(id: number | any ): Observable<ReportConfigVO | any> {

        return this.http.get<ReportConfigVO | any>(this.path + `/${id}`);

    }

    public getAll(): Observable<ReportConfigVO[] | any> {

        return this.http.get<ReportConfigVO[] | any>(this.path + `/all`);

    }

    public getAllPaged(pageNumber: number | any , pageSize: number | any ): Observable<ReportConfigVO[] | any> {

        return this.http.get<ReportConfigVO[] | any>(this.path + `/page/${pageNumber}/size/${pageSize}`);

    }

    public remove(id: number | any ): Observable<boolean | any> {

        return this.http.delete<boolean | any>(this.path + `/${id}`);

    }

    public save(reportConfig: ReportConfigVO | any ): Observable<ReportConfigVO | any> {

        return this.http.post<ReportConfigVO | any>(this.path, reportConfig);

    }

    public search(criteria: ReportConfigCriteria | any ): Observable<ReportConfigVO[] | any> {

        return this.http.post<ReportConfigVO[] | any>(this.path + `/search`, criteria);

    }

}