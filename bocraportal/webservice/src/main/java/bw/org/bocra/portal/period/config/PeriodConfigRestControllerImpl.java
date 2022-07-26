// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by SpringWSImpl.java.vsl in andromda-webservices.
//
package bw.org.bocra.portal.period.config;

import bw.org.bocra.portal.period.PeriodVO;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.Collection;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("period/config")
@Tag(name = "Period Configuration", description = "Managing time period configurations in the system.")
@CrossOrigin()
public class PeriodConfigRestControllerImpl extends PeriodConfigRestControllerBase {

    public PeriodConfigRestControllerImpl(PeriodConfigService periodConfigService) {
        super(periodConfigService);
    }

    @Override
    public ResponseEntity<?> handleFindById(Long id) {
        Optional<PeriodConfigVO> data = Optional.of(periodConfigService.findById(id)); 
        ResponseEntity<PeriodConfigVO> response;

        if(data.isPresent()) {
            response = ResponseEntity.status(HttpStatus.OK).body(data.get());
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }

    @Override
    public ResponseEntity<?> handleGetAll() {
        Optional<Collection<PeriodConfigVO>> data = Optional.of(periodConfigService.getAll());
        ResponseEntity<Collection<PeriodConfigVO>> response;

        if(data.isPresent()) {
            response = ResponseEntity.status(HttpStatus.OK).body(data.get());
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }

    @Override
    public ResponseEntity<?> handleRemove(Long id) {
        Optional<Boolean> data = Optional.of(periodConfigService.remove(id)); 
        ResponseEntity<Boolean> response;

        if(data.isPresent()) {
            response = ResponseEntity.status(HttpStatus.OK).body(data.get());
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }

    @Override
    public ResponseEntity<?> handleSave(PeriodConfigVO periodConfigVO) {
        Optional<PeriodConfigVO> data = Optional.of(periodConfigService.save(periodConfigVO)); 
        ResponseEntity<PeriodConfigVO> response;

        if(data.isPresent()) {
            response = ResponseEntity.status(HttpStatus.OK).body(data.get());
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }

    @Override
    public ResponseEntity<?> handleSearch(PeriodConfigCriteria criteria) {
        Optional<Collection<PeriodConfigVO>> data = Optional.of(periodConfigService.search(criteria));
        ResponseEntity<Collection<PeriodConfigVO>> response;

        if(data.isPresent()) {
            response = ResponseEntity.status(HttpStatus.OK).body(data.get());
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }

    @Override
    public ResponseEntity<?> handleGetAllPaged(Integer pageNumber, Integer pageSize) {
        Optional<Collection<PeriodConfigVO>> data = Optional.of(periodConfigService.getAll(pageNumber, pageSize)); 
        ResponseEntity<Collection<PeriodConfigVO>> response;

        if(data.isPresent()) {
            response = ResponseEntity.status(HttpStatus.OK).body(data.get());
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }
}