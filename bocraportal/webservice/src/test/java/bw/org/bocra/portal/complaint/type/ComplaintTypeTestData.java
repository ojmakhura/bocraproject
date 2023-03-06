package bw.org.bocra.portal.complaint.type;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import bw.org.bocra.portal.GenericTestData;

@Component
@Profile("test")
public class ComplaintTypeTestData extends GenericTestData<ComplaintTypeVO, ComplaintTypeRepository, String, ComplaintTypeRestController> {
    private final ComplaintTypeService complaintTypeService;

    public ComplaintTypeTestData(ComplaintTypeRestController restController, ComplaintTypeRepository repository, ComplaintTypeService complaintTypeService) {
        super(repository, restController);
        this.complaintTypeService = complaintTypeService;
    }

    public void clean() {
        repository.deleteAll();
    }

    public ComplaintTypeVO createUnsavedData() {
        ComplaintTypeVO type = new ComplaintTypeVO();

        type.setCode("TestCode");
        type.setCreatedBy("Test User");
        type.setCreatedDate(LocalDateTime.now());
        type.setDescription("Complaint Type Description Test");
        type.setId(null);
        type.setTypeName("Test Name");
        type.setUpdatedBy("Test User");
        type.setUpdatedDate(LocalDateTime.now());

        return type;
    }

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

            type.setCode("TestCode" + i);
            type.setCreatedBy("Test User" + i);
            type.setCreatedDate(LocalDateTime.now());
            type.setDescription("Complaint Type Description Test" + i);
            type.setId(null);
            type.setTypeName("Test Name" + i);
            type.setUpdatedBy("Test User" + i);
            type.setUpdatedDate(LocalDateTime.now());

            types.add(type);

        }
        return types;
    }
    
    public Collection<ComplaintTypeVO> searchData() {
        Collection<ComplaintTypeVO> types = new ArrayList<>();

        ComplaintTypeVO type = new ComplaintTypeVO();

        type.setCode("TestCode");
        type.setCreatedBy("Test User");
        type.setCreatedDate(LocalDateTime.now());
        type.setDescription("Complaint Type Description Test");
        type.setId(null);
        type.setTypeName("Test Name");
        type.setUpdatedBy("Test User");
        type.setUpdatedDate(LocalDateTime.now());

        types.add(complaintTypeService.save(type));

        type = new ComplaintTypeVO();

        type.setCode("TestCode");
        type.setCreatedBy("Test User");
        type.setCreatedDate(LocalDateTime.now());
        type.setDescription("Complaint Type Description Test");
        type.setId(null);
        type.setTypeName("Test Name");
        type.setUpdatedBy("Test User");
        type.setUpdatedDate(LocalDateTime.now());

        types.add(complaintTypeService.save(type));

        type = new ComplaintTypeVO();

        type.setCode("TestCode");
        type.setCreatedBy("Test User");
        type.setCreatedDate(LocalDateTime.now());
        type.setDescription("Complaint Type Description Test");
        type.setId(null);
        type.setTypeName("Test Name");
        type.setUpdatedBy("Test User");
        type.setUpdatedDate(LocalDateTime.now());

        types.add(complaintTypeService.save(type));

        type = new ComplaintTypeVO();

        type.setCode("TestCode");
        type.setCreatedBy("Test User");
        type.setCreatedDate(LocalDateTime.now());
        type.setDescription("Complaint Type Description Test");
        type.setId(null);
        type.setTypeName("Test Name");
        type.setUpdatedBy("Test User");
        type.setUpdatedDate(LocalDateTime.now());

        types.add(complaintTypeService.save(type));

        type = new ComplaintTypeVO();

        type.setCode("TestCode");
        type.setCreatedBy("Test User");
        type.setCreatedDate(LocalDateTime.now());
        type.setDescription("Complaint Type Description Test");
        type.setId(null);
        type.setTypeName("Test Name");
        type.setUpdatedBy("Test User");
        type.setUpdatedDate(LocalDateTime.now());

        types.add(complaintTypeService.save(type));

        type = new ComplaintTypeVO();

        type.setCode("TestCode");
        type.setCreatedBy("Test User");
        type.setCreatedDate(LocalDateTime.now());
        type.setDescription("Complaint Type Description Test");
        type.setId(null);
        type.setTypeName("Test Name");
        type.setUpdatedBy("Test User");
        type.setUpdatedDate(LocalDateTime.now());

        types.add(complaintTypeService.save(type));

        type = new ComplaintTypeVO();

        type.setCode("TestCode");
        type.setCreatedBy("Test User");
        type.setCreatedDate(LocalDateTime.now());
        type.setDescription("Complaint Type Description Test");
        type.setId(null);
        type.setTypeName("Test Name");
        type.setUpdatedBy("Test User");
        type.setUpdatedDate(LocalDateTime.now());

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
        
        return "";
    }

    @Override
    public String searchCriteriaNone() {
        return "null";
    }

    @Override
    public Class<ComplaintTypeVO> getDataClass() {
        // TODO Auto-generated method stub
        return ComplaintTypeVO.class;
    }
}
