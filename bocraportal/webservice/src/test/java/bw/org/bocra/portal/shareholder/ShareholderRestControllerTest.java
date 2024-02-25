// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by SpringWS.java.vsl in andromda-webservices. Do not modify by hand!.
//
package bw.org.bocra.portal.shareholder;

import bw.org.bocra.portal.BocraportalTestContainer;
import bw.org.bocra.portal.GenericRestTest;
import bw.org.bocra.portal.GenericTestData;
import bw.org.bocra.portal.licensee.shares.ShareholderType;

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
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class ShareholderRestControllerTest
        extends GenericRestTest<ShareholderVO, ShareholderRepository, ShareholderCriteria, ShareholderRestController> {

    protected Logger logger = LoggerFactory.getLogger(ShareholderRestControllerTest.class);

    private String path = "/shareholder";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ShareholderService shareholderService;

    @Autowired
    private ShareholderTestData shareholderTestData;

    @Autowired
    public ShareholderRestControllerTest(ShareholderRestController restController,
            GenericTestData<ShareholderVO, ShareholderRepository,  ShareholderCriteria, ShareholderRestController> testData) {
        super(restController, testData);
    }

    @BeforeEach
    public void clean() {
        shareholderTestData.clean();
    }

    public Collection<ShareholderVO> dummyData(int size) {

        return shareholderTestData.generateSequentialData(size);
    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void saveShareholderTest_missing() {

        ResponseEntity<?> response = restController.save(null);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        logger.info(response.getBody().toString());
        String message = response.getBody().toString();
        Assertions.assertTrue(message.contains("information is missing"));

    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void saveShareholderTest_missingId() {
        ShareholderVO shareholder = shareholderTestData.createUnsavedData();

        shareholder.setShareholderId(" ");
        ResponseEntity<?> response = restController.save(shareholder);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        logger.info(response.getBody().toString());

    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void saveShareholderTest_missingName() {
        ShareholderVO shareholder = shareholderTestData.createUnsavedData();

        shareholder.setName(null);
        ResponseEntity<?> response = restController.save(shareholder);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        logger.info(response.getBody().toString());

    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void save_noType() {
        
        ShareholderVO shareholder = ((ShareholderTestData)testData).createUnsavedShareholderNoType();

        ResponseEntity<?> response = restController.save(shareholder);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        String message = response.getBody().toString();
        Assertions.assertTrue(
            message.contains("type is missing")
            || message.contains("type or its id is missing")
        );
    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void saveShareholderTest_missingAddress() {
        ShareholderVO shareholder = shareholderTestData.createUnsavedData();

        shareholder.setAddress(null);
        ResponseEntity<?> response = restController.save(shareholder);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        logger.info(response.getBody().toString());

    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void save_nullCreatedDate() {

        ShareholderVO shareholder = testData.createUnsavedData();
        shareholder.setCreatedDate(null);

        ResponseEntity<?> response = restController.save(shareholder);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        logger.info(response.getBody().toString());
    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void save_nullCreatedBy() {
        ShareholderVO shareholder = shareholderTestData.createUnsavedData();
        shareholder.setCreatedBy(null);

        ResponseEntity<?> response = restController.save(shareholder);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        logger.info(response.getBody().toString());

    }

    // @WithMockUser(username = "testuser4", password = "testuser1")
    // @Test
    // public void saveShareholderTest_sameName() {
    //     ShareholderVO exists = (ShareholderVO) dummyData(1).iterator().next();
    //     // Ensure the data we just saved has been committed.
    //     this.shareholderService.getAll();
    //     ShareholderVO shareholder = new ShareholderVO();

    //     shareholder.setName(exists.getName());        

    //     Assertions.assertNotNull(response);
    //     Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
    //     logger.info(response.getBody().toString());
    // }

    @Override
    protected void basicCompareAssertions(ShareholderVO o1, ShareholderVO o2) {
        ShareholderVO shareholder1 = (ShareholderVO) o1;
        ShareholderVO shareholder2 = (ShareholderVO) o2;

        Assertions.assertEquals(shareholder1.getAddress(), shareholder2.getAddress());
        Assertions.assertEquals(shareholder1.getCreatedBy(), shareholder2.getCreatedBy());
        Assertions.assertEquals(shareholder1.getCreatedDate(), shareholder2.getCreatedDate());
        Assertions.assertEquals(shareholder1.getDocuments(), shareholder2.getDocuments());
        Assertions.assertEquals(shareholder1.getId(), shareholder2.getId());
        Assertions.assertEquals(shareholder1.getName(), shareholder2.getName());
        Assertions.assertEquals(shareholder1.getShareholderId(), shareholder2.getShareholderId());
        Assertions.assertEquals(shareholder1.getShares(), shareholder2.getShares());
        Assertions.assertEquals(shareholder1.getType(), shareholder2.getType());
        Assertions.assertEquals(shareholder1.getUpdatedBy(), shareholder2.getUpdatedBy());
        Assertions.assertEquals(shareholder1.getUpdatedDate(), shareholder2.getUpdatedDate());
    }

    @Override
    protected Class<ShareholderCriteria> getCriteriaClass() {
        
        return ShareholderCriteria.class;
    }

    @Override
    protected Class<ShareholderVO> getDataClass() {
        return ShareholderVO.class;
    }

    @Override
    protected void searchResultsAssertions(ResponseEntity<?> response) {
        Collection<ShareholderVO> shareholders = (Collection<ShareholderVO>) response.getBody();
        Assertions.assertEquals(shareholders.size(), 7);
    }
}

