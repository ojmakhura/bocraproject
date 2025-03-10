// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by SpringService.vsl in andromda-spring-cartridge on $springUtils.date. Do not modify by hand!.
//
package bw.org.bocra.portal.licence.type.form;

import bw.org.bocra.portal.form.FormDao;
import bw.org.bocra.portal.form.FormRepository;
import bw.org.bocra.portal.licence.type.LicenceTypeDao;
import bw.org.bocra.portal.licence.type.LicenceTypeRepository;
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
public class LicenceTypeFormServiceTest {

    protected Logger logger = LoggerFactory.getLogger(LicenceTypeFormServiceTest.class);

    @Autowired
    ApplicationContext context;

    @Autowired
    private JdbcTemplate jdbc;
    
    @Autowired
    private LicenceTypeFormService licenceTypeFormService;

    @Autowired
    private LicenceTypeFormDao licenceTypeFormDao;

    @Autowired
    private LicenceTypeFormRepository licenceTypeFormRepository;

    @Autowired
    private LicenceTypeDao licenceTypeDao;

    @Autowired
    private LicenceTypeRepository licenceTypeRepository;

    @Autowired
    private FormDao formDao;

    @Autowired
    private FormRepository formRepository;

    @Test
    @DisplayName("Test Find By Id Success")
    public void testFindByIdSuccess() {
        
    }

    @Test
    @DisplayName("Test Find By Id Fail")
    public void testFindByIdFail() {
        
    }

    @Test
    @DisplayName("Test Create Success")
    public void testCreateSuccess() {
        
    }

    @Test
    @DisplayName("Test Create Fail")
    public void testCreateFail() {
        
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
    @DisplayName("Test Find By Licence Type Success")
    public void testFindByLicenceTypeSuccess() {
        
    }

    @Test
    @DisplayName("Test Find By Licence Type Fail")
    public void testFindByLicenceTypeFail() {
        
    }

    @Test
    @DisplayName("Test Find By Form Success")
    public void testFindByFormSuccess() {
        
    }

    @Test
    @DisplayName("Test Find By Form Fail")
    public void testFindByFormFail() {
        
    }

    @Test
    @DisplayName("Test Update Licence Type Success")
    public void testUpdateLicenceTypeSuccess() {
        
    }

    @Test
    @DisplayName("Test Update Licence Type Fail")
    public void testUpdateLicenceTypeFail() {
        
    }

    @Test
    @DisplayName("Test Update Form Success")
    public void testUpdateFormSuccess() {
        
    }

    @Test
    @DisplayName("Test Update Form Fail")
    public void testUpdateFormFail() {
        
    }

}