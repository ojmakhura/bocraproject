package bw.org.bocra.portal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan("bw.org.bocra.portal")
@ComponentScan({"bw.org.bocra.portal"})
@ConfigurationPropertiesScan
public class CommApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommApplication.class, args);
	}

}
