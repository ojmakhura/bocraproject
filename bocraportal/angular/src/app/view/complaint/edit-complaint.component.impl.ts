// Generated by andromda-angular cartridge (view\view.component.imp.ts.vsl) CAN EDIT!
import { Component, Injector, ViewChild } from '@angular/core';
import { FormArray, FormControl, FormGroup, Validators } from '@angular/forms';
import { ComplaintReplyVO } from '@app/model/bw/org/bocra/portal/complaint/complaint-reply-vo';
import { ComplaintStatus } from '@app/model/bw/org/bocra/portal/complaint/complaint-status';
import { ComplaintVO } from '@app/model/bw/org/bocra/portal/complaint/complaint-vo';
import { DocumentVO } from '@app/model/bw/org/bocra/portal/document/document-vo';
import * as ComplaintActions from '@app/store/complaint/complaint.actions';
import * as ComplaintSelectors from '@app/store/complaint/complaint.selectors';
import * as ComplaintTypeActions from '@app/store/complaint/type/complaint-type.actions';
import * as ComplaintTypeSelectors from '@app/store/complaint/type/complaint-type.selectors';
import * as DocumentActions from '@app/store/document/document.actions';
import * as DocumentSelectors from '@app/store/document/document.selectors';
import * as LicenseeActions from '@app/store/licensee/licensee.actions';
import * as LicenseeSelectors from '@app/store/licensee/licensee.selectors';
import * as ViewActions from '@app/store/view/view.actions';
import * as ViewSelectors from '@app/store/view/view.selectors';
import {
  EditComplaintComponent,
  EditComplaintDeleteForm,
  EditComplaintNewDocumentForm,
  EditComplaintReplyForm,
  EditComplaintSaveForm,
} from '@app/view/complaint/edit-complaint.component';
import { DocumentMetadataTarget } from '@model/bw/org/bocra/portal/document/document-metadata-target';
import { select } from '@ngrx/store';
import { KeycloakService } from 'keycloak-angular';
import { Observable } from 'rxjs';
import { saveAs } from 'file-saver';
import { UserVO } from '@app/model/bw/org/bocra/portal/user/user-vo';
import { MatTableDataSource } from '@angular/material/table';
import { MatCheckboxChange } from '@angular/material/checkbox';
import { MatRadioChange } from '@angular/material/radio';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import * as UserActions from '@app/store/user/user.actions';
import * as UserSelectors from '@app/store/user/user.selectors';

@Component({
  selector: 'app-edit-complaint',
  templateUrl: './edit-complaint.component.html',
  styleUrls: ['./edit-complaint.component.scss'],
})
export class EditComplaintComponentImpl extends EditComplaintComponent {
  searchComplaintIdForm = new FormGroup({
    searchComplaintId: new FormControl(''),
  });

  protected keycloakService: KeycloakService;
  unauthorisedUrls$!: Observable<string[]>;
  deleteUnrestricted: boolean = true;
  loadUnrestricted: boolean = true;
  newUnrestricted: boolean = true;
  deleteReplyUnrestricted: boolean = true;
  editReplyUnrestricted: boolean = true;
  addReplyUnrestricted: boolean = true;
  deleteDocumentUnrestricted: boolean = true;
  addDocumentUnrestricted: boolean = true;
  downloadDocumentUnrestricted: boolean = true;
  documentDelete$: Observable<boolean>;
  complaintReply$: Observable<ComplaintReplyVO>;
  complaintDocument$: Observable<DocumentVO>;
  loggedIn: boolean = false;
  loadComplaint: boolean = false;
  loadNew: boolean = false;
  file$: Observable<Blob>;

  constructor(private injector: Injector) {
    super(injector);
    this.keycloakService = this._injector.get(KeycloakService);
    this.complaintLicensees$ = this.store.pipe(select(LicenseeSelectors.selectLicensees));
    this.complaintDocument$ = this.store.pipe(select(DocumentSelectors.selectDocument));
    this.complaintComplaintTypes$ = this.store.pipe(select(ComplaintTypeSelectors.selectComplaintTypes));
    this.documentDelete$ = this.store.pipe(select(DocumentSelectors.selectRemoved));
    this.complaintReply$ = this.store.pipe(select(ComplaintSelectors.selectComplaintReply));
    this.unauthorisedUrls$ = this.store.pipe(select(ViewSelectors.selectUnauthorisedUrls));
    this.file$ = this.store.pipe(select(DocumentSelectors.selectFile));
    this.licenseeUsers$ = this.store.pipe(select(UserSelectors.selectUsers));
    // this.accessPointAccessPointTypes$ = this.store.pipe(select(AccessPointTypeSelectors.selectAccessPointTypes));
    this.keycloakService.isLoggedIn().then((loggedIn) => {
      this.loggedIn = loggedIn;
    });
  }

