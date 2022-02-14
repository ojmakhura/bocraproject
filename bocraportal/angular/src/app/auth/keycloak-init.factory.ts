import { environment } from "@env/environment";
import { KeycloakService } from "keycloak-angular";

export function initializeKeycloak(keycloak: KeycloakService) {
    return () =>
      keycloak.init({
        config: {
          url: environment.keycloak.issuer,
          realm: environment.keycloak.realm,
          clientId: environment.keycloak.clientId,
        },
        initOptions: {
           onLoad: 'check-sso',
           //checkLoginIframe: true
        }   
      });
}