package datalayer.booking;

import dto.Booking;
import dto.BookingCreation;
import dto.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BookingStorageImpl implements BookingStorage {
    private String connectionString;
    private String username, password;

    public BookingStorageImpl(String conStr, String user, String pass) {
        connectionString = conStr;
        username = user;
        password = pass;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(connectionString, username, password);
    }


    @Override
    public List<Booking> getBookingsForCustomer(int cId) throws SQLException {
        var sql = "select ID, customerId, employeeId, date, start, end from Bookings where customerId = ?";
        try (var con = getConnection();
             var stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, cId);

            var results = new ArrayList<Booking>();
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {

                var id = resultSet.getInt("ID");
                var customerId = resultSet.getInt("customerId");
                var employeeId = resultSet.getInt("employeeId");
                var date = resultSet.getDate("date");
                var start = resultSet.getTime("start");
                var end = resultSet.getTime("end");
                results.add(new Booking(id, customerId, employeeId, date, start, end));

            }
            return results;
        }

    }

    @Override
    public Collection<Booking> getBookingsForEmployee(int eId) throws SQLException {
        var sql = "select ID, customerId, employeeId, date, start, end from Bookings where employeeId = ?";
        try (var con = getConnection();
             var stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, eId);

            var results = new ArrayList<Booking>();
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {

                var id = resultSet.getInt("ID");
                var customerId = resultSet.getInt("customerId");
                var employeeId = resultSet.getInt("employeeId");
                var date = resultSet.getDate("date");
                var start = resultSet.getTime("start");
                var end = resultSet.getTime("end");
                results.add(new Booking(id, customerId, employeeId, date, start, end));

            }
            return results;
        }
    }

    @Override
    public int createBooking(BookingCreation bookingCreation) throws SQLException {
        var sql = "insert into Customers(customerId, employeeId,date,start,end) values (?,?,?,?,?)";
        try (var con = getConnection();
             var stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, bookingCreation.getCustomerId());
            stmt.setInt(2, bookingCreation.getEmployeeId());
            stmt.setDate(3, bookingCreation.getDate());
            stmt.setTime(4, bookingCreation.getStartTime());
            stmt.setTime(5, bookingCreation.getEndTime());

            stmt.executeUpdate();

            // get the newly created id
            try (var resultSet = stmt.getGeneratedKeys()) {
                resultSet.next();
                int newId = resultSet.getInt(1);
                return newId;
            }
        }
    }
}
