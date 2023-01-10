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
import { MatTableDataSource } from '@angular/material/table';
import { UseCaseScope } from '@app/utils/use-case-scope';
import { SelectItem } from '@app/utils/select-item';
import { Store, select } from '@ngrx/store';
import { Observable, of } from 'rxjs';
import { MatDialog } from '@angular/material/dialog';
import { ComplaintState } from '@app/store/complaint/complaint.state';
import * as ComplaintSelectors from '@app/store/complaint/complaint.selectors';
import * as ComplaintActions from '@app/store/complaint/complaint.actions';

import { ComplaintControllerImpl } from '@app/controller/complaint/complaint-controller.impl';
import { SearchComplaintsComplaintsComponentImpl } from '@app/view/complaint/search-complaints-complaints.component.impl';
import { ComplaintStatus } from '@app/model/bw/org/bocra/portal/complaint/complaint-status';
import { ComplaintTypeVO } from '@app/model/bw/org/bocra/portal/complaint/type/complaint-type-vo';
import { DocumentVO } from '@app/model/bw/org/bocra/portal/document/document-vo';
import { ComplaintVO } from '@app/model/bw/org/bocra/portal/complaint/complaint-vo';
import { ComplaintSeachCriteria } from '@app/model/bw/org/bocra/portal/complaint/complaint-seach-criteria';
import { ComplaintRestController } from '@app/service/bw/org/bocra/portal/complaint/complaint-rest-controller';
import { ComplaintReplyVO } from '@app/model/bw/org/bocra/portal/complaint/complaint-reply-vo';
import { LicenseeVO } from '@app/model/bw/org/bocra/portal/licensee/licensee-vo';
import { KeycloakService } from 'keycloak-angular';

export class SearchComplaintsSearchForm {
    complaints: Array<ComplaintVO>[] | any[];
    criteria: String | any;
    loggedInSearch: ComplaintSeachCriteria | any;
    id: number | any;
}

export class SearchComplaintsCreateForm {
    complaint: ComplaintVO | any;
    id: number | any;
}

export class SearchComplaintsAnalyseForm {
}
export class SearchComplaintsVarsForm {
    criteria: String | any;
    loggedInSearch: ComplaintSeachCriteria | any;
}

@Component({
  selector: 'app-search-complaints-base',
  template: ''
})
export abstract class SearchComplaintsComponent implements OnInit, AfterViewInit, OnDestroy {

    isLoggedIn: Observable<boolean> = of(false);
	searchComplaintsForm: FormGroup | any;
    hide: boolean = false;
    protected route: ActivatedRoute;
    protected router: Router;
    protected formBuilder: FormBuilder;
    protected _injector: Injector;
    protected useCaseScope: UseCaseScope;
    protected store: Store<ComplaintState>;
    dialog: MatDialog;
    complaintController: ComplaintControllerImpl;
    complaintRestController: ComplaintRestController;
    complaintsDocumentsColumns = [
        'id',
        'documentId',
        'documentType.name',
        'documentName',
    ];

    complaintsDocumentsModalColumns = [
        'actions',
        ...this.complaintsDocumentsColumns
    ];

    @ViewChild('complaintsDocumentsPaginator', {static: true}) complaintsDocumentsPaginator: MatPaginator;
    @ViewChild('complaintsDocumentsSort', {static: true}) complaintsDocumentsSort: MatSort;

    @ViewChild('complaintsDocumentsModalPaginator', {static: true}) complaintsDocumentsModalPaginator: MatPaginator;
    @ViewChild('complaintsDocumentsModalSort', {static: true}) complaintsDocumentsModalSort: MatSort;

    complaintsDocuments$: Observable<DocumentVO[]>;
    complaintsDocumentsDataSource = new MatTableDataSource<DocumentVO>([]);
    complaintsDocumentsSearchField: FormControl;
    complaintsDocumentsSelect: DocumentVO[] = [];

    complaintsComplaintRepliesColumns = [
        'id',
        'date',
        'replyUser',
    ];

