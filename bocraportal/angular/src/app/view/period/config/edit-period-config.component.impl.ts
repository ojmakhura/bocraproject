// Generated by andromda-angular cartridge (view\view.component.imp.ts.vsl) CAN EDIT!
import { Component, Injector } from '@angular/core';
import { days, months } from '@app/period-items';
import { EditPeriodConfigComponent } from '@app/view/period/config/edit-period-config.component';
import { EditPeriodConfigSaveForm } from '@app/view/period/config/edit-period-config.component';
import { EditPeriodConfigSearchForm } from '@app/view/period/config/edit-period-config.component';
import { EditPeriodConfigDeleteForm } from '@app/view/period/config/edit-period-config.component';
import { EditPeriodConfigVarsForm } from '@app/view/period/config/edit-period-config.component';
import * as PeriodConfigActions from '@app/store/period/config/period-config.actions';
import * as PeriodConfigSelectors from '@app/store/period/config/period-config.selectors';
import { KeycloakService } from 'keycloak-angular';
import * as ViewActions from '@app/store/view/view.actions';
import * as ViewSelectors from '@app/store/view/view.selectors';
import { Observable } from 'rxjs';
import { select } from '@ngrx/store';

@Component({
  selector: 'app-edit-period-config',
  templateUrl: './edit-period-config.component.html',
  styleUrls: ['./edit-period-config.component.scss'],
})
export class EditPeriodConfigComponentImpl extends EditPeriodConfigComponent {
  protected keycloakService: KeycloakService;
  deleteUnrestricted: boolean = true;
  unauthorisedUrls$: Observable<string[]>;

  constructor(private injector: Injector) {
    super(injector);
    this.keycloakService = injector.get(KeycloakService);
    this.unauthorisedUrls$ = this.store.pipe(select(ViewSelectors.selectUnauthorisedUrls));
  }

  override beforeOnInit(form: EditPeriodConfigVarsForm) {
    this.periodConfigFinalDayBackingList = days;
    this.periodConfigStartDayBackingList = days;
    this.periodConfigStartMonthBackingList = months;

    return form;
  }

  override doNgAfterViewInit() {
    this.route.queryParams.subscribe((queryParams: any) => {
      if (queryParams?.id) {
        this.store.dispatch(
          PeriodConfigActions.findById({
            id: queryParams?.id,
            loading: true,
            loaderMessage: 'Loading period config by id ...',
          })
        );
      }
    });

    this.periodConfig$.subscribe((periodConfig) => {
      this.setEditPeriodConfigFormValue({ periodConfig: periodConfig });
    });
    this.store.dispatch(
      ViewActions.loadViewAuthorisations({
        viewUrl: '/period/config/edit-period-config',
        roles: this.keycloakService.getUserRoles(),
        loading: true,
      })
    );

    this.unauthorisedUrls$.subscribe((restrictedItems) => {
      restrictedItems.forEach((item) => {
        if (item === '/period/config/edit-period-config/{button:delete}') {
          this.deleteUnrestricted = false;
        }
      });
    });
  }

  override doNgOnDestroy() {}

  override beforeEditPeriodConfigDelete(form: EditPeriodConfigDeleteForm): void {
    if (form?.periodConfig?.id && confirm('Are you sure you want to delete the license type?')) {
      this.store.dispatch(
        PeriodConfigActions.remove({
          id: form.periodConfig.id,
          loading: true,
          loaderMessage: 'Removing period config ...',
        })
      );
      this.editPeriodConfigFormReset();
    } else {
      this.store.dispatch(PeriodConfigActions.periodConfigFailure({ messages: ['Please select something to delete'] }));
    }
  }

  /**
   * This method may be overwritten
   */
  override beforeEditPeriodConfigSave(form: EditPeriodConfigSaveForm): void {
    if (this.periodConfigControl.valid) {
      if (form.periodConfig.id) {
        form.periodConfig.updatedBy = this.keycloakService.getUsername();
        form.periodConfig.updatedDate = new Date();
      } else {
        form.periodConfig.createdBy = this.keycloakService.getUsername();
        form.periodConfig.createdDate = new Date();
      }
      this.store.dispatch(
        PeriodConfigActions.save({
          periodConfig: form.periodConfig,
          loading: true,
          loaderMessage: 'Saving period config ...',
        })
      );
    } else {
      let messages: string[] = [];
      if (!this.periodConfigControl.valid) {
        messages.push('Period Configuration has errors, Please fill in the required form fields');
      }
      if (!this.periodConfigPeriodConfigNameControl.valid) {
        messages.push('Period Config Name missing!');
      }
      if (!this.periodConfigRepeatPeriodControl.valid) {
        messages.push('Repeat Period missing!');
      }
      this.store.dispatch(PeriodConfigActions.periodConfigFailure({ messages: messages }));
    }
  }
}
