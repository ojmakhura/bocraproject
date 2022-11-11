// Generated by andromda-angular cartridge (view\view.component.imp.ts.vsl) CAN EDIT!
import { Component, Injector } from '@angular/core';
import {
  EditFormActivationComponent,
  EditFormActivationDeleteForm,
  EditFormActivationSaveForm,
} from '@app/view/form/activation/edit-form-activation.component';
import { EditFormActivationVarsForm } from '@app/view/form/activation/edit-form-activation.component';
import { FormCriteria } from '@app/model/bw/org/bocra/portal/form/form-criteria';
import * as FormActions from '@app/store/form/form.actions';
import * as FormSelectors from '@app/store/form/form.selectors';
import * as PeriodActions from '@app/store/period/period.actions';
import * as PeriodSelectors from '@app/store/period/period.selectors';
import { PeriodCriteria } from '@app/model/bw/org/bocra/portal/period/period-criteria';
import { KeycloakService } from 'keycloak-angular';
import { select } from '@ngrx/store';
import { PeriodVO } from '@app/model/bw/org/bocra/portal/period/period-vo';
import { FormVO } from '@app/model/bw/org/bocra/portal/form/form-vo';
import * as FormActivationActions from '@app/store/form/activation/form-activation.actions';
import { FormSubmissionVO } from '@app/model/bw/org/bocra/portal/form/submission/form-submission-vo';
import { FormGroup } from '@angular/forms';
import * as ViewActions from '@app/store/view/view.actions';
import * as ViewSelectors from '@app/store/view/view.selectors';
import { Observable } from 'rxjs';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-edit-form-activation',
  templateUrl: './edit-form-activation.component.html',
  styleUrls: ['./edit-form-activation.component.scss'],
})
export class EditFormActivationComponentImpl extends EditFormActivationComponent {
  protected keycloakService: KeycloakService;
  unauthorisedUrls$: Observable<string[]>;
  deleteUnrestricted: boolean = true;
  acceptUnrestricted: boolean = true;
  returnUnrestricted: boolean = true;
  submitUnrestricted: boolean = true;
  datePipe: DatePipe;

  constructor(private injector: Injector) {
    super(injector);
    this.keycloakService = this._injector.get(KeycloakService);
    this.formActivationPeriods$ = this.store.pipe(select(PeriodSelectors.selectPeriods));
    this.formActivationForms$ = this.store.pipe(select(FormSelectors.selectForms));
    this.unauthorisedUrls$ = this.store.pipe(select(ViewSelectors.selectUnauthorisedUrls));
    this.datePipe = this._injector.get(DatePipe);
  }

  override beforeOnInit(form: EditFormActivationVarsForm): EditFormActivationVarsForm {
    return form;
  }

  doNgOnDestroy(): void {}

  override doNgAfterViewInit(): void {

    this.store.dispatch(
      ViewActions.loadViewAuthorisations({
        viewUrl: "/form/activation/edit-form-activation",
        roles: this.keycloakService.getUserRoles(),
        loading: true
      })
    );

    this.route.queryParams.subscribe((queryParams: any) => {
      if (queryParams?.id) {
        this.store.dispatch(
            FormActivationActions.findById({
            id: queryParams.id,
            loading: true,
            loaderMessage: 'Loading form activation by id ...'
          })
        );
      }
    });

    this.formActivationFormControl.valueChanges.subscribe((form: FormVO) => {
      if (form.formName) {
        let activationName = `${form.formName}`;
        if (this.formActivationPeriod?.periodName) {
          activationName = `${form.formName}: ${this.formActivationPeriod?.periodName}`;
        }

        activationName = `${activationName} Activation`;

        this.formActivationActivationNameControl.patchValue(activationName);
      }
    });

    this.formActivationPeriodControl.valueChanges.subscribe((period: PeriodVO) => {
      if (period.periodName) {
        let activationName = `${period.periodName}`;
        if (this.formActivationForm?.formName) {
          activationName = `${this.formActivationForm?.formName}: ${activationName}`;
        }

        activationName = `${activationName} Activation`;
        let end: Date = new Date(period.periodEnd);
        end.setDate(end.getDate() + period.periodConfig.finalDay);
        this.formActivationActivationDeadlineControl.patchValue(this.datePipe.transform(end, 'yyyy-MM-dd'));
        this.formActivationActivationNameControl.patchValue(activationName);
      }
    });

    this.formActivation$.subscribe(formActivation => {
        this.setEditFormActivationFormValue({formActivation});
    });

    // this.store.dispatch(
    //   ViewActions.loadViewAuthorisations({
    //     viewUrl: "/form/activation",
    //     roles: this.keycloakService.getUserRoles(),
    //     loading: true,
    //   })
    // );

    this.unauthorisedUrls$.subscribe(restrictedItems => {
      restrictedItems.forEach(item => {
        if(item === '/form/activation/edit-form-activation/{button:delete}') {
          this.deleteUnrestricted = false;
        }
      });
    });
  }

