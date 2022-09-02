// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by SpringWSImpl.java.vsl in andromda-webservices.
//
package bw.org.bocra.portal.licensee;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;

import org.keycloak.representations.AccessToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import bw.org.bocra.portal.document.DocumentService;
import bw.org.bocra.portal.document.DocumentVO;
import bw.org.bocra.portal.document.type.DocumentTypeVO;
import bw.org.bocra.portal.keycloak.KeycloakService;
import bw.org.bocra.portal.keycloak.KeycloakUserService;
import bw.org.bocra.portal.user.LicenseeUserService;
import bw.org.bocra.portal.user.UserVO;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/licensee")
@CrossOrigin()
@PreAuthorize("@securityCheck.isAuthorised('/licensee')")
@Tag(name = "Licensee", description = "Managing licensees.")
public class LicenseeRestControllerImpl extends LicenseeRestControllerBase {
    
    private final KeycloakUserService keycloakUserService;
    private final KeycloakService keycloakService;
    
    public LicenseeRestControllerImpl(LicenseeService licenseeService, LicenseeUserService licenseeUserService, DocumentService documentService, KeycloakUserService keycloakUserService, KeycloakService keycloakService) {
        
        super(licenseeService, licenseeUserService, documentService);
        this.keycloakUserService = keycloakUserService;
        this.keycloakService = keycloakService;
    }


    @Override
    public ResponseEntity<?> handleAddDocument(Long id, Long documentTypeId, MultipartFile file, String fileName) {
        try {
            AccessToken token = keycloakService.getSecurityContext().getToken();
            DocumentVO document = new DocumentVO();
            document.setCreatedBy(token.getPreferredUsername());
            document.setCreatedDate(LocalDateTime.now());
            document.setFile(file.getBytes());
            document.setDocumentName(fileName);
            
            LicenseeVO licensee = new LicenseeVO();
            licensee.setId(id);
            document.setLicensee(licensee);

            DocumentTypeVO docType = new DocumentTypeVO();
            docType.setId(documentTypeId);

            document.setDocumentType(docType);
            document = this.documentService.save(document);            
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

    @Override
    public ResponseEntity<?> handleAddSector(Long licenseeId, Long sectorId) {
        try {
            Optional<?> data = Optional.of(getLicenseeService().addSector(licenseeId, sectorId));
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
    public ResponseEntity<?> handleFindById(Long id) {
        try {

            LicenseeVO licensee = licenseeService.findById(id);
            ResponseEntity<?> response;

            if(licensee != null && licensee.getId() != null) {
                Collection<UserVO> users = this.keycloakUserService.getLicenseeUsers(licensee.getId());
                licensee.setUsers(users);
                response = ResponseEntity.status(HttpStatus.OK).body(licensee);
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
            Optional<?> data = Optional.of(this.licenseeService.getAll());
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
            Optional<?> data = Optional.of(this.licenseeService.getAll(pageNumber, pageSize));
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
    public ResponseEntity<?> handleGetDocuments(Long id) {
        try {
            Optional<?> data = Optional.of(this.licenseeService.getDocuments(id));
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
    public ResponseEntity<?> handleGetForms(Long id) {
        try {
            Optional<?> data = Optional.of(this.licenseeService.getForms(id));
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
    public ResponseEntity<?> handleGetFormSubmissions(Long id) {
        try {
            Optional<?> data = Optional.of(this.licenseeService.getFormSubmissions(id));
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
    public ResponseEntity<?> handleGetLicences(Long id) {
        try {
            Optional<?> data = Optional.of(this.licenseeService.getLicences(id));
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
    public ResponseEntity<?> handleGetReportConfigurations(Long id) {
        try {
            Optional<?> data = Optional.of(this.licenseeService.getReportConfigurations(id));
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
    public ResponseEntity<?> handleGetReports(Long id) {
        try {
            Optional<?> data = Optional.of(this.licenseeService.getReports(id));
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
    public ResponseEntity<?> handleGetSectors(Long id) {
        try {
            Optional<?> data = Optional.of(this.licenseeService.getSectors(id));
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
    public ResponseEntity<?> handleGetShareholders(Long id) {
        try {
            Optional<?> data = Optional.of(this.licenseeService.getShareholders(id));
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
            Optional<?> data = Optional.of(this.licenseeService.remove(id));
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
    public ResponseEntity<?> handleRemoveSector(Long licenseeSectorId) {
        try {
            Optional<?> data = Optional.of(this.licenseeService.removeSector(licenseeSectorId));
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
    public ResponseEntity<?> handleSave(LicenseeVO licensee) {
        try {
            Optional<?> data = Optional.of(this.licenseeService.save(licensee));
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
    public ResponseEntity<?> handleSearch(LicenseeCriteria criteria) {
        try {
            Optional<?> data = Optional.of(this.licenseeService.search(criteria));
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
}