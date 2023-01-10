package bw.org.bocra.portal.access.type;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import bw.org.bocra.portal.GenericTestData;

@Component
@Profile("test")
public class AccessPointTypeTestData extends GenericTestData<AccessPointTypeVO, AccessPointTypeRepository, String, AccessPointTypeRestController> {


    public AccessPointTypeTestData(AccessPointTypeRestController restController, AccessPointTypeRepository repository) {
        super(repository, restController);
    }

    public void clean() {
        repository.deleteAll();
    }

    public AccessPointTypeVO createUnsavedData() {
        AccessPointTypeVO type = new AccessPointTypeVO();

        type.setCode("test");
        type.setName("Test Type");
        type.setDescription("This is a test");

        return type;
    }

    // public AccessPointTypeVO createAccessPointType() {
    //     AccessPointTypeVO type = new AccessPointTypeVO();

    //     type.setCode("test");
    //     type.setName("Test Type");
    //     type.setDescription("This is a test");
    //     type = (AccessPointTypeVO) restController.save(type).getBody();
        
    //     return type;
    // }

    public Collection<AccessPointTypeVO> generateSequentialData(int size) {
        
        return generateUnsavedSequentialData(size)
            .stream()
            .map(type -> (AccessPointTypeVO)restController.save(type).getBody())
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
    
    public Collection<AccessPointTypeVO> searchData() {
        Collection<AccessPointTypeVO> types = new ArrayList<>();

        AccessPointTypeVO type = new AccessPointTypeVO();

        type.setCode("test");
        type.setName("Test Type");
        type.setDescription("This is a test");

        types.add((AccessPointTypeVO) restController.save(type).getBody());

        type = new AccessPointTypeVO();

        type.setCode("serious");
        type.setName("Serious Type");
        type.setDescription("This is a test");

        types.add((AccessPointTypeVO) restController.save(type).getBody());

        type = new AccessPointTypeVO();

        type.setCode("onelove");
        type.setName("Top love");
        type.setDescription("This is a test");

        types.add((AccessPointTypeVO) restController.save(type).getBody());

        type = new AccessPointTypeVO();

        type.setCode("test6");
        type.setName("Test Type 6");
        type.setDescription("This is a test");

        types.add((AccessPointTypeVO) restController.save(type).getBody());

        type = new AccessPointTypeVO();

        type.setCode("sixteen");
        type.setName("One Six");
        type.setDescription("This is a test");

        types.add((AccessPointTypeVO) restController.save(type).getBody());

        type = new AccessPointTypeVO();

        type.setCode("test16");
        type.setName("Testing sixteen");
        type.setDescription("This is a test");

        types.add((AccessPointTypeVO) restController.save(type).getBody());

        type = new AccessPointTypeVO();

        type.setCode("stop");
        type.setName("Test Type Stop");
        type.setDescription("This is a test");

        types.add((AccessPointTypeVO) restController.save(type).getBody());
        return types;
    }

    @Override
    public String searchCriteria() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String searchCriteriaEmpty() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String searchCriteriaNone() {
        // TODO Auto-generated method stub
        return null;
    }
}
