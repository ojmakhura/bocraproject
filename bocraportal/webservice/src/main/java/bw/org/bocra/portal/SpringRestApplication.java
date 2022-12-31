package bw.org.bocra.portal;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import bw.org.bocra.portal.period.PeriodService;
import bw.org.bocra.portal.period.config.PeriodConfigService;

@SpringBootApplication
@Import(SharedAutoConfiguration.class)
public class SpringRestApplication {

	private Logger log = LoggerFactory.getLogger(getClass());

	public static void main(String[] args) {
		SpringApplication.run(SpringRestApplication.class, args);
	}

	// @Bean
	// public ApplicationRunner applicationRunner(PeriodService periodService, PeriodConfigService periodConfigService) {
		
	// 	return (args) -> {

	// 		if(CollectionUtils.isEmpty(periodService.getAll())) {
	// 			log.info("Bocraportal system not initialised. Begin initialisation ....");
	// 			log.info("Initialising period configurations .... ");

	// 		}
	// 	};
	// }
}
