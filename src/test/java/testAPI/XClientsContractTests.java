package testAPI;

import Practice.OkHttp.AuthService;
import Practice.OkHttp.EmployeeService;
import Practice.OkHttp.Model.Employee;
import Practice.ServiceDB.ServiceDB;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class XClientsContractTests {

    private final String LOGIN = "leonardo";
    private final String PASSWORD = "leads";
    private final String URL = "http://51.250.26.13:8083/employee";

    private final String CONNECTION_URL = "jdbc:postgresql://51.250.26.13/pg-x-clients-be";
    private final String CONNECTION_USERNAME = "merionpg";
    private final String CONNECTION_PASSWORD = "UZObS42{8>}>";

    private static ServiceDB serviceDB;
    private String token;
    private int companyID;

    private final OkHttpClient client = new OkHttpClient();
    private final ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void setUP() throws IOException, SQLException {
        token = new AuthService(LOGIN, PASSWORD).getToken();
        if (serviceDB == null) {
            serviceDB = new ServiceDB(CONNECTION_URL, CONNECTION_USERNAME, CONNECTION_PASSWORD);
        }
        companyID = serviceDB.createCompany("Test", "For test");
    }

    @AfterEach
    public void deleteCompany() throws SQLException {
        serviceDB.deleteCompany(companyID);
    }

    @AfterAll
    public static void closeConnection() throws SQLException {
        serviceDB.closeConnection();
    }

    @Test
    public void testGetAllEmployeeInCompany() throws IOException {
        HttpUrl url = HttpUrl.parse(URL).newBuilder()
                .addQueryParameter("company", String.valueOf(companyID))
                .build();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        String respBody = response.body().string();

        assertEquals(200, response.code());
        assertTrue(respBody.startsWith("["));
        assertTrue(respBody.endsWith("]"));
    }

    @Test
    public void testCreateNewEmployee() throws IOException {
        String json = "{ \"firstName\": \"Иван\", \"lastName\": \"Иванов\", \"companyId\": " + companyID + ", \"phone\": \"89994446767\"}";
        RequestBody reqBody = RequestBody.create(json, MediaType.get("application/json"));

        Request request = new Request.Builder()
                .url(URL)
                .header("x-client-token", token)
                .post(reqBody)
                .build();

        Response response = client.newCall(request).execute();
        JsonNode respBody = mapper.readTree(response.body().string());
        int emplID = Integer.parseInt(respBody.get("id").asText());

        assertEquals(201, response.code());
        assertTrue(emplID > 0);
    }

    @Test
    public void testCreateNewEmployeeUnauthorized() throws IOException {
        String json = "{ \"firstName\": \"Иван\", \"lastName\": \"Иванов\", \"companyId\": " + companyID + ", \"phone\": \"89994446767\"}";
        RequestBody reqBody = RequestBody.create(json, MediaType.get("application/json"));

        Request request = new Request.Builder()
                .url(URL)
                .post(reqBody)
                .build();

        Response response = client.newCall(request).execute();
        String respBody = response.body().string();

        assertEquals(401, response.code());
        assertEquals("{\"statusCode\":401,\"message\":\"Unauthorized\"}", respBody);
    }

    @Test
    public void testGetEmployeeByID () throws IOException {
        String firstname = "Иван";
        String lastname = "Иванов";
        String phone = "89994446464";
        int emplID = new EmployeeService(token, companyID).addEmployee(firstname, lastname, phone);

        HttpUrl url = HttpUrl.parse(URL).newBuilder()
                .addPathSegment(String.valueOf(emplID))
                .build();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        Employee employee = mapper.readValue(response.body().string(), Employee.class);

        assertEquals(200, response.code());
        assertEquals(emplID, employee.id());
        assertEquals(companyID, employee.companyId());
        assertEquals(firstname, employee.firstName());
        assertEquals(lastname, employee.lastName());
        assertEquals(phone, employee.phone());
    }

    @Test
    public void testGetNONEmployeeByID () throws IOException {
        HttpUrl url = HttpUrl.parse(URL).newBuilder()
                .addPathSegment("0")
                .build();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        assertEquals(404, response.code());
    }

    @Test
    public void testUpdateEmployee() throws IOException {
        String firstname = "Иван";
        String lastname = "Иванов";
        String newLastname = "Test";
        String phone = "89994446464";
        EmployeeService emplService = new EmployeeService(token, companyID);
        int emplID = emplService.addEmployee(firstname, lastname, phone);

        HttpUrl url = HttpUrl.parse(URL).newBuilder()
                .addPathSegment(String.valueOf(emplID))
                .build();

        String json = "{\"lastName\": \"" + newLastname + "\"}";
        RequestBody reqBody = RequestBody.create(json, MediaType.get("application/json"));

        Request request = new Request.Builder()
                .url(url)
                .header("x-client-token", token)
                .patch(reqBody)
                .build();

        Response response = client.newCall(request).execute();
        JsonNode respBody = mapper.readTree(response.body().string());

        assertEquals(200, response.code());
        assertEquals(emplID, respBody.get("id").asInt());
        assertEquals(newLastname, emplService.getEmployeeByID(emplID).lastName());
    }

    @Test
    public void testUpdateEmployeeUnauthorized() throws IOException {
        String firstname = "Иван";
        String lastname = "Иванов";
        String phone = "89994446464";
        EmployeeService emplService = new EmployeeService(token, companyID);
        int emplID = emplService.addEmployee(firstname, lastname, phone);

        HttpUrl url = HttpUrl.parse(URL).newBuilder()
                .addPathSegment(String.valueOf(emplID))
                .build();

        String json = "{\"lastName\": \"Test\"}";
        RequestBody reqBody = RequestBody.create(json, MediaType.get("application/json"));

        Request request = new Request.Builder()
                .url(url)
                .patch(reqBody)
                .build();

        Response response = client.newCall(request).execute();

        assertEquals(401, response.code());
        assertEquals("{\"statusCode\":401,\"message\":\"Unauthorized\"}", response.body().string());
        assertEquals(lastname, emplService.getEmployeeByID(emplID).lastName());
    }

    @Test
    public void testUpdateNONEmployee() throws IOException {
        HttpUrl url = HttpUrl.parse(URL).newBuilder()
                .addPathSegment("0")
                .build();

        String json = "{\"lastName\": \"Test\"}";
        RequestBody reqBody = RequestBody.create(json, MediaType.get("application/json"));

        Request request = new Request.Builder()
                .url(url)
                .header("x-client-token", token)
                .patch(reqBody)
                .build();

        Response response = client.newCall(request).execute();

        assertEquals(500, response.code());
        assertEquals("{\"statusCode\":500,\"message\":\"Internal server error\"}", response.body().string());
    }

}
