// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 * TEMPLATE:    SpringServiceImpl.vsl in andromda-spring cartridge
 * MODEL CLASS: bocraportal::backend::bw.org.bocra.portal::shareholder::ShareholderService
 * STEREOTYPE:  Service
 */
package bw.org.bocra.portal.shareholder;

import java.util.Collection;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @see bw.org.bocra.portal.shareholder.ShareholderService
 */
@Service("shareholderService")
@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
public class ShareholderServiceImpl
    extends ShareholderServiceBase
{
    public ShareholderServiceImpl(
        ShareholderDao shareholder,
        ShareholderRepository shareholderRepository,
        MessageSource messageSource
    ) {
        
        super(
            shareholder,
            shareholderRepository,
            messageSource
        );
    }

    /**
     * @see bw.org.bocra.portal.shareholder.ShareholderService#findById(Long)
     */
    @Override
    protected ShareholderVO handleFindById(Long id)
        throws Exception
    {
        // TODO implement protected  ShareholderVO handleFindById(Long id)
        throw new UnsupportedOperationException("bw.org.bocra.portal.shareholder.ShareholderService.handleFindById(Long id) Not implemented!");
    }

    /**
     * @see bw.org.bocra.portal.shareholder.ShareholderService#save(ShareholderVO)
     */
    @Override
    protected ShareholderVO handleSave(ShareholderVO shareholder)
        throws Exception
    {
        // TODO implement protected  ShareholderVO handleSave(ShareholderVO shareholder)
        throw new UnsupportedOperationException("bw.org.bocra.portal.shareholder.ShareholderService.handleSave(ShareholderVO shareholder) Not implemented!");
    }

    /**
     * @see bw.org.bocra.portal.shareholder.ShareholderService#remove(Long)
     */
    @Override
    protected boolean handleRemove(Long id)
        throws Exception
    {
        // TODO implement protected  boolean handleRemove(Long id)
        throw new UnsupportedOperationException("bw.org.bocra.portal.shareholder.ShareholderService.handleRemove(Long id) Not implemented!");
    }

    /**
     * @see bw.org.bocra.portal.shareholder.ShareholderService#getAll()
     */
    @Override
    protected Collection<ShareholderVO> handleGetAll()
        throws Exception
    {
        // TODO implement protected  Collection<ShareholderVO> handleGetAll()
        throw new UnsupportedOperationException("bw.org.bocra.portal.shareholder.ShareholderService.handleGetAll() Not implemented!");
    }

    /**
     * @see bw.org.bocra.portal.shareholder.ShareholderService#search(ShareholderCriteria)
     */
    @Override
    protected Collection<ShareholderVO> handleSearch(ShareholderCriteria criteria)
        throws Exception
    {
        // TODO implement protected  Collection<ShareholderVO> handleSearch(ShareholderCriteria criteria)
        throw new UnsupportedOperationException("bw.org.bocra.portal.shareholder.ShareholderService.handleSearch(ShareholderCriteria criteria) Not implemented!");
    }

    /**
     * @see bw.org.bocra.portal.shareholder.ShareholderService#getAll(Integer, Integer)
     */
    @Override
    protected Collection<ShareholderVO> handleGetAll(Integer pageNumber, Integer pageSize)
        throws Exception
    {
        // TODO implement protected  Collection<ShareholderVO> handleGetAll(Integer pageNumber, Integer pageSize)
        throw new UnsupportedOperationException("bw.org.bocra.portal.shareholder.ShareholderService.handleGetAll(Integer pageNumber, Integer pageSize) Not implemented!");
    }

}