// Generated by andromda-angular cartridge (view\view.component.imp.ts.vsl) CAN EDIT!
import { Component, Injector } from '@angular/core';
import { NewDocumentComponent } from '@app/view/licensee/new-document.component';
import { LicenseeState } from '@app/store/licensee/licensee.state';
import * as LicenseeSelectors from '@app/store/licensee/licensee.selectors';
import * as LicenseeActions from '@app/store/licensee/licensee.actions';

@Component({
  selector: 'app-new-document',
  templateUrl: './new-document.component.html',
  styleUrls: ['./new-document.component.scss']
})
export class NewDocumentComponentImpl extends NewDocumentComponent {

    constructor(private injector: Injector) {
        super(injector);
    }

    beforeOnInit(){     
    }
	
    afterOnInit() {
    }

    doNgAfterViewInit() {
    }

    doNgOnDestroy(){}

    handleFormChanges(change: any) {
    }


    handleCancelDialog(): void {

    }

    handleDialogDone(data: any): any {
        return data;
    }
}