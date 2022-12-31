package bw.org.bocra.portal.init;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

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

        period = periodService.save(period);

        while(now.compareTo(period.getPeriodEnd()) > 0) {
            PeriodVO next = new PeriodVO();
            next.setPeriodConfig(config);

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

        AccessPointTypeVO type = accessPointTypeService.search("API")
                                        .stream()
                                        .filter(tp -> tp.getCode().equals("API"))
                                        .collect(Collectors.toList()).get(0);

        AccessPointVO point = new AccessPointVO();
        point.setCreatedBy("system");
        point.setCreatedDate(LocalDateTime.now());
        point.setAccessPointType(type);
        point.setName("Authorisation");
        point.setUrl("/authorisation");

        point = accessPointService.save(point);

        point = new AccessPointVO();
        point.setCreatedBy("system");
        point.setCreatedDate(LocalDateTime.now());
        point.setAccessPointType(type);
        point.setName("Access Point Type");
        point.setUrl("/access/type");

        point = accessPointService.save(point);

        point = new AccessPointVO();
        point.setCreatedBy("system");
        point.setCreatedDate(LocalDateTime.now());
        point.setAccessPointType(type);
        point.setName("Access Point");
        point.setUrl("/access");

        point = accessPointService.save(point);

        point = new AccessPointVO();
        point.setCreatedBy("system");
        point.setCreatedDate(LocalDateTime.now());
        point.setAccessPointType(type);
        point.setName("Licensee");
        point.setUrl("/licensee");

        point = accessPointService.save(point);

        point = new AccessPointVO();
        point.setCreatedBy("system");
        point.setCreatedDate(LocalDateTime.now());
        point.setAccessPointType(type);
        point.setName("Users");
        point.setUrl("/user");

        point = accessPointService.save(point);

        point = new AccessPointVO();
        point.setCreatedBy("system");
        point.setCreatedDate(LocalDateTime.now());
        point.setAccessPointType(type);
        point.setName("Periods");
        point.setUrl("/period");

        point = accessPointService.save(point);

        point = new AccessPointVO();
        point.setCreatedBy("system");
        point.setCreatedDate(LocalDateTime.now());
        point.setAccessPointType(type);
        point.setName("Period Configurations");
        point.setUrl("/period/config");

        point = accessPointService.save(point);

        point = new AccessPointVO();
        point.setCreatedBy("system");
        point.setCreatedDate(LocalDateTime.now());
        point.setAccessPointType(type);
        point.setName("Form Definitions");
        point.setUrl("/form");

        point = accessPointService.save(point);

        point = new AccessPointVO();
        point.setCreatedBy("system");
        point.setCreatedDate(LocalDateTime.now());
        point.setAccessPointType(type);
        point.setName("Data Capture");
        point.setUrl("/form/submission");

        point = accessPointService.save(point);

        point = new AccessPointVO();
        point.setCreatedBy("system");
        point.setCreatedDate(LocalDateTime.now());
        point.setAccessPointType(type);
        point.setName("Licence Types");
        point.setUrl("/licence/type");

        point = accessPointService.save(point);

        point = new AccessPointVO();
        point.setCreatedBy("system");
        point.setCreatedDate(LocalDateTime.now());
        point.setAccessPointType(type);
        point.setName("Licences");
        point.setUrl("/licence");

        point = accessPointService.save(point);

        point = new AccessPointVO();
        point.setCreatedBy("system");
        point.setCreatedDate(LocalDateTime.now());
        point.setAccessPointType(type);
        point.setName("Sectors");
        point.setUrl("/sector");

        point = accessPointService.save(point);

        point = new AccessPointVO();
        point.setCreatedBy("system");
        point.setCreatedDate(LocalDateTime.now());
        point.setAccessPointType(type);
        point.setName("Document Types");
        point.setUrl("/document/type");

        point = accessPointService.save(point);

        point = new AccessPointVO();
        point.setCreatedBy("system");
        point.setCreatedDate(LocalDateTime.now());
        point.setAccessPointType(type);
        point.setName("Documents");
        point.setUrl("/document");

        point = accessPointService.save(point);

    }

    private void initAuthorisation() {
        Set<String> roles = new HashSet<>();

        for (AccessPointVO point : accessPointService.getAll()) {
            AuthorisationVO authorisation = new AuthorisationVO();
            authorisation.setCreatedBy("system");
            authorisation.setCreatedDate(LocalDateTime.now());
            
            roles.add("DEVELOPER");
            authorisation.setRoles(roles);
            authorisation.setAccessPoint(point);

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
