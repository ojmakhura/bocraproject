// Generated by andromda-angular cartridge (view\use-case.module.ts.vsl) DO NOT EDIT
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TranslateModule } from '@ngx-translate/core';
import { FlexLayoutModule } from '@angular/flex-layout';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { SharedModule } from '@shared';
import { CsvModule } from '@ctrl/ngx-csv';
import { MaterialModule } from '@app/material.module';
import { LicenseeRoutingModule } from './licensee-routing.module';
import { EditLicenseeComponentImpl } from '@app/view/licensee/edit-licensee.component.impl';
import { NewDocumentComponentImpl } from '@app/view/licensee/new-document.component.impl';
import { NewShareholderComponentImpl } from '@app/view/licensee/new-shareholder.component.impl';
import { SearchLicenseesComponentImpl } from '@app/view/licensee/search-licensees.component.impl';
import { SearchLicenseesLicenseesComponentImpl } from '@app/view/licensee/search-licensees-licensees.component.impl'; // 2
import { StoreModule } from '@ngrx/store';
import { EffectsModule } from '@ngrx/effects';
import { licenseeFeature } from '@app/store/licensee/licensee.reducers';
import { LicenseeEffects } from '@app/store/licensee/licensee.effects';
import { LicenseeControllerImpl } from '@app/controller/licensee/licensee-controller.impl';
import { DocumentRestController } from '@app/service/bw/org/bocra/portal/document/document-rest-controller';
import { LicenseeRestController } from '@app/service/bw/org/bocra/portal/licensee/licensee-rest-controller';
import { viewFeature } from '@app/store/view/view.reducers';
import { ViewEffects } from '@app/store/view/view.effects';
import { licenseeSectorFeature } from '@app/store/licensee/sector/licensee-sector.reducers';
import { LicenseeSectorEffects } from '@app/store/licensee/sector/licensee-sector.effects';
import { licenseeShareholderFeature } from '@app/store/licensee/shares/licensee-shareholder.reducers';
import { LicenseeShareholderEffects } from '@app/store/licensee/shares/licensee-shareholder.effects';
import { MatTableExporterModule } from 'mat-table-exporter';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    TranslateModule,
    SharedModule,
    MatTableExporterModule,
    FlexLayoutModule,
    MaterialModule,
    CsvModule,
    LicenseeRoutingModule,
    StoreModule.forFeature(licenseeFeature),
    StoreModule.forFeature(licenseeSectorFeature),
    StoreModule.forFeature(licenseeShareholderFeature),
    StoreModule.forFeature(viewFeature),
    EffectsModule.forFeature([LicenseeEffects, ViewEffects, LicenseeSectorEffects, LicenseeShareholderEffects]),
  ],
  declarations: [
    EditLicenseeComponentImpl,
    NewDocumentComponentImpl,
    NewShareholderComponentImpl,
    SearchLicenseesComponentImpl,
    SearchLicenseesLicenseesComponentImpl,
  ],
  entryComponents: [],
  providers: [LicenseeControllerImpl, DocumentRestController, LicenseeRestController],
})
export class LicenseeModule {}
