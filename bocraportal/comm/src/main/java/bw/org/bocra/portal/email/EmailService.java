package bw.org.bocra.portal.email;

import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import bw.org.bocra.portal.message.CommunicationMessageVO;
import bw.org.bocra.portal.smtp.RealmSmtpDTO;
import bw.org.bocra.portal.smtp.RealmSmtpService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmailService {

    @Value("${keycloak.realm}")
    private String realmId;

    private final RestTemplate restTemplate;

    @Value("${bocra.api.url}")
    private String apiUrl;

    private final RealmSmtpService configService;

    public EmailService(RealmSmtpService configService, RestTemplate restTemplate) {
        this.configService = configService;
        this.restTemplate = restTemplate;
    }

    @RabbitListener(queues = "q.send-email")
    public void sendEmail(CommunicationMessageVO emailMessage) {
        log.info("Sending email to {}", emailMessage.getSubject());
        String url = apiUrl + "/message/due";

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

        message.setTo(emailMessage.getDestinations().toArray(new String[0]));;
        message.setSubject(emailMessage.getSubject());
        message.setText(emailMessage.getText());

        // try {
            mailSender.send(message);
        // } catch(MailException e) {

        // }
        
    }
}
