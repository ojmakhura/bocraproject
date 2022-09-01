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
import { MenuSectionState } from '@app/store/menu/menu-section.state';
import * as MenuSectionSelectors from '@app/store/menu/menu-section.selectors';
import * as MenuSectionActions from '@app/store/menu/menu-section.actions';

import { AuthorisationVO } from '@app/model/bw/org/bocra/portal/auth/authorisation-vo';
import { MenuSectionRestController } from '@app/service/bw/org/bocra/portal/menu/menu-section-rest-controller';
import { MenuSectionVO } from '@app/model/bw/org/bocra/portal/menu/menu-section-vo';
import { MenuSectionControllerImpl } from '@app/controller/menu/menu-section-controller.impl';

export class CreateOrEditMenuSectionSaveForm {
  menuSection: MenuSectionVO | any;
}

export class CreateOrEditMenuSectionSearchForm {
  menuSections: Array<MenuSectionVO>[] | any[];
  criteria: String | any;
  id: number | any;
}

export class CreateOrEditMenuSectionDeleteForm {
  menuSection: MenuSectionVO | any;
}
export class CreateOrEditMenuSectionVarsForm {
  menuSection: MenuSectionVO | any;
}

@Component({
selector: 'app-create-or-edit-menu-section-base',
template: ''
})
export abstract class CreateOrEditMenuSectionComponent implements OnInit, AfterViewInit, OnDestroy {

createOrEditMenuSectionForm: FormGroup | any;
  hide: boolean = false;
  protected route: ActivatedRoute;
  protected router: Router;
  protected formBuilder: FormBuilder;
  protected _injector: Injector;
  protected useCaseScope: UseCaseScope;
  protected store: Store<MenuSectionState>;
  dialog: MatDialog;
  menuSectionController: MenuSectionControllerImpl;
  menuSectionRestController: MenuSectionRestController;
  menuSectionAuthorisationsColumns = [
      'id',
      'accessPoint.name',
      'accessPoint.url',
  ];

  menuSectionAuthorisationsModalColumns = [
      'actions',
      ...this.menuSectionAuthorisationsColumns
  ];

  @ViewChild('menuSectionAuthorisationsPaginator', {static: true}) menuSectionAuthorisationsPaginator: MatPaginator;
  @ViewChild('menuSectionAuthorisationsSort', {static: true}) menuSectionAuthorisationsSort: MatSort;

  @ViewChild('menuSectionAuthorisationsModalPaginator', {static: true}) menuSectionAuthorisationsModalPaginator: MatPaginator;
  @ViewChild('menuSectionAuthorisationsModalSort', {static: true}) menuSectionAuthorisationsModalSort: MatSort;

  menuSectionAuthorisations$: Observable<AuthorisationVO[]>;
  menuSectionAuthorisationsSearchField: FormControl;
  menuSectionAuthorisationsSelect: AuthorisationVO[] = [];

  menuSection$: Observable<MenuSectionVO>;
  messages: Observable<any>;
  success: Observable<boolean>;
  loading: Observable<boolean>;
  error: Observable<boolean>;
  selected: any = null;

  constructor(injector: Injector) {
      
      this.route = injector.get(ActivatedRoute);
      this.router = injector.get(Router);
      this.formBuilder = injector.get(FormBuilder);
      this.useCaseScope = injector.get(UseCaseScope);
      this.store = injector.get(Store);
      this.dialog = injector.get(MatDialog);
      this.menuSectionController = injector.get(MenuSectionControllerImpl);
      this.menuSectionRestController = injector.get(MenuSectionRestController);
      this._injector = injector;
      this.menuSection$ = this.store.pipe(select(MenuSectionSelectors.selectMenuSection));
      this.loading = this.store.pipe(select(MenuSectionSelectors.selectLoading));
      this.success = this.store.pipe(select(MenuSectionSelectors.selectSuccess));
      this.error = this.store.pipe(select(MenuSectionSelectors.selectError));
      this.messages = this.store.pipe(select(MenuSectionSelectors.selectMessages));
      this.menuSectionAuthorisationsSearchField = new FormControl();
  }

  abstract beforeOnInit(form: CreateOrEditMenuSectionVarsForm): CreateOrEditMenuSectionVarsForm;

