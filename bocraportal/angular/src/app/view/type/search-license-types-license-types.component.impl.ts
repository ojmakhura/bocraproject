// Generated by andromda-jsf cartridge (view\table\table.component.impl.ts.vsl)
import { Component, Injector } from '@angular/core';
import { SearchLicenseTypesLicenseTypesComponent } from '@app/view/type/search-license-types-license-types.component';

@Component({
  selector: 'search-license-types-license-types',
  templateUrl: './search-license-types-license-types.component.html',
  styleUrls: [ './search-license-types-license-types.component.scss' ]
})
export class SearchLicenseTypesLicenseTypesComponentImpl extends SearchLicenseTypesLicenseTypesComponent {

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


	doSearchLicenseTypesEdit(form: any): any {
    return form;	}
}