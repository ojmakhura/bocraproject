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

    beforeOnInit(form: SearchUsersVarsForm): SearchUsersVarsForm{

        return form;
    }
	
    afterOnInit() {
        // this.addUsersDummyData();
    }

    doNgAfterViewInit() {
    }

    handleFormChanges(change: any) {
    }

    doNgOnDestroy(){}

    /**
     * This method may be overwritten
     */
    afterSetSearchUsersVarsForm(form: SearchUsersVarsForm): void {

    }

    /**
     * This method may be overwritten
     */
    afterSetSearchUsersSearchForm(form: SearchUsersSearchForm): void {

    }

    /**
     * This method may be overwritten
     */
    beforeSearchUsersSearch(form: SearchUsersSearchForm): void {
        this.store.dispatch(UserActions.search({
            criteria: form.criteria,
            loading: true
        }));
    }

    /**
     * This method may be overwritten
     */
    afterSearchUsersSearch(form: SearchUsersSearchForm): void {

    }
    
}