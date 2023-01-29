// Generated by andromda-angular cartridge (view\view.component.imp.ts.vsl) CAN EDIT!
import { Component, Injector } from '@angular/core';
import { LicenceVO } from '@app/model/bw/org/bocra/portal/licence/licence-vo';
import { DocumentRestController } from '@app/service/bw/org/bocra/portal/document/document-rest-controller';
import * as DocumentActions from '@app/store/document/document.actions';
import * as DocumentSelectors from '@app/store/document/document.selectors';
import * as LicenceSelectors from '@app/store/licence/licence.selectors';
import * as ViewActions from '@app/store/view/view.actions';
import * as ViewSelectors from '@app/store/view/view.selectors';
import { EditDocumentComponent, EditDocumentSaveForm, EditDocumentVarsForm } from '@app/view/document/edit-document.component';
import { select } from '@ngrx/store';
import { KeycloakService } from 'keycloak-angular';
import { Observable } from 'rxjs';
import { saveAs } from 'file-saver';

@Component({
  selector: 'app-edit-document',
  templateUrl: './edit-document.component.html',
  styleUrls: ['./edit-document.component.scss'],
})
export class EditDocumentComponentImpl extends EditDocumentComponent {
  protected keycloakService: KeycloakService;
  licences$: Observable<LicenceVO[]>;
  deleteUnrestricted: boolean = false;
  unauthorisedUrls$: Observable<string[]>;
  file$: Observable<Blob>;

  constructor(private injector: Injector) {
    super(injector);
    this.keycloakService = injector.get(KeycloakService);
    this.licences$ = this.store.select(LicenceSelectors.selectLicences);
    this.unauthorisedUrls$ = this.store.pipe(select(ViewSelectors.selectUnauthorisedUrls));
    this.file$ = this.store.pipe(select(DocumentSelectors.selectFile));
  }

  override beforeOnInit(form: EditDocumentVarsForm): EditDocumentVarsForm {
    return form;
  }

  override afterOnInit() {
    
  }

  override doNgAfterViewInit(): void {
    this.route.queryParams.subscribe((queryParams: any) => {
      if (queryParams?.documentId) {
        this.store.dispatch(
          DocumentActions.findByDocumentId({
            documentId: queryParams?.documentId,
            loading: true,
            loaderMessage: 'Loading by documentId ...'
          })
        );
      }
    });

    this.store.dispatch(
      ViewActions.loadViewAuthorisations({
        viewUrl: "/document/edit-document",
        roles: this.keycloakService.getUserRoles(),
        loading: true
      })
    );

    this.document$.subscribe((document) => {
      this.setEditDocumentFormValue({document: document});
    });

    this.unauthorisedUrls$.subscribe(restrictedItems => {
      restrictedItems.forEach(item => {
        if(item === '/document/edit-document/{button:delete}') {
          this.deleteUnrestricted = false;
        }
      });
    });
  }

  override doNgOnDestroy() {}

  /**
   * This method may be overwritten
   */
  override beforeEditDocumentSave(form: EditDocumentSaveForm): void {
    if (form.document?.id) {
      form.document.updatedBy = this.keycloakService.getUsername();
      form.document.updatedDate = new Date();
    } else {
      form.document.createdBy = this.keycloakService.getUsername();
      form.document.createdDate = new Date();
    }

    this.store.dispatch(
      DocumentActions.save({
        document: form.document,
        loading: false,
        loaderMessage: 'Saving document ...'
      })
    );
  }

  downloadFile() {

    this.store.dispatch(
      DocumentActions.downloadFile({
        documentId: this.documentDocumentId,
        loading: false,
        loaderMessage: 'Downloading document ...'
      })
    );

    this.file$.subscribe(file => {
      if(file) {
        let blob:any = new Blob([file], { type: this.documentContentType });
        const url = window.URL.createObjectURL(blob);
        saveAs(blob, this.documentDocumentName);
      }
    });
  }
}
