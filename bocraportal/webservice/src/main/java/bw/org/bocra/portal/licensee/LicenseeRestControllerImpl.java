// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by SpringWSImpl.java.vsl in andromda-webservices.
//
package bw.org.bocra.portal.licensee;

import java.util.Collection;
import java.util.Optional;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bw.org.bocra.portal.document.DocumentVO;
import bw.org.bocra.portal.form.FormVO;
import bw.org.bocra.portal.form.submission.FormSubmissionVO;
import bw.org.bocra.portal.keycloak.KeycloakUserService;
import bw.org.bocra.portal.licence.LicenceVO;
import bw.org.bocra.portal.licensee.sector.LicenseeSectorVO;
import bw.org.bocra.portal.licensee.shares.ShareholderVO;
import bw.org.bocra.portal.report.ReportVO;
import bw.org.bocra.portal.report.config.ReportConfigVO;
import bw.org.bocra.portal.sector.SectorVO;
import bw.org.bocra.portal.user.LicenseeUserService;
import bw.org.bocra.portal.user.UserVO;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/licensee")
@Tag(name = "Licensee", description = "Managing licensees.")
@CrossOrigin()
public class LicenseeRestControllerImpl extends LicenseeRestControllerBase {

    private final KeycloakUserService keycloakUserService;
    
    public LicenseeRestControllerImpl(LicenseeService licenseeService, LicenseeUserService licenseeUserService, KeycloakUserService keycloakUserService) {
        super(licenseeService, licenseeUserService);
        this.keycloakUserService = keycloakUserService;
    }

    protected static Logger log = LoggerFactory.getLogger(LicenseeRestControllerImpl.class);

    @Override
    public ResponseEntity<?> handleFindById(Long id) {
        LicenseeVO licensee = this.licenseeService.findById(id);

        if(licensee == null || licensee.getId() == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Collection<UserVO> users = this.keycloakUserService.getLicenseeUsers(licensee.getId());
        licensee.setUsers(users);
        
        return ResponseEntity.status(HttpStatus.OK).body(licensee);
    }

    @Override
    public ResponseEntity<?> handleGetAll() {
        Optional<Collection<LicenseeVO>> data = Optional.of(this.licenseeService.getAll()); // TODO: Add custom code here;
        ResponseEntity<Collection<LicenseeVO>> response;

        if(data.isPresent()) {
            response = ResponseEntity.status(HttpStatus.OK).body(data.get());
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }

    @Override
    public ResponseEntity<?> handleRemove(Long id) {
        Optional<Boolean> data = Optional.of(this.licenseeService.remove(id)); // TODO: Add custom code here;
        ResponseEntity<Boolean> response;

        if(data.isPresent()) {
            response = ResponseEntity.status(HttpStatus.OK).body(data.get());
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }

    @Override
    public ResponseEntity<?> handleSave(LicenseeVO licenseeVO) {
        Optional<LicenseeVO> data = Optional.of(this.licenseeService.save(licenseeVO)); // TODO: Add custom code here;
        ResponseEntity<LicenseeVO> response;

        if(data.isPresent()) {
            response = ResponseEntity.status(HttpStatus.OK).body(data.get());
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }

    @Override
    public ResponseEntity<?> handleSearch(LicenseeCriteria criteria) {
        Optional<Collection<LicenseeVO>> data = Optional.of(this.licenseeService.search(criteria)); // TODO: Add custom code here;
        ResponseEntity<Collection<LicenseeVO>> response;

        if(data.isPresent()) {
            response = ResponseEntity.status(HttpStatus.OK).body(data.get());
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }

    @Override
    public ResponseEntity<?> handleGetAllPaged(Integer pageNumber, Integer pageSize) {
        Optional<Collection<LicenseeVO>> data = Optional.of(this.licenseeService.getAll(pageNumber, pageSize)); // TODO: Add custom code here;
        ResponseEntity<Collection<LicenseeVO>> response;

        if(data.isPresent()) {
            response = ResponseEntity.status(HttpStatus.OK).body(data.get());
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }

    @Override
    public ResponseEntity<?> handleGetDocuments(Long id) {
        Collection<DocumentVO> docs = licenseeService.getDocuments(id);

        if(CollectionUtils.isEmpty(docs)) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.ok().body(docs);
        }

    }

    @Override
    public ResponseEntity<?> handleGetForms(Long id) {
        Collection<FormVO> vos = licenseeService.getForms(id);

        if(CollectionUtils.isEmpty(vos)) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.ok().body(vos);
        }
    }

    @Override
    public ResponseEntity<?> handleGetFormSubmissions(Long id) {
        Collection<FormSubmissionVO> vos = licenseeService.getFormSubmissions(id);

        if(CollectionUtils.isEmpty(vos)) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.ok().body(vos);
        }
    }

    @Override
    public ResponseEntity<?> handleGetLicences(Long id) {
        Collection<LicenceVO> vos = licenseeService.getLicences(id);

        if(CollectionUtils.isEmpty(vos)) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.ok().body(vos);
        }
    }

    @Override
    public ResponseEntity<?> handleGetReportConfigurations(Long id) {
        Collection<ReportConfigVO> vos = licenseeService.getReportConfigurations(id);

        if(CollectionUtils.isEmpty(vos)) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.ok().body(vos);
        }
    }

    @Override
    public ResponseEntity<?> handleGetReports(Long id) {
        Collection<ReportVO> vos = licenseeService.getReports(id);

        if(CollectionUtils.isEmpty(vos)) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.ok().body(vos);
        }
    }

    @Override
    public ResponseEntity<?> handleGetSectors(Long id) {
        Collection<SectorVO> vos = licenseeService.getSectors(id);

        if(CollectionUtils.isEmpty(vos)) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.ok().body(vos);
        }
    }

    @Override
    public ResponseEntity<?> handleGetShareholders(Long id) {
        Collection<ShareholderVO> vos = licenseeService.getShareholders(id);

        if(CollectionUtils.isEmpty(vos)) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.ok().body(vos);
        }
    }

    @Override
    public ResponseEntity<?> handleAddSector(Long licenseeId, Long sectorId) {

        LicenseeSectorVO lvo = getLicenseeService().addSector(licenseeId, sectorId);

        if(lvo == null || lvo.getId() == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

        } else {
            return ResponseEntity.ok().body(lvo);
        }

    }

    // @Override
    // public ResponseEntity<?> handleAddForm(Long licenseeId, Long formId) {
    //     LicenseeFormVO vo = getLicenseeService().addForm(licenseeId, formId);

    //     if(vo == null || vo.getId() != null) {
    //         return ResponseEntity.noContent().build();
    //     }

    //     return ResponseEntity.ok(vo);
    // }

    // @Override
    // public ResponseEntity<?> handleRemoveForm(Long licenseeFormId) {
        
    //     return ResponseEntity.ok(licenseeService.removeForm(licenseeFormId));
    // }

    @Override
    public ResponseEntity<?> handleRemoveSector(Long licenseeSectorId) {
        return ResponseEntity.ok(getLicenseeService().removeSector(licenseeSectorId));
    }

    // @Override
    // public ResponseEntity<?> handleUpdateForm(Long licenseeFormId, Long formId) {
    //     return ResponseEntity.ok(licenseeService.updateForm(licenseeFormId, formId));
    // }

    // @Override
    // public ResponseEntity<?> handleUpdateSector(Long licenseeSectorId, Long sectorId) {
       
    //     return ResponseEntity.ok(licenseeService.updateSector(licenseeSectorId, sectorId));
    // }
}