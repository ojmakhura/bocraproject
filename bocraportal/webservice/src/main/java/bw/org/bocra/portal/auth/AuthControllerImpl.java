// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by SpringWSImpl.java.vsl in andromda-webservices.
//
package bw.org.bocra.portal.auth;

import java.util.Collection;
import java.util.Optional;

import org.keycloak.representations.idm.RoleRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication", description = "Managing authentications.")
@CrossOrigin()
public class AuthControllerImpl extends AuthControllerBase {

    public AuthControllerImpl() {
        super();
    }

    protected static Logger log = LoggerFactory.getLogger(AuthControllerImpl.class);

    @Override
    public ResponseEntity<?> handleGetAccessTokenString(String username, String password) {
        Optional<String> data = Optional.empty(); // TODO: Add custom code here;
        ResponseEntity<String> response;

        if(data.isPresent()) {
            response = ResponseEntity.ok().body(data.get());
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }

    @Override
    public ResponseEntity<?> handleGetRealmUrl() {
        Optional<String> data = Optional.empty(); // TODO: Add custom code here;
        ResponseEntity<String> response;

        if(data.isPresent()) {
            response = ResponseEntity.ok().body(data.get());
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }

    @Override
    public ResponseEntity<?> handleGetRoles() {

        Optional<Collection<RoleRepresentation>> data = Optional.empty(); // TODO: Add custom code here;
        ResponseEntity<Collection<RoleRepresentation>> response;

        if(data.isPresent()) {
            response = ResponseEntity.ok().body(data.get());
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }

    @Override
    public ResponseEntity<?> handleGetUserInfo(String token) {
        Optional<String> data = Optional.empty(); // TODO: Add custom code here;
        ResponseEntity<String> response;

        if(data.isPresent()) {
            response = ResponseEntity.ok().body(data.get());
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }

    @Override
    public ResponseEntity<?> handleSignin(String username, String password) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("username", username);
        map.add("password", password);
        map.add("client_id", getClientId());
        map.add("grant_type", getGrantType());
        map.add("client_secret", getClientSecret());
        map.add("scope", getScope());

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, new HttpHeaders());

        Optional<String> data = Optional.of(getRestTemplate().postForObject(getKeycloakTokenUri(), request, String.class)); // TODO: Add custom code here;
        ResponseEntity<String> response;

        if(data.isPresent()) {
            response = ResponseEntity.ok().body(data.get());
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }

    @Override
    public void handleSignout(String refreshToken) {
        // TODO: Add custom code here;

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("client_id", getClientId());
        map.add("client_secret", getClientSecret());
        map.add("refresh_token", refreshToken);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, null);
        getRestTemplate().postForObject(getKeycloakLogout(), request, String.class);
    }
}