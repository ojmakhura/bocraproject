// Generated by andromda-angular cartridge (view\view.component.imp.ts.vsl) CAN EDIT!
import { Component, Injector } from '@angular/core';
import * as DocumentTypeActions from '@app/store/document/type/document-type.actions';
import * as ViewActions from '@app/store/view/view.actions';
import * as ViewSelectors from '@app/store/view/view.selectors';
import { EditDocumentTypeComponent, EditDocumentTypeSaveForm, EditDocumentTypeVarsForm } from '@app/view/document/type/edit-document-type.component';
import { select } from '@ngrx/store';
import { KeycloakService } from 'keycloak-angular';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-edit-document-type',
  templateUrl: './edit-document-type.component.html',
  styleUrls: ['./edit-document-type.component.scss']
})
export class EditDocumentTypeComponentImpl extends EditDocumentTypeComponent {
  deleteUnrestricted: boolean = true;
  unauthorisedUrls$: Observable<string[]>;

  protected keycloakService: KeycloakService;

  constructor(private injector: Injector) {
    super(injector);
    this.keycloakService = injector.get(KeycloakService);
    this.unauthorisedUrls$ = this.store.pipe(select(ViewSelectors.selectUnauthorisedUrls));
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

    this.store.dispatch(
      ViewActions.loadViewAuthorisations({
        viewUrl: "/document/type/edit-document-type",
        roles: this.keycloakService.getUserRoles(),
        loading: true
      })
    );


    this.documentType$.subscribe((documentType) => {
      this.setEditDocumentTypeFormValue({ documentType: documentType });
    });

    this.unauthorisedUrls$.subscribe(restrictedItems => {
      restrictedItems.forEach(item => {
        if (item === '/document/edit-document-type/{button:delete}') {
          this.deleteUnrestricted = false;
        }
      });
    }

    );
  }




  override doNgOnDestroy() { }

  /**
   * This method may be overwritten
   */
  override beforeEditDocumentTypeSave(form: EditDocumentTypeSaveForm): void {
    if (form.documentType?.id) {

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