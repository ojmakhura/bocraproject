// Generated by andromda-angular cartridge (view\view.component.imp.ts.vsl) CAN EDIT!
import { HttpClient } from '@angular/common/http';
import { Component, Injector } from '@angular/core';
import { SelectItem } from '@app/utils/select-item';
import { EditUserComponent } from '@app/view/user/edit-user.component';
import { EditUserSaveForm } from '@app/view/user/edit-user.component';
import { EditUserDeleteForm } from '@app/view/user/edit-user.component';
import { EditUserSearchForm } from '@app/view/user/edit-user.component';
import { EditUserVarsForm } from '@app/view/user/edit-user.component';
import { environment } from '@env/environment';
import * as LicenseSelectors from '@app/store/licensee/licensee.selectors';
import * as LicenseeActions from '@app/store/licensee/licensee.actions';
import * as UserActions from '@app/store/user/user.actions';
import { select } from '@ngrx/store';
import { LicenseeVO } from '@app/model/bw/org/bocra/portal/licensee/licensee-vo';
import { Observable } from 'rxjs';
import { MatRadioChange } from '@angular/material/radio';
import { KeycloakService } from 'keycloak-angular';

@Component({
  selector: 'app-edit-user',
  templateUrl: './edit-user.component.html',
  styleUrls: ['./edit-user.component.scss'],
})

 export class EditUserComponentImpl extends EditUserComponent {
  protected http: HttpClient;
  protected keycloakService: KeycloakService;
  store: any;

  constructor(private injector: Injector) {
    super(injector);
    this.http = this._injector.get(HttpClient);
    this.userLicensees$ = this.store.pipe(select(LicenseSelectors.selectLicensees));
    this.keycloakService = this.injector.get(KeycloakService);
  }

  override beforeOnInit(form: EditUserVarsForm): EditUserVarsForm {
    this.http.get<any[]>(environment.keycloakClientRoleUrl).subscribe((role) => {
      role.forEach((val) => {
        let item = new SelectItem();
        item.label = val['description'];
        item.value = val['name'];

        this.userRolesBackingList.push(item);
      });
    });

    this.http.get<any[]>(environment.keycloakRealmRoleUrl).subscribe((role) => {
      role.forEach((val) => {
        let item = new SelectItem();
        item.label = val['description'];
        item.value = val['name'];

        this.userRolesBackingList.push(item);
      });
    });

    this.keycloakService.getToken().then((token) => console.log(token));

    return form;
  }

  override afterOnInit() {
    this.user$.subscribe((user) => {
      this.setEditUserFormValue({ user: user });
    });
  }

  override doNgAfterViewInit() {
    this.route.queryParams.subscribe((queryParams: any) => {
      
      if (queryParams?.userId) {
        this.store.dispatch(
          UserActions.findById({userId: queryParams.userId, loading: true})
        );
      }
    });
  }

  override doNgOnDestroy() {
    this.store.dispatch(LicenseeActions.licenseeReset());
  }

  /**
   * This method may be overwritten
   */
  override beforeEditUserSave(form: EditUserSaveForm): void {

    if (this.editUserForm.valid && this.editUserForm.dirty) {
      if (form.user?.id) {
        form.user.updatedBy = this.keycloakService.getUsername();
        form.user.updatedDate = new Date();
      } else {
        form.user.createdBy = this.keycloakService.getUsername();
        form.user.createdDate = new Date();
      }
  
      // this.store.dispatch(
      //   UserActions.save({
      //     user: form.user,
      //     loading: true,
      //   })
      // );
      }
      else {
        let messages: string[] = []
        if(!this.userUsernameControl.valid) {
          messages.push("Username has errors")
        }
        if(!this.userEmailControl.valid) {
          messages.push("Email has errors")
        }
        if(!this.userPasswordControl.valid) {
          messages.push("Password has errors")
        }
        if(!this.userFirstNameControl.valid) {
          messages.push("First name has errors")
        }
        if(!this.userLastNameControl.valid) {
          messages.push("lastname has errors")
        }

        this.store.dispatch(UserActions.userFailure({ messages: messages }));
      }
    }
  


  override userLicenseeSearch(): void {
    let criteria: string = '';
    criteria = this.userLicenseeSearchField.value;
    this.store.dispatch(
      LicenseeActions.search({
        criteria: { uin: criteria, licenseeName: criteria },
        loading: true,
      })
    );
  }
}

