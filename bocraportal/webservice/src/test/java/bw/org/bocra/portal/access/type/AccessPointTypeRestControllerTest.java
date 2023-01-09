package bw.org.bocra.portal.access.type;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
import org.testcontainers.containers.PostgreSQLContainer;

import com.fasterxml.jackson.databind.ObjectMapper;

import bw.org.bocra.portal.BocraportalTestContainer;
// import bw.org.bocra.portal.BocraportalTestContainer;
import bw.org.bocra.portal.GenericRestTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class AccessPointTypeRestControllerTest extends GenericRestTest<AccessPointTypeVO, String> {

    // @ClassRule
    // public static PostgreSQLContainer postgreSQLContainer = BocraportalTestContainer.getInstance();

    protected Logger logger = LoggerFactory.getLogger(AccessPointTypeRestControllerTest.class);

    private String path = "/access/type";

    @Autowired
    // private RestTemplate restTemplate;
    private MockMvc mockMvc;

    @Autowired
    private AccessPointTypeService accessPointTypeService;

    @Autowired
    private AccessPointTypeRestController accessPointTypeRestController;

    @Autowired
    private AccessPointTypeRepository accessPointTypeRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AccessPointTypeTestData accessPointTypeTestData;

    // public AccessPointTypeRestControllerTest() {
    //     accessPointTypeTestData.setAccessPointTypeRestController(accessPointTypeRestController);
    // }

    @BeforeEach
    public void clean() {
        accessPointTypeRepository.deleteAll();
    }

    public Collection<AccessPointTypeVO> dummyData(int size) {

        return accessPointTypeTestData.generateSequentialData(size);
    }


    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void saveAccessPointTest_missing() {
        
        ResponseEntity<?> response = accessPointTypeRestController.save(null);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        logger.info(response.getBody().toString());
        String message = response.getBody().toString();
        Assertions.assertTrue(message.contains("information is missing"));

    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void saveAccessPointTest_missingCode() {
        AccessPointTypeVO type = accessPointTypeTestData.createUnsavedAccessPointType();

        type.setCode(null);
        ResponseEntity<?> response = accessPointTypeRestController.save(type);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        logger.info(response.getBody().toString());
        String message = response.getBody().toString();
        Assertions.assertTrue(message.contains("code is missing"));

    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void saveAccessPointTest_missingName() {
        AccessPointTypeVO type = accessPointTypeTestData.createUnsavedAccessPointType();

        type.setName(null);
        ResponseEntity<?> response = accessPointTypeRestController.save(type);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        logger.info(response.getBody().toString());
        String message = response.getBody().toString();
        Assertions.assertTrue(message.contains("name is missing"));

    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void saveAccessPointTest_missingDescription() {
        AccessPointTypeVO type = accessPointTypeTestData.createUnsavedAccessPointType();

        type.setDescription(null);
        
        ResponseEntity<?> response = accessPointTypeRestController.save(type);
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
        ResponseEntity<?> response = accessPointTypeRestController.save(type);
        
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
        ResponseEntity<?> response = accessPointTypeRestController.save(type);
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
        ResponseEntity<?> response = accessPointTypeRestController.save(type);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        type = (AccessPointTypeVO) response.getBody();
        Assertions.assertNotNull(type);
        Assertions.assertNotNull(type.getId());
        logger.info(type.toString());
    }


    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void search_six() {
        
        this.searchData();

        ResponseEntity<?> response = accessPointTypeRestController.search("six");

        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Collection<AccessPointTypeVO> types = (Collection<AccessPointTypeVO>) response.getBody();
        Assertions.assertEquals(types.size(), 2);
    }

    @Override
    protected AccessPointTypeVO unsavedDummyData() {
        
        return accessPointTypeTestData.createUnsavedAccessPointType();
    }

    @Override
    protected ResponseEntity<?> handleGetAll() {
        return accessPointTypeRestController.getAll();
    }

    @Override
    protected ResponseEntity<?> handleGetAllPaged(int pageNumber, int pageSize) {
        return accessPointTypeRestController.getAllPaged(pageNumber, pageSize);
    }

    @Override
    protected ResponseEntity<?> handleFindById(Long id) {

        return accessPointTypeRestController.findById(id);
    }

    @Override
    protected ResponseEntity<?> handleRemove(Long id) {
        return accessPointTypeRestController.remove(id);
    }

    @Override
    protected ResponseEntity<?> handleSearch(String criteria) {
        return accessPointTypeRestController.search(criteria);
    }

    @Override
    protected ResponseEntity<?> handlePagedSearch(int pageNumber, int pageSize, String criteria) {
        return accessPointTypeRestController.getAllPaged(pageNumber, pageSize);
    }

    @Override
    protected ResponseEntity<?> handleSave(AccessPointTypeVO o) {
        
        return accessPointTypeRestController.save(o);
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
    protected Collection<AccessPointTypeVO> searchData() {

        return accessPointTypeTestData.generateSearchData();

    }

    @Override
    protected String searchCriteria() {
        return null;
    }

    @Override
    protected String searchCriteriaEmpty() {
        return "";
    }

    @Override
    protected String searchCriteriaNone() {
        return "null";
    }
}
