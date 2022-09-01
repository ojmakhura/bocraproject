// Generated by andromda-angular cartridge (view\view.component.imp.ts.vsl) CAN EDIT!
import { Component, Injector } from '@angular/core';
import { CreateOrEditMenuSectionComponent } from '@app/view/menu/create-or-edit-menu-section.component';
import { CreateOrEditMenuSectionSaveForm } from '@app/view/menu/create-or-edit-menu-section.component';
import { CreateOrEditMenuSectionVarsForm } from '@app/view/menu/create-or-edit-menu-section.component';
import * as AuthorisationSelectors from '@app/store/auth/authorisation.selectors';
import * as AuthorisationActions from '@app/store/auth/authorisation.actions';
import * as MenuSectionActions from '@app/store/menu/menu-section.actions';
import { select } from '@ngrx/store';
import { KeycloakService } from 'keycloak-angular';
import { AuthorisationCriteria } from '@app/model/bw/org/bocra/portal/auth/authorisation-criteria';
import { AuthorisationVO } from '@app/model/bw/org/bocra/portal/auth/authorisation-vo';
import { FormGroup } from '@angular/forms';
import { Observable } from 'rxjs';
import * as ViewActions from '@app/store/view/view.actions';
import * as ViewSelectors from '@app/store/view/view.selectors';

@Component({
  selector: 'app-create-or-edit-menu-section',
  templateUrl: './create-or-edit-menu-section.component.html',
  styleUrls: ['./create-or-edit-menu-section.component.scss'],
})
export class CreateOrEditMenuSectionComponentImpl extends CreateOrEditMenuSectionComponent {
  protected keycloakService: KeycloakService;
  unauthorisedUrls$: Observable<string[]>;
  deleteUnrestricted: boolean = true;

  constructor(private injector: Injector) {
    super(injector);
    this.keycloakService = injector.get(KeycloakService);
    this.menuSectionAuthorisations$ = this.store.pipe(select(AuthorisationSelectors.selectAuthorisations));
    this.unauthorisedUrls$ = this.store.pipe(select(ViewSelectors.selectUnauthorisedUrls));
  }

  override beforeOnInit(form: CreateOrEditMenuSectionVarsForm): CreateOrEditMenuSectionVarsForm {
    return form;
  }

  doNgOnDestroy(): void {}

  override doNgAfterViewInit(): void {
    this.route.queryParams.subscribe((queryParams: any) => {
      console.log(queryParams);
      if (queryParams?.id) {
        this.store.dispatch(
          MenuSectionActions.findById({
            id: queryParams?.id,
            loading: true,
          })
        );
      }
    });

    this.store.dispatch(
      ViewActions.loadViewAuthorisations({
        viewUrl: "/menu/create-or-edit-menu-section",
        roles: this.keycloakService.getUserRoles(),
        loading: true
      })
    );

  

    this.unauthorisedUrls$.subscribe(restrictedItems => {
      restrictedItems.forEach(item => {
        if(item === '/menu/create-or-edit-menu-section/{button:delete}') {
          this.deleteUnrestricted = false;
        }
      });
    });

    this.menuSection$.subscribe((menuSection) => {
        console.log(menuSection)
      this.setCreateOrEditMenuSectionFormValue({ menuSection: menuSection });
    });
  }

  override beforeCreateOrEditMenuSectionSave(form: CreateOrEditMenuSectionSaveForm): void {
    if (form.menuSection?.id) {
      form.menuSection.updatedBy = this.keycloakService.getUsername();
      form.menuSection.updatedDate = new Date();
    } else {
      form.menuSection.createdBy = this.keycloakService.getUsername();
      form.menuSection.createdDate = new Date();
    }

    this.store.dispatch(
      MenuSectionActions.save({
        menuSection: form.menuSection,
        loading: true,
      })
    );
  }

  // override beforeCreateOrEditMenuSectionDelete(form: CreateOrEditMenuSectionDeleteForm): void {
  //   if (form?.menuSection?.id && confirm("Are you sure you want to delete the period?")) {
  //     this.store.dispatch(MenuSectionActions.remove({
  //       id: form?.menuSection?.id,
  //       loading: false,
  //     }));
  //     this.createOrEditMenuSectionFormReset();
  //   } else {
  //     this.store.dispatch(MenuSectionActions.menuSectionFailure({ messages: ['Please select something to delete'] }));
  //   }
  // }


  override menuSectionAuthorisationsSearch(): void {
    let criteria: AuthorisationCriteria = new AuthorisationCriteria();
    criteria.accessPointName = this.menuSectionAuthorisationsSearchField.value;
    criteria.accessPointUrl = this.menuSectionAuthorisationsSearchField.value;
    this.store.dispatch(
      AuthorisationActions.search({
        criteria: criteria,
        loading: true,
      })
    );
  }

  override addToMenuSectionAuthorisations(data: AuthorisationVO) {
    this.store.dispatch(
        AuthorisationActions.assignMenuSection({
            authorisationId: data.id,
            menuSectionId: this.menuSectionId,
            loading: true
        })
    );
    this.menuSectionAuthorisationsControl.push(this.createAuthorisationVOGroup(data));
  }

  override createAuthorisationVOGroup(value: AuthorisationVO): FormGroup {
      return this.formBuilder.group({
          id: [value?.id],
          createdBy: [value?.createdBy],
          updatedBy: [value?.updatedBy],
          createdDate: [value?.createdDate],
          updatedDate: [value?.updatedDate],
          accessPoint: {
            id: value?.accessPoint?.id,
            name: value?.accessPoint?.name,
            url: value?.accessPoint?.url,
          }
      });
  }
}
