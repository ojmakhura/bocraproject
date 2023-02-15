// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by SpringWSImpl.java.vsl in andromda-webservices.
//
package bw.org.bocra.portal.complaint;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import javax.persistence.EntityNotFoundException;

import org.postgresql.util.PSQLException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.nimbusds.jose.shaded.json.JSONObject;
import com.nimbusds.jose.util.JSONArrayUtils;

import bw.org.bocra.portal.properties.RabbitProperties;
import io.micrometer.core.instrument.util.StringUtils;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/complaint")
@CrossOrigin()
@Tag(name = "Complaint", description = "Managing the complaints.")
public class ComplaintRestControllerImpl extends ComplaintRestControllerBase {

    @Value("${bocra.comm.url}")
    private String commUrl;

    @Value("${bocra.web.url}")
    private String webUrl;

    private final RestTemplate restTemplate;
    private final RabbitTemplate rabbitTemplate;
    private final RabbitProperties rabbitProperties;

    public ComplaintRestControllerImpl(ComplaintService complaintService, RabbitTemplate rabbitTemplate, RabbitProperties rabbitProperties,
            RestTemplate restTemplate) {

        super(complaintService);
        this.restTemplate = restTemplate;
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitProperties = rabbitProperties;
    }

    @Override
    public ResponseEntity<?> handleFindById(Long id) {
        try {
            logger.debug("Searches for a Complaint by " + id);
            Optional<?> data = Optional.of(complaintService.findById(id)); // TODO: Add custom code here;
            ResponseEntity<?> response;

            if (data.isPresent()) {
                response = ResponseEntity.ok().body(data.get());
            } else {
                response = ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(String.format("Complaint with id %ld not found.", id));
            }

            return response;
        } catch (Exception e) {
            String message = e.getMessage();
            if (e instanceof NoSuchElementException || e.getCause() instanceof NoSuchElementException
                    || e instanceof EntityNotFoundException || e.getCause() instanceof EntityNotFoundException) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(String.format("Complaint with id %d not found.", id));
            } else {
                message = "An unknown error has occured. Please contact the system administrator.";
            }

            logger.error(message, e);
            return ResponseEntity.badRequest().body(message);
        }
    }

    @Override
    public ResponseEntity<?> handleGetAllPaged(Integer pageNumber, Integer pageSize) {
        try {
            logger.debug("Displays all Complaints of the specified " + "Page number" + pageNumber + " and Page size "
                    + pageSize);
            return ResponseEntity.ok().body(complaintService.getAll(pageNumber, pageSize));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.badRequest()
                    .body("An unknown error has occured. Please contact the system administrator.");
        }
    }

    @Override
    public ResponseEntity<?> handleRemove(Long id) {
        try {
            logger.debug("Deletes a Complaint by Id" + id);
            Optional<?> data = Optional.of(complaintService.remove(id)); // TODO: Add custom code here;
            ResponseEntity<?> response;

            if (data.isPresent()) {
                response = ResponseEntity.ok().body(data.get());
            } else {
                response = ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Failed to delete the complaint with id " + id);
            }

            return response;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);

            if (e instanceof EmptyResultDataAccessException || e.getCause() instanceof EmptyResultDataAccessException) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Could not delete compaint with id " + id);
            }

            return ResponseEntity.badRequest().body("Unknown error encountered when deleting complaint with id " + id);
        }
    }

    @Override
    public ResponseEntity<?> handleSave(ComplaintVO complaint) {
        try {
            logger.debug("Save Complaint " + complaint);

            if (complaint.getId() == null) {
                if (SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken) {
                    complaint.setCreatedDate(LocalDateTime.now());
                }
            } else {
                if (SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken) {
                    complaint.setUpdatedDate(LocalDateTime.now());
                }
            }

            complaint = complaintService.save(complaint); // TODO: Add custom code here;
            ResponseEntity<?> response;

            if (complaint != null && complaint.getId() != null) {
                response = ResponseEntity.ok().body(complaint);

                System.out.println(complaint);

                String emailTempate = """
                        Dear %s

                        We acknowledge receipt of your complaint against %s and will get back
                        to you as soon as possible. Please note that to access your
                        complaint, go the the url %s.

                        Regards,

                        BOCRA Complaint Management Team
                        """;

                String complaintUrl = webUrl + "/complaint/edit-complaint?complaintId=" + complaint.getComplaintId();

                String subject = String.format("Complaint against %s.", complaint.getLicensee().getLicenseeName());
                String text = String.format(
                        emailTempate,
                        complaint.getFirstName() + " " + complaint.getSurname(),
                        complaint.getLicensee().getLicenseeName(),
                        complaintUrl);

                this.sendComplaintMessage(complaint, subject, List.of(complaint.getEmail()), text,
                        complaint.getFirstName() + " " + complaint.getSurname());

            } else {
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("Could not save the complaint.");
            }

            return response;
        } catch (IllegalArgumentException | ComplaintServiceException e) {

            e.printStackTrace();

            String message = e.getMessage();

            if (e instanceof IllegalArgumentException || e.getCause() instanceof IllegalArgumentException) {

                if (message.contains("'complaint'")) {

                    message = "The complaint information is missing.";

                } else if (message.contains("'licensee' or its id can not be null")
                        || message.contains("'complaint.licensee' can not be null")) {

                    message = "The licensee or its id is missing.";

                } else if (message.contains("'complaintType' or its id can not be null")
                        || message.contains("'complaint.complaintType' can not be null")) {

                    message = "The complaint type or its id is missing.";

                } else if (message.contains("'complaint.status'")) {

                    message = "The complaint status is missing.";

                } else if (message.contains("'complaint.firstName'")) {

                    message = "The complaint first name is missing.";

                } else if (message.contains("'complaint.surname'")) {

                    message = "The complaint surname is missing.";

                } else if (message.contains("'complaint.email'")) {

                    message = "The complaint email is missing.";

                } else if (message.contains("'complaint.subject'")) {

                    message = "The complaint subject is missing.";

                } else if (message.contains("'complaint.details'")) {

                    message = "The complaint details is missing.";

                } else if (message.contains("'complaint.createdDate'")) {

                    message = "The complaint created date is missing.";

                } else {
                    message = "An unknown error has occured. Please contact the system administrator.";
                }

                return ResponseEntity.badRequest().body(message);

            } else if (e.getCause() instanceof PSQLException) {

                if (e.getCause().getMessage().contains("duplicate key")) {
                    if (e.getCause().getMessage().contains("(complaint_id)")) {

                        return ResponseEntity.badRequest().body("An complaint with this id has been already created.");
                    }

                } else if (e.getCause().getMessage().contains("null value in column")) {
                    if (e.getCause().getMessage().contains("column \"created_by\"")) {
                        return ResponseEntity.badRequest().body("The created-by value is missing.");
                    } else if (e.getCause().getMessage().contains("column \"created_date\"")) {
                        return ResponseEntity.badRequest().body("The created date value is missing.");
                    }
                }

                return ResponseEntity.badRequest().body("This complaint is conflicting with an existing one.");
            }

            return ResponseEntity.badRequest()
                    .body("An unknown database error has occured. Please contact the portal administrator.");
        } catch (Exception e) {

            e.printStackTrace();
            // e.getCause().printStackTrace();
            return ResponseEntity.badRequest()
                    .body("An unknown error has occured. Please contact the portal administrator.");
        }
    }

    @Override
    public ResponseEntity<?> handleSearch(ComplaintSeachCriteria criteria) {

        try {
            logger.debug("Searchs for a Complaint");
            return ResponseEntity.ok().body(complaintService.search(criteria));

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResponseEntity.badRequest()
                    .body("An unknown error has occured. Please contact the portal administrator.");
        }
    }

    // private String getAuthToken() {
    // if(SecurityContextHolder.getContext().getAuthentication() instanceof
    // AnonymousAuthenticationToken) {
    // System.out.println(keycloakProperties);
    // Keycloak keycloak = KeycloakBuilder.builder()
    // .serverUrl(keycloakProperties.getAuthServerUrl())
    // .realm( keycloakProperties.getRealm() )
    // .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
    // .clientId(keycloakProperties.getResource())
    // .clientSecret(clientSecret)
    // .resteasyClient(new ResteasyClientBuilderImpl()
    // .connectionPoolSize(100)
    // .build()
    // ) //
    // .build();

    // return keycloak.tokenManager().getAccessToken().getToken();
    // } else {
    // return keycloakService.getSecurityContext().getTokenString();

    // }
    // }

    public void sendComplaintMessage(ComplaintVO complaint, String subject, List<String> destinations, String text,
            String user) {
        JSONObject messageObj = new JSONObject();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        messageObj.put("createdBy", user);
        messageObj.put("createdDate", format.format(LocalDateTime.now()));
        messageObj.put("sendNow", Boolean.TRUE);
        messageObj.put("dispatchDate", format.format(LocalDate.now().atStartOfDay()));
        messageObj.put("messagePlatform", "EMAIL");
        messageObj.put("status", "DRAFT");
        messageObj.put("subject", subject);
        messageObj.put("text", text);

        messageObj.put("destinations", destinations);
        List<Object> messageObjects = JSONArrayUtils.newJSONArray();
        messageObjects.add(messageObj);

        rabbitTemplate.convertAndSend(rabbitProperties.getEmailQueueExchange(), rabbitProperties.getEmailQueueRoutingKey(), messageObjects);
    }

    @Override
    public ResponseEntity<?> handleAddComplaintReply(String complaintId, ComplaintReplyVO reply) {
        try {
            logger.debug("Reply Complaint with Complaint Id:" + complaintId);

            ComplaintReplyVO added = complaintService.addComplaintReply(complaintId, reply);
            ResponseEntity<?> response;

            // if (data.isPresent()) {
            response = ResponseEntity.ok().body(added);
            // } else {
            // response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            // }

            JSONObject healthStatus = restTemplate.getForObject(commUrl + "/actuator/health", JSONObject.class);

            if (reply.getId() == null && healthStatus.get("status").toString().equals("UP")) {
                String emailTempate = """
                        Dear %s

                        Your complaint %s against %s has a new reply. Go to the URL
                        %s to view the reply and respond.

                        Regards,

                        BOCRA Complaint Management Team
                        """;

                ComplaintVO complaint = complaintService.findByComplaintId(complaintId);

                String complaintUrl = webUrl + "/complaint/edit-complaint?complaintId=" + complaint.getComplaintId();
                System.out.println(1);
                String text = String.format(
                        emailTempate,
                        complaint.getFirstName() + " " + complaint.getSurname(),
                        complaint.getComplaintId(),
                        complaint.getLicensee().getLicenseeName(),
                        complaintUrl);
                System.out.println(2);

                this.sendComplaintMessage(complaint, "Complait reply received.", List.of(complaint.getEmail()), text,
                        reply.getReplyUser());
            }

            return response;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.badRequest()
                    .body("Unable to add the reply to this complaint. Please contact administrator.");
        }

    }

    @Override
    public ResponseEntity<?> handleRemoveComplaintReply(Long id) {
        try {
            logger.debug("Deletes a Complaint Reply with Id" + id);
            Optional<?> data = Optional.of(complaintService.removeComplaintReply(id)); // TODO: Add custom code here;
            ResponseEntity<?> response;

            response = ResponseEntity.ok().body(data.get());

            return response;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.badRequest()
                    .body("Unable to remove the reply from this complaint. Please contact administrator.");
        }
    }

    @Override
    public ResponseEntity<?> handleFindByComplaintId(String complaintId) {

        if (StringUtils.isBlank(complaintId)) {
            return ResponseEntity.badRequest().body("Complaint ID should not be empty.");
        }

        try {
            logger.debug("Searches for a Complaint assigned by " + complaintId);
            Optional<?> data = Optional.of(complaintService.findByComplaintId(complaintId)); // TODO: Add custom code
                                                                                             // here;
            return ResponseEntity.ok().body(data.get());
        } catch (Exception e) {
            String message = e.getMessage();
            if (e instanceof NoSuchElementException || e.getCause() instanceof NoSuchElementException
                    || e instanceof EntityNotFoundException || e.getCause() instanceof EntityNotFoundException) {

                message = String.format("Complaint with complaint id %s not found.", complaintId);

            } else if (e instanceof ComplaintServiceException || e.getCause() instanceof ComplaintServiceException) {
                message = String.format("Complaint with complaint id %s not found.", complaintId);
            } else {
                message = "An unknown error has occured. Please contact the system administrator.";
            }

            logger.error(message, e);
            return ResponseEntity.badRequest().body(message);
        }
    }

    @Override
    public ResponseEntity<?> handleAddDocument(Long id, Long documentTypeId, MultipartFile file, String fileName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResponseEntity<?> handleFindByIds(Set<Long> ids) {
        // TODO Auto-generated method stub
        return null;
    }
}