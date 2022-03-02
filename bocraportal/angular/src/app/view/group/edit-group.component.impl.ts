// Generated by andromda-angular cartridge (view\view.component.imp.ts.vsl) CAN EDIT!
import { Component, Injector } from '@angular/core';
import { LicenseeGroupVO } from '@app/model/bw/org/bocra/portal/group/licensee-group-vo';
import { EditGroupComponent } from '@app/view/group/edit-group.component';
import { EditGroupSaveForm } from '@app/view/group/edit-group.component';
import { EditGroupNewForm } from '@app/view/group/edit-group.component';
import { EditGroupSearchForm } from '@app/view/group/edit-group.component';
import { select } from '@ngrx/store';
import { Observable } from 'rxjs';
import * as groupSelectors from '@app/store/group/group.selector';
import * as groupActions from '@app/store/group/group.action';
import { EditLicenseeSaveForm } from '../licensee/edit-licensee.component';

@Component({
  selector: 'app-edit-group',
  templateUrl: './edit-group.component.html',
  styleUrls: ['./edit-group.component.scss'],
})
export class EditGroupComponentImpl extends EditGroupComponent {
  
  licenseeGroup$: Observable<LicenseeGroupVO>;
  licenseeGroups$: Observable<LicenseeGroupVO[]>;
  id$: Observable<number>;  

  constructor(private injector: Injector) {
    super(injector);
    this.licenseeGroup$ = this.store.pipe(select(groupSelectors.selectGroup));
    this.licenseeGroups$ = this.store.pipe(select(groupSelectors.selectGroups));
    this.id$ = this.store.pipe(select(groupSelectors.selectId));
  }

  beforeOnInit() {}

  afterOnInit() {
    if (this.useCaseScope.pageVariables['id']) {
      this.store.dispatch(groupActions.findById({ id: this.useCaseScope.pageVariables['id'] }));
    }

    this.licenseeGroup$.subscribe((group) => {
      this.setEditGroupSaveForm({ licenseeGroupVO: group } as EditGroupSaveForm);
    });
  }

  doNgAfterViewInit() {}

  handleFormChanges(change: any) {}

  /**
   * This method may be overwritten
   */
  afterSetEditGroupSaveForm(form: EditGroupSaveForm): void {}

  /**
   * This method may be overwritten
   */
  beforeEditGroupSave(form: EditGroupSaveForm): void {
    this.store.dispatch(groupActions.saveGroup({ group: form.licenseeGroupVO }));
  }

  /**
   * This method may be overwritten
   */
  afterEditGroupSave(form: EditGroupSaveForm): void {}

  /**
   * This method may be overwritten
   */
  afterSetEditGroupNewForm(form: EditGroupNewForm): void {}

  /**
   * This method may be overwritten
   */
  beforeEditGroupNew(form: EditGroupNewForm): void {
    this.store.dispatch(groupActions.reset());
  }

  /**
   * This method may be overwritten
   */
  afterEditGroupNew(form: EditGroupNewForm): void {}

  /**
   * This method may be overwritten
   */
  afterSetEditGroupSearchForm(form: EditGroupSearchForm): void {}

  /**
   * This method may be overwritten
   */
  beforeEditGroupSearch(form: EditGroupSearchForm): void {}

  /**
   * This method may be overwritten
   */
  afterEditGroupSearch(form: EditGroupSearchForm): void {}
}
