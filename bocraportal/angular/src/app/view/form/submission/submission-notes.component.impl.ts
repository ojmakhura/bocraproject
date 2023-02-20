// Generated by andromda-angular cartridge (view\view.component.imp.ts.vsl) CAN EDIT!
import { Component, Injector, Inject } from '@angular/core';
import { SubmissionNotesComponent } from '@app/view/form/submission/submission-notes.component';
import { FormSubmissionState } from '@app/store/form/submission/form-submission.state';
import * as FormSubmissionSelectors from '@app/store/form/submission/form-submission.selectors';
import * as FormSubmissionActions from '@app/store/form/submission/form-submission.actions';
import { MatRadioChange } from '@angular/material/radio';
import { MatCheckboxChange } from '@angular/material/checkbox';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-submission-notes',
  templateUrl: './submission-notes.component.html',
  styleUrls: ['./submission-notes.component.scss'],
})
export class SubmissionNotesComponentImpl extends SubmissionNotesComponent {
  constructor(@Inject(MAT_DIALOG_DATA) data: any, private injector: Injector) {
    super(data, injector);
  }

  override beforeOnInit(): void {}

  doNgOnDestroy(): void {}
}
