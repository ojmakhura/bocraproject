// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 * TEMPLATE:    SpringServiceImpl.vsl in andromda-spring cartridge
 * MODEL CLASS: AndroMDAModel::backend::bw.org.bocra.portal::user::LicenseeUserService
 * STEREOTYPE:  Service
 */
package bw.org.bocra.portal.user;

import java.util.Collection;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import bw.org.bocra.portal.licensee.LicenseeService;

/**
 * @see bw.org.bocra.portal.user.LicenseeUserService
 */
@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
@Service("licenseeUserService")
public class LicenseeUserServiceImpl
    extends LicenseeUserServiceBase
{

    public LicenseeUserServiceImpl(LicenseeService licenseeService, LicenseeUserDao licenseeUserDao,
            LicenseeUserRepository licenseeUserRepository, MessageSource messageSource) {
        super(licenseeService, licenseeUserDao, licenseeUserRepository, messageSource);
    }

    /**
     * @see bw.org.bocra.portal.user.LicenseeUserService#findById(Long)
     */
    @Override
    protected  LicenseeUserVO handleFindById(Long id)
        throws Exception
    {
        throw new UnsupportedOperationException("bw.org.bocra.portal.user.LicenseeUserService.handleFindById(Long id) Not implemented!");
    }

    /**
     * @see bw.org.bocra.portal.user.LicenseeUserService#save(LicenseeUserVO)
     */
    @Override
    protected  LicenseeUserVO handleSave(LicenseeUserVO LicenseeUserVO)
        throws Exception
    {
        // TODO implement protected  LicenseeUserVO handleSave(LicenseeUserVO LicenseeUserVO)
        throw new UnsupportedOperationException("bw.org.bocra.portal.user.LicenseeUserService.handleSave(LicenseeUserVO LicenseeUserVO) Not implemented!");
    }

    /**
     * @see bw.org.bocra.portal.user.LicenseeUserService#remove(Long)
     */
    @Override
    protected  boolean handleRemove(Long id)
        throws Exception
    {
        // TODO implement protected  boolean handleRemove(Long id)
        throw new UnsupportedOperationException("bw.org.bocra.portal.user.LicenseeUserService.handleRemove(Long id) Not implemented!");
    }

    /**
     * @see bw.org.bocra.portal.user.LicenseeUserService#getAll()
     */
    @Override
    protected  Collection<LicenseeUserVO> handleGetAll()
        throws Exception
    {
        // TODO implement protected  Collection<LicenseeUserVO> handleGetAll()
        throw new UnsupportedOperationException("bw.org.bocra.portal.user.LicenseeUserService.handleGetAll() Not implemented!");
    }

    /**
     * @see bw.org.bocra.portal.user.LicenseeUserService#search(String, String, String)
     */
    // @Override
    // protected  Collection<LicenseeUserVO> handleSearch(String userId, String licenseeUin, String licenseeName)
    //     throws Exception
    // {
    //     // TODO implement protected  Collection<LicenseeUserVO> handleSearch(String userId, String licenseeUin, String licenseeName)
    //     throw new UnsupportedOperationException("bw.org.bocra.portal.user.LicenseeUserService.handleSearch(String userId, String licenseeUin, String licenseeName) Not implemented!");
    // }

    /**
     * @see bw.org.bocra.portal.user.LicenseeUserService#update(LicenseeUserVO)
     */
    @Override
    protected  LicenseeUserVO handleUpdate(LicenseeUserVO LicenseeUserVO)
        throws Exception
    {
        // TODO implement protected  LicenseeUserVO handleUpdate(LicenseeUserVO LicenseeUserVO)
        throw new UnsupportedOperationException("bw.org.bocra.portal.user.LicenseeUserService.handleUpdate(LicenseeUserVO LicenseeUserVO) Not implemented!");
    }

    @Override
    protected Collection<LicenseeUserVO> handleSearch(String userId, String licenseeUin, String licenseeName,
            Integer pageNumber, Integer pageSize) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected Collection<LicenseeUserVO> handleGetAll(Integer pageNumber, Integer pageSize) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

}