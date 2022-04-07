// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 * TEMPLATE:    SpringServiceImpl.vsl in andromda-spring cartridge
 * MODEL CLASS: bocraportal::backend::bw.org.bocra.portal::form::FormService
 * STEREOTYPE:  Service
 */
package bw.org.bocra.portal.form;

import bw.org.bocra.portal.guard.UrlGuardVO;

import java.util.ArrayList;
import java.util.Collection;
import org.springframework.stereotype.Service;

/**
 * @see bw.org.bocra.portal.form.FormService
 */
@Service("formService")
public class FormServiceImpl
    extends FormServiceBase
{

    /**
     * @see bw.org.bocra.portal.form.FormService#findById(Long)
     */
    @Override
    protected  FormVO handleFindById(Long id)
        throws Exception
    {

        if(id == null) {
            return null;
        }

        return getFormDao().toFormVO(getFormDao().load(id));
    }

    /**
     * @see bw.org.bocra.portal.form.FormService#save(FormVO)
     */
    @Override
    protected  FormVO handleSave(FormVO formVO)
        throws Exception
    {

        Form form = getFormDao().formVOToEntity(formVO);

        if(formVO.getId() == null) {
            form = getFormDao().create(form);
        } else {
            getFormDao().update(form);
        }

        return getFormDao().toFormVO(form);
    }

    /**
     * @see bw.org.bocra.portal.form.FormService#remove(Long)
     */
    @Override
    protected  boolean handleRemove(Long id)
        throws Exception
    {
        if(id == null) {
            return false;
        }

        getFormDao().remove(id);
        return true;
    }

    /**
     * @see bw.org.bocra.portal.form.FormService#getAll()
     */
    @Override
    protected  Collection<FormVO> handleGetAll()
        throws Exception
    {

        return (Collection<FormVO>) getFormDao().loadAll(FormDao.TRANSFORM_FORMVO);

    }

    /**
     * @see bw.org.bocra.portal.form.FormService#search(FormCriteria)
     */
    @Override
    protected  Collection<FormVO> handleSearch(FormCriteria criteria)
        throws Exception
    {

        

        // TODO implement protected  Collection<FormVO> handleSearch(FormCriteria criteria)
        throw new UnsupportedOperationException("bw.org.bocra.portal.form.FormService.handleSearch(FormCriteria criteria) Not implemented!");
    }

    /**
     * @see bw.org.bocra.portal.form.FormService#getAll(Integer, Integer)
     */
    @Override
    protected  Collection<FormVO> handleGetAll(Integer pageNumber, Integer pageSize)
        throws Exception
    {
        return (Collection<FormVO>) getFormDao().loadAll(FormDao.TRANSFORM_FORMVO, pageNumber, pageSize);
    }

}