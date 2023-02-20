// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 * TEMPLATE:    SpringServiceImpl.vsl in andromda-spring cartridge
 * MODEL CLASS: bocraportal::backend::bw.org.bocra.portal::licensee::shares::LicenseeShareholderService
 * STEREOTYPE:  Service
 */
package bw.org.bocra.portal.licensee.shares;

import bw.org.bocra.portal.licensee.Licensee;
import bw.org.bocra.portal.licensee.LicenseeDao;
import bw.org.bocra.portal.licensee.LicenseeRepository;
import bw.org.bocra.portal.licensee.form.LicenseeFormVO;
import bw.org.bocra.portal.shareholder.Shareholder;
import bw.org.bocra.portal.shareholder.ShareholderDao;
import bw.org.bocra.portal.shareholder.ShareholderRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.context.MessageSource;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @see bw.org.bocra.portal.licensee.shares.LicenseeShareholderService
 */
@Service("LicenseeShareholderService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class LicenseeShareholderServiceImpl
        extends LicenseeShareholderServiceBase {
            
    public LicenseeShareholderServiceImpl(LicenseeShareholderDao licenseeShareholderDao,
            LicenseeShareholderRepository licenseeShareholderRepository, ShareholderDao shareholderDao,
            ShareholderRepository shareholderRepository, LicenseeDao licenseeDao,
            LicenseeRepository licenseeRepository,
            MessageSource messageSource) {
        super(licenseeShareholderDao, licenseeShareholderRepository, shareholderDao, shareholderRepository,
                licenseeDao,
                licenseeRepository, messageSource);
        // TODO Auto-generated constructor stub
    }

    /**
     * @see bw.org.bocra.portal.licensee.shares.LicenseeShareholderService#findById(Long)
     */
    @Override
    protected LicenseeShareholderVO handleFindById(Long id)
            throws Exception {
        LicenseeShareholder ls = getLicenseeShareholderDao().load(id);
        return getLicenseeShareholderDao().toLicenseeShareholderVO(ls);
    }

    /**
     * @see bw.org.bocra.portal.licensee.shares.LicenseeShareholderService#remove(Long)
     */
    @Override
    protected boolean handleRemove(Long id)
            throws Exception {
        getLicenseeShareholderRepository().deleteById(id);
        return true;
    }

    /**
     * @see bw.org.bocra.portal.licensee.shares.LicenseeShareholderService#getAll()
     */
    @Override
    protected Collection<LicenseeShareholderVO> handleGetAll()
            throws Exception {
        return (Collection<LicenseeShareholderVO>) getLicenseeShareholderDao()
                .loadAll(LicenseeShareholderDao.TRANSFORM_LICENSEESHAREHOLDERVO);
    }

    /**
     * @see bw.org.bocra.portal.licensee.shares.LicenseeShareholderService#findByLicensee(Long)
     */
    @Override
    protected Collection<LicenseeShareholderVO> handleFindByLicensee(Long licenseeId)
            throws Exception {
        Specification<LicenseeShareholder> spec = LicenseeShareholderSpecifications.findByLicenseeId(licenseeId);
        Collection<LicenseeShareholder> holders = getLicenseeShareholderRepository().findAll(spec);
        Collection<LicenseeShareholderVO> vos = new ArrayList<>();

        for (LicenseeShareholder licenseeShareholder : holders) {
            LicenseeShareholderVO vo = new LicenseeShareholderVO();
            getLicenseeShareholderDao().toLicenseeShareholderVO(licenseeShareholder, vo);

            vos.add(vo);
        }
        return vos;
    }

    /**
     * @see bw.org.bocra.portal.licensee.shares.LicenseeShareholderService#findByShareholder(Long)
     */
    @Override
    protected Collection<LicenseeShareholderVO> handleFindByShareholder(Long shareholderId)
            throws Exception {
        Specification<LicenseeShareholder> spec = LicenseeShareholderSpecifications.findByShareholderId(shareholderId);
        Collection<LicenseeShareholder> holders = getLicenseeShareholderRepository().findAll(spec);
        Collection<LicenseeShareholderVO> vos = new ArrayList<>();

        for (LicenseeShareholder licenseeShareholder : holders) {
            LicenseeShareholderVO vo = new LicenseeShareholderVO();
            getLicenseeShareholderDao().toLicenseeShareholderVO(licenseeShareholder, vo);

            vos.add(vo);
        }
        return vos;
    }

    /**
     * @see bw.org.bocra.portal.licensee.shares.LicenseeShareholderService#updateLicensee(Long,
     *      Long)
     */
    @Override
    protected LicenseeShareholderVO handleUpdateLicensee(Long id, Long licenseeId)
            throws Exception {
        LicenseeShareholder ls = getLicenseeShareholderDao().load(id);
        ls.setLicensee(getLicenseeDao().load(licenseeId));
        ls = getLicenseeShareholderRepository().save(ls);

        return getLicenseeShareholderDao().toLicenseeShareholderVO(ls);
    }

    /**
     * @see bw.org.bocra.portal.licensee.shares.LicenseeShareholderService#updateShareholder(Long,
     *      Long)
     */
    @Override
    protected LicenseeShareholderVO handleUpdateShareholder(Long id, Long shareholderId)
            throws Exception {
        LicenseeShareholder ls = getLicenseeShareholderDao().load(id);
        ls.setShareholder(getShareholderDao().load(shareholderId));
        ls = getLicenseeShareholderRepository().save(ls);

        return getLicenseeShareholderDao().toLicenseeShareholderVO(ls);
    }

    @Override
    protected LicenseeShareholderVO handleCreate(Long licenseeId, Long shareholderId, Integer numberOfShares)
            throws Exception {

        Licensee licensee = getLicenseeDao().load(licenseeId);
        Shareholder holder = getShareholderDao().load(shareholderId);
        LicenseeShareholder ls = LicenseeShareholder.Factory.newInstance();
        ls.setLicensee(licensee);
        ls.setShareholder(holder);
        ls.setNumberOfShares(numberOfShares);
        ls = getLicenseeShareholderDao().create(ls);

        return getLicenseeShareholderDao().toLicenseeShareholderVO(ls);

    }

    @Override
    protected LicenseeShareholderVO handleAttachDocument(Long id, Long documentId) throws Exception {
        LicenseeShareholder ls = licenseeShareholderRepository.getReferenceById(id);
        return getLicenseeShareholderDao().toLicenseeShareholderVO(ls);
    }

    @Override
    protected LicenseeShareholderVO handleSave(LicenseeShareholderVO licenseeShareholder) throws Exception {

        LicenseeShareholder ls = licenseeShareholderDao.licenseeShareholderVOToEntity(licenseeShareholder);
        ls = licenseeShareholderRepository.save(ls);
        return getLicenseeShareholderDao().toLicenseeShareholderVO(ls);
    }

    @Override
    protected LicenseeShareholderVO handleUpdateNumberOfShares(Long id, Integer numberOfShares) throws Exception {
        
        LicenseeShareholder ls = getLicenseeShareholderDao().load(id);
        ls.setNumberOfShares(numberOfShares);
        ls = getLicenseeShareholderRepository().save(ls);

        return getLicenseeShareholderDao().toLicenseeShareholderVO(ls);
    }

}