  ngOnInit() {
      let form: CreateOrEditMenuSectionVarsForm = this.beforeOnInit(new CreateOrEditMenuSectionVarsForm);
      this.createOrEditMenuSectionForm = this.newForm(form);

      this.createOrEditMenuSectionForm.valueChanges.subscribe(
          (change: any) => {
              this.handleFormChanges(change);
          }
      );
      
      this.afterOnInit();
  }

  handleFormChanges(change: any): void {
      
  }

  createOrEditMenuSectionFormReset() {

      this.store.dispatch(MenuSectionActions.menuSectionReset());
      this.createOrEditMenuSectionForm.reset()
      this.createOrEditMenuSectionForm.markAsPristine();

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
      this.menuSectionController.resetUseCaseScope();
  }

  newForm(createOrEditMenuSectionVarsForm$: CreateOrEditMenuSectionVarsForm): FormGroup {
      return this.formBuilder.group({
          menuSection: this.createMenuSectionForm(createOrEditMenuSectionVarsForm$?.menuSection),
      });
  }

  abstract doNgOnDestroy(): void;

  ngOnDestroy() { 
      this.doNgOnDestroy();
      this.store.dispatch(MenuSectionActions.menuSectionReset());
  }

  get createOrEditMenuSectionSaveForm(): CreateOrEditMenuSectionSaveForm {

      let form: CreateOrEditMenuSectionSaveForm = new CreateOrEditMenuSectionSaveForm();
      form.menuSection = this.menuSection;

      return form;
  }

  /**
   * This method may be overwritten
   */
  beforeCreateOrEditMenuSectionSave(form: CreateOrEditMenuSectionSaveForm): void {}

  /**
   * This method may be overwritten
   */
  afterCreateOrEditMenuSectionSave(form: CreateOrEditMenuSectionSaveForm): void {}

  createOrEditMenuSectionSave(): void {
      let form: CreateOrEditMenuSectionSaveForm = this.createOrEditMenuSectionSaveForm;
      this.beforeCreateOrEditMenuSectionSave(form);

      this.menuSectionController.createOrEditMenuSectionSave(form);
      this.afterCreateOrEditMenuSectionSave(form);
  }

  getCreateOrEditMenuSectionSaveForm(value: any): CreateOrEditMenuSectionSaveForm {
      
      let form: CreateOrEditMenuSectionSaveForm = new CreateOrEditMenuSectionSaveForm();

      if(value?.menuSection) {
          form.menuSection = value?.menuSection;
      }

      return form;

  }

  get createOrEditMenuSectionSearchForm(): CreateOrEditMenuSectionSearchForm {

      let form: CreateOrEditMenuSectionSearchForm = new CreateOrEditMenuSectionSearchForm();

      return form;
  }

  /**
   * This method may be overwritten
   */
  beforeCreateOrEditMenuSectionSearch(form: CreateOrEditMenuSectionSearchForm): void {}

  /**
   * This method may be overwritten
   */
  afterCreateOrEditMenuSectionSearch(form: CreateOrEditMenuSectionSearchForm): void {}

  createOrEditMenuSectionSearch(): void {
      let form: CreateOrEditMenuSectionSearchForm = this.createOrEditMenuSectionSearchForm;
      this.beforeCreateOrEditMenuSectionSearch(form);

      this.menuSectionController.createOrEditMenuSectionSearch(form);
      this.afterCreateOrEditMenuSectionSearch(form);
  }

  getCreateOrEditMenuSectionSearchForm(value: any): CreateOrEditMenuSectionSearchForm {
      
      let form: CreateOrEditMenuSectionSearchForm = new CreateOrEditMenuSectionSearchForm();

      return form;

  }

  get createOrEditMenuSectionDeleteForm(): CreateOrEditMenuSectionDeleteForm {

      let form: CreateOrEditMenuSectionDeleteForm = new CreateOrEditMenuSectionDeleteForm();
      form.menuSection = this.menuSection;

      return form;
  }

  /**
   * This method may be overwritten
   */
  beforeCreateOrEditMenuSectionDelete(form: CreateOrEditMenuSectionDeleteForm): void {}

