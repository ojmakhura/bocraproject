// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 * TEMPLATE:    SpringServiceImpl.vsl in andromda-spring cartridge
 * MODEL CLASS: AndroMDAModel::backend::bw.org.bocra.portal::group::LicenseeGroupService
 * STEREOTYPE:  Service
 */
package bw.org.bocra.portal.group;

import java.util.Collection;
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
        // TODO implement protected  LicenseeGroupVO handleFindById(Long id)
        throw new UnsupportedOperationException("bw.org.bocra.portal.group.LicenseeGroupService.handleFindById(Long id) Not implemented!");
    }

    /**
     * @see bw.org.bocra.portal.group.LicenseeGroupService#save(LicenseeGroupVO)
     */
    @Override
    protected  LicenseeGroupVO handleSave(LicenseeGroupVO licenseeGroupVO)
        throws Exception
    {
        // TODO implement protected  LicenseeGroupVO handleSave(LicenseeGroupVO licenseeGroupVO)
        throw new UnsupportedOperationException("bw.org.bocra.portal.group.LicenseeGroupService.handleSave(LicenseeGroupVO licenseeGroupVO) Not implemented!");
    }

    /**
     * @see bw.org.bocra.portal.group.LicenseeGroupService#remove(Long)
     */
    @Override
    protected  boolean handleRemove(Long id)
        throws Exception
    {
        // TODO implement protected  boolean handleRemove(Long id)
        throw new UnsupportedOperationException("bw.org.bocra.portal.group.LicenseeGroupService.handleRemove(Long id) Not implemented!");
    }

    /**
     * @see bw.org.bocra.portal.group.LicenseeGroupService#getAll()
     */
    @Override
    protected  Collection<LicenseeGroupVO> handleGetAll()
        throws Exception
    {
        // TODO implement protected  Collection<LicenseeGroupVO> handleGetAll()
        throw new UnsupportedOperationException("bw.org.bocra.portal.group.LicenseeGroupService.handleGetAll() Not implemented!");
    }

    /**
     * @see bw.org.bocra.portal.group.LicenseeGroupService#search(LicenseeGroupCriteria)
     */
    @Override
    protected  Collection<LicenseeGroupVO> handleSearch(LicenseeGroupCriteria searchCriteria)
        throws Exception
    {
        // TODO implement protected  Collection<LicenseeGroupVO> handleSearch(LicenseeGroupCriteria searchCriteria)
        throw new UnsupportedOperationException("bw.org.bocra.portal.group.LicenseeGroupService.handleSearch(LicenseeGroupCriteria searchCriteria) Not implemented!");
    }

    /**
     * @see bw.org.bocra.portal.group.LicenseeGroupService#update(LicenseeGroupVO)
     */
    @Override
    protected  LicenseeGroupVO handleUpdate(LicenseeGroupVO licenseeGroupVO)
        throws Exception
    {
        // TODO implement protected  LicenseeGroupVO handleUpdate(LicenseeGroupVO licenseeGroupVO)
        throw new UnsupportedOperationException("bw.org.bocra.portal.group.LicenseeGroupService.handleUpdate(LicenseeGroupVO licenseeGroupVO) Not implemented!");
    }

}