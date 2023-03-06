// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by SpringWS.java.vsl in andromda-webservices. Do not modify by hand!.
//
package bw.org.bocra.portal.licence.type;

import java.time.LocalDateTime;
import java.util.Collection;

import javax.transaction.Transactional;
// import org.junit.ClassRule;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
// import org.testcontainers.containers.PostgreSQLContainer;

// import bw.org.bocra.portal.BocraportalTestContainer;
import bw.org.bocra.portal.GenericRestTest;
import bw.org.bocra.portal.GenericTestData;

@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@Transactional
public class LicenceTypeRestControllerTest 
         extends GenericRestTest<LicenceTypeVO, LicenceTypeRepository, LicenceTypeCriteria, LicenceTypeRestController> {

    // @ClassRule
    // public static PostgreSQLContainer postgreSQLContainer = BocraportalTestContainer.getInstance();

    private String path = "/licence/type";

    @Autowired
    private MockMvc mockMvc;

    protected Logger logger = LoggerFactory.getLogger(LicenceTypeRestControllerTest.class);

    @Autowired
    private LicenceTypeService licenceTypeService;

    @Autowired
    private LicenceTypeTestData licenceTypeTestData;

    @Autowired
    public LicenceTypeRestControllerTest(LicenceTypeRestController restController,
            GenericTestData<LicenceTypeVO, LicenceTypeRepository, LicenceTypeCriteria, LicenceTypeRestController> testData) {
        super(restController, testData);
    }
    
    @BeforeEach
    public void clean() {
        testData.clean();
        licenceTypeTestData.clean();
    }

    public Collection<LicenceTypeVO> dummyData(int size) {

        return licenceTypeTestData.generateSequentialData(size);
    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void saveLicenceTypeTest_missing() {

        ResponseEntity<?> response = restController.save(null);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        logger.info(response.getBody().toString());
        String message = response.getBody().toString();
        Assertions.assertTrue(message.contains("information is missing"));

    }
    
    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void saveLicenceTypeTest_missingCode() {
        LicenceTypeVO type = licenceTypeTestData.createUnsavedData();

        type.setCode(null);
        ResponseEntity<?> response = restController.save(type);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        logger.info(response.getBody().toString());
        String message = response.getBody().toString();
        Assertions.assertTrue(message.contains("code is missing"));

    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void saveLicenceTypeTest_missingName() {
        LicenceTypeVO type = licenceTypeTestData.createUnsavedData();

        type.setName(null);
        ResponseEntity<?> response = restController.save(type);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        logger.info(response.getBody().toString());
        String message = response.getBody().toString();
        Assertions.assertTrue(message.contains("name is missing"));

    }

    
    
    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void saveLicenceTypeTest_missingDescription() {
        LicenceTypeVO type = licenceTypeTestData.createUnsavedData();

        type.setDescription(null);

        ResponseEntity<?> response = restController.save(type);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        type = (LicenceTypeVO) response.getBody();
        Assertions.assertNotNull(type);
        Assertions.assertNotNull(type.getId());
        logger.info(type.toString());

    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void saveLicenceTypeTest_sameCode() {
        LicenceTypeVO exists = (LicenceTypeVO) dummyData(1).iterator().next();

        LicenceTypeVO type = new LicenceTypeVO();

        type.setCode(exists.getCode());
        type.setName("Test Type 21");
        type.setDescription("This is a test");
        type.setCreatedBy("testuser4");
        type.setCreatedDate(LocalDateTime.now());
        ResponseEntity<?> response = restController.save(type);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        logger.info(response.getBody().toString());

    }
    
    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void saveLicenceTypeTest_sameName() {
        LicenceTypeVO exists = (LicenceTypeVO) dummyData(1).iterator().next();
        // Ensure the data we just saved has been committed.
        this.licenceTypeService.getAll();
        LicenceTypeVO type = new LicenceTypeVO();

        type.setName(exists.getName());
        type.setCode("Test Type 21");
        type.setDescription("This is a test");
        type.setCreatedBy("testuser4");
        type.setCreatedDate(LocalDateTime.now());
        ResponseEntity<?> response = restController.save(type);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        logger.info(response.getBody().toString());

        // String message = response.getBody().toString();
        // Assertions.assertTrue(message.contains("this name"));
    } 

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void saveLicenceTypeTest_existingDescription() throws Exception {
        dummyData(9);
        LicenceTypeVO type = new LicenceTypeVO();

        type.setCode("test10");
        type.setName("Test Type 10");
        type.setDescription("This is a test");
        ResponseEntity<?> response = restController.save(type);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        type = (LicenceTypeVO) response.getBody();
        Assertions.assertNotNull(type);
        Assertions.assertNotNull(type.getId());
        logger.info(type.toString());
    }

    @Override
    protected void basicCompareAssertions(LicenceTypeVO o1, LicenceTypeVO o2) {
        LicenceTypeVO type1 = (LicenceTypeVO) o1;
        LicenceTypeVO type2 = (LicenceTypeVO) o2;

        Assertions.assertEquals(type1.getId(), type2.getId());
        Assertions.assertEquals(type1.getCode(), type2.getCode());
        Assertions.assertEquals(type1.getName(), type2.getName());
    }


    @Override
    protected Class<LicenceTypeCriteria> getCriteriaClass() {
        return LicenceTypeCriteria.class;
    }
    
    

    @Override
    protected Class<LicenceTypeVO> getDataClass() {
        return LicenceTypeVO.class;
    }


    @Override
    protected void searchResultsAssertions(ResponseEntity<?> response) {
        // TODO Auto-generated method stub
        Collection<LicenceTypeVO> types = (Collection<LicenceTypeVO>) response.getBody();
        Assertions.assertEquals(types.size(), 7);;
    }


}