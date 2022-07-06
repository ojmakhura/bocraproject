// Generated by andromda-angular cartridge (service\service.impl.ts.vsl) CAN EDIT
import { Injectable, Injector } from '@angular/core';
import { Observable } from 'rxjs';
import { LicenseeSectorRestController } from '@app/service/bw/org/bocra/portal/licensee/sector/licensee-sector-rest-controller';
import { FormVO } from '@app/model/bw/org/bocra/portal/form/form-vo';
import { LicenseeFormVO } from '@app/model/bw/org/bocra/portal/licensee/form/licensee-form-vo';

@Injectable()
export class LicenseeSectorRestControllerImpl extends LicenseeSectorRestController {

    constructor(private injector: Injector) {
        super(injector);
    }

    public override create(licenseeId: number | any , sectorId: number | any ): Observable<LicenseeFormVO | any> {

        return this.http.post<LicenseeFormVO | any>(this.path, {licenseeId: licenseeId, sectorId: sectorId});

    }

    public override findById(id: number | any ): Observable<FormVO | any> {

        return this.http.get<FormVO | any>(this.path + `/${id}`);

    }

    public override findByLicensee(licenseeId: number | any ): Observable<LicenseeFormVO[] | any[]> {

        return this.http.get<LicenseeFormVO[] | any[]>(this.path + `/${licenseeId}`);

    }

    public override findBySector(sectorId: number | any ): Observable<FormVO[] | any[]> {

        return this.http.get<FormVO[] | any[]>(this.path + `/{sectorId}/sectorId/${sectorId}`);

    }

    public override getAll(): Observable<LicenseeFormVO[] | any[]> {

        return this.http.get<LicenseeFormVO[] | any[]>(this.path + `/all`);

    }

    public override remove(id: number | any ): Observable<boolean | any> {

        return this.http.delete<boolean | any>(this.path + `/${id}`);

    }

    public override updateLicensee(id: number | any , licenseeId: number | any ): Observable<LicenseeFormVO | any> {

        return this.http.patch<LicenseeFormVO | any>(this.path + `/${id}/${licenseeId}`, {id: id, licenseeId: licenseeId});

    }

    public override updateSector(id: number | any , sectorId: number | any ): Observable<LicenseeFormVO | any> {

        return this.http.patch<LicenseeFormVO | any>(this.path + `/${id}/${sectorId}`, {id: id, sectorId: sectorId});

    }

}