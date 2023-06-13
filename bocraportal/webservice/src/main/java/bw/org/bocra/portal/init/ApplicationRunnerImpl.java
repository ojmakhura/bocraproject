package bw.org.bocra.portal.init;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import bw.org.bocra.portal.access.AccessPointCriteria;
import bw.org.bocra.portal.access.AccessPointService;
import bw.org.bocra.portal.access.AccessPointVO;
import bw.org.bocra.portal.access.type.AccessPointTypeService;
import bw.org.bocra.portal.access.type.AccessPointTypeVO;
import bw.org.bocra.portal.auth.AuthorisationService;
import bw.org.bocra.portal.auth.AuthorisationVO;
import bw.org.bocra.portal.config.SystemConfigService;
import bw.org.bocra.portal.config.SystemConfigVO;
import bw.org.bocra.portal.period.PeriodService;
import bw.org.bocra.portal.period.PeriodVO;
import bw.org.bocra.portal.period.config.PeriodConfigService;
import bw.org.bocra.portal.period.config.PeriodConfigVO;
import bw.org.bocra.portal.period.config.RepeatPeriod;
import bw.org.bocra.portal.sector.SectorService;
import bw.org.bocra.portal.sector.SectorVO;

@Component
@Transactional
@Profile("!test")
public class ApplicationRunnerImpl implements ApplicationRunner {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final PeriodService periodService;
    private final PeriodConfigService periodConfigService;
    private final AccessPointTypeService accessPointTypeService;
    private final AccessPointService accessPointService;
    private final AuthorisationService authorisationService;
    private final SectorService sectorService;
    private final SystemConfigService systemConfigService;

    @Value("${bocra.complaints.emails}")
    private String complaintEmails;

    @Value("${bocra.api.url}")
    private String apiUrl;

    public ApplicationRunnerImpl(SystemConfigService systemConfigService, PeriodService periodService, PeriodConfigService periodConfigService, AccessPointTypeService accessPointTypeService, AccessPointService accessPointService, AuthorisationService authorisationService, SectorService sectorService) {

        this.periodService = periodService;
        this.periodConfigService = periodConfigService;
        this.accessPointService = accessPointService;
        this.accessPointTypeService = accessPointTypeService;
        this.authorisationService = authorisationService;
        this.sectorService = sectorService;
        this.systemConfigService = systemConfigService;
    }

    private Collection<PeriodConfigVO> initPeriodConfigs() {

        Collection<PeriodConfigVO> configs = new ArrayList<>();

        PeriodConfigVO config = new PeriodConfigVO();

        config.setCreatedBy("system");
        config.setCreatedDate(LocalDateTime.now());
        config.setFinalDay(15);
        config.setPeriodConfigName("MONTHLY");
        config.setRepeat(1);
        config.setStartDay(1);
        config.setStartMonth(1);
        config.setRepeatPeriod(RepeatPeriod.MONTHS);
        log.info("Creating MONTHLY period configuration ...");
        config = periodConfigService.save(config);
        configs.add(config);
        log.info(String.format("MONTHLY period configuration creation complete with id %d ...", config.getId()));

        config = new PeriodConfigVO();

        config.setCreatedBy("system");
        config.setCreatedDate(LocalDateTime.now());
        config.setFinalDay(15);
        config.setPeriodConfigName("QUARTERLY");
        config.setRepeat(3);
        config.setStartDay(1);
        config.setStartMonth(1);
        config.setRepeatPeriod(RepeatPeriod.MONTHS);
        log.info("Creating QUARTERLY period configuration ...");
        config = periodConfigService.save(config);
        configs.add(config);
        log.info(String.format("QUARTERLY period configuration creation complete with id %d ...", config.getId()));

        config = new PeriodConfigVO();

        config.setCreatedBy("system");
        config.setCreatedDate(LocalDateTime.now());
        config.setFinalDay(15);
        config.setPeriodConfigName("ANNUALLY");
        config.setRepeat(1);
        config.setStartDay(1);
        config.setStartMonth(1);
        config.setRepeatPeriod(RepeatPeriod.YEARS);
        log.info("Creating ANNUAL period configuration ...");
        config = periodConfigService.save(config);
        configs.add(config);
        log.info(String.format("ANNUAL period configuration creation complete with id %d ...", config.getId()));

        return configs;
    }

