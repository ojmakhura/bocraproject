package bw.org.bocra.portal.complaint.type;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class ComplaintTypeTestData {
    private final ComplaintTypeRestController complaintTypeRestController;

    public ComplaintTypeTestData(ComplaintTypeRestController complaintTypeRestController) {
        this.complaintTypeRestController = complaintTypeRestController;
    }

    public ComplaintTypeVO createUnsavedComplaintType() {
        ComplaintTypeVO type = new ComplaintTypeVO();

        type.setCode("test");
        type.setTypeName("Test Type");
        type.setDescription("This is a test");

        return type;
    }

    public ComplaintTypeVO createComplaintType() {
        ComplaintTypeVO type = new ComplaintTypeVO();

        type.setCode("test");
        type.setTypeName("Test Type");
        type.setDescription("This is a test");
        type = (ComplaintTypeVO) complaintTypeRestController.save(type).getBody();
        
        return type;
    }

    public Collection<ComplaintTypeVO> generateSequentialData(int size) {
        
        return generateUnsavedSequentialData(size)
            .stream()
            .map(type -> (ComplaintTypeVO)complaintTypeRestController.save(type).getBody())
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
    
    public Collection<ComplaintTypeVO> generateSearchData() {
        Collection<ComplaintTypeVO> types = new ArrayList<>();

        ComplaintTypeVO type = new ComplaintTypeVO();

        type.setCode("test");
        type.setTypeName("Test Type");
        type.setDescription("This is a test");

        types.add((ComplaintTypeVO) complaintTypeRestController.save(type).getBody());

        type = new ComplaintTypeVO();

        type.setCode("serious");
        type.setTypeName("Serious Type");
        type.setDescription("This is a test");

        types.add((ComplaintTypeVO) complaintTypeRestController.save(type).getBody());

        type = new ComplaintTypeVO();

        type.setCode("onelove");
        type.setTypeName("Top love");
        type.setDescription("This is a test");

        types.add((ComplaintTypeVO) complaintTypeRestController.save(type).getBody());

        type = new ComplaintTypeVO();

        type.setCode("test6");
        type.setTypeName("Test Type 6");
        type.setDescription("This is a test");

        types.add((ComplaintTypeVO) complaintTypeRestController.save(type).getBody());

        type = new ComplaintTypeVO();

        type.setCode("sixteen");
        type.setTypeName("One Six");
        type.setDescription("This is a test");

        types.add((ComplaintTypeVO) complaintTypeRestController.save(type).getBody());

        type = new ComplaintTypeVO();

        type.setCode("test16");
        type.setTypeName("Testing sixteen");
        type.setDescription("This is a test");

        types.add((ComplaintTypeVO) complaintTypeRestController.save(type).getBody());

        type = new ComplaintTypeVO();

        type.setCode("stop");
        type.setTypeName("Test Type Stop");
        type.setDescription("This is a test");

        types.add((ComplaintTypeVO) complaintTypeRestController.save(type).getBody());
        return types;
    }
}
