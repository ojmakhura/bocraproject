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
    private String resourse;
    private boolean bearerOnly;

    public KeycloakProperties(String realm, String authServerUrl, String sslRequired, boolean publicClient,
            String resourse, boolean bearerOnly) {
        this.realm = realm;
        this.authServerUrl = authServerUrl;
        this.sslRequired = sslRequired;
        this.publicClient = publicClient;
        this.resourse = resourse;
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

    public String getResourse() {
        return resourse;
    }

    public void setResourse(String resourse) {
        this.resourse = resourse;
    }

    public boolean isBearerOnly() {
        return bearerOnly;
    }

    public void setBearerOnly(boolean bearerOnly) {
        this.bearerOnly = bearerOnly;
    }

}
