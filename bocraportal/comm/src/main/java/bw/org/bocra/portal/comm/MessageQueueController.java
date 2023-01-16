package bw.org.bocra.portal.comm;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import bw.org.bocra.portal.message.CommunicationMessageStatus;
import bw.org.bocra.portal.message.CommunicationMessageVO;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/messages")
@Slf4j
public class MessageQueueController {
    
    private final RabbitTemplate rabbitTemplate;
    private final RestTemplate restTemplate;
    
    @Value("${bocra.api.url}")
    private String apiUrl;

    public MessageQueueController(RabbitTemplate rabbitTemplate, RestTemplate restTemplate) {

        this.rabbitTemplate = rabbitTemplate;
        this.restTemplate = restTemplate;
    }

    @PostMapping()
    public ResponseEntity<?> sendEmails(@RequestBody Collection<CommunicationMessageVO> messages) {

        for (CommunicationMessageVO message : messages) {
            this.sendEmail(message);
        }

        return ResponseEntity.ok(messages.size());
    }

    public ResponseEntity<?> sendEmail(CommunicationMessageVO message) {

        log.info(message.toString());
        rabbitTemplate.convertAndSend("", "q.communication", message);
        
        return ResponseEntity.ok().build();
    }

    @GetMapping("/due")
    public ResponseEntity<Integer> loadDueMessages() {

        System.out.println(SecurityContextHolder.getContext().getAuthentication());

        JwtAuthenticationToken auth = (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
            
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + auth.getToken().getTokenValue());
        String url = apiUrl + "/message/due";
        HttpEntity<String> request = new HttpEntity<String>(headers);

        ResponseEntity<CommunicationMessageVO[]> response = restTemplate.exchange(url, HttpMethod.GET, request, CommunicationMessageVO[].class);
        CommunicationMessageVO[] messages = response.getBody();
        if(messages != null) {

            for (CommunicationMessageVO message : messages) {

                String status = CommunicationMessageStatus.FAILED.getValue();

                if(!CollectionUtils.isEmpty(message.getDestinations())) {
                    message.setCreatedDate(null);
                    message.setDispatchDate(null);
                    message.setUpdatedDate(null);
                    this.sendEmail(message);

                    // Update the message status
                    status = CommunicationMessageStatus.SENT.getValue();
                }

                String updateUrl = String.format("%s/message/status?id=%d&status=%s", apiUrl, message.getId(), status);
                restTemplate.exchange(updateUrl, HttpMethod.PATCH, request, CommunicationMessageVO[].class);
            }
        } else {
            messages = new CommunicationMessageVO[0];
        }

        return ResponseEntity.ok(messages.length);
    }

}