    complaintsComplaintRepliesModalColumns = [
        'actions',
        ...this.complaintsComplaintRepliesColumns
    ];

    @ViewChild('complaintsComplaintRepliesPaginator', {static: true}) complaintsComplaintRepliesPaginator: MatPaginator;
    @ViewChild('complaintsComplaintRepliesSort', {static: true}) complaintsComplaintRepliesSort: MatSort;

    @ViewChild('complaintsComplaintRepliesModalPaginator', {static: true}) complaintsComplaintRepliesModalPaginator: MatPaginator;
    @ViewChild('complaintsComplaintRepliesModalSort', {static: true}) complaintsComplaintRepliesModalSort: MatSort;

    complaintsComplaintReplies$: Observable<ComplaintReplyVO[]>;
    complaintsComplaintRepliesDataSource = new MatTableDataSource<ComplaintReplyVO>([]);
    complaintsComplaintRepliesSearchField: FormControl;
    complaintsComplaintRepliesSelect: ComplaintReplyVO[] = [];

    @ViewChild('complaintsComplaintTypeModalPaginator', {static: true}) complaintsComplaintTypeModalPaginator: MatPaginator;
    @ViewChild('complaintsComplaintTypeModalSort', {static: true}) complaintsComplaintTypeModalSort: MatSort;
    
    complaintsComplaintTypes$: Observable<Array<ComplaintTypeVO>>;
    complaintsComplaintTypesDataSource = new MatTableDataSource<ComplaintTypeVO>([]);
    complaintsComplaintTypeSelect: ComplaintTypeVO = new ComplaintTypeVO();
    complaintsComplaintTypeSearchField: FormControl = new FormControl();

    complaintsComplaintTypeModalColumns = [
        'actions',
        'id',
        'code',
        'typeName',
    ];

    @ViewChild('complaintsLicenseeModalPaginator', {static: true}) complaintsLicenseeModalPaginator: MatPaginator;
    @ViewChild('complaintsLicenseeModalSort', {static: true}) complaintsLicenseeModalSort: MatSort;
    
    complaintsLicensees$: Observable<Array<LicenseeVO>>;
    complaintsLicenseesDataSource = new MatTableDataSource<LicenseeVO>([]);
    complaintsLicenseeSelect: LicenseeVO = new LicenseeVO();
    complaintsLicenseeSearchField: FormControl = new FormControl();

    complaintsLicenseeModalColumns = [
        'actions',
        'id',
        'uin',
        'licenseeName',
    ];

    statusT = ComplaintStatus;
    statusOptions: string[] = Object.keys(this.statusT);
    loggedInSearch$: Observable<ComplaintSeachCriteria>;
    criteria$: Observable<String>;
    messages: Observable<any>;
    success: Observable<boolean>;
    loading: Observable<boolean>;
    loaderMessage: Observable<string>;
    error: Observable<boolean>;
    selected: any = null;

    constructor(injector: Injector, public keycloakService: KeycloakService) {
        
        this.route = injector.get(ActivatedRoute);
        this.router = injector.get(Router);
        this.formBuilder = injector.get(FormBuilder);
        this.useCaseScope = injector.get(UseCaseScope);
        this.store = injector.get(Store);
        this.dialog = injector.get(MatDialog);
        this.complaintController = injector.get(ComplaintControllerImpl);
        this.complaintRestController = injector.get(ComplaintRestController);
        this._injector = injector;
        this.loggedInSearch$ = this.store.pipe(select(ComplaintSelectors.selectLoggedInSearch));
        this.criteria$ = this.store.pipe(select(ComplaintSelectors.selectCriteria));
        this.loading = this.store.pipe(select(ComplaintSelectors.selectLoading));
        this.loaderMessage = this.store.pipe(select(ComplaintSelectors.selectLoaderMessage));
        this.success = this.store.pipe(select(ComplaintSelectors.selectSuccess));
        this.error = this.store.pipe(select(ComplaintSelectors.selectError));
        this.messages = this.store.pipe(select(ComplaintSelectors.selectMessages));
        this.complaintsDocumentsSearchField = new FormControl();
        this.complaintsComplaintRepliesSearchField = new FormControl();
    }

