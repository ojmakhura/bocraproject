// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by SpringWSImpl.java.vsl in andromda-webservices.
//
package bw.org.bocra.portal.licence.type;

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
@RequestMapping("licence/type")
@CrossOrigin()
public class LicenceTypeRestControllerImpl extends LicenceTypeRestControllerBase {

    protected static Logger log = LoggerFactory.getLogger(LicenceTypeRestControllerImpl.class);


    @Override
    public ResponseEntity<?> handleFindById(Long id) {
        Optional<LicenceTypeVO> data = Optional.of(this.licenceTypeService.findById(id)); 
        ResponseEntity<LicenceTypeVO> response;

        if(data.isPresent()) {
            response = ResponseEntity.status(HttpStatus.OK).body(data.get());
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }

    @Override
    public ResponseEntity<?> handleGetAll() {
        Optional<Collection<LicenceTypeVO>> data = Optional.of(this.licenceTypeService.getAll()); 
        ResponseEntity<Collection<LicenceTypeVO>> response;

        if(data.isPresent()) {
            response = ResponseEntity.status(HttpStatus.OK).body(data.get());
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }

    @Override
    public ResponseEntity<?> handleRemove(Long id) {
        Optional<Boolean> data = Optional.of(this.licenceTypeService.remove(id));
        ResponseEntity<Boolean> response;

        if(data.isPresent()) {
            response = ResponseEntity.status(HttpStatus.OK).body(data.get());
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }

    @Override
    public ResponseEntity<?> handleSave(LicenceTypeVO LicenceTypeVO) {
        Optional<LicenceTypeVO> data = Optional.of(this.licenceTypeService.save(LicenceTypeVO));
        ResponseEntity<LicenceTypeVO> response;

        if(data.isPresent()) {
            response = ResponseEntity.status(HttpStatus.OK).body(data.get());
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }

    @Override
    public ResponseEntity<?> handleSearch(LicenceTypeCriteria criteria) {
        Optional<Collection<LicenceTypeVO>> data = Optional.of(this.licenceTypeService.search(criteria));
        ResponseEntity<Collection<LicenceTypeVO>> response;

        if(data.isPresent()) {
            response = ResponseEntity.status(HttpStatus.OK).body(data.get());
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }

    @Override
    public ResponseEntity<?> handleGetAllPaged(Integer pageNumber, Integer pageSize) {
        Optional<Collection<LicenceTypeVO>> data = Optional.of(this.licenceTypeService.getAll(pageNumber, pageSize));
        ResponseEntity<Collection<LicenceTypeVO>> response;

        if(data.isPresent()) {
            response = ResponseEntity.status(HttpStatus.OK).body(data.get());
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }
}