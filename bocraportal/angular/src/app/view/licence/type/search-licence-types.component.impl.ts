// Generated by andromda-angular cartridge (view\view.component.imp.ts.vsl) CAN EDIT!
import { Component, Injector } from '@angular/core';
import { SearchLicenceTypesComponent, SearchLicenceTypesVarsForm } from '@app/view/licence/type/search-licence-types.component';
import { SearchLicenceTypesSearchForm } from '@app/view/licence/type/search-licence-types.component';
import * as licenceTypeActions from '@app/store/licence/type/licence-type.actions';

@Component({
  selector: 'app-search-licence-types',
  templateUrl: './search-licence-types.component.html',
  styleUrls: ['./search-licence-types.component.scss']
})
export class SearchLicenceTypesComponentImpl extends SearchLicenceTypesComponent {
    
    constructor(private injector: Injector) {
        super(injector);
    }

    override beforeOnInit(form: SearchLicenceTypesVarsForm): SearchLicenceTypesVarsForm {
        return form;
    }
	
    doNgOnDestroy(){}

    /**
     * This method may be overwritten
     */
    override beforeSearchLicenceTypesSearch(form: SearchLicenceTypesSearchForm): void {
        this.store.dispatch(licenceTypeActions.search({
            criteria: form.criteria,
            loading: true
        }));
    }
}