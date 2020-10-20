package dto;

import java.sql.Date;
import java.sql.Time;


public class BookingCreation {
    private final int customerId, employeeId;
    private final Date date;
    private final Time startTime, endTime;


    public BookingCreation(int customerId, int employeeId, Date date, Time startTime, Time endTime) {
        this.customerId = customerId;
        this.employeeId = employeeId;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }


    public int getCustomerId() {
        return customerId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public Date getDate() {
        return date;
    }

    public Time getStartTime() {
        return startTime;
    }

    public Time getEndTime() {
        return endTime;
    }
}
