package FinishProject.core.serviceDB;

import FinishProject.core.model.Employee;

import java.sql.*;

public class ServiceDB {

    private final Connection connection;

    public ServiceDB(Connection connection) {
        this.connection = connection;
    }

    public int createCompany(String name, String description) throws SQLException {
        String sql = "insert into company(name, description) values(?, ?)";

        PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, description);
        preparedStatement.executeUpdate();

        ResultSet result = preparedStatement.getGeneratedKeys();
        result.next();
        return result.getInt("id");
    }

    public Employee getEmployeeByID(int employeeID) throws SQLException {
        String sql = "select * from employee where id = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, employeeID);
        ResultSet result = preparedStatement.executeQuery();
        result.next();

        return new Employee(result.getInt("id"),
                result.getBoolean("is_active"),
                result.getString("first_name"),
                result.getString("last_name"),
                result.getString("phone"),
                result.getInt("company_id"));
    }

    public ResultSet getAllEmployeeInCompany(int companyID) throws SQLException {
        String sql = "select * from employee where company_id = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, companyID);
        return preparedStatement.executeQuery();
    }

    public void deleteEmployee(int employeeID) throws SQLException {
        String sql = "delete from employee where id = ";
        connection.createStatement().executeUpdate(sql + String.valueOf(employeeID));
    }

    public void deleteCompany(int companyID) throws SQLException {
        String sql = "delete from company where id = ";

        if (getAllEmployeeInCompany(companyID).next()) {
            while (getAllEmployeeInCompany(companyID).next()) {
                ResultSet employee = getAllEmployeeInCompany(companyID);
                employee.next();
                deleteEmployee(employee.getInt("id"));
            }
        }

        connection.createStatement().executeUpdate(sql + String.valueOf(companyID));
    }

}
