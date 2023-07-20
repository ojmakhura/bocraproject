// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by SpringService.vsl in andromda-spring-cartridge on $springUtils.date. Do not modify by hand!.
//
package bw.org.bocra.portal.menu;

import bw.org.bocra.portal.auth.AuthorisationDao;
import bw.org.bocra.portal.auth.AuthorisationRepository;
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
public class MenuSectionServiceTest {

    protected Logger logger = LoggerFactory.getLogger(MenuSectionServiceTest.class);

    @Autowired
    ApplicationContext context;

    @Autowired
    private JdbcTemplate jdbc;
    
    @Autowired
    private MenuSectionService menuSectionService;

    @Autowired
    private MenuSectionDao menuSectionDao;

    @Autowired
    private MenuSectionRepository menuSectionRepository;

    @Autowired
    private AuthorisationDao authorisationDao;

    @Autowired
    private AuthorisationRepository authorisationRepository;

    @Test
    @DisplayName("Test Save Success")
    public void testSaveSuccess() {
        
    }

    @Test
    @DisplayName("Test Save Fail")
    public void testSaveFail() {
        
    }

    @Test
    @DisplayName("Test Save Menu Section Null")
    public void testSaveMenuSectionNull() {
        
    }

    @Test
    @DisplayName("Test Save Menu Section Id Null")
    public void testSaveMenuSectionIdNull() {
        
    }

    @Test
    @DisplayName("Test Save Menu Section Created By Null")
    public void testSaveMenuSectionCreatedByNull() {
        
    }

    @Test
    @DisplayName("Test Save Menu Section Updated By Null")
    public void testSaveMenuSectionUpdatedByNull() {
        
    }

    @Test
    @DisplayName("Test Save Menu Section Created Date Null")
    public void testSaveMenuSectionCreatedDateNull() {
        
    }

    @Test
    @DisplayName("Test Save Menu Section Updated Date Null")
    public void testSaveMenuSectionUpdatedDateNull() {
        
    }

    @Test
    @DisplayName("Test Save Menu Section Position Null")
    public void testSaveMenuSectionPositionNull() {
        
    }

    @Test
    @DisplayName("Test Save Menu Section Display Id Null")
    public void testSaveMenuSectionDisplayIdNull() {
        
    }

    @Test
    @DisplayName("Test Save Menu Section Display Name Null")
    public void testSaveMenuSectionDisplayNameNull() {
        
    }

    @Test
    @DisplayName("Test Save Menu Section Authorisations Null")
    public void testSaveMenuSectionAuthorisationsNull() {
        
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
    @DisplayName("Test Find By Id Success")
    public void testFindByIdSuccess() {
        
    }

    @Test
    @DisplayName("Test Find By Id Fail")
    public void testFindByIdFail() {
        
    }

    @Test
    @DisplayName("Test Find By Authorisation Roles Success")
    public void testFindByAuthorisationRolesSuccess() {
        
    }

    @Test
    @DisplayName("Test Find By Authorisation Roles Fail")
    public void testFindByAuthorisationRolesFail() {
        
    }

}