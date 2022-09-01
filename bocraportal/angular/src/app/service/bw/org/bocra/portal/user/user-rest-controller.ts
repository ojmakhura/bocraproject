// Generated by andromda-angular cartridge (service\service.ts.vsl) DO NOT EDIT






// Generated by andromda-angular cartridge (service\service.impl.ts.vsl) CAN EDIT
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserVO } from '@app/model/bw/org/bocra/portal/user/user-vo';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UserRestController {
    protected path = '/user';

    constructor(private http: HttpClient) {
    }

    public createUser(user: UserVO | any ): Observable<UserVO | any> {

        return this.http.post<UserVO | any>(this.path + `/create`, user);
    }
    
    public findUserById(userId: string | any): Observable<UserVO | any> {
        if (!userId) {
            userId = '';
        }

        return this.http.get<UserVO | any>(this.path + `?userId=${userId}`);
    }

    public loadUsers(): Observable<UserVO[] | any[]> {

        return this.http.get<UserVO[] | any[]>(this.path + `/load`);
    }

    public search(criteria: string | any ): Observable<UserVO | any> {

        if(!criteria) {
            criteria = '';
        }

        return this.http.get<UserVO | any>(this.path + `/search?criteria=${criteria}`);
    }

    public updateUserName(userId: string | any , username: string | any ): Observable<Boolean | any> {

        return this.http.patch<Boolean | any>(this.path + `/${userId}`, {userId: userId, username: username});

    }

}
