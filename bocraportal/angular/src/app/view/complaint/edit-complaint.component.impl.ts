// Generated by andromda-angular cartridge (view\view.component.imp.ts.vsl) CAN EDIT!
import { Component, Injector } from '@angular/core';
import { EditComplaintComponent, EditComplaintDeleteForm, EditComplaintNewDocumentForm, EditComplaintReplyForm, EditComplaintSaveForm } from '@app/view/complaint/edit-complaint.component';
import { ComplaintState } from '@app/store/complaint/complaint.state';
import * as ComplaintSelectors from '@app/store/complaint/complaint.selectors';
import * as ComplaintActions from '@app/store/complaint/complaint.actions';
import { MatRadioChange } from '@angular/material/radio';
import { MatCheckboxChange } from '@angular/material/checkbox';
import * as LicenseeActions from '@app/store/licensee/licensee.actions';
import * as LicenseSelectors from '@app/store/licensee/licensee.selectors';
import * as DocumentActions from '@app/store/document/document.actions';
import * as DocumentSelectors from '@app/store/document/document.selectors';
import { select } from '@ngrx/store';
import { Form, FormGroup } from '@angular/forms';
import { Observable } from 'rxjs';
import { DocumentVO } from '@app/model/bw/org/bocra/portal/document/document-vo';
import * as ViewActions from '@app/store/view/view.actions';
import { KeycloakService } from 'keycloak-angular';

export class Reply {
  date: string = '';
  reply: string = '';
  reply_user: string = '';
}

@Component({
  selector: 'app-edit-complaint',
  templateUrl: './edit-complaint.component.html',
  styleUrls: ['./edit-complaint.component.scss']
})

export class EditComplaintComponentImpl extends EditComplaintComponent {

  protected keycloakService: KeycloakService;
  unauthorisedUrls$!: Observable<string[]>;
  deleteUnrestricted: boolean = true;
  documentDelete$: Observable<boolean>;
  replies: Reply[] = [];

  constructor(private injector: Injector) {
    super(injector);
    this.keycloakService = this._injector.get(KeycloakService);
    this.complaintLicensees$ = this.store.pipe(select(LicenseSelectors.selectLicensees));
    this.complaintDocuments$ = this.store.pipe(select(ComplaintSelectors.selectDocument));
    this.documentDelete$ = this.store.pipe(select(DocumentSelectors.selectRemoved));
  }

  doNgOnDestroy(): void {
  }

  override doNgAfterViewInit(): void {

    this.replies = [
      {date: '2022-11-16 09:31:45.834', reply: 'Your complaint has been received. We will get back to you as soon as possible', reply_user:'Patience Obvious'},
      {date: '2022-11-16 09:31:45.834', reply: 'Thank you for informing me.', reply_user:'Kutlwano Kambela'},
      {date: '2022-11-16 09:31:45.834', reply: 'Your complaint has been resolved. Thank you for your patience', reply_user:'Patience Obvious'},

    ]

    this.store.dispatch(
      ViewActions.loadViewAuthorisations({
        viewUrl: "/complaint/edit-complaint",
        roles: this.keycloakService.getUserRoles(),
        loading: true,
      })
    );

    this.route.queryParams.subscribe((queryParams: any) => {
      if (queryParams?.id) {
        this.store.dispatch(
          ComplaintActions.findById({
            id: queryParams?.id,
            loading: false,
            loaderMessage: 'Find complaint by id.'
          })
        );
      }
    });

    this.complaint$.subscribe((complaint) => {
      this.setEditComplaintFormValue({ complaint: complaint });
    });

    this.unauthorisedUrls$.subscribe(restrictedItems => {
      restrictedItems.forEach(item => {
        if (item === '/complaint/edit-complaint/{button:delete}') {
          this.deleteUnrestricted = false;
        }
      });
    });
  }

  override complaintLicenseeSearch(): void {
    let criteria: string = '';
    criteria = this.complaintLicenseeSearchField.value;
    this.store.dispatch(
      LicenseeActions.search({
        criteria: { licenseeName: criteria },
        loading: true,
        loaderMessage: 'Searching licensees ...'
      })
    );
  }

  override beforeEditComplaintSave(form: EditComplaintSaveForm): void {
    if (this.complaintControl.valid) {
      if (form.complaint.id) {
        form.complaint.updatedDate = new Date();
      } else {
        form.complaint.createdDate = new Date();
      }
      this.store.dispatch(
        ComplaintActions.save({
          complaint: form.complaint,
          loading: true,
          loaderMessage: 'Save complaint',
        })
      );
    } else {
      let messages: string[] = [];
      if (!this.complaintControl.valid) {
        messages.push('Form has errors, Please fill in the required form fields');
      }

      this.store.dispatch(ComplaintActions.complaintFailure({ messages: messages }));
    }
  }

  override beforeEditComplaintDelete(form: EditComplaintDeleteForm): void {
    if (form?.complaint?.id && confirm("Are you sure you want to delete the complaint?")) {
      this.store.dispatch(
        ComplaintActions.remove({
          id: form?.complaint?.id,
          loading: false,
          loaderMessage: 'Removing compliant'
        })
      );
      this.editComplaintFormReset();
    } else {

      this.store.dispatch(
        ComplaintActions.complaintFailure({ messages: ['Please select something to delete'] })
      );
    }
  }

  override afterEditComplaintReply(form: EditComplaintReplyForm, dialogData: any): void {
    if (dialogData) {
      console.log(dialogData);
      this.store.dispatch(
        ComplaintActions.addComplaintReply({
          complaintId: this.complaintId,
          reply: dialogData.complaintReply,
          loading: true,
          loaderMessage: 'Adding reply to complaint ...'
        })
      )
    }
  }

  // override afterEditComplaintNewDocument(form: EditComplaintNewDocumentForm, dialogData: any): void {

  //   if (dialogData) {
  //     this.store.dispatch(
  //       ComplaintActions.addDocument({
  //         id: this.complaintId,
  //         documentTypeId: dialogData.document.documentType.id,
  //         file: dialogData.document.file,
  //         fileName: dialogData.document.documentName,
  //         loading: true,
  //         loaderMessage: 'Adding document to complaint ...'
  //       })
  //     );
  //   }
  // }

  override deleteFromComplaintDocuments(index: number) {
    if (confirm('Are you sure you want to delete the complaint document?')) {
      const doc: DocumentVO = this.complaintDocuments[index];
      this.store.dispatch(
        DocumentActions.remove({
          id: doc.id,
          loading: true,
          loaderMessage: 'Removing document ...'
        })
      );
      this.documentDelete$.subscribe(removed => {
        this.complaintDocumentsControl.removeAt(index);
      });

    }
  }

  override createDocumentVOGroup(value: DocumentVO): FormGroup {
    return this.formBuilder.group({
      id: [value?.id],
      createdBy: [value?.createdBy],
      updatedBy: [value?.updatedBy],
      createdDate: [value?.createdDate],
      updatedDate: [value?.updatedDate],
      documentName: [value?.documentName],
      file: [value?.file],
      documentId: [value?.documentId],
      documentType: {
        id: [value?.documentType?.id],
        code: [value?.documentType?.code],
        name: [value?.documentType?.name],
      }
    });
  }

  scroll(el: HTMLElement) {
    el.scrollIntoView();
  }
}