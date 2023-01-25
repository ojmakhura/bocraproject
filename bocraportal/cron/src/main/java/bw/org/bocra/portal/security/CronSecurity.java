package bw.org.bocra.portal.security;

import org.jboss.resteasy.client.jaxrs.internal.ResteasyClientBuilderImpl;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CronSecurity {
    @Value("${keycloak.auth-server-url}")
    private String authUrl;

    @Value("${keycloak.realm}")
    private String realm;

    @Value("${keycloak.resource}")
    private String clientId;

    @Value("${keycloak.credentials.secret}")
    private String clientSecret;

    public Keycloak getKeycloak() {
        
        Keycloak keycloak = KeycloakBuilder.builder()
                    .serverUrl(authUrl)
                    .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                    .realm(realm)
                    .clientId(clientId)
                    .clientSecret(clientSecret)
                    .resteasyClient(
                        new ResteasyClientBuilderImpl().connectionPoolSize(10).build()
                    )
                    .build();

        return keycloak;
    }

    public AccessTokenResponse getAccessToken() {
        return getKeycloak().tokenManager().getAccessToken();
    }
}
