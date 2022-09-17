// Generated by andromda-angular cartridge (view\view.component.imp.ts.vsl) CAN EDIT!
import { Component, Injector } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { FormCriteria } from '@app/model/bw/org/bocra/portal/form/form-criteria';
import { LicenseeSectorVO } from '@app/model/bw/org/bocra/portal/licensee/sector/licensee-sector-vo';
import { SectorVO } from '@app/model/bw/org/bocra/portal/sector/sector-vo';
import * as FormActions from '@app/store/form/form.actions';
import * as LicenseeActions from '@app/store/licensee/licensee.actions';
import * as SectorActions from '@app/store/sector/sector.actions';
import * as ViewActions from '@app/store/view/view.actions';
import * as ViewSelectors from '@app/store/view/view.selectors';
import * as LicenseeSectorSelectors from '@app/store/licensee/sector/licensee-sector.selectors';
import * as LicenseeSectorActions from '@app/store/licensee/sector/licensee-sector.actions';
import * as SectorFormActions from '@app/store/sector/form/sector-form.actions';
import * as SectorFormSelectors from '@app/store/sector/form/sector-form.selectors';
import {
  EditSectorComponent,
  EditSectorDeleteForm,
  EditSectorSaveForm,
  EditSectorVarsForm,
} from '@app/view/sector/edit-sector.component';
import { select } from '@ngrx/store';
import { KeycloakService } from 'keycloak-angular';
import { Observable } from 'rxjs';
import { SectorFormVO } from '@app/model/bw/org/bocra/portal/sector/form/sector-form-vo';

@Component({
  selector: 'app-edit-sector',
  templateUrl: './edit-sector.component.html',
  styleUrls: ['./edit-sector.component.scss'],
})
export class EditSectorComponentImpl extends EditSectorComponent {
  protected keycloakService: KeycloakService;
  unauthorisedUrls$: Observable<string[]>;
  deleteUnrestricted: boolean = true;
  licenseeRemoved$: Observable<boolean>;

  constructor(private injector: Injector) {
    super(injector);
    this.keycloakService = injector.get(KeycloakService);
    this.unauthorisedUrls$ = this.store.pipe(select(ViewSelectors.selectUnauthorisedUrls));
    this.licenseeRemoved$ = this.store.pipe(select(LicenseeSectorSelectors.selectRemoved));
  }

  override beforeOnInit(form: EditSectorVarsForm): EditSectorVarsForm {
    form.sector = new SectorVO();
    form.sector.themeColour = '#000000';
    return form;
  }

  override doNgAfterViewInit() {
    this.route.queryParams.subscribe((queryParams: any) => {
      if (queryParams?.id) {
        this.store.dispatch(
          SectorActions.findById({
            id: queryParams?.id,
            loading: true,
            loaderMessage: 'Loading sector by id ...'
          })
        );
      }
    });

    this.sector$.subscribe((sector) => {
      this.setEditSectorFormValue({ sector: sector });
    });

    this.sectorLicensee$.subscribe((ls) => {
      if (ls?.id) this.addToSectorLicensees(ls);
    });

    this.sectorForm$.subscribe((sf) => {
      if(sf?.id) {
        this.addToSectorForms(sf);
      }
    });

    this.store.dispatch(
      ViewActions.loadViewAuthorisations({
        viewUrl: '/sector/edit-sector',
        roles: this.keycloakService.getUserRoles(),
        loading: true,
      })
    );

    this.unauthorisedUrls$.subscribe((restrictedItems) => {
      restrictedItems.forEach((item) => {
        if (item === '/sector/edit-sector/{button:delete}') {
          this.deleteUnrestricted = false;
        }
      });
    });
  }

  override doNgOnDestroy() {}

  override sectorLicenseesAddDialog(): void {}

  override sectorLicenseesSearch(): void {
    this.store.dispatch(SectorActions.setLicensees({ licensees: [] }));
    let criteria: string = '';
    criteria = this.sectorLicenseesSearchField.value;
    this.store.dispatch(
      LicenseeActions.search({
        criteria: { uin: criteria, licenseeName: criteria },
        loading: true,
        loaderMessage: 'Searching licensees ...'
      })
    );
  }

  override beforeEditSectorDelete(form: EditSectorDeleteForm): void {
    if (form?.sector?.id && confirm('Are you sure you want to delete the sector?')) {
      this.store.dispatch(
        SectorActions.remove({
          id: form?.sector?.id,
          loading: false,
          loaderMessage: ''
        })
      );
      this.editSectorFormReset();
    } else {
      this.store.dispatch(SectorActions.sectorFailure({ messages: ['Please select something to delete'] }));
    }
  }

  /**
   * This method may be overwritten
   */
  override beforeEditSectorSave(form: EditSectorSaveForm): void {
    if (this.editSectorForm.valid) {
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
          loaderMessage: 'Saving sector ...'
        })
      );
    } else {
      let messages: string[] = [];
      if (!this.sectorControl.valid) {
        messages.push('Sector has errors, Please fill in the required form fields.');
      }
      if (!this.sectorNameControl.valid) {
        messages.push('Sector name is missing');
      }
      if (!this.sectorCodeControl.valid) {
        messages.push('Sector code is missing');
      }
      this.store.dispatch(SectorActions.sectorFailure({ messages: messages }));
    }
  }

  override createLicenseeSectorVOGroup(value: LicenseeSectorVO): FormGroup {
    return this.formBuilder.group({
      id: [value?.id],
      licensee: {
        id: value?.licensee?.id,
        uin: value?.licensee?.uin,
        licenseeName: value?.licensee?.licenseeName,
      },
    });
  }

  override sectorFormsSearch() {
    let criteria: FormCriteria = new FormCriteria();

    criteria.code = this.sectorFormsSearchField.value;
    criteria.formName = this.sectorFormsSearchField.value;

    this.store.dispatch(
      FormActions.searchForms({
        criteria: criteria,
        loading: true,
        loaderMessage: 'Searching forms ...'
      })
    );
  }

  override deleteFromSectorLicensees(index: number) {
    if (confirm('Are you sure you want to remove the licensee from the sector?')) {
      let lic: LicenseeSectorVO = this.sectorLicensees[index];
      this.store.dispatch(
        LicenseeSectorActions.remove({
          id: lic.id,
          loading: true,
          loaderMessage: 'Removing licensee from sector ...'
        })
      );
    }

    this.licenseeRemoved$.subscribe((removed) => {
      if (removed) {
        this.handleDeleteFromSectorLicensees(this.sectorLicensees[index]);
        this.sectorLicenseesControl.removeAt(index);
      }
    });
  }
}
