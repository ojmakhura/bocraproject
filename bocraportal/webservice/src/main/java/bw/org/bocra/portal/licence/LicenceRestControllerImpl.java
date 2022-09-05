// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by SpringWSImpl.java.vsl in andromda-webservices.
//
package bw.org.bocra.portal.licence;

import bw.org.bocra.portal.document.DocumentService;
import bw.org.bocra.portal.document.DocumentVO;
import bw.org.bocra.portal.document.type.DocumentTypeVO;
import bw.org.bocra.portal.keycloak.KeycloakService;
import bw.org.bocra.portal.keycloak.KeycloakUserService;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.time.LocalDateTime;
import java.util.Optional;

import org.keycloak.representations.AccessToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/licence")
@CrossOrigin()
@Tag(name = "Licence", description = "Managing licences.")
public class LicenceRestControllerImpl extends LicenceRestControllerBase {
    
    private final KeycloakUserService keycloakUserService;
    private final KeycloakService keycloakService;
    
    public LicenceRestControllerImpl(LicenceService licenceService, DocumentService documentService, KeycloakUserService keycloakUserService, KeycloakService keycloakService) {
        
        super(licenceService, documentService);
        this.keycloakUserService = keycloakUserService;
        this.keycloakService = keycloakService;
    }

    @Override
    public ResponseEntity<?> handleFindById(Long id) {
        try {
            logger.debug("Error detected Licence Service handleFindById "+id);
            Optional<?> data = Optional.of(licenceService.findById(id));
            ResponseEntity<?> response;

            if(data.isPresent()) {
                response = ResponseEntity.status(HttpStatus.OK).body(data.get());
            } else {
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            return response;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> handleGetAll() {
        try {
            logger.debug("Error detected Licence Service handleGetAll ");
            Optional<?> data = Optional.of(licenceService.getAll());
            ResponseEntity<?> response;

            if(data.isPresent()) {
                response = ResponseEntity.status(HttpStatus.OK).body(data.get());
            } else {
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            return response;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> handleGetAllPaged(Integer pageNumber, Integer pageSize) {
        try {
            logger.debug("Error detected Licence Service handleGetAllPaged "+pageNumber+" "+pageSize);
            Optional<?> data = Optional.of(licenceService.getAll(pageNumber, pageSize));
            ResponseEntity<?> response;

            if(data.isPresent()) {
                response = ResponseEntity.status(HttpStatus.OK).body(data.get());
            } else {
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            return response;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> handleRemove(Long id) {
        try {
            logger.debug("Error detected Licence Service handleRemove "+id);
            Optional<?> data = Optional.of(licenceService.remove(id));
            ResponseEntity<?> response;

            if(data.isPresent()) {
                response = ResponseEntity.status(HttpStatus.OK).body(data.get());
            } else {
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            return response;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> handleSave(LicenceVO licence) {
        try {
            logger.debug("Error detected Licence Service handleSave "+licence);
            Optional<?> data = Optional.of(licenceService.save(licence));
            ResponseEntity<?> response;

            if(data.isPresent()) {
                response = ResponseEntity.status(HttpStatus.OK).body(data.get());
            } else {
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            return response;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> handleSearch(LicenceCriteria criteria) {
        try {
            logger.debug("Error detected Licence Service handleSearch "+criteria);
            Optional<?> data = Optional.of(licenceService.search(criteria));
            ResponseEntity<?> response;

            if(data.isPresent()) {
                response = ResponseEntity.status(HttpStatus.OK).body(data.get());
            } else {
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            return response;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @Override
    public ResponseEntity<?> handleAddDocument(Long id, Long documentTypeId, MultipartFile file, String fileName) {
        try {
<<<<<<< HEAD
            logger.debug("Error detected Licence Service handleAddDocument "+id+" "+documentTypeId+" "+file+" "+" "+fileName);
            Optional<?> data = Optional.empty();
=======
            AccessToken token = keycloakService.getSecurityContext().getToken();
            DocumentVO document = new DocumentVO();
            document.setCreatedBy(token.getPreferredUsername());
            document.setCreatedDate(LocalDateTime.now());
            document.setFile(file.getBytes());
            document.setDocumentName(fileName);
            
            LicenceVO licence = new LicenceVO();
            licence.setId(id);
            document.setLicence(licence);

            DocumentTypeVO docType = new DocumentTypeVO();
            docType.setId(documentTypeId);

            document.setDocumentType(docType);
            document = this.documentService.save(document);            
>>>>>>> origin/ojm-dev
            ResponseEntity<?> response;

            if(document != null && document.getId() != null) {
                response = ResponseEntity.status(HttpStatus.OK).body(document);
            } else {
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            return response;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}