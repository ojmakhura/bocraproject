package bw.org.bocra.portal.licensee;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("test")
public class LicenseeTestData {
    private final LicenseeRestController licenseeFormRestController;

    public LicenseeTestData(LicenseeRestController licenseeFormRestController) {
        this.licenseeFormRestController = licenseeFormRestController;
    }
    
    public LicenseeRestController getLicenseeRestController() {
        return licenseeFormRestController;
    }

    public LicenseeVO createUnsavedLicensee() {
        LicenseeVO licensee = new LicenseeVO();

        licensee.setAddress("test address");
        licensee.setLicenseeName("Test Licensee");
        licensee.setAlias("Test");
        licensee.setStatus(LicenseeStatus.ACTIVE);
        licensee.setUin(UUID.randomUUID().toString());
        licensee.setCreatedBy("testuser4");
        licensee.setCreatedDate(LocalDateTime.now());

        return licensee;
    }

    public LicenseeVO createLicensee() {
        
        return (LicenseeVO) licenseeFormRestController.save(createUnsavedLicensee()).getBody();
    }

    public Collection<LicenseeVO> generateSequentialData(int size) {
        
        return generateUnsavedSequentialData(size)
            .stream()
            .map(licensee -> (LicenseeVO)licenseeFormRestController.save(licensee).getBody())
            .collect(Collectors.toList());
    }

    public Collection<LicenseeVO> generateUnsavedSequentialData(int size) {
        Collection<LicenseeVO> licensees = new ArrayList<>();
        for (int i = 1; i <= size; i++) {

            LicenseeVO licensee = new LicenseeVO();

            licensee.setAddress("test address " + i);
            licensee.setLicenseeName("Test Licensee " + i);
            licensee.setAlias("Test" + i);
            licensee.setStatus(LicenseeStatus.ACTIVE);
            licensee.setUin(UUID.randomUUID().toString());
            licensee.setCreatedBy("testuser4");
            licensee.setCreatedDate(LocalDateTime.now());

            licensees.add(licensee);

        }
        return licensees;
    }
    
    public Collection<LicenseeVO> generateSearchData() {
        Collection<LicenseeVO> licensees = new ArrayList<>();

        LicenseeVO licensee = new LicenseeVO();

        licensee.setAddress("test address");
        licensee.setLicenseeName("Test Licensee");
        licensee.setAlias("Test");
        licensee.setStatus(LicenseeStatus.ACTIVE);
        licensee.setUin(UUID.randomUUID().toString());
        licensee.setCreatedBy("testuser4");
        licensee.setCreatedDate(LocalDateTime.now());

        licensees.add((LicenseeVO) licenseeFormRestController.save(licensee).getBody());

        licensee = new LicenseeVO();

        licensee.setAddress("serious");
        licensee.setLicenseeName("Serious Type");
        licensee.setAlias("serious");
        licensee.setStatus(LicenseeStatus.ACTIVE);
        licensee.setUin(UUID.randomUUID().toString());
        licensee.setCreatedBy("testuser4");
        licensee.setCreatedDate(LocalDateTime.now());

        licensees.add((LicenseeVO) licenseeFormRestController.save(licensee).getBody());

        licensee = new LicenseeVO();

        licensee.setAddress("onelove");
        licensee.setLicenseeName("Top love");
        licensee.setAlias("onelove");
        licensee.setStatus(LicenseeStatus.SUSPENDED);
        licensee.setUin(UUID.randomUUID().toString());
        licensee.setCreatedBy("testuser4");
        licensee.setCreatedDate(LocalDateTime.now());

        licensees.add((LicenseeVO) licenseeFormRestController.save(licensee).getBody());

        licensee = new LicenseeVO();

        licensee.setAddress("test6");
        licensee.setLicenseeName("Test Type 6");
        licensee.setAlias("test6");
        licensee.setStatus(LicenseeStatus.ACTIVE);
        licensee.setUin(UUID.randomUUID().toString());
        licensee.setCreatedBy("testuser4");
        licensee.setCreatedDate(LocalDateTime.now());

        licensees.add((LicenseeVO) licenseeFormRestController.save(licensee).getBody());

        licensee = new LicenseeVO();

        licensee.setAddress("sixteen");
        licensee.setLicenseeName("One Six");
        licensee.setAlias("sixteen");
        licensee.setStatus(LicenseeStatus.SUSPENDED);
        licensee.setUin(UUID.randomUUID().toString());
        licensee.setCreatedBy("testuser4");
        licensee.setCreatedDate(LocalDateTime.now());

        licensees.add((LicenseeVO) licenseeFormRestController.save(licensee).getBody());

        licensee = new LicenseeVO();

        licensee.setAddress("test16");
        licensee.setLicenseeName("Testing sixteen");
        licensee.setAlias("test16");
        licensee.setStatus(LicenseeStatus.ACTIVE);
        licensee.setUin(UUID.randomUUID().toString());
        licensee.setCreatedBy("testuser4");
        licensee.setCreatedDate(LocalDateTime.now());

        licensees.add((LicenseeVO) licenseeFormRestController.save(licensee).getBody());

        licensee = new LicenseeVO();

        licensee.setAddress("stop");
        licensee.setLicenseeName("Test Type Stop");
        licensee.setAlias("stop");
        licensee.setStatus(LicenseeStatus.SUSPENDED);
        licensee.setUin(UUID.randomUUID().toString());
        licensee.setCreatedBy("testuser4");
        licensee.setCreatedDate(LocalDateTime.now());

        licensees.add((LicenseeVO) licenseeFormRestController.save(licensee).getBody());
        return licensees;
    }
}
