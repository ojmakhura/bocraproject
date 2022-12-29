package bw.org.bocra.portal.schedule;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    private String getDateTime() {
        LocalDateTime current = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return current.format(formatter);
    }

    @Async
    @Scheduled(cron = "@daily", zone = "Africa/Gaborone")
    public void dailySchedule() {
        String formatTime = this.getDateTime();
        log.info("Daily cron job at " + formatTime);
    }

    @Async
    @Scheduled(cron = "@weekly", zone = "Africa/Gaborone")
    public void weeklySchedule() {
        String formatTime = this.getDateTime();
        log.info("Weekly cron job at " + formatTime);
    }

    @Async
    @Scheduled(cron = "@monthly", zone = "Africa/Gaborone")
    public void monthlySchedule() {
        String formatTime = this.getDateTime();
        log.info("Monthly cron job at " + formatTime);
    }

    @Async
    @Scheduled(cron = "1 0 0 1 JAN,APR,JUL,OCT ?", zone = "Africa/Gaborone")
    public void quarterlySchedule() {
        String formatTime = this.getDateTime();
        log.info("Monthly cron job at " + formatTime);
    }

    @Async
    @Scheduled(cron = "@yearly", zone = "Africa/Gaborone")
    public void annualSchedule() {
        String formatTime = this.getDateTime();
        log.info("Monthly cron job at " + formatTime);
    }

    @Async
    @Scheduled(cron = "0 0 8 * * *", zone = "Africa/Gaborone")
    public void dueMessages() {
        String formatTime = this.getDateTime();
        log.info("Due messages cron job at " + formatTime);
        String accessToken = cronSecurity.getAccessToken().getToken();
        log.info(accessToken);
        String due = commUrl + "/messages/due";
        log.info("Loading due messages from " + due);

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> request = new HttpEntity<String>(headers);

        // ResponseEntity<Integer> response = restTemplate.exchange(due, HttpMethod.GET, request, Integer.class);

        // log.info(String.format("%d messages sent to queue", response.getBody()));
    }

    @Async
    @Scheduled(cron = "0 0 8 1 * *", zone = "Africa/Gaborone")
    public void overdueSubmissions() {
        String formatTime = this.getDateTime();
        log.info("Overdue submissions cron job at " + formatTime);

        String accessToken = cronSecurity.getAccessToken().getToken();
        log.info(accessToken);
        String due = commUrl + "/messages/due";
        log.info("Loading overdue submissions " + due);

        HttpHeaders headers = new HttpHeaders();
        
        HttpEntity<String> request = new HttpEntity<String>(headers);

        // ResponseEntity<Integer> response = restTemplate.exchange(due, HttpMethod.GET, request, Integer.class);

        // log.info(String.format("%d messages sent to queue", response.getBody()));
    }
}
