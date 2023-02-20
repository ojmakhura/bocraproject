// Generated by andromda-angular cartridge (view\use-case.module.ts.vsl) DO NOT EDIT
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TranslateModule } from '@ngx-translate/core';
import { FlexLayoutModule } from '@angular/flex-layout';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { SharedModule } from '@shared';
import { CsvModule } from '@ctrl/ngx-csv';
import { MaterialModule } from '@app/material.module';
import { FormRoutingModule } from './form-routing.module';
import { EditFormComponentImpl } from '@app/view/form/edit-form.component.impl'; // 1
import { EditSectionComponentImpl } from '@app/view/form/edit-section.component.impl'; // 3
import { EditLicenseeComponentImpl } from '@app/view/form/edit-licensee.component.impl'; // 3
import { SearchFormsComponentImpl } from '@app/view/form/search-forms.component.impl'; // 1
import { SearchFormsFormsComponentImpl } from '@app/view/form/search-forms-forms.component.impl'; // 2
import { EditFieldComponentImpl } from '@app/view/form/edit-field.component.impl'; // 1
import { StoreModule } from '@ngrx/store';
import { EffectsModule } from '@ngrx/effects';
import { formFeature } from '@app/store/form/form.reducers';
import { FormEffects } from '@app/store/form/form.effects';
import { FormControllerImpl } from '@app/controller/form/form-controller.impl';
import { FormSectionRestController } from '@app/service/bw/org/bocra/portal/form/section/form-section-rest-controller';
import { FormFieldRestController } from '@app/service/bw/org/bocra/portal/form/field/form-field-rest-controller';
import { FormRestController } from '@app/service/bw/org/bocra/portal/form/form-rest-controller';
import { licenseeFormFeature } from '@app/store/licensee/form/licensee-form.reducers';
import { LicenseeFormEffects } from '@app/store/licensee/form/licensee-form.effects';
import { sectorFormFeature } from '@app/store/sector/form/sector-form.reducers';
import { SectorFormEffects } from '@app/store/sector/form/sector-form.effects';
import { licenceTypeFormFeature } from '@app/store/licence/type/form/licence-type-form.reducers';
import { LicenceTypeFormEffects } from '@app/store/licence/type/form/licence-type-form.effects';
import { MatTableExporterModule } from 'mat-table-exporter';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    TranslateModule,
    SharedModule,
    FlexLayoutModule,
    MatTableExporterModule,
    MaterialModule,
    CsvModule,
    FormRoutingModule,
    StoreModule.forFeature(formFeature),
    StoreModule.forFeature(licenseeFormFeature),
    StoreModule.forFeature(licenceTypeFormFeature),
    StoreModule.forFeature(sectorFormFeature),
    EffectsModule.forFeature([FormEffects, LicenseeFormEffects, SectorFormEffects, LicenceTypeFormEffects]),
  ],
  declarations: [
    EditFormComponentImpl,
    EditSectionComponentImpl,
    EditLicenseeComponentImpl,
    SearchFormsComponentImpl,
    SearchFormsFormsComponentImpl,
    EditFieldComponentImpl,
  ],
  entryComponents: [],
  providers: [FormControllerImpl, FormSectionRestController, FormFieldRestController, FormRestController],
})
export class FormModule {}
