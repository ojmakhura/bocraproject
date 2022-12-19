// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by SpringWS.java.vsl in andromda-webservices. Do not modify by hand!.
//
package bw.org.bocra.portal.complaint;

import bw.org.bocra.portal.BocraportalTestContainer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.ClassRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
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
public class ComplaintRestControllerTest {

    @ClassRule
    public static PostgreSQLContainer postgreSQLContainer = BocraportalTestContainer.getInstance();

    private String path = "/complaint";

    protected Logger logger = LoggerFactory.getLogger(ComplaintRestControllerTest.class);

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ComplaintRestController complaintRestController;

    @Autowired
    protected ComplaintService complaintService;

    @BeforeEach
    public void clean() {
    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void addComplaintReply() {

    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void addDocument() {

    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void findByComplaintId() {

    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void findById() {

    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void findByIds() {

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
    public void removeComplaintReply() {

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