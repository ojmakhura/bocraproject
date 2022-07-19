package bw.org.bocra.portal.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

import bw.org.bocra.portal.access.AccessPointRepository;
import bw.org.bocra.portal.auth.AuthorisationRepository;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MethodSecurityConfig extends GlobalMethodSecurityConfiguration {


  @Autowired
  private AccessPointRepository accessPointRepository;

  @Autowired
  private AuthorisationRepository authorisationRepository;

  @Override
  protected MethodSecurityExpressionHandler createExpressionHandler() {
    BocraMethodSecurityExpressionHandler expressionHandler = new BocraMethodSecurityExpressionHandler(accessPointRepository, authorisationRepository);
    expressionHandler.setPermissionEvaluator(new BocraPermissionEvaluator());
    return expressionHandler;
  }
}
