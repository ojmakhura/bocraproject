// `.env.ts` is generated by the `npm run env` command
// `npm run env` exposes environment variables as JSON for any usage you might
// want, like displaying the version or getting extra config from your CI bot, etc.
// This is useful for granularity you might need beyond just the environment.
// Note that as usual, any environment variables you expose through it will end up in your
// bundle, and you should not use it for any sensitive information like passwords or keys.
import { env } from './.env';

export const environment = {
  production: true,
  version: env['npm_package_version'] + '-dev',
  serverUrl: 'http://192.168.57.4:8085',
  defaultLanguage: 'en-US',
  supportedLanguages: ['en-US'],
  bocraApiServer: 'http://192.168.57.4:8085',
  bocraKeycloakServer: 'http://192.168.57.3:8080',
  keycloakRealm: 'bocraportal',
  webClientId: '5bf59357-eafb-4de3-baff-dc98f1d9bab7',
  redirectUri: 'http://192.168.57.6',
  keycloakClientRoleUrl:
    'http://192.168.57.3:8080/auth/admin/realms/bocraportal/clients/5bf59357-eafb-4de3-baff-dc98f1d9bab7/roles',
  keycloakRealmRoleUrl: 'http://192.168.57.3:8080/auth/admin/realms/bocraportal/roles',
  keycloak: {
    issuer: 'http://192.168.57.3:8080/auth',
    redirectUri: window.location.origin,
    clientId: 'bocraportal-web',
    scope: 'openid profile email offline_access',
    responseType: 'code',
    realm: 'bocraportal',
    // at_hash is not present in JWT token
    disableAtHashCheck: true,
    showDebugInformation: true,
    requireHttps: false,
    checkLoginIframe: false,
  },
};
