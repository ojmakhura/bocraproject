// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by SpringService.vsl in andromda-spring-cartridge on $springUtils.date. Do not modify by hand!.
//
package bw.org.bocra.portal.licensee;

import bw.org.bocra.portal.form.FormDao;
import bw.org.bocra.portal.form.FormRepository;
import bw.org.bocra.portal.form.submission.FormSubmissionDao;
import bw.org.bocra.portal.form.submission.FormSubmissionRepository;
import bw.org.bocra.portal.licence.LicenceDao;
import bw.org.bocra.portal.licence.LicenceRepository;
import bw.org.bocra.portal.licensee.form.LicenseeFormDao;
import bw.org.bocra.portal.licensee.form.LicenseeFormRepository;
import bw.org.bocra.portal.licensee.sector.LicenseeSectorDao;
import bw.org.bocra.portal.licensee.sector.LicenseeSectorRepository;
import bw.org.bocra.portal.licensee.shares.LicenseeShareholderDao;
import bw.org.bocra.portal.licensee.shares.LicenseeShareholderRepository;
import bw.org.bocra.portal.sector.SectorDao;
import bw.org.bocra.portal.sector.SectorRepository;
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
public class LicenseeServiceTest {

    protected Logger logger = LoggerFactory.getLogger(LicenseeServiceTest.class);
    
    @InjectMocks
    private LicenseeServiceImpl licenseeService;

    @Mock
    private LicenseeDao licenseeDao;

    @Mock
    private LicenseeRepository licenseeRepository;
    @Mock
    private LicenseeShareholderDao licenseeShareholderDao;

    @Mock
    private LicenseeShareholderRepository licenseeShareholderRepository;
    @Mock
    private LicenseeFormDao licenseeFormDao;

    @Mock
    private LicenseeFormRepository licenseeFormRepository;
    @Mock
    private LicenseeSectorDao licenseeSectorDao;

    @Mock
    private LicenseeSectorRepository licenseeSectorRepository;
    @Mock
    private LicenceDao licenceDao;

    @Mock
    private LicenceRepository licenceRepository;
    @Mock
    private FormSubmissionDao formSubmissionDao;

    @Mock
    private FormSubmissionRepository formSubmissionRepository;
    @Mock
    private SectorDao sectorDao;

    @Mock
    private SectorRepository sectorRepository;
    @Mock
    private FormDao formDao;

    @Mock
    private FormRepository formRepository;

    @Test
    public void checkInjects() {

        Assertions.assertNotNull(licenseeDao);
        Assertions.assertNotNull(licenseeRepository);
        Assertions.assertNotNull(licenseeShareholderDao);
        Assertions.assertNotNull(licenseeShareholderRepository);
        Assertions.assertNotNull(licenseeFormDao);
        Assertions.assertNotNull(licenseeFormRepository);
        Assertions.assertNotNull(licenseeSectorDao);
        Assertions.assertNotNull(licenseeSectorRepository);
        Assertions.assertNotNull(licenceDao);
        Assertions.assertNotNull(licenceRepository);
        Assertions.assertNotNull(formSubmissionDao);
        Assertions.assertNotNull(formSubmissionRepository);
        Assertions.assertNotNull(sectorDao);
        Assertions.assertNotNull(sectorRepository);
        Assertions.assertNotNull(formDao);
        Assertions.assertNotNull(formRepository);
        Assertions.assertNotNull(licenseeService);

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
    public void save_licensee_null() {
        
    }

    @Test
    public void save_Licensee_Id_null() {
        
    }

    @Test
    public void save_Licensee_CreatedBy_null() {
        
    }

    @Test
    public void save_Licensee_UpdatedBy_null() {
        
    }

    @Test
    public void save_Licensee_CreatedDate_null() {
        
    }

    @Test
    public void save_Licensee_UpdatedDate_null() {
        
    }

    @Test
    public void save_Licensee_Status_null() {
        
    }

    @Test
    public void save_Licensee_Uin_null() {
        
    }

    @Test
    public void save_Licensee_LicenseeName_null() {
        
    }

    @Test
    public void save_Licensee_Alias_null() {
        
    }

    @Test
    public void save_Licensee_Address_null() {
        
    }

    @Test
    public void save_Licensee_Users_null() {
        
    }

    @Test
    public void save_Licensee_Forms_null() {
        
    }

    @Test
    public void save_Licensee_Licences_null() {
        
    }

    @Test
    public void save_Licensee_Documents_null() {
        
    }

    @Test
    public void save_Licensee_Sectors_null() {
        
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
    public void search_criteria_null() {
        
    }

    @Test
    public void search_Criteria_Uin_null() {
        
    }

    @Test
    public void search_Criteria_LicenseeName_null() {
        
    }

    @Test
    public void getLicences_success() {
        
    }

    @Test
    public void getLicences_fail() {
        
    }


    @Test
    public void getFormSubmissions_success() {
        
    }

    @Test
    public void getFormSubmissions_fail() {
        
    }


    @Test
    public void getDocuments_success() {
        
    }

    @Test
    public void getDocuments_fail() {
        
    }


    @Test
    public void getReports_success() {
        
    }

    @Test
    public void getReports_fail() {
        
    }


    @Test
    public void getShareholders_success() {
        
    }

    @Test
    public void getShareholders_fail() {
        
    }


    @Test
    public void getSectors_success() {
        
    }

    @Test
    public void getSectors_fail() {
        
    }


    @Test
    public void getForms_success() {
        
    }

    @Test
    public void getForms_fail() {
        
    }


    @Test
    public void getReportConfigurations_success() {
        
    }

    @Test
    public void getReportConfigurations_fail() {
        
    }


    @Test
    public void addSector_success() {
        
    }

    @Test
    public void addSector_fail() {
        
    }


    @Test
    public void removeSector_success() {
        
    }

    @Test
    public void removeSector_fail() {
        
    }


    @Test
    public void addForm_success() {
        
    }

    @Test
    public void addForm_fail() {
        
    }


    @Test
    public void removeForm_success() {
        
    }

    @Test
    public void removeForm_fail() {
        
    }


    @Test
    public void updateForm_success() {
        
    }

    @Test
    public void updateForm_fail() {
        
    }


    @Test
    public void updateSector_success() {
        
    }

    @Test
    public void updateSector_fail() {
        
    }

}