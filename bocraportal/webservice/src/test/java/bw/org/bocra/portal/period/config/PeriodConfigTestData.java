package bw.org.bocra.portal.period.config;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("test")
public class PeriodConfigTestData {
    
    private final PeriodConfigRestController periodConfigRestController;

    public PeriodConfigTestData(PeriodConfigRestController periodConfigRestController) {
        this.periodConfigRestController = periodConfigRestController;
    }
    
    public PeriodConfigRestController getPeriodConfigRestController() {
        return periodConfigRestController;
    }

    public PeriodConfigVO createUnsavedPeriodConfig() {
        PeriodConfigVO config = new PeriodConfigVO();

        config.setCreatedBy("testuser4");
        config.setCreatedDate(LocalDateTime.now());
        config.setFinalDay(15);
        config.setPeriodConfigName("MONTHLY");
        config.setRepeat(1);
        config.setRepeatPeriod(RepeatPeriod.MONTHS);

        return config;
    }

    public PeriodConfigVO createMonthlyPeriodConfig() {
        return createPeriodConfig();
    }

    public PeriodConfigVO createQuarterlyPeriodConfig() {
        PeriodConfigVO config = new PeriodConfigVO();

        config.setCreatedBy("testuser4");
        config.setCreatedDate(LocalDateTime.now());
        config.setFinalDay(15);
        config.setPeriodConfigName("QUARTERLY");
        config.setRepeat(3);
        config.setRepeatPeriod(RepeatPeriod.MONTHS);

        return (PeriodConfigVO) periodConfigRestController.save(config).getBody();
    }

    public PeriodConfigVO createYearlyPeriodConfig() {
        PeriodConfigVO config = new PeriodConfigVO();

        config.setCreatedBy("testuser4");
        config.setCreatedDate(LocalDateTime.now());
        config.setFinalDay(15);
        config.setPeriodConfigName("YEARLY");
        config.setRepeat(3);
        config.setRepeatPeriod(RepeatPeriod.YEARS);

        return (PeriodConfigVO) periodConfigRestController.save(config).getBody();
    }


    public PeriodConfigVO createPeriodConfig() {

        return (PeriodConfigVO) periodConfigRestController.save(createUnsavedPeriodConfig()).getBody();
    }

    public Collection<PeriodConfigVO> generateSequentialData(int size) {
        
        return generateUnsavedSequentialData(size)
            .stream()
            .map(config -> (PeriodConfigVO)periodConfigRestController.save(config).getBody())
            .collect(Collectors.toList());
    }

    public Collection<PeriodConfigVO> generateUnsavedSequentialData(int size) {
        Collection<PeriodConfigVO> configs = new ArrayList<>();
        for (int i = 1; i <= size; i++) {

            PeriodConfigVO config = new PeriodConfigVO();

            config.setCreatedBy("testuser4");
            config.setCreatedDate(LocalDateTime.now());
            configs.add(config);

        }
        return configs;
    }
    
    public Collection<PeriodConfigVO> generateSearchData() {
        
        Collection<PeriodConfigVO> configs = new ArrayList<>();

        configs.add(this.createMonthlyPeriodConfig());
        configs.add(this.createQuarterlyPeriodConfig());
        configs.add(this.createYearlyPeriodConfig());

        return configs;
    }
}