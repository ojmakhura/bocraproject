// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by SpringWSImpl.java.vsl in andromda-webservices.
//
package bw.org.bocra.portal.complaint;

import io.swagger.v3.oas.annotations.tags.Tag;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

import org.keycloak.representations.AccessToken;
// import org.keycloak.representations.AccessToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import bw.org.bocra.portal.document.DocumentService;
import bw.org.bocra.portal.keycloak.KeycloakService;
import bw.org.bocra.portal.keycloak.KeycloakUserService;

@RestController
@RequestMapping("/complaint")
@CrossOrigin()
@Tag(name = "Complaint", description = "Managing the complaints.")
public class ComplaintRestControllerImpl extends ComplaintRestControllerBase {

    private final DocumentService documentService;
    private final KeycloakUserService keycloakUserService;
    private final KeycloakService keycloakService;

    public ComplaintRestControllerImpl(ComplaintService complaintService, DocumentService documentService,
            KeycloakUserService keycloakUserService, KeycloakService keycloakService) {

        super(complaintService);
        this.documentService = documentService;
        this.keycloakUserService = keycloakUserService;
        this.keycloakService = keycloakService;

    }

    @Override
    public ResponseEntity<?> handleFindById(Long id) {
        try {
            logger.debug("Searches for a Complaint by " + id);
            Optional<?> data = Optional.of(complaintService.findById(id)); // TODO: Add custom code here;
            ResponseEntity<?> response;

            if (data.isPresent()) {
                response = ResponseEntity.status(HttpStatus.OK).body(data.get());
            } else {
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            return response;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // @Override
    // public ResponseEntity<?> handleGetAll() {
    // try {
    // logger.debug("Displays all Complaints");
    // Optional<?> data = Optional.of(complaintService.getAll()); // TODO: Add
    // custom code here;
    // ResponseEntity<?> response;

    // if (data.isPresent()) {
    // response = ResponseEntity.status(HttpStatus.OK).body(data.get());
    // } else {
    // response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    // }

    // return response;
    // } catch (Exception e) {
    // logger.error(e.getMessage());
    // return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    // }
    // }

    @Override
    public ResponseEntity<?> handleGetAllPaged(Integer pageNumber, Integer pageSize) {
        try {
            logger.debug("Displays all Complaints of the specified " + "Page number" + pageNumber + " and Page size "
                    + pageSize);
            Optional<?> data = Optional.of(complaintService.getAll(pageNumber, pageSize)); // TODO: Add custom code
                                                                                           // here;
            ResponseEntity<?> response;

            if (data.isPresent()) {
                response = ResponseEntity.status(HttpStatus.OK).body(data.get());
            } else {
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            return response;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> handleRemove(Long id) {
        try {
            logger.debug("Deletes a Complaint by Id" + id);
            Optional<?> data = Optional.of(complaintService.remove(id)); // TODO: Add custom code here;
            ResponseEntity<?> response;

            if (data.isPresent()) {
                response = ResponseEntity.status(HttpStatus.OK).body(data.get());
            } else {
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            return response;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> handleSave(ComplaintVO complaint) {
        try {
            logger.debug("Save Complaint " + complaint);
            Optional<?> data = Optional.of(complaintService.save(complaint)); // TODO: Add custom code here;
            ResponseEntity<?> response;

            if (data.isPresent()) {
                response = ResponseEntity.status(HttpStatus.OK).body(data.get());
            } else {
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            return response;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> handleSearch(ComplaintSeachCriteria criteria) {
        try {
            logger.debug("Searchs for a Complaint");
            Optional<?> data = Optional.of(complaintService.search(criteria)); // TODO: Add custom code here;
            ResponseEntity<?> response;

            if (data.isPresent()) {
                response = ResponseEntity.status(HttpStatus.OK).body(data.get());
            } else {
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            return response;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> handleAddComplaintReply(String complaintId, ComplaintReplyVO reply) {
        try {
            logger.debug("Reply Complaint with Complaint Id:" + complaintId);
            Optional<?> data = Optional.of(complaintService.addComplaintReply(complaintId, reply));
            ResponseEntity<?> response;

            if (data.isPresent()) {
                response = ResponseEntity.status(HttpStatus.OK).body(data.get());
            } else {
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return response;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @Override
    public ResponseEntity<?> handleRemoveComplaintReply(Long id) {
        try {
            logger.debug("Deletes a Complaint Reply with Id" + id);
            Optional<?> data = Optional.of(complaintService.removeComplaintReply(id)); // TODO: Add custom code here;
            ResponseEntity<?> response;

            if (data.isPresent()) {
                response = ResponseEntity.status(HttpStatus.OK).body(data.get());
            } else {
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            return response;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> handleFindByComplaintId(String complaintId) {
        try {
            logger.debug("Searches for a Complaint assigned by " + complaintId);
            Optional<?> data = Optional.of(complaintService.findByComplaintId(complaintId)); // TODO: Add custom code
                                                                                             // here;
            ResponseEntity<?> response;

            if (data.isPresent()) {
                response = ResponseEntity.status(HttpStatus.OK).body(data.get());
            } else {
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            return response;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
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