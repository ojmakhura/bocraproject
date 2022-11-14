// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by SpringWSImpl.java.vsl in andromda-webservices.
//
package bw.org.bocra.portal.document;

import java.time.LocalDateTime;
import java.util.Optional;

import org.keycloak.representations.AccessToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import bw.org.bocra.portal.complaint.ComplaintService;
import bw.org.bocra.portal.keycloak.KeycloakService;
import bw.org.bocra.portal.licence.LicenceVO;
import bw.org.bocra.portal.licensee.LicenseeVO;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/document")
@CrossOrigin()
@Tag(name = "Document", description = "Managing the documents.")
public class DocumentRestControllerImpl extends DocumentRestControllerBase {



    private final KeycloakService keycloakService;
    
    public DocumentRestControllerImpl(DocumentService documentService, ComplaintService complaintService, KeycloakService keycloakService) {
        super(documentService, complaintService);
        this.keycloakService = keycloakService;
    }

    @Override
    public ResponseEntity<?> handleFindById(Long id) {
        try {
            logger.debug("Searches Document by "+id);
            Optional<?> data = Optional.of(documentService.findById(id)); // TODO: Add custom code here;
            ResponseEntity<?> response;

            if(data.isPresent()) {
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
            logger.debug("Displays all Documents");
            Optional<?> data = Optional.of(documentService.getAll()); // TODO: Add custom code here;
            ResponseEntity<?> response;

            if(data.isPresent()) {
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
            logger.debug("Displays all Documents by specified "+"Page Number: "+pageNumber+" and Page Size: "+pageSize);
            Optional<?> data = Optional.of(documentService.getAll(pageNumber, pageSize));
            ResponseEntity<?> response;

            if(data.isPresent()) {
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
            logger.debug("Deletes a Document by "+id);
            Optional<?> data = Optional.of(documentService.remove(id));
            ResponseEntity<?> response;

            if(data.isPresent()) {
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
    public ResponseEntity<?> handleSave(DocumentVO document) {
        try {
            logger.debug("Saves Document "+document);
            Optional<?> data = Optional.of(documentService.save(document));
            ResponseEntity<?> response;

            if(data.isPresent()) {
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
            logger.debug("Searches Document by "+criteria);
            Optional<?> data = Optional.of(documentService.search(criteria));
            ResponseEntity<?> response;

            if(data.isPresent()) {
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
    public ResponseEntity<?> handleUploadLicenceDocument(Long licenceId, MultipartFile file) {
        try {
            logger.debug("Upload Licence Document with Licence Id: "+licenceId+" and a File: "+file);
            AccessToken token = keycloakService.getSecurityContext().getToken();
            DocumentVO document = new DocumentVO();
            document.setCreatedBy(token.getPreferredUsername());
            document.setCreatedDate(LocalDateTime.now());
            document.setFile(file.getBytes());
            LicenceVO licence = new LicenceVO();
            licence.setId(licenceId);
            document.setLicence(licence);
            Optional<?> data = Optional.of(documentService.save(document));
            ResponseEntity<?> response;

            if(data.isPresent()) {
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
    public ResponseEntity<?> handleUploadLicenseeDocument(Long licenseeId, MultipartFile file) {
        try {
            logger.debug("Upload Licensee Document with Licensee Id"+licenseeId+" File:"+file );
            AccessToken token = keycloakService.getSecurityContext().getToken();
            DocumentVO document = new DocumentVO();
            document.setCreatedBy(token.getPreferredUsername());
            document.setCreatedDate(LocalDateTime.now());
            document.setFile(file.getBytes());
            LicenseeVO licensee = new LicenseeVO();
            licensee.setId(licenseeId);
            document.setLicensee(licensee);

            Optional<?> data = Optional.of(documentService.save(document));
            ResponseEntity<?> response;

            if(data.isPresent()) {
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
    public ResponseEntity<?> handleDownloadFile(Long id) {
        try {
            logger.debug("Downloads File with "+id);
            Optional<?> data = Optional.of(documentService.downloadFile(id)); // TODO: Add custom code here;
            ResponseEntity<?> response;

            if(data.isPresent()) {
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
    public ResponseEntity<?> handleUploadComplaintDocument(Long complaintId, MultipartFile file) {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public ResponseEntity<?> handleUploadComplaintReplyDocument(Long complaintReplyId, MultipartFile file) {
        // TODO Auto-generated method stub
        return null;
    }
}