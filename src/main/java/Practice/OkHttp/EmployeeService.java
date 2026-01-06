package Practice.OkHttp;

import Practice.OkHttp.Model.CreateNewEmployeeRequest;
import Practice.OkHttp.Model.CreateNewEmployeeResponse;
import Practice.OkHttp.Model.GetAllEmployeeResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;

import java.io.IOException;
import java.util.List;

public class EmployeeService {

    private final OkHttpClient client = new OkHttpClient();
    private final ObjectMapper mapper = new ObjectMapper();

    private final String URL = "http://51.250.26.13:8083/employee";
    private final String token;
    private final String companyID;

    public EmployeeService(String token, String companyID) {
        this.token = token;
        this.companyID = companyID;
    }

    public String addEmployee(String firstname, String lastname, String phone) throws IOException {
        CreateNewEmployeeRequest employee = new CreateNewEmployeeRequest(firstname, lastname, Integer.parseInt(companyID), phone);
        String json = mapper.writeValueAsString(employee);

        RequestBody reqBody = RequestBody.create(json, MediaType.get("application/json"));
        Request request = new Request.Builder()
                .url(URL)
                .header("x-client-token", token)
                .post(reqBody)
                .build();

        Response response = client.newCall(request).execute();
        return mapper.readValue(response.body().string(), CreateNewEmployeeResponse.class).id();
    }

    public List<GetAllEmployeeResponse> getAllEmployee() throws IOException {
        HttpUrl url = HttpUrl.parse(URL).newBuilder()
                .addQueryParameter("company", companyID)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return mapper.readValue(response.body().string(), new TypeReference<List<GetAllEmployeeResponse>>() {});
    }

    public int updateEmployeeByParameter(String employeeID, String parameter, String value) throws IOException {
        HttpUrl url = HttpUrl.parse(URL).newBuilder()
                .addPathSegment(employeeID)
                .build();

        String json = "{\""+parameter+"\":\""+value+"\"}";
        RequestBody reqBody = RequestBody.create(json, MediaType.get("application/json"));

        Request request = new Request.Builder()
                .url(url)
                .header("x-client-token", token)
                .patch(reqBody)
                .build();

        Response response = client.newCall(request).execute();
        return response.code();
    }

    public int deactivationEmployee(String employeeID) throws IOException {
        HttpUrl url = HttpUrl.parse(URL).newBuilder()
                .addPathSegment(employeeID)
                .build();

        String json = "{\"isActive\":\"false\"}";
        RequestBody reqBody = RequestBody.create(json, MediaType.get("application/json"));

        Request request = new Request.Builder()
                .url(url)
                .header("x-client-token", token)
                .patch(reqBody)
                .build();

        Response response = client.newCall(request).execute();
        return response.code();
    }

    public int updateLastName(String employeeID, String newLastName) throws IOException {
        return updateEmployeeByParameter(employeeID, "lastName", newLastName);
    }

    public int updateEmail(String employeeID, String newEmail) throws IOException {
        return updateEmployeeByParameter(employeeID, "email", newEmail);
    }

    public int updateURL(String employeeID, String newURL) throws IOException {
        return updateEmployeeByParameter(employeeID, "url", newURL);
    }

    public int updatePhone(String employeeID, String newPhone) throws IOException {
        return updateEmployeeByParameter(employeeID, "phone", newPhone);
    }

    public String getEmployeeByID(String employeeID) throws IOException {
        HttpUrl url = HttpUrl.parse(URL).newBuilder()
                .addPathSegment(employeeID)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

}
