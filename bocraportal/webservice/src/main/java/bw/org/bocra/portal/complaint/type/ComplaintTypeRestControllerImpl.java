// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by SpringWSImpl.java.vsl in andromda-webservices.
//
package bw.org.bocra.portal.complaint.type;

import java.util.NoSuchElementException;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.postgresql.util.PSQLException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/complaint/type")
@CrossOrigin()
public class ComplaintTypeRestControllerImpl extends ComplaintTypeRestControllerBase {

    public ComplaintTypeRestControllerImpl(
            ComplaintTypeService complaintTypeService) {

        super(
                complaintTypeService);
    }

    @Override
    public ResponseEntity<?> handleFindById(Long id) {
        try {
            logger.debug("Searches for complaint types using ID " + id);
            Optional<?> data = Optional.of(complaintTypeService.findById(id));
            ResponseEntity<?> response;

            if (data.isPresent()) {
                response = ResponseEntity.status(HttpStatus.OK).body(data.get());
            } else {
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("Complaint type with id %ld not found.", id));
            }

            return response;
        } catch (Exception e) {
            logger.error(e.getMessage());
            String message = e.getMessage();
            if (e instanceof NoSuchElementException || e.getCause() instanceof NoSuchElementException
                    || e instanceof EntityNotFoundException || e.getCause() instanceof EntityNotFoundException) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("Complaint type with id %d not found.", id));
            } else {
                message = "An unknown error has occured. Please contact the system administrator.";
            }

            logger.error(message);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
    }

    @Override
    public ResponseEntity<?> handleGetAll() {
        try {
            logger.debug("Displays all Complaint Types");
            return  ResponseEntity.status(HttpStatus.OK).body(complaintTypeService.getAll());
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An unknown error has occured. Please contact the system administrator.");
        }
    }

    @Override
    public ResponseEntity<?> handleGetAllPaged(Integer pageNumber, Integer pageSize) {
        try {
            logger.debug("Displays all Complaint Types of the specified " + "Page number: " + pageNumber
                    + "and Page size: " + pageSize);
            return  ResponseEntity.status(HttpStatus.OK).body(complaintTypeService.getAll(pageNumber, pageSize));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> handleRemove(Long id) {
        try {
            logger.debug("Deletes Complaint Type by ID " + id);
            Optional<?> data = Optional.of(complaintTypeService.remove(id));
            ResponseEntity<?> response;

            if (data.isPresent()) {
                response = ResponseEntity.status(HttpStatus.OK).body(data.get());
            } else {
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            return response;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());

            if(e instanceof EmptyResultDataAccessException || e.getCause() instanceof EmptyResultDataAccessException) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Could not delete complaint type with id " + id);
            }

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unknown error encountered when deleting complaint type with id " + id);
        }
    }

    @Override
    public ResponseEntity<?> handleSave(ComplaintTypeVO complaintType) {
        try {
            logger.debug("Saves Complaint Type " + complaintType);
            Optional<?> data = Optional.of(complaintTypeService.save(complaintType));
            ResponseEntity<?> response;

            if (data.isPresent()) {
                response = ResponseEntity.status(HttpStatus.OK).body(data.get());
            } else {
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            return response;
        } catch (IllegalArgumentException | ComplaintTypeServiceException e) {

            e.printStackTrace();

            String message = e.getMessage();

            if(e instanceof IllegalArgumentException || e.getCause() instanceof IllegalArgumentException) {

                if(message.contains("'complaintType'")) {

                    message = "The complaint type information is missing.";

                } else if(message.contains("'complaintType.code'")) {
                
                    message = "The complaint type code is missing.";
                
                } else if(message.contains("'complaintType.typeName'")) {
                  
                    message = "The complaint type name is missing.";
                
                } else {
                    message = "An unknown error has occured. Please contact the system administrator.";
                }

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);

            } else if(e.getCause() instanceof PSQLException) {

                if (e.getCause().getMessage().contains("duplicate key")) {
                    if(e.getCause().getMessage().contains("(code)")) {

                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An complaint type with this code has been already created.");
                    } else if(e.getCause().getMessage().contains("(type_name)")) {

                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An complaint type with this name has been already created.");
                    } 
                    
                } else if (e.getCause().getMessage().contains("null value in column")) {

                    if (e.getCause().getMessage().contains("column \"created_by\"")) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The created-by value is missing.");
                    } else if (e.getCause().getMessage().contains("column \"created_date\"")) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The created date value is missing.");
                    } else if (e.getCause().getMessage().contains("column \"code\"")) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The code value is missing.");
                    } else if (e.getCause().getMessage().contains("column \"type_name\"")) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The type name value is missing.");
                    }

                }
                
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This access point is conflicting with an existing one.");
            } 

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An unknown database error has occured. Please contact the portal administrator.");
        } catch(Exception e) {

            e.printStackTrace();
            // e.getCause().printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An unknown error has occured. Please contact the portal administrator.");
        }
    }

    @Override
    public ResponseEntity<?> handleSearch(String criteria) {
        try {
            logger.debug("Searches for a Complaint Type by criteria " + criteria);

            return ResponseEntity.status(HttpStatus.OK).body(complaintTypeService.search(criteria));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An unknown error has occurred. Please contact the site administrator.");
        }
    }
}