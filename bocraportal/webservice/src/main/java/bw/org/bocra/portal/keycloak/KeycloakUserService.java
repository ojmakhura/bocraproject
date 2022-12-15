package bw.org.bocra.portal.keycloak;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.Response.StatusType;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.RolesResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.ClientMappingsRepresentation;
import org.keycloak.representations.idm.ClientRepresentation;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import bw.org.bocra.portal.licensee.LicenseeService;
import bw.org.bocra.portal.licensee.LicenseeVO;
import bw.org.bocra.portal.user.LicenseeUserService;
import bw.org.bocra.portal.user.UserVO;

@Component
public class KeycloakUserService {

    protected Logger logger = LoggerFactory.getLogger(KeycloakUserService.class);

    private final LicenseeService licenseeService;
    private final KeycloakService keycloakService;

    public KeycloakUserService(LicenseeUserService licenseeUserService, LicenseeService licenseeService,
            KeycloakService keycloakService) {
        this.licenseeService = licenseeService;
        this.keycloakService = keycloakService;
    }

    private CredentialRepresentation createCredential(String type, String value, Boolean temporary) {
        CredentialRepresentation cred = new CredentialRepresentation();

        cred.setType(type);
        cred.setValue(value);
        cred.setTemporary(temporary);

        return cred;
    }

    private ClientRepresentation findAuthenticatedClientResource() {
        for (ClientRepresentation clientRep : keycloakService.getClientsResource().findAll()) {
            if (clientRep.getClientId().equals(keycloakService.getAuthClient())) {
                return clientRep;
            }
        }

        return null;
    }

    public UserVO getLoggedInUser() {

        String userId = keycloakService.getSecurityContext().getToken().getSubject();
    
        UsersResource usersResource = keycloakService.getUsersResource();
        UserRepresentation userRep = usersResource.get(userId).toRepresentation();

        return userRepresentationUserVO(userRep);
    }

    private UserRepresentation userVOUserRepresentation(UserVO user) {

        UserRepresentation userRepresentation = new UserRepresentation();

        userRepresentation.setUsername(user.getUsername());
        userRepresentation.setEmail(user.getEmail());
        userRepresentation.setFirstName(user.getFirstName());
        userRepresentation.setLastName(user.getLastName());
        userRepresentation.setEnabled(user.getEnabled());
        userRepresentation.setEmailVerified(false);
        userRepresentation.setRequiredActions(Collections.singletonList("VERIFY_EMAIL"));
        userRepresentation.setCredentials(Collections
                .singletonList(createCredential(CredentialRepresentation.PASSWORD, user.getPassword(), true)));

        if (user.getLicensee() != null && user.getLicensee().getId() != null) {
            Map<String, List<String>> attributes = new HashMap<>();
            attributes.put("licenseeId", Arrays.asList(user.getLicensee().getId().toString()));
            attributes.put("licenseeName", Arrays.asList(user.getLicensee().getLicenseeName()));

            userRepresentation.setAttributes(attributes);
        }

        if (CollectionUtils.isNotEmpty(user.getRoles())) {

            Map<String, List<String>> roles = new HashMap<>();
            roles.put(findAuthenticatedClientResource().getId(), (List<String>) user.getRoles());
            userRepresentation.setClientRoles(roles);
        }

        return userRepresentation;
    }

    private UserVO userRepresentationUserVO(UserRepresentation userRepresentation) {
        UserVO user = new UserVO();

        user.setUserId(userRepresentation.getId());
        user.setEmail(userRepresentation.getEmail());
        user.setEnabled(userRepresentation.isEnabled());
        user.setFirstName(userRepresentation.getFirstName());
        user.setUsername(userRepresentation.getUsername());
        user.setLastName(userRepresentation.getLastName());
        user.setRoles(new ArrayList<>());

        if (userRepresentation.getAttributes() != null && !userRepresentation.getAttributes().isEmpty()) {
            List<String> licenseeIds = userRepresentation.getAttributes().get("licenseeId");
            List<String> licenseeNames = userRepresentation.getAttributes().get("licenseeName");

            LicenseeVO licensee = new LicenseeVO();
            if (CollectionUtils.isNotEmpty(licenseeIds))
                licensee.setId(Long.parseLong(licenseeIds.get(0)));

            if (CollectionUtils.isNotEmpty(licenseeNames))
                licensee.setLicenseeName(licenseeNames.get(0));

            user.setLicensee(licensee);
        }

        RealmResource realmResource = keycloakService.getRealmResource();
        UserResource userResource = realmResource.users().get(userRepresentation.getId());

        Collection<ClientRepresentation> cReps = keycloakService.getClientsResource()
                                                    .findByClientId(keycloakService.getAuthClient());

        if(CollectionUtils.isNotEmpty(cReps)) {

            ClientRepresentation clientRepresentation = keycloakService.getClientsResource()
                    .findByClientId(keycloakService.getAuthClient()).get(0);
    
            for (RoleRepresentation roleRep : userResource.roles().clientLevel(clientRepresentation.getId()).listAll()) {
                user.getRoles().add(roleRep.getName());
            }
        }

        return user;
    }

    public Collection<UserVO> getLicenseeUsers(Long id) {

        List<UserRepresentation> userRep = keycloakService.getUsersResource().searchByAttributes("licenseeId:" + id);

        Collection<UserVO> users = new ArrayList<>();

        for (UserRepresentation user : userRep) {
            UserVO vo = this.userRepresentationUserVO(user);
            users.add(vo);
        }

        return users;
    }

