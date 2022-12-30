// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 * TEMPLATE:    SpringServiceImpl.vsl in andromda-spring cartridge
 * MODEL CLASS: bocraportal::backend::bw.org.bocra.portal::access::AccessPointService
 * STEREOTYPE:  Service
 */
package bw.org.bocra.portal.access;

import java.util.Collection;

import org.springframework.context.MessageSource;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @see bw.org.bocra.portal.access.AccessPointService
 */
@Service("accessPointService")
@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
public class AccessPointServiceImpl
    extends AccessPointServiceBase
{

    public AccessPointServiceImpl(AccessPointDao accessPointDao, AccessPointRepository accessPointRepository,
            MessageSource messageSource) {
        super(accessPointDao, accessPointRepository, messageSource);
    }

    /**
     * @see bw.org.bocra.portal.access.AccessPointService#findById(Long)
     */
    @Override
    protected  AccessPointVO handleFindById(Long id)
        throws Exception
    {
        AccessPoint point = getAccessPointDao().load(id);
        return accessPointDao.toAccessPointVO(point);
    }

    /**
     * @see bw.org.bocra.portal.access.AccessPointService#getAll()
     */
    @Override
    protected  Collection<AccessPointVO> handleGetAll()
        throws Exception
    {
        return (Collection<AccessPointVO>) getAccessPointDao().loadAll(AccessPointDao.TRANSFORM_ACCESSPOINTVO);
    }

    /**
     * @see bw.org.bocra.portal.access.AccessPointService#getAll(Integer, Integer)
     */
    @Override
    protected  Collection<AccessPointVO> handleGetAll(Integer pageNumber, Integer pageSize)
        throws Exception
    {
        Collection<AccessPoint> entities = accessPointRepository.findAll(PageRequest.of(pageNumber, pageSize)).getContent();
        return accessPointDao.toAccessPointVOCollection(entities);
    }

    /**
     * @see bw.org.bocra.portal.access.AccessPointService#remove(Long)
     */
    @Override
    protected  boolean handleRemove(Long id)
        throws Exception
    {
        accessPointRepository.deleteById(id);
        return true;
    }

    /**
     * @see bw.org.bocra.portal.access.AccessPointService#save(AccessPointVO)
     */
    @Override
    protected  AccessPointVO handleSave(AccessPointVO accessPoint)
        throws Exception
    {
        AccessPoint point = getAccessPointDao().accessPointVOToEntity(accessPoint);
        // point = accessPointDao.createOrUpdate(point);
        point = accessPointRepository.saveAndFlush(point);

        if(accessPoint.getId() != null) {
            return getAccessPointDao().toAccessPointVO(point);
        }

        return accessPointDao.toAccessPointVO(point);
    }

    /**
     * @see bw.org.bocra.portal.access.AccessPointService#search(String)
     */
    @Override
    protected  Collection<AccessPointVO> handleSearch(AccessPointCriteria criteria)
        throws Exception
    {
        Collection<AccessPoint> points = accessPointDao.findByCriteria(criteria);
        return accessPointDao.toAccessPointVOCollection(points);
    }

    /**
     * @see bw.org.bocra.portal.access.AccessPointService#search(Integer, Integer, AccessPointCriteria)
     */
    @Override
    protected  Collection<AccessPointVO> handleSearch(Integer pageNumber, Integer pageSize, AccessPointCriteria criteria)
        throws Exception
    {
        criteria.setFetchSize(pageSize);
        criteria.setPageNumber(pageNumber);
        Collection<AccessPoint> entities = accessPointDao.findByCriteria(criteria);
        return accessPointDao.toAccessPointVOCollection(entities);
    }

}