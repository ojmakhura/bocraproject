// Generated by andromda-angular cartridge (view\view.component.imp.ts.vsl) CAN EDIT!
import { Component, Injector } from '@angular/core';
import { EditLicenseeComponent } from '@app/view/licensee/edit-licensee.component';
import { EditLicenseeSaveForm } from '@app/view/licensee/edit-licensee.component';
import { EditLicenseeNewForm } from '@app/view/licensee/edit-licensee.component';
import { EditLicenseeSearchForm } from '@app/view/licensee/edit-licensee.component';

@Component({
  selector: 'app-edit-licensee',
  templateUrl: './edit-licensee.component.html',
  styleUrls: ['./edit-licensee.component.scss']
})
export class EditLicenseeComponentImpl extends EditLicenseeComponent {

    constructor(private injector: Injector) {
        super(injector);
    }

    beforeOnInit(){
    }
	
    afterOnInit() {
    }

    doNgAfterViewInit() {
    }

    handleFormChanges(change: any) {
    }

    /**
     * This method may be overwritten
     */
    afterSetEditLicenseeSaveForm(form: EditLicenseeSaveForm): void {

    }

    /**
     * This method may be overwritten
     */
    beforeEditLicenseeSave(form: EditLicenseeSaveForm): void {

    }

    /**
     * This method may be overwritten
     */
    afterEditLicenseeSave(form: EditLicenseeSaveForm): void {

    }
    
    /**
     * This method may be overwritten
     */
    afterSetEditLicenseeNewForm(form: EditLicenseeNewForm): void {

    }

    /**
     * This method may be overwritten
     */
    beforeEditLicenseeNew(form: EditLicenseeNewForm): void {

    }

    /**
     * This method may be overwritten
     */
    afterEditLicenseeNew(form: EditLicenseeNewForm): void {

    }
    
    /**
     * This method may be overwritten
     */
    afterSetEditLicenseeSearchForm(form: EditLicenseeSearchForm): void {

    }

    /**
     * This method may be overwritten
     */
    beforeEditLicenseeSearch(form: EditLicenseeSearchForm): void {

    }

    /**
     * This method may be overwritten
     */
    afterEditLicenseeSearch(form: EditLicenseeSearchForm): void {

    }
    
}