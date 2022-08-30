// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 * TEMPLATE:    SpringServiceImpl.vsl in andromda-spring cartridge
 * MODEL CLASS: bocraportal::backend::bw.org.bocra.portal::licence::type::form::LicenceTypeFormService
 * STEREOTYPE:  Service
 */
package bw.org.bocra.portal.licence.type.form;

import java.util.ArrayList;
import java.util.Collection;
import org.springframework.context.MessageSource;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import bw.org.bocra.portal.form.Form;
import bw.org.bocra.portal.form.FormDao;
import bw.org.bocra.portal.form.FormRepository;
import bw.org.bocra.portal.licence.type.LicenceType;
import bw.org.bocra.portal.licence.type.LicenceTypeDao;
import bw.org.bocra.portal.licence.type.LicenceTypeRepository;

/**
 * @see bw.org.bocra.portal.licence.type.form.LicenceTypeFormService
 */
@Service("licenceTypeFormService")
@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
public class LicenceTypeFormServiceImpl
    extends LicenceTypeFormServiceBase
{
    

    public LicenceTypeFormServiceImpl(LicenceTypeFormDao licenceTypeFormDao,
            LicenceTypeFormRepository licenceTypeFormRepository, LicenceTypeDao licenceTypeDao,
            LicenceTypeRepository licenceTypeRepository, FormDao formDao, FormRepository formRepository,
            MessageSource messageSource) {
        super(licenceTypeFormDao, licenceTypeFormRepository, licenceTypeDao, licenceTypeRepository, formDao, formRepository,
                messageSource);
        //TODO Auto-generated constructor stub
    }

    /**
     * @see bw.org.bocra.portal.licence.type.form.LicenceTypeFormService#findById(Long)
     */
    @Override
    protected LicenceTypeFormVO handleFindById(Long id)
        throws Exception
    {
        LicenceTypeForm ltf = getLicenceTypeFormDao().load(id);
        return getLicenceTypeFormDao().toLicenceTypeFormVO(ltf);
    }

    /**
     * @see bw.org.bocra.portal.licence.type.form.LicenceTypeFormService#create(Long, Long)
     */
    @Override
    protected LicenceTypeFormVO handleCreate(Long licenceTypeId, Long formId)
        throws Exception
    {
        LicenceType licenceType = getLicenceTypeDao().load(licenceTypeId);
        Form form = getFormDao().load(formId);
        LicenceTypeForm lf = LicenceTypeForm.Factory.newInstance();
        lf.setLicenceType(licenceType);
        lf = getLicenceTypeFormDao().create(form, licenceType);

        return getLicenceTypeFormDao().toLicenceTypeFormVO(lf);
    }

    /**
     * @see bw.org.bocra.portal.licence.type.form.LicenceTypeFormService#remove(Long)
     */
    @Override
    protected boolean handleRemove(Long id)
        throws Exception
    {
        getLicenceTypeFormRepository().deleteById(id);
        return true;
    }

    /**
     * @see bw.org.bocra.portal.licence.type.form.LicenceTypeFormService#getAll()
     */
    @Override
    protected Collection<LicenceTypeFormVO> handleGetAll()
        throws Exception
    {
        return (Collection<LicenceTypeFormVO>) getLicenceTypeFormDao().loadAll(LicenceTypeFormDao.TRANSFORM_LICENCETYPEFORMVO);
    }

    /**
     * @see bw.org.bocra.portal.licence.type.form.LicenceTypeFormService#findByLicenceType(Long)
     */
    @Override
    protected Collection<LicenceTypeFormVO> handleFindByLicenceType(Long licenceTypeId)
        throws Exception
    {
        Specification<LicenceTypeForm> spec = LicenceTypeFormSpecifications.findByLicenceTypeId(licenceTypeId);
        Collection<LicenceTypeForm> forms = getLicenceTypeFormRepository().findAll(spec);
        Collection<LicenceTypeFormVO> vos = new ArrayList<>();

        for (LicenceTypeForm licenceTypeForm : forms) {
            LicenceTypeFormVO vo = new LicenceTypeFormVO();
            getLicenceTypeFormDao().toLicenceTypeFormVO(licenceTypeForm, vo);

            vos.add(vo);
        }

        return vos;
    }

    /**
     * @see bw.org.bocra.portal.licence.type.form.LicenceTypeFormService#findByForm(Long)
     */
    @Override
    protected Collection<LicenceTypeFormVO> handleFindByForm(Long formId)
        throws Exception
    {
        Specification<LicenceTypeForm> spec = LicenceTypeFormSpecifications.findByFormId(formId);
        Collection<LicenceTypeForm> forms = getLicenceTypeFormRepository().findAll(spec);
        Collection<LicenceTypeFormVO> vos = new ArrayList<>();

        for (LicenceTypeForm licenceTypeForm : forms) {
            LicenceTypeFormVO vo = new LicenceTypeFormVO();
            getLicenceTypeFormDao().toLicenceTypeFormVO(licenceTypeForm, vo);

            vos.add(vo);
        }

        return vos;
    }

    /**
     * @see bw.org.bocra.portal.licence.type.form.LicenceTypeFormService#updateLicenceType(Long, Long)
     */
    @Override
    protected LicenceTypeFormVO handleUpdateLicenceType(Long id, Long licenceTypeId)
        throws Exception
    {
        LicenceTypeForm lf = getLicenceTypeFormDao().load(id);
        lf.setLicenceType(getLicenceTypeDao().load(licenceTypeId));
        lf = getLicenceTypeFormRepository().save(lf);

        return getLicenceTypeFormDao().toLicenceTypeFormVO(lf);

    }

    /**
     * @see bw.org.bocra.portal.licence.type.form.LicenceTypeFormService#updateForm(Long, Long)
     */
    @Override
    protected LicenceTypeFormVO handleUpdateForm(Long id, Long formId)
        throws Exception
    {
        LicenceTypeForm lf = getLicenceTypeFormDao().load(id);
        lf.setForm(getFormDao().load(formId));
        lf = getLicenceTypeFormRepository().save(lf);

        return getLicenceTypeFormDao().toLicenceTypeFormVO(lf);
    }

}