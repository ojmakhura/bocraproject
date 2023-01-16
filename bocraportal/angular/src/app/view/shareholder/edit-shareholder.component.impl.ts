// Generated by andromda-angular cartridge (view\view.component.imp.ts.vsl) CAN EDIT!
import { Component, Injector } from '@angular/core';
import { EditShareholderAddDocumentForm, EditShareholderComponent, EditShareholderDeleteForm, EditShareholderSaveForm, EditShareholderVarsForm } from '@app/view/shareholder/edit-shareholder.component';
import { ShareholderState } from '@app/store/shareholder/shareholder.state';
import * as ShareholderSelectors from '@app/store/shareholder/shareholder.selectors';
import * as ShareholderActions from '@app/store/shareholder/shareholder.actions';
import * as DocumentActions from '@app/store/document/document.actions';
import * as DocumentSelectors from '@app/store/document/document.selectors';
import { MatRadioChange } from '@angular/material/radio';
import { MatCheckboxChange } from '@angular/material/checkbox';
import { KeycloakService } from 'keycloak-angular';
import { Observable } from 'rxjs';
import { select } from '@ngrx/store';
import * as ViewActions from '@app/store/view/view.actions';
import * as ViewSelectors from '@app/store/view/view.selectors';
import { DocumentVO } from '@app/model/bw/org/bocra/portal/document/document-vo';

@Component({
  selector: 'app-edit-shareholder',
  templateUrl: './edit-shareholder.component.html',
  styleUrls: ['./edit-shareholder.component.scss'],
})
export class EditShareholderComponentImpl extends EditShareholderComponent {
  protected keycloakService: KeycloakService;
  unauthorisedUrls$: Observable<string[]>;
  deleteUnrestricted: boolean = true;
  shareholderDocument$: Observable<DocumentVO>
  constructor(private injector: Injector) {
    super(injector);
    this.keycloakService = injector.get(KeycloakService);
    this.unauthorisedUrls$ = this.store.pipe(select(ViewSelectors.selectUnauthorisedUrls));
    this.shareholderDocuments$ = this.store.pipe(select(DocumentSelectors.selectDocuments));
    this.shareholderDocument$ = this.store.pipe(select(DocumentSelectors.selectDocument));
  }

  override beforeOnInit(form: EditShareholderVarsForm): EditShareholderVarsForm {
    return form;
  }

  doNgOnDestroy(): void {}

  override doNgAfterViewInit(): void {
    this.store.dispatch(
      ViewActions.loadViewAuthorisations({
        viewUrl: '/shareholder/edit-shareholder',
        roles: this.keycloakService.getUserRoles(),
        loading: true,
      })
    );

    this.route.queryParams.subscribe((queryParams: any) => {
      if(queryParams?.id){
        this.store.dispatch(
          ShareholderActions.findById({
            id: queryParams?.id,
            loading: false,
            loaderMessage: 'Find shareholder by id.',
          })
        );
      }
    });

    this.shareholder$.subscribe((shareholder) => {
      this.setEditShareholderFormValue({shareholder: shareholder});
    });

    this.unauthorisedUrls$.subscribe((restrictedItems) => {
      restrictedItems.forEach((item) => {
        if(item === '/shareholder/edit-shareholder/{button:delete}'){
          this.deleteUnrestricted = false;
        }
      });
    });

    this.shareholderDocument$.subscribe(document => {
      if(document?.id){
        this.addToShareholderDocuments(document);
      }
    });
  }

  override beforeEditShareholderSave(form: EditShareholderSaveForm): void {
    if(this.editShareholderForm.valid){
      if(form?.shareholder?.id){
        form.shareholder.updatedBy = this.keycloakService.getUsername();
        form.shareholder.updatedDate = new Date();
      }else {
        form.shareholder.createdBy = this.keycloakService.getUsername();
        form.shareholder.createdDate = new Date();
      }

      this.store.dispatch(
        ShareholderActions.save({
          shareholder: form.shareholder,
          loading: true,
          loaderMessage: 'Saving shareholder ...',
        })
      );
    }else {
      let messages: string[] = [];
      if(!this.shareholderControl.valid){
        messages.push('Shareholder has errors, Please fill in the required form fields.');
      }
      if(!this.shareholderIdControl.valid){
        messages.push('Shareholder Id is missing!');
      }
      if(!this.shareholderNameControl.valid){
        messages.push('Shareholder name is missing!');
      }
      if(!this.shareholderTypeControl.valid){
        messages.push('Shareholder type is missing!');
      }
      this.store.dispatch(ShareholderActions.shareholderFailure({ messages: messages}));
    }
  }

  override beforeEditShareholderDelete(form: EditShareholderDeleteForm): void {
    if(form?.shareholder?.id){
      if(!(form.shareholder?.shares.length > 0) || !(form?.shareholder?.documents.length > 0)){
        if(confirm('Are you sure you want to delete the shareholder')){
          this.store.dispatch(
            ShareholderActions.remove({
              id: form?.shareholder?.id,
              loading: false,
              loaderMessage: 'Removing shareholder',
            })
          );
          this.editShareholderFormReset();
        }
      }else {
        this.store.dispatch(ShareholderActions.shareholderFailure({messages: ['This shareholder can not be deleted it has attachments']}));
      }
    }else {
      this.store.dispatch(ShareholderActions.shareholderFailure({messages: ['Please select something to delete']}));
    }
  }

  override afterEditShareholderAddDocument(form: EditShareholderAddDocumentForm, dialogData: any): void {
    if (dialogData) {

      this.store.dispatch(
        DocumentActions.uploadFile({
          metadataTarget: dialogData.document.metadataTarget,
          metadataTargetId: this.shareholderId,
          documentTypeId: dialogData.document.documentType.id,
          file: dialogData.document.file,
          fileName: dialogData.document.documentName,
          loading: true,
          loaderMessage: 'Add shareholder document ...'
        })
      );
    }
        
  }
}
