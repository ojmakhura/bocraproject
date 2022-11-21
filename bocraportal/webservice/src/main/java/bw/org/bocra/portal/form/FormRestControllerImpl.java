// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by SpringWSImpl.java.vsl in andromda-webservices.
//
package bw.org.bocra.portal.form;

import java.util.Collection;
import java.util.Optional;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/form")
@CrossOrigin()
@Tag(name = "Form", description = "Managing form definitions.")
public class FormRestControllerImpl extends FormRestControllerBase {

    protected static Logger log = LoggerFactory.getLogger(FormRestControllerImpl.class);

    public FormRestControllerImpl(FormService formService) {
        super(formService);
    }

    @Override
    public ResponseEntity<?> handleFindById(Long id) {
        try{
            logger.debug("Search Form by Id"+id);
            Optional<FormVO> data = Optional.of(formService.findById(id));
            ResponseEntity<?> response;
    
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

    @Override
    public ResponseEntity<?> handleGetAll() {
        try{
            logger.debug("Display Forms");
            Optional<Collection<FormVO>> data = Optional.of(formService.getAll());
            ResponseEntity<Collection<FormVO>> response;
    
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

    @Override
    public ResponseEntity<?> handleGetAllPaged(Integer pageNumber, Integer pageSize) {
        try{
            logger.debug("Display all Forms of the specified Page number "+pageNumber+" and Page size "+pageSize);
            Optional<Collection<FormVO>> data = Optional.of(formService.getAll(pageNumber, pageSize));
            ResponseEntity<Collection<FormVO>> response;
    
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

    @Override
    public ResponseEntity<?> handleRemove(Long id) {
        try{
            logger.debug("Deletes a Form by Id "+id);
            Optional<Boolean> data = Optional.of(formService.remove(id)); 
            ResponseEntity<Boolean> response;
    
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

    @Override
    public ResponseEntity<?> handleSave(FormVO formVO) {
        try{
            logger.debug("Save Form "+formVO);
            Optional<FormVO> data = Optional.of(formService.save(formVO));
            ResponseEntity<FormVO> response;
    
            if(data.isPresent()) {
                response = ResponseEntity.status(HttpStatus.OK).body(data.get());
            } else {
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
    
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            if(e instanceof ConstraintViolationException) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This form form has been already created.");
            }
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> handleSearch(FormCriteria criteria) {
        try{
            logger.debug("Search Form by criteria"+criteria);
            Optional<Collection<FormVO>> data = Optional.of(formService.search(criteria)); 
            ResponseEntity<Collection<FormVO>> response;
    
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