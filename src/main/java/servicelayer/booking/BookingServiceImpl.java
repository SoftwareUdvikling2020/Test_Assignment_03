package servicelayer.booking;

import datalayer.booking.BookingStorage;
import dto.Booking;
import dto.BookingCreation;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Collection;

public class BookingServiceImpl implements BookingService {
    private BookingStorage storage;

    public BookingServiceImpl(BookingStorage storage) {
        this.storage = storage;
    }

    @Override
    public int createBooking(int customerId, int employeeId, Date date, Time start, Time end) throws SQLException {
        return storage.createBooking(new BookingCreation(customerId, employeeId, date, start, end));
    }

    @Override
    public Collection<Booking> getBookingsForCustomer(int customerId) throws SQLException {
        return storage.getBookingsForCustomer(customerId);
    }

    @Override
    public Collection<Booking> getBookingsForEmployee(int employeeId) throws SQLException {
        return storage.getBookingsForEmployee(employeeId);
    }
}
