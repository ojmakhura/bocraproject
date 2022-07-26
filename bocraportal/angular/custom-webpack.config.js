const webpack = require('webpack');

module.exports = {
  plugins: [
    new webpack.DefinePlugin({
      $ENV: {
        ENVIRONMENT: JSON.stringify(process.env.ENVIRONMENT),
        KEYCLOAK_AUTH_URL: JSON.stringify(process.env.KEYCLOAK_AUTH_URL),
        API_URL: JSON.stringify(process.env.API_URL),
        KEYCLOAK_REALM: JSON.stringify(process.env.KEYCLOAK_REALM),
        BOCRA_API_ADDRESS: JSON.stringify(process.env.BOCRA_API_ADDRESS),
        KEYCLOAK_WEB_CLIENT: JSON.stringify(process.env.KEYCLOAK_WEB_CLIENT),
        REDIRECT_URL: JSON.stringify(process.env.WEB_URL),
        KEYCLOAK_WEB_CLIENT_ID: JSON.stringify(process.env.KEYCLOAK_WEB_CLIENT_ID),
      }
    })
  ]
};