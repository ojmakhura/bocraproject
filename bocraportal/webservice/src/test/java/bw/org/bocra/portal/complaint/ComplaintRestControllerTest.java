package bw.org.bocra.portal.complaint;

import java.time.LocalDateTime;
import java.util.Collection;

import javax.transaction.Transactional;

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
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.PostgreSQLContainer;

import bw.org.bocra.portal.BocraportalTestContainer;
import bw.org.bocra.portal.GenericRestTest;
import bw.org.bocra.portal.complaint.type.ComplaintTypeRepository;
import bw.org.bocra.portal.complaint.type.ComplaintTypeTestData;
import bw.org.bocra.portal.complaint.type.ComplaintTypeVO;

@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@Transactional
public class ComplaintRestControllerTest extends GenericRestTest<ComplaintVO, ComplaintRepository, String, ComplaintRestController> {

    @ClassRule
    public static PostgreSQLContainer postgreSQLContainer = BocraportalTestContainer.getInstance();

    private String path = "/access";

    protected Logger logger = LoggerFactory.getLogger(ComplaintRestControllerTest.class);


    @Autowired
    private ComplaintTypeTestData complaintTypeTestData;

    @Autowired
    public ComplaintRestControllerTest(ComplaintRestController restController, ComplaintTestData testData) {
        super(restController, testData);
    }

