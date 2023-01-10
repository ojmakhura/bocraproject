// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 * TEMPLATE:    SpringServiceImpl.vsl in andromda-spring cartridge
 * MODEL CLASS: bocraportal::backend::bw.org.bocra.portal::licensee::shares::LicenseeShareholderService
 * STEREOTYPE:  Service
 */
package bw.org.bocra.portal.licensee.shares;

import bw.org.bocra.portal.licensee.LicenseeDao;
import bw.org.bocra.portal.licensee.LicenseeRepository;
import bw.org.bocra.portal.licensee.form.LicenseeFormVO;
import bw.org.bocra.portal.shareholder.ShareholderDao;
import bw.org.bocra.portal.shareholder.ShareholderRepository;

import java.util.Collection;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @see bw.org.bocra.portal.licensee.shares.LicenseeShareholderService
 */
@Service("LicenseeShareholderService")
@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
public class LicenseeShareholderServiceImpl
    extends LicenseeShareholderServiceBase
{
    // public LicenseeShareholderServiceImpl(
    //     LicenseeShareholderDao licenseeShareholder,
    //     LicenseeShareholderRepository licenseeShareholderRepository,
    //     ShareholderDao shareholder,
    //     ShareholderRepository shareholderRepository,
    //     LicenseeDao licensee,
    //     LicenseeRepository licenseeRepository,
    //     MessageSource messageSource
    // ) {
        
    //     super(
    //         licenseeShareholder,
    //         licenseeShareholderRepository,
    //         shareholder,
    //         shareholderRepository,
    //         licensee,
    //         licenseeRepository,
    //         messageSource
    //     );
    // }

    public LicenseeShareholderServiceImpl(LicenseeShareholderDao licenseeShareholderDao,
            LicenseeShareholderRepository licenseeShareholderRepository, ShareholderDao shareholderDao,
            ShareholderRepository shareholderRepository, LicenseeDao licenseeDao, LicenseeRepository licenseeRepository,
            MessageSource messageSource) {
        super(licenseeShareholderDao, licenseeShareholderRepository, shareholderDao, shareholderRepository, licenseeDao,
                licenseeRepository, messageSource);
        //TODO Auto-generated constructor stub
    }

    /**
     * @see bw.org.bocra.portal.licensee.shares.LicenseeShareholderService#findById(Long)
     */
    @Override
    protected LicenseeShareholderVO handleFindById(Long id)
        throws Exception
    {
        // TODO implement protected  LicenseeShareholderVO handleFindById(Long id)
        throw new UnsupportedOperationException("bw.org.bocra.portal.licensee.shares.LicenseeShareholderService.handleFindById(Long id) Not implemented!");
    }

    /**
     * @see bw.org.bocra.portal.licensee.shares.LicenseeShareholderService#remove(Long)
     */
    @Override
    protected boolean handleRemove(Long id)
        throws Exception
    {
        // TODO implement protected  boolean handleRemove(Long id)
        throw new UnsupportedOperationException("bw.org.bocra.portal.licensee.shares.LicenseeShareholderService.handleRemove(Long id) Not implemented!");
    }

    /**
     * @see bw.org.bocra.portal.licensee.shares.LicenseeShareholderService#getAll()
     */
    @Override
    protected Collection<LicenseeShareholderVO> handleGetAll()
        throws Exception
    {
        // TODO implement protected  Collection<LicenseeShareholderVO> handleGetAll()
        throw new UnsupportedOperationException("bw.org.bocra.portal.licensee.shares.LicenseeShareholderService.handleGetAll() Not implemented!");
    }

    /**
     * @see bw.org.bocra.portal.licensee.shares.LicenseeShareholderService#findByLicensee(Long)
     */
    @Override
    protected Collection<LicenseeShareholderVO> handleFindByLicensee(Long licenseeId)
        throws Exception
    {
        // TODO implement protected  Collection<LicenseeShareholderVO> handleFindByLicensee(Long licenseeId)
        throw new UnsupportedOperationException("bw.org.bocra.portal.licensee.shares.LicenseeShareholderService.handleFindByLicensee(Long licenseeId) Not implemented!");
    }

    /**
     * @see bw.org.bocra.portal.licensee.shares.LicenseeShareholderService#findByShareholder(Long)
     */
    @Override
    protected Collection<LicenseeShareholderVO> handleFindByShareholder(Long formId)
        throws Exception
    {
        // TODO implement protected  Collection<LicenseeShareholderVO> handleFindByShareholder(Long formId)
        throw new UnsupportedOperationException("bw.org.bocra.portal.licensee.shares.LicenseeShareholderService.handleFindByShareholder(Long formId) Not implemented!");
    }

    /**
     * @see bw.org.bocra.portal.licensee.shares.LicenseeShareholderService#updateLicensee(Long, Long)
     */
    @Override
    protected LicenseeFormVO handleUpdateLicensee(Long id, Long licenseeId)
        throws Exception
    {
        // TODO implement protected  LicenseeFormVO handleUpdateLicensee(Long id, Long licenseeId)
        throw new UnsupportedOperationException("bw.org.bocra.portal.licensee.shares.LicenseeShareholderService.handleUpdateLicensee(Long id, Long licenseeId) Not implemented!");
    }

    /**
     * @see bw.org.bocra.portal.licensee.shares.LicenseeShareholderService#updateShareholder(Long, Long)
     */
    @Override
    protected LicenseeShareholderVO handleUpdateShareholder(Long id, Long formId)
        throws Exception
    {
        // TODO implement protected  LicenseeShareholderVO handleUpdateShareholder(Long id, Long formId)
        throw new UnsupportedOperationException("bw.org.bocra.portal.licensee.shares.LicenseeShareholderService.handleUpdateShareholder(Long id, Long formId) Not implemented!");
    }

    @Override
    protected LicenseeShareholderVO handleCreate(Long licenseeId, Long shareholderId, Integer numberOfShares)
            throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

}