    public void initPeriods(PeriodConfigVO config) {

        log.info("Creating periods for configuration " + config.getPeriodConfigName());

        LocalDate now = LocalDate.now();
        // Creating the first of this year
        PeriodVO period = new PeriodVO();
        period.setPeriodConfig(config);
        period.setCreatedBy("system");
        period.setCreatedDate(LocalDateTime.now());
        LocalDate cur = LocalDate.now();
        period.setPeriodStart(LocalDate.now().with(java.time.temporal.TemporalAdjusters.firstDayOfYear()));

        period = periodService.save(period);

        while(now.compareTo(period.getPeriodEnd()) > 0) {

            Set<Long> s = new HashSet<>();
            s.add(period.getId());
            Collection<PeriodVO> periods = periodService.createNextPeriods("system", s);

            if(CollectionUtils.isNotEmpty(periods)) {
                period = periods.iterator().next();
            }
            
        }

        log.info("Created periods for configuration " + config.getPeriodConfigName());   
    }

    public void initAccessPointType() {
        AccessPointTypeVO type = new AccessPointTypeVO();
        type.setCode("MENU");
        type.setName("Menu");
        type.setDescription("This access point type is meant for menus.");

        type = accessPointTypeService.save(type);

        type = new AccessPointTypeVO();
        type.setCode("API");
        type.setName("API Endpoint");
        type.setDescription("This access point type is meant for API endpoints.");
        type = accessPointTypeService.save(type);

        type = new AccessPointTypeVO();
        type.setCode("VIEW");
        type.setName("View");
        type.setDescription("This access point type is meant for views.");
        type = accessPointTypeService.save(type);

        type = new AccessPointTypeVO();
        type.setCode("BUTTON");
        type.setName("Button");
        type.setDescription("This access point type is meant for buttons is a view.");
        type = accessPointTypeService.save(type);
    }

    private void initMenuAccessPoints() {

        Map<String, AccessPointTypeVO> typeMap = new HashMap<>();
        Collection<AccessPointTypeVO> types = accessPointTypeService.getAll();

        for (AccessPointTypeVO type : types) {
            typeMap.put(type.getCode(), type);
        }

        try {
            ClassPathResource resource = new ClassPathResource("/accessPointData.csv");
            List<List<String>> records = new ArrayList<>();

            Scanner scan = new Scanner(resource.getInputStream());

            while(scan.hasNextLine()) {

                String[] values = scan.nextLine().split(",");
                records.add(Arrays.asList(values));
            }

            for (List<String> record : records) {
                
                AccessPointVO point = new AccessPointVO();
                point.setCreatedBy("system");
                point.setCreatedDate(LocalDateTime.now());

                Iterator<String> recordIter = record.iterator();

                point.setName(recordIter.next());
                point.setUrl(recordIter.next());
                point.setAccessPointType(typeMap.get(recordIter.next()));
                if(record.size() == 4) {
                    point.setIcon(recordIter.next());
                }

                point = accessPointService.save(point);
            }

        } catch (IOException e) {
            log.error("Failed to load access point file resource.", e);
            e.printStackTrace();
        }
    }

    private void initAuthorisation() {
        Set<String> roles = new HashSet<>();
        roles.add("DEVELOPER");
        
        for (AccessPointVO point : accessPointService.getAll()) {
            log.info(String.format("Creating authorisation for %s", point.getName()));
            AuthorisationVO authorisation = new AuthorisationVO();
            authorisation.setCreatedBy("system");
            authorisation.setCreatedDate(LocalDateTime.now());
            
            authorisation.setRoles(roles);
            authorisation.setAccessPoint(point);
            authorisation = authorisationService.save(authorisation);
        }
    }

    private void initSectors() throws StreamReadException, DatabindException, IOException {

        ClassPathResource resource = new ClassPathResource("/sectors.json");

        ObjectMapper mapper = new ObjectMapper();
        List<SectorVO> sectors = Arrays.asList(mapper.readValue(resource.getInputStream(), SectorVO[].class));
        for (SectorVO sector : sectors) {
            log.info(String.format("Creating sector %s", sector.getName()));
            sector.setCreatedBy("system");
            sector.setCreatedDate(LocalDateTime.now());
            sectorService.save(sector);
        }
    }

