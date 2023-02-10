package bw.org.bocra.portal.email;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import bw.org.bocra.portal.keycloak.smtp.RealmSmtpDTO;
import bw.org.bocra.portal.keycloak.smtp.RealmSmtpService;
import bw.org.bocra.portal.message.BocraMesssage;
import bw.org.bocra.portal.message.BocraMesssageDao;
import bw.org.bocra.portal.message.BocraMesssageRepository;
import bw.org.bocra.portal.message.BocraMesssageService;
import bw.org.bocra.portal.message.BocraMesssageStatus;
import bw.org.bocra.portal.message.BocraMesssageVO;
import lombok.extern.slf4j.Slf4j;
import bw.org.bocra.portal.properties.RabbitProperties;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
public class EmailService {

    @Value("${keycloak.realm}")
    private String realmId;

    private final RealmSmtpService realmSmtpService;
    private final RabbitTemplate rabbitTemplate;
    private final RabbitProperties rabbitProperties;
    private BocraMesssageDao bocraMesssageDao;
    private BocraMesssageRepository bocraMesssageRepository;
    
    @Autowired
    public EmailService(RabbitProperties rabbitProperties, RealmSmtpService realmSmtpService,
                RabbitTemplate rabbitTemplate, BocraMesssageDao bocraMesssageDao, BocraMesssageRepository bocraMesssageRepository) {

        this.realmSmtpService = realmSmtpService;
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitProperties = rabbitProperties;
        this.bocraMesssageDao = bocraMesssageDao;
        this.bocraMesssageRepository = bocraMesssageRepository;
    }

    @RabbitListener(queues = {"q.email-dispatch"})
    public void sendEmail(BocraMesssageVO emailMessage) {

        log.info("Sending email to {}", emailMessage.getDestinations());

        try {

            BocraMesssage bocraMesssage = bocraMesssageDao.bocraMesssageVOToEntity(emailMessage);

            RealmSmtpDTO dto = realmSmtpService.getRealmSmtpConfig(realmId);

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

            message.setTo(bocraMesssage.getDestinations().toArray(new String[0]));
            
            message.setSubject(bocraMesssage.getSubject());
            message.setText(bocraMesssage.getText());

            try {
                mailSender.send(message);

                if(bocraMesssage.getId() == null) {
                    bocraMesssage.setCreatedBy(dto.getUser());
                    bocraMesssage.setCreatedDate(LocalDateTime.now());
                }

                bocraMesssage.setStatus(BocraMesssageStatus.SENT);
                bocraMesssage.setDispatchDate(LocalDateTime.now());

            } catch(MailException e) {

                if(bocraMesssage.getId() == null) {
                    bocraMesssage.setCreatedBy(dto.getUser());
                    bocraMesssage.setCreatedDate(LocalDateTime.now());
                }
                bocraMesssage.setStatus(BocraMesssageStatus.FAILED);

                throw e;
            }

            bocraMesssage = bocraMesssageRepository.save(bocraMesssage);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
