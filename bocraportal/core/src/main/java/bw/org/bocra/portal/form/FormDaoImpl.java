// Generated by hibernate/SpringHibernateDaoImpl.vsl in andromda-spring-cartridge on $springUtils.date.
// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package bw.org.bocra.portal.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import bw.org.bocra.portal.BocraportalSpecifications;
import bw.org.bocra.portal.form.field.FormField;
import bw.org.bocra.portal.form.field.FormFieldRepository;
import bw.org.bocra.portal.form.field.FormFieldVO;
import bw.org.bocra.portal.form.section.FormSection;
import bw.org.bocra.portal.form.section.FormSectionRepository;
import bw.org.bocra.portal.form.section.FormSectionVO;
import bw.org.bocra.portal.form.submission.FormSubmissionRepository;
import bw.org.bocra.portal.licence.type.LicenceTypeRepository;
import bw.org.bocra.portal.licence.type.form.LicenceTypeFormRepository;
import bw.org.bocra.portal.licensee.LicenseeRepository;
import bw.org.bocra.portal.licensee.form.LicenseeForm;
import bw.org.bocra.portal.licensee.form.LicenseeFormRepository;
import bw.org.bocra.portal.licensee.form.LicenseeFormVO;
import bw.org.bocra.portal.period.config.PeriodConfig;
import bw.org.bocra.portal.period.config.PeriodConfigRepository;
import bw.org.bocra.portal.report.config.ReportConfigRepository;
import bw.org.bocra.portal.sector.SectorRepository;
import bw.org.bocra.portal.sector.form.SectorForm;
import bw.org.bocra.portal.sector.form.SectorFormRepository;
import bw.org.bocra.portal.sector.form.SectorFormVO;

/**
 * @see Form
 */
