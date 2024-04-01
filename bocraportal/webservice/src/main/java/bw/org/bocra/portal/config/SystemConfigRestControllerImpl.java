// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by SpringWSImpl.java.vsl in andromda-webservices.
//
package bw.org.bocra.portal.config;

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
@RequestMapping("/configs")
@CrossOrigin()
public class SystemConfigRestControllerImpl extends SystemConfigRestControllerBase {
    
    public SystemConfigRestControllerImpl(
        SystemConfigService systemConfigService    ) {
        
        super(systemConfigService);
    }


    @Override
    public ResponseEntity<?> handleFindById(Long id) {
        try {
            logger.debug("Searches for system configs using ID " + id);
            
            Optional<?> data = Optional.of(systemConfigService.findById(id));
            ResponseEntity<?> response;

            if (data.isPresent()) {
                response = ResponseEntity.ok().body(data.get());
            } else {
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("System config with id %ld not found.", id));
            }

            return response;
        } catch (Exception e) {
            logger.error(e.getMessage());
            String message = e.getMessage();
            if (e instanceof NoSuchElementException || e.getCause() instanceof NoSuchElementException
                    || e instanceof EntityNotFoundException || e.getCause() instanceof EntityNotFoundException) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("System config with id %d not found.", id));
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
            logger.debug("Displays all system configs");
            return  ResponseEntity.ok().body(systemConfigService.getAll());
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().body("An unknown error has occured. Please contact the system administrator.");
        }
    }

    @Override
    public ResponseEntity<?> handleGetAllPaged(Integer pageNumber, Integer pageSize) {
        try {
            logger.debug("Displays all system configs of the specified " + "Page number: " + pageNumber
                    + "and Page size: " + pageSize);
            return  ResponseEntity.ok().body(systemConfigService.getAll(pageNumber, pageSize));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().body("An unknown error has occured. Please contact the system administrator.");
        }
    }

    @Override
    public ResponseEntity<?> handleRemove(Long id) {
        try {
            logger.debug("Deletes system config by ID " + id);
            

            if (systemConfigService.remove(id)) {
                return ResponseEntity.ok().body("System config successfully deleted.");
            } else {
                return ResponseEntity.badRequest().body("Could not delete system config");
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());

            if(e instanceof EmptyResultDataAccessException || e.getCause() instanceof EmptyResultDataAccessException) {
                return ResponseEntity.badRequest().body("Could not delete system config with id " + id);
            }

            return ResponseEntity.badRequest().body("Unknown error encountered when deleting system config with id " + id);
        }
    }

    @Override
    public ResponseEntity<?> handleSave(SystemConfigVO systemConfig) {
        try {
            logger.debug("Saves system config " + systemConfig);
            Optional<?> data = Optional.of(systemConfigService.save(systemConfig));
            ResponseEntity<?> response;

            if (data.isPresent()) {
                response = ResponseEntity.ok().body(data.get());
            } else {
                response = ResponseEntity.badRequest().body("Could not save the system config.");
            }

            return response;
        } catch (IllegalArgumentException | SystemConfigServiceException e) {

            e.printStackTrace();

            String message = e.getMessage();

            if(e instanceof IllegalArgumentException || e.getCause() instanceof IllegalArgumentException) {

                if(message.contains("'systemConfig'")) {

                    message = "The system config information is missing.";

                } else if(message.contains("'systemConfig.name'")) {
                
                    message = "The system config name is missing.";
                
                } else if(message.contains("'systemConfig.value'")) {
                  
                    message = "The system config value is missing.";
                
                } else {
                    message = "An unknown error has occured. Please contact the system administrator.";
                }

                return ResponseEntity.badRequest().body(message);

            } else if(e.getCause() instanceof PSQLException) {

                if (e.getCause().getMessage().contains("duplicate key")) {
                    if(e.getCause().getMessage().contains("(name)")) {

                        return ResponseEntity.badRequest().body("An system config with this code has been already created.");
                    }
                    
                } else if (e.getCause().getMessage().contains("null value in column")) {

                    if (e.getCause().getMessage().contains("column \"name\"")) {
                        return ResponseEntity.badRequest().body("The name value is missing.");
                    }

                }
                
                return ResponseEntity.badRequest().body("This system config is conflicting with an existing one.");
            } 

            return ResponseEntity.badRequest().body("An unknown database error has occured. Please contact the portal administrator.");
        } catch(Exception e) {

            e.printStackTrace();
            return ResponseEntity.badRequest().body("An unknown error has occured. Please contact the portal administrator.");
        }
    }

    @Override
    public ResponseEntity<?> handleSearch(String criteria) {
        try {
            logger.debug("Searches for a system config by criteria " + criteria);

            return ResponseEntity.ok().body(systemConfigService.search(criteria));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().body("An unknown error has occurred. Please contact the site administrator.");
        }
    }


    @Override
    public ResponseEntity<?> handleFindByName(SystemConfigName name) {
        try {
            logger.debug("Searches for system configs using ID " + name);
            Optional<?> data = Optional.of(systemConfigService.findByName(name));
            ResponseEntity<?> response;

            if (data.isPresent()) {
                response = ResponseEntity.ok().body(data.get());
            } else {
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("System config with name %ld not found.", name));
            }

            return response;
        } catch (Exception e) {
            logger.error(e.getMessage());
            String message = e.getMessage();
            if (e instanceof NoSuchElementException || e.getCause() instanceof NoSuchElementException
                    || e instanceof EntityNotFoundException || e.getCause() instanceof EntityNotFoundException) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("System config with name %d not found.", name));
            } else {
                message = "An unknown error has occured. Please contact the system administrator.";
            }

            logger.error(message);
            return ResponseEntity.badRequest().body(message);
        }
    }
}