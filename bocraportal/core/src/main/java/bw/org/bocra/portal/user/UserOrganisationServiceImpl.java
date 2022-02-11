// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 * TEMPLATE:    SpringServiceImpl.vsl in andromda-spring cartridge
 * MODEL CLASS: AndroMDAModel::backend::bw.org.bocra.portal::user::UserOrganisationService
 * STEREOTYPE:  Service
 */
package bw.org.bocra.portal.user;

import java.util.Collection;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @see bw.org.bocra.portal.user.UserOrganisationService
 */
@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
@Service("userOrganisationService")
public class UserOrganisationServiceImpl
    extends UserOrganisationServiceBase
{

    /**
     * @see bw.org.bocra.portal.user.UserOrganisationService#findById(Long)
     */
    @Override
    protected  UserOrganisationVO handleFindById(Long id)
        throws Exception
    {
        throw new UnsupportedOperationException("bw.org.bocra.portal.user.UserOrganisationService.handleFindById(Long id) Not implemented!");
    }

    /**
     * @see bw.org.bocra.portal.user.UserOrganisationService#save(UserOrganisationVO)
     */
    @Override
    protected  UserOrganisationVO handleSave(UserOrganisationVO userOrganisationVO)
        throws Exception
    {
        // TODO implement protected  UserOrganisationVO handleSave(UserOrganisationVO userOrganisationVO)
        throw new UnsupportedOperationException("bw.org.bocra.portal.user.UserOrganisationService.handleSave(UserOrganisationVO userOrganisationVO) Not implemented!");
    }

    /**
     * @see bw.org.bocra.portal.user.UserOrganisationService#remove(Long)
     */
    @Override
    protected  boolean handleRemove(Long id)
        throws Exception
    {
        // TODO implement protected  boolean handleRemove(Long id)
        throw new UnsupportedOperationException("bw.org.bocra.portal.user.UserOrganisationService.handleRemove(Long id) Not implemented!");
    }

    /**
     * @see bw.org.bocra.portal.user.UserOrganisationService#getAll()
     */
    @Override
    protected  Collection<UserOrganisationVO> handleGetAll()
        throws Exception
    {
        // TODO implement protected  Collection<UserOrganisationVO> handleGetAll()
        throw new UnsupportedOperationException("bw.org.bocra.portal.user.UserOrganisationService.handleGetAll() Not implemented!");
    }

    /**
     * @see bw.org.bocra.portal.user.UserOrganisationService#search(String, String, String)
     */
    @Override
    protected  Collection<UserOrganisationVO> handleSearch(String userId, String licenseeUin, String licenseeName)
        throws Exception
    {
        // TODO implement protected  Collection<UserOrganisationVO> handleSearch(String userId, String licenseeUin, String licenseeName)
        throw new UnsupportedOperationException("bw.org.bocra.portal.user.UserOrganisationService.handleSearch(String userId, String licenseeUin, String licenseeName) Not implemented!");
    }

    /**
     * @see bw.org.bocra.portal.user.UserOrganisationService#update(UserOrganisationVO)
     */
    @Override
    protected  UserOrganisationVO handleUpdate(UserOrganisationVO userOrganisationVO)
        throws Exception
    {
        // TODO implement protected  UserOrganisationVO handleUpdate(UserOrganisationVO userOrganisationVO)
        throw new UnsupportedOperationException("bw.org.bocra.portal.user.UserOrganisationService.handleUpdate(UserOrganisationVO userOrganisationVO) Not implemented!");
    }

}