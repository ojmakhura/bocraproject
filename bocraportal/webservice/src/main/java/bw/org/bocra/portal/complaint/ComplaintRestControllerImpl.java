// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by SpringWSImpl.java.vsl in andromda-webservices.
//
package bw.org.bocra.portal.complaint;

import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/complaint")
@CrossOrigin()
@Tag(name = "Complaint", description = "Managing the complaints.")
public class ComplaintRestControllerImpl extends ComplaintRestControllerBase {

    public ComplaintRestControllerImpl(
            ComplaintService complaintService) {

        super(
                complaintService);
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

    @Override
    public ResponseEntity<?> handleGetAll() {
        try {
            logger.debug("Displays all Complaints");
            Optional<?> data = Optional.of(complaintService.getAll()); // TODO: Add custom code here;
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
    public ResponseEntity<?> handleSearch(String criteria) {
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
}