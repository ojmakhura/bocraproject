package bw.org.bocra.portal.auth;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import bw.org.bocra.portal.auth.AuthorisationServiceImpl;

@ExtendWith(MockitoExtension.class)
public class AuthorisationControllerTest {
    
    @Mock
    private AuthorisationDao authorisationDao;

    @Mock
    private AuthorisationRepository authorisationRepository;

	@InjectMocks
	private AuthorisationServiceImpl authorisationService;

	@BeforeEach
    public void setupMock() {
       MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAuthorisationControllerCreation() {
        assertNotNull(authorisationService, "AuthorisationService injection failed!");
        //assertNotNull(authorisationService.getAuthorisationDao(), "Unable to mock AuthorisationDao injection failed!");
        //assertNotNull(authorisationService.getAuthorisationRepository(), "Unable to mock AuthorisationRepository injection failed!");
    }

    // @Test
    // public void createAuthorisationTest() {
    //     AuthorisationVO authorisationVO = new AuthorisationVO();
    //     authorisationVO.setId(1l);
    //     authorisationVO.setUrl("/test1");
    //     authorisationVO.setType(AuthorisationType.BUTTON);

    //     authorisationVO = authorisationService.save(authorisationVO);
    //     assertNotNull(authorisationVO, "Failed to save URL authorisation");

    //     assertNotNull(authorisationVO.getId(), "Failed to save URL authorisation");
    // }
}
