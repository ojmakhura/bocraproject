package bw.org.bocra.portal.email;

import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.keycloak.adapters.RefreshableKeycloakSecurityContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import bw.org.bocra.portal.comm.CommunicationMessage;
import bw.org.bocra.portal.keycloak.KeycloakService;
import bw.org.bocra.portal.smtp.RealmSmtpDTO;
import bw.org.bocra.portal.smtp.RealmSmtpService;

@Service
public class EmailService {

    protected Logger logger = LoggerFactory.getLogger(EmailService.class);

    private final RealmSmtpService configService;
    private final KeycloakService keycloakService;

    public EmailService(RealmSmtpService configService, KeycloakService keycloakService) {
        this.configService = configService;
        this.keycloakService = keycloakService;
    }

    @RabbitListener(queues = "q.send-email")
    public void sendEmail(CommunicationMessage emailMessage) {
        logger.info("Sending email to {}", emailMessage.getSubject());

        RefreshableKeycloakSecurityContext context = keycloakService.getSecurityContext();
        RealmSmtpDTO dto = configService.getRealmSmtpConfig("bocraportal");

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(dto.getHost());
        mailSender.setPort(dto.getPort());

        mailSender.setUsername(dto.getUser());
        mailSender.setPassword(dto.getPassword());

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", dto.isAuth());
        props.put("mail.smtp.starttls.enable", dto.isStartTLS());
        // props.put("mail.debug", "true");

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(dto.getFrom());

        if (StringUtils.isNotBlank(dto.getReplyTo())) {
            message.setReplyTo(dto.getReplyTo());
        }

        message.setTo(emailMessage.getTo());
        message.setSubject(emailMessage.getSubject());
        message.setText(emailMessage.getMessage());

        mailSender.send(message);
    }
}
