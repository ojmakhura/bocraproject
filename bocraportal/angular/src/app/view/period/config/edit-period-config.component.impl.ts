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

@Component({
  selector: 'app-edit-period-config',
  templateUrl: './edit-period-config.component.html',
  styleUrls: ['./edit-period-config.component.scss']
})
export class EditPeriodConfigComponentImpl extends EditPeriodConfigComponent {

    protected keycloakService: KeycloakService;

    constructor(private injector: Injector) {
        super(injector);
        this.keycloakService = injector.get(KeycloakService);
    }

    beforeOnInit(){
      this.periodConfigFinalDayBackingList = days;
      this.periodConfigStartDayBackingList = days;
      this.periodConfigStartMonthBackingList = months;
    }
	
    afterOnInit() {
    }

    doNgAfterViewInit() {
      if (this.useCaseScope.pageVariables['id']) {
        this.store.dispatch(PeriodConfigActions.findById({ id: this.useCaseScope.pageVariables['id'] }));
      }
  
      this.periodConfig$.subscribe((periodConfig) => {
        this.setEditPeriodConfigSaveForm({ periodConfig: periodConfig } as EditPeriodConfigSaveForm);
      });
    }

    handleFormChanges(change: any) {
    }

    doNgOnDestroy(){}

    /**
     * This method may be overwritten
     */
    afterSetEditPeriodConfigVarsForm(form: EditPeriodConfigVarsForm): void {

    }

    /**
     * This method may be overwritten
     */
    afterSetEditPeriodConfigSaveForm(form: EditPeriodConfigSaveForm): void {
    }

    /**
     * This method may be overwritten
     */
    beforeEditPeriodConfigSave(form: EditPeriodConfigSaveForm): void {
      if(this.periodConfig.valid) {
        if(form.periodConfig.id) {
          form.periodConfig.updatedBy = this.keycloakService.getUsername();
          form.periodConfig.updatedDate = new Date();
        } else {
  
          form.periodConfig.createdBy = this.keycloakService.getUsername();
          form.periodConfig.createdDate = new Date();
        }
        this.store.dispatch(PeriodConfigActions.save({ periodConfig: form.periodConfig }));
      }

    }

    /**
     * This method may be overwritten
     */
    afterEditPeriodConfigSave(form: EditPeriodConfigSaveForm): void {

    }
    
    /**
     * This method may be overwritten
     */
    afterSetEditPeriodConfigSearchForm(form: EditPeriodConfigSearchForm): void {

    }

    /**
     * This method may be overwritten
     */
    beforeEditPeriodConfigSearch(form: EditPeriodConfigSearchForm): void {

    }

    /**
     * This method may be overwritten
     */
    afterEditPeriodConfigSearch(form: EditPeriodConfigSearchForm): void {

    }
    
    /**
     * This method may be overwritten
     */
    afterSetEditPeriodConfigDeleteForm(form: EditPeriodConfigDeleteForm): void {

    }

    /**
     * This method may be overwritten
     */
    beforeEditPeriodConfigDelete(form: EditPeriodConfigDeleteForm): void {

    }

    /**
     * This method may be overwritten
     */
    afterEditPeriodConfigDelete(form: EditPeriodConfigDeleteForm): void {

    }
    
}