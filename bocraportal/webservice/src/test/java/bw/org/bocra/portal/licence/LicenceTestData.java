package bw.org.bocra.portal.licence;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import bw.org.bocra.portal.licence.type.LicenceTypeTestData;
import bw.org.bocra.portal.licence.type.LicenceTypeVO;

@Component
public class LicenceTestData {
    
    private final LicenceRestController accessPointRestController;
    private final LicenceTypeTestData accessPointTypeTestData;

    public LicenceTestData(LicenceRestController accessPointRestController, LicenceTypeTestData accessPointTypeTestData) {
        this.accessPointRestController = accessPointRestController;
        this.accessPointTypeTestData = accessPointTypeTestData;
    }

    public LicenceRestController getLicenceRestController() {
        return accessPointRestController;
    }
    
    public  LicenceVO createUnsavedLicence() {
        LicenceTypeVO type = accessPointTypeTestData.generateSequentialData(1).iterator().next();

        LicenceVO licence = new LicenceVO();

        licence.setLicenceType(type);
        licence.setCreatedBy("testuser4");
        licence.setCreatedDate(LocalDateTime.now());
        //licence.setLicensee(null);
        licence.setLicenceNumber(UUID.randomUUID().toString());
        licence.setProvisional(false);
        licence.setStartDate(LocalDate.now().plusWeeks(1));
        licence.setEndDate(licence.getStartDate().plusYears(2));
        licence.setStatus(LicenceStatus.ACTIVE);
        

        return licence;
    }
    
    public LicenceVO createUnsavedLicenceNoType() {

        LicenceVO licence = new LicenceVO();

        licence.setLicenceType(null);
        licence.setCreatedBy("testuser4");
        licence.setCreatedDate(LocalDateTime.now());
        //licence.setLicensee(null);
        licence.setLicenceNumber(UUID.randomUUID().toString());
        licence.setProvisional(false);
        licence.setStartDate(LocalDate.now().plusWeeks(1));
        licence.setEndDate(licence.getStartDate().plusYears(2));
        licence.setStatus(LicenceStatus.ACTIVE);

        return licence;
    }
    
    public LicenceVO createUnsavedLicenceUnsavedType() {

        LicenceVO licence = new LicenceVO();

        licence.setLicenceType(accessPointTypeTestData.createUnsavedLicenceType());
        licence.setCreatedBy("testuser4");
        licence.setCreatedDate(LocalDateTime.now());
        //licence.setLicensee(null);
        licence.setLicenceNumber(UUID.randomUUID().toString());
        licence.setProvisional(false);
        licence.setStartDate(LocalDate.now().plusWeeks(1));
        licence.setEndDate(licence.getStartDate().plusYears(2));
        licence.setStatus(LicenceStatus.ACTIVE);

        return licence;
    }

    public Collection<LicenceVO> generateSequentialData(int size) {

        return generateUnsavedSequentialData(size)
            .stream()
            .map(licence -> (LicenceVO)accessPointRestController.save(licence).getBody())
            .collect(Collectors.toList());

    }

    public Collection<LicenceVO> generateUnsavedSequentialData(int size) {
        Collection<LicenceVO> licences = new ArrayList<>();
        LicenceTypeVO type = accessPointTypeTestData.generateSequentialData(1).iterator().next();

        for (int i = 1; i <= size; i++) {

            LicenceVO licence = new LicenceVO();

            licence.setLicenceType(type);
            licence.setCreatedBy("testuser4");
            licence.setCreatedDate(LocalDateTime.now());
            //licence.setLicensee(null);
            licence.setLicenceNumber(UUID.randomUUID().toString());
            licence.setProvisional(false);
            licence.setStartDate(LocalDate.now().plusWeeks(1));
            licence.setEndDate(licence.getStartDate().plusYears(2));
            licence.setStatus(LicenceStatus.ACTIVE);

            licences.add(licence);
            
        }

        return licences;
    }
    
