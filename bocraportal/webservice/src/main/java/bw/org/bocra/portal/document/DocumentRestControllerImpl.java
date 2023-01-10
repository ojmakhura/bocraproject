// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by SpringWSImpl.java.vsl in andromda-webservices.
//
package bw.org.bocra.portal.document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import javax.persistence.EntityNotFoundException;

import org.hibernate.exception.ConstraintViolationException;
import org.keycloak.representations.AccessToken;
import org.postgresql.util.PSQLException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import bw.org.bocra.portal.complaint.ComplaintService;
import bw.org.bocra.portal.keycloak.KeycloakService;
import bw.org.bocra.portal.complaint.ComplaintVO;
import bw.org.bocra.portal.document.type.DocumentTypeVO;
import bw.org.bocra.portal.licence.LicenceService;
import bw.org.bocra.portal.licence.LicenceVO;
import bw.org.bocra.portal.licensee.LicenseeService;
import bw.org.bocra.portal.licensee.LicenseeVO;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/document")
@CrossOrigin()
@Tag(name = "Document", description = "Managing the documents.")
public class DocumentRestControllerImpl extends DocumentRestControllerBase {

    private final KeycloakService keycloakService;
    private final LicenseeService licenseeService;
    private final LicenceService licenceService;

    public DocumentRestControllerImpl(DocumentService documentService, ComplaintService complaintService,
            KeycloakService keycloakService, LicenseeService licenseeService, LicenceService licenceService) {
        super(documentService, complaintService);
        this.keycloakService = keycloakService;
        this.licenseeService = licenseeService;
        this.licenceService = licenceService;
    }

