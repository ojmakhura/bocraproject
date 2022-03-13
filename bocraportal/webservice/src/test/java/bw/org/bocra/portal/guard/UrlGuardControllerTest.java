package bw.org.bocra.portal.guard;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UrlGuardControllerTest {
    
    @Mock
    private UrlGuardDao urlGuardDao;

    @Mock
    private UrlGuardRepository urlGuardRepository;

	@InjectMocks
	private UrlGuardServiceImpl urlGuardService = new UrlGuardServiceImpl();

	@BeforeEach
    public void setupMock() {
       MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUrlGuardControllerCreation() {
        assertNotNull(urlGuardService, "UrlGuardService injection failed!");
        //assertNotNull(urlGuardService.getUrlGuardDao(), "Unable to mock UrlGuardDao injection failed!");
        //assertNotNull(urlGuardService.getUrlGuardRepository(), "Unable to mock UrlGuardRepository injection failed!");
    }

    // @Test
    // public void createUrlGuardTest() {
    //     UrlGuardVO urlGuardVO = new UrlGuardVO();
    //     urlGuardVO.setId(1l);
    //     urlGuardVO.setUrl("/test1");
    //     urlGuardVO.setType(UrlGuardType.BUTTON);

    //     urlGuardVO = urlGuardService.save(urlGuardVO);
    //     assertNotNull(urlGuardVO, "Failed to save URL guard");

    //     assertNotNull(urlGuardVO.getId(), "Failed to save URL guard");
    // }
}