    public boolean updateUserPassword(String userId, String newPassword) {

        if(StringUtils.isNotBlank(userId)) {
            userId = keycloakService.getSecurityContext().getToken().getSubject();
        }
        
        UsersResource usersResource = keycloakService.getUsersResource();
        UserResource userResource = usersResource.get(userId);
        CredentialRepresentation credential = createCredential(CredentialRepresentation.PASSWORD, newPassword, false);
        userResource.resetPassword(credential);

        return true;
    }

    public void updateUser(UserVO user) {

        if(StringUtils.isNotBlank(user.getUserId())) {
            UsersResource usersResource = keycloakService.getUsersResource();
            UserResource userResource = usersResource.get(user.getUserId());

            UserRepresentation userRep = userResource.toRepresentation();
            userRep.setEmail(user.getEmail());
            userRep.setFirstName(user.getFirstName());
            userRep.setLastName(user.getLastName());
            userRep.setEnabled(user.getEnabled());

            userResource.update(userRep);
        }
    }
    
    public static String getCreatedId(Response response) {
        URI location = response.getLocation();
        //if (!response.getStatusInfo().equals(Status.CREATED)) {
        if (response.getStatus() != HttpStatus.CREATED.value()) {
            StatusType statusInfo = response.getStatusInfo();
            response.bufferEntity();
            String body = response.readEntity(String.class);
            throw new WebApplicationException("Create method returned status "
                    + statusInfo.getReasonPhrase() + " (Code: " + statusInfo.getStatusCode() + "); expected status: Created (201). Response body: " + body, response);
        }

        if (location == null) {
            return null;
        }

        String path = location.getPath();
        return path.substring(path.lastIndexOf('/') + 1);
    }

    public ResponseEntity<?> createUser(UserVO user) {

        UsersResource usersResource = keycloakService.getUsersResource();
        UserRepresentation userRepresentation = this.userVOUserRepresentation(user);
        UserResource userResource = null;

        if (StringUtils.isBlank(user.getUserId())) {

            Response res = usersResource.create(userRepresentation);

            if(res.getStatus() != HttpStatus.CREATED.value()) {
                return ResponseEntity.status(res.getStatus()).body(getCreatedId(res));
            }

            userResource = usersResource.get(getCreatedId(res));
            user.setUserId(getCreatedId(res));
        } else {
            userResource = usersResource.get(user.getUserId());
        }

        if (userResource != null) {

            List<RoleRepresentation> roleReps = new ArrayList<>();

            for (String role : user.getRoles()) {
                RolesResource rolesResource = keycloakService
                        .getClientRolesResource(findAuthenticatedClientResource().getId());

                RoleRepresentation roleRep = rolesResource.get(role).toRepresentation();
                if (StringUtils.isNotBlank(roleRep.getId())) {
                    roleReps.add(roleRep);
                }
            }

            if (CollectionUtils.isNotEmpty(roleReps)) {
                userResource.roles().clientLevel(findAuthenticatedClientResource().getId()).add(roleReps);
            }

        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("User not created!");
        }

        return ResponseEntity.ok(user);
    }

    public Collection<UserVO> loadUsers() {

        UsersResource usersResource = keycloakService.getUsersResource();

        List<UserRepresentation> userRep = usersResource.list();
        Collection<UserVO> users = new ArrayList<>();

        for (UserRepresentation user : userRep) {
            UserVO vo = this.userRepresentationUserVO(user);
            users.add(vo);
        }

        return users;
    }

    public UserVO updateUserName(String username, String userId) {

        return null;
    }

    private List<UserVO> userRepsToVOs(List<UserRepresentation> usersRep) {

        if (CollectionUtils.isEmpty(usersRep)) {
            return null;
        }

        List<UserVO> users = new ArrayList<>();

        for (UserRepresentation rep : usersRep) {
            users.add(userRepresentationUserVO(rep));
        }

        return users;
    }

    public List<UserVO> searchByAttributes(String criteria) {
        List<UserRepresentation> usersRep = keycloakService.getUsersResource().searchByAttributes(criteria);

        return this.userRepsToVOs(usersRep);
    }

    public List<UserVO> search(String criteria) {

        List<UserRepresentation> usersRep = keycloakService.getUsersResource().search(criteria);

        return this.userRepsToVOs(usersRep);
    }

    public UserVO addClientRoles(String clientId, Set<String> roles, String userId) {
        List<RoleRepresentation> roleReps = new ArrayList<>();
        UserResource userResource = keycloakService.getUsersResource().get(userId);
        RolesResource rolesResource = keycloakService.getClientRolesResource(clientId);
        UserRepresentation rep = userResource.toRepresentation();

        if (StringUtils.isBlank(rep.getId())) {
            return null;
        }

        for (String role : roles) {

            RoleRepresentation roleRep = rolesResource.get(role).toRepresentation();
            if (StringUtils.isNotBlank(roleRep.getId())) {
                roleReps.add(roleRep);
            }
        }

        if (CollectionUtils.isNotEmpty(roleReps)) {
            userResource.roles().clientLevel(clientId).add(roleReps);
        }

        return userRepresentationUserVO(rep);
    }

    public UserVO findUserById(String userId) {
        UserRepresentation rep = keycloakService.getUsersResource().get(userId).toRepresentation();

        if (StringUtils.isNotBlank(rep.getId())) {
            return userRepresentationUserVO(rep);
        }

        return null;
    }
}
