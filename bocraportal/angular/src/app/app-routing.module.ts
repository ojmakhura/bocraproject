// license-header angular merge-point
//
/**
 * @author Generated by app-routing.module.ts.vsl in andromda-anglar-cartridge Do not modify by hand!
 *
 * MODEL CLASS:  $validationName
 */
import { NgModule } from '@angular/core';
import { Routes, RouterModule, PreloadAllModules } from '@angular/router';
import { Shell } from '@app/shell/shell.service';

const routes: Routes = [
  Shell.childRoutes([
    {
      path: 'about',
      loadChildren: async () => (await import('./about/about.module')).AboutModule
    },
    {
      path: 'licensee', 
      loadChildren: async () => (await import('@app/view/licensee/licensee.module')).LicenseeModule
    },
    {
      path: 'user', 
      loadChildren: async () => (await import('@app/view/user/user.module')).UserModule
    },
    {
      path: 'authorisation', 
      loadChildren: async () => (await import('@app/view/auth/authorisation.module')).AuthorisationModule
    },
    {
      path: 'period', 
      loadChildren: async () => (await import('@app/view/period/period.module')).PeriodModule
    },
    {
      path: 'period/config', 
      loadChildren: async () => (await import('@app/view/period/config/period-config.module')).PeriodConfigModule
    },
    {
      path: 'form', 
      loadChildren: async () => (await import('@app/view/form/form.module')).FormModule
    },
    {
      path: 'form/submission', 
      loadChildren: async () => (await import('@app/view/form/submission/form-submission.module')).FormSubmissionModule
    },
    {
      path: 'form/activation', 
      loadChildren: async () => (await import('@app/view/form/activation/form-activation.module')).FormActivationModule
    },
    {
      path: 'dashboard', 
      loadChildren: async () => (await import('@app/view/dashboard/dashboard.module')).DashboardModule
    },
    {
      path: 'licence/type', 
      loadChildren: async () => (await import('@app/view/licence/type/licence-type.module')).LicenceTypeModule
    },
    {
      path: 'licence', 
      loadChildren: async () => (await import('@app/view/licence/licence.module')).LicenceModule
    },
    {
      path: 'document/type', 
      loadChildren: async () => (await import('@app/view/document/type/document-type.module')).DocumentTypeModule
    },
    {
      path: 'document', 
      loadChildren: async () => (await import('@app/view/document/document.module')).DocumentModule
    },
    {
      path: 'sector', 
      loadChildren: async () => (await import('@app/view/sector/sector.module')).SectorModule
    },
    {
      path: 'access', 
      loadChildren: async () => (await import('@app/view/access/access-point.module')).AccessPointModule
    },
    {
      path: 'access/type', 
      loadChildren: async () => (await import('@app/view/access/type/access-point-type.module')).AccessPointTypeModule
    },
    {
      path: 'menu', 
      loadChildren: async () => (await import('@app/view/menu/menu-section.module')).MenuSectionModule
    },
  ]),
  // Fallback when no prior route is matched
  { 
    path: '**', redirectTo: '', pathMatch: 'full' 
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { preloadingStrategy: PreloadAllModules })],
  exports: [RouterModule],
  providers: [],
})
export class AppRoutingModule {}
