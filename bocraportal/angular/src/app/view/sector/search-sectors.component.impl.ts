// Generated by andromda-angular cartridge (view\view.component.imp.ts.vsl) CAN EDIT!
import { Component, Injector } from '@angular/core';
import * as SectorActions from '@app/store/sector/sector.actions';
import {
  SearchSectorsComponent,
  SearchSectorsSearchForm,
  SearchSectorsVarsForm,
} from '@app/view/sector/search-sectors.component';

@Component({
  selector: 'app-search-sectors',
  templateUrl: './search-sectors.component.html',
  styleUrls: ['./search-sectors.component.scss'],
})
export class SearchSectorsComponentImpl extends SearchSectorsComponent {
  constructor(private injector: Injector) {
    super(injector);
  }

  override beforeOnInit(form: SearchSectorsVarsForm): SearchSectorsVarsForm {
    return form;
  }

  override doNgOnDestroy() {}

  /**
   * This method may be overwritten
   */
  override beforeSearchSectorsSearch(form: SearchSectorsSearchForm): void {
    this.store.dispatch(
      SectorActions.search({
        criteria: form.criteria,
        loading: true,
        loaderMessage: 'Searching sectors ...',
      })
    );
  }
}
