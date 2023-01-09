package bw.org.bocra.portal.access;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import bw.org.bocra.portal.access.type.AccessPointTypeTestData;
import bw.org.bocra.portal.access.type.AccessPointTypeVO;

@Component
public class AccessPointTestData {

    private final AccessPointRestController accessPointRestController;
    private final AccessPointTypeTestData accessPointTypeTestData;

    public AccessPointTestData(AccessPointRestController accessPointRestController, AccessPointTypeTestData accessPointTypeTestData) {
        this.accessPointRestController = accessPointRestController;
        this.accessPointTypeTestData = accessPointTypeTestData;
    }

    public AccessPointRestController getAccessPointRestController() {
        return accessPointRestController;
    }
    
    public  AccessPointVO createUnsavedAccessPoint() {
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
        point.setAccessPointType(accessPointTypeTestData.createUnsavedAccessPointType());

        return point;
    }

    public AccessPointVO create() {
        return (AccessPointVO)accessPointRestController.save(createUnsavedAccessPoint()).getBody();
    }

    public Collection<AccessPointVO> generateSequentialData(int size) {

        return generateUnsavedSequentialData(size)
            .stream()
            .map(point -> (AccessPointVO)accessPointRestController.save(point).getBody())
            .collect(Collectors.toList());

    }

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
    
    public Collection<AccessPointVO> generateSearchData() {
        
        AccessPointTypeVO type = accessPointTypeTestData.generateSequentialData(1).iterator().next();
        Collection<AccessPointVO> data = new ArrayList<>();

        AccessPointVO point = new AccessPointVO();

        point.setAccessPointType(type);
        point.setCreatedBy("testuser4");
        point.setCreatedDate(LocalDateTime.now());
        point.setName("Test");
        point.setUrl("/test");
        data.add((AccessPointVO) accessPointRestController.save(point).getBody());

        point = new AccessPointVO();

        point.setAccessPointType(type);
        point.setCreatedBy("testuser4");
        point.setCreatedDate(LocalDateTime.now());
        point.setName("aceess");
        point.setUrl("/aceess");
        data.add((AccessPointVO) accessPointRestController.save(point).getBody());

        point = new AccessPointVO();

        point.setAccessPointType(type);
        point.setCreatedBy("testuser4");
        point.setCreatedDate(LocalDateTime.now());
        point.setName("authorisation");
        point.setUrl("/authorisation");
        data.add((AccessPointVO) accessPointRestController.save(point).getBody());

        point = new AccessPointVO();

        point.setAccessPointType(type);
        point.setCreatedBy("testuser4");
        point.setCreatedDate(LocalDateTime.now());
        point.setName("access");
        point.setUrl("/access");
        data.add((AccessPointVO) accessPointRestController.save(point).getBody());

        point = new AccessPointVO();

        point.setAccessPointType(type);
        point.setCreatedBy("testuser4");
        point.setCreatedDate(LocalDateTime.now());
        point.setName("Access Point Type");
        point.setUrl("/access/type");
        data.add((AccessPointVO) accessPointRestController.save(point).getBody());

        point = new AccessPointVO();

        point.setAccessPointType(type);
        point.setCreatedBy("testuser4");
        point.setCreatedDate(LocalDateTime.now());
        point.setName("Licensee");
        point.setUrl("/licensee");
        data.add((AccessPointVO) accessPointRestController.save(point).getBody());

        point = new AccessPointVO();

        point.setAccessPointType(type);
        point.setCreatedBy("testuser4");
        point.setCreatedDate(LocalDateTime.now());
        point.setName("Licence");
        point.setUrl("/licence");
        data.add((AccessPointVO) accessPointRestController.save(point).getBody());

        point = new AccessPointVO();

        point.setAccessPointType(type);
        point.setCreatedBy("testuser4");
        point.setCreatedDate(LocalDateTime.now());
        point.setName("Licence Type");
        point.setUrl("/licence/type");
        data.add((AccessPointVO) accessPointRestController.save(point).getBody());

        point = new AccessPointVO();
        point.setAccessPointType(type);
        point.setCreatedBy("testuser4");
        point.setCreatedDate(LocalDateTime.now());
        point.setName("Licence Type Form");
        point.setUrl("/licence/type/form");
        data.add((AccessPointVO) accessPointRestController.save(point).getBody());

        point = new AccessPointVO();
        point.setAccessPointType(type);
        point.setCreatedBy("testuser4");
        point.setCreatedDate(LocalDateTime.now());
        point.setName("Licence Type Sector");
        point.setUrl("/licence/type/sector");
        data.add((AccessPointVO) accessPointRestController.save(point).getBody());

        point = new AccessPointVO();
        point.setAccessPointType(type);
        point.setCreatedBy("testuser4");
        point.setCreatedDate(LocalDateTime.now());
        point.setName("Licence Type Form Field");
        point.setUrl("/licence/type/form/field");
        data.add((AccessPointVO) accessPointRestController.save(point).getBody());

        return data;
    }
}
