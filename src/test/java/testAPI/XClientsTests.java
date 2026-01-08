package testAPI;

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
public class XClientsTests {

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

}
