// Generated by andromda-jsf cartridge (view\table\table.component.impl.ts.vsl)
import { Component, Injector } from '@angular/core';
import { SearchAccessPointTypesAccessPointTypesComponent } from '@app/view/access/type/search-access-point-types-access-point-types.component';

@Component({
  selector: 'search-access-point-types-access-point-types',
  templateUrl: './search-access-point-types-access-point-types.component.html',
  styleUrls: ['./search-access-point-types-access-point-types.component.scss'],
})
export class SearchAccessPointTypesAccessPointTypesComponentImpl extends SearchAccessPointTypesAccessPointTypesComponent {
  
  constructor(private injector: Injector) {
    super(injector);
  }

  override doSearchAccessPointTypesEdit(form: any): any {
    return form;
  }
}
