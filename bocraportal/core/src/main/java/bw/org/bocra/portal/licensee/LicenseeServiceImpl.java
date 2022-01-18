// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 * TEMPLATE:    SpringServiceImpl.vsl in andromda-spring cartridge
 * MODEL CLASS: AndroMDAModel::backend::bw.org.bocra.portal::licensee::LicenseeService
 * STEREOTYPE:  Service
 */
package bw.org.bocra.portal.licensee;

import java.util.Collection;
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
        // TODO implement protected  LicenseeVO handleFindById(Long id)
        throw new UnsupportedOperationException("bw.org.bocra.portal.licensee.LicenseeService.handleFindById(Long id) Not implemented!");
    }

    /**
     * @see bw.org.bocra.portal.licensee.LicenseeService#save(LicenseeVO)
     */
    @Override
    protected  LicenseeVO handleSave(LicenseeVO licenseeVO)
        throws Exception
    {
        // TODO implement protected  LicenseeVO handleSave(LicenseeVO licenseeVO)
        throw new UnsupportedOperationException("bw.org.bocra.portal.licensee.LicenseeService.handleSave(LicenseeVO licenseeVO) Not implemented!");
    }

    /**
     * @see bw.org.bocra.portal.licensee.LicenseeService#remove(Long)
     */
    @Override
    protected  boolean handleRemove(Long id)
        throws Exception
    {
        // TODO implement protected  boolean handleRemove(Long id)
        throw new UnsupportedOperationException("bw.org.bocra.portal.licensee.LicenseeService.handleRemove(Long id) Not implemented!");
    }

    /**
     * @see bw.org.bocra.portal.licensee.LicenseeService#getAll()
     */
    @Override
    protected  Collection<LicenseeVO> handleGetAll()
        throws Exception
    {
        // TODO implement protected  Collection<LicenseeVO> handleGetAll()
        throw new UnsupportedOperationException("bw.org.bocra.portal.licensee.LicenseeService.handleGetAll() Not implemented!");
    }

    /**
     * @see bw.org.bocra.portal.licensee.LicenseeService#search(LicenseeCriteria)
     */
    @Override
    protected  Collection<LicenseeVO> handleSearch(LicenseeCriteria searchCriteria)
        throws Exception
    {
        // TODO implement protected  Collection<LicenseeVO> handleSearch(LicenseeCriteria searchCriteria)
        throw new UnsupportedOperationException("bw.org.bocra.portal.licensee.LicenseeService.handleSearch(LicenseeCriteria searchCriteria) Not implemented!");
    }

    /**
     * @see bw.org.bocra.portal.licensee.LicenseeService#update(LicenseeVO)
     */
    @Override
    protected  LicenseeVO handleUpdate(LicenseeVO licenseeVO)
        throws Exception
    {
        // TODO implement protected  LicenseeVO handleUpdate(LicenseeVO licenseeVO)
        throw new UnsupportedOperationException("bw.org.bocra.portal.licensee.LicenseeService.handleUpdate(LicenseeVO licenseeVO) Not implemented!");
    }

}