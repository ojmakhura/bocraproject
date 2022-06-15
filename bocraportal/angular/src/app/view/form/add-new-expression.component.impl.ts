// Generated by andromda-angular cartridge (view\view.component.imp.ts.vsl) CAN EDIT!
import { Component, Injector } from '@angular/core';
import { AddNewExpressionComponent, AddNewExpressionVarsForm } from '@app/view/form/add-new-expression.component';
import { AddNewExpressionCancelForm } from '@app/view/form/add-new-expression.component';
import { AddNewExpressionDoneForm } from '@app/view/form/add-new-expression.component';
import { FormState } from '@app/store/form/form.state';
import * as FormSelectors from '@app/store/form/form.selectors';
import * as FormActions from '@app/store/form/form.actions';
import { MatRadioChange } from '@angular/material/radio';
import { MatCheckboxChange } from '@angular/material/checkbox';
import { FormFieldVO } from '@app/model/bw/org/bocra/portal/form/field/form-field-vo';

@Component({
  selector: 'app-add-new-expression',
  templateUrl: './add-new-expression.component.html',
  styleUrls: ['./add-new-expression.component.scss']
})
export class AddNewExpressionComponentImpl extends AddNewExpressionComponent {
    

    constructor(private injector: Injector) {
        super(injector);
    }

    beforeOnInit(){     
    }
	
    afterOnInit() {
    }

    doNgAfterViewInit() {
    }

    doNgOnDestroy(){}

    handleFormChanges(change: any) {
    }

    handleExpressionFormFieldAddDialog(): void {}

    handleExpressionFormFieldSearch(): void {}
    
    handleExpressionFormFieldSelected(event: MatRadioChange, data: FormFieldVO): void {}
    
    handleExpressionRootAddDialog(): void {}

    handleExpressionRootSearch(): void {}
    
    handleExpressionRootSelected(event: MatRadioChange, data: FormFieldVO): void {}
    
    /**
     * This method may be overwritten
     */
    afterSetAddNewExpressionCancelForm(form: AddNewExpressionCancelForm): void {

    }

    /**
     * This method may be overwritten
     */
    beforeAddNewExpressionCancel(form: AddNewExpressionCancelForm): void {

    }

    /**
     * This method may be overwritten
     */
    afterAddNewExpressionCancel(form: AddNewExpressionCancelForm): void {

    }
    
    /**
     * This method may be overwritten
     */
    afterSetAddNewExpressionDoneForm(form: AddNewExpressionDoneForm): void {

    }

    /**
     * This method may be overwritten
     */
    beforeAddNewExpressionDone(form: AddNewExpressionDoneForm): void {

    }

    /**
     * This method may be overwritten
     */
    afterAddNewExpressionDone(form: AddNewExpressionDoneForm): void {

    }
    

    handleCancelDialog(): void {

    }

    handleDialogDone(data: any): any {
        return data;
    }

    afterSetAddNewExpressionVarsForm(form: AddNewExpressionVarsForm): void {
    }
}