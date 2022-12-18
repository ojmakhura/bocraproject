package bw.org.bocra.portal.access.type;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import org.testcontainers.containers.PostgreSQLContainer;

import com.fasterxml.jackson.databind.ObjectMapper;

import bw.org.bocra.portal.BocraportalPostgresqlContainer;

@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class AccessPointTypeRestControllerTest {

    @ClassRule
    public static PostgreSQLContainer postgreSQLContainer = BocraportalPostgresqlContainer.getInstance();

    protected Logger logger = LoggerFactory.getLogger(AccessPointTypeRestControllerTest.class);

    @Autowired
    private RestTemplate restTemplate;
    // private MockMvc mockMvc;

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

    @BeforeEach
    public void dummyData() {
        AccessPointTypeVO type = new AccessPointTypeVO();
        
        type.setCode("test");
        type.setName("Test Type");
        type.setDescription("This is a test");

        accessPointTypeRestController.save(type);

        type = new AccessPointTypeVO();
        
        type.setCode("test2");
        type.setName("Test Type 2");
        type.setDescription("This is a test 2");

        accessPointTypeRestController.save(type);

        type = new AccessPointTypeVO();
        
        type.setCode("test3");
        type.setName("Test Type 3");
        type.setDescription("This is a test 3");

        accessPointTypeRestController.save(type);

        type = new AccessPointTypeVO();
        
        type.setCode("test4");
        type.setName("Test Type4");
        type.setDescription("This is a test 4");

        accessPointTypeRestController.save(type);

        type = new AccessPointTypeVO();
        
        type.setCode("test5");
        type.setName("Test Type 5");
        type.setDescription("This is a test 5");

        accessPointTypeRestController.save(type);

        type = new AccessPointTypeVO();
        
        type.setCode("test6");
        type.setName("Test Type 6");
        type.setDescription("This is a test 6");

        accessPointTypeRestController.save(type);

        type = new AccessPointTypeVO();
        
        type.setCode("test7");
        type.setName("Test Type 7");
        type.setDescription("This is a test 7");

        accessPointTypeRestController.save(type);
        type = new AccessPointTypeVO();
        
        type.setCode("test8");
        type.setName("Test Type 8");
        type.setDescription("This is a test 8");

        accessPointTypeRestController.save(type);

        type = new AccessPointTypeVO();
        
        type.setCode("test9");
        type.setName("Test Type 9");
        type.setDescription("This is a test 9");

        accessPointTypeRestController.save(type);
    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void saveAccessPointTest_whenCreateAccessPointType_thenReturnSavedAccessPointType() throws Exception {
        AccessPointTypeVO type = new AccessPointTypeVO();
        
        type.setCode("test");
        type.setName("Test Type");
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
    public void findById() {

    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void getAll() {

    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void getAllPaged() {

    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void pagedSearch() {

    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void remove() {

    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void save() {

    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void search() {

    }
}
