// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by SpringWSImpl.java.vsl in andromda-webservices.
//
package bw.org.bocra.portal.user;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bw.org.bocra.portal.keycloak.KeycloakUserService;
import bw.org.bocra.portal.licensee.LicenseeService;
import io.swagger.v3.oas.annotations.tags.Tag;
@RestController
@RequestMapping("user")
@Tag(name = "User", description = "Managing users.")
@CrossOrigin()
public class UserRestControllerImpl extends UserRestControllerBase {

    protected Logger logger = LoggerFactory.getLogger(UserRestControllerImpl.class);
    private final KeycloakUserService keycloakUserService;

    public UserRestControllerImpl(LicenseeUserService licenseeUserService, LicenseeService licenseeService, KeycloakUserService keycloakUserService) {
        super(licenseeUserService, licenseeService);
        this.keycloakUserService = keycloakUserService;
    }

    @Override
    public ResponseEntity<?> handleCreateUser(UserVO user) {
        try{
            user = this.keycloakUserService.createUser(user);

            if(user == null || StringUtils.isBlank(user.getUserId())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
    
            return ResponseEntity.status(HttpStatus.OK).body(user);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> handleLoadUsers() {
        try{
            Collection<UserVO> users = this.keycloakUserService.loadUsers();

            Optional<Collection<UserVO>> data = CollectionUtils.isEmpty(users) ? Optional.empty() : Optional.of(users);
            ResponseEntity<Collection<UserVO>> response;
    
            if (data.isPresent()) {
                response = ResponseEntity.status(HttpStatus.OK).body(data.get());
            } else {
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
    
            return response;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> handleUpdateUserName(String username, String userId) {
        try{
            Optional<Boolean> data = Optional.empty(); // TODO: Add custom code here;
            ResponseEntity<Boolean> response;
    
            if (data.isPresent()) {
                response = ResponseEntity.status(HttpStatus.OK).body(data.get());
            } else {
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
    
            return response;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> handleSearch(String criteria) {
        try{

        List<UserVO> users = this.keycloakUserService.search(criteria);

        return ResponseEntity.ok(users);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> handleAddClientRoles(String clientId, Set<String> roles, String userId) {
        try{
            UserVO rep = this.keycloakUserService.addClientRoles(clientId, roles, userId);

            if(rep == null || StringUtils.isBlank(rep.getUserId())) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("User with userId %s does not exist.", clientId));
            }
    
            return ResponseEntity.ok(rep);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
 
    }

    @Override
    public ResponseEntity<?> handleFindUserById(String userId) {
        try{
            UserVO rep = this.keycloakUserService.findUserById(userId);

            if (rep != null) {
                return ResponseEntity.ok(rep);
            }
    
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}