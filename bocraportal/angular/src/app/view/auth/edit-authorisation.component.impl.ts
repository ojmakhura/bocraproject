// Generated by andromda-angular cartridge (view\view.component.imp.ts.vsl) CAN EDIT!
import { HttpClient } from '@angular/common/http';
import { Component, Injector } from '@angular/core';
import { EditAuthorisationSaveForm, EditAuthorisationVarsForm } from '@app/view/auth/edit-authorisation.component';
import { EditAuthorisationComponent, EditAuthorisationDeleteForm } from '@app/view/auth/edit-authorisation.component';
import { SelectItem } from '@app/utils/select-item';
import { environment } from '@env/environment';
import * as authorisationSelectors from '@app/store/auth/authorisation.selectors';
import * as authorisationActions from '@app/store/auth/authorisation.actions';
import { KeycloakService } from 'keycloak-angular';

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
  }

  override doNgOnDestroy(): void {
  }

<<<<<<< HEAD
  override afterEditAuthorisationDelete(form: EditAuthorisationDeleteForm): void {
    if(form?.dashboard?.id) {
      if(confirm("Are you sure to delete the new document configuration?")) {
        this.store.dispatch(AuthorisationActions.remove({id: form.authorisation.id, loading: true}));
        this.editAuthorisationFormReset();
      }
    }
  }

=======
>>>>>>> origin/ojm-dev
  beforeOnInit(form: EditAuthorisationVarsForm): EditAuthorisationVarsForm {
    this.http.get<any[]>(environment.keycloakClientRoleUrl).subscribe((role) => {
      role.forEach((val) => {
        let item = new SelectItem();
        item.label = val['description'];
        item.value = val['name'];

        this.authorisationRolesBackingList.push(item);
      });
    });

    return form;
  }

  override afterOnInit() {
    
  }

  override doNgAfterViewInit(): void {
    this.route.queryParams.subscribe((queryParams: any) => {
      if (queryParams?.id) {
        this.store.dispatch(
          authorisationActions.findById({
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
    if (form.authorisation?.id) {
      form.authorisation.updatedBy = this.keycloakService.getUsername();
      form.authorisation.updatedDate = new Date();
    } else {
      form.authorisation.createdBy = this.keycloakService.getUsername();
      form.authorisation.createdDate = new Date();
    }
    this.store.dispatch(
      authorisationActions.save({
        authorisation: form.authorisation,
        loading: true,
      })
    );
  }

  override beforeEditAuthorisationDelete(form: EditAuthorisationDeleteForm): void {
    this.store.dispatch(
      authorisationActions.remove({
        id: form?.authorisation?.id,
        loading: false,
      })
    );
  }
}
