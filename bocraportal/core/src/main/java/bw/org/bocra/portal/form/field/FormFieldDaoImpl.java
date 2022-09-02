// Generated by hibernate/SpringHibernateDaoImpl.vsl in andromda-spring-cartridge on $springUtils.date.
// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package bw.org.bocra.portal.form.field;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import bw.org.bocra.portal.form.Form;
import bw.org.bocra.portal.form.FormDao;
import bw.org.bocra.portal.form.FormRepository;
import bw.org.bocra.portal.form.FormVO;
import bw.org.bocra.portal.form.section.FormSection;
import bw.org.bocra.portal.form.section.FormSectionDao;
import bw.org.bocra.portal.form.section.FormSectionRepository;
import bw.org.bocra.portal.form.section.FormSectionVO;

/**
 * @see FormField
 */
@Repository("formFieldDao")
@Transactional
public class FormFieldDaoImpl
    extends FormFieldDaoBase
{

    public FormFieldDaoImpl(FormRepository formRepository, FormSectionRepository formSectionRepository,
            FormFieldRepository formFieldRepository) {
                
        super(formRepository, formSectionRepository, formFieldRepository);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void toFormFieldVO(
        FormField source,
        FormFieldVO target)
    {
        // TODO verify behavior of toFormFieldVO
        super.toFormFieldVO(source, target);
        // WARNING! No conversion for target.form (can't convert source.getForm():bw.org.bocra.portal.form.Form to bw.org.bocra.portal.form.FormVO
        if(source.getForm() != null) {
            FormVO form = new FormVO();
            form.setId(source.getForm().getId());
            form.setCode (source.getForm().getCode());
            form.setFormName(source.getForm().getFormName());
            
            target.setForm(form);
        }

        if(source.getFormSection() != null) {
            FormSection entity = source.getFormSection();
            FormSectionVO section = new FormSectionVO();
            section.setCreatedBy(entity.getCreatedBy());
            section.setCreatedDate(entity.getCreatedDate());
            section.setId(entity.getId());
            section.setPosition(entity.getPosition());
            section.setSectionLabel(entity.getSectionLabel());
            section.setSectionId(entity.getSectionId());
            section.setUpdatedBy(entity.getUpdatedBy());
            section.setUpdatedDate(entity.getUpdatedDate());

            target.setFormSection(section);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FormFieldVO toFormFieldVO(final FormField entity)
    {
        // TODO verify behavior of toFormFieldVO
        return super.toFormFieldVO(entity);
    }

    /**
     * Retrieves the entity object that is associated with the specified value object
     * from the object store. If no such entity object exists in the object store,
     * a new, blank entity is created
     */
    private FormField loadFormFieldFromFormFieldVO(FormFieldVO formFieldVO)
    {
        if (formFieldVO.getId() == null)
        {
            return  FormField.Factory.newInstance();
        }
        else
        {
            return this.load(formFieldVO.getId());
        }
    }

    /**
     * {@inheritDoc}
     */
    public FormField formFieldVOToEntity(FormFieldVO formFieldVO)
    {
        // TODO verify behavior of formFieldVOToEntity
        FormField entity = this.loadFormFieldFromFormFieldVO(formFieldVO);
        this.formFieldVOToEntity(formFieldVO, entity, true);
        return entity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void formFieldVOToEntity(
        FormFieldVO source,
        FormField target,
        boolean copyIfNull)
    {
        // TODO verify behavior of formFieldVOToEntity
        super.formFieldVOToEntity(source, target, copyIfNull);

        if(source.getForm() != null && source.getForm().getId() != null) {

            Form form = formDao.load(source.getForm().getId());
            target.setForm(form);
        }

        if(source.getFormSection() != null && source.getFormSection().getId() != null) {
            FormSection section = formSectionDao.load(source.getFormSection().getId());
            target.setFormSection(section);
        }
        
    }
}