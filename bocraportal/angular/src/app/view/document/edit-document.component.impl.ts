// Generated by andromda-angular cartridge (view\view.component.imp.ts.vsl) CAN EDIT!
import { Component, Injector } from '@angular/core';
import { EditDocumentComponent } from '@app/view/document/edit-document.component';
import { EditDocumentSaveForm } from '@app/view/document/edit-document.component';
import { EditDocumentSearchForm } from '@app/view/document/edit-document.component';
import { EditDocumentDeleteForm } from '@app/view/document/edit-document.component';
import { EditDocumentVarsForm } from '@app/view/document/edit-document.component';
import { DocumentState } from '@app/store/document/document.state';
import * as DocumentSelectors from '@app/store/document/document.selectors';
import * as DocumentActions from '@app/store/document/document.actions';
import { KeycloakService } from 'keycloak-angular';

@Component({
  selector: 'app-edit-document',
  templateUrl: './edit-document.component.html',
  styleUrls: ['./edit-document.component.scss']
})
export class EditDocumentComponentImpl extends EditDocumentComponent {

    protected keycloakService: KeycloakService;

    constructor(private injector: Injector) {
        super(injector);
        this.keycloakService = injector.get(KeycloakService);
    }

    beforeOnInit(){     
    }
	
    afterOnInit() {
      if (this.useCaseScope.pageVariables['id']) {
        this.store.dispatch(DocumentActions.findById({
          id: this.useCaseScope.pageVariables['id'],
          loading: true
        }));
      }
  
      this.document$.subscribe((document) => {
        this.setEditDocumentSaveForm({ document: document } as EditDocumentSaveForm);
      });
    }

    doNgAfterViewInit() {
    }

    doNgOnDestroy(){}

    handleFormChanges(change: any) {
    }

    /**
     * This method may be overwritten
     */
    afterSetEditDocumentVarsForm(form: EditDocumentVarsForm): void {

    }

    /**
     * This method may be overwritten
     */
    afterSetEditDocumentSaveForm(form: EditDocumentSaveForm): void {

    }

    /**
     * This method may be overwritten
     */
    beforeEditDocumentSave(form: EditDocumentSaveForm): void {
      if(form.document?.id) {

        form.document.updatedBy = this.keycloakService.getUsername();
        form.document.updatedDate = new Date();
      } else {
        form.document.createdBy = this.keycloakService.getUsername();
        form.document.createdDate = new Date();
      }
      
      this.store.dispatch(DocumentActions.save({
        document: form.document,
        loading: false
      }));
    }

    /**
     * This method may be overwritten
     */
    afterEditDocumentSave(form: EditDocumentSaveForm): void {

    }
    
    /**
     * This method may be overwritten
     */
    afterSetEditDocumentSearchForm(form: EditDocumentSearchForm): void {

    }

    /**
     * This method may be overwritten
     */
    beforeEditDocumentSearch(form: EditDocumentSearchForm): void {

    }

    /**
     * This method may be overwritten
     */
    afterEditDocumentSearch(form: EditDocumentSearchForm): void {

    }
    
    /**
     * This method may be overwritten
     */
    afterSetEditDocumentDeleteForm(form: EditDocumentDeleteForm): void {

    }

    /**
     * This method may be overwritten
     */
    beforeEditDocumentDelete(form: EditDocumentDeleteForm): void {

    }

    /**
     * This method may be overwritten
     */
    afterEditDocumentDelete(form: EditDocumentDeleteForm): void {

    }
    
}