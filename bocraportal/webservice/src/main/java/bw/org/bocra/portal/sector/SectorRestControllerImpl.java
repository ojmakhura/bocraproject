// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by SpringWSImpl.java.vsl in andromda-webservices.
//
package bw.org.bocra.portal.sector;

import java.util.Collection;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bw.org.bocra.portal.licensee.sector.LicenseeSectorVO;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/sector")
@Tag(name = "Sector", description = "Managing sectors.")
public class SectorRestControllerImpl extends SectorRestControllerBase {

    public SectorRestControllerImpl(SectorService sectorService) {
        super(sectorService);
    }

    @Override
    public ResponseEntity<?> handleFindById(Long id) {
        Optional<SectorVO> data = Optional.of(sectorService.findById(id)); // TODO: Add custom code here;
        ResponseEntity<SectorVO> response;

        if(data.isPresent()) {
            response = ResponseEntity.status(HttpStatus.OK).body(data.get());
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }

    @Override
    public ResponseEntity<?> handleGetAll() {
        Optional<Collection<SectorVO>> data = Optional.of(sectorService.getAll()); // TODO: Add custom code here;
        ResponseEntity<Collection<SectorVO>> response;

        if(data.isPresent()) {
            response = ResponseEntity.status(HttpStatus.OK).body(data.get());
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }

    @Override
    public ResponseEntity<?> handleGetAllPaged(Integer pageNumber, Integer pageSize) {
        Optional<Collection<SectorVO>> data = Optional.of(sectorService.getAll(pageNumber, pageSize)); // TODO: Add custom code here;
        ResponseEntity<Collection<SectorVO>> response;

        if(data.isPresent()) {
            response = ResponseEntity.status(HttpStatus.OK).body(data.get());
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }

    @Override
    public ResponseEntity<?> handleRemove(Long id) {
        Optional<Boolean> data = Optional.of(sectorService.remove(id)); // TODO: Add custom code here;
        ResponseEntity<Boolean> response;

        if(data.isPresent()) {
            response = ResponseEntity.status(HttpStatus.OK).body(data.get());
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }

    @Override
    public ResponseEntity<?> handleSave(SectorVO sector) {
        Optional<SectorVO> data = Optional.of(sectorService.save(sector)); // TODO: Add custom code here;
        ResponseEntity<SectorVO> response;

        if(data.isPresent()) {
            response = ResponseEntity.status(HttpStatus.OK).body(data.get());
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }

    @Override
    public ResponseEntity<?> handleSearch(String criteria) {
        Optional<Collection<SectorVO>> data = Optional.of(sectorService.search(criteria)); // TODO: Add custom code here;
        ResponseEntity<Collection<SectorVO>> response;

        if(data.isPresent()) {
            response = ResponseEntity.status(HttpStatus.OK).body(data.get());
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }

    @Override
    public ResponseEntity<?> handleAddLicensee(Long sectorId, Long licenseeId) {

        LicenseeSectorVO lvo = getSectorService().addLicensee(sectorId, licenseeId);

        if(lvo == null || lvo.getId() == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

        } else {
            return ResponseEntity.ok().body(lvo);
        }
    }
}