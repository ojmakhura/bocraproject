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
import bw.org.bocra.portal.licence.LicenceVO;
import bw.org.bocra.portal.licensee.shares.ShareholderVO;
import bw.org.bocra.portal.report.ReportVO;
import bw.org.bocra.portal.report.config.ReportConfigVO;
import bw.org.bocra.portal.sector.SectorVO;

@RestController
@RequestMapping("/licensee")
// @CrossOrigin()
public class LicenseeRestControllerImpl extends LicenseeRestControllerBase {

    protected static Logger log = LoggerFactory.getLogger(LicenseeRestControllerImpl.class);


    @Override
    public ResponseEntity<LicenseeVO> handleFindById(Long id) {
        Optional<LicenseeVO> data = Optional.of(this.licenseeService.findById(id)); // TODO: Add custom code here;
        ResponseEntity<LicenseeVO> response;

        if(data.isPresent()) {
            response = ResponseEntity.status(HttpStatus.OK).body(data.get());
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }

    @Override
    public ResponseEntity<Collection<LicenseeVO>> handleGetAll() {
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
    public ResponseEntity<Boolean> handleRemove(Long id) {
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
    public ResponseEntity<LicenseeVO> handleSave(LicenseeVO licenseeVO) {
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
    public ResponseEntity<Collection<LicenseeVO>> handleSearch(LicenseeCriteria criteria) {
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
    public ResponseEntity<Collection<LicenseeVO>> handleGetAllPaged(Integer pageNumber, Integer pageSize) {
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
    public ResponseEntity<Collection<DocumentVO>> handleGetDocuments(Long id) {
        Collection<DocumentVO> docs = licenseeService.getDocuments(id);

        if(CollectionUtils.isEmpty(docs)) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.ok().body(docs);
        }

    }

    @Override
    public ResponseEntity<Collection<FormVO>> handleGetForms(Long id) {
        Collection<FormVO> vos = licenseeService.getForms(id);

        if(CollectionUtils.isEmpty(vos)) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.ok().body(vos);
        }
    }

    @Override
    public ResponseEntity<Collection<FormSubmissionVO>> handleGetFormSubmissions(Long id) {
        Collection<FormSubmissionVO> vos = licenseeService.getFormSubmissions(id);

        if(CollectionUtils.isEmpty(vos)) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.ok().body(vos);
        }
    }

    @Override
    public ResponseEntity<Collection<LicenceVO>> handleGetLicences(Long id) {
        Collection<LicenceVO> vos = licenseeService.getLicences(id);

        if(CollectionUtils.isEmpty(vos)) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.ok().body(vos);
        }
    }

    @Override
    public ResponseEntity<Collection<ReportConfigVO>> handleGetReportConfigurations(Long id) {
        Collection<ReportConfigVO> vos = licenseeService.getReportConfigurations(id);

        if(CollectionUtils.isEmpty(vos)) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.ok().body(vos);
        }
    }

    @Override
    public ResponseEntity<Collection<ReportVO>> handleGetReports(Long id) {
        Collection<ReportVO> vos = licenseeService.getReports(id);

        if(CollectionUtils.isEmpty(vos)) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.ok().body(vos);
        }
    }

    @Override
    public ResponseEntity<Collection<SectorVO>> handleGetSectors(Long id) {
        Collection<SectorVO> vos = licenseeService.getSectors(id);

        if(CollectionUtils.isEmpty(vos)) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.ok().body(vos);
        }
    }

    @Override
    public ResponseEntity<Collection<ShareholderVO>> handleGetShareholders(Long id) {
        Collection<ShareholderVO> vos = licenseeService.getShareholders(id);

        if(CollectionUtils.isEmpty(vos)) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.ok().body(vos);
        }
    }

    // @Override
    // public ResponseEntity<LicenseeVO> handleUpdateLicensee(LicenseeVO licenseeVO) {
    //     Optional<LicenseeVO> data = Optional.empty(); // TODO: Add custom code here;
    //     ResponseEntity<LicenseeVO> response;

    //     if(data.isPresent()) {
    //         response = ResponseEntity.status(HttpStatus.OK).body(data.get());
    //     } else {
    //         response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    //     }

    //     return response;
    // }
}