  doNgOnDestroy(): void {}

  override doNgAfterViewInit(): void {
    this.store.dispatch(
      ViewActions.loadViewAuthorisations({
        viewUrl: '/complaint/edit-complaint',
        roles: this.keycloakService.getUserRoles(),
        loading: true,
      })
    );

    this.route.queryParams.subscribe((queryParams: any) => {
      if (queryParams?.complaintId) {
        this.store.dispatch(
          ComplaintActions.findByComplaintId({
            complaintId: queryParams?.complaintId,
            loading: true,
            loaderMessage: 'Find complaint by complaint id ...',
          })
        );
      } else if (queryParams?.id) {
        this.store.dispatch(
          ComplaintActions.findById({
            id: queryParams?.id,
            loading: false,
            loaderMessage: 'Find complaint by id.',
          })
        );
      }
    });

    this.complaint$?.subscribe((complaint) => {
      this.setEditComplaintFormValue({ complaint: complaint });
    });

    this.unauthorisedUrls$?.subscribe((restrictedItems) => {
      restrictedItems.forEach((item) => {
        if (item === '/complaint/edit-complaint/{button:delete}') {
          this.deleteUnrestricted = false;
        }
        if(item === '/complaint/edit-complaint/{button:load}') {
          this.loadUnrestricted = false;
        }
        if(item === '/complaint/edit-complaint/{button:new}') {
          this.newUnrestricted = false;
        }
        if(item === '/complaint/edit-complaint/{button:addReply}') {
          this.addReplyUnrestricted = false;
        }
        if(item === '/complaint/edit-complaint/{button:editReply}') {
          this.editReplyUnrestricted = false;
        }
        if(item === '/complaint/edit-complaint/{button:deleteReply}') {
          this.deleteReplyUnrestricted = false;
        }
        if(item === '/complaint/edit-complaint/{button:deleteDocument}') {
          this.deleteDocumentUnrestricted = false;
        }
        if(item === '/complaint/edit-complaint/{button:addDocument}') {
          this.addDocumentUnrestricted = false;
        }
        if(item === '/complaint/edit-complaint/{button:downloadDocument}') {
          this.downloadDocumentUnrestricted = false;
        }
      });
    });

    this.complaintReply$.subscribe((reply) => {
      if (reply) {
        let rp: ComplaintReplyVO | undefined = this.complaintComplaintReplies.find((rep, i) => {
          if (rep.id == reply.id) {
            this.complaintComplaintRepliesControl.at(i).patchValue(reply);
            return true;
          } else {
            return false;
          }
        });

        if (!rp) {
          this.addToComplaintComplaintReplies(reply);
        }
      }
    });

    this.complaintDocument$.subscribe((document) => {
      this.addToComplaintDocuments(document);
    });
  }

  complaintIdEntered() {
    console.log(this.complaintComplaintId);
    this.router.navigate(['/complaint/edit-complaint'], { queryParams: { complaintId: this.complaintComplaintId } });
  }

  override createComplaintForm(complaint: ComplaintVO): FormGroup {
    if (complaint === undefined) {
      complaint = new ComplaintVO();
    }

    return this.formBuilder.group({
      id: [{ value: complaint?.id, disabled: false }],
      status: [{ value: complaint?.status ? complaint?.status : 'NEW', disabled: false }, [Validators.required]],
      complaintId: [{ value: complaint?.complaintId, disabled: false }],
      complaintType: this.createComplaintTypeVOGroup(complaint?.complaintType),
      licensee: this.createLicenseeVOGroup(complaint?.licensee),
      firstName: [{ value: complaint?.firstName, disabled: false }, [Validators.required]],
      surname: [{ value: complaint?.surname, disabled: false }, [Validators.required]],
      email: [{ value: complaint?.email, disabled: false }, [Validators.required]],
      subject: [{ value: complaint?.subject, disabled: false }, [Validators.required]],
      details: [{ value: complaint?.details, disabled: false }, [Validators.required]],
      assignedTo: [{ value: complaint?.assignedTo, disabled: false }],
      documents: this.createDocumentVOArray(complaint?.documents),
      complaintReplies: this.createComplaintReplyVOArray(complaint?.complaintReplies),
      createdDate: [{ value: complaint?.createdDate, disabled: false }],
      updatedDate: [{ value: complaint?.updatedDate, disabled: false }],
    });
  }

  override complaintLicenseeSearch(): void {
    let criteria: string = '';
    criteria = this.complaintLicenseeSearchField.value;
    this.store.dispatch(
      LicenseeActions.search({
        criteria: { licenseeName: criteria },
        loading: true,
        loaderMessage: 'Searching licensees ...',
      })
    );
  }

