// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by SpringWSImpl.java.vsl in andromda-webservices.
//
package bw.org.bocra.portal.sector;

import java.util.Collection;
import java.util.Optional;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bw.org.bocra.portal.licensee.sector.LicenseeSectorVO;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/sector")
@Tag(name = "Sector", description = "Managing sectors.")
@CrossOrigin()
public class SectorRestControllerImpl extends SectorRestControllerBase {

    public SectorRestControllerImpl(SectorService sectorService) {
        super(sectorService);
    }

    @Override
    public ResponseEntity<?> handleFindById(Long id) {
        try{
            logger.debug("Search Sector by Id "+id);
            Optional<SectorVO> data = Optional.of(sectorService.findById(id)); // TODO: Add custom code here;
            ResponseEntity<SectorVO> response;
    
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
        try{
            logger.debug("Display all Sectors");
            Optional<Collection<SectorVO>> data = Optional.of(sectorService.getAll()); // TODO: Add custom code here;
            ResponseEntity<Collection<SectorVO>> response;
    
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
        try{
            logger.debug("Display all Sectors with specified page number "+pageNumber+" and page size"+pageSize);
            Optional<Collection<SectorVO>> data = Optional.of(sectorService.getAll(pageNumber, pageSize)); // TODO: Add custom code here;
            ResponseEntity<Collection<SectorVO>> response;
    
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
        try{
            logger.debug("Delete Sector with Id "+id);
            Optional<Boolean> data = Optional.of(sectorService.remove(id)); // TODO: Add custom code here;
            ResponseEntity<Boolean> response;
    
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
    public ResponseEntity<?> handleSave(SectorVO sector) {
        try{
            logger.debug("Save Sector "+sector);
            Optional<SectorVO> data = Optional.of(sectorService.save(sector)); // TODO: Add custom code here;
            ResponseEntity<SectorVO> response;
    
            if(data.isPresent()) {
                response = ResponseEntity.status(HttpStatus.OK).body(data.get());
            } else {
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
    
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            if(e instanceof ConstraintViolationException) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This Sector has been already created.");
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
               
      }
     
    

    @Override
    public ResponseEntity<?> handleSearch(String criteria) {
        
        try {
            logger.debug("Search Sector by criteria "+criteria);
            Optional<Collection<SectorVO>> data = Optional.of(sectorService.search(criteria)); // TODO: Add custom code here;
            ResponseEntity<Collection<SectorVO>> response;
    
            if(data.isPresent()) {
                response = ResponseEntity.status(HttpStatus.OK).body(data.get());
            } else {
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
    
            return response;
            
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e.getMessage());
        }
    }
    
    

    @Override
    public ResponseEntity<?> handleAddLicensee(Long sectorId, Long licenseeId) {
        try{
            logger.debug("Add Licensee with sector Id "+sectorId+" and Licensee Id "+licenseeId );
        LicenseeSectorVO lvo = getSectorService().addLicensee(sectorId, licenseeId);

        if(lvo == null || lvo.getId() == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

        } else {
            return ResponseEntity.ok().body(lvo);
        }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e.getMessage());
        }
    }
}