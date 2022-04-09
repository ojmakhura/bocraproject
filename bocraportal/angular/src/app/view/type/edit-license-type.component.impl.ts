// Generated by andromda-angular cartridge (view\view.component.imp.ts.vsl) CAN EDIT!
import { Component, Injector } from '@angular/core';
import { EditLicenseTypeComponent, EditLicenseTypeDeleteForm, EditLicenseTypeVarsForm } from '@app/view/type/edit-license-type.component';
import { EditLicenseTypeSaveForm } from '@app/view/type/edit-license-type.component';
import { EditLicenseTypeSearchForm } from '@app/view/type/edit-license-type.component';
import { LicenseTypeVO } from '@app/model/bw/org/bocra/portal/type/license-type-vo';
import * as licenseTypeSelectors from '@app/store/type/license-type.selector';
import * as licenseTypeActions from '@app/store/type/license-type.action';
import { Observable } from 'rxjs';
import { select } from '@ngrx/store';

@Component({
  selector: 'app-edit-license-type',
  templateUrl: './edit-license-type.component.html',
  styleUrls: ['./edit-license-type.component.scss'],
})
export class EditLicenseTypeComponentImpl extends EditLicenseTypeComponent {
  licenseeType$: Observable<LicenseTypeVO>;
  licenseeTypes$: Observable<LicenseTypeVO[]>;
  id$: Observable<number>;

  constructor(private injector: Injector) {
    super(injector);
    this.licenseeType$ = this.store.pipe(select(licenseTypeSelectors.selectLicenseType));
    this.licenseeTypes$ = this.store.pipe(select(licenseTypeSelectors.selectLicenseTypes));
    this.id$ = this.store.pipe(select(licenseTypeSelectors.selectId));
  }

  beforeOnInit() {}

  afterOnInit() {
    if (this.useCaseScope.pageVariables['id']) {
      this.store.dispatch(licenseTypeActions.findById({ id: this.useCaseScope.pageVariables['id'] }));
    }

    this.licenseeType$.subscribe((licenseType) => {
      this.setEditLicenseTypeSaveForm({ licenseTypeVO: licenseType } as EditLicenseTypeSaveForm);
    });
  }

  doNgAfterViewInit() {}

  handleFormChanges(change: any) {}

  /**
   * This method may be overwritten
   */
  afterSetEditLicenseTypeSaveForm(form: EditLicenseTypeSaveForm): void {}

  /**
   * This method may be overwritten
   */
  beforeEditLicenseTypeSave(form: EditLicenseTypeSaveForm): void {
    this.store.dispatch(licenseTypeActions.saveLicenseType({ licenseType: form.licenseTypeVO }));
  }

  /**
   * This method may be overwritten
   */
  afterEditLicenseTypeSave(form: EditLicenseTypeSaveForm): void {}

  /**
   * This method may be overwritten
   */
  afterSetEditLicenseTypeSearchForm(form: EditLicenseTypeSearchForm): void {}

  /**
   * This method may be overwritten
   */
  beforeEditLicenseTypeSearch(form: EditLicenseTypeSearchForm): void {}

  /**
   * This method may be overwritten
   */
  afterEditLicenseTypeSearch(form: EditLicenseTypeSearchForm): void {}

  afterSetEditLicenseTypeDeleteForm(form: EditLicenseTypeDeleteForm): void {
  }

  beforeEditLicenseTypeDelete(form: EditLicenseTypeDeleteForm): void {
  }

  afterEditLicenseTypeDelete(form: EditLicenseTypeDeleteForm): void {
  }

  afterSetEditLicenseTypeVarsForm(form: EditLicenseTypeVarsForm): void {
  }

}
