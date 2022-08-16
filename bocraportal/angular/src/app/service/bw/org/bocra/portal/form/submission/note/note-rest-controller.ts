// Generated by andromda-angular cartridge (service\service.ts.vsl) DO NOT EDIT






// Generated by andromda-angular cartridge (service\service.impl.ts.vsl) CAN EDIT
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { NoteVO } from '@app/model/bw/org/bocra/portal/form/submission/note/note-vo';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class NoteRestController {
    protected path = 'form/submission/note';


    constructor(private http: HttpClient) {
    }

    public findById(id: number | any ): Observable<NoteVO | any> {

        return this.http.get<NoteVO | any>(this.path + `/${id}`);

    }

    public getFormSubmissionNotes(formSubmissionId: number | any ): Observable<NoteVO[] | any[]> {

        return this.http.post<NoteVO[] | any[]>(this.path, formSubmissionId);

    }

    public remove(id: number | any ): Observable<boolean | any> {

        return this.http.delete<boolean | any>(this.path + `/${id}`);

    }

    public save(note: NoteVO | any ): Observable<NoteVO | any> {

        return this.http.post<NoteVO | any>(this.path, note);

    }

}
