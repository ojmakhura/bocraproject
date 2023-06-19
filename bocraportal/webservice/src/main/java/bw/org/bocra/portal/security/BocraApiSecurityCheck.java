package bw.org.bocra.portal.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.keycloak.adapters.springsecurity.account.SimpleKeycloakAccount;
import org.keycloak.representations.AccessToken;
import org.keycloak.representations.AccessToken.Access;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import bw.org.bocra.portal.auth.AuthorisationRepository;
import bw.org.bocra.portal.auth.Authorisation;

@Component(value = "securityCheck")
public class BocraApiSecurityCheck {
    
    private final AuthorisationRepository authorisationRepository;

    public BocraApiSecurityCheck(AuthorisationRepository authorisationRepository) {
        this.authorisationRepository = authorisationRepository;
    }

    public boolean isAuthorised(String apiEndpoint, String method) {

        return StringUtils.isBlank(method) ? this.isAuthorised(apiEndpoint) : this.handleIsAuthorised(method + " " + apiEndpoint);
    }

    public boolean isAuthorised(String apiEndpoint) {

        return this.handleIsAuthorised(apiEndpoint);
    }

    private boolean handleIsAuthorised(String url) {
        System.out.println("=====> " + url);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        /// Find the api with the requesting URL
        Collection<Authorisation> auths = this.authorisationRepository.findByAccessUrlAndCode(url, "API");

        System.out.println("auths " + auths);
        // If there are no authorisations, we assume the request is authorised by default
        if(CollectionUtils.isEmpty(auths)) {
            return true;
        }

        if(authentication.getDetails() instanceof SimpleKeycloakAccount) {
            SimpleKeycloakAccount acc = (SimpleKeycloakAccount) authentication.getDetails();
            AccessToken token = acc.getKeycloakSecurityContext().getToken();
            Access access = token.getResourceAccess(token.getIssuedFor()); // Get the authenticated client
            
            Set<String> roles = access != null ? access.getRoles() : new HashSet<>();
            if(roles == null) {
                roles = token.getRealmAccess().getRoles();
            } else {
                roles.addAll(token.getRealmAccess().getRoles());
            }
    
            for (Authorisation auth : auths) {
                for(String role : auth.getRoles()) {
                    if(roles.contains(role)) {
                        return true;
                    }
                }
            }
        } else {
            WebAuthenticationDetails dt = (WebAuthenticationDetails) authentication.getDetails();
            return true;
        }

        return false;
    }
}
