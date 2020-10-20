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
    public int createEmployee(Employee employee) throws SQLException {
        return storage.createEmployee(new EmployeeCreation(employee.getFirstname(), employee.getLastname()));
    }

    @Override
    public Employee getEmployeeById(int employeeId) throws SQLException {
        return storage.getEmployeeWithId(employeeId);
    }
}
