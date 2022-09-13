// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by SpringWSImpl.java.vsl in andromda-webservices.
//
package bw.org.bocra.portal.document.type;

import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/document/type")
@CrossOrigin()
@Tag(name = "Document Type", description = "Managing document types.")
public class DocumentTypeRestControllerImpl extends DocumentTypeRestControllerBase {

    public DocumentTypeRestControllerImpl(
            DocumentTypeService documentTypeService) {

        super(documentTypeService);
    }

    @Override
    public ResponseEntity<?> handleFindById(Long id) {
        try {
            logger.debug("Error detected at Document Type Service handleFindById "+"For "+id);
            Optional<?> data = Optional.of(documentTypeService.findById(id));
            ResponseEntity<?> response;

            if (data.isPresent()) {
                response = ResponseEntity.status(HttpStatus.OK).body(data.get());
            } else {
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            return response;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> handleGetAll() {
        try {
            logger.debug("Error detected at Document Type Service handleGetAll");
            Optional<?> data = Optional.of(documentTypeService.getAll());
            ResponseEntity<?> response;

            if (data.isPresent()) {
                response = ResponseEntity.status(HttpStatus.OK).body(data.get());
            } else {
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            return response;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> handleGetAllPaged(Integer pageNumber, Integer pageSize) {
        try {
            logger.debug("Error detected at Document Type Service handleGetAllPaged "+" Page Number: "+pageNumber+" Page Size"+pageSize);
            Optional<?> data = Optional.of(documentTypeService.getAll(pageNumber, pageSize));
            ResponseEntity<?> response;

            if (data.isPresent()) {
                response = ResponseEntity.status(HttpStatus.OK).body(data.get());
            } else {
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            return response;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> handleRemove(Long id) {
        try {
            logger.debug("Error detected at Document Type Service handleRemove "+"For "+id);
            Optional<?> data = Optional.of(documentTypeService.remove(id));
            ResponseEntity<?> response;

            if (data.isPresent()) {
                response = ResponseEntity.status(HttpStatus.OK).body(data.get());
            } else {
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            return response;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> handleSave(DocumentTypeVO documentType) {
        try {
            logger.debug("Error detected at Document Type Service handleSave "+"Document Type "+documentType);
            Optional<?> data = Optional.of(documentTypeService.save(documentType));
            ResponseEntity<?> response;

            if (data.isPresent()) {
                response = ResponseEntity.status(HttpStatus.OK).body(data.get());
            } else {
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            return response;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> handleSearch(String criteria) {
        try {
            logger.debug("Error detected at Document Type Service handleSearch "+"By "+criteria);
            Optional<?> data = Optional.of(documentTypeService.search(criteria));
            ResponseEntity<?> response;

            if (data.isPresent()) {
                response = ResponseEntity.status(HttpStatus.OK).body(data.get());
            } else {
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            return response;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}