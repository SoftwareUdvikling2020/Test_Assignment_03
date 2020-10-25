package integration.servicelayer.customer;

import datalayer.booking.BookingStorage;
import datalayer.booking.BookingStorageImpl;
import datalayer.customer.CustomerStorage;
import datalayer.customer.CustomerStorageImpl;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.FluentConfiguration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;
import servicelayer.booking.BookingService;
import servicelayer.booking.BookingServiceImpl;
import servicelayer.customer.CustomerService;
import servicelayer.customer.CustomerServiceException;
import servicelayer.customer.CustomerServiceImpl;

import java.sql.SQLException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Testcontainers
public class SvcCreateBookingTest {

    private BookingService svc;
    private BookingStorage storage;

    private static final int PORT = 3306;
    private static final String PASSWORD = "testuser1234";

    @Container
    public static MySQLContainer mysql = (MySQLContainer) new MySQLContainer(DockerImageName.parse("mysql"))
            .withPassword(PASSWORD)
            .withExposedPorts(PORT);

    @BeforeAll
    public void setup() {
        System.err.println("mysql created: " + mysql.isCreated());
        System.err.println("mysql running: " + mysql.isRunning());
        System.err.println("mysql host: " + mysql.getHost());
        String url = "jdbc:mysql://"+mysql.getHost()+":"+mysql.getFirstMappedPort()+"/";
        String db = "DemoApplicationTest";
        Flyway flyway = new Flyway(
                new FluentConfiguration()
                        .schemas(db)
                        .defaultSchema(db)
                        .createSchemas(true)
                        .target("3")
                        .dataSource(url, "root", PASSWORD)
        );
        flyway.migrate();

        storage = new BookingStorageImpl(url + db,"root", PASSWORD);
        svc = new BookingServiceImpl(storage);
    }

    @Test
    public void mustSaveBookingToDatabaseWhenCallingCreateBooking() throws  SQLException {

        // Arrange
        var customerId = 1;
        svc.createBooking(customerId, 1, null,null,null);

        // Act
        var createdCustomer = storage.getBookingsForCustomer(customerId);

        // Assert
        assertEquals(1, createdCustomer.size());
    }
}
