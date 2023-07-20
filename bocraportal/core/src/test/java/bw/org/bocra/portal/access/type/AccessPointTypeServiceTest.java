// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by SpringService.vsl in andromda-spring-cartridge on $springUtils.date. Do not modify by hand!.
//
package bw.org.bocra.portal.access.type;

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

import org.junit.jupiter.api.AfterEach;

@SpringBootTest
public class AccessPointTypeServiceTest {

    protected Logger logger = LoggerFactory.getLogger(AccessPointTypeServiceTest.class);

    @Autowired
    ApplicationContext context;

    @Autowired
    private JdbcTemplate jdbc;
    
    @Autowired
    private AccessPointTypeService accessPointTypeService;

    @Autowired
    private AccessPointTypeDao accessPointTypeDao;

    @Autowired
    private AccessPointTypeRepository accessPointTypeRepository;

    public void init() {

        jdbc.execute(
                "INSERT INTO access_point_type (id, code, name, description) VALUES (1, 'test', 'Test Type', 'This is a test')");
    }

    @AfterEach
    public void clean() {
        jdbc.execute("DELETE FROM access_point_type");
    }

    @Test
    @DisplayName("Test Find By Id Success")
    public void testFindByIdSuccess() {

        this.init();

        AccessPointTypeVO type = accessPointTypeService.findById(1l);

        assertNotNull(type);
        assertNotNull(type.getId());
        assertEquals("test", type.getCode());
        assertEquals("Test Type", type.getName());
        assertEquals("This is a test", type.getDescription());
        
    }

    @Test
    @DisplayName("Test Find By Id Fail")
    public void testFindByIdFail() {

        Exception exception = assertThrows(AccessPointTypeServiceException.class, () -> {
            accessPointTypeService.findById(2l);
        }, "AccessPointTypeServiceException should be thrown");

        assertTrue(exception.getMessage().contains("java.util.NoSuchElementException: No value present"));
        
    }

    @Test
    @DisplayName("Test Save Success")
    public void testSaveSuccess() {

        AccessPointTypeVO type = new AccessPointTypeVO();

        type.setCode("test2");
        type.setName("Test Type 2");
        type.setDescription("This is a test 2");
        
        type = accessPointTypeService.save(type);

        assertNotNull(type);
        assertNotNull(type.getId());
        assertEquals(1L, type.getId(), "Id should be 2");
        assertEquals("test2", type.getCode(), "Code should be test2");
        assertEquals("Test Type 2", type.getName(), "Name should be Test Type 2");
        
    }

    @Test
    @DisplayName("Test save null")
    public void testSaveNullFail() {

        Exception exception = assertThrows(AccessPointTypeServiceException.class, () -> {
            accessPointTypeService.save(null);
        }, "AccessPointTypeServiceException should be thrown");

        assertTrue(exception.getMessage().contains("'accessPointType' can not be null"));
    }

    @Test
    @DisplayName("Test save same code")
    public void testSaveSameCodeFail() {

        AccessPointTypeVO type = new AccessPointTypeVO();

        type.setCode("test");
        type.setName("Test Type");
        type.setDescription("This is a test");
        
        type = accessPointTypeService.save(type);

        Exception exception = assertThrows(AccessPointTypeServiceException.class, () -> {

            AccessPointTypeVO type2 = new AccessPointTypeVO();
    
            type2.setCode("test");
            type2.setName("Test Type 2");
            type2.setDescription("This is a test 2");

            type2 = accessPointTypeService.save(type2);
        }, "AccessPointTypeServiceException should be thrown");

        assertTrue(exception.getMessage().contains("org.springframework.dao.DataIntegrityViolationException: could not execute statement"));

    }

    @Test
    @DisplayName("Test save null fail")
    public void testSaveCodeNullFail() {

        Exception exception = assertThrows(AccessPointTypeServiceException.class, () -> {

            AccessPointTypeVO type2 = new AccessPointTypeVO();
    
            type2.setName("Test Type 2");
            type2.setDescription("This is a test 2");

            type2 = accessPointTypeService.save(type2);
        }, "AccessPointTypeServiceException should be thrown");

        assertTrue(exception.getMessage().contains("'accessPointType.code' can not be null or empty"));

    }

    @Test
    @DisplayName("Test Save Fail")
    public void testSaveFail() {
        
    }

    @Test
    @DisplayName("Test Save Access Point Type Null")
    public void testSaveAccessPointTypeNull() {
        
    }

    @Test
    @DisplayName("Test Save Access Point Type Id Null")
    public void testSaveAccessPointTypeIdNull() {
        
    }

    @Test
    @DisplayName("Test Save Access Point Type Code Null")
    public void testSaveAccessPointTypeCodeNull() {
        
    }

    @Test
    @DisplayName("Test Save Access Point Type Name Null")
    public void testSaveAccessPointTypeNameNull() {
        
    }

    @Test
    @DisplayName("Test Save Access Point Type Description Null")
    public void testSaveAccessPointTypeDescriptionNull() {
        
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
    @DisplayName("Test Paged Get All Success")
    public void testPagedGetAllSuccess() {
        
    }

    @Test
    @DisplayName("Test Paged Get All Fail")
    public void testPagedGetAllFail() {
        
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

}