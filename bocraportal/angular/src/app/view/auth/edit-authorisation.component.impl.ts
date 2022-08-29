// Generated by andromda-angular cartridge (view\view.component.imp.ts.vsl) CAN EDIT!
import { HttpClient } from '@angular/common/http';
import { Component, Injector } from '@angular/core';
import { EditAuthorisationSaveForm, EditAuthorisationVarsForm } from '@app/view/auth/edit-authorisation.component';
import { EditAuthorisationComponent, EditAuthorisationDeleteForm } from '@app/view/auth/edit-authorisation.component';
import { SelectItem } from '@app/utils/select-item';
import { environment } from '@env/environment';
import * as AuthorisationActions from '@app/store/auth/authorisation.actions';
import * as AccessPointSelectors from '@app/store/access/access-point.selectors';
import * as AccessPointActions from '@app/store/access/access-point.actions';

import { KeycloakService } from 'keycloak-angular';
import { select } from '@ngrx/store';
import { AccessPointCriteria } from '@app/model/bw/org/bocra/portal/access/access-point-criteria';
import { FormGroup } from '@angular/forms';
import { AccessPointVO } from '@app/model/bw/org/bocra/portal/access/access-point-vo';

@Component({
  selector: 'app-edit-authorisation',
  templateUrl: './edit-authorisation.component.html',
  styleUrls: ['./edit-authorisation.component.scss'],
})
export class EditAuthorisationComponentImpl extends EditAuthorisationComponent {
  
  protected http: HttpClient;
  protected keycloakService: KeycloakService;

  constructor(private injector: Injector) {
    super(injector);
    this.http = this._injector.get(HttpClient);
    this.keycloakService = this._injector.get(KeycloakService);
    this.authorisationAccessPoints$ = this.store.pipe(select(AccessPointSelectors.selectAccessPoints));
  }

  override doNgOnDestroy(): void {
  }

  beforeOnInit(form: EditAuthorisationVarsForm): EditAuthorisationVarsForm {
    this.http.get<any[]>(environment.keycloakClientRoleUrl).subscribe((role) => {

      role.forEach((val) => {
        if(this.keycloakService.getUserRoles().includes(val.name)) {

          let item = new SelectItem();
          item.label = val['description'];
          item.value = val['name'];
  
          this.authorisationRolesBackingList.push(item);
        }
      });
    });

    return form;
  }
  
  override handleFormChanges(change: any): void {
  }

  override afterOnInit() {
    
  }

  override createAccessPointVOGroup(value: AccessPointVO): FormGroup {
    return this.formBuilder.group({
        id: [value?.id],
        createdBy: [value?.createdBy],
        updatedBy: [value?.updatedBy],
        createdDate: [value?.createdDate],
        updatedDate: [value?.updatedDate],
        name: [value?.name],
        url: [value?.url],
        accessPointType: this.formBuilder.group({
            id: [value?.accessPointType?.id],
            code: [value?.accessPointType?.code],
            name: [value?.accessPointType?.name]
        })
    });
}

  override doNgAfterViewInit(): void {
    this.route.queryParams.subscribe((queryParams: any) => {
      if (queryParams?.id) {
        this.store.dispatch(
          AuthorisationActions.findById({
            id: queryParams?.id,
            loading: false,
          })
        );
      }
    });

    this.authorisation$.subscribe((authorisation) => {
      this.setEditAuthorisationFormValue({authorisation: authorisation});
    });
  }

  /**
   * This method may be overwritten
   */
  override beforeEditAuthorisationSave(form: EditAuthorisationSaveForm): void {
    if (this.editAuthorisationForm.valid && this.editAuthorisationForm.pristine) {
      if (form.authorisation?.id) {
        form.authorisation.updatedBy = this.keycloakService.getUsername();
        form.authorisation.updatedDate = new Date();
      } else {
        form.authorisation.createdBy = this.keycloakService.getUsername();
        form.authorisation.createdDate = new Date();
      }
      this.store.dispatch(
        AuthorisationActions.save({
          authorisation: form.authorisation,
          loading: true,
        })
      );
  
      }else{
        
        this.store.dispatch(AuthorisationActions.authorisationFailure({ messages:['Form has to be filled'] }));
      }
    }

    override beforeEditAuthorisationDelete(form: EditAuthorisationDeleteForm): void {
      if (this.editAuthorisationForm.valid && this.editAuthorisationForm.dirty){
        if (form.authorisation?.id) {
          form.authorisation.updatedBy = this.keycloakService.getUsername();
          form.authorisation.updatedDate = new Date();
        } else {
          form.authorisation.createdBy = this.keycloakService.getUsername();
          form.authorisation.createdDate = new Date();
        }
        if(form?.authorisation?.id && confirm("Are you sure you want to delete the period?")){
      this.store.dispatch(
        AuthorisationActions.remove({
          id: form?.authorisation?.id,
          loading: false,
        })
  
      );
        }
    }else{
          
      this.store.dispatch(AuthorisationActions.authorisationFailure({ messages:['Please select something to delete'] }));
    }
    }
    
  override authorisationAccessPointSearch(): void {
    let criteria: AccessPointCriteria = new AccessPointCriteria();
    criteria.name = this.authorisationAccessPointSearchField.value;
    criteria.url = this.authorisationAccessPointSearchField.value;
    this.store.dispatch(AccessPointActions.search({criteria: criteria, loading: true}));
  }
}
