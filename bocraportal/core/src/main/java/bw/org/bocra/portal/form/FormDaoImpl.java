// Generated by hibernate/SpringHibernateDaoImpl.vsl in andromda-spring-cartridge on $springUtils.date.
// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package bw.org.bocra.portal.form;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import bw.org.bocra.portal.form.field.FormField;
import bw.org.bocra.portal.form.field.FormFieldVO;
import bw.org.bocra.portal.type.LicenseType;
import bw.org.bocra.portal.type.LicenseTypeVO;

/**
 * @see Form
 */
@Repository("formDao")
public class FormDaoImpl
    extends FormDaoBase
{
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

        if(!CollectionUtils.isEmpty(source.getLicenseTypes())) {

            if(target.getLicenseTypes() == null) {
                target.setLicenseTypes(new ArrayList<>());
            }
            
            for (LicenseType entity : source.getLicenseTypes()) {
                LicenseTypeVO type = new LicenseTypeVO();
                type.setId(entity.getId());
                type.setCode(entity.getCode());
                type.setDescription(entity.getDescription());
                type.setName(entity.getName());
                
                target.getLicenseTypes().add(type);
            }
        }

        if(!CollectionUtils.isEmpty(source.getFormFields())) {
            if(target.getFormFields() == null) {
                target.setFormFields(new ArrayList<>());
            }

            for (FormField entity : source.getFormFields()) {
                FormFieldVO field = new FormFieldVO();
                field.setForm(target);
                //field.setCreatedBy(entity);

                field.setFieldName(entity.getFieldName());
                field.setFieldType(entity.getFieldType());
                field.setId(entity.getId());
            }
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

            if(target.getFormFields() == null) {
                target.setFormFields(new ArrayList<>());
            }

            for(FormFieldVO field : source.getFormFields()) {

                FormField entity = FormField.Factory.newInstance();
                getFormFieldDao().formFieldVOToEntity(field, entity, copyIfNull);
                target.getFormFields().add(entity);
            }

        }

        if(!CollectionUtils.isEmpty(source.getLicenseTypes())) {

            if(target.getLicenseTypes() == null) {
                target.setLicenseTypes(new ArrayList<>());
            }

            for(LicenseTypeVO type : source.getLicenseTypes()) {
                LicenseType entity = LicenseType.Factory.newInstance();
                getLicenseTypeDao().licenseTypeVOToEntity(type, entity, copyIfNull);
                target.getLicenseTypes().add(entity);
            }
        }

    }

}