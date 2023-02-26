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

    public UserRestControllerImpl(LicenseeService licenseeService, KeycloakUserService keycloakUserService) {
        super(licenseeService);
        this.keycloakUserService = keycloakUserService;
    }

    @Override
    public ResponseEntity<?> handleSaveUser(UserVO user) {
        try{
            logger.debug("Save User "+user);

            if(StringUtils.isBlank(user.getUserId()))
                return this.keycloakUserService.createUser(user);
            else {
                keycloakUserService.updateUser(user);
            }
                
            if(user == null || StringUtils.isBlank(user.getUserId())) {
                return ResponseEntity.badRequest().body("The user could not be saved.");
            }
    
            return ResponseEntity.ok().body(user);
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            return ResponseEntity.badRequest().body("An unknown error has occured. Please contact the portal administrator.");
        }
    }

    @Override
    public ResponseEntity<?> handleLoadUsers() {
        try{
            logger.debug("Load Users ");
            return ResponseEntity.ok().body(this.keycloakUserService.loadUsers());
            
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().body("An unknown error has occured. Please contact the portal administrator.");
        }
    }

    @Override
    public ResponseEntity<?> handleUpdateUserName(String username, String userId) {
        try{
            logger.debug("Update the user's Username with username "+username+"  and user Id"+userId);
            Optional<Boolean> data = Optional.empty(); // TODO: Add custom code here;
            ResponseEntity<Boolean> response;
    
            if (data.isPresent()) {
                response = ResponseEntity.ok().body(data.get());
            } else {
                response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
    
            return response;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().body("An unknown error has occured. Please contact the portal administrator.");
        }
    }

    @Override
    public ResponseEntity<?> handleSearch(String criteria) {
        try{
            logger.debug("Search user by criteria"+criteria);
        List<UserVO> users = this.keycloakUserService.search(criteria);

        return ResponseEntity.ok(users);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().body("An unknown error has occured. Please contact the portal administrator.");
        }
    }

    @Override
    public ResponseEntity<?> handleAddClientRoles(String clientId, Set<String> roles, String userId) {
        try{
            logger.debug("Add user/client roles with client Id "+clientId+",roles "+roles+" and user Id "+userId);
            UserVO rep = this.keycloakUserService.addClientRoles(clientId, roles, userId);

            if(rep == null || StringUtils.isBlank(rep.getUserId())) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("User with userId %s does not exist.", clientId));
            }
    
            return ResponseEntity.ok(rep);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().body("An unknown error has occured. Please contact the portal administrator.");
        }
 
    }

    @Override
    public ResponseEntity<?> handleFindUserById(String userId) {
        try{
            logger.debug("Search user by Id "+userId);
            UserVO rep = this.keycloakUserService.findUserById(userId);

            if (rep != null) {
                return ResponseEntity.ok(rep);
            }
    
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().body("An unknown error has occured. Please contact the portal administrator.");
        }
    }

    @Override
    public ResponseEntity<?> handleChangePassword(String userId, String newPassword) {
        try{
            logger.debug("Change User password with user id" + userId + " and new password " + newPassword);
            this.keycloakUserService.updateUserPassword(userId, newPassword);

            return ResponseEntity.ok().body("User password updated.");
    
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().body("User password not updated.");
        }
    }

    @Override
    public ResponseEntity<?> handleFindByLicenseeId(Long licenseeId) {
        try{
            logger.debug("Search user by licensee id");
            String criteria = "licenseeId:" + licenseeId;
            List<UserVO> users = this.keycloakUserService.searchByAttributes(criteria);

            return ResponseEntity.ok(users);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().body("An unknown error has occured. Please contact the portal administrator.");
        }
    }

    @Override
    public ResponseEntity<?> handleFindByLicenseeName(String licenseeName) {
        try{
            logger.debug("Search user by licensee name.");
            String criteria = "licenseeName:" + licenseeName;
            List<UserVO> users = this.keycloakUserService.searchByAttributes(criteria);

            return ResponseEntity.ok(users);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().body("An unknown error has occured. Please contact the portal administrator.");
        }
    }

    @Override
    public ResponseEntity<?> handleFindByRealmRoles(Set<String> roles) {
        try{
            logger.debug("Search user by licensee name.");
            Collection<UserVO> users = this.keycloakUserService.getUsersByRoles(roles);

            return ResponseEntity.ok(users);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().body("An unknown error has occured. Please contact the portal administrator.");
        }
    }

    @Override
    public ResponseEntity<?> handleFindByClientRoles(Set<String> roles, String client) {
        try{
            logger.debug("Search user by licensee name.");
            Collection<UserVO> users = this.keycloakUserService.getUsersByRoles(client, roles);

            return ResponseEntity.ok(users);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().body("An unknown error has occured. Please contact the portal administrator.");
        }
    }
}