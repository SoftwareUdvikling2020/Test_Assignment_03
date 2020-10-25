package main;

import datalayer.booking.BookingStorage;
import datalayer.booking.BookingStorageImpl;
import datalayer.employee.EmployeeStorage;
import datalayer.employee.EmployeeStorageImpl;
import dto.BookingCreation;
import dto.Customer;
import datalayer.customer.CustomerStorageImpl;
import dto.Employee;
import servicelayer.booking.BookingService;
import servicelayer.booking.BookingServiceImpl;
import servicelayer.employee.EmployeeService;
import servicelayer.employee.EmployeeServiceImpl;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;

public class Main {

    private static final String conStr = "jdbc:mysql://localhost:3306/DemoApplication";
    private static final String user = "root";
    private static final String pass = "root";

    public static void main(String[] args) throws SQLException {
        CustomerStorageImpl storage = new CustomerStorageImpl(conStr, user, pass);
        EmployeeStorageImpl empstorage = new EmployeeStorageImpl(conStr, user, pass);
        BookingStorageImpl bookstorage = new BookingStorageImpl(conStr, user, pass);


        System.out.println("Got customers: ");
        for(Customer c : storage.getCustomers()) {
            System.out.println(toString(c));
        }
        System.out.println("The end.");

        EmployeeServiceImpl es = new EmployeeServiceImpl(empstorage);
        es.createEmployee("mathias", "kristensen");

        empstorage.getEmployees().forEach(employee -> System.out.println(employee.getFirstname()));

        BookingServiceImpl bs = new BookingServiceImpl(bookstorage);
        bs.createBooking(1, 3,new Date(System.currentTimeMillis()), new Time(System.currentTimeMillis()), new Time(System.currentTimeMillis()+1000000));

        bookstorage.getBookingsForCustomer(1).forEach(booking -> System.out.println(booking.getDate()));

    }

    public static String toString(Customer c) {
        return "{" + c.getId() + ", " + c.getFirstname() + ", " + c.getLastname() + "}";
    }
}
