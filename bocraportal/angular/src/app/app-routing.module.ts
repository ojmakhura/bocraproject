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
      path: 'licensetype', 
      loadChildren: async () => (await import('@app/view/type/license-type.module')).LicenseTypeModule
    },
    {
      path: 'user', 
      loadChildren: async () => (await import('@app/view/user/user.module')).UserModule
    },
    {
      path: 'guard', 
      loadChildren: async () => (await import('@app/view/guard/guard.module')).GuardModule
    },
    {
      path: 'period', 
      loadChildren: async () => (await import('@app/view/period/period.module')).PeriodModule
    },
    {
      path: 'periodconfig', 
      loadChildren: async () => (await import('@app/view/period/config/period-config.module')).PeriodConfigModule
    },
    {
      path: 'form', 
      loadChildren: async () => (await import('@app/view/form/form.module')).FormModule
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
