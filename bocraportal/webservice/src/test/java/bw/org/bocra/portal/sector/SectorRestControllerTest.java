// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by SpringWS.java.vsl in andromda-webservices. Do not modify by hand!.
//
package bw.org.bocra.portal.sector;

import bw.org.bocra.portal.BocraportalTestContainer;
import bw.org.bocra.portal.licensee.sector.LicenseeSectorDao;
import bw.org.bocra.portal.licensee.sector.LicenseeSectorRepository;
import bw.org.bocra.portal.licensee.sector.LicenseeSectorService;
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
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;

@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class SectorRestControllerTest {

    @ClassRule
    public static PostgreSQLContainer postgreSQLContainer = BocraportalTestContainer.getInstance();

    private String path = "/sector";

    protected Logger logger = LoggerFactory.getLogger(SectorRestControllerTest.class);

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SectorRestController sectorRestController;

    @Autowired
    protected SectorService sectorService;

    @Autowired
    private SectorRepository sectorRepository;

    @Autowired
    private LicenseeSectorDao licenseeSectorDao;

    @Autowired
    private LicenseeSectorRepository licenseeSectorRepository;

    @Autowired
    private LicenseeSectorService licenseeSectorService;

    @BeforeEach
    public void clean() {

        sectorRepository.deleteAll();
    }

    public void dummyData(int size) {

        for (int i = 1; i <= size; i++) {

            SectorVO sector = new SectorVO();

            sector.setCreatedBy("testuser4");
            sector.setCreatedDate(LocalDateTime.now());
            sector.setName("Sector " + i);
            sector.setCode("sector" + i);
            sector.setThemeColour("color" + i);
            sector.setDescription("This is sector " + i);

           sectorRestController.save(sector);
        }
    }

    private void searchData() {
        SectorVO sector = new SectorVO();

        sector.setCreatedBy("testuser4");
        sector.setCreatedDate(LocalDateTime.now());
        sector.setCode("test");
        sector.setName("Test sector");
        sector.setThemeColour("color" + 1);
        sector.setDescription("This is a test");

        sectorRestController.save(sector);

        sector = new SectorVO();

        sector.setCreatedBy("testuser4");
        sector.setCreatedDate(LocalDateTime.now());
        sector.setCode("serious");
        sector.setName("Serious sector");
        sector.setThemeColour("color" + 1);
        sector.setDescription("This is a test");

        sectorRestController.save(sector);

        sector = new SectorVO();

        sector.setCreatedBy("testuser4");
        sector.setCreatedDate(LocalDateTime.now());
        sector.setCode("onelove");
        sector.setName("Top love");
        sector.setThemeColour("color" + 1);
        sector.setDescription("This is a test");

        sectorRestController.save(sector);

        sector = new SectorVO();

        sector.setCreatedBy("testuser4");
        sector.setCreatedDate(LocalDateTime.now());
        sector.setCode("test6");
        sector.setName("Test sector 6");
        sector.setThemeColour("color" + 1);
        sector.setDescription("This is a test");

        sectorRestController.save(sector);

        sector = new SectorVO();

        sector.setCreatedBy("testuser4");
        sector.setCreatedDate(LocalDateTime.now());
        sector.setCode("sixteen");
        sector.setName("One Six");
        sector.setThemeColour("color" + 1);
        sector.setDescription("This is a test");

        sectorRestController.save(sector);

        sector = new SectorVO();

        sector.setCreatedBy("testuser4");
        sector.setCreatedDate(LocalDateTime.now());
        sector.setCode("test16");
        sector.setName("Testing sixteen");
        sector.setThemeColour("color" + 1);
        sector.setDescription("This is a test");

        sectorRestController.save(sector);

        sector = new SectorVO();

        sector.setCreatedBy("testuser4");
        sector.setCreatedDate(LocalDateTime.now());
        sector.setCode("stop");
        sector.setName("Test sector Stop");
        sector.setThemeColour("color" + 1);
        sector.setDescription("This is a test");

        sectorRestController.save(sector);

    }

    /**
     * TODO: Implement add license
     */
    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void addLicensee() {

    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void findById() {
        SectorVO sector = new SectorVO();

        sector.setCreatedBy("testuser4");
        sector.setCreatedDate(LocalDateTime.now());
        sector.setCode("test");
        sector.setName("Test sector");
        sector.setThemeColour("color" + 1);
        sector.setDescription("This is a test");

        sector = sectorService.save(sector);

        ResponseEntity<?> response = sectorRestController.findById(sector.getId());

        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        SectorVO found = (SectorVO) response.getBody();

        Assertions.assertNotNull(found);
        Assertions.assertEquals(found.getId(), sector.getId());
        Assertions.assertEquals(found.getCode(), sector.getCode());
        Assertions.assertEquals(found.getName(), sector.getName());
        Assertions.assertEquals(found.getThemeColour(), sector.getThemeColour());
    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void findById_notExisting() {
        SectorVO sector = new SectorVO();

        sector.setCreatedBy("testuser4");
        sector.setCreatedDate(LocalDateTime.now());
        sector.setCode("test");
        sector.setName("Test sector");
        sector.setThemeColour("color" + 1);
        sector.setDescription("This is a test");

        sector = sectorService.save(sector);

        Long id = sector.getId();

        ResponseEntity<?> response = sectorRestController.findById(id + 10);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);

        String message = response.getBody().toString();
        System.out.println(message);
        Assertions.assertTrue(message.contains(String.format("Sector with id %d not found.", id + 10)));
    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    @Transactional
    public void getAll() {
        dummyData(9);
        ResponseEntity<?> response = sectorRestController.getAll();

        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Collection<SectorVO> sectors = (Collection<SectorVO>) response.getBody();
        Assertions.assertTrue(CollectionUtils.isNotEmpty(sectors));
        Assertions.assertEquals(sectors.size(), 9);
    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void getAll_empty() {
        ResponseEntity<?> response = sectorRestController.getAll();

        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Collection<SectorVO> types = (Collection<SectorVO>) response.getBody();
        Assertions.assertTrue(CollectionUtils.isEmpty(types));

    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    @Transactional
    public void getAllPaged() {
        dummyData(25);
        int pageNumber = 2;
        int pageSize = 4;
        ResponseEntity<?> response = sectorRestController.getAllPaged(pageNumber, pageSize);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Collection<SectorVO> types = (Collection<SectorVO>) response.getBody();
        Assertions.assertTrue(CollectionUtils.isNotEmpty(types));
        Assertions.assertEquals(types.size(), pageSize);
    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    @Transactional
    public void getAllPaged_lastPage() {
        dummyData(15);
        int pageNumber = 3;
        int pageSize = 4;

        ResponseEntity<?> response = sectorRestController.getAllPaged(pageNumber, pageSize);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Collection<SectorVO> types = (Collection<SectorVO>) response.getBody();
        Assertions.assertTrue(CollectionUtils.isNotEmpty(types));
        Assertions.assertEquals(types.size(), 3);
    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    @Transactional
    public void remove() {
        dummyData(15);
        ResponseEntity<?> response = sectorRestController.getAll();
        Collection<SectorVO> types = (Collection<SectorVO>) response.getBody();
        SectorVO t = types.iterator().next();

        response = sectorRestController.remove(t.getId());
        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assertions.assertTrue((boolean) response.getBody());

        response = sectorRestController.getAll();
        Collection<SectorVO> types2 = (Collection<SectorVO>) response.getBody();

        Assertions.assertEquals(types2.size(), types.size() - 1);
    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    @Transactional
    public void remove_non_existing() {
        dummyData(10);
        ResponseEntity<?> response = sectorRestController.getAll();
        Collection<SectorVO> types = (Collection<SectorVO>) response.getBody();

        response = sectorRestController.remove(30L);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
        Assertions.assertTrue(response.getBody().toString().contains("Failed to delete the sector"));

        response = sectorRestController.getAll();
        Collection<SectorVO> types2 = (Collection<SectorVO>) response.getBody();

        Assertions.assertEquals(types2.size(), types.size());

    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void save() {
        SectorVO sector = new SectorVO();

        sector.setCreatedBy("testuser4");
        sector.setCreatedDate(LocalDateTime.now());
        sector.setName("Sector One");
        sector.setCode("sector");
        sector.setThemeColour("#05ecd6");

        ResponseEntity<?> response = sectorRestController.save(sector);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        sector = (SectorVO) response.getBody();
        Assertions.assertNotNull(sector);
        Assertions.assertNotNull(sector.getId());
    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void save_nullSector() {
        
        ResponseEntity<?> response = sectorRestController.save(null);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        String message = response.getBody().toString();
        System.out.println(message);
        Assertions.assertNotNull(message);
        Assertions.assertTrue(message.contains("sector information is missing"));
    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void save_nullCode() {
        SectorVO sector = new SectorVO();

        sector.setCreatedBy("testuser4");
        sector.setCreatedDate(LocalDateTime.now());
        sector.setName("Sector One");
        // sector.setCode("sector");
        sector.setThemeColour("#05ecd6");
        
        ResponseEntity<?> response = sectorRestController.save(sector);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        String message = response.getBody().toString();
        System.out.println(message);
        Assertions.assertNotNull(message);
        Assertions.assertTrue(message.contains("sector code is missing"));
    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void save_nullName() {
        SectorVO sector = new SectorVO();

        sector.setCreatedBy("testuser4");
        sector.setCreatedDate(LocalDateTime.now());
        // sector.setName("Sector One");
        sector.setCode("sector");
        sector.setThemeColour("#05ecd6");
        
        ResponseEntity<?> response = sectorRestController.save(sector);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        String message = response.getBody().toString();
        System.out.println(message);
        Assertions.assertNotNull(message);
        Assertions.assertTrue(message.contains("sector name is missing"));
    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void save_nullThemeColour() {
        SectorVO sector = new SectorVO();

        sector.setCreatedBy("testuser4");
        sector.setCreatedDate(LocalDateTime.now());
        sector.setName("Sector One");
        sector.setCode("sector");
        // sector.setThemeColour("#05ecd6");
        
        ResponseEntity<?> response = sectorRestController.save(sector);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        String message = response.getBody().toString();
        System.out.println(message);
        Assertions.assertNotNull(message);
        Assertions.assertTrue(message.contains("sector colour is missing"));
    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void save_emptyCode() {
        SectorVO sector = new SectorVO();

        sector.setCreatedBy("testuser4");
        sector.setCreatedDate(LocalDateTime.now());
        sector.setName("Sector One");
        sector.setCode(" ");
        sector.setThemeColour("#05ecd6");
        
        ResponseEntity<?> response = sectorRestController.save(sector);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        String message = response.getBody().toString();
        System.out.println(message);
        Assertions.assertNotNull(message);
        Assertions.assertTrue(message.contains("sector code is missing"));
    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void save_emptyName() {
        SectorVO sector = new SectorVO();

        sector.setCreatedBy("testuser4");
        sector.setCreatedDate(LocalDateTime.now());
        sector.setName(" ");
        sector.setCode("sector");
        sector.setThemeColour("#05ecd6");
        
        ResponseEntity<?> response = sectorRestController.save(sector);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        String message = response.getBody().toString();
        System.out.println(message);
        Assertions.assertNotNull(message);
        Assertions.assertTrue(message.contains("sector name is missing"));
    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void save_emptyThemeColour() {
        SectorVO sector = new SectorVO();

        sector.setCreatedBy("testuser4");
        sector.setCreatedDate(LocalDateTime.now());
        sector.setName("Sector One");
        sector.setCode("sector");
        sector.setThemeColour(" ");
        
        ResponseEntity<?> response = sectorRestController.save(sector);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        String message = response.getBody().toString();
        System.out.println(message);
        Assertions.assertNotNull(message);
        Assertions.assertTrue(message.contains("sector colour is missing"));
    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void save_sameCode() {
        SectorVO sector = new SectorVO();

        sector.setCreatedBy("testuser4");
        sector.setCreatedDate(LocalDateTime.now());
        sector.setName("Sector One");
        sector.setCode("sector");
        sector.setThemeColour("#05ecd6");
        
        sector = sectorService.save(sector);

        System.out.println(sector);

        sector = new SectorVO();

        sector.setCreatedBy("testuser4");
        sector.setCreatedDate(LocalDateTime.now());
        sector.setName("Sector One Two");
        sector.setCode("sector");
        sector.setThemeColour("#35ecd6");

        ResponseEntity<?> response = sectorRestController.save(sector);

        System.out.println(response.getBody());

        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        String message = response.getBody().toString();
        System.out.println(message);
        Assertions.assertNotNull(message);
        Assertions.assertTrue(message.contains("sector with this code has been already created"));
    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void save_sameName() {
        SectorVO sector = new SectorVO();

        sector.setCreatedBy("testuser4");
        sector.setCreatedDate(LocalDateTime.now());
        sector.setName("Sector One");
        sector.setCode("sector");
        sector.setThemeColour("#05ecd6");
        
        sector = sectorService.save(sector);

        sector = new SectorVO();

        sector.setCreatedBy("testuser4");
        sector.setCreatedDate(LocalDateTime.now());
        sector.setName("Sector One");
        sector.setCode("sector1");
        sector.setThemeColour("#35ecd6");

        ResponseEntity<?> response = sectorRestController.save(sector);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        String message = response.getBody().toString();
        System.out.println(message);
        Assertions.assertNotNull(message);
        Assertions.assertTrue(message.contains("sector with this name has been already created"));
    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    @Transactional
    public void search_six() {

        this.searchData();
        ResponseEntity<?> response = sectorRestController.search("six");
        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Collection<SectorVO> sectors = (Collection<SectorVO>) response.getBody();
        Assertions.assertEquals(sectors.size(), 2);
    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    public void search_no_results() {
        
        this.searchData();

        ResponseEntity<?> response = sectorRestController.search("zero");

        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Collection<SectorVO> types = (Collection<SectorVO>) response.getBody();
        Assertions.assertEquals(types.size(), 0);
    }

    @WithMockUser(username = "testuser4", password = "testuser1")
    @Test
    @Transactional
    public void search_empty() {
        
        this.searchData();

        ResponseEntity<?> response = sectorRestController.search("");

        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Collection<SectorVO> types = (Collection<SectorVO>) response.getBody();
        Assertions.assertEquals(types.size(), 7);
    }

}