// Generated by andromda-angular cartridge (view\view.component.imp.ts.vsl) CAN EDIT!
import { HttpClient } from '@angular/common/http';
import { Component, Injector } from '@angular/core';
import { FormArray, FormGroup, Validators } from '@angular/forms';
import { UserVO } from '@app/model/bw/org/bocra/portal/user/user-vo';
import * as LicenseeActions from '@app/store/licensee/licensee.actions';
import * as LicenseSelectors from '@app/store/licensee/licensee.selectors';
import * as UserActions from '@app/store/user/user.actions';
import { SelectItem } from '@app/utils/select-item';
import {
  EditUserChangePasswordForm,
  EditUserComponent,
  EditUserDeleteForm,
  EditUserSaveForm,
  EditUserVarsForm,
} from '@app/view/user/edit-user.component';
import { environment } from '@env/environment';
import { select } from '@ngrx/store';
import { KeycloakService } from 'keycloak-angular';
import { Observable, of } from 'rxjs';
import * as ViewActions from '@app/store/view/view.actions';
import * as ViewSelectors from '@app/store/view/view.selectors';

@Component({
  selector: 'app-edit-user',
  templateUrl: './edit-user.component.html',
  styleUrls: ['./edit-user.component.scss'],
})
export class EditUserComponentImpl extends EditUserComponent {
  protected http: HttpClient;
  protected keycloakService: KeycloakService;
  searchUnrestricted: boolean = true;
  deleteUnrestricted: boolean = true;
  unauthorisedUrls$: Observable<string[]>;
  starPass: string = '';
  assignedLicensee$: Observable<boolean> = of(false);

  constructor(private injector: Injector) {
    super(injector);
    this.http = this._injector.get(HttpClient);
    this.userLicensees$ = this.store.pipe(select(LicenseSelectors.selectLicensees));
    this.keycloakService = this.injector.get(KeycloakService);
    this.unauthorisedUrls$ = this.store.pipe(select(ViewSelectors.selectUnauthorisedUrls));
  }

  override beforeOnInit(form: EditUserVarsForm): EditUserVarsForm {
    this.http.get<any[]>(`${environment.keycloakRealmUrl}/clients`).subscribe((clients) => {
      let client = clients.filter((client) => client.clientId === environment.keycloak.clientId)[0];
      this.keycloakService.loadUserProfile().then((profile) => {
        this.http
          .get<any[]>(
            `${environment.keycloakRealmUrl}/users/${profile.id}/role-mappings/clients/${client.id}/composite`
          )
          .subscribe((roles) => {
            roles
              .sort((a, b) => a.name.localeCompare(b.name))
              .forEach((role) => {
                if (this.keycloakService.getUserRoles().includes(role.name)) {
                  let item = new SelectItem();
                  item.label = role['description'];
                  item.value = role['name'];

                  this.userRolesBackingList.push(item);
                }
              });
          });

        this.http
          .get<any[]>(
            `${environment.keycloakRealmUrl}/users/${profile.id}/role-mappings/realm/composite`
          )
          .subscribe((roles) => {
            roles
              .sort((a, b) => a.name.localeCompare(b.name))
              .forEach((role: any) => {
                if (this.keycloakService.getUserRoles().includes(role.name) && !role.description?.includes("${")) {
                  let item = new SelectItem();
                  item.label = role['description'];
                  item.value = role['name'];

                  this.userRolesBackingList.push(item);
                }
              });
          });
      });
    });

    return form;
  }

  override afterOnInit() {
    this.keycloakService.loadUserProfile().then((profile) => {
      let user: UserVO = new UserVO();
      if (profile['attributes'] && profile['attributes']?.licenseeId) {
        user.licensee.id = profile['attributes']?.licenseeId[0];
        user.licensee.licenseeName = profile['attributes']?.licenseeName[0];

        if (user.licensee.id && user.licensee.id > 0) {
          this.assignedLicensee$ = of(true);
        }
      }

      this.setEditUserFormValue({ user: user });
    });
  }

