import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TranslateModule } from '@ngx-translate/core';
import { FlexLayoutModule } from '@angular/flex-layout';

import { SharedModule } from '@shared';
import { MaterialModule } from '@app/material.module';
import { HomeRoutingModule } from './home-routing.module';
import { HomeComponent } from './home.component';
import { SanitizeHtml } from '@app/pipe/sanitize-html.pipe';

@NgModule({
  imports: [CommonModule, TranslateModule, SharedModule, FlexLayoutModule, MaterialModule, HomeRoutingModule],
  declarations: [HomeComponent, SanitizeHtml],
})
export class HomeModule {}
