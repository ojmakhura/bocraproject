package bw.org.bocra.portal.comm;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import bw.org.bocra.portal.message.CommunicationMessageVO;

import java.util.Collection;

import org.apache.commons.collections.CollectionUtils;
import org.keycloak.adapters.RefreshableKeycloakSecurityContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/messages")
public class MessageQueueController {
    protected Logger logger = LoggerFactory.getLogger(MessageQueueController.class);
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

        logger.info(message.toString());
        rabbitTemplate.convertAndSend("", "q.communication", message);
        
        return ResponseEntity.ok().build();
    }

    @GetMapping("/due")
    public ResponseEntity<?> loadDueMessages() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        RefreshableKeycloakSecurityContext ksc = (RefreshableKeycloakSecurityContext) authentication.getCredentials();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + ksc.getTokenString());
        String url = apiUrl + "/message/due";
        HttpEntity<String> request = new HttpEntity<String>(headers);

        ResponseEntity<CommunicationMessageVO[]> response = restTemplate.exchange(url, HttpMethod.GET, request, CommunicationMessageVO[].class);
        CommunicationMessageVO[] messages = response.getBody();
        if(messages != null) {

            for (CommunicationMessageVO message : messages) {

                if(CollectionUtils.isNotEmpty(message.getDestinations())) {
                    message.setCreatedDate(null);
                    message.setDispatchDate(null);
                    message.setUpdatedDate(null);
                    this.sendEmail(message);
                }                
            }
        } else {
            messages = new CommunicationMessageVO[0];
        }

        return ResponseEntity.ok(messages.length);
    }

}
