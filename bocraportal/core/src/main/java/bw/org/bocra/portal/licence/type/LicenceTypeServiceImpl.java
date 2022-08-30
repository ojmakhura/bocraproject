// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 * TEMPLATE:    SpringServiceImpl.vsl in andromda-spring cartridge
 * MODEL CLASS: bocraportal::backend::bw.org.bocra.portal::type::LicenceTypeService
 * STEREOTYPE:  Service
 */
package bw.org.bocra.portal.licence.type;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.context.MessageSource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * @see bw.org.bocra.portal.licence.type.LicenceTypeService
 */
@Service("licenceTypeService")
public class LicenceTypeServiceImpl
    extends LicenceTypeServiceBase
{

    public LicenceTypeServiceImpl(LicenceTypeDao licenceTypeDao, LicenceTypeRepository licenceTypeRepository,
            MessageSource messageSource) {
        super(licenceTypeDao, licenceTypeRepository, messageSource);
    }

    /**
     * @see bw.org.bocra.portal.licence.type.LicenceTypeService#findById(Long)
     */
    @Override
    protected  LicenceTypeVO handleFindById(Long id)
        throws Exception
    {
        if(id == null) {
            return null;
        }

        LicenceType type = licenceTypeRepository.getById(id);

        return licenceTypeDao.toLicenceTypeVO(type);
    }

    /**
     * @see bw.org.bocra.portal.licence.type.LicenceTypeService#save(LicenceTypeVO)
     */
    @Override
    protected  LicenceTypeVO handleSave(LicenceTypeVO LicenceTypeVO)
        throws Exception
    {
        LicenceType entity = getLicenceTypeDao().licenceTypeVOToEntity(LicenceTypeVO);
        if(LicenceTypeVO.getId() == null) {

            return (LicenceTypeVO) licenceTypeDao.create(LicenceTypeDao.TRANSFORM_LICENCETYPEVO, entity);

        } else {

            licenceTypeDao.update(entity);
            return LicenceTypeVO;
        }
    }

    /**
     * @see bw.org.bocra.portal.licence.type.LicenceTypeService#remove(Long)
     */
    @Override
    protected  boolean handleRemove(Long id)
        throws Exception
    {
        if(id == null) {
            return false;
        }

        licenceTypeRepository.deleteById(id);
        return true;
    }

    /**
     * @see bw.org.bocra.portal.licence.type.LicenceTypeService#getAll()
     */
    @Override
    protected  Collection<LicenceTypeVO> handleGetAll()
        throws Exception
    {
        return (Collection<LicenceTypeVO>) this.licenceTypeDao.loadAll(LicenceTypeDao.TRANSFORM_LICENCETYPEVO);
    }

    /**
     * @see bw.org.bocra.portal.licence.type.LicenceTypeService#search(LicenceTypeCriteria)
     */
    @Override
    protected  Collection<LicenceTypeVO> handleSearch(LicenceTypeCriteria criteria)
        throws Exception
    {

        Collection<LicenceType> entities = licenceTypeDao.findByCriteria(criteria);
        Collection<LicenceTypeVO> vos = new ArrayList<>();

        for (LicenceType licenceType : entities) {
            LicenceTypeVO vo = new LicenceTypeVO();
            getLicenceTypeDao().toLicenceTypeVO(licenceType, vo);
            vos.add(vo);
        }

        return vos;
    }

    @Override
    protected Collection<LicenceTypeVO> handleGetAll(Integer pageNumber, Integer pageSize) throws Exception {
        
        Collection<LicenceType> types = null;

        if(pageNumber < 0 || pageSize < 1) {
            types = licenceTypeRepository.findAll();
        } else {
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("code").descending());
            types = licenceTypeRepository.findAll(pageable).getContent();
        }

        return types == null ? null : getLicenceTypeDao().toLicenceTypeVOCollection(types);
    }

}