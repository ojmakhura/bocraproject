package bw.org.bocra.portal.document.type;

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
public class DocumentTypeRestControllerTest
        extends GenericRestTest<DocumentTypeVO, DocumentTypeRepository, String, DocumentTypeRestController> {

    protected Logger logger = LoggerFactory.getLogger(DocumentTypeRestControllerTest.class);

    private String path = "/document/type";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DocumentTypeService documentTypeService;

    @Autowired
    private DocumentTypeTestData documentTypeTestData;

    @Autowired
    public DocumentTypeRestControllerTest(DocumentTypeRestController restController,
            GenericTestData<DocumentTypeVO, DocumentTypeRepository, String, DocumentTypeRestController> testData) {
        super(restController, testData);
    }

    @BeforeEach
    public void clean() {
        documentTypeTestData.clean();
    }

    public Collection<DocumentTypeVO> dummyData(int size) {

        return documentTypeTestData.generateSequentialData(size);
    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void saveDocumentTest_missing() {

        ResponseEntity<?> response = restController.save(null);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        logger.info(response.getBody().toString());
        String message = response.getBody().toString();
        Assertions.assertTrue(message.contains("information is missing"));

    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void saveDocumentTest_missingCode() {
        DocumentTypeVO type = documentTypeTestData.createUnsavedData();

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
    public void saveDocumentTest_missingName() {
        DocumentTypeVO type = documentTypeTestData.createUnsavedData();

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
    public void saveDocumentTest_missingDescription() {
        DocumentTypeVO type = documentTypeTestData.createUnsavedData();

        type.setDescription(null);

        ResponseEntity<?> response = restController.save(type);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        logger.info(response.getBody().toString());
        String message = response.getBody().toString();
        Assertions.assertTrue(message.contains("description is missing"));

    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void saveDocumentTest_sameCode() {
        DocumentTypeVO exists = (DocumentTypeVO) dummyData(1).iterator().next();

        DocumentTypeVO type = new DocumentTypeVO();

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
    public void saveDocumentTest_sameName() {
        DocumentTypeVO exists = (DocumentTypeVO) dummyData(1).iterator().next();
        DocumentTypeVO type = new DocumentTypeVO();

        type.setCode("TEST01)");
        type.setName(exists.getName());
        type.setDescription("This is a test");
        ResponseEntity<?> response = restController.save(type);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        logger.info(response.getBody().toString());
    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void saveDocumentTest_existingDescription() throws Exception {
        dummyData(9);
        DocumentTypeVO type = new DocumentTypeVO();

        type.setCode("test10");
        type.setName("Test Type 10");
        type.setDescription("This is a test");
        type.setCreatedBy("test test" + 1);
            type.setCreatedDate(LocalDateTime.now() );
        ResponseEntity<?> response = restController.save(type);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        type = (DocumentTypeVO) response.getBody();
        Assertions.assertNotNull(type);
        Assertions.assertNotNull(type.getId());
        logger.info(type.toString());
    }

    // @WithMockUser(username = "testuser4", password = "testuser1")
    // @Test
    // public void search_six() {

    //     DocumentTypeTestData.searchData();

    //     ResponseEntity<?> response = restController.search("six");

    //     Assertions.assertNotNull(response);
    //     Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
    //     Collection<DocumentTypeVO> types = (Collection<DocumentTypeVO>) response.getBody();
    //     Assertions.assertEquals(types.size(), 2);
    // }

    @Override
    protected ResponseEntity<?> handlePagedSearch(Integer pageNumber, Integer pageSize, String criteria) throws Exception {
        return restController.getAllPaged(pageNumber, pageSize);
    }

    @Override
    protected void basicCompareAssertions(DocumentTypeVO o1, DocumentTypeVO o2) {
        DocumentTypeVO type1 = (DocumentTypeVO) o1;
        DocumentTypeVO type2 = (DocumentTypeVO) o2;

        Assertions.assertEquals(type1.getId(), type2.getId());
        Assertions.assertEquals(type1.getCode(), type2.getCode());
        Assertions.assertEquals(type1.getName(), type2.getName());
    }

    @Override
    protected Class<String> getCriteriaClass() {
        
        return String.class;
    }

    @Override
    protected Class<DocumentTypeVO> getDataClass() {
        return DocumentTypeVO.class;
    }

    @Override
    protected void searchResultsAssertions(ResponseEntity<?> response) {
        Collection<DocumentTypeVO> types = (Collection<DocumentTypeVO>) response.getBody();
        Assertions.assertEquals(types.size(), 7);
    }
}
