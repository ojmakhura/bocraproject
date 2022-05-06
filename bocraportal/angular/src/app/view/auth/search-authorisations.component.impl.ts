// Generated by andromda-angular cartridge (view\view.component.imp.ts.vsl) CAN EDIT!
import { Component, Injector } from '@angular/core';
import { SearchAuthorisationsComponent, SearchAuthorisationsVarsForm } from '@app/view/auth/search-authorisations.component';
import { SearchAuthorisationsSearchForm } from '@app/view/auth/search-authorisations.component';
import { Observable } from 'rxjs/internal/Observable';
import { AuthorisationVO } from '@app/model/bw/org/bocra/portal/auth/authorisation-vo';
import * as authorisationSelectors from '@app/store/auth/authorisation.selectors';
import * as authorisationActions from '@app/store/auth/authorisation.actions';
import { select } from '@ngrx/store';
import { KeycloakService } from 'keycloak-angular';
import { HttpClient } from '@angular/common/http';
import { environment } from '@env/environment';
import { SelectItem } from '@app/utils/select-item';

@Component({
  selector: 'app-search-authorisations',
  templateUrl: './search-authorisations.component.html',
  styleUrls: ['./search-authorisations.component.scss'],
})
export class SearchAuthorisationsComponentImpl extends SearchAuthorisationsComponent {
  protected keycloakService: KeycloakService;
  protected http: HttpClient;

  constructor(private injector: Injector) {
    super(injector);
    this.keycloakService = this._injector.get(KeycloakService);
    this.http = this._injector.get(HttpClient);
  }

  beforeOnInit() {
    this.store.dispatch(authorisationActions.authorisationReset());

    this.http.get<any[]>(environment.keycloakClientRoleUrl).subscribe((role) => {
      role.forEach((val) => {
        let item = new SelectItem();
        item.label = val['description'];
        item.value = val['name'];

        this.searchCriteriaRolesBackingList.push(item);
      });
    });

    this.http.get<any[]>(environment.keycloakRealmRoleUrl).subscribe((role) => {
      role.forEach((val) => {
        let item = new SelectItem();
        item.label = val['description'];
        item.value = val['name'];

        this.searchCriteriaRolesBackingList.push(item);
      });
    });
  }

  afterOnInit() {}

  doNgAfterViewInit() {
    // this.authorisations$.subscribe((authorisations) => {
    //   this.setAuthorisations(authorisations);
    // });
  }

  doNgOnDestroy(){}

  handleFormChanges(change: any) {}

  /**
   * This method may be overwritten
   */
  afterSetSearchAuthorisationsSearchForm(form: SearchAuthorisationsSearchForm): void {}

  /**
   * This method may be overwritten
   */
  beforeSearchAuthorisationsSearch(form: SearchAuthorisationsSearchForm): void {
    this.store.dispatch(authorisationActions.search({criteria: form.searchCriteria}));
    
  }

  /**
   * This method may be overwritten
   */
  afterSearchAuthorisationsSearch(form: SearchAuthorisationsSearchForm): void {}

  afterSetSearchAuthorisationsVarsForm(form: SearchAuthorisationsVarsForm): void {
  }
}
