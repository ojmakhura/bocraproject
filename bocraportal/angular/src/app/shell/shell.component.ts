import { Title } from '@angular/platform-browser';
import { AfterViewInit, Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Breakpoints, BreakpointObserver } from '@angular/cdk/layout';
import { KeycloakService } from 'keycloak-angular';
import { AuthorisationRestControllerImpl } from '@app/service/bw/org/bocra/portal/auth/authorisation-rest-controller.impl';
import { AuthorisationCriteria } from '@app/model/bw/org/bocra/portal/auth/authorisation-criteria';
import * as nav from './navigation';
import { Observable } from 'rxjs';
import { Menu } from '@app/model/menu/menu';
import { Store, select } from '@ngrx/store';
import { AuthState } from '@app/store/auth/auth.state';
import * as AuthSelectors from '@app/store/auth/auth.selectors';
import * as AuthActions from '@app/store/auth/auth.actions';
import * as MenuSelectors from '@app/store/menu/menu.selectors';
import * as MenuActions from '@app/store/menu/menu.actions';
import { AuthorisationVO } from '@app/model/bw/org/bocra/portal/auth/authorisation-vo';

@Component({
  selector: 'app-shell',
  templateUrl: './shell.component.html',
  styleUrls: ['./shell.component.scss'],
})
export class ShellComponent implements OnInit, AfterViewInit {
  menus: any[] = [];
  menus$: Observable<Menu[]>;
  username$: Observable<string>;
  toggled = false;

  constructor(
    private router: Router,
    private titleService: Title,
    private keycloakService: KeycloakService,
    private store: Store<AuthState>,
    private breakpoint: BreakpointObserver,
    private authorisationRestController: AuthorisationRestControllerImpl
  ) {
    this.menus$ = this.store.pipe(select(MenuSelectors.selectMenus));
    this.username$ = this.store.pipe(select(AuthSelectors.selectUsername));
  }

  ngOnInit() { }

  ngAfterViewInit(): void {
    this.keycloakService.isLoggedIn().then((loggedIn) => {
      if (loggedIn) {
        this.store.dispatch(AuthActions.setUsername({ username: this.keycloakService.getUsername() }));
      }
    });

    let auths = new Set();

    this.authorisationRestController
      .getAccessTypeCodeAuthorisations(this.keycloakService.getUserRoles(), 'MENU')
      .subscribe((authorisations) => {
        authorisations.forEach((authorisation: AuthorisationVO) => {
          let menu: Menu = nav.menuItems.find((item) => authorisation.accessPoint.url === item.routerLink);
          if (menu) {
            let m: Menu = new Menu();
            m.icon = authorisation.accessPoint.icon ? authorisation.accessPoint.icon : "fa-puzzle-piece";
            m.routerLink = menu.routerLink;
            m.titleKey = authorisation.accessPoint.name;
            this.store.dispatch(MenuActions.addMenu({ menu: m }));
          }
        });
      });

    // this.keycloakService.getToken().then((token) => {
    //   var decoded: any = jwt_decode(token);
    // });
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