// Generated by andromda-angular cartridge (view\use-case.module.ts.vsl) DO NOT EDIT
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TranslateModule } from '@ngx-translate/core';
import { FlexLayoutModule } from '@angular/flex-layout';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { SharedModule } from '@shared';
import { CsvModule } from '@ctrl/ngx-csv';
import { MaterialModule } from '@app/material.module';
import { ComplaintRoutingModule } from './complaint-routing.module';
import { EditComplaintComponentImpl } from '@app/view/complaint/edit-complaint.component.impl'; // 1
import { ComplaintDocumentComponentImpl } from '@app/view/complaint/complaint-document.component.impl'; // 3
import { ReplyComponentImpl } from '@app/view/complaint/reply.component.impl'; // 3
import { SearchComplaintsComponentImpl } from '@app/view/complaint/search-complaints.component.impl'; // 1
import { SearchComplaintsComplaintsComponentImpl } from '@app/view/complaint/search-complaints-complaints.component.impl'; // 2
import { ReplyDocumentComponentImpl } from '@app/view/complaint/reply-document.component.impl'; // 3
import { ComplaintsAnalysisComponentImpl } from '@app/view/complaint/complaints-analysis.component.impl'; // 1
import { StoreModule } from '@ngrx/store';
import { EffectsModule } from '@ngrx/effects';
import { complaintFeature } from '@app/store/complaint/complaint.reducers';
import { ComplaintEffects } from '@app/store/complaint/complaint.effects';
import { ComplaintControllerImpl } from '@app/controller/complaint/complaint-controller.impl';
import { ComplaintRestController } from '@app/service/bw/org/bocra/portal/complaint/complaint-rest-controller';
import { NgChartsModule } from 'ng2-charts';
import 'chart.js';
import { complaintTypeFeature } from '@app/store/complaint/type/complaint-type.reducers';
import { ComplaintTypeEffects } from '@app/store/complaint/type/complaint-type.effects';

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
    ComplaintRoutingModule,
    SharedModule,
    NgChartsModule,
    StoreModule.forFeature(complaintFeature),
    StoreModule.forFeature(complaintTypeFeature),
    EffectsModule.forFeature([ ComplaintEffects, ComplaintTypeEffects])
  ],
  declarations: [
    EditComplaintComponentImpl,
    ComplaintDocumentComponentImpl,
    ReplyComponentImpl,
    SearchComplaintsComponentImpl,
    SearchComplaintsComplaintsComponentImpl,
    ComplaintDocumentComponentImpl,
    ReplyComponentImpl,
    ReplyDocumentComponentImpl,
    ReplyDocumentComponentImpl,
    ComplaintsAnalysisComponentImpl,
  ],
  entryComponents: [],
  providers: [
    ComplaintControllerImpl,
    ComplaintRestController,
  ],
})
export class ComplaintModule {}
