// Generated by hibernate/SpringHibernateDaoImpl.vsl in andromda-spring-cartridge on $springUtils.date.
// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package bw.org.bocra.portal.sector;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import bw.org.bocra.portal.licensee.Licensee;
import bw.org.bocra.portal.licensee.LicenseeSector;
import bw.org.bocra.portal.licensee.LicenseeSectorSpecifications;
import bw.org.bocra.portal.licensee.LicenseeSectorVO;
import bw.org.bocra.portal.licensee.LicenseeVO;

/**
 * @see Sector
 */
@Repository("sectorDao")
public class SectorDaoImpl
    extends SectorDaoBase
{
    /**
     * {@inheritDoc}
     */
    @Override
    public void toSectorVO(
        Sector source,
        SectorVO target)
    {
        // TODO verify behavior of toSectorVO
        super.toSectorVO(source, target);

        if(CollectionUtils.isNotEmpty(source.getLicenseeSectors())) {
            Collection<LicenseeSectorVO> licensees = new ArrayList<>();

            for(LicenseeSector l : source.getLicenseeSectors()) {
                LicenseeSectorVO lvo = new LicenseeSectorVO();
                lvo.setLicenseeSectorId(l.getId());
                lvo.setId(l.getLicensee().getId());
                //lvo.setCreatedBy(l.getLicensee().getCreatedBy());
                //lvo.setCreatedDate(l.getLicensee().getCreatedDate());
                lvo.setLicenseeName(l.getLicensee().getLicenseeName());
                lvo.setStatus(l.getLicensee().getStatus());
                lvo.setUin(l.getLicensee().getUin());
                licensees.add(lvo);
            }

            target.setLicensees(licensees);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SectorVO toSectorVO(final Sector entity)
    {
        // TODO verify behavior of toSectorVO
        return super.toSectorVO(entity);
    }

    /**
     * Retrieves the entity object that is associated with the specified value object
     * from the object store. If no such entity object exists in the object store,
     * a new, blank entity is created
     */
    private Sector loadSectorFromSectorVO(SectorVO sectorVO)
    {
        if (sectorVO.getId() == null)
        {
            return  Sector.Factory.newInstance();
        }
        else
        {
            return this.load(sectorVO.getId());
        }
    }

    /**
     * {@inheritDoc}
     */
    public Sector sectorVOToEntity(SectorVO sectorVO)
    {
        // TODO verify behavior of sectorVOToEntity
        Sector entity = this.loadSectorFromSectorVO(sectorVO);
        this.sectorVOToEntity(sectorVO, entity, true);
        return entity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sectorVOToEntity(
        SectorVO source,
        Sector target,
        boolean copyIfNull)
    {
        // TODO verify behavior of sectorVOToEntity
        super.sectorVOToEntity(source, target, copyIfNull);

        if(CollectionUtils.isNotEmpty(source.getLicensees())) {
            Collection<LicenseeSector> licensees = new ArrayList<>();

            for(LicenseeSectorVO lvo : source.getLicensees()) {

                if(lvo.getLicenseeSectorId() != null) {
                    licensees.add(licenseeSectorRepository.getById(lvo.getLicenseeSectorId()));

                } else if(lvo.getId() != null) {
                    LicenseeSector ls = LicenseeSector.Factory.newInstance(licenseeDao.load(lvo.getId()), target);
                    licensees.add(licenseeSectorRepository.save(ls));
                }
            }

            target.setLicenseeSectors(licensees);
        }
    }

    @Override
    protected Collection<Sector> handleFindByCriteria(String criteria) throws Exception {
        
        Specification<Sector> specs = null;
        
        if(StringUtils.isNotBlank(criteria)) {
            specs = SectorSpecifications.findByCodeContainingIgnoreCase(criteria)
                    .or(SectorSpecifications.findByNameContainingIgnoreCase(criteria))
                    .or(SectorSpecifications.findByDescriptionContainingIgnoreCase(criteria));
        }

        return this.sectorRepository.findAll(specs, Sort.by("name").ascending());
    }
}