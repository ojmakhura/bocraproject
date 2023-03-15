// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 * TEMPLATE:    SpringServiceImpl.vsl in andromda-spring cartridge
 * MODEL CLASS: bocraportal::backend::bw.org.bocra.portal::form::activation::FormActivationService
 * STEREOTYPE:  Service
 */
package bw.org.bocra.portal.form.activation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import bw.org.bocra.portal.form.Form;
import bw.org.bocra.portal.form.FormDao;
import bw.org.bocra.portal.form.FormRepository;
import bw.org.bocra.portal.form.FormVO;
import bw.org.bocra.portal.form.submission.FormSubmission;
import bw.org.bocra.portal.form.submission.FormSubmissionDao;
import bw.org.bocra.portal.form.submission.FormSubmissionRepository;
import bw.org.bocra.portal.form.submission.FormSubmissionVO;
import bw.org.bocra.portal.form.submission.SubmissionService;
import bw.org.bocra.portal.form.submission.data.DataFieldDao;
import bw.org.bocra.portal.form.submission.data.DataFieldRepository;
import bw.org.bocra.portal.licensee.Licensee;
import bw.org.bocra.portal.licensee.LicenseeStatus;
import bw.org.bocra.portal.licensee.LicenseeVO;
import bw.org.bocra.portal.licensee.form.LicenseeForm;
import bw.org.bocra.portal.licensee.sector.LicenseeSector;
import bw.org.bocra.portal.period.Period;
import bw.org.bocra.portal.period.PeriodDao;
import bw.org.bocra.portal.period.PeriodVO;
import bw.org.bocra.portal.sector.form.SectorForm;

/**
 * @see bw.org.bocra.portal.form.activation.FormActivationService
 */
