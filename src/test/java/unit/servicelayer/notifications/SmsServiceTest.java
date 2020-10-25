package unit.servicelayer.notifications;

import datalayer.employee.EmployeeStorage;
import dto.SmsMessage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import servicelayer.customer.CustomerServiceException;
import servicelayer.employee.EmployeeService;
import servicelayer.employee.EmployeeServiceImpl;
import servicelayer.notifications.SmsService;
import org.junit.jupiter.api.Assertions.*;
import java.sql.SQLException;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SmsServiceTest {

    // SUT (System Under Test)
    private SmsService smsService;

    // DOC (Depended-on Component)


    @BeforeAll
    public void beforeAll(){
        smsService = mock(SmsService.class);
    }

    @Test
    public void mustCallStorageWhenCreatingCustomer() throws CustomerServiceException, SQLException {
        // Arrange
        // Act
        var recipient = "a";
        var message = "b";
        SmsMessage sms = new SmsMessage(recipient,message);
        when(smsService.sendSms(sms)).thenReturn(true);
        // Assert
        Assertions.assertTrue(smsService.sendSms(sms));
    }
}
