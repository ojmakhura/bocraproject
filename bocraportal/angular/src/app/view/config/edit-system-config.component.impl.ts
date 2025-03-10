// Generated by andromda-angular cartridge (view\view.component.imp.ts.vsl) CAN EDIT!
import { Component, Injector } from '@angular/core';
import * as SystemConfigActions from '@app/store/config/system-config.actions';
import * as ViewActions from '@app/store/view/view.actions';
import * as ViewSelectors from '@app/store/view/view.selectors';
import { EditSystemConfigComponent, EditSystemConfigSaveForm } from '@app/view/config/edit-system-config.component';
import { select } from '@ngrx/store';
import { KeycloakService } from 'keycloak-angular';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-edit-system-config',
  templateUrl: './edit-system-config.component.html',
  styleUrls: ['./edit-system-config.component.scss']
})
export class EditSystemConfigComponentImpl extends EditSystemConfigComponent {

  deleteUnrestricted: boolean = false;
  unauthorisedUrls$: Observable<string[]>;
  protected keycloakService: KeycloakService;

  constructor(private injector: Injector) {
    super(injector);
    this.unauthorisedUrls$ = this.store.pipe(select(ViewSelectors.selectUnauthorisedUrls));
    this.keycloakService = injector.get(KeycloakService);
  }

  doNgOnDestroy(): void {
  }

  override doNgAfterViewInit(): void {
    this.store.dispatch(
      ViewActions.loadViewAuthorisations({
        viewUrl: '/config/edit-config',
        roles: this.keycloakService.getUserRoles(),
        loading: true,
      })
    );

    this.route.queryParams.subscribe((queryParams: any) => {
      if (queryParams?.id) {
        this.store.dispatch(
          SystemConfigActions.findById({
            id: queryParams?.id,
            loading: false,
            loaderMessage: 'Loading system configuration by id ...',
          })
        );
      }
    });

    this.unauthorisedUrls$.subscribe((restrictedItems) => {
      restrictedItems.forEach((item) => {
        if (item === '/config/edit-config/{button:delete}') {
          this.deleteUnrestricted = false;
        }
      });
    });

    this.systemConfig$.subscribe((systemConfig) => {
      this.setEditSystemConfigFormValue({ systemConfig: systemConfig });
    });
  }

  override beforeEditSystemConfigSave(form: EditSystemConfigSaveForm): void {
    console.log(form?.systemConfig);
    if (this.editSystemConfigForm.valid) {
      
      this.store.dispatch(
        SystemConfigActions.save({
          systemConfig: form.systemConfig,
          loading: true,
          loaderMessage: 'Saving system config ...',
        })
      );
    } else {
      let messages: string[] = [];
      if (!this.systemConfigControl.valid) {
        messages.push('System config has errors, Please fill the required form fields');
      }
      if (!this.systemConfigNameControl.valid) {
        messages.push('System config name is missing!');
      }
      if (!this.systemConfigValueControl.valid) {
        messages.push('System config value is missing!');
      }
      this.store.dispatch(SystemConfigActions.systemConfigFailure({ messages: messages }));
    }
  }

  override beforeEditSystemConfigDelete(form: EditSystemConfigSaveForm) {
    if (form?.systemConfig?.id && confirm('Are you sure you want to delete the system config')) {
      this.store.dispatch(
        SystemConfigActions.remove({
          id: form?.systemConfig?.id,
          loading: false,
          loaderMessage: 'Removing system config ...',
        })
      );
      this.editSystemConfigFormReset();
    } else {
      this.store.dispatch(
        SystemConfigActions.systemConfigFailure({ messages: ['Please select something to delete'] })
      );
    }
  }
}