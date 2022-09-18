// Generated by andromda-angular cartridge (view\view.component.ts.vsl) DO NOT EDIT
import { 
    Component, 
    OnInit, 
    Injector, 
    ViewChild, 
    Input, 
    Output, 
    EventEmitter, 
    AfterViewInit,
    OnDestroy,
} from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { 
    FormGroup, 
    FormControl, 
    FormArray, 
    FormBuilder, 
    ReactiveFormsModule, 
    Validators 
} from '@angular/forms';
import { formatDate } from '@angular/common';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatSelectChange } from '@angular/material/select';
import { MatRadioChange } from '@angular/material/radio';
import { MatCheckboxChange } from '@angular/material/checkbox';
import { UseCaseScope } from '@app/utils/use-case-scope';
import { SelectItem } from '@app/utils/select-item';
import { Store, select } from '@ngrx/store';
import { Observable } from 'rxjs';
import { MatDialog } from '@angular/material/dialog';
import { DataProcessingState } from '@app/store/form/processing/data-processing.state';
import * as DataProcessingSelectors from '@app/store/form/processing/data-processing.selectors';
import * as DataProcessingActions from '@app/store/form/processing/data-processing.actions';

import { FormSubmissionVO } from '@app/model/bw/org/bocra/portal/form/submission/form-submission-vo';
import { SubmissionRestController } from '@app/service/bw/org/bocra/portal/form/submission/submission-rest-controller';
import { SubmissionProcessingImpl } from '@app/controller/form/processing/submission-processing.impl';
import { SubmissionDataComponentImpl } from '@app/view/form/processing/submission-data.component.impl';
import { MatDialogConfig } from '@angular/material/dialog';

export class DataCaptureProcessingNewSubmissionForm {
    submissions: Array<FormSubmissionVO>[] | any[];
}

export class DataCaptureProcessingDraftsForm {
    submissions: Array<FormSubmissionVO>[] | any[];
}

export class DataCaptureProcessingMySubmissionsForm {
    submissions: Array<FormSubmissionVO>[] | any[];
}

export class DataCaptureProcessingAllSubmissionsForm {
    submissions: Array<FormSubmissionVO>[] | any[];
}

export class DataCaptureProcessingOverdueSubmissionsForm {
    submissions: Array<FormSubmissionVO>[] | any[];
}

export class DataCaptureProcessingReturnedSubmissionsForm {
    submissions: Array<FormSubmissionVO>[] | any[];
}

export class DataCaptureProcessingAcceptedSubmissionsForm {
  formSubmissions: Array<FormSubmissionVO>[] | any[];
  id: number | any;
}

@Component({
  selector: 'app-data-capture-processing-base',
  template: ''
})
export abstract class DataCaptureProcessingComponent implements OnInit, AfterViewInit, OnDestroy {

	dataCaptureProcessingForm: FormGroup | any;
    hide: boolean = false;
    protected route: ActivatedRoute;
    protected router: Router;
    protected formBuilder: FormBuilder;
    protected _injector: Injector;
    protected useCaseScope: UseCaseScope;
    protected store: Store<DataProcessingState>;
    dialog: MatDialog;
    submissionProcessing: SubmissionProcessingImpl;
    submissionRestController: SubmissionRestController;
    messages: Observable<any>;
    success: Observable<boolean>;
    loading: Observable<boolean>;
    loaderMessage: Observable<string>;
    error: Observable<boolean>;
    selected: any = null;

    constructor(injector: Injector) {
        
        this.route = injector.get(ActivatedRoute);
        this.router = injector.get(Router);
        this.formBuilder = injector.get(FormBuilder);
        this.useCaseScope = injector.get(UseCaseScope);
        this.store = injector.get(Store);
        this.dialog = injector.get(MatDialog);
        this.submissionProcessing = injector.get(SubmissionProcessingImpl);
        this.submissionRestController = injector.get(SubmissionRestController);
        this._injector = injector;
        this.loading = this.store.pipe(select(DataProcessingSelectors.selectLoading));
        this.loaderMessage = this.store.pipe(select(DataProcessingSelectors.selectLoaderMessage));
        this.success = this.store.pipe(select(DataProcessingSelectors.selectSuccess));
        this.error = this.store.pipe(select(DataProcessingSelectors.selectError));
        this.messages = this.store.pipe(select(DataProcessingSelectors.selectMessages));
    }

    abstract beforeOnInit(): void;
	
    ngOnInit() {
        this.beforeOnInit();
        this.dataCaptureProcessingForm = this.newForm();

        this.dataCaptureProcessingForm.valueChanges.subscribe(
            (change: any) => {
                this.handleFormChanges(change);
            }
        );
        
        this.afterOnInit();
    }

    handleFormChanges(change: any): void {
        
    }

