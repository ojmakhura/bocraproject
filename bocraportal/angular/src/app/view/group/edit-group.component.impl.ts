// Generated by andromda-angular cartridge (view\view.component.imp.ts.vsl) CAN EDIT!
import { Component, Injector } from '@angular/core';
import { EditGroupComponent } from '@app/view/group/edit-group.component';
import { EditGroupSaveForm } from '@app/view/group/edit-group.component';
import { EditGroupNewForm } from '@app/view/group/edit-group.component';
import { EditGroupSearchForm } from '@app/view/group/edit-group.component';

@Component({
  selector: 'app-edit-group',
  templateUrl: './edit-group.component.html',
  styleUrls: ['./edit-group.component.scss']
})
export class EditGroupComponentImpl extends EditGroupComponent {

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
    afterSetEditGroupSaveForm(form: EditGroupSaveForm): void {

    }

    /**
     * This method may be overwritten
     */
    beforeEditGroupSave(form: EditGroupSaveForm): void {

    }

    /**
     * This method may be overwritten
     */
    afterEditGroupSave(form: EditGroupSaveForm): void {

    }
    
    /**
     * This method may be overwritten
     */
    afterSetEditGroupNewForm(form: EditGroupNewForm): void {

    }

    /**
     * This method may be overwritten
     */
    beforeEditGroupNew(form: EditGroupNewForm): void {

    }

    /**
     * This method may be overwritten
     */
    afterEditGroupNew(form: EditGroupNewForm): void {

    }
    
    /**
     * This method may be overwritten
     */
    afterSetEditGroupSearchForm(form: EditGroupSearchForm): void {

    }

    /**
     * This method may be overwritten
     */
    beforeEditGroupSearch(form: EditGroupSearchForm): void {

    }

    /**
     * This method may be overwritten
     */
    afterEditGroupSearch(form: EditGroupSearchForm): void {

    }
    
}