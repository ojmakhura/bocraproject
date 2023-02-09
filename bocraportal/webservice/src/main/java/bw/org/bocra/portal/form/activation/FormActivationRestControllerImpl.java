// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by SpringWSImpl.java.vsl in andromda-webservices.
//
package bw.org.bocra.portal.form.activation;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.apache.commons.collections4.CollectionUtils;
import org.postgresql.util.PSQLException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.nimbusds.jose.shaded.json.JSONObject;
import com.nimbusds.jose.util.JSONArrayUtils;

import bw.org.bocra.portal.form.FormService;
import bw.org.bocra.portal.form.FormVO;
import bw.org.bocra.portal.form.submission.FormSubmissionVO;
import bw.org.bocra.portal.form.submission.SubmissionService;
import bw.org.bocra.portal.keycloak.KeycloakService;
import bw.org.bocra.portal.keycloak.KeycloakUserService;
import bw.org.bocra.portal.licensee.LicenseeStatus;
import bw.org.bocra.portal.licensee.form.LicenseeFormVO;
import bw.org.bocra.portal.licensee.sector.LicenseeSectorService;
import bw.org.bocra.portal.licensee.sector.LicenseeSectorVO;
import bw.org.bocra.portal.properties.RabbitProperties;
import bw.org.bocra.portal.sector.form.SectorFormVO;
import bw.org.bocra.portal.user.UserVO;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/form/activation")
@Tag(name = "Form Activation", description = "Managing form activation within specified period.")
@CrossOrigin()
public class FormActivationRestControllerImpl extends FormActivationRestControllerBase {

    private final SubmissionService submissionService;
    private final FormService formService;
    private final LicenseeSectorService licenseeSectorService;
    private final KeycloakUserService keycloakUserService;
    private final KeycloakService keycloakService;
    private final RabbitTemplate rabbitTemplate;
    private final RabbitProperties rabbitProperties;

    @Value("${bocra.web.url}")
    private String webUrl;

    @Value("${bocra.comm.url}")
    private String commUrl;
    
    public FormActivationRestControllerImpl(FormActivationService formActivationService, RabbitTemplate rabbitTemplate, RabbitProperties rabbitProperties,
            SubmissionService submissionService, FormService formService, LicenseeSectorService licenseeSectorService,
            KeycloakUserService keycloakUserService, KeycloakService keycloakService) {

        super(formActivationService);
        this.submissionService = submissionService;
        this.formService = formService;
        this.licenseeSectorService = licenseeSectorService;
        this.keycloakUserService = keycloakUserService;
        this.keycloakService = keycloakService;
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitProperties = rabbitProperties;

    }

