package bw.org.bocra.portal.keycloak;

import org.keycloak.adapters.RefreshableKeycloakSecurityContext;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.ClientsResource;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.RolesResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.ClientRepresentation;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class KeycloakService {
    
    public Keycloak getKeycloak() {
        RefreshableKeycloakSecurityContext context = getSecurityContext();
        
        Keycloak keycloak = Keycloak.getInstance(
                context.getDeployment().getAuthServerBaseUrl(), context.getRealm(),
                getAuthClient(), context.getTokenString());

        return keycloak;
    }

    public RealmResource getRealmResource() {
        
        RefreshableKeycloakSecurityContext context = getSecurityContext();
        return getKeycloak().realm(context.getRealm());
    }

    public RefreshableKeycloakSecurityContext getSecurityContext() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (RefreshableKeycloakSecurityContext) authentication.getCredentials();
    }

    public String getAuthClient() {

        RefreshableKeycloakSecurityContext context = getSecurityContext();
        return context.getToken().issuedFor;
    }

    public ClientRepresentation findAuthenticatedClientResource() {
        for (ClientRepresentation clientRep : getRealmResource().clients().findAll()) {
            if (clientRep.getClientId().equals(getAuthClient())) {
                return clientRep;
            }
        }

        return null;
    }

    public UsersResource getUsersResource() {

        RefreshableKeycloakSecurityContext context = getSecurityContext();
        Keycloak keycloak = getKeycloak();

        return keycloak.realm(context.getRealm()).users();
    }

    public RolesResource getClientRolesResource(String clientId) {

        RefreshableKeycloakSecurityContext context = getSecurityContext();
        Keycloak keycloak = getKeycloak();

        return keycloak.realm(context.getRealm()).clients().get(clientId).roles();
    }

    public ClientsResource getClientsResource() {

        return getRealmResource().clients();
    }
}