    private void initSystemConfig() {

        SystemConfigVO config = new SystemConfigVO("API_URL", apiUrl);
        systemConfigService.save(config);

        config = new SystemConfigVO("COMPLAINTS_MANAGEMENT_EMAIL", "tochange@bocra.org.bw");
        systemConfigService.save(config);

        String emailTempate =
                            "Dear %s\n\n" +

                            "We acknowledge receipt of your complaint against %s and will get back\n" +
                            "to you as soon as possible. Please note that to access your\n" +
                            "complaint, go the the url %s.\n\n" +

                            "Regards,\n\n" +

                            "BOCRA Complaint Management Team";

        config = new SystemConfigVO("COMPLAINANT_EMAIL_TEMPLATE", emailTempate);
        systemConfigService.save(config);

        emailTempate = 
            "Dear Complaint Officer\n\n" +
            "A new complaint has been logged against %s. Please go to\n" +
            "the url %s to process it.\n\n" +
            "Regards,\n\n" +
            "BOCRA Online Data Collection Portal";

        config = new SystemConfigVO("COMPLAINTS_OFFICER_EMAIL_TEMPLATE", emailTempate);
        systemConfigService.save(config);

        emailTempate = "Dear %s\n\n" +
            "Your complaint %s against %s has a new reply. Go to the URL\n" +
            "%s to view the reply and respond.\n\n" +
            "Regards,\n\n" +
            "BOCRA Complaint Management Team";

        config = new SystemConfigVO("COMPLAINT_REPLY_TEMPLATE", emailTempate);
        systemConfigService.save(config);

        emailTempate =
            "Dear %s\n\n" +
            "You have been assigned to handle the complaint against %s.\n" +
            "Please go to the url %s to manage it.\n\n" +
            "Regards,\n\n" +
            "%s";
        config = new SystemConfigVO("COMPLAINTS_USER_ASSIGNMENT_TEMPLATE", emailTempate);
        systemConfigService.save(config);

        emailTempate = "Dear %s user\n\n" +

            "You are notified that BOCRA requests your participation to\n" +
            "provide %s data. Please go to %s?id=%d to fill out the form.\n" +
            "The deadline to submit the information is %s\n\n" +
            "Kind Regards\n\n" +
            "BOCRA";
        config = new SystemConfigVO("ACTIVATION_SUBMISSION_TEMPLATE", emailTempate);
        systemConfigService.save(config);

        emailTempate = "Dear %s user\n\n" +
            "You are notified that BOCRA has returned your submission for %s data.\n" +
            "Please go to %s?id=%d to get the details of the return. The deadline to submit the information is %s.\n\n" +
            "Kind Regards\n\n" +
            "BOCRA";

        config = new SystemConfigVO("SUBMISSION_RETURN_EMAIL_TEMPLATE", emailTempate);
        systemConfigService.save(config);

    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        log.info("Checking system initialisation status");
        
        if (CollectionUtils.isEmpty(periodConfigService.getAll(1, 1))) {
            log.info("Initialising period configurations .... ");

            this.initPeriodConfigs();
            log.info("Period configurations initialisation complete .... ");
            
        }

        if(CollectionUtils.isEmpty(periodService.getAll(1, 1))) {
            log.info("Initialising periods .... ");


            for (PeriodConfigVO config : periodConfigService.getAll()) {
                this.initPeriods(config);     
            }

            log.info("Period initialisation complete .... ");
        }

        if(CollectionUtils.isEmpty(accessPointTypeService.getAll(1, 1))) {

            log.info("Initialising access point types .... ");
            this.initAccessPointType();
            log.info("Access point types initialisation complete .... ");
        }

        if(CollectionUtils.isEmpty(accessPointService.getAll(1, 1))) {

            log.info("Initialising access points .... ");
            this.initMenuAccessPoints();
            log.info("Access points initialisation complete .... ");
        }

        if(CollectionUtils.isEmpty(authorisationService.getAll(1, 1))) {

            log.info("Initialising authorisations .... ");
            this.initAuthorisation();
            log.info("Authorisations initialisation complete .... ");
        }

        if(CollectionUtils.isEmpty(sectorService.getAll(1, 1))) {

            log.info("Initialising sectors .... ");
            this.initSectors();
            log.info("Sectors initialisation complete .... ");

        }
        log.info(complaintEmails);

        if(CollectionUtils.isEmpty(systemConfigService.getAll())) {

            log.info("Initialising system configs .... ");
            this.initSystemConfig();
            log.info("System config complete .... ");

        }

        log.info("System fully intialised ...");
    }

}
