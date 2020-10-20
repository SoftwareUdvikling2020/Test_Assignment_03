package servicelayer.employee;
import dto.*;

import java.util.Collection;

public interface EmployeeService {


    int createEmployee(Employee employee);
    Employee getEmployeeById(int employeeId);
}
