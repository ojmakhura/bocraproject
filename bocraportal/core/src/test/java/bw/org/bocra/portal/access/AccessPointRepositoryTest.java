package bw.org.bocra.portal.access;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import bw.org.bocra.portal.access.type.AccessPointType;

@DataJpaTest
public class AccessPointRepositoryTest {
    
    private final AccessPointRepository accessPointRepository;

    private AccessPoint accessPoint;

    public AccessPointRepositoryTest(AccessPointRepository accessPointRepository) {
        this.accessPointRepository = accessPointRepository;
    }

    @BeforeEach
    public void setUp() {

        AccessPointType type = new AccessPointType();
        type.setCode("Type1");
        type.setName("Type 1");
        type.setDescription("Type 1 Description");
        
        accessPoint = new AccessPoint();
        accessPoint.setAccessPointType(type);
        // accessPoint.setId(1L);
        accessPoint.setName("Access Point 1");
        //accessPoint.setDescription("Access Point 1 Description");
        accessPoint.setUrl("http://localhost:8080");
        accessPoint.setCreatedBy("test");
        accessPoint.setCreatedDate(LocalDateTime.now());
    }

    @Test
    @DisplayName("JUnit test for saving an access point")
    public void givenAccessPoint_whenSave_thenSaveAccessPoint() {
        accessPoint = accessPointRepository.save(accessPoint);

        assertNotNull(accessPoint);
    }
}
