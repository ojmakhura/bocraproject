package bw.org.bocra.portal.comm;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import bw.org.bocra.portal.message.BocraMesssageVO;
import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import bw.org.bocra.portal.properties.RabbitProperties;

@Service
@Slf4j
public class EmailDispatchListener {
    // protected Logger logger = LoggerFactory.getLogger(CommunicationListener.class);
    
    private final RabbitTemplate rabbitTemplate;
    private final RabbitProperties rabbitProperties;

    public EmailDispatchListener(RabbitTemplate rabbitTemplate, RabbitProperties rabbitProperties) {
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitProperties = rabbitProperties;
    }

    @RabbitListener(queues = {"q.email-dispatch"})
    public void onCommunication(BocraMesssageVO emailMessage)  {
        log.info("Communication message Received: {}", emailMessage);

        //executeCommunication(event);

        rabbitTemplate.convertAndSend("x.post-email-dispatch","", emailMessage);
    }

    private void executeCommunication(BocraMesssageVO emailMessage) {
        log.info("Executing Communication Event: {}", emailMessage);

        throw new RuntimeException("Communication Failed");

    }
}
