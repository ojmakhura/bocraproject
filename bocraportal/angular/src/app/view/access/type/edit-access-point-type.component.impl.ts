// Generated by andromda-angular cartridge (view\view.component.imp.ts.vsl) CAN EDIT!
import { Component, Injector } from '@angular/core';
import { EditAccessPointTypeComponent } from '@app/view/access/type/edit-access-point-type.component';
import { EditAccessPointTypeSaveForm } from '@app/view/access/type/edit-access-point-type.component';
import { EditAccessPointTypeDeleteForm } from '@app/view/access/type/edit-access-point-type.component';
import { EditAccessPointTypeSearchForm } from '@app/view/access/type/edit-access-point-type.component';
import { EditAccessPointTypeVarsForm } from '@app/view/access/type/edit-access-point-type.component';
import { AccessPointTypeState } from '@app/store/access/type/access-point-type.state';
import * as AccessPointTypeSelectors from '@app/store/access/type/access-point-type.selectors';
import * as AccessPointTypeActions from '@app/store/access/type/access-point-type.actions';
import { MatRadioChange } from '@angular/material/radio';
import { MatCheckboxChange } from '@angular/material/checkbox';
import { KeycloakService } from 'keycloak-angular';
import * as ViewActions from '@app/store/view/view.actions';
import * as ViewSelectors from '@app/store/view/view.selectors';
import { Observable } from 'rxjs';
import { select } from '@ngrx/store';

@Component({
  selector: 'app-edit-access-point-type',
  templateUrl: './edit-access-point-type.component.html',
  styleUrls: ['./edit-access-point-type.component.scss'],
})
export class EditAccessPointTypeComponentImpl extends EditAccessPointTypeComponent {
  protected keycloakService: KeycloakService;
  unauthorisedUrls$: Observable<string[]>;
  deleteUnrestricted: boolean = true;

  constructor(private injector: Injector) {
    super(injector);
    this.keycloakService = injector.get(KeycloakService);
    this.unauthorisedUrls$ = this.store.pipe(select(ViewSelectors.selectUnauthorisedUrls));
  }

  override beforeOnInit(form: EditAccessPointTypeVarsForm): EditAccessPointTypeVarsForm {
    return form;
  }

  override doNgOnDestroy() {}

  override doNgAfterViewInit(): void {

    this.store.dispatch(
      ViewActions.loadViewAuthorisations({
        viewUrl: "/access/type/edit-access-point-type",
        roles: this.keycloakService.getUserRoles(),
        loading: true
      })
    );

    this.route.queryParams.subscribe((queryParams: any) => {
      if (queryParams?.id) {
        this.store.dispatch(
          AccessPointTypeActions.findById({
            id: queryParams?.id,
            loading: false,
          })
        );
      }
    });

    this.accessPointType$.subscribe((accessPointType) => {
      this.setEditAccessPointTypeFormValue({ accessPointType: accessPointType });
    });

    this.unauthorisedUrls$.subscribe(restrictedItems => {
      restrictedItems.forEach(item => {
        if(item === '/access/type/edit-access-point-type/{button:delete}') {
          this.deleteUnrestricted = false;
        }
      });
    });
  }

  override beforeEditAccessPointTypeSave(form: EditAccessPointTypeSaveForm): void {

    this.store.dispatch(
      AccessPointTypeActions.save({
        accessPointType: form.accessPointType,
        loading: true,
      })
    );
  }

  override beforeEditAccessPointTypeDelete(form: EditAccessPointTypeDeleteForm): void {
    this.store.dispatch(
      AccessPointTypeActions.remove({
        id: form?.accessPointType?.id,
        loading: false,
      })
    );
  }
}
