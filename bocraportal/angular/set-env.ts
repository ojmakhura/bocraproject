
import {writeFile} from 'fs'
// Configure Angular `environment.ts` file path
const targetPath = './src/environments/.env.ts';
// Load node modules
const colors = require('colors');
require('dotenv').config();
// `environment.ts` file structure
const envConfigFile = `export const env = {
   npmPackageVersion: '1.0.0'
   keycloakBaseUrl: '${process.env['KEYCLOAK_AUTH_URL']}',
   keycloakRealm: '${process.env['KEYCLOAK_REALM']}',
   apiUrl: '${process.env['API_URL']}',
   redirectUri: '${process.env['WEB_URL']}',
   keycloakClientId: '${process.env['KEYCLOAK_WEB_CLIENT_ID']}',
   clientId: '${process.env['KEYCLOAK_WEB_CLIENT']}',
};
`;
console.log(colors.magenta('The file `environment.ts` will be written with the following content: \n'));
console.log(colors.grey(envConfigFile));
writeFile(targetPath, envConfigFile, function (err: any) {
   if (err) {
       throw console.error(err);
   } else {
       console.log(colors.magenta(`Angular environment.ts file generated correctly at ${targetPath} \n`));
   }
});