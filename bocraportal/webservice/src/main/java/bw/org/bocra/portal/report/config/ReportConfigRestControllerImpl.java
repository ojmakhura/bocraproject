// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by SpringWSImpl.java.vsl in andromda-webservices.
//
package bw.org.bocra.portal.report.config;

import java.util.Collection;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/report/config")
@Tag(name = "Report Configuration", description = "Managing report configurations in the system.")
@CrossOrigin()
public class ReportConfigRestControllerImpl extends ReportConfigRestControllerBase {

    public ReportConfigRestControllerImpl(ReportConfigService reportConfigService) {
        super(reportConfigService);
    }

    @Override
    public ResponseEntity<?> handleFindById(Long id) {
        try{
            logger.debug("Search Report Config by Id "+id);
            Optional<ReportConfigVO> data = Optional.empty(); // TODO: Add custom code here;
            ResponseEntity<ReportConfigVO> response;
    
            if(data.isPresent()) {
                response = ResponseEntity.ok().body(data.get());
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
            logger.debug("Display all Report Configs");
            Optional<Collection<ReportConfigVO>> data = Optional.empty(); // TODO: Add custom code here;
            ResponseEntity<Collection<ReportConfigVO>> response;
    
            if(data.isPresent()) {
                response = ResponseEntity.ok().body(data.get());
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
            logger.debug("Display all Report Configs with the specified page number "+pageNumber+" and  page size "+pageSize);
            Optional<Collection<ReportConfigVO>> data = Optional.empty(); // TODO: Add custom code here;
            ResponseEntity<Collection<ReportConfigVO>> response;
    
            if(data.isPresent()) {
                response = ResponseEntity.ok().body(data.get());
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
            logger.debug("Delete Report Config with Id "+id);
            Optional<Boolean> data = Optional.empty(); // TODO: Add custom code here;
            ResponseEntity<Boolean> response;
    
            if(data.isPresent()) {
                response = ResponseEntity.ok().body(data.get());
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
    public ResponseEntity<?> handleSave(ReportConfigVO reportConfig) {
        try{
            logger.debug("Save Report config "+reportConfig);
            Optional<ReportConfigVO> data = Optional.empty(); // TODO: Add custom code here;
            ResponseEntity<ReportConfigVO> response;
    
            if(data.isPresent()) {
                response = ResponseEntity.ok().body(data.get());
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
    public ResponseEntity<?> handleSearch(ReportConfigCriteria criteria) {
        try{
            logger.debug("Search Report Config by criteria "+criteria );
            Optional<Collection<ReportConfigVO>> data = Optional.empty(); // TODO: Add custom code here;
            ResponseEntity<Collection<ReportConfigVO>> response;
    
            if(data.isPresent()) {
                response = ResponseEntity.ok().body(data.get());
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