package bw.org.bocra.portal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BocracronApplication {

	public static void main(String[] args) {
		SpringApplication.run(BocracronApplication.class, args);
	}

}
