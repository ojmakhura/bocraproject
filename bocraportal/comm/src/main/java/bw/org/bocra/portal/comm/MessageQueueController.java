package bw.org.bocra.portal.comm;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/queue")
public class MessageQueueController {
    protected Logger logger = LoggerFactory.getLogger(MessageQueueController.class);
    private final RabbitTemplate rabbitTemplate;

    public MessageQueueController(RabbitTemplate rabbitTemplate) {

        this.rabbitTemplate = rabbitTemplate;

    }

    @PostMapping()
    public ResponseEntity<?> sendEmail(@RequestBody CommunicationMessage message) {

        logger.info(message.toString());
        rabbitTemplate.convertAndSend("", "q.communication", message);
        
        return ResponseEntity.ok().build();
    }
}
