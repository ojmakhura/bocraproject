// Generated by hibernate/SpringHibernateDaoImpl.vsl in andromda-spring-cartridge on $springUtils.date.
// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package bw.org.bocra.portal.sector.form;

import bw.org.bocra.portal.form.Form;
import bw.org.bocra.portal.form.FormRepository;
import bw.org.bocra.portal.form.FormVO;
import bw.org.bocra.portal.sector.Sector;
import bw.org.bocra.portal.sector.SectorRepository;
import bw.org.bocra.portal.sector.SectorVO;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @see SectorForm
 */
@Repository("sectorFormDao")
@Transactional
public class SectorFormDaoImpl
    extends SectorFormDaoBase
{
   
    public SectorFormDaoImpl(FormRepository formRepository, SectorRepository sectorRepository,
            SectorFormRepository sectorFormRepository) {

        super(formRepository, sectorRepository, sectorFormRepository);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void toSectorFormVO(
        SectorForm source,
        SectorFormVO target)
    {
        // TODO verify behavior of toSectorFormVO
        super.toSectorFormVO(source, target);
        // WARNING! No conversion for target.form (can't convert source.getForm():bw.org.bocra.portal.form.Form to bw.org.bocra.portal.form.FormVO
        if(source.getForm() != null && source.getForm().getId() != null) {
            FormVO form = new FormVO();
            form.setId(source.getForm().getId());
            form.setCreatedBy(source.getForm().getCreatedBy());
            form.setUpdatedBy(source.getForm().getUpdatedBy());
            form.setCreatedDate(source.getForm().getCreatedDate());
            form.setUpdatedDate(source.getForm().getUpdatedDate());
            form.setCode(source.getForm().getCode());
            form.setFormName(source.getForm().getFormName());
            form.setEntryType(source.getForm().getEntryType());

            target.setForm(form);
        }

        // WARNING! No conversion for target.sector (can't convert source.getSector():bw.org.bocra.portal.sector.Sector to bw.org.bocra.portal.sector.SectorVO
        if(source.getSector() != null && source.getSector().getId() != null) {
            SectorVO sector = new SectorVO();
            sector.setId(source.getSector().getId());
            sector.setCreatedBy(source.getSector().getCreatedBy());
            sector.setUpdatedBy(source.getSector().getUpdatedBy());
            sector.setCreatedDate(source.getSector().getCreatedDate());
            sector.setUpdatedDate(source.getSector().getUpdatedDate());
            sector.setCode(source.getSector().getCode());
            sector.setName(source.getSector().getName());
            sector.setDescription(source.getSector().getDescription());

            target.setSector(sector);
        }

                
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SectorFormVO toSectorFormVO(final SectorForm entity)
    {
        // TODO verify behavior of toSectorFormVO
        return super.toSectorFormVO(entity);
    }

    /**
     * Retrieves the entity object that is associated with the specified value object
     * from the object store. If no such entity object exists in the object store,
     * a new, blank entity is created
     */
    private SectorForm loadSectorFormFromSectorFormVO(SectorFormVO sectorFormVO)
    {
        if (sectorFormVO.getId() == null)
        {
            return  SectorForm.Factory.newInstance();
        }
        else
        {
            return this.load(sectorFormVO.getId());
        }
    }

    /**
     * {@inheritDoc}
     */
    public SectorForm sectorFormVOToEntity(SectorFormVO sectorFormVO)
    {
        // TODO verify behavior of sectorFormVOToEntity
        SectorForm entity = this.loadSectorFormFromSectorFormVO(sectorFormVO);
        this.sectorFormVOToEntity(sectorFormVO, entity, true);
        return entity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sectorFormVOToEntity(
        SectorFormVO source,
        SectorForm target,
        boolean copyIfNull)
    {
        // TODO verify behavior of sectorFormVOToEntity
        super.sectorFormVOToEntity(source, target, copyIfNull);
        // WARNING! No conversion for target.form (can't convert source.getForm():bw.org.bocra.portal.form.Form to bw.org.bocra.portal.form.FormVO
        if(source.getForm() != null && source.getForm().getId() != null) {
            Form form = getFormDao().load(source.getForm().getId());

            target.setForm(form);
        }

        // WARNING! No conversion for target.sector (can't convert source.getSector():bw.org.bocra.portal.sector.Sector to bw.org.bocra.portal.sector.SectorVO
        if(source.getSector() != null && source.getSector().getId() != null) {
            Sector sector = getSectorDao().load(source.getSector().getId());
            target.setSector(sector);
        }
    }
}