package bw.org.bocra.portal.smtp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/smtp")
public class RealmSmtpController {

    private final RealmSmtpService realmSmtpService;
    public RealmSmtpController(RealmSmtpService realmSmtpService) {
        this.realmSmtpService = realmSmtpService;
    }
    
    @GetMapping()
    public RealmSmtpDTO getRealmSmtpConfig(@RequestParam String realmId) {

        return this.realmSmtpService.getRealmSmtpConfig(realmId);
    }
}
