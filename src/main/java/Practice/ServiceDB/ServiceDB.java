package Practice.ServiceDB;

import java.sql.*;

public class ServiceDB {

    private final String CONNECTION_URL = "jdbc:postgresql://51.250.26.13/pg-x-clients-be";
    private final String USERNAME = "merionpg";
    private final String PASSWORD = "UZObS42{8>}>";

    public int addCompany(String companyName, String description ) throws SQLException {
        Connection connection = DriverManager.getConnection(CONNECTION_URL, USERNAME, PASSWORD);

        String sql = "insert into company(name, description) values(?, ?)";

        PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, companyName);
        preparedStatement.setString(2, description);
        preparedStatement.executeUpdate();

        ResultSet result = preparedStatement.getGeneratedKeys();
        result.next();
        connection.close();
        return result.getInt("id");
    }

    public int addEmployee(int companyID, String firstName, String lastName, String phone) throws SQLException {
        Connection connection = DriverManager.getConnection(CONNECTION_URL, USERNAME, PASSWORD);

        String sql = "insert into employee(company_id, first_name, last_name, phone) values(?, ?, ?, ?)";

        PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setInt(1, companyID);
        preparedStatement.setString(2, firstName);
        preparedStatement.setString(3, lastName);
        preparedStatement.setString(4, phone);
        preparedStatement.executeUpdate();

        ResultSet result = preparedStatement.getGeneratedKeys();
        result.next();
        connection.close();
        return result.getInt("id");
    }

    public String getEmployeeFirstName(int employeeID) throws SQLException {
        Connection connection = DriverManager.getConnection(CONNECTION_URL, USERNAME, PASSWORD);

        String sql = "select first_name from employee where id = ";

        ResultSet result = connection.createStatement().executeQuery(sql + String.valueOf(employeeID));
        result.next();
        connection.close();
        return result.getString("first_name");
    }

    public String getEmployeeLastName(int employeeID) throws SQLException {
        Connection connection = DriverManager.getConnection(CONNECTION_URL, USERNAME, PASSWORD);

        String sql = "select last_name from employee where id = ";

        ResultSet result = connection.createStatement().executeQuery(sql + String.valueOf(employeeID));
        result.next();
        connection.close();
        return result.getString("last_name");
    }

    public String getEmployeePhone(int employeeID) throws SQLException {
        Connection connection = DriverManager.getConnection(CONNECTION_URL, USERNAME, PASSWORD);

        String sql = "select phone from employee where id = ";

        ResultSet result = connection.createStatement().executeQuery(sql + String.valueOf(employeeID));
        result.next();
        connection.close();
        return result.getString("phone");
    }

    public String getEmployeeCompanyID(int employeeID) throws SQLException {
        Connection connection = DriverManager.getConnection(CONNECTION_URL, USERNAME, PASSWORD);

        String sql = "select company_id from employee where id = ";

        ResultSet result = connection.createStatement().executeQuery(sql + String.valueOf(employeeID));
        result.next();
        connection.close();
        return result.getString("company_id");
    }

    public void deleteCompany(int companyID) throws SQLException {
        Connection connection = DriverManager.getConnection(CONNECTION_URL, USERNAME, PASSWORD);

        String sql = "delete from company where id = ";
        connection.createStatement().executeUpdate(sql + String.valueOf(companyID));
        connection.close();
    }

    public void deleteEmployee(int employeeID) throws SQLException {
        Connection connection = DriverManager.getConnection(CONNECTION_URL, USERNAME, PASSWORD);

        String sql = "delete from employee where id = ";
        connection.createStatement().executeUpdate(sql + String.valueOf(employeeID));
        connection.close();
    }

}
