package bw.org.bocra.portal.period;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import bw.org.bocra.portal.period.config.PeriodConfigTestData;
import bw.org.bocra.portal.period.config.PeriodConfigVO;

@Component
public class PeriodTestData {
    private final PeriodRestController periodRestController;
    private final PeriodConfigTestData periodConfigTestData;

    public PeriodTestData(PeriodRestController periodRestController, PeriodConfigTestData periodConfigTestData) {
        this.periodRestController = periodRestController;
        this.periodConfigTestData = periodConfigTestData;
    }

    public PeriodRestController getPeriodRestController() {
        return periodRestController;
    }
    
    public  PeriodVO createUnsavedPeriod() {
        PeriodConfigVO config = periodConfigTestData.createMonthlyPeriodConfig();

        PeriodVO period = createUnsavedPeriodNoConfig();

        period.setPeriodConfig(config);

        return period;
    }
    
    public PeriodVO createUnsavedPeriodNoConfig() {

        PeriodVO period = new PeriodVO();

        period.setPeriodConfig(null);
        period.setCreatedBy("testuser4");
        period.setCreatedDate(LocalDateTime.now());
        period.setPeriodName("Test Type ");

        return period;
    }
    
    public PeriodVO createUnsavedPeriodUnsavedType() {

        PeriodVO period = createUnsavedPeriodNoConfig();
        period.setPeriodConfig(periodConfigTestData.createUnsavedPeriodConfig());

        return period;
    }

    public PeriodVO create() {
        return (PeriodVO)periodRestController.save(createUnsavedPeriod()).getBody();
    }

    public Collection<PeriodVO> generateSequentialData(int size) {

        return generateUnsavedSequentialData(size)
            .stream()
            .map(period -> (PeriodVO)periodRestController.save(period).getBody())
            .collect(Collectors.toList());

    }

    public Collection<PeriodVO> generateSequentialMonthlyPeriods(int size) {

        PeriodVO period = new PeriodVO();
        period.setPeriodConfig(periodConfigTestData.createMonthlyPeriodConfig());
        period.setCreatedBy("testuser4");
        period.setCreatedDate(LocalDateTime.now());
        
        LocalDate.of(LocalDate.now().getYear(), 1, 1);
        Collection<PeriodVO> periods = new ArrayList<>();


        return periods;
    }

    public Collection<PeriodVO> generateSequentialQuarterlyPeriods(int size) {
        Collection<PeriodVO> periods = new ArrayList<>();

        return periods;
    }

    public Collection<PeriodVO> generateSequentialYearlyPeriods(int size) {
        Collection<PeriodVO> periods = new ArrayList<>();

        return periods;
    }

    public Collection<PeriodVO> generateUnsavedSequentialData(int size) {
        Collection<PeriodVO> periods = new ArrayList<>();
        PeriodConfigVO config = periodConfigTestData.generateSequentialData(1).iterator().next();

        for (int i = 1; i <= size; i++) {

            PeriodVO period = new PeriodVO();

            period.setPeriodConfig(config);
            period.setCreatedBy("testuser4");
            period.setCreatedDate(LocalDateTime.now());
            period.setPeriodName("Test Type " + i);
            // period.set("/test" + i);

            periods.add(period);
            
        }

        return periods;
    }
    
    public Collection<PeriodVO> generateSearchData() {
        
        PeriodConfigVO config = periodConfigTestData.generateSequentialData(1).iterator().next();
        Collection<PeriodVO> data = new ArrayList<>();

        PeriodVO period = new PeriodVO();

        period.setPeriodConfig(config);
        period.setCreatedBy("testuser4");
        period.setCreatedDate(LocalDateTime.now());
        period.setPeriodName("Test");
        // period.setUrl("/test");
        data.add((PeriodVO) periodRestController.save(period).getBody());

        period = new PeriodVO();

        period.setPeriodConfig(config);
        period.setCreatedBy("testuser4");
        period.setCreatedDate(LocalDateTime.now());
        period.setPeriodName("aceess");
        // period.setUrl("/aceess");
        data.add((PeriodVO) periodRestController.save(period).getBody());

        period = new PeriodVO();

        period.setPeriodConfig(config);
        period.setCreatedBy("testuser4");
        period.setCreatedDate(LocalDateTime.now());
        period.setPeriodName("authorisation");
        // period.setUrl("/authorisation");
        data.add((PeriodVO) periodRestController.save(period).getBody());

        period = new PeriodVO();

        period.setPeriodConfig(config);
        period.setCreatedBy("testuser4");
        period.setCreatedDate(LocalDateTime.now());
        period.setPeriodName("access");
        // period.setUrl("/access");
        data.add((PeriodVO) periodRestController.save(period).getBody());

        period = new PeriodVO();

        period.setPeriodConfig(config);
        period.setCreatedBy("testuser4");
        period.setCreatedDate(LocalDateTime.now());
        period.setPeriodName("Access Point Type");
        // period.setUrl("/access/config");
        data.add((PeriodVO) periodRestController.save(period).getBody());

        period = new PeriodVO();

        period.setPeriodConfig(config);
        period.setCreatedBy("testuser4");
        period.setCreatedDate(LocalDateTime.now());
        period.setPeriodName("Licensee");
        // period.setUrl("/licensee");
        data.add((PeriodVO) periodRestController.save(period).getBody());

        period = new PeriodVO();

        period.setPeriodConfig(config);
        period.setCreatedBy("testuser4");
        period.setCreatedDate(LocalDateTime.now());
        period.setPeriodName("Licence");
        // period.setUrl("/licence");
        data.add((PeriodVO) periodRestController.save(period).getBody());

        period = new PeriodVO();

        period.setPeriodConfig(config);
        period.setCreatedBy("testuser4");
        period.setCreatedDate(LocalDateTime.now());
        period.setPeriodName("Licence Type");
        // period.setUrl("/licence/config");
        data.add((PeriodVO) periodRestController.save(period).getBody());

        period = new PeriodVO();
        period.setPeriodConfig(config);
        period.setCreatedBy("testuser4");
        period.setCreatedDate(LocalDateTime.now());
        period.setPeriodName("Licence Type Form");
        // period.setUrl("/licence/config/form");
        data.add((PeriodVO) periodRestController.save(period).getBody());

        period = new PeriodVO();
        period.setPeriodConfig(config);
        period.setCreatedBy("testuser4");
        period.setCreatedDate(LocalDateTime.now());
        period.setPeriodName("Licence Type Sector");
        // period.setUrl("/licence/config/sector");
        data.add((PeriodVO) periodRestController.save(period).getBody());

        period = new PeriodVO();
        period.setPeriodConfig(config);
        period.setCreatedBy("testuser4");
        period.setCreatedDate(LocalDateTime.now());
        period.setPeriodName("Licence Type Form Field");
        // period.setUrl("/licence/config/form/field");
        data.add((PeriodVO) periodRestController.save(period).getBody());

        return data;
    }
}
