// Generated by hibernate/SpringHibernateDaoImpl.vsl in andromda-spring-cartridge on $springUtils.date.
// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package bw.org.bocra.portal.message;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @see BocraMesssage
 */
@Repository("bocraMesssageDao")
@Transactional
public class BocraMesssageDao
    extends BocraMesssageDaoBase
{
    
    public BocraMesssageDao(
        BocraMesssageRepository BocraMesssageRepository
    ) {

        super(
            BocraMesssageRepository
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void toBocraMesssageVO(
        BocraMesssage source,
        BocraMesssageVO target)
    {
        // TODO verify behavior of toBocraMesssageVO
        super.toBocraMesssageVO(source, target);
        // WARNING! No conversion for target.dispatchDate (can't convert source.getDispatchDate():java.time.LocalDate to java.time.LocalDateTime
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BocraMesssageVO toBocraMesssageVO(final BocraMesssage entity)
    {
        // TODO verify behavior of toBocraMesssageVO
        return super.toBocraMesssageVO(entity);
    }

    /**
     * Retrieves the entity object that is associated with the specified value object
     * from the object store. If no such entity object exists in the object store,
     * a new, blank entity is created
     */
    private BocraMesssage loadBocraMesssageFromBocraMesssageVO(BocraMesssageVO BocraMesssageVO)
    {
        if (BocraMesssageVO.getId() == null)
        {
            return  BocraMesssage.Factory.newInstance();
        }
        else
        {
            return this.BocraMesssageRepository.getReferenceById(BocraMesssageVO.getId());
        }
    }

    /**
     * {@inheritDoc}
     */
    public BocraMesssage bocraMesssageVOToEntity(BocraMesssageVO BocraMesssageVO)
    {
        // TODO verify behavior of BocraMesssageVOToEntity
        BocraMesssage entity = this.loadBocraMesssageFromBocraMesssageVO(BocraMesssageVO);
        this.bocraMesssageVOToEntity(BocraMesssageVO, entity, true);
        return entity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void bocraMesssageVOToEntity(
        BocraMesssageVO source,
        BocraMesssage target,
        boolean copyIfNull)
    {
        // TODO verify behavior of BocraMesssageVOToEntity
        super.bocraMesssageVOToEntity(source, target, copyIfNull);
        // No conversion for target.dispatchDate (can't convert source.getDispatchDate():java.time.LocalDateTime to java.time.LocalDate
    }
}