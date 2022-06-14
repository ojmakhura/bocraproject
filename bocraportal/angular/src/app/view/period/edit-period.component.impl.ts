// Generated by andromda-angular cartridge (view\view.component.imp.ts.vsl) CAN EDIT!
import { Component, Injector } from '@angular/core';
import { MatRadioChange } from '@angular/material/radio';
import { EditPeriodComponent } from '@app/view/period/edit-period.component';
import { EditPeriodSaveForm } from '@app/view/period/edit-period.component';
import { EditPeriodSearchForm } from '@app/view/period/edit-period.component';
import { EditPeriodDeleteForm } from '@app/view/period/edit-period.component';
import { EditPeriodVarsForm } from '@app/view/period/edit-period.component';
import { PeriodVO } from '@app/model/bw/org/bocra/portal/period/period-vo';
import { KeycloakService } from 'keycloak-angular';
import * as PeriodActions from '@app/store/period/period.actions';

@Component({
  selector: 'app-edit-period',
  templateUrl: './edit-period.component.html',
  styleUrls: ['./edit-period.component.scss']
})
export class EditPeriodComponentImpl extends EditPeriodComponent {

    protected keycloakService: KeycloakService;

    constructor(private injector: Injector) {
        super(injector);
        this.keycloakService = injector.get(KeycloakService);
    }

    beforeOnInit(){
    }
	
    afterOnInit() {
    }

    doNgOnDestroy(){}

    doNgAfterViewInit() {
      if (this.useCaseScope.pageVariables['id']) {
        this.store.dispatch(PeriodActions.findById({
          id: this.useCaseScope.pageVariables['id'],
          loading: true
        }));
      }
  
      this.period$.subscribe((period) => {
        this.setEditPeriodSaveForm({ period: period } as EditPeriodSaveForm);
      });
    }

    handleFormChanges(change: any) {
    }

    /**
     * This method may be overwritten
     */
    afterSetEditPeriodVarsForm(form: EditPeriodVarsForm): void {

    }

    /**
     * This method may be overwritten
     */
    afterSetEditPeriodSaveForm(form: EditPeriodSaveForm): void {

    }

    /**
     * This method may be overwritten
     */
    beforeEditPeriodSave(form: EditPeriodSaveForm): void {
      if(this.periodControl.valid) {
        if(form.period.id) {
          form.period.updatedBy = this.keycloakService.getUsername();
          form.period.updatedDate = new Date();
        } else {
  
          form.period.createdBy = this.keycloakService.getUsername();
          form.period.createdDate = new Date();
        }
        this.store.dispatch(PeriodActions.save({
          period: form.period,
          loading: true
        }));
      }

    }

    /**
     * This method may be overwritten
     */
    afterEditPeriodSave(form: EditPeriodSaveForm): void {

    }
    
    /**
     * This method may be overwritten
     */
    afterSetEditPeriodSearchForm(form: EditPeriodSearchForm): void {

    }

    /**
     * This method may be overwritten
     */
    beforeEditPeriodSearch(form: EditPeriodSearchForm): void {

    }

    /**
     * This method may be overwritten
     */
    afterEditPeriodSearch(form: EditPeriodSearchForm): void {

    }
    
    /**
     * This method may be overwritten
     */
    afterSetEditPeriodDeleteForm(form: EditPeriodDeleteForm): void {

    }

    /**
     * This method may be overwritten
     */
    beforeEditPeriodDelete(form: EditPeriodDeleteForm): void {

    }

    /**
     * This method may be overwritten
     */
    afterEditPeriodDelete(form: EditPeriodDeleteForm): void {

    }
        
    handlePeriodNextAddDialog(): void {
    }
    
    handlePeriodNextSearch(): void {
    }

    handlePeriodNextSelected(event: MatRadioChange, data: PeriodVO): void {
    }

    handlePeriodPreviousAddDialog(): void {
    }

    handlePeriodPreviousSearch(): void {
    }

    handlePeriodPreviousSelected(event: MatRadioChange, data: PeriodVO): void {
    }
}