    dataCaptureProcessingFormReset() {

        this.store.dispatch(DataProcessingActions.dataProcessingReset());
        this.dataCaptureProcessingForm.reset()
        this.dataCaptureProcessingForm.markAsPristine();

        if(this.router.url.substring(0, this.router.url.indexOf('?'))) {
            this.router.navigate([this.router.url.substring(0, this.router.url.indexOf('?'))]);
        } else {
            this.router.navigate([this.router.url]);
        }
    }

    afterOnInit(): void {}
    
    doNgAfterViewInit(): void {}

    ngAfterViewInit() {
        this.doNgAfterViewInit();
        this.submissionProcessing.resetUseCaseScope();
    }

    newForm(): FormGroup {
        return this.formBuilder.group({
        });
    }

    abstract doNgOnDestroy(): void;

    ngOnDestroy() { 
        this.doNgOnDestroy();
        this.store.dispatch(DataProcessingActions.dataProcessingReset());
    }

    get dataCaptureProcessingNewSubmissionForm(): DataCaptureProcessingNewSubmissionForm {

        let form: DataCaptureProcessingNewSubmissionForm = new DataCaptureProcessingNewSubmissionForm();

        return form;
    }

    /**
     * This method may be overwritten
     */
    beforeDataCaptureProcessingNewSubmission(form: DataCaptureProcessingNewSubmissionForm): void {}

    /**
     * This method may be overwritten
     */
    getDataCaptureProcessingNewSubmissionFormDialogConfig(data: any): any{
        return {
            data: {
                width: '800px'
            }
        };
    }

    /**
     * This method may be overwritten
     */
    afterDataCaptureProcessingNewSubmission(form: DataCaptureProcessingNewSubmissionForm, dialogData: any): void {
        
    }

    dataCaptureProcessingNewSubmission(): void {
        let form: DataCaptureProcessingNewSubmissionForm = this.dataCaptureProcessingNewSubmissionForm;
        this.beforeDataCaptureProcessingNewSubmission(form);

        this.submissionProcessing.dataCaptureProcessingNewSubmission(form);
        let dialogConfig = this.getDataCaptureProcessingNewSubmissionFormDialogConfig(form);
        const dialogRef = this.dialog.open(SubmissionDataComponentImpl, dialogConfig);

        dialogRef.afterClosed().subscribe((result) => {
            this.afterDataCaptureProcessingNewSubmission(form, result?.dialogData);
        });
    }

    getDataCaptureProcessingNewSubmissionForm(value: any): DataCaptureProcessingNewSubmissionForm {
        
        let form: DataCaptureProcessingNewSubmissionForm = new DataCaptureProcessingNewSubmissionForm();

        return form;

    }

    get dataCaptureProcessingDraftsForm(): DataCaptureProcessingDraftsForm {

        let form: DataCaptureProcessingDraftsForm = new DataCaptureProcessingDraftsForm();

        return form;
    }

    /**
     * This method may be overwritten
     */
    beforeDataCaptureProcessingDrafts(form: DataCaptureProcessingDraftsForm): void {}

    /**
     * This method may be overwritten
     */
    getDataCaptureProcessingDraftsFormDialogConfig(data: any): any{
        return {
            data: {
                width: '800px'
            }
        };
    }

    /**
     * This method may be overwritten
     */
    afterDataCaptureProcessingDrafts(form: DataCaptureProcessingDraftsForm, dialogData: any): void {
        
    }

    dataCaptureProcessingDrafts(): void {
        let form: DataCaptureProcessingDraftsForm = this.dataCaptureProcessingDraftsForm;
        this.beforeDataCaptureProcessingDrafts(form);

        this.submissionProcessing.dataCaptureProcessingDrafts(form);
        let dialogConfig = this.getDataCaptureProcessingDraftsFormDialogConfig(form);
        const dialogRef = this.dialog.open(SubmissionDataComponentImpl, dialogConfig);

        dialogRef.afterClosed().subscribe((result) => {
            this.afterDataCaptureProcessingDrafts(form, result?.dialogData);
        });
    }

    getDataCaptureProcessingDraftsForm(value: any): DataCaptureProcessingDraftsForm {
        
        let form: DataCaptureProcessingDraftsForm = new DataCaptureProcessingDraftsForm();

        return form;

    }

    get dataCaptureProcessingMySubmissionsForm(): DataCaptureProcessingMySubmissionsForm {

        let form: DataCaptureProcessingMySubmissionsForm = new DataCaptureProcessingMySubmissionsForm();

        return form;
    }

    /**
     * This method may be overwritten
     */
    beforeDataCaptureProcessingMySubmissions(form: DataCaptureProcessingMySubmissionsForm): void {}

    /**
     * This method may be overwritten
     */
    getDataCaptureProcessingMySubmissionsFormDialogConfig(data: any): any{
        return {
            data: {
                width: '800px'
            }
        };
    }

