// Generated by andromda-angular cartridge (view\view.component.ts.vsl) DO NOT EDIT
import {
    AfterViewInit, Component, Injector, OnDestroy, OnInit, ViewChild
} from '@angular/core';
import {
    FormArray,
    FormBuilder, FormControl, FormGroup, Validators
} from '@angular/forms';
import { MatCheckboxChange } from '@angular/material/checkbox';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { ActivatedRoute, Router } from '@angular/router';
import * as LicenseeActions from '@app/store/licensee/licensee.actions';
import * as LicenseeSelectors from '@app/store/licensee/licensee.selectors';
import * as FormActions from '@app/store/form/form.actions';
import * as FormSelectors from '@app/store/form/form.selectors';
import { LicenseeState } from '@app/store/licensee/licensee.state';
import * as LicenseeSectorActions from '@app/store/licensee/sector/licensee-sector.actions';
import * as LicenseeSectorSelectors from '@app/store/licensee/sector/licensee-sector.selectors';
import * as LicenseeFormActions from '@app/store/licensee/form/licensee-form.actions';
import * as LicenseeFormSelectors from '@app/store/licensee/form/licensee-form.selectors';
import { UseCaseScope } from '@app/utils/use-case-scope';
import { select, Store } from '@ngrx/store';
import { Observable } from 'rxjs';

import { LicenseeControllerImpl } from '@app/controller/licensee/licensee-controller.impl';
import { DocumentVO } from '@app/model/bw/org/bocra/portal/document/document-vo';
import { LicenceVO } from '@app/model/bw/org/bocra/portal/licence/licence-vo';
import { LicenseeFormVO } from '@app/model/bw/org/bocra/portal/licensee/form/licensee-form-vo';
import { LicenseeCriteria } from '@app/model/bw/org/bocra/portal/licensee/licensee-criteria';
import { LicenseeStatus } from '@app/model/bw/org/bocra/portal/licensee/licensee-status';
import { LicenseeVO } from '@app/model/bw/org/bocra/portal/licensee/licensee-vo';
import { LicenseeSectorVO } from '@app/model/bw/org/bocra/portal/licensee/sector/licensee-sector-vo';
import { ShareholderVO } from '@app/model/bw/org/bocra/portal/licensee/shares/shareholder-vo';
import { SectorVO } from '@app/model/bw/org/bocra/portal/sector/sector-vo';
import { UserVO } from '@app/model/bw/org/bocra/portal/user/user-vo';
import { DocumentRestController } from '@app/service/bw/org/bocra/portal/document/document-rest-controller';
import { LicenseeRestController } from '@app/service/bw/org/bocra/portal/licensee/licensee-rest-controller';
import * as SectorSelectors from '@app/store/sector/sector.selectors';
import { NewDocumentComponentImpl } from '@app/view/licensee/new-document.component.impl';
import { NewShareholderComponentImpl } from '@app/view/licensee/new-shareholder.component.impl';
import { FormVO } from '@app/model/bw/org/bocra/portal/form/form-vo';
import { MatTableDataSource } from '@angular/material/table';

export class EditLicenseeSaveForm {
    licensee: LicenseeVO | any;
}

export class EditLicenseeSearchForm {
    licensees: Array<LicenseeVO>[] | any[];
    criteria: LicenseeCriteria | any;
    id: number | any;
}

export class EditLicenseeDeleteForm {
    licensee: LicenseeVO | any;
}

export class EditLicenseeDocumentsForm {
    licensee: LicenseeVO | any;
}

export class EditLicenseeNewShareholderForm {
    shareholder: ShareholderVO | any;
}

export class EditLicenseeNewDocumentForm {
    document: DocumentVO | any;
}
export class EditLicenseeVarsForm {
    licensee: LicenseeVO | any;
}

@Component({
  selector: 'app-edit-licensee-base',
  template: ''
})
export abstract class EditLicenseeComponent implements OnInit, AfterViewInit, OnDestroy {

	editLicenseeForm: FormGroup | any;
    hide: boolean = false;
    protected route: ActivatedRoute;
    protected router: Router;
    protected formBuilder: FormBuilder;
    protected _injector: Injector;
    protected useCaseScope: UseCaseScope;
    protected store: Store<LicenseeState>;
    dialog: MatDialog;
    licenseeController: LicenseeControllerImpl;
    documentRestController: DocumentRestController;
    licenseeRestController: LicenseeRestController;
    licenseeUsersColumns = [
        'userId',
        'username',
        'email',
        'firstName',
        'lastName',
    ];

    licenseeUsersModalColumns = [
        'actions',
        ...this.licenseeUsersColumns
    ];

    @ViewChild('licenseeUsersPaginator', {static: true}) licenseeUsersPaginator: MatPaginator;
    @ViewChild('licenseeUsersSort', {static: true}) licenseeUsersSort: MatSort;

