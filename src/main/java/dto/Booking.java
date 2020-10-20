package dto;

import java.sql.Time;
import java.util.Date;

public class Booking {

    private final int id, customerId, employeeId;
    private final Date date;
    private final Time startTime, endTime;


    public Booking(int id, int customerId, int employeeId, Date date, Time startTime, Time endTime) {
        this.id = id;
        this.customerId = customerId;
        this.employeeId = employeeId;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getId() {
        return id;
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
