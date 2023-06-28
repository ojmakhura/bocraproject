import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { AfterViewInit, Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { AuthorisationVO } from '@app/model/bw/org/bocra/portal/auth/authorisation-vo';
import { SystemConfigVO } from '@app/model/bw/org/bocra/portal/config/system-config-vo';
import { Menu } from '@app/model/menu/menu';
import { AuthorisationRestController } from '@app/service/bw/org/bocra/portal/auth/authorisation-rest-controller';
import { SystemConfigRestController } from '@app/service/bw/org/bocra/portal/config/system-config-rest-controller';
import * as AuthActions from '@app/store/auth/auth.actions';
import * as AuthSelectors from '@app/store/auth/auth.selectors';
import { AuthState } from '@app/store/auth/auth.state';
import * as MenuActions from '@app/store/menu/menu.actions';
import * as MenuSelectors from '@app/store/menu/menu.selectors';
import * as SystemConfigActions from '@app/store/config/system-config.actions';
import * as SystemConfigSelectors from '@app/store/config/system-config.selectors';
import { environment } from '@env/environment';
import { select, Store } from '@ngrx/store';
import { KeycloakService } from 'keycloak-angular';
import { from, Observable, of } from 'rxjs';
import * as nav from './navigation';

@Component({
  selector: 'app-shell',
  templateUrl: './shell.component.html',
  styleUrls: ['./shell.component.scss'],
})
export class ShellComponent implements OnInit, AfterViewInit {
  menus: any[] = [];
  menus$: Observable<Menu[]>;
  username$: Observable<string>;
  isLoggedIn: Observable<boolean> = of(false);
  toggled = false;
  accoutUri: string;
  configs$: Observable<SystemConfigVO[]>

  constructor(
    private router: Router,
    private titleService: Title,
    private keycloakService: KeycloakService,
    private store: Store<AuthState>,
    private breakpoint: BreakpointObserver,
    private authorisationRestController: AuthorisationRestController,
    private systemConfigRestController: SystemConfigRestController
  ) {
    this.menus$ = this.store.pipe(select(MenuSelectors.selectMenus));
    this.username$ = this.store.pipe(select(AuthSelectors.selectUsername));
    this.configs$ = this.store.pipe(select(SystemConfigSelectors.selectSystemConfigs));
  }

  ngOnInit() {}

  ngAfterViewInit(): void {
    this.keycloakService.isLoggedIn().then((loggedIn) => {
      if (loggedIn) {
        this.accoutUri = 
          `${environment.keycloak.issuer}/realms/${environment.keycloak.realm}/account?referrer=' + ${encodeURIComponent(environment.keycloak.clientId)}&referrer_uri=' + ${encodeURIComponent(environment.keycloak.redirectUri)}`;
        this.isLoggedIn = of(loggedIn);
        this.store.dispatch(AuthActions.setUsername({ username: this.keycloakService.getUsername() }));
        this.authorisationRestController
          .getAccessTypeCodeAuthorisations(this.keycloakService.getUserRoles(), 'MENU')
          .subscribe((authorisations) => {
            authorisations.forEach((authorisation: AuthorisationVO) => {
              let menu: Menu = nav.menuItems.find((item) => authorisation.accessPoint.url === item.routerLink);
              if (menu) {
                let m: Menu = new Menu();
                m.icon = authorisation.accessPoint.icon ? authorisation.accessPoint.icon : 'fa-puzzle-piece';
                m.routerLink = menu.initialView;
                m.titleKey = authorisation.accessPoint.name;
                this.store.dispatch(MenuActions.addMenu({ menu: m }));
              }
            });
          });

        this.store.dispatch(SystemConfigActions.getAll({
          loaderMessage: "Loading all configs",
          loading: true
        }));

        this.configs$.subscribe(cfgs => {
          this.systemConfigRestController.systemConfigs = cfgs;
        })
      }
    });
  }

  logout() {
    this.keycloakService.logout(environment.redirectUri).then(() => {
      this.store.dispatch(AuthActions.authReset());
      this.store.dispatch(MenuActions.menuReset());
    });
  }
  
  login() {
    this.keycloakService.login({
      redirectUri: window.location.origin,
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