    @BeforeEach
    public void clean() {
        testData.clean();
        complaintTypeTestData.clean();
    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void save_noType() {
        
        ComplaintVO complaint = ((ComplaintTestData)testData).createUnsavedComplaintNoType();

        ResponseEntity<?> response = restController.save(complaint);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        String message = response.getBody().toString();
        System.out.println(message);
        Assertions.assertTrue(
            message.contains("complaint type is missing")
            || message.contains("complaint type or its id is missing")
        );
    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void save_badType() {

        ComplaintVO complaint = ((ComplaintTestData)testData).createUnsavedComplaintUnsavedType();

        ResponseEntity<?> response = restController.save(complaint);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        String message = response.getBody().toString();
        System.out.println(message);
        Assertions.assertTrue(
            message.contains("access complaint type is not valid")
            || message.contains("The complaint type is invalid")
            || message.contains("complaint type or its id is missing")
        );
    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void save_nullCreatedDate() {

        ComplaintVO complaint = testData.createUnsavedData();
        complaint.setCreatedDate(null);

        ResponseEntity<?> response = restController.save(complaint);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        String message = response.getBody().toString();
        System.out.println(message);
        Assertions.assertTrue(message.contains("created date value is missing"));
    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void save_nullAssignedDate() {
        ComplaintVO complaint = testData.createUnsavedData();
        System.out.println(complaint);
        complaint.setAssignedDate(null);

        ResponseEntity<?> response = restController.save(complaint);
        Assertions.assertNotNull(response);
        System.out.println(response.getBody());
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        String message = response.getBody().toString();
        System.out.println(message);
        Assertions.assertTrue(message.contains("AssignedDate value is missing"));
    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void save_nullAssignedTo() {
        ComplaintVO complaint = testData.createUnsavedData();
        System.out.println(complaint);
        complaint.setAssignedTo(null);

        ResponseEntity<?> response = restController.save(complaint);
        Assertions.assertNotNull(response);
        System.out.println(response.getBody());
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        String message = response.getBody().toString();
        System.out.println(message);
        Assertions.assertTrue(message.contains("AssignedDate value is missing"));
    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void save_nullFirstName() {
        ComplaintVO complaint = testData.createUnsavedData();

        complaint.setFirstName(null);

        ResponseEntity<?> response = restController.save(complaint);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        String message = response.getBody().toString();
        System.out.println(message);
        Assertions.assertTrue(message.contains("First name is missing"));
    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void save_nullSurname() {
        ComplaintVO complaint = testData.createUnsavedData();

        complaint.setSurname(null);

        ResponseEntity<?> response = restController.save(complaint);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        String message = response.getBody().toString();
        System.out.println(message);
        Assertions.assertTrue(message.contains("Surname is missing"));
    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void save_emptyFirstName() {
        ComplaintVO complaint = testData.createUnsavedData();
        complaint.setFirstName(" ");

        ResponseEntity<?> response = restController.save(complaint);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        String message = response.getBody().toString();
        System.out.println(message);
        Assertions.assertTrue(message.contains("First name is missing"));
    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void save_emptySurname() {
        ComplaintVO complaint = testData.createUnsavedData();
        complaint.setSurname(" ");

        ResponseEntity<?> response = restController.save(complaint);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        String message = response.getBody().toString();
        System.out.println(message);
        Assertions.assertTrue(message.contains("Surname is missing"));
    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void save_nullLicensee() {
        ComplaintVO complaint = testData.createUnsavedData();
        complaint.setLicensee(null);

        ResponseEntity<?> response = restController.save(complaint);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        String message = response.getBody().toString();
        System.out.println(message);
        Assertions.assertTrue(message.contains("Licensee is missing"));
    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void save_nullStatus() {
        ComplaintVO complaint = testData.createUnsavedData();
        complaint.setStatus(null);

        ResponseEntity<?> response = restController.save(complaint);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        String message = response.getBody().toString();
        System.out.println(message);
        Assertions.assertTrue(message.contains("Licensee is missing"));
    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void save_sameComplaint() {
        ComplaintTypeVO type = complaintTypeTestData.generateSequentialData(1).iterator().next();

        ComplaintVO complaint = new ComplaintVO();

        complaint.setComplaintType(type);
        complaint.setCreatedDate(LocalDateTime.now());
        complaint.setAssignedDate(LocalDateTime.now());
        complaint.setAssignedTo("Bocra User");
        complaint.setComplaintId("TestComplaint");
        complaint.setComplaintReplies(null);
        complaint.setStatus(ComplaintStatus.NEW);
        complaint.setDocuments(null);
        complaint.setDetails("I have no internet");
        complaint.setCreatedDate(LocalDateTime.now());
        complaint.setFirstName("Random");
        complaint.setSurname("Person");
        complaint.setId(null);
        complaint.setLicensee(null);
        complaint.setEmail("complaint@person.com");

        ResponseEntity<?> response = restController.save(complaint);

        complaint = new ComplaintVO();

        complaint.setComplaintType(type);
        complaint.setCreatedDate(LocalDateTime.now());
        complaint.setAssignedDate(LocalDateTime.now());
        complaint.setAssignedTo("Bocra User");
        complaint.setComplaintId("TestComplaint");
        complaint.setComplaintReplies(null);
        complaint.setStatus(ComplaintStatus.NEW);
        complaint.setDocuments(null);
        complaint.setDetails("I have no internet");
        complaint.setCreatedDate(LocalDateTime.now());
        complaint.setFirstName("Random");
        complaint.setSurname("Person");
        complaint.setId(null);
        complaint.setLicensee(null);
        complaint.setEmail("complaint@person.com");

        response = restController.save(complaint);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        complaint = (ComplaintVO) response.getBody();
        Assertions.assertNotNull(complaint);
        Assertions.assertNotNull(complaint.getId());
    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void getAllPaged() {
        testData.generateSequentialData(25);
        int pageNumber = 2;
        int pageSize = 4;
        ResponseEntity<?> response = restController.getAllPaged(pageNumber, pageSize);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Collection<ComplaintVO> types = (Collection<ComplaintVO>) response.getBody();
        Assertions.assertTrue(CollectionUtils.isNotEmpty(types));
        Assertions.assertEquals(types.size(), pageSize);
    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void getAllPaged_lastPage() {
        testData.generateSequentialData(15);
        int pageNumber = 3;
        int pageSize = 4;

        ResponseEntity<?> response = restController.getAllPaged(pageNumber, pageSize);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Collection<ComplaintVO> types = (Collection<ComplaintVO>) response.getBody();
        Assertions.assertTrue(CollectionUtils.isNotEmpty(types));
        Assertions.assertEquals(types.size(), 3);
    }

    protected Collection<ComplaintVO> searchData() {

        return testData.searchData();

    }

    @Override
    protected void basicCompareAssertions(ComplaintVO o1, ComplaintVO o2) {
        // TODO Auto-generated method stub
        ComplaintVO complaint1 = (ComplaintVO)o1;
        ComplaintVO complaint2 = (ComplaintVO)o2;
        
        Assertions.assertEquals(complaint1.getCreatedDate(), complaint2.getCreatedDate());
        Assertions.assertEquals(complaint1.getComplaintId(), complaint2.getComplaintId());
        Assertions.assertEquals(complaint1.getComplaintReplies(), complaint2.getComplaintReplies());
        Assertions.assertEquals(complaint1.getCreatedDate(), complaint2.getCreatedDate());
        Assertions.assertEquals(complaint1.getAssignedTo(), complaint2.getAssignedTo());
        Assertions.assertEquals(complaint1.getAssignedDate(), complaint2.getAssignedDate());
        Assertions.assertEquals(complaint1.getDetails(), complaint2.getDetails());
        Assertions.assertEquals(complaint1.getDocuments(), complaint2.getDocuments());
        Assertions.assertEquals(complaint1.getFirstName(), complaint2.getFirstName());
        Assertions.assertEquals(complaint1.getSubject(), complaint2.getSubject());
        Assertions.assertEquals(complaint1.getSurname(), complaint2.getSurname());
        Assertions.assertEquals(complaint1.getStatus(), complaint2.getStatus());
        Assertions.assertEquals(complaint1.getEmail(), complaint2.getEmail());
        Assertions.assertEquals(complaint1.getId(), complaint2.getId());
        Assertions.assertEquals(complaint1.getLicensee(), complaint2.getLicensee());
        Assertions.assertEquals(complaint1.getComplaintType(), complaint2.getComplaintType());
    }

    @Override
    protected void searchResultsAssertions(ResponseEntity<?> response) {
        // TODO Auto-generated method stub
        Collection<ComplaintVO> types = (Collection<ComplaintVO>) response.getBody();
        Assertions.assertEquals(types.size(), 7);
        
    }

    @Override
    protected Class<String> getCriteriaClass() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected Class<ComplaintVO> getDataClass() {
        // TODO Auto-generated method stub
        return ComplaintVO.class;
    }
}
