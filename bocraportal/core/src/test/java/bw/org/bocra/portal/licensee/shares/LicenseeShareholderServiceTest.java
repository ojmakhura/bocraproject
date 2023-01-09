// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by SpringService.vsl in andromda-spring-cartridge on $springUtils.date. Do not modify by hand!.
//
package bw.org.bocra.portal.licensee.shares;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import bw.org.bocra.portal.licensee.LicenseeDao;
import bw.org.bocra.portal.licensee.LicenseeRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class LicenseeShareholderServiceTest {

    protected Logger logger = LoggerFactory.getLogger(LicenseeShareholderServiceTest.class);
    
    @InjectMocks
    private LicenseeShareholderServiceImpl LicenseeShareholderService;

    @Mock
    private LicenseeShareholderDao licenseeShareholderDao;

    @Mock
    private LicenseeShareholderRepository licenseeShareholderRepository;
    @Mock
    private ShareholderDao shareholderDao;

    @Mock
    private ShareholderRepository shareholderRepository;
    @Mock
    private LicenseeDao licenseeDao;

    @Mock
    private LicenseeRepository licenseeRepository;

    @Test
    public void checkInjects() {

        Assertions.assertNotNull(licenseeShareholderDao);
        Assertions.assertNotNull(licenseeShareholderRepository);
        Assertions.assertNotNull(shareholderDao);
        Assertions.assertNotNull(shareholderRepository);
        Assertions.assertNotNull(licenseeDao);
        Assertions.assertNotNull(licenseeRepository);
        Assertions.assertNotNull(LicenseeShareholderService);

    }
    

    @Test
    public void findById_success() {
        
    }

    @Test
    public void findById_fail() {
        
    }


    @Test
    public void create_success() {
        
    }

    @Test
    public void create_fail() {
        
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
    public void findByLicensee_success() {
        
    }

    @Test
    public void findByLicensee_fail() {
        
    }


    @Test
    public void findByShareholder_success() {
        
    }

    @Test
    public void findByShareholder_fail() {
        
    }


    @Test
    public void updateLicensee_success() {
        
    }

    @Test
    public void updateLicensee_fail() {
        
    }


    @Test
    public void updateShareholder_success() {
        
    }

    @Test
    public void updateShareholder_fail() {
        
    }

}