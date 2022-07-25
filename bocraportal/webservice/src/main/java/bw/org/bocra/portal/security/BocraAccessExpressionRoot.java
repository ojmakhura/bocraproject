package bw.org.bocra.portal.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.apache.commons.collections4.CollectionUtils;
import org.keycloak.adapters.springsecurity.account.SimpleKeycloakAccount;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.AccessToken;
import org.keycloak.representations.AccessToken.Access;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import bw.org.bocra.portal.access.AccessPoint;
import bw.org.bocra.portal.access.AccessPointRepository;
import bw.org.bocra.portal.access.AccessPointSpecifications;
import bw.org.bocra.portal.auth.Authorisation;
import bw.org.bocra.portal.auth.AuthorisationRepository;
import bw.org.bocra.portal.auth.AuthorisationService;
import bw.org.bocra.portal.auth.AuthorisationSpecifications;
import bw.org.bocra.portal.keycloak.KeycloakService;

//@Component
public class BocraAccessExpressionRoot extends SecurityExpressionRoot implements MethodSecurityExpressionOperations {

    protected Logger logger = LoggerFactory.getLogger(BocraAccessExpressionRoot.class);

    private Object filterObject;
    private Object returnObject;
    private Object target;

    private AccessPointRepository accessPointRepository;
    private AuthorisationRepository authorisationRepository;

    public BocraAccessExpressionRoot(Authentication authentication, AccessPointRepository accessPointRepository, AuthorisationRepository authorisationRepository) {
        
        super(authentication);
        this.accessPointRepository = accessPointRepository;
        this.authorisationRepository = authorisationRepository;
    }

    @Override
    public Object getFilterObject() {
        return this.filterObject;
    }

    @Override
    public Object getReturnObject() {
        return this.returnObject;
    }

    @Override
    public Object getThis() {
        return this.target;
    }

    void setThis(Object target) {
        this.target = target;
    }

    @Override
    public void setFilterObject(final Object obj) {
        this.filterObject = obj;
    }

    @Override
    public void setReturnObject(final Object obj) {
        this.returnObject = obj;
    }

    public boolean isAuthorised(String apiEndpoint) {

        /// Find the api with the requesting URL
        Collection<Authorisation> auths = this.authorisationRepository.findByAccessUrlAndCode(apiEndpoint, "API");

        // If there are no authorisations, we assume the request is authorised by default
        if(CollectionUtils.isEmpty(auths)) {
            return true;
        }

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

        return false;
    }
}
