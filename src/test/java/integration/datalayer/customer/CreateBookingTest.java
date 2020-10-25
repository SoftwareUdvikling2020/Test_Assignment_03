package integration.datalayer.customer;


import datalayer.booking.BookingStorage;
import datalayer.booking.BookingStorageImpl;
import dto.BookingCreation;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.FluentConfiguration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Tag("integration")
public class CreateBookingTest {
    private BookingStorage bookingStorage;

    @BeforeAll
    public void Setup() throws SQLException {
        var url = "jdbc:mysql://localhost:3307/";
        var db = "DemoApplicationTest";

        Flyway flyway = new Flyway(new FluentConfiguration()
                .defaultSchema(db)
                .createSchemas(true)
                .schemas(db)
                .target("3")
                .dataSource(url, "root", "testuser123"));

        flyway.migrate();

        bookingStorage = new BookingStorageImpl(url+db, "root", "testuser123");
    }

    @Test
    public void mustSaveBookingInDatabaseWhenCallingCreateBooking() throws SQLException {
        // Arrange
        // Act
        bookingStorage.createBooking(new BookingCreation(1, 1,null,null,null));
        var booking = bookingStorage.getBookingsForCustomer(1);
        // Assert
        assertTrue(
                booking.stream().anyMatch(x ->
                        x.getEmployeeId() == 1 &&
                                x.getCustomerId() == 1));
    }

    @Test
    public void mustReturnLatestId() throws SQLException {
        // Arrange
        // Act
        var bday = new Date(1239821l);
        var id1 = bookingStorage.createBooking(new BookingCreation(2, 2,bday,new Time(1111111),new Time(111111)));
        var id2 = bookingStorage.createBooking(new BookingCreation(1, 1,bday,new Time(111111),new Time(111111)));

        // Assert
        assertEquals(1, id2 - id1);
    }
}
