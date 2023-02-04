package bw.org.bocra.portal.keycloak.realm;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RealmRepository extends JpaRepository<Realm, String> {
    
    List<Realm> findByName(String name);
}
