// Generated by andromda-angular cartridge (view\view.component.imp.ts.vsl) CAN EDIT!
import { Component, Injector } from '@angular/core';
import {
  EditLicenseeComponent,
  EditLicenseeDeleteForm,
  EditLicenseeVarsForm,
} from '@app/view/licensee/edit-licensee.component';
import { EditLicenseeSaveForm } from '@app/view/licensee/edit-licensee.component';
import { EditLicenseeSearchForm } from '@app/view/licensee/edit-licensee.component';
import * as licenseeSelectors from '@app/store/licensee/licensee.selectors';
import * as licenseeActions from '@app/store/licensee/licensee.actions';
import { Observable } from 'rxjs';
import { LicenseeVO } from '@app/model/bw/org/bocra/portal/licensee/licensee-vo';
import { select } from '@ngrx/store';
import { KeycloakService } from 'keycloak-angular';

@Component({
  selector: 'app-edit-licensee',
  templateUrl: './edit-licensee.component.html',
  styleUrls: ['./edit-licensee.component.scss'],
})
export class EditLicenseeComponentImpl extends EditLicenseeComponent {
  protected keycloakService: KeycloakService;

  constructor(private injector: Injector) {
    super(injector);
    this.keycloakService = injector.get(KeycloakService);
  }

  beforeOnInit() {}

  afterOnInit() {
    if (this.useCaseScope.pageVariables['id']) {
      this.store.dispatch(licenseeActions.findById({ id: this.useCaseScope.pageVariables['id'] }));
    }

    this.licenseeVO$.subscribe((licensee) => {
      this.setEditLicenseeSaveForm({ licenseeVO: licensee } as EditLicenseeSaveForm);
    });
  }

  doNgAfterViewInit() {}

  handleFormChanges(change: any) {}

  doNgOnDestroy(){}

  /**
   * This method may be overwritten
   */
  afterSetEditLicenseeSaveForm(form: EditLicenseeSaveForm): void {}

  /**
   * This method may be overwritten
   */
  beforeEditLicenseeSave(form: EditLicenseeSaveForm): void {
    if(form.licenseeVO?.id) {

      form.licenseeVO.updatedBy = this.keycloakService.getUsername();
      form.licenseeVO.updatedDate = new Date();
    } else {
      form.licenseeVO.createdBy = this.keycloakService.getUsername();
      form.licenseeVO.createdDate = new Date();
    }
    
    this.store.dispatch(licenseeActions.save({ licenseeVO: form.licenseeVO }));
  }

  /**
   * This method may be overwritten
   */
  afterEditLicenseeSave(form: EditLicenseeSaveForm): void {}

  /**
   * This method may be overwritten
   */
  afterSetEditLicenseeSearchForm(form: EditLicenseeSearchForm): void {}

  /**
   * This method may be overwritten
   */
  beforeEditLicenseeSearch(form: EditLicenseeSearchForm): void {}

  /**
   * This method may be overwritten
   */
  afterEditLicenseeSearch(form: EditLicenseeSearchForm): void {}

  afterSetEditLicenseeDeleteForm(form: EditLicenseeDeleteForm): void {}

  beforeEditLicenseeDelete(form: EditLicenseeDeleteForm): void {}

  afterEditLicenseeDelete(form: EditLicenseeDeleteForm): void {}

  afterSetEditLicenseeVarsForm(form: EditLicenseeVarsForm): void {}
}
