// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 * TEMPLATE:    SpringServiceImpl.vsl in andromda-spring cartridge
 * MODEL CLASS: bocraportal::backend::bw.org.bocra.portal::licence::LicenceService
 * STEREOTYPE:  Service
 */
package bw.org.bocra.portal.licence;

import java.util.Collection;

import org.hibernate.Criteria;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * @see bw.org.bocra.portal.licence.LicenceService
 */
@Service("licenceService")
public class LicenceServiceImpl
    extends LicenceServiceBase
{

    public LicenceServiceImpl(LicenceDao licenceDao, LicenceRepository licenceRepository, MessageSource messageSource) {
        super(licenceDao, licenceRepository, messageSource);
    }

    /**
     * @see bw.org.bocra.portal.licence.LicenceService#findById(Long)
     */
    @Override
    protected  LicenceVO handleFindById(Long id)
        throws Exception
    {
        if(id == null) {
            return null;
        }

        return (LicenceVO) licenceDao.load(LicenceDao.TRANSFORM_LICENCEVO, id);
    }

    /**
     * @see bw.org.bocra.portal.licence.LicenceService#save(LicenceVO)
     */
    @Override
    protected  LicenceVO handleSave(LicenceVO licence)
        throws Exception
    {
        Licence entity = getLicenceDao().licenceVOToEntity(licence);
        entity = licenceRepository.save(entity);

        return getLicenceDao().toLicenceVO(entity);
    }

    /**
     * @see bw.org.bocra.portal.licence.LicenceService#remove(Long)
     */
    @Override
    protected  boolean handleRemove(Long id)
        throws Exception
    {
        if(id == null) return false;

        licenceRepository.deleteById(id);
        return true;
    }

    /**
     * @see bw.org.bocra.portal.licence.LicenceService#getAll()
     */
    @Override
    protected  Collection<LicenceVO> handleGetAll()
        throws Exception
    {
        return (Collection<LicenceVO>) getLicenceDao().loadAll(LicenceDao.TRANSFORM_LICENCEVO);
    }

    /**
     * @see bw.org.bocra.portal.licence.LicenceService#search(LicenceCriteria)
     */
    @Override
    protected  Collection<LicenceVO> handleSearch(LicenceCriteria criteria)
        throws Exception
    {
        return (Collection<LicenceVO>) getLicenceDao().findByCriteria(LicenceDao.TRANSFORM_LICENCEVO, criteria);
    }

    /**
     * @see bw.org.bocra.portal.licence.LicenceService#getAll(Integer, Integer)
     */
    @Override
    protected  Collection<LicenceVO> handleGetAll(Integer pageNumber, Integer pageSize)
        throws Exception
    {
        Collection<Licence> types = null;

        if(pageNumber < 0 || pageSize < 1) {
            types = licenceRepository.findAll();
        } else {
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("licenceNumber").descending());
            types = licenceRepository.findAll(pageable).getContent();
        }

        return types == null ? null : getLicenceDao().toLicenceVOCollection(types);
    }

}