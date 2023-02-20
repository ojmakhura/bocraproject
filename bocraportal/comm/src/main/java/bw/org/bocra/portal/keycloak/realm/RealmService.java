package bw.org.bocra.portal.keycloak.realm;

import java.util.Collection;

import org.springframework.stereotype.Service;

@Service
public class RealmService {
    
    private final RealmRepository realmRepository;

    public RealmService(RealmRepository realmRepository) {
        this.realmRepository = realmRepository;
    }

    public Collection<Realm> getAll() {
        return realmRepository.findAll();
    }

    public Realm save(Realm realm) {
        return realmRepository.save(realm);
    }

    public Realm findById(String realmId) {
        return realmRepository.getReferenceById(realmId);
    }

    public Collection<Realm> searchByName(String name) {
        return realmRepository.findByName(name);
    }
}
