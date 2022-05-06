// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by SpringWSImpl.java.vsl in andromda-webservices.
//
package bw.org.bocra.portal.user;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.ws.rs.core.Response;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.RefreshableKeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.client.KeycloakRestTemplate;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("user")
@CrossOrigin()
public class UserRestControllerImpl extends UserRestControllerBase {

    protected static Logger log = LoggerFactory.getLogger(UserRestControllerImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    @Value("${keycloak.realm}")
    private String realm;

    @Value("${keycloak.auth-server-url}")
    private String authServerUrl;

    @Value("${keycloak.resource}")
    private String clientId;

    private Keycloak getKeycloak(String realm, String authUrl) {
        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //RefreshableKeycloakSecurityContext context = (RefreshableKeycloakSecurityContext) authentication.getCredentials();
        Keycloak keycloak = Keycloak.getInstance(authUrl, realm, clientId, realm);

        return keycloak;
    }

    private CredentialRepresentation createCredential(String type, String value, Boolean temporary) {
        CredentialRepresentation cred = new CredentialRepresentation();

        cred.setType(type);
        cred.setValue(value);
        cred.setTemporary(temporary);

        return cred;
    }

    private RefreshableKeycloakSecurityContext getSecurityContext() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (RefreshableKeycloakSecurityContext) authentication.getCredentials();
    }

    private UserRepresentation getLoggedInUser() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.getPrincipal());
        System.out.println(authentication.getCredentials());
        System.out.println(authentication.getDetails());

        return null;
    }

    private UsersResource getUsersResource() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        RefreshableKeycloakSecurityContext context = (RefreshableKeycloakSecurityContext) authentication.getCredentials();

        Keycloak keycloak = getKeycloak(context.getTokenString(), context.getDeployment().getAuthServerBaseUrl());
        return keycloak.realm(context.getRealm()).users();
    }

    @Override
    public ResponseEntity<UserVO> handleCreateUser(UserVO user) {
        
        UsersResource usersResource = getUsersResource();
        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setUsername(user.getUsername());
        userRepresentation.setEmail(user.getEmail());
        userRepresentation.setFirstName(user.getFirstName());
        userRepresentation.setLastName(user.getLastName());
        userRepresentation.setEnabled(true);
        userRepresentation.setEmailVerified(false);
        userRepresentation.setRequiredActions(Collections.singletonList("VERIFY_EMAIL"));
        userRepresentation.setCredentials(Collections.singletonList(createCredential(CredentialRepresentation.PASSWORD, user.getPassword(), true)));
        
        Response res = usersResource.create(userRepresentation);
        
        System.out.println(res.getEntity());
        System.out.println(res.getStatus());
        List<UserRepresentation> users = usersResource.search(user.getUsername(), true);
        if(CollectionUtils.isNotEmpty(users)) {
    
            UserRepresentation rep = users.get(0);
            if(StringUtils.isNotBlank(rep.getId())) {
                user.setUserId(rep.getId());
                LicenseeUserVO licenseeUser = new LicenseeUserVO();
                licenseeUser.setDateAdded(LocalDate.now());
                licenseeUser.setUser(user);
                licenseeUser.setLicensee(user.getLicensee());
                licenseeUser = licenseeUserService.save(licenseeUser);
            }
        }
        
        Optional<UserVO> data = Optional.empty();
        ResponseEntity<UserVO> response;

        if(data.isPresent()) {
            response = ResponseEntity.status(HttpStatus.OK).body(data.get());
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }

    @Override
    public ResponseEntity<Collection<UserVO>> handleLoadUsers() {
        
        UsersResource usersResource = getUsersResource();
        //usersResource.
        Optional<Collection<UserVO>> data = Optional.empty(); // TODO: Add custom code here;
        ResponseEntity<Collection<UserVO>> response;

        if(data.isPresent()) {
            response = ResponseEntity.status(HttpStatus.OK).body(data.get());
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }

    @Override
    public ResponseEntity<Boolean> handleUpdateUserName(String username, String userId) {
        Optional<Boolean> data = Optional.empty(); // TODO: Add custom code here;
        ResponseEntity<Boolean> response;

        if(data.isPresent()) {
            response = ResponseEntity.status(HttpStatus.OK).body(data.get());
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }
}