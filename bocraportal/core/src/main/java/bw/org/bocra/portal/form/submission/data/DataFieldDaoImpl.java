// Generated by hibernate/SpringHibernateDaoImpl.vsl in andromda-spring-cartridge on $springUtils.date.
// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package bw.org.bocra.portal.form.submission.data;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import bw.org.bocra.portal.form.field.FormField;
import bw.org.bocra.portal.form.field.FormFieldRepository;
import bw.org.bocra.portal.form.field.FormFieldVO;
import bw.org.bocra.portal.form.section.FormSection;
import bw.org.bocra.portal.form.submission.FormSubmission;
import bw.org.bocra.portal.form.submission.FormSubmissionRepository;
import bw.org.bocra.portal.form.submission.FormSubmissionVO;

/**
 * @see DataField
 */
@Repository("dataFieldDao")
@Transactional
public class DataFieldDaoImpl
    extends DataFieldDaoBase
{

    public DataFieldDaoImpl(FormFieldRepository formFieldRepository, FormSubmissionRepository formSubmissionRepository,
            DataFieldRepository dataFieldRepository) {
                
        super(formFieldRepository, formSubmissionRepository, dataFieldRepository);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void toDataFieldVO(
        DataField source,
        DataFieldVO target)
    {
        // TODO verify behavior of toDataFieldVO
        super.toDataFieldVO(source, target);
        if(source.getFormField().getFormSection() != null) {
            FormSection section = source.getFormField().getFormSection();
            // section.setForm(null);

            if(section.getPosition() != null) {
                target.setSectionPosition(section.getPosition());
                target.setSectionLabel(section.getSectionLabel());
                target.setSectionId(section.getSectionId());
            }
        }

        if(source.getFormField() != null) {
            FormFieldVO field = new FormFieldVO();
            field.setId(source.getFormField().getId());
            field.setFieldId(source.getFormField().getFieldId());
            field.setFieldName(source.getFormField().getFieldName());
            field.setFieldValueType(source.getFormField().getFieldValueType());
            field.setFieldType(source.getFormField().getFieldType());
            field.setDefaultValue(source.getFormField().getDefaultValue());
            field.setMin(source.getFormField().getMin());
            field.setMinLength(source.getFormField().getMinLength());
            field.setMax(source.getFormField().getMax());
            field.setMaxLength(source.getFormField().getMaxLength());
            field.setExpression(source.getFormField().getExpression());
            field.setPosition(source.getFormField().getPosition());
            
            target.setFormField(field);
        }

        if(source.getFormSubmission() != null) {
            FormSubmissionVO submission = new FormSubmissionVO();
            submission.setId(source.getFormSubmission().getId());
            target.setFormSubmission(submission);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DataFieldVO toDataFieldVO(final DataField entity)
    {
        // TODO verify behavior of toDataFieldVO
        return super.toDataFieldVO(entity);
    }

    /**
     * Retrieves the entity object that is associated with the specified value object
     * from the object store. If no such entity object exists in the object store,
     * a new, blank entity is created
     */
    private DataField loadDataFieldFromDataFieldVO(DataFieldVO dataFieldVO)
    {

        if (dataFieldVO.getId() == null)
        {
            return  DataField.Factory.newInstance();
        }
        else
        {
            return this.load(dataFieldVO.getId());
        }
    }

    /**
     * {@inheritDoc}
     */
    public DataField dataFieldVOToEntity(DataFieldVO dataFieldVO)
    {
        // TODO verify behavior of dataFieldVOToEntity
        DataField entity = this.loadDataFieldFromDataFieldVO(dataFieldVO);
        this.dataFieldVOToEntity(dataFieldVO, entity, true);
        return entity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void dataFieldVOToEntity(
        DataFieldVO source,
        DataField target,
        boolean copyIfNull)
    {
        // TODO verify behavior of dataFieldVOToEntity
        super.dataFieldVOToEntity(source, target, copyIfNull);

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