// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by SpringService.vsl in andromda-spring-cartridge on $springUtils.date. Do not modify by hand!.
//
package bw.org.bocra.portal.shareholder;

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
public class ShareholderServiceTest {

    protected Logger logger = LoggerFactory.getLogger(ShareholderServiceTest.class);

    @Autowired
    ApplicationContext context;

    @Autowired
    private JdbcTemplate jdbc;
    
    @Autowired
    private ShareholderService shareholderService;

    @Autowired
    private ShareholderDao shareholderDao;

    @Autowired
    private ShareholderRepository shareholderRepository;

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
    @DisplayName("Test Save Shareholder Null")
    public void testSaveShareholderNull() {
        
    }

    @Test
    @DisplayName("Test Save Shareholder Id Null")
    public void testSaveShareholderIdNull() {
        
    }

    @Test
    @DisplayName("Test Save Shareholder Created By Null")
    public void testSaveShareholderCreatedByNull() {
        
    }

    @Test
    @DisplayName("Test Save Shareholder Updated By Null")
    public void testSaveShareholderUpdatedByNull() {
        
    }

    @Test
    @DisplayName("Test Save Shareholder Created Date Null")
    public void testSaveShareholderCreatedDateNull() {
        
    }

    @Test
    @DisplayName("Test Save Shareholder Updated Date Null")
    public void testSaveShareholderUpdatedDateNull() {
        
    }

    @Test
    @DisplayName("Test Save Shareholder Shareholder Id Null")
    public void testSaveShareholderShareholderIdNull() {
        
    }

    @Test
    @DisplayName("Test Save Shareholder Type Null")
    public void testSaveShareholderTypeNull() {
        
    }

    @Test
    @DisplayName("Test Save Shareholder Name Null")
    public void testSaveShareholderNameNull() {
        
    }

    @Test
    @DisplayName("Test Save Shareholder Address Null")
    public void testSaveShareholderAddressNull() {
        
    }

    @Test
    @DisplayName("Test Save Shareholder Documents Null")
    public void testSaveShareholderDocumentsNull() {
        
    }

    @Test
    @DisplayName("Test Save Shareholder Shares Null")
    public void testSaveShareholderSharesNull() {
        
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
    @DisplayName("Test Search Criteria Shareholder Id Null")
    public void testSearchCriteriaShareholderIdNull() {
        
    }

    @Test
    @DisplayName("Test Search Criteria Type Null")
    public void testSearchCriteriaTypeNull() {
        
    }

    @Test
    @DisplayName("Test Search Criteria Name Null")
    public void testSearchCriteriaNameNull() {
        
    }

    @Test
    @DisplayName("Test Paged Get All Success")
    public void testPagedGetAllSuccess() {
        
    }

    @Test
    @DisplayName("Test Paged Get All Fail")
    public void testPagedGetAllFail() {
        
    }

}