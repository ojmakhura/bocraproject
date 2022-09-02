// Generated by andromda-angular cartridge (service\service.ts.vsl) DO NOT EDIT






// Generated by andromda-angular cartridge (service\service.impl.ts.vsl) CAN EDIT
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { LicenseeSectorVO } from '@app/model/bw/org/bocra/portal/licensee/sector/licensee-sector-vo';
import { SectorVO } from '@app/model/bw/org/bocra/portal/sector/sector-vo';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class SectorRestController {
    protected path = '/sector';


    constructor(private http: HttpClient) {
    }

    public addLicensee(sectorId: number | any , licenseeId: number | any ): Observable<LicenseeSectorVO | any> {

        return this.http.get<LicenseeSectorVO | any>(this.path + `/${sectorId}/add/licensee/${licenseeId}`);

    }

    public findById(id: number | any ): Observable<SectorVO | any> {

        return this.http.get<SectorVO | any>(this.path + `/${id}`);

    }

    public getAll(): Observable<SectorVO[] | any[]> {

        return this.http.get<SectorVO[] | any[]>(this.path + `/all`);

    }

    public getAllPaged(pageNumber: number | any , pageSize: number | any ): Observable<SectorVO[] | any[]> {

        return this.http.get<SectorVO[] | any[]>(this.path + `/page/${pageNumber}/size/${pageSize}`);

    }

    public remove(id: number | any ): Observable<boolean | any> {

        return this.http.delete<boolean | any>(this.path + `/${id}`);

    }

    public save(sector: SectorVO | any ): Observable<SectorVO | any> {

        return this.http.post<SectorVO | any>(this.path, sector);

    }

    public search(criteria: string | any ): Observable<SectorVO[] | any[]> {

        return this.http.post<SectorVO[] | any[]>(this.path + `/search`, criteria);

    }

}
