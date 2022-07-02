// Generated by andromda-angular cartridge (service\service.impl.ts.vsl) CAN EDIT
import { Injectable, Injector } from '@angular/core';
import { Observable } from 'rxjs';
import { LicenseeShareholderRestController } from '@app/service/bw/org/bocra/portal/licensee/shares/licensee-shareholder-rest-controller';
import { LicenseeFormVO } from '@app/model/bw/org/bocra/portal/licensee/form/licensee-form-vo';
import { LicenseeShareholderVO } from '@app/model/bw/org/bocra/portal/licensee/shares/licensee-shareholder-vo';

@Injectable()
export class LicenseeShareholderRestControllerImpl extends LicenseeShareholderRestController {

    constructor(private injector: Injector) {
        super(injector);
    }

    public override create(licenseeId: number | any , shareholderId: number | any ): Observable<LicenseeShareholderVO | any> {

        return this.http.post<LicenseeShareholderVO | any>(this.path, {licenseeId: licenseeId, shareholderId: shareholderId});

    }

    public override findByLicensee(licenseeId: number | any ): Observable<LicenseeShareholderVO[] | any[]> {

        return this.http.get<LicenseeShareholderVO[] | any[]>(this.path + `/${licenseeId}`);

    }

    public override findByShareholder(shareholderId: number | any ): Observable<LicenseeShareholderVO[] | any[]> {

        return this.http.get<LicenseeShareholderVO[] | any[]>(this.path + `/${shareholderId}/shareholderId/${shareholderId}`);

    }

    public override findShareholderById(id: number | any ): Observable<LicenseeShareholderVO | any> {

        return this.http.get<LicenseeShareholderVO | any>(this.path + `/${id}`);

    }

    public override getAllShareholders(): Observable<LicenseeShareholderVO[] | any[]> {

        return this.http.get<LicenseeShareholderVO[] | any[]>(this.path + `/form/all`);

    }

    public override removeShareholder(id: number | any ): Observable<boolean | any> {

        return this.http.delete<boolean | any>(this.path + `/${id}`);

    }

    public override updateLicensee(id: number | any , licenseeId: number | any ): Observable<LicenseeFormVO | any> {

        return this.http.patch<LicenseeFormVO | any>(this.path + `/${id}/${licenseeId}`, {id: id, licenseeId: licenseeId});

    }

    public override updateShareholder(id: number | any , formId: number | any ): Observable<LicenseeShareholderVO | any> {

        return this.http.patch<LicenseeShareholderVO | any>(this.path + `/${id}/${shareholderId}`, {id: id, formId: formId});

    }

}