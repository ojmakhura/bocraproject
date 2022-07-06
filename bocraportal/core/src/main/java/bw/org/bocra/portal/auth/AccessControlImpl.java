// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 * TEMPLATE:    SpringServiceImpl.vsl in andromda-spring cartridge
 * MODEL CLASS: bocraportal::backend::bw.org.bocra.portal::auth::AccessControl
 * STEREOTYPE:  Service
 */
package bw.org.bocra.portal.auth;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import bw.org.bocra.portal.user.LicenseeUserService;

/**
 * @see bw.org.bocra.portal.auth.AccessControl
 */
//@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
@Service("accessControl")
public class AccessControlImpl
    extends AccessControlBase
{

    public AccessControlImpl(AuthorisationService authorisationService, LicenseeUserService licenseeUserService,
            MessageSource messageSource) {
        super(authorisationService, licenseeUserService, messageSource);
    }

    /**
     * @see bw.org.bocra.portal.auth.AccessControl#authorised(String)
     */
    @Override
    protected  boolean handleAuthorised(String url)
        throws Exception
    {
        // TODO implement protected  boolean handleAuthorised(String url)
        throw new UnsupportedOperationException("bw.org.bocra.portal.auth.AccessControl.handleAuthorised(String url) Not implemented!");
    }

}