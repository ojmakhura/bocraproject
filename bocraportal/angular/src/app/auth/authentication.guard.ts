import { Injectable } from '@angular/core';
import {
  ActivatedRouteSnapshot,
  Router,
  RouterStateSnapshot,
} from '@angular/router';
import { KeycloakAuthGuard, KeycloakService } from 'keycloak-angular';
import { or } from 'mathjs';

@Injectable({
  providedIn: 'root',
})
export class AuthenticationGuard extends KeycloakAuthGuard {
  constructor(
    protected readonly router$: Router,
    protected readonly keycloak: KeycloakService
  ) {
    super(router$, keycloak);
  }

  public async isAccessAllowed(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ) {

    console.log(state.url)

    if(state.url === '/' || state.url === '/assets' || state.url === '/about' || state.url === '/home' || state.url === '/contact') {
      return true;
    }

    // Force the user to log in if currently unauthenticated.
    if (!this.authenticated) {
      await this.keycloak.login({
        redirectUri: window.location.origin + state.url,
      });
    }

    // Get the roles required from the route.
    const requiredRoles = route.data['roles'];

    // Allow the user to to proceed if no additional roles are required to access the route.
    if (!(requiredRoles instanceof Array) || requiredRoles.length === 0) {
      return true;
    }

    // Allow the user to proceed if all the required roles are present.
    if (requiredRoles.every((role: any) => this.roles.includes(role))) {
      return true;
    } else {
      // redirect to error page if the user doesn't have the nessecairy  role to access
      // we will define this routes in a bit
      this.router.navigate(['access-denied']);
      return false;
    }
  }
}