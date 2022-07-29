// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by SpringWSImpl.java.vsl in andromda-webservices.
//
package bw.org.bocra.portal.licence;

import java.util.Collection;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("license")
@Tag(name = "Licence", description = "Managing licences.")
@CrossOrigin()
public class LicenceRestControllerImpl extends LicenceRestControllerBase {

    public LicenceRestControllerImpl(LicenceService licenceService) {
        super(licenceService);
    }

    @Override
    public ResponseEntity<?> handleFindById(Long id) {
        Optional<LicenceVO> data = Optional.of(licenceService.findById(id)); // TODO: Add custom code here;
        ResponseEntity<LicenceVO> response;

        if(data.isPresent()) {
            response = ResponseEntity.status(HttpStatus.OK).body(data.get());
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }

    @Override
    public ResponseEntity<?> handleGetAll() {
        Optional<Collection<LicenceVO>> data = Optional.of(licenceService.getAll()); // TODO: Add custom code here;
        ResponseEntity<Collection<LicenceVO>> response;

        if(data.isPresent()) {
            response = ResponseEntity.status(HttpStatus.OK).body(data.get());
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }

    @Override
    public ResponseEntity<?> handleGetAllPaged(Integer pageNumber, Integer pageSize) {
        Optional<Collection<LicenceVO>> data = Optional.of(licenceService.getAll(pageNumber, pageSize)); // TODO: Add custom code here;
        ResponseEntity<Collection<LicenceVO>> response;

        if(data.isPresent()) {
            response = ResponseEntity.status(HttpStatus.OK).body(data.get());
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }

    @Override
    public ResponseEntity<?> handleRemove(Long id) {
        Optional<Boolean> data = Optional.of(licenceService.remove(id)); // TODO: Add custom code here;
        ResponseEntity<Boolean> response;

        if(data.isPresent()) {
            response = ResponseEntity.status(HttpStatus.OK).body(data.get());
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }

    @Override
    public ResponseEntity<?> handleSave(LicenceVO licence) {
        Optional<LicenceVO> data = Optional.of(licenceService.save(licence)); // TODO: Add custom code here;
        ResponseEntity<LicenceVO> response;

        if(data.isPresent()) {
            response = ResponseEntity.status(HttpStatus.OK).body(data.get());
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }

    @Override
    public ResponseEntity<?> handleSearch(LicenceCriteria criteria) {
        Optional<Collection<LicenceVO>> data = Optional.of(licenceService.search(criteria)); // TODO: Add custom code here;
        ResponseEntity<Collection<LicenceVO>> response;

        if(data.isPresent()) {
            response = ResponseEntity.status(HttpStatus.OK).body(data.get());
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }
}