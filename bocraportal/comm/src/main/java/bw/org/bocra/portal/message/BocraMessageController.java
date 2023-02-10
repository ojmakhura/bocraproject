package bw.org.bocra.portal.message;

import java.util.Collection;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;
import bw.org.bocra.portal.properties.RabbitProperties;

// @RestController
// @RequestMapping("/messages")
// @Slf4j
public class BocraMessageController {
    
    // private final RabbitTemplate rabbitTemplate;
    // private final RestTemplate restTemplate;
    // private final BocraMesssageService bocraMesssageService;
    // private final RabbitProperties rabbitProperties;
    
    // public BocraMessageController(RabbitProperties rabbitProperties, RabbitTemplate rabbitTemplate, RestTemplate restTemplate, BocraMesssageService bocraMesssageService) {

    //     this.rabbitTemplate = rabbitTemplate;
    //     this.restTemplate = restTemplate;
    //     this.bocraMesssageService = bocraMesssageService;
    //     this.rabbitProperties = rabbitProperties;
    // }

    // @PostMapping()
    // public ResponseEntity<?> sendEmails(@RequestBody Collection<BocraMesssageVO> messages) {

    //     for (BocraMesssageVO message : messages) {

    //         if(message.sendNow) {
    //             this.sendEmail(message);
    //         } else {
    //             bocraMesssageService.save(message);
    //         }
            
    //     }

    //     return ResponseEntity.ok(messages.size());
    // }

    // public ResponseEntity<?> sendEmail(BocraMesssageVO message) {
    //     message.setCreatedDate(null);
    //     message.setDispatchDate(null);
    //     log.info(message.toString());
    //     rabbitTemplate.convertAndSend("", rabbitProperties.getEmailHandler(), message);
    //     // rabbitTemplate.convertAndSend("", "q.communication", message);
        
    //     return ResponseEntity.ok().build();
    // }

    // @GetMapping("/due")
    // public ResponseEntity<Integer> loadDueMessages() {

    //     Collection<BocraMesssageVO> messages = bocraMesssageService.loadDueSubmissionMessages();
    //     if(messages != null) {

    //         for (BocraMesssageVO message : messages) {

    //             BocraMesssageStatus status = BocraMesssageStatus.FAILED;

    //             if(!CollectionUtils.isEmpty(message.getDestinations())) {
    //                 // message.setCreatedDate(null);
    //                 // message.setDispatchDate(null);
    //                 // message.setUpdatedDate(null);
    //                 this.sendEmail(message);

    //                 // Update the message status
    //                 status = BocraMesssageStatus.SENT;
    //             }

    //             message.setStatus(status);
    //             bocraMesssageService.save(message);
    //         }
    //     } else {
    //         return ResponseEntity.ok(0);
    //     }

    //     return ResponseEntity.ok(messages.size());
    // }

}
