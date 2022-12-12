// Generated by andromda-angular cartridge (view\view.component.imp.ts.vsl) CAN EDIT!
import { Component, Injector } from '@angular/core';
import { EditComplaintComponent, EditComplaintDeleteForm, EditComplaintNewDocumentForm, EditComplaintReplyForm, EditComplaintSaveForm, EditComplaintVarsForm } from '@app/view/complaint/edit-complaint.component';
import { ComplaintState } from '@app/store/complaint/complaint.state';
import * as ComplaintSelectors from '@app/store/complaint/complaint.selectors';
import * as ComplaintTypeSelectors from '@app/store/complaint/type/complaint-type.selectors';
import * as ComplaintActions from '@app/store/complaint/complaint.actions';
import * as ComplaintTypeActions from '@app/store/complaint/type/complaint-type.actions';
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
import { ComplaintReplyVO } from '@app/model/bw/org/bocra/portal/complaint/complaint-reply-vo';
import * as ViewSelectors from '@app/store/view/view.selectors';
import { ReplyComponentImpl } from './reply.component.impl';
import { SelectItem } from '@app/utils/select-item';
import { ComplaintTypeVO } from '@app/model/bw/org/bocra/portal/complaint/type/complaint-type-vo';

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
  complaintReply$: Observable<ComplaintReplyVO>
  loggedIn: boolean = false;

  constructor(private injector: Injector) {
    super(injector);
    this.keycloakService = this._injector.get(KeycloakService);
    this.complaintLicensees$ = this.store.pipe(select(LicenseSelectors.selectLicensees));
    this.complaintDocuments$ = this.store.pipe(select(ComplaintSelectors.selectDocument));
    this.documentDelete$ = this.store.pipe(select(DocumentSelectors.selectRemoved));
    this.complaintReply$ = this.store.pipe(select(ComplaintSelectors.selectComplaintReply));
    this.unauthorisedUrls$ = this.store.pipe(select(ViewSelectors.selectUnauthorisedUrls));
    // this.accessPointAccessPointTypes$ = this.store.pipe(select(AccessPointTypeSelectors.selectAccessPointTypes));
    this.complaintComplaintTypes$ = this.store.pipe(select(ComplaintTypeSelectors.selectComplaintTypes));
    this.complaintComplaintTypeBackingList = [];
    this.keycloakService.isLoggedIn().then((loggedIn) => {
      this.loggedIn = loggedIn;
    });
  }

  // override beforeOnInit(form: EditComplaintVarsForm): EditComplaintVarsForm {
  //   this.store.dispatch(ComplaintActions.getAll({loading: true, loaderMessage: 'Loading all complaint types ...'}));
  //   this.complaintComplaintTypes$.subscribe(types => {
  //     this.complaintComplaintTypeBackingList = [];
  //     types.forEach(type => {
  //       let item: SelectItem = new SelectItem;
  //       item.label = type.typeName
  //       item.value = type.id

  //       this.complaintComplaintTypeBackingList.push(item);
  //     });
  //   });
  //   return form;  
  // }

  doNgOnDestroy(): void {
  }

  override doNgAfterViewInit(): void {

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

    this.complaint$?.subscribe((complaint) => {
      this.setEditComplaintFormValue({ complaint: complaint });
    });

    this.unauthorisedUrls$?.subscribe(restrictedItems => {
      restrictedItems.forEach(item => {
        if (item === '/complaint/edit-complaint/{button:delete}') {
          this.deleteUnrestricted = false;
        }
      });
    });

    this.complaintReply$.subscribe(reply => {
      if (reply) {
        let rp: ComplaintReplyVO | undefined = this.complaintComplaintReplies.find((rep, i) => {
          if(rep.id == reply.id) {
            this.complaintComplaintRepliesControl.at(i).patchValue(reply);
            return true;
          }else {
            return false;
          }
        });

        if(!rp) {
          this.addToComplaintComplaintReplies(reply);
        }
      }
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

  override complaintComplaintTypeSearch(): void {
    let criteria: string = '';
    criteria = this.complaintComplaintTypeSearchField.value;
    this.store.dispatch(
      ComplaintTypeActions.search({
        criteria: criteria,
        loading: true,
        loaderMessage: 'Searching complaint types ...'
      })
    )
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
      if (!form?.complaintReply?.id) {
        dialogData.complaintReply.date = new Date();
      }
      if (this.keycloakService.getUsername()) {
        dialogData.complaintReply.replyUser = this.keycloakService.getUsername()
      } else {
        dialogData.complaintReply.replyUser = this.complaint?.firstName + ' ' + this.complaint?.surname;
      }
      this.store.dispatch(
        ComplaintActions.addComplaintReply({
          complaintId: this.complaintComplaintId,
          reply: dialogData.complaintReply,
          loading: true,
          loaderMessage: 'Adding reply to complaint ...'
        })
      );
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
      }
    };
  }

  // override handleDeleteFromComplaintComplaintReplies(complaintReplies: ComplaintReplyVO): void {
  //   if (confirm('Are you sure you want to delete the complaint reply')) {
  //     this.store.dispatch(
  //       ComplaintActions.removeComplaintReply({
  //         id: complaintReplies.id,
  //         loading: true,
  //         loaderMessage: 'Removing reply ...'
  //       })
  //     )
  //   }
  // }

  override deleteFromComplaintComplaintReplies(id: number) {
    for (let i = 0; i < this.complaintComplaintReplies.length; i++) {
      if (this.complaintComplaintReplies[i].id === id) {
        // this.handleDeleteFromComplaintComplaintReplies(this.complaintComplaintReplies[i]);
        if (confirm('Are you sure you want to delete the complaint reply')) {
          this.store.dispatch(
            ComplaintActions.removeComplaintReply({
              id: this.complaintComplaintReplies[i].id,
              loading: true,
              loaderMessage: 'Removing reply ...'
            })
          )
          this.complaintComplaintRepliesControl.removeAt(i);
        }
        return;
      }
    }
  }

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