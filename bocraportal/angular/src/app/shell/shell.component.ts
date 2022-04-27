import { Title } from '@angular/platform-browser';
import { AfterViewInit, Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Breakpoints, BreakpointObserver } from '@angular/cdk/layout';
import { KeycloakService } from 'keycloak-angular';
import { UrlGuardRestControllerImpl } from '@app/service/bw/org/bocra/portal/guard/url-guard-rest-controller.impl';
import { UrlGuardCriteria } from '@app/model/bw/org/bocra/portal/guard/url-guard-criteria';
import { UrlGuardType } from '@app/model/bw/org/bocra/portal/guard/url-guard-type';
import * as nav from './navigation';
import { Observable } from 'rxjs';
import { Menu } from '@app/model/menu/menu';
import { Store, select } from '@ngrx/store';
import { AuthState } from '@app/store/auth/auth.state';
import * as AuthSelectors from '@app/store/auth/auth.selectors';
import * as AuthActions from '@app/store/auth/auth.actions';
import * as MenuSelectors from '@app/store/menu/menu.selectors';
import * as MenuActions from '@app/store/menu/menu.actions';

@Component({
  selector: 'app-shell',
  templateUrl: './shell.component.html',
  styleUrls: ['./shell.component.scss'],
})
export class ShellComponent implements OnInit, AfterViewInit {
  menus: any[] = [];
  menus$: Observable<Menu[]>;
  username$: Observable<string>;

  constructor(
    private router: Router,
    private titleService: Title,
    private keycloakService: KeycloakService,
    private store: Store<AuthState>,
    private breakpoint: BreakpointObserver,
    private urlGuardRestController: UrlGuardRestControllerImpl
  ) {
    this.menus$ = this.store.pipe(select(MenuSelectors.selectMenus));
    this.username$ = this.store.pipe(select(AuthSelectors.selectUsername));
  }

  ngOnInit() {}

  ngAfterViewInit(): void {
    let criteria: UrlGuardCriteria = new UrlGuardCriteria();
    criteria.type = UrlGuardType.MENU;
    criteria.roles = this.keycloakService.getUserRoles();

    this.keycloakService.isLoggedIn().then(loggedIn => {
      if(loggedIn) {
        this.store.dispatch(AuthActions.setUsername({ username: this.keycloakService.getUsername() }));
      }
    });

    this.urlGuardRestController.search(criteria).subscribe((guards) => {

      let menuItems = nav.menuItems.filter(menu => guards.some(guard => guard.url === menu.routerLink))
      this.store.dispatch(MenuActions.getMenusSuccess({ menus: menuItems}));
      
    });
  }

  logout() {
    this.keycloakService.logout().then(() => {
      this.store.dispatch(AuthActions.authReset());
      this.store.dispatch(MenuActions.menuReset());
    });
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
