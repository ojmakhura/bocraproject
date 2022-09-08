// Generated by andromda-angular cartridge (view\view.component.imp.ts.vsl) CAN EDIT!
import { Component, Injector } from '@angular/core';
import * as DocumentTypeActions from '@app/store/document/type/document-type.actions';
import * as DocumentTypeSelectors from '@app/store/document/type/document-type.selectors';
import * as ViewActions from '@app/store/view/view.actions';
import * as ViewSelectors from '@app/store/view/view.selectors';
import {
  EditDocumentTypeComponent,
  EditDocumentTypeDeleteForm,
  EditDocumentTypeSaveForm,
  EditDocumentTypeVarsForm,
} from '@app/view/document/type/edit-document-type.component';
import { select } from '@ngrx/store';
import { KeycloakService } from 'keycloak-angular';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-edit-document-type',
  templateUrl: './edit-document-type.component.html',
  styleUrls: ['./edit-document-type.component.scss'],
})
export class EditDocumentTypeComponentImpl extends EditDocumentTypeComponent {
  deleteUnrestricted: boolean = true;
  unauthorisedUrls$: Observable<string[]>;
  removed$: Observable<boolean>;

  protected keycloakService: KeycloakService;

  constructor(private injector: Injector) {
    super(injector);
    this.keycloakService = injector.get(KeycloakService);
    this.unauthorisedUrls$ = this.store.pipe(select(ViewSelectors.selectUnauthorisedUrls));
    this.removed$ = this.store.pipe(select(DocumentTypeSelectors.selectRemoved));
  }

  override beforeOnInit(form: EditDocumentTypeVarsForm): EditDocumentTypeVarsForm {
    return form;
  }

  override afterOnInit() {}

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
        viewUrl: '/document/type/edit-document-type',
        roles: this.keycloakService.getUserRoles(),
        loading: true,
      })
    );

    this.documentType$.subscribe((documentType) => {
      this.setEditDocumentTypeFormValue({ documentType: documentType });
    });

    this.unauthorisedUrls$.subscribe((restrictedItems) => {
      restrictedItems.forEach((item) => {
        if (item === '/document/edit-document-type/{button:delete}') {
          this.deleteUnrestricted = false;
        }
      });
    });

    this.removed$.subscribe(removed => {
      if(removed) {
        this.editDocumentTypeFormReset();
      }
    });
  }

  override doNgOnDestroy() {}

  /**
   * This method may be overwritten
   */
  override beforeEditDocumentTypeSave(form: EditDocumentTypeSaveForm): void {
    if (this.editDocumentTypeForm.valid) {
      if (form.documentType?.id) {
        form.documentType.updatedBy = this.keycloakService.getUsername();
        form.documentType.updatedDate = new Date();
      } else {
        form.documentType.createdBy = this.keycloakService.getUsername();
        form.documentType.createdDate = new Date();
      }

      this.store.dispatch(
        DocumentTypeActions.save({
          documentType: form.documentType,
          loading: true,
        })
      );
    } else {
      let messages: string[] = [];
      if (!this.documentTypeControl.valid) {
        messages.push('Sector has errors, Please fill in the required form fields.');
      }
      if (!this.documentTypeCodeControl.valid) {
        messages.push('Document Type Code is missing!');
      }
      if (!this.documentTypeNameControl.valid) {
        messages.push('Document Type Name is missing!');
      }
      this.store.dispatch(DocumentTypeActions.documentTypeFailure({ messages: messages }));
    }
  }

  override beforeEditDocumentTypeDelete(form: EditDocumentTypeDeleteForm): void {
    console.log(form);
    if (form?.documentType?.id && confirm(`Are you sure you want to delete the document type?`)) {
      this.store.dispatch(
        DocumentTypeActions.remove({
          id: form.documentType.id,
          loading: true,
        })
      );
    }
  }
}
