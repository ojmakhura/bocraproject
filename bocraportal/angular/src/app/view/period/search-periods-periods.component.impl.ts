// Generated by andromda-jsf cartridge (view\table\table.component.impl.ts.vsl)
import { Component, Injector } from '@angular/core';
import { SearchPeriodsPeriodsComponent } from '@app/view/period/search-periods-periods.component';

@Component({
  selector: 'search-periods-periods',
  templateUrl: './search-periods-periods.component.html',
  styleUrls: [ './search-periods-periods.component.scss' ]
})
export class SearchPeriodsPeriodsComponentImpl extends SearchPeriodsPeriodsComponent {

    constructor(private injector: Injector) {
        super(injector);
    }

	beforeOnInit(){
	}
	
	afterOnInit() {
	}

  doNgAfterViewInit() {
    
  }
  doNgOnDestroy(): void {}


	doSearchPeriodsEdit(form: any): any {
    return form;	}
}