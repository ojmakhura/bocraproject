package bw.org.bocra.portal.user;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import bw.org.bocra.portal.GenericTestData;
import bw.org.bocra.portal.user.UserTestData;
import bw.org.bocra.portal.user.UserVO;

@Component
@Profile("test")
public class UserTestData extends GenericTestData<UserVO, LicenseeUserRepository, UserCriteria, UserRestController> {

    // private final UserRestController UserRestController;
    private final UserTestData userTestData;
    private final LicenseeUserService licenseeUserService;
    // private final UserRepository UserRepository;

    public UserTestData(UserRestController UserRestController, UserTestData userTestData, LicenseeUserRepository licenseeUserRepository, LicenseeUserService licenseeUserService) {
        super(licenseeUserRepository, UserRestController);
        this.userTestData = userTestData;
        this.licenseeUserService = licenseeUserService;
    }

    @Override
    public  UserVO createUnsavedData() {
        UserVO user = new UserVO();

        user.setEmail("test@gmail");
        user.setEnabled(true);
        user.setFirstName("User");
        user.setLastName("Test");
        user.setLicensee(user.getLicensee());
        user.setPassword("Test@123");
        user.setRoles(user.getRoles());
        user.setUserId("test123");
        user.setUsername("UserTest");

        return user;
    }

    public Collection<UserVO> generateSequentialData(int size) {

        return generateUnsavedSequentialData(size)
            .stream()
            .map(user -> licenseeUserService.save(user))
            .collect(Collectors.toList());

    }

    @Override
    public Collection<UserVO> generateUnsavedSequentialData(int size) {
        Collection<UserVO> users = new ArrayList<>();

        for (int i = 1; i <= size; i++) {

            UserVO user = new UserVO();

            user.setEmail("test@gmail");
            user.setEnabled(true);
            user.setFirstName("User");
            user.setLastName("Test");
            user.setLicensee(user.getLicensee());
            // user.setPassword("Test@123");
            // user.setRoles(user.getRoles());
            user.setUserId("test123");
            // user.setUsername("UserTest");

            users.add(user);
            
        }

        return users;
    }
    
    @Override
    public Collection<UserVO> searchData() {

        Collection<UserVO> data = new ArrayList<>();
        Collection<LicenseeUserVO> users = new ArrayList<>();

        LicenseeUserVO user = new LicenseeUserVO();

        user.setUser(user.getUser());
        user.setLicensee(user.getLicensee());
        users.add(licenseeUserService.save(user));

        user = new LicenseeUserVO();

        user.setUser(user.getUser());
        user.setLicensee(user.getLicensee());
        users.add(licenseeUserService.save(user));

        user = new LicenseeUserVO();

        user.setUser(user.getUser());
        user.setLicensee(user.getLicensee());
        users.add(licenseeUserService.save(user));

        user = new LicenseeUserVO();

        user.setUser(user.getUser());
        user.setLicensee(user.getLicensee());
        users.add(licenseeUserService.save(user));

        user = new LicenseeUserVO();

        user.setUser(user.getUser());
        user.setLicensee(user.getLicensee());
        users.add(licenseeUserService.save(user));

        user = new LicenseeUserVO();

        user.setUser(user.getUser());
        user.setLicensee(user.getLicensee());
        users.add(licenseeUserService.save(user));

        user = new LicenseeUserVO();

        user.setUser(user.getUser());
        user.setLicensee(user.getLicensee());
        users.add(licenseeUserService.save(user));

        user = new LicenseeUserVO();

        user.setUser(user.getUser());
        user.setLicensee(user.getLicensee());
        users.add(licenseeUserService.save(user));

        user = new LicenseeUserVO();
        
        user.setUser(user.getUser());
        user.setLicensee(user.getLicensee());
        users.add(licenseeUserService.save(user));

        user = new LicenseeUserVO();
        
        user.setUser(user.getUser());
        user.setLicensee(user.getLicensee());
        users.add(licenseeUserService.save(user));

        user = new LicenseeUserVO();
        
        user.setUser(user.getUser());
        user.setLicensee(user.getLicensee());
        users.add(licenseeUserService.save(user));

        return data;
    }

    @Override
    public UserCriteria searchCriteria() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public UserCriteria searchCriteriaEmpty() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public UserCriteria searchCriteriaNone() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Class<UserVO> getDataClass() {
        // TODO Auto-generated method stub
        return UserVO.class;
    }
}
