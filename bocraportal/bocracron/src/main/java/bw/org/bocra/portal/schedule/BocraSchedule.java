package bw.org.bocra.portal.schedule;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import bw.org.bocra.portal.security.CronSecurity;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class BocraSchedule {

    private final CronSecurity cronSecurity;

    private final RestTemplate restTemplate;

    @Value("${bocra.comm.url}")
    private String commUrl;

    public BocraSchedule(CronSecurity cronSecurity, RestTemplate restTemplate) {
        this.cronSecurity = cronSecurity;
        this.restTemplate = restTemplate;
    }

    @Async
    @Scheduled(cron = "@daily", zone = "Africa/Gaborone")
    public void dailySchedule() {

    }

    @Async
    @Scheduled(cron = "@weekly", zone = "Africa/Gaborone")
    public void weeklySchedule() {

    }

    @Async
    @Scheduled(cron = "@monthly", zone = "Africa/Gaborone")
    public void monthlySchedule() {

    }

    @Async
    @Scheduled(cron = "1 0 0 1 JAN,APR,JUL,OCT ?", zone = "Africa/Gaborone")
    public void quarterlySchedule() {

    }

    @Async
    @Scheduled(cron = "@yearly", zone = "Africa/Gaborone")
    public void annualSchedule() {

    }

    @Async
    @Scheduled(cron = "@daily", zone = "Africa/Gaborone")
    public void dueMessages() {
        String accessToken = cronSecurity.getAccessToken().getToken();
        log.info(accessToken);
        String due = commUrl + "/messages/due";
        log.info("Loading due messages from " + due);

        HttpHeaders headers = new HttpHeaders();
        
        headers.set("Authorization", "Bearer " + accessToken);
        HttpEntity<String> request = new HttpEntity<String>(headers);

        ResponseEntity<Integer> response = restTemplate.exchange(due, HttpMethod.GET, request, Integer.class);

        log.info(String.format("%d messages sent to queue", response.getBody()));
    }

}
