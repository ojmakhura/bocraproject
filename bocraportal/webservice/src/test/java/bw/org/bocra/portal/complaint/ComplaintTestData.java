package bw.org.bocra.portal.complaint;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import bw.org.bocra.portal.GenericTestData;
import bw.org.bocra.portal.complaint.type.ComplaintTypeTestData;
import bw.org.bocra.portal.complaint.type.ComplaintTypeVO;

@Component
@Profile("test")
public class ComplaintTestData extends GenericTestData<ComplaintVO, ComplaintRepository, String, ComplaintRestController>{
    // private final ComplaintRestController complaintRestController;
    private final ComplaintTypeTestData complaintTypeTestData;
    private final ComplaintService complaintService;

    public ComplaintTestData(ComplaintRestController complaintRestController, ComplaintTypeTestData complaintTypeTestData, ComplaintRepository complaintRepository, ComplaintService complaintService) {
        super(complaintRepository, complaintRestController);
        this.complaintTypeTestData = complaintTypeTestData;
        this.complaintService = complaintService;
    }
 
    public  ComplaintVO createUnsavedData() {
        ComplaintTypeVO type = complaintTypeTestData.generateSequentialData(1).iterator().next();

        ComplaintVO complaint = new ComplaintVO();

        complaint.setComplaintType(type);
        complaint.setCreatedDate(LocalDateTime.now());

        return complaint;
    }
    
    public ComplaintVO createUnsavedComplaintNoType() {

        ComplaintVO complaint = new ComplaintVO();

        complaint.setComplaintType(null);
        complaint.setCreatedDate(LocalDateTime.now());

        return complaint;
    }
    
    public ComplaintVO createUnsavedComplaintUnsavedType() {

        ComplaintVO complaint = new ComplaintVO();

        complaint.setComplaintType(complaintTypeTestData.createUnsavedData());
        complaint.setCreatedDate(LocalDateTime.now());

        return complaint;
    }

    public Collection<ComplaintVO> generateSequentialData(int size) {

        return generateUnsavedSequentialData(size)
            .stream()
            .map(complaint -> complaintService.save(complaint))
            .collect(Collectors.toList());

    }

    public Collection<ComplaintVO> generateUnsavedSequentialData(int size) {
        Collection<ComplaintVO> complaints = new ArrayList<>();
        ComplaintTypeVO type = complaintTypeTestData.generateSequentialData(1).iterator().next();

        for (int i = 1; i <= size; i++) {

            ComplaintVO complaint = new ComplaintVO();

            complaint.setComplaintType(type);
            complaint.setDetails("I have no internet");
            complaint.setCreatedDate(LocalDateTime.now());
            complaint.setFirstName("Random" + i);
            complaint.setSurname("Person" + i);
            complaint.setEmail(i + "complaint@person.com");
            
            complaints.add(complaint);
            
        }

        return complaints;
    }
    
    public Collection<ComplaintVO> searchData() {
        
        ComplaintTypeVO type = complaintTypeTestData.generateSequentialData(1).iterator().next();
        Collection<ComplaintVO> data = new ArrayList<>();

        ComplaintVO complaint = new ComplaintVO();

        complaint.setComplaintType(type);
        complaint.setCreatedDate(LocalDateTime.now());
        data.add((ComplaintVO) getRestController().save(complaint).getBody());

        complaint = new ComplaintVO();

        complaint.setComplaintType(type);
        complaint.setCreatedDate(LocalDateTime.now());
        data.add((ComplaintVO) getRestController().save(complaint).getBody());

        complaint = new ComplaintVO();

        complaint.setComplaintType(type);
        complaint.setCreatedDate(LocalDateTime.now());
        data.add((ComplaintVO) getRestController().save(complaint).getBody());

        complaint = new ComplaintVO();

        complaint.setComplaintType(type);
        complaint.setCreatedDate(LocalDateTime.now());
        data.add((ComplaintVO) getRestController().save(complaint).getBody());

        complaint = new ComplaintVO();

        complaint.setComplaintType(type);
        complaint.setCreatedDate(LocalDateTime.now());
        data.add((ComplaintVO) getRestController().save(complaint).getBody());

        complaint = new ComplaintVO();

        complaint.setComplaintType(type);
        complaint.setCreatedDate(LocalDateTime.now());
        data.add((ComplaintVO) getRestController().save(complaint).getBody());

        complaint = new ComplaintVO();

        complaint.setComplaintType(type);
        complaint.setCreatedDate(LocalDateTime.now());
        data.add((ComplaintVO) getRestController().save(complaint).getBody());

        complaint = new ComplaintVO();

        complaint.setComplaintType(type);
        complaint.setCreatedDate(LocalDateTime.now());
        data.add((ComplaintVO) getRestController().save(complaint).getBody());

        complaint = new ComplaintVO();
        complaint.setComplaintType(type);
        complaint.setCreatedDate(LocalDateTime.now());
        data.add((ComplaintVO) getRestController().save(complaint).getBody());

        complaint = new ComplaintVO();
        complaint.setComplaintType(type);
        complaint.setCreatedDate(LocalDateTime.now());
        data.add((ComplaintVO) getRestController().save(complaint).getBody());

        complaint = new ComplaintVO();
        complaint.setComplaintType(type);
        complaint.setCreatedDate(LocalDateTime.now());
        data.add((ComplaintVO) getRestController().save(complaint).getBody());

        return data;
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
    public Class<ComplaintVO> getDataClass() {
        // TODO Auto-generated method stub
        return ComplaintVO.class;
    }
}
