// Generated by andromda-angular cartridge (service\service.impl.ts.vsl) CAN EDIT
import { Injectable, Injector } from '@angular/core';
import { Observable } from 'rxjs';
import { NoteRestController } from '@app/service/bw/org/bocra/portal/form/submission/note/note-rest-controller';
import { NoteVO } from '@app/model/bw/org/bocra/portal/form/submission/note/note-vo';

@Injectable()
export class NoteRestControllerImpl extends NoteRestController {

    constructor(private injector: Injector) {
        super(injector);
    }

    public override findById(id: number | any ): Observable<NoteVO | any> {

        return this.http.get<NoteVO | any>(this.path + `/${id}`);

    }

    public override getFormSubmissionNotes(formSubmissionId: number | any ): Observable<NoteVO[] | any[]> {

        return this.http.post<NoteVO[] | any[]>(this.path, formSubmissionId);

    }

    public override remove(id: number | any ): Observable<boolean | any> {

        return this.http.delete<boolean | any>(this.path + `/${id}`);

    }

    public override save(note: NoteVO | any ): Observable<NoteVO | any> {

        return this.http.post<NoteVO | any>(this.path, note);

    }

}