// Generated by andromda-angular cartridge (view\view.component.imp.ts.vsl) CAN EDIT!
import { Component, Injector } from '@angular/core';
import { SearchUsersComponent } from '@app/view/user/search-users.component';
import { SearchUsersSearchForm } from '@app/view/user/search-users.component';

@Component({
  selector: 'app-search-users',
  templateUrl: './search-users.component.html',
  styleUrls: ['./search-users.component.scss']
})
export class SearchUsersComponentImpl extends SearchUsersComponent {

    constructor(private injector: Injector) {
        super(injector);
    }

    beforeOnInit(){
    }
	
    afterOnInit() {
    }

    doNgAfterViewInit() {
    }

    handleFormChanges(change: any) {
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

    }

    /**
     * This method may be overwritten
     */
    afterSearchUsersSearch(form: SearchUsersSearchForm): void {

    }
    
}