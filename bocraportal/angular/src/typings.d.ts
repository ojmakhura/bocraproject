/*
 * Extra typings definitions
 */

// Allow .json files imports
declare module '*.json';

// SystemJS module definition
declare var module: NodeModule;
interface NodeModule {
  id: string;
}

declare var $ENV: Env;

interface Env {
  ENVIRONMENT: string;
  KEYCLOAK_AUTH_URL: string;
  API_URL: string;
  KEYCLOAK_REALM: string;
  BOCRA_API_ADDRESS: string;
  KEYCLOAK_WEB_CLIENT: string;
  REDIRECT_URI: string;
  KEYCLOAK_WEB_CLIENT_ID: string;
}
