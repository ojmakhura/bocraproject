// Generated by andromda-angular cartridge (view\view.component.imp.ts.vsl) CAN EDIT!
import { Component, Injector } from '@angular/core';
import { EditSectionComponent } from '@app/view/form/edit-section.component';
import { EditSectionVarsForm } from '@app/view/form/edit-section.component';
import { FormState } from '@app/store/form/form.state';
import * as FormSelectors from '@app/store/form/form.selectors';
import * as FormActions from '@app/store/form/form.actions';
import { MatRadioChange } from '@angular/material/radio';
import { MatCheckboxChange } from '@angular/material/checkbox';

@Component({
  selector: 'app-edit-section',
  templateUrl: './edit-section.component.html',
  styleUrls: ['./edit-section.component.scss']
})
export class EditSectionComponentImpl extends EditSectionComponent {

    constructor(private injector: Injector) {
        super(injector);
    }

    override beforeOnInit(form: EditSectionVarsForm): EditSectionVarsForm{     
        return form;
    }
	
    override doNgOnDestroy(){}
<<<<<<< HEAD

    
=======
>>>>>>> origin/ojm-dev
}