import { Title } from '@angular/platform-browser';
import { AfterViewInit, Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Breakpoints, BreakpointObserver } from '@angular/cdk/layout';
import { KeycloakService } from 'keycloak-angular';
import { UrlGuardRestControllerImpl } from '@app/service/bw/org/bocra/portal/guard/url-guard-rest-controller.impl';
import { UrlGuardCriteria } from '@app/model/bw/org/bocra/portal/guard/url-guard-criteria';
import { UrlGuardType } from '@app/model/bw/org/bocra/portal/guard/url-guard-type';
import * as nav from './navigation';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-shell',
  templateUrl: './shell.component.html',
  styleUrls: ['./shell.component.scss']
})
export class ShellComponent implements OnInit, AfterViewInit {

  menus: any[] = [];
  constructor(private router: Router,
    private titleService: Title,
    private keycloakService: KeycloakService,
    private http: HttpClient,
    private breakpoint: BreakpointObserver,
    private urlGuardRestController: UrlGuardRestControllerImpl) 
  { }

  ngOnInit() { 
  }

  ngAfterViewInit(): void {
    let criteria: UrlGuardCriteria = new UrlGuardCriteria;
    criteria.type = UrlGuardType.MENU;
    criteria.roles = this.keycloakService.getUserRoles();

    this.urlGuardRestController.search(criteria).subscribe(guards => {
      nav.menuItems.forEach(value => {
        guards.find(guard => {
          if(guard.url === value.routerLink) {
            if(!this.menus.find(menu => menu.routerLink === guard.url)){
              this.menus.push(value);
            }
          }
        })
      })
    })
  }

  logout() {
    this.keycloakService.logout();
  }

  get username(): string | null {
    const credentials = this.keycloakService.getUsername();
    return credentials ? credentials : null;
  }

  get isMobile(): boolean {
    return this.breakpoint.isMatched(Breakpoints.Small) || this.breakpoint.isMatched(Breakpoints.XSmall);
  }

  get title(): string {
    return this.titleService.getTitle();
  }
}
