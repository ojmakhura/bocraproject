// Generated by hibernate/SpringHibernateDaoImpl.vsl in andromda-spring-cartridge on $springUtils.date.
// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package bw.org.bocra.portal.form.submission.data;

import org.springframework.stereotype.Repository;

import bw.org.bocra.portal.form.field.FormField;
import bw.org.bocra.portal.form.field.FormFieldVO;
import bw.org.bocra.portal.form.submission.FormSubmission;
import bw.org.bocra.portal.form.submission.FormSubmissionVO;

/**
 * @see FormData
 */
@Repository("formDataDao")
public class FormDataDaoImpl
    extends FormDataDaoBase
{
    /**
     * {@inheritDoc}
     */
    @Override
    public void toFormDataVO(
        FormData source,
        FormDataVO target)
    {
        // TODO verify behavior of toFormDataVO
        super.toFormDataVO(source, target);

        if(source.getFormField() != null) {
            FormFieldVO field = new FormFieldVO();
            getFormFieldDao().toFormFieldVO(source.getFormField(), field);
            target.setFormField(field);
        }

        if(source.getFormSubmission() != null) {
            FormSubmissionVO submission = new FormSubmissionVO();
            getFormSubmissionDao().toFormSubmissionVO(source.getFormSubmission(), submission);
            target.setFormSubmission(submission);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FormDataVO toFormDataVO(final FormData entity)
    {
        // TODO verify behavior of toFormDataVO
        return super.toFormDataVO(entity);
    }

    /**
     * Retrieves the entity object that is associated with the specified value object
     * from the object store. If no such entity object exists in the object store,
     * a new, blank entity is created
     */
    private FormData loadFormDataFromFormDataVO(FormDataVO formDataVO)
    {

        if (formDataVO.getId() == null)
        {
            return  FormData.Factory.newInstance();
        }
        else
        {
            return this.load(formDataVO.getId());
        }
    }

    /**
     * {@inheritDoc}
     */
    public FormData formDataVOToEntity(FormDataVO formDataVO)
    {
        // TODO verify behavior of formDataVOToEntity
        FormData entity = this.loadFormDataFromFormDataVO(formDataVO);
        this.formDataVOToEntity(formDataVO, entity, true);
        return entity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void formDataVOToEntity(
        FormDataVO source,
        FormData target,
        boolean copyIfNull)
    {
        // TODO verify behavior of formDataVOToEntity
        super.formDataVOToEntity(source, target, copyIfNull);

        if(source.getFormField() != null && source.getFormField().getId() != null) {
            FormField field = formFieldDao.load(source.getFormField().getId());
            target.setFormField(field);
        }

        if(source.getFormSubmission() != null && source.getFormSubmission().getId() != null) {
            FormSubmission submission = formSubmissionDao.load(source.getFormSubmission().getId());
            target.setFormSubmission(submission);
        }
    }
}