// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by SpringWSImpl.java.vsl in andromda-webservices.
//
package bw.org.bocra.portal.period;

import java.util.Collection;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("period")
@Tag(name = "Period", description = "Managing time periods in the system.")
@PreAuthorize("@securityCheck.isAuthorised('/period')")
@CrossOrigin()
public class PeriodRestControllerImpl extends PeriodRestControllerBase {

    public PeriodRestControllerImpl(PeriodService periodService) {
        super(periodService);
    }

    @Override
    public ResponseEntity<?> handleFindById(Long id) {
        try{
            Optional<PeriodVO> data = Optional.of(this.periodService.findById(id));
            ResponseEntity<PeriodVO> response;
    
            if (data.isPresent()) {
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
            Optional<Collection<PeriodVO>> data = Optional.of(periodService.getAll());
            ResponseEntity<Collection<PeriodVO>> response;
    
            if (data.isPresent()) {
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
            Optional<Boolean> data = Optional.of(periodService.remove(id));
            ResponseEntity<Boolean> response;
    
            if (data.isPresent()) {
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
    public ResponseEntity<?> handleSave(PeriodVO periodVO) {

        try {
            Optional<PeriodVO> data = Optional.of(periodService.save(periodVO));
            ResponseEntity<PeriodVO> response;

            if (data.isPresent()) {
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
    public ResponseEntity<?> handleSearch(PeriodCriteria criteria) {
        try{
            Optional<Collection<PeriodVO>> data = Optional.of(periodService.search(criteria));
            ResponseEntity<Collection<PeriodVO>> response;
    
            if (data.isPresent()) {
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
            Optional<Collection<PeriodVO>> data = Optional.of(periodService.getAll(pageNumber, pageSize));
            ResponseEntity<Collection<PeriodVO>> response;
    
            if (data.isPresent()) {
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