    @Override
    public ResponseEntity<?> handleFindById(Long id) {
        try {
            logger.debug("Searches for document using ID " + id);
            Optional<?> data = Optional.of(documentService.findById(id));
            ResponseEntity<?> response;

            if (data.isPresent()) {
                response = ResponseEntity.status(HttpStatus.OK).body(data.get());
            } else {
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("Document with id %ld not found.", id));
            }

            return response;
        } catch (Exception e) {
            logger.error(e.getMessage());
            String message = e.getMessage();
            if (e instanceof NoSuchElementException || e.getCause() instanceof NoSuchElementException
                    || e instanceof EntityNotFoundException || e.getCause() instanceof EntityNotFoundException) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("Document with id %d not found.", id));
            } else {
                message = "An unknown error has occured. Please contact the system administrator.";
            }

            logger.error(message);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
    }

    @Override
    public ResponseEntity<?> handleGetAll() {
        try {
            logger.debug("Displays all document");
            return  ResponseEntity.status(HttpStatus.OK).body(documentService.getAll());
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An unknown error has occured. Please contact the system administrator.");
        }
    }

    @Override
    public ResponseEntity<?> handleGetAllPaged(Integer pageNumber, Integer pageSize) {
        try {
            logger.debug("Displays all document of the specified Page number: " + pageNumber
                    + " and Page size: " + pageSize);
            return  ResponseEntity.status(HttpStatus.OK).body(documentService.getAll(pageNumber, pageSize));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An unknown error has occured. Please contact the system administrator.");
        }
    }

    @Override
    public ResponseEntity<?> handleRemove(Long id) {
        try {
            logger.debug("Deletes document by ID " + id);
            

            if (documentService.remove(id)) {
                return ResponseEntity.status(HttpStatus.OK).body("Document successfully deleted.");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Could not delete document");
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());

            if(e instanceof EmptyResultDataAccessException || e.getCause() instanceof EmptyResultDataAccessException) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Could not delete document with id " + id);
            }

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unknown error encountered when deleting document with id " + id);
        }
    }

    @Override
    public ResponseEntity<?> handleSave(DocumentVO document) {
        try {
            logger.debug("Saves document " + document);
            Optional<?> data = Optional.of(documentService.save(document));
            ResponseEntity<?> response;

            if (data.isPresent()) {
                response = ResponseEntity.status(HttpStatus.OK).body(data.get());
            } else {
                response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Could not save the document.");
            }

            return response;
        } catch (IllegalArgumentException | DocumentServiceException e) {

            e.printStackTrace();

            String message = e.getMessage();

            if(e instanceof IllegalArgumentException || e.getCause() instanceof IllegalArgumentException) {

                if(message.contains("'document'")) {

                    message = "The document information is missing.";

                } else if(message.contains("'document.documentName'")) {
                
                    message = "The document name is missing.";
                
                } else if(message.contains("'document.documentType'")) {
                  
                    message = "The document type is missing.";
                
                } else if(message.contains("'document.file'")) {
                  
                    message = "The document contents are missing.";
                
                } else {
                    message = "An unknown error has occured. Please contact the system administrator.";
                }

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);

            } else if(e.getCause() instanceof PSQLException) {

                if (e.getCause().getMessage().contains("duplicate key")) {
                    if(e.getCause().getMessage().contains("(document_id)")) {

                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An document with this document id has been already created.");
                    }
                    
                } else if (e.getCause().getMessage().contains("null value in column")) {

                    if (e.getCause().getMessage().contains("column \"created_by\"")) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The created-by value is missing.");
                    } else if (e.getCause().getMessage().contains("column \"created_date\"")) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The created date value is missing.");
                    } else if (e.getCause().getMessage().contains("column \"document_name\"")) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The document name is missing.");
                    } else if (e.getCause().getMessage().contains("column \"document_type_fk\"")) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The document type value is missing.");
                    }

                }
                
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This access point is conflicting with an existing one.");
            } 

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An unknown database error has occured. Please contact the portal administrator.");
        } catch(Exception e) {

            e.printStackTrace();
            // e.getCause().printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An unknown error has occured. Please contact the portal administrator.");
        }
    }

    @Override
    public ResponseEntity<?> handleSearch(DocumentCriteria criteria) {
        try {
            logger.debug("Searches Document by " + criteria);
            Optional<?> data = Optional.of(documentService.search(criteria));
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
    public ResponseEntity<?> handleUploadLicenceDocument(Long licenceId, MultipartFile file) {
        try {
            logger.debug("Upload Licence Document with Licence Id: " + licenceId + " and a File: " + file);
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
    public ResponseEntity<?> handleUploadLicenseeDocument(Long licenseeId, MultipartFile file) {
        try {
            logger.debug("Upload Licensee Document with Licensee Id" + licenseeId + " File:" + file);
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
    public ResponseEntity<?> handleDownloadFile(Long id) {
        try {
            logger.debug("Downloads File with " + id);
            byte[] file = documentService.downloadFile(id);

            return ResponseEntity.status(HttpStatus.OK)
                    .contentType(MediaType.MULTIPART_MIXED)
                    .body(file);

            // ResponseEntity<?> response;

            // if (data.isPresent()) {
            //     response = ResponseEntity.status(HttpStatus.OK).body(data.get());
            // } else {
            //     response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            // }

            // return response;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> handleUploadComplaintDocument(Long complaintId, MultipartFile file) {
        try {
            logger.debug("Upload Complaint Document with Complaint Id: " + complaintId + " and a File: " + file);
            AccessToken token = keycloakService.getSecurityContext().getToken();
            DocumentVO document = new DocumentVO();
            document.setCreatedBy(token.getPreferredUsername());
            document.setCreatedDate(LocalDateTime.now());
            document.setFile(file.getBytes());
            ComplaintVO complaint = new ComplaintVO();
            complaint.setId(complaintId);
            document.setComplaint(complaint);
            Optional<?> data = Optional.of(documentService.save(document));
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
    public ResponseEntity<?> handleUploadFile(Long documentTypeId, MultipartFile file, String fileName,
            DocumentMetadataTarget metadataTarget, Long metadataTargetId) {
        try {
            logger.debug("Upload Complaint Document with name : " + fileName );
            AccessToken token = keycloakService.getSecurityContext().getToken();
            DocumentVO document = new DocumentVO();
            document.setCreatedBy(token.getPreferredUsername());
            document.setCreatedDate(LocalDateTime.now());
            document.setFile(file.getBytes());
            document.setMetadataTarget(metadataTarget);
            document.setMetadataTargetId(metadataTargetId);
            document.setSize(file.getSize());
            DocumentTypeVO documentType = new DocumentTypeVO();
            documentType.setId(documentTypeId);

            document.setDocumentType(documentType);
            document.setDocumentName(fileName);

            System.out.println(document);
            
            // document.setComplaint(complaint);
            Optional<?> data = Optional.of(documentService.save(document));
            ResponseEntity<?> response;

            if (data.isPresent()) {
                response = ResponseEntity.status(HttpStatus.OK).body(data.get());
                if(metadataTarget == DocumentMetadataTarget.LICENSEE) {
                    LicenseeVO licensee = licenseeService.findById(metadataTargetId);
                    if(licensee.getDocuments() == null) {
                        licensee.setDocuments(new ArrayList<>());
                    }

                    licensee.getDocuments().add((DocumentVO) data.get());
                    licenseeService.save(licensee);

                } else if(metadataTarget == DocumentMetadataTarget.LICENCE) {
                    LicenceVO licence = licenceService.findById(metadataTargetId);
                    if(licence.getDocuments() == null) {
                        licence.setDocuments(new ArrayList<>());
                    }

                    licence.getDocuments().add((DocumentVO) data.get());
                    licenceService.save(licence);

                } else if(metadataTarget == DocumentMetadataTarget.COMPLAINT) {
                    ComplaintVO complaint = complaintService.findById(metadataTargetId);
                    if(complaint.getDocuments() == null) {
                        complaint.setDocuments(new ArrayList<>());
                    }

                    complaint.getDocuments().add((DocumentVO) data.get());
                    complaintService.save(complaint);
                }


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
    public ResponseEntity<?> handleFindByDocumentIds(Set<String> documentIds) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResponseEntity<?> handleFindByIds(Set<Long> ids) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResponseEntity<?> handleFindDocumentsByMetadata(DocumentMetadataTarget metadataTarget,
            Long metadataTargetId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResponseEntity<?> handleUploadComplaintReplyDocument(Long complaintReplyId, MultipartFile file) {
        // TODO Auto-generated method stub
        return null;
    }
}