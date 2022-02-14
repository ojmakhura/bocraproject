// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 * TEMPLATE:    SpringServiceImpl.vsl in andromda-spring cartridge
 * MODEL CLASS: AndroMDAModel::backend::bw.org.bocra.portal::group::LicenseeGroupService
 * STEREOTYPE:  Service
 */
package bw.org.bocra.portal.group;

import java.util.Arrays;
import java.util.Collection;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @see bw.org.bocra.portal.group.LicenseeGroupService
 */
 @Transactional(propagation = Propagation.REQUIRED, readOnly=false)
@Service("licenseeGroupService")
public class LicenseeGroupServiceImpl
    extends LicenseeGroupServiceBase
{

    /**
     * @see bw.org.bocra.portal.group.LicenseeGroupService#findById(Long)
     */
    @Override
    protected  LicenseeGroupVO handleFindById(Long id)
        throws Exception
    {
        if(id == null) {
            return null;
        }

        LicenseeGroup group = licenseeGroupRepository.getById(id);

        return licenseeGroupDao.toLicenseeGroupVO(group);
    }

    /**
     * @see bw.org.bocra.portal.group.LicenseeGroupService#save(LicenseeGroupVO)
     */
    @Override
    protected  LicenseeGroupVO handleSave(LicenseeGroupVO licenseeGroupVO)
        throws Exception
    {

        if(licenseeGroupVO.getId() == null) {

            LicenseeGroup entity = getLicenseeGroupDao().licenseeGroupVOToEntity(licenseeGroupVO);
            return (LicenseeGroupVO) licenseeGroupDao.create(LicenseeGroupDao.TRANSFORM_LICENSEEGROUPVO, entity);

        } else {


            return licenseeGroupVO;
        }

    }

    /**
     * @see bw.org.bocra.portal.group.LicenseeGroupService#remove(Long)
     */
    @Override
    protected  boolean handleRemove(Long id)
        throws Exception
    {
        if(id == null) {
            return false;
        }

        licenseeGroupRepository.deleteById(id);
        return true;
    }

    /**
     * @see bw.org.bocra.portal.group.LicenseeGroupService#getAll()
     */
    @Override
    protected  Collection<LicenseeGroupVO> handleGetAll()
        throws Exception
    {

        return (Collection<LicenseeGroupVO>) this.licenseeGroupDao.loadAll(LicenseeGroupDao.TRANSFORM_LICENSEEGROUPVO);
    }

    /**
     * @see bw.org.bocra.portal.group.LicenseeGroupService#search(LicenseeGroupCriteria)
     */
    @Override
    protected  Collection<LicenseeGroupVO> handleSearch(LicenseeGroupCriteria searchCriteria)
        throws Exception
    {
        
        Specification<LicenseeGroup> specs = null;

        if(StringUtils.isNotBlank(searchCriteria.getGroupSearch())) {

            specs = LicenseeGroupSpecifications.findByCodeContainingIgnoreCase(searchCriteria.getGroupSearch());
            specs.or(LicenseeGroupSpecifications.findByDescriptionContainingIgnoreCase(searchCriteria.getGroupSearch()));
            specs.or(LicenseeGroupSpecifications.findByGroupNameContainingIgnoreCase(searchCriteria.getGroupSearch()));
        }

        if(searchCriteria.getLicenseeId() != null) {
            Specification<LicenseeGroup> licenseeSpec = LicenseeGroupSpecifications.findByLicenseesIdIn(Arrays.asList(searchCriteria.getLicenseeId()));

            if(specs == null) {
                specs = licenseeSpec;
            } else {
                specs.and(licenseeSpec);
            }
        }

        Collection<LicenseeGroup> entities = licenseeGroupRepository.findAll(specs);

        return getLicenseeGroupDao().toLicenseeGroupVOCollection(entities);
    }

}