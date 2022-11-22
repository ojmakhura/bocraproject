package bw.org.bocra.portal.smtp;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RealmSmtpConfigRepository extends JpaRepository<RealmSmtpConfig, RealmSmtpConfigId> {
    
    List<RealmSmtpConfig> findByIdRealmId(String realmId);
}
