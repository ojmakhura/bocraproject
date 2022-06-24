// Generated by andromda-angular cartridge (view\view.component.imp.ts.vsl) CAN EDIT!
import { Component, Injector } from '@angular/core';
import { SearchUsersComponent } from '@app/view/user/search-users.component';
import { SearchUsersSearchForm } from '@app/view/user/search-users.component';
import { SearchUsersVarsForm } from '@app/view/user/search-users.component';
import * as UserActions from '@app/store/user/user.actions';

@Component({
  selector: 'app-search-users',
  templateUrl: './search-users.component.html',
  styleUrls: ['./search-users.component.scss']
})
export class SearchUsersComponentImpl extends SearchUsersComponent {

    constructor(private injector: Injector) {
        super(injector);
    }

    override beforeOnInit(form: SearchUsersVarsForm): SearchUsersVarsForm{

        return form;
    }
	
    override doNgOnDestroy(){}

    /**
     * This method may be overwritten
     */
    override beforeSearchUsersSearch(form: SearchUsersSearchForm): void {
        this.store.dispatch(UserActions.search({
            criteria: form.criteria,
            loading: true
        }));
    }
    
}