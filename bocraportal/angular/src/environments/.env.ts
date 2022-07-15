require('dotenv').config();

export const env = {
  npm_package_version: '1.0.0',
  keycloakBaseUrl: process.env['KEYCLOAK_AUTH_URL'],
  keycloakRealm: process.env['KEYCLOAK_REALM'],
  apiUrl: process.env['API_URL'],
  redirectUri: process.env['WEB_URL'],
  keycloakClientId: process.env['KEYCLOAK_WEB_CLIENT_ID'],
  clientId: process.env['KEYCLOAK_WEB_CLIENT'],
};
