// Generated by hibernate/SpringHibernateDaoImpl.vsl in andromda-spring-cartridge on 01/29/2022 22:20:44+0200.
// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package bw.org.bocra.portal.user;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import bw.org.bocra.portal.licensee.LicenseeDao;
import bw.org.bocra.portal.licensee.LicenseeRepository;

/**
 * @see LicenseeUser
 */
@Repository("licenseeUserDao")
public class LicenseeUserDaoImpl
    extends LicenseeUserDaoBase
{
    
    public LicenseeUserDaoImpl(LicenseeRepository licenseeRepository, LicenseeUserRepository licenseeUserRepository) {
        super(licenseeRepository, licenseeUserRepository);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void toLicenseeUserVO(
        LicenseeUser source,
        LicenseeUserVO target)
    {
        // TODO verify behavior of toLicenseeUserVO
        super.toLicenseeUserVO(source, target);

        if(source.getLicensee() != null) {
            target.setLicensee(licenseeDao.toLicenseeVO(source.getLicensee()));
        }

        if(StringUtils.isNotBlank(source.getUserId())) {
            //target.setUser(value);
            
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LicenseeUserVO toLicenseeUserVO(final LicenseeUser entity)
    {
        // TODO verify behavior of toLicenseeUserVO
        return super.toLicenseeUserVO(entity);
    }

    /**
     * Retrieves the entity object that is associated with the specified value object
     * from the object store. If no such entity object exists in the object store,
     * a new, blank entity is created
     */
    private LicenseeUser loadLicenseeUserFromLicenseeUserVO(LicenseeUserVO LicenseeUserVO)
    {

        if (LicenseeUserVO.getId() == null)
        {
            return  LicenseeUser.Factory.newInstance();
        }
        else
        {
            return this.load(LicenseeUserVO.getId());
        }
    }

    /**
     * {@inheritDoc}
     */
    public LicenseeUser licenseeUserVOToEntity(LicenseeUserVO LicenseeUserVO)
    {
        // TODO verify behavior of LicenseeUserVOToEntity
        LicenseeUser entity = this.loadLicenseeUserFromLicenseeUserVO(LicenseeUserVO);
        this.licenseeUserVOToEntity(LicenseeUserVO, entity, true);
        return entity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void licenseeUserVOToEntity(
        LicenseeUserVO source,
        LicenseeUser target,
        boolean copyIfNull)
    {
        // TODO verify behavior of LicenseeUserVOToEntity
        super.licenseeUserVOToEntity(source, target, copyIfNull);

        if(source.getLicensee() != null) {
            target.setLicensee(licenseeDao.licenseeVOToEntity(source.getLicensee()));
        }

        if(source.getUser() != null) {
            target.setUserId(source.getUser().getUserId());
        }
    }

}