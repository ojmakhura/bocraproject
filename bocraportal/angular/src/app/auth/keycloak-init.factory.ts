import { environment } from "@env/environment";
import { KeycloakService } from "keycloak-angular";

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
          // If set a false you cannot get any information about the user example the username
          // if you use keycloakService.getUserName() you get this error
          // User not logged in or user profile was not loaded.
          loadUserProfileAtStartUp: true,
          initOptions: {
            //   This is an action we specified on keycloak load
            //   We have two options : 'login-required'|'check-sso'
            //   If is set to 'login-required' this means your browser will do a full redirect to the Keycloak server and back to your application.
            //   If is set to  'check-sso'  instead this action will be performed in a hidden iframe, so your application resources only need to be loaded and parsed once by the browser.
            //   Then you will need to add the silentCheckSsoRedirectUri and create a html file  silent-check-sso.html with this content
            // <html>
            //    <body>
            //         <script>
            //           parent.postMessage(location.href, location.origin);
            //         </script>
            //      </body>
            // </html>
            onLoad: 'login-required',
            // onLoad: 'check-sso',
            checkLoginIframe: false,
            // silentCheckSsoRedirectUri: window.location.origin + '/assets/silent-check-sso.html',
          },
          bearerExcludedUrls: ['/assets', '/home', '/'],
        });
        resolve(resolve);
      } catch (error) {
        reject(error);
      }
    });
  };
}
