// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 * TEMPLATE:    SpringServiceImpl.vsl in andromda-spring cartridge
 * MODEL CLASS: bocraportal::backend::bw.org.bocra.portal::licensee::form::LicenseeFormService
 * STEREOTYPE:  Service
 */
package bw.org.bocra.portal.licensee.form;

import bw.org.bocra.portal.form.Form;
import bw.org.bocra.portal.form.FormDao;
import bw.org.bocra.portal.form.FormRepository;
import bw.org.bocra.portal.form.FormVO;
import bw.org.bocra.portal.licensee.Licensee;
import bw.org.bocra.portal.licensee.LicenseeDao;
import bw.org.bocra.portal.licensee.LicenseeRepository;

import java.util.ArrayList;
import java.util.Collection;
import org.springframework.context.MessageSource;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @see bw.org.bocra.portal.licensee.form.LicenseeFormService
 */
@Service("licenseeFormService")
@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
public class LicenseeFormServiceImpl
    extends LicenseeFormServiceBase
{
    public LicenseeFormServiceImpl(
        LicenseeDao licensee,
        LicenseeRepository licenseeRepository,
        FormDao form,
        FormRepository formRepository,
        LicenseeFormDao licenseeForm,
        LicenseeFormRepository licenseeFormRepository,
        MessageSource messageSource
    ) {
        
        super(
            licensee,
            licenseeRepository,
            form,
            formRepository,
            licenseeForm,
            licenseeFormRepository,
            messageSource
        );
    }

    /**
     * @see bw.org.bocra.portal.licensee.form.LicenseeFormService#findById(Long)
     */
    @Override
    protected LicenseeFormVO handleFindById(Long id)
        throws Exception
    {
        // TODO implement protected  LicenseeFormVO handleFindById(Long id)
        throw new UnsupportedOperationException("bw.org.bocra.portal.licensee.form.LicenseeFormService.handleFindById(Long id) Not implemented!");
    }

    /**
     * @see bw.org.bocra.portal.licensee.form.LicenseeFormService#create(Long, Long)
     */
    @Override
    protected LicenseeFormVO handleCreate(Long licenseeId, Long formId)
        throws Exception
    {
        Licensee licensee = getLicenseeDao().load(licenseeId);
        Form form = getFormDao().load(formId);
        LicenseeForm lf = LicenseeForm.Factory.newInstance();
        lf.setLicensee(licensee);
        lf = getLicenseeFormDao().create(form, licensee);

        return getLicenseeFormDao().toLicenseeFormVO(lf);

    }

    /**
     * @see bw.org.bocra.portal.licensee.form.LicenseeFormService#remove(Long)
     */
    @Override
    protected boolean handleRemove(Long id)
        throws Exception
    {
        getFormRepository().deleteById(id);
        return true;
    }

    /**
     * @see bw.org.bocra.portal.licensee.form.LicenseeFormService#getAll()
     */
    @Override
    protected Collection<LicenseeFormVO> handleGetAll()
        throws Exception
    {
        return (Collection<LicenseeFormVO>) getLicenseeFormDao().loadAll(LicenseeFormDao.TRANSFORM_LICENSEEFORMVO);
    }

    /**
     * @see bw.org.bocra.portal.licensee.form.LicenseeFormService#findByLicensee(Long)
     */
    @Override
    protected Collection<LicenseeFormVO> handleFindByLicensee(Long licenseeId)
        throws Exception
    {

        Specification<LicenseeForm> spec = LicenseeFormSpecifications.findByLicenseeId(licenseeId);
        Collection<LicenseeForm> forms = getLicenseeFormRepository().findAll(spec);
        Collection<LicenseeFormVO> vos = new ArrayList<>();

        for (LicenseeForm licenseeForm : forms) {
            LicenseeFormVO vo = new LicenseeFormVO();
            getLicenseeFormDao().toLicenseeFormVO(licenseeForm, vo);

            vos.add(vo);
        }

        return vos;

    }

    /**
     * @see bw.org.bocra.portal.licensee.form.LicenseeFormService#findByForm(Long)
     */
    @Override
    protected Collection<LicenseeFormVO> handleFindByForm(Long formId)
        throws Exception
    {
        

        Specification<LicenseeForm> spec = LicenseeFormSpecifications.findByFormId(formId);
        Collection<LicenseeForm> forms = getLicenseeFormRepository().findAll(spec);
        Collection<LicenseeFormVO> vos = new ArrayList<>();

        for (LicenseeForm licenseeForm : forms) {
            LicenseeFormVO vo = new LicenseeFormVO();
            getLicenseeFormDao().toLicenseeFormVO(licenseeForm, vo);

            vos.add(vo);
        }

        return vos;
    }

    /**
     * @see bw.org.bocra.portal.licensee.form.LicenseeFormService#updateLicensee(Long, Long)
     */
    @Override
    protected LicenseeFormVO handleUpdateLicensee(Long id, Long licenseeId)
        throws Exception
    {
        // TODO implement protected  LicenseeFormVO handleUpdateLicensee(Long id, Long licenseeId)
        throw new UnsupportedOperationException("bw.org.bocra.portal.licensee.form.LicenseeFormService.handleUpdateLicensee(Long id, Long licenseeId) Not implemented!");
    }

    /**
     * @see bw.org.bocra.portal.licensee.form.LicenseeFormService#updateForm(Long, Long)
     */
    @Override
    protected LicenseeFormVO handleUpdateForm(Long id, Long formId)
        throws Exception
    {
        // TODO implement protected  LicenseeFormVO handleUpdateForm(Long id, Long formId)
        throw new UnsupportedOperationException("bw.org.bocra.portal.licensee.form.LicenseeFormService.handleUpdateForm(Long id, Long formId) Not implemented!");
    }

}