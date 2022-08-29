// Generated by andromda-angular cartridge (view\view.component.imp.ts.vsl) CAN EDIT!
import { Component, Injector } from '@angular/core';
import {
  EditLicenceTypeComponent,
  EditLicenceTypeDeleteForm,
  EditLicenceTypeVarsForm,
} from '@app/view/licence/type/edit-licence-type.component';
import { EditLicenceTypeSaveForm } from '@app/view/licence/type/edit-licence-type.component';
import * as licenceTypeActions from '@app/store/licence/type/licence-type.actions';
import { KeycloakService } from 'keycloak-angular';
import { select } from '@ngrx/store';
import * as LicenseSelectors from '@app/store/licensee/licensee.selectors';
import * as LicenseeActions from '@app/store/licensee/licensee.actions';
import * as FormActions from '@app/store/form/form.actions';
import { FormCriteria } from '@app/model/bw/org/bocra/portal/form/form-criteria';

@Component({
  selector: 'app-edit-licence-type',
  templateUrl: './edit-licence-type.component.html',
  styleUrls: ['./edit-licence-type.component.scss'],
})
export class EditLicenceTypeComponentImpl extends EditLicenceTypeComponent {
  protected keycloakService: KeycloakService;
  deleteUnrestricted: boolean = true;

  constructor(private injector: Injector) {
    super(injector);
    this.keycloakService = injector.get(KeycloakService);
  }

  override beforeOnInit(form: EditLicenceTypeVarsForm): EditLicenceTypeVarsForm {
    return form;
  }

  override afterOnInit() {
  }

  override doNgAfterViewInit(): void {
    this.route.queryParams.subscribe((queryParams: any) => {
      if (queryParams?.id) {
        this.store.dispatch(
          licenceTypeActions.findById({
            id: queryParams?.id,
            loading: true,
          })
        );
      }
    });

    this.licenceType$.subscribe((licenceType) => {
      this.setEditLicenceTypeFormValue({licenceType: licenceType});
    });
  }

  override doNgOnDestroy() {}

  /**
   * This method may be overwritten
   */
   override beforeEditLicenceTypeSave(form: EditLicenceTypeSaveForm): void {
    if (form.licenceType?.id) {
      form.licenceType.updatedBy = this.keycloakService.getUsername();
      form.licenceType.updatedDate = new Date();
    } else {
      form.licenceType.createdBy = this.keycloakService.getUsername();
      form.licenceType.createdDate = new Date();
    }

    this.store.dispatch(licenceTypeActions.save({
      licenceType: form.licenceType,
      loading: true
    }));
  }

  override beforeEditLicenceTypeDelete(form: EditLicenceTypeDeleteForm): void {
    this.store.dispatch(licenceTypeActions.remove({
      id: form?.licenceType?.id,
      loading: true
    }));
  }

  override licenceTypeFormsSearch(): void {

    let criteria: FormCriteria = new  FormCriteria();
    criteria.code = this.licenceTypeFormsSearchField.value;
    criteria.formName = this.licenceTypeFormsSearchField.value;
    this.store.dispatch(LicenseeActions.search({
      criteria,
      loading: true
    }));
  }
}
