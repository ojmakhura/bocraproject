// Generated by andromda-angular cartridge (service\service.ts.vsl) DO NOT EDIT

// Generated by andromda-angular cartridge (service\service.impl.ts.vsl) CAN EDIT
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class AuthController {
  protected path = '/auth';

  constructor(private http: HttpClient) {}

  public getAccessTokenString(username: string | any, password: string | any): Observable<string | any> {
    return this.http.post<string | any>(this.path + `/token_string`, { username: username, password: password });
  }

  public getRealmUrl(): Observable<string | any> {
    return this.http.get<string | any>(this.path);
  }

  public getRoles(): Observable<RoleRepresentation[] | any[]> {
    return this.http.get<RoleRepresentation[] | any[]>(this.path + `/roles`);
  }

  public getUserInfo(token: string | any): Observable<string | any> {
    return this.http.post<string | any>(this.path + `/info`, token);
  }

  public signin(username: string | any, password: string | any): Observable<string | any> {
    return this.http.post<string | any>(this.path + `/signin`, { username: username, password: password });
  }

  public signout(refreshToken: string | any): Observable<void> {
    return this.http.post<void>(this.path + `/signout`, refreshToken);
  }
}
