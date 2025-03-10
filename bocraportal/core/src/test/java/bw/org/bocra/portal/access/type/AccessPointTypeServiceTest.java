// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by SpringService.vsl in andromda-spring-cartridge on $springUtils.date. Do not modify by hand!.
//
package bw.org.bocra.portal.access.type;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.util.ResourceUtils;

import bw.org.bocra.portal.BocraportalCoreApplication;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.description;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

@SpringBootTest(classes = BocraportalCoreApplication.class)
@TestPropertySource("/application.properties")
public class AccessPointTypeServiceTest {

    protected Logger logger = LoggerFactory.getLogger(AccessPointTypeServiceTest.class);

    @Autowired
    ApplicationContext context;

    @Autowired
    private JdbcTemplate jdbc;

    @Autowired
    private AccessPointTypeService accessPointTypeService;

    @Autowired
    private AccessPointTypeDao accessPointTypeDao;

    @Autowired
    private AccessPointTypeRepository accessPointTypeRepository;

    public void loadCsvFile() throws Exception {
        File file = ResourceUtils.getFile("classpath:access_point_type.csv");
        try (
                
                InputStream is = file.toURI().toURL().openStream();
                BufferedReader fileReader = new BufferedReader(new InputStreamReader(is));
                CSVParser csvParser = new CSVParser(
                        fileReader,
                        CSVFormat.DEFAULT.builder()
                                .setHeader("code" , "description","name")
                                .setSkipHeaderRecord(true)
                                .build());) {

            Collection<AccessPointType> types = new ArrayList<>();
            csvParser.forEach(csvRecord -> {
                AccessPointType type = new AccessPointType();
                type.setCode(csvRecord.get("code"));
                type.setName(csvRecord.get("name"));
                type.setDescription(csvRecord.get("description"));
                types.add(type);
            });

            accessPointTypeRepository.saveAll(types);
        }
    }

    @BeforeEach
    public void init() {

        AccessPointType type = new AccessPointType();
        type.setCode("test");
        type.setName("Test Type");
        type.setDescription("This is a test");

        accessPointTypeRepository.save(type);

    }

    @AfterEach
    public void clean() {
        jdbc.execute("DELETE FROM access_point_type");
    }

    @Test
    @DisplayName("Test Find By Id Success")
    public void testFindByIdSuccess() {

        Long id = accessPointTypeRepository.findAll().get(0).getId();

        AccessPointTypeVO type = accessPointTypeService.findById(id);

        assertNotNull(type);
        assertNotNull(type.getId());
        assertEquals("test", type.getCode());
        assertEquals("Test Type", type.getName());
        assertEquals("This is a test", type.getDescription());

    }

    @Test
    @DisplayName("Test Find By Id Fail")
    public void testFindByIdFail() {

        Exception exception = assertThrows(AccessPointTypeServiceException.class, () -> {
            accessPointTypeService.findById(200l);
        }, "AccessPointTypeServiceException should be thrown");

        assertTrue(exception.getMessage().contains("java.util.NoSuchElementException: No value present"));

    }

    @Test
    @DisplayName("Test Find By Id Fail")
    public void testFindByNullIdFail() {

        Exception exception = assertThrows(AccessPointTypeServiceException.class, () -> {
            accessPointTypeService.findById(null);
        }, "AccessPointTypeServiceException should be thrown");

        assertTrue(exception.getMessage().contains("'id' can not be null"));

    }

    @Test
    @DisplayName("Test Save Success")
    public void testSaveSuccess() {

        AccessPointTypeVO type = new AccessPointTypeVO();

        type.setCode("test2");
        type.setName("Test Type 2");
        type.setDescription("This is a test 2");

        type = accessPointTypeService.save(type);

        assertNotNull(type);
        assertNotNull(type.getId());
        assertEquals("test2", type.getCode(), "Code should be test2");
        assertEquals("Test Type 2", type.getName(), "Name should be Test Type 2");

    }