    /**
     * This method may be overwritten
     */
    afterDataCaptureProcessingMySubmissions(form: DataCaptureProcessingMySubmissionsForm, dialogData: any): void {
        
    }

    dataCaptureProcessingMySubmissions(): void {
        let form: DataCaptureProcessingMySubmissionsForm = this.dataCaptureProcessingMySubmissionsForm;
        this.beforeDataCaptureProcessingMySubmissions(form);

        this.submissionProcessing.dataCaptureProcessingMySubmissions(form);
        let dialogConfig = this.getDataCaptureProcessingMySubmissionsFormDialogConfig(form);
        const dialogRef = this.dialog.open(SubmissionDataComponentImpl, dialogConfig);

        dialogRef.afterClosed().subscribe((result) => {
            this.afterDataCaptureProcessingMySubmissions(form, result?.dialogData);
        });
    }

    getDataCaptureProcessingMySubmissionsForm(value: any): DataCaptureProcessingMySubmissionsForm {
        
        let form: DataCaptureProcessingMySubmissionsForm = new DataCaptureProcessingMySubmissionsForm();

        return form;

    }

    get dataCaptureProcessingAllSubmissionsForm(): DataCaptureProcessingAllSubmissionsForm {

        let form: DataCaptureProcessingAllSubmissionsForm = new DataCaptureProcessingAllSubmissionsForm();

        return form;
    }

    /**
     * This method may be overwritten
     */
    beforeDataCaptureProcessingAllSubmissions(form: DataCaptureProcessingAllSubmissionsForm): void {}

    /**
     * This method may be overwritten
     */
    getDataCaptureProcessingAllSubmissionsFormDialogConfig(data: any): any{
        return {
            data: {
                width: '800px'
            }
        };
    }

    /**
     * This method may be overwritten
     */
    afterDataCaptureProcessingAllSubmissions(form: DataCaptureProcessingAllSubmissionsForm, dialogData: any): void {
        
    }

    dataCaptureProcessingAllSubmissions(): void {
        let form: DataCaptureProcessingAllSubmissionsForm = this.dataCaptureProcessingAllSubmissionsForm;
        this.beforeDataCaptureProcessingAllSubmissions(form);

        this.submissionProcessing.dataCaptureProcessingAllSubmissions(form);
        let dialogConfig = this.getDataCaptureProcessingAllSubmissionsFormDialogConfig(form);
        const dialogRef = this.dialog.open(SubmissionDataComponentImpl, dialogConfig);

        dialogRef.afterClosed().subscribe((result) => {
            this.afterDataCaptureProcessingAllSubmissions(form, result?.dialogData);
        });
    }

    getDataCaptureProcessingAllSubmissionsForm(value: any): DataCaptureProcessingAllSubmissionsForm {
        
        let form: DataCaptureProcessingAllSubmissionsForm = new DataCaptureProcessingAllSubmissionsForm();

        return form;

    }

    get dataCaptureProcessingOverdueSubmissionsForm(): DataCaptureProcessingOverdueSubmissionsForm {

        let form: DataCaptureProcessingOverdueSubmissionsForm = new DataCaptureProcessingOverdueSubmissionsForm();

        return form;
    }

    /**
     * This method may be overwritten
     */
    beforeDataCaptureProcessingOverdueSubmissions(form: DataCaptureProcessingOverdueSubmissionsForm): void {}

    /**
     * This method may be overwritten
     */
    getDataCaptureProcessingOverdueSubmissionsFormDialogConfig(data: any): any{
        return {
            data: {
                width: '800px'
            }
        };
    }

    /**
     * This method may be overwritten
     */
    afterDataCaptureProcessingOverdueSubmissions(form: DataCaptureProcessingOverdueSubmissionsForm, dialogData: any): void {
        
    }

    dataCaptureProcessingOverdueSubmissions(): void {
        let form: DataCaptureProcessingOverdueSubmissionsForm = this.dataCaptureProcessingOverdueSubmissionsForm;
        this.beforeDataCaptureProcessingOverdueSubmissions(form);

        this.submissionProcessing.dataCaptureProcessingOverdueSubmissions(form);
        let dialogConfig = this.getDataCaptureProcessingOverdueSubmissionsFormDialogConfig(form);
        const dialogRef = this.dialog.open(SubmissionDataComponentImpl, dialogConfig);

        dialogRef.afterClosed().subscribe((result) => {
            this.afterDataCaptureProcessingOverdueSubmissions(form, result?.dialogData);
        });
    }

    getDataCaptureProcessingOverdueSubmissionsForm(value: any): DataCaptureProcessingOverdueSubmissionsForm {
        
        let form: DataCaptureProcessingOverdueSubmissionsForm = new DataCaptureProcessingOverdueSubmissionsForm();

        return form;

    }

