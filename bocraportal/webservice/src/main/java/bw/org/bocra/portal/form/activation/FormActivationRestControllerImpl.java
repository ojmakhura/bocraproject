// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by SpringWSImpl.java.vsl in andromda-webservices.
//
package bw.org.bocra.portal.form.activation;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/form/activation")
@Tag(name = "Form Activation", description = "Managing form activation within specified period.")
@CrossOrigin()
public class FormActivationRestControllerImpl extends FormActivationRestControllerBase {
    
    public FormActivationRestControllerImpl(FormActivationService formActivationService) {
        
        super(formActivationService);
    }

    @Override
    public ResponseEntity<?> handleFindById(Long id) {
        try {
            logger.debug("Error detected at Form Activation Service handleFindById "+"For "+id);
            Optional<?> data = Optional.of(formActivationService.findById(id));
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
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> handleGetAll() {
        try {
            logger.debug("Error detected at Form Activation Service handleGetAll");
            Optional<?> data = Optional.of(formActivationService.getAll());
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
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> handleGetAllPaged(Integer pageNumber, Integer pageSize) {
        try {
            logger.debug("Error detected at Form Activation Service handleGetAllPaged "+"Page Number:"+pageNumber+"Page Size:"+pageSize);
            Optional<?> data = Optional.of(formActivationService.getAll(pageNumber, pageSize));
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
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> handleRemove(Long id) {
        try {
            logger.debug("Error detected at Form Activation Service handleRemove "+"For "+id);
            Optional<?> data = Optional.of(formActivationService.remove(id));
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
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> handleSave(FormActivationVO formActivation) {
        try {
            logger.debug("Error detected at Form Activation Service handleSave "+formActivation);
            Optional<?> data = Optional.of(formActivationService.save(formActivation));
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
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> handleSearch(FormActivationCriteria criteria) {
        try {
            logger.debug("Error detected at Form Activation Service handleSearch "+criteria);
            Optional<?> data = Optional.of(formActivationService.search(criteria));
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
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}