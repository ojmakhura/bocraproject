// Generated by andromda-angular cartridge (view\view.component.imp.ts.vsl) CAN EDIT!
import { Component, Injector } from '@angular/core';
import { EditLicenceComponent } from '@app/view/licence/edit-licence.component';
import { EditLicenceSaveForm } from '@app/view/licence/edit-licence.component';
import { EditLicenceVarsForm } from '@app/view/licence/edit-licence.component';
import { LicenceState } from '@app/store/licence/licence.state';
import * as LicenceSelectors from '@app/store/licence/licence.selectors';
import * as LicenceActions from '@app/store/licence/licence.actions';
import { KeycloakService } from 'keycloak-angular';

@Component({
  selector: 'app-edit-licence',
  templateUrl: './edit-licence.component.html',
  styleUrls: ['./edit-licence.component.scss'],
})
export class EditLicenceComponentImpl extends EditLicenceComponent {
  protected keycloakService: KeycloakService;
  constructor(private injector: Injector) {
    super(injector);
    this.keycloakService = injector.get(KeycloakService);
  }

  override beforeOnInit(form: EditLicenceVarsForm): EditLicenceVarsForm {
    return form;
  }

  override doNgAfterViewInit(): void {
    this.route.queryParams.subscribe((queryParams: any) => {
      if (queryParams?.id) {
        this.store.dispatch(
          LicenceActions.findById({
            id: queryParams?.id,
            loading: true,
          })
        );
      }
    });

    this.licence$.subscribe((licence) => {
      this.setEditLicenceFormValue({licence: licence});
    });
  }

  override doNgOnDestroy() {}

  /**
   * This method may be overwritten
   */
  override beforeEditLicenceSave(form: EditLicenceSaveForm): void {
    if (form.licence?.id) {
      form.licence.updatedBy = this.keycloakService.getUsername();
      form.licence.updatedDate = new Date();
    } else {
      form.licence.createdBy = this.keycloakService.getUsername();
      form.licence.createdDate = new Date();
    }

    this.store.dispatch(
      LicenceActions.save({
        licence: form.licence,
        loading: true,
      })
    );
  }
}
