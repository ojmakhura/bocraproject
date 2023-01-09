// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by SpringService.vsl in andromda-spring-cartridge on $springUtils.date. Do not modify by hand!.
//
package bw.org.bocra.portal.message;

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
public class CommunicationMessageServiceTest {

    protected Logger logger = LoggerFactory.getLogger(CommunicationMessageServiceTest.class);
    
    @InjectMocks
    private CommunicationMessageServiceImpl communicationMessageService;

    @Mock
    private CommunicationMessageDao communicationMessageDao;

    @Mock
    private CommunicationMessageRepository communicationMessageRepository;

    @Test
    public void checkInjects() {

        Assertions.assertNotNull(communicationMessageDao);
        Assertions.assertNotNull(communicationMessageRepository);
        Assertions.assertNotNull(communicationMessageService);

    }
    

    @Test
    public void findById_success() {
        
    }

    @Test
    public void findById_fail() {
        
    }


    @Test
    public void getAll_success() {
        
    }

    @Test
    public void getAll_fail() {
        
    }

    @Test
    public void remove_success() {
        
    }

    @Test
    public void remove_fail() {
        
    }


    @Test
    public void save_success() {
        
    }

    @Test
    public void save_fail() {
        
    }

    @Test
    public void save_communicationMessage_null() {
        
    }

    @Test
    public void save_CommunicationMessage_Id_null() {
        
    }

    @Test
    public void save_CommunicationMessage_CreatedBy_null() {
        
    }

    @Test
    public void save_CommunicationMessage_UpdatedBy_null() {
        
    }

    @Test
    public void save_CommunicationMessage_CreatedDate_null() {
        
    }

    @Test
    public void save_CommunicationMessage_UpdatedDate_null() {
        
    }

    @Test
    public void save_CommunicationMessage_Status_null() {
        
    }

    @Test
    public void save_CommunicationMessage_MessagePlatform_null() {
        
    }

    @Test
    public void save_CommunicationMessage_SendNow_null() {
        
    }

    @Test
    public void save_CommunicationMessage_Subject_null() {
        
    }

    @Test
    public void save_CommunicationMessage_Destinations_null() {
        
    }

    @Test
    public void save_CommunicationMessage_Source_null() {
        
    }

    @Test
    public void save_CommunicationMessage_Text_null() {
        
    }

    @Test
    public void save_CommunicationMessage_DispatchDate_null() {
        
    }
    
    @Test
    public void search_success() {
        
    }

    @Test
    public void search_fail() {
        
    }

    @Test
    public void search_criteria_null() {
        
    }


    @Test
    public void loadTodayMessages_success() {
        
    }

    @Test
    public void loadTodayMessages_fail() {
        
    }


    @Test
    public void clearSentMessages_success() {
        
    }

    @Test
    public void clearSentMessages_fail() {
        
    }


    @Test
    public void clearFailedMessages_success() {
        
    }

    @Test
    public void clearFailedMessages_fail() {
        
    }


    @Test
    public void loadDueSubmissionMessages_success() {
        
    }

    @Test
    public void loadDueSubmissionMessages_fail() {
        
    }


    @Test
    public void updateMessageStatus_success() {
        
    }

    @Test
    public void updateMessageStatus_fail() {
        
    }

    @Test
    public void updateMessageStatus_status_null() {
        
    }

}