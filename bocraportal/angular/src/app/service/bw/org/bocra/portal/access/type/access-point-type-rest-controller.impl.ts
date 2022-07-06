// Generated by andromda-angular cartridge (service\service.impl.ts.vsl) CAN EDIT
import { Injectable, Injector } from '@angular/core';
import { Observable } from 'rxjs';
import { AccessPointTypeRestController } from '@app/service/bw/org/bocra/portal/access/type/access-point-type-rest-controller';
import { AccessPointTypeVO } from '@app/model/bw/org/bocra/portal/access/type/access-point-type-vo';
import { AuthorisationVO } from '@app/model/bw/org/bocra/portal/auth/authorisation-vo';

@Injectable()
export class AccessPointTypeRestControllerImpl extends AccessPointTypeRestController {

    constructor(private injector: Injector) {
        super(injector);
    }

    public override findById(id: number | any ): Observable<AuthorisationVO | any> {

        return this.http.get<AuthorisationVO | any>(this.path + `/id/${id}`);

    }

    public override getAll(): Observable<AccessPointTypeVO[] | any[]> {

        return this.http.get<AccessPointTypeVO[] | any[]>(this.path + `/all`);

    }

    public override getAllPaged(pageNumber: number | any , pageSize: number | any ): Observable<AccessPointTypeVO[] | any[]> {

        return this.http.get<AccessPointTypeVO[] | any[]>(this.path + `/page/${pageNumber}/size/${pageSize}`);

    }

    public override remove(id: number | any ): Observable<boolean | any> {

        return this.http.delete<boolean | any>(this.path + `/id/${id}`);

    }

    public override save(accessPointType: AccessPointTypeVO | any ): Observable<AccessPointTypeVO | any> {

        return this.http.post<AccessPointTypeVO | any>(this.path, accessPointType);

    }

    public override search(criteria: string | any ): Observable<AccessPointTypeVO[] | any[]> {

        return this.http.post<AccessPointTypeVO[] | any[]>(this.path + `/search`, criteria);

    }

}