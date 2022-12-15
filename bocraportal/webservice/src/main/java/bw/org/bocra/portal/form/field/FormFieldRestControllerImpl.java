// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by SpringWSImpl.java.vsl in andromda-webservices.
//
package bw.org.bocra.portal.form.field;

import java.util.Collection;
import java.util.Optional;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/form/field")
@Tag(name = "Form Field", description = "Managing form fields.")
@CrossOrigin()
public class FormFieldRestControllerImpl extends FormFieldRestControllerBase {

    public FormFieldRestControllerImpl(FormFieldService formFieldService) {
        super(formFieldService);
    }

    @Override
    public ResponseEntity<?> handleFindById(Long id) {
        try{
            logger.debug("Search Form Field by "+id);
            Optional<FormFieldVO> data = Optional.of(formFieldService.findById(id));
            ResponseEntity<FormFieldVO> response;
    
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
            logger.debug("Display all Form Fields ");
            Optional<Collection<FormFieldVO>> data = Optional.of(formFieldService.getAll());
            ResponseEntity<Collection<FormFieldVO>> response;
    
            if(data.isPresent()) {
                response = ResponseEntity.status(HttpStatus.OK).body(data.get());
            } else {
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
    
            return response;
        } catch (Exception e) {
            logger.debug("Error detected at Form Field Service");
            e.printStackTrace();
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> handleGetAllPaged(Integer pageNumber, Integer pageSize) {
        try{
            logger.debug("Display Form Fields of specified "+"Page number: "+pageNumber+" and Page Size "+pageSize);
            Optional<Collection<FormFieldVO>> data = Optional.of(formFieldService.getAll(pageNumber, pageSize));
            ResponseEntity<Collection<FormFieldVO>> response;
    
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
            logger.debug("Deletes Form Field by "+id);
            Optional<Boolean> data = Optional.of(formFieldService.remove(id));
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
    public ResponseEntity<?> handleSave(FormFieldVO formFieldVO) {
        try{
            logger.debug("Save Form Field "+formFieldVO);
            Optional<FormFieldVO> data = Optional.of(formFieldService.save(formFieldVO));
            ResponseEntity<FormFieldVO> response;
    
            if(data.isPresent()) {
                response = ResponseEntity.status(HttpStatus.OK).body(data.get());
            } else {
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
    
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            if(e instanceof ConstraintViolationException){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This field has been already done.");
            }
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e.getMessage());
        }
    }
}