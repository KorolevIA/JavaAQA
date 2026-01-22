package testAPI;

import Practice.OkHttp.CompanyService;
import Practice.OkHttp.EmployeeService;
import Practice.OkHttp.Model.Employee;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import testAPI.resolver.EmplService;
import testAPI.resolver.EmplServiceResolver;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

@ExtendWith(EmplServiceResolver.class)
public class XClientsBusinessTests {

    public final String CONNECTION_URL = "jdbc:postgresql://51.250.26.13/pg-x-clients-be";
    private final String CONNECTION_USERNAME = "merionpg";
    private final String CONNECTION_PASSWORD = "UZObS42{8>}>";

    private final String LOGIN = "leonardo";
    private final String PASSWORD = "leads";

    @Test
    public void testCreateEmployeeInCompany(@EmplService(login = LOGIN, password = PASSWORD) EmployeeService service) throws IOException {
        String firstName = "Иванов";
        String lastName = "Иван";
        String phone = "89994562245";

        int companyID = service.getCompanyID();
        int emplID = service.addEmployee(firstName, lastName, phone);
        List<Employee> listEmpl = service.getAllEmployee();

        assertEquals(1, listEmpl.size());
        assertEquals(emplID, listEmpl.getFirst().id());
        assertEquals(firstName, listEmpl.getFirst().firstName());
        assertEquals(lastName, listEmpl.getFirst().lastName());
        assertEquals(phone, listEmpl.getFirst().phone());
        assertEquals(companyID, listEmpl.getFirst().companyId());
        assertTrue(listEmpl.getFirst().isActive());
    }

    @Test
    public void testDeactivationEmployee(@EmplService(login = LOGIN, password = PASSWORD) EmployeeService service) throws IOException {
        String firstName = "Иванов";
        String lastName = "Иван";
        String phone = "89994562245";

        int emplID = service.addEmployee(firstName, lastName, phone);
        int statusCode = service.deactivationEmployee(emplID);
        Employee empl = service.getEmployeeByID(emplID);

        assertEquals(200, statusCode);
        assertFalse(empl.isActive());
    }

    @Test
    public void testUpdateEmailEmployee(@EmplService(login = LOGIN, password = PASSWORD) EmployeeService service) throws IOException {
        String firstName = "Иванов";
        String lastName = "Иван";
        String phone = "89994562245";
        String newEmail = "test@mail.ru";

        int emplID = service.addEmployee(firstName, lastName, phone);
        int statusCode = service.updateEmail(emplID, newEmail);
        Employee empl = service.getEmployeeByID(emplID);

        assertEquals(200, statusCode);
        assertEquals(newEmail, empl.email());
    }

    @Test
    public void testCreateEmployeeInNONCompany(@EmplService(login = LOGIN, password = PASSWORD) EmployeeService service) throws IOException {
        String firstName = "Иванов";
        String lastName = "Иван";
        String phone = "89994562245";

        int deletedCompanyID = new CompanyService(service.getToken()).getDeletedCompany();
        String response = service.addEmployeeInNewCompany(deletedCompanyID, firstName, lastName, phone);
        assertEquals("{\"statusCode\":404,\"message\":\"Non company\"}", response);
    }

}
