package bw.org.bocra.portal.complaint.type;

import java.time.LocalDateTime;
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

// import bw.org.bocra.portal.BocraportalTestContainer;
import bw.org.bocra.portal.GenericRestTest;
import bw.org.bocra.portal.GenericTestData;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class ComplaintTypeRestControllerTest
        extends GenericRestTest<ComplaintTypeVO, ComplaintTypeRepository, String,ComplaintTypeRestController> {

    protected Logger logger = LoggerFactory.getLogger(ComplaintTypeRestControllerTest.class);

    private String path = "/complaint/type";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ComplaintTypeService complaintTypeService;

    @Autowired
    private ComplaintTypeTestData complaintTypeTestData;

    @Autowired
    public ComplaintTypeRestControllerTest(ComplaintTypeRestController restController,
            GenericTestData<ComplaintTypeVO, ComplaintTypeRepository, String, ComplaintTypeRestController> testData) {
        super(restController, testData);
    }

    @BeforeEach
    public void clean() {
        complaintTypeTestData.clean();
    }

    public Collection<ComplaintTypeVO> dummyData(int size) {

        return complaintTypeTestData.generateSequentialData(size);
    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void saveComplaintTest_missing() {

        ResponseEntity<?> response = restController.save(null);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        logger.info(response.getBody().toString());
        String message = response.getBody().toString();
        Assertions.assertTrue(message.contains("information is missing"));

    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void saveComplaintTest_missingCode() {
        ComplaintTypeVO type = complaintTypeTestData.createUnsavedData();

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
    public void saveComplaintTest_missingTypeName() {
        ComplaintTypeVO type = complaintTypeTestData.createUnsavedData();

        type.setTypeName(null);
        ResponseEntity<?> response = restController.save(type);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        logger.info(response.getBody().toString());
        String message = response.getBody().toString();
        Assertions.assertTrue(message.contains("Type name is missing"));

    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void saveComplaintTest_missingDescription() {
        ComplaintTypeVO type = complaintTypeTestData.createUnsavedData();

        type.setDescription(null);

        ResponseEntity<?> response = restController.save(type);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        type = (ComplaintTypeVO) response.getBody();
        Assertions.assertNotNull(type);
        Assertions.assertNotNull(type.getId());
        logger.info(type.toString());

    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void saveComplaintTest_sameCode() {
        ComplaintTypeVO exists = (ComplaintTypeVO) dummyData(1).iterator().next();

        ComplaintTypeVO type = new ComplaintTypeVO();

        type.setCode(exists.getCode());
        type.setCreatedBy("Test User");
        type.setCreatedDate(LocalDateTime.now());
        type.setDescription("Complaint Type Description Test");
        type.setId(null);
        type.setTypeName("Test Name");
        type.setUpdatedBy("Test User");
        type.setUpdatedDate(LocalDateTime.now());
        ResponseEntity<?> response = restController.save(type);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        logger.info(response.getBody().toString());

    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void saveComplaintTest_sameTypeName() {
        ComplaintTypeVO exists = (ComplaintTypeVO) dummyData(1).iterator().next();
        // Ensure the data we just saved has been committed.
        this.complaintTypeService.getAll();
        ComplaintTypeVO type = new ComplaintTypeVO();

        type.setTypeName(exists.getTypeName());
        type.setCode("TestCode");
        type.setCreatedBy("Test User");
        type.setCreatedDate(LocalDateTime.now());
        type.setDescription("Complaint Type Description Test");
        type.setId(null);
        type.setUpdatedBy("Test User");
        type.setUpdatedDate(LocalDateTime.now());
        ResponseEntity<?> response = restController.save(type);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        logger.info(response.getBody().toString());
        String message = response.getBody().toString();
        Assertions.assertTrue(message.contains("this name"));
    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void saveComplaintTest_existingDescription() throws Exception {
        dummyData(9);
        ComplaintTypeVO type = new ComplaintTypeVO();

        type.setCode("TestCode");
        type.setCreatedBy("Test User");
        type.setCreatedDate(LocalDateTime.now());
        type.setDescription("Complaint Type Description Test");
        type.setId(null);
        type.setTypeName("Test Name");
        type.setUpdatedBy("Test User");
        type.setUpdatedDate(LocalDateTime.now());
        ResponseEntity<?> response = restController.save(type);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        type = (ComplaintTypeVO) response.getBody();
        Assertions.assertNotNull(type);
        Assertions.assertNotNull(type.getId());
        logger.info(type.toString());
    }

    @Override
    protected ResponseEntity<?> handlePagedSearch(Integer pageNumber, Integer pageSize, String criteria) throws Exception {
        return restController.getAllPaged(pageNumber, pageSize);
    }

    @Override
    protected void basicCompareAssertions(ComplaintTypeVO o1, ComplaintTypeVO o2) {
        ComplaintTypeVO type1 = (ComplaintTypeVO) o1;
        ComplaintTypeVO type2 = (ComplaintTypeVO) o2;

        Assertions.assertEquals(type1.getCode(), type2.getCode());
        Assertions.assertEquals(type1.getCreatedBy(), type2.getCreatedBy());
        Assertions.assertEquals(type1.getCreatedDate(), type2.getCreatedDate());
        Assertions.assertEquals(type1.getDescription(), type2.getDescription());
        Assertions.assertEquals(type1.getId(), type2.getId());
        Assertions.assertEquals(type1.getTypeName(), type2.getTypeName());
        Assertions.assertEquals(type1.getUpdatedBy(), type2.getUpdatedBy());
        Assertions.assertEquals(type1.getUpdatedDate(), type2.getUpdatedDate());

    }

    @Override
    protected Class<String> getCriteriaClass() {
        
        return String.class;
    }

    @Override
    protected Class<ComplaintTypeVO> getDataClass() {
        return ComplaintTypeVO.class;
    }

    @Override
    protected void searchResultsAssertions(ResponseEntity<?> response) {
        Collection<ComplaintTypeVO> types = (Collection<ComplaintTypeVO>) response.getBody();
        Assertions.assertEquals(types.size(), 7);
    }
}
