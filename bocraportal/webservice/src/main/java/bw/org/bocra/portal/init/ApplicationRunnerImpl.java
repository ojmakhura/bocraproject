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
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

import bw.org.bocra.portal.access.AccessPointCriteria;
import bw.org.bocra.portal.access.AccessPointService;
import bw.org.bocra.portal.access.AccessPointVO;
import bw.org.bocra.portal.access.type.AccessPointTypeService;
import bw.org.bocra.portal.access.type.AccessPointTypeVO;
import bw.org.bocra.portal.auth.AuthorisationService;
import bw.org.bocra.portal.auth.AuthorisationVO;
import bw.org.bocra.portal.period.PeriodService;
import bw.org.bocra.portal.period.PeriodVO;
import bw.org.bocra.portal.period.config.PeriodConfigService;
import bw.org.bocra.portal.period.config.PeriodConfigVO;
import bw.org.bocra.portal.period.config.RepeatPeriod;

@Component
@Transactional
public class ApplicationRunnerImpl implements ApplicationRunner {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final PeriodService periodService;
    private final PeriodConfigService periodConfigService;
    private final AccessPointTypeService accessPointTypeService;
    private final AccessPointService accessPointService;
    private final AuthorisationService authorisationService;

    public ApplicationRunnerImpl(PeriodService periodService, PeriodConfigService periodConfigService, AccessPointTypeService accessPointTypeService, AccessPointService accessPointService, AuthorisationService authorisationService) {

        this.periodService = periodService;
        this.periodConfigService = periodConfigService;
        this.accessPointService = accessPointService;
        this.accessPointTypeService = accessPointTypeService;
        this.authorisationService = authorisationService;
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
        log.info(String.format("MONTHLY period configuration creation complete with id ...", config.getId()));

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
        log.info(String.format("QUARTERLY period configuration creation complete with id ...", config.getId()));

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
        log.info(String.format("ANNUAL period configuration creation complete with id ...", config.getId()));

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

        period = periodService.save(period);

        while(now.compareTo(period.getPeriodEnd()) > 0) {
            PeriodVO next = new PeriodVO();
            next.setPeriodConfig(config);
            period.setCreatedBy("system");
            period.setCreatedDate(LocalDateTime.now());

            next.setPeriodStart(period.getPeriodEnd().plusDays(1));
            period = periodService.save(next);
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

        // AccessPointTypeVO type = accessPointTypeService.search("MENU")
        //                                 .stream()
        //                                 .filter(tp -> tp.getCode().equals("MENU"))
        //                                 .collect(Collectors.toList()).get(0);

        try {
            File file = ResourceUtils.getFile("classpath:accessPointData.csv");

            List<List<String>> records = new ArrayList<>();

            try(BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] values = line.split(",");
                    records.add(Arrays.asList(values));
                }
            }

            for (List<String> record : records) {
                System.out.println(record);
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

        log.info("System fully intialised ...");
    }

}
