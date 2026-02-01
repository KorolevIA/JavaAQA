package FinishProject.test.testUI;

import FinishProject.core.fixture.EmployeeServiceResolver;
import FinishProject.core.fixture.ServiceDbResolver;
import FinishProject.core.model.Employee;
import FinishProject.core.serviceAPI.EmployeeService;
import FinishProject.core.serviceDB.ServiceDB;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.validation.constraints.Positive;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith({EmployeeServiceResolver.class, ServiceDbResolver.class})
public class EmployeeTest {

    @Test
    @DisplayName("Проверка создания сотрудника в команию")
    @Positive
    public void testCreateEmployee(ServiceDB dbService, EmployeeService emplService) throws IOException, SQLException {
        Faker faker = new Faker(Locale.ENGLISH);

        int companyID = emplService.getCompanyID();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String phone = faker.phoneNumber().cellPhone();

        int emplID = emplService.createEmployee(firstName, lastName, phone);
        Employee empl = dbService.getEmployeeByID(emplID);

        assertEquals(companyID, empl.companyID());
        assertEquals(firstName, empl.firstName());
        assertEquals(lastName, empl.lastName());
        assertEquals(phone, empl.phone());
        assertTrue(empl.isActive());

    }

}