  override handleFormChanges(change: any): void {
  }

  override beforeEditFormActivationDelete(form: EditFormActivationDeleteForm): void {

    if(form?.formActivation?.id && confirm('Are you sure you want to delete the form activation?')) {

      this.store.dispatch(
        FormActivationActions.remove({
          id: form.formActivation.id,
          loading: true,
          loaderMessage: 'Removing form activations ...'
        })
      );
      this.editFormActivationFormReset();
    }else {
      this.store.dispatch(FormActivationActions.formActivationFailure({ messages: ['Please select something to delete'] }));
    }
  }

  override formActivationFormSearch(): void {
    this.store.dispatch(FormActions.searchForms({ criteria: {formName: this.formActivationFormSearchField.value}, loading: true, loaderMessage: 'Searching forms ...' }));
  }

  override formActivationPeriodSearch(): void {
    let criteria: PeriodCriteria = new PeriodCriteria();
    criteria.periodName = this.formActivationPeriodSearchField.value;

    this.store.dispatch(PeriodActions.search({ criteria, loading: true, loaderMessage: 'Searching periods ...' }));
  }

  override beforeEditFormActivationSave(form: EditFormActivationSaveForm): void {
    if (this.formActivationControl.valid) {
      if (form.formActivation.id) {
        form.formActivation.updatedBy = this.keycloakService.getUsername();
        form.formActivation.updatedDate = new Date();
      } else {
        form.formActivation.createdBy = this.keycloakService.getUsername();
        form.formActivation.createdDate = new Date();
      }

      form.formActivation.form = new FormVO();
      form.formActivation.form.id = this.formActivationForm.id;

      form.formActivation.period = new PeriodVO();
      form.formActivation.period.id = this.formActivationPeriod.id;

      this.store.dispatch(
        FormActivationActions.save({
          formActivation: form.formActivation,
          loading: true,
          loaderMessage: 'Saving form activation ...'
        })
      );
    } else {
      let messages: string[] = []
      if(!this.formActivationControl.valid) {
        messages.push("Period has errors, Please fill in the required form fields")
      } 
      if(!this.formActivationActivationNameControl.valid) {
        messages.push("Activation Name is missing")
      }  
      if(!this.formActivationPeriodControl.valid) {
        messages.push("Period is missing!")
      }
      // if(!this.periodPeriodConfigControl.valid) {
      //   messages.push("Period Name missing!")
      // }
      // if(!this.periodPeriodConfigControl.valid) {
      //   messages.push("Repeat Period missing!")
      // }
    this.store.dispatch(FormActivationActions.formActivationFailure({ messages: messages }));
  }
  }

  override createFormSubmissionVOGroup(value: FormSubmissionVO): FormGroup {
      return this.formBuilder.group({
          id: [value?.id],
          createdBy: [value?.createdBy],
          updatedBy: [value?.updatedBy],
          createdDate: [value?.createdDate],
          updatedDate: [value?.updatedDate],
          submittedBy: [value?.submittedBy],
          submissionDate: [value?.submissionDate],
          submissionStatus: [value?.submissionStatus],
          upload: [value?.upload],
          form: {
            id: value?.form?.id,
            code: value?.form?.code,
            formName: value?.form?.formName,
            entryType: value?.form?.entryType,
          },
          period: {
            id: value?.period?.id,
            periodName: value?.period?.periodName,
            periodStart: value?.period?.periodStart,
            periodEnd: value?.period?.periodEnd,
          },
          licensee: {
            id: value?.licensee?.id,
            uin: value?.licensee?.uin,
            licenseeName: value?.licensee?.licenseeName,
            status: value?.licensee?.status,
          }
      });
  }

  override createPeriodVOGroup(value: PeriodVO): FormGroup {
    
    return this.formBuilder.group({
        id: [value?.id],
        periodName: [value?.periodName],
        periodStart: [value?.periodStart],
        periodEnd: [value?.periodEnd],
        createdBy: [value?.createdBy],
        updatedBy: [value?.updatedBy],
        createdDate: [value?.createdDate],
        updatedDate: [value?.updatedDate],
        periodConfig: {
          id: [value?.periodConfig?.id],
          finalDay: [value?.periodConfig?.finalDay],
          periodConfigName: [value?.periodConfig?.periodConfigName],
        }
    });
  }

  override editFormActivationFormReset() {

    this.store.dispatch(FormActivationActions.formActivationReset());
    this.formActivationFormSubmissionsControl.clear()
    this.editFormActivationForm.reset()
    this.editFormActivationForm.markAsPristine();

    if(this.router.url.substring(0, this.router.url.indexOf('?'))) {
        this.router.navigate([this.router.url.substring(0, this.router.url.indexOf('?'))]);
    } else {
        this.router.navigate([this.router.url]);
    }
}
}