    get dataCaptureProcessingReturnedSubmissionsForm(): DataCaptureProcessingReturnedSubmissionsForm {

        let form: DataCaptureProcessingReturnedSubmissionsForm = new DataCaptureProcessingReturnedSubmissionsForm();

        return form;
    }

    /**
     * This method may be overwritten
     */
    beforeDataCaptureProcessingReturnedSubmissions(form: DataCaptureProcessingReturnedSubmissionsForm): void {}

    /**
     * This method may be overwritten
     */
    getDataCaptureProcessingReturnedSubmissionsFormDialogConfig(data: any): any{
        return {
            data: {
                width: '800px'
            }
        };
    }

    /**
     * This method may be overwritten
     */
    afterDataCaptureProcessingReturnedSubmissions(form: DataCaptureProcessingReturnedSubmissionsForm, dialogData: any): void {
        
    }

    dataCaptureProcessingReturnedSubmissions(): void {
        let form: DataCaptureProcessingReturnedSubmissionsForm = this.dataCaptureProcessingReturnedSubmissionsForm;
        this.beforeDataCaptureProcessingReturnedSubmissions(form);

        this.submissionProcessing.dataCaptureProcessingReturnedSubmissions(form);
        let dialogConfig = this.getDataCaptureProcessingReturnedSubmissionsFormDialogConfig(form);
        const dialogRef = this.dialog.open(SubmissionDataComponentImpl, dialogConfig);

        dialogRef.afterClosed().subscribe((result) => {
            this.afterDataCaptureProcessingReturnedSubmissions(form, result?.dialogData);
        });
    }

    getDataCaptureProcessingReturnedSubmissionsForm(value: any): DataCaptureProcessingReturnedSubmissionsForm {
        
        let form: DataCaptureProcessingReturnedSubmissionsForm = new DataCaptureProcessingReturnedSubmissionsForm();

        return form;

    }

    getItemControl(name: string): FormControl {
        return this.dataCaptureProcessingForm.get(name) as FormControl;
    }

    getGroupControl(name: string): FormGroup {
        return this.dataCaptureProcessingForm.get(name) as FormGroup;
    }

    getArrayControl(name: string): FormArray {
        return this.dataCaptureProcessingForm.get(name) as FormArray;
    }

    setDataCaptureProcessingFormValue(form: any) {
    }

    createFormSubmissionVOGroup(value: FormSubmissionVO): FormGroup {
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
        });
    }

    createFormSubmissionVOArray(values: FormSubmissionVO[]): FormArray {
        if(values) {
            let formArray: FormArray = this.formBuilder.array([]);
            values?.forEach(value => formArray.push(this.createFormSubmissionVOGroup(value)))

            return formArray;
        } else {
            return new FormArray([]);
        }
    }


    get dataCaptureProcessingAcceptedSubmissionsForm(): DataCaptureProcessingAcceptedSubmissionsForm {

      let form: DataCaptureProcessingAcceptedSubmissionsForm = new DataCaptureProcessingAcceptedSubmissionsForm();

      return form;
  }

  /**
   * This method may be overwritten
   */
  beforeDataCaptureProcessingAcceptedSubmissions(form: DataCaptureProcessingAcceptedSubmissionsForm): void {}

  /**
   * This method may be overwritten
   */
  getDataCaptureProcessingAcceptedSubmissionsFormDialogConfig(data: any): any{
      return {
          data: {
              width: '800px'
          }
      };
  }

  /**
   * This method may be overwritten
   */
  afterDataCaptureProcessingAcceptedSubmissions(form: DataCaptureProcessingAcceptedSubmissionsForm, dialogData: any): void {
      
  }

  dataCaptureProcessingAcceptedSubmissions(): void {
      let form: DataCaptureProcessingAcceptedSubmissionsForm = this.dataCaptureProcessingAcceptedSubmissionsForm;
      this.beforeDataCaptureProcessingAcceptedSubmissions(form);

      this.submissionProcessing.dataCaptureProcessingAcceptedSubmissions(form);
      let dialogConfig = this.getDataCaptureProcessingAcceptedSubmissionsFormDialogConfig(form);
      const dialogRef = this.dialog.open(SubmissionDataComponentImpl, dialogConfig);

      dialogRef.afterClosed().subscribe((result) => {
          this.afterDataCaptureProcessingAcceptedSubmissions(form, result?.dialogData);
      });
  }

  getDataCaptureProcessingAcceptedSubmissionsForm(value: any): DataCaptureProcessingAcceptedSubmissionsForm {
      
      let form: DataCaptureProcessingAcceptedSubmissionsForm = new DataCaptureProcessingAcceptedSubmissionsForm();

      return form;

  }

}