  /**
   * This method may be overwritten
   */
  afterCreateOrEditMenuSectionDelete(form: CreateOrEditMenuSectionDeleteForm): void {}

  createOrEditMenuSectionDelete(): void {
      let form: CreateOrEditMenuSectionDeleteForm = this.createOrEditMenuSectionDeleteForm;
      this.beforeCreateOrEditMenuSectionDelete(form);

      this.menuSectionController.createOrEditMenuSectionDelete(form);
      this.afterCreateOrEditMenuSectionDelete(form);
  }

  getCreateOrEditMenuSectionDeleteForm(value: any): CreateOrEditMenuSectionDeleteForm {
      
      let form: CreateOrEditMenuSectionDeleteForm = new CreateOrEditMenuSectionDeleteForm();

      if(value?.menuSection) {
          form.menuSection = value?.menuSection;
      }

      return form;

  }

  get createOrEditMenuSectionVarsFormControl(): FormGroup {
      return this.getGroupControl('createOrEditMenuSectionVarsForm');
  }

  /**
   * This method may be overwritten
   */
  afterSetCreateOrEditMenuSectionVarsForm(form: CreateOrEditMenuSectionVarsForm): void {}

  setCreateOrEditMenuSectionVarsForm(form: CreateOrEditMenuSectionVarsForm) {

      this.createOrEditMenuSectionVarsFormControl.setControl('menuSection', this.createMenuSectionForm(form.menuSection));

      this.afterSetCreateOrEditMenuSectionVarsForm(form);
  }

  createMenuSectionForm(menuSection: MenuSectionVO): FormGroup {
      return this.formBuilder.group({
          id: [{value: menuSection?.id, disabled: false}],
          createdBy: [{value: menuSection?.createdBy, disabled: false}],
          updatedBy: [{value: menuSection?.updatedBy, disabled: false}],
          createdDate: [{value: menuSection?.createdDate, disabled: false}],
          updatedDate: [{value: menuSection?.updatedDate, disabled: false}],
          position: [{value: menuSection?.position, disabled: false}],
          displayId: [{value: menuSection?.displayId, disabled: false}, [Validators.required, ]],
          displayName: [{value: menuSection?.displayName, disabled: false}, [Validators.required, ]],
          authorisations: this.createAuthorisationVOArray(menuSection?.authorisations),
      });
  }

  get menuSectionControl(): FormGroup {
      return this.getGroupControl('menuSection') as FormGroup;
  }

  get menuSection(): MenuSectionVO {
      return this.menuSectionControl.value;
  }

  get menuSectionIdControl(): FormControl {
      return this.menuSectionControl.get('id') as FormControl;
  }

  get menuSectionId(): number {
      return this.menuSectionIdControl.value;
  }

  get menuSectionCreatedByControl(): FormControl {
      return this.menuSectionControl.get('createdBy') as FormControl;
  }

  get menuSectionCreatedBy(): string {
      return this.menuSectionCreatedByControl.value;
  }

  get menuSectionUpdatedByControl(): FormControl {
      return this.menuSectionControl.get('updatedBy') as FormControl;
  }

  get menuSectionUpdatedBy(): string {
      return this.menuSectionUpdatedByControl.value;
  }

  get menuSectionCreatedDateControl(): FormControl {
      return this.menuSectionControl.get('createdDate') as FormControl;
  }

  get menuSectionCreatedDate(): Date {
      return this.menuSectionCreatedDateControl.value;
  }

  get menuSectionUpdatedDateControl(): FormControl {
      return this.menuSectionControl.get('updatedDate') as FormControl;
  }

  get menuSectionUpdatedDate(): Date {
      return this.menuSectionUpdatedDateControl.value;
  }

  get menuSectionPositionControl(): FormControl {
      return this.menuSectionControl.get('position') as FormControl;
  }

  get menuSectionPosition(): number {
      return this.menuSectionPositionControl.value;
  }

  get menuSectionDisplayIdControl(): FormControl {
      return this.menuSectionControl.get('displayId') as FormControl;
  }

  get menuSectionDisplayId(): string {
      return this.menuSectionDisplayIdControl.value;
  }

  get menuSectionDisplayNameControl(): FormControl {
      return this.menuSectionControl.get('displayName') as FormControl;
  }

