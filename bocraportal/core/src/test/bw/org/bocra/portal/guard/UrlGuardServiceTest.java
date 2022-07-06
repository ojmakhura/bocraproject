package bw.org.bocra.portal.authorisation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class AuthorisationServiceTest {
    
	@Mock
	private AuthorisationService authorisationService;
    
	@BeforeEach
    public void setupMock() {
       MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAuthorisationControllerCreation() {
        
    }
}