    public Collection<LicenceVO> generateSearchData() {
        
        LicenceTypeVO type = accessPointTypeTestData.generateSequentialData(1).iterator().next();
        Collection<LicenceVO> data = new ArrayList<>();

        LicenceVO licence = new LicenceVO();

        licence.setLicenceType(type);
        licence.setCreatedBy("testuser4");
        licence.setCreatedDate(LocalDateTime.now());
        //licence.setLicensee(null);
        licence.setLicenceNumber(UUID.randomUUID().toString());
        licence.setProvisional(false);
        licence.setStartDate(LocalDate.now().plusWeeks(1));
        licence.setEndDate(licence.getStartDate().plusYears(2));
        licence.setStatus(LicenceStatus.ACTIVE);
        data.add((LicenceVO) accessPointRestController.save(licence).getBody());

        licence = new LicenceVO();

        licence.setLicenceType(type);
        licence.setCreatedBy("testuser4");
        licence.setCreatedDate(LocalDateTime.now());
        //licence.setLicensee(null);
        licence.setLicenceNumber(UUID.randomUUID().toString());
        licence.setProvisional(false);
        licence.setStartDate(LocalDate.now().plusWeeks(1));
        licence.setEndDate(licence.getStartDate().plusYears(2));
        licence.setStatus(LicenceStatus.ACTIVE);
        data.add((LicenceVO) accessPointRestController.save(licence).getBody());

        licence = new LicenceVO();

        licence.setLicenceType(type);
        licence.setCreatedBy("testuser4");
        licence.setCreatedDate(LocalDateTime.now());
        //licence.setLicensee(null);
        licence.setLicenceNumber(UUID.randomUUID().toString());
        licence.setProvisional(false);
        licence.setStartDate(LocalDate.now().plusWeeks(1));
        licence.setEndDate(licence.getStartDate().plusYears(2));
        licence.setStatus(LicenceStatus.ACTIVE);
        data.add((LicenceVO) accessPointRestController.save(licence).getBody());

        licence = new LicenceVO();

        licence.setLicenceType(type);
        licence.setCreatedBy("testuser4");
        licence.setCreatedDate(LocalDateTime.now());
        //licence.setLicensee(null);
        licence.setLicenceNumber(UUID.randomUUID().toString());
        licence.setProvisional(false);
        licence.setStartDate(LocalDate.now().plusWeeks(1));
        licence.setEndDate(licence.getStartDate().plusYears(2));
        licence.setStatus(LicenceStatus.ACTIVE);
        data.add((LicenceVO) accessPointRestController.save(licence).getBody());

        licence = new LicenceVO();

        licence.setLicenceType(type);
        licence.setCreatedBy("testuser4");
        licence.setCreatedDate(LocalDateTime.now());
        //licence.setLicensee(null);
        licence.setLicenceNumber(UUID.randomUUID().toString());
        licence.setProvisional(false);
        licence.setStartDate(LocalDate.now().plusWeeks(1));
        licence.setEndDate(licence.getStartDate().plusYears(2));
        licence.setStatus(LicenceStatus.ACTIVE);
        data.add((LicenceVO) accessPointRestController.save(licence).getBody());

        licence = new LicenceVO();

        licence.setLicenceType(type);
        licence.setCreatedBy("testuser4");
        licence.setCreatedDate(LocalDateTime.now());
        //licence.setLicensee(null);
        licence.setLicenceNumber(UUID.randomUUID().toString());
        licence.setProvisional(false);
        licence.setStartDate(LocalDate.now().plusWeeks(1));
        licence.setEndDate(licence.getStartDate().plusYears(2));
        licence.setStatus(LicenceStatus.ACTIVE);
        data.add((LicenceVO) accessPointRestController.save(licence).getBody());

        licence = new LicenceVO();

        licence.setLicenceType(type);
        licence.setCreatedBy("testuser4");
        licence.setCreatedDate(LocalDateTime.now());
        //licence.setLicensee(null);
        licence.setLicenceNumber(UUID.randomUUID().toString());
        licence.setProvisional(false);
        licence.setStartDate(LocalDate.now().plusWeeks(1));
        licence.setEndDate(licence.getStartDate().plusYears(2));
        licence.setStatus(LicenceStatus.ACTIVE);
        data.add((LicenceVO) accessPointRestController.save(licence).getBody());

        licence = new LicenceVO();

        licence.setLicenceType(type);
        licence.setCreatedBy("testuser4");
        licence.setCreatedDate(LocalDateTime.now());
        //licence.setLicensee(null);
        licence.setLicenceNumber(UUID.randomUUID().toString());
        licence.setProvisional(false);
        licence.setStartDate(LocalDate.now().plusWeeks(1));
        licence.setEndDate(licence.getStartDate().plusYears(2));
        licence.setStatus(LicenceStatus.ACTIVE);
        data.add((LicenceVO) accessPointRestController.save(licence).getBody());

        licence = new LicenceVO();
        licence.setLicenceType(type);
        licence.setCreatedBy("testuser4");
        licence.setCreatedDate(LocalDateTime.now());
        //licence.setLicensee(null);
        licence.setLicenceNumber(UUID.randomUUID().toString());
        licence.setProvisional(false);
        licence.setStartDate(LocalDate.now().plusWeeks(1));
        licence.setEndDate(licence.getStartDate().plusYears(2));
        licence.setStatus(LicenceStatus.ACTIVE);
        data.add((LicenceVO) accessPointRestController.save(licence).getBody());

        licence = new LicenceVO();
        licence.setLicenceType(type);
        licence.setCreatedBy("testuser4");
        licence.setCreatedDate(LocalDateTime.now());
        //licence.setLicensee(null);
        licence.setLicenceNumber(UUID.randomUUID().toString());
        licence.setProvisional(false);
        licence.setStartDate(LocalDate.now().plusWeeks(1));
        licence.setEndDate(licence.getStartDate().plusYears(2));
        licence.setStatus(LicenceStatus.ACTIVE);
        data.add((LicenceVO) accessPointRestController.save(licence).getBody());

        licence = new LicenceVO();
        licence.setLicenceType(type);
        licence.setCreatedBy("testuser4");
        licence.setCreatedDate(LocalDateTime.now());
        //licence.setLicensee(null);
        licence.setLicenceNumber(UUID.randomUUID().toString());
        licence.setProvisional(false);
        licence.setStartDate(LocalDate.now().plusWeeks(1));
        licence.setEndDate(licence.getStartDate().plusYears(2));
        licence.setStatus(LicenceStatus.ACTIVE);
        data.add((LicenceVO) accessPointRestController.save(licence).getBody());

        return data;
    }
}
