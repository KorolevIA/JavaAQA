package testAPI;

import Practice.OkHttp.Model.Employee;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class XClientsRestAssuredTests {

    private final String URL = "http://51.250.26.13:8083";
    private final String URL_LOGIN = "/auth/login";
    private final String URL_COMPANY = "/company";
    private final String URL_EMPLOYEE = "/employee";

    private final String LOGIN = "leonardo";
    private final String PASSWORD = "leads";

    @Test
    public void testCreateEmployeeInCompany() {
        String firstname = "Иван";
        String lastname = "Иванов";
        String phone = "+79994263377";
        int companyID = createCompany();

        String json = "{\"firstName\": \"" + firstname + "\"," +
                "\"lastName\": \"" + lastname + "\"," +
                "\"companyId\": " + companyID + "," +
                "\"phone\": \"" + phone + "\"}";

        given()
                .header("x-client-token", getToken())
                .body(json).contentType(ContentType.JSON)
                .when()
                .post(URL + URL_EMPLOYEE)
                .then()
                .statusCode(201)
                .body("id", is(greaterThan(0)));
    }

    @Test
    public void testGetAllEmployeeInCompany() {
        String firstname_1 = "Иван";
        String lastname_1 = "Иванов";
        String phone_1 = "+79854561737";
        String firstname_2 = "Сергей";
        String lastname_2 = "Петров";
        String phone_2 = "+79996004433";
        int companyID = createCompany();

        int ID_1 = addEmployee(firstname_1, lastname_1, companyID, phone_1);
        int ID_2 = addEmployee(firstname_2, lastname_2, companyID, phone_2);

        List<Employee> listEmpl = given()
                .queryParam("company", companyID)
                .when()
                .get(URL + URL_EMPLOYEE)
                .then()
                .statusCode(200)
                .extract().jsonPath().getList("", Employee.class);

        assertEquals(2, listEmpl.size());

        assertEquals(ID_1, listEmpl.getFirst().id());
        assertEquals(companyID, listEmpl.getFirst().companyId());
        assertEquals(firstname_1, listEmpl.getFirst().firstName());
        assertEquals(lastname_1, listEmpl.getFirst().lastName());
        assertEquals(phone_1, listEmpl.getFirst().phone());

        assertEquals(ID_2, listEmpl.get(1).id());
        assertEquals(companyID, listEmpl.get(1).companyId());
        assertEquals(firstname_2, listEmpl.get(1).firstName());
        assertEquals(lastname_2, listEmpl.get(1).lastName());
        assertEquals(phone_2, listEmpl.get(1).phone());
    }

    @Test
    public void testGetEmployeeByID() {
        String firstname = "Иван";
        String lastname = "Иванов";
        String phone = "+79854561737";
        int companyID = createCompany();

        int emplID = addEmployee(firstname, lastname, companyID, phone);

        Employee empl = given()
                .get(URL + URL_EMPLOYEE + "/{id}", emplID)
                .then()
                .statusCode(200)
                .extract().jsonPath().getObject("", Employee.class);

        assertEquals(emplID, empl.id());
        assertEquals(companyID, empl.companyId());
        assertEquals(firstname, empl.firstName());
        assertEquals(lastname, empl.lastName());
        assertEquals(phone, empl.phone());
    }

    @Test
    public void testUpdateEmployeeParameter() {
        String firstname = "Иван";
        String lastname = "Иванов";
        String newLastname = "Test";
        String phone = "+79854561737";
        int companyID = createCompany();

        int emplID = addEmployee(firstname, lastname, companyID, phone);

        String json = "{\"lastName\": \"" + newLastname + "\"}";

        given()
                .header("x-client-token", getToken())
                .body(json).contentType(ContentType.JSON)
                .patch(URL + URL_EMPLOYEE + "/{id}", emplID)
                .then()
                .statusCode(200)
                .body("id", equalTo(emplID));
    }

    private String getToken() {
       String json = "{\"username\": \"" + LOGIN + "\", \"password\": \"" + PASSWORD + "\"}";

       return given()
               .body(json).contentType(ContentType.JSON)
               .when()
               .post(URL + URL_LOGIN)
               .then()
               .extract().path("userToken");
   }

   private int createCompany() {
        String json = """
                {
                  "name": "Test",
                  "description": "For test"
                }
                """;

        return given()
                .header("x-client-token", getToken())
                .body(json).contentType(ContentType.JSON)
                .when()
                .post(URL + URL_COMPANY)
                .then()
                .extract().path("id");
   }

   private int addEmployee(String firstname, String lastname, int companyID, String phone) {
       String json = "{\"firstName\": \"" + firstname + "\"," +
               "\"lastName\": \"" + lastname + "\"," +
               "\"companyId\": " + companyID + "," +
               "\"phone\": \"" + phone + "\"}";

       return given()
               .header("x-client-token", getToken())
               .body(json).contentType(ContentType.JSON)
               .when()
               .post(URL + URL_EMPLOYEE)
               .then()
               .extract().path("id");
   }

}
