package bw.org.bocra.portal.access.type;

import java.util.Collection;

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

import bw.org.bocra.portal.GenericRestTest;
import bw.org.bocra.portal.GenericTestData;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class AccessPointTypeRestControllerTest
        extends GenericRestTest<AccessPointTypeVO, AccessPointTypeRepository, String, AccessPointTypeRestController> {

    protected Logger logger = LoggerFactory.getLogger(AccessPointTypeRestControllerTest.class);

    private String path = "/access/type";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AccessPointTypeService accessPointTypeService;

    @Autowired
    private AccessPointTypeTestData accessPointTypeTestData;

    @Autowired
    public AccessPointTypeRestControllerTest(AccessPointTypeRestController restController,
            GenericTestData<AccessPointTypeVO, AccessPointTypeRepository, String, AccessPointTypeRestController> testData) {
        super(restController, testData);
    }

    @BeforeEach
    public void clean() {
        accessPointTypeTestData.clean();
    }

    public Collection<AccessPointTypeVO> dummyData(int size) {

        return accessPointTypeTestData.generateSequentialData(size);
    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void saveAccessPointTest_missing() {

        ResponseEntity<?> response = restController.save(null);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        logger.info(response.getBody().toString());
        String message = response.getBody().toString();
        Assertions.assertTrue(message.contains("information is missing"));

    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void saveAccessPointTest_missingCode() {
        AccessPointTypeVO type = accessPointTypeTestData.createUnsavedData();

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
    public void saveAccessPointTest_missingName() {
        AccessPointTypeVO type = accessPointTypeTestData.createUnsavedData();

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
    public void saveAccessPointTest_missingDescription() {
        AccessPointTypeVO type = accessPointTypeTestData.createUnsavedData();

        type.setDescription(null);

        ResponseEntity<?> response = restController.save(type);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        type = (AccessPointTypeVO) response.getBody();
        Assertions.assertNotNull(type);
        Assertions.assertNotNull(type.getId());
        logger.info(type.toString());

    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void saveAccessPointTest_sameCode() {
        AccessPointTypeVO exists = (AccessPointTypeVO) dummyData(1).iterator().next();

        AccessPointTypeVO type = new AccessPointTypeVO();

        type.setCode(exists.getCode());
        type.setName("Test Type 21");
        type.setDescription("This is a test");
        ResponseEntity<?> response = restController.save(type);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        logger.info(response.getBody().toString());

    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void saveAccessPointTest_sameName() {
        AccessPointTypeVO exists = (AccessPointTypeVO) dummyData(1).iterator().next();
        // Ensure the data we just saved has been committed.
        this.accessPointTypeService.getAll();
        AccessPointTypeVO type = new AccessPointTypeVO();

        type.setCode("test31");
        type.setName(exists.getName());
        type.setDescription("This is a test");
        ResponseEntity<?> response = restController.save(type);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        logger.info(response.getBody().toString());
        String message = response.getBody().toString();
        Assertions.assertTrue(message.contains("this name"));
    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void saveAccessPointTest_existingDescription() throws Exception {
        dummyData(9);
        AccessPointTypeVO type = new AccessPointTypeVO();

        type.setCode("test10");
        type.setName("Test Type 10");
        type.setDescription("This is a test");
        ResponseEntity<?> response = restController.save(type);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        type = (AccessPointTypeVO) response.getBody();
        Assertions.assertNotNull(type);
        Assertions.assertNotNull(type.getId());
        logger.info(type.toString());
    }

    // @WithMockUser(username = "testuser4", password = "testuser1")
    // @Test
    // public void search_six() {

    //     accessPointTypeTestData.searchData();

    //     ResponseEntity<?> response = restController.search("six");

    //     Assertions.assertNotNull(response);
    //     Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
    //     Collection<AccessPointTypeVO> types = (Collection<AccessPointTypeVO>) response.getBody();
    //     Assertions.assertEquals(types.size(), 2);
    // }

    @Override
    protected ResponseEntity<?> handlePagedSearch(Integer pageNumber, Integer pageSize, String criteria) throws Exception {
        return restController.getAllPaged(pageNumber, pageSize);
    }

    @Override
    protected void basicCompareAssertions(AccessPointTypeVO o1, AccessPointTypeVO o2) {
        AccessPointTypeVO type1 = (AccessPointTypeVO) o1;
        AccessPointTypeVO type2 = (AccessPointTypeVO) o2;

        Assertions.assertEquals(type1.getId(), type2.getId());
        Assertions.assertEquals(type1.getCode(), type2.getCode());
        Assertions.assertEquals(type1.getName(), type2.getName());
    }

    @Override
    protected Class<String> getCriteriaClass() {
        
        return String.class;
    }

    @Override
    protected Class<AccessPointTypeVO> getDataClass() {
        return AccessPointTypeVO.class;
    }

    @Override
    protected void searchResultsAssertions(ResponseEntity<?> response) {
        Collection<AccessPointTypeVO> types = (Collection<AccessPointTypeVO>) response.getBody();
        Assertions.assertEquals(types.size(), 2);
    }
}
