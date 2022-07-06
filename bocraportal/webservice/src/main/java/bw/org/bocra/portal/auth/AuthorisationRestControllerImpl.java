// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by SpringWSImpl.java.vsl in andromda-webservices.
//
package bw.org.bocra.portal.auth;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("authorisation")
@CrossOrigin()
public class AuthorisationRestControllerImpl extends AuthorisationRestControllerBase {

    public AuthorisationRestControllerImpl(AuthorisationService authorisationService) {
        super(authorisationService);
    }

    protected static Logger log = LoggerFactory.getLogger(AuthorisationRestController.class);

    @Override
    public ResponseEntity<?> handleFindById(Long id) {
        try {
            Optional<AuthorisationVO> data = Optional.of(this.authorisationService.findById(id)); // TODO: Add custom code here;
            ResponseEntity<AuthorisationVO> response;
    
            if(data.isPresent()) {
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

            Optional<Collection<AuthorisationVO>> data = Optional.of(this.authorisationService.getAll()); // TODO: Add custom code here;
            ResponseEntity<Collection<AuthorisationVO>> response;
    
            if(data.isPresent()) {
                response = ResponseEntity.status(HttpStatus.OK).body(data.get());
            } else {
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
    
            return response;
            
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> handleRemove(Long id) {

        try {
            Optional<Boolean> data = Optional.of(this.authorisationService.remove(id)); // TODO: Add custom code here;
            ResponseEntity<Boolean> response;
    
            if(data.isPresent()) {
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
    public ResponseEntity<?> handleSave(AuthorisationVO authorisationVO) {
       
        try {
            Optional<AuthorisationVO> data = Optional.of(authorisationService.save(authorisationVO)); // TODO: Add custom code here;
            ResponseEntity<AuthorisationVO> response;
    
            if(data.isPresent()) {
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
    public ResponseEntity<?> handleSearch(AuthorisationCriteria criteria) {
        try {
            Optional<Collection<AuthorisationVO>> data = Optional.of(authorisationService.search(criteria)); // TODO: Add custom code here;
            ResponseEntity<Collection<AuthorisationVO>> response;
    
            if(data.isPresent()) {
                response = ResponseEntity.status(HttpStatus.OK).body(data.get());
            } else {
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
    
            return response;
            
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> handleGetAllPaged(Integer pageNumber, Integer pageSize) {

        try {
            Optional<Collection<AuthorisationVO>> data = Optional.of(authorisationService.getAll(pageNumber, pageSize)); // TODO: Add custom code here;
            ResponseEntity<Collection<AuthorisationVO>> response;
    
            if(data.isPresent()) {
                response = ResponseEntity.status(HttpStatus.OK).body(data.get());
            } else {
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
    
            return response;
            
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e.getMessage());
        }

    }

    @Override
    public ResponseEntity<?> handleGetAccessTypeCodeAuthorisations(Set<String> roles, Set<String> accessPointTypeCode) {
        try {
            Optional<Collection<AuthorisationVO>> data = Optional.of(authorisationService.getAccessTypeCodeAuthorisations(roles, accessPointTypeCode)); // TODO: Add custom code here;
            ResponseEntity<Collection<AuthorisationVO>> response;
    
            if(data.isPresent()) {
                response = ResponseEntity.status(HttpStatus.OK).body(data.get());
            } else {
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
    
            return response;
            
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e.getMessage());
        }
    }
}