package testAPI;

import Practice.OkHttp.EmployeeService;
import Practice.OkHttp.Model.Employee;
import Practice.ServiceDB.ServiceDB;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import testAPI.resolver.ProviderEmplService;
import testAPI.resolver.EmplServiceResolver;
import testAPI.resolver.ProviderDB;
import testAPI.resolver.ServiceDBResolver;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

@ExtendWith({EmplServiceResolver.class, ServiceDBResolver.class})
public class XClientsBusinessTests {

    private static final String CONNECTION_URL = "jdbc:postgresql://51.250.26.13/pg-x-clients-be";
    private static final String CONNECTION_USERNAME = "merionpg";
    private static final String CONNECTION_PASSWORD = "UZObS42{8>}>";

    private static final String LOGIN = "leonardo";
    private static final String PASSWORD = "leads";

    @Test
    public void testCreateEmployeeInCompany(@ProviderDB(URL = CONNECTION_URL, USERNAME = CONNECTION_USERNAME, PASSWORD = CONNECTION_PASSWORD) ServiceDB serviceDB,
                                            @ProviderEmplService(login = LOGIN, password = PASSWORD) EmployeeService serviceEmpl) throws IOException, SQLException {
        String firstName = "Иванов";
        String lastName = "Иван";
        String phone = "89994562245";

        int companyID = serviceEmpl.getCompanyID();
        int emplID = serviceEmpl.addEmployee(firstName, lastName, phone);
        ResultSet allEmployee = serviceDB.getAllEmployeeInCompany(companyID);

        assertTrue(allEmployee.next());
        assertEquals(emplID, allEmployee.getInt("id"));
        assertEquals(firstName, allEmployee.getString("first_name"));
        assertEquals(lastName, allEmployee.getString("last_name"));
        assertEquals(phone, allEmployee.getString("phone"));
        assertEquals(companyID, allEmployee.getInt("company_id"));
        assertFalse(allEmployee.next());
    }

    @Test
    public void testGetAllEmployeeInCompany(@ProviderDB(URL = CONNECTION_URL, USERNAME = CONNECTION_USERNAME, PASSWORD = CONNECTION_PASSWORD) ServiceDB serviceDB,
                                            @ProviderEmplService(login = LOGIN, password = PASSWORD) EmployeeService serviceEmpl) throws SQLException, IOException {
        String firstName = "Иванов";
        String lastName = "Иван";
        String phone = "89994562245";

        int companyID = serviceEmpl.getCompanyID();
        int emplID = serviceDB.createEmployee(companyID, firstName, lastName, phone);

        List<Employee> emplAPI = serviceEmpl.getAllEmployee();

        assertEquals(1, emplAPI.size());
        assertEquals(emplID, emplAPI.getFirst().id());
        assertEquals(firstName, emplAPI.getFirst().firstName());
        assertEquals(lastName, emplAPI.getFirst().lastName());
        assertEquals(phone, emplAPI.getFirst().phone());
        assertEquals(companyID, emplAPI.getFirst().companyId());
        assertTrue(emplAPI.getFirst().isActive());
    }

    @Test
    public void testDeactivationEmployee(@ProviderDB(URL = CONNECTION_URL, USERNAME = CONNECTION_USERNAME, PASSWORD = CONNECTION_PASSWORD) ServiceDB serviceDB,
                                         @ProviderEmplService(login = LOGIN, password = PASSWORD) EmployeeService serviceEmpl) throws IOException, SQLException {
        String firstName = "Иванов";
        String lastName = "Иван";
        String phone = "89994562245";

        int companyID = serviceEmpl.getCompanyID();
        int emplID = serviceDB.createEmployee(companyID, firstName, lastName, phone);
        int statusCode = serviceEmpl.deactivationEmployee(emplID);

        assertEquals(200, statusCode);
        assertFalse(Boolean.parseBoolean(serviceDB.getEmployeeIsActive(emplID)));
    }

    @Test
    public void testUpdateEmailEmployee(@ProviderDB(URL = CONNECTION_URL, USERNAME = CONNECTION_USERNAME, PASSWORD = CONNECTION_PASSWORD) ServiceDB serviceDB,
                                        @ProviderEmplService(login = LOGIN, password = PASSWORD) EmployeeService serviceEmpl) throws IOException, SQLException {
        String firstName = "Иванов";
        String lastName = "Иван";
        String phone = "89994562245";
        String newEmail = "test@mail.ru";

        int companyID = serviceEmpl.getCompanyID();
        int emplID = serviceDB.createEmployee(companyID,firstName,lastName,phone);
        int statusCode = serviceEmpl.updateEmail(emplID, newEmail);

        assertEquals(200, statusCode);
        assertEquals(newEmail, serviceDB.getEmployeeEmail(emplID));
    }

}
