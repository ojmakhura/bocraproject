// Generated by andromda-angular cartridge (view\view.component.imp.ts.vsl) CAN EDIT!
import { Component, Injector } from '@angular/core';
import { EditUserComponent } from '@app/view/user/edit-user.component';
import { EditUserSaveForm } from '@app/view/user/edit-user.component';
import { EditUserNewForm } from '@app/view/user/edit-user.component';
import { EditUserSearchForm } from '@app/view/user/edit-user.component';

@Component({
  selector: 'app-edit-user',
  templateUrl: './edit-user.component.html',
  styleUrls: ['./edit-user.component.scss']
})
export class EditUserComponentImpl extends EditUserComponent {

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
    afterSetEditUserSaveForm(form: EditUserSaveForm): void {

    }

    /**
     * This method may be overwritten
     */
    beforeEditUserSave(form: EditUserSaveForm): void {

    }

    /**
     * This method may be overwritten
     */
    afterEditUserSave(form: EditUserSaveForm): void {

    }
    
    /**
     * This method may be overwritten
     */
    afterSetEditUserNewForm(form: EditUserNewForm): void {

    }

    /**
     * This method may be overwritten
     */
    beforeEditUserNew(form: EditUserNewForm): void {

    }

    /**
     * This method may be overwritten
     */
    afterEditUserNew(form: EditUserNewForm): void {

    }
    
    /**
     * This method may be overwritten
     */
    afterSetEditUserSearchForm(form: EditUserSearchForm): void {

    }

    /**
     * This method may be overwritten
     */
    beforeEditUserSearch(form: EditUserSearchForm): void {

    }

    /**
     * This method may be overwritten
     */
    afterEditUserSearch(form: EditUserSearchForm): void {

    }
    
}