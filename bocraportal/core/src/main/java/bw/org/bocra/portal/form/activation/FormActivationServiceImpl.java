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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import bw.org.bocra.portal.form.Form;
import bw.org.bocra.portal.form.FormDao;
import bw.org.bocra.portal.form.FormEntryType;
import bw.org.bocra.portal.form.FormRepository;
import bw.org.bocra.portal.form.FormVO;
import bw.org.bocra.portal.form.field.FormField;
import bw.org.bocra.portal.form.submission.FormSubmission;
import bw.org.bocra.portal.form.submission.FormSubmissionDao;
import bw.org.bocra.portal.form.submission.FormSubmissionRepository;
import bw.org.bocra.portal.form.submission.FormSubmissionStatus;
import bw.org.bocra.portal.form.submission.FormSubmissionVO;
import bw.org.bocra.portal.form.submission.data.DataField;
import bw.org.bocra.portal.form.submission.data.DataFieldDao;
import bw.org.bocra.portal.form.submission.data.DataFieldRepository;
import bw.org.bocra.portal.keycloak.KeycloakService;
import bw.org.bocra.portal.keycloak.KeycloakUserService;
import bw.org.bocra.portal.licence.type.form.LicenceTypeForm;
import bw.org.bocra.portal.licensee.Licensee;
import bw.org.bocra.portal.licensee.LicenseeVO;
import bw.org.bocra.portal.licensee.form.LicenseeForm;
import bw.org.bocra.portal.licensee.sector.LicenseeSector;
import bw.org.bocra.portal.message.CommunicationMessage;
import bw.org.bocra.portal.message.CommunicationMessageDao;
import bw.org.bocra.portal.message.CommunicationMessagePlatform;
import bw.org.bocra.portal.message.CommunicationMessageStatus;
import bw.org.bocra.portal.message.CommunicationMessageVO;
import bw.org.bocra.portal.period.Period;
import bw.org.bocra.portal.period.PeriodVO;
import bw.org.bocra.portal.sector.SectorService;
import bw.org.bocra.portal.sector.form.SectorForm;
import bw.org.bocra.portal.sector.form.SectorFormService;
import bw.org.bocra.portal.sector.form.SectorFormVO;
import bw.org.bocra.portal.user.UserVO;

/**
 * @see bw.org.bocra.portal.form.activation.FormActivationService
 */