    @Test
    @DisplayName("Test Update Pass")
    public void testUpdatePass() {
        Long id = accessPointTypeRepository.findAll().get(0).getId();
        AccessPointTypeVO type = accessPointTypeService.findById(id);

        assertNotNull(type);
        assertNotNull(type.getId());
        assertEquals("test", type.getCode());
        assertEquals("Test Type", type.getName());
        assertEquals("This is a test", type.getDescription());

        type.setName("Test Type Updated");
        type.setDescription("This is a test updated");

        type = accessPointTypeService.save(type);

        assertNotNull(type);
        assertNotNull(type.getId());
        assertEquals(id, type.getId());
        assertEquals("test", type.getCode());
        assertEquals("Test Type Updated", type.getName());
        assertEquals("This is a test updated", type.getDescription());

    }

    @Test
    @DisplayName("Test Update Wrong Id Fail")
    public void testUpdateWrongIdFail() {

        AccessPointTypeVO type = new AccessPointTypeVO();

        type.setId(2l);
        type.setCode("test2");
        type.setName("Test Type 2");
        type.setDescription("This is a test 2");

        assertThrows(AccessPointTypeServiceException.class, () -> {
            accessPointTypeService.save(type);
        }, "AccessPointTypeServiceException should be thrown");

    }

    @Test
    @DisplayName("Test Save Null")
    public void testSaveNullFail() {

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            accessPointTypeService.save(null);
        }, "AccessPointTypeServiceException should be thrown");

        assertTrue(exception.getMessage().contains("'accessPointType' can not be null"));
    }

    @Test
    @DisplayName("Test Save Same Name Fail")
    public void testSaveSameNameFail() {

        Exception exception = assertThrows(AccessPointTypeServiceException.class, () -> {

            AccessPointTypeVO type2 = new AccessPointTypeVO();

            type2.setCode("test");
            type2.setName("Test Type 2");
            type2.setDescription("This is a test 2");

            type2 = accessPointTypeService.save(type2);
        }, "AccessPointTypeServiceException should be thrown");

        assertTrue(exception instanceof AccessPointTypeServiceException);
        assertTrue(exception.getMessage()
                .contains("org.springframework.dao.DataIntegrityViolationException: could not execute statement"));
    }

    @Test
    @DisplayName("Test Save Same Code Fail")
    public void testSaveSameCodeFail() {

        Exception exception = assertThrows(AccessPointTypeServiceException.class, () -> {

            AccessPointTypeVO type2 = new AccessPointTypeVO();

            type2.setCode("test");
            type2.setName("Test Type 2");
            type2.setDescription("This is a test 2");

            type2 = accessPointTypeService.save(type2);
        }, "AccessPointTypeServiceException should be thrown");

        assertTrue(exception instanceof AccessPointTypeServiceException);
        assertTrue(exception.getMessage()
                .contains("org.springframework.dao.DataIntegrityViolationException: could not execute statement"));
    }

    @Test
    @DisplayName("Test Save Code Null Fail")
    public void testSaveCodeNullFail() {

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {

            AccessPointTypeVO type2 = new AccessPointTypeVO();

            type2.setName("Test Type 2");
            type2.setDescription("This is a test 2");

            type2 = accessPointTypeService.save(type2);
        }, "AccessPointTypeServiceException should be thrown");

        assertTrue(exception.getMessage().contains("'accessPointType.code' can not be null or empty"));

    }

    @Test
    @DisplayName("Test Save Name Null Fail")
    public void testSaveNameNullFail() {

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {

            AccessPointTypeVO type2 = new AccessPointTypeVO();

            type2.setCode("test");
            type2.setDescription("This is a test 2");

            type2 = accessPointTypeService.save(type2);
        }, "AccessPointTypeServiceException should be thrown");

        assertTrue(exception.getMessage().contains("'accessPointType.name' can not be null or empty"));

    }

    @Test
    @DisplayName("Test Save Access Point Type Null Fail")
    public void testSaveAccessPointTypeNullFail() {

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {

            accessPointTypeService.save(null);
        }, "AccessPointTypeServiceException should be thrown");

        assertTrue(exception instanceof IllegalArgumentException);
        assertTrue(exception.getMessage().contains("'accessPointType' can not be null"));
    }

    @Test
    @DisplayName("Test Save Access Point Type Description Null Pass")
    public void testSaveAccessPointTypeDescriptionNullPass() {

        AccessPointTypeVO type = new AccessPointTypeVO();

        type.setCode("test2");
        type.setName("Test Type 2");

        type = accessPointTypeService.save(type);

        assertNotNull(type);
        assertNotNull(type.getId());
        assertEquals("test2", type.getCode(), "Code should be test2");
        assertNull(type.getDescription());
    }

    @Test
    @DisplayName("Test Remove Success")
    public void testRemoveSuccess() {

        AccessPointTypeVO type = new AccessPointTypeVO();

        type.setCode("test2");
        type.setName("Test Type 2");
        type.setDescription("This is a test 2");

        type = accessPointTypeService.save(type);

        final Long id = type.getId();

        assertNotNull(type.getId());

        boolean removed = accessPointTypeService.remove(type.getId());

        assertTrue(removed);

        assertThrows(AccessPointTypeServiceException.class, () -> {
            accessPointTypeService.findById(id);
        }, "AccessPointTypeServiceException should be thrown");
    }

    @Test
    @DisplayName("Test Remove Wrong Id Fail")
    public void testRemoveWrongIdFail() {

        AccessPointTypeVO type = new AccessPointTypeVO();

        type.setCode("test2");
        type.setName("Test Type 2");
        type.setDescription("This is a test 2");

        type = accessPointTypeService.save(type);

        assertNotNull(type.getId());

        final Long id = type.getId() + 1;

        Exception exception = assertThrows(AccessPointTypeServiceException.class, () -> {
            accessPointTypeService.remove(id);
        }, "AccessPointTypeServiceException should be thrown");

        exception.printStackTrace();

        assertTrue(exception.getMessage().contains(
                "java.util.NoSuchElementException: No value present"));

        AccessPointTypeVO type2 = accessPointTypeService.findById(type.getId());

        assertNotNull(type2);
        assertNotNull(type2.getId());
        assertEquals("test2", type2.getCode());
        assertEquals("Test Type 2", type2.getName());
        assertEquals("This is a test 2", type2.getDescription());
    }

    @Test
    @DisplayName("Test Remove Null Id Fail")
    public void testRemoveNullIdFail() {

        Exception exception = assertThrows(AccessPointTypeServiceException.class, () -> {
            accessPointTypeService.remove(null);
        }, "AccessPointTypeServiceException should be thrown");

        assertTrue(exception.getMessage().contains("'id' can not be null"));
    }

    @Test
    @DisplayName("Test Get All Success")
    public void testGetAllSuccess() {

        int count = accessPointTypeService.getAll().size();

        AccessPointTypeVO type = new AccessPointTypeVO();

        type.setCode("test2");
        type.setName("Test Type 2");
        type.setDescription("This is a test 2");

        type = accessPointTypeService.save(type);

        assertEquals(count + 1, accessPointTypeService.getAll().size());
    }

    @Test
    @DisplayName("Test Search Success")
    @CsvFileSource(resources = "/access_point_type.csv", numLinesToSkip = 1)
    public void testSearchSuccess() throws Exception {

        this.loadCsvFile();
        Collection<AccessPointTypeVO> types = accessPointTypeService.search(null);
        assertEquals(5, types.size());

        types = accessPointTypeService.search("test");
        assertEquals(1, types.size());

        types = accessPointTypeService.search("nu");
        assertEquals(1, types.size());

    }

    @Test
    @DisplayName("Test Search Fail")
    public void testSearchFail() throws Exception {
        this.loadCsvFile();

        Collection<AccessPointTypeVO> types = accessPointTypeService.search("test21");
        assertEquals(0, types.size());
    }

    @Test
    @DisplayName("Test Paged Get All Success")
    public void testPagedGetAllSuccess() {

    }

    @Test
    @DisplayName("Test Paged Get All Fail")
    public void testPagedGetAllFail() {

    }

    @Test
    @DisplayName("Test Paged Search Success")
    public void testPagedSearchSuccess() {

    }

    @Test
    @DisplayName("Test Paged Search Fail")
    public void testPagedSearchFail() {

    }

    @Test
    @DisplayName("Test Paged Search Criteria Null")
    public void testPagedSearchCriteriaNull() {

    }

}