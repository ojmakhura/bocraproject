package bw.org.bocra.portal.guard;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class UrlGuardServiceTest {
    
	@Mock
	private UrlGuardService urlGuardService;
    
	@BeforeEach
    public void setupMock() {
       MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUrlGuardControllerCreation() {
        
    }
}