@Repository("formDao")
@Transactional
public class FormDaoImpl
    extends FormDaoBase
{

    public FormDaoImpl(LicenceTypeRepository licenceTypeRepository, FormFieldRepository formFieldRepository,
            FormSubmissionRepository formSubmissionRepository, LicenseeRepository licenseeRepository,
            ReportConfigRepository reportConfigRepository, FormSectionRepository formSectionRepository,
            LicenceTypeFormRepository licenceTypeFormRepository, LicenseeFormRepository licenseeFormRepository,
            FormReportConfigRepository formReportConfigRepository, SectorFormRepository sectorFormRepository,
            SectorRepository sectorRepository, PeriodConfigRepository periodConfigRepository,
            FormRepository formRepository) {
        super(licenceTypeRepository, formFieldRepository, formSubmissionRepository, licenseeRepository, reportConfigRepository,
                formSectionRepository, licenceTypeFormRepository, licenseeFormRepository, formReportConfigRepository,
                sectorFormRepository, sectorRepository, periodConfigRepository, formRepository);
        //TODO Auto-generated constructor stub
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void toFormVO(
        Form source,
        FormVO target)
    {
        // TODO verify behavior of toFormVO
        super.toFormVO(source, target);

        if(CollectionUtils.isNotEmpty(source.getFormFields())) {
            if(target.getFormFields() == null) {
                target.setFormFields(new ArrayList<>());
            }

            for (FormField entity : source.getFormFields()) {

                FormFieldVO field = formFieldDao.toFormFieldVO(entity);

                target.getFormFields().add(field);
            }
        }

        if(source.getPeriodConfig() != null && source.getPeriodConfig().getId() != null) {
            target.setPeriodConfig(periodConfigDao.toPeriodConfigVO(source.getPeriodConfig()));
        }

        if(CollectionUtils.isNotEmpty(source.getFormSections())) {
            if(target.getFormSections() == null) {
                target.setFormSections(new ArrayList<>());
            }

            for(FormSection entity : source.getFormSections()) {
                FormSectionVO section = new FormSectionVO();
                getFormSectionDao().toFormSectionVO(entity, section);

                target.getFormSections().add(section);
            }
        }

        Set<LicenseeFormVO> licensees = new HashSet<>();
        for(LicenseeForm lf : source.getLicenseeForms()) {
            LicenseeFormVO vo = new LicenseeFormVO();
            getLicenseeFormDao().toLicenseeFormVO(lf, vo);

            licensees.add(vo);
        }

        target.setLicensees(licensees);

        if(CollectionUtils.isNotEmpty(source.getSectorForms())) {
            Collection<SectorFormVO> sectors = new ArrayList<>();
            
            for(SectorForm sec : source.getSectorForms()) {
                sectors.add(getSectorFormDao().toSectorFormVO(sec));
            }

            target.setSectors(sectors);
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FormVO toFormVO(final Form entity)
    {
        // TODO verify behavior of toFormVO
        return super.toFormVO(entity);
    }

    /**
     * Retrieves the entity object that is associated with the specified value object
     * from the object store. If no such entity object exists in the object store,
     * a new, blank entity is created
     */
    private Form loadFormFromFormVO(FormVO formVO)
    {
        if (formVO.getId() == null)
        {
            return  Form.Factory.newInstance();
        }
        else
        {
            return this.load(formVO.getId());
        }
    }

    /**
     * {@inheritDoc}
     */
    public Form formVOToEntity(FormVO formVO)
    {
        // TODO verify behavior of formVOToEntity
        Form entity = this.loadFormFromFormVO(formVO);
        this.formVOToEntity(formVO, entity, true);
        return entity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void formVOToEntity(
        FormVO source,
        Form target,
        boolean copyIfNull)
    {
        // TODO verify behavior of formVOToEntity
        super.formVOToEntity(source, target, copyIfNull);

        if(!CollectionUtils.isEmpty(source.getFormFields())) {

            Collection<FormField> fields = new ArrayList<>();
            for(FormFieldVO field : source.getFormFields()) {

                /**
                 * here we are getting a form field that has not been saved
                 * in our datastore. So we first save it before adding it to 
                 * the fields.
                 */
                if(field.getId() == null) {

                    FormField entity = FormField.Factory.newInstance();
                    getFormFieldDao().formFieldVOToEntity(field, entity, copyIfNull);
    
                    entity.setForm(target);
                    entity = formFieldDao.create(entity);
    
                    fields.add(entity);
                } else {

                    FormField entity = formFieldDao.load(field.getId());
                    entity.setForm(target);
                    fields.add(entity);
                }
            }

            target.setFormFields(fields);
        }

        if(CollectionUtils.isNotEmpty(source.getFormSections())) {

            Collection<FormSection> sections = new ArrayList<>();

            for(FormSectionVO section : source.getFormSections()) {
                if(section.getId() == null) {
                    FormSection entity = FormSection.Factory.newInstance();
                    getFormSectionDao().formSectionVOToEntity(section, entity, copyIfNull);

                    entity.setForm(target);
                    entity = formSectionDao.create(entity);

                    sections.add(entity);
                } else {
                    FormSection entity = formSectionDao.load(section.getId());
                    entity.setForm(target);
                    sections.add(entity);
                }
            }

            target.setFormSections(sections);
        }

        if(source.getPeriodConfig() != null && source.getPeriodConfig().getId() != null) {
            target.setPeriodConfig(periodConfigRepository.getReferenceById(source.getPeriodConfig().getId()));
        }
    }

    @Override
    protected Collection<Form> handleFindFormsByPeriodConfigs(Set<Long> periodConfigs) throws Exception {

        if(CollectionUtils.isEmpty(periodConfigs)) {
            return new HashSet<>();
        }

        Specification<Form> specs = BocraportalSpecifications.<Form, PeriodConfig, Long>findByJoinAttributeIn("periodConfig", "id", periodConfigs);

        return formRepository.findAll(specs, Sort.by("formName").ascending());
    }

    @Override
    public Specification<Form> getFindByCriteriaSpecifications(final FormCriteria criteria) {
        
        Specification<Form> specifications = null;
        
        if(StringUtils.isNotBlank(criteria.getCode())) {
            specifications = BocraportalSpecifications.<Form, String>findByAttributeContainingIgnoreCase("code", criteria.getCode());
        }

        if(StringUtils.isNotBlank(criteria.getFormName())) {
            if(specifications == null) {
                specifications = BocraportalSpecifications.<Form, String>findByAttributeContainingIgnoreCase("formName", criteria.getFormName());
            } else {
                specifications = specifications.and(BocraportalSpecifications.<Form, String>findByAttributeContainingIgnoreCase("formName", criteria.getFormName()));
            }
        }

        if(criteria.getEntryType() != null) {
            if(specifications == null) {
                specifications = BocraportalSpecifications.<Form, FormEntryType>findByAttribute("entryType", criteria.getEntryType());
            } else {
                specifications = specifications.and(BocraportalSpecifications.<Form, FormEntryType>findByAttribute("entryType", criteria.getEntryType()));
            }
        }

        if(org.apache.commons.collections4.CollectionUtils.isNotEmpty(criteria.getRoles())) {

            Iterator<String> iter = criteria.getRoles().iterator();

            // Specification tmp = BocraportalSpecifications.findByAttributeIn("roles", iter.next());

            if(specifications == null) {
                specifications = BocraportalSpecifications.<Form, String>findByAttributeIn("roles", criteria.getRoles());
            } else {
                specifications = specifications.and(BocraportalSpecifications.<Form, String>findByAttributeIn("roles", criteria.getRoles()));
            }
        }

        return specifications;
    }

}