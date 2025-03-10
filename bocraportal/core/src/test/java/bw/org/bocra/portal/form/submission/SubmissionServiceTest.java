// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by SpringService.vsl in andromda-spring-cartridge on $springUtils.date. Do not modify by hand!.
//
package bw.org.bocra.portal.form.submission;

import bw.org.bocra.portal.form.submission.data.DataFieldDao;
import bw.org.bocra.portal.form.submission.data.DataFieldRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class SubmissionServiceTest {

    protected Logger logger = LoggerFactory.getLogger(SubmissionServiceTest.class);

    @Autowired
    ApplicationContext context;

    @Autowired
    private JdbcTemplate jdbc;
    
    @Autowired
    private SubmissionService submissionService;

    @Autowired
    private FormSubmissionDao formSubmissionDao;

    @Autowired
    private FormSubmissionRepository formSubmissionRepository;

    @Autowired
    private DataFieldDao dataFieldDao;

    @Autowired
    private DataFieldRepository dataFieldRepository;

    @Test
    @DisplayName("Test Find By Id Success")
    public void testFindByIdSuccess() {
        
    }

    @Test
    @DisplayName("Test Find By Id Fail")
    public void testFindByIdFail() {
        
    }

    @Test
    @DisplayName("Test Save Success")
    public void testSaveSuccess() {
        
    }

    @Test
    @DisplayName("Test Save Fail")
    public void testSaveFail() {
        
    }

    @Test
    @DisplayName("Test Save Form Submission Null")
    public void testSaveFormSubmissionNull() {
        
    }

    @Test
    @DisplayName("Test Save Form Submission Id Null")
    public void testSaveFormSubmissionIdNull() {
        
    }

    @Test
    @DisplayName("Test Save Form Submission Created By Null")
    public void testSaveFormSubmissionCreatedByNull() {
        
    }

    @Test
    @DisplayName("Test Save Form Submission Updated By Null")
    public void testSaveFormSubmissionUpdatedByNull() {
        
    }

    @Test
    @DisplayName("Test Save Form Submission Created Date Null")
    public void testSaveFormSubmissionCreatedDateNull() {
        
    }

    @Test
    @DisplayName("Test Save Form Submission Updated Date Null")
    public void testSaveFormSubmissionUpdatedDateNull() {
        
    }

    @Test
    @DisplayName("Test Save Form Submission Submitted By Null")
    public void testSaveFormSubmissionSubmittedByNull() {
        
    }

    @Test
    @DisplayName("Test Save Form Submission Submission Date Null")
    public void testSaveFormSubmissionSubmissionDateNull() {
        
    }

    @Test
    @DisplayName("Test Save Form Submission Form Null")
    public void testSaveFormSubmissionFormNull() {
        
    }

    @Test
    @DisplayName("Test Save Form Submission Period Null")
    public void testSaveFormSubmissionPeriodNull() {
        
    }

    @Test
    @DisplayName("Test Save Form Submission Licensee Null")
    public void testSaveFormSubmissionLicenseeNull() {
        
    }

    @Test
    @DisplayName("Test Save Form Submission Data Fields Null")
    public void testSaveFormSubmissionDataFieldsNull() {
        
    }

    @Test
    @DisplayName("Test Save Form Submission Submission Status Null")
    public void testSaveFormSubmissionSubmissionStatusNull() {
        
    }

    @Test
    @DisplayName("Test Save Form Submission Upload Null")
    public void testSaveFormSubmissionUploadNull() {
        
    }

    @Test
    @DisplayName("Test Save Form Submission Notes Null")
    public void testSaveFormSubmissionNotesNull() {
        
    }

    @Test
    @DisplayName("Test Save Form Submission Sections Null")
    public void testSaveFormSubmissionSectionsNull() {
        
    }

    @Test
    @DisplayName("Test Save Form Submission Expected Submission Date Null")
    public void testSaveFormSubmissionExpectedSubmissionDateNull() {
        
    }

    @Test
    @DisplayName("Test Save Form Submission Accepted By Null")
    public void testSaveFormSubmissionAcceptedByNull() {
        
    }

    @Test
    @DisplayName("Test Save Form Submission Accepted Date Null")
    public void testSaveFormSubmissionAcceptedDateNull() {
        
    }

    @Test
    @DisplayName("Test Save Form Submission Returned Date Null")
    public void testSaveFormSubmissionReturnedDateNull() {
        
    }

    @Test
    @DisplayName("Test Save Form Submission Returned By Null")
    public void testSaveFormSubmissionReturnedByNull() {
        
    }

    @Test
    @DisplayName("Test Save Form Submission Form Activation Null")
    public void testSaveFormSubmissionFormActivationNull() {
        
    }

    @Test
    @DisplayName("Test Remove Success")
    public void testRemoveSuccess() {
        
    }

    @Test
    @DisplayName("Test Remove Fail")
    public void testRemoveFail() {
        
    }

    @Test
    @DisplayName("Test Get All Success")
    public void testGetAllSuccess() {
        
    }

    @Test
    @DisplayName("Test Get All Fail")
    public void testGetAllFail() {
        
    }

    @Test
    @DisplayName("Test Search Success")
    public void testSearchSuccess() {
        
    }

    @Test
    @DisplayName("Test Search Fail")
    public void testSearchFail() {
        
    }

    @Test
    @DisplayName("Test Search Criteria Null")
    public void testSearchCriteriaNull() {
        
    }

    @Test
    @DisplayName("Test Search Criteria Start Date Null")
    public void testSearchCriteriaStartDateNull() {
        
    }

    @Test
    @DisplayName("Test Search Criteria End Date Null")
    public void testSearchCriteriaEndDateNull() {
        
    }

    @Test
    @DisplayName("Test Search Criteria Form Null")
    public void testSearchCriteriaFormNull() {
        
    }

    @Test
    @DisplayName("Test Search Criteria Submission Status Null")
    public void testSearchCriteriaSubmissionStatusNull() {
        
    }

    @Test
    @DisplayName("Test Search Criteria Licensee Id Null")
    public void testSearchCriteriaLicenseeIdNull() {
        
    }

    @Test
    @DisplayName("Test Search Criteria Submitted By Null")
    public void testSearchCriteriaSubmittedByNull() {
        
    }

    @Test
    @DisplayName("Test Search Criteria Licensee Name Null")
    public void testSearchCriteriaLicenseeNameNull() {
        
    }

    @Test
    @DisplayName("Test Search Criteria Period Ids Null")
    public void testSearchCriteriaPeriodIdsNull() {
        
    }

    @Test
    @DisplayName("Test Search Criteria Period Start Date Null")
    public void testSearchCriteriaPeriodStartDateNull() {
        
    }

    @Test
    @DisplayName("Test Search Criteria Period End Date Null")
    public void testSearchCriteriaPeriodEndDateNull() {
        
    }

    @Test
    @DisplayName("Test Search Criteria Roles Null")
    public void testSearchCriteriaRolesNull() {
        
    }

    @Test
    @DisplayName("Test Paged Get All Success")
    public void testPagedGetAllSuccess() {
        
    }

    @Test
    @DisplayName("Test Paged Get All Fail")
    public void testPagedGetAllFail() {
        
    }

    @Test
    @DisplayName("Test Add Data Field Success")
    public void testAddDataFieldSuccess() {
        
    }

    @Test
    @DisplayName("Test Add Data Field Fail")
    public void testAddDataFieldFail() {
        
    }

    @Test
    @DisplayName("Test Add Data Field Data Field Null")
    public void testAddDataFieldDataFieldNull() {
        
    }

    @Test
    @DisplayName("Test Add Data Field Data Field Id Null")
    public void testAddDataFieldDataFieldIdNull() {
        
    }

    @Test
    @DisplayName("Test Add Data Field Data Field Form Field Null")
    public void testAddDataFieldDataFieldFormFieldNull() {
        
    }

    @Test
    @DisplayName("Test Add Data Field Data Field Value Null")
    public void testAddDataFieldDataFieldValueNull() {
        
    }

    @Test
    @DisplayName("Test Add Data Field Data Field Form Submission Null")
    public void testAddDataFieldDataFieldFormSubmissionNull() {
        
    }

    @Test
    @DisplayName("Test Add Data Field Data Field Units Null")
    public void testAddDataFieldDataFieldUnitsNull() {
        
    }

    @Test
    @DisplayName("Test Add Data Field Data Field Comments Null")
    public void testAddDataFieldDataFieldCommentsNull() {
        
    }

    @Test
    @DisplayName("Test Add Data Field Data Field Section Position Null")
    public void testAddDataFieldDataFieldSectionPositionNull() {
        
    }

    @Test
    @DisplayName("Test Add Data Field Data Field Section Id Null")
    public void testAddDataFieldDataFieldSectionIdNull() {
        
    }

    @Test
    @DisplayName("Test Add Data Field Data Field Section Label Null")
    public void testAddDataFieldDataFieldSectionLabelNull() {
        
    }

    @Test
    @DisplayName("Test Add Data Field Data Field Row Null")
    public void testAddDataFieldDataFieldRowNull() {
        
    }

    @Test
    @DisplayName("Test Add Data Fields Success")
    public void testAddDataFieldsSuccess() {
        
    }

    @Test
    @DisplayName("Test Add Data Fields Fail")
    public void testAddDataFieldsFail() {
        
    }

    @Test
    @DisplayName("Test Delete Data Field Success")
    public void testDeleteDataFieldSuccess() {
        
    }

    @Test
    @DisplayName("Test Delete Data Field Fail")
    public void testDeleteDataFieldFail() {
        
    }

    @Test
    @DisplayName("Test Get Submission Summary Success")
    public void testGetSubmissionSummarySuccess() {
        
    }

    @Test
    @DisplayName("Test Get Submission Summary Fail")
    public void testGetSubmissionSummaryFail() {
        
    }

    @Test
    @DisplayName("Test Get Submission Summary Criteria Null")
    public void testGetSubmissionSummaryCriteriaNull() {
        
    }

    @Test
    @DisplayName("Test Get Submission Summary Criteria Start Date Null")
    public void testGetSubmissionSummaryCriteriaStartDateNull() {
        
    }

    @Test
    @DisplayName("Test Get Submission Summary Criteria End Date Null")
    public void testGetSubmissionSummaryCriteriaEndDateNull() {
        
    }

    @Test
    @DisplayName("Test Get Submission Summary Criteria Form Null")
    public void testGetSubmissionSummaryCriteriaFormNull() {
        
    }

    @Test
    @DisplayName("Test Get Submission Summary Criteria Submission Status Null")
    public void testGetSubmissionSummaryCriteriaSubmissionStatusNull() {
        
    }

    @Test
    @DisplayName("Test Get Submission Summary Criteria Licensee Id Null")
    public void testGetSubmissionSummaryCriteriaLicenseeIdNull() {
        
    }

    @Test
    @DisplayName("Test Get Submission Summary Criteria Submitted By Null")
    public void testGetSubmissionSummaryCriteriaSubmittedByNull() {
        
    }

    @Test
    @DisplayName("Test Get Submission Summary Criteria Licensee Name Null")
    public void testGetSubmissionSummaryCriteriaLicenseeNameNull() {
        
    }

    @Test
    @DisplayName("Test Get Submission Summary Criteria Period Ids Null")
    public void testGetSubmissionSummaryCriteriaPeriodIdsNull() {
        
    }

    @Test
    @DisplayName("Test Get Submission Summary Criteria Period Start Date Null")
    public void testGetSubmissionSummaryCriteriaPeriodStartDateNull() {
        
    }

    @Test
    @DisplayName("Test Get Submission Summary Criteria Period End Date Null")
    public void testGetSubmissionSummaryCriteriaPeriodEndDateNull() {
        
    }

    @Test
    @DisplayName("Test Get Submission Summary Criteria Roles Null")
    public void testGetSubmissionSummaryCriteriaRolesNull() {
        
    }

    @Test
    @DisplayName("Test Find By Ids Success")
    public void testFindByIdsSuccess() {
        
    }

    @Test
    @DisplayName("Test Find By Ids Fail")
    public void testFindByIdsFail() {
        
    }

    @Test
    @DisplayName("Test Update Submission Status Success")
    public void testUpdateSubmissionStatusSuccess() {
        
    }

    @Test
    @DisplayName("Test Update Submission Status Fail")
    public void testUpdateSubmissionStatusFail() {
        
    }

    @Test
    @DisplayName("Test Update Submission Status Submission Status Null")
    public void testUpdateSubmissionStatusSubmissionStatusNull() {
        
    }

    @Test
    @DisplayName("Test Update Submission Status Update Time Null")
    public void testUpdateSubmissionStatusUpdateTimeNull() {
        
    }

    @Test
    @DisplayName("Test Update Submission Status Username Null")
    public void testUpdateSubmissionStatusUsernameNull() {
        
    }

    @Test
    @DisplayName("Test Load Due Submissions Success")
    public void testLoadDueSubmissionsSuccess() {
        
    }

    @Test
    @DisplayName("Test Load Due Submissions Fail")
    public void testLoadDueSubmissionsFail() {
        
    }

    @Test
    @DisplayName("Test Check Overdue Submissions Success")
    public void testCheckOverdueSubmissionsSuccess() {
        
    }

    @Test
    @DisplayName("Test Check Overdue Submissions Fail")
    public void testCheckOverdueSubmissionsFail() {
        
    }

    @Test
    @DisplayName("Test Create New Submissions Success")
    public void testCreateNewSubmissionsSuccess() {
        
    }

    @Test
    @DisplayName("Test Create New Submissions Fail")
    public void testCreateNewSubmissionsFail() {
        
    }

    @Test
    @DisplayName("Test Upload Data Success")
    public void testUploadDataSuccess() {
        
    }

    @Test
    @DisplayName("Test Upload Data Fail")
    public void testUploadDataFail() {
        
    }

    @Test
    @DisplayName("Test Upload Data File Null")
    public void testUploadDataFileNull() {
        
    }

    @Test
    @DisplayName("Test Get Submission Data Success")
    public void testGetSubmissionDataSuccess() {
        
    }

    @Test
    @DisplayName("Test Get Submission Data Fail")
    public void testGetSubmissionDataFail() {
        
    }

    @Test
    @DisplayName("Test Paged Search Success")
    public void testPagedSearchSuccess() {
        
    }

    @Test
    @DisplayName("Test Paged Search Fail")
    public void testPagedSearchFail() {
        
    }

    @Test
    @DisplayName("Test Paged Search Criteria Null")
    public void testPagedSearchCriteriaNull() {
        
    }

    @Test
    @DisplayName("Test Paged Search Criteria Start Date Null")
    public void testPagedSearchCriteriaStartDateNull() {
        
    }

    @Test
    @DisplayName("Test Paged Search Criteria End Date Null")
    public void testPagedSearchCriteriaEndDateNull() {
        
    }

    @Test
    @DisplayName("Test Paged Search Criteria Form Null")
    public void testPagedSearchCriteriaFormNull() {
        
    }

    @Test
    @DisplayName("Test Paged Search Criteria Submission Status Null")
    public void testPagedSearchCriteriaSubmissionStatusNull() {
        
    }

    @Test
    @DisplayName("Test Paged Search Criteria Licensee Id Null")
    public void testPagedSearchCriteriaLicenseeIdNull() {
        
    }

    @Test
    @DisplayName("Test Paged Search Criteria Submitted By Null")
    public void testPagedSearchCriteriaSubmittedByNull() {
        
    }

    @Test
    @DisplayName("Test Paged Search Criteria Licensee Name Null")
    public void testPagedSearchCriteriaLicenseeNameNull() {
        
    }

    @Test
    @DisplayName("Test Paged Search Criteria Period Ids Null")
    public void testPagedSearchCriteriaPeriodIdsNull() {
        
    }

    @Test
    @DisplayName("Test Paged Search Criteria Period Start Date Null")
    public void testPagedSearchCriteriaPeriodStartDateNull() {
        
    }

    @Test
    @DisplayName("Test Paged Search Criteria Period End Date Null")
    public void testPagedSearchCriteriaPeriodEndDateNull() {
        
    }

    @Test
    @DisplayName("Test Paged Search Criteria Roles Null")
    public void testPagedSearchCriteriaRolesNull() {
        
    }

    @Test
    @DisplayName("Test Pre Processed Find By Id Success")
    public void testPreProcessedFindByIdSuccess() {
        
    }

    @Test
    @DisplayName("Test Pre Processed Find By Id Fail")
    public void testPreProcessedFindByIdFail() {
        
    }

    @Test
    @DisplayName("Test Pre Processed Find By Id Filter Null")
    public void testPreProcessedFindByIdFilterNull() {
        
    }

    @Test
    @DisplayName("Test Pre Processed Find By Id Filter Ids Null")
    public void testPreProcessedFindByIdFilterIdsNull() {
        
    }

    @Test
    @DisplayName("Test Pre Processed Find By Id Filter Group By Null")
    public void testPreProcessedFindByIdFilterGroupByNull() {
        
    }

    @Test
    @DisplayName("Test Pre Processed Find By Id Filter Order By Null")
    public void testPreProcessedFindByIdFilterOrderByNull() {
        
    }

    @Test
    @DisplayName("Test Pre Processed Find By Id Filter Limit Null")
    public void testPreProcessedFindByIdFilterLimitNull() {
        
    }

    @Test
    @DisplayName("Test Pre Processed Find By Id Filter Group Operation Null")
    public void testPreProcessedFindByIdFilterGroupOperationNull() {
        
    }

    @Test
    @DisplayName("Test Pre Processed Find By Id Filter Percentile Null")
    public void testPreProcessedFindByIdFilterPercentileNull() {
        
    }

    @Test
    @DisplayName("Test Pre Processed Find By Id Filter Sort Order Null")
    public void testPreProcessedFindByIdFilterSortOrderNull() {
        
    }

    @Test
    @DisplayName("Test Pre Processed Find By Id Filter Min Null")
    public void testPreProcessedFindByIdFilterMinNull() {
        
    }

    @Test
    @DisplayName("Test Pre Processed Find By Id Filter Threshold Field Null")
    public void testPreProcessedFindByIdFilterThresholdFieldNull() {
        
    }

    @Test
    @DisplayName("Test Pre Processed Find By Id Filter Max Null")
    public void testPreProcessedFindByIdFilterMaxNull() {
        
    }

}