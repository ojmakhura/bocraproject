package bw.org.bocra.portal.auth;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import bw.org.bocra.portal.GenericTestData;
import bw.org.bocra.portal.access.AccessPointTestData;
import bw.org.bocra.portal.access.AccessPointVO;
import bw.org.bocra.portal.access.type.AccessPointTypeTestData;
import bw.org.bocra.portal.access.type.AccessPointTypeVO;

@Component
@Profile("test")
public class AuthorisationTestData extends GenericTestData<AuthorisationVO, AuthorisationRepository, AuthorisationCriteria, AuthorisationRestController> {

    // private final AuthorisationRestController authorisationRestController;

    @Autowired
    private AccessPointTestData accessPointTestData;

    @Autowired
    private AccessPointTypeTestData accessPointTypeTestData;

    @Autowired
    private final AuthorisationService authorisationService;

    public AuthorisationTestData(AuthorisationRepository repository, AuthorisationRestController restController, AuthorisationService authorisationService) {
        super(repository, restController);
        this.authorisationService = authorisationService;
        //TODO Auto-generated constructor stub
    }

    @Override
    public AuthorisationVO createUnsavedData() {
        AccessPointVO point = accessPointTestData.generateSequentialData(1).iterator().next();
        AuthorisationVO auth = new AuthorisationVO();

        auth.setAccessPoint(point);
        auth.setCreatedBy("testuser4");
        auth.setCreatedDate(LocalDateTime.now());

        return auth;
    }

    public AuthorisationVO createUnsavedAuthorisationNoAccess() {

        AuthorisationVO auth = new AuthorisationVO();

        auth.setCreatedBy("testuser4");
        auth.setCreatedDate(LocalDateTime.now());

        return auth;
    }

    public AuthorisationVO createUnsavedAuthorisationUnsavedAccess() {
        AccessPointVO point = accessPointTestData.createUnsavedData();
        AuthorisationVO auth = new AuthorisationVO();

        auth.setAccessPoint(point);
        auth.setCreatedBy("testuser4");
        auth.setCreatedDate(LocalDateTime.now());

        return auth;
    }

    public Collection<AuthorisationVO> generateSequentialData(int size) {

        Collection<AuthorisationVO> auths = new ArrayList<>();
        Collection<AccessPointVO> access = accessPointTestData.generateSequentialData(size);
        Iterator<AccessPointVO> iterator = access.iterator();

        for (int i = 1; i <= size / 2; i++) {

            AccessPointVO point = iterator.next();
            AuthorisationVO auth = new AuthorisationVO();

            auth.setAccessPoint(point);
            auth.setCreatedBy("testuser4");
            auth.setCreatedDate(LocalDateTime.now());

            auths.add(authorisationService.save(auth));
        }

        return auths;
    }

    public Collection<AuthorisationVO> generateUnsavedSequentialData(int size) {
        Collection<AuthorisationVO> auths = new ArrayList<>();
        Collection<AccessPointVO> access = accessPointTestData.generateSequentialData(size);
        Iterator<AccessPointVO> iterator = access.iterator();

        for (int i = 1; i <= size / 2; i++) {

            AccessPointVO point = iterator.next();
            AuthorisationVO auth = new AuthorisationVO();

            auth.setAccessPoint(point);
            auth.setCreatedBy("testuser4");
            auth.setCreatedDate(LocalDateTime.now());

            auths.add(auth);
        }

        return auths;
    }

    @Override
    public Collection<AuthorisationVO> searchData() {
        Collection<AuthorisationVO> auths = new ArrayList<>();

        AccessPointTypeVO menuType = new AccessPointTypeVO();
        menuType.setCode("MENU");
        menuType.setName("Menu");

        menuType = (AccessPointTypeVO) accessPointTypeTestData.getRestController().save(menuType).getBody();

        AccessPointTypeVO apiType = new AccessPointTypeVO();
        apiType.setCode("API");
        apiType.setName("Api");

        apiType = (AccessPointTypeVO) accessPointTypeTestData.getRestController().save(apiType).getBody();

        for (int i = 1; i <= 4; i++){
            AccessPointVO ap = new AccessPointVO();
            ap.setAccessPointType(menuType);
            ap.setName("Menu " + i);
            ap.setUrl("/menu" + i);
            ap.setCreatedBy("path");
            ap.setCreatedDate(LocalDateTime.now());

            try {
                ap = (AccessPointVO) accessPointTestData.create(ap);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
            AuthorisationVO ath = new AuthorisationVO();
            ath.setAccessPoint(ap);
            ath.setCreatedBy("path");
            ath.setCreatedDate(LocalDateTime.now());
            // ath = (AuthorisationVO) authorisationRestController.save(ath).getBody();

            auths.add(ath);
        }

        for (int i = 1; i <= 3; i++){
            
            AccessPointVO ap = new AccessPointVO();
            ap.setAccessPointType(menuType);
            ap.setName("Api " + i);
            ap.setUrl("/api" + i);
            ap.setCreatedBy("path");
            ap.setCreatedDate(LocalDateTime.now());

            try {
                ap = (AccessPointVO) accessPointTestData.create(ap);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
            AuthorisationVO ath = new AuthorisationVO();
            ath.setAccessPoint(ap);
            ath.setCreatedBy("path");
            ath.setCreatedDate(LocalDateTime.now());

            // auths.add((AuthorisationVO) authorisationRestController.save(ath));
        }
        
        return auths;
    }

    @Override
    public AuthorisationCriteria searchCriteria() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public AuthorisationCriteria searchCriteriaEmpty() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public AuthorisationCriteria searchCriteriaNone() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Class<AuthorisationVO> getDataClass() {
        // TODO Auto-generated method stub
        return AuthorisationVO.class;
    }
}
