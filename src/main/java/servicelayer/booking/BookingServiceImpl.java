package servicelayer.booking;

import datalayer.booking.BookingStorage;
import dto.Booking;

import java.sql.Date;
import java.sql.Time;
import java.util.Collection;

public class BookingServiceImpl implements BookingService {
    private BookingStorage storage;

    public BookingServiceImpl(BookingStorage storage) {
        this.storage = storage;
    }

    @Override
    public int createBooking(int customerId, int employeeId, Date date, Time start, Time end) {
        storage.
    }

    @Override
    public Collection<Booking> getBookingsForCustomer(int customerId) {
        return null;
    }

    @Override
    public Collection<Booking> getBookingsForEmployee(int employeeId) {
        return null;
    }
}
