// Generated by andromda-angular cartridge (view\view.component.ts.vsl) DO NOT EDIT
import {
    AfterViewInit, Component, Injector, OnDestroy, OnInit
} from '@angular/core';
import {
    FormArray,
    FormBuilder, FormControl, FormGroup
} from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { UseCaseScope } from '@app/utils/use-case-scope';
import { select, Store } from '@ngrx/store';
import { Observable } from 'rxjs';
import * as SubmissionActions from '@app/store/form/submission/form-submission.actions';
import * as SubmissionSelectors from '@app/store/form/submission/form-submission.selectors';
import { FormSubmissionState } from '@app/store/form/submission/form-submission.state';

import { ReportControllerImpl } from '@app/controller/report/report-controller.impl';
@Component({
  selector: 'app-report-base',
  template: ''
})
export abstract class ReportComponent implements OnInit, AfterViewInit, OnDestroy {

	reportForm: FormGroup | any;
    hide: boolean = false;
    protected route: ActivatedRoute;
    protected router: Router;
    protected formBuilder: FormBuilder;
    protected _injector: Injector;
    protected useCaseScope: UseCaseScope;
    protected store: Store<FormSubmissionState>;
    dialog: MatDialog;
    reportController: ReportControllerImpl;
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
        this.reportController = injector.get(ReportControllerImpl);
        this._injector = injector;
        this.loading = this.store.pipe(select(SubmissionSelectors.selectLoading));
        this.loaderMessage = this.store.pipe(select(SubmissionSelectors.selectLoaderMessage));
        this.success = this.store.pipe(select(SubmissionSelectors.selectSuccess));
        this.error = this.store.pipe(select(SubmissionSelectors.selectError));
        this.messages = this.store.pipe(select(SubmissionSelectors.selectMessages));
    }

    beforeOnInit(): void {
    }
	
    ngOnInit() {
        this.beforeOnInit();
        this.reportForm = this.newForm();

        this.reportForm.valueChanges.subscribe(
            (change: any) => {
                this.handleFormChanges(change);
            }
        );

        this.afterOnInit();
    }

    handleFormChanges(change: any): void {
        
    }

    reportFormReset() {

        this.store.dispatch(SubmissionActions.formSubmissionReset());

        this.reportForm.reset()
        this.reportForm.markAsPristine();

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
        this.reportController.resetUseCaseScope();
    }

    newForm(): FormGroup {
        return this.formBuilder.group({
        });
    }

    abstract doNgOnDestroy(): void;

    ngOnDestroy() { 
        this.doNgOnDestroy();
        this.store.dispatch(SubmissionActions.formSubmissionReset());
    }

    getItemControl(name: string): FormControl {
        return this.reportForm.get(name) as FormControl;
    }

    getGroupControl(name: string): FormGroup {
        return this.reportForm.get(name) as FormGroup;
    }

    getArrayControl(name: string): FormArray {
        return this.reportForm.get(name) as FormArray;
    }

    setReportFormValue(form: any) {
    }

}
