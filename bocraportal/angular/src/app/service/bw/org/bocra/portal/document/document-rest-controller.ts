// Generated by andromda-angular cartridge (service\service.impl.ts.vsl) CAN EDIT
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { DocumentVO } from '@app/model/bw/org/bocra/portal/document/document-vo';
import { DocumentCriteria } from '@app/model/bw/org/bocra/portal/document/document-criteria';
import { HttpClient } from '@angular/common/http';
import { DocumentMetadataTarget } from '@app/model/bw/org/bocra/portal/document/document-metadata-target';

@Injectable({
  providedIn: 'root',
})
export class DocumentRestController {
  protected path = '/document';

  constructor(private http: HttpClient) {}

  public downloadFile(documentId: string | any): Observable<any> {
    return this.http.get(`${this.path}/download/${documentId}`, { responseType: 'blob' });
  }

  public findByDocumentId(documentId: string | any): Observable<DocumentVO | any> {
    return this.http.get<DocumentVO | any>(`${this.path}/load?documentId=${documentId}`);
  }

  public findByDocumentIds(documentIds: Set<string> | any): Observable<DocumentVO[] | any[]> {
    return this.http.get<DocumentVO[] | any[]>(`${this.path}/byIds/documentIds/${documentIds}`);
  }

  public findById(id: number | any): Observable<DocumentVO | any> {
    return this.http.get<DocumentVO | any>(`${this.path}/${id}`);
  }

  public findByIds(ids: Set<number> | any): Observable<DocumentVO[] | any[]> {
    return this.http.get<DocumentVO[] | any[]>(`${this.path}/byIds/ids/${ids}`);
  }

  public findDocumentsByMetadata(
    metadataTarget: DocumentMetadataTarget | any,
    metadataTargetId: number | any
  ): Observable<DocumentVO[] | any[]> {
    return this.http.get<DocumentVO[] | any[]>(
      `${this.path}/metadata?metadataTarget=${metadataTarget}&metadataTargetId=${metadataTargetId}`
    );
  }

  public getAll(): Observable<DocumentVO[] | any[]> {
    return this.http.get<DocumentVO[] | any[]>(`${this.path}/all`);
  }

  public getAllPaged(pageNumber: number | any, pageSize: number | any): Observable<DocumentVO[] | any[]> {
    return this.http.get<DocumentVO[] | any[]>(`${this.path}/page/${pageNumber}/size/${pageSize}`);
  }

  public remove(documentId: string | any): Observable<boolean | any> {
    return this.http.delete<boolean | any>(`${this.path}/${documentId}`, {});
  }

  public save(document: DocumentVO | any): Observable<DocumentVO | any> {
    return this.http.post<DocumentVO | any>(`${this.path}`, document);
  }

  public search(criteria: DocumentCriteria | any): Observable<DocumentVO[] | any[]> {
    return this.http.post<DocumentVO[] | any[]>(`${this.path}/search`, criteria);
  }

  public uploadFile(
    documentTypeId: number | any,
    file: File | any,
    fileName: string | any,
    metadataTarget: DocumentMetadataTarget | any,
    metadataTargetId: number | any
  ): Observable<DocumentVO | any> {
    const formData: FormData = new FormData();
    formData.append('documentTypeId', documentTypeId);
    formData.append('file', file);
    formData.append('fileName', fileName);
    formData.append('metadataTarget', metadataTarget);
    formData.append('metadataTargetId', metadataTargetId);
    return this.http.post<DocumentVO | any>(`${this.path}/upload`, formData);
  }
}
