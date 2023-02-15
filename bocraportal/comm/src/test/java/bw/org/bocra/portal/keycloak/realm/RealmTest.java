package bw.org.bocra.portal.keycloak.realm;

import org.junit.ClassRule;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.PostgreSQLContainer;

import bw.org.bocra.portal.CommTestContainer;
import jakarta.transaction.Transactional;

@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@Transactional
public class RealmTest {
    
    @ClassRule
    public static PostgreSQLContainer postgreSQLContainer = CommTestContainer.getInstance();

    @Autowired
    private RealmRepository realmRepository;

    @Test
    public void test() {

    }
}
