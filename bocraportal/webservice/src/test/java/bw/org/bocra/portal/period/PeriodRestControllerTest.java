// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by SpringWS.java.vsl in andromda-webservices. Do not modify by hand!.
//
package bw.org.bocra.portal.period;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.apache.commons.lang3.StringUtils;
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

import com.fasterxml.jackson.databind.ObjectMapper;

import bw.org.bocra.portal.BocraportalTestContainer;
import bw.org.bocra.portal.GenericRestTest;
import bw.org.bocra.portal.GenericTestData;
import bw.org.bocra.portal.period.config.PeriodConfigRepository;
import bw.org.bocra.portal.period.config.PeriodConfigService;
import bw.org.bocra.portal.period.config.PeriodConfigVO;
import bw.org.bocra.portal.period.config.RepeatPeriod;

@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class PeriodRestControllerTest extends GenericRestTest<PeriodVO, PeriodRepository, PeriodCriteria, PeriodRestController> {

    private String path = "/period";

    protected Logger logger = LoggerFactory.getLogger(PeriodRestControllerTest.class);

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Autowired
    protected PeriodService periodService;

    @Autowired
    protected PeriodConfigService periodConfigService;

    @Autowired
    private PeriodConfigRepository periodConfigRepository;

    @Autowired
    public PeriodRestControllerTest(PeriodRestController restController,
            GenericTestData<PeriodVO, PeriodRepository, PeriodCriteria, PeriodRestController> testData) {
        super(restController, testData);
    }

    @BeforeEach
    public void clean() {
        testData.clean();
        periodConfigRepository.deleteAll();
    }

    private PeriodConfigVO createDefaultConfig(){
        PeriodConfigVO config = new PeriodConfigVO();

        config.setCreatedBy("testuser4");
        config.setCreatedDate(LocalDateTime.now());
        config.setFinalDay(15);
        config.setPeriodConfigName("Test Config");
        config.setRepeat(3);
        config.setRepeatPeriod(RepeatPeriod.MONTHS);
        config.setStartDay(1);
        config.setStartMonth(1);

        config = periodConfigService.save(config);

        return config;
    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void createNextPeriods() {

    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void save() {
        PeriodConfigVO config = createDefaultConfig();

        PeriodVO period = new PeriodVO();
        period.setCreatedBy("testuser4");
        period.setCreatedDate(LocalDateTime.now());
        period.setPeriodConfig(config);
        period.setPeriodEnd(LocalDate.now().plusMonths(config.getRepeat()));
        period.setPeriodName("Test Period");
        period.setPeriodStart(LocalDate.now());

        ResponseEntity<?> response = restController.save(period);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        period = (PeriodVO) response.getBody();
        Assertions.assertNotNull(period);
        Assertions.assertNotNull(period.getId());
    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void save_nullNextId() {
        PeriodConfigVO config = createDefaultConfig();

        PeriodVO period = new PeriodVO();
        period.setCreatedBy("testuser4");
        period.setCreatedDate(LocalDateTime.now());
        period.setPeriodConfig(config);
        period.setPeriodEnd(LocalDate.now().plusMonths(config.getRepeat()));
        period.setPeriodName("Test Period");
        period.setPeriodStart(LocalDate.now());

        period.setNext(new PeriodVO());

        ResponseEntity<?> response = restController.save(period);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        period = (PeriodVO) response.getBody();
        Assertions.assertNotNull(period);
        Assertions.assertNotNull(period.getId());
    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void save_nullPreviousId() {
        PeriodConfigVO config = createDefaultConfig();

        PeriodVO period = new PeriodVO();
        period.setCreatedBy("testuser4");
        period.setCreatedDate(LocalDateTime.now());
        period.setPeriodConfig(config);
        period.setPeriodEnd(LocalDate.now().plusMonths(config.getRepeat()));
        period.setPeriodName("Test Period");
        period.setPeriodStart(LocalDate.now());

        period.setPrevious(new PeriodVO());

        ResponseEntity<?> response = restController.save(period);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        period = (PeriodVO) response.getBody();
        Assertions.assertNotNull(period);
        Assertions.assertNotNull(period.getId());
    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void save_noConfig() {

        PeriodVO period = new PeriodVO();
        period.setCreatedBy("testuser4");
        period.setCreatedDate(LocalDateTime.now());
        period.setPeriodConfig(null);
        period.setPeriodEnd(LocalDate.now().plusMonths(3));
        period.setPeriodName("Test Period");
        period.setPeriodStart(LocalDate.now());

        ResponseEntity<?> response = restController.save(period);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        String message = response.getBody().toString();
        Assertions.assertTrue(StringUtils.isNotBlank(message));
        Assertions.assertTrue(message.contains("Period config is missing."));
    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void save_badConfig() {
        PeriodConfigVO config = new PeriodConfigVO();
        config.setId(21L);

        PeriodVO period = new PeriodVO();
        period.setCreatedBy("testuser4");
        period.setCreatedDate(LocalDateTime.now());
        period.setPeriodConfig(config);
        period.setPeriodEnd(LocalDate.now().plusMonths(config.getRepeat()));
        period.setPeriodName("Test Period");
        period.setPeriodStart(LocalDate.now());

        ResponseEntity<?> response = restController.save(period);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        
        String message = response.getBody().toString();
        Assertions.assertTrue(StringUtils.isNotBlank(message));
        Assertions.assertTrue(message.contains("Invalid period config"));
    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void save_badNext() {
        PeriodConfigVO config = createDefaultConfig();

        PeriodVO period = new PeriodVO();
        period.setCreatedBy("testuser4");
        period.setCreatedDate(LocalDateTime.now());
        period.setPeriodConfig(config);
        period.setPeriodEnd(LocalDate.now().plusMonths(config.getRepeat()));
        period.setPeriodName("Test Period");
        period.setPeriodStart(LocalDate.now());

        period.setNext(new PeriodVO());
        period.getNext().setId(33L);

        ResponseEntity<?> response = restController.save(period);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        
        String message = response.getBody().toString();
        Assertions.assertTrue(StringUtils.isNotBlank(message));
        Assertions.assertTrue(message.contains("Invalid next period"));
    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void save_period_unique() {
        PeriodConfigVO config = createDefaultConfig();

        PeriodVO period = new PeriodVO();
        period.setCreatedBy("testuser4");
        period.setCreatedDate(LocalDateTime.now());
        period.setPeriodConfig(config);
        period.setPeriodEnd(LocalDate.now().plusMonths(config.getRepeat()));
        period.setPeriodName("Test Period");
        period.setPeriodStart(LocalDate.now());
        
        restController.save(period);

        ResponseEntity<?> response = restController.save(period);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        String message = response.getBody().toString();
        Assertions.assertTrue(StringUtils.isNotBlank(message));
        Assertions.assertTrue(message.contains("period with this information has already been created"));
    }


    @Override
    protected void basicCompareAssertions(PeriodVO o1, PeriodVO o2) {
        // TODO Auto-generated method stub
        Assertions.assertEquals(o1.getId(), o2.getId());
        Assertions.assertEquals(o1.getPeriodName(), o2.getPeriodName());
        Assertions.assertEquals(o1.getPeriodConfig().getId(), o2.getPeriodConfig().getId());
        
    }

    @Override
    protected Class<PeriodCriteria> getCriteriaClass() {
        return PeriodCriteria.class;
    }

    @Override
    protected Class<PeriodVO> getDataClass() {
        return PeriodVO.class;
    }

    @Override
    protected void searchResultsAssertions(ResponseEntity<?> response) {
        // TODO Auto-generated method stub
        
    }
}