package bw.org.bocra.portal.comm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import bw.org.bocra.portal.message.CommunicationMessageVO;

@Service
public class FallBackCommunicationService {

    protected Logger logger = LoggerFactory.getLogger(FallBackCommunicationService.class);
    
    @RabbitListener(queues = {"q.fall-back-communication"})
    public void onCommunicationFailure(CommunicationMessageVO failedCommunication){
        //log.info("Executing fallback for failed registration {}", failedRegistration);
    }
}
