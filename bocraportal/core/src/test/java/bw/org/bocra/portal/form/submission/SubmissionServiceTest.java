// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by SpringService.vsl in andromda-spring-cartridge on $springUtils.date. Do not modify by hand!.
//
package bw.org.bocra.portal.form.submission;

import bw.org.bocra.portal.form.submission.data.DataFieldDao;
import bw.org.bocra.portal.form.submission.data.DataFieldRepository;
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
public class SubmissionServiceTest {

    protected Logger logger = LoggerFactory.getLogger(SubmissionServiceTest.class);
    
    @InjectMocks
    private SubmissionServiceImpl submissionService;

    @Mock
    private FormSubmissionDao formSubmissionDao;

    @Mock
    private FormSubmissionRepository formSubmissionRepository;
    @Mock
    private DataFieldDao dataFieldDao;

    @Mock
    private DataFieldRepository dataFieldRepository;

    @Test
    public void checkInjects() {

        Assertions.assertNotNull(formSubmissionDao);
        Assertions.assertNotNull(formSubmissionRepository);
        Assertions.assertNotNull(dataFieldDao);
        Assertions.assertNotNull(dataFieldRepository);
        Assertions.assertNotNull(submissionService);

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
    public void save_formSubmissionVO_null() {
        
    }

    @Test
    public void save_FormSubmissionVO_Id_null() {
        
    }

    @Test
    public void save_FormSubmissionVO_CreatedBy_null() {
        
    }

    @Test
    public void save_FormSubmissionVO_UpdatedBy_null() {
        
    }

    @Test
    public void save_FormSubmissionVO_CreatedDate_null() {
        
    }

    @Test
    public void save_FormSubmissionVO_UpdatedDate_null() {
        
    }

    @Test
    public void save_FormSubmissionVO_SubmittedBy_null() {
        
    }

    @Test
    public void save_FormSubmissionVO_SubmissionDate_null() {
        
    }

    @Test
    public void save_FormSubmissionVO_Form_null() {
        
    }

    @Test
    public void save_FormSubmissionVO_Period_null() {
        
    }

    @Test
    public void save_FormSubmissionVO_Licensee_null() {
        
    }

    @Test
    public void save_FormSubmissionVO_DataFields_null() {
        
    }

    @Test
    public void save_FormSubmissionVO_SubmissionStatus_null() {
        
    }

    @Test
    public void save_FormSubmissionVO_Upload_null() {
        
    }

    @Test
    public void save_FormSubmissionVO_Notes_null() {
        
    }

    @Test
    public void save_FormSubmissionVO_Sections_null() {
        
    }

    @Test
    public void save_FormSubmissionVO_ExpectedSubmissionDate_null() {
        
    }

    @Test
    public void save_FormSubmissionVO_AcceptedBy_null() {
        
    }

    @Test
    public void save_FormSubmissionVO_AcceptedDate_null() {
        
    }

    @Test
    public void save_FormSubmissionVO_ReturnedDate_null() {
        
    }

    @Test
    public void save_FormSubmissionVO_ReturnedBy_null() {
        
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
    public void search_Criteria_StartDate_null() {
        
    }

    @Test
    public void search_Criteria_EndDate_null() {
        
    }

    @Test
    public void search_Criteria_Form_null() {
        
    }

    @Test
    public void search_Criteria_SubmissionStatus_null() {
        
    }

    @Test
    public void search_Criteria_LicenseeId_null() {
        
    }

    @Test
    public void search_Criteria_SubmittedBy_null() {
        
    }

    @Test
    public void search_Criteria_LicenseeName_null() {
        
    }

    @Test
    public void search_Criteria_PeriodIds_null() {
        
    }

    @Test
    public void search_Criteria_PeriodDate_null() {
        
    }


    @Test
    public void addDataField_success() {
        
    }

    @Test
    public void addDataField_fail() {
        
    }

    @Test
    public void addDataField_dataField_null() {
        
    }

    @Test
    public void addDataField_DataField_Id_null() {
        
    }

    @Test
    public void addDataField_DataField_FormField_null() {
        
    }

    @Test
    public void addDataField_DataField_Value_null() {
        
    }

    @Test
    public void addDataField_DataField_FormSubmission_null() {
        
    }

    @Test
    public void addDataField_DataField_Units_null() {
        
    }

    @Test
    public void addDataField_DataField_Comments_null() {
        
    }

    @Test
    public void addDataField_DataField_SectionPosition_null() {
        
    }

    @Test
    public void addDataField_DataField_SectionId_null() {
        
    }

    @Test
    public void addDataField_DataField_SectionLabel_null() {
        
    }

    @Test
    public void addDataField_DataField_Row_null() {
        
    }


    @Test
    public void addDataFields_success() {
        
    }

    @Test
    public void addDataFields_fail() {
        
    }


    @Test
    public void deleteDataField_success() {
        
    }

    @Test
    public void deleteDataField_fail() {
        
    }


    @Test
    public void getSubmissionSummary_success() {
        
    }

    @Test
    public void getSubmissionSummary_fail() {
        
    }

    @Test
    public void getSubmissionSummary_criteria_null() {
        
    }

    @Test
    public void getSubmissionSummary_Criteria_StartDate_null() {
        
    }

    @Test
    public void getSubmissionSummary_Criteria_EndDate_null() {
        
    }

    @Test
    public void getSubmissionSummary_Criteria_Form_null() {
        
    }

    @Test
    public void getSubmissionSummary_Criteria_SubmissionStatus_null() {
        
    }

    @Test
    public void getSubmissionSummary_Criteria_LicenseeId_null() {
        
    }

    @Test
    public void getSubmissionSummary_Criteria_SubmittedBy_null() {
        
    }

    @Test
    public void getSubmissionSummary_Criteria_LicenseeName_null() {
        
    }

    @Test
    public void getSubmissionSummary_Criteria_PeriodIds_null() {
        
    }

    @Test
    public void getSubmissionSummary_Criteria_PeriodDate_null() {
        
    }


    @Test
    public void findByIds_success() {
        
    }

    @Test
    public void findByIds_fail() {
        
    }


    @Test
    public void updateSubmissionStatus_success() {
        
    }

    @Test
    public void updateSubmissionStatus_fail() {
        
    }

    @Test
    public void updateSubmissionStatus_submissionStatus_null() {
        
    }

    @Test
    public void updateSubmissionStatus_updateTime_null() {
        
    }

    @Test
    public void updateSubmissionStatus_username_null() {
        
    }


    @Test
    public void loadDueSubmissions_success() {
        
    }

    @Test
    public void loadDueSubmissions_fail() {
        
    }


    @Test
    public void checkOverdueSubmissions_success() {
        
    }

    @Test
    public void checkOverdueSubmissions_fail() {
        
    }

}