  override complaintComplaintTypeSearch(): void {
    console.log(this.complaintComplaintTypeSearchField.value);
    this.store.dispatch(
      ComplaintTypeActions.search({
        criteria: this.complaintComplaintTypeSearchField.value,
        loading: true,
        loaderMessage: 'Searching complaint types ...',
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

      form.complaint.documents = [];

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
    if (form?.complaint?.id && confirm('Are you sure you want to delete the complaint?')) {
      this.store.dispatch(
        ComplaintActions.remove({
          id: form?.complaint?.id,
          loading: false,
          loaderMessage: 'Removing compliant',
        })
      );
      this.editComplaintFormReset();
    } else {
      this.store.dispatch(ComplaintActions.complaintFailure({ messages: ['Please select something to delete'] }));
    }
  }

  override afterEditComplaintReply(form: EditComplaintReplyForm, dialogData: any): void {
    console.log(dialogData);
    if (dialogData) {
      if (!form?.complaintReply?.id) {
        dialogData.complaintReply.date = new Date();
      }

      this.keycloakService.isLoggedIn().then((loggedIn) => {
        if (loggedIn) {
          dialogData.complaintReply.replyUser = this.keycloakService.getUsername();
        } else {
          dialogData.complaintReply.replyUser = this.complaint?.firstName + ' ' + this.complaint?.surname;
        }

        this.store.dispatch(
          ComplaintActions.addComplaintReply({
            complaintId: this.complaintComplaintId,
            reply: dialogData.complaintReply,
            loading: true,
            loaderMessage: 'Adding reply to complaint ...',
          })
        );
      });
    }
  }

  override doEditComplaintComplaintReplies(complaintReplies: ComplaintReplyVO) {
    this.useCaseScope.queryParams['complaintReply'] = complaintReplies;
    this.useCaseScope.pageVariables['complaintReply'] = complaintReplies;
    this.editComplaintReply();
  }

  override getEditComplaintReplyFormDialogConfig(data: any): any {
    return {
      width: '600px',
      data: {
        complaintReply: data,
      },
    };
  }

  override deleteFromComplaintComplaintReplies(id: number) {
    for (let i = 0; i < this.complaintComplaintReplies.length; i++) {
      if (this.complaintComplaintReplies[i].id === id) {
        // this.handleDeleteFromComplaintComplaintReplies(this.complaintComplaintReplies[i]);
        if (confirm('Are you sure you want to delete the complaint reply')) {
          this.store.dispatch(
            ComplaintActions.removeComplaintReply({
              id: this.complaintComplaintReplies[i].id,
              loading: true,
              loaderMessage: 'Removing reply ...',
            })
          );
          this.complaintComplaintRepliesControl.removeAt(i);
        }
        return;
      }
    }
  }

  override afterEditComplaintNewDocument(form: EditComplaintNewDocumentForm, dialogData: any) {
    if (dialogData) {
      this.store.dispatch(
        DocumentActions.uploadFile({
          metadataTarget: DocumentMetadataTarget.COMPLAINT,
          metadataTargetId: this.complaintId,
          documentTypeId: dialogData.document.documentType.id,
          file: dialogData.document.file,
          fileName: dialogData.document.documentName,
          loading: true,
          loaderMessage: 'Adding document to complaint ...',
        })
      );
    }
  }

  override deleteFromComplaintDocuments(index: number) {
    if (confirm('Are you sure you want to delete the complaint document?')) {
      const doc: DocumentVO = this.complaintDocuments[index];
      this.store.dispatch(
        DocumentActions.remove({
          id: doc.id,
          loading: true,
          loaderMessage: 'Removing document ...',
        })
      );
      this.documentDelete$.subscribe((removed) => {
        if (removed) {
          this.complaintDocumentsControl.removeAt(index);
        }
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
      },
    });
  }

  scroll(el: HTMLElement) {
    el.scrollIntoView();
  }

  downloadFile(documentId: number, documentName: string) {
    this.store.dispatch(
      DocumentActions.downloadFile({
        documentId: documentId,
        loading: true,
        loaderMessage: 'Downloading document ...',
      })
    );

    this.file$.subscribe((file: any) => {
      if (file) {
        let blob: any = file as Blob;
        const url = window.URL.createObjectURL(blob);
        saveAs(blob, documentName);
      }
    });
  }

  onSubmit(): void {
    this.router.navigate([`/complaint/edit-complaint`], {queryParams: {complaintId: this.searchComplaintIdForm.value.searchComplaintId}});
  }

  newComplaint(): void {
    this.loadNew = true;
  }

  load(): void {
    this.loadNew = false;
    this.loadComplaint = false;
  }

  override licenseeUsersSearch(): void {
    this.store.dispatch(
      UserActions.search({
        criteria: this.licenseeUsersSearchField.value,
        loading: true,
        loaderMessage: 'Searching users ...',
      })
    );
  }

  accessDashboard(): void {
    this.router.navigate([`/complaint/complaints-dashboard`]);
}
}
