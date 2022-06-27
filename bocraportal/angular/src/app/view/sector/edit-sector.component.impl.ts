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
import { Observable } from 'rxjs';
import { LicenseeSectorVO } from '@app/model/bw/org/bocra/portal/licensee/licensee-sector-vo';

@Component({
  selector: 'app-edit-sector',
  templateUrl: './edit-sector.component.html',
  styleUrls: ['./edit-sector.component.scss'],
})
export class EditSectorComponentImpl extends EditSectorComponent {
  protected keycloakService: KeycloakService;
  protected licensees$: Observable<LicenseeVO[]>;
  protected licensee$: Observable<LicenseeVO>;
  protected sectorLicensee$: Observable<LicenseeSectorVO>;

  constructor(private injector: Injector) {
    super(injector);
    this.keycloakService = injector.get(KeycloakService);
    this.sectorLicensees$ = this.store.pipe(select(SectorSelectors.selectLicensees));
    this.licensees$ = this.store.pipe(select(LicenseeSelectors.selectLicensees));
    this.licensee$ = this.store.pipe(select(LicenseeSelectors.selectLicensee));
    this.sectorLicensee$ = this.store.pipe(select(SectorSelectors.selectLicensee));
  }

  override beforeOnInit(form: EditSectorVarsForm): EditSectorVarsForm {
    return form;
  }

  override doNgAfterViewInit() {
    this.route.queryParams.subscribe((queryParams: any) => {
      if (queryParams?.id) {
        this.store.dispatch(
          SectorActions.findById({
            id: queryParams?.id,
            loading: true,
          })
        );
      }
    });

    this.sector$.subscribe((sector) => {
      this.setEditSectorFormValue({ sector: sector });
    });

    this.licensee$.subscribe((licensee) => {
      console.log(licensee)
    });

    this.licensees$.subscribe((licensees) => {
      licensees.forEach((lc) => {
        this.store.dispatch(SectorActions.addLicenseeSuccess({ licensee: lc, messages: [''],success: true }));
      });
    });
  }

  override addToSectorLicensees(licensee: LicenseeSectorVO) {
    this.store.dispatch(SectorActions.addLicensee({sectorId: this.sectorId, licenseeId: licensee.id, loading: true}));
    console.log(licensee);
    console.log(this.sectorId);
    let tmp: LicenseeSectorVO = new LicenseeSectorVO();
    tmp.id = licensee.id;
    tmp.address = licensee.address;
    tmp.code = licensee.code;
    tmp.licenseeName = licensee.licenseeName;
    tmp.name = licensee.name;
    tmp.uin = licensee.uin;
    tmp.licenseeSectorId = this.sectorId;

    this.sectorLicenseesControl.push(this.createLicenseeSectorVOGroup(tmp));
  }

  override doNgOnDestroy() {}

  override sectorLicenseesAddDialog(): void {
    this.store.dispatch(SectorActions.setLicensees({ licensees: [] }));
  }

  override sectorLicenseesSearch(): void {
    let criteria: string = '';
    criteria = this.sectorLicenseesSearchField.value;
    this.store.dispatch(
      LicenseeActions.search({
        criteria: { uin: criteria, licenseeName: criteria },
        loading: true,
      })
    );
  }

  /**
   * This method may be overwritten
   */
  override beforeEditSectorSave(form: EditSectorSaveForm): void {
    if (this.editSectorForm.valid && this.editSectorForm.dirty) {
      if (form.sector?.id) {
        form.sector.updatedBy = this.keycloakService.getUsername();
        form.sector.updatedDate = new Date();
      } else {
        form.sector.createdBy = this.keycloakService.getUsername();
        form.sector.createdDate = new Date();
      }

      this.store.dispatch(
        SectorActions.save({
          sector: form.sector,
          loading: true,
        })
      );
    } else {
      this.store.dispatch(SectorActions.sectorFailure({ messages: ['Form has errors!'] }));
    }
  }
}
