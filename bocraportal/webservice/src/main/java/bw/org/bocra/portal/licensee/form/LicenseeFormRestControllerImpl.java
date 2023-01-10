// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by SpringWSImpl.java.vsl in andromda-webservices.
//
package bw.org.bocra.portal.licensee.form;

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

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/licensee/form")
@Tag(name = "Licensee Form", description = "Managing licensee forms.")
@CrossOrigin()
public class LicenseeFormRestControllerImpl extends LicenseeFormRestControllerBase {
    
    public LicenseeFormRestControllerImpl(LicenseeFormService licenseeFormService) {
        
        super(licenseeFormService);
    }

    @Override
    public ResponseEntity<?> handleCreate(Long licenseeId, Long formId) {
        try {
            logger.debug("Create Licence Form with Licence Id "+licenseeId+" and Form Id "+formId);
            Optional<?> data = Optional.of(licenseeFormService.create(licenseeId, formId));
            ResponseEntity<?> response;

            if(data.isPresent()) {
                response = ResponseEntity.status(HttpStatus.OK).body(data.get());
            } else {
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("Could not create a licensee form entry. Please contact administrator.");
            }

            return response;
        } catch (IllegalArgumentException | LicenseeFormServiceException e) {

            e.printStackTrace();

            String message = e.getMessage();

            if(e instanceof IllegalArgumentException || e.getCause() instanceof IllegalArgumentException) {

                if(message.contains("'licenseeForm'")) {

                    message = "The licensee form information is missing.";

                } else if(message.contains("or its id can not be null")) {
                    if(message.contains("'licenseeForm.form'")) {
                
                        message = "The form type or its id is missing.";

                    } else if(message.contains("'licenseeForm.licensee'")) {
                
                        message = "The licensee or its id is missing.";
                    }
                
                } else {
                    message = "An unknown error has occured. Please contact the system administrator.";
                }

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);

            } else if(e.getCause() instanceof PSQLException) {

                if (e.getCause().getMessage().contains("duplicate key")) {
                    if(e.getCause().getMessage().contains("(licensee_form_unique)")) {

                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An licensee form has been already created.");
                    } 
                    
                } else if (e.getCause().getMessage().contains("null value in column")) {
                    if (e.getCause().getMessage().contains("column \"form_fk\"")) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The form value is missing.");
                    } else if (e.getCause().getMessage().contains("column \"licensee_fk\"")) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The licensee value is missing.");
                    }
                }
                
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An unknown database error has occured. Please contact the portal administrator.");
            } 

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An unknown error has occured. Please contact the portal administrator.");
        } catch(Exception e) {

            e.printStackTrace();
            logger.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An unknown error has occured. Please contact the portal administrator.");
        }
    }

    @Override
    public ResponseEntity<?> handleFindByForm(Long formId) {
        try {
            logger.debug("Search Licensee Form by Form Id "+formId);
            Optional<?> data = Optional.ofNullable(licenseeFormService.findByForm(formId));
            ResponseEntity<?> response;

            if(data.isPresent()) {
                response = ResponseEntity.status(HttpStatus.OK).body(data.get());
            } else {
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("Could not find the licensee form with this form id.");
            }

            return response;
        } catch (Exception e) {
            e.printStackTrace();

            String message = e.getMessage();

            logger.error(message, e);
            if (e instanceof NoSuchElementException || e.getCause() instanceof NoSuchElementException
                    || e instanceof EntityNotFoundException || e.getCause() instanceof EntityNotFoundException) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("licensee with form id %d not found.", formId));
            } else {
                message = "An unknown error has occured. Please contact the system administrator.";
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
    }

    @Override
    public ResponseEntity<?> handleFindById(Long id) {
        try {
            logger.debug("Search licensee Form by Id "+id);
            Optional<?> data = Optional.of(licenseeFormService.findById(id));
            ResponseEntity<?> response;

            if(data.isPresent()) {
                response = ResponseEntity.status(HttpStatus.OK).body(data.get());
            } else {
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("Licensee form with id %d not found.", id));
            }

            return response;
        } catch (Exception e) {
            e.printStackTrace();

            String message = e.getMessage();
            if (e instanceof NoSuchElementException || e.getCause() instanceof NoSuchElementException
                    || e instanceof EntityNotFoundException || e.getCause() instanceof EntityNotFoundException) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("Licensee form with id %d not found.", id));
            } else {
                message = "An unknown error has occured. Please contact the system administrator.";
            }

            logger.error(message, e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
    }

    @Override
    public ResponseEntity<?> handleFindByLicensee(Long licenseeId) {
        try {
            logger.debug("Search licensee Form by licensee Id " + licenseeId);
            Optional<?> data = Optional.of(licenseeFormService.findByLicensee(licenseeId));
            ResponseEntity<?> response;

            if(data.isPresent()) {
                response = ResponseEntity.status(HttpStatus.OK).body(data.get());
            } else {
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("Could not find the licensee form with this licensee id.");
            }

            return response;
        } catch (Exception e) {
            e.printStackTrace();

            String message = e.getMessage();

            logger.error(message, e);
            if (e instanceof NoSuchElementException || e.getCause() instanceof NoSuchElementException
                    || e instanceof EntityNotFoundException || e.getCause() instanceof EntityNotFoundException) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("Licensee with licensee id %d not found.", licenseeId));
            } else {
                message = "An unknown error has occured. Please contact the system administrator.";
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
    }

    @Override
    public ResponseEntity<?> handleGetAll() {
        try {
            logger.debug("Display all licensee Forms");
            return ResponseEntity.status(HttpStatus.OK).body(licenseeFormService.getAll());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An unknown error has occurred. Please contact the site administrator.");
        }
    }

    @Override
    public ResponseEntity<?> handleRemove(Long id) {
        try {
            logger.debug("Deletes licensee Form by Id "+id);
            Optional<?> data = Optional.of(licenseeFormService.remove(id));
            ResponseEntity<?> response;

            if(data.isPresent()) {
                response = ResponseEntity.status(HttpStatus.OK).body(data.get());
            } else {
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("Failed to delete the licensee form with id " + id);
            }

            return response;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);

            if(e instanceof EmptyResultDataAccessException || e.getCause() instanceof EmptyResultDataAccessException) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Could not delete licensee form with id " + id);
            }

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unknown error encountered when deleting licensee form with id " + id);
        }
    }

    @Override
    public ResponseEntity<?> handleUpdateForm(Long id, Long formId) {
        try {
            logger.debug("Update licensee Form by Id "+id+" and Form Id"+formId);
            Optional<?> data = Optional.of(licenseeFormService.updateForm(id, formId));
            ResponseEntity<?> response;

            if(data.isPresent()) {
                response = ResponseEntity.status(HttpStatus.OK).body(data.get());
            } else {
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("Failed to change the form id.");
            }

            return response;
        } catch (Exception e) {
            logger.error(e.getMessage(), e );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An unknown error has occurred. Please contact the site administrator.");
        }
    }

    @Override
    public ResponseEntity<?> handleUpdateLicensee(Long id, Long licenseeId) {
        try {
            logger.debug("Update licensee Form by Id " + id + " and licensee Id " + licenseeId);
            Optional<?> data = Optional.of(licenseeFormService.updateLicensee(id, licenseeId));
            ResponseEntity<?> response;

            if(data.isPresent()) {
                response = ResponseEntity.status(HttpStatus.OK).body(data.get());
            } else {
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("Failed to change the licensee id.");
            }

            return response;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An unknown error has occurred. Please contact the site administrator.");
        }
    }
}