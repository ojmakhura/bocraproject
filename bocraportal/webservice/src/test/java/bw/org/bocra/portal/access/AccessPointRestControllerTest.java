// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by SpringWS.java.vsl in andromda-webservices. Do not modify by hand!.
//
package bw.org.bocra.portal.access;

import bw.org.bocra.portal.BocraportalTestContainer;
import bw.org.bocra.portal.access.type.AccessPointTypeService;
import bw.org.bocra.portal.access.type.AccessPointTypeVO;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
import java.util.Collection;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.ClassRule;
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
public class AccessPointRestControllerTest {

    @ClassRule
    public static PostgreSQLContainer postgreSQLContainer = BocraportalTestContainer.getInstance();

    private String path = "/access";

    protected Logger logger = LoggerFactory.getLogger(AccessPointRestControllerTest.class);

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AccessPointRestController accessPointRestController;

    @Autowired
    private AccessPointTypeService accessPointTypeService;

    @Autowired
    protected AccessPointService accessPointService;

    @Autowired
    private AccessPointRepository accessPointRepository;

    public void dummyData(int size) {

        AccessPointTypeVO type = new AccessPointTypeVO();

        type.setCode("test");
        type.setName("Test Type");
        type.setDescription("This is a test");

        type = accessPointTypeService.save(type);

        for (int i = 1; i <= size; i++) {

            AccessPointVO point = new AccessPointVO();

            point.setAccessPointType(type);
            point.setCreatedBy("testuser4");
            point.setCreatedDate(LocalDateTime.now());
            point.setName("Test Type " + i);
            point.setUrl("/test" + i);

            accessPointRestController.save(point);
        }

    }

