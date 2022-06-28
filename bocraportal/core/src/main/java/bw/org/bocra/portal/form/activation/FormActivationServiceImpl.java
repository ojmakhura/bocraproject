// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 * TEMPLATE:    SpringServiceImpl.vsl in andromda-spring cartridge
 * MODEL CLASS: bocraportal::backend::bw.org.bocra.portal::form::activation::FormActivationService
 * STEREOTYPE:  Service
 */
package bw.org.bocra.portal.form.activation;

import bw.org.bocra.portal.form.FormDao;
import bw.org.bocra.portal.form.FormRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.MessageSource;


/**
 * @see bw.org.bocra.portal.form.activation.FormActivationService
 */
@Service("formActivationService")
@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
public class FormActivationServiceImpl
    extends FormActivationServiceBase
{
    public FormActivationServiceImpl(
        FormActivationDao formActivation,
        FormActivationRepository formActivationRepository,
        FormDao form,
        FormRepository formRepository,
        MessageSource messageSource
    ) {
        
        super(
            formActivation,
            formActivationRepository,
            form,
            formRepository,
            messageSource
        );
    }

    /**
     * @see bw.org.bocra.portal.form.activation.FormActivationService#findById(Long)
     */
    @Override
    protected FormActivationVO handleFindById(Long id)
        throws Exception
    {
        return (FormActivationVO) getFormActivationDao().load(FormActivationDao.TRANSFORM_FORMACTIVATIONVO, id);
    }

    /**
     * @see bw.org.bocra.portal.form.activation.FormActivationService#save(FormActivationVO)
     */
    @Override
    protected FormActivationVO handleSave(FormActivationVO formActivation)
        throws Exception
    {
        FormActivation activation = getFormActivationDao().formActivationVOToEntity(formActivation);
        activation = formActivationRepository.save(activation);

        return formActivationDao.toFormActivationVO(activation);
    }

    /**
     * @see bw.org.bocra.portal.form.activation.FormActivationService#remove(Long)
     */
    @Override
    protected boolean handleRemove(Long id)
        throws Exception
    {
        formActivationDao.remove(id);
        return true;
    }

    /**
     * @see bw.org.bocra.portal.form.activation.FormActivationService#getAll()
     */
    @Override
    protected Collection<FormActivationVO> handleGetAll()
        throws Exception
    {
        return (Collection<FormActivationVO>) formActivationDao.loadAll(FormActivationDao.TRANSFORM_FORMACTIVATIONVO);
    }

    /**
     * @see bw.org.bocra.portal.form.activation.FormActivationService#search(FormActivationCriteria)
     */
    @Override
    protected Collection<FormActivationVO> handleSearch(FormActivationCriteria criteria)
        throws Exception
    {
        List<FormActivation> activations = new ArrayList<>();
        List<FormActivationVO> vos = new ArrayList<>();

        for(FormActivation activation : activations) {
            vos.add(getFormActivationDao().toFormActivationVO(activation));
        }

        return vos;
    }

    /**
     * @see bw.org.bocra.portal.form.activation.FormActivationService#getAll(Integer, Integer)
     */
    @Override
    protected Collection<FormActivationVO> handleGetAll(Integer pageNumber, Integer pageSize)
        throws Exception
    {
        return (Collection<FormActivationVO>) getFormActivationDao().loadAll(FormActivationDao.TRANSFORM_FORMACTIVATIONVO, pageNumber, pageSize);
    }

}