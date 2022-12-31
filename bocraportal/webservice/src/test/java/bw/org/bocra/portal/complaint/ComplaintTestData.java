package bw.org.bocra.portal.complaint;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import bw.org.bocra.portal.complaint.type.ComplaintTypeTestData;
import bw.org.bocra.portal.complaint.type.ComplaintTypeVO;

@Component
public class ComplaintTestData {
    private final ComplaintRestController complaintRestController;
    private final ComplaintTypeTestData complaintTypeTestData;

    public ComplaintTestData(ComplaintRestController complaintRestController, ComplaintTypeTestData complaintTypeTestData) {
        this.complaintRestController = complaintRestController;
        this.complaintTypeTestData = complaintTypeTestData;
    }

    public ComplaintRestController getComplaintRestController() {
        return complaintRestController;
    }
    
    public  ComplaintVO createUnsavedComplaint() {
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

        complaint.setComplaintType(complaintTypeTestData.createUnsavedComplaintType());
        complaint.setCreatedDate(LocalDateTime.now());

        return complaint;
    }

    public Collection<ComplaintVO> generateSequentialData(int size) {

        return generateUnsavedSequentialData(size)
            .stream()
            .map(complaint -> (ComplaintVO)complaintRestController.save(complaint).getBody())
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
    
    public Collection<ComplaintVO> generateSearchData() {
        
        ComplaintTypeVO type = complaintTypeTestData.generateSequentialData(1).iterator().next();
        Collection<ComplaintVO> data = new ArrayList<>();

        ComplaintVO complaint = new ComplaintVO();

        complaint.setComplaintType(type);
        complaint.setCreatedDate(LocalDateTime.now());
        data.add((ComplaintVO) complaintRestController.save(complaint).getBody());

        complaint = new ComplaintVO();

        complaint.setComplaintType(type);
        complaint.setCreatedDate(LocalDateTime.now());
        data.add((ComplaintVO) complaintRestController.save(complaint).getBody());

        complaint = new ComplaintVO();

        complaint.setComplaintType(type);
        complaint.setCreatedDate(LocalDateTime.now());
        data.add((ComplaintVO) complaintRestController.save(complaint).getBody());

        complaint = new ComplaintVO();

        complaint.setComplaintType(type);
        complaint.setCreatedDate(LocalDateTime.now());
        data.add((ComplaintVO) complaintRestController.save(complaint).getBody());

        complaint = new ComplaintVO();

        complaint.setComplaintType(type);
        complaint.setCreatedDate(LocalDateTime.now());
        data.add((ComplaintVO) complaintRestController.save(complaint).getBody());

        complaint = new ComplaintVO();

        complaint.setComplaintType(type);
        complaint.setCreatedDate(LocalDateTime.now());
        data.add((ComplaintVO) complaintRestController.save(complaint).getBody());

        complaint = new ComplaintVO();

        complaint.setComplaintType(type);
        complaint.setCreatedDate(LocalDateTime.now());
        data.add((ComplaintVO) complaintRestController.save(complaint).getBody());

        complaint = new ComplaintVO();

        complaint.setComplaintType(type);
        complaint.setCreatedDate(LocalDateTime.now());
        data.add((ComplaintVO) complaintRestController.save(complaint).getBody());

        complaint = new ComplaintVO();
        complaint.setComplaintType(type);
        complaint.setCreatedDate(LocalDateTime.now());
        data.add((ComplaintVO) complaintRestController.save(complaint).getBody());

        complaint = new ComplaintVO();
        complaint.setComplaintType(type);
        complaint.setCreatedDate(LocalDateTime.now());
        data.add((ComplaintVO) complaintRestController.save(complaint).getBody());

        complaint = new ComplaintVO();
        complaint.setComplaintType(type);
        complaint.setCreatedDate(LocalDateTime.now());
        data.add((ComplaintVO) complaintRestController.save(complaint).getBody());

        return data;
    }
}
