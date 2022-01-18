// Generated by hibernate/SpringHibernateDaoImpl.vsl in andromda-spring-cartridge on 01/15/2022 22:36:25+0200.
// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package bw.org.bocra.portal.licensee;

import bw.org.bocra.portal.user.UserOrganisationVO;
import org.springframework.stereotype.Repository;

/**
 * @see Licensee
 */
@Repository("licenseeDao")
public class LicenseeDaoImpl
    extends LicenseeDaoBase
{
    /**
     * {@inheritDoc}
     */
    @Override
    public void toLicenseeVO(
        Licensee source,
        LicenseeVO target)
    {
        // TODO verify behavior of toLicenseeVO
        super.toLicenseeVO(source, target);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LicenseeVO toLicenseeVO(final Licensee entity)
    {
        // TODO verify behavior of toLicenseeVO
        return super.toLicenseeVO(entity);
    }

    /**
     * Retrieves the entity object that is associated with the specified value object
     * from the object store. If no such entity object exists in the object store,
     * a new, blank entity is created
     */
    private Licensee loadLicenseeFromLicenseeVO(LicenseeVO licenseeVO)
    {
        // TODO implement loadLicenseeFromLicenseeVO
        throw new UnsupportedOperationException("bw.org.bocra.portal.licensee.loadLicenseeFromLicenseeVO(LicenseeVO) not yet implemented.");

        /* A typical implementation looks like this:
        if (licenseeVO.getId() == null)
        {
            return  Licensee.Factory.newInstance();
        }
        else
        {
            return this.load(licenseeVO.getId());
        }
        */
    }

    /**
     * {@inheritDoc}
     */
    public Licensee licenseeVOToEntity(LicenseeVO licenseeVO)
    {
        // TODO verify behavior of licenseeVOToEntity
        Licensee entity = this.loadLicenseeFromLicenseeVO(licenseeVO);
        this.licenseeVOToEntity(licenseeVO, entity, true);
        return entity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void licenseeVOToEntity(
        LicenseeVO source,
        Licensee target,
        boolean copyIfNull)
    {
        // TODO verify behavior of licenseeVOToEntity
        super.licenseeVOToEntity(source, target, copyIfNull);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void toUserOrganisationVO(
        Licensee source,
        UserOrganisationVO target)
    {
        // TODO verify behavior of toUserOrganisationVO
        super.toUserOrganisationVO(source, target);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserOrganisationVO toUserOrganisationVO(final Licensee entity)
    {
        // TODO verify behavior of toUserOrganisationVO
        return super.toUserOrganisationVO(entity);
    }

    /**
     * Retrieves the entity object that is associated with the specified value object
     * from the object store. If no such entity object exists in the object store,
     * a new, blank entity is created
     */
    private Licensee loadLicenseeFromUserOrganisationVO(UserOrganisationVO userOrganisationVO)
    {
        // TODO implement loadLicenseeFromUserOrganisationVO
        throw new UnsupportedOperationException("bw.org.bocra.portal.licensee.loadLicenseeFromUserOrganisationVO(UserOrganisationVO) not yet implemented.");

        /* A typical implementation looks like this:
        if (userOrganisationVO.getId() == null)
        {
            return  Licensee.Factory.newInstance();
        }
        else
        {
            return this.load(userOrganisationVO.getId());
        }
        */
    }

    /**
     * {@inheritDoc}
     */
    public Licensee userOrganisationVOToEntity(UserOrganisationVO userOrganisationVO)
    {
        // TODO verify behavior of userOrganisationVOToEntity
        Licensee entity = this.loadLicenseeFromUserOrganisationVO(userOrganisationVO);
        this.userOrganisationVOToEntity(userOrganisationVO, entity, true);
        return entity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void userOrganisationVOToEntity(
        UserOrganisationVO source,
        Licensee target,
        boolean copyIfNull)
    {
        // TODO verify behavior of userOrganisationVOToEntity
        super.userOrganisationVOToEntity(source, target, copyIfNull);
    }
}