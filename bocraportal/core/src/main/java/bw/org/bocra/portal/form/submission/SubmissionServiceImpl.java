// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 * TEMPLATE:    SpringServiceImpl.vsl in andromda-spring cartridge
 * MODEL CLASS: bocraportal::backend::bw.org.bocra.portal::form::submission::SubmissionService
 * STEREOTYPE:  Service
 */
package bw.org.bocra.portal.form.submission;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import bw.org.bocra.portal.BocraportalSpecifications;
import bw.org.bocra.portal.DataPage;
import bw.org.bocra.portal.access.AccessPoint;
import bw.org.bocra.portal.access.AccessPointDaoImpl;
import bw.org.bocra.portal.form.Form;
import bw.org.bocra.portal.form.FormEntryType;
import bw.org.bocra.portal.form.FormVO;
import bw.org.bocra.portal.form.activation.FormActivation;
import bw.org.bocra.portal.form.activation.FormActivationRepository;
import bw.org.bocra.portal.form.field.FieldType;
import bw.org.bocra.portal.form.field.FieldValueType;
import bw.org.bocra.portal.form.field.FormField;
import bw.org.bocra.portal.form.section.FormSection;
import bw.org.bocra.portal.form.submission.data.DataField;
import bw.org.bocra.portal.form.submission.data.DataFieldDao;
import bw.org.bocra.portal.form.submission.data.DataFieldRepository;
import bw.org.bocra.portal.form.submission.data.DataFieldSectionVO;
import bw.org.bocra.portal.form.submission.data.DataFieldVO;
import bw.org.bocra.portal.form.submission.data.SubmissionDataRepository;
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
    private final SubmissionDataRepository submissionDataRepository;

    public SubmissionServiceImpl(FormSubmissionDao formSubmissionDao, FormSubmissionRepository formSubmissionRepository,
            FormActivationRepository activationRepository, PeriodDao periodDao,
            SubmissionDataRepository submissionDataRepository,
            LicenseeRepository licenseeRepository, DataFieldDao dataFieldDao, DataFieldRepository dataFieldRepository,
            MessageSource messageSource) {

        super(formSubmissionDao, formSubmissionRepository, dataFieldDao, dataFieldRepository, messageSource);
        this.licenseeRepository = licenseeRepository;
        this.activationRepository = activationRepository;
        this.periodDao = periodDao;
        this.submissionDataRepository = submissionDataRepository;
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
        submission = formSubmissionRepository.saveAndFlush(submission);

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

        Specification<FormSubmission> tmp = BocraportalSpecifications.findByJoinAttributeIsEmpty("form", "roles");

        if (CollectionUtils.isNotEmpty(criteria.getRoles())) {

            for (String role : criteria.getRoles()) {
                tmp = tmp.or(BocraportalSpecifications.findByJoinAttributeIsMember("form", "roles", role));
            }
        }

        SubmissionSummary summary = new SubmissionSummary();
        Specification<FormSubmission> specs = tmp;

        if (StringUtils.isNotBlank(criteria.getSubmittedBy())) {
            specs = specs.and(BocraportalSpecifications.<FormSubmission, String>findByAttribute("submittedBy",
                    criteria.getSubmittedBy()))
                    .and(BocraportalSpecifications
                            .<FormSubmission, FormSubmissionStatus>findByAttribute("submissionStatus",
                                    FormSubmissionStatus.SUBMITTED));

        }

        summary.setMySubmissions(formSubmissionRepository.count(specs));
        specs = tmp;

        if (criteria.getLicenseeId() != null) {
            specs = specs.and(FormSubmissionSpecifications.findByLicenseeId(criteria.getLicenseeId()));
        }

        /**
         * Get all values related to status
         */
        Specification<FormSubmission> sSpecs = BocraportalSpecifications
                .<FormSubmission, FormSubmissionStatus>findByAttribute("submissionStatus",
                        FormSubmissionStatus.SUBMITTED);

        sSpecs = sSpecs.and(specs);
        summary.setAllSubmissions(formSubmissionRepository.count(sSpecs));

        /**
         * Get count of returned submissions
         */
        sSpecs = BocraportalSpecifications.<FormSubmission, FormSubmissionStatus>findByAttribute("submissionStatus",
                FormSubmissionStatus.RETURNED);

        sSpecs = sSpecs.and(specs);
        summary.setReturnedSubmissions(formSubmissionRepository.count(sSpecs));

        /**
         * Get count of accepted submissions
         */
        sSpecs = BocraportalSpecifications.<FormSubmission, FormSubmissionStatus>findByAttribute("submissionStatus",
                FormSubmissionStatus.ACCEPTED);

        sSpecs = sSpecs.and(specs);
        summary.setAcceptedSubmissions(formSubmissionRepository.count(sSpecs));

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

        sSpecs = sSpecs.and(specs);

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

        sSpecs = sSpecs.and(specs);
        summary.setNewSubmissions(formSubmissionRepository.count(sSpecs));

        /**
         * Get count of draft submissions
         */
        sSpecs = BocraportalSpecifications.<FormSubmission, FormSubmissionStatus>findByAttribute("submissionStatus",
                FormSubmissionStatus.DRAFT);

        sSpecs = sSpecs.and(specs);
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
            FormSubmissionVO vo = getFormSubmissionDao().toFormSubmissionVO(formSubmission);
            if(StringUtils.isBlank(vo.getLicensee().getAlias())) {
                vo.getLicensee().setAlias(vo.getLicensee().getLicenseeName());
            }
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
                .map(period -> period.getId()).collect(Collectors.toList());

        criteria.setPeriodIds(periodIds);
        Collection<FormSubmission> submissions = getFormSubmissionDao().findByCriteria(criteria);

        return toSubmissionVOCollection(submissions);
    }

    @Override
    protected Collection<FormSubmissionVO> handleCheckOverdueSubmissions() throws Exception {
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

        return formSubmissionDao.toFormSubmissionVOCollection(overdue);
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

        return formSubmissionDao.createNewSubmissions(licenseeIds, activationId)
                .stream().map(sub -> getFormSubmissionDao().toFormSubmissionVO(sub)).collect(Collectors.toList());

    }

    @Override
    protected FormSubmissionVO handleUploadData(Long submissonId, MultipartFile file) throws Exception {
        FormSubmission submission = formSubmissionRepository.getReferenceById(submissonId);

        /**
         * Each file MUST have the fist line as headers. So we read the first line
         * and use it as headers before closing the buffered reader object.
         */
        BufferedReader fr = new BufferedReader(new InputStreamReader(file.getInputStream()));
        List<String> headers = List.of(fr.readLine().split(","));
        fr.close();

        Form form = submission.getForm();
        Collection<FormField> fields = form.getFormFields();

        try (
                InputStream is = file.getInputStream();
                BufferedReader fileReader = new BufferedReader(new InputStreamReader(is));
                CSVParser csvParser = new CSVParser(
                        fileReader,
                        CSVFormat.DEFAULT.builder()
                                .setHeader(headers.toArray(new String[headers.size()]))
                                .setSkipHeaderRecord(true)
                                .build());) {

            Collection<DataField> dataFields = new ArrayList<>();
            ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
            ScriptEngine scriptEngine = scriptEngineManager.getEngineByName("nashorn");

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {


                List<DataField> expressions = new ArrayList<>();
                List<DataField> tmpFields = new ArrayList<>();

                for (FormField f : fields) {

                    DataField dataField = new DataField();
                    dataField.setRow((int) csvRecord.getRecordNumber());
                    dataField.setFormField(f);
                    dataField.setFormSubmission(submission);

                    if (f.getFieldValueType() == FieldValueType.MANUAL) {
                        dataField.setValue(csvRecord.get(f.getFieldId()).trim());
                    } else {
                        dataField.setValue(f.getExpression());
                        expressions.add(dataField);
                    }

                    tmpFields.add(dataField);
                }

                // Evaluate expressions
                for (DataField dataField : expressions) {
                    String expression = dataField.getValue();

                    for (DataField df : tmpFields) {
                        // check if the expression contains the field id
                        if (expression.contains('[' + df.getFormField().getFieldId() + ']')
                                && NumberUtils.isParsable(df.getValue())) {
                            expression = expression.replaceAll("\\[" + df.getFormField().getFieldId() + "\\]",
                                    df.getValue() == null ? "0" : df.getValue());
                        }
                    }

                    double v = (Double) scriptEngine.eval(expression);
                    DecimalFormat f = new DecimalFormat("##.00");

                    dataField.setValue(f.format(v) + "");
                }

                dataFields.addAll(tmpFields);
            }

            submission.setDataFields(dataFields);
        }

        if (submission.getExpectedSubmissionDate().isAfter(LocalDate.now())) {
            submission.setSubmissionStatus(FormSubmissionStatus.DRAFT);
        } else {
            submission.setSubmissionStatus(FormSubmissionStatus.OVERDUE);
        }

        return getFormSubmissionDao().toFormSubmissionVO(submission);
    }

    @Override
    protected DataPage handleGetSubmissionData(Long submissionId, Integer pageNumber, Integer pageSize)
            throws Exception {

        if (submissionId == null) {
            throw new SubmissionServiceException("Submission ID must not be null.");
        }

        if (submissionId < 1) {
            throw new SubmissionServiceException("Submission ID must not be less than 1.");
        }

        if (pageNumber == null) {
            throw new SubmissionServiceException("Page number must not be null.");
        }

        if (pageNumber < 1) {
            throw new SubmissionServiceException("Page number must not be less than 1.");
        }

        if (pageSize == null) {
            throw new SubmissionServiceException("Page size must not be null.");
        }

        if (pageSize < 1) {
            throw new SubmissionServiceException("Page size must not be less than 1.");
        }

        FormSubmission submission = formSubmissionRepository.getReferenceById(submissionId);

        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize * submission.getForm().getFormFields().size());

        Page<DataField> data = submissionDataRepository
                .findByFormSubmissionIdOrderByRowAscFormFieldPositionAsc(submissionId, pageable);

        List<Object> vos = new ArrayList<>();
        data.stream().forEach(d -> vos.add(dataFieldDao.toDataFieldVO(d)));

        DataPage page = new DataPage();
        page.setPageNumber(data.getNumber() + 1);
        page.setTotalElements(data.getTotalElements() / submission.getForm().getFormFields().size());
        page.setTotalPages(data.getTotalPages() / submission.getForm().getFormFields().size());
        page.setElements(vos);

        return page;
    }

    @Override
    protected DataPage handleSearch(Integer pageNumber, Integer pageSize, FormSubmissionCriteria criteria)
            throws Exception {

        if (pageNumber == null) {
            throw new SubmissionServiceException("Page number must not be null.");
        }

        if (pageNumber < 1) {
            throw new SubmissionServiceException("Page number must not be less than 1.");
        }

        if (pageSize == null) {
            throw new SubmissionServiceException("Page size must not be null.");
        }

        if (pageSize < 1) {
            throw new SubmissionServiceException("Page size must not be less than 1.");
        }

        Specification<FormSubmission> specifications = ((FormSubmissionDaoImpl) formSubmissionDao)
                .getCriteriaSpecifications(criteria);
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        Page<FormSubmission> pageData = formSubmissionRepository.findAll(specifications, pageable);

        List<Object> vos = new ArrayList<>();

        pageData.getContent().forEach(submission -> {
            vos.add(formSubmissionDao.toFormSubmissionVO(submission));
        });

        DataPage page = new DataPage();
        page.setPageNumber(pageData.getNumber() + 1);
        page.setTotalElements(pageData.getTotalElements());
        page.setTotalPages(pageData.getTotalPages());
        page.setElements(vos);

        return page;
    }

    @Override
    protected Collection<FormSubmissionVO> handlePreProcessedFindById(MultipleEntryFormFilter filters)
            throws Exception {

        Specification<FormSubmission> sSpecs = BocraportalSpecifications.<FormSubmission, Long>findByAttributeIn("id",
                filters.getIds());

        Collection<FormSubmission> submissions = formSubmissionRepository.findAll(sSpecs);
        Collection<FormSubmissionVO> vos = new ArrayList<>();

        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        ScriptEngine scriptEngine = scriptEngineManager.getEngineByName("nashorn");

        for (FormSubmission formSubmission : submissions) {

            Collection<String> numberFields = new ArrayList<>();
            formSubmission.getForm().getFormFields().forEach(f -> {
                if (f.getFieldType() == FieldType.NUMBER) {
                    numberFields.add(f.getFieldId());
                }
            });

            FormSubmissionVO subVO = new FormSubmissionVO();
            formSubmissionDao.toFormSubmissionVO(formSubmission, subVO);
            subVO.getForm().setLicenceTypes(null);
            subVO.getForm().setFormSections(null);
            subVO.getForm().setLicensees(null);
            subVO.getForm().setSectors(null);

            subVO.getLicensee().setUsers(null);
            subVO.getLicensee().setForms(null);
            subVO.getLicensee().setLicences(null);
            subVO.getLicensee().setDocuments(null);
            subVO.getLicensee().setSectors(null);
            subVO.getLicensee().setShareholders(null);

            if(StringUtils.isBlank(subVO.getLicensee().getAlias())) {
                subVO.getLicensee().setAlias(subVO.getLicensee().getLicenseeName());
            }

            Collection<DataFieldVO> fvo = getDataFieldDao().toDataFieldVOCollection(formSubmission.getDataFields());

            final Collection<DataFieldVO> newFields = new ArrayList<>();

            if (filters.getGroupOperation() != GroupOperation.NONE) {

                // Get a collection of fields by row
                Map<Integer, Collection<DataFieldVO>> fmap = new HashMap<>();
                fvo.forEach(f -> {
                    if (!fmap.containsKey(f.getRow())) {
                        fmap.put(f.getRow(), new ArrayList<>());
                    }

                    fmap.get(f.getRow()).add(f);
                });

                Map<String, Map<Integer, Collection<DataFieldVO>>> fieldMap = new HashMap<>();

                fmap.entrySet().forEach(entry -> {

                    entry.getValue().stream().filter(p -> p.getFormField().getFieldId().equals(filters.getGroupBy()) || StringUtils.isBlank(filters.getGroupBy()))
                            .findFirst().ifPresent(f -> {
                                String value = f.getValue();
                                if(StringUtils.isBlank(filters.getGroupBy())) {
                                    value = "All";
                                }

                                if (!fieldMap.containsKey(value)) {
                                    fieldMap.put(value, new HashMap<>());
                                }

                                fieldMap.get(value).put(entry.getKey(), entry.getValue());

                            });
                });

                int row = 1;

                for (Map.Entry<String, Map<Integer, Collection<DataFieldVO>>> entry : fieldMap.entrySet()) {

                    // TODO: Aggregate number data fields
                    Map<String, Collection<Double>> agg = new HashMap<>();
                    entry.getValue().entrySet().forEach(e -> {
                        e.getValue().forEach(f -> {
                            if (numberFields.contains(f.getFormField().getFieldId())) {
                                
                                if (!agg.containsKey(f.getFormField().getFieldId())) {
                                    agg.put(f.getFormField().getFieldId(), new ArrayList<>());
                                }

                                double value = 0.0;

                                try {
                                    value = Double.parseDouble(f.getValue());
                                } catch (Exception ex) {
                                    // Ignore
                                }

                                agg.get(f.getFormField().getFieldId()).add(value);
                            }
                        });
                    });

                    // TODO: Aggregate the fields
                    Map<String, Double> output = this.calculate(agg, filters.getGroupOperation());

                    Collection<DataFieldVO> first = entry.getValue().values().iterator().next();

                    for (DataFieldVO field : first) {

                        if (output.containsKey(field.getFormField().getFieldId())) {
                            field.setValue(output.get(field.getFormField().getFieldId()).toString());
                        }

                        if(StringUtils.isBlank(filters.getGroupBy()) && field.getFormField().getFieldType() != FieldType.NUMBER) {
                            field.setValue("-");
                        }

                        field.setRow(row);
                    }

                    newFields.addAll(first);

                    row++;
                }

            } else {
                newFields.addAll(fvo);
            }

            Collection<DataFieldVO> finalFields = newFields;

            if(StringUtils.isNotBlank(filters.getOrderBy())) {
                finalFields = this.sortDataFields(newFields, filters.getOrderBy(), filters.getSortOrder() == null ? GroupSort.ASCENDING : filters.getSortOrder());
            }

            if(filters.getLimit() != null && filters.getLimit() > 0) {
                int limit = filters.getLimit() * subVO.getForm().getFormFields().size();
                finalFields = finalFields.stream().limit(limit).collect(Collectors.toList());
            }

            subVO.setDataFields(finalFields);

            vos.add(subVO);
        }

        return vos;
    }

    private Collection<DataFieldVO> sortDataFields(Collection<DataFieldVO> fields, String sortBy, GroupSort groupSort) {

        if (StringUtils.isBlank(sortBy)) {
            return fields;
        }

        // Get a collection of fields to sort by
        Collection<DataFieldVO> sortFields = fields.stream().filter(f -> f.getFormField().getFieldId().equals(sortBy)).collect(Collectors.toList());
        Collection<DataFieldVO> nonSortFields = fields.stream().filter(f -> !f.getFormField().getFieldId().equals(sortBy)).collect(Collectors.toList());
        Map<Integer, Collection<DataFieldVO>> nonSortFieldsMap = new HashMap<>();

        // Create a map of unsorted fields keyed by row
        nonSortFields.forEach(f -> {
            if (!nonSortFieldsMap.containsKey(f.getRow())) {
                nonSortFieldsMap.put(f.getRow(), new ArrayList<>());
            }

            nonSortFieldsMap.get(f.getRow()).add(f);
        });

        // Sort the fields
        sortFields = sortFields.stream().sorted((f1, f2) -> {
            return groupSort == GroupSort.ASCENDING ? 
                        f1.getValue().compareTo(f2.getValue()) :
                        f2.getValue().compareTo(f1.getValue());

        }).collect(Collectors.toList());

        Map<Integer, Integer> rowMap = new HashMap<>();

        int i = 1;
        for (DataFieldVO dataFieldVO : sortFields) {
            rowMap.put(i, dataFieldVO.getRow());
            dataFieldVO.setRow(i);

            i++;
        }

        Collection<DataFieldVO> sortedFields = new ArrayList<>();
        sortFields.forEach(f -> {
            sortedFields.add(f);
            int row = rowMap.get(f.getRow());
            if (nonSortFieldsMap.containsKey(row)) {
                Collection<DataFieldVO> tmp = nonSortFieldsMap.get(row);
                tmp.forEach(t -> {
                    t.setRow(f.getRow());
                    sortedFields.add(t);
                });
                // sortedFields.addAll(nonSortFieldsMap.get(f.getRow()));
            }
        });

        return sortedFields;
    }

    private Map<String, Double> calculate(Map<String, Collection<Double>> agg, GroupOperation groupOperation) {
        Map<String, Double> output = new HashMap<>();
        
        DecimalFormat f = new DecimalFormat("##.00");

        agg.entrySet().forEach(e -> {

            if (groupOperation == GroupOperation.MAX) {
                Double v = Collections.max(e.getValue());
                output.put(e.getKey(), Double.parseDouble(f.format(v)));

            } else if (groupOperation == GroupOperation.MIN) {

                double v = Collections.min(e.getValue());
                output.put(e.getKey(), Double.parseDouble(f.format(v)));

            } else if (groupOperation == GroupOperation.MEAN) {

                double v = e.getValue().stream().mapToDouble(Double::doubleValue).average().getAsDouble();
                output.put(e.getKey(), Double.parseDouble(f.format(v)));

            } else if (groupOperation == GroupOperation.SUM) {

                double v = e.getValue().stream().mapToDouble(Double::doubleValue).sum();
                output.put(e.getKey(), Double.parseDouble(f.format(v)));

            } else if (groupOperation == GroupOperation.MEDIAN) {

                // scriptEngine.eval("math.evaluate")

            } else if (groupOperation == GroupOperation.STANDARD_DEVIATION) {

            } else if (groupOperation == GroupOperation.VARIANCE) {

            }
        });
        return output;
    }

}