package bw.org.bocra.portal.access;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import bw.org.bocra.portal.GenericTestData;
import bw.org.bocra.portal.access.type.AccessPointTypeTestData;
import bw.org.bocra.portal.access.type.AccessPointTypeVO;

@Component
@Profile("test")
public class AccessPointTestData extends GenericTestData<AccessPointVO, AccessPointRepository, AccessPointCriteria, AccessPointRestController> {

    // private final AccessPointRestController accessPointRestController;
    private final AccessPointTypeTestData accessPointTypeTestData;
    private final AccessPointService accessPointService;
    // private final AccessPointRepository accessPointRepository;

    public AccessPointTestData(AccessPointRestController accessPointRestController, AccessPointTypeTestData accessPointTypeTestData, AccessPointRepository accessPointRepository, AccessPointService accessPointService) {
        super(accessPointRepository, accessPointRestController);
        this.accessPointTypeTestData = accessPointTypeTestData;
        this.accessPointService = accessPointService;
    }

    @Override
    public  AccessPointVO createUnsavedData() {
        AccessPointTypeVO type = accessPointTypeTestData.generateSequentialData(1).iterator().next();

        AccessPointVO point = createUnsavedAccessPointNoType();

        point.setAccessPointType(type);

        return point;
    }
    
    public AccessPointVO createUnsavedAccessPointNoType() {

        AccessPointVO point = new AccessPointVO();

        point.setAccessPointType(null);
        point.setCreatedBy("testuser4");
        point.setCreatedDate(LocalDateTime.now());
        point.setName("Test Type ");
        point.setUrl("/test");

        return point;
    }
    
    public AccessPointVO createUnsavedAccessPointUnsavedType() {

        AccessPointVO point = createUnsavedAccessPointNoType();
        point.setAccessPointType(accessPointTypeTestData.createUnsavedData());

        return point;
    }

    // public AccessPointVO create() {
    //     return (AccessPointVO)getRestController().save(createUnsavedAccessPoint()).getBody();
    // }

    // public Collection<AccessPointVO> generateSequentialData(int size) {

    //     return generateUnsavedSequentialData(size)
    //         .stream()
    //         .map(point -> (AccessPointVO)getRestController().save(point).getBody())
    //         .collect(Collectors.toList());

    // }

    @Override
    public Collection<AccessPointVO> generateUnsavedSequentialData(int size) {
        Collection<AccessPointVO> points = new ArrayList<>();
        AccessPointTypeVO type = accessPointTypeTestData.generateSequentialData(1).iterator().next();

        for (int i = 1; i <= size; i++) {

            AccessPointVO point = new AccessPointVO();

            point.setAccessPointType(type);
            point.setCreatedBy("testuser4");
            point.setCreatedDate(LocalDateTime.now());
            point.setName("Test Type " + i);
            point.setUrl("/test" + i);

            points.add(point);
            
        }

        return points;
    }
    
    @Override
    public Collection<AccessPointVO> searchData() {
        
        AccessPointTypeVO type = accessPointTypeTestData.generateSequentialData(1).iterator().next();
        Collection<AccessPointVO> data = new ArrayList<>();

        AccessPointVO point = new AccessPointVO();

        point.setAccessPointType(type);
        point.setCreatedBy("testuser4");
        point.setCreatedDate(LocalDateTime.now());
        point.setName("Test");
        point.setUrl("/test");
        data.add((AccessPointVO) getRestController().save(point).getBody());

        point = new AccessPointVO();

        point.setAccessPointType(type);
        point.setCreatedBy("testuser4");
        point.setCreatedDate(LocalDateTime.now());
        point.setName("aceess");
        point.setUrl("/aceess");
        data.add((AccessPointVO) getRestController().save(point).getBody());

        point = new AccessPointVO();

        point.setAccessPointType(type);
        point.setCreatedBy("testuser4");
        point.setCreatedDate(LocalDateTime.now());
        point.setName("authorisation");
        point.setUrl("/authorisation");
        data.add((AccessPointVO) getRestController().save(point).getBody());

        point = new AccessPointVO();

        point.setAccessPointType(type);
        point.setCreatedBy("testuser4");
        point.setCreatedDate(LocalDateTime.now());
        point.setName("access");
        point.setUrl("/access");
        data.add((AccessPointVO) getRestController().save(point).getBody());

        point = new AccessPointVO();

        point.setAccessPointType(type);
        point.setCreatedBy("testuser4");
        point.setCreatedDate(LocalDateTime.now());
        point.setName("Access Point Type");
        point.setUrl("/access/type");
        data.add((AccessPointVO) getRestController().save(point).getBody());

        point = new AccessPointVO();

        point.setAccessPointType(type);
        point.setCreatedBy("testuser4");
        point.setCreatedDate(LocalDateTime.now());
        point.setName("Licensee");
        point.setUrl("/licensee");
        data.add((AccessPointVO) getRestController().save(point).getBody());

        point = new AccessPointVO();

        point.setAccessPointType(type);
        point.setCreatedBy("testuser4");
        point.setCreatedDate(LocalDateTime.now());
        point.setName("Licence");
        point.setUrl("/licence");
        data.add((AccessPointVO) getRestController().save(point).getBody());

        point = new AccessPointVO();

        point.setAccessPointType(type);
        point.setCreatedBy("testuser4");
        point.setCreatedDate(LocalDateTime.now());
        point.setName("Licence Type");
        point.setUrl("/licence/type");
        data.add((AccessPointVO) getRestController().save(point).getBody());

        point = new AccessPointVO();
        point.setAccessPointType(type);
        point.setCreatedBy("testuser4");
        point.setCreatedDate(LocalDateTime.now());
        point.setName("Licence Type Form");
        point.setUrl("/licence/type/form");
        data.add((AccessPointVO) getRestController().save(point).getBody());

        point = new AccessPointVO();
        point.setAccessPointType(type);
        point.setCreatedBy("testuser4");
        point.setCreatedDate(LocalDateTime.now());
        point.setName("Licence Type Sector");
        point.setUrl("/licence/type/sector");
        data.add((AccessPointVO) getRestController().save(point).getBody());

        point = new AccessPointVO();
        point.setAccessPointType(type);
        point.setCreatedBy("testuser4");
        point.setCreatedDate(LocalDateTime.now());
        point.setName("Licence Type Form Field");
        point.setUrl("/licence/type/form/field");
        data.add((AccessPointVO) getRestController().save(point).getBody());

        return data;
    }

    @Override
    public AccessPointCriteria searchCriteria() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public AccessPointCriteria searchCriteriaEmpty() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public AccessPointCriteria searchCriteriaNone() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Class<AccessPointVO> getDataClass() {
        // TODO Auto-generated method stub
        return AccessPointVO.class;
    }
}
