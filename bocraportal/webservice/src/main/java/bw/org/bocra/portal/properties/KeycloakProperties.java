package bw.org.bocra.portal.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConfigurationProperties("keycloak")
@ConstructorBinding
public class KeycloakProperties {
    private String realm;
    private String authServerUrl;
    private String sslRequired;
    private boolean publicClient;
    private String resource;
    private boolean bearerOnly;

    public KeycloakProperties(String realm, String authServerUrl, String sslRequired, boolean publicClient,
            String resource, boolean bearerOnly) {
        this.realm = realm;
        this.authServerUrl = authServerUrl;
        this.sslRequired = sslRequired;
        this.publicClient = publicClient;
        this.resource = resource;
        this.bearerOnly = bearerOnly;
    }

    public String getRealm() {
        return realm;
    }

    public void setRealm(String realm) {
        this.realm = realm;
    }

    public String getAuthServerUrl() {
        return authServerUrl;
    }

    public void setAuthServerUrl(String authServerUrl) {
        this.authServerUrl = authServerUrl;
    }

    public String getSslRequired() {
        return sslRequired;
    }

    public void setSslRequired(String sslRequired) {
        this.sslRequired = sslRequired;
    }

    public boolean isPublicClient() {
        return publicClient;
    }

    public void setPublicClient(boolean publicClient) {
        this.publicClient = publicClient;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public boolean isBearerOnly() {
        return bearerOnly;
    }

    public void setBearerOnly(boolean bearerOnly) {
        this.bearerOnly = bearerOnly;
    }

    @Override
    public String toString() {
        return "KeycloakProperties [realm=" + realm + ", authServerUrl=" + authServerUrl + ", sslRequired="
                + sslRequired + ", publicClient=" + publicClient + ", resourse=" + resource + ", bearerOnly="
                + bearerOnly + "]";
    }
    
}
