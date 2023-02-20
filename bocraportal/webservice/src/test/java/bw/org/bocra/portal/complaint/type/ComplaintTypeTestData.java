package bw.org.bocra.portal.complaint.type;

import static org.mockito.ArgumentMatchers.same;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import bw.org.bocra.portal.GenericTestData;

@Component
@Profile("test")
public class ComplaintTypeTestData extends GenericTestData<ComplaintTypeVO, ComplaintTypeRepository,String, ComplaintTypeRestController>{
    // private final ComplaintTypeRestController complaintTypeRestController;
    
    private final ComplaintTypeService complaintTypeService;

    public ComplaintTypeTestData(ComplaintTypeRestController restController, ComplaintTypeRepository complaintTypeRepository, ComplaintTypeService complaintTypeService) {
        // this.complaintTypeRestController = complaintTypeRestController;
        super(complaintTypeRepository, restController);
        this.complaintTypeService = complaintTypeService;
    }

    public void clean() {
        repository.deleteAll();
    }

    public ComplaintTypeVO createUnsavedData() {
        ComplaintTypeVO type = new ComplaintTypeVO();

        type.setCode("test");
        type.setTypeName("Test Type");
        type.setDescription("This is a test");

        return type;
    }

    // public ComplaintTypeVO createComplaintType() {
    //     ComplaintTypeVO type = new ComplaintTypeVO();

    //     type.setCode("test");
    //     type.setTypeName("Test Type");
    //     type.setDescription("This is a test");
    //     type = complaintTypeService.save(type);
        
    //     return type;
    // }

    public Collection<ComplaintTypeVO> generateSequentialData(int size) {
        
        return generateUnsavedSequentialData(size)
            .stream()
            .map(type -> complaintTypeService.save(type))
            .collect(Collectors.toList());

    }

    public Collection<ComplaintTypeVO> generateUnsavedSequentialData(int size) {
        Collection<ComplaintTypeVO> types = new ArrayList<>();
        for (int i = 1; i <= size; i++) {

            ComplaintTypeVO type = new ComplaintTypeVO();

            type.setCode("test" + i);
            type.setTypeName("Test Type " + i);
            type.setDescription("This is a test " + i);

            types.add(type);

        }
        return types;
    }
    
    public Collection<ComplaintTypeVO> searchData() {
        Collection<ComplaintTypeVO> types = new ArrayList<>();

        ComplaintTypeVO type = new ComplaintTypeVO();

        type.setCode("test");
        type.setTypeName("Test Type");
        type.setDescription("This is a test");

        types.add(complaintTypeService.save(type));

        type = new ComplaintTypeVO();

        type.setCode("serious");
        type.setTypeName("Serious Type");
        type.setDescription("This is a test");

        types.add(complaintTypeService.save(type));

        type = new ComplaintTypeVO();

        type.setCode("onelove");
        type.setTypeName("Top love");
        type.setDescription("This is a test");

        types.add(complaintTypeService.save(type));

        type = new ComplaintTypeVO();

        type.setCode("test6");
        type.setTypeName("Test Type 6");
        type.setDescription("This is a test");

        types.add(complaintTypeService.save(type));

        type = new ComplaintTypeVO();

        type.setCode("sixteen");
        type.setTypeName("One Six");
        type.setDescription("This is a test");

        types.add(complaintTypeService.save(type));

        type = new ComplaintTypeVO();

        type.setCode("test16");
        type.setTypeName("Testing sixteen");
        type.setDescription("This is a test");

        types.add(complaintTypeService.save(type));
        type = new ComplaintTypeVO();

        type.setCode("stop");
        type.setTypeName("Test Type Stop");
        type.setDescription("This is a test");

        types.add(complaintTypeService.save(type));
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

    @Override
    public Class<ComplaintTypeVO> getDataClass() {
        // TODO Auto-generated method stub
        return ComplaintTypeVO.class;
    }
}
