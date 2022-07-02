// Generated by andromda-angular cartridge (service\service.impl.ts.vsl) CAN EDIT
import { Injectable, Injector } from '@angular/core';
import { Observable } from 'rxjs';
import { SubmissionRestController } from '@app/service/bw/org/bocra/portal/form/submission/submission-rest-controller';
import { FormSubmissionCriteria } from '@app/model/bw/org/bocra/portal/form/submission/form-submission-criteria';
import { FormSubmissionVO } from '@app/model/bw/org/bocra/portal/form/submission/form-submission-vo';

@Injectable()
export class SubmissionRestControllerImpl extends SubmissionRestController {

    constructor(private injector: Injector) {
        super(injector);
    }

    public override findById(id: number | any ): Observable<FormSubmissionVO | any> {

        return this.http.get<FormSubmissionVO | any>(this.path + `/${id}`);

    }

    public override getAll(): Observable<FormSubmissionVO[] | any[]> {

        return this.http.get<FormSubmissionVO[] | any[]>(this.path + `/all`);

    }

    public override getAllPaged(pageNumber: number | any , pageSize: number | any ): Observable<FormSubmissionVO[] | any[]> {

        return this.http.get<FormSubmissionVO[] | any[]>(this.path + `/page/${pageNumber}/size/${pageSize}`);

    }

    public override remove(id: number | any ): Observable<boolean | any> {

        return this.http.delete<boolean | any>(this.path + `/${id}`);

    }

    public override save(formSubmissionVO: FormSubmissionVO | any ): Observable<FormSubmissionVO | any> {

        return this.http.post<FormSubmissionVO | any>(this.path, formSubmissionVO);

    }

    public override search(criteria: FormSubmissionCriteria | any ): Observable<FormSubmissionVO[] | any[]> {

        return this.http.post<FormSubmissionVO[] | any[]>(this.path + `/search`, criteria);

    }

}