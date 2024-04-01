// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by SpringWSImpl.java.vsl in andromda-webservices.
//
package bw.org.bocra.portal.form.submission;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.postgresql.util.PSQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nimbusds.jose.shaded.json.JSONObject;
import com.nimbusds.jose.util.JSONArrayUtils;

import bw.org.bocra.portal.access.AccessPointCriteria;
import bw.org.bocra.portal.config.SystemConfigName;
import bw.org.bocra.portal.config.SystemConfigService;
import bw.org.bocra.portal.config.SystemConfigVO;
import bw.org.bocra.portal.form.submission.data.DataFieldVO;
import bw.org.bocra.portal.keycloak.KeycloakUserService;
import bw.org.bocra.portal.properties.RabbitProperties;
import bw.org.bocra.portal.user.UserVO;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/form/submission")
@Tag(name = "Form Submission", description = "Managing form submission.")
@CrossOrigin()
public class SubmissionRestControllerImpl extends SubmissionRestControllerBase {

    protected static Logger logger = LoggerFactory.getLogger(SubmissionRestControllerImpl.class);
    private final KeycloakUserService keycloakUserService;
    private final SystemConfigService systemConfigService;
    private final RabbitTemplate rabbitTemplate;
    private final RabbitProperties rabbitProperties;

    @Value("${email.submission.upload}")
    private String emailSbmissionUpload;

    @Value("${bocra.web.url}")
    private String webUrl;

    @Value("${bocra.comm.url}")
    private String commUrl;

    public SubmissionRestControllerImpl(SubmissionService submissionService, KeycloakUserService keycloakUserService, RabbitTemplate rabbitTemplate, SystemConfigService systemConfigService, RabbitProperties rabbitProperties) {
        super(submissionService);
        this.keycloakUserService = keycloakUserService;
        this.rabbitTemplate = rabbitTemplate;
        this.systemConfigService = systemConfigService;
        this.rabbitProperties = rabbitProperties;
    }

