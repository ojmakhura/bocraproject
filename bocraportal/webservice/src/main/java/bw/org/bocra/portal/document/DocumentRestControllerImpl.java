// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by SpringWSImpl.java.vsl in andromda-webservices.
//
package bw.org.bocra.portal.document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import javax.persistence.EntityNotFoundException;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.exception.ConstraintViolationException;
import org.keycloak.representations.AccessToken;
import org.postgresql.util.PSQLException;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
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
import bw.org.bocra.portal.licensee.shares.LicenseeShareholderService;
import bw.org.bocra.portal.licensee.shares.LicenseeShareholderVO;
import bw.org.bocra.portal.shareholder.ShareholderService;
import bw.org.bocra.portal.shareholder.ShareholderVO;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/document")
@CrossOrigin()
@Tag(name = "Document", description = "Managing the documents.")
public class DocumentRestControllerImpl extends DocumentRestControllerBase {

    private final KeycloakService keycloakService;
    private final LicenseeService licenseeService;
    private final LicenceService licenceService;
    private final ShareholderService shareholderService;
    private final LicenseeShareholderService licenseeShareholderService;

    public DocumentRestControllerImpl(DocumentService documentService, ComplaintService complaintService,
            KeycloakService keycloakService, LicenseeService licenseeService, LicenceService licenceService,
            ShareholderService shareholderService, LicenseeShareholderService licenseeShareholderService) {
        super(documentService, complaintService);
        this.keycloakService = keycloakService;
        this.licenseeService = licenseeService;
        this.licenceService = licenceService;
        this.shareholderService = shareholderService;
        this.licenseeShareholderService = licenseeShareholderService;
    }

