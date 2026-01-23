package Practice.ServiceDB;

import java.sql.*;

public class ServiceDB {

    private final Connection connection;

    public ServiceDB(String CONNECTION_URL, String USERNAME, String PASSWORD) throws SQLException {
        connection = DriverManager.getConnection(CONNECTION_URL, USERNAME, PASSWORD);
    }

    public int createCompany(String companyName, String description ) throws SQLException {
        String sql = "insert into company(name, description) values(?, ?)";

        PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, companyName);
        preparedStatement.setString(2, description);
        preparedStatement.executeUpdate();

        ResultSet result = preparedStatement.getGeneratedKeys();
        result.next();
        return result.getInt("id");
    }

    public int createEmployee(int companyID, String firstName, String lastName, String phone) throws SQLException {
        String sql = "insert into employee(company_id, first_name, last_name, phone) values(?, ?, ?, ?)";

        PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setInt(1, companyID);
        preparedStatement.setString(2, firstName);
        preparedStatement.setString(3, lastName);
        preparedStatement.setString(4, phone);
        preparedStatement.executeUpdate();

        ResultSet result = preparedStatement.getGeneratedKeys();
        result.next();
        return result.getInt("id");
    }

    public ResultSet getAllEmployeeInCompany(int companyID) throws SQLException {
        String sql = "select * from employee where company_id = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, companyID);
        return preparedStatement.executeQuery();
    }

    public ResultSet getEmployee(int employeeID) throws SQLException {
        String sql = "select * from employee where id = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, employeeID);
        return preparedStatement.executeQuery();
    }

    public String getEmployeeFirstName(int employeeID) throws SQLException {
        String sql = "select first_name from employee where id = ";

        ResultSet result = connection.createStatement().executeQuery(sql + String.valueOf(employeeID));
        result.next();
        return result.getString("first_name");
    }

    public String getEmployeeLastName(int employeeID) throws SQLException {
        String sql = "select last_name from employee where id = ";

        ResultSet result = connection.createStatement().executeQuery(sql + String.valueOf(employeeID));
        result.next();
        return result.getString("last_name");
    }

    public String getEmployeePhone(int employeeID) throws SQLException {
        String sql = "select phone from employee where id = ";

        ResultSet result = connection.createStatement().executeQuery(sql + String.valueOf(employeeID));
        result.next();
        return result.getString("phone");
    }

    public String getEmployeeCompanyID(int employeeID) throws SQLException {
        String sql = "select company_id from employee where id = ";

        ResultSet result = connection.createStatement().executeQuery(sql + String.valueOf(employeeID));
        result.next();
        return result.getString("company_id");
    }

    public String getEmployeeIsActive(int employeeID) throws SQLException {
        String sql = "select is_active from employee where id = ";

        ResultSet result = connection.createStatement().executeQuery(sql + String.valueOf(employeeID));
        result.next();
        return result.getString("is_active");
    }

    public String getEmployeeEmail(int employeeID) throws SQLException {
        String sql = "select email from employee where id = ";

        ResultSet result = connection.createStatement().executeQuery(sql + String.valueOf(employeeID));
        result.next();
        return result.getString("email");
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

    public void deleteEmployee(int employeeID) throws SQLException {
        String sql = "delete from employee where id = ";
        connection.createStatement().executeUpdate(sql + String.valueOf(employeeID));
    }

    public void closeConnection() throws SQLException {
        connection.close();
    }

}
