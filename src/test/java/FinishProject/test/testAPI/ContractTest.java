package FinishProject.test.testAPI;

import FinishProject.core.cofig.ConfigAPI;
import FinishProject.core.fixture.EmployeeServiceResolver;
import FinishProject.core.fixture.ServiceDbResolver;
import FinishProject.core.model.CreateEmployeeRequest;
import FinishProject.core.model.Employee;
import FinishProject.core.serviceAPI.EmployeeService;
import FinishProject.core.serviceDB.ServiceDB;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import org.aeonbits.owner.ConfigCache;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.validation.constraints.Positive;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith({ServiceDbResolver.class, EmployeeServiceResolver.class})
public class ContractTest {

    private static String URL;
    private static ObjectMapper mapper;

    @BeforeAll
    public static void getURL() {
        ConfigAPI config = ConfigCache.getOrCreate(ConfigAPI.class);
        URL = config.employeeURL();
        mapper = new ObjectMapper();
    }

    @Test
    @DisplayName("Проверка метода, который возвращает всех сотрудников компании")
    @Positive
    public void testGetAllEmployeeInCompany(ServiceDB serviceDB, EmployeeService serviceEmpl) throws SQLException {
        Faker faker = new Faker(Locale.ENGLISH);

        int companyID = serviceEmpl.getCompanyID();
        String firstName1 = faker.name().firstName();
        String lastName1 = faker.name().lastName();
        String phone1 = faker.phoneNumber().cellPhone();
        String firstName2 = faker.name().firstName();
        String lastName2 = faker.name().lastName();
        String phone2 = faker.phoneNumber().cellPhone();

        int empID1 = serviceDB.createEmployee(firstName1, lastName1, companyID, phone1);
        int empID2 = serviceDB.createEmployee(firstName2, lastName2, companyID, phone2);

        List<Employee> listEmp = given()
                .queryParam("company", companyID)
                .get(URL)
                .then()
                .statusCode(200)
                .extract().jsonPath().getList("", Employee.class);

        assertEquals(2, listEmp.size());

        assertEquals(empID1, listEmp.getFirst().id());
        assertEquals(firstName1, listEmp.getFirst().firstName());
        assertEquals(lastName1, listEmp.getFirst().lastName());
        assertEquals(phone1, listEmp.getFirst().phone());
        assertEquals(companyID, listEmp.getFirst().companyId());
        assertTrue(listEmp.getFirst().isActive());

        assertEquals(empID2, listEmp.get(1).id());
        assertEquals(firstName2, listEmp.get(1).firstName());
        assertEquals(lastName2, listEmp.get(1).lastName());
        assertEquals(phone2, listEmp.get(1).phone());
        assertEquals(companyID, listEmp.get(1).companyId());
        assertTrue(listEmp.get(1).isActive());
    }

    @Test
    public void testAddEmployeeInCompany(ServiceDB serviceDB, EmployeeService serviceEmpl) throws JsonProcessingException, SQLException {
        Faker faker = new Faker(Locale.ENGLISH);

        int companyID = serviceEmpl.getCompanyID();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String phone = faker.phoneNumber().cellPhone();

        String json = mapper.writeValueAsString(new CreateEmployeeRequest(firstName, lastName, companyID, phone));

        int emplID = given()
                .header("x-client-token", serviceEmpl.getToken())
                .body(json).contentType(ContentType.JSON)
                .post(URL)
                .then()
                .statusCode(201)
                .extract().path("id");

        Employee employee = serviceDB.getEmployeeByID(emplID);

        assertEquals(employee.companyId(), companyID);
        assertEquals(employee.firstName(), firstName);
        assertEquals(employee.lastName(), lastName);
        assertEquals(employee.phone(), phone);
        assertTrue(employee.isActive());
    }

}
