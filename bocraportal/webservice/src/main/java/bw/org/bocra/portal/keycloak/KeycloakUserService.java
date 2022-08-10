package bw.org.bocra.portal.keycloak;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.ws.rs.core.Response;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.RolesResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.ClientRepresentation;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import bw.org.bocra.portal.licensee.LicenseeService;
import bw.org.bocra.portal.licensee.LicenseeVO;
import bw.org.bocra.portal.user.LicenseeUserService;
import bw.org.bocra.portal.user.LicenseeUserVO;
import bw.org.bocra.portal.user.UserVO;

@Component
public class KeycloakUserService {

    protected Logger logger = LoggerFactory.getLogger(KeycloakUserService.class);

    private final LicenseeService licenseeService;
    private final KeycloakService keycloakService;

    public KeycloakUserService(LicenseeUserService licenseeUserService, LicenseeService licenseeService, KeycloakService keycloakService) {
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
            List<String> licenseeAttributes = new ArrayList<>();
            licenseeAttributes.add(user.getLicensee().getId().toString());
            licenseeAttributes.add(user.getLicensee().getLicenseeName());
            attributes.put("licensee", licenseeAttributes);

            userRepresentation.setAttributes(attributes);
        }

        if (CollectionUtils.isNotEmpty(user.getRoles())) {

            Map<String, List<String>> roles = new HashMap<>();
            roles.put(user.getClient(), (List<String>) user.getRoles());
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
            List<String> licenseeAttributes = userRepresentation.getAttributes().get("licensee");

            if (CollectionUtils.isNotEmpty(licenseeAttributes)) {
                LicenseeVO licensee = licenseeService.findById(Long.parseLong(licenseeAttributes.get(0)));

                // The licensee with that ID does not exist (maybe deleted)
                // So we update the user
                if (licensee == null || licensee.getId() == null) {
                    UsersResource usersResource = keycloakService.getUsersResource();
                    UserResource userResource = usersResource.get(userRepresentation.getId());
                    userRepresentation.getAttributes().remove("licensee");
                    userResource.update(userRepresentation);
                }

                user.setLicensee(licensee);
            }
        }

        RealmResource realmResource = keycloakService.getRealmResource();
        UserResource userResource = realmResource.users().get(userRepresentation.getId());

        ClientRepresentation clientRepresentation = keycloakService.getClientsResource().findByClientId(keycloakService.getAuthClient()).get(0);

        for (RoleRepresentation roleRep : userResource.roles().clientLevel(clientRepresentation.getId()).listAll()) {
            user.getRoles().add(roleRep.getName());
        }

        return user;
    }

    public Collection<UserVO> getLicenseeUsers(Long id) {

        List<UserRepresentation> userRep = keycloakService.getUsersResource().searchByAttributes("licensee:" + id);

        Collection<UserVO> users = new ArrayList<>();

        for (UserRepresentation user : userRep) {
            UserVO vo = this.userRepresentationUserVO(user);
            users.add(vo);
        }

        return users;
    }

    public UserVO createUser( UserVO user) {

        if (StringUtils.isBlank(user.getClient())) {
            user.setClient(findAuthenticatedClientResource().getId());
        }

        UsersResource usersResource = keycloakService.getUsersResource();
        UserRepresentation userRepresentation = this.userVOUserRepresentation(user);

        Response res = usersResource.create(userRepresentation);

        List<UserRepresentation> users = usersResource.search(user.getUsername(), user.getFirstName(),
                user.getLastName(), user.getEmail(), 0, 1);
        if (CollectionUtils.isNotEmpty(users)) {

            UserRepresentation rep = users.get(0);
            UserResource userResource = usersResource.get(rep.getId());

            if (StringUtils.isNotBlank(rep.getId())) {
                user.setUserId(rep.getId());
                LicenseeUserVO licenseeUser = new LicenseeUserVO();
                licenseeUser.setDateAdded(LocalDate.now());
                licenseeUser.setUser(user);
                licenseeUser.setLicensee(user.getLicensee());
                // licenseeUser = licenseeUserService.save(licenseeUser);
            }

            List<RoleRepresentation> roleReps = new ArrayList<>();

            for (String role : user.getRoles()) {
                RolesResource rolesResource = keycloakService.getClientRolesResource(user.getClient());

                RoleRepresentation roleRep = rolesResource.get(role).toRepresentation();
                if (StringUtils.isNotBlank(roleRep.getId())) {
                    roleReps.add(roleRep);
                }
            }

            if (CollectionUtils.isNotEmpty(roleReps)) {
                userResource.roles().clientLevel(user.getClient()).add(roleReps);
            }

        } else {
            return null;
        }

        return user;
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

    public List<UserVO> search(String criteria) {

        List<UserRepresentation> usersRep = keycloakService.getUsersResource().search(criteria, false);

        if (CollectionUtils.isEmpty(usersRep)) {
            return null;
        }

        List<UserVO> users = new ArrayList<>();

        for (UserRepresentation rep : usersRep) {
            users.add(userRepresentationUserVO(rep));
        }

        return users;
    }

    public UserVO addClientRoles(String clientId, Set<String> roles, String userId) {
        List<RoleRepresentation> roleReps = new ArrayList<>();
        UserResource userResource = keycloakService.getUsersResource().get(userId);
        RolesResource rolesResource = keycloakService.getClientRolesResource(clientId);
        UserRepresentation rep = userResource.toRepresentation();

        if(StringUtils.isBlank(rep.getId())) {
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
