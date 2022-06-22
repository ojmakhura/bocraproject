// Generated by andromda-angular cartridge (view\view.component.imp.ts.vsl) CAN EDIT!
import { Component, Injector } from '@angular/core';
import { EditExpressionComponent } from '@app/view/form/edit-expression.component';
import { EditExpressionVarsForm } from '@app/view/form/edit-expression.component';
import { FormState } from '@app/store/form/form.state';
import * as FormSelectors from '@app/store/form/form.selectors';
import * as FormActions from '@app/store/form/form.actions';
import { MatRadioChange } from '@angular/material/radio';
import { MatCheckboxChange } from '@angular/material/checkbox';

@Component({
  selector: 'app-edit-expression',
  templateUrl: './edit-expression.component.html',
  styleUrls: ['./edit-expression.component.scss']
})
export class EditExpressionComponentImpl extends EditExpressionComponent {

    constructor(private injector: Injector) {
        super(injector);
    }

    override beforeOnInit(form: EditExpressionVarsForm): EditExpressionVarsForm{     
        return form;
    }
	
    override doNgOnDestroy(){}
}