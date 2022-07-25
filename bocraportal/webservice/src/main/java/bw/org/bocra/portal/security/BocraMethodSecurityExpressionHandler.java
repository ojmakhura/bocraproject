package bw.org.bocra.portal.security;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;

import bw.org.bocra.portal.access.AccessPointRepository;
import bw.org.bocra.portal.auth.AuthorisationRepository;
import bw.org.bocra.portal.keycloak.KeycloakService;

public class BocraMethodSecurityExpressionHandler extends DefaultMethodSecurityExpressionHandler {

    private AuthenticationTrustResolver trustResolver = new AuthenticationTrustResolverImpl();

    private final AccessPointRepository accessPointRepository;
    private final AuthorisationRepository authorisationRepository;

    public BocraMethodSecurityExpressionHandler(AccessPointRepository accessPointRepository, AuthorisationRepository authorisationRepository) {
      this.accessPointRepository = accessPointRepository;
      this.authorisationRepository = authorisationRepository;
    }

    @Override
    protected MethodSecurityExpressionOperations createSecurityExpressionRoot(
      Authentication authentication, MethodInvocation invocation) {
        BocraAccessExpressionRoot root = new BocraAccessExpressionRoot(authentication, accessPointRepository, authorisationRepository);
        
        root.setPermissionEvaluator(getPermissionEvaluator());
        root.setTrustResolver(this.trustResolver);
        root.setRoleHierarchy(getRoleHierarchy());

        return root;
    }

    
}
