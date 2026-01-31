package FinishProject.test.testUI;

import FinishProject.core.fixture.EmployeeServiceResolver;
import FinishProject.core.serviceAPI.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.IOException;

@ExtendWith(EmployeeServiceResolver.class)
public class EmployeeTest {

    @Test
    public void testCreateEmployee(EmployeeService emplService) throws IOException {
        int emplID = emplService.createEmployee("Jon", "Test", "+79998887766");
        System.out.println(emplID);
    }

}
