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
import { SanitizeHtml } from './pipe/SanitizeHtml';
import { EffectsModule } from '@ngrx/effects';
import { StoreModule } from '@ngrx/store';
import { reducers, metaReducers } from './reducers';
import { StoreDevtoolsModule } from '@ngrx/store-devtools';
//import { ConnectFormDirective } from './connect-form.directive';
import { LicenseeModule } from '@app/view/licensee/licensee.module';
import { LicenseeControllerImpl } from '@app/controller/licensee/licensee-controller.impl';
import { LicenseTypeModule } from '@app/view/type/license-type.module';
import { LicenseTypeControllerImpl } from '@app/controller/type/license-type-controller.impl';
import { UserModule } from '@app/view/user/user.module';
import { UserControllerImpl } from '@app/controller/user/user-controller.impl';
import { GuardModule } from '@app/view/guard/guard.module';
import { GuardControllerImpl } from '@app/controller/guard/guard-controller.impl';
import { PeriodModule } from '@app/view/period/period.module';
import { PeriodControllerImpl } from '@app/controller/period/period-controller.impl';
import { PeriodConfigModule } from '@app/view/period/config/period-config.module';
import { PeriodConfigControllerImpl } from '@app/controller/period/config/period-config-controller.impl';
import { FormModule } from '@app/view/form/form.module';
import { FormControllerImpl } from '@app/controller/form/form-controller.impl';
import { FormSubmissionModule } from '@app/view/form/submission/form-submission.module';
import { FormSubmissionControllerImpl } from '@app/controller/form/submission/form-submission-controller.impl';
import { UseCaseScope } from '@app/utils/use-case-scope';
import { FormRestControllerImpl } from '@app/service/bw/org/bocra/portal/form/form-rest-controller.impl';
import { LicenseeRestControllerImpl } from '@app/service/bw/org/bocra/portal/licensee/licensee-rest-controller.impl';
import { UrlGuardRestControllerImpl } from '@app/service/bw/org/bocra/portal/guard/url-guard-rest-controller.impl';
import { LicenseTypeRestControllerImpl } from '@app/service/bw/org/bocra/portal/type/license-type-rest-controller.impl';
import { PeriodRestControllerImpl } from '@app/service/bw/org/bocra/portal/period/period-rest-controller.impl';
import { UserRestControllerImpl } from '@app/service/bw/org/bocra/portal/user/user-rest-controller.impl';
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
        LicenseTypeModule,
        UserModule,
        GuardModule,
        PeriodModule,
        PeriodConfigModule,
        FormModule,
        FormSubmissionModule,
        AuthModule,
        AppRoutingModule, // must be imported as the last module as it contains the fallback route
    ],
    exports: [
    ],
    providers: [
        UseCaseScope,
        FormRestControllerImpl,
        LicenseeRestControllerImpl,
        UrlGuardRestControllerImpl,
        LicenseTypeRestControllerImpl,
        PeriodRestControllerImpl,
        UserRestControllerImpl,
        PeriodConfigRestControllerImpl,
        LicenseeControllerImpl,
        LicenseTypeControllerImpl,
        UserControllerImpl,
        GuardControllerImpl,
        PeriodControllerImpl,
        PeriodConfigControllerImpl,
        FormControllerImpl,
        FormSubmissionControllerImpl,
    ],
    bootstrap: [AppComponent]
})
export class AppModule { }
