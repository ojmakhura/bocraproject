// Generated by andromda-angular cartridge (view\use-case.module.ts.vsl) DO NOT EDIT
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TranslateModule } from '@ngx-translate/core';
import { FlexLayoutModule } from '@angular/flex-layout';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { SharedModule } from '@shared';
import { CsvModule } from '@ctrl/ngx-csv';
import { MaterialModule } from '@app/material.module';
import { ReportRoutingModule } from './report-routing.module';
import { ReportComponentImpl } from '@app/view/report/report.component.impl'; // 1
import { SearchComponentImpl } from '@app/view/report/search.component.impl'; // 3
import { ReportFormSubmissionsComponentImpl } from '@app/view/report/report-form-submissions.component.impl'; // 2
import { StoreModule } from '@ngrx/store';
import { EffectsModule } from '@ngrx/effects';
import { reportFeature } from '@app/store/report/report.reducers';
import { ReportEffects } from '@app/store/report/report.effects';
import { ReportControllerImpl } from '@app/controller/report/report-controller.impl';
import { NgChartsModule } from 'ng2-charts';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    TranslateModule,
    SharedModule,
    FlexLayoutModule,
    MaterialModule,
    CsvModule,
    ReportRoutingModule,
    SharedModule,
    NgChartsModule,
    StoreModule.forFeature(reportFeature),
    EffectsModule.forFeature([ ReportEffects ])
  ],
  declarations: [
    ReportComponentImpl,
    SearchComponentImpl,
    SearchComponentImpl,
    ReportFormSubmissionsComponentImpl,
  ],
  entryComponents: [],
  providers: [
    ReportControllerImpl,
  ],
})
export class ReportModule {}