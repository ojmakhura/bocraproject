// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by SpringService.vsl in andromda-spring-cartridge on $springUtils.date. Do not modify by hand!.
//
package bw.org.bocra.portal.auth;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class AccessControlTest {

    protected Logger logger = LoggerFactory.getLogger(AccessControlTest.class);
    
    @InjectMocks
    private AccessControlImpl accessControl;


    @Test
    public void checkInjects() {

        Assertions.assertNotNull(accessControl);

    }
    

    @Test
    public void authorised_success() {
        
    }

    @Test
    public void authorised_fail() {
        
    }

    @Test
    public void authorised_url_null() {
        
    }

}