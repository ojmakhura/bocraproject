// Generated by andromda-angular cartridge (view\view.component.imp.ts.vsl) CAN EDIT!
import { Component, Injector } from '@angular/core';
import { DataCaptureProcessingComponent } from '@app/view/form/processing/data-capture-processing.component';
import { DataCaptureProcessingNewSubmissionForm } from '@app/view/form/processing/data-capture-processing.component';
import { DataCaptureProcessingDraftsForm } from '@app/view/form/processing/data-capture-processing.component';
import { DataCaptureProcessingMySubmissionsForm } from '@app/view/form/processing/data-capture-processing.component';
import { DataCaptureProcessingAllSubmissionsForm } from '@app/view/form/processing/data-capture-processing.component';
import { DataCaptureProcessingOverdueSubmissionsForm } from '@app/view/form/processing/data-capture-processing.component';
import { DataCaptureProcessingReturnedSubmissionsForm } from '@app/view/form/processing/data-capture-processing.component';
import { DataProcessingState } from '@app/store/form/processing/data-processing.state';
import * as DataProcessingSelectors from '@app/store/form/processing/data-processing.selectors';
import * as DataProcessingActions from '@app/store/form/processing/data-processing.actions';
import { MatRadioChange } from '@angular/material/radio';
import { MatCheckboxChange } from '@angular/material/checkbox';

@Component({
  selector: 'app-data-capture-processing',
  templateUrl: './data-capture-processing.component.html',
  styleUrls: ['./data-capture-processing.component.scss']
})
export class DataCaptureProcessingComponentImpl extends DataCaptureProcessingComponent {

    constructor(private injector: Injector) {
        super(injector);
    }

    override beforeOnInit(): void{     
    }

    doNgOnDestroy(): void {
    }
}