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

import bw.org.bocra.portal.keycloak.smtp.RealmSmtpDTO;
import bw.org.bocra.portal.keycloak.smtp.RealmSmtpService;
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
    private final BocraMesssageService bocraMesssageService;
    private final BocraMesssageRepository bocraMesssageRepository;
    private final BocraMesssageDao bocraMesssageDao;
    private final RabbitTemplate rabbitTemplate;
    private final RabbitProperties rabbitProperties;
    
    public EmailService(RabbitProperties rabbitProperties, 
                RealmSmtpService realmSmtpService, BocraMesssageService bocraMesssageService, 
                RabbitTemplate rabbitTemplate, BocraMesssageRepository bocraMesssageRepository,
                BocraMesssageDao bocraMesssageDao) {
        this.realmSmtpService = realmSmtpService;
        this.bocraMesssageService = bocraMesssageService;
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitProperties = rabbitProperties;
        this.bocraMesssageRepository = bocraMesssageRepository;
        this.bocraMesssageDao = bocraMesssageDao;
    }

    @RabbitListener(queues = {"q.email-dispatch"})
    public void sendEmail(BocraMesssageVO emailMessage) {

        log.info("Sending email to {}", emailMessage.getDestinations());

        try {

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

            message.setTo(emailMessage.getDestinations().toArray(new String[0]));
            
            message.setSubject(emailMessage.getSubject());
            message.setText(emailMessage.getText());

            try {
                mailSender.send(message);

                if(emailMessage.getId() != null) {
                    emailMessage = bocraMesssageService.findById(emailMessage.getId());
                } else {
                    emailMessage.setCreatedBy(dto.getUser());
                    emailMessage.setCreatedDate(LocalDateTime.now());
                }

                emailMessage.setStatus(BocraMesssageStatus.SENT);
                emailMessage.setDispatchDate(LocalDateTime.now());
                
                bocraMesssageService.save(emailMessage);

            } catch(MailException e) {

                if(emailMessage.getId() != null) {
                    emailMessage = bocraMesssageService.findById(emailMessage.getId());
                } else {
                    emailMessage.setCreatedBy(dto.getUser());
                    emailMessage.setCreatedDate(LocalDateTime.now());
                }
                emailMessage.setStatus(BocraMesssageStatus.FAILED);
                bocraMesssageService.save(emailMessage);

                throw e;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @RabbitListener(queues = "q.email-queue")
    public void readEmailQueue(Collection<BocraMesssageVO> emailMessages) {

        log.info("Getting {} from email queue.", emailMessages.size());
        
        for (BocraMesssageVO emailMessage : emailMessages) {
            
            if(emailMessage.getId() != null) {
                emailMessage = bocraMesssageService.findById(emailMessage.getId());
            }
            
            emailMessage = bocraMesssageService.save(emailMessage);

            if(emailMessage.getSendNow()) {
                rabbitTemplate.convertAndSend("x.post-email-dispatch", rabbitProperties.getEmailDispatchRoutingKey(), emailMessage);
            }

        }

    }
}
