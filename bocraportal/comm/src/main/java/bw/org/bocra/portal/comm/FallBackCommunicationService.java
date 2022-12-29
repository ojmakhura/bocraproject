package bw.org.bocra.portal.comm;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import bw.org.bocra.portal.message.CommunicationMessageVO;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FallBackCommunicationService {

    // protected Logger logger = LoggerFactory.getLogger(FallBackCommunicationService.class);
    
    @RabbitListener(queues = {"q.fall-back-communication"})
    public void onCommunicationFailure(CommunicationMessageVO failedCommunication){
        //log.info("Executing fallback for failed registration {}", failedRegistration);
    }
}
