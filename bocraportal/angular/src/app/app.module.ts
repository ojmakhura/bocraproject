// Generated by andromda-jsf cartridge (app.module.ts.vsl) DO NOT EDIT!
// license-header angular merge-point
//
/**
 * @author Generated by app.module.ts.vsl in andromda-anglar-cartridge Do not modify by hand!
 *
 * MODEL CLASS:  $validationName
 */
import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ServiceWorkerModule } from '@angular/service-worker';
import { TranslateModule } from '@ngx-translate/core';
import { MaterialModule } from './material.module';

import { CoreModule } from '@core';
import { environment } from '@env/environment';
import { EffectsModule } from '@ngrx/effects';
import { StoreModule } from '@ngrx/store';
import { StoreDevtoolsModule } from '@ngrx/store-devtools';
import { SharedModule } from '@shared';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeModule } from './home/home.module';
import { ShellModule } from './shell/shell.module';
//import { ConnectFormDirective } from './connect-form.directive';
import { UseCaseScope } from '@app/utils/use-case-scope';
import { AccessPointModule } from '@app/view/access/access-point.module';
import { AccessPointTypeModule } from '@app/view/access/type/access-point-type.module';
import { AuthorisationModule } from '@app/view/auth/authorisation.module';
import { DashboardModule } from '@app/view/dashboard/dashboard.module';
import { DocumentModule } from '@app/view/document/document.module';
import { DocumentTypeModule } from '@app/view/document/type/document-type.module';
import { FormActivationModule } from '@app/view/form/activation/form-activation.module';
import { FormModule } from '@app/view/form/form.module';
import { FormSubmissionModule } from '@app/view/form/submission/form-submission.module';
import { LicenceModule } from '@app/view/licence/licence.module';
import { LicenceTypeModule } from '@app/view/licence/type/licence-type.module';
import { LicenseeModule } from '@app/view/licensee/licensee.module';
import { MenuSectionModule } from '@app/view/menu/menu-section.module';
import { PeriodConfigModule } from '@app/view/period/config/period-config.module';
import { PeriodModule } from '@app/view/period/period.module';
import { SectorModule } from '@app/view/sector/sector.module';
import { UserModule } from '@app/view/user/user.module';
import { AuthModule } from './auth';

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
        FormActivationModule,
        DashboardModule,
        LicenceTypeModule,
        LicenceModule,
        DocumentTypeModule,
        DocumentModule,
        SectorModule,
        AccessPointModule,
        AccessPointTypeModule,
        MenuSectionModule,
        AuthModule,
        AppRoutingModule, // must be imported as the last module as it contains the fallback route
    ],
    exports: [
    ],
    providers: [
        UseCaseScope,
    ],
    bootstrap: [AppComponent]
})
export class AppModule { }
