// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 * TEMPLATE:    SpringServiceImpl.vsl in andromda-spring cartridge
 * MODEL CLASS: bocraportal::backend::bw.org.bocra.portal::sector::form::SectorFormService
 * STEREOTYPE:  Service
 */
package bw.org.bocra.portal.sector.form;

import java.util.Collection;
import org.springframework.context.MessageSource;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import bw.org.bocra.portal.form.Form;
import bw.org.bocra.portal.form.FormDao;
import bw.org.bocra.portal.form.FormRepository;
import bw.org.bocra.portal.sector.Sector;
import bw.org.bocra.portal.sector.SectorDao;
import bw.org.bocra.portal.sector.SectorRepository;

/**
 * @see bw.org.bocra.portal.sector.form.SectorFormService
 */
@Service("sectorFormService")
@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
public class SectorFormServiceImpl
    extends SectorFormServiceBase
{
    

    public SectorFormServiceImpl(SectorFormDao sectorFormDao, SectorFormRepository sectorFormRepository,
            SectorDao sectorDao, SectorRepository sectorRepository, FormDao formDao, FormRepository formRepository,
            MessageSource messageSource) {

        super(sectorFormDao, sectorFormRepository, sectorDao, sectorRepository, formDao, formRepository, messageSource);
    }

    /**
     * @see bw.org.bocra.portal.sector.form.SectorFormService#findById(Long)
     */
    @Override
    protected SectorFormVO handleFindById(Long id)
        throws Exception
    {
        return (SectorFormVO) getSectorFormDao().get(SectorFormDao.TRANSFORM_SECTORFORMVO, id);
    }

    /**
     * @see bw.org.bocra.portal.sector.form.SectorFormService#create(Long, Long)
     */
    @Override
    protected SectorFormVO handleCreate(Long sectorId, Long formId)
        throws Exception
    {
        Form form = getFormDao().load(formId);
        Sector sector = getSectorDao().load(sectorId);

        SectorForm sectorForm = getSectorFormDao().create(form, sector);

        return getSectorFormDao().toSectorFormVO(sectorForm);
    }

    /**
     * @see bw.org.bocra.portal.sector.form.SectorFormService#remove(Long)
     */
    @Override
    protected boolean handleRemove(Long id)
        throws Exception
    {
        getSectorFormRepository().deleteById(id);
        return true;
    }

    /**
     * @see bw.org.bocra.portal.sector.form.SectorFormService#getAll()
     */
    @Override
    protected Collection<SectorFormVO> handleGetAll()
        throws Exception
    {
        return (Collection<SectorFormVO>) getSectorFormDao().loadAll(SectorFormDao.TRANSFORM_SECTORFORMVO);
    }

    /**
     * @see bw.org.bocra.portal.sector.form.SectorFormService#findBySector(Long)
     */
    @Override
    protected Collection<SectorFormVO> handleFindBySector(Long sectorId)
        throws Exception
    {
        Specification<SectorForm> spec = SectorFormSpecifications.findBySectorId(sectorId);

        Collection<SectorForm> forms = sectorFormRepository.findAll(spec);
        return getSectorFormDao().toSectorFormVOCollection(forms);
    }

    /**
     * @see bw.org.bocra.portal.sector.form.SectorFormService#findByForm(Long)
     */
    @Override
    protected Collection<SectorFormVO> handleFindByForm(Long formId)
        throws Exception
    {
        Specification<SectorForm> spec = SectorFormSpecifications.findByFormId(formId);
        Collection<SectorForm> forms = sectorFormRepository.findAll(spec);
        return getSectorFormDao().toSectorFormVOCollection(forms);
    }

    /**
     * @see bw.org.bocra.portal.sector.form.SectorFormService#updateSector(Long, Long)
     */
    @Override
    protected SectorFormVO handleUpdateSector(Long id, Long sectorId)
        throws Exception
    {
        SectorForm sectorForm = getSectorFormDao().load(id);
        Sector sector = getSectorDao().load(sectorId);
        sectorForm.setSector(sector);

        getSectorFormDao().update(sectorForm);
        
        return getSectorFormDao().toSectorFormVO(sectorForm);
    }

    /**
     * @see bw.org.bocra.portal.sector.form.SectorFormService#updateForm(Long, Long)
     */
    @Override
    protected SectorFormVO handleUpdateForm(Long id, Long formId)
        throws Exception
    {

        SectorForm sectorForm = getSectorFormDao().load(id);
        Form form = getFormDao().load(formId);
        sectorForm.setForm(form);

        getSectorFormDao().update(sectorForm);
        
        return getSectorFormDao().toSectorFormVO(sectorForm);
    }

}