// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by SpringService.vsl in andromda-spring-cartridge on $springUtils.date. Do not modify by hand!.
//
package bw.org.bocra.portal.sector;

import bw.org.bocra.portal.licensee.LicenseeDao;
import bw.org.bocra.portal.licensee.LicenseeRepository;
import bw.org.bocra.portal.licensee.sector.LicenseeSectorDao;
import bw.org.bocra.portal.licensee.sector.LicenseeSectorRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class SectorServiceTest {

    protected Logger logger = LoggerFactory.getLogger(SectorServiceTest.class);
    
    @InjectMocks
    private SectorServiceImpl sectorService;

    @Mock
    private SectorDao sectorDao;

    @Mock
    private SectorRepository sectorRepository;
    @Mock
    private LicenseeSectorDao licenseeSectorDao;

    @Mock
    private LicenseeSectorRepository licenseeSectorRepository;
    @Mock
    private LicenseeDao licenseeDao;

    @Mock
    private LicenseeRepository licenseeRepository;

    @Test
    public void checkInjects() {

        Assertions.assertNotNull(sectorDao);
        Assertions.assertNotNull(sectorRepository);
        Assertions.assertNotNull(licenseeSectorDao);
        Assertions.assertNotNull(licenseeSectorRepository);
        Assertions.assertNotNull(licenseeDao);
        Assertions.assertNotNull(licenseeRepository);
        Assertions.assertNotNull(sectorService);

    }
    

    @Test
    public void findById_success() {
        
    }

    @Test
    public void findById_fail() {
        
    }


    @Test
    public void save_success() {
        
    }

    @Test
    public void save_fail() {
        
    }

    @Test
    public void save_sector_null() {
        
    }

    @Test
    public void save_Sector_Id_null() {
        
    }

    @Test
    public void save_Sector_CreatedBy_null() {
        
    }

    @Test
    public void save_Sector_UpdatedBy_null() {
        
    }

    @Test
    public void save_Sector_CreatedDate_null() {
        
    }

    @Test
    public void save_Sector_UpdatedDate_null() {
        
    }

    @Test
    public void save_Sector_Code_null() {
        
    }

    @Test
    public void save_Sector_Name_null() {
        
    }

    @Test
    public void save_Sector_ThemeColour_null() {
        
    }

    @Test
    public void save_Sector_Description_null() {
        
    }

    @Test
    public void save_Sector_Licensees_null() {
        
    }

    @Test
    public void save_Sector_Forms_null() {
        
    }


    @Test
    public void remove_success() {
        
    }

    @Test
    public void remove_fail() {
        
    }


    @Test
    public void getAll_success() {
        
    }

    @Test
    public void getAll_fail() {
        
    }


    @Test
    public void search_success() {
        
    }

    @Test
    public void search_fail() {
        
    }

    @Test
    public void addLicensee_success() {
        
    }

    @Test
    public void addLicensee_fail() {
        
    }

}