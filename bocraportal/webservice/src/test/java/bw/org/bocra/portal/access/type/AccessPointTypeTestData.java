package bw.org.bocra.portal.access.type;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class AccessPointTypeTestData {

    private final AccessPointTypeRestController accessPointTypeRestController;

    public AccessPointTypeTestData(AccessPointTypeRestController accessPointTypeRestController) {
        this.accessPointTypeRestController = accessPointTypeRestController;
    }
    
    public AccessPointTypeRestController getAccessPointTypeRestController() {
        return accessPointTypeRestController;
    }

    public AccessPointTypeVO createUnsavedAccessPointType() {
        AccessPointTypeVO type = new AccessPointTypeVO();

        type.setCode("test");
        type.setName("Test Type");
        type.setDescription("This is a test");

        return type;
    }

    public AccessPointTypeVO createAccessPointType() {
        AccessPointTypeVO type = new AccessPointTypeVO();

        type.setCode("test");
        type.setName("Test Type");
        type.setDescription("This is a test");
        type = (AccessPointTypeVO) accessPointTypeRestController.save(type).getBody();
        
        return type;
    }

    public Collection<AccessPointTypeVO> generateSequentialData(int size) {
        
        return generateUnsavedSequentialData(size)
            .stream()
            .map(type -> (AccessPointTypeVO)accessPointTypeRestController.save(type).getBody())
            .collect(Collectors.toList());
    }

    public Collection<AccessPointTypeVO> generateUnsavedSequentialData(int size) {
        Collection<AccessPointTypeVO> types = new ArrayList<>();
        for (int i = 1; i <= size; i++) {

            AccessPointTypeVO type = new AccessPointTypeVO();

            type.setCode("test" + i);
            type.setName("Test Type " + i);
            type.setDescription("This is a test " + i);

            types.add(type);

        }
        return types;
    }
    
    public Collection<AccessPointTypeVO> generateSearchData() {
        Collection<AccessPointTypeVO> types = new ArrayList<>();

        AccessPointTypeVO type = new AccessPointTypeVO();

        type.setCode("test");
        type.setName("Test Type");
        type.setDescription("This is a test");

        types.add((AccessPointTypeVO) accessPointTypeRestController.save(type).getBody());

        type = new AccessPointTypeVO();

        type.setCode("serious");
        type.setName("Serious Type");
        type.setDescription("This is a test");

        types.add((AccessPointTypeVO) accessPointTypeRestController.save(type).getBody());

        type = new AccessPointTypeVO();

        type.setCode("onelove");
        type.setName("Top love");
        type.setDescription("This is a test");

        types.add((AccessPointTypeVO) accessPointTypeRestController.save(type).getBody());

        type = new AccessPointTypeVO();

        type.setCode("test6");
        type.setName("Test Type 6");
        type.setDescription("This is a test");

        types.add((AccessPointTypeVO) accessPointTypeRestController.save(type).getBody());

        type = new AccessPointTypeVO();

        type.setCode("sixteen");
        type.setName("One Six");
        type.setDescription("This is a test");

        types.add((AccessPointTypeVO) accessPointTypeRestController.save(type).getBody());

        type = new AccessPointTypeVO();

        type.setCode("test16");
        type.setName("Testing sixteen");
        type.setDescription("This is a test");

        types.add((AccessPointTypeVO) accessPointTypeRestController.save(type).getBody());

        type = new AccessPointTypeVO();

        type.setCode("stop");
        type.setName("Test Type Stop");
        type.setDescription("This is a test");

        types.add((AccessPointTypeVO) accessPointTypeRestController.save(type).getBody());
        return types;
    }
}
