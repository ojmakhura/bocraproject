package bw.org.bocra.portal.access.type;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.ClassRule;
import org.junit.jupiter.api.Assertions;
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
public class AccessPointTypeRestControllerTest extends GenericRestTest {

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

    @BeforeEach
    public void clean() {
        accessPointTypeRepository.deleteAll();
    }

    // @BeforeEach
    public Collection<?> dummyData(int size) {

        Collection types = new ArrayList<>();

        for (int i = 1; i <= size; i++) {

            AccessPointTypeVO type = new AccessPointTypeVO();

            type.setCode("test" + i);
            type.setName("Test Type " + i);
            type.setDescription("This is a test " + i);

            types.add(accessPointTypeRestController.save(type).getBody());
        }

        return types;
    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    @DisplayName(value = "Testing the successful creation of a new Access Point Type.")
    public void saveAccessPointType() throws Exception {
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
    public void saveAccessPointTest_missing() {
        // dummyData();
        AccessPointTypeVO type = new AccessPointTypeVO();

        type.setName("Test Type 10");
        type.setDescription("This is a test");
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
        // dummyData();
        AccessPointTypeVO type = new AccessPointTypeVO();

        type.setName("Test Type 10");
        type.setDescription("This is a test");
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
        // dummyData();
        AccessPointTypeVO type = new AccessPointTypeVO();

        type.setCode("test");
        type.setDescription("This is a test");
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
        AccessPointTypeVO type = new AccessPointTypeVO();

        type.setCode("test12");
        type.setName("Test Type 12");
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
        dummyData(9);
        AccessPointTypeVO type = new AccessPointTypeVO();

        type.setCode("test1");
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
        dummyData(9);
        AccessPointTypeVO type = new AccessPointTypeVO();

        type.setCode("test31");
        type.setName("Test Type 1");
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

    // @WithMockUser(username = "testuser4", password = "testuser1")
    // @Test
    // public void findById() {
    //     // dummyData();
    //     AccessPointTypeVO type = new AccessPointTypeVO();

    //     type.setCode("test10");
    //     type.setName("Test Type 10");
    //     type.setDescription("This is a test");
    //     type = accessPointTypeService.save(type);

    //     ResponseEntity<?> response = accessPointTypeRestController.findById(type.getId());

    //     Assertions.assertNotNull(response);
    //     Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
    //     AccessPointTypeVO found = (AccessPointTypeVO) response.getBody();

    //     Assertions.assertNotNull(found);
    //     Assertions.assertEquals(found.getId(), type.getId());
    //     Assertions.assertEquals(found.getCode(), type.getCode());
    //     Assertions.assertEquals(found.getName(), type.getName());
    // }

    // @WithMockUser(username = "testuser4", password = "testuser1")
    // @Test
    // public void findById_notExisting() {
    //     // dummyData();
    //     AccessPointTypeVO type = new AccessPointTypeVO();

    //     type.setCode("test10");
    //     type.setName("Test Type 10");
    //     type.setDescription("This is a test");
    //     type = accessPointTypeService.save(type);
    //     Long id = type.getId();

    //     ResponseEntity<?> response = accessPointTypeRestController.findById(id + 10);

    //     Assertions.assertNotNull(response);
    //     Assertions.assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);

    //     String message = response.getBody().toString();
    //     Assertions.assertTrue(message.contains(String.format("Access point type with id %d not found.", id + 10)));
    // }

    // @WithMockUser(username = "testuser4", password = "testuser1")
    // @Test
    // public void getAll() {
    //     dummyData(9);
    //     ResponseEntity<?> response = accessPointTypeRestController.getAll();

    //     Assertions.assertNotNull(response);
    //     Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
    //     Collection<AccessPointTypeVO> types = (Collection<AccessPointTypeVO>) response.getBody();
    //     Assertions.assertTrue(CollectionUtils.isNotEmpty(types));
    //     Assertions.assertEquals(types.size(), 9);

    // }

    // @WithMockUser(username = "testuser4", password = "testuser1")
    // @Test
    // public void getAll_empty() {
    //     ResponseEntity<?> response = accessPointTypeRestController.getAll();

    //     Assertions.assertNotNull(response);
    //     Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
    //     Collection<AccessPointTypeVO> types = (Collection<AccessPointTypeVO>) response.getBody();
    //     Assertions.assertTrue(CollectionUtils.isEmpty(types));

    // }

    // @WithMockUser(username = "testuser4", password = "testuser1")
    // @Test
    // public void getAllPaged() {
    //     dummyData(25);
    //     int pageNumber = 2;
    //     int pageSize = 4;
    //     ResponseEntity<?> response = accessPointTypeRestController.getAllPaged(pageNumber, pageSize);

    //     Assertions.assertNotNull(response);
    //     Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
    //     Collection<AccessPointTypeVO> types = (Collection<AccessPointTypeVO>) response.getBody();
    //     Assertions.assertTrue(CollectionUtils.isNotEmpty(types));
    //     Assertions.assertEquals(types.size(), pageSize);
    // }

    // @WithMockUser(username = "testuser4", password = "testuser1")
    // @Test
    // public void getAllPaged_lastPage() {
    //     dummyData(15);
    //     int pageNumber = 3;
    //     int pageSize = 4;

    //     ResponseEntity<?> response = accessPointTypeRestController.getAllPaged(pageNumber, pageSize);

    //     Assertions.assertNotNull(response);
    //     Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
    //     Collection<AccessPointTypeVO> types = (Collection<AccessPointTypeVO>) response.getBody();
    //     Assertions.assertTrue(CollectionUtils.isNotEmpty(types));
    //     Assertions.assertEquals(types.size(), 3);
    // }

    // @WithMockUser(username = "testuser4", password = "testuser1")
    // @Test
    // public void remove() {
    //     dummyData(15);
    //     ResponseEntity<?> response = accessPointTypeRestController.getAll();
    //     Collection<AccessPointTypeVO> types = (Collection<AccessPointTypeVO>) response.getBody();
    //     AccessPointTypeVO t = types.iterator().next();

    //     response = accessPointTypeRestController.remove(t.getId());
    //     Assertions.assertNotNull(response);
    //     Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
    //     Assertions.assertTrue((boolean) response.getBody());

    //     response = accessPointTypeRestController.getAll();
    //     Collection<AccessPointTypeVO> types2 = (Collection<AccessPointTypeVO>) response.getBody();

    //     Assertions.assertEquals(types2.size(), types.size() - 1);

    // }

    // @WithMockUser(username = "testuser4", password = "testuser1")
    // @Test
    // public void remove_non_existing() {
    //     dummyData(10);
    //     ResponseEntity<?> response = accessPointTypeRestController.getAll();
    //     Collection<AccessPointTypeVO> types = (Collection<AccessPointTypeVO>) response.getBody();

    //     response = accessPointTypeRestController.remove(30L);
    //     Assertions.assertNotNull(response);
    //     Assertions.assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    //     Assertions.assertTrue(response.getBody().toString().contains("Could not delete access point type"));

    //     response = accessPointTypeRestController.getAll();
    //     Collection<AccessPointTypeVO> types2 = (Collection<AccessPointTypeVO>) response.getBody();

    //     Assertions.assertEquals(types2.size(), types.size());

    // }

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

    // @WithMockUser(username = "testuser4", password = "testuser1")
    // @Test
    // public void search_no_results() {
        
    //     this.searchData();

    //     ResponseEntity<?> response = accessPointTypeRestController.search("zero");

    //     Assertions.assertNotNull(response);
    //     Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
    //     Collection<AccessPointTypeVO> types = (Collection<AccessPointTypeVO>) response.getBody();
    //     Assertions.assertEquals(types.size(), 0);
    // }

    // @WithMockUser(username = "testuser4", password = "testuser1")
    // @Test
    // public void search_empty() {
        
    //     this.searchData();

    //     ResponseEntity<?> response = accessPointTypeRestController.search("");

    //     Assertions.assertNotNull(response);
    //     Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
    //     Collection<AccessPointTypeVO> types = (Collection<AccessPointTypeVO>) response.getBody();
    //     Assertions.assertEquals(types.size(), 7);
    // }

    // @WithMockUser(username = "testuser4", password = "testuser1")
    // @Test
    // public void search_null() {
        
    //     this.searchData();

    //     ResponseEntity<?> response = accessPointTypeRestController.search(null);

    //     Assertions.assertNotNull(response);
    //     Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
    //     Collection<AccessPointTypeVO> types = (Collection<AccessPointTypeVO>) response.getBody();
    //     Assertions.assertEquals(types.size(), 7);
    // }

    // @WithMockUser(username = "testuser4", password = "testuser1")
    // @Test
    // public void search_space() {
        
    //     this.searchData();

    //     ResponseEntity<?> response = accessPointTypeRestController.search(" ");

    //     Assertions.assertNotNull(response);
    //     Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
    //     Collection<AccessPointTypeVO> types = (Collection<AccessPointTypeVO>) response.getBody();
    //     Assertions.assertEquals(types.size(), 7);
    // }

    @Override
    protected Object unsavedDummyData() {
        AccessPointTypeVO type = new AccessPointTypeVO();

        type.setCode("test10");
        type.setName("Test Type 10");
        type.setDescription("This is a test");

        return type;
    }

    @Override
    protected ResponseEntity<?> handleGetAll() {
        // dummyData(9);
        return accessPointTypeRestController.getAll();
    }

    @Override
    protected ResponseEntity<?> handleGetAllPaged(int pageNumber, int pageSize) {
        // dummyData(25);
        return accessPointTypeRestController.getAllPaged(pageNumber, pageSize);
    }

    @Override
    protected ResponseEntity<?> handleFindById(Long id) {
        // AccessPointTypeVO type = (AccessPointTypeVO) unsavedDummyData();
        // type = accessPointTypeService.save(type);

        return accessPointTypeRestController.findById(id);
    }

    @Override
    protected ResponseEntity<?> handleRemove(Long id) {
        return accessPointTypeRestController.remove(id);
    }

    @Override
    protected ResponseEntity<?> handleSearch(Object criteria) {
        return accessPointTypeRestController.search(criteria.toString());
    }

    @Override
    protected ResponseEntity<?> handlePagedSearch(int pageNumber, int pageSize, Object criteria) {
        // dummyData(25);
        return accessPointTypeRestController.getAllPaged(pageNumber, pageSize);
    }

    @Override
    protected ResponseEntity<?> handleSave(Object o) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected void basicCompareAssertions(Object o1, Object o2) {
        AccessPointTypeVO type1 = (AccessPointTypeVO) o1;
        AccessPointTypeVO type2 = (AccessPointTypeVO) o2;

        Assertions.assertEquals(type1.getId(), type2.getId());
        Assertions.assertEquals(type1.getCode(), type2.getCode());
        Assertions.assertEquals(type1.getName(), type2.getName());
    }

    @Override
    protected Collection<?> searchData() {

        Collection types = new ArrayList();

        AccessPointTypeVO type = new AccessPointTypeVO();

        type.setCode("test");
        type.setName("Test Type");
        type.setDescription("This is a test");

        types.add(accessPointTypeRestController.save(type).getBody());

        type = new AccessPointTypeVO();

        type.setCode("serious");
        type.setName("Serious Type");
        type.setDescription("This is a test");

        types.add(accessPointTypeRestController.save(type).getBody());

        type = new AccessPointTypeVO();

        type.setCode("onelove");
        type.setName("Top love");
        type.setDescription("This is a test");

        types.add(accessPointTypeRestController.save(type).getBody());

        type = new AccessPointTypeVO();

        type.setCode("test6");
        type.setName("Test Type 6");
        type.setDescription("This is a test");

        types.add(accessPointTypeRestController.save(type).getBody());

        type = new AccessPointTypeVO();

        type.setCode("sixteen");
        type.setName("One Six");
        type.setDescription("This is a test");

        types.add(accessPointTypeRestController.save(type).getBody());

        type = new AccessPointTypeVO();

        type.setCode("test16");
        type.setName("Testing sixteen");
        type.setDescription("This is a test");

        types.add(accessPointTypeRestController.save(type).getBody());

        type = new AccessPointTypeVO();

        type.setCode("stop");
        type.setName("Test Type Stop");
        type.setDescription("This is a test");

        types.add(accessPointTypeRestController.save(type).getBody());
        return types;
    }

    @Override
    protected Object searchCriteria() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected Object searchCriteriaNone() {
        return accessPointTypeRestController.search("six");
    }

    @Override
    protected Object searchCriteriaEmpty() {
        return accessPointTypeRestController.search("");
    }
}