@Service("formActivationService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class FormActivationServiceImpl
        extends FormActivationServiceBase {

    private final SubmissionService submissionService;
    private final PeriodDao periodDao;

    public FormActivationServiceImpl(FormActivationDao formActivationDao, PeriodDao periodDao,
            FormActivationRepository formActivationRepository, FormDao formDao, FormRepository formRepository,
            FormSubmissionDao formSubmissionDao, FormSubmissionRepository formSubmissionRepository,
            SubmissionService submissionService, DataFieldDao dataFieldDao, DataFieldRepository dataFieldRepository,
            MessageSource messageSource) {

        super(formActivationDao, formActivationRepository, formDao, formRepository, formSubmissionDao,
                formSubmissionRepository, dataFieldDao, dataFieldRepository, messageSource);
        // TODO Auto-generated constructor stub
        this.submissionService = submissionService;
        this.periodDao = periodDao;
    }

    /**
     * @see bw.org.bocra.portal.form.activation.FormActivationService#findById(Long)
     */
    @Override
    protected FormActivationVO handleFindById(Long id)
            throws Exception {
        FormActivation act = getFormActivationDao().load(id);
        FormActivationVO activation = getFormActivationDao().toFormActivationVO(act);
        activation.setFormSubmissions(new ArrayList<>());

        for (FormSubmission submission : act.getFormSubmissions()) {
            FormSubmissionVO vo = new FormSubmissionVO();
            vo.setId(submission.getId());
            vo.setSubmittedBy(submission.getSubmittedBy());
            vo.setSubmissionDate(submission.getSubmissionDate());
            vo.setId(submission.getId());
            vo.setSubmissionStatus(submission.getSubmissionStatus());
            vo.setExpectedSubmissionDate(submission.getExpectedSubmissionDate());

            Licensee le = submission.getLicensee();
            LicenseeVO licensee = new LicenseeVO();
            licensee.setId(le.getId());
            licensee.setAddress(le.getAddress());
            licensee.setLicenseeName(le.getLicenseeName());
            licensee.setUin(le.getUin());
            vo.setLicensee(licensee);

            Form frm = act.getForm();
            FormVO form = new FormVO();
            form.setId(frm.getId());
            form.setFormName(frm.getFormName());
            form.setCode(frm.getCode());
            vo.setForm(form);

            Period prd = act.getPeriod();
            PeriodVO period = new PeriodVO();
            period.setId(prd.getId());
            period.setPeriodName(prd.getPeriodName());
            vo.setPeriod(period);

            activation.getFormSubmissions().add(vo);
        }

        return activation;
    }

    /**
     * @see bw.org.bocra.portal.form.activation.FormActivationService#save(FormActivationVO)
     */
    @Override
    protected FormActivationVO handleSave(FormActivationVO formActivation)
            throws Exception {

        FormActivation activation = getFormActivationDao().formActivationVOToEntity(formActivation);

        Set<Long> ids = getLicenseeIds(activation.getForm());

        if (CollectionUtils.isEmpty(ids)) {
            throw new FormActivationServiceException(
                    "Cannot create an activation for a form that has no licensees. Please attach licensees to the form first.");
        }

        boolean isNew = formActivation.getId() == null;

        if (CollectionUtils.isEmpty(activation.getForm().getLicenseeForms())
                && CollectionUtils.isEmpty(activation.getForm().getSectorForms())) {

            throw new FormActivationServiceException("No licensees");
        }

        if (activation.getActivationDeadline() == null) {
            activation.setActivationDeadline(activation.getPeriod().getPeriodEnd()
                    .plusDays(activation.getPeriod().getPeriodConfig().getFinalDay()));
        }

        if (StringUtils.isBlank(activation.getActivationName())) {
            activation.setActivationName(String.format("%s: %s Activation", activation.getForm().getFormName(),
                    activation.getPeriod().getPeriodName()));
        }
        activation = formActivationRepository.save(activation);

        /**
         * The form activations is a new one so we need to
         * create form submissions for all the licensees
         * associated with the form.
         */
        if (isNew) {
            activation.setFormSubmissions(formSubmissionDao.createNewSubmissions(ids, activation.getId()));
        }

        return formActivationDao.toFormActivationVO(activation);
    }

    /**
     * @see bw.org.bocra.portal.form.activation.FormActivationService#remove(Long)
     */
    @Override
    protected boolean handleRemove(Long id)
            throws Exception {
        formActivationDao.remove(id);
        return true;
    }

    /**
     * @see bw.org.bocra.portal.form.activation.FormActivationService#getAll()
     */
    @Override
    protected Collection<FormActivationVO> handleGetAll()
            throws Exception {
        return (Collection<FormActivationVO>) formActivationDao.loadAll(FormActivationDao.TRANSFORM_FORMACTIVATIONVO);
    }

    /**
     * @see bw.org.bocra.portal.form.activation.FormActivationService#search(FormActivationCriteria)
     */
    @Override
    protected Collection<FormActivationVO> handleSearch(FormActivationCriteria criteria)
            throws Exception {
        Collection<FormActivation> activations = getFormActivationDao().findByCriteria(criteria);
        List<FormActivationVO> vos = new ArrayList<>();

        for (FormActivation activation : activations) {
            vos.add(getFormActivationDao().toFormActivationVO(activation));
        }

        return vos;
    }

    /**
     * @see bw.org.bocra.portal.form.activation.FormActivationService#getAll(Integer,
     *      Integer)
     */
    @Override
    protected Collection<FormActivationVO> handleGetAll(Integer pageNumber, Integer pageSize)
            throws Exception {
        return (Collection<FormActivationVO>) getFormActivationDao()
                .loadAll(FormActivationDao.TRANSFORM_FORMACTIVATIONVO, pageNumber, pageSize);
    }

    private Set<Long> getLicenseeIds(Form form) {

        Set<Long> ids = new HashSet<>();

        for (LicenseeForm licensee : form.getLicenseeForms()) {
            if (licensee.getLicensee().getStatus() == LicenseeStatus.ACTIVE)
                ids.add(licensee.getLicensee().getId());
        }

        for (SectorForm sectorForm : form.getSectorForms()) {

            for (LicenseeSector licensee : sectorForm.getSector().getLicenseeSectors()) {
                if (licensee.getLicensee().getStatus() == LicenseeStatus.ACTIVE)
                    ids.add(licensee.getLicensee().getId());
            }
        }

        return ids;
    }

    @Override
    protected FormActivationVO handleRecreateActivationSubmission(Long id, String createdBy) throws Exception {
        FormActivation activation = formActivationRepository.getReferenceById(id);

        formSubmissionDao.remove(activation.getFormSubmissions());

        FormActivationVO formActivation = formActivationDao.toFormActivationVO(activation);
        formActivation
                .setFormSubmissions(submissionService.createNewSubmissions(getLicenseeIds(activation.getForm()), id));

        return formActivation;
    }

    @Override
    protected Collection<FormSubmissionVO> handleCreateMissingSubmissions(Long id, String createdBy) throws Exception {
        FormActivation activation = formActivationRepository.getReferenceById(id);

        // Get the ids of the submissions already existing for the activation
        Set<Long> submissionLicenseeIds = activation.getFormSubmissions().stream().map(sub -> sub.getLicensee().getId())
                .collect(Collectors.toSet());

        // Get licensees attached to the form
        Set<Long> formLicenseeIds = getLicenseeIds(activation.getForm());

        // Get the difference to the two sets.
        Set<Long> missingLicenseeIds = formLicenseeIds.stream().filter(fl -> !submissionLicenseeIds.contains(fl))
                .collect(Collectors.toSet());

        return submissionService.createNewSubmissions(missingLicenseeIds, id);
    }

    @Override
    protected Collection<FormActivationVO> handleActivateDueForms(String createdBy) throws Exception {

        Collection<FormActivationVO> activations = new HashSet<>();

        Collection<Period> periods = periodDao.getActivePeriods();

        if (CollectionUtils.isEmpty(periods)) {
            return new HashSet<>();
        }
        Set<Long> periodConfigs = periods.stream()
                .map(period -> period.getPeriodConfig().getId())
                .collect(Collectors.toSet());

        Collection<Form> forms = formDao.findFormsByPeriodConfigs(periodConfigs);

        periods.forEach(period -> {
            Collection<Form> filtered = forms.stream().filter(form -> {
                return form.getPeriodConfig().getId() == period.getPeriodConfig().getId();
            }).collect(Collectors.toList());
            PeriodVO pv = new PeriodVO();
            pv.setId(period.getId());

            filtered.forEach(fil -> {

                FormActivationCriteria criteria = new FormActivationCriteria();
                criteria.setFormId(fil.getId());
                criteria.setPeriodId(period.getId());

                // We only want to create activations that do not exist.
                if (CollectionUtils.isEmpty(this.search(criteria))) {

                    FormActivationVO activation = new FormActivationVO();
                    activation.setCreatedBy(createdBy);
                    activation.setCreatedDate(LocalDateTime.now());
                    FormVO form = new FormVO();
                    form.setId(fil.getId());
                    activation.setForm(form);

                    activation.setPeriod(pv);
                    activation.setActivationDeadline(
                            period.getPeriodEnd().plusDays(period.getPeriodConfig().getFinalDay()));
                    String activationName = String.format("%s: %s Activation", fil.getFormName(),
                            period.getPeriodName());

                    activation.setActivationName(activationName);
                    activation = this.save(activation);
                    
                    activations.add(activation);

                }
            });
        });

        return activations;
    }

}