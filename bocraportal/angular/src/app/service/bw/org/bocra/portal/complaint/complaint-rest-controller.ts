// Generated by andromda-angular cartridge (service\service.impl.ts.vsl) CAN EDIT
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ComplaintReplyVO } from '@app/model/bw/org/bocra/portal/complaint/complaint-reply-vo';
import { ComplaintVO } from '@app/model/bw/org/bocra/portal/complaint/complaint-vo';
import { HttpClient } from '@angular/common/http';
import { ComplaintSeachCriteria } from '@app/model/bw/org/bocra/portal/complaint/complaint-seach-criteria';
import { DocumentVO } from '@app/model/bw/org/bocra/portal/document/document-vo';

@Injectable({
  providedIn: 'root'
})
export class ComplaintRestController {
    
    protected path = 'complaint';

    constructor(private http: HttpClient) {
    }

    public addDocument(id: number | any , documentTypeId: number | any , file: File | any , fileName: string | any ): Observable<DocumentVO | any> {

        const formData: FormData = new FormData();
        formData.append('documentTypeId', documentTypeId);
        formData.append('file', file);
        formData.append('fileName', fileName);
        
        return this.http.post<DocumentVO | any>(this.path + `/${id}/document`, formData);

    }

    public addComplaintReply(complaintId: string | any , reply: ComplaintReplyVO | any ): Observable<ComplaintReplyVO | any> {
        return this.http.post<ComplaintReplyVO | any>(`${this.path}/complaint/reply?complaintId=${complaintId}`, reply);
    }

    public findByComplaintId(complaintId: string | any ): Observable<ComplaintVO | any> {
        return this.http.get<ComplaintVO | any>(`${this.path}?complaintId=${complaintId}`, {});
    }

    public findById(id: number | any ): Observable<ComplaintVO | any> {
        return this.http.get<ComplaintVO | any>(`${this.path}/${id}`, {});
    }

    public getAll(): Observable<ComplaintVO[] | any[]> {
        return this.http.get<ComplaintVO[] | any[]>(`${this.path}/all`);
    }

    public getAllPaged(pageNumber: number | any , pageSize: number | any ): Observable<ComplaintVO[] | any[]> {
        return this.http.get<ComplaintVO[] | any[]>(`${this.path}/page/${pageNumber}/size/${pageSize}`, {});
    }

    public remove(id: number | any ): Observable<boolean | any> {
        return this.http.delete<boolean | any>(`${this.path}/${id}`, {});
    }

    public removeComplaintReply(id: number | any ): Observable<Boolean | any> {
        return this.http.delete<Boolean | any>(`${this.path}/complaint/reply`, id);
    }

    public save(complaint: ComplaintVO | any ): Observable<ComplaintVO | any> {
        return this.http.post<ComplaintVO | any>(`${this.path}`, complaint);
    }

    public search(criteria: ComplaintSeachCriteria | any ): Observable<ComplaintVO[] | any[]> {
        return this.http.post<ComplaintVO[] | any[]>(`${this.path}/search`, criteria);
    }

}