    beforeOnInit(form: SearchComplaintsVarsForm): SearchComplaintsVarsForm {
        return form;
    }
	
    ngOnInit() {
        let form: SearchComplaintsVarsForm = this.beforeOnInit(new SearchComplaintsVarsForm);
        this.searchComplaintsForm = this.newForm(form);

        this.searchComplaintsForm.valueChanges.subscribe(
            (change: any) => {
                this.handleFormChanges(change);
            }
        );

        this.complaintsDocuments$?.subscribe(data => {
            this.complaintsDocumentsDataSource.data = data;
            this.complaintsDocumentsDataSource.paginator = this.complaintsDocumentsModalPaginator;
            this.complaintsDocumentsDataSource.sort = this.complaintsDocumentsModalSort;
        });

        this.complaintsComplaintReplies$?.subscribe(data => {
            this.complaintsComplaintRepliesDataSource.data = data;
            this.complaintsComplaintRepliesDataSource.paginator = this.complaintsComplaintRepliesModalPaginator;
            this.complaintsComplaintRepliesDataSource.sort = this.complaintsComplaintRepliesModalSort;
        });

        this.complaintsComplaintTypes$?.subscribe(data => {
            this.complaintsComplaintTypesDataSource.data = data;
            this.complaintsComplaintTypesDataSource.paginator = this.complaintsComplaintTypeModalPaginator;
            this.complaintsComplaintTypesDataSource.sort = this.complaintsComplaintTypeModalSort;
        });

        this.complaintsLicensees$?.subscribe(data => {
            this.complaintsLicenseesDataSource.data = data;
            this.complaintsLicenseesDataSource.paginator = this.complaintsLicenseeModalPaginator;
            this.complaintsLicenseesDataSource.sort = this.complaintsLicenseeModalSort;
        });

        this.afterOnInit();
    }

    handleFormChanges(change: any): void {
        
    }

    searchComplaintsFormReset() {

        this.store.dispatch(ComplaintActions.complaintReset());

        this.searchComplaintsForm.reset()
        this.searchComplaintsForm.markAsPristine();

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
        this.complaintController.resetUseCaseScope();
        this.keycloakService.isLoggedIn().then((loggedIn) => {
            if (loggedIn) {
                this.isLoggedIn = of(loggedIn);
            }
        });
    }

    newForm(searchComplaintsVarsForm$: SearchComplaintsVarsForm): FormGroup {
        return this.formBuilder.group({
            criteria: [{value: searchComplaintsVarsForm$?.criteria, disabled: false}, [Validators.required, ]],
            loggedInSearch: this.createLoggedInSearchForm(searchComplaintsVarsForm$?.loggedInSearch),
            complaints: this.formBuilder.array([
                this.formBuilder.group({
                    id: [''],
                    status: [''],
                    firstName: [''],
                    surname: [''],
                    createdDate: [''],
                })
            ]),
        });
    }

    abstract doNgOnDestroy(): void;

    ngOnDestroy() { 
        this.doNgOnDestroy();
        this.store.dispatch(ComplaintActions.complaintReset());
    }

    get searchComplaintsSearchForm(): SearchComplaintsSearchForm {

        let form: SearchComplaintsSearchForm = new SearchComplaintsSearchForm();
        form.criteria = this.criteria;
        form.loggedInSearch = this.loggedInSearch;

        return form;
    }

    /**
     * This method may be overwritten
     */
    beforeSearchComplaintsSearch(form: SearchComplaintsSearchForm): void {}

    /**
     * This method may be overwritten
     */
    afterSearchComplaintsSearch(form: SearchComplaintsSearchForm): void {}

    searchComplaintsSearch(): void {
        let form: SearchComplaintsSearchForm = this.searchComplaintsSearchForm;
        this.beforeSearchComplaintsSearch(form);

        this.complaintController.searchComplaintsSearch(form);
        this.afterSearchComplaintsSearch(form);
    }

