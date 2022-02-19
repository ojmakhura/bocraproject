// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by SpringWSImpl.java.vsl in andromda-webservices.
//
package bw.org.bocra.portal.group;

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
@RequestMapping("group")
@CrossOrigin()
public class LicenseeGroupRestControllerImpl extends LicenseeGroupRestControllerBase {

    protected static Logger log = LoggerFactory.getLogger(LicenseeGroupRestControllerImpl.class);


    @Override
    public ResponseEntity<LicenseeGroupVO> handleFindById(Long id) {
        Optional<LicenseeGroupVO> data = Optional.of(this.licenseeGroupService.findById(id)); // TODO: Add custom code here;
        ResponseEntity<LicenseeGroupVO> response;

        if(data.isPresent()) {
            response = ResponseEntity.status(HttpStatus.OK).body(data.get());
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }

    @Override
    public ResponseEntity<Collection<LicenseeGroupVO>> handleGetAll() {
        Optional<Collection<LicenseeGroupVO>> data = Optional.of(this.licenseeGroupService.getAll()); // TODO: Add custom code here;
        ResponseEntity<Collection<LicenseeGroupVO>> response;

        if(data.isPresent()) {
            response = ResponseEntity.status(HttpStatus.OK).body(data.get());
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }

    @Override
    public ResponseEntity<Boolean> handleRemove(Long id) {
        Optional<Boolean> data = Optional.of(this.licenseeGroupService.remove(id)); // TODO: Add custom code here;
        ResponseEntity<Boolean> response;

        if(data.isPresent()) {
            response = ResponseEntity.status(HttpStatus.OK).body(data.get());
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }

    @Override
    public ResponseEntity<LicenseeGroupVO> handleSave(LicenseeGroupVO licenseeGroupVO) {
        Optional<LicenseeGroupVO> data = Optional.of(this.licenseeGroupService.save(licenseeGroupVO)); // TODO: Add custom code here;
        ResponseEntity<LicenseeGroupVO> response;

        if(data.isPresent()) {
            response = ResponseEntity.status(HttpStatus.OK).body(data.get());
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }

    @Override
    public ResponseEntity<Collection<LicenseeGroupVO>> handleSearch(LicenseeGroupCriteria searchCriteria) {
        Optional<Collection<LicenseeGroupVO>> data = Optional.of(this.licenseeGroupService.search(searchCriteria)); // TODO: Add custom code here;
        ResponseEntity<Collection<LicenseeGroupVO>> response;

        if(data.isPresent()) {
            response = ResponseEntity.status(HttpStatus.OK).body(data.get());
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }

    // @Override
    // public ResponseEntity<LicenseeGroupVO> handleUpdate(LicenseeGroupVO licenseeGroupVO) {
    //     Optional<LicenseeGroupVO> data = Optional.empty(); // TODO: Add custom code here;
    //     ResponseEntity<LicenseeGroupVO> response;

    //     if(data.isPresent()) {
    //         response = ResponseEntity.status(HttpStatus.OK).body(data.get());
    //     } else {
    //         response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    //     }

    //     return response;
    // }
}