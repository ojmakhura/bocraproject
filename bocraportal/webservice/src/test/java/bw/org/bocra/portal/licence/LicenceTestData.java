package bw.org.bocra.portal.licence;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import bw.org.bocra.portal.GenericTestData;
import bw.org.bocra.portal.licence.type.LicenceTypeService;
import bw.org.bocra.portal.licence.type.LicenceTypeTestData;
import bw.org.bocra.portal.licence.type.LicenceTypeVO;
import bw.org.bocra.portal.licensee.Licensee;
import bw.org.bocra.portal.licensee.LicenseeTestData;
import bw.org.bocra.portal.licensee.LicenseeVO;

@Component
@Profile("test")
public class LicenceTestData extends GenericTestData<LicenceVO, LicenceRepository, LicenceCriteria, LicenceRestController>{
    
    // private final LicenceRestController accessPointRestController;
    private final LicenceTypeTestData licenceTypeTestData;
    private LicenceService licenceService;
    private LicenseeTestData licenseeTestData;

    public LicenceTestData(LicenceRestController licenceRestController, LicenceTypeTestData licenceTypeTestData, LicenceRepository licenceRepository, LicenceService licenceService) {
        super(licenceRepository, licenceRestController);
        this.licenceTypeTestData = licenceTypeTestData;
        this.licenceService = licenceService;
    }

    @Override
    public  LicenceVO createUnsavedData() {
        LicenceTypeVO type = licenceTypeTestData.generateSequentialData(1).iterator().next();

        LicenceVO lice = createUnsavedLicenceNoType();

        lice.setLicenceType(type);

        return lice;
    }

    public LicenceVO createUnsavedLicenceNoType() {

        LicenceVO licence = new LicenceVO();

        licence.setLicenceType(licence.getLicenceType());
        licence.setLicensee(licence.getLicensee());
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

        LicenceVO licence = createUnsavedLicenceNoType();
        licence.setLicenceType(licenceTypeTestData.createUnsavedData());

        return licence;

        // LicenceVO licence = new LicenceVO();

        // licence.setLicenceType(licenceTypeTestData.create());
        // licence.setCreatedBy("testuser4");
        // licence.setCreatedDate(LocalDateTime.now());
        // //licence.setLicensee(null);
        // licence.setLicenceNumber(UUID.randomUUID().toString());
        // licence.setProvisional(false);
        // licence.setStartDate(LocalDate.now().plusWeeks(1));
        // licence.setEndDate(licence.getStartDate().plusYears(2));
        // licence.setStatus(LicenceStatus.ACTIVE);

        // return licence;
    }
    
    public  LicenceVO createUnsavedLicence() {
        LicenceTypeVO type = licenceTypeTestData.generateSequentialData(1).iterator().next();

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

    public Collection<LicenceVO> generateSequentialData(int size) {

        return generateUnsavedSequentialData(size)
            .stream()
            .map(licence -> licenceService.save(licence))
            .collect(Collectors.toList());

    }

    public Collection<LicenceVO> generateUnsavedSequentialData(int size) {
        Collection<LicenceVO> licences = new ArrayList<>();
        LicenceTypeVO type = licenceTypeTestData.generateSequentialData(1).iterator().next();
        LicenseeVO licensee = licenseeTestData.generateSequentialData(1).iterator().next();

        for (int i = 1; i <= size; i++) {

            LicenceVO licence = new LicenceVO();

            licence.setLicenceType(type);
            licence.setCreatedBy("testuser4");
            licence.setCreatedDate(LocalDateTime.now());
            licence.setLicensee(licensee);
            licence.setLicenceNumber(UUID.randomUUID().toString());
            licence.setProvisional(false);
            licence.setStartDate(LocalDate.now().plusWeeks(1));
            licence.setEndDate(licence.getStartDate().plusYears(2));
            licence.setStatus(LicenceStatus.ACTIVE);

            licences.add(licence);
            
        }

        return licences;
    }
    
    @Override
    public Collection<LicenceVO> searchData() {
        
        LicenceTypeVO type = licenceTypeTestData.generateSequentialData(1).iterator().next();
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
        data.add((LicenceVO) getRestController().save(licence).getBody());

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
        data.add((LicenceVO) getRestController().save(licence).getBody());

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
        data.add((LicenceVO) getRestController().save(licence).getBody());

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
        data.add((LicenceVO) getRestController().save(licence).getBody());

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
        data.add((LicenceVO) getRestController().save(licence).getBody());

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
        data.add((LicenceVO) getRestController().save(licence).getBody());

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
        data.add((LicenceVO) getRestController().save(licence).getBody());

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
        data.add((LicenceVO) getRestController().save(licence).getBody());

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
        data.add((LicenceVO) getRestController().save(licence).getBody());

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
        data.add((LicenceVO) getRestController().save(licence).getBody());

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
        data.add((LicenceVO) getRestController().save(licence).getBody());

        return data;
    }

    @Override
    public LicenceCriteria searchCriteria() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public LicenceCriteria searchCriteriaEmpty() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public LicenceCriteria searchCriteriaNone() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Class<LicenceVO> getDataClass() {
        // TODO Auto-generated method stub
        return LicenceVO.class;
    }
}
