package servicelayer.employee;

import datalayer.employee.EmployeeStorage;
import dto.Employee;
import dto.EmployeeCreation;

import java.sql.SQLException;

public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeStorage storage;

    public EmployeeServiceImpl(EmployeeStorage storage) {
        this.storage = storage;
    }


    @Override
    public int createEmployee(String firstname, String lastname) throws SQLException {
        return storage.createEmployee(new EmployeeCreation(firstname, lastname));
    }

    @Override
    public Employee getEmployeeById(int employeeId) throws SQLException {
        return storage.getEmployeeWithId(employeeId);
    }
}
