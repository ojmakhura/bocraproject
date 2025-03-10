// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by SpringWSImpl.java.vsl in andromda-webservices.
//
package bw.org.bocra.portal.licensee.shares;

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
@RequestMapping("/licensee/shareholder")
@Tag(name = "Licensee SHareholder", description = "Managing licensee shareholders mapping.")
@CrossOrigin()
public class LicenseeShareholderRestControllerImpl extends LicenseeShareholderRestControllerBase {
    
    public LicenseeShareholderRestControllerImpl(LicenseeShareholderService licenseeShareholderService) {
        
        super(licenseeShareholderService);
    }

    @Override
    public ResponseEntity<?> handleCreate(Long licenseeId, Long shareholderId, Integer numberofShares) {
        try {
            logger.debug("Create Licensee Sector with Licensee Id "+licenseeId+" and Sector Id "+shareholderId);
            Optional<?> data = Optional.of(licenseeShareholderService.create(licenseeId, shareholderId, numberofShares));
            ResponseEntity<?> response;

            if(data.isPresent()) {
                response = ResponseEntity.ok().body(data.get());
            } else {
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("Could not create a licensee shareholder entry. Please contact administrator.");
            }

            return response;
        } catch (IllegalArgumentException | LicenseeShareholderServiceException e) {

            e.printStackTrace();

            String message = e.getMessage();

            if(e instanceof IllegalArgumentException || e.getCause() instanceof IllegalArgumentException) {

                if(message.contains("'licenseeShareholder'")) {

                    message = "The licensee shareholder information is missing.";

                } else if(message.contains("or its id can not be null")) {
                    if(message.contains("'licenseeShareholder.shareholder'")) {
                
                        message = "The shareholder type or its id is missing.";

                    } else if(message.contains("'licenseeShareholder.licensee'")) {
                
                        message = "The licensee or its id is missing.";
                    }
                
                } else {
                    message = "An unknown error has occured. Please contact the system administrator.";
                }

                return ResponseEntity.badRequest().body(message);

            } else if(e.getCause() instanceof PSQLException) {

                if (e.getCause().getMessage().contains("duplicate key")) {
                    if(e.getCause().getMessage().contains("(licensee_shareholder_unique)")) {

                        return ResponseEntity.badRequest().body("An licensee shareholder has been already created.");
                    } 
                    
                } else if (e.getCause().getMessage().contains("null value in column")) {
                    if (e.getCause().getMessage().contains("column \"shareholder_fk\"")) {
                        return ResponseEntity.badRequest().body("The shareholder value is missing.");
                    } else if (e.getCause().getMessage().contains("column \"licence_type_fk\"")) {
                        return ResponseEntity.badRequest().body("The licensee value is missing.");
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
    public ResponseEntity<?> handleFindByLicensee(Long licenseeId) {
        try {
            logger.debug("Search licensee shareholder by licensee Id " + licenseeId);
            Optional<?> data = Optional.of(licenseeShareholderService.findByLicensee(licenseeId));
            ResponseEntity<?> response;

            if(data.isPresent()) {
                response = ResponseEntity.ok().body(data.get());
            } else {
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("Could not find the licensee shareholder with this licensee id.");
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
            return ResponseEntity.badRequest().body(message);
        }
    }

    @Override
    public ResponseEntity<?> handleFindByShareholder(Long shareholderId) {
        try {
            logger.debug("Search licensee shareholder by shareholder Id " + shareholderId);
            Optional<?> data = Optional.of(licenseeShareholderService.findByShareholder(shareholderId));
            ResponseEntity<?> response;

            if(data.isPresent()) {
                response = ResponseEntity.ok().body(data.get());
            } else {
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("Could not find the licensee shareholder with this shareholder id.");
            }

            return response;
        } catch (Exception e) {
            e.printStackTrace();

            String message = e.getMessage();

            logger.error(message, e);
            if (e instanceof NoSuchElementException || e.getCause() instanceof NoSuchElementException
                    || e instanceof EntityNotFoundException || e.getCause() instanceof EntityNotFoundException) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("Licensee shareholder with shareholder id %d not found.", shareholderId));
            } else {
                message = "An unknown error has occured. Please contact the system administrator.";
            }
            return ResponseEntity.badRequest().body(message);
        }
    }

    @Override
    public ResponseEntity<?> handleFindById(Long id) {
        try {
            logger.debug("Search licensee shareholder by Id "+id);
            Optional<?> data = Optional.of(licenseeShareholderService.findById(id));
            ResponseEntity<?> response;

            if(data.isPresent()) {
                response = ResponseEntity.ok().body(data.get());
            } else {
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("Licensee shareholder with id %d not found.", id));
            }

            return response;
        } catch (Exception e) {
            e.printStackTrace();

            String message = e.getMessage();
            if (e instanceof NoSuchElementException || e.getCause() instanceof NoSuchElementException
                    || e instanceof EntityNotFoundException || e.getCause() instanceof EntityNotFoundException) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("Licensee shareholder with id %d not found.", id));
            } else {
                message = "An unknown error has occured. Please contact the system administrator.";
            }

            logger.error(message, e);
            return ResponseEntity.badRequest().body(message);
        }
    }

    @Override
    public ResponseEntity<?> handleGetAll() {
        try {
            logger.debug("Display all Licensee shareholders ");
            return ResponseEntity.ok().body(licenseeShareholderService.getAll());

        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().body("An unknown error has occurred. Please contact the site administrator.");
        }
    }

    @Override
    public ResponseEntity<?> handleRemove(Long id) {
        try {
            logger.debug("Deletes licensee shareholder by Id "+id);
            Optional<?> data = Optional.of(licenseeShareholderService.remove(id));
            ResponseEntity<?> response;

            if(data.isPresent()) {
                response = ResponseEntity.ok().body(data.get());
            } else {
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("Failed to delete the licensee shareholder with id " + id);
            }

            return response;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);

            if(e instanceof EmptyResultDataAccessException || e.getCause() instanceof EmptyResultDataAccessException) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Could not delete licensee shareholder with id " + id);
            }

            return ResponseEntity.badRequest().body("Unknown error encountered when deleting licensee shareholder with id " + id);
        }
    }

    @Override
    public ResponseEntity<?> handleUpdateLicensee(Long id, Long licenseeId) {
        try {
            logger.debug("Update licensee shareholder by Id " + id + " and licensee Id " + licenseeId);
            Optional<?> data = Optional.of(licenseeShareholderService.updateLicensee(id, licenseeId));
            ResponseEntity<?> response;

            if(data.isPresent()) {
                response = ResponseEntity.ok().body(data.get());
            } else {
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("Failed to change the licensee id.");
            }

            return response;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResponseEntity.badRequest().body("An unknown error has occurred. Please contact the site administrator.");
        }
    }

    @Override
    public ResponseEntity<?> handleUpdateShareholder(Long id, Long shareholderId) {
        try {
            logger.debug("Update licensee shareholder by Id " + id + " and shareholder Id " + shareholderId);
            Optional<?> data = Optional.of(licenseeShareholderService.updateShareholder(id, shareholderId));
            ResponseEntity<?> response;

            if(data.isPresent()) {
                response = ResponseEntity.ok().body(data.get());
            } else {
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("Failed to change the shareholder id.");
            }

            return response;
        } catch (Exception e) {
            logger.error(e.getMessage(), e );
            return ResponseEntity.badRequest().body("An unknown error has occurred. Please contact the site administrator.");
        }
    }

    @Override
    public ResponseEntity<?> handleUpdateNumberOfShares(Long id, Integer numberOfShares) {
        try {
            logger.debug("Update licensee shareholder by Id " + id + " and number of shares " + numberOfShares);
            Optional<?> data = Optional.of(licenseeShareholderService.updateNumberOfShares(id, numberOfShares));
            ResponseEntity<?> response;

            if(data.isPresent()) {
                response = ResponseEntity.ok().body(data.get());
            } else {
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("Failed to change the licensee id.");
            }

            return response;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResponseEntity.badRequest().body("An unknown error has occurred. Please contact the site administrator.");
        }
    }
}