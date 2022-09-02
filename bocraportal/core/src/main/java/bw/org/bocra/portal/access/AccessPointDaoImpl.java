// Generated by hibernate/SpringHibernateDaoImpl.vsl in andromda-spring-cartridge on $springUtils.date.
// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package bw.org.bocra.portal.access;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import bw.org.bocra.portal.access.type.AccessPointType;
import bw.org.bocra.portal.access.type.AccessPointTypeDao;
import bw.org.bocra.portal.access.type.AccessPointTypeRepository;
import bw.org.bocra.portal.access.type.AccessPointTypeVO;

/**
 * @see AccessPoint
 */
@Repository("accessPointDao")
@Transactional
public class AccessPointDaoImpl
    extends AccessPointDaoBase
{

    public AccessPointDaoImpl(AccessPointTypeRepository accessPointTypeRepository,
            AccessPointRepository accessPointRepository) {
        super(accessPointTypeRepository, accessPointRepository);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void toAccessPointVO(
        AccessPoint source,
        AccessPointVO target)
    {
        // TODO verify behavior of toAccessPointVO
        super.toAccessPointVO(source, target);

        if(source.getAccessPointType() != null && source.getAccessPointType().getId() != null) {
            AccessPointTypeVO type = getAccessPointTypeDao().toAccessPointTypeVO(source.getAccessPointType());
            target.setAccessPointType(type);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AccessPointVO toAccessPointVO(final AccessPoint entity)
    {
        // TODO verify behavior of toAccessPointVO
        return super.toAccessPointVO(entity);
    }

    /**
     * Retrieves the entity object that is associated with the specified value object
     * from the object store. If no such entity object exists in the object store,
     * a new, blank entity is created
     */
    private AccessPoint loadAccessPointFromAccessPointVO(AccessPointVO accessPointVO)
    {
        if (accessPointVO.getId() == null)
        {
            return  AccessPoint.Factory.newInstance();
        }
        else
        {
            return this.load(accessPointVO.getId());
        }
    }

    /**
     * {@inheritDoc}
     */
    public AccessPoint accessPointVOToEntity(AccessPointVO accessPointVO)
    {
        // TODO verify behavior of accessPointVOToEntity
        AccessPoint entity = this.loadAccessPointFromAccessPointVO(accessPointVO);
        this.accessPointVOToEntity(accessPointVO, entity, true);
        return entity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void accessPointVOToEntity(
        AccessPointVO source,
        AccessPoint target,
        boolean copyIfNull)
    {
        // TODO verify behavior of accessPointVOToEntity
        super.accessPointVOToEntity(source, target, copyIfNull);
        if(source.getAccessPointType() != null && source.getAccessPointType().getId() != null) {
            AccessPointType type = getAccessPointTypeDao().get(source.getAccessPointType().getId());
            target.setAccessPointType(type);
        }
    }
}