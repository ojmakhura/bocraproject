// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by SpringService.vsl in andromda-spring-cartridge on $springUtils.date. Do not modify by hand!.
//
package bw.org.bocra.portal.sector.form;

import bw.org.bocra.portal.form.FormDao;
import bw.org.bocra.portal.form.FormRepository;
import bw.org.bocra.portal.sector.SectorDao;
import bw.org.bocra.portal.sector.SectorRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class SectorFormServiceTest {

    protected Logger logger = LoggerFactory.getLogger(SectorFormServiceTest.class);
    
    @InjectMocks
    private SectorFormServiceImpl sectorFormService;

    @Mock
    private SectorFormDao sectorFormDao;

    @Mock
    private SectorFormRepository sectorFormRepository;
    @Mock
    private SectorDao sectorDao;

    @Mock
    private SectorRepository sectorRepository;
    @Mock
    private FormDao formDao;

    @Mock
    private FormRepository formRepository;

    @Test
    public void checkInjects() {

        Assertions.assertNotNull(sectorFormDao);
        Assertions.assertNotNull(sectorFormRepository);
        Assertions.assertNotNull(sectorDao);
        Assertions.assertNotNull(sectorRepository);
        Assertions.assertNotNull(formDao);
        Assertions.assertNotNull(formRepository);
        Assertions.assertNotNull(sectorFormService);

    }
    

    @Test
    public void findById_success() {
        
    }

    @Test
    public void findById_fail() {
        
    }


    @Test
    public void create_success() {
        
    }

    @Test
    public void create_fail() {
        
    }


    @Test
    public void remove_success() {
        
    }

    @Test
    public void remove_fail() {
        
    }


    @Test
    public void getAll_success() {
        
    }

    @Test
    public void getAll_fail() {
        
    }


    @Test
    public void findBySector_success() {
        
    }

    @Test
    public void findBySector_fail() {
        
    }


    @Test
    public void findByForm_success() {
        
    }

    @Test
    public void findByForm_fail() {
        
    }


    @Test
    public void updateSector_success() {
        
    }

    @Test
    public void updateSector_fail() {
        
    }


    @Test
    public void updateForm_success() {
        
    }

    @Test
    public void updateForm_fail() {
        
    }

}