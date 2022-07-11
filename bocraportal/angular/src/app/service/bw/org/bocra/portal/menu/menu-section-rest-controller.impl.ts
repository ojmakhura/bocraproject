// Generated by andromda-angular cartridge (service\service.impl.ts.vsl) CAN EDIT
import { Injectable, Injector } from '@angular/core';
import { Observable } from 'rxjs';
import { MenuSectionRestController } from '@app/service/bw/org/bocra/portal/menu/menu-section-rest-controller';
import { MenuSectionVO } from '@app/model/bw/org/bocra/portal/menu/menu-section-vo';

@Injectable()
export class MenuSectionRestControllerImpl extends MenuSectionRestController {

    constructor(private injector: Injector) {
        super(injector);
    }

    public override findByAuthorisationRoles(roles: Set<string> | any ): Observable<MenuSectionVO[] | any[]> {

        return this.http.get<MenuSectionVO[] | any[]>(this.path + `/authorised`);

    }

    public override findById(id: number | any ): Observable<MenuSectionVO | any> {

        return this.http.get<MenuSectionVO | any>(this.path + `/id/${id}`);

    }

    public override getAll(): Observable<MenuSectionVO[] | any[]> {

        return this.http.get<MenuSectionVO[] | any[]>(this.path + `/all`);

    }

    public override getAllPaged(pageNumber: number | any , pageSize: number | any ): Observable<MenuSectionVO[] | any[]> {

        return this.http.get<MenuSectionVO[] | any[]>(this.path + `/page/${pageNumber}/size/${pageSize}`);

    }

    public override remove(id: number | any ): Observable<boolean | any> {

        return this.http.delete<boolean | any>(this.path + `/id/${id}`);

    }

    public override save(menuSection: MenuSectionVO | any ): Observable<MenuSectionVO | any> {

        return this.http.post<MenuSectionVO | any>(this.path, menuSection);

    }

    public override search(criteria: string | any ): Observable<MenuSectionVO[] | any[]> {

        return this.http.post<MenuSectionVO[] | any[]>(this.path + `/search?criteria=${criteria}`, {});

    }

}