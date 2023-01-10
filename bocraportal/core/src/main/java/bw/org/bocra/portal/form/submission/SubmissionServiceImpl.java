// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 * TEMPLATE:    SpringServiceImpl.vsl in andromda-spring cartridge
 * MODEL CLASS: bocraportal::backend::bw.org.bocra.portal::form::submission::SubmissionService
 * STEREOTYPE:  Service
 */
package bw.org.bocra.portal.form.submission;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.MessageSource;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import bw.org.bocra.portal.BocraportalSpecifications;
import bw.org.bocra.portal.form.FormEntryType;
import bw.org.bocra.portal.form.FormVO;
import bw.org.bocra.portal.form.activation.FormActivation;
import bw.org.bocra.portal.form.activation.FormActivationRepository;
import bw.org.bocra.portal.form.field.FormField;
import bw.org.bocra.portal.form.submission.data.DataField;
import bw.org.bocra.portal.form.submission.data.DataFieldDao;
import bw.org.bocra.portal.form.submission.data.DataFieldRepository;
import bw.org.bocra.portal.form.submission.data.DataFieldVO;
import bw.org.bocra.portal.licensee.Licensee;
import bw.org.bocra.portal.licensee.LicenseeRepository;
import bw.org.bocra.portal.licensee.LicenseeVO;
import bw.org.bocra.portal.period.PeriodDao;
import bw.org.bocra.portal.period.PeriodVO;

/**
 * @see bw.org.bocra.portal.form.submission.SubmissionService
 */
