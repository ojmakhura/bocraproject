// Generated by andromda-angular cartridge (view\view.component.imp.ts.vsl) CAN EDIT!
import { Component, Injector } from '@angular/core';
import { ReportComponent } from '@app/view/report/report.component';

@Component({
  selector: 'app-report',
  templateUrl: './report.component.html',
  styleUrls: ['./report.component.scss']
})
export class ReportComponentImpl extends ReportComponent {

    constructor(private injector: Injector) {
        super(injector);
    }

    doNgOnDestroy(): void {
    }
}