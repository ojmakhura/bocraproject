package bw.org.bocra.portal.security;

import java.util.Collection;

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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        /// Find the api with the requesting URL
        Collection<Authorisation> auths = this.authorisationRepository.findByAccessUrlAndCode(url, "API");

        // If there are no authorisations, we assume the request is authorised by default
        if(CollectionUtils.isEmpty(auths)) {
            return true;
        }

        if(authentication.getDetails() instanceof SimpleKeycloakAccount) {
            SimpleKeycloakAccount acc = (SimpleKeycloakAccount) authentication.getDetails();
            AccessToken token = acc.getKeycloakSecurityContext().getToken();
            Access access = token.getResourceAccess(token.getIssuedFor()); // Get the authenticated client
    
            for (Authorisation auth : auths) {
                for(String role : auth.getRoles()) {
                    if(access.getRoles().contains(role)) {
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
