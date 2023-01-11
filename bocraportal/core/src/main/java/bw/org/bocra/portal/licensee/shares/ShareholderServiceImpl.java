// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 * TEMPLATE:    SpringServiceImpl.vsl in andromda-spring cartridge
 * MODEL CLASS: bocraportal::backend::bw.org.bocra.portal::licensee::shares::ShareholderService
 * STEREOTYPE:  Service
 */
package bw.org.bocra.portal.licensee.shares;

import bw.org.bocra.portal.licence.LicenceVO;

import java.util.ArrayList;
import java.util.Collection;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @see bw.org.bocra.portal.licensee.shares.ShareholderService
 */
@Service("shareholderService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class ShareholderServiceImpl
        extends ShareholderServiceBase {
    public ShareholderServiceImpl(
            ShareholderDao shareholder,
            ShareholderRepository shareholderRepository,
            MessageSource messageSource) {

        super(
                shareholder,
                shareholderRepository,
                messageSource);
    }

    /**
     * @see bw.org.bocra.portal.licensee.shares.ShareholderService#findById(Long)
     */
    @Override
    protected ShareholderVO handleFindById(Long id)
            throws Exception {
        if (id == null) {
            return null;
        }

        Shareholder holder = getShareholderDao().load(id);
        return getShareholderDao().toShareholderVO(holder);
    }

    /**
     * @see bw.org.bocra.portal.licensee.shares.ShareholderService#save(ShareholderVO)
     */
    @Override
    protected ShareholderVO handleSave(ShareholderVO shareholder)
            throws Exception {
        Shareholder entity = shareholderDao.shareholderVOToEntity(shareholder);

        if (shareholder.getId() == null) {
            entity = shareholderDao.create(entity);
        } else {
            shareholderDao.update(entity);
        }

        return getShareholderDao().toShareholderVO(entity);
    }

    /**
     * @see bw.org.bocra.portal.licensee.shares.ShareholderService#remove(Long)
     */
    @Override
    protected boolean handleRemove(Long id)
            throws Exception {
        if (id != null) {
            shareholderRepository.deleteById(id);

            return true;
        }

        return false;
    }

    /**
     * @see bw.org.bocra.portal.licensee.shares.ShareholderService#getAll()
     */
    @Override
    protected Collection<LicenceVO> handleGetAll()
            throws Exception {
        // TODO implement protected Collection<LicenceVO> handleGetAll()
        throw new UnsupportedOperationException(
                "bw.org.bocra.portal.licensee.shares.ShareholderService.handleGetAll() Not implemented!");
        // return (Collection<ShareholderVO>)
        // shareholderDao.loadAll(ShareholderDao.TRANSFORM_SHAREHOLDERVO);
    }

    /**
     * @see bw.org.bocra.portal.licensee.shares.ShareholderService#search(ShareholderVO)
     */
    @Override
    protected Collection<ShareholderVO> handleSearch(ShareholderCriteria criteria)
            throws Exception {
        Collection<Shareholder> holders = this.shareholderDao.findByCriteria(criteria);
        Collection<ShareholderVO> vos = new ArrayList<>();

        for (Shareholder holder : holders) {
            ShareholderVO vo = new ShareholderVO();
            getShareholderDao().toShareholderVO(holder, vo);
            vos.add(vo);
        }
        return vos;
    }

    /**
     * @see bw.org.bocra.portal.licensee.shares.ShareholderService#getAll(Integer,
     *      Integer)
     */
    @Override
    protected Collection<ShareholderVO> handleGetAll(Integer pageNumber, Integer pageSize)
            throws Exception {
        return (Collection<ShareholderVO>) shareholderDao.loadAll(ShareholderDao.TRANSFORM_SHAREHOLDERVO, pageNumber,
                pageSize);
    }

}