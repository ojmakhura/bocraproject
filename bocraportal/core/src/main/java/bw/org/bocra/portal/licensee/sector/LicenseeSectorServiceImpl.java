// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 * TEMPLATE:    SpringServiceImpl.vsl in andromda-spring cartridge
 * MODEL CLASS: bocraportal::backend::bw.org.bocra.portal::licensee::sector::LicenseeSectorService
 * STEREOTYPE:  Service
 */
package bw.org.bocra.portal.licensee.sector;

import bw.org.bocra.portal.form.FormVO;
import bw.org.bocra.portal.licensee.LicenseeDao;
import bw.org.bocra.portal.licensee.LicenseeRepository;
import bw.org.bocra.portal.licensee.form.LicenseeFormVO;
import bw.org.bocra.portal.sector.SectorDao;
import bw.org.bocra.portal.sector.SectorRepository;
import java.util.Collection;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @see bw.org.bocra.portal.licensee.sector.LicenseeSectorService
 */
@Service("licenseeSectorService")
@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
public class LicenseeSectorServiceImpl
    extends LicenseeSectorServiceBase
{
    public LicenseeSectorServiceImpl(
        LicenseeDao licensee,
        LicenseeRepository licenseeRepository,
        SectorDao sector,
        SectorRepository sectorRepository,
        LicenseeSectorDao licenseeSector,
        LicenseeSectorRepository licenseeSectorRepository,
        MessageSource messageSource
    ) {
        
        super(
            licensee,
            licenseeRepository,
            sector,
            sectorRepository,
            licenseeSector,
            licenseeSectorRepository,
            messageSource
        );
    }

    /**
     * @see bw.org.bocra.portal.licensee.sector.LicenseeSectorService#findById(Long)
     */
    @Override
    protected LicenseeFormVO handleFindById(Long id)
        throws Exception
    {
        // TODO implement protected  LicenseeFormVO handleFindById(Long id)
        throw new UnsupportedOperationException("bw.org.bocra.portal.licensee.sector.LicenseeSectorService.handleFindById(Long id) Not implemented!");
    }

    /**
     * @see bw.org.bocra.portal.licensee.sector.LicenseeSectorService#create(Long, Long)
     */
    @Override
    protected LicenseeFormVO handleCreate(Long licenseeId, Long sectorId)
        throws Exception
    {
        // TODO implement protected  LicenseeFormVO handleCreate(Long licenseeId, Long sectorId)
        throw new UnsupportedOperationException("bw.org.bocra.portal.licensee.sector.LicenseeSectorService.handleCreate(Long licenseeId, Long sectorId) Not implemented!");
    }

    /**
     * @see bw.org.bocra.portal.licensee.sector.LicenseeSectorService#remove(Long)
     */
    @Override
    protected boolean handleRemove(Long id)
        throws Exception
    {
        // TODO implement protected  boolean handleRemove(Long id)
        throw new UnsupportedOperationException("bw.org.bocra.portal.licensee.sector.LicenseeSectorService.handleRemove(Long id) Not implemented!");
    }

    /**
     * @see bw.org.bocra.portal.licensee.sector.LicenseeSectorService#getAll()
     */
    @Override
    protected Collection<LicenseeFormVO> handleGetAll()
        throws Exception
    {
        // TODO implement protected  Collection<LicenseeFormVO> handleGetAll()
        throw new UnsupportedOperationException("bw.org.bocra.portal.licensee.sector.LicenseeSectorService.handleGetAll() Not implemented!");
    }

    /**
     * @see bw.org.bocra.portal.licensee.sector.LicenseeSectorService#findByLicensee(Long)
     */
    @Override
    protected Collection<LicenseeFormVO> handleFindByLicensee(Long licenseeId)
        throws Exception
    {
        // TODO implement protected  Collection<LicenseeFormVO> handleFindByLicensee(Long licenseeId)
        throw new UnsupportedOperationException("bw.org.bocra.portal.licensee.sector.LicenseeSectorService.handleFindByLicensee(Long licenseeId) Not implemented!");
    }

    /**
     * @see bw.org.bocra.portal.licensee.sector.LicenseeSectorService#findBySector(Long)
     */
    @Override
    protected Collection<FormVO> handleFindBySector(Long sectorId)
        throws Exception
    {
        // TODO implement protected  Collection<FormVO> handleFindBySector(Long sectorId)
        throw new UnsupportedOperationException("bw.org.bocra.portal.licensee.sector.LicenseeSectorService.handleFindBySector(Long sectorId) Not implemented!");
    }

    /**
     * @see bw.org.bocra.portal.licensee.sector.LicenseeSectorService#updateLicensee(Long, Long)
     */
    @Override
    protected LicenseeFormVO handleUpdateLicensee(Long id, Long licenseeId)
        throws Exception
    {
        // TODO implement protected  LicenseeFormVO handleUpdateLicensee(Long id, Long licenseeId)
        throw new UnsupportedOperationException("bw.org.bocra.portal.licensee.sector.LicenseeSectorService.handleUpdateLicensee(Long id, Long licenseeId) Not implemented!");
    }

    /**
     * @see bw.org.bocra.portal.licensee.sector.LicenseeSectorService#updateSector(Long, Long)
     */
    @Override
    protected LicenseeFormVO handleUpdateSector(Long id, Long sectorId)
        throws Exception
    {
        // TODO implement protected  LicenseeFormVO handleUpdateSector(Long id, Long sectorId)
        throw new UnsupportedOperationException("bw.org.bocra.portal.licensee.sector.LicenseeSectorService.handleUpdateSector(Long id, Long sectorId) Not implemented!");
    }

    

}