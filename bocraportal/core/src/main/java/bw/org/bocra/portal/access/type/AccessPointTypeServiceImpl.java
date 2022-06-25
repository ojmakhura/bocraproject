// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 * TEMPLATE:    SpringServiceImpl.vsl in andromda-spring cartridge
 * MODEL CLASS: bocraportal::backend::bw.org.bocra.portal::access::type::AccessPointTypeService
 * STEREOTYPE:  Service
 */
package bw.org.bocra.portal.access.type;

import java.util.ArrayList;
import java.util.Collection;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @see bw.org.bocra.portal.access.type.AccessPointTypeService
 */
@Service("accessPointTypeService")
@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
public class AccessPointTypeServiceImpl
    extends AccessPointTypeServiceBase
{

    /**
     * @see bw.org.bocra.portal.access.type.AccessPointTypeService#findById(Long)
     */
    @Override
    protected  AccessPointTypeVO handleFindById(Long id)
        throws Exception
    {
        AccessPointType type = getAccessPointTypeDao().load(id);
        return getAccessPointTypeDao().toAccessPointTypeVO(type);

    }

    /**
     * @see bw.org.bocra.portal.access.type.AccessPointTypeService#save(AccessPointTypeVO)
     */
    @Override
    protected  AccessPointTypeVO handleSave(AccessPointTypeVO accessPointType)
        throws Exception
    {
        AccessPointType entity = getAccessPointTypeDao().accessPointTypeVOToEntity(accessPointType);
        entity = accessPointTypeRepository.save(entity);

        if(accessPointType.getId() == null) {
            accessPointType = getAccessPointTypeDao().toAccessPointTypeVO(entity);
        }

        return accessPointType;
    }

    /**
     * @see bw.org.bocra.portal.access.type.AccessPointTypeService#remove(Long)
     */
    @Override
    protected  boolean handleRemove(Long id)
        throws Exception
    {
        accessPointTypeRepository.deleteById(id);

        return true;
    }

    /**
     * @see bw.org.bocra.portal.access.type.AccessPointTypeService#getAll()
     */
    @Override
    protected  Collection<AccessPointTypeVO> handleGetAll()
        throws Exception
    {
        return (Collection<AccessPointTypeVO>) getAccessPointTypeDao().loadAll(AccessPointTypeDao.TRANSFORM_ACCESSPOINTTYPEVO);
    }

    /**
     * @see bw.org.bocra.portal.access.type.AccessPointTypeService#search(String)
     */
    @Override
    protected  Collection<AccessPointTypeVO> handleSearch(String criteria)
        throws Exception
    {
        Collection<AccessPointType> types = accessPointTypeDao.findByCriteria(criteria);
        Collection<AccessPointTypeVO> vos = new ArrayList<>();

        for (AccessPointType type : types) {
            vos.add(getAccessPointTypeDao().toAccessPointTypeVO(type));
        }

        return vos;
    }

    /**
     * @see bw.org.bocra.portal.access.type.AccessPointTypeService#getAll(Integer, Integer)
     */
    @Override
    protected  Collection<AccessPointTypeVO> handleGetAll(Integer pageNumber, Integer pageSize)
        throws Exception
    {
        return (Collection<AccessPointTypeVO>) accessPointTypeDao.loadAll(accessPointTypeDao.TRANSFORM_ACCESSPOINTTYPEVO, pageNumber, pageSize);
    }

}