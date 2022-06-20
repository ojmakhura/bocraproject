// Generated by andromda-jsf cartridge (app.module.ts.vsl) DO NOT EDIT!
// license-header angular merge-point
//
/**
 * @author Generated by app.module.ts.vsl in andromda-anglar-cartridge Do not modify by hand!
 *
 * MODEL CLASS:  $validationName
 */
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { ServiceWorkerModule } from '@angular/service-worker';
import { TranslateModule } from '@ngx-translate/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MaterialModule } from './material.module';

import { environment } from '@env/environment';
import { CoreModule } from '@core';
import { SharedModule } from '@shared';
import { HomeModule } from './home/home.module';
import { ShellModule } from './shell/shell.module';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SanitizeHtml } from './pipe/sanitize-html.pipe';
import { EffectsModule } from '@ngrx/effects';
import { StoreModule } from '@ngrx/store';
import { reducers, metaReducers } from './reducers';
import { StoreDevtoolsModule } from '@ngrx/store-devtools';
//import { ConnectFormDirective } from './connect-form.directive';
import { LicenseeModule } from '@app/view/licensee/licensee.module';
import { LicenseeControllerImpl } from '@app/controller/licensee/licensee-controller.impl';
import { UserModule } from '@app/view/user/user.module';
import { UserControllerImpl } from '@app/controller/user/user-controller.impl';
import { AuthorisationModule } from '@app/view/auth/authorisation.module';
import { AuthorisationControllerImpl } from '@app/controller/auth/authorisation-controller.impl';
import { PeriodModule } from '@app/view/period/period.module';
import { PeriodControllerImpl } from '@app/controller/period/period-controller.impl';
import { PeriodConfigModule } from '@app/view/period/config/period-config.module';
import { PeriodConfigControllerImpl } from '@app/controller/period/config/period-config-controller.impl';
import { FormModule } from '@app/view/form/form.module';
import { FormControllerImpl } from '@app/controller/form/form-controller.impl';
import { FormSubmissionModule } from '@app/view/form/submission/form-submission.module';
import { FormSubmissionControllerImpl } from '@app/controller/form/submission/form-submission-controller.impl';
import { DashboardModule } from '@app/view/dashboard/dashboard.module';
import { DashboardControllerImpl } from '@app/controller/dashboard/dashboard-controller.impl';
import { LicenceTypeModule } from '@app/view/licence/type/licence-type.module';
import { LicenceTypeControllerImpl } from '@app/controller/licence/type/licence-type-controller.impl';
import { LicenceModule } from '@app/view/licence/licence.module';
import { LicenceControllerImpl } from '@app/controller/licence/licence-controller.impl';
import { DocumentTypeModule } from '@app/view/document/type/document-type.module';
import { DocumentTypeControllerImpl } from '@app/controller/document/type/document-type-controller.impl';
import { DocumentModule } from '@app/view/document/document.module';
import { DocumentControllerImpl } from '@app/controller/document/document-controller.impl';
import { SectorModule } from '@app/view/sector/sector.module';
import { SectorControllerImpl } from '@app/controller/sector/sector-controller.impl';
import { UseCaseScope } from '@app/utils/use-case-scope';
import { FormSectionRestControllerImpl } from '@app/service/bw/org/bocra/portal/form/section/form-section-rest-controller.impl';
import { PeriodRestControllerImpl } from '@app/service/bw/org/bocra/portal/period/period-rest-controller.impl';
import { ExpressionControllerImpl } from '@app/service/bw/org/bocra/portal/expression/expression-controller.impl';
import { DocumentRestControllerImpl } from '@app/service/bw/org/bocra/portal/document/document-rest-controller.impl';
import { FormRestControllerImpl } from '@app/service/bw/org/bocra/portal/form/form-rest-controller.impl';
import { DocumentTypeRestControllerImpl } from '@app/service/bw/org/bocra/portal/document/type/document-type-rest-controller.impl';
import { LicenceRestControllerImpl } from '@app/service/bw/org/bocra/portal/licence/licence-rest-controller.impl';
import { UserRestControllerImpl } from '@app/service/bw/org/bocra/portal/user/user-rest-controller.impl';
import { LicenseeRestControllerImpl } from '@app/service/bw/org/bocra/portal/licensee/licensee-rest-controller.impl';
import { SubmissionRestControllerImpl } from '@app/service/bw/org/bocra/portal/form/submission/submission-rest-controller.impl';
import { FormFieldRestControllerImpl } from '@app/service/bw/org/bocra/portal/form/field/form-field-rest-controller.impl';
import { LicenceTypeRestControllerImpl } from '@app/service/bw/org/bocra/portal/licence/type/licence-type-rest-controller.impl';
import { SectorRestControllerImpl } from '@app/service/bw/org/bocra/portal/sector/sector-rest-controller.impl';
import { AuthorisationRestControllerImpl } from '@app/service/bw/org/bocra/portal/auth/authorisation-rest-controller.impl';
import { PeriodConfigRestControllerImpl } from '@app/service/bw/org/bocra/portal/period/config/period-config-rest-controller.impl';
import { AuthModule } from './auth';
true

@NgModule({
    declarations: [
        AppComponent,
    ],
    imports: [
        BrowserModule,
        ServiceWorkerModule.register('./ngsw-worker.js', { enabled: environment.production }),
        FormsModule,
        HttpClientModule,
        TranslateModule.forRoot(),
        BrowserAnimationsModule,
        MaterialModule,
        CoreModule,
        SharedModule,
        ShellModule,
        HomeModule,
        EffectsModule.forRoot([]),
        StoreModule.forRoot({}),
        StoreDevtoolsModule.instrument({}),
        LicenseeModule,
        UserModule,
        AuthorisationModule,
        PeriodModule,
        PeriodConfigModule,
        FormModule,
        FormSubmissionModule,
        DashboardModule,
        LicenceTypeModule,
        LicenceModule,
        DocumentTypeModule,
        DocumentModule,
        SectorModule,
        AuthModule,
        AppRoutingModule, // must be imported as the last module as it contains the fallback route
    ],
    exports: [
    ],
    providers: [
        UseCaseScope,
        FormSectionRestControllerImpl,
        PeriodRestControllerImpl,
        ExpressionControllerImpl,
        DocumentRestControllerImpl,
        FormRestControllerImpl,
        DocumentTypeRestControllerImpl,
        LicenceRestControllerImpl,
        UserRestControllerImpl,
        LicenseeRestControllerImpl,
        SubmissionRestControllerImpl,
        FormFieldRestControllerImpl,
        LicenceTypeRestControllerImpl,
        SectorRestControllerImpl,
        AuthorisationRestControllerImpl,
        PeriodConfigRestControllerImpl,
        LicenseeControllerImpl,
        UserControllerImpl,
        AuthorisationControllerImpl,
        PeriodControllerImpl,
        PeriodConfigControllerImpl,
        FormControllerImpl,
        FormSubmissionControllerImpl,
        DashboardControllerImpl,
        LicenceTypeControllerImpl,
        LicenceControllerImpl,
        DocumentTypeControllerImpl,
        DocumentControllerImpl,
        SectorControllerImpl,
    ],
    bootstrap: [AppComponent]
})
export class AppModule { }
