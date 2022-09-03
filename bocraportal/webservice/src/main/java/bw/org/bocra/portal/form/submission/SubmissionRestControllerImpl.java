// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by SpringWSImpl.java.vsl in andromda-webservices.
//
package bw.org.bocra.portal.form.submission;

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

import bw.org.bocra.portal.form.submission.data.DataFieldVO;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/form/submission")
@Tag(name = "Form Submission", description = "Managing form submission.")
@CrossOrigin()
public class SubmissionRestControllerImpl extends SubmissionRestControllerBase {

    protected static Logger logger = LoggerFactory.getLogger(SubmissionRestControllerImpl.class);

    public SubmissionRestControllerImpl(SubmissionService submissionService) {
        super(submissionService);
    }

    @Override
    public ResponseEntity<?> handleFindById(Long id) {
        try{
            logger.debug("");
            Optional<FormSubmissionVO> data = Optional.of(submissionService.findById(id));
            ResponseEntity<FormSubmissionVO> response;
    
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
            logger.debug("");
            Optional<Collection<FormSubmissionVO>> data = Optional.of(submissionService.getAll());
            ResponseEntity<Collection<FormSubmissionVO>> response;
    
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
            logger.debug("");
            Optional<Collection<FormSubmissionVO>> data = Optional.of(submissionService.getAll(pageNumber, pageSize));
            ResponseEntity<Collection<FormSubmissionVO>> response;
    
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
            logger.debug("");
            Optional<Boolean> data = Optional.of(submissionService.remove(id));
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
    public ResponseEntity<?> handleSave(FormSubmissionVO formSubmissionVO) {
        try{
            logger.debug("");
            Optional<FormSubmissionVO> data = Optional.of(submissionService.save(formSubmissionVO));
            ResponseEntity<FormSubmissionVO> response;
    
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
    public ResponseEntity<?> handleSearch(FormSubmissionCriteria criteria) {
        try{
            logger.debug("");
            Optional<Collection<FormSubmissionVO>> data = Optional.of(submissionService.search(criteria));
            ResponseEntity<Collection<FormSubmissionVO>> response;
    
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
    public ResponseEntity<?> handleAddDataField(DataFieldVO dataField) {
        try{
            logger.debug("");
            Optional<DataFieldVO> data = Optional.of(submissionService.addDataField(dataField));
            ResponseEntity<DataFieldVO> response;
    
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
    public ResponseEntity<?> handleAddDataFields(Set<DataFieldVO> dataFields) {
        try{
            logger.debug("");
            Optional<Collection<DataFieldVO>> data = Optional.of(submissionService.addDataFields(dataFields));
            ResponseEntity<Collection<DataFieldVO>> response;
    
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
    public ResponseEntity<?> handleDeleteDataField(Long id) {
        try{
            logger.debug("");
            Optional<Boolean> data = Optional.of(submissionService.deleteDataField(id));
            ResponseEntity<Boolean> response;
    
            if(data.isPresent()) {
                response = ResponseEntity.status(HttpStatus.OK).body(data.get());
            } else {
                response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
    
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> handleGetSubmissionSummary(FormSubmissionCriteria criteria) {
        try{
            logger.debug("");
            SubmissionSummary data = submissionService.getSubmissionSummary(criteria);
            ResponseEntity<SubmissionSummary> response;
    
            if(data != null) {
                response = ResponseEntity.status(HttpStatus.OK).body(data);
            } else {
                response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
    
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e.getMessage());
        }
    }
}