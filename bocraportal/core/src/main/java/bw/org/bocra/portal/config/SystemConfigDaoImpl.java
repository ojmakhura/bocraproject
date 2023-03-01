// Generated by hibernate/SpringHibernateDaoImpl.vsl in andromda-spring-cartridge on $springUtils.date.
// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package bw.org.bocra.portal.config;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @see SystemConfig
 */
@Repository("systemConfigDao")
@Transactional
public class SystemConfigDaoImpl
    extends SystemConfigDaoBase
{
    
    public SystemConfigDaoImpl(
        SystemConfigRepository systemConfigRepository
    ) {

        super(
            systemConfigRepository
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void toSystemConfigVO(
        SystemConfig source,
        SystemConfigVO target)
    {
        // TODO verify behavior of toSystemConfigVO
        super.toSystemConfigVO(source, target);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SystemConfigVO toSystemConfigVO(final SystemConfig entity)
    {
        // TODO verify behavior of toSystemConfigVO
        return super.toSystemConfigVO(entity);
    }

    /**
     * Retrieves the entity object that is associated with the specified value object
     * from the object store. If no such entity object exists in the object store,
     * a new, blank entity is created
     */
    private SystemConfig loadSystemConfigFromSystemConfigVO(SystemConfigVO systemConfigVO)
    {
        if (systemConfigVO.getId() == null)
        {
            return  SystemConfig.Factory.newInstance();
        }
        else
        {
            return this.load(systemConfigVO.getId());
        }
    }

    /**
     * {@inheritDoc}
     */
    public SystemConfig systemConfigVOToEntity(SystemConfigVO systemConfigVO)
    {
        // TODO verify behavior of systemConfigVOToEntity
        SystemConfig entity = this.loadSystemConfigFromSystemConfigVO(systemConfigVO);
        this.systemConfigVOToEntity(systemConfigVO, entity, true);
        return entity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void systemConfigVOToEntity(
        SystemConfigVO source,
        SystemConfig target,
        boolean copyIfNull)
    {
        // TODO verify behavior of systemConfigVOToEntity
        super.systemConfigVOToEntity(source, target, copyIfNull);
    }
}