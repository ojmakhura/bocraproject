// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by SpringService.vsl in andromda-spring-cartridge on $springUtils.date. Do not modify by hand!.
//
package bw.org.bocra.portal.auth;

import bw.org.bocra.portal.menu.MenuSectionDao;
import bw.org.bocra.portal.menu.MenuSectionRepository;
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
public class AuthorisationServiceTest {

    protected Logger logger = LoggerFactory.getLogger(AuthorisationServiceTest.class);

    @Autowired
    ApplicationContext context;

    @Autowired
    private JdbcTemplate jdbc;
    
    @Autowired
    private AuthorisationService authorisationService;

    @Autowired
    private AuthorisationDao authorisationDao;

    @Autowired
    private AuthorisationRepository authorisationRepository;

    @Autowired
    private MenuSectionDao menuSectionDao;

    @Autowired
    private MenuSectionRepository menuSectionRepository;

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
    @DisplayName("Test Save Authorisation Null")
    public void testSaveAuthorisationNull() {
        
    }

    @Test
    @DisplayName("Test Save Authorisation Id Null")
    public void testSaveAuthorisationIdNull() {
        
    }

    @Test
    @DisplayName("Test Save Authorisation Created By Null")
    public void testSaveAuthorisationCreatedByNull() {
        
    }

    @Test
    @DisplayName("Test Save Authorisation Updated By Null")
    public void testSaveAuthorisationUpdatedByNull() {
        
    }

    @Test
    @DisplayName("Test Save Authorisation Created Date Null")
    public void testSaveAuthorisationCreatedDateNull() {
        
    }

    @Test
    @DisplayName("Test Save Authorisation Updated Date Null")
    public void testSaveAuthorisationUpdatedDateNull() {
        
    }

    @Test
    @DisplayName("Test Save Authorisation Access Point Null")
    public void testSaveAuthorisationAccessPointNull() {
        
    }

    @Test
    @DisplayName("Test Save Authorisation Roles Null")
    public void testSaveAuthorisationRolesNull() {
        
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
    @DisplayName("Test Search Criteria Access Point Name Null")
    public void testSearchCriteriaAccessPointNameNull() {
        
    }

    @Test
    @DisplayName("Test Search Criteria Access Point Url Null")
    public void testSearchCriteriaAccessPointUrlNull() {
        
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
    @DisplayName("Test Get Access Type Code Authorisations Success")
    public void testGetAccessTypeCodeAuthorisationsSuccess() {
        
    }

    @Test
    @DisplayName("Test Get Access Type Code Authorisations Fail")
    public void testGetAccessTypeCodeAuthorisationsFail() {
        
    }

    @Test
    @DisplayName("Test Assign Menu Section Success")
    public void testAssignMenuSectionSuccess() {
        
    }

    @Test
    @DisplayName("Test Assign Menu Section Fail")
    public void testAssignMenuSectionFail() {
        
    }

    @Test
    @DisplayName("Test Find By Roles And Url Success")
    public void testFindByRolesAndUrlSuccess() {
        
    }

    @Test
    @DisplayName("Test Find By Roles And Url Fail")
    public void testFindByRolesAndUrlFail() {
        
    }

    @Test
    @DisplayName("Test Find By Roles And Url Url Null")
    public void testFindByRolesAndUrlUrlNull() {
        
    }

    @Test
    @DisplayName("Test Find By Url Prefix Success")
    public void testFindByUrlPrefixSuccess() {
        
    }

    @Test
    @DisplayName("Test Find By Url Prefix Fail")
    public void testFindByUrlPrefixFail() {
        
    }

    @Test
    @DisplayName("Test Find By Url Prefix Prefix Null")
    public void testFindByUrlPrefixPrefixNull() {
        
    }

}