package bw.org.bocra.portal.schedule;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

import bw.org.bocra.portal.complaint.ComplaintSeachCriteria;
import bw.org.bocra.portal.complaint.ComplaintStatus;
import bw.org.bocra.portal.complaint.ComplaintVO;
import bw.org.bocra.portal.form.activation.FormActivationVO;
import bw.org.bocra.portal.period.PeriodVO;
import bw.org.bocra.portal.security.CronSecurity;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class BocraSchedule {

    private final CronSecurity cronSecurity;

    private final RestTemplate restTemplate;

    @Value("${bocra.comm.url}")
    private String commUrl;

    @Value("${bocra.api.url}")
    private String apiUrl;

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
        this.setComplaintsPending(cronSecurity.getAccessToken().getToken());

    }

    public void setComplaintsPending(String token) {

        ComplaintSeachCriteria criteria = new ComplaintSeachCriteria();
        criteria.setStatus(ComplaintStatus.NEW);
        criteria.setPastDays(20);

        String complaintsNew = apiUrl + "/complaint/search";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + cronSecurity.getAccessToken().getToken());
        HttpEntity<ComplaintSeachCriteria> request = new HttpEntity<>(criteria, headers);

        ResponseEntity<?> response = restTemplate.postForEntity(complaintsNew, request, ComplaintVO[].class);

        if (response.getStatusCode() == HttpStatus.OK) {
            ComplaintVO[] newComplaints = (ComplaintVO[]) response.getBody();

            String statusUpdateUrl = apiUrl + "/complaint/status?complaintId=%s&status=%s";

            for (ComplaintVO complaint : newComplaints) {
                statusUpdateUrl = String.format(statusUpdateUrl, complaint.getComplaintId(), ComplaintStatus.PENDING);
                request = new HttpEntity<>(headers);
                response = restTemplate.exchange(statusUpdateUrl, HttpMethod.GET, request, Boolean.class);

                if (response.getStatusCode() != HttpStatus.OK) {
                    log.error(response.getBody().toString());
                }
            }

        } else {
            log.error(response.getBody().toString());
        }
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
        log.info("Quarterly cron job at " + formatTime);
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

        String due = commUrl + "/messages/due";
        log.info("Loading due messages from " + due);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + cronSecurity.getAccessToken().getToken());

        HttpEntity<String> request = new HttpEntity<String>(headers);

        // ResponseEntity<Integer> response = restTemplate.exchange(due, HttpMethod.GET, request, Integer.class);
        // log.info(String.format("%d messages sent to queue", response.getBody()));
    }

    @Async
    @Scheduled(cron = "0 0 8 1 * *", zone = "Africa/Gaborone")
    public void overdueSubmissions() {
        String formatTime = this.getDateTime();
        log.info("Overdue submissions cron job at " + formatTime);

        String due = apiUrl + "/form/submission/overdue";
        log.info("Loading overdue submissions " + due);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + cronSecurity.getAccessToken().getToken());

        HttpEntity<String> request = new HttpEntity<String>(headers);

        ResponseEntity<Integer> response = restTemplate.exchange(due, HttpMethod.GET, request, Integer.class);

        log.info(String.format("%d submissions overdue.", response.getBody()));
    }

    /**
     * On the last day of each month at 1800hrs, we create the next periods.
     */
    @Async
    @Scheduled(cron = "0 0 18 L * *", zone = "Africa/Gaborone")
    public void nextPeriods() {
        String formatTime = this.getDateTime();

        log.info("Creating new next periods at " + formatTime);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + cronSecurity.getAccessToken().getToken());
        HttpEntity<String> request = new HttpEntity<String>(headers);
        String nextPeriodUrl = apiUrl + "/period/next";
        ResponseEntity<PeriodVO[]> response = restTemplate.exchange(nextPeriodUrl, HttpMethod.GET, request,
                PeriodVO[].class);
        if (response.getStatusCode() == HttpStatus.OK) {
            log.info(
                    String.format(
                            "Created %d periods named %s.",
                            response.getBody().length,
                            Stream.<PeriodVO>of(response.getBody()).map(period -> period.getPeriodName())
                                    .collect(Collectors.toList())));
        }
    }

    /**
     * Three days before the last day of each month, we activate the due forms.
     */
    @Async
    @Scheduled(cron = "0 0 0 L-3 * *", zone = "Africa/Gaborone")
    public void activateDueForms() {
        String formatTime = this.getDateTime();

        log.info("Activating due forms at " + formatTime);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + cronSecurity.getAccessToken().getToken());
        HttpEntity<String> request = new HttpEntity<String>(headers);
        String activateUrl = apiUrl + "/form/activation/activate";
        ResponseEntity<FormActivationVO[]> response = restTemplate.exchange(activateUrl, HttpMethod.GET, request,
                FormActivationVO[].class);
        if (response.getStatusCode() == HttpStatus.OK) {
            log.info(
                    String.format(
                            "Created %d activations named %s.",
                            response.getBody().length,
                            Stream.<FormActivationVO>of(response.getBody())
                                    .map(activation -> activation.getActivationName()).collect(Collectors.toList())));
        }
    }
}
