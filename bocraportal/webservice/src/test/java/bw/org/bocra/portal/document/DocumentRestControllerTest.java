// license-header java merge-document
//
// Attention: Generated code! Do not modify by hand!
// Generated by SpringWS.java.vsl in andromda-webservices. Do not modify by hand!.
//
package bw.org.bocra.portal.document;

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
import bw.org.bocra.portal.document.type.DocumentTypeRepository;
import bw.org.bocra.portal.document.type.DocumentTypeTestData;
import bw.org.bocra.portal.document.type.DocumentTypeVO;

@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@Transactional
public class DocumentRestControllerTest extends GenericRestTest<DocumentVO, DocumentRepository, DocumentCriteria, DocumentRestController> {

    @ClassRule
    public static PostgreSQLContainer postgreSQLContainer = BocraportalTestContainer.getInstance();

    private String path = "/access";

    protected Logger logger = LoggerFactory.getLogger(DocumentRestControllerTest.class);


    @Autowired
    private DocumentTypeTestData documentTypeTestData;

    @Autowired
    public DocumentRestControllerTest(DocumentRestController restController, DocumentTestData testData) {
        super(restController, testData);
    }

    @BeforeEach
    public void clean() {
        testData.clean();
        documentTypeTestData.clean();
    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void save_noType() {
        
        DocumentVO document = ((DocumentTestData)testData).createUnsavedDocumentNoType();

        ResponseEntity<?> response = restController.save(document);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        String message = response.getBody().toString();
        System.out.println(message);
        Assertions.assertTrue(
            message.contains("document type is missing")
            || message.contains("ocument type or its id is missing")
        );
    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void save_badType() {

        DocumentVO document = ((DocumentTestData)testData).createUnsavedDocumentUnsavedType();

        ResponseEntity<?> response = restController.save(document);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        String message = response.getBody().toString();
        System.out.println(message);
        Assertions.assertTrue(
            message.contains("document type is not valid")
            || message.contains("The document type is invalid")
            || message.contains("document type or its id is missing")
        );
    }

    // @WithMockUser(username = "testuser4", password = "testuser1")
    // @Test
    // public void save_nullCreatedDate() {

    //     DocumentVO document = testData.createUnsavedData();
    //     document.setCreatedDate(null);
    //     document.getFile();

    //     ResponseEntity<?> response = restController.save(document);
    //     Assertions.assertNotNull(response);
    //     Assertions.assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    //     String message = response.getBody().toString();
    //     System.out.println(message);
    //     Assertions.assertTrue(message.contains("created date value is missing"));
    // }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void save_nullCreatedBy() {
        DocumentVO document = testData.createUnsavedData();
        System.out.println(document);
        document.setCreatedBy(null);

        ResponseEntity<?> response = restController.save(document);
        Assertions.assertNotNull(response);
        System.out.println(response.getBody());
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        String message = response.getBody().toString();
        System.out.println(message);
        Assertions.assertTrue(message.contains("created-by value is missing"));
    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void save_nullDocumentName() {
        DocumentVO document = testData.createUnsavedData();

        document.setDocumentName(null);

        ResponseEntity<?> response = restController.save(document);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        String message = response.getBody().toString();
        System.out.println(message);
        Assertions.assertTrue(message.contains("document name is missing"));
    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void save_emptyDocumentName() {
        DocumentVO document = testData.createUnsavedData();
        document.setDocumentName(" ");

        ResponseEntity<?> response = restController.save(document);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        String message = response.getBody().toString();
        System.out.println(message);
        Assertions.assertTrue(message.contains("document name is missing"));
    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void save_nullFile() {
        DocumentVO document = testData.createUnsavedData();
        document.setFile(null);

        ResponseEntity<?> response = restController.save(document);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        String message = response.getBody().toString();
        System.out.println(message);
        Assertions.assertTrue(message.contains("access document url is missing"));
    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void save_nullExtension() {
        DocumentVO document = testData.createUnsavedData();
        document.setExtension(null);

        ResponseEntity<?> response = restController.save(document);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        String message = response.getBody().toString();
        System.out.println(message);
        Assertions.assertTrue(message.contains("access document url is missing"));
    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void save_emptyExtension() {
        DocumentVO document = testData.createUnsavedData();
        document.setExtension(" ");

        ResponseEntity<?> response = restController.save(document);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        String message = response.getBody().toString();
        System.out.println(message);
        Assertions.assertTrue(message.contains("document extension is missing"));
    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void save_sameDocumentName() {
        DocumentTypeVO type = documentTypeTestData.generateSequentialData(1).iterator().next();

        DocumentVO document = new DocumentVO();

        document.setDocumentType(type);
        document.setCreatedBy("testuser4");
        document.setCreatedDate(LocalDateTime.now());
        document.setDocumentId("1234");
        document.setDocumentName("Test test");
        document.setExtension(".pdf");
        document.setFile(null);

        ResponseEntity<?> response = restController.save(document);

        document = new DocumentVO();

        document.setDocumentType(type);
        document.setCreatedBy("testuser4");
        document.setCreatedDate(LocalDateTime.now());
        document.setDocumentId("1234");
        document.setDocumentName("Test test");
        document.setExtension(".pdf");
        document.setFile(null);

        response = restController.save(document);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        document = (DocumentVO) response.getBody();
        Assertions.assertNotNull(document);
        Assertions.assertNotNull(document.getId());
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
        Collection<DocumentVO> types = (Collection<DocumentVO>) response.getBody();
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
        Collection<DocumentVO> types = (Collection<DocumentVO>) response.getBody();
        Assertions.assertTrue(CollectionUtils.isNotEmpty(types));
        Assertions.assertEquals(types.size(), 3);
    }

    protected Collection<DocumentVO> searchData() {

        return testData.searchData();

    }

    // @WithMockUser(username = "testuser4", password = "testuser1")
    // @Test
    // public void pagedSearch() {
    //     testData.searchData();

    //     DocumentCriteria criteria = new DocumentCriteria();
    //     criteria.setDocumentId("type");

    //     ResponseEntity<?> response = restController.pagedSearch(2, 2, criteria);

    //     Assertions.assertNotNull(response);
    //     Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
    //     Collection<DocumentVO> documents = (Collection<DocumentVO>) response.getBody();
        
    //     Assertions.assertTrue(CollectionUtils.isNotEmpty(documents));
    //     Assertions.assertEquals(documents.size(), 1);
    // }

    // @WithMockUser(username = "testuser4", password = "testuser1")
    // @Test
    // public void remove() {
    //     testData.generateSequentialData(15);
    //     ResponseEntity<?> response = restController.getAll();
    //     Collection<DocumentVO> documents = (Collection<DocumentVO>) response.getBody();
    //     DocumentVO t = documents.iterator().next();

    //     response = restController.remove(t.getId());
    //     Assertions.assertNotNull(response);
    //     Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
    //     Assertions.assertTrue((boolean) response.getBody());

    //     response = restController.getAll();
    //     Collection<DocumentVO> documents2 = (Collection<DocumentVO>) response.getBody();

    //     Assertions.assertEquals(documents2.size(), documents.size() - 1);

    // }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void remove_non_existing() {
        testData.generateSequentialData(10);
        ResponseEntity<?> response = restController.getAll();
        Collection<DocumentVO> types = (Collection<DocumentVO>) response.getBody();

        response = restController.remove(300L);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
        Assertions.assertTrue(response.getBody().toString().contains("Could not delete access document"));

        response = restController.getAll();
        Collection<DocumentVO> types2 = (Collection<DocumentVO>) response.getBody();

        Assertions.assertEquals(types2.size(), types.size());

    }

    // @WithMockUser(username = "testuser4", password = "testuser1")
    // @Test
    // public void search_type() {
    //     this.searchData();

    //     DocumentCriteria criteria = new DocumentCriteria();
    //     criteria.setDocumentId("type");

    //     ResponseEntity<?> response = restController.search(criteria);

    //     Assertions.assertNotNull(response);
    //     Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
    //     Collection<DocumentTypeVO> types = (Collection<DocumentTypeVO>) response.getBody();
    //     Assertions.assertTrue(CollectionUtils.isNotEmpty(types));
    //     Assertions.assertEquals(types.size(), 5);
    // }

    // @WithMockUser(username = "testuser4", password = "testuser1")
    // @Test
    // public void search_no_results() {
        
    //     this.searchData();

    //     DocumentCriteria criteria = new DocumentCriteria();
    //     criteria.setDocumentId("types");

    //     ResponseEntity<?> response = restController.search(criteria);

    //     Assertions.assertNotNull(response);
    //     Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
    //     Collection<DocumentTypeVO> types = (Collection<DocumentTypeVO>) response.getBody();
    //     Assertions.assertEquals(types.size(), 0);
    // }

    @Override
    protected void basicCompareAssertions(DocumentVO o1, DocumentVO o2) {
        // TODO Auto-generated method stub
        DocumentVO document1 = (DocumentVO)o1;
        DocumentVO document2 = (DocumentVO)o2;
        
        Assertions.assertEquals(document1.getDocumentId(), document2.getDocumentId());
        Assertions.assertEquals(document1.getDocumentName(), document2.getDocumentName());
        Assertions.assertEquals(document1.getDocumentType(), document2.getDocumentType());
        Assertions.assertEquals(document1.getExtension(), document2.getExtension());
        Assertions.assertEquals(document1.getFile(), document2.getFile());
        Assertions.assertEquals(document1.getCreatedBy(), document2.getCreatedBy());
        Assertions.assertEquals(document1.getCreatedDate(), document2.getCreatedDate());
    }

    @Override
    protected Class<DocumentCriteria> getCriteriaClass() {
        return DocumentCriteria.class;
    }

    @Override
    protected Class<DocumentVO> getDataClass() {
        return DocumentVO.class;
    }

    @Override
    protected void searchResultsAssertions(ResponseEntity<?> response) {
        // TODO Auto-generated method stub
        Collection<DocumentVO> types = (Collection<DocumentVO>) response.getBody();
        Assertions.assertEquals(types.size(), 7);
        
    }
}
