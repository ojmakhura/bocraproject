// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 * TEMPLATE:    SpringServiceImpl.vsl in andromda-spring cartridge
 * MODEL CLASS: bocraportal::backend::bw.org.bocra.portal::form::field::FormFieldService
 * STEREOTYPE:  Service
 */
package bw.org.bocra.portal.form.field;

import java.util.Collection;
import org.springframework.stereotype.Service;

/**
 * @see bw.org.bocra.portal.form.field.FormFieldService
 */
@Service("formFieldService")
public class FormFieldServiceImpl
    extends FormFieldServiceBase
{

    /**
     * @see bw.org.bocra.portal.form.field.FormFieldService#findById(Long)
     */
    @Override
    protected  FormFieldVO handleFindById(Long id)
        throws Exception
    {
        if(id == null) {
            return null;
        }

        return (FormFieldVO) formFieldDao.get(FormFieldDao.TRANSFORM_FORMFIELDVO, id);

    }

    /**
     * @see bw.org.bocra.portal.form.field.FormFieldService#save(FormFieldVO)
     */
    @Override
    protected  FormFieldVO handleSave(FormFieldVO formFieldVO)
        throws Exception
    {

        FormField field = getFormFieldDao().formFieldVOToEntity(formFieldVO);

        if(field.getId() == null) {
            field = getFormFieldDao().create(field);
        } else {
            getFormFieldDao().update(field);
        }
        return getFormFieldDao().toFormFieldVO(field);
    }

    /**
     * @see bw.org.bocra.portal.form.field.FormFieldService#remove(Long)
     */
    @Override
    protected  boolean handleRemove(Long id)
        throws Exception
    {
        if(id == null) {
            return false;
        }

        formFieldRepository.deleteById(id);

        return true;
    }

    /**
     * @see bw.org.bocra.portal.form.field.FormFieldService#getAll()
     */
    @Override
    protected  Collection<FormFieldVO> handleGetAll()
        throws Exception
    {
        return (Collection<FormFieldVO>) getFormFieldDao().loadAll(FormFieldDao.TRANSFORM_FORMFIELDVO);
    }

    /**
     * @see bw.org.bocra.portal.form.field.FormFieldService#getAll(Integer, Integer)
     */
    @Override
    protected  Collection<FormFieldVO> handleGetAll(Integer pageNumber, Integer pageSize)
        throws Exception
    {
        return (Collection<FormFieldVO>) getFormFieldDao().loadAll(FormFieldDao.TRANSFORM_FORMFIELDVO, pageNumber, pageSize);
    }

}