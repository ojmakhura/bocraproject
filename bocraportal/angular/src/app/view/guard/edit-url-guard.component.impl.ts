// Generated by andromda-angular cartridge (view\view.component.imp.ts.vsl) CAN EDIT!
import { HttpClient } from '@angular/common/http';
import { Component, Injector } from '@angular/core';
import { EditURLGuardSaveForm } from '@app/view/guard/edit-url-guard.component';
import { EditURLGuardNewForm } from '@app/view/guard/edit-url-guard.component';
import { EditURLGuardSearchForm } from '@app/view/guard/edit-url-guard.component';
import { EditURLGuardComponent, EditURLGuardDeleteForm } from '@app/view/guard/edit-url-guard.component';
import { SelectItem } from '@app/utils/select-item';
import { environment } from '@env/environment';
import { saveGuard } from '@app/store/guard/guard.action';
import * as guardSelectors from '@app/store/guard/guard.selector';
import * as guardActions from '@app/store/guard/guard.action';
import { KeycloakService } from 'keycloak-angular';
import { Observable } from 'rxjs';
import { UrlGuardVO } from '@app/model/bw/org/bocra/portal/guard/url-guard-vo';
import { select } from '@ngrx/store';

@Component({
  selector: 'app-edit-url-guard',
  templateUrl: './edit-url-guard.component.html',
  styleUrls: ['./edit-url-guard.component.scss']
})
export class EditURLGuardComponentImpl extends EditURLGuardComponent {

  protected http: HttpClient;
  protected keycloakService: KeycloakService;
  urlGuard$: Observable<UrlGuardVO>;
  urlGuards$: Observable<UrlGuardVO[]>;
  id$: Observable<number>;

  constructor(private injector: Injector) {
    super(injector);
    this.http = this._injector.get(HttpClient);
    this.keycloakService = this._injector.get(KeycloakService);
    this.urlGuard$ = this.store.pipe(select(guardSelectors.selectGuard))
    this.urlGuards$ = this.store.pipe(select(guardSelectors.selectGuards))
    this.id$ = this.store.pipe(select(guardSelectors.selectId))
  }

    beforeOnInit(){
      this.http.get<any[]>(environment.keycloakClientRoleUrl)
      .subscribe(role => {
        role.forEach(val => {
          
          let item = new SelectItem();
          item.label = val['description'];
          item.value = val['name'];
          
          this.urlGuardVORolesBackingList.push(item);
        })
      });

      this.http.get<any[]>(environment.keycloakRealmRoleUrl)
      .subscribe(role => {
        role.forEach(val => {
          
          let item = new SelectItem();
          item.label = val['description'];
          item.value = val['name'];
          
          this.urlGuardVORolesBackingList.push(item);
        })
      });
    }
	
    afterOnInit() {

      if(this.useCaseScope.pageVariables['id']) {
        this.store.dispatch(guardActions.findById({id: this.useCaseScope.pageVariables['id']}));
      }
  
      this.urlGuard$.subscribe(guard => {
        this.setEditURLGuardSaveForm({urlGuardVO: guard} as EditURLGuardSaveForm);
      });
    }

    doNgAfterViewInit() {
    }

    handleFormChanges(change: any) {
    }

    /**
     * This method may be overwritten
     */
    afterSetEditURLGuardSaveForm(form: EditURLGuardSaveForm): void {
      
    }

    /**
     * This method may be overwritten
     */
    beforeEditURLGuardSave(form: EditURLGuardSaveForm): void {
      this.store.dispatch(saveGuard({guard: form.urlGuardVO}));
    }

    /**
     * This method may be overwritten
     */
    afterEditURLGuardSave(form: EditURLGuardSaveForm): void {

    }
    
    /**
     * This method may be overwritten
     */
    afterSetEditURLGuardNewForm(form: EditURLGuardNewForm): void {

    }

    /**
     * This method may be overwritten
     */
    beforeEditURLGuardNew(form: EditURLGuardNewForm): void {
      this.store.dispatch(guardActions.reset());
    }

    /**
     * This method may be overwritten
     */
    afterEditURLGuardNew(form: EditURLGuardNewForm): void {

    }
    
    /**
     * This method may be overwritten
     */
    afterSetEditURLGuardSearchForm(form: EditURLGuardSearchForm): void {

    }

    /**
     * This method may be overwritten
     */
    beforeEditURLGuardSearch(form: EditURLGuardSearchForm): void {

    }

    /**
     * This method may be overwritten
     */
    afterEditURLGuardSearch(form: EditURLGuardSearchForm): void {

    }
    
  afterSetEditURLGuardDeleteForm(form: EditURLGuardDeleteForm): void {
    console.log('1');
    
  }

  beforeEditURLGuardDelete(form: EditURLGuardDeleteForm): void {
    console.log('2');
  }

  afterEditURLGuardDelete(form: EditURLGuardDeleteForm): void {
    console.log('3');
  }
}