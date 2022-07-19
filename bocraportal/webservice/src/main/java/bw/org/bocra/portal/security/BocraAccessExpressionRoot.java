package bw.org.bocra.portal.security;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.collections4.CollectionUtils;
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

//@Component
public class BocraAccessExpressionRoot extends SecurityExpressionRoot implements MethodSecurityExpressionOperations {

    protected Logger logger = LoggerFactory.getLogger(BocraAccessExpressionRoot.class);

    private Object filterObject;
    private Object returnObject;
    private Object target;

    @Autowired
    private AccessPointRepository accessPointRepository;

    @Autowired
    private AuthorisationRepository authorisationRepository;

    //private Authentication authentication;

    public BocraAccessExpressionRoot(Authentication authentication) {
        
        super(authentication);
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
        System.out.println(apiEndpoint);
        System.out.println(authentication);

        Specification<AccessPoint> apSpec = AccessPointSpecifications.findByUrlContaining(apiEndpoint);
        Collection<AccessPoint> aps = this.accessPointRepository.findAll(apSpec);

        if(CollectionUtils.isEmpty(aps)) return false;

        Collection<Long> apIds = new ArrayList<>();
        for(AccessPoint ap : aps) {
            apIds.add(ap.getId());
        }

        Specification<Authorisation> spec = AuthorisationSpecifications.findByAccessPointIdIn(apIds);
        Collection<Authorisation> auths = this.authorisationRepository.findAll(spec);

        // for(Authorisation auth : auths) {
        //     if(auth.)
        // }

        return true;
    }
}
