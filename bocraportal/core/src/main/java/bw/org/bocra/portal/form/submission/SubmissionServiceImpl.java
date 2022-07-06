// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 * TEMPLATE:    SpringServiceImpl.vsl in andromda-spring cartridge
 * MODEL CLASS: bocraportal::backend::bw.org.bocra.portal::form::submission::SubmissionService
 * STEREOTYPE:  Service
 */
package bw.org.bocra.portal.form.submission;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import bw.org.bocra.portal.form.FormVO;
import bw.org.bocra.portal.form.submission.data.DataField;
import bw.org.bocra.portal.form.submission.data.DataFieldDao;
import bw.org.bocra.portal.form.submission.data.DataFieldRepository;
import bw.org.bocra.portal.form.submission.data.DataFieldVO;
import bw.org.bocra.portal.licensee.LicenseeVO;
import bw.org.bocra.portal.period.PeriodVO;

/**
 * @see bw.org.bocra.portal.form.submission.SubmissionService
 */
@Service("submissionService")
public class SubmissionServiceImpl
        extends SubmissionServiceBase {

    public SubmissionServiceImpl(FormSubmissionDao formSubmissionDao, FormSubmissionRepository formSubmissionRepository,
            DataFieldDao dataFieldDao, DataFieldRepository dataFieldRepository, MessageSource messageSource) {

        super(formSubmissionDao, formSubmissionRepository, dataFieldDao, dataFieldRepository, messageSource);
    }

    /**
     * @see bw.org.bocra.portal.form.submission.SubmissionService#findById(Long)
     */
    @Override
    protected FormSubmissionVO handleFindById(Long id)
            throws Exception {

        FormSubmission entity = getFormSubmissionDao().get(id);
        FormSubmissionVO submission = getFormSubmissionDao().toFormSubmissionVO(entity);

        // for(DataField field : entity.getDataFields()) {
        //     DataFieldVO vo = getDataFieldDao().toDataFieldVO(field);
        // }
        //submission.setDataFields(value);
        
        return submission;
    }

    /**
     * @see bw.org.bocra.portal.form.submission.SubmissionService#save(FormSubmissionVO)
     */
    @Override
    protected FormSubmissionVO handleSave(FormSubmissionVO formSubmissionVO)
            throws Exception {
        FormSubmission submission = getFormSubmissionDao().formSubmissionVOToEntity(formSubmissionVO);

        if (formSubmissionVO.getId() == null) {
            submission = getFormSubmissionDao().create(submission);

        } else {
            getFormSubmissionDao().update(submission);
        }

        return getFormSubmissionDao().toFormSubmissionVO(submission);
    }

    /**
     * @see bw.org.bocra.portal.form.submission.SubmissionService#remove(Long)
     */
    @Override
    protected boolean handleRemove(Long id)
            throws Exception {
        if (id == null) {
            return false;
        }

        this.getFormSubmissionDao().remove(id);
        return true;
    }

    private FormSubmissionVO getSubmissionVO(FormSubmission formSubmission) {
        FormSubmissionVO vo = new FormSubmissionVO();
        vo.setId(formSubmission.getId());
        vo.setCreatedBy(formSubmission.getCreatedBy());
        vo.setUpdatedBy(formSubmission.getUpdatedBy());
        vo.setCreatedDate(formSubmission.getCreatedDate());
        vo.setUpdatedDate(formSubmission.getUpdatedDate());
        vo.setSubmittedBy(formSubmission.getSubmittedBy());
        vo.setSubmissionDate(formSubmission.getSubmissionDate());
        vo.setSubmissionStatus(formSubmission.getSubmissionStatus());

        LicenseeVO lvo = new LicenseeVO();
        lvo.setId(formSubmission.getLicensee().getId());
        lvo.setAddress(formSubmission.getLicensee().getAddress());
        lvo.setUin(formSubmission.getLicensee().getUin());
        lvo.setLicenseeName(formSubmission.getLicensee().getLicenseeName());

        vo.setLicensee(lvo);

        FormVO form = new FormVO();
        form.setId(formSubmission.getForm().getId());
        form.setCode(formSubmission.getForm().getCode());
        form.setFormName(formSubmission.getForm().getFormName());

        vo.setForm(form);

        PeriodVO period = new PeriodVO();
        period.setId(formSubmission.getPeriod().getId());
        period.setPeriodName(formSubmission.getPeriod().getPeriodName());
        vo.setPeriod(period);

        return vo;
    }

    private Collection<FormSubmissionVO> toSubmissionVOCollection(Collection<FormSubmission> submissions) {
        Collection<FormSubmissionVO> vos = new ArrayList<>();

        for (FormSubmission formSubmission : submissions) {
            vos.add(getSubmissionVO(formSubmission));
        }

        return vos;
    }

    /**
     * @see bw.org.bocra.portal.form.submission.SubmissionService#getAll()
     */
    @Override
    protected Collection<FormSubmissionVO> handleGetAll()
            throws Exception {
        return toSubmissionVOCollection(getFormSubmissionDao()
                .loadAll());
    }

    /**
     * @see bw.org.bocra.portal.form.submission.SubmissionService#search(FormSubmissionCriteria)
     */
    @Override
    protected Collection<FormSubmissionVO> handleSearch(FormSubmissionCriteria criteria)
            throws Exception {
        
        Collection<FormSubmission> submissions = getFormSubmissionDao().findByCriteria(criteria);

        return toSubmissionVOCollection(submissions);
    }

    /**
     * @see bw.org.bocra.portal.form.submission.SubmissionService#getAll(Integer,
     *      Integer)
     */
    @Override
    protected Collection<FormSubmissionVO> handleGetAll(Integer pageNumber, Integer pageSize)
            throws Exception {

        return toSubmissionVOCollection((Collection<FormSubmission>) getFormSubmissionDao().loadAll(pageNumber, pageSize));
    }

}