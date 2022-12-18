// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by SpringWS.java.vsl in andromda-webservices. Do not modify by hand!.
//
package bw.org.bocra.portal.licence;

import bw.org.bocra.portal.BocraportalPostgresqlContainer;
import bw.org.bocra.portal.document.DocumentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.ClassRule;
import org.junit.jupiter.api.Assertions;
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

// @SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class LicenceRestControllerTest {

    @ClassRule
    public static PostgreSQLContainer postgreSQLContainer = BocraportalPostgresqlContainer.getInstance();

    protected Logger logger = LoggerFactory.getLogger(LicenceRestControllerTest.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private LicenceRestController licenceRestController;

    @Autowired
    protected LicenceService licenceService;

    @Autowired
    protected DocumentService documentService;

    @BeforeEach
    public void clean() {
    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void addDocument() {

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