package bw.org.bocra.portal.access.type;

import bw.org.bocra.portal.BocraportalTestContainer;
import bw.org.bocra.portal.licence.type.LicenceType;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

import org.junit.Before;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.containers.PostgreSQLContainer;

@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class AccessPointTypeRestControllerTest {

    @ClassRule
    public static PostgreSQLContainer postgreSQLContainer = BocraportalTestContainer.getInstance();

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
    public void dummyData(int size) {

        for (int i = 1; i <= size; i++){
            
            AccessPointTypeVO type = new AccessPointTypeVO();
            
            type.setCode("test" + i);
            type.setName("Test Type " + i);
            type.setDescription("This is a test " + i);

            accessPointTypeRestController.save(type);
        }

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
        // dummyData();
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

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void findById() {
        // dummyData();
        AccessPointTypeVO type = new AccessPointTypeVO();
        
        type.setCode("test10");
        type.setName("Test Type 10");
        type.setDescription("This is a test");
        type = accessPointTypeService.save(type);

        ResponseEntity<?> response = accessPointTypeRestController.findById(type.getId());

        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.FOUND);
        AccessPointTypeVO found = (AccessPointTypeVO) response.getBody();

        Assertions.assertNotNull(found);
        Assertions.assertEquals(found.getId(), type.getId());
        Assertions.assertEquals(found.getCode(), type.getCode());
        Assertions.assertEquals(found.getName(), type.getName());
    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void findById_notExisting() {
        // dummyData();
        AccessPointTypeVO type = new AccessPointTypeVO();
        
        type.setCode("test10");
        type.setName("Test Type 10");
        type.setDescription("This is a test");
        type = accessPointTypeService.save(type);
        Long id = type.getId();

        ResponseEntity<?> response = accessPointTypeRestController.findById(id + 10);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
        
        String message = response.getBody().toString();
        Assertions.assertTrue(message.contains(String.format("Access point type with id %d not found.", id + 10)));
    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void getAll() {
        dummyData(9);
        ResponseEntity<?> response = accessPointTypeRestController.getAll();

        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.FOUND);
        Collection<AccessPointTypeVO> types = (Collection<AccessPointTypeVO>) response.getBody();
        Assertions.assertTrue(CollectionUtils.isNotEmpty(types));
        Assertions.assertEquals(types.size(), 9);

    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void getAll_empty() {
        ResponseEntity<?> response = accessPointTypeRestController.getAll();

        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.NO_CONTENT);
        Collection<AccessPointTypeVO> types = (Collection<AccessPointTypeVO>) response.getBody();
        Assertions.assertTrue(CollectionUtils.isEmpty(types));

    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void getAllPaged() {
        dummyData(25);
        int pageNumber = 2;
        int pageSize = 4;
        ResponseEntity<?> response = accessPointTypeRestController.getAllPaged(pageNumber, pageSize);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.FOUND);
        Collection<AccessPointTypeVO> types = (Collection<AccessPointTypeVO>) response.getBody();
        Assertions.assertTrue(CollectionUtils.isNotEmpty(types));
        Assertions.assertEquals(types.size(), pageSize);
    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void getAllPaged_lastPage() {
        dummyData(15);
        int pageNumber = 3;
        int pageSize = 4;

        ResponseEntity<?> response = accessPointTypeRestController.getAllPaged(pageNumber, pageSize);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.FOUND);
        Collection<AccessPointTypeVO> types = (Collection<AccessPointTypeVO>) response.getBody();
        Assertions.assertTrue(CollectionUtils.isNotEmpty(types));
        Assertions.assertEquals(types.size(), 3);
    }

    // @WithMockUser(username = "testuser4", password = "testuser1")
    // @Test
    // public void pagedSearch() {
    //     dummyData(15);
    //     int pageNumber = 3;
    //     int pageSize = 4;

    //     ResponseEntity<?> response = accessPointTypeRestController.getAllPaged(pageNumber, pageSize);

    //     Assertions.assertNotNull(response);
    //     Assertions.assertEquals(response.getStatusCode(), HttpStatus.FOUND);
    //     Collection<AccessPointTypeVO> types = (Collection<AccessPointTypeVO>) response.getBody();
    //     Assertions.assertTrue(CollectionUtils.isNotEmpty(types));
    //     Assertions.assertEquals(types.size(), 3);

    // }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void remove() {
        dummyData(15);
        ResponseEntity<?> response = accessPointTypeRestController.getAll();
        Collection<AccessPointTypeVO> types = (Collection<AccessPointTypeVO>) response.getBody();
        AccessPointTypeVO t = types.iterator().next();

        response = accessPointTypeRestController.remove(t.getId());
        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assertions.assertTrue((boolean) response.getBody());

        response = accessPointTypeRestController.getAll();
        Collection<AccessPointTypeVO> types2 = (Collection<AccessPointTypeVO>) response.getBody();

        Assertions.assertEquals(types2.size(), types.size()-1);

    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void remove_non_existing() {
        dummyData(10);
        ResponseEntity<?> response = accessPointTypeRestController.getAll();
        Collection<AccessPointTypeVO> types = (Collection<AccessPointTypeVO>) response.getBody();

        response = accessPointTypeRestController.remove(30L);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
        Assertions.assertTrue((boolean) response.getBody());

        response = accessPointTypeRestController.getAll();
        Collection<AccessPointTypeVO> types2 = (Collection<AccessPointTypeVO>) response.getBody();

        Assertions.assertEquals(types2.size(), types.size());

    }

    // @WithMockUser(username = "testuser4", password = "testuser1")
    // @Test
    // public void search() {

    // }
}
