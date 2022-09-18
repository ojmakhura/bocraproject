// Generated by andromda-angular cartridge (view\view.component.imp.ts.vsl) CAN EDIT!
import { Component, Injector } from '@angular/core';
import { LicenceVO } from '@app/model/bw/org/bocra/portal/licence/licence-vo';
import * as DocumentActions from '@app/store/document/document.actions';
import * as LicenceSelectors from '@app/store/licence/licence.selectors';
import * as ViewActions from '@app/store/view/view.actions';
import * as ViewSelectors from '@app/store/view/view.selectors';
import { EditDocumentComponent, EditDocumentSaveForm, EditDocumentVarsForm } from '@app/view/document/edit-document.component';
import { select } from '@ngrx/store';
import { KeycloakService } from 'keycloak-angular';
import { Observable } from 'rxjs';

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
  

  constructor(private injector: Injector) {
    super(injector);
    this.keycloakService = injector.get(KeycloakService);
    this.licences$ = this.store.select(LicenceSelectors.selectLicences);
    this.unauthorisedUrls$ = this.store.pipe(select(ViewSelectors.selectUnauthorisedUrls));
  }

  override beforeOnInit(form: EditDocumentVarsForm): EditDocumentVarsForm {
    return form;
  }

  override afterOnInit() {
    
  }

  override doNgAfterViewInit(): void {
    this.route.queryParams.subscribe((queryParams: any) => {
      if (queryParams?.id) {
        this.store.dispatch(
          DocumentActions.findById({
            id: queryParams?.id,
            loading: true,
            loaderMessage: 'Loading document by id ...'
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
}
