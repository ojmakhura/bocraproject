import { Component, Injector } from '@angular/core';
import { ComplaintsDashboardComponent } from '@app/view/complaint/complaints-dashboard.component';


@Component({
    selector: 'app-complaints-dashboard',
    templateUrl: './complaints-dashboard.component.html',
})

export class ComplaintsDashboardComponentImpl extends ComplaintsDashboardComponent {
    constructor(private injector: Injector) {
        super(injector);
    }
}