  get menuSectionDisplayName(): string {
      return this.menuSectionDisplayNameControl.value;
  }

  get menuSectionAuthorisationsControl(): FormArray {
      return this.menuSectionControl.get('authorisations') as FormArray;
  }

  get menuSectionAuthorisations(): AuthorisationVO[] {
      return this.menuSectionAuthorisationsControl.value;
  }


  menuSectionAuthorisationsAddDialog(): void {
  }

  
  menuSectionAuthorisationsSearch(): void {
  }

  handleDeleteFromMenuSectionAuthorisations(authorisations: AuthorisationVO): void {}
  
  deleteFromMenuSectionAuthorisations(index: number) {
      this.handleDeleteFromMenuSectionAuthorisations(this.menuSectionAuthorisations[index]);
      this.menuSectionAuthorisationsControl.removeAt(index);
  }

  doEditMenuSectionAuthorisations(authorisations: AuthorisationVO) {
  }

  handleMenuSectionAuthorisationsSelected(event: MatCheckboxChange, data: AuthorisationVO): void {}
  
  menuSectionAuthorisationsSelected(event: MatCheckboxChange, data: AuthorisationVO): void {
      if(event.checked) {
          this.menuSectionAuthorisationsSelect.push(data);
      } else {
          const key = Object.keys(data)[0];
          let tmp = this.menuSectionAuthorisationsSelect.filter(d => d[key] !== data[key]);
          this.menuSectionAuthorisationsSelect = tmp;
      }

      this.handleMenuSectionAuthorisationsSelected(event, data);
  }

  addToMenuSectionAuthorisations(data: AuthorisationVO) {
      this.menuSectionAuthorisationsControl.push(this.createAuthorisationVOGroup(data));
  }

  /**
   * May be overridden to customise behaviour
   *
   */
  addSelectedMenuSectionAuthorisations(): void {
      this.menuSectionAuthorisationsSelect.forEach((data) => {
          const key = Object.keys(data)[0];
          const found = this.menuSectionAuthorisations.find((d: AuthorisationVO) => d[key] === data[key])
          if(!found) {
              this.addToMenuSectionAuthorisations(data);
          }
      });
  }

  getItemControl(name: string): FormControl {
      return this.createOrEditMenuSectionForm.get(name) as FormControl;
  }

  getGroupControl(name: string): FormGroup {
      return this.createOrEditMenuSectionForm.get(name) as FormGroup;
  }

  getArrayControl(name: string): FormArray {
      return this.createOrEditMenuSectionForm.get(name) as FormArray;
  }

  setCreateOrEditMenuSectionFormValue(form: any) {
      if(form.menuSection) {
          this.createOrEditMenuSectionForm.setControl('menuSection', this.createMenuSectionForm(form.menuSection));
      }
  }

  createAuthorisationVOGroup(value: AuthorisationVO): FormGroup {
      return this.formBuilder.group({
          id: [value?.id],
          createdBy: [value?.createdBy],
          updatedBy: [value?.updatedBy],
          createdDate: [value?.createdDate],
          updatedDate: [value?.updatedDate],
      });
  }

  createAuthorisationVOArray(values: AuthorisationVO[]): FormArray {
      if(values) {
          let formArray: FormArray = this.formBuilder.array([]);
          values?.forEach(value => formArray.push(this.createAuthorisationVOGroup(value)))

          return formArray;
      } else {
          return new FormArray([]);
      }
  }

  createMenuSectionVOGroup(value: MenuSectionVO): FormGroup {
      return this.formBuilder.group({
          id: [value?.id],
          createdBy: [value?.createdBy],
          updatedBy: [value?.updatedBy],
          createdDate: [value?.createdDate],
          updatedDate: [value?.updatedDate],
          position: [value?.position],
          displayId: [value?.displayId],
          displayName: [value?.displayName],
      });
  }

  createMenuSectionVOArray(values: MenuSectionVO[]): FormArray {
      if(values) {
          let formArray: FormArray = this.formBuilder.array([]);
          values?.forEach(value => formArray.push(this.createMenuSectionVOGroup(value)))

          return formArray;
      } else {
          return new FormArray([]);
      }
  }

}
