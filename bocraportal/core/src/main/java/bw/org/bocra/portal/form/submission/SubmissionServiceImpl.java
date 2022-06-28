// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 * TEMPLATE:    SpringServiceImpl.vsl in andromda-spring cartridge
 * MODEL CLASS: bocraportal::backend::bw.org.bocra.portal::form::submission::SubmissionService
 * STEREOTYPE:  Service
 */
package bw.org.bocra.portal.form.submission;

import java.util.Collection;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import bw.org.bocra.portal.form.submission.data.FormDataDao;
import bw.org.bocra.portal.form.submission.data.FormDataRepository;

/**
 * @see bw.org.bocra.portal.form.submission.SubmissionService
 */
@Service("submissionService")
public class SubmissionServiceImpl
    extends SubmissionServiceBase
{

    public SubmissionServiceImpl(FormSubmissionDao formSubmissionDao, FormSubmissionRepository formSubmissionRepository,
            FormDataDao formDataDao, FormDataRepository formDataRepository, MessageSource messageSource) {
        super(formSubmissionDao, formSubmissionRepository, formDataDao, formDataRepository, messageSource);
    }

    /**
     * @see bw.org.bocra.portal.form.submission.SubmissionService#findById(Long)
     */
    @Override
    protected  FormSubmissionVO handleFindById(Long id)
        throws Exception
    {
        return id == null ? null : (FormSubmissionVO) getFormSubmissionDao().get(FormSubmissionDao.TRANSFORM_FORMSUBMISSIONVO, id);
    }

    /**
     * @see bw.org.bocra.portal.form.submission.SubmissionService#save(FormSubmissionVO)
     */
    @Override
    protected  FormSubmissionVO handleSave(FormSubmissionVO formSubmissionVO)
        throws Exception
    {
        FormSubmission submission = getFormSubmissionDao().formSubmissionVOToEntity(formSubmissionVO);

        if(formSubmissionVO.getId() == null) {
            return (FormSubmissionVO) getFormSubmissionDao().create(FormSubmissionDao.TRANSFORM_FORMSUBMISSIONVO, submission);
        } else {
            getFormSubmissionDao().update(submission);
            return getFormSubmissionDao().toFormSubmissionVO(submission);
        }

    }

    /**
     * @see bw.org.bocra.portal.form.submission.SubmissionService#remove(Long)
     */
    @Override
    protected  boolean handleRemove(Long id)
        throws Exception
    {
        if(id == null) {
            return false;
        }

        this.getFormSubmissionDao().remove(id);
        return true;
    }

    /**
     * @see bw.org.bocra.portal.form.submission.SubmissionService#getAll()
     */
    @Override
    protected  Collection<FormSubmissionVO> handleGetAll()
        throws Exception
    {
        return (Collection<FormSubmissionVO>) getFormSubmissionDao().loadAll(FormSubmissionDao.TRANSFORM_FORMSUBMISSIONVO);
    }

    /**
     * @see bw.org.bocra.portal.form.submission.SubmissionService#search(FormSubmissionCriteria)
     */
    @Override
    protected  Collection<FormSubmissionVO> handleSearch(FormSubmissionCriteria criteria)
        throws Exception
    {
        return (Collection<FormSubmissionVO>) getFormSubmissionDao().findByCriteria(FormSubmissionDao.TRANSFORM_FORMSUBMISSIONVO, criteria);
    }

    /**
     * @see bw.org.bocra.portal.form.submission.SubmissionService#getAll(Integer, Integer)
     */
    @Override
    protected  Collection<FormSubmissionVO> handleGetAll(Integer pageNumber, Integer pageSize)
        throws Exception
    {
        return (Collection<FormSubmissionVO>) getFormSubmissionDao().loadAll(FormSubmissionDao.TRANSFORM_FORMSUBMISSIONVO, pageNumber, pageSize);
    }

}