    @Override
    public ResponseEntity<?> handleFindById(Long id) {
        try {
            logger.debug("Searches for document using ID " + id);
            Optional<?> data = Optional.of(documentService.findById(id));
            ResponseEntity<?> response;

            if (data.isPresent()) {
                response = ResponseEntity.ok().body(data.get());
            } else {
                response = ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(String.format("Document with id %ld not found.", id));
            }

            return response;
        } catch (Exception e) {
            logger.error(e.getMessage());
            String message = e.getMessage();
            if (e instanceof NoSuchElementException || e.getCause() instanceof NoSuchElementException
                    || e instanceof EntityNotFoundException || e.getCause() instanceof EntityNotFoundException) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(String.format("Document with id %d not found.", id));
            } else if(e instanceof DocumentServiceException || e.getCause() instanceof DocumentServiceException) {
                return ResponseEntity.badRequest().body(e.getMessage());
            } else {
                message = "An unknown error has occured. Please contact the system administrator.";
            }

            logger.error(message);
            return ResponseEntity.badRequest().body(message);
        }
    }

    @Override
    public ResponseEntity<?> handleGetAll() {
        try {
            logger.debug("Displays all document");
            return ResponseEntity.ok().body(documentService.getAll());
        } catch (Exception e) {
            logger.error(e.getMessage()); 
            if(e instanceof DocumentServiceException || e.getCause() instanceof DocumentServiceException) {
                return ResponseEntity.badRequest().body(e.getMessage()); 
            }
            return ResponseEntity.badRequest()
                    .body("An unknown error has occured. Please contact the system administrator.");
        }
    }

    @Override
    public ResponseEntity<?> handleGetAllPaged(Integer pageNumber, Integer pageSize) {
        try {
            logger.debug("Displays all document of the specified Page number: " + pageNumber
                    + " and Page size: " + pageSize);
            return ResponseEntity.ok().body(documentService.getAll(pageNumber, pageSize));
        } catch (Exception e) {
            logger.error(e.getMessage());
            if(e instanceof DocumentServiceException || e.getCause() instanceof DocumentServiceException) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
            return ResponseEntity.badRequest()
                    .body("An unknown error has occured. Please contact the system administrator.");
        }
    }

    @Override
    public ResponseEntity<?> handleRemove(String documentId) {
        try {
            logger.debug("Deletes document by ID " + documentId);

            if (documentService.remove(documentId)) {
                return ResponseEntity.ok().body("Document successfully deleted.");
            } else {
                return ResponseEntity.badRequest().body("Could not delete document");
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());

            if (e instanceof EmptyResultDataAccessException || e.getCause() instanceof EmptyResultDataAccessException) {
                return ResponseEntity.badRequest().body("Could not delete document with id " + documentId);
            } else if(e instanceof DocumentServiceException || e.getCause() instanceof DocumentServiceException) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }

            return ResponseEntity.badRequest()
                    .body("Unknown error encountered when deleting document with id " + documentId);
        }
    }

    @Override
    public ResponseEntity<?> handleSave(DocumentVO document) {
        try {
            logger.debug("Saves document " + document);
            Optional<?> data = Optional.of(documentService.save(document));
            ResponseEntity<?> response;

            if (data.isPresent()) {
                response = ResponseEntity.ok().body(data.get());
            } else {
                response = ResponseEntity.badRequest().body("Could not save the document.");
            }

            return response;
        } catch (IllegalArgumentException | DocumentServiceException e) {

            e.printStackTrace();

            String message = e.getMessage();

            if (e instanceof IllegalArgumentException || e.getCause() instanceof IllegalArgumentException) {

                if (message.contains("'document'")) {

                    message = "The document information is missing.";

                } else if (message.contains("'document.documentName'")) {

                    message = "The document name is missing.";

                } else if (message.contains("'document.documentType'")) {

                    message = "The document type is missing.";

                } else if (message.contains("'document.file'")) {

                    message = "The document contents are missing.";

                } else {
                    message = "An unknown error has occured. Please contact the system administrator.";
                }

                return ResponseEntity.badRequest().body(message);

            } else if (e.getCause() instanceof PSQLException) {

                if (e.getCause().getMessage().contains("duplicate key")) {
                    if (e.getCause().getMessage().contains("(document_id)")) {

                        return ResponseEntity.badRequest()
                                .body("An document with this document id has been already created.");
                    }

                } else if (e.getCause().getMessage().contains("null value in column")) {

                    if (e.getCause().getMessage().contains("column \"created_by\"")) {
                        return ResponseEntity.badRequest().body("The created-by value is missing.");
                    } else if (e.getCause().getMessage().contains("column \"created_date\"")) {
                        return ResponseEntity.badRequest().body("The created date value is missing.");
                    } else if (e.getCause().getMessage().contains("column \"document_name\"")) {
                        return ResponseEntity.badRequest().body("The document name is missing.");
                    } else if (e.getCause().getMessage().contains("column \"document_type_fk\"")) {
                        return ResponseEntity.badRequest()
                                .body("The document type value is missing.");
                    }

                } else if(e instanceof DocumentServiceException || e.getCause() instanceof DocumentServiceException) {
                    return ResponseEntity.badRequest().body(e.getMessage());
                }

                return ResponseEntity.badRequest()
                        .body("This access point is conflicting with an existing one.");
            }

            return ResponseEntity.badRequest()
                    .body("An unknown database error has occured. Please contact the portal administrator.");
        } catch (Exception e) {

            e.printStackTrace();
            // e.getCause().printStackTrace();
            return ResponseEntity.badRequest()
                    .body("An unknown error has occured. Please contact the portal administrator.");
        }
    }

    @Override
    public ResponseEntity<?> handleSearch(DocumentCriteria criteria) {
        try {
            System.out.println(criteria);
            if(criteria == null) {
                return ResponseEntity.ok().body(documentService.getAll());
            }

            logger.debug("Searches Document by " + criteria);
            return ResponseEntity.ok().body(documentService.search(criteria));
        } catch (Exception e) {
            logger.error(e.getMessage());
            if(e instanceof DocumentServiceException || e.getCause() instanceof DocumentServiceException) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
            return ResponseEntity.badRequest().body("An unknown error has occurred. Please contact the site administrator.");
        }
    }

    @Override
    public ResponseEntity<?> handleDownloadFile(String documentId) {
        try {
            logger.debug("Downloads File with " + documentId);
            DocumentVO file = documentService.findByDocumentId(documentId);
        
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(file.getContentType()))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getDocumentName() + "\"")
                    .body(new ByteArrayResource(documentService.downloadFile(documentId)));

        } catch (Exception e) {
            logger.error(e.getMessage());
            if(e instanceof DocumentServiceException || e.getCause() instanceof DocumentServiceException) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
            return ResponseEntity.badRequest().body("An unknown error has occurred. Please contact the site administrator.");
        }
    }

    @Override
    public ResponseEntity<?> handleUploadFile(Long documentTypeId, MultipartFile file, String fileName,
            DocumentMetadataTarget metadataTarget, Long metadataTargetId) {
        try {
            logger.debug("Upload Complaint Document with name : " + fileName);
            AccessToken token = keycloakService.getSecurityContext().getToken();
            System.out.println(file.getContentType());
            DocumentVO document = new DocumentVO();
            document.setCreatedBy(token.getPreferredUsername());
            document.setCreatedDate(LocalDateTime.now());
            document.setFile(file.getBytes());
            document.setMetadataTarget(metadataTarget);
            document.setMetadataTargetId(metadataTargetId);
            document.setSize(file.getSize());
            document.setContentType(file.getContentType());
                                    
            DocumentTypeVO documentType = new DocumentTypeVO();
            documentType.setId(documentTypeId);

            document.setDocumentType(documentType);
            document.setDocumentName(fileName);

            Optional<?> data = Optional.of(documentService.save(document));
            ResponseEntity<?> response;

            if (data.isPresent()) {
                response = ResponseEntity.ok().body(data.get());
                if (metadataTarget == DocumentMetadataTarget.LICENSEE) {
                    LicenseeVO licensee = licenseeService.findById(metadataTargetId);
                    if (licensee.getDocuments() == null) {
                        licensee.setDocuments(new ArrayList<>());
                    }

                    licensee.getDocuments().add((DocumentVO) data.get());
                    licenseeService.save(licensee);

                } else if (metadataTarget == DocumentMetadataTarget.LICENCE) {
                    LicenceVO licence = licenceService.findById(metadataTargetId);
                    if (licence.getDocuments() == null) {
                        licence.setDocuments(new ArrayList<>());
                    }

                    licence.getDocuments().add((DocumentVO) data.get());
                    licenceService.save(licence);

                } else if (metadataTarget == DocumentMetadataTarget.COMPLAINT) {
                    ComplaintVO complaint = complaintService.findById(metadataTargetId);
                    if (complaint.getDocuments() == null) {
                        complaint.setDocuments(new ArrayList<>());
                    }

                    complaint.getDocuments().add((DocumentVO) data.get());
                    complaintService.save(complaint);
                }

                else if (metadataTarget == DocumentMetadataTarget.SHAREHOLDER) {
                    ShareholderVO holder = shareholderService.findById(metadataTargetId);
                    if (holder.getDocuments() == null) {
                        holder.setDocuments(new ArrayList<>());
                    }

                    holder.getDocuments().add((DocumentVO) data.get());
                    shareholderService.save(holder);
                }

                else if (metadataTarget == DocumentMetadataTarget.LICENSEE_SHAREHOLDER) {
                    LicenseeShareholderVO ls = licenseeShareholderService.findById(metadataTargetId);
                    if (ls.getDocuments() == null) {
                        ls.setDocuments(new ArrayList<>());
                    }

                    ls.getDocuments().add((DocumentVO) data.get());
                    licenseeShareholderService.save(ls);
                }

            } else {
                response = ResponseEntity.badRequest().body("An unknown error occured when uploading a file.");
            }

            return response;
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            if(e instanceof DocumentServiceException || e.getCause() instanceof DocumentServiceException) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
            return ResponseEntity.badRequest().body("An unknown error occured when uploading a file.");
        }
    }

    @Override
    public ResponseEntity<?> handleFindByDocumentIds(Set<String> documentIds) {
                
        try {
            
            Collection<DocumentVO> docs = documentService.findByDocumentIds(documentIds);

            if(CollectionUtils.isEmpty(docs)) {
                return ResponseEntity.badRequest().body("No documents found.");
            }

            return ResponseEntity.ok(docs);
        } catch(Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            if(e instanceof DocumentServiceException || e.getCause() instanceof DocumentServiceException) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
            return ResponseEntity.badRequest().body("An unknown error occured. Please contact the site administrator.");

        }
    }

    @Override
    public ResponseEntity<?> handleFindByIds(Set<Long> ids) {
                
        try {
            
            Collection<DocumentVO> docs = documentService.findByIds(ids);

            if(CollectionUtils.isEmpty(docs)) {
                return ResponseEntity.badRequest().body("No documents found.");
            }

            return ResponseEntity.ok(docs);
        } catch(Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            if(e instanceof DocumentServiceException || e.getCause() instanceof DocumentServiceException) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
            return ResponseEntity.badRequest().body("An unknown error occured. Please contact the site administrator.");

        }
    }

    @Override
    public ResponseEntity<?> handleFindDocumentsByMetadata(DocumentMetadataTarget metadataTarget,
            Long metadataTargetId) {
                
        try {
            DocumentCriteria criteria = new DocumentCriteria();
            criteria.setMetadataTarget(metadataTarget);
            return this.search(criteria);
        } catch(Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            if(e instanceof DocumentServiceException || e.getCause() instanceof DocumentServiceException) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
            return ResponseEntity.badRequest().body("An unknown error occured. Please contact the site administrator.");

        }
    }

    @Override
    public ResponseEntity<?> handleFindByDocumentId(String documentId) {
        
        try {
            return ResponseEntity.ok().body(documentService.findByDocumentId(documentId));
        } catch(Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            if(e instanceof DocumentServiceException || e.getCause() instanceof DocumentServiceException) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
            return ResponseEntity.badRequest().body("An unknown error occured. Please contact the site administrator.");

        }
    }
}