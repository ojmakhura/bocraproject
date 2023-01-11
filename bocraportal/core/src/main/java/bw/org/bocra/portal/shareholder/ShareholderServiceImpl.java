package bw.org.bocra.portal.shareholder;

import bw.org.bocra.portal.licence.LicenceVO;

import java.util.ArrayList;
import java.util.Collection;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @see bw.org.bocra.portal.shareholder.ShareholderService
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
     * @see bw.org.bocra.portal.shareholder.ShareholderService#findById(Long)
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
     * @see bw.org.bocra.portal.shareholder.ShareholderService#save(ShareholderVO)
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
     * @see bw.org.bocra.portal.shareholder.ShareholderService#remove(Long)
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
     * @see bw.org.bocra.portal.shareholder.ShareholderService#getAll()
     */
    @Override
    protected Collection<ShareholderVO> handleGetAll()
            throws Exception {
        return (Collection<ShareholderVO>) shareholderDao.loadAll(ShareholderDao.TRANSFORM_SHAREHOLDERVO);
    }

    /**
     * @see bw.org.bocra.portal.shareholder.ShareholderService#search(ShareholderCriteria)
     */
    @Override
    protected Collection<ShareholderVO> handleSearch(ShareholderCriteria criteria)
            throws Exception {
        Collection<Shareholder> holders = this.shareholderDao.findByCriteria(criteria);
        Collection<ShareholderVO> vos = new ArrayList<>();
        // throw new UnsupportedOperationException(
        // "bw.org.bocra.portal.shareholder.loadShareholderFromShareholderVO(ShareholderVO)
        // not yet implemented.");
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