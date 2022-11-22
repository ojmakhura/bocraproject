package bw.org.bocra.portal.comm;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class FallBackCommunicationService {
    
    @RabbitListener(queues = {"q.fall-back-communication"})
    public void onCommunicationFailure(CommunicationMessage failedCommunication){
        //log.info("Executing fallback for failed registration {}", failedRegistration);
    }
}
