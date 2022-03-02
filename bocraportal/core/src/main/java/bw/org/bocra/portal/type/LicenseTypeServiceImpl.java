// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 * TEMPLATE:    SpringServiceImpl.vsl in andromda-spring cartridge
 * MODEL CLASS: bocraportal::backend::bw.org.bocra.portal::type::LicenseTypeService
 * STEREOTYPE:  Service
 */
package bw.org.bocra.portal.type;

import java.util.Arrays;
import java.util.Collection;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @see bw.org.bocra.portal.type.LicenseTypeService
 */
@Service("licenseTypeService")
public class LicenseTypeServiceImpl
    extends LicenseTypeServiceBase
{

    /**
     * @see bw.org.bocra.portal.type.LicenseTypeService#findById(Long)
     */
    @Override
    protected  LicenseTypeVO handleFindById(Long id)
        throws Exception
    {
        if(id == null) {
            return null;
        }

        LicenseType type = licenseTypeRepository.getById(id);

        return licenseTypeDao.toLicenseTypeVO(type);
    }

    /**
     * @see bw.org.bocra.portal.type.LicenseTypeService#save(LicenseTypeVO)
     */
    @Override
    protected  LicenseTypeVO handleSave(LicenseTypeVO licenseTypeVO)
        throws Exception
    {
        if(licenseTypeVO.getId() == null) {

            LicenseType entity = getLicenseTypeDao().licenseTypeVOToEntity(licenseTypeVO);
            return (LicenseTypeVO) licenseTypeDao.create(LicenseTypeDao.TRANSFORM_LICENSETYPEVO, entity);

        } else {


            return licenseTypeVO;
        }
    }

    /**
     * @see bw.org.bocra.portal.type.LicenseTypeService#remove(Long)
     */
    @Override
    protected  boolean handleRemove(Long id)
        throws Exception
    {
        if(id == null) {
            return false;
        }

        licenseTypeRepository.deleteById(id);
        return true;
    }

    /**
     * @see bw.org.bocra.portal.type.LicenseTypeService#getAll()
     */
    @Override
    protected  Collection<LicenseTypeVO> handleGetAll()
        throws Exception
    {
        return (Collection<LicenseTypeVO>) this.licenseTypeDao.loadAll(LicenseTypeDao.TRANSFORM_LICENSETYPEVO);
    }

    /**
     * @see bw.org.bocra.portal.type.LicenseTypeService#search(LicenseTypeCriteria)
     */
    @Override
    protected  Collection<LicenseTypeVO> handleSearch(LicenseTypeCriteria searchCriteria)
        throws Exception
    {
        Specification<LicenseType> specs = null;

        if(StringUtils.isNotBlank(searchCriteria.getTypeSearch())) {

            specs = LicenseTypeSpecifications.findByCodeContainingIgnoreCase(searchCriteria.getTypeSearch());
            specs.or(LicenseTypeSpecifications.findByDescriptionContainingIgnoreCase(searchCriteria.getTypeSearch()));
            specs.or(LicenseTypeSpecifications.findByNameContainingIgnoreCase(searchCriteria.getTypeSearch()));
        }

        if(searchCriteria.getLicenseeId() != null) {
            Specification<LicenseType> licenseeSpec = LicenseTypeSpecifications.findByLicenseesIdIn(Arrays.asList(searchCriteria.getLicenseeId()));

            if(specs == null) {
                specs = licenseeSpec;
            } else {
                specs.and(licenseeSpec);
            }
        }

        Collection<LicenseType> entities = licenseTypeRepository.findAll(specs);

        return getLicenseTypeDao().toLicenseTypeVOCollection(entities);
    }

}