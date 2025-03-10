// Generated by andromda-angular cartridge (view\view.component.imp.ts.vsl) CAN EDIT!
import { Component, Injector } from '@angular/core';
import {
  SearchShareholdersComponent,
  SearchShareholdersSearchForm,
  SearchShareholdersVarsForm,
} from '@app/view/shareholder/search-shareholders.component';
import { ShareholderState } from '@app/store/shareholder/shareholder.state';
import * as ShareholderSelectors from '@app/store/shareholder/shareholder.selectors';
import * as ShareholderActions from '@app/store/shareholder/shareholder.actions';
import { MatRadioChange } from '@angular/material/radio';
import { MatCheckboxChange } from '@angular/material/checkbox';

@Component({
  selector: 'app-search-shareholders',
  templateUrl: './search-shareholders.component.html',
  styleUrls: ['./search-shareholders.component.scss'],
})
export class SearchShareholdersComponentImpl extends SearchShareholdersComponent {
  constructor(private injector: Injector) {
    super(injector);
  }

  override beforeOnInit(form: SearchShareholdersVarsForm): SearchShareholdersVarsForm {
    return form;
  }

  doNgOnDestroy(): void {
    this.store.dispatch(ShareholderActions.shareholderReset());
  }

  override beforeSearchShareholdersSearch(form: SearchShareholdersSearchForm): void {
    this.store.dispatch(
      ShareholderActions.search({
        criteria: form?.criteria,
        loading: true,
        loaderMessage: 'Searching shareholders ...',
      })
    );
  }
}
