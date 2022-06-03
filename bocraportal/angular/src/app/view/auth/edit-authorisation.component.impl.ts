// Generated by andromda-angular cartridge (view\view.component.imp.ts.vsl) CAN EDIT!
import { HttpClient } from '@angular/common/http';
import { Component, Injector } from '@angular/core';
import { EditAuthorisationSaveForm, EditAuthorisationVarsForm } from '@app/view/auth/edit-authorisation.component';
import { EditAuthorisationSearchForm } from '@app/view/auth/edit-authorisation.component';
import { EditAuthorisationComponent, EditAuthorisationDeleteForm } from '@app/view/auth/edit-authorisation.component';
import { SelectItem } from '@app/utils/select-item';
import { environment } from '@env/environment';
import * as authorisationSelectors from '@app/store/auth/authorisation.selectors';
import * as authorisationActions from '@app/store/auth/authorisation.actions';
import { KeycloakService } from 'keycloak-angular';
import { Observable } from 'rxjs';
import { AuthorisationVO } from '@app/model/bw/org/bocra/portal/auth/authorisation-vo';
import { select } from '@ngrx/store';
import jwt_decode from "jwt-decode";

@Component({
  selector: 'app-edit-authorisation',
  templateUrl: './edit-authorisation.component.html',
  styleUrls: ['./edit-authorisation.component.scss']
})
export class EditAuthorisationComponentImpl extends EditAuthorisationComponent {

  protected http: HttpClient;
  protected keycloakService: KeycloakService;

  constructor(private injector: Injector) {
    super(injector);
    this.http = this._injector.get(HttpClient);
    this.keycloakService = this._injector.get(KeycloakService);
  }

    beforeOnInit(){
      this.http.get<any[]>(environment.keycloakClientRoleUrl)
      .subscribe(role => {
        role.forEach(val => {
          let item = new SelectItem();
          item.label = val['description'];
          item.value = val['name'];
          
          this.authorisationRolesBackingList.push(item);
        })
      });
    }
	
    afterOnInit() {

      if(this.useCaseScope.pageVariables['id']) {
        this.store.dispatch(authorisationActions.findById({
          id: this.useCaseScope.pageVariables['id'],
          loading: false
        }));
      }
  
      this.authorisation$.subscribe(authorisation => {
        this.setEditAuthorisationSaveForm({authorisation: authorisation} as EditAuthorisationSaveForm);
      });
    }

    doNgAfterViewInit() {
    }

    handleFormChanges(change: any) {
    }

    /**
     * This method may be overwritten
     */
    afterSetEditAuthorisationSaveForm(form: EditAuthorisationSaveForm): void {
      
    }

    /**
     * This method may be overwritten
     */
    beforeEditAuthorisationSave(form: EditAuthorisationSaveForm): void {
      if(form.authorisation?.id) {

        form.authorisation.updatedBy = this.keycloakService.getUsername();
        form.authorisation.updatedDate = new Date();
      } else {
        form.authorisation.createdBy = this.keycloakService.getUsername();
        form.authorisation.createdDate = new Date();
      }
      this.store.dispatch(authorisationActions.save({
        authorisation: form.authorisation,
        loading: true
      }));
    }

    doNgOnDestroy(){}

    /**
     * This method may be overwritten
     */
    afterEditAuthorisationSave(form: EditAuthorisationSaveForm): void {

    }
    
    /**
     * This method may be overwritten
     */
    afterSetEditAuthorisationSearchForm(form: EditAuthorisationSearchForm): void {

    }

    /**
     * This method may be overwritten
     */
    beforeEditAuthorisationSearch(form: EditAuthorisationSearchForm): void {

    }

    /**
     * This method may be overwritten
     */
    afterEditAuthorisationSearch(form: EditAuthorisationSearchForm): void {

    }
    
  afterSetEditAuthorisationDeleteForm(form: EditAuthorisationDeleteForm): void {
    
  }

  beforeEditAuthorisationDelete(form: EditAuthorisationDeleteForm): void {
    this.store.dispatch(authorisationActions.remove({
      id: form?.authorisation?.id,
      loading: false
    }));
  }

  afterEditAuthorisationDelete(form: EditAuthorisationDeleteForm): void {
  }

  afterSetEditAuthorisationVarsForm(form: EditAuthorisationVarsForm): void {
  }
}