    @BeforeEach
    public void clean() {
        accessPointRepository.deleteAll();
    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void save() {
        AccessPointTypeVO type = new AccessPointTypeVO();

        type.setCode("test");
        type.setName("Test Type");
        type.setDescription("This is a test");

        type = accessPointTypeService.save(type);

        AccessPointVO point = new AccessPointVO();

        point.setAccessPointType(type);
        point.setCreatedBy("testuser4");
        point.setCreatedDate(LocalDateTime.now());
        point.setName("Test Type ");
        point.setUrl("/test");

        ResponseEntity<?> response = accessPointRestController.save(point);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        point = (AccessPointVO) response.getBody();
        Assertions.assertNotNull(point);
        Assertions.assertNotNull(point.getId());
    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void save_noType() {
        
        AccessPointVO point = new AccessPointVO();
        point.setCreatedBy("testuser4");
        point.setCreatedDate(LocalDateTime.now());
        point.setName("Test Type ");
        point.setUrl("/test");

        ResponseEntity<?> response = accessPointRestController.save(point);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        String message = response.getBody().toString();
        System.out.println(message);
        Assertions.assertTrue(message.contains("access point type is missing"));
    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void save_badType() {
        AccessPointTypeVO type = new AccessPointTypeVO();

        AccessPointVO point = new AccessPointVO();

        point.setAccessPointType(type);
        point.setCreatedBy("testuser4");
        point.setCreatedDate(LocalDateTime.now());
        point.setName("Test Type ");
        point.setUrl("/test");

        ResponseEntity<?> response = accessPointRestController.save(point);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        String message = response.getBody().toString();
        System.out.println(message);
        Assertions.assertTrue(
            message.contains("access point type is not valid")
            || message.contains("The access point type is invalid")
            || message.contains("access point type or its id is missing")
        );
    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void save_nullCreatedDate() {
        AccessPointTypeVO type = new AccessPointTypeVO();

        type.setCode("test");
        type.setName("Test Type");
        type.setDescription("This is a test");

        type = accessPointTypeService.save(type);

        AccessPointVO point = new AccessPointVO();

        point.setAccessPointType(type);
        point.setCreatedBy("testuser4");
        point.setName("Test Type ");
        point.setUrl("/test");

        ResponseEntity<?> response = accessPointRestController.save(point);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        String message = response.getBody().toString();
        System.out.println(message);
        Assertions.assertTrue(message.contains("created date value is missing"));
    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void save_nullCreatedBy() {
        AccessPointTypeVO type = new AccessPointTypeVO();

        type.setCode("test");
        type.setName("Test Type");
        type.setDescription("This is a test");

        type = accessPointTypeService.save(type);

        AccessPointVO point = new AccessPointVO();

        point.setAccessPointType(type);
        point.setCreatedDate(LocalDateTime.now());
        point.setName("Test Type ");
        point.setUrl("/test");

        ResponseEntity<?> response = accessPointRestController.save(point);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        String message = response.getBody().toString();
        System.out.println(message);
        Assertions.assertTrue(message.contains("created-by value is missing"));
    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void save_nullName() {
        AccessPointTypeVO type = new AccessPointTypeVO();

        type.setCode("test");
        type.setName("Test Type");
        type.setDescription("This is a test");

        type = accessPointTypeService.save(type);

        AccessPointVO point = new AccessPointVO();

        point.setAccessPointType(type);
        point.setCreatedBy("testuser4");
        point.setCreatedDate(LocalDateTime.now());
        point.setUrl("/test");

        ResponseEntity<?> response = accessPointRestController.save(point);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        String message = response.getBody().toString();
        System.out.println(message);
        Assertions.assertTrue(message.contains("access point name is missing"));
    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void save_emptyName() {
        AccessPointTypeVO type = new AccessPointTypeVO();

        type.setCode("test");
        type.setName("Test Type");
        type.setDescription("This is a test");

        type = accessPointTypeService.save(type);

        AccessPointVO point = new AccessPointVO();

        point.setAccessPointType(type);
        point.setCreatedBy("testuser4");
        point.setCreatedDate(LocalDateTime.now());
        point.setUrl("/test");
        point.setName(" ");

        ResponseEntity<?> response = accessPointRestController.save(point);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        String message = response.getBody().toString();
        System.out.println(message);
        Assertions.assertTrue(message.contains("access point name is missing"));
    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void save_nullUrl() {
        AccessPointTypeVO type = new AccessPointTypeVO();

        type.setCode("test");
        type.setName("Test Type");
        type.setDescription("This is a test");

        type = accessPointTypeService.save(type);

        AccessPointVO point = new AccessPointVO();

        point.setAccessPointType(type);
        point.setCreatedBy("testuser4");
        point.setCreatedDate(LocalDateTime.now());
        point.setName("Test Type");

        ResponseEntity<?> response = accessPointRestController.save(point);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        String message = response.getBody().toString();
        System.out.println(message);
        Assertions.assertTrue(message.contains("access point url is missing"));
    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void save_emptyUrl() {
        AccessPointTypeVO type = new AccessPointTypeVO();

        type.setCode("test");
        type.setName("Test Type");
        type.setDescription("This is a test");

        type = accessPointTypeService.save(type);

        AccessPointVO point = new AccessPointVO();

        point.setAccessPointType(type);
        point.setCreatedBy("testuser4");
        point.setCreatedDate(LocalDateTime.now());
        point.setName("Test Type");
        point.setUrl(" ");

        ResponseEntity<?> response = accessPointRestController.save(point);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        String message = response.getBody().toString();
        System.out.println(message);
        Assertions.assertTrue(message.contains("access point url is missing"));
    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void save_null() {

        ResponseEntity<?> response = accessPointRestController.save(null);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        String message = response.getBody().toString();
        System.out.println(message);
        Assertions.assertTrue(message.contains("access point information is missing"));
    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void save_sameName() {
        AccessPointTypeVO type = new AccessPointTypeVO();

        type.setCode("test");
        type.setName("Test Type");
        type.setDescription("This is a test");

        type = accessPointTypeService.save(type);

        AccessPointVO point = new AccessPointVO();

        point.setAccessPointType(type);
        point.setCreatedBy("testuser4");
        point.setCreatedDate(LocalDateTime.now());
        point.setName("Test ");
        point.setUrl("/test");

        ResponseEntity<?> response = accessPointRestController.save(point);

        point = new AccessPointVO();

        point.setAccessPointType(type);
        point.setCreatedBy("testuser4");
        point.setCreatedDate(LocalDateTime.now());
        point.setName("Test");
        point.setUrl("/test2");

        response = accessPointRestController.save(point);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        point = (AccessPointVO) response.getBody();
        Assertions.assertNotNull(point);
        Assertions.assertNotNull(point.getId());
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

        AccessPointVO point = new AccessPointVO();

        point.setAccessPointType(type);
        point.setCreatedBy("testuser4");
        point.setCreatedDate(LocalDateTime.now());
        point.setName("Test ");
        point.setUrl("/test");
        point = accessPointService.save(point);

        ResponseEntity<?> response = accessPointRestController.findById(point.getId());

        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        AccessPointVO found = (AccessPointVO) response.getBody();

        Assertions.assertNotNull(found);
        Assertions.assertEquals(found.getId(), point.getId());
        Assertions.assertEquals(found.getUrl(), point.getUrl());
        Assertions.assertEquals(found.getName(), point.getName());
    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void findById_notExisting() {
        AccessPointTypeVO type = new AccessPointTypeVO();

        type.setCode("test10");
        type.setName("Test Type 10");
        type.setDescription("This is a test");
        type = accessPointTypeService.save(type);

        AccessPointVO point = new AccessPointVO();

        point.setAccessPointType(type);
        point.setCreatedBy("testuser4");
        point.setCreatedDate(LocalDateTime.now());
        point.setName("Test ");
        point.setUrl("/test");
        point = accessPointService.save(point);

        Long id = point.getId();

        ResponseEntity<?> response = accessPointRestController.findById(id + 10);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);

        String message = response.getBody().toString();
        Assertions.assertTrue(message.contains(String.format("Access point with id %d not found.", id + 10)));
    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void getAll() {
        dummyData(9);
        ResponseEntity<?> response = accessPointRestController.getAll();

        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Collection<AccessPointVO> types = (Collection<AccessPointVO>) response.getBody();
        Assertions.assertTrue(CollectionUtils.isNotEmpty(types));
        Assertions.assertEquals(types.size(), 9);

    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void getAll_empty() {
        ResponseEntity<?> response = accessPointRestController.getAll();

        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Collection<AccessPointVO> types = (Collection<AccessPointVO>) response.getBody();
        Assertions.assertTrue(CollectionUtils.isEmpty(types));

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
    public void search() {

    }

}