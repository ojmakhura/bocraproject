import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TranslateModule } from '@ngx-translate/core';
import { RouterModule } from '@angular/router';
import { FlexLayoutModule } from '@angular/flex-layout';
import { StoreModule } from '@ngrx/store';
import { EffectsModule } from '@ngrx/effects';

import { I18nModule } from '@app/i18n';
import { MaterialModule } from '@app/material.module';
import { AuthModule } from '@app/auth';
import { ShellComponent } from './shell.component';
import { useCaseFeature } from '@app/store/usecase/use-case.reducers';

import { authFeature } from '@app/store/auth/auth.reducers';
import { AuthEffects } from '@app/store/auth/auth.effects';
import { menuFeature } from '@app/store/menu/menu.reducers';
import { MenuEffects } from '@app/store/menu/menu.effects';

@NgModule({
  imports: [
    CommonModule,
    TranslateModule,
    FlexLayoutModule,
    MaterialModule,
    AuthModule,
    I18nModule,
    RouterModule,
    StoreModule.forFeature(useCaseFeature),
    StoreModule.forFeature(authFeature),
    StoreModule.forFeature(menuFeature),
    EffectsModule.forFeature([AuthEffects, MenuEffects])
  ],
  declarations: [
    ShellComponent
  ]
})
export class ShellModule {
}
