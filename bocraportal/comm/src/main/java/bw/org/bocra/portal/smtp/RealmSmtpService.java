package bw.org.bocra.portal.smtp;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class RealmSmtpService {

    private final RealmSmtpConfigRepository configRepository;

    public RealmSmtpService(RealmSmtpConfigRepository configRepository) {
        this.configRepository = configRepository;
    }

    public RealmSmtpDTO getRealmSmtpConfig(String realmId) {

        RealmSmtpDTO config = new RealmSmtpDTO();

        List<RealmSmtpConfig> configs = configRepository.findByIdRealmId(realmId);

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