    @Override
    public ResponseEntity<?> handleFindById(Long id) {
        try {
            logger.debug("Searches Form Activation by " + id);
            Optional<?> data = Optional.of(formActivationService.findById(id));
            ResponseEntity<?> response;

            if (data.isPresent()) {
                response = ResponseEntity.ok().body(data.get());
            } else {
                response = ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(String.format("Form activation with id %ld not found.", id));
            }

            return response;
        } catch (Exception e) {
            e.printStackTrace();

            String message = e.getMessage();
            if (e instanceof NoSuchElementException || e.getCause() instanceof NoSuchElementException
                    || e instanceof EntityNotFoundException || e.getCause() instanceof EntityNotFoundException) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(String.format("Form activation with id %d not found.", id));
            } else {
                message = "An unknown error has occured. Please contact the system administrator.";
            }

            logger.error(message);
            return ResponseEntity.badRequest().body(message);
        }
    }

    @Override
    public ResponseEntity<?> handleGetAll() {
        try {
            logger.debug("Displays all Form Activations");
            return ResponseEntity.ok().body(formActivationService.getAll());

        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return ResponseEntity.badRequest()
                    .body("An unknown error has occured. Please contact the system administrator.");
        }
    }

    @Override
    public ResponseEntity<?> handleGetAllPaged(Integer pageNumber, Integer pageSize) {
        try {
            logger.debug("Displays all Form Activations of the specified " + "Page Number:" + pageNumber
                    + "and Page Size:" + pageSize);
            return ResponseEntity.ok().body(formActivationService.getAll(pageNumber, pageSize));

        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return ResponseEntity.badRequest()
                    .body("An unknown error has occured. Please contact the system administrator.");
        }
    }

    @Override
    public ResponseEntity<?> handleRemove(Long id) {
        try {
            logger.debug("Deletes form activation by ID " + id);
            boolean rm = formActivationService.remove(id);
            ResponseEntity<?> response;

            if (rm) {
                response = ResponseEntity.ok().body(rm);
            } else {
                response = ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Failed to delete the form activation with id " + id);
            }

            return response;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());

            if (e instanceof EmptyResultDataAccessException || e.getCause() instanceof EmptyResultDataAccessException) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Could not delete form activation with id " + id);
            }

            return ResponseEntity.badRequest()
                    .body("Unknown error encountered when deleting form activation with id " + id);
        }
    }

    private Set<Long> getLicenseeIds(FormVO form) {

        Set<Long> ids = new HashSet<>();

        for (LicenseeFormVO licensee : form.getLicensees()) {
            if (licensee.getLicensee().getStatus() == LicenseeStatus.ACTIVE)
                ids.add(licensee.getLicensee().getId());
        }

        for (SectorFormVO sectorForm : form.getSectors()) {

            for (LicenseeSectorVO licensee : licenseeSectorService.findBySector(sectorForm.getSector().getId())) {
                if (licensee.getLicensee().getStatus() == LicenseeStatus.ACTIVE)
                    ids.add(licensee.getLicensee().getId());
            }
        }

        return ids;
    }

    private String emailTempate = "Dear %s user\n\n"
                    + "You are notified that BOCRA requests your participation to\n"
                    + "provide %s data. Please go to %s?id=%d to fill out the form.\n\n"
                    + "The deadline to submit the information is %s\n\n"
                    + "Kind Regards\n\n"
                    + "BOCRA";

    public void sendNotifications(FormActivationVO formActivation) {
        
        String submissionUrl = webUrl + "/form/submission/edit-form-submission";
        List<Object> messageObjects = JSONArrayUtils.newJSONArray();
        
        for (FormSubmissionVO submission : formActivation.getFormSubmissions()) {

            Collection<UserVO> users = keycloakUserService.getLicenseeUsers(submission.getLicensee().getId());
            Collection<String> userEmails = users.stream().map(user -> user.getEmail()).collect(Collectors.toSet());

            if(CollectionUtils.isEmpty(userEmails)) {
                continue;
            }

            JSONObject messageObj = new JSONObject();
            
            messageObj.put("createdBy", formActivation.getCreatedBy());
            messageObj.put("createdDate", LocalDateTime.now().toString());
            messageObj.put("sendNow", Boolean.TRUE);
            messageObj.put("dispatchDate", formActivation.getPeriod().getPeriodEnd().atStartOfDay().toString());
            messageObj.put("messagePlatform", "EMAIL");
            messageObj.put("status", "DRAFT");
            messageObj.put("subject", String.format("%s data request for period %s.", formActivation.getForm().getFormName(), formActivation.getPeriod().getPeriodName()));
            messageObj.put("text", String.format(
                emailTempate,
                submission.getLicensee().getLicenseeName(),
                formActivation.getForm().getFormName(),
                submissionUrl,
                submission.getId(),
                submission.getExpectedSubmissionDate()
            ));
                
            messageObj.put("destinations", userEmails);

            messageObjects.add(messageObj);
        }

        rabbitTemplate.convertAndSend(rabbitProperties.getEmailQueueExchange(), rabbitProperties.getEmailQueueRoutingKey(), messageObjects);
    }

    @Override
    public ResponseEntity<?> handleSave(FormActivationVO formActivation) {
        try {
            logger.debug("Saves Form Activation " + formActivation);
            boolean fresh = formActivation.getId() == null;

            FormActivationVO activation = formActivationService.save(formActivation);
            ResponseEntity<?> response;

            if (activation.getId() != null) {

                /**
                 * The form activations is a new one so we need to
                 * create form submissions for all the licensees
                 * associated with the form.
                 */

                if (fresh) {
                    FormVO f = formService.findById(activation.getForm().getId());
                    Collection<FormSubmissionVO> submissions = this.submissionService
                            .createNewSubmissions(this.getLicenseeIds(f), activation.getId());

                    activation.setFormSubmissions(submissions);
                    this.sendNotifications(activation);
                }

                response = ResponseEntity.ok().body(activation);
            } else {
                response = ResponseEntity.badRequest().body("Could not save the form activation");
            }

            return response;
        } catch (IllegalArgumentException | FormActivationServiceException e) {

            e.printStackTrace();

            String message = e.getMessage();

            if (e instanceof IllegalArgumentException || e.getCause() instanceof IllegalArgumentException) {

                if (message.contains("'formActivation'")) {

                    message = "The form activation information is missing.";

                } else if (message.contains("or its id can not be null")) {

                    if (message.contains("'formActivation.form'")) {

                        message = "The form activation type or its id is missing.";

                    } else if (message.contains("'formActivation.period'")) {

                        message = "The form activation type or its id is missing.";
                    }

                } else if (message.contains("'formActivation.activationName'")) {

                    message = "The form activation name is missing.";

                } else {
                    message = "An unknown error has occured. Please contact the system administrator.";
                }

                return ResponseEntity.badRequest().body(message);

            } else if (e.getCause() instanceof PSQLException) {

                e.printStackTrace();

                if (e.getCause().getMessage().contains("duplicate key")) {
                    if (e.getCause().getMessage().contains("form_period_unique")) {

                        return ResponseEntity.badRequest()
                                .body("An form activation with this form and period has been already created.");
                    } else if (e.getCause().getMessage().contains("activation_name")) {

                        return ResponseEntity.badRequest()
                                .body("An form activation with this name has been already created.");
                    }

                } else if (e.getCause().getMessage().contains("null value in column")) {
                    if (e.getCause().getMessage().contains("column \"created_by\"")) {
                        return ResponseEntity.badRequest().body("The created-by value is missing.");
                    } else if (e.getCause().getMessage().contains("column \"created_date\"")) {
                        return ResponseEntity.badRequest().body("The created date value is missing.");
                    }
                }

                return ResponseEntity.badRequest()
                        .body("An unknown database error has occured. Please contact the administrator.");
            }

            return ResponseEntity.badRequest()
                    .body("An unknown database error has occured. Please contact the portal administrator.");
        } catch (Exception e) {

            e.printStackTrace();
            logger.error(e.getMessage(), e);
            return ResponseEntity.badRequest()
                    .body("An unknown error has occured. Please contact the portal administrator.");
        }
    }

    @Override
    public ResponseEntity<?> handleSearch(FormActivationCriteria criteria) {
        try {
            logger.debug("Search Form Activation by " + criteria);
            return ResponseEntity.ok().body(formActivationService.search(criteria));

        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return ResponseEntity.badRequest()
                    .body("An unknown error has occured. Please contact the portal administrator.");
        }
    }

    @Override
    public ResponseEntity<?> handleActivateDueForms() {
        try {
            logger.debug("Activating due forms");
            return ResponseEntity.ok().body(formActivationService.activateDueForms());

        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return ResponseEntity.badRequest()
                    .body("An unknown error has occured. Please contact the portal administrator.");
        }
    }

    @Override
    public ResponseEntity<?> handleCreateMissingSubmissions(Long id) {

        if (id == null) {
            return ResponseEntity.badRequest().body("Form activation should not be null.");
        }

        try {
            logger.debug("Creating missing submission for activation " + id);
            return ResponseEntity.ok().body(formActivationService.createMissingSubmissions(id));

        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return ResponseEntity.badRequest()
                    .body("An unknown error has occured. Please contact the portal administrator.");
        }
    }

    @Override
    public ResponseEntity<?> handleRecreateActivationSubmission(Long id) {

        if (id == null) {
            return ResponseEntity.badRequest().body("Form activation should not be null.");
        }

        try {
            logger.debug("Recreating submission for activation " + id);
            return ResponseEntity.ok().body(formActivationService.recreateActivationSubmission(id));

        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return ResponseEntity.badRequest()
                    .body("An unknown error has occured. Please contact the portal administrator.");
        }
    }
}