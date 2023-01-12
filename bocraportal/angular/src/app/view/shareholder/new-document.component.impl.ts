// Generated by andromda-angular cartridge (view\view.component.imp.ts.vsl) CAN EDIT!
import { Component, Injector, Inject } from '@angular/core';
import { NewDocumentComponent } from '@app/view/shareholder/new-document.component';
import { ShareholderState } from '@app/store/shareholder/shareholder.state';
import * as ShareholderSelectors from '@app/store/shareholder/shareholder.selectors';
import * as ShareholderActions from '@app/store/shareholder/shareholder.actions';
import { MatRadioChange } from '@angular/material/radio';
import { MatCheckboxChange } from '@angular/material/checkbox';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-new-document',
  templateUrl: './new-document.component.html',
  styleUrls: ['./new-document.component.scss']
})
export class NewDocumentComponentImpl extends NewDocumentComponent {

    constructor(@Inject(MAT_DIALOG_DATA) data: any, private injector: Injector) {
        super(data, injector);
    }

    doNgOnDestroy(): void {
    }
}