    getSearchComplaintsSearchForm(value: any): SearchComplaintsSearchForm {
        
        let form: SearchComplaintsSearchForm = new SearchComplaintsSearchForm();

        if(value?.criteria) {
            form.criteria = value?.criteria;
        }

        if(value?.loggedInSearch) {
            form.loggedInSearch = value?.loggedInSearch;
        }

        return form;

    }

    get searchComplaintsCreateForm(): SearchComplaintsCreateForm {

        let form: SearchComplaintsCreateForm = new SearchComplaintsCreateForm();

        return form;
    }

    /**
     * This method may be overwritten
     */
    beforeSearchComplaintsCreate(form: SearchComplaintsCreateForm): void {}

    /**
     * This method may be overwritten
     */
    afterSearchComplaintsCreate(form: SearchComplaintsCreateForm): void {}

    searchComplaintsCreate(): void {
        let form: SearchComplaintsCreateForm = this.searchComplaintsCreateForm;
        this.beforeSearchComplaintsCreate(form);

        this.complaintController.searchComplaintsCreate(form);
        this.afterSearchComplaintsCreate(form);
    }

    getSearchComplaintsCreateForm(value: any): SearchComplaintsCreateForm {
        
        let form: SearchComplaintsCreateForm = new SearchComplaintsCreateForm();

        if(value?.id) {
            form.id = value?.id;
        }

        return form;

    }

    get searchComplaintsAnalyseForm(): SearchComplaintsAnalyseForm {

        let form: SearchComplaintsAnalyseForm = new SearchComplaintsAnalyseForm();

        return form;
    }

    /**
     * This method may be overwritten
     */
    beforeSearchComplaintsAnalyse(): void {}

    /**
     * This method may be overwritten
     */
    afterSearchComplaintsAnalyse(): void {}

    searchComplaintsAnalyse(): void {
        this.beforeSearchComplaintsAnalyse();

        this.afterSearchComplaintsAnalyse();
    }


    get searchComplaintsVarsFormControl(): FormGroup {
        return this.getGroupControl('searchComplaintsVarsForm');
    }

    /**
     * This method may be overwritten
     */
    afterSetSearchComplaintsVarsForm(form: SearchComplaintsVarsForm): void {}

    setSearchComplaintsVarsForm(form: SearchComplaintsVarsForm) {

        this.searchComplaintsVarsFormControl.get('criteria').setValue(form.criteria);
        this.searchComplaintsVarsFormControl.setControl('loggedInSearch', this.createLoggedInSearchForm(form.loggedInSearch));

        this.afterSetSearchComplaintsVarsForm(form);
    }

    get criteriaControl(): FormControl {
        return this.getItemControl('criteria') as FormControl;
    }

    get criteria(): String {
        return this.criteriaControl.value;
    }

    createLoggedInSearchForm(loggedInSearch: ComplaintSeachCriteria): FormGroup {
        return this.formBuilder.group({
            status: [{value: loggedInSearch?.status, disabled: false}],
            surname: [{value: loggedInSearch?.surname, disabled: false}],
            email: [{value: loggedInSearch?.email, disabled: false}],
            subject: [{value: loggedInSearch?.subject, disabled: false}],
            complaintId: [{value: loggedInSearch?.complaintId, disabled: false}],
            licenseeName: [{value: loggedInSearch?.licenseeName, disabled: false}],
            complaintType: [{value: loggedInSearch?.complaintType, disabled: false}],
        });
    }

    get loggedInSearchControl(): FormGroup {
        return this.getGroupControl('loggedInSearch') as FormGroup;
    }

    get loggedInSearch(): ComplaintSeachCriteria {
        return this.loggedInSearchControl.value;
    }

    get loggedInSearchStatusControl(): FormGroup {
        return this.loggedInSearchControl.get('status') as FormGroup;
    }

    get loggedInSearchStatus(): ComplaintStatus {
        return this.loggedInSearchStatusControl.value;
    }

    get loggedInSearchSurnameControl(): FormControl {
        return this.loggedInSearchControl.get('surname') as FormControl;
    }

