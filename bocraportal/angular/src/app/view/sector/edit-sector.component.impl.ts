// Generated by andromda-angular cartridge (view\view.component.imp.ts.vsl) CAN EDIT!
import { Component, Injector } from '@angular/core';
import { EditSectorComponent } from '@app/view/sector/edit-sector.component';
import { EditSectorSaveForm } from '@app/view/sector/edit-sector.component';
import { EditSectorSearchForm } from '@app/view/sector/edit-sector.component';
import { EditSectorDeleteForm } from '@app/view/sector/edit-sector.component';
import { EditSectorVarsForm } from '@app/view/sector/edit-sector.component';
import { SectorState } from '@app/store/sector/sector.state';
import * as SectorSelectors from '@app/store/sector/sector.selectors';
import * as SectorActions from '@app/store/sector/sector.actions';
import * as LicenseeActions from '@app/store/licensee/licensee.actions';
import * as LicenseeSelectors from '@app/store/licensee/licensee.selectors';
import { MatRadioChange } from '@angular/material/radio';
import { MatCheckboxChange } from '@angular/material/checkbox';
import { LicenseeVO } from '@app/model/bw/org/bocra/portal/licensee/licensee-vo';
import { KeycloakService } from 'keycloak-angular';
import { select } from '@ngrx/store';

@Component({
  selector: 'app-edit-sector',
  templateUrl: './edit-sector.component.html',
  styleUrls: ['./edit-sector.component.scss'],
})
export class EditSectorComponentImpl extends EditSectorComponent {

  protected keycloakService: KeycloakService;

  constructor(private injector: Injector) {
    super(injector);
    this.keycloakService = injector.get(KeycloakService);
    this.sectorLicensees$ = this.store.pipe(select(LicenseeSelectors.selectLicensees))
  }

  beforeOnInit() {}

  afterOnInit() {
    if (this.useCaseScope.pageVariables['id']) {
      this.store.dispatch(SectorActions.findById({
        id: this.useCaseScope.pageVariables['id'],
        loading: true
      }));
    }

    this.sector$.subscribe((sector) => {
      this.setEditSectorSaveForm({ sector } as EditSectorSaveForm);
    });
  }

  doNgAfterViewInit() {}

  doNgOnDestroy() {}

  handleFormChanges(change: any) {}

  handleSectorLicenseesAddDialog(): void {}

  handleSectorLicenseesSearch(): void {

    let criteria: string = '';
    criteria = this.sectorLicenseesSearchField.value;
    this.store.dispatch(LicenseeActions.search({
      criteria: {uin: criteria, licenseeName: criteria},
      loading: true
    }));
  }

  handleSectorLicenseesSelected(event: MatCheckboxChange, data: LicenseeVO): void {}

  /**
   * This method may be overwritten
   */
  afterSetEditSectorVarsForm(form: EditSectorVarsForm): void {}

  /**
   * This method may be overwritten
   */
  afterSetEditSectorSaveForm(form: EditSectorSaveForm): void {}

  /**
   * This method may be overwritten
   */
  beforeEditSectorSave(form: EditSectorSaveForm): void {

    if(form.sector?.id) {

      form.sector.updatedBy = this.keycloakService.getUsername();
      form.sector.updatedDate = new Date();
    } else {
      form.sector.createdBy = this.keycloakService.getUsername();
      form.sector.createdDate = new Date();
    }
    
    this.store.dispatch(SectorActions.save({
      sector: form.sector,
      loading: true
    }));
  }

  /**
   * This method may be overwritten
   */
  afterEditSectorSave(form: EditSectorSaveForm): void {}

  /**
   * This method may be overwritten
   */
  afterSetEditSectorSearchForm(form: EditSectorSearchForm): void {}

  /**
   * This method may be overwritten
   */
  beforeEditSectorSearch(form: EditSectorSearchForm): void {}

  /**
   * This method may be overwritten
   */
  afterEditSectorSearch(form: EditSectorSearchForm): void {}

  /**
   * This method may be overwritten
   */
  afterSetEditSectorDeleteForm(form: EditSectorDeleteForm): void {}

  /**
   * This method may be overwritten
   */
  beforeEditSectorDelete(form: EditSectorDeleteForm): void {}

  /**
   * This method may be overwritten
   */
  afterEditSectorDelete(form: EditSectorDeleteForm): void {}
}
