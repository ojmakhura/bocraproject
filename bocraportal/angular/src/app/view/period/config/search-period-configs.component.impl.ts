// Generated by andromda-angular cartridge (view\view.component.imp.ts.vsl) CAN EDIT!
import { Component, Injector } from '@angular/core';
import { SearchPeriodConfigsComponent } from '@app/view/period/config/search-period-configs.component';
import { SearchPeriodConfigsSearchForm } from '@app/view/period/config/search-period-configs.component';
import { SearchPeriodConfigsVarsForm } from '@app/view/period/config/search-period-configs.component';

@Component({
  selector: 'app-search-period-configs',
  templateUrl: './search-period-configs.component.html',
  styleUrls: ['./search-period-configs.component.scss']
})
export class SearchPeriodConfigsComponentImpl extends SearchPeriodConfigsComponent {

    constructor(private injector: Injector) {
        super(injector);
    }

    beforeOnInit(){
    }
	
    afterOnInit() {
        // this.addPeriodConfigsDummyData();
    }

    doNgAfterViewInit() {
    }

    handleFormChanges(change: any) {
    }

    /**
     * This method may be overwritten
     */
    afterSetSearchPeriodConfigsVarsForm(form: SearchPeriodConfigsVarsForm): void {

    }

    /**
     * This method may be overwritten
     */
    afterSetSearchPeriodConfigsSearchForm(form: SearchPeriodConfigsSearchForm): void {

    }

    /**
     * This method may be overwritten
     */
    beforeSearchPeriodConfigsSearch(form: SearchPeriodConfigsSearchForm): void {

    }

    /**
     * This method may be overwritten
     */
    afterSearchPeriodConfigsSearch(form: SearchPeriodConfigsSearchForm): void {

    }
    
}