@Service("formActivationService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class FormActivationServiceImpl
        extends FormActivationServiceBase {

    @Value("${bocra.web.url}")
    private String webUrl;

    private final SectorService sectorService;
    private final SectorFormService sectorFormService;
    private final KeycloakUserService keycloakUserService;
    private final CommunicationMessageDao communicationMessageDao; 

    public FormActivationServiceImpl(FormActivationDao formActivationDao, KeycloakUserService keycloakUserService,
            FormActivationRepository formActivationRepository, FormDao formDao, FormRepository formRepository, CommunicationMessageDao communicationMessageDao,
            FormSubmissionDao formSubmissionDao, FormSubmissionRepository formSubmissionRepository, SectorService sectorService, SectorFormService sectorFormService,
            DataFieldDao dataFieldDao, DataFieldRepository dataFieldRepository, MessageSource messageSource) {

        super(formActivationDao, formActivationRepository, formDao, formRepository, formSubmissionDao,
                formSubmissionRepository,
                dataFieldDao, dataFieldRepository, messageSource);
        // TODO Auto-generated constructor stub
        this.sectorService = sectorService;
        this.sectorFormService = sectorFormService; 
        this.keycloakUserService = keycloakUserService;
        this.communicationMessageDao = communicationMessageDao;
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

        boolean fresh = formActivation.getId() == null;

        FormActivation activation = getFormActivationDao().formActivationVOToEntity(formActivation);

        if(activation.getActivationDeadline() == null) {
            activation.setActivationDeadline(activation.getPeriod().getPeriodEnd());
        }

        activation = formActivationRepository.save(activation);

        /**
         * The form activations is a new one so we need to
         * create form submissions for all the licensees
         * associated with the form.
         */
        if (formActivation.getId() == null) {
            Map<Long, Licensee> lmap = new HashMap<>();

            Form form = activation.getForm();

            for (LicenseeForm licensee : form.getLicenseeForms()) {
                lmap.putIfAbsent(licensee.getLicensee().getId(), licensee.getLicensee());
            }

            for(SectorForm sectorForm : form.getSectorForms()) {
                List<SectorFormVO> sf = (List<SectorFormVO>) sectorFormService.findByForm(form.getId());
                if(sf != null && sf.size() > 0)
                    sf.get(0);
                    
                for(LicenseeSector licensee : sectorForm.getSector().getLicenseeSectors()) {
                    lmap.putIfAbsent(licensee.getLicensee().getId(), licensee.getLicensee());
                }
            }

            formActivation = formActivationDao.toFormActivationVO(activation);
            formActivation.setFormSubmissions(new ArrayList<>());

            Set<LicenseeVO> licensees = new HashSet<>();

            for (Licensee licensee : lmap.values()) {
                FormSubmission submission = FormSubmission.Factory.newInstance();
                submission.setCreatedBy(activation.getCreatedBy());
                submission.setCreatedDate(LocalDateTime.now());
                submission.setForm(form);
                submission.setLicensee(licensee);
                submission.setFormActivation(activation);
                submission.setPeriod(activation.getPeriod());
                submission.setSubmissionStatus(FormSubmissionStatus.NEW);

                submission.setExpectedSubmissionDate(formActivation.getActivationDeadline());

                /**
                 * If the for requires single entry, the we create the data fields
                 */
                if (form.getEntryType() == FormEntryType.SINGLE) {
                    for (FormField field : form.getFormFields()) {
                        DataField dataField = DataField.Factory.newInstance();
                        dataField.setFormSubmission(submission);
                        dataField.setFormField(field);
                        dataField.setValue(field.getDefaultValue());
                        dataField.setRow(0);

                        submission.getDataFields().add(dataField);
                    }
                }
                submission = formSubmissionRepository.save(submission);

                FormSubmissionVO vo = new FormSubmissionVO();
                getFormSubmissionDao().toFormSubmissionVO(submission, vo);
                formActivation.getFormSubmissions().add(vo);
                licensees.add(vo.getLicensee());
            }
        }

        if(fresh) {
            this.scheduleNotifications(formActivation);
        }

        return formActivation;
    }

    private String emailTempate = "Dear %s user\n\n"
                    + "You are notified that BOCRA requests your participation to\n"
                    + "provide %s data. Please go to %s?id=%d to fill out the form.\n\n"
                    + "The deadline to submit the information is %s\n\n"
                    + "Kind Regards\n\n"
                    + "BOCRA";

    private void scheduleNotifications(FormActivationVO formActivation) {

        String submissionUrl = webUrl + "/form/submission/edit-form-submission";

        for (FormSubmissionVO submission : formActivation.getFormSubmissions()) {

            Collection<UserVO> users = keycloakUserService.getLicenseeUsers(submission.getLicensee().getId());
            Collection<String> userEmails = users.stream().map(user -> user.getEmail()).collect(Collectors.toSet());

            if(CollectionUtils.isEmpty(userEmails)) {
                continue;
            }

            CommunicationMessage message = CommunicationMessage.Factory.newInstance();

            message.setCreatedBy(formActivation.getCreatedBy());
            message.setCreatedDate(LocalDateTime.now());
            message.setSendNow(false);
            message.setDispatchDate(formActivation.getPeriod().getPeriodEnd().atStartOfDay());
            message.setMessagePlatform(CommunicationMessagePlatform.EMAIL);
            message.setStatus(CommunicationMessageStatus.DRAFT);
            message.setSubject(String.format("%s data request for period %s.", formActivation.getForm().getFormName(), formActivation.getPeriod().getPeriodName()));
            message.setText(
                String.format(
                    emailTempate,
                    submission.getLicensee().getLicenseeName(),
                    formActivation.getForm().getFormName(),
                    submissionUrl,
                    submission.getId(),
                    submission.getExpectedSubmissionDate()
                )
            );

            message.setDestinations(userEmails);

            communicationMessageDao.create(message);
        }
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

}