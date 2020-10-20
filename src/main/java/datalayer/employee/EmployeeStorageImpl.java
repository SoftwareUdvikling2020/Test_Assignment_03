package datalayer.employee;

import dto.Employee;
import dto.EmployeeCreation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeStorageImpl implements EmployeeStorage {

    private String connectionString;
    private String username, password;

    public EmployeeStorageImpl(String conStr, String user, String pass){
        connectionString = conStr;
        username = user;
        password = pass;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(connectionString, username, password);
    }

    @Override
    public Employee getEmployeeWithId(int employeeId) throws SQLException {
        var sql = "select ID, firstname, lastname, birthdate from Employee where id = ?";
        try (var con = getConnection();
             var stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, employeeId);

            try (var resultSet = stmt.executeQuery()) {
                if (resultSet.next()){
                    var id = resultSet.getInt("ID");
                    var firstname = resultSet.getString("firstname");
                    var lastname = resultSet.getString("lastname");
                    var birthdate = resultSet.getDate("birthdate");
                    return new Employee(id, firstname, lastname, birthdate);
                }
                return null;
            }
        }
    }

    @Override
    public List<Employee> getEmployees() throws SQLException {
        try (var con = getConnection();
             var stmt = con.createStatement()) {
            var results = new ArrayList<Employee>();

            ResultSet resultSet = stmt.executeQuery("select ID, firstname, lastname from Employee");

            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String firstname = resultSet.getString("firstname");
                String lastname = resultSet.getString("lastname");
                Date birthdate = resultSet.getDate("birthdate");

                Employee employee = new Employee(id, firstname, lastname, birthdate);
                results.add(employee);
            }

            return results;
        }
    }

    public int createEmployee(EmployeeCreation emplyeeToCreate) throws SQLException {
        var sql = "insert into Employee(firstname, lastname) values (?, ?)";
        try (var con = getConnection();
             var stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, emplyeeToCreate.getFirstname());
            stmt.setString(2, emplyeeToCreate.getLastname());

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
