// Generated by andromda-angular cartridge (view\view.component.imp.ts.vsl) CAN EDIT!
import { Component, Injector, Inject } from '@angular/core';
import { NewDocumentComponent, NewDocumentVarsForm } from '@app/view/shareholder/new-document.component';
import { ShareholderState } from '@app/store/shareholder/shareholder.state';
import * as ShareholderSelectors from '@app/store/shareholder/shareholder.selectors';
import * as ShareholderActions from '@app/store/shareholder/shareholder.actions';
import * as DocumentTypeSelectors from '@app/store/document/type/document-type.selectors';
import * as DocumentTypeActions from '@app/store/document/type/document-type.actions';
import { MatRadioChange } from '@angular/material/radio';
import { MatCheckboxChange } from '@angular/material/checkbox';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { select } from '@ngrx/store';

@Component({
  selector: 'app-new-document',
  templateUrl: './new-document.component.html',
  styleUrls: ['./new-document.component.scss']
})
export class NewDocumentComponentImpl extends NewDocumentComponent {
  currentFile?: File = undefined;

  constructor(@Inject(MAT_DIALOG_DATA) data: any, private injector: Injector) {
    super(data, injector);
    this.documentDocumentTypes$ = this.store.pipe(select(DocumentTypeSelectors.selectDocumentTypes));
  }

  override beforeOnInit(form: NewDocumentVarsForm): NewDocumentVarsForm {
    return form;
  }

  doNgOnDestroy(): void {
  }

  override documentDocumentTypeSearch(): void {
    this.store.dispatch(
      DocumentTypeActions.search({
        criteria: this.documentDocumentTypeSearchField.value,
        loading: true,
        loaderMessage: 'Searching document types ...'
      })
    );
  }

  onFileSelected(event: any) {
    if (event) {
      this.currentFile = event.target.files[0];
      this.documentDocumentNameControl.patchValue(this.currentFile?.name)
    }
  }

  override handleDialogDone(data: any): any {
    if(data.document) {
      data.document.file = this.currentFile;
    }
    return data;
  }
}