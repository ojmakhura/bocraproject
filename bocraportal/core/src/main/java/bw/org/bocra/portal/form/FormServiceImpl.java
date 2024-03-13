// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 * TEMPLATE:    SpringServiceImpl.vsl in andromda-spring cartridge
 * MODEL CLASS: bocraportal::backend::bw.org.bocra.portal::form::FormService
 * STEREOTYPE:  Service
 */
package bw.org.bocra.portal.form;

import java.util.Collection;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import bw.org.bocra.portal.BocraportalSpecifications;

/**
 * @see bw.org.bocra.portal.form.FormService
 */
@Service("formService")
@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
public class FormServiceImpl
    extends FormServiceBase
{

    public FormServiceImpl(FormDao formDao, FormRepository formRepository, MessageSource messageSource) {
        super(formDao, formRepository, messageSource);
    }

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
        form = formRepository.saveAndFlush(form);

        return getFormDao().toFormVO(form);
    }

    /**
     * @see bw.org.bocra.portal.form.FormService#remove(Long)
     */
    @Override
    protected  boolean handleRemove(Long id)
        throws Exception
    {

        Form form = getFormDao().load(id);
        if(CollectionUtils.isNotEmpty(form.getFormSubmissions())) {
            throw new FormServiceException("This form cannot be removed. It has data associated with it.");
        }

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
        return (Collection<FormVO>) getFormDao().findByCriteria(FormDao.TRANSFORM_FORMVO, criteria);

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

    @Override
    protected Collection<FormVO> handleGetFormsByPeriods(Set<Long> periodConfigIds) throws Exception {

        if(CollectionUtils.isEmpty(periodConfigIds)) {
            throw new FormServiceException("Period config ids cannot be empty.");
        }

        return getFormDao().toFormVOCollection(formDao.findFormsByPeriodConfigs(periodConfigIds));
    }

}