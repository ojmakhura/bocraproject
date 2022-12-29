package bw.org.bocra.portal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import bw.org.bocra.portal.security.CronSecurity;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
// @RunWith(SpringRunner.class)
@Slf4j
class BocracronApplicationTests {

	@Autowired
	private CronSecurity cronSecurity;

	// public BocracronApplicationTests(CronSecurity cronSecurity) {
	// 	this.cronSecurity = cronSecurity;
	// }
    
	// @Test
	// void contextLoads() {
	// }

	@Test
	void canGetToken() {
		log.info(cronSecurity.getAccessToken().getToken());
	}

}
