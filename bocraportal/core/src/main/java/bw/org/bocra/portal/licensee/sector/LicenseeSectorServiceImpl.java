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
import org.springframework.data.jpa.domain.Specification;
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
    protected LicenseeSectorVO handleFindById(Long id)
        throws Exception
    {
        LicenseeSector ls = getLicenseeSectorDao().load(id);
        return getLicenseeSectorDao().toLicenseeSectorVO(ls);
    }

    /**
     * @see bw.org.bocra.portal.licensee.sector.LicenseeSectorService#create(Long, Long)
     */
    @Override
    protected LicenseeSectorVO handleCreate(Long licenseeId, Long sectorId)
        throws Exception
    {

        LicenseeSector ls = LicenseeSector.Factory.newInstance();
        ls.setLicensee(getLicenseeDao().load(licenseeId));
        ls.setSector(getSectorDao().load(sectorId));

        ls = getLicenseeSectorDao().create(ls);
        return getLicenseeSectorDao().toLicenseeSectorVO(ls);
    }

    /**
     * @see bw.org.bocra.portal.licensee.sector.LicenseeSectorService#remove(Long)
     */
    @Override
    protected boolean handleRemove(Long id)
        throws Exception
    {
        getLicenseeSectorDao().remove(id);
        return true;
    }

    /**
     * @see bw.org.bocra.portal.licensee.sector.LicenseeSectorService#getAll()
     */
    @Override
    protected Collection<LicenseeSectorVO> handleGetAll()
        throws Exception
    {
        return (Collection<LicenseeSectorVO>) getLicenseeSectorDao().loadAll(LicenseeSectorDao.TRANSFORM_LICENSEESECTORVO);
    }

    /**
     * @see bw.org.bocra.portal.licensee.sector.LicenseeSectorService#findByLicensee(Long)
     */
    @Override
    protected Collection<LicenseeSectorVO> handleFindByLicensee(Long licenseeId)
        throws Exception
    {
        Specification<LicenseeSector> specs = LicenseeSectorSpecifications.findByLicenseeId(licenseeId);
        Collection<LicenseeSector> lss = getLicenseeSectorRepository().findAll(specs);

        return getLicenseeSectorDao().toLicenseeSectorVOCollection(lss);
    }

    /**
     * @see bw.org.bocra.portal.licensee.sector.LicenseeSectorService#findBySector(Long)
     */
    @Override
    protected Collection<LicenseeSectorVO> handleFindBySector(Long sectorId)
        throws Exception
    {
        Specification<LicenseeSector> specs = LicenseeSectorSpecifications.findBySectorId(sectorId);
        Collection<LicenseeSector> lss = getLicenseeSectorRepository().findAll(specs);

        return getLicenseeSectorDao().toLicenseeSectorVOCollection(lss);
    }

    /**
     * @see bw.org.bocra.portal.licensee.sector.LicenseeSectorService#updateLicensee(Long, Long)
     */
    @Override
    protected LicenseeSectorVO handleUpdateLicensee(Long id, Long licenseeId)
        throws Exception
    {
        LicenseeSector ls = getLicenseeSectorDao().load(id);
        ls.setLicensee(getLicenseeDao().load(licenseeId));

        return getLicenseeSectorDao().toLicenseeSectorVO(ls);
    }

    /**
     * @see bw.org.bocra.portal.licensee.sector.LicenseeSectorService#updateSector(Long, Long)
     */
    @Override
    protected LicenseeSectorVO handleUpdateSector(Long id, Long sectorId)
        throws Exception
    {
        LicenseeSector ls = getLicenseeSectorDao().load(id);
        ls.setSector(getSectorDao().load(sectorId));

        return getLicenseeSectorDao().toLicenseeSectorVO(ls);
    }

    

}