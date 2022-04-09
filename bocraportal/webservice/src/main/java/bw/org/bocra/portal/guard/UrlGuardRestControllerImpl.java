// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by SpringWSImpl.java.vsl in andromda-webservices.
//
package bw.org.bocra.portal.guard;

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
@RequestMapping("url-guard")
@CrossOrigin()
public class UrlGuardRestControllerImpl extends UrlGuardRestControllerBase {

    protected static Logger log = LoggerFactory.getLogger(UrlGuardRestControllerImpl.class);


    @Override
    public ResponseEntity<UrlGuardVO> handleFindById(Long id) {
        Optional<UrlGuardVO> data = Optional.of(this.urlGuardService.findById(id)); // TODO: Add custom code here;
        ResponseEntity<UrlGuardVO> response;

        if(data.isPresent()) {
            response = ResponseEntity.status(HttpStatus.OK).body(data.get());
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }

    @Override
    public ResponseEntity<Collection<UrlGuardVO>> handleGetAll() {
        Optional<Collection<UrlGuardVO>> data = Optional.of(this.urlGuardService.getAll()); // TODO: Add custom code here;
        ResponseEntity<Collection<UrlGuardVO>> response;

        if(data.isPresent()) {
            response = ResponseEntity.status(HttpStatus.OK).body(data.get());
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }

    @Override
    public ResponseEntity<Boolean> handleRemove(Long id) {
        Optional<Boolean> data = Optional.of(this.urlGuardService.remove(id)); // TODO: Add custom code here;
        ResponseEntity<Boolean> response;

        if(data.isPresent()) {
            response = ResponseEntity.status(HttpStatus.OK).body(data.get());
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }

    @Override
    public ResponseEntity<UrlGuardVO> handleSave(UrlGuardVO urlGuardVO) {
        log.info(urlGuardVO.toString());
        Optional<UrlGuardVO> data = Optional.of(urlGuardService.save(urlGuardVO)); // TODO: Add custom code here;
        ResponseEntity<UrlGuardVO> response;

        if(data.isPresent()) {
            response = ResponseEntity.status(HttpStatus.OK).body(data.get());
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }

    @Override
    public ResponseEntity<Collection<UrlGuardVO>> handleSearch(UrlGuardCriteria criteria) {
        log.info("Searching");
        System.out.println(urlGuardService.getAll());
        System.out.println(criteria.getUrl());
        System.out.println(criteria.getRoles());
        System.out.println(criteria.getType());
        System.out.println(criteria.getFirstResult());
        Optional<Collection<UrlGuardVO>> data = Optional.of(urlGuardService.search(criteria)); // TODO: Add custom code here;
        ResponseEntity<Collection<UrlGuardVO>> response;

        if(data.isPresent()) {
            response = ResponseEntity.status(HttpStatus.OK).body(data.get());
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }

    @Override
    public ResponseEntity<Collection<UrlGuardVO>> handleGetAllPaged(Integer pageNumber, Integer pageSize) {
        Optional<Collection<UrlGuardVO>> data = Optional.of(urlGuardService.getAll(pageNumber, pageSize)); // TODO: Add custom code here;
        ResponseEntity<Collection<UrlGuardVO>> response;

        if(data.isPresent()) {
            response = ResponseEntity.status(HttpStatus.OK).body(data.get());
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }
}