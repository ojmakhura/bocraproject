// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by SpringService.vsl in andromda-spring-cartridge on $springUtils.date. Do not modify by hand!.
//
package bw.org.bocra.portal.licence;

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
public class LicenceServiceTest {

    protected Logger logger = LoggerFactory.getLogger(LicenceServiceTest.class);
    
    @InjectMocks
    private LicenceServiceImpl licenceService;

    @Mock
    private LicenceDao licenceDao;

    @Mock
    private LicenceRepository licenceRepository;

    @Test
    public void checkInjects() {

        Assertions.assertNotNull(licenceDao);
        Assertions.assertNotNull(licenceRepository);
        Assertions.assertNotNull(licenceService);

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
    public void save_licence_null() {
        
    }

    @Test
    public void save_Licence_Id_null() {
        
    }

    @Test
    public void save_Licence_CreatedBy_null() {
        
    }

    @Test
    public void save_Licence_UpdatedBy_null() {
        
    }

    @Test
    public void save_Licence_CreatedDate_null() {
        
    }

    @Test
    public void save_Licence_UpdatedDate_null() {
        
    }

    @Test
    public void save_Licence_LicenceType_null() {
        
    }

    @Test
    public void save_Licence_Licensee_null() {
        
    }

    @Test
    public void save_Licence_Status_null() {
        
    }

    @Test
    public void save_Licence_LicenceNumber_null() {
        
    }

    @Test
    public void save_Licence_Provisional_null() {
        
    }

    @Test
    public void save_Licence_StartDate_null() {
        
    }

    @Test
    public void save_Licence_EndDate_null() {
        
    }

    @Test
    public void save_Licence_Documents_null() {
        
    }


    @Test
    public void remove_success() {
        
    }

    @Test
    public void remove_fail() {
        
    }

    @Test
    public void search_success() {
        
    }

    @Test
    public void search_fail() {
        
    }

    @Test
    public void search_criteria_null() {
        
    }

    @Test
    public void search_Criteria_Status_null() {
        
    }

    @Test
    public void search_Criteria_LicenceNumber_null() {
        
    }

    @Test
    public void search_Criteria_LicenceType_null() {
        
    }


    @Test
    public void getAll_success() {
        
    }

    @Test
    public void getAll_fail() {
        
    }

}