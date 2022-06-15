// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by SpringWSImpl.java.vsl in andromda-webservices.
//
package bw.org.bocra.portal.expression;

import java.util.Collection;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/expression")
public class ExpressionControllerImpl extends ExpressionControllerBase {


    @Override
    public ResponseEntity<ExpressionVO> handleFindById(Long id) {
        Optional<ExpressionVO> data = Optional.empty(); // TODO: Add custom code here;
        ResponseEntity<ExpressionVO> response;

        if(data.isPresent()) {
            response = ResponseEntity.status(HttpStatus.OK).body(data.get());
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }

    @Override
    public ResponseEntity<Collection<ExpressionVO>> handleGetAll() {
        Optional<Collection<ExpressionVO>> data = Optional.empty(); // TODO: Add custom code here;
        ResponseEntity<Collection<ExpressionVO>> response;

        if(data.isPresent()) {
            response = ResponseEntity.status(HttpStatus.OK).body(data.get());
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }

    @Override
    public ResponseEntity<Collection<ExpressionVO>> handleGetAllPaged(Integer pageNumber, Integer pageSize) {
        Optional<Collection<ExpressionVO>> data = Optional.empty(); // TODO: Add custom code here;
        ResponseEntity<Collection<ExpressionVO>> response;

        if(data.isPresent()) {
            response = ResponseEntity.status(HttpStatus.OK).body(data.get());
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }

    @Override
    public ResponseEntity<Boolean> handleRemove(Long id) {
        Optional<Boolean> data = Optional.empty(); // TODO: Add custom code here;
        ResponseEntity<Boolean> response;

        if(data.isPresent()) {
            response = ResponseEntity.status(HttpStatus.OK).body(data.get());
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }

    @Override
    public ResponseEntity<ExpressionVO> handleSave(ExpressionVO expression) {
        Optional<ExpressionVO> data = Optional.empty(); // TODO: Add custom code here;
        ResponseEntity<ExpressionVO> response;

        if(data.isPresent()) {
            response = ResponseEntity.status(HttpStatus.OK).body(data.get());
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }
}