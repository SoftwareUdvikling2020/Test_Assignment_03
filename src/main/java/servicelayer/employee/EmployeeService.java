package servicelayer.employee;
import dto.*;

import java.sql.SQLException;
import java.util.Collection;

public interface EmployeeService {


    int createEmployee(String firstname, String lastname) throws SQLException;
    Employee getEmployeeById(int employeeId) throws SQLException;
}
