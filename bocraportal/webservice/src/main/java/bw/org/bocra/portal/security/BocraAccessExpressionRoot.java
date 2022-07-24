package bw.org.bocra.portal.security;

import java.util.ArrayList;
import java.util.Arrays;
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

    private AccessPointRepository accessPointRepository;
    private AuthorisationRepository authorisationRepository;

    //private Authentication authentication;

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
        System.out.println(apiEndpoint);
        System.out.println(authentication);

        Specification<AccessPoint> apSpec = AccessPointSpecifications.findByUrlLikeIgnoreCase(apiEndpoint);
        Collection<AccessPoint> aps = this.accessPointRepository.findAll(apSpec);
        System.out.println(aps);

        if(CollectionUtils.isEmpty(aps)) return true;

        Collection<Long> apIds = new ArrayList<>();
        for(AccessPoint ap : aps) {
            apIds.add(ap.getId());
        }

        Specification<Authorisation> spec = AuthorisationSpecifications.findByAccessPointIdIn(apIds);
        Collection<Authorisation> auths = this.authorisationRepository.findAll(spec);
        ArrayList<Long> st = new ArrayList<>();
        st.add(2l);
        st.add(65l);

        this.authorisationRepository.findAll(AuthorisationSpecifications.findByMenuSectionIdIn(st));

        // for(Authorisation auth : auths) {
        //     if(auth.)
        // }

        return true;
    }
}
