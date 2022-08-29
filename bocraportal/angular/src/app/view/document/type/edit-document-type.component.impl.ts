// Generated by andromda-angular cartridge (view\view.component.imp.ts.vsl) CAN EDIT!
import { Component, Injector } from '@angular/core';
import { EditDocumentTypeComponent } from '@app/view/document/type/edit-document-type.component';
import { EditDocumentTypeSaveForm } from '@app/view/document/type/edit-document-type.component';
import { EditDocumentTypeVarsForm } from '@app/view/document/type/edit-document-type.component';
import { DocumentTypeState } from '@app/store/document/type/document-type.state';
import * as DocumentTypeSelectors from '@app/store/document/type/document-type.selectors';
import * as DocumentTypeActions from '@app/store/document/type/document-type.actions';
import { KeycloakService } from 'keycloak-angular';

@Component({
  selector: 'app-edit-document-type',
  templateUrl: './edit-document-type.component.html',
  styleUrls: ['./edit-document-type.component.scss']
})
export class EditDocumentTypeComponentImpl extends EditDocumentTypeComponent {

    protected keycloakService: KeycloakService;
    deleteUnrestricted: boolean = true;
    
    constructor(private injector: Injector) {
        super(injector);
        this.keycloakService = injector.get(KeycloakService);
    }

    beforeOnInit(form: EditDocumentTypeVarsForm): EditDocumentTypeVarsForm {   
      return form;  
    }
	
    override afterOnInit() {
      
    }

    override doNgAfterViewInit(): void {
      this.route.queryParams.subscribe((queryParams: any) => {
        if (queryParams?.id) {
          this.store.dispatch(
            DocumentTypeActions.findById({
              id: queryParams?.id,
              loading: false,
            })
          );
        }
      });
  
      this.documentType$.subscribe((documentType) => {
        this.setEditDocumentTypeFormValue({documentType: documentType});
      });
    }

    override doNgOnDestroy(){}

    /**
     * This method may be overwritten
     */
    override beforeEditDocumentTypeSave(form: EditDocumentTypeSaveForm): void {
      if(form.documentType?.id) {

        form.documentType.updatedBy = this.keycloakService.getUsername();
        form.documentType.updatedDate = new Date();
      } else {
        form.documentType.createdBy = this.keycloakService.getUsername();
        form.documentType.createdDate = new Date();
      }
      
      this.store.dispatch(DocumentTypeActions.save({
        documentType: form.documentType,
        loading: false
      }));
    }   
}