package bw.org.bocra.portal.email;

import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.keycloak.adapters.RefreshableKeycloakSecurityContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import bw.org.bocra.portal.message.CommunicationMessageVO;
import bw.org.bocra.portal.smtp.RealmSmtpDTO;
import bw.org.bocra.portal.smtp.RealmSmtpService;

@Service
public class EmailService {

    protected Logger logger = LoggerFactory.getLogger(EmailService.class);

    @Value("${keycloak.realm}")
    private String realmId;

    private final RealmSmtpService configService;

    public EmailService(RealmSmtpService configService) {
        this.configService = configService;
    }

    @RabbitListener(queues = "q.send-email")
    public void sendEmail(CommunicationMessageVO emailMessage) {
        logger.info("Sending email to {}", emailMessage.getSubject());

        RealmSmtpDTO dto = configService.getRealmSmtpConfig(realmId);

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

        message.setTo(emailMessage.getDestination());
        message.setSubject(emailMessage.getSubject());
        message.setText(emailMessage.getText());

        mailSender.send(message);
    }
}