    get loggedInSearchSurname(): string {
        return this.loggedInSearchSurnameControl.value;
    }

    get loggedInSearchEmailControl(): FormControl {
        return this.loggedInSearchControl.get('email') as FormControl;
    }

    get loggedInSearchEmail(): string {
        return this.loggedInSearchEmailControl.value;
    }

    get loggedInSearchSubjectControl(): FormControl {
        return this.loggedInSearchControl.get('subject') as FormControl;
    }

    get loggedInSearchSubject(): string {
        return this.loggedInSearchSubjectControl.value;
    }

    get loggedInSearchComplaintIdControl(): FormControl {
        return this.loggedInSearchControl.get('complaintId') as FormControl;
    }

    get loggedInSearchComplaintId(): string {
        return this.loggedInSearchComplaintIdControl.value;
    }

    get loggedInSearchLicenseeIdControl(): FormControl {
        return this.loggedInSearchControl.get('licenseeId') as FormControl;
    }

    get loggedInSearchLicenseeId(): number {
        return this.loggedInSearchLicenseeIdControl.value;
    }

    get loggedInSearchComplaintTypeIdControl(): FormControl {
        return this.loggedInSearchControl.get('complaintTypeId') as FormControl;
    }

    get loggedInSearchComplaintTypeId(): number {
        return this.loggedInSearchComplaintTypeIdControl.value;
    }

    getItemControl(name: string): FormControl {
        return this.searchComplaintsForm.get(name) as FormControl;
    }

    getGroupControl(name: string): FormGroup {
        return this.searchComplaintsForm.get(name) as FormGroup;
    }

    getArrayControl(name: string): FormArray {
        return this.searchComplaintsForm.get(name) as FormArray;
    }

    setSearchComplaintsFormValue(form: any) {
        if(form.criteria) {
            this.searchComplaintsForm.get('criteria').setValue(form.criteria);
        }
        if(form.loggedInSearch) {
            this.searchComplaintsForm.setControl('loggedInSearch', this.createLoggedInSearchForm(form.loggedInSearch));
        }
    }

    setComplaints(complaints: Array<ComplaintVO>): FormArray {

        const complaintsArray = new FormArray([]);

        if(!complaints || complaints === undefined || complaints === null) {
            return complaintsArray;
        }

        complaints.forEach((item) => {
            complaintsArray.push(this.createComplaintsGroup(item));
        });

        this.searchComplaintsForm.setControl('complaints', complaintsArray);

        return complaintsArray;
    }

    get complaintsControl(): FormArray {
        return this.getArrayControl('complaints');
    }

    get complaints(): Array<ComplaintVO>[] {
        return this.complaintsControl.value;
    }

    deleteFromComplaints(index: number) {
        this.complaintsControl.removeAt(index);
    }

    addToComplaints(data: any) {
        this.complaintsControl.push(this.createComplaintsGroup(data));
    }

    createComplaintsGroup(data: any): FormGroup {
        return this.formBuilder.group({
            id: [data?.id],
            status: [data?.status],
            firstName: [data?.firstName],
            surname: [data?.surname],
            createdDate: [data?.createdDate.toISOString()],
        });
    }

    addComplaintsDummyData() {
        this.complaintsControl.push(this.formBuilder.group({
            id: [1],
            status: ['status-1'],
            firstName: ['firstName-1'],
            surname: ['surname-1'],
            createdDate: [new Date()],
        }));

        this.complaintsControl.push(this.formBuilder.group({
            id: [2],
            status: ['status-2'],
            firstName: ['firstName-2'],
            surname: ['surname-2'],
            createdDate: [new Date()],
        }));
    }

    createComplaintTypeVOGroup(value: ComplaintTypeVO): FormGroup {
        return this.formBuilder.group({
            id: [value?.id],
            createdBy: [value?.createdBy],
            updatedBy: [value?.updatedBy],
            createdDate: [value?.createdDate],
            updatedDate: [value?.updatedDate],
            code: [value?.code],
            typeName: [value?.typeName],
            description: [value?.description],
        });
    }