  override doNgAfterViewInit() {
    this.route.queryParams.subscribe((queryParams: any) => {
      if (queryParams?.userId) {
        this.store.dispatch(
          UserActions.findById({ userId: queryParams.userId, loading: true, loaderMessage: 'Loading user bu id ...' })
        );
      }
    });

    this.store.dispatch(
      ViewActions.loadViewAuthorisations({
        viewUrl: '/user/edit-user',
        roles: this.keycloakService.getUserRoles(),
        loading: true,
      })
    );

    this.user$.subscribe((user) => {
      this.setEditUserFormValue({ user: user });
    });

    this.unauthorisedUrls$.subscribe((restrictedItems) => {
      restrictedItems.forEach((item) => {
        if (item === '/user/edit-user/{button:search}') {
          this.searchUnrestricted = false;
        } else if (item === '/user/edit-user/{button:delete}') {
          this.deleteUnrestricted = false;
        }
      });
    });
  }

  override doNgOnDestroy() {
    this.store.dispatch(LicenseeActions.licenseeReset());
  }

  override beforeEditUserDelete(form: EditUserDeleteForm): void {
    if (form?.user?.userId && confirm('Are you sure you want to delete the user?')) {
      this.store.dispatch(
        UserActions.remove({
          id: form?.user?.userId,
          loading: false,
          loaderMessage: 'Removing user ...',
        })
      );
      this.editUserFormReset();
    } else {
      this.store.dispatch(UserActions.userFailure({ messages: ['Please select something to delete'] }));
    }
  }

  /**
   * This method may be overwritten
   */
  override beforeEditUserSave(form: EditUserSaveForm): void {
    if (this.editUserForm.valid) {
      this.store.dispatch(
        UserActions.createUser({
          user: form.user,
          loading: true,
          loaderMessage: 'Creating a user ...',
        })
      );
    } else {
      let messages: string[] = [];

      if (!this.userUserId) {
        if (!this.userPasswordControl.valid) {
          messages.push('Password is missing!');
        }

        if (!this.userUsernameControl.valid) {
          messages.push('Username is missing!');
        }
      }

      if (!this.userEmailControl.valid) {
        messages.push('Email is missing!');
      }

      if (!this.userFirstNameControl.valid) {
        messages.push('First name is missing!');
      }

      if (!this.userLastNameControl.valid) {
        messages.push('Last Name is missing!');
      }

      if (messages.length > 0) {
        if (!this.userControl.valid) {
          messages = ['User has errors. Please fill in the required form fields.', ...messages];
        }
        this.store.dispatch(UserActions.userFailure({ messages: messages }));
      } else {
        if (this.userUserId) {
          form.user.updatedBy = this.keycloakService.getUsername();
          form.user.updatedDate = new Date();

          this.store.dispatch(
            UserActions.createUser({
              user: form.user,
              loading: true,
              loaderMessage: 'Saving a user ...',
            })
          );
        }
      }
    }
  }

  override userLicenseeSearch(): void {
    let criteria: string = '';
    criteria = this.userLicenseeSearchField.value;
    this.store.dispatch(
      LicenseeActions.search({
        criteria: { licenseeName: criteria },
        loading: true,
        loaderMessage: 'Searching users ...',
      })
    );
  }

  override createUserForm(user: UserVO): FormGroup {
    if (!user) {
      user = new UserVO();
    }
    return this.formBuilder.group({
      userId: [user?.userId ? user.userId : null],
      username: [{ value: user?.username, disabled: user?.userId }, [Validators.required]],
      email: [user?.email ? user.email : null, [Validators.required, Validators.email]],
      password: [{ value: user?.password, disabled: false }, [Validators.required]],
      firstName: [user?.firstName ? user.firstName : null, [Validators.required]],
      lastName: [user?.lastName ? user.lastName : null, [Validators.required]],
      enabled: [user?.enabled ? user.enabled : null],
      licensee: {
        id: user?.licensee?.id,
        licenseeName: user?.licensee?.licenseeName,
      },
      roles: user?.roles ? this.formBuilder.array(user?.roles ? user.roles : []) : new FormArray([]),
    });
  }

  override getEditUserChangePasswordFormDialogConfig(data: any): any {
    return {
      data: {
        userId: this.userUserId,
        width: '800px',
      },
    };
  }

  override afterEditUserChangePassword(form: EditUserChangePasswordForm, dialogData: any): void {
    if (!this.userUserId) {
      this.starPass = '*'.repeat(dialogData?.newPassword?.length);
      this.userPasswordControl.patchValue(dialogData?.newPassword);
    } else {
    }
  }

  scroll(el: HTMLElement) {
    el.scrollIntoView();
  }
}
