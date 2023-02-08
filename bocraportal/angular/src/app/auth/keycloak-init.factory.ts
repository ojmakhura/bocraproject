import { environment } from '@env/environment';
import { KeycloakService } from 'keycloak-angular';

export function initializeKeycloak(keycloak: KeycloakService): () => Promise<any> {
  return (): Promise<any> => {
    return new Promise(async (resolve, reject) => {
      try {
        await keycloak.init({
          config: {
            url: environment.keycloak.issuer,
            realm: environment.keycloak.realm,
            clientId: environment.keycloak.clientId,
          },
          loadUserProfileAtStartUp: true,
          initOptions: {
            onLoad: 'check-sso',
            silentCheckSsoRedirectUri: window.location.origin + '/assets/silent-check-sso.html',
          },
          bearerExcludedUrls: ['/assets', '/home', '/about', '/contact', '/complaint', '/complaint/search-complaints'],
          shouldAddToken: (request) => {
            const { method, url } = request;

            const isGetRequest = 'GET' === method.toUpperCase();
            const acceptablePaths = [
              '/assets',
              '/home',
              '/about',
              '/contact',
              '/complaint',
              '/complaint/search-complaints',
            ];
            const isAcceptablePathMatch = acceptablePaths.some((path) => url.includes(path));

            return !(isGetRequest && isAcceptablePathMatch);
          },
        });
        resolve(resolve);
      } catch (error) {
        reject(error);
      }
    });
  };
}
