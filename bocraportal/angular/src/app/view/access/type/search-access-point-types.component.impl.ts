// Generated by andromda-angular cartridge (view\view.component.imp.ts.vsl) CAN EDIT!
import { Component, Injector } from '@angular/core';
import { SearchAccessPointTypesComponent } from '@app/view/access/type/search-access-point-types.component';
import { SearchAccessPointTypesSearchForm } from '@app/view/access/type/search-access-point-types.component';
import { SearchAccessPointTypesVarsForm } from '@app/view/access/type/search-access-point-types.component';
import { AccessPointTypeState } from '@app/store/access/type/access-point-type.state';
import * as AccessPointTypeSelectors from '@app/store/access/type/access-point-type.selectors';
import * as AccessPointTypeActions from '@app/store/access/type/access-point-type.actions';
import { MatRadioChange } from '@angular/material/radio';
import { MatCheckboxChange } from '@angular/material/checkbox';

@Component({
  selector: 'app-search-access-point-types',
  templateUrl: './search-access-point-types.component.html',
  styleUrls: ['./search-access-point-types.component.scss']
})
export class SearchAccessPointTypesComponentImpl extends SearchAccessPointTypesComponent {

    constructor(private injector: Injector) {
        super(injector);
    }

    override beforeOnInit(form: SearchAccessPointTypesVarsForm): SearchAccessPointTypesVarsForm{     
        return form;
    }
	
    override doNgOnDestroy(){}

    /**
     * This method may be overwritten
     */
    override beforeSearchAccessPointTypesSearch(form: SearchAccessPointTypesSearchForm): void {
      this.store.dispatch(AccessPointTypeActions.search({
        criteria: form.criteria,
        loading: true
      }));
      
    }
}