    createComplaintTypeVOArray(values: ComplaintTypeVO[]): FormArray {
        if(values) {
            let formArray: FormArray = this.formBuilder.array([]);
            values?.forEach(value => formArray.push(this.createComplaintTypeVOGroup(value)))

            return formArray;
        } else {
            return new FormArray([]);
        }
    }

    createDocumentVOGroup(value: DocumentVO): FormGroup {
        return this.formBuilder.group({
            id: [value?.id],
            createdBy: [value?.createdBy],
            updatedBy: [value?.updatedBy],
            createdDate: [value?.createdDate],
            updatedDate: [value?.updatedDate],
            documentName: [value?.documentName],
            file: [value?.file],
            documentId: [value?.documentId],
            extension: [value?.extension],
            size: [value?.size],
        });
    }

    createDocumentVOArray(values: DocumentVO[]): FormArray {
        if(values) {
            let formArray: FormArray = this.formBuilder.array([]);
            values?.forEach(value => formArray.push(this.createDocumentVOGroup(value)))

            return formArray;
        } else {
            return new FormArray([]);
        }
    }

    createComplaintVOGroup(value: ComplaintVO): FormGroup {
        return this.formBuilder.group({
            id: [value?.id],
            status: [value?.status],
            complaintId: [value?.complaintId],
            firstName: [value?.firstName],
            surname: [value?.surname],
            email: [value?.email],
            subject: [value?.subject],
            details: [value?.details],
            assignedTo: [value?.assignedTo],
            createdDate: [value?.createdDate],
            updatedDate: [value?.updatedDate],
        });
    }

    createComplaintVOArray(values: ComplaintVO[]): FormArray {
        if(values) {
            let formArray: FormArray = this.formBuilder.array([]);
            values?.forEach(value => formArray.push(this.createComplaintVOGroup(value)))

            return formArray;
        } else {
            return new FormArray([]);
        }
    }

    createComplaintSeachCriteriaGroup(value: ComplaintSeachCriteria): FormGroup {
        return this.formBuilder.group({
            status: [value?.status],
            surname: [value?.surname],
            email: [value?.email],
            subject: [value?.subject],
            complaintId: [value?.complaintId],
            licenseeName: [value?.licenseeName],
            complaintType: [value?.complaintType],
        });
    }

    createComplaintSeachCriteriaArray(values: ComplaintSeachCriteria[]): FormArray {
        if(values) {
            let formArray: FormArray = this.formBuilder.array([]);
            values?.forEach(value => formArray.push(this.createComplaintSeachCriteriaGroup(value)))

            return formArray;
        } else {
            return new FormArray([]);
        }
    }

    createComplaintReplyVOGroup(value: ComplaintReplyVO): FormGroup {
        return this.formBuilder.group({
            id: [value?.id],
            date: [value?.date],
            replyUser: [value?.replyUser],
            reply: [value?.reply],
        });
    }

    createComplaintReplyVOArray(values: ComplaintReplyVO[]): FormArray {
        if(values) {
            let formArray: FormArray = this.formBuilder.array([]);
            values?.forEach(value => formArray.push(this.createComplaintReplyVOGroup(value)))

            return formArray;
        } else {
            return new FormArray([]);
        }
    }

    createLicenseeVOGroup(value: LicenseeVO): FormGroup {
        return this.formBuilder.group({
            id: [value?.id],
            createdBy: [value?.createdBy],
            updatedBy: [value?.updatedBy],
            createdDate: [value?.createdDate],
            updatedDate: [value?.updatedDate],
            status: [value?.status],
            uin: [value?.uin],
            licenseeName: [value?.licenseeName],
            alias: [value?.alias],
            address: [value?.address],
        });
    }

    createLicenseeVOArray(values: LicenseeVO[]): FormArray {
        if(values) {
            let formArray: FormArray = this.formBuilder.array([]);
            values?.forEach(value => formArray.push(this.createLicenseeVOGroup(value)))

            return formArray;
        } else {
            return new FormArray([]);
        }
    }

}
