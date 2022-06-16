// Generated by andromda-angular cartridge (view\view.component.imp.ts.vsl) CAN EDIT!
import { Component, Injector } from '@angular/core';
import { AddNewFieldComponent } from '@app/view/form/add-new-field.component';
import { AddNewFieldAddExpressionForm } from '@app/view/form/add-new-field.component';
import { AddNewFieldCancelForm } from '@app/view/form/add-new-field.component';
import { AddNewFieldDoneForm } from '@app/view/form/add-new-field.component';
import { AddNewFieldSaveForm } from '@app/view/form/add-new-field.component';
import { AddNewFieldVarsForm } from '@app/view/form/add-new-field.component';
import { FormState } from '@app/store/form/form.state';
import * as FormSelectors from '@app/store/form/form.selectors';
import * as FormActions from '@app/store/form/form.actions';
import { MatRadioChange } from '@angular/material/radio';
import { MatCheckboxChange } from '@angular/material/checkbox';
import { ExpressionVO } from '@app/model/bw/org/bocra/portal/expression/expression-vo';
import { FormVO } from '@app/model/bw/org/bocra/portal/form/form-vo';
import { FormSectionVO } from '@app/model/bw/org/bocra/portal/form/section/form-section-vo';
import { FormFieldVO } from '@model/bw/org/bocra/portal/form/field/form-field-vo';
import { SelectItem } from '@app/utils/select-item';
import { Observable } from 'rxjs';
import { select } from '@ngrx/store';

@Component({
  selector: 'app-add-new-field',
  templateUrl: './add-new-field.component.html',
  styleUrls: ['./add-new-field.component.scss']
})
export class AddNewFieldComponentImpl extends AddNewFieldComponent {

    form$: Observable<FormVO>;
    constructor(private injector: Injector) {
        super(injector);
        this.form$ = this.store.pipe(select(FormSelectors.selectForm));
    }

    beforeOnInit(form: AddNewFieldVarsForm): AddNewFieldVarsForm {     
      if (this.useCaseScope?.pageVariables['form']) {
        const f: FormVO = this.useCaseScope?.pageVariables['form'];
  
        f?.formSections?.forEach((element: FormSectionVO) => {
          let item: SelectItem = new SelectItem();
          item.label = element.sectionName;
          item.value = element;
  
          this.formFieldFormSectionBackingList.push(item);
        });
  
        if(!form?.formField) {
          form.formField = new FormFieldVO();
        }
  
        form.formField.form = f;
      } 

      return form;
    }
	
    afterOnInit() {
    }

    doNgAfterViewInit() {
      console.log(this.formController.getPageVariables())
      this.route.queryParams.subscribe((queryParams: any) => {

        if(queryParams?.id) {
          this.store.dispatch(
            FormActions.findFieldById({id: queryParams.id, loading: true})
          );

          this.form$.subscribe(f => {
            this.formFieldFormControl.patchValue(f);
            
          });
        } else {
          if(queryParams?.formId) {
            this.store.dispatch(
              FormActions.findFormById({id: queryParams.formId, loading: true})
            );

            this.form$.subscribe(f => {
              this.formFieldFormControl.patchValue(f);
              
            });
          }
        }
      });
    }

    doNgOnDestroy(){}

    handleFormChanges(change: any) {
    }

    handleFormFieldFormAddDialog(): void {}

    handleFormFieldFormSearch(): void {}
    
    handleFormFieldFormSelected(event: MatRadioChange, data: FormVO): void {}
    
    handleFormFieldExpressionAddDialog(): void {}

    handleFormFieldExpressionSearch(): void {}
    
    handleFormFieldExpressionSelected(event: MatRadioChange, data: ExpressionVO): void {}
    

    /**
     * This method may be overwritten
     */
    afterSetAddNewFieldVarsForm(form: AddNewFieldVarsForm): void {

    }

    /**
     * This method may be overwritten
     */
    afterSetAddNewFieldAddExpressionForm(form: AddNewFieldAddExpressionForm): void {

    }

    /**
     * This method may be overwritten
     */
    beforeAddNewFieldAddExpression(form: AddNewFieldAddExpressionForm): void {

    }

    /**
     * This method may be overwritten
     */
    afterAddNewFieldAddExpression(form: AddNewFieldAddExpressionForm): void {

    }
    
    /**
     * This method may be overwritten
     */
    afterSetAddNewFieldCancelForm(form: AddNewFieldCancelForm): void {

    }

    /**
     * This method may be overwritten
     */
    beforeAddNewFieldCancel(form: AddNewFieldCancelForm): void {
      console.log(form);

    }

    /**
     * This method may be overwritten
     */
    afterAddNewFieldCancel(form: AddNewFieldCancelForm): void {
      console.log(form);
    }
    
    /**
     * This method may be overwritten
     */
    afterSetAddNewFieldDoneForm(form: AddNewFieldDoneForm): void {

    }

    /**
     * This method may be overwritten
     */
    beforeAddNewFieldDone(form: AddNewFieldDoneForm): void {

    }

    /**
     * This method may be overwritten
     */
    afterAddNewFieldDone(form: AddNewFieldDoneForm): void {

    }
    
    /**
     * This method may be overwritten
     */
    afterSetAddNewFieldSaveForm(form: AddNewFieldSaveForm): void {

    }

    /**
     * This method may be overwritten
     */
    beforeAddNewFieldSave(form: AddNewFieldSaveForm): void {

    }

    /**
     * This method may be overwritten
     */
    afterAddNewFieldSave(form: AddNewFieldSaveForm): void {

    }
    
}