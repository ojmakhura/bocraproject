package bw.org.bocra.portal.licence.type;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("test")
public class LicenceTypeTestData {
    
    private final LicenceTypeRestController accessPointTypeRestController;

    public LicenceTypeTestData(LicenceTypeRestController accessPointTypeRestController) {
        this.accessPointTypeRestController = accessPointTypeRestController;
    }
    
    public LicenceTypeRestController getLicenceTypeRestController() {
        return accessPointTypeRestController;
    }

    public LicenceTypeVO createUnsavedLicenceType() {
        LicenceTypeVO type = new LicenceTypeVO();

        type.setCode("test");
        type.setName("Test Type");
        type.setDescription("This is a test");
        type.setCreatedBy("testuser4");
        type.setCreatedDate(LocalDateTime.now());

        return type;
    }

    public LicenceTypeVO createLicenceType() {
        LicenceTypeVO type = new LicenceTypeVO();

        type.setCode("test");
        type.setName("Test Type");
        type.setDescription("This is a test");
        type.setCreatedBy("testuser4");
        type.setCreatedDate(LocalDateTime.now());
        type = (LicenceTypeVO) accessPointTypeRestController.save(type).getBody();
        
        return type;
    }

    public Collection<LicenceTypeVO> generateSequentialData(int size) {
        
        return generateUnsavedSequentialData(size)
            .stream()
            .map(type -> (LicenceTypeVO)accessPointTypeRestController.save(type).getBody())
            .collect(Collectors.toList());
    }

    public Collection<LicenceTypeVO> generateUnsavedSequentialData(int size) {
        Collection<LicenceTypeVO> types = new ArrayList<>();
        for (int i = 1; i <= size; i++) {

            LicenceTypeVO type = new LicenceTypeVO();

            type.setCode("test" + i);
            type.setName("Test Type " + i);
            type.setDescription("This is a test " + i);
            type.setCreatedBy("testuser4");
            type.setCreatedDate(LocalDateTime.now());

            types.add(type);

        }
        return types;
    }
    
    public Collection<LicenceTypeVO> generateSearchData() {
        Collection<LicenceTypeVO> types = new ArrayList<>();

        LicenceTypeVO type = new LicenceTypeVO();

        type.setCode("test");
        type.setName("Test Type");
        type.setDescription("This is a test");
        type.setCreatedBy("testuser4");
        type.setCreatedDate(LocalDateTime.now());

        types.add((LicenceTypeVO) accessPointTypeRestController.save(type).getBody());

        type = new LicenceTypeVO();

        type.setCode("serious");
        type.setName("Serious Type");
        type.setDescription("This is a test");
        type.setCreatedBy("testuser4");
        type.setCreatedDate(LocalDateTime.now());

        types.add((LicenceTypeVO) accessPointTypeRestController.save(type).getBody());

        type = new LicenceTypeVO();

        type.setCode("onelove");
        type.setName("Top love");
        type.setDescription("This is a test");
        type.setCreatedBy("testuser4");
        type.setCreatedDate(LocalDateTime.now());

        types.add((LicenceTypeVO) accessPointTypeRestController.save(type).getBody());

        type = new LicenceTypeVO();

        type.setCode("test6");
        type.setName("Test Type 6");
        type.setDescription("This is a test");
        type.setCreatedBy("testuser4");
        type.setCreatedDate(LocalDateTime.now());

        types.add((LicenceTypeVO) accessPointTypeRestController.save(type).getBody());

        type = new LicenceTypeVO();

        type.setCode("sixteen");
        type.setName("One Six");
        type.setDescription("This is a test");
        type.setCreatedBy("testuser4");
        type.setCreatedDate(LocalDateTime.now());

        types.add((LicenceTypeVO) accessPointTypeRestController.save(type).getBody());

        type = new LicenceTypeVO();

        type.setCode("test16");
        type.setName("Testing sixteen");
        type.setDescription("This is a test");
        type.setCreatedBy("testuser4");
        type.setCreatedDate(LocalDateTime.now());

        types.add((LicenceTypeVO) accessPointTypeRestController.save(type).getBody());

        type = new LicenceTypeVO();

        type.setCode("stop");
        type.setName("Test Type Stop");
        type.setDescription("This is a test");
        type.setCreatedBy("testuser4");
        type.setCreatedDate(LocalDateTime.now());

        types.add((LicenceTypeVO) accessPointTypeRestController.save(type).getBody());
        return types;
    }
}