    @ViewChild('licenseeUsersModalPaginator', {static: true}) licenseeUsersModalPaginator: MatPaginator;
    @ViewChild('licenseeUsersModalSort', {static: true}) licenseeUsersModalSort: MatSort;

    licenseeUsers$: Observable<UserVO[]>;
    licenseeUsersDataSource = new MatTableDataSource<UserVO>([]);
    licenseeUsersSearchField: FormControl;
    licenseeUsersSelect: UserVO[] = [];

    licenseeFormsColumns = [
        'id',
        'code',
        'formName',
    ];

    licenseeFormsModalColumns = [
        'actions',
        'id',
        'code',
        'formName',
    ];

    @ViewChild('licenseeFormsPaginator', {static: true}) licenseeFormsPaginator: MatPaginator;
    @ViewChild('licenseeFormsSort', {static: true}) licenseeFormsSort: MatSort;

    @ViewChild('licenseeFormsModalPaginator', {static: true}) licenseeFormsModalPaginator: MatPaginator;
    @ViewChild('licenseeFormsModalSort', {static: true}) licenseeFormsModalSort: MatSort;

    licenseeForms$: Observable<FormVO[]>;
    licenseeFormsDataSource = new MatTableDataSource<FormVO>([]);
    licenseeForm$: Observable<LicenseeFormVO>;
    licenseeFormsSearchField: FormControl;
    licenseeFormsSelect: FormVO[] = [];

    licenseeLicencesColumns = [
        'id',
        'licenceType.name',
        'licenceNumber',
        'startDate',
        'endDate',
        'status',
    ];

    licenseeLicencesModalColumns = [
        'actions',
        ...this.licenseeLicencesColumns
    ];

    @ViewChild('licenseeLicencesPaginator', {static: true}) licenseeLicencesPaginator: MatPaginator;
    @ViewChild('licenseeLicencesSort', {static: true}) licenseeLicencesSort: MatSort;

    @ViewChild('licenseeLicencesModalPaginator', {static: true}) licenseeLicencesModalPaginator: MatPaginator;
    @ViewChild('licenseeLicencesModalSort', {static: true}) licenseeLicencesModalSort: MatSort;

    licenseeLicences$: Observable<LicenceVO[]>;
    licenseeLicencesDataSource = new MatTableDataSource<LicenceVO>([]);
    licenseeLicencesSearchField: FormControl;
    licenseeLicencesSelect: LicenceVO[] = [];

    licenseeDocumentsColumns = [
        'id',
        'documentId',
        'documentName',
        'documentType.name',
    ];

    licenseeDocumentsModalColumns = [
        'actions',
        ...this.licenseeDocumentsColumns
    ];

    @ViewChild('licenseeDocumentsPaginator', {static: true}) licenseeDocumentsPaginator: MatPaginator;
    @ViewChild('licenseeDocumentsSort', {static: true}) licenseeDocumentsSort: MatSort;

    @ViewChild('licenseeDocumentsModalPaginator', {static: true}) licenseeDocumentsModalPaginator: MatPaginator;
    @ViewChild('licenseeDocumentsModalSort', {static: true}) licenseeDocumentsModalSort: MatSort;

    licenseeDocuments$: Observable<DocumentVO[]>;
    licenseeDocumentsDataSource = new MatTableDataSource<DocumentVO>([]);
    licenseeDocumentsSearchField: FormControl;
    licenseeDocumentsSelect: DocumentVO[] = [];

    licenseeSectorsColumns = [
        'id',
        'code',
        'name',
    ];

    licenseeSectorsModalColumns = [
        'actions',
        ...this.licenseeSectorsColumns
    ];

    @ViewChild('licenseeSectorsPaginator', {static: true}) licenseeSectorsPaginator: MatPaginator;
    @ViewChild('licenseeSectorsSort', {static: true}) licenseeSectorsSort: MatSort;

    @ViewChild('licenseeSectorsModalPaginator', {static: true}) licenseeSectorsModalPaginator: MatPaginator;
    @ViewChild('licenseeSectorsModalSort', {static: true}) licenseeSectorsModalSort: MatSort;

    licenseeSectors$: Observable<SectorVO[]>;
    licenseeSectorsDataSource = new MatTableDataSource<SectorVO>([]);
    licenseeSector$: Observable<LicenseeSectorVO>;
    licenseeSectorsSearchField: FormControl;
    licenseeSectorsSelect: SectorVO[] = [];

