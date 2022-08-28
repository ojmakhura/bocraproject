// Generated by andromda-angular cartridge (view\view.component.imp.ts.vsl) CAN EDIT!
import { HttpClient } from '@angular/common/http';
import { Component, Injector } from '@angular/core';
import { FormArray, FormGroup, Validators } from '@angular/forms';
import { UserVO } from '@app/model/bw/org/bocra/portal/user/user-vo';
import * as LicenseeActions from '@app/store/licensee/licensee.actions';
import * as LicenseSelectors from '@app/store/licensee/licensee.selectors';
import * as UserActions from '@app/store/user/user.actions';
import { SelectItem } from '@app/utils/select-item';
import { EditUserChangePasswordForm, EditUserComponent, EditUserDeleteForm, EditUserSaveForm, EditUserVarsForm } from '@app/view/user/edit-user.component';
import { environment } from '@env/environment';
import { select } from '@ngrx/store';
import { KeycloakService } from 'keycloak-angular';

@Component({
  selector: 'app-edit-user',
  templateUrl: './edit-user.component.html',
  styleUrls: ['./edit-user.component.scss'],
})

export class EditUserComponentImpl extends EditUserComponent {
  protected http: HttpClient;
  protected keycloakService: KeycloakService;

  constructor(private injector: Injector) {
    super(injector);
    this.http = this._injector.get(HttpClient);
    this.userLicensees$ = this.store.pipe(select(LicenseSelectors.selectLicensees));
    this.keycloakService = this.injector.get(KeycloakService);
  }

  override beforeOnInit(form: EditUserVarsForm): EditUserVarsForm {
    this.http.get<any[]>(environment.keycloakClientRoleUrl).subscribe((role) => {

      role.forEach((val) => {
        if(this.keycloakService.getUserRoles().includes(val.name)) {

          let item = new SelectItem();
          item.label = val['description'];
          item.value = val['name'];
  
          this.userRolesBackingList.push(item);
        }
      });
    });

    return form;
  }

  override afterOnInit() {
    this.user$.subscribe((user) => {
      this.setEditUserFormValue({ user: user });
    });

    console.log(this.keycloakService.loadUserProfile());
    this.keycloakService.loadUserProfile().then((val) => console.log(val));
  }

  override doNgAfterViewInit() {
    this.route.queryParams.subscribe((queryParams: any) => {
      if (queryParams?.userId) {
        this.store.dispatch(UserActions.findById({ userId: queryParams.userId, loading: true }));
      }
    });
  }

  override doNgOnDestroy() {
    this.store.dispatch(LicenseeActions.licenseeReset());
  }

  override beforeEditUserDelete(form: EditUserDeleteForm): void {
    if (this.editUserForm.valid && this.editUserForm.dirty) {
      if (form.user?.id) {
        form.user.updatedBy = this.keycloakService.getUsername();
        form.user.updatedDate = new Date();
      } else {
        form.user.createdBy = this.keycloakService.getUsername();
        form.user.createdDate = new Date();
      }
      if (form?.user?.id && confirm("Are you sure you want to delete the period?")) {
        this.store.dispatch(
          UserActions.remove({
            id: form?.user?.id,
            loading: false,
          })

        );
      }
    } else {

      this.store.dispatch(UserActions.userFailure({ messages: ['Please select something to delete'] }));
    }
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

      this.store.dispatch(
        UserActions.createUser({
          user: form.user,
          loading: true,
        })
      );
    }
    else {
      let messages: string[] = []
      if (!this.userUsernameControl.valid) {
        messages.push("Username has errors")
      }
      if (!this.userEmailControl.valid) {
        messages.push("Email has errors")
      }
      if (!this.userPasswordControl.valid) {
        messages.push("Password has errors")
      }
      if (!this.userFirstNameControl.valid) {
        messages.push("First name has errors")
      }
      if (!this.userLastNameControl.valid) {
        messages.push("Last Name has errors")
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

  override createUserForm(user: UserVO): FormGroup {
    if (!user) {
      user = new UserVO();
    }
    return this.formBuilder.group({
      userId: [user?.userId ? user.userId : null],
      username: [user?.username ? user.username : null, [Validators.required]],
      email: [user?.email ? user.email : null, [Validators.required, Validators.email]],
      password: [{ value: user?.password }, [Validators.required]],
      firstName: [user?.firstName ? user.firstName : null, [Validators.required]],
      lastName: [user?.lastName ? user.lastName : null, [Validators.required]],
      enabled: [user?.enabled ? user.enabled : null],
      licensee: this.createLicenseeVOGroup(user?.licensee),
      roles: user?.roles ? this.formBuilder.array(user?.roles ? user.roles : []) : new FormArray([]),
      client: [user?.client ? user.client : null],
    });
  }

  override getEditUserChangePasswordFormDialogConfig(data: any): any {
    console.log(data)
    return {
      data: {
        userId: this.userUserId,
        width: '800px',
      },
    };
  }

  override afterEditUserChangePassword(form: EditUserChangePasswordForm, dialogData: any): void {
    if (!this.userUserId) {
      this.userPasswordControl.patchValue(dialogData?.newPassword)
    } else {

    }
  }
}

