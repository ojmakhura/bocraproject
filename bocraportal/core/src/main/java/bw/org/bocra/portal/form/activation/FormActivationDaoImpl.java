// Generated by hibernate/SpringHibernateDaoImpl.vsl in andromda-spring-cartridge on $springUtils.date.
// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package bw.org.bocra.portal.form.activation;

import bw.org.bocra.portal.form.FormRepository;
import bw.org.bocra.portal.form.FormVO;
import bw.org.bocra.portal.form.submission.FormSubmissionRepository;
import bw.org.bocra.portal.period.PeriodRepository;
import bw.org.bocra.portal.period.PeriodVO;
import bw.org.bocra.portal.period.config.PeriodConfig;
import bw.org.bocra.portal.period.config.PeriodConfigVO;

import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

/**
 * @see FormActivation
 */
@Repository("formActivationDao")
public class FormActivationDaoImpl
    extends FormActivationDaoBase
{
    

    public FormActivationDaoImpl(FormRepository formRepository, PeriodRepository periodRepository,
            FormSubmissionRepository formSubmissionRepository, FormActivationRepository formActivationRepository) {
        
        super(formRepository, periodRepository, formSubmissionRepository, formActivationRepository);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void toFormActivationVO(
        FormActivation source,
        FormActivationVO target)
    {
        // TODO verify behavior of toFormActivationVO
        super.toFormActivationVO(source, target);
        // WARNING! No conversion for target.period (can't convert source.getPeriod():bw.org.bocra.portal.period.Period to bw.org.bocra.portal.period.PeriodVO
        if(source.getPeriod() != null && source.getPeriod().getId() != null) {
            PeriodVO period = new PeriodVO();
            
            // No conversion for target.periodConfig (can't convert source.getPeriodConfig():PeriodConfig to bw.org.bocra.portal.period.config.PeriodConfigVO)
            PeriodConfigVO config = new PeriodConfigVO();
            PeriodConfig conf = source.getPeriod().getPeriodConfig();
            config.setId(conf.getId());
            config.setPeriodConfigName(conf.getPeriodConfigName());
            config.setStartDay(conf.getStartDay());
            config.setStartMonth(conf.getStartMonth());
            config.setCreatedBy(conf.getCreatedBy());
            config.setUpdatedBy(conf.getUpdatedBy());
            config.setCreatedDate(conf.getCreatedDate());
            config.setUpdatedDate(conf.getUpdatedDate());
            config.setRepeat(conf.getRepeat());
            config.setRepeatPeriod(conf.getRepeatPeriod());
            config.setFinalDay(conf.getFinalDay());

            period.setId(source.getPeriod().getId());
            period.setPeriodName(source.getPeriod().getPeriodName());
            period.setPeriodStart(source.getPeriod().getPeriodStart());
            period.setPeriodEnd(source.getPeriod().getPeriodEnd());
            period.setCreatedBy(source.getPeriod().getCreatedBy());
            period.setUpdatedBy(source.getPeriod().getUpdatedBy());
            period.setCreatedDate(source.getPeriod().getCreatedDate());
            period.setUpdatedDate(source.getPeriod().getUpdatedDate());
            period.setPeriodConfig(config);

            target.setPeriod(period);
        }
        
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
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FormActivationVO toFormActivationVO(final FormActivation entity)
    {
        // TODO verify behavior of toFormActivationVO
        return super.toFormActivationVO(entity);
    }

    /**
     * Retrieves the entity object that is associated with the specified value object
     * from the object store. If no such entity object exists in the object store,
     * a new, blank entity is created
     */
    private FormActivation loadFormActivationFromFormActivationVO(FormActivationVO formActivationVO)
    {
        if (formActivationVO.getId() == null)
        {
            return  FormActivation.Factory.newInstance();
        }
        else
        {
            return this.load(formActivationVO.getId());
        }
    }

    /**
     * {@inheritDoc}
     */
    public FormActivation formActivationVOToEntity(FormActivationVO formActivationVO)
    {
        // TODO verify behavior of formActivationVOToEntity
        FormActivation entity = this.loadFormActivationFromFormActivationVO(formActivationVO);
        this.formActivationVOToEntity(formActivationVO, entity, true);
        return entity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void formActivationVOToEntity(
        FormActivationVO source,
        FormActivation target,
        boolean copyIfNull)
    {
        // TODO verify behavior of formActivationVOToEntity
        super.formActivationVOToEntity(source, target, copyIfNull);// WARNING! No conversion for target.period (can't convert source.getPeriod():bw.org.bocra.portal.period.Period to bw.org.bocra.portal.period.PeriodVO
        if(source.getPeriod() != null && source.getPeriod().getId() != null) {
            target.setPeriod(getPeriodDao().load(source.getPeriod().getId()));
        }
        
        // WARNING! No conversion for target.form (can't convert source.getForm():bw.org.bocra.portal.form.Form to bw.org.bocra.portal.form.FormVO
        if(source.getForm() != null && source.getForm().getId() != null) {
            
            target.setForm(getFormDao().load(source.getForm().getId()));
        }
    }

    @Override
    protected Collection<FormActivation> handleFindByCriteria(FormActivationCriteria criteria) throws Exception {
        Specification<FormActivation> spec = null;
        if(StringUtils.isNotBlank(criteria.getActivationName())) {
            spec = FormActivationSpecifications.findByActivationNameContainingIgnoreCase(criteria.activationName);
        }

        if(criteria.getForm() != null) {
            Specification<FormActivation> tmp = FormActivationSpecifications.findByFormId(criteria.getForm().getId());
            if(spec == null) {
                spec = tmp;
            } else {
                spec = spec.and(tmp);
            }
        }

        if(criteria.getPeriod() != null) {
            Specification<FormActivation> tmp = FormActivationSpecifications.findByPeriodId(criteria.getPeriod().getId());
            if(spec == null) {
                spec = tmp;
            } else {
                spec = spec.and(tmp);
            }
        }

        return formActivationRepository.findAll(spec);
    }
}