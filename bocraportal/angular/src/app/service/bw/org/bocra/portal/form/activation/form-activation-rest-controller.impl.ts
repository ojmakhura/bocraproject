// Generated by andromda-angular cartridge (service\service.impl.ts.vsl) CAN EDIT
import { Injectable, Injector } from '@angular/core';
import { Observable } from 'rxjs';
import { FormActivationRestController } from '@app/service/bw/org/bocra/portal/form/activation/form-activation-rest-controller';
import { FormActivationCriteria } from '@app/model/bw/org/bocra/portal/form/activation/form-activation-criteria';
import { FormActivationVO } from '@app/model/bw/org/bocra/portal/form/activation/form-activation-vo';

@Injectable()
export class FormActivationRestControllerImpl extends FormActivationRestController {

    constructor(private injector: Injector) {
        super(injector);
    }

    public override findById(id: number | any ): Observable<FormActivationVO | any> {

        return this.http.get<FormActivationVO | any>(this.path + `/${id}`);

    }

    public override getAll(): Observable<FormActivationVO[] | any[]> {

        return this.http.get<FormActivationVO[] | any[]>(this.path + `/all`);

    }

    public override getAllPaged(pageNumber: number | any , pageSize: number | any ): Observable<FormActivationVO[] | any[]> {

        return this.http.get<FormActivationVO[] | any[]>(this.path + `/page/${pageNumber}/size/${pageSize}`);

    }

    public override remove(id: number | any ): Observable<boolean | any> {

        return this.http.delete<boolean | any>(this.path + `/${id}`);

    }

    public override save(formActivation: FormActivationVO | any ): Observable<FormActivationVO | any> {

        return this.http.post<FormActivationVO | any>(this.path, formActivation);

    }

    public override search(criteria: FormActivationCriteria | any ): Observable<FormActivationVO[] | any[]> {

        return this.http.post<FormActivationVO[] | any[]>(this.path + `/search`, criteria);

    }

}