    statusT = LicenseeStatus;
    statusOptions: string[] = Object.keys(this.statusT);
    licensee$: Observable<LicenseeVO>;
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
        this.licenseeController = injector.get(LicenseeControllerImpl);
        this.documentRestController = injector.get(DocumentRestController);
        this.licenseeRestController = injector.get(LicenseeRestController);
        this._injector = injector;
        this.licensee$ = this.store.pipe(select(LicenseeSelectors.selectLicensee));
        this.loading = this.store.pipe(select(LicenseeSelectors.selectLoading));
        this.loaderMessage = this.store.pipe(select(LicenseeSelectors.selectLoaderMessage));
        this.success = this.store.pipe(select(LicenseeSelectors.selectSuccess));
        this.error = this.store.pipe(select(LicenseeSelectors.selectError));
        this.messages = this.store.pipe(select(LicenseeSelectors.selectMessages));
        this.licenseeSectors$ = this.store.pipe(select(SectorSelectors.selectSectors));
        this.licenseeSector$ = this.store.pipe(select(LicenseeSectorSelectors.selectLicenseeSector));
        this.licenseeForms$ = this.store.pipe(select(FormSelectors.selectForms));
        this.licenseeForm$ = this.store.pipe(select(LicenseeFormSelectors.selectLicenseeForm));
        this.licenseeUsersSearchField = new FormControl();
        this.licenseeFormsSearchField = new FormControl();
        this.licenseeLicencesSearchField = new FormControl();
        this.licenseeDocumentsSearchField = new FormControl();
        this.licenseeSectorsSearchField = new FormControl();
    }

    abstract beforeOnInit(form: EditLicenseeVarsForm): EditLicenseeVarsForm;
	
    ngOnInit() {
        let form: EditLicenseeVarsForm = this.beforeOnInit(new EditLicenseeVarsForm);
        this.editLicenseeForm = this.newForm(form);

        this.editLicenseeForm.valueChanges.subscribe(
            (change: any) => {
                this.handleFormChanges(change);
            }
        );

        this.licenseeUsers$?.subscribe(data => {
            this.licenseeUsersDataSource.data = data;
            this.licenseeUsersDataSource.paginator = this.licenseeUsersModalPaginator;
            this.licenseeUsersDataSource.sort = this.licenseeUsersModalSort;
        });

        this.licenseeForms$?.subscribe(data => {
            this.licenseeFormsDataSource.data = data;
            this.licenseeFormsDataSource.paginator = this.licenseeFormsModalPaginator;
            this.licenseeFormsDataSource.sort = this.licenseeFormsModalSort;
        });

        this.licenseeLicences$?.subscribe(data => {
            this.licenseeLicencesDataSource.data = data;
            this.licenseeLicencesDataSource.paginator = this.licenseeLicencesModalPaginator;
            this.licenseeLicencesDataSource.sort = this.licenseeLicencesModalSort;
        });

        this.licenseeDocuments$?.subscribe(data => {
            this.licenseeDocumentsDataSource.data = data;
            this.licenseeDocumentsDataSource.paginator = this.licenseeDocumentsModalPaginator;
            this.licenseeDocumentsDataSource.sort = this.licenseeDocumentsModalSort;
        });

        this.licenseeSectors$?.subscribe(data => {
            this.licenseeSectorsDataSource.data = data;
            this.licenseeSectorsDataSource.paginator = this.licenseeSectorsModalPaginator;
            this.licenseeSectorsDataSource.sort = this.licenseeSectorsModalSort;
        });
        
        this.afterOnInit();
    }

    handleFormChanges(change: any): void {
        
    }

    editLicenseeFormReset() {

        this.store.dispatch(LicenseeActions.licenseeReset());
        this.editLicenseeForm.reset()
        this.editLicenseeForm.markAsPristine();

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
        this.licenseeController.resetUseCaseScope();
    }

    newForm(editLicenseeVarsForm$: EditLicenseeVarsForm): FormGroup {
        return this.formBuilder.group({
            licensee: this.createLicenseeForm(editLicenseeVarsForm$?.licensee),
        });
    }

    abstract doNgOnDestroy(): void;

    ngOnDestroy() { 
        this.doNgOnDestroy();
        this.store.dispatch(LicenseeActions.licenseeReset());
    }

    get editLicenseeSaveForm(): EditLicenseeSaveForm {

        let form: EditLicenseeSaveForm = new EditLicenseeSaveForm();
        form.licensee = this.licensee;

        return form;
    }

    /**
     * This method may be overwritten
     */
    beforeEditLicenseeSave(form: EditLicenseeSaveForm): void {}

    /**
     * This method may be overwritten
     */
    afterEditLicenseeSave(form: EditLicenseeSaveForm): void {}

    editLicenseeSave(): void {
        let form: EditLicenseeSaveForm = this.editLicenseeSaveForm;
        this.beforeEditLicenseeSave(form);

        this.licenseeController.editLicenseeSave(form);
        this.afterEditLicenseeSave(form);
    }

    getEditLicenseeSaveForm(value: any): EditLicenseeSaveForm {
        
        let form: EditLicenseeSaveForm = new EditLicenseeSaveForm();

        if(value?.licensee) {
            form.licensee = value?.licensee;
        }

        return form;

    }

    get editLicenseeSearchForm(): EditLicenseeSearchForm {

        let form: EditLicenseeSearchForm = new EditLicenseeSearchForm();

        return form;
    }

    /**
     * This method may be overwritten
     */
    beforeEditLicenseeSearch(form: EditLicenseeSearchForm): void {}

    /**
     * This method may be overwritten
     */
    afterEditLicenseeSearch(form: EditLicenseeSearchForm): void {}

    editLicenseeSearch(): void {
        let form: EditLicenseeSearchForm = this.editLicenseeSearchForm;
        this.beforeEditLicenseeSearch(form);

        this.licenseeController.editLicenseeSearch(form);
        this.afterEditLicenseeSearch(form);
    }

    getEditLicenseeSearchForm(value: any): EditLicenseeSearchForm {
        
        let form: EditLicenseeSearchForm = new EditLicenseeSearchForm();

        return form;

    }

    get editLicenseeDeleteForm(): EditLicenseeDeleteForm {

        let form: EditLicenseeDeleteForm = new EditLicenseeDeleteForm();
        form.licensee = this.licensee;

        return form;
    }

    /**
     * This method may be overwritten
     */
    beforeEditLicenseeDelete(form: EditLicenseeDeleteForm): void {}

    /**
     * This method may be overwritten
     */
    afterEditLicenseeDelete(form: EditLicenseeDeleteForm): void {}

    editLicenseeDelete(): void {
        let form: EditLicenseeDeleteForm = this.editLicenseeDeleteForm;
        this.beforeEditLicenseeDelete(form);

        this.licenseeController.editLicenseeDelete(form);
        this.afterEditLicenseeDelete(form);
    }

    getEditLicenseeDeleteForm(value: any): EditLicenseeDeleteForm {
        
        let form: EditLicenseeDeleteForm = new EditLicenseeDeleteForm();

        if(value?.licensee) {
            form.licensee = value?.licensee;
        }

        return form;

    }

    get editLicenseeDocumentsForm(): EditLicenseeDocumentsForm {

        let form: EditLicenseeDocumentsForm = new EditLicenseeDocumentsForm();
        form.licensee = this.licensee;

        return form;
    }

    /**
     * This method may be overwritten
     */
    beforeEditLicenseeDocuments(form: EditLicenseeDocumentsForm): void {}

    /**
     * This method may be overwritten
     */
    afterEditLicenseeDocuments(form: EditLicenseeDocumentsForm): void {}

    editLicenseeDocuments(): void {
        let form: EditLicenseeDocumentsForm = this.editLicenseeDocumentsForm;
        this.beforeEditLicenseeDocuments(form);

        this.licenseeController.editLicenseeDocuments(form);
        this.afterEditLicenseeDocuments(form);
    }

    getEditLicenseeDocumentsForm(value: any): EditLicenseeDocumentsForm {
        
        let form: EditLicenseeDocumentsForm = new EditLicenseeDocumentsForm();

        if(value?.licensee) {
            form.licensee = value?.licensee;
        }

        return form;

    }

    get editLicenseeNewShareholderForm(): EditLicenseeNewShareholderForm {

        let form: EditLicenseeNewShareholderForm = new EditLicenseeNewShareholderForm();

        return form;
    }

    /**
     * This method may be overwritten
     */
    beforeEditLicenseeNewShareholder(form: EditLicenseeNewShareholderForm): void {}

    /**
     * This method may be overwritten
     */
    getEditLicenseeNewShareholderFormDialogConfig(data: any): any{
        return {
            data: {
                width: '800px'
            }
        };
    }

    /**
     * This method may be overwritten
     */
    afterEditLicenseeNewShareholder(form: EditLicenseeNewShareholderForm, dialogData: any): void {
        
    }

    editLicenseeNewShareholder(): void {
        let form: EditLicenseeNewShareholderForm = this.editLicenseeNewShareholderForm;
        this.beforeEditLicenseeNewShareholder(form);

        this.licenseeController.editLicenseeNewShareholder(form);
        let dialogConfig = this.getEditLicenseeNewShareholderFormDialogConfig(form);
        const dialogRef = this.dialog.open(NewShareholderComponentImpl, dialogConfig);

        dialogRef.afterClosed().subscribe((result) => {
            this.afterEditLicenseeNewShareholder(form, result?.dialogData);
        });
    }

    getEditLicenseeNewShareholderForm(value: any): EditLicenseeNewShareholderForm {
        
        let form: EditLicenseeNewShareholderForm = new EditLicenseeNewShareholderForm();

        return form;

    }

    get editLicenseeNewDocumentForm(): EditLicenseeNewDocumentForm {

        let form: EditLicenseeNewDocumentForm = new EditLicenseeNewDocumentForm();

        return form;
    }

    /**
     * This method may be overwritten
     */
    beforeEditLicenseeNewDocument(form: EditLicenseeNewDocumentForm): void {}

    /**
     * This method may be overwritten
     */
    getEditLicenseeNewDocumentFormDialogConfig(data: any): any{
        return {
            data: {
                width: '800px'
            }
        };
    }

    /**
     * This method may be overwritten
     */
    afterEditLicenseeNewDocument(form: EditLicenseeNewDocumentForm, dialogData: any): void {
        
    }

    editLicenseeNewDocument(): void {
        let form: EditLicenseeNewDocumentForm = this.editLicenseeNewDocumentForm;
        this.beforeEditLicenseeNewDocument(form);

        this.licenseeController.editLicenseeNewDocument(form);
        let dialogConfig = this.getEditLicenseeNewDocumentFormDialogConfig(form);
        const dialogRef = this.dialog.open(NewDocumentComponentImpl, dialogConfig);

        dialogRef.afterClosed().subscribe((result) => {
            this.afterEditLicenseeNewDocument(form, result?.dialogData);
        });
    }

    getEditLicenseeNewDocumentForm(value: any): EditLicenseeNewDocumentForm {
        
        let form: EditLicenseeNewDocumentForm = new EditLicenseeNewDocumentForm();

        return form;

    }

    get editLicenseeVarsFormControl(): FormGroup {
        return this.getGroupControl('editLicenseeVarsForm');
    }

    /**
     * This method may be overwritten
     */
    afterSetEditLicenseeVarsForm(form: EditLicenseeVarsForm): void {}

    setEditLicenseeVarsForm(form: EditLicenseeVarsForm) {

        this.editLicenseeVarsFormControl.setControl('licensee', this.createLicenseeForm(form.licensee));

        this.afterSetEditLicenseeVarsForm(form);
    }

    createLicenseeForm(licensee: LicenseeVO): FormGroup {
        return this.formBuilder.group({
            id: [{value: licensee?.id, disabled: false}],
            createdBy: [{value: licensee?.createdBy, disabled: false}],
            updatedBy: [{value: licensee?.updatedBy, disabled: false}],
            createdDate: [{value: licensee?.createdDate, disabled: false}],
            updatedDate: [{value: licensee?.updatedDate, disabled: false}],
            status: [{value: licensee?.status, disabled: false}, [Validators.required, ]],
            uin: [{value: licensee?.uin, disabled: false}, [Validators.required, ]],
            licenseeName: [{value: licensee?.licenseeName, disabled: false}, [Validators.required, ]],
            address: [{value: licensee?.address, disabled: false}],
            users: this.createUserVOArray(licensee?.users),
            forms: this.createLicenseeFormVOArray(licensee?.forms),
            licences: this.createLicenceVOArray(licensee?.licences),
            documents: this.createDocumentVOArray(licensee?.documents),
            sectors: this.createLicenseeSectorVOArray(licensee?.sectors),
        });
    }

    get licenseeControl(): FormGroup {
        return this.getGroupControl('licensee') as FormGroup;
    }

    get licensee(): LicenseeVO {
        return this.licenseeControl.value;
    }

    get licenseeIdControl(): FormControl {
        return this.licenseeControl.get('id') as FormControl;
    }

    get licenseeId(): number {
        return this.licenseeIdControl.value;
    }

    get licenseeCreatedByControl(): FormControl {
        return this.licenseeControl.get('createdBy') as FormControl;
    }

    get licenseeCreatedBy(): string {
        return this.licenseeCreatedByControl.value;
    }

    get licenseeUpdatedByControl(): FormControl {
        return this.licenseeControl.get('updatedBy') as FormControl;
    }

    get licenseeUpdatedBy(): string {
        return this.licenseeUpdatedByControl.value;
    }

    get licenseeCreatedDateControl(): FormControl {
        return this.licenseeControl.get('createdDate') as FormControl;
    }

    get licenseeCreatedDate(): Date {
        return this.licenseeCreatedDateControl.value;
    }

    get licenseeUpdatedDateControl(): FormControl {
        return this.licenseeControl.get('updatedDate') as FormControl;
    }

    get licenseeUpdatedDate(): Date {
        return this.licenseeUpdatedDateControl.value;
    }

    get licenseeStatusControl(): FormGroup {
        return this.licenseeControl.get('status') as FormGroup;
    }

    get licenseeStatus(): LicenseeStatus {
        return this.licenseeStatusControl.value;
    }

    get licenseeUinControl(): FormControl {
        return this.licenseeControl.get('uin') as FormControl;
    }

    get licenseeUin(): string {
        return this.licenseeUinControl.value;
    }

    get licenseeLicenseeNameControl(): FormControl {
        return this.licenseeControl.get('licenseeName') as FormControl;
    }

    get licenseeLicenseeName(): string {
        return this.licenseeLicenseeNameControl.value;
    }

    get licenseeAddressControl(): FormControl {
        return this.licenseeControl.get('address') as FormControl;
    }

    get licenseeAddress(): string {
        return this.licenseeAddressControl.value;
    }

    get licenseeUsersControl(): FormArray {
        return this.licenseeControl.get('users') as FormArray;
    }

    get licenseeUsers(): UserVO[] {
        return this.licenseeUsersControl.value;
    }


    licenseeUsersAddDialog(): void {
    }

    
    licenseeUsersSearch(): void {
    }

    handleDeleteFromLicenseeUsers(users: UserVO): void {}
    
    deleteFromLicenseeUsers(index: number) {
        this.handleDeleteFromLicenseeUsers(this.licenseeUsers[index]);
        this.licenseeUsersControl.removeAt(index);
    }

    doEditLicenseeUsers(users: UserVO) {
    }

    handleLicenseeUsersSelected(event: MatCheckboxChange, data: UserVO): void {}
    
    licenseeUsersSelected(event: MatCheckboxChange, data: UserVO): void {
        if(event.checked) {
            this.licenseeUsersSelect.push(data);
        } else {
            const key = Object.keys(data)[0];
            let tmp = this.licenseeUsersSelect.filter(d => d[key] !== data[key]);
            this.licenseeUsersSelect = tmp;
        }

        this.handleLicenseeUsersSelected(event, data);
    }

    addToLicenseeUsers(data: UserVO) {
        this.licenseeUsersControl.push(this.createUserVOGroup(data));
    }

    /**
     * May be overridden to customise behaviour
     *
     */
    addSelectedLicenseeUsers(): void {
        this.licenseeUsersSelect.forEach((data) => {
            const key = Object.keys(data)[0];
            const found = this.licenseeUsers.find((d: UserVO) => d[key] === data[key])
            if(!found) {
                this.addToLicenseeUsers(data);
            }
        });
    }

    get licenseeFormsControl(): FormArray {
        return this.licenseeControl.get('forms') as FormArray;
    }

    get licenseeForms(): LicenseeFormVO[] {
        return this.licenseeFormsControl.value;
    }


    licenseeFormsAddDialog(): void {
    }

    
    licenseeFormsSearch(): void {
    }

    handleDeleteFromLicenseeForms(forms: LicenseeFormVO): void {}
    
    deleteFromLicenseeForms(index: number) {
        this.handleDeleteFromLicenseeForms(this.licenseeForms[index]);
        this.licenseeFormsControl.removeAt(index);
    }

    doEditLicenseeForms(forms: LicenseeFormVO) {
    }

    handleLicenseeFormsSelected(event: MatCheckboxChange, data: FormVO): void {}
    
    licenseeFormsSelected(event: MatCheckboxChange, data: FormVO): void {
        if(event.checked) {
            this.licenseeFormsSelect.push(data);
        } else {
            const key = Object.keys(data)[0];
            let tmp = this.licenseeFormsSelect.filter(d => d[key] !== data[key]);
            this.licenseeFormsSelect = tmp;
        }

        this.handleLicenseeFormsSelected(event, data);
    }

    addToLicenseeForms(data: LicenseeFormVO) {
        this.licenseeFormsControl.push(this.createLicenseeFormVOGroup(data));
    }

    /**
     * May be overridden to customise behaviour
     *
     */
    addSelectedLicenseeForms(): void {
        this.licenseeFormsSelect.forEach((data) => {
            const found = this.licenseeForms.find((d: LicenseeFormVO) => d.form.id === data?.id)
            if(!found) {
                this.store.dispatch(
                    LicenseeFormActions.create({
                        licenseeId: this.licenseeId,
                        formId: data.id,
                        loading: true,
                        loaderMessage: 'Creating a licensee form associations ....'
                    })
                );
            }
        });
    }

    get licenseeLicencesControl(): FormArray {
        return this.licenseeControl.get('licences') as FormArray;
    }

    get licenseeLicences(): LicenceVO[] {
        return this.licenseeLicencesControl.value;
    }


    licenseeLicencesAddDialog(): void {
    }

    
    licenseeLicencesSearch(): void {
    }

    handleDeleteFromLicenseeLicences(licences: LicenceVO): void {}
    
    deleteFromLicenseeLicences(index: number) {
        this.handleDeleteFromLicenseeLicences(this.licenseeLicences[index]);
        this.licenseeLicencesControl.removeAt(index);
    }

    doEditLicenseeLicences(licences: LicenceVO) {
    }

    handleLicenseeLicencesSelected(event: MatCheckboxChange, data: LicenceVO): void {}
    
    licenseeLicencesSelected(event: MatCheckboxChange, data: LicenceVO): void {
        if(event.checked) {
            this.licenseeLicencesSelect.push(data);
        } else {
            const key = Object.keys(data)[0];
            let tmp = this.licenseeLicencesSelect.filter(d => d[key] !== data[key]);
            this.licenseeLicencesSelect = tmp;
        }

        this.handleLicenseeLicencesSelected(event, data);
    }

    addToLicenseeLicences(data: LicenceVO) {
        this.licenseeLicencesControl.push(this.createLicenceVOGroup(data));
    }

    /**
     * May be overridden to customise behaviour
     *
     */
    addSelectedLicenseeLicences(): void {
        this.licenseeLicencesSelect.forEach((data) => {
            const key = Object.keys(data)[0];
            const found = this.licenseeLicences.find((d: LicenceVO) => d[key] === data[key])
            if(!found) {
                this.addToLicenseeLicences(data);
            }
        });
    }

    get licenseeDocumentsControl(): FormArray {
        return this.licenseeControl.get('documents') as FormArray;
    }

    get licenseeDocuments(): DocumentVO[] {
        return this.licenseeDocumentsControl.value;
    }


    licenseeDocumentsAddDialog(): void {
    }

    
    licenseeDocumentsSearch(): void {
    }

    handleDeleteFromLicenseeDocuments(documents: DocumentVO): void {}
    
    deleteFromLicenseeDocuments(index: number) {
        this.handleDeleteFromLicenseeDocuments(this.licenseeDocuments[index]);
        this.licenseeDocumentsControl.removeAt(index);
    }

    doEditLicenseeDocuments(documents: DocumentVO) {
    }

    handleLicenseeDocumentsSelected(event: MatCheckboxChange, data: DocumentVO): void {}
    
    licenseeDocumentsSelected(event: MatCheckboxChange, data: DocumentVO): void {
        if(event.checked) {
            this.licenseeDocumentsSelect.push(data);
        } else {
            const key = Object.keys(data)[0];
            let tmp = this.licenseeDocumentsSelect.filter(d => d[key] !== data[key]);
            this.licenseeDocumentsSelect = tmp;
        }

        this.handleLicenseeDocumentsSelected(event, data);
    }

    addToLicenseeDocuments(data: DocumentVO) {
        this.licenseeDocumentsControl.push(this.createDocumentVOGroup(data));
    }

    /**
     * May be overridden to customise behaviour
     *
     */
    addSelectedLicenseeDocuments(): void {
        this.licenseeDocumentsSelect.forEach((data) => {
            const key = Object.keys(data)[0];
            const found = this.licenseeDocuments.find((d: DocumentVO) => d[key] === data[key])
            if(!found) {
                this.addToLicenseeDocuments(data);
            }
        });
    }

    get licenseeSectorsControl(): FormArray {
        return this.licenseeControl.get('sectors') as FormArray;
    }

    get licenseeSectors(): LicenseeSectorVO[] {
        return this.licenseeSectorsControl.value;
    }


    licenseeSectorsAddDialog(): void {
    }

    
    licenseeSectorsSearch(): void {
    }

    handleDeleteFromLicenseeSectors(sectors: LicenseeSectorVO): void {}
    
    deleteFromLicenseeSectors(index: number) {
        if(confirm('Are you sure you want to delete the licensee sector?')) {
            this.store.dispatch(
                LicenseeSectorActions.remove({
                    id: this.licenseeSectors[index].id,
                    loading: true,
                    loaderMessage: 'Removing licensee sector association ...'
                })
            );

            this.licenseeForms$.subscribe(removed => {

                this.handleDeleteFromLicenseeSectors(this.licenseeSectors[index]);
                this.licenseeSectorsControl.removeAt(index);
            })
        }
    }

    doEditLicenseeSectors(sectors: LicenseeSectorVO) {
    }

    handleLicenseeSectorsSelected(event: MatCheckboxChange, data: SectorVO): void {}
    
    licenseeSectorsSelected(event: MatCheckboxChange, data: SectorVO): void {
        if(event.checked) {
            this.licenseeSectorsSelect.push(data);
        } else {
            const key = Object.keys(data)[0];
            let tmp = this.licenseeSectorsSelect.filter(d => d[key] !== data[key]);
            this.licenseeSectorsSelect = tmp;
        }

        this.handleLicenseeSectorsSelected(event, data);
    }

    addToLicenseeSectors(data: LicenseeSectorVO) {
        this.licenseeSectorsControl.push(this.createLicenseeSectorVOGroup(data));
    }

    /**
     * May be overridden to customise behaviour
     *
     */
    addSelectedLicenseeSectors(): void {
        this.licenseeSectorsSelect.forEach((data) => {
            const key = Object.keys(data)[0];
            const found = this.licenseeSectors.find((d: LicenseeSectorVO) => d.sector.id === data.id)
            if(!found) {
                this.store.dispatch(
                    LicenseeSectorActions.create({
                        licenseeId: this.licenseeId,
                        sectorId: data.id,
                        loading: true,
                        loaderMessage: 'Creating a licensee sector association ...'
                    })
                );
            }
        });
    }

    getItemControl(name: string): FormControl {
        return this.editLicenseeForm.get(name) as FormControl;
    }

    getGroupControl(name: string): FormGroup {
        return this.editLicenseeForm.get(name) as FormGroup;
    }

    getArrayControl(name: string): FormArray {
        return this.editLicenseeForm.get(name) as FormArray;
    }

    setEditLicenseeFormValue(form: any) {
        if(form.licensee) {
            this.editLicenseeForm.setControl('licensee', this.createLicenseeForm(form.licensee));
        }
    }

    createLicenseeFormVOGroup(value: LicenseeFormVO): FormGroup {
        return this.formBuilder.group({
            id: [value?.id],
        });
    }

    createLicenseeFormVOArray(values: LicenseeFormVO[]): FormArray {
        if(values) {
            let formArray: FormArray = this.formBuilder.array([]);
            values?.forEach(value => formArray.push(this.createLicenseeFormVOGroup(value)))

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

    createLicenceVOGroup(value: LicenceVO): FormGroup {
        return this.formBuilder.group({
            id: [value?.id],
            createdBy: [value?.createdBy],
            updatedBy: [value?.updatedBy],
            createdDate: [value?.createdDate],
            updatedDate: [value?.updatedDate],
            status: [value?.status],
            licenceNumber: [value?.licenceNumber],
            provisional: [value?.provisional],
            startDate: [value?.startDate],
            endDate: [value?.endDate],
        });
    }

    createLicenceVOArray(values: LicenceVO[]): FormArray {
        if(values) {
            let formArray: FormArray = this.formBuilder.array([]);
            values?.forEach(value => formArray.push(this.createLicenceVOGroup(value)))

            return formArray;
        } else {
            return new FormArray([]);
        }
    }

    createUserVOGroup(value: UserVO): FormGroup {
        return this.formBuilder.group({
            userId: [value?.userId],
            username: [value?.username],
            email: [value?.email],
            password: [value?.password],
            firstName: [value?.firstName],
            lastName: [value?.lastName],
            enabled: [value?.enabled],
        });
    }

    createUserVOArray(values: UserVO[]): FormArray {
        if(values) {
            let formArray: FormArray = this.formBuilder.array([]);
            values?.forEach(value => formArray.push(this.createUserVOGroup(value)))

            return formArray;
        } else {
            return new FormArray([]);
        }
    }

    createLicenseeSectorVOGroup(value: LicenseeSectorVO): FormGroup {
        return this.formBuilder.group({
            id: [value?.id],
            sector: {
                id: value?.sector?.id,
                code: value?.sector?.code,
                name: value?.sector?.name,
                description: value?.sector?.description,
                themeColour: value?.sector?.themeColour
            }
        });
    }

    createLicenseeSectorVOArray(values: LicenseeSectorVO[]): FormArray {
        if(values) {
            let formArray: FormArray = this.formBuilder.array([]);
            values?.forEach(value => formArray.push(this.createLicenseeSectorVOGroup(value)))

            return formArray;
        } else {
            return new FormArray([]);
        }
    }

    createShareholderVOGroup(value: ShareholderVO): FormGroup {
        return this.formBuilder.group({
            id: [value?.id],
            createdBy: [value?.createdBy],
            updatedBy: [value?.updatedBy],
            createdDate: [value?.createdDate],
            updatedDate: [value?.updatedDate],
            shareholderId: [value?.shareholderId],
            type: [value?.type],
            name: [value?.name],
            address: [value?.address],
            numberOfShares: [value?.numberOfShares],
            percentageShares: [value?.percentageShares],
        });
    }

    createShareholderVOArray(values: ShareholderVO[]): FormArray {
        if(values) {
            let formArray: FormArray = this.formBuilder.array([]);
            values?.forEach(value => formArray.push(this.createShareholderVOGroup(value)))

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

    createLicenseeCriteriaGroup(value: LicenseeCriteria): FormGroup {
        return this.formBuilder.group({
            uin: [value?.uin],
            licenseeName: [value?.licenseeName],
        });
    }

    createLicenseeCriteriaArray(values: LicenseeCriteria[]): FormArray {
        if(values) {
            let formArray: FormArray = this.formBuilder.array([]);
            values?.forEach(value => formArray.push(this.createLicenseeCriteriaGroup(value)))

            return formArray;
        } else {
            return new FormArray([]);
        }
    }
}
