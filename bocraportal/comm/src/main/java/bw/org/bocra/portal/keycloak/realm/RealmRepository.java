package bw.org.bocra.portal.keycloak.realm;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RealmRepository extends JpaRepository<Realm, String> {
    
    List<Realm> findByName(String name);
}
