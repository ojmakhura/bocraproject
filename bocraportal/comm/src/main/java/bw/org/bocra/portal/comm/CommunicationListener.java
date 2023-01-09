package bw.org.bocra.portal.comm;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import bw.org.bocra.portal.message.CommunicationMessageVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class CommunicationListener {
    protected Logger logger = LoggerFactory.getLogger(CommunicationListener.class);
    
    private final RabbitTemplate rabbitTemplate;

    public CommunicationListener(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @RabbitListener(queues = {"q.communication"})
    public void onCommunication(CommunicationMessageVO event)  {
        logger.info("Communication Event Received: {}", event);

        //executeCommunication(event);

        rabbitTemplate.convertAndSend("x.post-communication","", event);
    }

    private void executeCommunication(CommunicationMessageVO event) {
        logger.info("Executing Communication Event: {}", event);

        throw new RuntimeException("Communication Failed");

    }
}
