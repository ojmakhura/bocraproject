// Generated by andromda-angular cartridge (view\view.component.imp.ts.vsl) CAN EDIT!
import { Component, Injector } from '@angular/core';
import { SearchSystemConfigsComponent, SearchSystemConfigsSearchForm } from '@app/view/config/search-system-configs.component';
import * as SystemConfigActions from '@app/store/config/system-config.actions';

@Component({
  selector: 'app-search-system-configs',
  templateUrl: './search-system-configs.component.html',
  styleUrls: ['./search-system-configs.component.scss'],
})
export class SearchSystemConfigsComponentImpl extends SearchSystemConfigsComponent {
  constructor(private injector: Injector) {
    super(injector);
  }

  doNgOnDestroy(): void {}

  override beforeSearchSystemConfigsSearch(form: SearchSystemConfigsSearchForm): void {
    this.store.dispatch(
      SystemConfigActions.search({
        criteria: form.criteria,
        loading: true,
        loaderMessage: 'Searching system configurations ...',
      })
    );
  }
}
