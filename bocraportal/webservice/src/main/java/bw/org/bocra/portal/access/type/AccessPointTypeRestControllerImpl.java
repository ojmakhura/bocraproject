// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by SpringWSImpl.java.vsl in andromda-webservices.
//
package bw.org.bocra.portal.access.type;

import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.apache.commons.collections4.CollectionUtils;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bw.org.bocra.portal.access.AccessPointCriteria;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/access/type")
@Tag(name = "Access Point Type", description = "Configuring different resource types accessible.")
@CrossOrigin()
public class AccessPointTypeRestControllerImpl extends AccessPointTypeRestControllerBase {

    public AccessPointTypeRestControllerImpl(AccessPointTypeService accessPointTypeService) {
        super(accessPointTypeService);
    }

    @Override
    public ResponseEntity<?> handleFindById(Long id) {
        try {
            logger.debug("Searches for Access Point Type using ID " + id);
            AccessPointTypeVO type = accessPointTypeService.findById(id);

            if(type != null && type.getId() != null) {
                return ResponseEntity.status(HttpStatus.OK).body(type);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("Access point type with id %ld not found.", id));
            }

        } catch (Exception e) {
            e.printStackTrace();

            String message = e.getMessage();
            if (e instanceof NoSuchElementException || e.getCause() instanceof NoSuchElementException) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("Access point type with id %d not found.", id));
            } else {
                message = "An unknown error has occured while loading an access point type. Please contact the system administrator.";
            }


            logger.error(message);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
    }

    @Override
    public ResponseEntity<?> handleGetAll() {
        try {
            logger.debug("Displays all Access Point Types");

            return ResponseEntity.status(HttpStatus.OK).body(accessPointTypeService.getAll());

        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occured when loading all access point types.");
        }
    }

    @Override
    public ResponseEntity<?> handleGetAllPaged(Integer pageNumber, Integer pageSize) {
        try {
            
            logger.debug("Displays all Access Point Types of the specified Page number: " + pageNumber + "and Page size: " + pageSize);
            return ResponseEntity.status(HttpStatus.OK).body(accessPointTypeService.getAll(pageNumber, pageSize));

        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            String message = String.format("An error occurred when reading page %d of size %d.", pageNumber, pageSize);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
    }

    @Override
    public ResponseEntity<?> handleRemove(Long id) {
        try {
            logger.debug("Deletes Access Point Type by ID " +id);
            boolean rm = accessPointTypeService.remove(id);
            ResponseEntity<?> response;

            if(rm) {
                response = ResponseEntity.status(HttpStatus.OK).body(rm);
            } else {
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("Failed to delete the access point type with id " + id);
            }

            return response;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());

            if(e instanceof EmptyResultDataAccessException || e.getCause() instanceof EmptyResultDataAccessException) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Could not delete access point type with id " + id);
            }

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unknown error encountered when deleting access point type with id " + id);
        }
    }

    @Override
    public ResponseEntity<?> handleSave(AccessPointTypeVO accessPointType) {
        try {
            logger.debug("Saves Access Point Type "+accessPointType );
            Optional<?> data = Optional.of(accessPointTypeService.save(accessPointType));
            ResponseEntity<?> response;

            if(data.isPresent()) {
                response = ResponseEntity.status(HttpStatus.OK).body(data.get());
            } else {
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("Could not save the access point type.");
            }

            return response;
        } catch (Exception e) {
            // e.printStackTrace();
            logger.error(e.toString());
            String message = e.getMessage();
            if (e instanceof IllegalArgumentException || e.getCause() instanceof IllegalArgumentException) {
                
                if(message.contains("'accessPointType'")) {
                    message = "The access point type information is missing.";
                } else if(message.contains("'accessPointType.code'")) {
                    message = "The access point type code is missing.";
                } else if(message.contains("'accessPointType.name'")) {
                    message = "The access point type name is missing.";
                } else {
                    message = "An unknown error has occured. Please contact the system administrator.";
                }

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
                
            } else if (e instanceof AccessPointTypeServiceException) {

                StringBuilder builder = new StringBuilder();
                builder.append("An access point type with this");
                
                Collection<AccessPointTypeVO> types = accessPointTypeService.search(accessPointType.getCode());
                if(CollectionUtils.isNotEmpty(types) ) {
                    AccessPointTypeVO type = types.iterator().next();
                    builder.append(" code ");
                }

                types = accessPointTypeService.search(accessPointType.getName());
                if(CollectionUtils.isNotEmpty(types) ) {
                    AccessPointTypeVO type = types.iterator().next();
                    builder.append(" name ");
                }
                
                builder.append("has already been created.");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(builder.toString());

            } else if(e instanceof ConstraintViolationException) {
                
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This Access Point Type has been already created.");
            }

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Some error has occured. Please contact the portal administrator.");
        }
    }

    @Override
    public ResponseEntity<?> handleSearch(String criteria) {
        try {
            logger.debug("Searches for Access Point Type by criteria "+ criteria);
            return ResponseEntity.status(HttpStatus.OK).body(accessPointTypeService.search(criteria));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An unknown error has occurred. Please contact the site administrator.");
        }
    }

    @Override
    public ResponseEntity<?> handlePagedSearch(Integer pageNumber, Integer pageSize, AccessPointCriteria criteria) {
        // TODO Auto-generated method stub
        return null;
    }
}