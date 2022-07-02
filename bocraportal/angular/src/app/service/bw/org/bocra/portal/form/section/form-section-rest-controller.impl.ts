// Generated by andromda-angular cartridge (service\service.impl.ts.vsl) CAN EDIT
import { Injectable, Injector } from '@angular/core';
import { Observable } from 'rxjs';
import { FormSectionRestController } from '@app/service/bw/org/bocra/portal/form/section/form-section-rest-controller';
import { FormSectionVO } from '@app/model/bw/org/bocra/portal/form/section/form-section-vo';

@Injectable()
export class FormSectionRestControllerImpl extends FormSectionRestController {

    constructor(private injector: Injector) {
        super(injector);
    }

    public override findById(id: number | any ): Observable<FormSectionVO | any> {

        return this.http.get<FormSectionVO | any>(this.path + `/${id}`);

    }

    public override getAll(): Observable<FormSectionVO[] | any[]> {

        return this.http.get<FormSectionVO[] | any[]>(this.path + `/all`);

    }

    public override getAllPaged(pageNumber: number | any , pageSize: number | any ): Observable<FormSectionVO[] | any[]> {

        return this.http.get<FormSectionVO[] | any[]>(this.path + `/page/${pageNumber}/size/${pageSize}`);

    }

    public override remove(id: number | any ): Observable<boolean | any> {

        return this.http.delete<boolean | any>(this.path + `/${id}`);

    }

    public override save(formSection: FormSectionVO | any ): Observable<FormSectionVO | any> {

        return this.http.post<FormSectionVO | any>(this.path, formSection);

    }

    public override search(criteria: string | any ): Observable<FormSectionVO[] | any[]> {

        return this.http.post<FormSectionVO[] | any[]>(this.path + `/search`, criteria);

    }

}