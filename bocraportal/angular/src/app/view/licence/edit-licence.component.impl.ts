// Generated by andromda-angular cartridge (view\view.component.imp.ts.vsl) CAN EDIT!
import { Component, Injector } from '@angular/core';
import { EditLicenceComponent } from '@app/view/licence/edit-licence.component';
import { EditLicenceSaveForm } from '@app/view/licence/edit-licence.component';
import { EditLicenceSearchForm } from '@app/view/licence/edit-licence.component';
import { EditLicenceDeleteForm } from '@app/view/licence/edit-licence.component';
import { EditLicenceDocumentsForm } from '@app/view/licence/edit-licence.component';
import { EditLicenceVarsForm } from '@app/view/licence/edit-licence.component';
import { LicenceState } from '@app/store/licence/licence.state';
import * as LicenceSelectors from '@app/store/licence/licence.selectors';
import * as LicenceActions from '@app/store/licence/licence.actions';
import { MatCheckboxChange } from '@angular/material/checkbox';
import { DocumentVO } from '@app/model/bw/org/bocra/portal/document/document-vo';

@Component({
  selector: 'app-edit-licence',
  templateUrl: './edit-licence.component.html',
  styleUrls: ['./edit-licence.component.scss']
})
export class EditLicenceComponentImpl extends EditLicenceComponent {

    constructor(private injector: Injector) {
        super(injector);
    }

    beforeOnInit(){     
    }
	
    afterOnInit() {
    }

    doNgAfterViewInit() {
    }

    doNgOnDestroy(){}

    handleFormChanges(change: any) {
    }

    handleLicenceDocumentsAddDialog(): void {

    }

    handleLicenceDocumentsSearch(): void {
        
    }

    handleLicenceDocumentsSelected(event: MatCheckboxChange, data: DocumentVO): void {

    }
    
    /**
     * This method may be overwritten
     */
    afterSetEditLicenceVarsForm(form: EditLicenceVarsForm): void {

    }

    /**
     * This method may be overwritten
     */
    afterSetEditLicenceSaveForm(form: EditLicenceSaveForm): void {

    }

    /**
     * This method may be overwritten
     */
    beforeEditLicenceSave(form: EditLicenceSaveForm): void {

    }

    /**
     * This method may be overwritten
     */
    afterEditLicenceSave(form: EditLicenceSaveForm): void {

    }
    
    /**
     * This method may be overwritten
     */
    afterSetEditLicenceSearchForm(form: EditLicenceSearchForm): void {

    }

    /**
     * This method may be overwritten
     */
    beforeEditLicenceSearch(form: EditLicenceSearchForm): void {

    }

    /**
     * This method may be overwritten
     */
    afterEditLicenceSearch(form: EditLicenceSearchForm): void {

    }
    
    /**
     * This method may be overwritten
     */
    afterSetEditLicenceDeleteForm(form: EditLicenceDeleteForm): void {

    }

    /**
     * This method may be overwritten
     */
    beforeEditLicenceDelete(form: EditLicenceDeleteForm): void {

    }

    /**
     * This method may be overwritten
     */
    afterEditLicenceDelete(form: EditLicenceDeleteForm): void {

    }
    
    /**
     * This method may be overwritten
     */
    afterSetEditLicenceDocumentsForm(form: EditLicenceDocumentsForm): void {

    }

    /**
     * This method may be overwritten
     */
    beforeEditLicenceDocuments(form: EditLicenceDocumentsForm): void {

    }

    /**
     * This method may be overwritten
     */
    afterEditLicenceDocuments(form: EditLicenceDocumentsForm): void {

    }
    
}