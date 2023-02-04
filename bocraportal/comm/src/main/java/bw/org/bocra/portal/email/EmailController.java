package bw.org.bocra.portal.email;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bw.org.bocra.portal.message.BocraMesssageService;
import bw.org.bocra.portal.message.BocraMesssageVO;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/email")
@Slf4j
public class EmailController {

    private final EmailService emailService;
    private final BocraMesssageService bocraMesssageService;

    public EmailController(EmailService emailService, BocraMesssageService bocraMesssageService) {
        this.emailService = emailService;
        this.bocraMesssageService = bocraMesssageService;
    }

    @PostMapping
    public ResponseEntity<?> sendEmail(@RequestBody BocraMesssageVO emailMessage) {
        try {

            emailService.sendEmail(emailMessage);       

            return ResponseEntity.ok().body(emailMessage);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
