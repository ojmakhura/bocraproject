// Generated by andromda-angular cartridge (view\view.component.imp.ts.vsl) CAN EDIT!
import { Component, Injector } from '@angular/core';
import { EditComplaintTypeComponent, EditComplaintTypeSaveForm, EditComplaintTypeVarsForm } from '@app/view/complaint/type/edit-complaint-type.component';
import { ComplaintTypeState } from '@app/store/complaint/type/complaint-type.state';
import * as ComplaintTypeSelectors from '@app/store/complaint/type/complaint-type.selectors';
import * as ComplaintTypeActions from '@app/store/complaint/type/complaint-type.actions';
import { MatRadioChange } from '@angular/material/radio';
import { MatCheckboxChange } from '@angular/material/checkbox';
import { Observable } from 'rxjs';
import { KeycloakService } from 'keycloak-angular';
import { select } from '@ngrx/store';
import * as ViewActions from '@app/store/view/view.actions';
import * as ViewSelectors from '@app/store/view/view.selectors';

@Component({
  selector: 'app-edit-complaint-type',
  templateUrl: './edit-complaint-type.component.html',
  styleUrls: ['./edit-complaint-type.component.scss']
})
export class EditComplaintTypeComponentImpl extends EditComplaintTypeComponent {
  protected keycloakService: KeycloakService;
  deleteUnrestricted: boolean = true;
  unauthorisedUrls$: Observable<string[]>;
  constructor(private injector: Injector) {
    super(injector);
    this.unauthorisedUrls$ = this.store.pipe(select(ViewSelectors.selectUnauthorisedUrls));
    this.keycloakService = injector.get(KeycloakService);
  }

  override beforeOnInit(form: EditComplaintTypeVarsForm): EditComplaintTypeVarsForm {
      return form;
  }

  doNgOnDestroy(): void {
  }

  override doNgAfterViewInit(): void {
    this.store.dispatch(
      ViewActions.loadViewAuthorisations({
        viewUrl: "/complaint/type/edit-complaint-type",
        roles: this.keycloakService.getUserRoles(),
        loading: true,
      })
    );

    this.route.queryParams.subscribe((queryParams: any) => {
      if (queryParams?.id) {
        this.store.dispatch(
          ComplaintTypeActions.findById({
            id: queryParams?.id,
            loading: false,
            loaderMessage: 'Loading complaint type by id ...'
          })
        );
      }
    });

    this.unauthorisedUrls$.subscribe(restrictedItems => {
      restrictedItems.forEach(item => {
        if (item === '/complaint/type/edit-complaint-type/{button:delete}') {
          this.deleteUnrestricted = false;
        }
      });
    });

    this.complaintType$.subscribe((complaintType) => {
      this.setEditComplaintTypeFormValue({ complaintType: complaintType });
    })
  }

  override beforeEditComplaintTypeSave(form: EditComplaintTypeSaveForm): void {
    console.log(form?.complaintType);
    if(this.editComplaintTypeForm.valid){
      if (form.complaintType?.id){
        form.complaintType.updatedBy = this.keycloakService.getUsername();
        form.complaintType.updatedDate = new Date();
      }else {
        form.complaintType.createdBy = this.keycloakService.getUsername();
        form.complaintType.createdDate = new Date();
      }

      this.store.dispatch(
        ComplaintTypeActions.save({
          complaintType: form.complaintType,
          loading: true,
          loaderMessage: 'Saving complaint type ...'
        })
      );

    }else {
      let messages: string[] = []
      if(!this.complaintTypeControl.valid) {
        messages.push("Complaint Type has errors, Please fill the required form fields");
      }
      if(!this.complaintTypeCodeControl.valid){
        messages.push("Complaint Type Code is missing!");
      }
      if(!this.complaintTypeTypeNameControl.valid){
        messages.push("Complaint Type Name is missing!");
      }
      this.store.dispatch(ComplaintTypeActions.complaintTypeFailure({messages: messages}));
    }
  }

  override beforeEditComplaintTypeDelete(form: EditComplaintTypeSaveForm){
    if(form?.complaintType?.id && confirm("Are you sure you want to delete the complaint type")) {
      this.store.dispatch(
        ComplaintTypeActions.remove({
          id: form?.complaintType?.id,
          loading: false,
          loaderMessage: 'Removing complaint type ...'
        })
      );
      this.editComplaintTypeFormReset();
    }else {
      this.store.dispatch(ComplaintTypeActions.complaintTypeFailure({messages: ['Please select something to delete']}));
    }
  }
}