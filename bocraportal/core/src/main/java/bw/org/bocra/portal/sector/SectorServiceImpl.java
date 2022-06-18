// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 * TEMPLATE:    SpringServiceImpl.vsl in andromda-spring cartridge
 * MODEL CLASS: bocraportal::backend::bw.org.bocra.portal::sector::SectorService
 * STEREOTYPE:  Service
 */
package bw.org.bocra.portal.sector;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import bw.org.bocra.portal.licensee.Licensee;
import bw.org.bocra.portal.licensee.LicenseeSector;
import bw.org.bocra.portal.licensee.LicenseeSectorVO;

/**
 * @see bw.org.bocra.portal.sector.SectorService
 */
@Service("sectorService")
public class SectorServiceImpl
        extends SectorServiceBase {

    /**
     * @see bw.org.bocra.portal.sector.SectorService#findById(Long)
     */
    @Override
    protected SectorVO handleFindById(Long id)
            throws Exception {
        return (SectorVO) getSectorDao().load(SectorDao.TRANSFORM_SECTORVO, id);
    }

    /**
     * @see bw.org.bocra.portal.sector.SectorService#save(SectorVO)
     */
    @Override
    protected SectorVO handleSave(SectorVO sector)
            throws Exception {
        Sector sec = getSectorDao().sectorVOToEntity(sector);

        if (sec.getId() == null) {

            sec = getSectorDao().create(sec);
            return getSectorDao().toSectorVO(sec);

        } else {
            getSectorDao().update(sec);
            return sector;
        }

    }

    /**
     * @see bw.org.bocra.portal.sector.SectorService#remove(Long)
     */
    @Override
    protected boolean handleRemove(Long id)
            throws Exception {
        try {
            this.sectorRepository.deleteById(id);
            return true;
        } catch (Exception e) {

            return false;
        }
    }

    private Collection<SectorVO> convertToVOs(Collection<Sector> entities) {
        Collection<SectorVO> vos = new ArrayList<>();

        for (Sector sector : entities) {
            SectorVO vo = new SectorVO();
            getSectorDao().toSectorVO(sector, vo);
            vos.add(vo);
        }

        return vos;
    }

    /**
     * @see bw.org.bocra.portal.sector.SectorService#getAll()
     */
    @Override
    protected Collection<SectorVO> handleGetAll()
            throws Exception {
        return this.convertToVOs(getSectorDao().loadAll());
    }

    /**
     * @see bw.org.bocra.portal.sector.SectorService#search(String)
     */
    @Override
    protected Collection<SectorVO> handleSearch(String criteria)
            throws Exception {
        Collection<Sector> sectors = getSectorDao().findByCriteria(criteria);

        return this.convertToVOs(sectors);
    }

    /**
     * @see bw.org.bocra.portal.sector.SectorService#getAll(Integer, Integer)
     */
    @Override
    protected Collection<SectorVO> handleGetAll(Integer pageNumber, Integer pageSize)
            throws Exception {
        Collection<Sector> sectors = null;

        if (pageNumber < 0 || pageSize < 1) {
            sectors = sectorRepository.findAll();
        } else {
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").descending());
            sectors = sectorRepository.findAll(pageable).getContent();
        }

        return sectors == null ? null : this.convertToVOs(sectors);
    }

    @Override
    protected LicenseeSectorVO handleAddLicensee(Long licenseeId, Long sectorId) throws Exception {

        Sector sector = getSectorDao().load(sectorId);

        if(sector == null) {
            return null;
        }

        Licensee licensee = getLicenseeDao().load(licenseeId);
        if(licensee == null) {
            return null;
        }

        LicenseeSector licenseeSector = getLicenseeSectorDao().create(licensee, sector);
        LicenseeSectorVO lVo = new LicenseeSectorVO();
        lVo.setLicenseeSectorId(licenseeSector.getId());
        lVo.setUin(licensee.getUin());
        lVo.setLicenseeName(licensee.getLicenseeName());
        lVo.setAddress(licensee.getAddress());
        lVo.setId(licensee.getId());
        
        return lVo;
    }

}