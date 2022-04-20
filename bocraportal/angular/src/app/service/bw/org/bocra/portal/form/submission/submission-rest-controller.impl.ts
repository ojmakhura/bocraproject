// Generated by andromda-angular cartridge (service\service.impl.ts.vsl) CAN EDIT
import { Injectable, Injector } from '@angular/core';
import { HttpClient} from '@angular/common/http';
import { Observable } from 'rxjs';
import { SubmissionRestController } from '@app/service/bw/org/bocra/portal/form/submission/submission-rest-controller';
import { FormSubmissionVO } from '@app/model/bw/org/bocra/portal/form/submission/form-submission-vo';
import { FormSubmissionCriteria } from '@app/model/bw/org/bocra/portal/form/submission/form-submission-criteria';

@Injectable()
export class SubmissionRestControllerImpl extends SubmissionRestController {

    constructor(private injector: Injector) {
        super(injector);
    }

    public findById(id: number | any ): Observable<FormSubmissionVO | any> {

        return this.http.get<FormSubmissionVO | any>(this.path + `/id/${id}`);

    }

    public getAll(): Observable<FormSubmissionVO[] | any> {

        return this.http.get<FormSubmissionVO[] | any>(this.path + `/all`);

    }

    public getAllPaged(pageNumber: number | any , pageSize: number | any ): Observable<FormSubmissionVO[] | any> {

        return this.http.get<FormSubmissionVO[] | any>(this.path + `/all/pageNumber/${pageNumber}/pageSize/${pageSize}`);

    }

    public remove(id: number | any ): Observable<boolean | any> {

        return this.http.delete<boolean | any>(this.path + `/id/${id}`);

    }

    public save(formSubmissionVO: FormSubmissionVO | any ): Observable<FormSubmissionVO | any> {

        return this.http.post<FormSubmissionVO | any>(this.path, formSubmissionVO);

    }

    public search(criteria: FormSubmissionCriteria | any ): Observable<FormSubmissionVO[] | any> {

        return this.http.post<FormSubmissionVO[] | any>(this.path + `/search`, criteria);

    }

}