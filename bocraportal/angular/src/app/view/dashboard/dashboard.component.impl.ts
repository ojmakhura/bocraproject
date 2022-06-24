// Generated by andromda-angular cartridge (view\view.component.imp.ts.vsl) CAN EDIT!
import { Component, Injector } from '@angular/core';
import { DashboardComponent } from '@app/view/dashboard/dashboard.component';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss'],
})
export class DashboardComponentImpl extends DashboardComponent {
  constructor(private injector: Injector) {
    super(injector);
  }

  beforeOnInit() {}

  doNgOnDestroy() {}

  override afterEditDashboardDelete(form: EditDashboardDeleteForm): void {
    if(form?.Dashboard?.id) {
      if(confirm("Are you sure to delete the dashboard configuration?")) {
        this.store.dispatch(DashboardActions.remove({id: form.dashboard.id, loading: true}));
        this.editDashboardFormReset();
      }
    }
  }
}
