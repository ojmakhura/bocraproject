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
        // TODO implement protected  FormVO handleFindById(Long id)
        throw new UnsupportedOperationException("bw.org.bocra.portal.form.FormService.handleFindById(Long id) Not implemented!");
    }

    /**
     * @see bw.org.bocra.portal.form.FormService#save(FormVO)
     */
    @Override
    protected  FormVO handleSave(FormVO formVO)
        throws Exception
    {
        // TODO implement protected  FormVO handleSave(FormVO formVO)
        throw new UnsupportedOperationException("bw.org.bocra.portal.form.FormService.handleSave(FormVO formVO) Not implemented!");
    }

    /**
     * @see bw.org.bocra.portal.form.FormService#remove(Long)
     */
    @Override
    protected  boolean handleRemove(Long id)
        throws Exception
    {
        // TODO implement protected  boolean handleRemove(Long id)
        throw new UnsupportedOperationException("bw.org.bocra.portal.form.FormService.handleRemove(Long id) Not implemented!");
    }

    /**
     * @see bw.org.bocra.portal.form.FormService#getAll()
     */
    @Override
    protected  Collection<FormVO> handleGetAll()
        throws Exception
    {
        // TODO implement protected  Collection<FormVO> handleGetAll()
        throw new UnsupportedOperationException("bw.org.bocra.portal.form.FormService.handleGetAll() Not implemented!");
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
    protected  Collection<UrlGuardVO> handleGetAll(Integer pageNumber, Integer pageSize)
        throws Exception
    {
        // TODO implement protected  Collection<UrlGuardVO> handleGetAll(Integer pageNumber, Integer pageSize)
        throw new UnsupportedOperationException("bw.org.bocra.portal.form.FormService.handleGetAll(Integer pageNumber, Integer pageSize) Not implemented!");
    }

}