    @Override
    public ResponseEntity<?> handleFindById(Long id) {
        try {
            logger.debug("Searches for form submission using ID " + id);
            Optional<?> data = Optional.of(submissionService.findById(id));
            ResponseEntity<?> response;

            if (data.isPresent()) {
                response = ResponseEntity.ok().body(data.get());
            } else {
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("Form submission with id %ld not found.", id));
            }

            return response;
        } catch (Exception e) {
            logger.error(e.getMessage());
            String message = e.getMessage();
            if (e instanceof NoSuchElementException || e.getCause() instanceof NoSuchElementException
                    || e instanceof EntityNotFoundException || e.getCause() instanceof EntityNotFoundException) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("Form submission with id %d not found.", id));
            } else {
                message = "An unknown error has occured. Please contact the system administrator.";
            }

            logger.error(message);
            return ResponseEntity.badRequest().body(message);
        }
    }

    @Override
    public ResponseEntity<?> handleGetAll() {
        try{
            logger.debug("Display all Form Submissions");
            return ResponseEntity.ok().body(submissionService.getAll());
            
        } catch (Exception e) {
            // e.printStackTrace();
            logger.error(e.getMessage(), e);
            return ResponseEntity.badRequest().body("An unknown error has occured. Please contact the system administrator.");
        }
    }

    @Override
    public ResponseEntity<?> handleGetAllPaged(Integer pageNumber, Integer pageSize) {
        try{
            logger.debug("Display all Form Submissions of the specified "+"Page number "+pageNumber+" and Page size "+pageSize);
            
            return ResponseEntity.ok().body(submissionService.getAll(pageNumber, pageSize));
    
        } catch (Exception e) {
            // e.printStackTrace();
            logger.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("An unknown error has occured. Please contact the system administrator.");
        }
    }

    @Override
    public ResponseEntity<?> handleRemove(Long id) {
        try{
            logger.debug("Deletes Form Submission by "+id);
            Optional<Boolean> data = Optional.of(submissionService.remove(id));
            ResponseEntity<?> response;
    
            if(data.isPresent()) {
                response = ResponseEntity.ok().body(data.get());
            } else {
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("Failed to delete the form submission with id " + id);
            }
    
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);

            if(e instanceof EmptyResultDataAccessException || e.getCause() instanceof EmptyResultDataAccessException) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Could not delete form submission with id " + id);
            }

            return ResponseEntity.badRequest().body("Unknown error encountered when deleting form submission with id " + id);
        }
    }

    @Override
    public ResponseEntity<?> handleSave(FormSubmissionVO formSubmission) {
        try{
            logger.debug("Save Form Submisson " + formSubmission);
            Optional<FormSubmissionVO> data = Optional.of(submissionService.save(formSubmission));
            ResponseEntity<?> response;
    
            if(data.isPresent()) {
                response = ResponseEntity.ok().body(data.get());
            } else {
                response = ResponseEntity.badRequest().body("Could not save form submission.");
            }
    
            return response;
        } catch (IllegalArgumentException | SubmissionServiceException e) {

            e.printStackTrace();

            String message = e.getMessage();

            if(e instanceof IllegalArgumentException || e.getCause() instanceof IllegalArgumentException) {

                if(message.contains("'formSubmission'")) {

                    message = "The submission information is missing.";

                } else if(message.contains("can not be null")) {
                    if(message.contains("'formSubmission.form' can not be null")) {
                
                        message = "The submission form or its id is missing.";
                    } else if(message.contains("'formSubmission.formActivation' can not be null")) {
                
                        message = "The submission form activation or its id is missing.";
                    } else if(message.contains("'formSubmission.period' can not be null")) {
                
                        message = "The submission period or its id is missing.";
                    } else {
                        message = "One of the required attributes is null.";
                    }                    
                
                } else if(message.contains("'formSubmission.submissionStatus'")) {
                
                    message = "The submission status is missing.";
                
                } else {
                    message = "An unknown error has occured. Please contact the system administrator.";
                }

                return ResponseEntity.badRequest().body(message);

            } else if(e.getCause() instanceof PSQLException) {

                if (e.getCause().getMessage().contains("duplicate key")) {
                    if(e.getCause().getMessage().contains("(form_submission_unique)")) {

                        return ResponseEntity.badRequest().body("An submission for this has been already created.");
                    }  else {
                        message = "One of the unique attributes is duplicated.";
                    } 
                    
                } else if (e.getCause().getMessage().contains("null value in column")) {
                    if (e.getCause().getMessage().contains("column \"created_by\"")) {
                        return ResponseEntity.badRequest().body("The created-by value is missing.");
                    } else if (e.getCause().getMessage().contains("column \"created_date\"")) {
                        return ResponseEntity.badRequest().body("The created date value is missing.");
                    } else {
                        message = "One of the required columns is null.";
                    } 
                }
                
                return ResponseEntity.badRequest().body("An unknown database error has occured. Please contact the portal administrator.");
            } 

            return ResponseEntity.badRequest().body("An unknown error has occured. Please contact the portal administrator.");
        } catch(Exception e) {

            e.printStackTrace();
            logger.error(e.getMessage(), e);
            return ResponseEntity.badRequest().body("An unknown error has occured. Please contact the portal administrator.");
        }
    }

    @Override
    public ResponseEntity<?> handleSearch(FormSubmissionCriteria criteria) {
        try{
            logger.debug("Search Form Submission by " + criteria);

            UserVO user = keycloakUserService.getLoggedInUser();

            if(user.getLicensee() != null && user.getLicensee().getId() != null) {
                criteria.setLicenseeId(user.getLicensee().getId());
            }

            return ResponseEntity.ok().body(submissionService.search(criteria));
            
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().body("An unknown error has occured. Please contact the portal administrator.");
        }
    }

    @Override
    public ResponseEntity<?> handlePagedSearch(Integer pageNumber, Integer pageSize, FormSubmissionCriteria criteria) {
        try {
            logger.debug("Searches for an form submission of the specified Page Number: " + pageNumber + ", Page Size: " + pageSize + " and Criteria: " +criteria);


            UserVO user = keycloakUserService.getLoggedInUser();

            if(user.getLicensee() != null && user.getLicensee().getId() != null) {
                criteria.setLicenseeId(user.getLicensee().getId());
            }

            return ResponseEntity.ok().body(submissionService.search(pageNumber, pageSize, criteria));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
            String message = String.format("An error occurred when reading page %d of size %d.", pageNumber, pageSize);
            return ResponseEntity.badRequest().body(message);
        }
    }

    @Override
    public ResponseEntity<?> handleAddDataField(DataFieldVO dataField) {
        try{
            logger.debug("Adds Data Field " + dataField);
            Optional<DataFieldVO> data = Optional.of(submissionService.addDataField(dataField));
            ResponseEntity<?> response;
    
            if(data.isPresent()) {
                response = ResponseEntity.ok().body(data.get());
            } else {
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("Could not add a data field to the submission.");
            }
    
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().body("An unknown error has occured. Please contact the portal administrator.");
        }
    }

    @Override
    public ResponseEntity<?> handleAddDataFields(Set<DataFieldVO> dataFields) {
        try{
            logger.debug("Adds Data Fields "+dataFields);
            Optional<Collection<DataFieldVO>> data = Optional.of(submissionService.addDataFields(dataFields));
            ResponseEntity<?> response;
    
            if(data.isPresent()) {
                response = ResponseEntity.ok().body(data.get());
            } else {
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("Could not add data fields to the submission.");
            }
    
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().body("An unknown error has occured. Please contact the portal administrator.");
        }
    }

    @Override
    public ResponseEntity<?> handleDeleteDataField(Long id) {
        try{
            logger.debug("Deletes Data Field by "+id);
            Optional<Boolean> data = Optional.of(submissionService.deleteDataField(id));
            ResponseEntity<?> response;
    
            if(data.isPresent()) {
                response = ResponseEntity.ok().body(data.get());
            } else {
                response = ResponseEntity.badRequest().body("Failed to delete the data field with id " + id);
            }
    
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);

            if(e instanceof EmptyResultDataAccessException || e.getCause() instanceof EmptyResultDataAccessException) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Could not delete data field with id " + id);
            }

            return ResponseEntity.badRequest().body("An unknown error has occured. Please contact the portal administrator.");
        }
    }

    @Override
    public ResponseEntity<?> handleGetSubmissionSummary(FormSubmissionCriteria criteria) {
        try{
            logger.debug("Display Submission Summary by "+criteria);
            UserVO user = keycloakUserService.getLoggedInUser();

            if(user.getLicensee() != null && user.getLicensee().getId() != null) {
                criteria.setLicenseeId(user.getLicensee().getId());
            }

            SubmissionSummary data = submissionService.getSubmissionSummary(criteria);
            ResponseEntity<SubmissionSummary> response;
    
            response = ResponseEntity.ok().body(data);
    
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().body("An unknown error has occured. Please contact the portal administrator.");
        }
    }

    @Override
    public ResponseEntity<?> handleFindByIds(Set<Long> ids, Boolean loadData) {
        try {

            Collection<FormSubmissionVO> submissions = submissionService.findByIds(ids, loadData);
            return ResponseEntity.ok(submissions);

        } catch(Exception e) {

            e.printStackTrace();
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().body("An unknown error has occured. Please contact the portal administrator.");
        }
        
    }

    @Override
    public ResponseEntity<?> handleUpdateSubmissionStatus(Long id, FormSubmissionStatus submissionStatus, final LocalDateTime updateTime, final String username) {
        try {

            Boolean updated = submissionService.updateSubmissionStatus(id, submissionStatus, updateTime, username);
            return ResponseEntity.ok(updated);

        } catch(Exception e) {

            e.printStackTrace();
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().body("An unknown error has occured. Please contact the portal administrator.");
        }
    }

    @Override
    public ResponseEntity<?> handleLoadDueSubmissions() {
        try {

            Collection<FormSubmissionVO> dueSubmissions = submissionService.loadDueSubmissions();
            return ResponseEntity.ok(dueSubmissions);

        } catch(Exception e) {

            e.printStackTrace();
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().body("An unknown error has occured. Please contact the portal administrator.");
        }
    }

    @Override
    public ResponseEntity<?> handleCheckOverdueSubmissions() {
        
        try {

            Collection<FormSubmissionVO> overdue = submissionService.checkOverdueSubmissions();
            return ResponseEntity.ok(overdue);

        } catch(Exception e) {

            e.printStackTrace();
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().body("An unknown error has occured. Please contact the portal administrator.");
        }
    }

    @Override
    public ResponseEntity<?> handleCreateNewSubmissions(Set<Long> licenseeIds, Long activationId) {

        if(activationId == null) {
            return ResponseEntity.badRequest().body("Form activation should not be null.");
        }

        if(CollectionUtils.isEmpty(licenseeIds)) {
            return ResponseEntity.badRequest().body("Licensee IDs should not be empty.");
        }

        try {

            return ResponseEntity.ok(submissionService.createNewSubmissions(licenseeIds, activationId));

        } catch(Exception e) {

            e.printStackTrace();
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().body("An unknown error has occured. Please contact the portal administrator.");
        }
    }

    private final String uploadTemplate = """
            Dear %s,

            Your file upload for %s has been completed. To view the uploaded data
            please go to the following link: %s

            Regards

            BOCRA Team
            """;

    @Override
    public ResponseEntity<?> handleUploadData(Long submissonId, MultipartFile file, Boolean sendEmail) {
        
        try {
            logger.info("Submission {}", submissonId);
            logger.info("File name {} of size {}.", file.getOriginalFilename(), file.getSize());
            FormSubmissionVO submission = submissionService.uploadData(submissonId, file);

            if(sendEmail != null && sendEmail) {

                List<Object> messageObjects = JSONArrayUtils.newJSONArray();
                DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                SystemConfigVO config = systemConfigService.findByName(SystemConfigName.ACTIVATION_SUBMISSION_TEMPLATE);
                String submissionUrl = webUrl + "/form/submission/edit-form-submission";
    
                JSONObject messageObj = new JSONObject();
                
                messageObj.put("createdBy", keycloakUserService.getLoggedInUser().getUsername());
                messageObj.put("createdDate", format.format(LocalDateTime.now()));
                messageObj.put("sendNow", Boolean.TRUE);
                messageObj.put("dispatchDate", format.format(LocalDateTime.now()));
                messageObj.put("messagePlatform", "EMAIL");
                messageObj.put("status", "DRAFT");
                messageObj.put("subject", String.format("Data for %s has been uploaded.", submission.getFormActivation().getActivationName()));
                messageObj.put("text", String.format(
                    config != null && StringUtils.isNotBlank(config.getValue()) ? config.getValue() : uploadTemplate,
                    keycloakUserService.getLoggedInUser().getUsername(),
                    submission.getFormActivation().getActivationName(),
                    submissionUrl + "?id=" + submission.getId()
                ));
                
                messageObj.put("destinations", List.of(keycloakUserService.getLoggedInUser().getEmail()));
    
                messageObjects.add(messageObj);
                logger.info("Sending email to {}.", submission.getFormActivation().getActivationName());
                rabbitTemplate.convertAndSend(rabbitProperties.getEmailQueueExchange(), rabbitProperties.getEmailQueueRoutingKey(), messageObjects);
            }

            return ResponseEntity.ok(submission);

        } catch(Exception e) {

            e.printStackTrace();
            logger.error(e.getMessage());
            logger.error(e.getCause().getMessage());

            if(e instanceof SubmissionServiceException) {
                return ResponseEntity.badRequest().body(e.getCause().getMessage());
            }

            return ResponseEntity.badRequest().body("An unknown error has occured. Please contact the portal administrator.");
        }
    }

    @Override
    public ResponseEntity<?> handleGetSubmissionData(Long submissionId, Integer pageNumber, Integer pageSize) {

        try{

            return ResponseEntity.ok(submissionService.getSubmissionData(submissionId, pageNumber, pageSize));

        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().body("An unknown error has occured. Please contact the portal administrator.");
        }
    }

    @Override
    public ResponseEntity<?> handlePreProcessedFindById(MultipleEntryFormFilter filters) {

        logger.info("Filters ids {}", filters.getIds());
        logger.info("Filters groupBy {}", filters.getGroupBy());
        logger.info("Filters orderby {}", filters.getOrderBy());
        
        try {

            Collection<FormSubmissionVO> submissions = submissionService.preProcessedFindById(filters);

            return ResponseEntity.ok(submissions);

        } catch(Exception e) {

            e.printStackTrace();
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().body("An unknown error has occured. Please contact the portal administrator.");
        }
    }

    @Override
    public ResponseEntity<?> handleDownloadSubmission(Long id) {

        logger.info("Download submission {}", id);

        try {

            // FormSubmissionVO submission = submissionService.findById(id);

            return ResponseEntity.ok(submissionService.downloadSubmission(id));

        } catch(Exception e) {

            e.printStackTrace();
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().body("An unknown error has occured. Please contact the portal administrator.");
        }
    }
}