@Service("submissionService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class SubmissionServiceImpl
        extends SubmissionServiceBase {

    private final LicenseeRepository licenseeRepository;
    private final FormActivationRepository activationRepository;
    private final PeriodDao periodDao;

    public SubmissionServiceImpl(FormSubmissionDao formSubmissionDao, FormSubmissionRepository formSubmissionRepository, 
            FormActivationRepository activationRepository,PeriodDao periodDao,
            LicenseeRepository licenseeRepository, DataFieldDao dataFieldDao, DataFieldRepository dataFieldRepository, MessageSource messageSource) {

        super(formSubmissionDao, formSubmissionRepository, dataFieldDao, dataFieldRepository, messageSource);
        this.licenseeRepository = licenseeRepository;
        this.activationRepository = activationRepository;
        this.periodDao = periodDao;
    }

    /**
     * @see bw.org.bocra.portal.form.submission.SubmissionService#findById(Long)
     */
    @Override
    protected FormSubmissionVO handleFindById(Long id)
            throws Exception {

        FormSubmission entity = getFormSubmissionDao().get(id);
        FormSubmissionVO submission = getFormSubmissionDao().toFormSubmissionVO(entity);

        return submission;
    }

    /**
     * @see bw.org.bocra.portal.form.submission.SubmissionService#save(FormSubmissionVO)
     */
    @Override
    protected FormSubmissionVO handleSave(FormSubmissionVO formSubmissionVO)
            throws Exception {
        FormSubmission submission = getFormSubmissionDao().formSubmissionVOToEntity(formSubmissionVO);
        submission =formSubmissionRepository.saveAndFlush(submission);

        return getFormSubmissionDao().toFormSubmissionVO(submission);
    }

    /**
     * @see bw.org.bocra.portal.form.submission.SubmissionService#remove(Long)
     */
    @Override
    protected boolean handleRemove(Long id)
            throws Exception {
        if (id == null) {
            return false;
        }

        this.getFormSubmissionDao().remove(id);
        return true;
    }

    private FormSubmissionVO getSubmissionVO(FormSubmission formSubmission) {
        FormSubmissionVO vo = new FormSubmissionVO();
        vo.setId(formSubmission.getId());
        vo.setCreatedBy(formSubmission.getCreatedBy());
        vo.setUpdatedBy(formSubmission.getUpdatedBy());
        vo.setCreatedDate(formSubmission.getCreatedDate());
        vo.setUpdatedDate(formSubmission.getUpdatedDate());
        vo.setSubmittedBy(formSubmission.getSubmittedBy());
        vo.setSubmissionDate(formSubmission.getSubmissionDate());
        vo.setSubmissionStatus(formSubmission.getSubmissionStatus());
        vo.setExpectedSubmissionDate(formSubmission.getExpectedSubmissionDate());

        LicenseeVO lvo = new LicenseeVO();
        lvo.setId(formSubmission.getLicensee().getId());
        lvo.setAddress(formSubmission.getLicensee().getAddress());
        lvo.setUin(formSubmission.getLicensee().getUin());
        lvo.setLicenseeName(formSubmission.getLicensee().getLicenseeName());

        vo.setLicensee(lvo);

        FormVO form = new FormVO();
        form.setId(formSubmission.getForm().getId());
        form.setCode(formSubmission.getForm().getCode());
        form.setFormName(formSubmission.getForm().getFormName());

        vo.setForm(form);

        PeriodVO period = new PeriodVO();
        period.setId(formSubmission.getPeriod().getId());
        period.setPeriodName(formSubmission.getPeriod().getPeriodName());
        vo.setPeriod(period);

        return vo;
    }

    private Collection<FormSubmissionVO> toSubmissionVOCollection(Collection<FormSubmission> submissions) {
        Collection<FormSubmissionVO> vos = new ArrayList<>();

        for (FormSubmission formSubmission : submissions) {
            vos.add(getSubmissionVO(formSubmission));
        }

        return vos;
    }

    /**
     * @see bw.org.bocra.portal.form.submission.SubmissionService#getAll()
     */
    @Override
    protected Collection<FormSubmissionVO> handleGetAll()
            throws Exception {
        return toSubmissionVOCollection(getFormSubmissionDao()
                .loadAll());
    }

    /**
     * @see bw.org.bocra.portal.form.submission.SubmissionService#search(FormSubmissionCriteria)
     */
    @Override
    protected Collection<FormSubmissionVO> handleSearch(FormSubmissionCriteria criteria)
            throws Exception {

        Collection<FormSubmission> submissions = getFormSubmissionDao().findByCriteria(criteria);

        return toSubmissionVOCollection(submissions);
    }

    /**
     * @see bw.org.bocra.portal.form.submission.SubmissionService#getAll(Integer,
     *      Integer)
     */
    @Override
    protected Collection<FormSubmissionVO> handleGetAll(Integer pageNumber, Integer pageSize)
            throws Exception {

        return toSubmissionVOCollection(
                (Collection<FormSubmission>) getFormSubmissionDao().loadAll(pageNumber, pageSize));
    }

    @Override
    protected DataFieldVO handleAddDataField(DataFieldVO dataField) throws Exception {
        DataField field = getDataFieldDao().dataFieldVOToEntity(dataField);
        return (DataFieldVO) dataFieldDao.create(DataFieldDao.TRANSFORM_DATAFIELDVO, field);
    }

    @Override
    protected Collection<DataFieldVO> handleAddDataFields(Set<DataFieldVO> dataFields) throws Exception {

        Collection<DataFieldVO> fields = new ArrayList<>();

        for (DataFieldVO field : dataFields) {
            fields.add(this.addDataField(field));
        }

        return null;
    }

    @Override
    protected Boolean handleDeleteDataField(Long id) throws Exception {
        getDataFieldDao().remove(id);
        return true;
    }

    @Override
    protected SubmissionSummary handleGetSubmissionSummary(FormSubmissionCriteria criteria) throws Exception {
        SubmissionSummary summary = new SubmissionSummary();
        Specification<FormSubmission> specs = null;

        if (StringUtils.isNotBlank(criteria.getSubmittedBy())) {
            specs = BocraportalSpecifications.<FormSubmission, String>findByAttribute("submittedBy",
                    criteria.getSubmittedBy());
        }

        summary.setMySubmissions(formSubmissionRepository.count(specs));
        specs = null;

        if (criteria.getLicenseeId() != null) {
            specs = FormSubmissionSpecifications.findByLicenseeId(criteria.getLicenseeId());
        }

        /**
         * Get all values related to status
         */
        Specification<FormSubmission> sSpecs = BocraportalSpecifications
                .<FormSubmission, FormSubmissionStatus>findByAttribute("submissionStatus",
                        FormSubmissionStatus.SUBMITTED);

        if (specs != null) {
            sSpecs = sSpecs.and(specs);
        }
        summary.setAllSubmissions(formSubmissionRepository.count(sSpecs));

        /**
         * Get count of returned submissions
         */
        sSpecs = BocraportalSpecifications.<FormSubmission, FormSubmissionStatus>findByAttribute("submissionStatus",
                FormSubmissionStatus.RETURNED);

        if (specs != null) {
            sSpecs = sSpecs.and(specs);
        }
        summary.setReturnedSubmissions(formSubmissionRepository.count(sSpecs));

        /**
         * Get count of accepted submissions
         */
        sSpecs = BocraportalSpecifications.<FormSubmission, FormSubmissionStatus>findByAttribute("submissionStatus",
                FormSubmissionStatus.ACCEPTED);

        if (specs != null) {
            sSpecs = sSpecs.and(specs);
        }
        summary.setReturnedSubmissions(formSubmissionRepository.count(sSpecs));

        /**
         * Get count of overdue submissions
         */
        sSpecs = BocraportalSpecifications.<FormSubmission, FormSubmissionStatus>findByAttribute(
                "submissionStatus", FormSubmissionStatus.DRAFT)
                .or(BocraportalSpecifications.<FormSubmission, FormSubmissionStatus>findByAttribute(
                        "submissionStatus", FormSubmissionStatus.NEW))
                .or(BocraportalSpecifications.<FormSubmission, FormSubmissionStatus>findByAttribute(
                        "submissionStatus", FormSubmissionStatus.OVERDUE));
        sSpecs = sSpecs.and(
                BocraportalSpecifications.<FormSubmission, LocalDate>findByAttributeLessThan("expectedSubmissionDate",
                        LocalDate.now()));

        if (specs != null) {
            sSpecs = sSpecs.and(specs);
        }

        Collection<FormSubmission> overdue = formSubmissionRepository.findAll(sSpecs);
        for (FormSubmission sub : overdue) {
            if (sub.getSubmissionStatus() != FormSubmissionStatus.OVERDUE) {
                sub.setSubmissionStatus(FormSubmissionStatus.OVERDUE);
                formSubmissionRepository.saveAndFlush(sub);
            }
        }

        summary.setOverdueSubmissions(formSubmissionRepository.count(sSpecs));

        /**
         * Get all values related to status
         */
        sSpecs = BocraportalSpecifications
                .<FormSubmission, FormSubmissionStatus>findByAttribute("submissionStatus", FormSubmissionStatus.NEW);

        if (specs != null) {
            sSpecs = sSpecs.and(specs);
        }
        summary.setNewSubmissions(formSubmissionRepository.count(sSpecs));

        /**
         * Get count of draft submissions
         */
        sSpecs = BocraportalSpecifications.<FormSubmission, FormSubmissionStatus>findByAttribute("submissionStatus",
                FormSubmissionStatus.DRAFT);

        if (specs != null) {
            sSpecs = sSpecs.and(specs);
        }
        summary.setDraftSubmissions(formSubmissionRepository.count(sSpecs));

        return summary;
    }

    @Override
    protected Collection<FormSubmissionVO> handleFindByIds(Set<Long> ids) throws Exception {

        Specification<FormSubmission> sSpecs = BocraportalSpecifications.<FormSubmission, Long>findByAttributeIn("id",
                ids);
        Collection<FormSubmission> submissions = formSubmissionRepository.findAll(sSpecs);
        Collection<FormSubmissionVO> vos = new ArrayList<>();

        for (FormSubmission formSubmission : submissions) {
            vos.add(getFormSubmissionDao().toFormSubmissionVO(formSubmission));
        }

        return vos;
    }

    @Override
    protected Boolean handleUpdateSubmissionStatus(Long id, FormSubmissionStatus submissionStatus,
            final LocalDateTime updateTime, final String username) throws Exception {
        FormSubmission submission = formSubmissionRepository.getReferenceById(id);
        submission.setSubmissionStatus(submissionStatus);
        if (submissionStatus == FormSubmissionStatus.SUBMITTED) {
            submission.setSubmissionDate(updateTime);
            submission.setSubmittedBy(username);
        } else if (submissionStatus == FormSubmissionStatus.ACCEPTED) {
            submission.setAcceptedDate(updateTime);
            submission.setAcceptedBy(username);
        } else if (submissionStatus == FormSubmissionStatus.RETURNED) {
            submission.setReturnedDate(updateTime);
            submission.setReturnedBy(username);
        } else {
            submission.setUpdatedBy(username);
            submission.setUpdatedDate(updateTime);
        }

        formSubmissionRepository.saveAndFlush(submission);

        return true;
    }

    @Override
    protected Collection<FormSubmissionVO> handleLoadDueSubmissions() throws Exception {
        FormSubmissionCriteria criteria = new FormSubmissionCriteria();
        
        Collection<Long> periodIds = periodDao.getActivePeriods().stream()
                                        .map(period -> period.getId()).toList();

        criteria.setPeriodIds(periodIds);
        Collection<FormSubmission> submissions = getFormSubmissionDao().findByCriteria(criteria);

        return toSubmissionVOCollection(submissions);
    }

    @Override
    protected Integer handleCheckOverdueSubmissions() throws Exception {
        Specification<FormSubmission> sSpecs = BocraportalSpecifications
                .<FormSubmission, FormSubmissionStatus>findByAttribute(
                        "submissionStatus", FormSubmissionStatus.DRAFT)
                .or(BocraportalSpecifications.<FormSubmission, FormSubmissionStatus>findByAttribute(
                        "submissionStatus", FormSubmissionStatus.NEW))
                .or(BocraportalSpecifications.<FormSubmission, FormSubmissionStatus>findByAttribute(
                        "submissionStatus", FormSubmissionStatus.OVERDUE));
        sSpecs = sSpecs.and(
                BocraportalSpecifications.<FormSubmission, LocalDate>findByAttributeLessThan("expectedSubmissionDate",
                        LocalDate.now()));

        Collection<FormSubmission> overdue = formSubmissionRepository.findAll(sSpecs);
        for (FormSubmission sub : overdue) {
            if (sub.getSubmissionStatus() != FormSubmissionStatus.OVERDUE) {
                sub.setSubmissionStatus(FormSubmissionStatus.OVERDUE);
                formSubmissionRepository.saveAndFlush(sub);
            }
        }

        return overdue.size();
    }

    @Override
    protected Collection<FormSubmissionVO> handleCreateNewSubmissions(Set<Long> licenseeIds, Long activationId)
            throws Exception {

        if (activationId == null) {
            throw new SubmissionServiceException("Activation ID must not be null.");
        }

        if (activationId < 1) {
            throw new SubmissionServiceException("Activation ID must not be less than 1.");
        }

        if (CollectionUtils.isEmpty(licenseeIds)) {
            throw new SubmissionServiceException("At lease 1 licensee must be provided.");
        }

        FormActivation activation = activationRepository.getReferenceById(activationId);

        Collection<FormSubmissionVO> submissions = new ArrayList<>();

        for (Long licenseeId : licenseeIds) {

            Licensee licensee = licenseeRepository.getReferenceById(licenseeId);

            FormSubmission submission = FormSubmission.Factory.newInstance();
            submission.setCreatedBy(activation.getCreatedBy());
            submission.setCreatedDate(LocalDateTime.now());
            submission.setForm(activation.getForm());
            submission.setLicensee(licensee);
            submission.setFormActivation(activation);
            submission.setPeriod(activation.getPeriod());
            submission.setSubmissionStatus(FormSubmissionStatus.NEW);

            submission.setExpectedSubmissionDate(activation.getActivationDeadline());

            /**
             * If the for requires single entry, the we create the data fields
             */
            if (activation.getForm().getEntryType() == FormEntryType.SINGLE) {
                for (FormField field : activation.getForm().getFormFields()) {
                    DataField dataField = DataField.Factory.newInstance();
                    dataField.setFormSubmission(submission);
                    dataField.setFormField(field);
                    dataField.setValue(field.getDefaultValue());
                    dataField.setRow(0);

                    submission.getDataFields().add(dataField);
                }
            }
            submission = formSubmissionRepository.saveAndFlush(submission);

            FormSubmissionVO vo = new FormSubmissionVO();
            getFormSubmissionDao().toFormSubmissionVO(submission, vo);
            submissions.add(vo);
        }

        return submissions;
    }

}