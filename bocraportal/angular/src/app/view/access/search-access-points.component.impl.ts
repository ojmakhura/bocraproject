// Generated by andromda-angular cartridge (view\view.component.imp.ts.vsl) CAN EDIT!
import { Component, Injector } from '@angular/core';
import { SearchAccessPointsComponent } from '@app/view/access/search-access-points.component';
import { SearchAccessPointsSearchForm } from '@app/view/access/search-access-points.component';
import { SearchAccessPointsVarsForm } from '@app/view/access/search-access-points.component';
import { AccessPointState } from '@app/store/access/access-point.state';
import * as AccessPointSelectors from '@app/store/access/access-point.selectors';
import * as AccessPointActions from '@app/store/access/access-point.actions';
import { MatRadioChange } from '@angular/material/radio';
import { MatCheckboxChange } from '@angular/material/checkbox';

@Component({
  selector: 'app-search-access-points',
  templateUrl: './search-access-points.component.html',
  styleUrls: ['./search-access-points.component.scss']
})
export class SearchAccessPointsComponentImpl extends SearchAccessPointsComponent {

    constructor(private injector: Injector) {
        super(injector);
    }

    override beforeOnInit(form: SearchAccessPointsVarsForm): SearchAccessPointsVarsForm{     
        return form;
    }
	
    override doNgOnDestroy(){}
    
    /**
     * This method may be overwritten
     */
    override beforeSearchAccessPointsSearch(form: SearchAccessPointsSearchForm): void {
      this.store.dispatch(AccessPointActions.search({
        criteria: form.criteria,
        loading: true
      }));
      
    }
}