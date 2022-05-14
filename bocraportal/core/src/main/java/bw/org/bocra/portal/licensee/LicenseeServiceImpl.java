// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 * TEMPLATE:    SpringServiceImpl.vsl in andromda-spring cartridge
 * MODEL CLASS: AndroMDAModel::backend::bw.org.bocra.portal::licensee::LicenseeService
 * STEREOTYPE:  Service
 */
package bw.org.bocra.portal.licensee;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @see bw.org.bocra.portal.licensee.LicenseeService
 */
@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
@Service("licenseeService")
public class LicenseeServiceImpl
    extends LicenseeServiceBase
{

    /**
     * @see bw.org.bocra.portal.licensee.LicenseeService#findById(Long)
     */
    @Override
    protected  LicenseeVO handleFindById(Long id)
        throws Exception
    {
        if(id == null) {
            return null;
        }

        Licensee licensee = this.licenseeRepository.getById(id);

        return licenseeDao.toLicenseeVO(licensee);
    }

    /**
     * @see bw.org.bocra.portal.licensee.LicenseeService#save(LicenseeVO)
     */
    @Override
    protected  LicenseeVO handleSave(LicenseeVO licenseeVO)
        throws Exception
    {
        Licensee entity = licenseeDao.licenseeVOToEntity(licenseeVO);
        
        if(licenseeVO.getId() == null) {
            entity = licenseeDao.create(entity);
        } else {
            licenseeDao.update(entity);
        }

        return getLicenseeDao().toLicenseeVO(entity);

    }

    /**
     * @see bw.org.bocra.portal.licensee.LicenseeService#remove(Long)
     */
    @Override
    protected  boolean handleRemove(Long id)
        throws Exception
    {
        if(id != null) {
            licenseeRepository.deleteById(id);

            return true;
        }

        return false;
    }

    /**
     * @see bw.org.bocra.portal.licensee.LicenseeService#getAll()
     */
    @Override
    protected  Collection<LicenseeVO> handleGetAll()
        throws Exception
    {
        return (Collection<LicenseeVO>) licenseeDao.loadAll(LicenseeDao.TRANSFORM_LICENSEEVO);
    }

    /**
     * @see bw.org.bocra.portal.licensee.LicenseeService#search(LicenseeCriteria)
     */
    @Override
    protected  Collection<LicenseeVO> handleSearch(LicenseeCriteria criteria)
        throws Exception
    {

        Collection<Licensee> licenses = this.licenseeDao.findByCriteria(criteria);
        Collection<LicenseeVO> vos = new ArrayList<>();

        for (Licensee entity : licenses) {
            vos.add(licenseeDao.toLicenseeVO(entity));
        }
        return vos;
    }

    @Override
    protected Collection<LicenseeVO> handleGetAll(Integer pageNumber, Integer pageSize) throws Exception {
        Collection<Licensee> licensees = null;

        if(pageNumber < 0 || pageSize < 1) {
            licensees = licenseeRepository.findAll();
        } else {
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("uin").descending());
            licensees = licenseeRepository.findAll(pageable).getContent();
        }

        return licensees == null ? null : getLicenseeDao().toLicenseeVOCollection(licensees);
    }

}