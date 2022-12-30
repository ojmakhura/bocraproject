// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by SpringWSImpl.java.vsl in andromda-webservices.
//
package bw.org.bocra.portal.licence.type;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.postgresql.util.PSQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/licence/type")
@Tag(name = "Licence Type", description = "Managing licence types.")
@CrossOrigin()
public class LicenceTypeRestControllerImpl extends LicenceTypeRestControllerBase {

    public LicenceTypeRestControllerImpl(LicenceTypeService licenceTypeService) {
        super(licenceTypeService);
    }

    protected static Logger log = LoggerFactory.getLogger(LicenceTypeRestControllerImpl.class);

    @Override
    public ResponseEntity<?> handleFindById(Long id) {
        try{
            logger.debug("Search Licence Type by Id "+id);
            Optional<LicenceTypeVO> data = Optional.of(this.licenceTypeService.findById(id)); 
            ResponseEntity<?> response;
    
            if(data.isPresent()) {
                response = ResponseEntity.status(HttpStatus.OK).body(data.get());
            } else {
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("Licence type with id %ld not found.", id));
            }
    
            return response;
        } catch (Exception e) {
            String message = e.getMessage();
            if (e instanceof NoSuchElementException || e.getCause() instanceof NoSuchElementException) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("Licence type with id %d not found.", id));
            } else {
                message = "An unknown error has occured while loading an licence type. Please contact the system administrator.";
            }

            logger.error(message);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
    }

    @Override
    public ResponseEntity<?> handleGetAll() {
        try{

            logger.debug("Loading all Licence Types");
            return ResponseEntity.status(HttpStatus.OK).body(this.licenceTypeService.getAll());
            
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An unknown error has occurred. Please contact the site administrator.");
        }
    }

    @Override
    public ResponseEntity<?> handleRemove(Long id) {
        try{
            logger.debug("Deletes all Licence Type by Id "+id );
            boolean rm = licenceTypeService.remove(id);
            ResponseEntity<?> response;

            if(rm) {
                response = ResponseEntity.status(HttpStatus.OK).body(rm);
            } else {
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("Failed to delete the licence type with id " + id);
            }

            return response;
            
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());

            if(e instanceof EmptyResultDataAccessException || e.getCause() instanceof EmptyResultDataAccessException) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Could not delete licence type with id " + id);
            }

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unknown error encountered when deleting licence type with id " + id);
        }
    }

    @Override
    public ResponseEntity<?> handleSave(LicenceTypeVO LicenceTypeVO) {
        try{
            logger.debug("Save Licence Type "+LicenceTypeVO);
            Optional<LicenceTypeVO> data = Optional.of(this.licenceTypeService.save(LicenceTypeVO));
            ResponseEntity<?> response;
    
            if(data.isPresent()) {
                response = ResponseEntity.status(HttpStatus.OK).body(data.get());
            } else {
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("Could not save the access point type.");
            }
    
            return response;
        } catch (LicenceTypeServiceException | IllegalArgumentException e) {

            String message = e.getMessage();
            if(e instanceof IllegalArgumentException || e.getCause() instanceof IllegalArgumentException) {

                if(message.contains("'licenceType'")) {

                    message = "The licence type information is missing.";

                } else if(message.contains("'licenceType.code'")) {

                    message = "The licence type code is missing.";

                } else if(message.contains("'licenceType.name'")) {

                    message = "The licence type name is missing.";

                } else {
                    message = "An unknown error has occured. Please contact the system administrator.";
                }
                
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);

            } else if(e.getCause() instanceof PSQLException) {

                if (e.getCause().getMessage().contains("duplicate key")) {
                    if(e.getCause().getMessage().contains("(code)")) {

                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This licence type with this code has been already created.");

                    } else if(e.getCause().getMessage().contains("(name)")) {

                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This licence type with this name has been already created.");
                    } else {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This licence type is conflicting with an existing one.");
                    }
                    
                }  else if (e.getCause().getMessage().contains("null value in column")) {
                    if (e.getCause().getMessage().contains("column \"created_by\"")) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The created-by value is missing.");
                    } else if (e.getCause().getMessage().contains("column \"created_date\"")) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The created date value is missing.");
                    }
                }
                
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This licence type is conflicting with an existing one.");
            }

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An unknown error has occured. Please contact the portal administrator.");
        } catch(Exception e) {

            // e.printStackTrace();
            e.getCause().printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An unknown error has occured. Please contact the portal administrator.");
        }
    }

    @Override
    public ResponseEntity<?> handleSearch(LicenceTypeCriteria criteria) {
        try{
            logger.debug("Search Licence Type by criteria "+criteria);

            return ResponseEntity.status(HttpStatus.OK).body(this.licenceTypeService.search(criteria));

        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An unknown error has occurred. Please contact the site administrator.");
        }
    }

    @Override
    public ResponseEntity<?> handleGetAllPaged(Integer pageNumber, Integer pageSize) {
        
        try{
            logger.debug("Display all Licence Types of the specified page number "+pageNumber+" and page size"+pageSize);
            return ResponseEntity.status(HttpStatus.OK).body(this.licenceTypeService.getAll(pageNumber, pageSize));
            
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An unknown error has occurred. Please contact the site administrator.");
        }
    }
}