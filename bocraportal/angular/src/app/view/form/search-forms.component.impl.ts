// Generated by andromda-angular cartridge (view\view.component.imp.ts.vsl) CAN EDIT!
import { Component, Injector } from '@angular/core';
import { SearchFormsComponent } from '@app/view/form/search-forms.component';
import { SearchFormsSearchForm } from '@app/view/form/search-forms.component';
import { SearchFormsVarsForm } from '@app/view/form/search-forms.component';
import * as FormActions from '@app/store/form/form.actions';

@Component({
  selector: 'app-search-forms',
  templateUrl: './search-forms.component.html',
  styleUrls: ['./search-forms.component.scss'],
})
export class SearchFormsComponentImpl extends SearchFormsComponent {
  constructor(private injector: Injector) {
    super(injector);
  }

  beforeOnInit() {

    this.store.dispatch(FormActions.formReset());
  }

  afterOnInit() {}

  doNgAfterViewInit() {}

  handleFormChanges(change: any) {}

  doNgOnDestroy() {}

  /**
   * This method may be overwritten
   */
  afterSetSearchFormsVarsForm(form: SearchFormsVarsForm): void {}

  /**
   * This method may be overwritten
   */
  afterSetSearchFormsSearchForm(form: SearchFormsSearchForm): void {}

  /**
   * This method may be overwritten
   */
  beforeSearchFormsSearch(form: SearchFormsSearchForm): void {
    this.store.dispatch(FormActions.searchForms({
      criteria: form.criteria,
      loading: true
    }));
  }

  /**
   * This method may be overwritten
   */
  afterSearchFormsSearch(form: SearchFormsSearchForm): void {}
}
