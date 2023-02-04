package bw.org.bocra.portal.keycloak.smtp;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import bw.org.bocra.portal.keycloak.realm.Realm;
import bw.org.bocra.portal.keycloak.realm.RealmRepository;

@Service
public class RealmSmtpService {

    private final RealmSmtpConfigRepository configRepository;
    private final RealmRepository realmRepository;

    public RealmSmtpService(RealmSmtpConfigRepository configRepository, RealmRepository realmRepository) {
        this.configRepository = configRepository;
        this.realmRepository = realmRepository;
    }

    public RealmSmtpDTO getRealmSmtpConfig(String realmName) throws Exception {

        List<Realm> realms = realmRepository.findByName(realmName);

        if(CollectionUtils.isEmpty(realms)) {
            throw new Exception("No realms with name " + realmName + " found.");
        }

        RealmSmtpDTO config = new RealmSmtpDTO();

        List<RealmSmtpConfig> configs = configRepository.findByIdRealmId(realms.get(0).getId());

        if(CollectionUtils.isEmpty(configs)) {
            throw new Exception("No SMTP configurations found.");
        }

        for (RealmSmtpConfig realmSmtpConfig : configs) {
            if (realmSmtpConfig.getId().getName().equals("auth")) {

                config.setAuth(Boolean.parseBoolean(realmSmtpConfig.getValue()));
            } else if (realmSmtpConfig.getId().getName().equals("envelopeForm")) {

                config.setEnvelopeForm(realmSmtpConfig.getValue());

            } else if (realmSmtpConfig.getId().getName().equals("from")) {

                config.setFrom(realmSmtpConfig.getValue());

            } else if (realmSmtpConfig.getId().getName().equals("fromDisplayName")) {

                config.setFromDisplayName(realmSmtpConfig.getValue());

            } else if (realmSmtpConfig.getId().getName().equals("host")) {

                config.setHost(realmSmtpConfig.getValue());

            } else if (realmSmtpConfig.getId().getName().equals("password")) {

                config.setPassword(realmSmtpConfig.getValue());

            } else if (realmSmtpConfig.getId().getName().equals("port")) {

                config.setPort(Integer.parseInt(realmSmtpConfig.getValue()));

            } else if (realmSmtpConfig.getId().getName().equals("replyTo")) {

                config.setReplyTo(realmSmtpConfig.getValue());

            } else if (realmSmtpConfig.getId().getName().equals("replyToDisplayName")) {

                config.setReplyToDisplayName(realmSmtpConfig.getValue());

            } else if (realmSmtpConfig.getId().getName().equals("ssl")) {

                config.setSsl(Boolean.parseBoolean(realmSmtpConfig.getValue()));

            } else if (realmSmtpConfig.getId().getName().equals("starttls")) {

                config.setStartTLS(Boolean.parseBoolean(realmSmtpConfig.getValue()));

            } else if (realmSmtpConfig.getId().getName().equals("user")) {

                config.setUser(realmSmtpConfig.getValue());
            }
        }

        return config;
    }
}
